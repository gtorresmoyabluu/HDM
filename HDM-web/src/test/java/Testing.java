
import com.bluu.hdm.web.util.CryptoUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gonzalo Torres
 */
public class Testing {
    public static void main(String[] args){
        String data = CryptoUtils.encodeBase64("serrot");
        System.out.print("encodeBase64: " + data);
        data = CryptoUtils.decodeBase64(data);
        System.out.print("decodeBase64: " + data);
    }
}
