import java.util.*;
import java.io.*;
import java.text.*;

public class PlazaHotel{
   public static void main(String[] args) throws ParseException{
   
      // Creating a booking
      Booking newBooking = new Booking("05/05/1997", "06/05/1997", 233, "Guest 1");
      //System.out.println(newBooking);
      
      Menu.showMenu();
   }
   
}