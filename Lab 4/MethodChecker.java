package Lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodChecker {

	public static void matchMethod(File file) throws FileNotFoundException {

		String[] accessModifiers = { "public", "private", "protected", "final", "native", "synchronized", "abstract" };

		// method will start using the following pattern
		String methodStart = ("(public |private |protected |final |native |synchronized |abstract )?(static )?(void|int|double|char|byte|float|short|long|boolean|String)");

		Scanner scan = new Scanner(file);
		Pattern pattern = Pattern.compile(methodStart);
		Matcher matcher;

		System.out.println("Methods:\n");

		while (scan.hasNextLine()) {

			String input = scan.nextLine().trim();

			matcher = pattern.matcher(input);

			// methods start with the items in methodStart, does not have an "=" sign, and ends with a ")". main method is excluded.

			if (matcher.lookingAt() && !input.contains("=") && !input.contains(" main ") && input.endsWith(")")) {

				String[] brokenInput = input.split(" ");

				if (Arrays.asList(accessModifiers).contains(brokenInput[0])) { // if method starts with access modifier
					
					if (brokenInput[1].equals("static")) { // if access modifier is followed by static, then the next word is returnType and rest is method name with parameters

						String returnType = brokenInput[2];

						int index = input.indexOf(brokenInput[3]); // find the index of the method name

						System.out.println(input.substring(index) + ", return type: " + returnType); // print as shown
																										// in doc

					}

					else { // if second word is not static, then it is the return type, followed by method name and parameter

						String returnType = brokenInput[1];

						int index = input.indexOf(brokenInput[2]); // find the index of the method name

						System.out.println(input.substring(index) + ", return type: " + returnType); // print as shown in doc

					}

				}
				
				else if (brokenInput[0].equals("static")) { //if first word is not access modifier, then it could be static, followed by return type, method name and parameters
					
					String returnType = brokenInput[1];

					int index = input.indexOf(brokenInput[2]); // find the index of the method name

					System.out.println(input.substring(index) + ", return type: " + returnType); // print as shown in doc
					
				}
				
				else { //if first word is also not static, then it must be return type followed by method name and parameters
					
					String returnType = brokenInput[0];

					int index = input.indexOf(brokenInput[1]); // find the index of the method name

					System.out.println(input.substring(index) + ", return type: " + returnType); // print as shown in doc
					
				}

			}
		}
		
		scan.close();
	}

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("/Users/rafsanalmamun/Desktop/CSE420 Lab Files/Lab 4/input.txt");
		matchMethod(file);

	}

}
