import java.io.*;
import java.util.*;

public class MinimumCoinChange {

	public static int minCoinsRecursive(int[] coins, int S) {
		
		if(S == 0)
			return 0;

		int res = Integer.MAX_VALUE;
		for(int c: coins) {
			if(c<=S) {
				int temp = minCoinsRecursive(coins, S-c);
				if(temp+1<res)
					res = temp+1;
			}
		}
		return res;
	}

	public static int minCoinsDP(int[] coins, int S) {

		int[] dp = new int[S+1];
		dp[0] = 0;
		for(int i=1;i<=S;i++)
			dp[i] = Integer.MAX_VALUE;

		for(int i=1;i<=S;i++) {
			for(int j=0;j<coins.length;j++) {
				if(coins[j]<=i) {
					int temp = dp[i-coins[j]];
					if(temp!=Integer.MAX_VALUE && temp+1<dp[i])
						dp[i] = temp+1;
				}
			}
		}
		return dp[S];
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String[] tokens = a.split(" ");
		int[] coins = new int[tokens.length];
		for(int i=0;i<tokens.length;i++)
			coins[i] = Integer.parseInt(tokens[i]);

		int S = sc.nextInt();

		//System.out.println(minCoinsRecursive(coins, S));
		System.out.println(minCoinsDP(coins, S));
	}
}