package Hangler_MoserSchwaiger;

import Hangler_MoserSchwaiger.Model.INewsReceiver;
import Hangler_MoserSchwaiger.View.Receiver1;
import Hangler_MoserSchwaiger.View.Receiver2;
import Hangler_MoserSchwaiger.View.Receiver3;
import a9se2020ws.A9Spreader;
import a9se2020ws.AuthenticationException;
import a9se2020ws.UntrustedSourceException;

public class TestDemo {
    public static void main(String[] args) throws Exception {
        A9Spreader spreader = new A9Spreader();

        //create news receiver
        INewsReceiver nr1 = new Receiver1("Stefan", "#Sport#DailyNews");
        INewsReceiver nr2 = new Receiver2("Marie", "#Corona");
        INewsReceiver nr3 = new Receiver3("Eva", "#DailyNews#Corona");
        INewsReceiver nr4 = new Receiver3("Magda", "");

        //register news receiver
        spreader.registerNewsReceiver(nr1);
        spreader.registerNewsReceiver(nr2);
        spreader.registerNewsReceiver(nr3);
        spreader.registerNewsReceiver(nr4);

        //false if source is null or already registered or if pwd is null or empty , true otherwise
        System.out.println("Add Source: expected = true -> actual: " + spreader.registerTrustedSource("Orf.Sport", "sport123"));
        System.out.println("Add Source: expected = true -> actual: " + spreader.registerTrustedSource("Kronen Zeitung", "zeitung456"));

        System.out.println("Add Source: expected = false -> actual: " + spreader.registerTrustedSource(null,"123"));
        System.out.println("Add Source: expected = false -> actual: " + spreader.registerTrustedSource("Orf.Sport","123"));
        System.out.println("Add Source: expected = false -> actual: " + spreader.registerTrustedSource("new News",""));
        System.out.println("Add Source: expected = false -> actual: " + spreader.registerTrustedSource("new News",null));


        // throws UntrustedSourceException when the source was not registered before
        // throws AuthenticationException  when the source was registered with a different password
        spreader.spreadNews("Österreich hat ausnahmsweise im Fussball gewonnen #Sport", "Orf.Sport", "sport123");
        spreader.spreadNews("Corona Virus wurde ausgeloescht! #DailyNews#Corona", "Kronen Zeitung", "zeitung456");

        //spreader.spreadNews("Falsche Quelle (UntrustedSource Exception) #Sport", "Orf.at", "sport123");
        //spreader.spreadNews("Falsches Passwort (Authentication Exception) #Sport", "Orf.Sport", "sport124");
    }
}
