package com.nuc.utils;

import java.nio.ByteBuffer;
import java.util.Locale;

public class ByteUtils {
    private final static char[] mChars = "0123456789ABCDEF".toCharArray();


    public static byte[] hexStr2Byte(String hex) {
        if (hex == null) {
            return new byte[]{};
        }


        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }

        int length = hex.length();
        ByteBuffer buffer = ByteBuffer.allocate(length / 2);
        for (int i = 0; i < length; i++) {
            String hexStr = hex.charAt(i) + "";
            i++;
            hexStr += hex.charAt(i);
            byte b = (byte) Integer.parseInt(hexStr, 16);
            buffer.put(b);
        }
        return buffer.array();
    }


    public static String byteArrayToHexString(byte[] array) {
        if (array == null) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            buffer.append(byteToHex(array[i]));
        }
        return buffer.toString();
    }


    public static String byteToHex(byte b) {
        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        return hex.toUpperCase(Locale.getDefault());
    }

    

    public static String dispose(byte[] data, int iLen) {
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < iLen; n++) {
            sb.append(mChars[(data[n] & 0xFF) >> 4]);
            sb.append(mChars[data[n] & 0x0F]);
            sb.append(' ');
        }
        return sb.toString().trim().toUpperCase(Locale.US);
    }

    public static String checkcode(String para) {
        int length = para.length() / 2;
        String[] dateArr = new String[length];

        for (int i = 0; i < length; i++) {
            dateArr[i] = para.substring(i * 2, i * 2 + 2);
        }
        String code = "00";
        for (int i = 0; i < dateArr.length; i++) {
            code = Xor(code, dateArr[i]);
        }
        return code;
    }

    private static String Xor(String strHex_X, String strHex_Y) {



        String anotherBinary = Integer.toBinaryString(Integer.valueOf(strHex_X, 16));

        String thisBinary = Integer.toBinaryString(Integer.valueOf(strHex_Y, 16));

        String result = "";


        if (anotherBinary.length() != 8) {
            for (int i = anotherBinary.length(); i < 8; i++) {

                anotherBinary = "0" + anotherBinary;
            }
        }
        if (thisBinary.length() != 8) {
            for (int i = thisBinary.length(); i < 8; i++) {
                thisBinary = "0" + thisBinary;
            }
        }


        for (int i = 0; i < anotherBinary.length(); i++) {

            if (thisBinary.charAt(i) == anotherBinary.charAt(i))
                result += "0";
            else {
                result += "1";
            }
        }
        return Integer.toHexString(Integer.parseInt(result, 2));
    }

    public static byte[] hexStr2Bytes(String src) {

        src = src.trim().replace(" ", "").toUpperCase();

        int m = 0, n = 0;
        int l = src.length() / 2;
        System.out.println(l);
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
        }
        return ret;
    }


}
