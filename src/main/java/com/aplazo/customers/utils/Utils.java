package com.aplazo.customers.utils;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import com.aplazo.customers.constants.ErrorConstants;
import com.aplazo.customers.exception.error.InternalErrorException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class Utils {

    
    /**
     * Generate actual date String format.
     *
     * @param zone date zone.
     * @param format format date.
     * @return String of Instant now.
     * @throws InternalErrorException
     */
    public static String actualDate(Instant fechaActual,String zone, String format) throws InternalErrorException {
        DateTimeFormatter formatter;
        try {
            formatter = DateTimeFormatter.ofPattern(format)
                .withZone(ZoneId.of(zone));
        } catch (NullPointerException|DateTimeException e) {
            throw new InternalErrorException(e.getMessage(), ErrorConstants.ERROR_DETAIL);
        }

        return formatter.format(fechaActual);
    }

    

}
