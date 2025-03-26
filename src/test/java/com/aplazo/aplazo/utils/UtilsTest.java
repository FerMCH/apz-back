package com.aplazo.aplazo.utils;

import org.junit.jupiter.api.Test;

import com.aplazo.aplazo.TestConstants;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.utils.Utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UtilsTest {

    @Test
    void testActualDateWithValidZoneAndFormat() throws InternalErrorException {
        Instant now = Instant.now();
        String result = Utils.actualDate(now, TestConstants.ZONE, TestConstants.FORMAT);

        assertThat(result).isNotNull();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TestConstants.FORMAT)
                .withZone(ZoneId.of(TestConstants.ZONE));
        String expected = formatter.format(now);

        assertThat(result).isEqualTo(expected);
    }

  
    @Test
    void testActualDateWithNullZone() {
        Instant now = Instant.now();
        assertThatThrownBy(() -> Utils.actualDate(now, TestConstants.NULL_ZONE, TestConstants.FORMAT))
                .isInstanceOf(InternalErrorException.class);
    }

    @Test
    void testActualDateZoneRulesException() {
        Instant now = Instant.now();
        assertThatThrownBy(() -> Utils.actualDate(now, TestConstants.CUSTOMER_NOT_FOUND, TestConstants.FORMAT))
                .isInstanceOf(InternalErrorException.class);
    }

}
