import java.util.*;
import java.io.*;

public class Guest{
   private int guestID;
   private String firstName;
   private String lastName;
   private String address;
   private int phoneNumber;
   
   // To write region code on phone number, use '00' instead of '+'
   public Guest(int guestID, String firstName, String lastName, int phoneNumber){
      this.guestID = guestID;
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
   }
}