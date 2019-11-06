import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;
import java.time.format.*;

public class DateHelper{

   private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM yyyy");

   private static boolean isValid(String dateStr) {
        try {
            LocalDate retDate = LocalDate.parse(dateStr, formatter);
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
   
   // Asks the user to input a date. Only returns once date in correct format is given
   public static LocalDate getValidDateFromUser(){
      Scanner scan = new Scanner(System.in);
      String date = scan.nextLine();
      
      boolean validDate = isValid(date);
      
      while(!validDate){
         System.out.printf("%s does not match with the format dd/mm yyyy! Please try again.%n", date);
         date = scan.nextLine();
         validDate = isValid(date);
      }
      return parseDate(date);
   }
   
    
    
}