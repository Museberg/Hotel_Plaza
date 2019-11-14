import java.util.*;
import java.io.*;
import java.text.*;

public class FileHandler{

   private static void writeBookingsToDisk(ArrayList<Booking> bookings) throws FileNotFoundException{
      PrintStream output = new PrintStream(new File("Bookings.txt"));
      for(Booking booking : bookings){
         output.println(booking.getBookingInSaveFormat());
      }
   }
   
   private static void readBookingsFromDisk(ArrayList<Booking> bookings) throws ParseException, FileNotFoundException{
      Scanner scan = new Scanner(new File("Bookings.txt"));
      // While scanner can find a new line (new booking)
      while(scan.hasNextLine()){
         Scanner lineScan = new Scanner(scan.nextLine());
         lineScan.useDelimiter(", "); // .next() now looks for a ', ' rather than a space
         
         Booking booking = new Booking(DateHelper.parseDate(lineScan.next()), DateHelper.parseDate(lineScan.next()),
            lineScan.nextInt(), lineScan.next());
         bookings.add(booking);
      }
   }

   private static void writeStaffToDisk(ArrayList<Staff> staff) throws FileNotFoundException{
      PrintStream output = new PrintStream(new File("StaffMembers.txt"));
      for(Staff staffMember : staff){
         output.println(staffMember.getStaffInSaveFormat());
      }
   }

   private static void readStaffFromDisk(ArrayList<Staff> staff) throws FileNotFoundException{
      Scanner scan = new Scanner(new File("StaffMembers.txt"));
      // While scanner can find a new line (new staff member)
      while(scan.hasNextLine()){
         Scanner lineScan = new Scanner(scan.nextLine());
         lineScan.useDelimiter(", "); // .next() now looks for a ', ' rather than a space
         
         Staff newStaff = new Staff(lineScan.next(), lineScan.next(), lineScan.next(), lineScan.nextInt(), lineScan.nextDouble());
         staff.add(newStaff);
      }
   }

   private static void writeRoomToDisk(ArrayList<Room> rooms) throws FileNotFoundException{
      PrintStream output = new PrintStream(new File("Rooms.txt"));
      for(Room room : rooms){
         output.println(room.getRoomInSaveFormat());
      }
   }

   private static void readRoomFromDisk(ArrayList<Room> rooms) throws FileNotFoundException{
      Scanner scan = new Scanner(new File("Rooms.txt"));
      // While scanner can find a new line (new room)
      while(scan.hasNextLine()){
         Scanner lineScan = new Scanner(scan.nextLine());
         lineScan.useDelimiter(", "); // .next() now looks for a ', ' rather than a space
         
         Room newRoom = new Room(lineScan.nextInt(), lineScan.nextInt(), lineScan.nextBoolean(), lineScan.nextDouble());
         rooms.add(newRoom);
      }
   }

   private static void writeGuestToDisk(ArrayList<Guest> guests) throws FileNotFoundException{
      PrintStream output = new PrintStream(new File("Guests.txt"));
      for(Guest guest : guests){
         output.println(guest.getGuestInSaveFormat());
      }
   }

   private static void readGuestFromDisk(ArrayList<Guest> guests) throws FileNotFoundException{
      Scanner scan = new Scanner(new File("Guests.txt"));
      // While scanner can find a new line (new room)
      while(scan.hasNextLine()){
         Scanner lineScan = new Scanner(scan.nextLine());
         lineScan.useDelimiter(", "); // .next() now looks for a ', ' rather than a space
         
         Guest newGuest = new Guest(lineScan.next(), lineScan.next(), lineScan.next(), lineScan.next(), lineScan.nextInt());
         guests.add(newGuest);
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