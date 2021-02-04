package a10se2020ws;

public interface Translator {
	/**
	 * Translates one or more characters into a single character
	 * @param cs : the array
	 * @param fromIndex : points to the first element of the sequence to be translated (must be nonnegative) 
	 * @param untilIndex : points to the next element after the sequence (must be <= cs.length)
	 * @return the translated character
	 */
	public Character translate(char[] cs, int fromIndex, int untilIndex);
}
