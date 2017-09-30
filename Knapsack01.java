import java.io.*;
import java.util.*;

public class Knapsack01 {


	public static int maxValueRecursive(int[] weight, int[] values, int n, int C) {
		
		if(n<=0 || C<=0)
			return 0;

		if(weight[n-1]>C)
			return maxValueRecursive(weight, values, n-1, C);

		int x = values[n-1]+maxValueRecursive(weight, values, n-1, C-weight[n-1]);
		int y = maxValueRecursive(weight, values, n-1, C);

		return Math.max(x,y);
	}

	public static int maxValueDP(int[] weight, int[] values, int n, int C) {

		int table[][] = new int[n+1][C+1];

		//top rows and first col will hold zero
		for(int i=0;i<=n;i++) 
			table[i][0] = 0;
		for(int j=0;j<=C;j++)
			table[0][j] = 0;

		for(int i=1;i<=n;i++){
			for(int j=1;j<=C;j++) {
				if(weight[i-1]<=j) {
					int x = j-weight[i-1];
					table[i][j] = Math.max(values[i-1]+table[i-1][x], table[i-1][j]);
				} else {
					table[i][j] = table[i-1][j];
				}
			}
		}
		return table[n][C];
	}


	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);

		String b = sc.nextLine();
		String[] tokens1 = b.split(" ");
		int[] weight = new int[tokens1.length];
		for(int i=0;i<tokens1.length;i++)
			weight[i] = Integer.parseInt(tokens1[i]);


		String a = sc.nextLine();
		String[] tokens = a.split(" ");
		int[] values = new int[tokens.length];
		for(int i=0;i<tokens.length;i++)
			values[i] = Integer.parseInt(tokens[i]);

		int C = sc.nextInt();

		System.out.println(maxValueRecursive(weight, values, weight.length, C));
		System.out.println(maxValueDP(weight, values, weight.length, C));
	}
}