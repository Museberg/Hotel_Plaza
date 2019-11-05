import java.util.*;
import java.io.*;

public class Booking{
   Date startDate;
   Date endDate;
   int numberOfDays;
   int roomID;
   int guestID;
   
   public Booking(Date startDate, Date endDate, int numberOfDays, int roomID, int guestID){
      this.startDate = startDate;
      this.endDate = endDate;
      this.numberOfDays = numberOfDays;
      this.roomID = roomID;
      this.guestID = guestID;
   }

}