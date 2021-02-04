package Hangler_MoserSchwaiger.Source;

/* references: https://niels.nu/blog/2016/java-rsa.html */

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * RSA-Encryption
 */
public class RSAEncryption {

    /**
     * @return generated KeyPair (includes public- and private Key) for encryption
     */
    public static KeyPair generateKeyPair() {
        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert generator != null;
        generator.initialize(2048, new SecureRandom());

        return generator.generateKeyPair();
    }

    /**
     * RSA-Encryption of a password/text with the given key
     * @param plainText text which should be encrypted
     * @param publicKey key for specific encryption
     * @return encrypted text as String
     */
    public static String encrypt(String plainText, PublicKey publicKey){
        Cipher encryptCipher = null;
        try {
            encryptCipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        try {
            assert encryptCipher != null;
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        byte[] cipherText = new byte[0];
        try {
            cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(cipherText);
    }

    /**
     * RSA-Decryption of a encrypted password/text with the given key
     * @param cipherText text which should be decrypted
     * @param privateKey key for specific decryption
     * @return decrypted text as String
     */
    public static String decrypt(String cipherText, PrivateKey privateKey) {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = null;
        try {
            decriptCipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        try {
            assert decriptCipher != null;
            decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            return new String(decriptCipher.doFinal(bytes), UTF_8);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Anything go wrong in de description");
    }
}
