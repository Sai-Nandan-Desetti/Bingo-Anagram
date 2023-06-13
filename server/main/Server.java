package main;

import main.ServerThread;

import java.io.*;
import java.net.*;
import java.util.*;


public class Server{
 
       /**Description: Server class for providing various services to various clients

          Submission Date: 1.3.2020
        */


       public static void main(String args[]){

              int port=-1; 
              ServerSocket server;
              
              while(true){
                 
                    try{
                         Random r = new Random();                   
                         port = r.nextInt(5000);      
                   
                         server = new ServerSocket(port);
                         System.out.println("Server listening on port: " + port);
                   
                         break;
                    }     
                    catch(IOException i){ }                   
              }
                   
              try{
                   while(true){
                  
                         Socket connectToClient = server.accept();

                         new ServerThread(connectToClient).start();
                           
                   }                                                           
              }
              catch(IOException ie){
              
                    System.out.println("\nUnable to establish connection with client");
              }
       }
}
