import java.io.*;
import java.util.*;

public class LongestPalindromicSubsequence {

	public static int LPS(String a, int start, int end) {

		if(start > end)
			return 0;
		if(start == end)
			return 1;

		if(a.charAt(start) == a.charAt(end))
			return 2+LPS(a, start+1, end-1);

		return Math.max(LPS(a, start+1, end), LPS(a, start, end-1));
	}

	public static int LPSDP(String str, int n) {

		if(str == null)
			return 0;

		int[][] dp = new int[n][n];

		//Single char str is a palindrome of length 1
		for(int i=0;i<n;i++)
			dp[i][i] = 1;
			
		for(int k=2;k<=n;k++) {
			for(int i=0;i<n-k+1;i++) {
				int j = i+k-1;
				if(str.charAt(i) == str.charAt(j) && k == 2) {
					dp[i][j] = 2;
				} else if(str.charAt(i) == str.charAt(j)) {
					dp[i][j] = dp[i+1][j-1]+2;
				} else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
				}
			}
		}
		return dp[0][n-1];
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		
		System.out.println(LPS(a, 0, a.length()-1));

		System.out.println(LPSDP(a, a.length()));
	}
}