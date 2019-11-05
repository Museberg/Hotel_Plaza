import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;
import java.time.format.*;

public class Booking{
   LocalDate startDate;
   LocalDate endDate;
   long numberOfDays;
   int roomID;
   String guestID;
   
   
   private String dateFormat = "dd/MM yyyy";
   
   // Dates should be written as dd/MM yyyy
   public Booking(String startDate, String endDate, int roomID, String guestID) throws ParseException{
      
      
      this.startDate = DateHelper.parseDate(startDate);
      this.endDate = DateHelper.parseDate(endDate);
      this.numberOfDays = DateHelper.getDays(this.startDate, this.endDate);
      this.roomID = roomID;
      this.guestID = guestID;
   }
   
   public String getName(){
      return String.format("Booking of room %d for guest %s (ID)", roomID, guestID);
   }
   
   @Override
   public String toString(){
      return getName() +
         "\nStart date: " + DateHelper.dateToString(this.startDate) +
         "\nEnd date: " + DateHelper.dateToString(this.endDate) +
         "\nRoom is booked for: " + numberOfDays + " days" +
         "\nRoomID: " + roomID +
         "\nGuest ID: " + guestID;
   }
   
   // Presents the user with a list of all the bookings
   public static void showList(ArrayList<Booking> bookings){
      for(int i = 0; i < bookings.size(); i++){
         System.out.printf("%d - %s%n", i+1, bookings.get(i).getName());
      }
   }
   
   // Lets the user edit a value in a case
   public void edit(){
      Scanner scanInput = new Scanner(System.in);
      System.out.println("What do you want to edit?\n");
      
      System.out.printf("%d - Start Date (%s)%n", 1, DateHelper.dateToString(startDate));
      System.out.printf("%d - End Date (%s)%n", 2, DateHelper.dateToString(endDate));
      System.out.printf("%d - Room ID (%d)%n", 3, roomID);
      System.out.printf("%d - Guest ID (%s)%n", 4, guestID);
      
      int option = scanInput.nextInt();
      scanInput.nextLine(); // Eating leftover newline
      switch(option){
         case 1:
            System.out.printf("Current date is %s. What do you want to change it to? Write it as %s%n",
               DateHelper.dateToString(startDate), dateFormat);
            // Getting new date from user and checking it is given in the correct format
            String newDateString = scanInput.nextLine();
            LocalDate newDate = this.startDate;
            boolean validDate = DateHelper.isValid(newDateString);
            
            while(!validDate){
               System.out.printf("Invalid date. Please write it as %s%n", dateFormat);
               
               newDateString = scanInput.nextLine();
               validDate = DateHelper.isValid(newDateString);
               if(validDate){
                  newDate = DateHelper.parseDate(newDateString);
                  validDate = newDate.isBefore(this.endDate);
               }
            }
            
            // If first date entered was valid, it has not been parsed yet
            this.startDate = DateHelper.parseDate(newDateString);   
      }
   }

}