package com.mipres.scheduled;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mipres.dto.ProgramacionDTO;
import com.mipres.service.ProgramacionService;
import com.mipres.utils.UsoFechas;

@Service
public class GetProgramacionXFecha {

    private final ProgramacionService programacionService;

    public GetProgramacionXFecha(ProgramacionService programacionService) {
        this.programacionService = programacionService;
    }

    //@Scheduled(cron = "${scheduled.cron}")
    //@Scheduled(fixedRate = 600000)
    public void getProgramacionXFecha() {
        UsoFechas util = new UsoFechas();
        List<String> dates = util.getDatesBetweenTodayAndPast(383);

        for (String date : dates) {
            List<ProgramacionDTO> programacionDTOList = programacionService.getProgramacionByFecha(date);
            programacionDTOList = programacionService.crearProgramaciones(programacionDTOList);
            System.out.println("CREADAS PROGRAMACIONES: " + programacionDTOList.size() + " para la fecha: " + date);
        }
        System.out.println("Proceso de creación de programaciones completado");
    }


}
