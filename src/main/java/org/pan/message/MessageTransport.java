package org.pan.message;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import jssc.SerialPortException;

import java.io.IOException;

/**
 * Created by xiaopan on 2015/10/14 0014.
 */
public interface MessageTransport {

    void open() throws IOException, PortInUseException, UnsupportedCommOperationException, MessagingException;

    void close() throws IOException, SerialPortException, MessagingException;

    void sendMessageNoReturn(byte[] data) throws IOException;

    byte[] sendMessage(byte[] data, int returnSize, int returnTimeOut) throws IOException;
}
