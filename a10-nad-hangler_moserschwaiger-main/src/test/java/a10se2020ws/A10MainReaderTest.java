package a10se2020ws;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;

class A10MainReaderTest {
	
	A10Main am;

	@BeforeEach
	void setup(){
		am = new A10Main();
	}
	
	@Test
	void testGetMorseReaderFile() throws IOException {
		Reader reader;
		String input = ".... . .-.. .-.. ---|-.. .- -..";
		String expectedString = "hello dad";
		char[] expectedArray = expectedString.toCharArray();

		var file = new File("test.txt");
		var b = file.createNewFile();

		FileWriter w = new FileWriter("test.txt");
		w.write(input);
		w.close();

		reader = am.getMorseReader(file);
		assertNotNull(reader, "Morse reader from file is null");

		char[] cbuf = new char[9];
		var count = reader.read(cbuf,0, cbuf.length);

		for(int i=0; i<cbuf.length; i++)
			assertEquals(expectedArray[i],cbuf[i]);
	}

	@Test
	void testGetMorseReaderString() throws IOException {
		String expectedString = "hello dad";
		char[] expectedArray = expectedString.toCharArray();
		Reader reader = am.getMorseReader(".... . .-.. .-.. ---|-.. .- -..");
		assertNotNull(reader, "Morse reader from string is null");

		char[] cbuf = new char[9];
		var count = reader.read(cbuf,0, cbuf.length);

		for(int i=0; i<cbuf.length; i++)
			assertEquals(expectedArray[i],cbuf[i]);
	}
	
	@Test
	void testMorse_1(){
		var input = ".- -.. .";
		var expected = "ade";
		var strMR = am.getMorseReader(input);
		assertNotNull(strMR, "Morse reader is null");
		try {
			for( char expC : expected.toCharArray() ){
				int c = strMR.read();
				if ( c!= -1 ) {
					assertEquals(expC, (char)c);
				} else {
					fail("Morse reader unexpected stream end.");
				}
			}
			int c = strMR.read();
			assertEquals(c,-1);
		} catch (IOException e) {
			fail("Morse reader threw Exception " + e + " with cause: " + e.getCause());
		}
	}
	
}
