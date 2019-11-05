import java.io.*;
import java.util.*;

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
      
      return scanInput.nextInt();
   }
   
   public static void menuActions(int option, ArrayList<Room> rooms, ArrayList<Guest> guests, ArrayList<Staff> staff, ArrayList<Booking> bookings){
      switch(option){
         case 1: // Bookings
            bookingsMenu(bookings); // New menu with more options
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
   private static void bookingsMenu(ArrayList<Booking> bookings){
      Scanner scanInput = new Scanner(System.in);
      System.out.println("Bookings selected. What do you want to do?");
      
      System.out.printf("%d to show a list of all bookings - can also show booking information%n", 1);
      System.out.printf("%d to create a new booking%n", 2);
      System.out.printf("%d to edit a booking%n", 3);
      System.out.printf("%d to delete a booking. This action is permanent%n", 4);
      
      int input = scanInput.nextInt();
      scanInput.nextLine(); // Eating newline leftover
      int option;
      switch(input){
         case 1: // Show list of bookings - allows user to see more info on single booking
         Booking.showList(bookings);
         System.out.println("Press the corresponding number to see all information about a booking. Press 0 to go back to main menu");
         option = scanInput.nextInt() - 1;
         if(option != -1 && option < bookings.size()){ // When user selects 1, input becomes 0
            System.out.printf("%s%n", bookings.get(option));
         }
         case 2: // Create booking
            break;
         case 3: // Edit booking
            System.out.println("What booking do you want to edit?\n");
            Booking.showList(bookings);
            option = scanInput.nextInt();
            bookings.get(option-1).edit();  
      }
   }
   
   private static void roomMenu(ArrayList<Room> rooms){
      // TODO: Implement
   }
   
   private static void guestMenu(ArrayList<Guest> guests){
      // TODO: Implement
   }
   
   private static void staffMenu(ArrayList<Staff> staff){
      // TODO: Implement
   }
}