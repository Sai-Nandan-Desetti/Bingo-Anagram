package main.util;

import java.util.*;


public class FixedCapacityArrayList<T> extends ArrayList<T>{

//Data Members...
       int capacity;


//Methods...
       public FixedCapacityArrayList(int capacity){
              
              this.capacity=capacity;
       }


       @Override
       public boolean add(T o){
             
              if(this.size()<capacity)
                 return super.add(o);

              return false;
      }

      public int getCapacity(){

             return capacity;
      }
}
          
