package org.db1.api.domain.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeUtils {
    private LocalDateTimeUtils() {}

    public static LocalDateTime setSaoPauloDateTime() {
        return LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}
