import java.io.*;
import java.util.*;

public class NumPaths91 {


	public static int numPathsRecursion(int x, int y, int m, int n) {
		if(x == m && y == n)
			return 1;
		if(x > m || y > n)
			return 0;
		return numPathsRecursion(x+1, y, m, n)+numPathsRecursion(x, y+1, m, n);
	}

	public static int numPathsDP(int m, int n) {
		//The reason for taking m+1 and n+1 is because we are using 0th index
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
		int x = sc.nextInt();
		int y = sc.nextInt();
		System.out.println(numPathsRecursion(0, 0, x, y));
		System.out.println(numPathsDP(x, y));
	}
}