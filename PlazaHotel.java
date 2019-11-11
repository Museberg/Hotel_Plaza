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
   
      // Creating a booking
      /*LocalDate startDate = DateHelper.parseDate("05/05 1997");
      LocalDate endDate = DateHelper.parseDate("06/05 1997");
      Booking newBooking1 = new Booking(startDate, endDate, 233, "G1");
      Booking newBooking2 = new Booking(startDate, endDate, 322, "G2");
      bookings.add(newBooking1);
      bookings.add(newBooking2);

      // Creating a new guest
      Guest newGuest = new Guest("Guest 1", "Rasmus", "Falk-Jensen", "Frederiksberg Bredegade 13B", 31953678);
      guests.add(newGuest);
      
      // Creating a new room
      Room newRoom = new Room(403, 2, true, 1789.95);
      rooms.add(newRoom);

      // Creating a new staff member
      Staff newStaff = new Staff("Director", "Thomas", "Sørensen", 12345678, 30000.0);
      staff.add(newStaff);*/

      int option = -1;
      while(option != 5){
         option = Menu.showMenu(option);
         Menu.menuActions(option, rooms, guests, staff, bookings);
      }
      FileHandler.saveAllFiles(guests, rooms, staff, bookings);
         
   }
   
}