package main;

import main.bingo.*;
import main.dictionary.*;

import main.io.*;
import main.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class ServerThread extends Thread{

       Socket socket;

       
       public ServerThread(Socket socket){

              this.socket = socket;
       }

       /**
         <ul>
             <li>This method is automatically invoked when an object of ServerThread invokes start().
             <li>Each ServerThread interacts with an individual client, and, therefore, has the responsibility of
                  <ol>
                     <li>offering the services to the client, and
                     <li>invoking the respective functions based on the client's choice.
                  </ol>
          </ul>
        */
       public void run(){

              try{
                   String choice="";
                   MyBufferedReader fromClient = new MyBufferedReader(new InputStreamReader(socket.getInputStream()));
                   MyPrintStream toClient = new MyPrintStream(socket.getOutputStream(),true);

                   do{
                       toClient.println("We can provide the following services:");
                       toClient.println("1. Play BINGO!!");                  
                       toClient.println("2. Dictionary");     
                       toClient.printLine("\nEnter the service no. you wish to avail: ");                
                   
                       switch(new MyValidator(fromClient,toClient).getInteger(1,2)){
            
                           case 1:  PlayBingo.play(fromClient,toClient);
                                    break;

                           case 2:  try{
                                        UseDictionary.use(fromClient,toClient);
                                    }
                                    catch(Exception e){
                                          toClient.println("Sorry! Internal issue with the files...");
                                          toClient.printLine("We will not be able to provide the services right now.");
                                    }
                                    break;
                       }  
                          
                       toClient.printLine("\nDo you want to use any other service?(y/n)");
                       
                       choice = fromClient.readLine();
                            
                   }while(choice.equals("y"));
                                      
                   toClient.println("BYE!!");
                   
                   socket.close();                   
             }

             catch(IOException i){
                            
                  System.out.println("Sorry! There's an issue connecting with the client."); 
             }
   
       }
}
              
