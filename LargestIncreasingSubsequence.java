import java.io.*;
import java.util.*;

public class LargestIncreasingSubsequence {

	public static int LIS(int[] arr, int n, int i, int prev) {
		
		if(i == n)
			return 0;

		int excl = LIS(arr, n, i+1, prev);

		int incl = 0;
		if(arr[i] > prev)
			incl = 1 + LIS(arr, n, i+1, arr[i]);

		return Math.max(incl, excl);
	}

	public static int LISDP(int[] arr, int n) {
		int[] L = new int[n];
		Arrays.fill(L, 0);
		int[] prev = new int[n];
		Arrays.fill(prev, 0);
		L[0] = 1;
		prev[0] = -1; //marks which indices are included

		for(int i=1;i<n;i++) {
			prev[i] = -1;
			for(int j=i-1;j>=0;j--) {
				if(arr[i]>arr[j] && L[j]+1 > L[i]) {
					L[i] = L[j];
					prev[i] = j;
				}
			}
			L[i]++;
		}

		int max = 0;
		int bestEnd = 0;
		for(int i=0;i<n;i++) {
			if(L[i] > max) {
				bestEnd = i;
				max = L[i];
			}
		}

		printDP(L, prev);

		ArrayList<Integer> list = new ArrayList();

		System.out.print("LIS is : ");
		while(prev[bestEnd] != -1) {
			list.add(arr[bestEnd]);
			bestEnd = prev[bestEnd];
		}
		list.add(arr[bestEnd]);
		System.out.println(list);

		return max;
	}

	public static void printDP(int[] L, int[] prev) {
		System.out.print("DP is : ");
		for(int l: L)
			System.out.print(l+" ");
		System.out.println();
		System.out.print("prev is : ");
		for(int l: prev)
			System.out.print(l+" ");
		System.out.println();
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String[] tokens = a.split(" ");
		int[] arr = new int[tokens.length];
		for(int i=0;i<tokens.length;i++)
			arr[i] = Integer.parseInt(tokens[i]);

		System.out.println(LIS(arr, arr.length, 0, Integer.MIN_VALUE));
		System.out.println(LISDP(arr, arr.length));
	}
}