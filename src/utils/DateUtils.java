package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");
    
    public static String toDatabaseString(java.sql.Date date) {
        String result = null;
        if (date != null) {
            result = DB_DATE_FORMAT.format(new java.util.Date(date.getTime()));
        }
        return result;
    }
    
    public static Date fromDatabaseString(String dateString) {
        Date result = null;
        if (dateString != null && !dateString.isEmpty()) {
            try {
                result = DB_DATE_FORMAT.parse(dateString);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Formato de fecha inválido. Use dd/mm/aa");
            }
        }
        return result;
    }
}