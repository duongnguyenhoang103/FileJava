/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.license.encoding;

/**
 *
 * @author BinLe
 */
public class Base32 {
    // bảng kí tự chuẩn cho Base32

    private char[] baseChar = new char[36];

    /**
     * Khởi tạo Base32, tạo bảng kí tự chuẩn
     */
    public Base32() {
        int i = 0;
        for (char c = 'A'; c <= 'Z'; c++, i++) {
            baseChar[i] = c;
        }
        for (char c = '0'; c <= '9'; c++, i++) {
            baseChar[i] = c;
        }
    }

    /**
     * convert mảng byte sang chuỗi bit nhị phân
     * bytes: mảng byte được truyền vào
     * trả về chuỗi nhị phân giải mã được
     */
    private String byteArray_2_StringBit(byte[] bytes) {
        String stringBit = "";
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 7; j >= 0; j--) {
                if (((bytes[i]) & (1 << j)) == (1 << j)) {
                    stringBit += '1';
                } else {
                    stringBit += '0';
                }
            }
        }
        return stringBit;
    }

    /**
     * convert từ mảng byte sang chuỗi kí tự A->Z,0->9
     * bytes: mảng byte truyền vào
     * trả về chuỗi kí tự mã hóa
     */
    public String byteArray_2_String32(byte[] bytes) {
        String string32 = "";
        String stringBit = byteArray_2_StringBit(bytes);
        int i = 0;
        while (i < stringBit.length()) {
            int s = 0;
            for (int j = 0; j < 5; j++) {
                s = s << 1;
                if ((i + j < stringBit.length() && stringBit.charAt(i + j) == '1')) {
                    s = s | 1;
                }
            }
            string32 += baseChar[s];
            i = i + 5;
        }

        int p = stringBit.length() % 5;
        switch (p) {
            case 1:
                string32 += baseChar[32];
                break;
            case 2:
                string32 += baseChar[33];
                break;
            case 3:
                string32 += baseChar[34];
                break;
            case 4:
                string32 += baseChar[35];
                break;
            default:
                break;
        }
        return string32;
    }

    /**
     * convert chuỗi kí tự (A->Z,0->9) sang chuỗi bit nhị phân
     * string32: chuỗi kí tự dạng mã hóa Base32(A->Z,0->9)
     * trả về chuỗi bít giải mã được
     */
    private String String32_2_StringBit(String string32) {
        String stringBit = "";
        for (int i = 0; i < string32.length(); i++) {
            int s = -1;
            for (s = 0; s < 36; s++) {
                if (baseChar[s] == string32.charAt(i)) {
                    break;
                }
            }
            if (s >= 0 && s < 32) {
                for (int j = 4; j >= 0; j--) {
                    if ((s & (1 << j)) == (1 << j)) {
                        stringBit += '1';
                    } else {
                        stringBit += '0';
                    }
                }
            } else if (s >= 32) {
                switch (s) {
                    case 32:
                        stringBit = stringBit.substring(0, stringBit.length() - 4);
                        break;
                    case 33:
                        stringBit = stringBit.substring(0, stringBit.length() - 3);
                        break;
                    case 34:
                        stringBit = stringBit.substring(0, stringBit.length() - 2);
                        break;
                    case 35:
                        stringBit = stringBit.substring(0, stringBit.length() - 1);
                        break;
                    default:
                        break;
                }
            }
        }
        return stringBit;
    }

    /**
     * convert chuỗi kí tự (A->Z,0->9) sang mảng byte
     * string32: chuỗi kí tự dạng mã hóa Base32(A->Z,0->9)
     * trả về mảng byte giải mã được
     */
    public byte[] string32_2_ByteArray(String string32) {
        String stringBit = String32_2_StringBit(string32);
        byte[] bytes = new byte[stringBit.length() / 8];
        int dem = 0;
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = 0;
            for (int j = 7; j >= 0; j--) {
                if (stringBit.charAt(dem) == '1') {
                    bytes[i] = (byte) (bytes[i] | (1 << j));
                }
                dem++;
            }
        }
        return bytes;
    }
}
