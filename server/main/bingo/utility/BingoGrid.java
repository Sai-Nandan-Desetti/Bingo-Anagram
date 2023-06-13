package main.bingo.utility;

import java.util.*;


public class BingoGrid{

       /** Description: Creates a Bingo Grid and initializes it.
        */


//Data Members...

       public static final int gridSize = 25;
       public static final int dim = (int)Math.sqrt(gridSize);

       LinkedList<LinkedList<Integer>> rowList = new LinkedList<LinkedList<Integer>>();


//Methods...    
       public BingoGrid(){  

              for(int i=0;i<dim;i++)
                  rowList.add(new LinkedList<Integer>());
       }
       

       public void initialize(ArrayList<Integer> gridArray){
              for(int i=0;i<gridSize;i++)                  
                  rowList.get(i/dim).add(gridArray.get(i));                 
       }              

       
       public LinkedList<LinkedList<Integer>> getRowList(){
  
              return rowList;
       }
        
}
