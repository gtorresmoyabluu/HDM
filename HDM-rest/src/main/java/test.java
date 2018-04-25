
import com.bluu.hdm.rest.util.Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gonzalo Torres
 */
public class test {

    public static void main(String[] args) {
	String password = "admin";
	String encrypted = Utils.encodePassword(password);
	System.out.println(String.format("Clave: %s\nLargo: %s", encrypted, encrypted.length()));
    }
}
//$2a$16$Hj12reASapBD8b6mA5D83uZuO/pGcx7YE19OqK9ey2dcnxpcopbGC
//$2a$16$/12zzq6XY8aOeHI6Ii/gWOyJ1QLdCoh9eGPOITXKqkI9DkADyy/li
