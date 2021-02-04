package a9se2020ws;

public class UntrustedSourceException extends Exception {
	public UntrustedSourceException(String source) {
		super("untrusted source: " + source);
	}
}