import java.io.*;
import java.util.*;

public class Bitonic {

	public static int bitonicDP(int[] arr, int n) {
		
		int[] LIS = new int[n];
		int[] LDS = new int[n];
		Arrays.fill(LIS, 1);
		Arrays.fill(LDS, 1);

		for(int i=1;i<n;i++) {
			for(int j=i-1;j>=0;j--) {
				if(arr[i]>arr[j] && LIS[j]+1>LIS[i])
					LIS[i] = LIS[j]+1;
			}
		}

		for(int i=n-2;i>=0;i--) {
			for(int j=n-1; j>i; j--) {
				if(arr[i]>arr[j] && LDS[j]+1>LDS[i])
					LDS[i] = LDS[j]+1;
			}
		}

		int max = LIS[0]+LDS[0]-1;
		for(int i=1;i<n;i++)
			if(LIS[i]+LDS[i]-1 > max)
				max = LIS[i]+LDS[i]-1;

		return max;

	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String[] tokens = a.split(" ");
		int[] arr = new int[tokens.length];
		for(int i=0;i<tokens.length;i++)
			arr[i] = Integer.parseInt(tokens[i]);

		System.out.println(bitonicDP(arr, arr.length));
	}
}