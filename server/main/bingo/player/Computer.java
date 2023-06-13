package main.bingo.player;

import main.bingo.player.*;
import main.bingo.game.*;
import main.bingo.utility.BingoGrid;

import main.util.*;
import main.io.*;

import java.io.*;
import java.util.*;


public class Computer extends Player{


       HashMap<Integer,Integer> cross_freq = new HashMap<Integer,Integer>();


       public Computer(MyBufferedReader in, PrintStream out){
              
              super(in,out);

              for(int i=1; i<=BingoGrid.gridSize; i++)
                  cross_freq.put(i,0);
       }

      
       public void getReady(){
       
              this.name = "Computer";
              this.initializeGrid(new BingoServices(in,out).generateData());
              
              this.display();
       }
       
              
       @Override
       public void BingoStep(int row, int col){
              
              int i,check;
              LinkedList<LinkedList<Integer>> rowList = playerGrid.getRowList();

              cross_freq.remove(rowList.get(row).get(col));
 
              rowList.get(row).set(col,0);        

              for(check=i=0; i<BingoGrid.dim; i++)
                  if(rowList.get(row).get(i)!=0){
                     cross_freq.merge(rowList.get(row).get(i),1,Integer::sum);
                     check=1;
                  }

              if(check==0)
                 try{                  
                      rowBingo.set(row,bingo.remove(0));
                 }
                 catch(IndexOutOfBoundsException in){
                       rowBingo.set(row,'O');
                 }


              for(check=i=0; i<BingoGrid.dim; i++)
                  if(rowList.get(i).get(col)!=0){
                     cross_freq.merge(rowList.get(i).get(col),1,Integer::sum);
                     check=1;
                  }

              if(check==0)
                 try{                  
                      colBingo.set(col,bingo.remove(0));
                 }
                 catch(IndexOutOfBoundsException in){
                       colBingo.set(col,'O');
                 }


              if(row==col){
                 for(check=i=0;i<BingoGrid.dim;i++)
                     if(rowList.get(i).get(i)!=0){
                        cross_freq.merge(rowList.get(i).get(i),1,Integer::sum);
                        check=1;
                     }                     
                 if(check==0)
                    try{                  
                         diagBingo.set(0,bingo.remove(0));
                    }
                    catch(IndexOutOfBoundsException in){
                          diagBingo.set(0,'O');
                    }

              }

              if(row+col==BingoGrid.dim-1){
                 for(check=i=0;i<BingoGrid.dim;i++)
                     if(rowList.get(i).get(BingoGrid.dim-i-1)!=0){
                        cross_freq.merge(rowList.get(i).get(BingoGrid.dim-i-1),1,Integer::sum);
                        check=1;
                     }
                 if(check==0)
                    try{                  
                         diagBingo.set(1,bingo.remove(0));
                    }
                    catch(IndexOutOfBoundsException in){
                          diagBingo.set(1,'O');
                    }
              } 
       }


       @Override
       public int chance() throws IOException{
                          
              LinkedList<Integer> sortedValues = new LinkedList<Integer>(cross_freq.values());
        
              Collections.sort(sortedValues);
        
              for(Map.Entry<Integer,Integer> sf : cross_freq.entrySet())
                  if(sf.getValue().equals(sortedValues.getLast()))
                     return sf.getKey().intValue();

              return 0;      
       }
                     
}
