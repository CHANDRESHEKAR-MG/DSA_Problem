// Good attempt! Let's dry-run the input together.

// Input:
// (@HR*i{kcQl
// Vyom

// Good String characters:
// (  @  H  R  *  i  {  k  c  Q  l

// We need to process V, y, o, m one by one.
// 1. Character = V
// ASCII value of V = 86.

// Closest characters:
// V → R = |86 - 82| = 4
// V → Q = |86 - 81| = 5

// Selected = R

// Distance = 4
// 2. Character = y
// ASCII value of y = 121.
// y → { = |121 - 123| = 2

// Selected = {

// Distance = 2
// 3. Character = o
// ASCII value of o = 111.
// o → l = |111 - 108| = 3

// Selected = l

// Distance = 3
// 4. Character = m
// ASCII value of m = 109.
// m → l = |109 - 108| = 1

// Selected = l

// Distance = 1
// Final calculation
// Character	Selected	Distance
// V	R	4
// y	{	2
// o	l	3
// m	l	1
// Total		10


import java.util.Scanner;

public class GoodString {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String goodString = sc.nextLine();//(@HR*i{kcQl

        String name = sc.nextLine();    //Vyom

        int totalDistance = 0;

        // Initially, previous character is the first character
        // of the Good String.
        char previous = goodString.charAt(0);//@

        for (int i = 0; i < name.length(); i++) {

            char current = name.charAt(i);

            int minDistance = Integer.MAX_VALUE;

            char selected = '\0';// Initialize selected character result to null character

            for (int j = 0; j < goodString.length(); j++) {

                char candidate = goodString.charAt(j);

                // Calculate ASCII distance
                int distance = Math.abs(current - candidate);
                

                if (distance < minDistance) {

                    minDistance = distance;
                    selected = candidate;

                } else if (distance == minDistance) {

                    // Tie-breaking: choose the candidate          
                    // closest to the previously selected character.
                    int oldDistance =
                            Math.abs(previous - selected);

                    int newDistance =
                            Math.abs(previous - candidate);

                    if (newDistance < oldDistance) {
                        selected = candidate;
                    }
                }
            }

            totalDistance += minDistance;

            // Update previous selected character
            previous = selected;
        }

        System.out.println(totalDistance);

        sc.close();
    }
}