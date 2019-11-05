import java.io.*;
import java.util.*;

public class Menu{

   public static void showMenu(){
      Scanner input = new Scanner(System.in);
      
      System.out.println("Please select the action you want to perform.");
      System.out.println("Type in the number followed by a press of the enter key.");
      
      System.out.printf("%d to show, create, or edit bookings%n", 1);
      System.out.printf("%d to show, create, or edit customers%n", 2);
      System.out.printf("%d to show, create or edit bookings%n", 3);
      System.out.printf("%d to show, create, or edit members%n", 4);
      System.out.printf("%d to exit the program. All work is saved%n", 5);
      
      
   }
}