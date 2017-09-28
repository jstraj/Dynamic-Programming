import java.io.*;
import java.util.*;

public class NumPaths92 {

	public static int numPathsDP(int m, int n, int[][] obstacles) {

		int[][] dp = new int[m][n];
		for(int[] d: dp)
			Arrays.fill(d, 0);

		if(obstacles[0][0] == 0)
			dp[0][0] = 1;

		//first column
		for(int i=1;i<m;i++)
			if(obstacles[i][0] == 0)
				dp[i][0] = dp[i-1][0];
		//first row
		for(int j=1;j<n;j++)
			if(obstacles[0][j] == 0)
				dp[0][j] = dp[0][j-1];

		for(int i=1;i<m;i++) {
			for(int j=1;j<n;j++) {
				if(obstacles[i][j] == 0)
					dp[i][j] = dp[i-1][j]+dp[i][j-1];
			}
		}
		//printDP(dp);
		return dp[m-1][n-1];
	}

	public static void printDP(int[][] dp) {
		for(int i=0;i<dp.length;i++) {
			for(int j=0;j<dp[0].length;j++)
				System.out.print(dp[i][j]+" ");
			System.out.println();
		}
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		//Obstacles input
		int[][] obstacles = new int[x][y];
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++)
				obstacles[i][j] = sc.nextInt();
		}

		//System.out.println(numPathsRecursion(0, 0, x, y));
		System.out.println(numPathsDP(x, y, obstacles));
	}
}