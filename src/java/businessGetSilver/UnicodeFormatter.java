/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessGetSilver;

import java.math.BigInteger;
import static oracle.net.aso.C04.s;

/**
 *
 * @author pb
 */
public class UnicodeFormatter {

     static public String byteToHex(byte b) {
    // Returns hex String representation of byte b
    char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f' };
    char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
    return new String(array);
  }
 static public byte[] toByteArr(String no)  
    {  
        byte[] number = new byte[no.length()/2];  
        int i;  
        for (i=0; i<no.length(); i+=2)  
        {  
            int j = Integer.parseInt(no.substring(i,i+2), 16);  
            number[i/2] = (byte)(j & 0x000000ff);  
        }  
        return number;  
    }  
  
    public static  byte[] HexToByte(String hex)  
    {  
                    int len = hex.length();  
                    byte[] value= new byte[len / 2];  
                    for (int i = 0; i < len; i += 2)  
                      {  
                        value[i / 2] = (byte) ((Character.digit(hex  
                            .charAt(i), 16) << 4) + Character.digit(hex  
                            .charAt(i + 1), 16));
                        
                       
                        
  
                      }  
      
                    return value;  
    }  
public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                             + Character.digit(s.charAt(i+1), 16));
    }
    return data;
}
  static public String charToHex(char c) {
    // Returns hex String representation of char c
    byte hi = (byte) (c >>> 8);
    byte lo = (byte) (c & 0xff);
    return byteToHex(hi) + byteToHex(lo);
  }

}
