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
   
   
   private static String dateFormat = "dd/mm yyyy";
   
   // Dates should be written as dd/MM yyyy
   public Booking(LocalDate startDate, LocalDate endDate, int roomID, String guestID) throws ParseException{
      
      
      this.startDate = startDate;
      this.endDate = endDate;
      this.numberOfDays = DateHelper.getDays(this.startDate, this.endDate);
      this.roomID = roomID;
      this.guestID = guestID;
   }
   
   public String getBookingInSaveFormat(){
      return String.format("%s, %s, %d, %s",
         DateHelper.dateToString(startDate),
         DateHelper.dateToString(endDate),
         this.roomID,
         this.guestID);
   }
   
   public String getName(){
      return String.format("Booking for guest %s", this.guestID);
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
   
   public String toString(Guest guest){
      return "\nStart date: " + DateHelper.dateToString(this.startDate) +
         "\nEnd date: " + DateHelper.dateToString(this.endDate) +
         "\nRoom is booked for: " + numberOfDays + " days" +
         "\nRoomID: " + roomID +
         "\nGuest ID: " + guestID + String.format(" (%s)%n", guest.getName());
   }
   
   public static Booking letUserCreateBooking(ArrayList<Room> rooms, ArrayList<Guest> guests) throws ParseException{
      Scanner scan = new Scanner(System.in);
      
      System.out.printf("What's the start date of the booking? Write as %s%n", dateFormat);
      LocalDate startDate = DateHelper.getValidDateFromUser();
      
      System.out.printf("What's the end date of the booking? Write as %s%n", dateFormat);
      LocalDate endDate = DateHelper.getValidDateFromUser();
      while(!startDate.isBefore(endDate)){
         System.out.printf("Start date (%s) must be before end date (%s)! Please try again.%n",
            DateHelper.dateToString(startDate), DateHelper.dateToString(endDate));
         endDate = DateHelper.getValidDateFromUser();
      }
      
      int roomID = -1;
      System.out.printf("Which room should be booked?%n");
      Room.showList(rooms); // Showing list of rooms to book
      System.out.printf("%d to create a new room%n", 0);
      int option = Menu.getOptionFromUser(0, rooms.size()) - 1;
      if(option == -1){
         System.out.println("NOT IMPLEMENTED YET! Selecting first room on list");
         option = 0;
      }
      roomID = rooms.get(option).getRoomID();
      
      System.out.printf("Which guest is booking the room?%n");
      Guest.showList(guests);
      System.out.printf("%d to create a new guest%n", 0); // TODO: Imeplement
      option = Menu.getOptionFromUser(0, guests.size()) - 1;
      if(option == -1){
         System.out.println("NOT IMPLEMENTED YET! Selecting first guest on list");
         option = 0;
      }
      String guestID = guests.get(option).getGuestID();
      Booking newBooking = new Booking(startDate, endDate, roomID, guestID);   
      System.out.printf("Following booking has now been created:%n%s", newBooking.toString(guests.get(option)));
      return newBooking;
   }
   
   // Lets the user edit a value in a booking
   public void edit(ArrayList<Guest> guests){
      Scanner scanInput = new Scanner(System.in);
      System.out.println("What do you want to edit?\n");
      
      System.out.printf("%d - Start Date (%s)%n", 1, DateHelper.dateToString(startDate));
      System.out.printf("%d - End Date (%s)%n", 2, DateHelper.dateToString(endDate));
      System.out.printf("%d - Room ID (%d)%n", 3, roomID);
      System.out.printf("%d - Guest ID (%s)%n", 4, guestID);
      
      int option = Menu.getOptionFromUser(1, 4);
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
            break;
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
            break;
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
            break;
         case 4: // Guest ID   
            System.out.printf("Guest ID (%s) can only be changed to a guest ID currently assigned to a customer. Please select a customer.%n", this.roomID);
            Guest.showList(guests);
            option = scanInput.nextInt() - 1;
            scanInput.nextLine();
            if(option != -1 && option < guests.size()){ // If valid option chosen
               this.guestID = guests.get(option).getGuestID();
            }
            System.out.printf("Guest ID for has been updated to %s%n", this.guestID);
            break;
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