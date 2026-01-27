package com.mipres.scheduled;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mipres.dto.ReporteEntregaDTO;
import com.mipres.service.ReporteEntregaService;
import com.mipres.utils.UsoFechas;

@Service
public class GetReporteEntregaXFecha {

    private final ReporteEntregaService reporteEntregaService;

    public GetReporteEntregaXFecha(ReporteEntregaService reporteEntregaService) {
        this.reporteEntregaService = reporteEntregaService;
    }

    //@Scheduled(cron = "${scheduled.cron}")
    //@Scheduled(fixedRate = 600000)
    public void getReporteEntregaXFecha() {
        UsoFechas util = new UsoFechas();
        List<String> dates = util.getDatesBetweenTodayAndPast(382);

        for (String date : dates) {
            List<ReporteEntregaDTO> reporteEntregaDTOList = reporteEntregaService.getReporteEntregaByFecha(date);
            reporteEntregaDTOList = reporteEntregaService.crearReporteEntregas(reporteEntregaDTOList);
            System.out.println("CREADOS REPORTES DE ENTREGA: " + reporteEntregaDTOList.size() + " para la fecha: " + date);
        }
        System.out.println("Proceso de creación de reportes de entrega completado");
    }
}
