// 1. Understand the problem
// You are given two strings:
// - P = New alphabetical order.
// - S = String that you need to rearrange.
// Your task is to rearrange the characters of S according to the order specified by P.
// Example
// P = "polikujmnhytgbvfredcxswqaz";
// S = "abcd";

// Normally, alphabetical order is:
// a b c d

// But here, the new alphabetical order is:
// p o l i k u j m n h y t g b v f r e d c x s w q a z

// Look carefully:
// - b comes before d.
// - d comes before c.
// - c comes before a.
// Therefore, when we rearrange "abcd" according to P, the answer is:
// bdca

// 2. How does it work?
// Let's find the positions of each character in P.
// Character	Position in P
// a	24
// b	13
// c	21
// d	20


// Now arrange the characters of S according to their positions:
// b = 13
// d = 20
// c = 21
// a = 24

// Result:
// bdca

// Remember: Smaller position means higher priority.
// import java.util.*;
// public class SortingBoxes{
//     public static void main(String[] args){
//        String P = "polikujmnhytgbvfredcxswqaz";
//        String S = "aabbcd";
//        Map<Character, Integer> map = new HashMap<>();//map to store the position of each character in P
     
//        for(int i=0;i<S.length();i++){
//         for(int j=0;j<P.length();j++){
//             if(P.charAt(j)==S.charAt(i)){
//                 map.put(S.charAt(i), j);
//             }

//         }
//        }
//        List<Character> chars = new ArrayList<>(map.keySet());//store the characters of S in a list
//        Collections.sort(chars, (a, b) -> map.get(a) - map.get(b));//sort the characters according to their positions in P
//        for(char c : chars){
//            System.out.print(c);
//        }
//     }
// }

import java.util.*;

public class SortingBoxes {
    public static void main(String[] args) {

        String P = "polikujmnhytgbvfredcxswqaz";
        String S = "aabbcd";

        // Convert String into Character array
        Character[] arr = new Character[S.length()];

        for (int i = 0; i < S.length(); i++) {
            arr[i] = S.charAt(i);
        }

        // Custom sorting according to P
        Arrays.sort(arr, (a, b) ->//here we are using lambda expression to sort the array according to the order of P
        //sort array and here a , b are the characters of S and we are comparing their index in P and sorting them according to their index in P
        //a b are the characters of S and we are comparing their index in P and sorting them according to their index in P
        
            P.indexOf(a) - P.indexOf(b)// compare method is used to compare two objects and return an integer value. If the value is negative, it means a is less than b. If the value is positive, it means a is greater than b. If the value is zero, it means a is equal to b.
        );


//         Arrays.sort(arr, new Comparator<Character>() { //here we are using anonymous class to sort the array according to the order of P
//     @Override
//     public int compare(Character a, Character b) {//here compare method is used to compare the characters of S and we are comparing their index in P and sorting them according to their index in P
//compare method is used to compare two objects and return an integer value. If the value is negative, it means a is less than b. If the value is positive, it means a is greater than b. If the value is zero, it means a is equal to b.   

//         return P.indexOf(a) - P.indexOf(b);
//     }
// });


        // Print sorted characters
        for (char c : arr) {
            System.out.print(c);
        }
    }
}