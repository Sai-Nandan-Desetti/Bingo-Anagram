package main.bingo.game;

import main.bingo.player.*;

import main.io.*;
import main.util.*;

import java.io.*;
import java.util.*;


public class PlayerVsPlayer extends BingoServices{


//Data Members...
                     
       static ArrayList<Integer> chanceIndicatorList = new ArrayList<Integer>();
       
       static ArrayList<Boolean> gameOverList = new ArrayList<Boolean>();


//Methods...       
       public PlayerVsPlayer(MyBufferedReader in, PrintStream out, Player p){
       
              super(in,out);
       
              currentPlayer = p;
              
              int pos = currentPlayer.getPosition();
              
              chanceIndicatorList.add(pos/2,pos-(pos%2));                                        
              gameOverList.add(pos/2,false);              
       }
       


       public void getOpponentReady(Player opp){       
       
               opponent = opp;
               out.println("Your opponent is " + opponent.getName());
       }
       
       
       public void play(Player opp) throws IOException{
       
              getOpponentReady(opp);
              
              int pos = currentPlayer.getPosition();
                            
              out.println("\n\n\t\tLET'S BEGIN THE GAME\n");
              
              do{                       
                  makeYourMove(currentPlayer); 
                  
              }while(!(currentPlayer.isBingo() || opponent.isBingo()));
              
              if(pos%2==1)
                 gameOverList.set(pos/2,true);
                 
              else if(opponent.isBingo())
                 gameOverList.set(pos/2,true);
                                 
              while(!gameOverList.get(pos/2).booleanValue()){
                    try{
                         Thread.sleep(500);
                    }
                    catch(InterruptedException ie){ } 
              }                                                           
              
              displayResult();                                   
       }
       
                         
       public synchronized void makeYourMove(Player p) throws IOException{
                                          
              int pos = currentPlayer.getPosition();
              
              while(pos!=chanceIndicatorList.get(pos/2).intValue()){
                 try{                          
                      wait(50);
                                            
                 }catch(InterruptedException i){ }
              }
                
              if(!gameOverList.get(pos/2).booleanValue()){                       
              
                 opponent.displayPlayerTurn(currentPlayer.getName());
              
                 int num = currentPlayer.chance();                   
                 currentPlayer.display();
              
                 opponent.displayChance(currentPlayer.getName(),num);
                 opponent.modify(num);
                 opponent.display();
                    
                 chanceIndicatorList.set(pos/2,chanceIndicatorList.get(pos/2).intValue()+(-2*(pos%2)+1));                   
              
                 notify();      
              }
       }  
                                                       
}


