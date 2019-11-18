import java.util.*;
import java.io.*;

public class Guest{
   private final String guestID;
   private String firstName;
   private String lastName;
   private String address;
   private int phoneNumber;
   
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

   // Used for saving the object to a text file
   public String getGuestInSaveFormat(){
      return String.format("%s, %s, %s, %s, %d",
         this.guestID,
         this.firstName,
         this.lastName,
         this.address,
         this.phoneNumber);
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

   public static Guest letUserCreateGuest(boolean printGuest, ArrayList<Guest> guests){
      Scanner scan = new Scanner(System.in);

      String guestID = "Guest " + (getLastGuestID(guests) + 1);

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
         System.out.printf("Following guest has been added to the system%n%s%n", newGuest);
      }
      return newGuest;
   }

   public void edit(){
      Scanner scan = new Scanner(System.in);
      System.out.printf("What do you want to edit?%n");

      System.out.printf("%d - First name of guest (%s)%n", 1, this.firstName);
      System.out.printf("%d - Last name of guest (%s)%n", 2, this.lastName);
      System.out.printf("%d - Address of guest (%s)%n", 3, this.address);
      System.out.printf("%d - Phone number of guest (%d)%n", 4, this.phoneNumber);

      int option = InputHelper.getOptionFromUser(1, 4);

      switch(option){
         case 1: // Edit first name
            System.out.printf("What do you want to change the guest's first name to?%n");
            this.firstName = scan.nextLine();
            System.out.printf("First name of guest has been updated to %s%n", this.firstName);
            break;

         case 2: // Edit last name
            System.out.printf("What do you want to change the guest's last name to?%n");
            this.lastName = scan.nextLine();
            System.out.printf("Last name of guest has been updated to %s%n", this.lastName);
            break;

         case 3:
            System.out.printf("What do you want to change the guest's address to?");
            this.address = scan.nextLine();
            System.out.printf("Address of guest has been updated to %s%n", this.address);
            break;

         case 4:
            System.out.printf("What do you want to change the guest's phone number to?%n");
            this.phoneNumber = InputHelper.getIntFromUser();
            System.out.printf("Phone number of guest has been updated to %s%n", phoneNumber);
            break;
      }

   }

   private static int getLastGuestID(ArrayList<Guest> guests){
      if(guests.size() == 0){
         return 0;
      }
      String guestID = guests.get(guests.size() - 1).getGuestID();
      return Integer.parseInt(guestID.replaceAll("[\\D]", ""));
   }
}

