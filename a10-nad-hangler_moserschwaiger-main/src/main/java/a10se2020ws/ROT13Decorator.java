package a10se2020ws;

import java.io.IOException;
import java.io.Writer;

/**
 * Decorator class for ROT13 writer
 */
public class ROT13Decorator extends Writer {

    private final StringBuilder buffer;
    protected Writer writer;

    /**
     * constructs a {@link ROT13Decorator} object
     * @param writer given decorator writer
     */
    public ROT13Decorator(Writer writer) {
        this.writer = writer;
        this.buffer = new StringBuilder();
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        this.writer.write(cbuf,off,len);
    }

    @Override
    public void write(String str) throws IOException {
        ROT13Translator rt = ROT13Translator.getInstance();
        char[] word = str.toCharArray();

        for(int i=0; i<str.length(); i++)
            this.buffer.append(rt.translate(word,i,i+1));


        this.writer.write(this.buffer.toString());
        this.writer.close();
    }

    @Override
    public void flush() throws IOException {
        this.writer.flush();
    }

    @Override
    public void close() throws IOException {
        this.writer.close();
    }

    /**
     * Write the translated string into the command line
     * @return translated string
     */
    @Override
    public String toString() {
        return this.buffer.toString();
    }
}
