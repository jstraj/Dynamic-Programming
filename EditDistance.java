import java.io.*;
import java.util.*;

public class EditDistance {

	static int[][] dp;
	public static int EditDistanceRecursive(String a, String b, int m, int n) {
		if(m == 0)
			return n;
		if(n == 0)
			return m;
		if(a.charAt(m-1) == b.charAt(n-1))
			return EditDistanceRecursive(a, b, m-1, n-1);
		return 1+Math.min(EditDistanceRecursive(a, b, m-1, n-1),
							Math.min(EditDistanceRecursive(a, b, m-1, n), EditDistanceRecursive(a, b, m, n-1)));
	}

	public static int EditDistanceDP(String a, String b) {
		for(int i=0;i<=a.length();i++) {
			for(int j=0;j<=b.length();j++) {
				if(i == 0)
					dp[i][j] = j;
				else if(j == 0)
					dp[i][j] = i;
				else if(a.charAt(i-1) == b.charAt(j-1))
					dp[i][j] = dp[i-1][j-1];
				else
					dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
			}
		}
		return dp[a.length()][b.length()];
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();
		dp = new int[a.length()+1][b.length()+1];
		System.out.println(EditDistanceRecursive(a, b, a.length(), b.length()));
		System.out.println(EditDistanceDP(a, b));
	}
}