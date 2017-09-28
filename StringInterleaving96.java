import java.io.*;
import java.util.*;

public class StringInterleaving96 {

	public static boolean isInterleavingDifferent(String a, String b, String c) {
		
		int i=0,j=0,k=0;
		while(k<c.length()) {
			if(i<a.length() && a.charAt(i) == c.charAt(k))
				i++;
			else if(j<b.length() && b.charAt(j) == c.charAt(k))
				j++;
			else
				return false;
			k++;
		}
		return true;
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();
		String c = sc.next();

		System.out.println(isInterleavingDifferent(a, b, c));
		
	}
}