package main.dictionary;

import main.dictionary.services.*;
import main.dictionary.*;

import main.io.*;
import main.util.*;

import java.io.*;

import java.util.*;


public class UseDictionary{

       public static void use(MyBufferedReader fromClient, MyPrintStream toClient) 
              throws InvalidClassException, ClassNotFoundException, FileNotFoundException, IOException
       {
             
              String word,choice;
                
              DictionaryServices ds = new DictionaryServices(fromClient,toClient); 
              
              do{
                   toClient.printLine("Enter the word: ");
                   word = fromClient.readLine();

                   toClient.println("Enter your choice: ");
                   toClient.println("1. Get word meaning.");
                   toClient.printLine("2. Get anagrams (if present).");

                   switch(new MyValidator(fromClient,toClient).getInteger(1,2)){

                       case 1:	 ds.printMeaning(word);
                                 break;

                       case 2:   ds.printAnagrams(word);
                                 break;
                   }
        
                   toClient.printLine("\n\nDo you want to continue using the dictionary?(y/n)");
                   choice = fromClient.readLine();

              }while(choice.equals("y"));

              toClient.println("Thank You for using our dictionary...");
       }

}

                   
               
