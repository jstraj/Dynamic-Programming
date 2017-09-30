import java.io.*;
import java.util.*;

public class CutRod {


	public static int maxPriceRecursive(int[] values, int n) {
		
		if(n<=0)
			return 0;

		int maxVal = Integer.MIN_VALUE;
		for(int i=0;i<n;i++) {
			maxVal = Math.max(maxVal, values[i] + maxPriceRecursive(values, n-i-1));
		}
		return maxVal;
	}

	public static int maxPriceDP(int[] values, int n) {

		int dp[] = new int[n+1];
		Arrays.fill(dp, 0);

		for(int i=1;i<=n;i++) {
			for(int j=0;j<i;j++) {
				dp[i] = Math.max(dp[i], values[j]+dp[i-j-1]);
			}
		}
		return dp[n];
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String[] tokens = a.split(" ");
		int[] values = new int[tokens.length];
		for(int i=0;i<tokens.length;i++)
			values[i] = Integer.parseInt(tokens[i]);

		int n = sc.nextInt();

		System.out.println(maxPriceRecursive(values, n));
		System.out.println(maxPriceDP(values, n));
	}
}