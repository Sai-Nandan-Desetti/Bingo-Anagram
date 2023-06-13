package main.util;

import java.util.*;


public class MyString{

       public static LinkedHashSet<String> getDistinctPermutations(String word){

              if(word.length() == 0){ 

                 LinkedHashSet<String> basePermutation = new LinkedHashSet<>(); 
                 basePermutation.add(""); 

                 return basePermutation; 
              } 
  
              char ch = word.charAt(0);                

              LinkedHashSet<String> secondaryPermutations = getDistinctPermutations(word.substring(1));

 
              LinkedHashSet<String> permutations = new LinkedHashSet<>(); 

              for(String s : secondaryPermutations)
                   for (int i=0; i<=s.length(); i++){ 
                        String p = s.substring(0, i) + ch + s.substring(i); 
                        permutations.add(p); 
                   } 
  
              return permutations; 
       } 
}
