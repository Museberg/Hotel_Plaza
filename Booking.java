import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;

public class Booking{
   LocalDate startDate;
   LocalDate endDate;
   long numberOfDays;
   int roomID;
   String guestID;
   
   // Used to print dates nicely
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM yyyy");
  
   
   // Date should be written as DD/MM/YYYY
   public Booking(String startDate, String endDate, int roomID, String guestID) throws ParseException{
      DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      
      this.startDate = LocalDate.parse(startDate, dateFormat);
      this.endDate = LocalDate.parse(endDate, dateFormat);
      this.numberOfDays = Period.between(this.startDate, this.endDate).getDays();
      this.roomID = roomID;
      this.guestID = guestID;
   }
   
   private String startDateToString(){
      return startDate.format(formatter);
   }
   
   private String endDateToString(){
      return endDate.format(formatter);
   }
   
   public String getBookingName(){
      return String.format("Booking of room %d for guest %s (ID)", roomID, guestID);
   }
   
   @Override
   public String toString(){
      return getBookingName() +
         "\nStart date: " + startDateToString() +
         "\nEnd date: " + endDateToString() +
         "\nRoom is booked for: " + numberOfDays + " days" +
         "\nRoomID: " + roomID +
         "\nGuest ID: " + guestID;
   }

}