import java.util.Scanner;

public class ZeroCount {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int L = sc.nextInt();
        int K = sc.nextInt();

        int zeros = L - K;
        int groups = K + 1;

        int answer = (zeros + groups - 1) / groups;

        System.out.println(answer);

        sc.close();
    }
}

// Q1. Zero Count – TCS CodeVita Practice
// Difficulty: Easy–Medium
// Topics: Greedy, Arrays, Mathematics
// Language: Java
// Problem Statement
// You are given two integers, L and K.
// - L represents the total number of positions in a binary sequence.
// - K represents the number of ones (1) that must be placed in the sequence.
// - The remaining positions must contain zeros (0).
// Your task is to arrange the zeros and ones in such a way that the maximum number of consecutive zeros is as small as possible.

// Print the minimum possible length of the longest consecutive sequence of zeros.
// Input Format
// A single line containing two space-separated integers:
// - L – Total number of positions.
// - K – Number of ones.
// Output Format
// Print a single integer representing the minimum possible maximum number of consecutive zeros.
// Constraints
// Assume:
// 1 <= L
// 0 <= K <= L

// Sample Input 1
// 3 1

// Sample Output 1
// 1

// Explanation:

// We have 3 positions and 1 one.

// Possible arrangements:
// 100 → Longest zero sequence = 2
// 010 → Longest zero sequence = 1
// 001 → Longest zero sequence = 2

// The best arrangement is:
// 010

// Therefore, the answer is 1.
// Sample Input 2
// 12 3

// Sample Output 2
// 3

// Explanation:
// - Total positions = 12
// - Number of ones = 3
// - Number of zeros = 12 − 3 = 9
// - Number of zero groups = 3 + 1 = 4
// One optimal arrangement is:
// 000 1 000 1 00 1 0

// The longest consecutive zero sequence contains 3 zeros.

// Therefore, the answer is 3.