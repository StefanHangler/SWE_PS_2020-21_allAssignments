package a10se2020ws;

import java.io.File;
import java.io.Reader;
import java.io.Writer;

public interface A10Encoding {
	/**
	 * @param file2read : refers to a text file to be read, with Morse code of letters [a-z] separated by space
	 * @return a reader that enables reading the letters [a-z] as encoded in the file. This must use a Translator implementation!
	 */
	public Reader getMorseReader(File file2read);
	
	/**
	 * Similar with above, but the source with Morse code is the provided string
	 * @param string2read
	 * @return
	 */
	public Reader getMorseReader(String string2read);
	
	/**
	 * 
	 * @param file2Write : refers to a text file to be written
	 * @return a writer that writes in ROT13 code. This must use a Translator implementation! 
	 */
	public Writer getROT13Writer(File file2Write);
	
	/**
	 * Similar to above, but with output going to a String.
	 * @return a Writer that writes into an internal String, which can be obtained by calling the Writer's toString method.
	 */
	public Writer getROT13Writer();

}
