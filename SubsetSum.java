import java.io.*;
import java.util.*;

public class SubsetSum {

	public static boolean isSubsetSumRecursion(int[] a, int x, int index) {
		
		if(x == 0)
			return true;
		if(index >= a.length) //Arrays Exhausted
			return false;

		if(a[index] > x)
			return isSubsetSumRecursion(a, x, index+1);

		return isSubsetSumRecursion(a, x, index+1) | isSubsetSumRecursion(a, x-a[index], index+1);
	}

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
		return dp[a.length-1][x];
	}

	public static void printDP(boolean[][] dp) {
		for(int i=0;i<dp.length;i++) {
			for(int j=0;j<dp[0].length;j++)
				System.out.print(dp[i][j]+" ");
			System.out.println();
		}
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

		System.out.println(isSubsetSumRecursion(a, x, 0));
		System.out.println(isSubsetSumDP(a, x));
		
	}
}