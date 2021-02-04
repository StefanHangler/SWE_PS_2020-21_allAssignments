package a10se2020ws;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class A10Main implements A10Encoding {
	
	@Override
	public Reader getMorseReader(File file2read) {
		MorseTranslator mt = MorseTranslator.getInstance();

		try {
			return mt.getMorseReader(file2read);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reader getMorseReader(String string2read) {
		MorseTranslator mt = MorseTranslator.getInstance();
		return mt.getMorseReader(string2read);
	}

	@Override
	public Writer getROT13Writer(File file2Write) {
		ROT13Translator rt = ROT13Translator.getInstance();

		try {
			return rt.getROT13Writer(file2Write);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Writer getROT13Writer() {
		ROT13Translator rt = ROT13Translator.getInstance();
		return rt.getROT13Writer();
	}
}
