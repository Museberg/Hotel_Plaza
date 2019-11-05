import java.util.*;
import java.io.*;

public class Room{
   int roomID;
   int beds;
   boolean internetAccess;
   double pricePerNight;
   
   // Returns floor number by getting first digit of roomID
   int getFloor(){
      return Integer.parseInt(Integer.toString(roomID).substring(0, 1));
   }
   
   // RoomID should be given as '123' where '1' is floor number and '23' is room number
   public Room(int roomID, int beds, boolean internetAccess, double pricePerNight){
      this.roomID = roomID;
      this.beds = beds;
      this.internetAccess = internetAccess;
      this.pricePerNight = pricePerNight;
   }

}