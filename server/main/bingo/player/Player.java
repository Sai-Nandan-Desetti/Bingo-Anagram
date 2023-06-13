package main.bingo.player;

import main.bingo.game.*;
import main.bingo.utility.BingoGrid;

import main.io.*;
import main.util.*;

import java.io.*;
import java.util.*;


public class Player{


//Data Members...
       
       int pos;
       String name;
       BingoGrid playerGrid;
       
       ArrayList<Character> bingo     = new ArrayList<Character>();
       ArrayList<Character> rowBingo  = new ArrayList<Character>();
       ArrayList<Character> colBingo  = new ArrayList<Character>();
       ArrayList<Character> diagBingo = new ArrayList<Character>();

       MyBufferedReader in;
       PrintStream      out;



//Methods...


       public Player(MyBufferedReader in, PrintStream out){             
              
              this.in = in;
              this.out = out;

              this.playerGrid = new BingoGrid(); 
              
              bingo.add('B');
              bingo.add('I');
              bingo.add('N');
              bingo.add('G');
              bingo.add('O');                            

              for(int i=0;i<BingoGrid.dim;i++){
                  rowBingo.add(' ');
                  colBingo.add(' ');
              }
              
              for(int i=0;i<2;i++)
                  diagBingo.add(' ');                     
       }

       
       public void setPos(int pos){
       
              this.pos = pos;
       }
       

       public void getReady() throws IOException{

              out.println("Enter your name: ");
              out.println("EOI");
              
              this.name = in.readLine();
              
              out.println("\nEnter your choice:");
              out.println("1. Initialize your grid yourself.");
              out.println("2. Let the computer generate one for you.");
              out.println("EOI");                         

              switch(new MyValidator(in,out).getInteger(1,2)){
 
                   case 1:	this.initializeGrid(new BingoServices(in,out).getData());
                                break;

                   case 2:	this.initializeGrid(new BingoServices(in,out).generateData());
                                break;
              }
              
              this.display();   
       }


       public int chance() throws IOException{

              int num;  

              MyValidator p_io = new MyValidator(in,out);

              out.println("Your number:"); 
              out.println("EOI"); 
              
              num = p_io.getInteger(1,BingoGrid.gridSize);

              while(!modify(num)){
                    out.println("The number has already been cut.\nEnter another number:");
                    out.println("EOI");
                    num = p_io.getInteger(1,BingoGrid.gridSize); 
              }
              
              return num;
       }

       public void BingoStep(int row, int col){
              
              int i;
              LinkedList<LinkedList<Integer>> rowList = playerGrid.getRowList();
              
              rowList.get(row).set(col,0);        

              for(i=0; i<BingoGrid.dim && rowList.get(row).get(i)==0; i++);
              if(i==BingoGrid.dim)
                 try{                  
                      rowBingo.set(row,bingo.remove(0));
                 }
                 catch(IndexOutOfBoundsException in){
                       rowBingo.set(row,'O');
                 }


              for(i=0; i<BingoGrid.dim && rowList.get(i).get(col)==0; i++);
              if(i==BingoGrid.dim)
                 try{                  
                      colBingo.set(col,bingo.remove(0));
                 }
                 catch(IndexOutOfBoundsException in){
                       colBingo.set(col,'O');
                 }


              if(row==col){
                 for(i=0;i<BingoGrid.dim;i++)
                     if(rowList.get(i).get(i)!=0)
                        break;
                 if(i==BingoGrid.dim)
                    try{                  
                         diagBingo.set(0,bingo.remove(0));
                    }
                    catch(IndexOutOfBoundsException in){
                          diagBingo.set(0,'O');
                    }

              }

              if(row+col==BingoGrid.dim-1){
                 for(i=0;i<BingoGrid.dim;i++)

                     if(rowList.get(i).get(BingoGrid.dim-i-1)!=0)
                        break;             
                 if(i==BingoGrid.dim)
                    try{                  
                         diagBingo.set(1,bingo.remove(0));
                    }
                    catch(IndexOutOfBoundsException in){
                          diagBingo.set(1,'O');
                    }
              }              
       }


       private int getSize(ArrayList<Character> bl_rcd){
            
              int size=0;
              for(int i=0;i<bl_rcd.size();i++)
                  if(bl_rcd.get(i)!=' ')
                     size++;
             
              return size;
       }


       public void initializeGrid(ArrayList<Integer> gridArray){

              playerGrid.initialize(gridArray);
       }


       public int getPosition(){
       
              return pos;
       }
       
       
       public String getName(){

              return name;
       }
       
       
       public PrintStream getOutputStream(){
 
              return out;
       }
 
                     
       public int getBingoSize(){
               
              return getSize(rowBingo)+getSize(colBingo)+getSize(diagBingo);
       }


       public boolean modify(int num){                   
                
              int row,col;
              LinkedList<LinkedList<Integer>> rowList = playerGrid.getRowList();
              
              for(row=0,col=0; row<BingoGrid.dim && (col=rowList.get(row).indexOf(num))==-1; row++);

              if(row==BingoGrid.dim)
                 return false;
              
              BingoStep(row,col);             
         
              return true;
       }               

        
       public boolean isBingo(){              
            
              return bingo.isEmpty();
       }       


       public void displayChance(String name, int num){
            
              out.println(name+"'s number: "+num);
       }
       
       
       public void displayPlayerTurn(String name){
                   
              out.println(name + "'s turn...");
       }

       
       public void display(){

              display(out);
       }


       public void display(PrintStream out){
   
              int i,j;
              String temp="";
              LinkedList<LinkedList<Integer>> rowList = playerGrid.getRowList();
              
              out.println("\n\n"+name+":");
              out.println(" \t \t \t \t    "+diagBingo.get(1)+"\n");

              for(i=0;i<BingoGrid.dim;i++,temp=""){

                  for(j=0;j<BingoGrid.dim;j++)
                      if(rowList.get(i).get(j)==0)
                         temp+="X\t";                                      
                      else
                         temp+=((Integer)rowList.get(i).get(j)).toString()+"\t";

                  out.println(temp+"\b\b\b\b"+rowBingo.get(i)+"\n");
              }
              
              for(i=0;i<BingoGrid.dim;i++)
                  temp+=colBingo.get(i)+"\t";
              
              out.println(temp+"\b\b\b\b"+diagBingo.get(0));
       }
                                         
}
