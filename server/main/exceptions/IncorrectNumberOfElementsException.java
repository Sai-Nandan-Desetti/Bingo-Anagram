package main.exceptions;


public class IncorrectNumberOfElementsException extends RuntimeException{ 

       public IncorrectNumberOfElementsException(int correct_no){

              super("You must have "+correct_no+" of elements");
       }
}
