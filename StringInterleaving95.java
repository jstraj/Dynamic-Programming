import java.io.*;
import java.util.*;

public class StringInterleaving95 {

	public static void printInterleavingStrings(String a, String b, String interleavingString) {
		
		if(a == null)
			System.out.println(b);

		if(b == null)
			System.out.println(a);

		if(a.length() == 0 && b.length() == 0)
			System.out.println(interleavingString);

		if(a.length() != 0)
			printInterleavingStrings(a.substring(1), b, interleavingString+a.charAt(0));

		if(b.length() != 0)
			printInterleavingStrings(a, b.substring(1), interleavingString+b.charAt(0));
	}

	public static void main(String args[])throws IOException {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();

		printInterleavingStrings(a, b, "");
		
	}
}