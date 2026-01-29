package com.mipres.reporting;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class TabularExportEngine {

    private static final DateTimeFormatter DATE_TIME_FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @FunctionalInterface
    public interface PageSupplier<T> {
        Page<T> get(Pageable pageable);
    }

    public <T> void export(
            String format,
            OutputStream outputStream,
            Class<T> clazz,
            PageSupplier<T> pageSupplier,
            int pageSize,
            String sortProperty) throws IOException {

        String fmt = (format == null ? "csv" : format.trim().toLowerCase());

        Map<String, Field> fields = orderedFields(clazz);
        List<String> headers = new ArrayList<>(fields.keySet());

        if ("xlsx".equals(fmt)) {
            exportXlsx(outputStream, pageSupplier, fields, headers, pageSize, sortProperty);
            return;
        }

        String delimiter = "csv".equals(fmt) ? "," : "\t";
        exportDelimited(outputStream, pageSupplier, fields, headers, pageSize, delimiter, sortProperty);
    }

    private <T> void exportDelimited(
            OutputStream outputStream,
            PageSupplier<T> pageSupplier,
            Map<String, Field> fields,
            List<String> headers,
            int pageSize,
            String delimiter,
            String sortProperty) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            writeDelimitedRow(writer, headers, delimiter);

            int page = 0;
            while (true) {
                Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortProperty).ascending());
                Page<T> result = pageSupplier.get(pageable);
                if (result.isEmpty()) {
                    break;
                }
                for (T rowObj : result.getContent()) {
                    List<String> values = new ArrayList<>(headers.size());
                    for (String h : headers) {
                        Field f = fields.get(h);
                        values.add(toFlatString(getFieldValue(f, rowObj)));
                    }
                    writeDelimitedRow(writer, values, delimiter);
                }
                writer.flush();
                if (!result.hasNext()) {
                    break;
                }
                page++;
            }
        }
    }

    private void writeDelimitedRow(BufferedWriter writer, List<String> values, String delimiter) throws IOException {
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) {
                writer.write(delimiter);
            }
            writer.write(escapeCsvIfNeeded(values.get(i), delimiter));
        }
        writer.newLine();
    }

    private String escapeCsvIfNeeded(String s, String delimiter) {
        if (s == null) {
            return "";
        }
        boolean isCsv = ",".equals(delimiter);
        if (!isCsv) {
            return s.replace("\r", " ").replace("\n", " ");
        }

        String normalized = s.replace("\r", " ").replace("\n", " ");
        boolean needsQuotes = normalized.contains(",") || normalized.contains("\"");
        if (!needsQuotes) {
            return normalized;
        }
        String escaped = normalized.replace("\"", "\"\"");
        return "\"" + escaped + "\"";
    }

    private <T> void exportXlsx(
            OutputStream outputStream,
            PageSupplier<T> pageSupplier,
            Map<String, Field> fields,
            List<String> headers,
            int pageSize,
            String sortProperty) throws IOException {

        try (SXSSFWorkbook wb = new SXSSFWorkbook(100)) {
            Sheet sheet = wb.createSheet("export");

            int rowIdx = 0;
            Row headerRow = sheet.createRow(rowIdx++);
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers.get(i));
            }

            int page = 0;
            while (true) {
                Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortProperty).ascending());
                Page<T> result = pageSupplier.get(pageable);
                if (result.isEmpty()) {
                    break;
                }

                for (T rowObj : result.getContent()) {
                    Row row = sheet.createRow(rowIdx++);
                    for (int i = 0; i < headers.size(); i++) {
                        Field f = fields.get(headers.get(i));
                        String value = toFlatString(getFieldValue(f, rowObj));
                        row.createCell(i).setCellValue(value);
                    }
                }

                if (!result.hasNext()) {
                    break;
                }
                page++;
            }

            wb.write(outputStream);
        }
    }

    private Map<String, Field> orderedFields(Class<?> clazz) {
        Map<String, Field> map = new LinkedHashMap<>();
        for (Field f : clazz.getDeclaredFields()) {
            f.setAccessible(true);
            map.put(f.getName(), f);
        }
        return map;
    }

    private Object getFieldValue(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    private String toFlatString(Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof java.time.LocalDateTime ldt) {
            return DATE_TIME_FMT.format(ldt);
        }
        return String.valueOf(value);
    }
}
