package util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateProvider {

    public static String getCurrentTime() {

        LocalDateTime localDateTime = LocalDateTime.now();

        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME)
                .split("\\.")[0];

    }
}
