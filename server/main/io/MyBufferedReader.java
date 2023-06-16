package main.io;

import main.exceptions.OutOfBoundsException;

import java.io.*;

/**
 * My custom {@code BufferedReader} that defines a {@code getInt()} such that
 * the input integer is within the bounds specified: [lower bound ({@code lb}), upper bound ({@code ub})].
 * <p>
 * <b>Note</b>:
 * This class is required only on the server end. The client passes to the server whatever message it reads from the system.
 * It doesn't need to any checking for the validity of the input. The server needs to do that.
 * </p>
 */
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