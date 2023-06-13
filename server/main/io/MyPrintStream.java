package main.io;

import java.io.*;


public class MyPrintStream extends PrintStream{

       
       public MyPrintStream(OutputStream out, boolean autoFlush){

              super(out,autoFlush);
       }

       public void printLine(int num){

              println(num);
              println("EOI");  
       }

       public void printLine(String line){

              println(line);
              println("EOI");    
       }

}
