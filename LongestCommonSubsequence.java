import java.io.*;
import java.util.*;

public class LongestCommonSubsequence {

	static int[][] memo;

	public static int LCS(String a, String b, int i, int j) {
		//System.out.println("("+i+","+j+")");
		if(i == 0 || j == 0)
			return 0;

		if(a.charAt(i-1) == b.charAt(j-1))
			return 1+LCS(a,b,i-1,j-1);

		return Math.max(LCS(a,b,i-1,j), LCS(a,b,i,j-1));
	}

	public static int LCSMemo(String a, String b, int i, int j) {

		if(i == 0 || j == 0)
			return 0;

		if(memo[i][j] != -1)
			return memo[i][j];

		if(a.charAt(i-1) == b.charAt(j-1))
			memo[i][j] = 1+LCS(a,b,i-1,j-1);
		else
			memo[i][j] = Math.max(LCS(a,b,i-1,j), LCS(a,b,i,j-1));

		return memo[a.length()][b.length()];
	}

	public static int LCSDP(String a, String b) {

		int[][] dp = new int[a.length()+1][b.length()+1];
		for(int i=0;i<dp.length;i++)
			dp[i][0] = 0;
		for(int j=0;j<dp[0].length;j++)
			dp[0][j] = 0;

		for(int i=1;i<=a.length();i++) {
			for(int j=1;j<=b.length();j++) {
				if(a.charAt(i-1) == b.charAt(j-1))
					dp[i][j] = 1 + dp[i-1][j-1];
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		printLCS(a, b, dp, a.length(), b.length());
		return dp[a.length()][b.length()];
	}


	public static void printLCS(String a, String b, int[][] dp, int m, int n) {

		StringBuilder lcs = new StringBuilder();
		int i=m, j=n;
		while(i>0 && j>0) {
			if(a.charAt(i-1) == b.charAt(j-1)) {
				lcs.append(a.charAt(i-1));
				i--;
				j--;
			} else if(dp[i-1][j] > dp[i][j-1]) {
				i--;
			}
			else {
				j--;
			}
		}
		System.out.println(lcs.reverse());
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();

		memo = new int[a.length()+1][b.length()+1];
		for(int[] m: memo)
			Arrays.fill(m, -1);

		System.out.println(LCS(a, b, a.length(), b.length()));
		System.out.println(LCSMemo(a, b, a.length(), b.length()));
		System.out.println(LCSDP(a, b));
	}
}