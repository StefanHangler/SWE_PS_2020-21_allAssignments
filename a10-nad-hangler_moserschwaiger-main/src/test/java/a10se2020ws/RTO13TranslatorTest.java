package a10se2020ws;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RTO13TranslatorTest {

	ROT13Translator rot13t;

	@BeforeEach
	void setup(){ rot13t = ROT13Translator.getInstance();
	}

	@Test
	void testSingletonPattern(){
		ROT13Translator rot13t2 = ROT13Translator.getInstance();
		assertEquals(rot13t,rot13t2,"Singleton-Pattern implemented not right");
	}

	@Test
	void testTranslate() {
		String input = "Why 1me?";
		char[] testInput = input.toCharArray();
		StringBuilder translatedInput = new StringBuilder();
		String output = "Jul 1zr?";
		char[] expOutput = output.toCharArray();


		for(int i=0; i< testInput.length; i++){
			char c = rot13t.translate(testInput,i, i+1);
			assertEquals(expOutput[i],c);
			translatedInput.append(c);
		}
		assertEquals(output,translatedInput.toString());
	}

}
