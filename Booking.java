import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;
import java.time.format.*;

public class Booking{
   private LocalDate startDate;
   private LocalDate endDate;
   private long numberOfDays;
   private int roomID;
   private String guestID;
   
   
   private static String dateFormat = "dd/mm yyyy";
   
   // Dates should be written as dd/MM yyyy
   public Booking(LocalDate startDate, LocalDate endDate, int roomID, String guestID) throws ParseException{
      this.startDate = startDate;
      this.endDate = endDate;
      this.numberOfDays = DateHelper.getDays(this.startDate, this.endDate);
      this.roomID = roomID;
      this.guestID = guestID;
   }
   
   // Used for saving the object to a text file
   public String getBookingInSaveFormat(){
      return String.format("%s, %s, %d, %s",
         DateHelper.dateToString(startDate),
         DateHelper.dateToString(endDate),
         this.roomID,
         this.guestID);
   }
   
   public String getName(){
      return String.format("Booking for %s in room %d", this.guestID, this.roomID);
   }
   
   public long getNumberOfDays(){
      return numberOfDays;
   }

   public int getRoomID(){
      return this.roomID;
   }
   
   // Presents the user with a list of all the bookings
   public static void showList(ArrayList<Booking> bookings){
      for(int i = 0; i < bookings.size(); i++){
         System.out.printf("%d - %s%n", i+1, bookings.get(i).getName());
      }
   }
   
   // Sets the start date and updates the duration (in days)
   public void setStartDate(LocalDate newStartDate){
      this.startDate = newStartDate;
      this.numberOfDays = DateHelper.getDays(this.startDate, this.endDate);
   }

   // Sets the end date and updates the duration (in days)
   public void setEndDate(LocalDate newEndDate){
      this.endDate = newEndDate;
      this.numberOfDays = DateHelper.getDays(this.startDate, this.endDate);
   }

   public LocalDate getEndDate(){
      return this.endDate;
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
   
   // Also prints name of guest ID
   public String toString(Guest guest){
      return "\nStart date: " + DateHelper.dateToString(this.startDate) +
         "\nEnd date: " + DateHelper.dateToString(this.endDate) +
         "\nRoom is booked for: " + numberOfDays + " days" +
         "\nRoomID: " + roomID +
         "\nGuest ID: " + guestID + String.format(" (%s)", guest.getName());
   }
   
   public static Booking letUserCreateBooking(ArrayList<Room> rooms, ArrayList<Guest> guests) throws ParseException{
      Scanner scan = new Scanner(System.in);

      System.out.printf("Which guest is booking the room?%n");
      Guest.showList(guests);
      System.out.printf("%d to create a new guest%n", 0);
      int option = InputHelper.getOptionFromUser(0, guests.size()) - 1;
      String guestID;
      if(option != 0){ // If we are NOT creating a new guest
         guestID = guests.get(option).getGuestID();
      }
      else{ // Creating a new guest and taking ID of that
         Guest newGuest = Guest.letUserCreateGuest(false, guests);
         guests.add(newGuest);
         guestID = newGuest.getGuestID();
      }
      
      System.out.printf("What's the start date of the booking? Write as %s%n", dateFormat);
      LocalDate startDate = DateHelper.getValidDateFromUser();

      System.out.printf("What's the end date of the booking? Write as %s%n", dateFormat);
      LocalDate endDate = DateHelper.getValidDateFromUser();
      while(!startDate.isBefore(endDate)){
         System.out.printf("Start date (%s) must be before end date (%s)! Please try again.%n",
            DateHelper.dateToString(startDate), DateHelper.dateToString(endDate));
         endDate = DateHelper.getValidDateFromUser();
      }

      System.out.printf("How many beds should the room have?%n");
      int beds = InputHelper.getIntFromUser();

      System.out.printf("Should the room have internet access?%n%d for yes%n%d for no%n", 1, 2);
      boolean internet = InputHelper.getOptionFromUser(1, 2) == 1;

      System.out.printf("What is the max allowed price of the room per night?%n");
      double price = InputHelper.getDoubleFromUser();

      ArrayList<Room> validRooms = Room.getValidRooms(rooms, beds, internet, price);

      if(validRooms.size() == 0){
         System.out.printf("No rooms matching the criteria was found. Returning to main menu%n");
         return null;
      }
      
      System.out.printf("The following rooms matches the provided criteria.%nPlease select which room to book%n");
      Room.showList(validRooms);
      option = InputHelper.getOptionFromUser(1, validRooms.size()) - 1;

      Booking newBooking = new Booking(startDate, endDate, rooms.get(option).getRoomID(), guestID);   
      System.out.printf("Following booking has now been created:%n%s%n", newBooking.toString(guests.get(option)));
      return newBooking;
   }
   
   // Lets the user edit a value in a booking
   public void edit(ArrayList<Guest> guests, ArrayList<Room> rooms){
      Scanner scanInput = new Scanner(System.in);
      System.out.println("What do you want to edit?\n");
      
      System.out.printf("%d - Start Date (%s)%n", 1, DateHelper.dateToString(startDate));
      System.out.printf("%d - End Date (%s)%n", 2, DateHelper.dateToString(endDate));
      System.out.printf("%d - Room ID (%d)%n", 3, roomID);
      System.out.printf("%d - Guest ID (%s)%n", 4, guestID);
      System.out.printf("%d - Return to main menu%n", 0); 
      
      int option = InputHelper.getOptionFromUser(0, 4);
      LocalDate newDate;
      switch(option){
         case 1: // New start date
            System.out.printf("Current start date is %s. What do you want to change it to? Write it as %s%n",
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
            this.setStartDate(newDate);
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
            System.out.printf("New room ID must correspond with an existing room. Please select a room%n");
            Room.showList(rooms);
            System.out.printf("%d to create a new room and use room ID of that%n", 0);
            option = InputHelper.getOptionFromUser(0, rooms.size()) - 1;
            if(option != -1){ // If we are NOT creating a new room
               this.roomID = rooms.get(option).getRoomID();
               System.out.printf("The room ID has been updated to %d%n", this.roomID);
               break;
            }
            // Letting user create new room
            Room newRoom = Room.letUserCreateRoom(false);
            rooms.add(newRoom);
            this.roomID = newRoom.getRoomID();
            System.out.printf("The room ID has been updated to %d%n", this.roomID);
            break;

         case 4: // Guest ID   
            System.out.printf("Guest ID (%s) can only be changed to a guest ID currently assigned to a customer. Please select a customer.%n", this.roomID);
            Guest.showList(guests);
            System.out.printf("%d to create a new guest and use guest ID of that%n", 0);
            option = InputHelper.getOptionFromUser(0, guests.size()) - 1;
            if(option != -1){ // If we are NOT creating a new room
               this.guestID = guests.get(option).getGuestID();
               System.out.printf("The guest ID has been updated to %s%n", this.guestID);
               break;
            }
            // Letting user create new room
            Guest newGuest = Guest.letUserCreateGuest(false, guests);
            guests.add(newGuest);
            this.guestID = newGuest.getGuestID();
            System.out.printf("Guest ID for has been updated to %s%n", this.guestID);
            break;

         case 0:
            System.out.printf("Returning to main menu%n");
            break;
         }
   }

   // Returns the price of a booking
   public double getPrice(Room room){
      return room.getPrice() * this.numberOfDays;
   }

}