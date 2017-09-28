import java.io.*;
import java.util.*;

public class NumPaths {

	public static int numPathsRecursion(int m, int n) {
		if(m == 0 && n == 0)
			return 0;
		if(m == 0 || n == 0)
			return 1;
		return numPathsRecursion(m-1, n)+numPathsRecursion(m, n-1);
	}

	public static int numPathsDP(int m, int n) {
		//The reason for taking m+1 and n+1 is because we are skipping 0th row and col
		int[][] dp = new int[m+1][n+1];
		dp[0][0] = 0;

		for(int i=1;i<=m;i++)
			dp[i][0] = 1;
		for(int j=1;j<=n;j++)
			dp[0][j] = 1;

		for(int i=1;i<=m;i++)
			for(int j=1;j<=n;j++)
				dp[i][j] = dp[i-1][j]+dp[i][j-1];

		return dp[m][n];
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		System.out.println(numPathsRecursion(m,n));
		System.out.println(numPathsDP(m,n));
	}
}