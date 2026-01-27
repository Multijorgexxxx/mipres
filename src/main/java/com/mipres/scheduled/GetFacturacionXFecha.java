package com.mipres.scheduled;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mipres.dto.FacturacionDTO;
import com.mipres.service.FacturacionService;
import com.mipres.utils.UsoFechas;

@Service
public class GetFacturacionXFecha {

    private final FacturacionService facturacionService;

    public GetFacturacionXFecha(FacturacionService facturacionService) {
        this.facturacionService = facturacionService;
    }

    //@Scheduled(cron = "${scheduled.cron}")
    //@Scheduled(fixedRate = 600000)
    public void getFacturacionXFecha() {
        UsoFechas util = new UsoFechas();
        List<String> dates = util.getDatesBetweenTodayAndPast(383);

        for (String date : dates) {
            List<FacturacionDTO> facturacionDTOList = facturacionService.getFacturacionByFecha(date);
            facturacionDTOList = facturacionService.crearFacturaciones(facturacionDTOList);
            System.out.println("CREADAS FACTURACIONES: " + facturacionDTOList.size() + " para la fecha: " + date);
        }
        System.out.println("Proceso de creación de facturaciones completado");
    }
}
