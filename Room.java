import java.util.*;
import java.io.*;

public class Room{
   private int roomID;
   private int beds;
   private boolean internetAccess;
   private double pricePerNight;
   
   // RoomID should be given as "123" where "1" is floor number and "23" is room number
   public Room(int roomID, int beds, boolean internetAccess, double pricePerNight){
      this.roomID = roomID;
      this.beds = beds;
      this.internetAccess = internetAccess;
      this.pricePerNight = pricePerNight;
   }

   // Returns floor number by getting first digit of roomID
   int getFloor(){
      return Integer.parseInt(Integer.toString(roomID).substring(0, 1));
   }
   
   public int getRoomID(){
      return this.roomID;
   }

   public int getBeds(){
      return this.beds;
   }

   public boolean getInternetAccess(){
      return this.internetAccess;
   }

   public double getPrice(){
      return this.pricePerNight;
   }

   public String toString(){
      return getName() +
         "\nRoom ID: " + this.roomID +
         "\nFloor: " + getFloor() +
         "\nBeds: " + this.beds +
         "\nInternet access: " + (internetAccess ? "Yes" : "No") +
         "\nPrice per night: " + pricePerNight; 

   }

   // Used for saving the object to a text file
   public String getRoomInSaveFormat(){
      return String.format("%d, %d, %b, %.2f", 
         this.roomID, 
         this.beds, 
         this.internetAccess, 
         this.pricePerNight);
   }
   
   public String getName(){
      return String.format("Room %s on floor %d", this.roomID, this.getFloor());
   }

   // Returns the room matching the provided roomID
   public static Room getRoomFromID(int roomID, ArrayList<Room> rooms){
      for(Room room : rooms){
         if(room.roomID == roomID){
            return room;
         }
      }
      return null;
   }
   
   public static void showList(ArrayList<Room> rooms){
      for(int i = 0; i < rooms.size(); i++){
         System.out.printf("%d - %s%n", i+1, rooms.get(i).getName());
      }
   }

   public static Room letUserCreateRoom(boolean printRoom){
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
      double pricePerNight = InputHelper.getDoubleFromUser();

      Room newRoom = new Room(roomID, beds, internetAccess, pricePerNight);

      if(printRoom){
         System.out.printf("The following room has been added to the sytem%n%s%n", newRoom);
      }
      return newRoom;
   }

   public void edit(){
      System.out.printf("What do you want to edit?%n");

      System.out.printf("%d - Room ID (%d)%n", 1, this.roomID);
      System.out.printf("%d - Amount of beds(%d)%n", 2, this.beds);
      System.out.printf("%d - Internet access(%s)%n", 3, (this.internetAccess ? "Yes" : "No"));
      System.out.printf("%d - Price per night(%.2f)%n", 4, this.pricePerNight);
      System.out.printf("%d - Return to main menu%n", 0);

      int option = InputHelper.getOptionFromUser(0, 4);

      switch(option){
         case 1: // Edit room ID
            System.out.printf("What should the new room ID be?%n");
            this.roomID = InputHelper.getIntFromUser();
            while(String.valueOf(roomID).length() != 3){
               System.out.printf("The number %d is not 3 digits long. Please try again.%n", roomID);
               this.roomID = InputHelper.getIntFromUser();
            }
            System.out.printf("The room ID has been updated to %d%n", this.roomID);
            break;

         case 2: // Edit number of beds
            System.out.printf("How many beds should the room have now?%n");
            this.beds = InputHelper.getIntFromUser();
            System.out.printf("The amount of beds for the room has been updated to %d%n", beds);
            break;

         case 3: // Edit internet access
            System.out.printf("Type %d for internet access%nType %d for no internet access%n", 1, 2);
            this.internetAccess = InputHelper.getOptionFromUser(1, 2) == 1;
            if(internetAccess){
               System.out.printf("There is now internet access in %s%n", this.getName());
            }
            else{
               System.out.printf("There is now NOT internet access in %s%n", this.getName());
            }
            break;

         case 4: // Edit price per night
            System.out.printf("What should the new price be?%n");
            this.pricePerNight = InputHelper.getDoubleFromUser();
            System.out.printf("The price per night has been updated to %d%n", this.pricePerNight);
      }
   }

   // Returns an arraylist of rooms matching the criteria provided
   public static ArrayList<Room> getValidRooms(ArrayList<Room> rooms, int beds, boolean internetAccess, double price){
      ArrayList<Room> validRooms = new ArrayList<Room>();
      for(Room room : rooms){ // Looping through each room
         if(room.getBeds() >= beds){ // Checking if room have enough beds for guest
            if(!internetAccess || room.getInternetAccess()){ // Even if customer doesn't want internet, the room can still have it
               if(room.getPrice() <= price){ // If price per night is at or less than requested
                  validRooms.add(room); // If all are true, room is valid and added to return list
               }
            }
         }

      }
      return validRooms;

   }

}