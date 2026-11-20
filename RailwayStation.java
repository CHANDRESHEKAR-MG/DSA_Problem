import java.util.*;

public class RailwayStation {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arrival = new int[n];
        int[] departure = new int[n];

        // Read arrival and departure times
        for (int i = 0; i < n; i++) {

            arrival[i] = sc.nextInt();
            int stoppage = sc.nextInt();

            departure[i] = arrival[i] + stoppage;
        }

        // Sort both arrays
        Arrays.sort(arrival);
        Arrays.sort(departure);

        int i = 0;
        int j = 0;

        int platforms = 0;
        int maxPlatforms = 0;
//         4  arrival  10 12 14 25
//         stoppage= 10 8 5 3

//departure = 20 20 19 28
//sort 
//arrival = 10 12 14 25
//departure = 20 20 19 28
// 10 10
// 12 8
// 14 5
// 25 3
        while (i < n && j < n) {

            if (arrival[i] <= departure[j]) {

                platforms++;
                //maxPlatforms++;

                i++;

                maxPlatforms = Math.max(maxPlatforms, platforms);

            } else {

                platforms--;
                j++;
            }
        }

        System.out.println(maxPlatforms);

        sc.close();
    }
}