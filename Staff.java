import java.util.*;
import java.io.*;
import java.text.*;

public class Staff{
   private String title;
   private String firstName;
   private String lastName;
   private int phoneNumber;
   private double salary;
   
   public Staff(String title, String firstName, String lastName, int phoneNumber, double salary){
      this.title = title;
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
      this.salary = salary;
   }
   
   public String toString(){
      return getName() +
         "\nTitle: " + this.title +
         "\nFist name: " + this.firstName +
         "\nLast name: " + this.lastName +
         "\nPhone number: " + this.phoneNumber +
         "\nSalary: " + this.salary;
   }
   
   // Presents the user with a list of all the staff
   public static void showList(ArrayList<Staff> staff){
      for(int i = 0; i < staff.size(); i++){
         System.out.printf("%d - %s%n", i+1, staff.get(i).getName());
      }
   }

   public String getName(){
      return String.format("%s - %s %s", this.title, this.firstName, this.lastName);

   }

   // Used for saving the object to a text file
   public String getStaffInSaveFormat(){
      return String.format("%s, %s, %s, %d, %.2f",
         this.title,
         this.firstName,
         this.lastName,
         this.phoneNumber,
         this.salary);
   }

   public static Staff letUserCreateStaff(boolean printStaff) throws ParseException{
      Scanner scan = new Scanner(System.in);
      
      System.out.printf("What title do you want to give the new staff member?%n");
      String title = scan.nextLine();
      
      System.out.printf("What's the new staff member's first name?%n");
      String firstName = scan.nextLine();
      
      System.out.printf("What's the new staff member's last name?%n");
      String lastName = scan.nextLine();
      
      System.out.printf("What's the new staff member's phone number?%n");
      int phoneNumber = InputHelper.getPhoneNumber();
      
      System.out.printf("What's the salary of the new staff member?%n");
      double salary = InputHelper.getDoubleFromUser();
      
      Staff newStaff = new Staff(title, firstName, lastName, phoneNumber, salary);
      
      if(printStaff){
         System.out.printf("Following staff member has been added to system%n%s%n", newStaff);
      }
      return newStaff;
   }

   public void edit(){
      Scanner scan = new Scanner(System.in);

      System.out.printf("What do you want to edit?%n");

      System.out.printf("%d - Staff title (%s)%n", 1, title);
      System.out.printf("%d - Staff first name (%s)%n", 2, firstName);
      System.out.printf("%d - Staff last name (%s)%n", 3, lastName);
      System.out.printf("%d - Staff phone number (%d)%n", 4, phoneNumber);
      System.out.printf("%d - Staff salary (%.2f)%n", 5, salary);
      System.out.printf("%d - Return to main menu%n", 0);

      int option = InputHelper.getOptionFromUser(0, 5);

      switch(option){
         case 1: // Edit staff title
            System.out.println("What do you want to change the staff member's title to?");
            this.title = scan.nextLine();
            break;
         case 2: // Edit staff first name
            System.out.println("What do you want to change the staff member's first name to?");
            this.firstName = scan.nextLine();
            break;
         case 3: // Edit staff last name
            System.out.println("What do you want to change the staff member's last name to?");
            this.lastName = scan.nextLine();
            break;
         case 4: // Edit staff phone number
            System.out.println("What do you want to change the staff member's phone number to?");
            this.phoneNumber = InputHelper.getPhoneNumber();
            break;
         case 5: // Edit salary
            System.out.println("What do you want to change the staff member's salary to?");
            this.salary = InputHelper.getDoubleFromUser();
            break;
         case 0:
            System.out.printf("Returning to main menu%n");
            break;
      }



   }
}