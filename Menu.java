import java.io.*;
import java.util.*;
import java.text.*;

public class Menu{

   // Returns the option chosen by the user
   public static int showMenu(int option){
      Scanner scanInput = new Scanner(System.in);
      
      // If menu has not been show before. Makes it look nicer
      if(option != -1){
         System.out.println("");
      }
      
      System.out.println("Please select the action you want to perform.");
      System.out.println("Type in the number followed by a press of the enter key.");
      
      System.out.printf("%d Manage bookings%n", 1);
      System.out.printf("%d Manage rooms%n", 2);
      System.out.printf("%d Manage guests%n", 3);
      System.out.printf("%d Manage staff members%n", 4);
      System.out.printf("%d Exit and save%n", 5);
      
      return InputHelper.getOptionFromUser(1, 5);
   }
   
   public static void menuActions(int option, ArrayList<Room> rooms, ArrayList<Guest> guests, ArrayList<Staff> staff, ArrayList<Booking> bookings) throws ParseException{
      switch(option){
         case 1: // Bookings
            bookingsMenu(rooms, guests, bookings); // Submenu with more options
            break;
         case 2: // Rooms
            roomMenu(rooms); // Submenu with more options
            break;
         case 3: // Guests
            guestMenu(guests); // Submenu with more options
            break;
         case 4: // Staff
            staffMenu(staff); // Submenu with more options
            break;
         default:
            System.out.println("Saving progress and exiting program");
            break;
      }  
   }
   
   // All actions on bookings are handeled (called from) here
   private static void bookingsMenu(ArrayList<Room> rooms, ArrayList<Guest> guests, ArrayList<Booking> bookings) throws ParseException{
      Scanner scanInput = new Scanner(System.in);
      System.out.println("Bookings selected. What do you want to do?");
      
      System.out.printf("%d to show a list of all bookings - can also show booking information%n", 1);
      System.out.printf("%d to create a new booking%n", 2);
      System.out.printf("%d to edit a booking%n", 3);
      System.out.printf("%d to delete a booking. This action is permanent%n", 4);
      System.out.printf("%d print bill for a booking%n", 5);
      System.out.printf("%d add days to booking%n", 6);
      System.out.printf("%d return to main menu%n", 0);
      
      int option = InputHelper.getOptionFromUser(0, 6);
      switch(option){
         case 1: // Show list of bookings - allows user to see more info on single booking
         if(bookings.size() == 0){
            System.out.printf("There are currently no bookings registered in the system.%nReturning to main menu%n");
            break;
         }
         Booking.showList(bookings);
         System.out.printf("Press the corresponding number to see all information about a booking.%nPress 0 to go back to main menu%n");
         option = InputHelper.getOptionFromUser(0, bookings.size()) - 1;
         if(option == -1){
            break; // Going back to main menu
         }
         System.out.printf("%s%n", bookings.get(option));
         break;

         case 2: // Create booking
            Booking newBooking = Booking.letUserCreateBooking(rooms, guests);
            if(newBooking != null) // If no suibtle room found, method returns null
               bookings.add(newBooking);
            break;

         case 3: // Edit booking
            if(bookings.size() == 0){
               System.out.printf("There are currently no bookings registered in the system.%nReturning to main menu%n");
               break;
            }
            System.out.println("What booking do you want to edit?%n");
            Booking.showList(bookings); // Showing user list of bookings to select from
            option = InputHelper.getOptionFromUser(1, bookings.size()) - 1;
            bookings.get(option).edit(guests, rooms);
            break;

         case 4: // Delete booking
            Booking.showList(bookings); // Showing user list of bookings to select from
            option = InputHelper.getOptionFromUser(1, bookings.size()) - 1;
            String name = bookings.get(option).getName();
            bookings.remove(option); // Removes booking from array list
            System.out.printf("%s has been removed%n", name);
            break;

         case 5: // Print bill
            if(bookings.size() == 0){
               System.out.printf("There are currently no registered bookings in the system.%nReturning to main menu%n");
               break;
            }
            System.out.printf("Please select which booking to get the bill for%n");
            Booking.showList(bookings);
            option = InputHelper.getOptionFromUser(1, bookings.size()) - 1;
            Booking booking = bookings.get(option);
            Room room = Room.getRoomFromID(booking.roomID, rooms);

            String bill =
               "Bill for renting room " + Room.getRoomFromID(booking.getRoomID(), rooms).getName() +
               "\nDuration:        " + booking.getNumberOfDays() + " days" +
               "\nPrice per night: " + room.getPrice() +
               "\nTotal due:       " + booking.getPrice(room);

            System.out.printf("%s%n", bill);
            break;

         case 6: // Add days to booking
            System.out.printf("What booking do you want to extend the duration of?%n");
            Booking.showList(bookings); // Showing user list of bookings to select from
            option = InputHelper.getOptionFromUser(1, bookings.size()) - 1;
            Booking bk = bookings.get(option);
            String oldEndDate = DateHelper.dateToString(bk.getEndDate());

            System.out.printf("How many days do you want to expand the booking with?%n");
            int addDays = InputHelper.getIntFromUser();
            bk.setEndDate(bk.getEndDate().plusDays(addDays));
            System.out.printf("Enddate has been updated from %s to %s%n", oldEndDate, DateHelper.dateToString(bk.getEndDate()));

         case 0: // Returning to main menu
            break;
           
      }
   }
   
   private static void roomMenu(ArrayList<Room> rooms){
      Scanner scanInput = new Scanner(System.in);
      System.out.println("Bookings selected. What do you want to do?");
      
      System.out.printf("%d to show a list of all rooms - can also show room information%n", 1);
      System.out.printf("%d to create a new room%n", 2);
      System.out.printf("%d to edit a room%n", 3);
      System.out.printf("%d to delete a room. This action is permanent%n", 4);
      System.out.printf("%d return to main menu%n", 0);
      
      int option = InputHelper.getOptionFromUser(0, 4);

      switch(option){
         case 1: // Show list of rooms
            if(rooms.size() == 0){
               System.out.printf("There are currently no rooms registered in the system.%nReturning to main menu%n");
               break;
            }
            Room.showList(rooms);
            System.out.printf("Press the corresponding number to see all information about a room.%nPress 0 to go back to main menu%n");
            option = InputHelper.getOptionFromUser(0, rooms.size());
            if(option == 0){
               break; // Returning to main menu
            }
            System.out.printf("%s%n", rooms.get(option - 1));
            break;

         case 2: // Create a new room
            rooms.add(Room.letUserCreateRoom(true));
            break;

         case 3: // Edit a room
            if(rooms.size() == 0){
               System.out.printf("There are currently no rooms registered in the system.%nReturning to main menu%n");
               break;
            }
            System.out.printf("What room do you want to edit?%n");
            Room.showList(rooms);
            option = InputHelper.getOptionFromUser(1, rooms.size()) - 1;
            rooms.get(option).edit();
            break;

         case 4: // Delete room from system
            Room.showList(rooms); // Showing user list of rooms to select from
            option = InputHelper.getOptionFromUser(1, rooms.size()) - 1;
            String name = rooms.get(option).getName();
            rooms.remove(option); // Removes booking from array list
            System.out.printf("%s has been removed%n", name);
            break;

         case 0: // Return to main menu
            break;
      }
   }
   
   private static void guestMenu(ArrayList<Guest> guests){
      Scanner scan = new Scanner(System.in);
      System.out.println("Guest selected. What do you want to do?");
      
      System.out.printf("%d to show a list of all guests - can also show guest information%n", 1);
      System.out.printf("%d to create a new guest%n", 2);
      System.out.printf("%d to edit a guest%n", 3);
      System.out.printf("%d to delete a guest. This action is permanent%n", 4);
      System.out.printf("%d to return to main menu%n", 0);
      
      int option = InputHelper.getOptionFromUser(0, 4);

      switch(option){
         case 1: // Show guests
            if(guests.size() == 0){
               System.out.println("There are currently no guests registered in the system.\nReturning to the main menu");
               break;
            }
            Guest.showList(guests);
            System.out.printf("Press the corresponding number to see all information about a guest.%nPress 0 to go back to main menu%n");
            option = InputHelper.getOptionFromUser(0, guests.size());
            if(option == 0){
               break; // Returning to main menu
            }
            System.out.printf("%s%n", guests.get(option - 1));
            break;

         case 2: // Create a new guest
            guests.add(Guest.letUserCreateGuest(true, guests));
            break;
            
         case 3: // Edit a guest
            System.out.printf("What guest do you want to edit?%n");
            Guest.showList(guests);
            option = InputHelper.getOptionFromUser(1, guests.size()) - 1;
            guests.get(option).edit();
            break;

         case 4: // Delete a guest
            Guest.showList(guests); // Showing user list of guests to select from
            option = InputHelper.getOptionFromUser(1, guests.size()) - 1;
            String name = guests.get(option).getName();
            guests.remove(option); // Removes booking from array list
            System.out.printf("%s has been removed%n", name);
            break;
      }
   }
   
   private static void staffMenu(ArrayList<Staff> staff) throws ParseException{
      Scanner scan = new Scanner(System.in);
      System.out.println("Staff selected. What do you want to do?");
      
      System.out.printf("%d to show a list of all staff - can also show staff information%n", 1);
      System.out.printf("%d to create a new staff member%n", 2);
      System.out.printf("%d to edit a staff member%n", 3);
      System.out.printf("%d to delete a staff member. This action is permanent%n", 4);
      System.out.printf("%d to return to main menu%n", 0);
      
      int option = InputHelper.getOptionFromUser(0, 4);
      
      switch(option){
         case 1: // Show list of staff
            Staff.showList(staff);
            System.out.printf("Press the corresponding number to see all information about a staff member.%nPress 0 to go back to main menu%n");
            option = InputHelper.getOptionFromUser(0, staff.size());
            if(option == 0){
               break; // Going back to main menu
            }
            System.out.printf("s%n", staff.get(option - 1)); 
            break;

         case 2: // Create new staff member
            staff.add(Staff.letUserCreateStaff(true));
            break;

         case 3: // Edit staff member
            System.out.println("What staff do you want to edit?%n");
            Staff.showList(staff); // Showing user list of bookings to select from
            option = InputHelper.getOptionFromUser(1, staff.size()) - 1;
            staff.get(option).edit();

         case 4: // Delete staff member
            Staff.showList(staff); // Showing user list of bookings to select from
            option = InputHelper.getOptionFromUser(1, staff.size()) - 1;
            String name = staff.get(option).getName();
            staff.remove(option); // Removes staff member from array list
            System.out.printf("%s has been removed%n", name);
            break;
      }
   }
}