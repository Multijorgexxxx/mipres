package com.mipres.utils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Component
public class ExportResponseFactory {

    private static final DateTimeFormatter TS_FMT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    public ResponseEntity<StreamingResponseBody> build(String baseName, String format, StreamingResponseBody body) {
        String fmt = (format == null ? "csv" : format.trim().toLowerCase());
        if (!fmt.equals("csv") && !fmt.equals("txt") && !fmt.equals("xlsx")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        MediaType mediaType;
        String extension;
        switch (fmt) {
            case "xlsx" -> {
                mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                extension = "xlsx";
            }
            case "txt" -> {
                mediaType = MediaType.TEXT_PLAIN;
                extension = "txt";
            }
            default -> {
                mediaType = MediaType.parseMediaType("text/csv");
                extension = "csv";
            }
        }

        String fileName = baseName + "_" + TS_FMT.format(LocalDateTime.now()) + "." + extension;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDisposition(ContentDisposition.attachment().filename(fileName, StandardCharsets.UTF_8).build());

        return ResponseEntity.ok().headers(headers).body(body);
    }
}
