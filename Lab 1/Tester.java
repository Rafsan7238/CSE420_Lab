package Lab1;

import java.io.File;

public class Tester {

	public static void main(String[] args) throws Exception {
		
		File file = new File("/Users/rafsanalmamun/Desktop/CSE420 Lab/Lab 1/input.txt");

		LexicalAnalyser la = new LexicalAnalyser(file);
		la.analyse();
		la.printList();
	}

}
