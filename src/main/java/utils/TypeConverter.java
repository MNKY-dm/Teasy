package utils;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class TypeConverter {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static float StringToFloat(String s) {
        return Float.parseFloat(s);
    }

    public static String dateFormat(Timestamp date) {
        if (date == null) {
            return "Date inconnue";
        }

        return date.toLocalDateTime().format(DATE_TIME_FORMATTER);
    }
}
