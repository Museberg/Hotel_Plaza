import java.util.*;
import java.io.*;
import java.text.*;

public class PlazaHotel{
   public static void main(String[] args) throws ParseException{
   
      ArrayList<Room> rooms = new ArrayList<Room>();
      ArrayList<Guest> guests = new ArrayList<Guest>();
      ArrayList<Staff> staff = new ArrayList<Staff>();
      ArrayList<Booking> bookings = new ArrayList<Booking>();
   
      // Creating a booking
      Booking newBooking = new Booking("05/05 1997", "06/05 1997", 233, "Guest 1");
      bookings.add(newBooking);
      System.out.println(newBooking);
      
      int option = -1;
      while(option != 5){
         option = Menu.showMenu(option);
         Menu.menuActions(option, rooms, guests, staff, bookings);
      }
         
         
   }
   
}