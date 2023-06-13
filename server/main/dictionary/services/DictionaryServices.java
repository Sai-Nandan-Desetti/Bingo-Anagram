package main.dictionary.services;

import main.io.*;
import main.util.*;

import java.io.*;
import java.util.*;


public class DictionaryServices{

       MyBufferedReader in;
       MyPrintStream    out;

       LinkedHashSet<String> wordsSet;
       LinkedHashSet<String> anagramsSet;
                     

       public DictionaryServices(MyBufferedReader in, MyPrintStream out)
              throws InvalidClassException, ClassNotFoundException, IOException
       {
              this.in = in;
              this.out = out;
                
              wordsSet = readMyWords("Words",new LinkedHashSet<String>());              
              anagramsSet = new LinkedHashSet<String>();
       }


       @SuppressWarnings("unchecked")
       private static LinkedHashSet<String> readMyWords(String fileName, LinkedHashSet<String> words) 
               throws InvalidClassException, ClassNotFoundException, IOException
       {
              ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));    
              
              words = (LinkedHashSet<String>)objectIn.readObject();
    
              objectIn.close();           

              return words;
       }

        
       private int isMeaningfulWord(String word){
                     
               int i=0,a=0;
               
               for(String w: wordsSet){
                   i++;
                   String words[] = w.split("; ");
                   for(a=0; a<words.length; a++)
                       if(words[a].equals(word))
                          return i;
               }
               
               return 0;
       }


       public void printMeaning(String word) throws FileNotFoundException, InvalidClassException, NotSerializableException, IOException{

              String line,nextWord;              
              
              out.println("\n");              

              int nextWordIndex = isMeaningfulWord(word.toUpperCase());
              
              if(nextWordIndex!=0){
                            
                 Scanner input = new Scanner(new File(Character.toString(word.toUpperCase().charAt(0))));
                                                   
                 LinkedList<String> wordsList = new LinkedList<String>(wordsSet);

                 word = wordsList.get(nextWordIndex-1);
                                  
                 nextWord = wordsList.get(nextWordIndex);

                 while(input.hasNextLine()){

                    line = input.nextLine();

                    if(line.length()==0){

                       if(input.hasNextLine()){

                          line = input.nextLine();

                          if(line.equals(word)){
                             
                             do{
                                 out.println(line);                 
                                 line = input.nextLine();

                             }while(!line.equals(nextWord));                                              

                             break;
                          }
                       }
                    }
                 }

                 input.close();
              }
              
              else{
                    out.println("Sorry! The word is not present in the dictionary.");
              }              
       }


       private void getDistinctAnagrams(String word){
              
              LinkedHashSet<String> wordPermutations = MyString.getDistinctPermutations(word.toUpperCase());             
 
              for(String s: wordPermutations)
                  if(isMeaningfulWord(s)!=0)
                     anagramsSet.add(s);

       }
 

       public void printAnagrams(String word){
              
              word = word.toUpperCase();                            
              
              anagramsSet.add(word);
              getDistinctAnagrams(word);
              
              if(anagramsSet.size()==1)
                 out.println(word + " does not have any anagrams.");

              else{
                    out.println("\n\n" + word + " has the following anagrams: ");

                    anagramsSet.remove(word);
                    for(String anagram : anagramsSet)
                        out.println(anagram);
              }
              
              anagramsSet.clear();
       }

}

