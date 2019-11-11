import java.util.*;
import java.io.*;

public class InputHelper{
   // Returns a integer between minInt..maxInt
   public static int getOptionFromUser(int minInt, int maxInt){
      Scanner scan = new Scanner(System.in);
      int number;
      boolean isNotValid = false;
      
      do{
         if(isNotValid){
            System.out.printf("The number must be between %d and %d%n", minInt, maxInt);
         }
         while(!scan.hasNextInt()){
            System.out.printf("That is not a number! Please try again.%n");
            scan.next();
         }
         // hasNextInt() true, reading number from console
         number = scan.nextInt();
         isNotValid = number < minInt || number > maxInt;
      }while(isNotValid);
      
      return number;
   }

   public static int getPhoneNumber(){
      Scanner scan = new Scanner(System.in);
      
      while(!scan.hasNextInt()){
         System.out.printf("The entered phone number %s is not a number! Please try again%n", scan.nextLine());
      }
      return scan.nextInt();
   }
   
   public static int getSalary(){
      Scanner scan = new Scanner(System.in);
      
      while(!scan.hasNextInt()){
         System.out.printf("The entered salary %s is not a number! Please try again%n", scan.nextLine());
      }
      return scan.nextInt();
   }

   public static int getIntFromUser(){
      Scanner scanInput = new Scanner(System.in);
      while(!scanInput.hasNextInt()){
         System.out.printf("The input '%s' is not a number. Please try again.%n", scanInput.nextLine());
         continue;
      }
      int retVal = scanInput.nextInt();
      scanInput.nextLine(); // Eating leftover newline
      return retVal;
   }
   
}