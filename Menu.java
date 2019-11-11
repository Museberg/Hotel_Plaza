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
      
      System.out.printf("%d to show, create, or edit bookings%n", 1);
      System.out.printf("%d to show, create, or edit rooms%n", 2);
      System.out.printf("%d to show, create or edit guests%n", 3);
      System.out.printf("%d to show, create, or edit staff members%n", 4);
      System.out.printf("%d to exit the program. All work is saved%n", 5);
      
      return getOptionFromUser(1, 5);
   }
   
   public static void menuActions(int option, ArrayList<Room> rooms, ArrayList<Guest> guests, ArrayList<Staff> staff, ArrayList<Booking> bookings) throws ParseException{
      switch(option){
         case 1: // Bookings
            bookingsMenu(rooms, guests, bookings); // New menu with more options
            break;
         case 2: // Rooms
            roomMenu(rooms);
            break;
         case 3: // Guests
            guestMenu(guests);
            break;
         case 4: // Staff
            staffMenu(staff);
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
      
      int input = getOptionFromUser(1, 4);
      int option;
      switch(input){
         case 1: // Show list of bookings - allows user to see more info on single booking
         Booking.showList(bookings);
         System.out.printf("Press the corresponding number to see all information about a booking.%nPress 0 to go back to main menu%n");
         option = getOptionFromUser(0, bookings.size()) - 1;
         if(option == -1){
            break; // Going back to main menu
         }
         System.out.printf("%s%n", bookings.get(option));
         break;
         case 2: // Create booking
            bookings.add(Booking.letUserCreateBooking(rooms, guests));
            break;
         case 3: // Edit booking
            System.out.println("What booking do you want to edit?%n");
            Booking.showList(bookings); // Showing user list of bookings to select from
            option = getOptionFromUser(1, bookings.size()) - 1;
            bookings.get(option).edit(guests, rooms); // Guests is used to select guest ID
            break;
         case 4: // Delete booking
            Booking.showList(bookings); // Showing user list of bookings to select from
            option = getOptionFromUser(1, bookings.size()) - 1;
            String name = bookings.get(option).getName();
            bookings.remove(option); // Removes booking from array list
            System.out.printf("%s has been removed%n", name);
            break;
           
      }
   }
   
   private static void roomMenu(ArrayList<Room> rooms){
      // TODO: Implement
   }
   
   private static void guestMenu(ArrayList<Guest> guests){
      // TODO: Implement
   }
   
   private static void staffMenu(ArrayList<Staff> staff) throws ParseException{
      Scanner scan = new Scanner(System.in);
      System.out.println("Staff selected. What do you want to do?");
      
      System.out.printf("%d to show a list of all staff - can also show staff information%n", 1);
      System.out.printf("%d to create a new staff member%n", 2);
      System.out.printf("%d to edit a staff member%n", 3);
      System.out.printf("%d to delete a staff member. This action is permanent%n", 4);
      System.out.printf("%d to return to main menu", 0);
      
      int option = getOptionFromUser(0, 4);
      int input;
      
      switch(option){
         case 1: // Show list of staff
            Staff.showList(staff);
            System.out.printf("Press the corresponding number to see all information about a staff member.%nPress 0 to go back to main menu%n");
            input = getOptionFromUser(0, staff.size());
            if(input == 0){
               break; // Going back to main menu
            }
            System.out.printf("s%n", staff.get(input)); 
            break;
         case 2: // Create new staff member
            Staff.letUserCreateStaff(true);
            break;
         case 3: // Edit staff member
            System.out.println("What staff do you want to edit?%n");
            Staff.showList(staff); // Showing user list of bookings to select from
            option = getOptionFromUser(1, staff.size()) - 1;
            staff.get(option).edit();
         case 4: // Delete staff member
            Staff.showList(staff); // Showing user list of bookings to select from
            option = getOptionFromUser(1, staff.size()) - 1;
            String name = staff.get(option).getName();
            staff.remove(option); // Removes staff member from array list
            System.out.printf("%s has been removed%n", name);
            break;

      }
   }
   
   // Returns a integer between minInt..maxInt
   public static int getOptionFromUser(int minInt, int maxInt){
      Scanner scan = new Scanner(System.in);
      int number;
      boolean isNotValid = false;
      
      do{
         if(isNotValid){
            System.out.printf("The number must be between %d and %d%n", minInt, maxInt);
         }
         while(!scan.hasNextInt()){
            System.out.printf("That is not a number! Please try again.%n");
            scan.next();
         }
         // hasNextInt() true, reading number from console
         number = scan.nextInt();
         isNotValid = number < minInt || number > maxInt;
      }while(isNotValid);
      
      return number;
   }
}