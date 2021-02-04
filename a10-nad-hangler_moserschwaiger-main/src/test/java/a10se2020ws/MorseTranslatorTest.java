package a10se2020ws;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MorseTranslatorTest {

	MorseTranslator mt;

	@BeforeEach
	void setup(){ mt = MorseTranslator.getInstance();
	}

	@Test
	void testSingletonPattern(){
		MorseTranslator mt2 = MorseTranslator.getInstance();
		assertEquals(mt,mt2,"Singleton-Pattern implemented not right");
	}

	@Test
	void testTranslate() {
		String[] testInput = new String[]{"....", ".", ".-..", "---", ".-", "-.."};
		char[] expOutput = new char[]{'h','e','l','o','a','d'};
		char[] morseCode;
		for(int i=0; i< testInput.length; i++){
			morseCode = testInput[i].toCharArray();
			char c = mt.translate(morseCode,0, morseCode.length);
			assertEquals(expOutput[i],c);
		}

		char[] wrongInput = {'.','.','.','.','.','.'};
		assertThrows(RuntimeException.class, ()-> mt.translate(wrongInput,0, wrongInput.length));
	}

}
