import java.util.*;
import java.io.*;

public class InputHelper{

   // Returns a integer between minInt..maxInt
   public static int getOptionFromUser(int minInt, int maxInt){
      Scanner scan = new Scanner(System.in);
      int retNumber;
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
         retNumber = scan.nextInt();
         isNotValid = retNumber < minInt || retNumber > maxInt;
      }while(isNotValid);
      
      return retNumber;
   }

   public static int getPhoneNumber(){
      Scanner scan = new Scanner(System.in);
      
      while(!scan.hasNextInt()){
         System.out.printf("The entered phone number %s is not a number! Please try again%n", scan.nextLine());
         continue;
      }
      return scan.nextInt();
   }

   public static int getIntFromUser(){
      Scanner scanInput = new Scanner(System.in);
      
      while(!scanInput.hasNextInt()){
         System.out.printf("The input '%s' is not a number. Please try again.%n", scanInput.nextLine());
         continue;
      }
      return scanInput.nextInt();
   }
   
   public static double getDoubleFromUser(){
      Scanner scanInput = new Scanner(System.in);

      while(!scanInput.hasNextDouble()){
         System.out.printf("The input '%s' is not a number. Please try again.%n", scanInput.nextLine());
         continue;
      }
      return scanInput.nextDouble();
   }
}