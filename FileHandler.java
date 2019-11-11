import java.util.*;
import java.io.*;
import java.text.*;

public class FileHandler{

   public static void writeBookingsToDisk(ArrayList<Booking> bookingList) throws FileNotFoundException{
      PrintStream output = new PrintStream(new File("Bookings.txt"));
      for(Booking booking : bookingList){
         output.println(booking.getBookingInSaveFormat());
      }
   }
   
   public static void readBookingsFromDisk(ArrayList<Booking> bookingList) throws ParseException, FileNotFoundException{
      Scanner scan = new Scanner(new File("Bookings.txt"));
      // While scanner can find a new line (new booking)
      while(scan.hasNextLine()){
         Scanner lineScan = new Scanner(scan.nextLine());
         lineScan.useDelimiter(", "); // .next() now looks for a ', ' rather than a space
         
         Booking booking = new Booking(DateHelper.parseDate(lineScan.next()), DateHelper.parseDate(lineScan.next()),
            lineScan.nextInt(), lineScan.next());
         bookingList.add(booking);
      }
   }

   public static void writeStaffToDisk(ArrayList<Staff> staffList) throws FileNotFoundException{
      PrintStream output = new PrintStream(new File("StaffMembers.txt"));
      for(Staff staff : staffList){
         output.println(staff.getStaffInSaveFormat());
      }
   }

   public static void readStaffFromDisk(ArrayList<Staff> staffList) throws FileNotFoundException{
      Scanner scan = new Scanner(new File("StaffMembers.txt"));
      // While scanner can find a new line (new staff member)
      while(scan.hasNextLine()){
         Scanner lineScan = new Scanner(scan.nextLine());
         lineScan.useDelimiter(", "); // .next() now looks for a ', ' rather than a space
         
         Staff newStaff = new Staff(lineScan.next(), lineScan.next(), lineScan.next(), lineScan.nextInt(), lineScan.nextDouble());
         staffList.add(newStaff);
      }
   }

   public static void writeRoomToDisk(ArrayList<Room> roomList) throws FileNotFoundException{
      PrintStream output = new PrintStream(new File("Rooms.txt"));
      for(Room room : roomList){
         output.println(room.getRoomInSaveFormat());
      }
   }

   public static void readRoomFromDisk(ArrayList<Room> roomList) throws FileNotFoundException{
      Scanner scan = new Scanner(new File("Rooms.txt"));
      // While scanner can find a new line (new room)
      while(scan.hasNextLine()){
         Scanner lineScan = new Scanner(scan.nextLine());
         lineScan.useDelimiter(", "); // .next() now looks for a ', ' rather than a space
         
         Room newRoom = new Room(lineScan.nextInt(), lineScan.nextInt(), lineScan.nextBoolean(), lineScan.nextDouble());
         roomList.add(newRoom);
      }
   }

   public static void writeGuestToDisk(ArrayList<Guest> guestList) throws FileNotFoundException{
      PrintStream output = new PrintStream(new File("Guests.txt"));
      for(Guest guest : guestList){
         output.println(guest.getGuestInSaveFormat());
      }
   }

   public static void readGuestFromDisk(ArrayList<Guest> guestList) throws FileNotFoundException{
      Scanner scan = new Scanner(new File("Guests.txt"));
      // While scanner can find a new line (new room)
      while(scan.hasNextLine()){
         Scanner lineScan = new Scanner(scan.nextLine());
         lineScan.useDelimiter(", "); // .next() now looks for a ', ' rather than a space
         
         Guest newGuest = new Guest(lineScan.next(), lineScan.next(), lineScan.next(), lineScan.next(), lineScan.nextInt());
         guestList.add(newGuest);
      }
   }

   public static void loadAllFiles(ArrayList<Guest> guests, ArrayList<Room> rooms, ArrayList<Staff> staff, ArrayList<Booking> bookings)
   throws FileNotFoundException, ParseException{
      readGuestFromDisk(guests);
      readRoomFromDisk(rooms);
      readStaffFromDisk(staff);
      readBookingsFromDisk(bookings);
   }

   public static void saveAllFiles(ArrayList<Guest> guests, ArrayList<Room> rooms, ArrayList<Staff> staff, ArrayList<Booking> bookings)
   throws FileNotFoundException, ParseException{
      writeGuestToDisk(guests);
      writeRoomToDisk(rooms);
      writeStaffToDisk(staff);
      writeBookingsToDisk(bookings);
   }
   

}