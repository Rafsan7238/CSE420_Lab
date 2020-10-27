package Lab1;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class LexicalAnalyser {

	File file;

	String[] keywordsList = { "if", "else", "int", "float", "double", "boolean", "String" };
	String keywords = "";

	String[] operatorsList = { "+", "-", "=", "*", "/", "%", "+=", "-=", "*=", "/=", "%=" };
	String operators = "";

	String[] logicList = { ">", "<", ">=", "<=", "==" };
	String logic = "";

	String[] othersList = { ",", ";", "(", ")", "{", "}", "[", "]" };
	String others = "";

	String numRegex = "-?[0-9]+(.[0-9]+)?(E[0-9]+)?";
	String numbers = "";

	String idRegex = "[a-zA-Z_$][a-zA-Z_$0-9]*";
	String identifiers = "";

	public LexicalAnalyser(File newFile) {
		file = newFile;
	}

	public void analyse() throws Exception {

		Scanner scan = new Scanner(file);

		while (scan.hasNext()) {
			String word = scan.next();

			if (Arrays.asList(keywordsList).contains(word)) {
				addKeyword(word);
			}

			else if (Arrays.asList(operatorsList).contains(word)) {
				addOperator(word);
			}

			else if (Arrays.asList(logicList).contains(word)) {
				addLogic(word);
			}

			else if (Arrays.asList(othersList).contains(word)) {
				addOthers(word);
			}

			else if (word.matches(numRegex)) {
				addNumbers(word);
			}

			else if (word.matches(idRegex)) {
				addIdentifiers(word);
			}
			
			else {
				if(word.charAt(word.length()-1) == ',' || word.charAt(word.length()-1) == ';') {
					addOthers(word.substring(word.length()-1));
					
					String substring = word.substring(0, word.length()-1);
					
					if(substring.matches(idRegex)) {
						addIdentifiers(substring);
					}
					
					else if (substring.matches(numRegex)) {
						addNumbers(substring);
					}
				}
			}

		}

		scan.close();

	}

	public void addKeyword(String key) {

		if (!keywords.contains(key)) {
			if (keywords == "") {
				keywords = key;
			}

			else {
				keywords = keywords + ", " + key;
			}
		}
	}

	public void addOperator(String op) {

		if (!operators.contains(op)) {
			if (operators == "") {
				operators = op;
			}

			else {
				operators = operators + ", " + op;
			}
		}
	}

	public void addLogic(String log) {

		if (!logic.contains(log)) {
			if (logic == "") {
				logic = log;
			}

			else {
				logic = logic + ", " + log;
			}
		}
	}

	public void addOthers(String oth) {

		if (!others.contains(oth)) {
			if (others == "") {
				others = oth;
			}

			else {
				others = others + " " + oth;
			}
		}
	}

	public void addNumbers(String num) {

		if (!numbers.contains(num)) {
			if (numbers == "") {
				numbers = num;
			}

			else {
				numbers = numbers + ", " + num;
			}
		}
	}

	public void addIdentifiers(String id) {

		if (!identifiers.contains(id)) {
			if (identifiers == "") {
				identifiers = id;
			}

			else {
				identifiers = identifiers + ", " + id;
			}
		}
	}

	public void printList() {
		System.out.println("Keywords: " + keywords);
		System.out.println("\nIdentifiers: " + identifiers);
		System.out.println("\nMath Operators: " + operators);
		System.out.println("\nLogical Operators: " + logic);
		System.out.println("\nNumerical Values: " + numbers);
		System.out.println("\nOthers: " + others);
	}

}
