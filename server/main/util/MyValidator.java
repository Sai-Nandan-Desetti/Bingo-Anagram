package main.util;

import main.exceptions.*;
import main.io.*;

import java.io.*;
import java.util.*;

/**
 * My custom Validator class that defines a {@code getInteger()} to read an integer from the input stream.
 */
public class MyValidator{

       MyBufferedReader in;
       PrintStream    out;

     
       public MyValidator(MyBufferedReader in, PrintStream out){

              this.in = in;
              this.out = out;
       }


       public PrintStream getOutputStream(){
   
              return out;
       }


       public MyBufferedReader getReader(){

              return in;
       }

       /** 
       * Note: This function doesn't return until the user enters a valid input!
       */
       public int getInteger(int lb, int ub) throws IOException{
              
              while(true){
             
                    try{
                          return in.getInt(lb,ub);                         
                    }
                    catch(NumberFormatException n){
                          out.println("\nPlease enter an INTEGER!");
                          out.println("Enter again: ");
                          out.println("EOI");
                    }
                    catch(OutOfBoundsException o){
                          out.println("\n"+o.getMessage());
                          out.println("Enter again: ");
                          out.println("EOI");
                    }
              }            
       }
}
