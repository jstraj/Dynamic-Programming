import java.io.*;
import java.util.*;

public class SubsetSum97 {

	public static set returnSet(int[] a, int x) {
		
		Arrays.sort(a);
		for(int i=0;i<a.length;i++) {
			if(x > a[i]) {
				int index = Arrays.binarySearch(a, i+1, a.length, x-a[i]);
				if(index>=0)
					return new set(a[i], x-a[i]);
			}
		}
		return null;
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();

		String[] tokens = str.split(" ");
		int[] a = new int[tokens.length];
		for(int i=0;i<tokens.length;i++) {
			a[i] = Integer.parseInt(tokens[i]);
		}

		int x = sc.nextInt();

		set s = returnSet(a, x);
		if(s == null)
			System.out.println("No elements found");
		else
			System.out.println("Found: "+s.x+", "+s.y);
		
	}
}

class set {
	int x;
	int y;
	public set(int x, int y) {
		this.x = x;
		this.y = y;
	}
}