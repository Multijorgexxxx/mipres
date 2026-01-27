package com.mipres.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mipres.dto.DireccionamientoDTO;
import com.mipres.dto.EntregaDTO;
import com.mipres.dto.FacturacionDTO;
import com.mipres.dto.ProgramacionDTO;
import com.mipres.dto.ReporteEntregaDTO;
import com.mipres.service.DireccionamientoService;
import com.mipres.service.EntregaService;
import com.mipres.service.FacturacionService;
import com.mipres.service.GenerarTokenFacturaService;
import com.mipres.service.GenerarTokenService;
import com.mipres.service.ProgramacionService;
import com.mipres.service.ReporteEntregaService;

@RestController
public class ControllerTest {


    private final GenerarTokenService generarTokenService;

    private final GenerarTokenFacturaService generarTokenFacturaService;

    private final DireccionamientoService direccionamientoService;

    private final ProgramacionService programacionService;

    private final EntregaService entregaService;

    private final ReporteEntregaService reporteEntregaService;

    private final FacturacionService facturacionService;

    public ControllerTest(GenerarTokenService generarTokenService, GenerarTokenFacturaService generarTokenFacturaService, DireccionamientoService direccionamientoService, ProgramacionService programacionService, EntregaService entregaService, ReporteEntregaService reporteEntregaService, FacturacionService facturacionService) {
        this.generarTokenService = generarTokenService;
        this.generarTokenFacturaService = generarTokenFacturaService;
        this.direccionamientoService = direccionamientoService;
        this.programacionService = programacionService;
        this.entregaService = entregaService;
        this.reporteEntregaService = reporteEntregaService;
        this.facturacionService = facturacionService;
    }

    @GetMapping("/GenerarToken")
    public String getToken() {
        return generarTokenService.getToken();
    }

    @GetMapping("/GenerarTokenFac")
    public String getTokenFac() {
        return generarTokenFacturaService.getToken();
    }

    @GetMapping("/DireccionamientoByFecha/{fecha}")
    public List<DireccionamientoDTO> getDireccionamientoByFecha(@PathVariable String fecha) {

        List<DireccionamientoDTO> addressingResponseList = direccionamientoService.getDireccionamientoByFecha(fecha);

        return addressingResponseList;
    }

    @GetMapping("/DireccionamientoByPrescripcion/{numpres}")
    public List<DireccionamientoDTO> getDireccionamientoByPrescripcion(@PathVariable String numpres) {

        List<DireccionamientoDTO> addressingResponseList = direccionamientoService.getDireccionamientoByPrescripcion(numpres);

        return addressingResponseList;
    }

    
    @GetMapping("/ProgramacionByFecha/{fecha}")
    public List<ProgramacionDTO> getProgramacionByFecha(@PathVariable String fecha) {

        List<ProgramacionDTO> programacionResponseList = programacionService.getProgramacionByFecha(fecha);

        return programacionResponseList;
    }
    
    @GetMapping("/EntregaByFecha/{fecha}")
    public List<EntregaDTO> getEntregaByFecha(@PathVariable String fecha) {

        List<EntregaDTO> entregaResponseList = entregaService.getEntregaByFecha(fecha);

        return entregaResponseList;
    }
    
    @GetMapping("/ReporteEntregaByFecha/{fecha}")
    public List<ReporteEntregaDTO> getReporteEntregaByFecha(@PathVariable String fecha) {

        List<ReporteEntregaDTO> reporteEntregaResponseList = reporteEntregaService.getReporteEntregaByFecha(fecha);

        return reporteEntregaResponseList;
    }
        
    @GetMapping("/FacturacionByFecha/{fecha}")
    public List<FacturacionDTO> getFacturacionByFecha(@PathVariable String fecha) {

        List<FacturacionDTO> facturacionResponseList = facturacionService.getFacturacionByFecha(fecha);

        return facturacionResponseList;
    }
}