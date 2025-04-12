import java.io.*;
import java.util.*;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {

	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the
	 * requested file does not exist or if an error occurs as the file is being
	 * read.
	 */
	private ArrayList<NameSurferEntry> dataBase = new ArrayList<NameSurferEntry>();

	public NameSurferDataBase(String filename) {
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line=br.readLine();
			while (!line.isEmpty()) {
				NameSurferEntry entr = new NameSurferEntry(line);
				dataBase.add(entr);
				line=br.readLine();
			}
		} catch (Exception e) {

		}
	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If
	 * the name does not appear in the database, this method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		String processedName = name.toLowerCase();
		for (NameSurferEntry tempSurfer : dataBase) {
			String tempName = tempSurfer.getName().toLowerCase();
			if (tempName.equals(processedName)) {
				return tempSurfer;
			}
		}
		return null;
	}
}
