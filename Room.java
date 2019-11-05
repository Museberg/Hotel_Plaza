import java.util.*;
import java.io.*;

public class Room{
   private int roomID;
   private int beds;
   private boolean internetAccess;
   private double pricePerNight;
   
   // Returns floor number by getting first digit of roomID
   int getFloor(){
      return Integer.parseInt(Integer.toString(roomID).substring(0, 1));
   }
   
   public int getRoomID(){
      return this.roomID;
   }
   
   // RoomID should be given as "123" where "1" is floor number and "23" is room number
   public Room(int roomID, int beds, boolean internetAccess, double pricePerNight){
      this.roomID = roomID;
      this.beds = beds;
      this.internetAccess = internetAccess;
      this.pricePerNight = pricePerNight;
   }
   
   public String getName(){
      return String.format("Room %s on floor %d", this.roomID, this.getFloor());
   }
   
   public static void showList(ArrayList<Room> rooms){
      for(int i = 0; i < rooms.size(); i++){
         System.out.printf("%d - %s%n", i+1, rooms.get(i).getName());
      }
   }

}