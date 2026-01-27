package com.mipres.scheduled;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mipres.dto.DireccionamientoDTO;
import com.mipres.service.DireccionamientoService;
import com.mipres.utils.UsoFechas;

@Service
public class GetDireccionamientosXFecha {

    private final DireccionamientoService direccionamientoService;

    public GetDireccionamientosXFecha(DireccionamientoService direccionamientoService) {
        this.direccionamientoService = direccionamientoService;
    }

    //@Scheduled(cron = "${scheduled.cron}")
    //@Scheduled(fixedRate = 600000)
    public void getDireccionamientosXFecha() {
        UsoFechas util = new UsoFechas();
        List<String> dates = util.getDatesBetweenTodayAndPast(18);

        for (String date : dates) {
            List<DireccionamientoDTO> direccionamientoDTOList = direccionamientoService.getDireccionamientoByFecha(date);
            direccionamientoDTOList = direccionamientoService.crearDireccionamientos(direccionamientoDTOList);
            System.out.println("CREADOS DIRECCIONAMIENTOS: " + direccionamientoDTOList.size() + " para la fecha: " + date);
        }
        System.out.println("Proceso de creación de direccionamientos completado");
    }
}
