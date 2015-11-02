package org.pan.message.impl;

import org.pan.message.AbstractMessageTransport;
import org.pan.message.DeviceAddress;
import org.pan.message.MessageTransport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * TCP通讯底层
 * Created by xiaopan on 2015/10/14 0014.
 */
public class TCPTransport extends AbstractMessageTransport implements MessageTransport {

    private DeviceAddress deviceAddress;
    private Socket socket;

    public TCPTransport(DeviceAddress deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    @Override
    public void open() throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(deviceAddress.getLinkAddress(), 10001),1000);
        socket.setSoTimeout(3000);
    }

    @Override
    public void close() throws IOException {
        if(socket == null || socket.isClosed()){
            return;
        }
        socket.close();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }
}
