package org.serviciosSpei3.controles;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.time.*;

public class DateUtil {
    private static final ZoneId zonlaLocal = ZoneId.of("America/Mexico_City");
    private static final DateTimeFormatter formatoFechaOperacion = DateTimeFormatter.ofPattern("yyyyMMdd");
    //private static final DateTimeFormatter formatoFechaWS = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
    private static final DateTimeFormatter formatoFechaWS = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSXXX");
    public static String[] getTimeRangeForOperacion(String fechaOperacion) {
        try {
            LocalDate fecha = LocalDate.parse(fechaOperacion, formatoFechaOperacion);

            // Determinar el día de la semana con base en la fecha de operación
            DayOfWeek dayOfWeek = fecha.getDayOfWeek();

            // 6:00 PM del día de operación en el horario local
            ZonedDateTime endTime = fecha.atTime(18, 0).atZone(zonlaLocal);
            //endTime = endTime.minusMinutes(2);

            // Determinar el inicio según si es lunes o no
            ZonedDateTime startTime;
            if (dayOfWeek == DayOfWeek.MONDAY) {
                // Si es lunes, regresamos al viernes anterior a las 6:00 PM
                startTime = endTime.minusDays(3); // Restar 3 días para llegar al viernes
            } else {
                // Si no es lunes, regresamos un día atrás
                startTime = endTime.minusDays(1); // Restar 1 día
            }

            return new String[]{
                    startTime.format(formatoFechaWS),
                    endTime.format(formatoFechaWS)
            };
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El formato de fechaOperacion no es válido: " + fechaOperacion, e);
        }
    }
}
