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
   
   
   private String dateFormat = "dd/mm yyyy";
   
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
   
   // Presents the user with a list of all the bookings
   public static void showList(ArrayList<Booking> bookings){
      for(int i = 0; i < bookings.size(); i++){
         System.out.printf("%d - %s%n", i+1, bookings.get(i).getName());
      }
   }
   
   private void setStartDate(LocalDate newStartDate){
      this.startDate = newStartDate;
      this.numberOfDays = DateHelper.getDays(this.startDate, this.endDate);
   }

   private void setEndDate(LocalDate newEndDate){
      this.endDate = newEndDate;
      this.numberOfDays = DateHelper.getDays(this.startDate, this.endDate);
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
   
   // Lets the user edit a value in a case
   public void edit(ArrayList<Guest> guests){
      Scanner scanInput = new Scanner(System.in);
      System.out.println("What do you want to edit?\n");
      
      System.out.printf("%d - Start Date (%s)%n", 1, DateHelper.dateToString(startDate));
      System.out.printf("%d - End Date (%s)%n", 2, DateHelper.dateToString(endDate));
      System.out.printf("%d - Room ID (%d)%n", 3, roomID);
      System.out.printf("%d - Guest ID (%s)%n", 4, guestID);
      
      int option = scanInput.nextInt();
      scanInput.nextLine(); // Eating leftover newline
      LocalDate newDate;
      switch(option){
         case 1: // New start date
            System.out.printf("Current date is %s. What do you want to change it to? Write it as %s%n",
               DateHelper.dateToString(startDate), dateFormat);
            // Getting new date from user
            newDate = DateHelper.getValidDateFromUser();
            // If new date is after end date (imposssible) ask again
            while(!newDate.isBefore(endDate)){
               System.out.printf("The new start date (%s) must be before the end date (%s). Please try again.%n",
                  DateHelper.dateToString(newDate), DateHelper.dateToString(endDate));
               newDate = DateHelper.getValidDateFromUser();
            }
            // Valid date has now been found
            setStartDate(newDate);
            System.out.printf("The start date has been updated to %s%n", DateHelper.dateToString(startDate));   
            
         case 2: // New end date
            System.out.printf("Current date is %s. What do you want to change it to? Write it as %s%n",
               DateHelper.dateToString(endDate), dateFormat);
            // Getting new date from user
            newDate = DateHelper.getValidDateFromUser();
            // If new date is before start date (impossible) ask again
            while(newDate.isBefore(endDate)){
               System.out.printf("The new end date (%s) must be after the start date (%s). Please try again.%n",
                  DateHelper.dateToString(newDate), DateHelper.dateToString(endDate));
               newDate = DateHelper.getValidDateFromUser();
            }
            // Valid date has now been found
            setEndDate(newDate);
            System.out.printf("The end date has been updated to %s%n", DateHelper.dateToString(endDate));
            
         case 3: // Room ID
            System.out.printf("Please enter in a new room ID. Must be three digits long where the first digit represents the floor%n");
            // Getting input and validating
            int newID = getIntFromUser();
            
            while(String.valueOf(newID).length() != 3){
               System.out.printf("The number %d is not 3 digits long. Please try again.%n", newID);
               newID = getIntFromUser();
            }
            this.roomID = newID;
            System.out.printf("The room ID has been updated to %d%n", this.roomID);
            
         case 4: // Guest ID   
            System.out.printf("Guest ID (%s) can only be changed to a guest ID currently assigned to a customer. Please select a customer.%n", this.roomID);
            Guest.showList(guests);
            option = scanInput.nextInt() - 1;
            scanInput.nextLine();
            if(option != -1 && option < guests.size()){ // If valid option chosen
               this.guestID = guests.get(option).getGuestID();
            }
            System.out.printf("Guest ID for has been updated to %s%n", this.guestID);
         }
   }
   
   private int getIntFromUser(){
      Scanner scanInput = new Scanner(System.in);
      while(!scanInput.hasNextInt()){
         System.out.printf("The input '%s' is not a number. Please try again.%n", scanInput.nextLine());
         continue;
      }
      int retVal = scanInput.nextInt();
      scanInput.nextLine(); // Eating leftover newline
      return retVal;
   }

}