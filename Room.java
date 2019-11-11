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

   public static Room letUserCreateRoom(boolean printRoom){
      Scanner scan = new Scanner(System.in);

      System.out.printf("What's the ID of the new room?%n");
      int roomID = InputHelper.getIntFromUser();
      
      while(String.valueOf(roomID).length() != 3){
         System.out.printf("The number %d is not 3 digits long. Please try again.%n", roomID);
         roomID = InputHelper.getIntFromUser();
      }

      System.out.printf("How many beds are there in the new room?%n");
      int beds = InputHelper.getIntFromUser();

      System.out.printf("Is there internet access in the new room?%n%d for yes%n%d for no%n", 1, 2);
      boolean internetAccess = InputHelper.getOptionFromUser(1, 2) == 1; // If 1, internet access == true

      System.out.printf("What is the price per night for the new room?%n");
      int pricePerNight = InputHelper.getIntFromUser();

      Room newRoom = new Room(roomID, beds, internetAccess, pricePerNight);

      if(printRoom){
         System.out.printf("The following guest has been added to the sytem%n%s", printRoom);
      }
      return newRoom;

   }

}