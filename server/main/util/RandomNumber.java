package main.util;

import java.util.*;


public class RandomNumber extends Random{
      
       public int nextInt(int lowerBound, int upperBound){

              int num;

              do{
                   num = super.nextInt(upperBound);

              }while(num<lowerBound);

              return num;
       }
}
