package Lab2;

import java.util.Scanner;

public class LexicalAnalysis {
		
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		String[] result = new String[n];
		
		for(int i=0; i<n; i++) {
			String input = scan.next();
			
			if(verifiedEmail(input)) {
				result[i] = "Email";
			}
			
			else if(verifiedWeb(input)) {
				result[i] = "Web";
			}
			
			else {
				result[i] = "Invalid Input";
			}
		}
		
		scan.close();
		
		for(int i=0; i<n; i++) {
			System.out.println(result[i] + ", " + (i+1));
		}
	}

	public static boolean verifiedEmail(String email) {
		
		String[] splits = email.split("@"); // break into prefix and domain
		
		if(splits.length != 2) { // can only be a prefix and domain portion; must be only one @
			return false;
		}
		
		if(splits[0].length() == 0 || splits[1].length() < 3) { // prefix cannot be empty and domain length should be at least 3 (For ex: a.b)
			return false;
		}
		
		if(!splits[1].contains(".")) { // there has to be a dot in the domain portion
			return false;
		}
		
		if(!Character.isLetter(email.charAt(0))) { //email cannot start with anything other than letter
			return false;
		}
		
		for(int ch = 0; ch < email.length(); ch++) { // only valid characters in email are letters, numbers, underscore, dash, dot and @
			if(!Character.isLetter(email.charAt(ch)) && !Character.isDigit(email.charAt(ch)) && email.charAt(ch) != '_' && email.charAt(ch) != '-' && email.charAt(ch) != '.' && email.charAt(ch) != '@') {
				return false;
			}
		}
		
		if(email.contains("..") || email.contains("-@") || email.contains("@-") || email.contains("@_") || email.contains("_@") || email.contains("@.") || email.contains(".@") || email.contains("-.") || email.contains(".-") || email.contains("_.") || email.contains("._") || email.contains("--") || email.contains("__")) {
			return false; // dot, dash, underscore and @ must be followed by one or more letters or numbers
		}
		
		if(!Character.isLetter(email.charAt(email.length()-1))) { // must end with a letter
			return false; 
		}
		
		return true;  // true is returned if none of the conditions match
	}
	
	
	
	public static boolean verifiedWeb(String web) { //to verify if input is web address (** not url **)
		
		String[] splits = web.split("\\."); // break web into www, web name and domain
				
		if(splits.length < 3) { //there must be at least 3 parts
			return false;
		}
				
		if(!splits[0].equals("www")) { //must start with www
			return false;
		}
		
		for(int ch = 0; ch < web.length(); ch++) {
			if(!Character.isLetter(web.charAt(ch)) && !Character.isDigit(web.charAt(ch)) && web.charAt(ch) != '-' && web.charAt(ch) != '.') { //web address can contain only letters, numbers, dashes and dots
				return false;
			}
		}
		
		
		for(int i=0; i<splits.length; i++) { // each part of the web address must contain at least one letter
			
			boolean letter = false;
			
			for(int j=0; j<splits[i].length(); j++) {
				if(Character.isLetter(splits[i].charAt(j))) {
					letter = true; //break from inner loop if a letter is found
					break;
				}
			}
			
			if(!letter) {
				return false; // return false if any one part of the address does not contain any letter
			}
		}
		
		if(web.contains("..") || web.contains("-.") || web.contains(".-") || web.contains("--")) {
			return false; // dot and dash must be followed by one or more letters or numbers
		}
		
		if(!Character.isLetter(web.charAt(web.length()-1))) { // must end with a letter
			return false; 
		}
		
		return true; // true is returned if none of the conditions match
		
	}

}
