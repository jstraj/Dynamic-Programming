import java.io.*;
import java.util.*;

public class SubsetSum98 {

	public static boolean isSubsetSumDP(int[] a, int x) {

		boolean[][] dp = new boolean[a.length][x+1];

		for(int i=0;i<a.length;i++)
			dp[i][0] = true;

		for(int j=1;j<=x;j++)
			dp[0][j] = false;
		dp[0][a[0]] = true;

		for(int i=1;i<a.length;i++) {
			for(int j=1;j<=x;j++) {
				if(j<a[i])
					dp[i][j] = dp[i-1][j];
				else if(dp[i-1][j])
					dp[i][j] = true;
				else
					dp[i][j] = dp[i-1][j-a[i]];
			}
		}
		//printDP(dp);

		if(dp[a.length-1][x]) {
			ArrayList<Integer> list = new ArrayList();
			printSubsetSum(dp, a, a.length-1, x, list);
		}

		return dp[a.length-1][x];
	}

	public static void printDP(boolean[][] dp) {
		for(int i=0;i<dp.length;i++) {
			for(int j=0;j<dp[0].length;j++)
				System.out.print(dp[i][j]+" ");
			System.out.println();
		}
	}

	public static void printSubsetSum(boolean[][] dp, int[] a, int i, int x, ArrayList<Integer> p) {

		// If we reached end and sum is non-zero. We print
        // p[] only if arr[0] is equal to x OR dp[0][x]
        // is true.
		if(i == 0 && x!=0 && dp[0][x]) {
			p.add(a[i]);
			display(p);
			p.clear();
			return;
		}

		//if sum becomes 0
		if(i == 0 && x == 0) {
			display(p);
			p.clear();
			return;
		}

		if(dp[i-1][x]) { //Not considering the current element
			ArrayList<Integer> b = new ArrayList();
			b.addAll(p);
			printSubsetSum(dp, a, i-1, x, b);
		}

		if(x>=a[i] && dp[i-1][x-a[i]]) { //considering the element
			p.add(a[i]);
			printSubsetSum(dp, a, i-1, x-a[i], p);
		}
	}


	public static void display(ArrayList<Integer> p) {
		System.out.println(p);
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();

		String[] tokens = str.split(" ");
		int[] a = new int[tokens.length];
		for(int i=0;i<tokens.length;i++) {
			a[i] = Integer.parseInt(tokens[i]);
		}

		int x = sc.nextInt();

		System.out.println(isSubsetSumDP(a, x));
		
	}
}