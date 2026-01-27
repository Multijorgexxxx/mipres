package com.mipres.scheduled;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mipres.dto.EntregaDTO;
import com.mipres.service.EntregaService;
import com.mipres.utils.UsoFechas;

@Service
public class GetEntregaXFecha {

    private final EntregaService entregaService;

    public GetEntregaXFecha(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    //@Scheduled(cron = "${scheduled.cron}")
    //@Scheduled(fixedRate = 600000)
    public void getEntregaXFecha() {
        UsoFechas util = new UsoFechas();
        List<String> dates = util.getDatesBetweenTodayAndPast(383);

        for (String date : dates) {
            List<EntregaDTO> entregaDTOList = entregaService.getEntregaByFecha(date);
            entregaDTOList = entregaService.crearEntregas(entregaDTOList);
            System.out.println("CREADAS ENTREGAS: " + entregaDTOList.size() + " para la fecha: " + date);
        }
        System.out.println("Proceso de creación de entregas completado");
    }
}
