package Hangler_MoserSchwaiger;

import Hangler_MoserSchwaiger.Model.INewsReceiver;
import Hangler_MoserSchwaiger.Source.RSAEncryption;
import Hangler_MoserSchwaiger.View.Receiver1;
import Hangler_MoserSchwaiger.View.Receiver2;
import Hangler_MoserSchwaiger.View.Receiver3;
import a9se2020ws.A9Spreader;
import a9se2020ws.AuthenticationException;
import a9se2020ws.UntrustedSourceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.*;

class A9Test {
    A9Spreader spreader;

    @BeforeEach
    void setUp() {
        spreader = new A9Spreader();

        INewsReceiver nr1 = new Receiver1("Stefan", "#Sport#DailyNews");
        INewsReceiver nr2 = new Receiver2("Marie", "#Corona");
        INewsReceiver nr3 = new Receiver3("Eva", "#DailyNews#Corona");
        INewsReceiver nr4 = new Receiver3("Magda", "");

        spreader.registerNewsReceiver(nr1);
        spreader.registerNewsReceiver(nr2);
        spreader.registerNewsReceiver(nr3);
        spreader.registerNewsReceiver(nr4);
    }

    @Test
    void updateTest() throws AuthenticationException, UntrustedSourceException {
        //false if source is null or already registered or if pwd is null or empty , true otherwise
        assertTrue(spreader.registerTrustedSource("Orf.Sport", "sport123"));
        assertTrue(spreader.registerTrustedSource("Kronen Zeitung", "zeitung456"));

        assertFalse(spreader.registerTrustedSource(null,"123"));
        assertFalse(spreader.registerTrustedSource("Orf.Sport","123"));
        assertFalse(spreader.registerTrustedSource("new News",""));
        assertFalse(spreader.registerTrustedSource("new News",null));

        // throws UntrustedSourceException when the source was not registered before
        // throws AuthenticationException  when the source was registered with a different password
        spreader.spreadNews("Österreich hat ausnahmsweise im Fussball gewonnen #Sport", "Orf.Sport", "sport123");
        spreader.spreadNews("Corona Virus wurde ausgeloescht! #DailyNews#Corona", "Kronen Zeitung", "zeitung456");
        spreader.spreadNews("Bald ist Weihnachten!", "Kronen Zeitung", "zeitung456");

        assertThrows(UntrustedSourceException.class,() -> spreader.spreadNews("Falsche Quelle (UntrustedSource Exception) #Sport", "Orf.at", "sport123"));
        assertThrows(AuthenticationException.class,() ->  spreader.spreadNews("Falsches Passwort (Authentication Exception) #Sport", "Orf.Sport", "sport124"));
    }

    @Test
    void encryptionTest(){
        KeyPair keyPair = RSAEncryption.generateKeyPair();
        String encryption = RSAEncryption.encrypt("Uni_123_Sbg",keyPair.getPublic());
        //System.out.println(encription);
        assertEquals("Uni_123_Sbg",RSAEncryption.decrypt(encryption,keyPair.getPrivate()));
    }
}