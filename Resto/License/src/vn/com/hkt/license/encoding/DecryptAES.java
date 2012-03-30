/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.license.encoding;

import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author BinLe
 */
public class DecryptAES {
    //khóa  mã hóa, giải mã
    private byte[] keyBytes;
    // dữ liệu cần giải mã
    private byte[] dataEncrypted;
    // key giải mã
    private SecretKeySpec key;    
    private Cipher cipher;
 /**
     * Khởi tại Giải mã AES
     * keyBytes: key User truyền vào để làm khóa giải mã(dạng byte)
     * dataEncrypted: dữ liệu cần giải mã
     */
    public DecryptAES(byte[] keyBytes, byte[] dataEncrypted) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        try {
            this.keyBytes = keyBytes;
            this.dataEncrypted = dataEncrypted;
            key = new SecretKeySpec(this.keyBytes, "AES");
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * hàm giải mã
     * trả về mảng byte(dữ liệu) được giải mã
     * @return 
     */
    public byte[] decrypt() {
        try {            
            int ctLength = dataEncrypted[dataEncrypted.length - 1];
            byte[] cipherText = new byte[dataEncrypted.length - 1];
            System.arraycopy(dataEncrypted, 0, cipherText, 0, cipherText.length);

            byte[] plainText = new byte[cipher.getOutputSize(ctLength)];
            int ptLength = cipher.update(cipherText, 0, ctLength, plainText, 0);
            ptLength += cipher.doFinal(plainText, ptLength);

            byte[] dataAfterDecrypt = new byte[ptLength];
            System.arraycopy(plainText, 0, dataAfterDecrypt, 0, ptLength);
//            dataAfterDecrypt[dataAfterDecrypt.length - 1] = (byte) ptLength;
            
            return dataAfterDecrypt;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
