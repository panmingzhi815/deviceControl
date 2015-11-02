package org.pan.message.impl;

import gnu.io.RXTXPort;
import gnu.io.SerialPort;
import org.pan.message.AbstractMessageTransport;
import org.pan.message.DeviceAddress;
import org.pan.message.MessageTransport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 串口通讯底层
 * Created by xiaopan on 2015/10/14 0014.
 */
public class RXTXSerialPortTransport extends AbstractMessageTransport implements MessageTransport {

    private DeviceAddress deviceAddress;
    private RXTXPort serialPort;

    public RXTXSerialPortTransport(DeviceAddress deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    @Override
    public void open() throws IOException {
        try{
            this.serialPort = new RXTXPort(deviceAddress.getLinkAddress());
            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        }catch (Exception io){
            throw new IOException("打开串口失败",io);
        }
    }

    @Override
    public void close() {
        if(serialPort == null){
            return;
        }
        serialPort.close();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return serialPort.getInputStream();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return serialPort.getOutputStream();
    }
}
