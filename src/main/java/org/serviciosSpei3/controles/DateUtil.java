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
            DayOfWeek dayOfWeek = fecha.getDayOfWeek();
            ZonedDateTime endTime = fecha.atTime(18, 0).atZone(zonlaLocal);
            ZonedDateTime startTime;
            if (dayOfWeek == DayOfWeek.MONDAY) {
                startTime = endTime.minusDays(3);
            } else {
                startTime = endTime.minusDays(1);
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
