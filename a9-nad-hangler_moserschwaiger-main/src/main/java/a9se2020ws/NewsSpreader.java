package a9se2020ws;

/**
 * An interface for spreading news.
 */
public interface NewsSpreader {
	
	/**
	 * Registers a trusted news-source.
	 * 
	 * @param source a string used to identify the source
	 * @param pwd    a password that allows to authenticate the source when
	 *               spreading news
	 * @return false if source is null or already registered or if pwd is null or empty , true otherwise
	 */
	public boolean registerTrustedSource(String source, String pwd);

	/**
	 * 
	 * @param news   a string that contains the news to be spread
	 * @param source the source of the news (which must be already registered)
	 * @param pwd    the password (must match the registered password for this source)
	 * @throws UntrustedSourceException when the source was not registered before
	 * @throws AuthenticationException  when the source was registered with a different password
	 */
	public void spreadNews(String news, String source, String pwd)
			throws UntrustedSourceException, AuthenticationException;

}