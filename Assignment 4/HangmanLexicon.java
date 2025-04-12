/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import java.util.ArrayList;

import acm.util.*;

public class HangmanLexicon {

	ArrayList<String> lexicon = new ArrayList<String>();

	public HangmanLexicon() {
		try {
			FileReader file = new FileReader("HangmanLexicon.txt");
			BufferedReader lex = new BufferedReader(file);
			String line = lex.readLine();
			while (!line.isEmpty()) {
				lexicon.add(line);
				line = lex.readLine();
			}
			lex.close();
		} catch (Exception e) {

		}

	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		System.out.println(lexicon.size());
		return lexicon.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return lexicon.get(index);
	};
}
