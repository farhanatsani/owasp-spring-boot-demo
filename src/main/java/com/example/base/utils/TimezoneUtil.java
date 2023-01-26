package com.example.base.utils;

import java.time.ZoneId;

public class TimezoneUtil {

    private TimezoneUtil() {}

    public static ZoneId getZoneIdJakarta() {
        ZoneId jakartaTimezone = ZoneId.of("Asia/Jakarta");
        return jakartaTimezone;
    }

}
