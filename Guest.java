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
      this.address = address;
      this.phoneNumber = phoneNumber;
   }

   public String toString(){
      return getName() +
         "\nGuest ID: " + this.guestID +
         "\nFirst name: " + this.firstName +
         "\nLast name: " + this.lastName +
         "\nAddress: " + this.address +
         "\nPhone number: " + this.phoneNumber;
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

   public static Guest letUserCreateGuest(boolean printGuest){
      Scanner scan = new Scanner(System.in);

      System.out.printf("What's the ID of the new guest?%n");
      String guestID = scan.nextLine();

      System.out.printf("What's the first name of the new guest?%n");
      String firstName = scan.nextLine();

      System.out.printf("What's the last name of the new guest?%n");
      String lastName = scan.nextLine();

      System.out.printf("What's the address of the new guest?%n");
      String address = scan.nextLine();

      System.out.printf("What's the phone number of the new guest?%n");
      int phoneNumber = InputHelper.getPhoneNumber();

      Guest newGuest = new Guest(guestID, firstName, lastName, address, phoneNumber);
      if(printGuest){
         System.out.printf("Following guest has been added to the system%n%s", newGuest);
      }
      return newGuest;

   }
}

