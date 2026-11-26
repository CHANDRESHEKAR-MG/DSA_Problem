// This question combines two concepts:
// 1. Counting vowels after converting numbers into words.
// 2. Finding pairs in an array whose sum equals a calculated target.
// The original problem is available in the CodeChef CodeVita archive. GitHub
// Step 1: Understand the input
// 2
// 5
// 1 2 3 4 5
// 3
// 7 4 2

// Here:
// - 2 = Number of test cases.
// - 5 = Number of elements in the first test case.
// - 1 2 3 4 5 = Array elements.
// - 3 = Number of elements in the second test case.
// - 7 4 2 = Second array.
// Let's solve the first test case.
// Step 2: Convert numbers into words
// Convert every number into its English representation.
// Number	Word	Vowels	Count
// 1	one	o, e	2
// 2	two	o	1
// 3	three	e, e	2
// 4	four	o, u	2
// 5	five	i, e	2


// Now add all the vowel counts:
// \[
// D = 2+1+2+2+2
// \]
// \[
// \boxed{D=9}
// \]
// Important: D is the target sum we need to find in the original array.
// Step 3: Find pairs whose sum equals D
// Our original array is:
// arr = [1, 2, 3, 4, 5]
// D = 9

// We need to find pairs whose sum equals 9.

// Let's check:
// First number	Second number	Sum	Valid?
// 1	2	3	No
// 1	3	4	No
// 1	4	5	No
// 1	5	6	No
// 2	3	5	No
// 2	4	6	No
// 2	5	7	No
// 3	4	7	No
// 3	5	8	No
// 4	5	9	Yes


// Only one pair satisfies the condition:
// (4, 5)

// Therefore:
// Pair count = 1

// Step 4: Convert the answer into words
// The question asks us to print the answer in lowercase English words.
// 1 → one

// Therefore, the first test case output is:
// one

import java.util.*;

public class VowelCountConvert_int_to_string {

    // Convert a number (1 to 100) into English words
    static String numberToWords(int num) {

        String[] ones = {
            "", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen",
            "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen"
        };

        String[] tens = {
            "", "", "twenty", "thirty", "forty",
            "fifty", "sixty", "seventy", "eighty", "ninety"
        };

        if (num == 100) {
            return "hundred";
        }

        if (num < 20) {
            return ones[num];
        }

        int t = num / 10;//50/10=5
        int r = num % 10;//50%10=0

        return tens[t] + " " + ones[r];//fifty + " " + "" = fifty
    }

    // Count vowels in a string
    static int countVowels(String word) {

        int count = 0;

        for (int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);

            if (ch == 'a' || ch == 'e' || ch == 'i'
                    || ch == 'o' || ch == 'u') {
                count++;
            }
        }

        return count;
    }

    // Convert a number (0 to 999999) into English words
    static String countToWords(int num) {

        String[] ones = {
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen",
            "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen"
        };

        String[] tens = {
            "", "", "twenty", "thirty", "forty",
            "fifty", "sixty", "seventy", "eighty", "ninety"
        };

        if (num < 20) {
            return ones[num];
        }

        if (num < 100) {
            return tens[num / 10]
                    + (num % 10 == 0 ? "" : " " + ones[num % 10]);//if num%10==0 then return tens[num/10] else return tens[num/10] + " " + ones[num%10]
                    
                    //here we are using recursion to convert the number into words. 
                    // For example, if the number is 42, we will first convert 40 into words (forty) and then convert 2 into words (two) and
                    //  then combine them to get forty two.
        }

        if (num < 1000) {
            return ones[num / 100] + " hundred"
                    + (num % 100 == 0 ? "" : " " + countToWords(num % 100));//if num%100==0 then return ones[num/100] + " hundred"
                    //  else return ones[num/100] + " hundred" + " " + countToWords(num%100)
                    //here we are using recursion to convert the number into words.

        }

        if (num < 1000000) {
            return countToWords(num / 1000) + " thousand"
                    + (num % 1000 == 0 ? "" : " " + countToWords(num % 1000));
        }

        return "";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();// Number of test cases

        while (T-- > 0) {//while there are test cases

            int N = sc.nextInt();// number of elements in the array

            int[] arr = new int[N];

            int D = 0;// total vowel count      

            // Step 1: Calculate total vowel count
            for (int i = 0; i < N; i++) {

                arr[i] = sc.nextInt();

                String word = numberToWords(arr[i]);

                D += countVowels(word);
            }

            // Step 2: Find pairs whose sum equals D
            int pairCount = 0;

            for (int i = 0; i < N; i++) {

                for (int j = i + 1; j < N; j++) {

                    if (arr[i] + arr[j] == D) {
                        pairCount++;
                    }
                }
            }

            // Step 3: Convert pair count into words
            System.out.println(countToWords(pairCount));
        }

        sc.close();
    }
}