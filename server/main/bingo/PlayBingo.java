package main.bingo;

import main.bingo.player.*;
import main.bingo.game.*;

import main.io.*;
import main.util.*;

import java.io.*;
import java.util.*;


public class PlayBingo{

       static LinkedList<Player> players = new LinkedList<Player>();
       

       public static void play(MyBufferedReader fromClient, MyPrintStream toClient) throws IOException{

              int option,pos;
              Player player;
              
              toClient.println("\n\n\n\t\t\tWELCOME TO THE BINGO CLUB!!\n");
              
              player = new Player(fromClient,toClient);
              player.getReady();
              
              toClient.println("Enter your choice: ");
              toClient.println("1.Player vs Player.");
              toClient.printLine("2.Player vs Computer.");

              switch(new MyValidator(fromClient,toClient).getInteger(1,2)){

                  case 1:       do{
                                    option = 0;                                    
                                    players.add(player);
                                    
                                    pos = players.indexOf(player);
                                    players.get(pos).setPos(pos);
                                
                                    
                                    if(players.size()%2==1){
                                       toClient.println("Searching for an opponent...");
                                       toClient.println("(It may take a few seconds)\n");        
                                    }   
                                    
                                    if(playersAreAvailable())
                                       break;
                                       
                                    else{
                                          players.remove(player);                                             
                                             
                                          toClient.println("Sorry! Cannot find anyone!\n");
                                          toClient.println("What do you want to do?");
                                          toClient.println("1.Try again.");
                                          toClient.println("2.Play with computer.");
                                          toClient.println("Option: ");
                                          toClient.println("EOI");
                   
                                          option = new MyValidator(fromClient,toClient).getInteger(1,2);                                          
                                    }   
                                    
                                }while(option==1);

                                if(option==0){                                
                                   new PlayerVsPlayer(fromClient,toClient,player).play(players.get(pos+(-2*(pos%2))+1));
                                   break;                                   
                                }
                                
                                
                  case 2:	PlayerVsComputer pc = new PlayerVsComputer(fromClient,toClient,player);
                                pc.play(new Computer(fromClient,System.out));
                                
                                break;
                                                                  
              }

              toClient.println("\nHope you enjoyed playing!");
       }
       
       
       private static boolean playersAreAvailable(){
       
              long endTime = System.currentTimeMillis() + 5000;
                             
              while(System.currentTimeMillis()<endTime)                    
                    if(players.size()%2==0)
                       break;
                       
              return players.size()%2==0;
       }                                                       
}

