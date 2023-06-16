package main.io;

import java.io.*;

/**
 * <ul>
 *     <li>My customized PrintStream that defines a printLine function for integers and strings.</li>
 *     <li>"EOI" indicates End Of Input.</li>
 * </ul>
 * <p>
 * Why do we need {@code printLine()} ?
 * <p>
 * Suppose the server needs to print a message to the client.
 * And the message is composed of a series of lines.
 * You can use the default {@code println()} to print the lines.
 * Now, how will the client know when to stop reading lines from the server?
 * There has to be some special token to indicate the end of input.
 * So, to print the last line of the message, the server needs to use {@code printLine()} rather than {@code println()}.
 * <p>
 * <b>Note:</b>
 * This class is required only on the server end. The client always prints only a single line.
 * So, {@code println()} suffices. This class is useful when multiple lines have to be streamed.
*/
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
