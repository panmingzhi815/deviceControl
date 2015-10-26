package org.pan.utils;

/**
 * 字节与字符工具
 * Created by fangjinliu on 2015/10/26.
 */
public class ByteUtils {

    private final static char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final static String splitString = " ";

    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder format = new StringBuilder(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            format.append(byteToHexString(bytes[i])).append(splitString);
        }
        return format.toString().trim();
    }

    private static String byteToHexString(byte bytes) {
        char hexChars1;
        char hexChars2;
        int v;

        v = bytes & 0xFF;
        hexChars1 = hexArray[v / 16];
        hexChars2 = hexArray[v % 16];

        return hexChars1 + "" + hexChars2;
    }

    public static byte[] hexStringToByteArray(String s) {
        s = s.replaceAll(splitString,"");
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
