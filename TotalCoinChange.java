import java.io.*;
import java.util.*;

public class TotalCoinChange {


	public static int totalCoinsRecursivePerm(int[] coins, int S) {
		
		if(S == 0)
			return 1;
		if(S < 0)
			return 0;

		int res = 0;
		for(int c: coins) {
			if(c<=S) {
				int temp = totalCoinsRecursivePerm(coins, S-c);
				res+=temp;
			}
		}
		return res;
	}

	public static int totalCoinsRecursive(int[] coins, int index, int S) {
		
		if(S == 0)
			return 1;
		if(S < 0)
			return 0;

		if(index <= 0 && S >= 1)
			return 0;
		//we include the coin and exclude the coin as well
		return totalCoinsRecursive(coins, index-1, S)+totalCoinsRecursive(coins, index, S-coins[index-1]);
	}

	public static int totalCoinsDP(int[] coins, int S) {

		int[] dp = new int[S+1];
		Arrays.fill(dp, 0);
		dp[0] = 1;

		for(int i=0;i<coins.length;i++) {
			for(int j=coins[i];j<=S;j++) {
				dp[j]+=dp[j-coins[i]];
			}
		}
		printDP(dp);
		return dp[S];
	}

	public static void printDP(int[] dp) {
		for(int d: dp)
			System.out.print(d+" ");
		System.out.println();
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String[] tokens = a.split(" ");
		int[] coins = new int[tokens.length];
		for(int i=0;i<tokens.length;i++)
			coins[i] = Integer.parseInt(tokens[i]);

		int S = sc.nextInt();

		System.out.println("Total permutations(Recursion) : "+totalCoinsRecursivePerm(coins, S));
		System.out.println("Total combinations(Recursion) : "+totalCoinsRecursive(coins, coins.length, S));
		System.out.println("Total permutaions(DP) : "+totalCoinsDP(coins, S));
	}
}