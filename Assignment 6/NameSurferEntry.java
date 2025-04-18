/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears in the data
	 * file. Each line begins with the name, which is followed by integers
	 * giving the rank of that name for each decade.
	 */
	private String name;

	private ArrayList<Integer> ranking = new ArrayList<Integer>();

	
	//initializing the constructor
	public NameSurferEntry(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line," ");
		name = tokenizer.nextToken();
		while (tokenizer.hasMoreTokens()) {
			int tempRank = Integer.parseInt(tokenizer.nextToken());
			ranking.add(tempRank);
		}
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular decade. The
	 * decade value is an integer indicating how many decades have passed since
	 * the first year in the database, which is given by the constant
	 * START_DECADE. If a name does not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		return ranking.get(decade);
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		String temp = name +"[";
		System.out.println(ranking.size());
		for (int i = 0; i < ranking.size(); i++) {
			temp = temp + " " + ranking.get(i);
		}
		return temp +"]";
	}
}
