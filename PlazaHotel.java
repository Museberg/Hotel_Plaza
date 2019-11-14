import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;

public class PlazaHotel{
   public static void main(String[] args) throws ParseException, FileNotFoundException{
   
      ArrayList<Room> rooms = new ArrayList<Room>();
      ArrayList<Guest> guests = new ArrayList<Guest>();
      ArrayList<Staff> staff = new ArrayList<Staff>();
      ArrayList<Booking> bookings = new ArrayList<Booking>();

      FileHandler.loadAllFiles(guests, rooms, staff, bookings);

      int option = -1;
      while(option != 5){
         option = Menu.showMenu(option);
         Menu.menuActions(option, rooms, guests, staff, bookings);
      }
      FileHandler.saveAllFiles(guests, rooms, staff, bookings);
         
   }
   
}