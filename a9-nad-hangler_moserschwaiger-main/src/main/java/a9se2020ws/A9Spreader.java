package a9se2020ws;

import Hangler_MoserSchwaiger.Model.Event;
import Hangler_MoserSchwaiger.Model.INewsReceiver;
import Hangler_MoserSchwaiger.Model.ISubject;
import Hangler_MoserSchwaiger.Source.TrustedSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;

/**
* Concrete Class of the Subject Interface
*/

public class A9Spreader implements NewsSpreader, ISubject {

	private final HashMap<String, TrustedSource> trustedSources;
	private final HashSet<INewsReceiver> newsReceivers;

	/**
	 * constructs a {@link A9Spreader} object
	 */
	public A9Spreader(){
		trustedSources = new HashMap<>();
		newsReceivers = new HashSet<>();
	}

	@Override
	public boolean registerTrustedSource(String source, String pwd){
				
		// false if source is null or already registered or if pwd is null or empty
		if(source == null || this.trustedSources.containsKey(source) || pwd == null || pwd.equals("")){
			return false;
		}

		this.trustedSources.put(source,new TrustedSource(source,pwd));
		return true;
	}

	@Override
	public void spreadNews(String news, String source, String pwd) throws UntrustedSourceException, AuthenticationException {

		if(!this.trustedSources.containsKey(source)){
			throw new UntrustedSourceException(source);
		}

		if (!(this.trustedSources.get(source).isSamePwd(pwd))){
			throw new AuthenticationException(source);
		}

		notifyReceiver(new Event(source,news, getActualDateTime()));
	}

	@Override
	public void registerNewsReceiver(INewsReceiver nr) {
		newsReceivers.remove(nr); //remove if nr is present in hashset, to update it
		newsReceivers.add(nr);
	}

	@Override
	public void removeNewsReceiver(INewsReceiver nr){
		newsReceivers.remove(nr);
	}

	/**
	 * Notifies the registered receivers of a new event
	 * @param e the event to notify the receivers of
	 */
	public void notifyReceiver(Event e){
		for(INewsReceiver nr : newsReceivers)
			nr.update(e);
	}

	/**
	 * @return the current date and time as a String
	 */
	private String getActualDateTime(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}
