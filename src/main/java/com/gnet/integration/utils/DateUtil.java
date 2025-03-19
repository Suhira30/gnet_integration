package com.gnet.integration.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Slf4j
@Lazy
@Scope("prototype")
public class DateUtil {
    private DateUtil() {
    }

    public static String getSmartziDateTime(XMLGregorianCalendar clientDateTime) {
        // Convert XMLGregorianCalendar to LocalDateTime
        LocalDateTime localDateTime;
        if (clientDateTime != null) {
            localDateTime = clientDateTime.toGregorianCalendar()
                    .toZonedDateTime()
                    .toLocalDateTime();
        } else {
            localDateTime = LocalDateTime.now();
        }
        // Define the desired output format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        // Format the LocalDateTime object to the desired format
        return localDateTime.format(formatter);
    }

    public static String getSmartziDateTime(String clientDateTime) {
        // Parse the input date string
        OffsetDateTime dateTime = OffsetDateTime.parse(clientDateTime);

        // Define the output format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        // Format the date
        return dateTime.format(formatter);
    }

    public static String getSmartziCurrentDateTime() {
        // Get the current date and time in the Europe/London timezone
        ZonedDateTime londonTime = ZonedDateTime.now(ZoneId.of("Europe/London"));

        // Define the desired date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        // Format the date and time
        return londonTime.format(formatter);
    }

    public static String getCurrentDateTime() {
        // Define the desired date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Get the current date and time in UTC
        ZonedDateTime currentUTC = ZonedDateTime.now(ZoneId.of("UTC"));

        // Format the date and time
        return currentUTC.format(formatter);
    }

    public static String getExpiryTime(int minutesToAdd) {
        // Define the desired date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Get the current date and time in the Europe/London timezone and add specified minutes
        ZonedDateTime expiryTime = ZonedDateTime.now(ZoneId.of("UTC")).plusMinutes(minutesToAdd);

        // Format the date and time
        return expiryTime.format(formatter);
    }

    // Method to parse the String into LocalDateTime
    public static LocalDateTime parseDateTime(String dateTime) {
        // Define the date-time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return LocalDateTime.parse(dateTime, formatter);
    }

    // Method to check if currentTime is before sessionExpiryTime
    public static boolean isBeforeExpiryTime(String sessionExpiryTime) {
        // Parse both times to LocalDateTime
        LocalDateTime currentUTC = parseDateTime(DateUtil.getCurrentDateTime());
        LocalDateTime tokenTimeUTC = parseDateTime(sessionExpiryTime);

        // Return true if current time is before the expiry time
        log.info("isBeforeExpiryTime currentUTC {}, tokenTimeUTC {} ", currentUTC, tokenTimeUTC);
        boolean result = currentUTC.isBefore(tokenTimeUTC);
        log.info("isBeforeExpiryTime result {} ", result);
        return result;
    }

    public static long getCurrentUTCUNIXTime() {
        // in UTC format
        return ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond();
    }

    public static long getCurrentDateUNIXTime() {
        // YYYY-MM-DD
        return LocalDate.now(ZoneOffset.UTC).atStartOfDay(ZoneOffset.UTC).toEpochSecond();
    }
}
