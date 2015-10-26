package org.pan.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fangjinliu on 2015/10/26.
 */
public class ByteUtilTest {

    @Test
    public void testByteArrayToString() throws Exception {
        byte[] bytes_define = {(byte) 70, (byte) -66, (byte) 35, (byte) -14};
        String byteString = "46 BE 23 F2";
        byte[] bytes_result = ByteUtils.hexStringToByteArray(byteString);
        Assert.assertArrayEquals(bytes_define,bytes_result);
    }

    @Test
    public void testStringToByteArray() throws Exception {
        String byteString_define = "46 BE 23 F2";
        byte[] byteArray = {(byte) 70, (byte) -66, (byte) 35, (byte) -14};
        String byteString_result = ByteUtils.byteArrayToHexString(byteArray);
        Assert.assertEquals(byteString_define,byteString_result);
    }

}