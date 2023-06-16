package main;

import java.io.*;
import java.net.*;
import java.util.*;


public class Client{

       private static boolean readMessage(BufferedReader fromServer) throws IOException{
  
              String message = fromServer.readLine();              

              while(!message.equals("EOI")){
              
                  System.out.println(message);
                  
                  if(message.equals("BYE!!"))
                     return true;
                     
                  message = fromServer.readLine();
              }    
              
              return false;
       }
              

       public static void main(String args[]){

              try{
                   String response;

                   if(args.length>2)
                      throw new ArrayIndexOutOfBoundsException();
                      
                   Socket connectToServer = new Socket(args[0],Integer.parseInt(args[1]));

                   BufferedReader fromServer = new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));
                   PrintStream    toServer = new PrintStream(connectToServer.getOutputStream(),true);
              
                   BufferedReader fromSystem = new BufferedReader(new InputStreamReader(System.in));

                   // the only time you break from this loop is when you read "BYE!!" from the server...
                   while(!readMessage(fromServer)){                                    
                         response = fromSystem.readLine();                                           
                         toServer.println(response);
                   } 
                   // ...which is an indication for you to close the connection with the server.
                   connectToServer.close();
              }
              
              catch(ArrayIndexOutOfBoundsException a){
              
                    System.out.println("\nIncorrect input: you must have two arguments- Host name and port number.");
              }
              
              catch(NumberFormatException n){
              
                    System.out.println("\nPort number must be an integer!");
              }
              
              catch(IOException i){

                    System.out.println("\nSorry! Unable to establish a connection with the server.");
              }
       }
}
