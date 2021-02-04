package a10se2020ws;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;

class A10MainWriterTest {
	
	A10Main am;

	@BeforeEach
	void setup(){
		am = new A10Main();
	}

	@Test
	void testGetROT13WriterFile() throws IOException {
		String input = "Why 1me?";
		String output = "Jul 1zr?";
		char[] expOutput = output.toCharArray();

		Writer writer = am.getROT13Writer(new File ("rot13test.txt"));
		assertNotNull(writer, "ROT13 writer from file is null");
		writer.write(input);

		FileReader reader = new FileReader("rot13test.txt");
		char[] cbuf = new char[8];
		reader.read(cbuf);

		for(int i=0; i<cbuf.length; i++)
			assertEquals(expOutput[i],cbuf[i]);
	}

	@Test
	void testGetROT13Writer() throws IOException {
		String input = "Why 1me?";
		String output = "Jul 1zr?";

		Writer writer = am.getROT13Writer();
		assertNotNull(writer, "ROT13 writer from file is null");

		writer.write(input);
		assertEquals(writer.toString(),output);
	}
	
	@Test
	void testROT13_1(){
		var input = "beach";
		var expected = "ornpu";
		var strWR = am.getROT13Writer();
		assertNotNull(strWR, "ROT13 writer is null");
		try {
			strWR.write(input);
			String result = strWR.toString();
			assertEquals(expected,result);
		} catch (IOException e) {
				fail("ROT13 writer threw Exception " + e + " with cause: " + e.getCause());
		}
	}
}
