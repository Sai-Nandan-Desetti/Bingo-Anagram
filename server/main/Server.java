package main;

import main.ServerThread;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * The main {@code Server} class.
 * <ul>
 *    <li>Creates a {@code ServerSocket} that accepts and connects to any TCP client.
 *    <li>For each connected client, a {@code ServerThread} is created to interact with the client.
 *    <ul>
 *          <li> This is how multiple clients are handled simultaneously.
 *    </ul>
 * </ul>
 */
public class Server{
 
       /**Description: Server class for providing various services to various clients

          Submission Date: 1.3.2020
        */


       public static void main(String args[]){

              int port=-1; 
              ServerSocket server;
              
              while(true){
                 
                    try{
                         // To create a ServerSocket, we manually and randomly generate a port number.
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
                        
                         // The server accepts if the client creates a Socket using 
                         // 1. the hostname the server is hosted on and
                         // 2. the port number the server is listening on.
                         Socket connectToClient = server.accept();

                         // start() automatically invokes the ServerThread object's run() method
                         new ServerThread(connectToClient).start();
                        
                         // This loop continues infinitely.
                         // The Server keeps waiting for new clients.
                         // That's why to shut down the server, you've to explicitly press `Ctrl+c`.
                   }                                                           
              }
              catch(IOException ie){
              
                    System.out.println("\nUnable to establish connection with client");
              }
       }
}
