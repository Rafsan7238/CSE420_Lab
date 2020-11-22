package Lab3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex_Analyser {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int regexSize = scan.nextInt();
		Pattern[] patterns = new Pattern[regexSize];
		
		for(int i=0; i<regexSize; i++) {
			patterns[i] = Pattern.compile(scan.next()); //compile the input string into regex pattern and insert in matrix
		}
		
		int sampleSize = scan.nextInt();
		String[] samples = new String[sampleSize];
		
		for(int i=0; i<sampleSize; i++) {
			samples[i] = scan.next();
		}
		
		matchRegex(patterns, samples);
		scan.close();

	}

	private static void matchRegex(Pattern[] patterns, String[] samples) {
		
		for(int i=0; i<samples.length; i++) { //check each of the sample with all the pattern unless match found
			
			boolean matched = false; 
			
			for(int j=0; j<patterns.length; j++) {
				
				Matcher matcher = patterns[j].matcher(samples[i]); //match the input with the regex patern
				
				if(matcher.find()) {
					System.out.println("YES, " + (j+1)); //if match found, print the pattern number it matched with
					matched = true;
					break;
				}
			}
			
			if(!matched) {
				System.out.println("NO, 0"); //if no match found, print 0
			}
		}
	}

}
