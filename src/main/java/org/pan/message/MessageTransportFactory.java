package org.pan.message;

import org.pan.message.impl.RXTXSerialPortTransport;
import org.pan.message.impl.TCPTransport;

/**
 * Created by xiaopan on 2015/10/14 0014.
 */
public class MessageTransportFactory {

    public static MessageTransport create(DeviceAddress deviceAddress){
        if(deviceAddress.getLinkType() == DeviceAddress.LinkType.COM){
            return new RXTXSerialPortTransport(deviceAddress);
        }else {
            return new TCPTransport(deviceAddress);
        }
    }

}
