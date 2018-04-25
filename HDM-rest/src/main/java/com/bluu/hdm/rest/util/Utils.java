/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Gonzalo Torres
 */
public class Utils {

    private static Logger logger = LogManager.getLogger(Utils.class.getName());

    public static String encodePassword(String password) {
	SecureRandom random = null;
	try {
	    random = SecureRandom.getInstance("SHA1PRNG");

	} catch (NoSuchAlgorithmException ex) {
	    logger.error("Error: " + ex.getMessage());
	}
	PasswordEncoder encoder = new BCryptPasswordEncoder(16, random);
	return encoder.encode(password);
    }
}
