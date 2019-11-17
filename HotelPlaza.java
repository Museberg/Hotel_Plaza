import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;

public class HotelPlaza{
   public static void main(String[] args) throws ParseException, FileNotFoundException{
   
      ArrayList<Room> rooms = new ArrayList<Room>();
      ArrayList<Guest> guests = new ArrayList<Guest>();
      ArrayList<Staff> staff = new ArrayList<Staff>();
      ArrayList<Booking> bookings = new ArrayList<Booking>();

      FileHandler.loadAllFiles(guests, rooms, staff, bookings);

      System.out.println("Loading files...");
      showLoadingBar();

      int option = -1;
      while(option != 5){
         option = Menu.showMenu(option);
         Menu.menuActions(option, rooms, guests, staff, bookings);
      }
      FileHandler.saveAllFiles(guests, rooms, staff, bookings);
      
      showLoadingBar();
   }

   private static void showLoadingBar(){
      for(int i = 1; i <= 20; i++){
         Random rand = new Random();
         try
         {  
             Thread.sleep(rand.nextInt(250) + 20); // Number between 20-170
         }
         catch(InterruptedException ex)
         {
             Thread.currentThread().interrupt();
         }
         System.out.print("\r|" + "=".repeat(i) + " ".repeat(20-i) + "|");
      }
      System.out.println("");

   }
   
}