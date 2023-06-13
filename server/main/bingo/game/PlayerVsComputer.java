package main.bingo.game;

import main.bingo.*;
import main.bingo.player.*;

import main.util.*;
import main.io.*;

import java.io.*;
import java.util.*;


public class PlayerVsComputer extends BingoServices{


//Methods...

       public PlayerVsComputer(MyBufferedReader in, PrintStream out, Player p){
          
              super(in,out);
              
              currentPlayer = p;
       }


       public void getOpponentReady(Player computer) throws IOException{
       
              computer.getReady();
              opponent = computer;
       }
       
       
       public void play(Player computer) throws IOException{                                        
        
              getOpponentReady(computer);

              Player temp;
              int i=0,num;
                              
              out.println("\n\n\t\tLET'S BEGIN THE GAME");

              do{
                  if(i%2==0){
                     num = currentPlayer.chance();
                     opponent.displayChance(currentPlayer.getName(),num);                  
                     opponent.modify(num);
                  }
                  
                  else{
                        num = opponent.chance();
                        currentPlayer.displayChance(opponent.getName(),num);                  
                        opponent.modify(num);
                        currentPlayer.modify(num);
                  }

                  currentPlayer.display();
                  opponent.display();                                

                  i++;
                  
              }while(!(currentPlayer.isBingo() || opponent.isBingo()));

              displayResult();       
       }      

}
