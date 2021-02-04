# A9_WS2020
Observer

Assignment 9: Observer
----------------------------------------------

Carefully read the Observer chapter in [1].

Implement a small news-broadcasting application.
Trusted news sources can spread news to an arbitrary number of registered components.

Use the Observer pattern to decouple the involved components:
- 1 broadcasting component (an implementation of Subject [1])
- n news-sources
- m news-receivers (implementations of Observer [1])

Your application shall implement the following interface within the skeleton class provided in the template repository:

```java
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
```

Implement one broadcasting component that allows components to register as trusted source and spread news to other components.

Implement at least one component that acts as a news-source (can be as simple as the command-line asking for your news input).

Implement at least two components that receive news. Received news can be printed, for example, in a file or the console. Make sure that at least the following information is available for the receiving components: the time when the news was spread (i.e., when it was received by the broadcasting component), the source of the news, the news. 

By using the Observer pattern, the involved components only require minimal knowledge about each other.
Go for the push-approach [1]; encapsulate the broadcast details.


Try to identify reasonable java sub-packages for your application.

Bonus1: Do not store the password in the broadcast component in plain-text.

Bonus2: Components that receive news may register either for all news, or for certain topics (e.g., if the news contains a certain hashtag).

[1] Design Patterns by Gamma et al.: (Addison-Wesley, 1995)
