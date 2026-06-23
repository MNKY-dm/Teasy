package utils;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TypeConverter {

    public static float StringToFloat(String s) {
        return Float.parseFloat(s);
    }

    public static String dateFormat(Timestamp date) {
        System.out.println("MyProfileController : dateFormat --> " + date);

        if (date == null) {
            return "Date inconnue";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return date.toLocalDateTime().format(formatter);
    }
}
