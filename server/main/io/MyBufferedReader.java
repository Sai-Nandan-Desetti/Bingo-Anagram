package main.io;

import main.exceptions.OutOfBoundsException;

import java.io.*;


public class MyBufferedReader extends BufferedReader{


       public MyBufferedReader(Reader r){
  
              super(r);
       }

       public int getInt(int lb, int ub)throws IOException, NumberFormatException, OutOfBoundsException{

              int num = Integer.parseInt(readLine());
         
              if(num<lb || num>ub)
                 throw new OutOfBoundsException(lb,ub);
      
              return num;
       }
       
}


              
