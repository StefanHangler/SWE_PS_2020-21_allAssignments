package a9se2020ws;

public class AuthenticationException extends Exception {
	public AuthenticationException(String source) {
		super("authentication failure: " + source);
	}
}