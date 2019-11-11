import java.util.*;
import java.io.*;

public class Guest{
   private String guestID;
   private String firstName;
   private String lastName;
   private String address;
   private int phoneNumber;
   
   // To write region code on phone number, use '00' instead of '+'
   public Guest(String guestID, String firstName, String lastName, String address, int phoneNumber){
      this.guestID = guestID;
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
   }
   
   public String getGuestID(){
      return this.guestID;
   }
   
   // Returns 'firstName lastName'
   public String getName(){
      return String.format("%s %s", this.firstName, this.lastName);
   }
   
   // Presents the user with a list of all the guests
   public static void showList(ArrayList<Guest> guests){
      for(int i = 0; i < guests.size(); i++){
         System.out.printf("%d - %s%n", i+1, guests.get(i).getName());
      }
   }
}

