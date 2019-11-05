import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;
import java.time.format.*;

public class DateHelper{

   private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM yyyy");

   public static boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
    
   public static long getDays(LocalDate startDate, LocalDate endDate){
      return Period.between(startDate, endDate).getDays();
   }
   
   public static LocalDate parseDate(String date){
      return LocalDate.parse(date, formatter);
   }
   
   // Returns the date as dd/MM yyyy
   public static String dateToString(LocalDate date){
      return date.format(formatter);
   }
    
    
}