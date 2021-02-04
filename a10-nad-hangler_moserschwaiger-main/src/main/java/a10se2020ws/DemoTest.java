package a10se2020ws;

import java.io.*;
import java.nio.CharBuffer;


public class DemoTest {
    public static void main(String[] args) throws IOException {
        String morseInput = ".... . .-.. .-.. ---|-.. .- -..";
        String ROT13Input = "Why me?";

        A10Main am = new A10Main();
        /**
         * MorseReader Test
         */
        System.out.println("------ Morse Reader Test --------");

        Reader r = am.getMorseReader(morseInput);
        Reader reader = am.getMorseReader(new File("morseTest.txt"));
        char[] cbuf = new char[9];
        //System.out.println("Chars read: " + r.read(cbuf,0, cbuf.length));
        System.out.println("Chars read: " + reader.read(cbuf,0, cbuf.length));
        for(int i=0; i<cbuf.length; i++)
            System.out.print(cbuf[i]);


        /**
         * ROT13Writer Test
         */

        System.out.println("\n\n------ ROT13 Writer Test --------");
        Writer fw = am.getROT13Writer(new File("ROT13file.txt"));
        Writer w = am.getROT13Writer();
        w.write(ROT13Input);
        fw.write(ROT13Input);
        System.out.println(w.toString());

    }
}
