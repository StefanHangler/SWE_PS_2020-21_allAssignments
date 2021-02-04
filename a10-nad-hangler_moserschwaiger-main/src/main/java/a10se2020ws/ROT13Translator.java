package a10se2020ws;

import java.io.*;

public class ROT13Translator implements Translator {

	private static ROT13Translator uniqueInstance = null;

	private ROT13Translator(){}

	/**
	 * methode for the singleton-pattern -> only one instance can be created
	 * @return instance of this class
	 */
	public static ROT13Translator getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new ROT13Translator();

		return uniqueInstance;
	}

	/**
	 * Getter for ROT13 writer into file
	 * @param file file into which is written
	 * @return used writer
	 * @throws IOException
	 */
	public Writer getROT13Writer(File file) throws IOException {
		return new ROT13Decorator(new FileWriter(file));
	}

	/**
	 * Getter for ROT13 writer from string
	 * @return used writer
	 */
	public Writer getROT13Writer(){
		return new ROT13Decorator(new StringWriter());
	}

	@Override
	public Character translate(char[] cKey, int start, int end) {
		char c = cKey[start];
		int key = 13; //ROT13 key

		if (c >= 'a' && c <= 'm' || c >= 'A' && c <= 'M')
			return (char) (c + key);
		if (c >= 'n' && c <= 'z' || c >= 'N' && c <= 'Z')
			return (char) (c - key);

		return c;
	}
}
