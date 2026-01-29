package com.mipres.reporting;

import java.io.IOException;
import java.io.OutputStream;

@FunctionalInterface
public interface ReportExporter {

    void export(String format, OutputStream outputStream) throws IOException;
}
