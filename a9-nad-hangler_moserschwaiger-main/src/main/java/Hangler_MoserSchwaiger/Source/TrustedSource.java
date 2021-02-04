package Hangler_MoserSchwaiger.Source;

import java.security.KeyPair;
import java.util.Objects;

/**
 * A class representing a Trusted/Registered Source
 */
public class TrustedSource {
    private final String name;
    private final String pwd;
    private final KeyPair keyPair;

    /**
     * constructs a {@link TrustedSource} object
     *  create a RSA-key pair and encrypt the pwd with RSA-Encryption
     * @param name name of the source
     * @param pwd  password of the source
     */
    public TrustedSource(String name, String pwd){
        this.name = name;
        this.keyPair = RSAEncryption.generateKeyPair();
        this.pwd = RSAEncryption.encrypt(pwd,this.keyPair.getPublic());
    }

    /**
     * checks whether a given password is the correct one
     * @param checkingPwd the password to compare to the correct one
     * @return  true if the passwords are the same, false otherwise
     */
    public boolean isSamePwd(String checkingPwd){
        return Objects.equals(RSAEncryption.decrypt(this.pwd, this.keyPair.getPrivate()), checkingPwd);
    }
}
