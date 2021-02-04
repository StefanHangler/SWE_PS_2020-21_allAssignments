package a10se2020ws;

import java.io.*;

public class MorseTranslator implements Translator {

	private static MorseTranslator uniqueInstance = null;

	private final char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
			'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
			',', '.', '?'};

	//morse codes sorted like the char-array 'letters'
	private final String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
			".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
			"...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
			"..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
			"-----", "--..--", ".-.-.-", "..--.."};

	private MorseTranslator (){ }

	/**
	 * methode for the singleton-pattern -> only one instance can be created
	 * @return instance of this class
	 */
	public static MorseTranslator getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new MorseTranslator();

		return uniqueInstance;
	}

	/**
	 * Getter for morse reader from file
	 * @param file file from which is being read
	 * @return used reader
	 * @throws IOException
	 */
	public Reader getMorseReader(File file) throws IOException {
		return new MorseDecorator(new FileReader(file));
	}

	/**
	 * Getter for morse reader from plain text
	 * @param text string from which is being read
	 * @return used reader
	 */
	public Reader getMorseReader(String text) {
		return new MorseDecorator(new StringReader(text));
	}

	@Override
	public Character translate(char[] cKey, int start, int end) {
		StringBuilder word = new StringBuilder(); //stores given morse code
		for(char c : cKey)
			word.append(c);

		for(int i=0; i< morse.length; i++){
			if(morse[i].equals(word.toString()))
				return letters[i];
		}

		throw new RuntimeException("Error: given morse code not found: " + word);
	}
}
