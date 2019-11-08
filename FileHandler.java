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
   

}