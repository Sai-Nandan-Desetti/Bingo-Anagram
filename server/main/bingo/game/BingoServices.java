package main.bingo.game;

import main.bingo.player.*;
import main.bingo.utility.*;

import main.exceptions.*;
import main.io.*;
import main.util.*;

import java.io.*;
import java.util.*;


public class BingoServices{

       MyBufferedReader in;
       PrintStream out;

       Player currentPlayer;
       Player opponent;                     
          

       public BingoServices(MyBufferedReader in, PrintStream out){

              this.in = in;
              this.out = out;
       }


       public ArrayList<Integer> getData() throws IOException{

              ArrayList<Integer> gridArray = new ArrayList<Integer>();
              ArrayList<Integer> checkArray = new ArrayList<Integer>();
              FixedCapacityArrayList<Integer> eleInt = new FixedCapacityArrayList<Integer>(5);

              StringTokenizer element;
              String          row_str;
              int             size,number;

              for(int j=0;j<BingoGrid.gridSize;j++)
                  checkArray.add(0);

              out.println("\nEnter your Bingo grid.");                                         

              while(gridArray.size() < BingoGrid.gridSize){


                 try{
                      out.println("Row " + (gridArray.size()/BingoGrid.dim + 1) + ":");
                      out.println("EOI");   
         
                      row_str = in.readLine();
                      element = new StringTokenizer(row_str);    

                      while(element.hasMoreTokens()){

                            size = gridArray.size();
                            number = Integer.parseInt(element.nextToken());

                            if(number<1 || number>BingoGrid.gridSize)
                               throw new NumberFormatException();

                            if(!eleInt.add(number))
                               throw new IncorrectNumberOfElementsException(BingoGrid.dim);
 
                            if(checkArray.get(eleInt.get(size % BingoGrid.dim)-1)==0){
                               gridArray.add(eleInt.get(size % BingoGrid.dim));
                               checkArray.set(eleInt.get(size % BingoGrid.dim)-1,eleInt.get(size % BingoGrid.dim));
                            }

                            else
                               throw new RepeatingElementException();
                      }

                      if(eleInt.size()<eleInt.getCapacity())
                         throw new IncorrectNumberOfElementsException(BingoGrid.dim);

                      eleInt.clear();
                 }              

                 catch(IncorrectNumberOfElementsException ar){
                         
                       while(!eleInt.isEmpty()){
                             checkArray.set(eleInt.remove(eleInt.size()-1)-1,0);
                             gridArray.remove(gridArray.size()-1);
                       }

                       out.println("\n"+ar.getMessage()+" in a row");
                       out.println("Enter the numbers (for the last row) again.\n");
                       displayGridStatus(gridArray);

                 }

                 catch(NumberFormatException n){
                       
                       while(!eleInt.isEmpty()){
                             checkArray.set(eleInt.remove(eleInt.size()-1)-1,0); 
                             gridArray.remove(gridArray.size()-1);
                       }

                       out.println("\nOnly integers between 1 and "+BingoGrid.gridSize+" please!");
                       out.println("Enter the (last)row elements again.\n");
                       displayGridStatus(gridArray);
                 }

                 catch(RepeatingElementException r){
                                                    
                       while(!eleInt.isEmpty()){
                             if(eleInt.size()>1)
                                gridArray.remove(gridArray.size()-1);
                             checkArray.set(eleInt.remove(eleInt.size()-1)-1,0);                                                     
                       }

                       out.println("\nThe last row contains numbers that have already been used.");
                       out.println("Enter again.\n");
                       displayGridStatus(gridArray);
                 }
              }

              return gridArray;
       }       



       public ArrayList<Integer> generateData(){

              ArrayList<Integer> gridArray = new ArrayList<Integer>();         

              for(int i=0;i<BingoGrid.gridSize;i++)
                  gridArray.add(i+1);
              Collections.shuffle(gridArray);                            
               
              return gridArray;
       }



       private void displayGridStatus(ArrayList<Integer> gridArray){
                            
              if(gridArray.isEmpty())
                 out.println("Your grid as of now is empty\n");
     
              else{
                    String row="";

                    out.println("Your grid as of now:");                    

                    for(int j=0;j<gridArray.size();j++){
                        row+=gridArray.get(j)+" ";

                        if((j+1) % BingoGrid.dim==0){
                          out.println(row);
                          row="";
                        }
                    }                  
              }
       }

       
      
       public void displayResult() throws IOException{
       
              int n1 = currentPlayer.getBingoSize();
              int n2 = opponent.getBingoSize();

              String result="",choice;
                            
              if(n1==n2)
                 result = "DRAW!";
              
              else if(n1>n2)
                  result = currentPlayer.getName()+" WINS!";                  
              
              else
                  result = opponent.getName()+" WINS!";
                  
              out.println("\nGAME OVER!!!");
              out.println(result);
              
              out.println("\nDo you want to see " + opponent.getName() + "'s grid?(y/n)");
              out.println("EOI");
              
              choice = in.readLine();
              if(choice.equals("y"))
                 opponent.display(out);
        }           
        
}       


