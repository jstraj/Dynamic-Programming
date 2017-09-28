import java.io.*;
import java.util.*;

public class StringInterleaving {

	public static boolean isInterleavingRecursive(String a, String b, String c, int i, int j, int k) {
		
		//System.out.println("("+i+","+j+","+k+")");

		if(a.length() == i && b.length() == j && c.length() == k)
			return true;

		if(c.length() == k)
			return false;

		if(a.length() == i && b.length() == j)
			return false;

		boolean first = false;
		boolean second = false;

		if(i<a.length() && a.charAt(i) == c.charAt(k))
			first = isInterleavingRecursive(a, b, c, i+1, j, k+1);
		if(j<b.length() && b.charAt(j) == c.charAt(k))
			second = isInterleavingRecursive(a, b, c, i, j+1, k+1);

		
		return first | second;
	}

	public static boolean isInterleavingDP(String a, String b, String c) {

		int m = a.length();
		int n = b.length();
		
		if(c.length() != (m+n))
			return false;

		boolean[][] dp = new boolean[m+1][n+1];

		dp[0][0] = true;

		for(int i=1;i<=m;i++) {
			if(a.charAt(i-1) != c.charAt(i-1))
				dp[i][0] = false;
			else
				dp[i][0] = dp[i-1][0];
		}

		for(int j=1;j<=n;j++) {
			if(b.charAt(j-1) != c.charAt(j-1))
				dp[0][j] = false;
			else
				dp[0][j] = dp[0][j-1];
		}

		for(int i=1;i<=m;i++) {
			for(int j=1;j<=n;j++) {
				if(a.charAt(i-1) == c.charAt(i+j-1) && b.charAt(j-1) != c.charAt(i+j-1))
					dp[i][j] = dp[i-1][j];
				else if(a.charAt(i-1) != c.charAt(i+j-1) && b.charAt(j-1) == c.charAt(i+j-1))
					dp[i][j] = dp[i][j-1];
				else if(a.charAt(i-1) == c.charAt(i+j-1) && b.charAt(j-1) == c.charAt(i+j-1))
					dp[i][j] = dp[i-1][j] | dp[i][j-1];
				else
					dp[i][j] = false;
			}
		}
		//printDP(dp);
		return dp[m][n];
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
		String a = sc.next();
		String b = sc.next();
		String c = sc.next();

		System.out.println(isInterleavingRecursive(a, b, c, 0, 0, 0));
		System.out.println(isInterleavingDP(a, b, c));
		
	}
}