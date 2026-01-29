package com.mipres.service;

import java.io.IOException;
import java.io.OutputStream;

public interface DireccionamientosExportService {

    void exportPendientesProgramacion(String format, OutputStream outputStream) throws IOException;

    void exportPendientesEntrega(String format, OutputStream outputStream) throws IOException;
}
