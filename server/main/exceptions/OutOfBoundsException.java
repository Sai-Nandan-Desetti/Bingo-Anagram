package main.exceptions;

public class OutOfBoundsException extends RuntimeException{

       public OutOfBoundsException(int lowerBound, int upperBound){
    
              super("The number must be between "+lowerBound+" and "+upperBound+" (both inclusive)");
       }
}
