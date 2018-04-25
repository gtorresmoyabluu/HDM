package com.bluu.hdm.web.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Clase de utilidad referente al crifrado/descifrado de texto
 */
@SuppressWarnings("unused")
public abstract class CryptoUtils {

    /**
     * Nombre del conjunto de caracteres para codificar cadenas de texto.
     */
    private static final String CHARSET_NAME = "UTF-8";

    ////////////////////////////////////////////////////////////////////////////////
    // HASH WITH SALT CRYPTO ///////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    // <editor-fold defaultstate="collapsed" desc="Click on the + sign on the left to edit the code.">
    /**
     * Nombre del algoritmo de hash.
     */
    private static final String HASH_ALGORITHM = "HmacSHA512";

    /**
     * Longitud del <code>salt</code> para el algoritmo hash. Igual al tamaño
     * del resultado del algoritmo.
     */
    private static final int HASH_SALT_LENGTH = 64;

    /**
     * Número de iteraciones a realizar en un cifrado hash.
     */
    private static final int HASH_ITERATIONS = 1000;

    /**
     * Calcula el <code>hash</code> del valor indicado.
     * <p>
     * Este método genera un valor de <code>salt</code> aleatorio y lo utiliza
     * para calcular repetitivamente tantas operaciones de <code>hash</code>
     * como indique {@link #HASH_ITERATIONS}.
     * <p>
     * El objeto {@link PasswordHash} devuelto contiene el <code>hash</code>
     * calculado y el <code>salt</code> generado.
     *
     * @param value el valor para el que calcular el <code>hash</code>.
     * @return el resultado del cálculo <code>hash</code>.
     */
    public static PasswordHash hash(String value) {

	byte[] salt = new byte[HASH_SALT_LENGTH];
	SecureRandom rnd = new SecureRandom();
	rnd.nextBytes(salt);

	return hash(value, salt);
    }

    /**
     * Calcula el <code>hash</code> del valor indicado usando el
     * <code>salt</code> indicado.
     * <p>
     * El objeto {@link PasswordHash} devuelto contiene el <code>hash</code>
     * calculado y el <code>salt</code> generado.
     *
     * @param value el valor para el que calcular el <code>hash</code>.
     * @param salt el valor de <code>salt</code> a utilizar.
     * @return el resultado del cálculo <code>hash</code>.
     */
    private static PasswordHash hash(String value, byte[] salt) {

	try {
	    byte[] retVal;
	    byte[] valueBytes = value.getBytes(CHARSET_NAME);
	    Mac mac = Mac.getInstance(HASH_ALGORITHM);
	    Key key = new SecretKeySpec(salt, HASH_ALGORITHM);
	    mac.init(key);
	    retVal = mac.doFinal(valueBytes);
	    for (int i = 1; i < HASH_ITERATIONS; i++) {
		retVal = mac.doFinal(retVal);
	    }
	    return new PasswordHash(retVal, salt);

	} catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException cause) {
	    throw new RuntimeException(cause);
	}

    }

    /**
     * Comprueba si el <code>hash</code> calculado para el valor y el
     * <code>salt</code> indicados es igual al <code>hash</code> correcto
     * indicado.
     * <p>
     * Este método permite comprobar si un valor es integro con respecto al
     * <code>hash</code>.
     *
     * @param value el valor a comprobar.
     * @param correctHash el <code>hash</code> correcto.
     * @param salt el <code>salt</code> a utilizar para la generación del
     * <code>hash</code> de <code>value</code>. Debe ser el mismo que el
     * utilizado para calcular <code>hash</code>.
     * @return <code>true</code> si el <code>hash</code> de <code>value</code>
     * usando <code>salt</code> es igual a <code>correctHash</code>.
     */
    public static boolean isValid(String value, String correctHash, String salt) {

	byte[] saltBytes = Base64.decodeBase64(salt);

	PasswordHash ph = hash(value, saltBytes);
	return ph.getHash().equals(correctHash);
    }

    /**
     * Representa el resultado de una operación de <code>hash</code> con
     * <code>salt</code>.
     */
    public static class PasswordHash {

	/**
	 * Contains the value of <code> hash </ code>.
	 */
	private byte[] hash;

	/**
	 * Contiene el valor de <code>salt</code>.
	 */
	private byte[] salt;

	/**
	 * Construct a new instance with the indicated values.
	 *
	 * @param hash The value of <code> hash </ code>.
	 * @param salt The value of <code> salt </ code>.
	 */
	public PasswordHash(byte[] hash, byte[] salt) {
	    this.hash = hash;
	    this.salt = salt;
	}

	/**
	 * Returns the value of <code> hash </ code> as a String in base64.
	 *
	 * @return the value.
	 */
	public String getHash() {
	    return encodeBase64(hash);
	}

	/**
	 * Returns the value of <code> salt </ code> as a String in base64.
	 *
	 * @return the value.
	 */
	public String getSalt() {
	    return encodeBase64(salt);
	}
    }

    // </editor-fold>
    ////////////////////////////////////////////////////////////////////////////////
    // REVERSIBLE CRYPTO ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    // <editor-fold defaultstate="collapsed" desc="Click on the + sign on the left to edit the code.">
    /**
     * Nombre del algoritmo de cifrado reversible.
     */
    private static final String REVERSE_ALGORITHM = "AES";

    /**
     * Clave para cifrar/descifrar usado en el algoritmo reversible
     */
    private static final byte[] REVERSE_KEY = {(byte) 0x24, (byte) 0x6f, (byte) 0x4c, (byte) 0x61, (byte) 0x4b, (byte) 0x61,
	(byte) 0x53, (byte) 0x65, (byte) 0x48, (byte) 0x21, (byte) 0x53, (byte) 0x68, (byte) 0x55, (byte) 0x72, (byte) 0x21,
	(byte) 0x23};

    /**
     * Método para cifrar una cadena de texto que podrá ser posteriormente
     * cifrada
     *
     * @param value Cadena de texto a cifrar
     * @return Cadena de texto cifrada y codificada en Base64
     */
    public static String encrypt(String value) {
	try {
	    Key key = new SecretKeySpec(REVERSE_KEY, REVERSE_ALGORITHM);
	    Cipher cipher = Cipher.getInstance(REVERSE_ALGORITHM);
	    cipher.init(Cipher.ENCRYPT_MODE, key);

	    return encodeBase64(cipher.doFinal(value.getBytes(CHARSET_NAME)));

	} catch (Throwable e) {
	    throw new RuntimeException(e);
	}
    }

    /**
     * Método para descifrar una cadena de texto que ha sido previamente cifrada
     *
     * @param value Cadena de texto a descrifrar en código Base64
     * @return Cadena de texto decodificada y descifrada
     */
    public static String decrypt(String value) {
	try {
	    Key key = new SecretKeySpec(REVERSE_KEY, REVERSE_ALGORITHM);
	    Cipher cipher = Cipher.getInstance(REVERSE_ALGORITHM);
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    return new String(cipher.doFinal(Base64.decodeBase64(value)), CHARSET_NAME);

	} catch (Throwable e) {
	    throw new RuntimeException(e);
	}
    }

    // </editor-fold>
    ////////////////////////////////////////////////////////////////////////////////
    // BASE 64 CODIFICATION ////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    // <editor-fold defaultstate="collapsed" desc="Click on the + sign on the left to edit the code.">
    public static String encodeBase64(byte[] value) {
	try {
	    return new String(Base64.encodeBase64(value), CHARSET_NAME);
	} catch (UnsupportedEncodingException ex) {
	    throw new RuntimeException(ex);
	}
    }

    public static String encodeBase64(String value) {
	try {
	    return encodeBase64(value.getBytes(CHARSET_NAME));
	} catch (UnsupportedEncodingException ex) {
	    throw new RuntimeException(ex);
	}
    }

    public static String decodeBase64(byte[] value) {
	try {
	    return new String(Base64.decodeBase64(value), CHARSET_NAME);
	} catch (UnsupportedEncodingException ex) {
	    throw new RuntimeException(ex);
	}
    }

    public static String decodeBase64(String value) {
	try {
	    return new String(Base64.decodeBase64(value), CHARSET_NAME);
	} catch (UnsupportedEncodingException ex) {
	    throw new RuntimeException(ex);
	}
    }

    // </editor-fold>
}
