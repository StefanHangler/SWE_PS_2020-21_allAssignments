package a10se2020ws;

import java.io.IOException;
import java.io.Reader;

/**
 * Decorator class for morse reader
 */
public class MorseDecorator extends Reader {

    protected Reader reader;
    private int lastChar = -1;

    /**
     * constructs a {@link MorseDecorator} object
     * @param reader given decorator reader
     */
    public MorseDecorator(Reader reader) {
        this.reader = reader;
    }

    @Override
    public int read() throws IOException {
        //if next letter is '|' in morse code
        if(lastChar == 124) {
            lastChar = -1;
            return 32; //id of space
        }

        MorseTranslator mt = MorseTranslator.getInstance();
        StringBuilder sb = new StringBuilder();
        int c = this.reader.read();

        // 32 = id of space (int value of the char-type)
        // 124 = id of '|'
        // -1 if end of string
        while(c != 32 && c != 124 && c != -1){
            sb.append((char) c);
            c = this.reader.read();
        }

        //if next char is '|' -> translated '' space
        if(c == 124)
            lastChar = c;

        char[] word = sb.toString().toCharArray();

        if(word.length > 0)
            return mt.translate(word,0,word.length);
        else {
            close();
            return -1;
        }
    }

    /**
     * stores whole sentence/word and stores in @param cbuf
     */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int count = 0;
        int c = -1;

        if(len > 0)
            c = read();

        for(int i=off; i<(off+len); i++) {
            count++;
            cbuf[i] = (char) c;
            c = read();
        }

        close();
        return c == -1 ? c : ++count;
    }

    @Override
    public boolean ready() throws IOException {
        return this.reader.ready();
    }

    @Override
    public void close() throws IOException {
         this.reader.close();
    }
}
