package org.pan;

import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import org.apache.commons.configuration.ConfigurationException;
import org.pan.message.DeviceAddress;
import org.pan.message.MessageTransport;
import org.pan.message.MessageTransportFactory;
import org.pan.utils.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by Administrator on 2015/11/2.
 */
public class DeviceUI {
    private final Logger LOGGER = LoggerFactory.getLogger(DeviceUI.class);
    private final String loadConfigFileErrorTips = "加载必须要设备命令配置参数失败！请检查文件内容是否完整：" + DeviceConfigurator.configFileName;

    private DeviceConfigurator deviceConfigurator;

    private JPanel panel1;
    private JButton 打开Button;
    private JButton 关闭Button;
    private JButton 单色模式Button;
    private JButton 自由模式Button;
    private JButton 六色模式Button;
    private JComboBox 串口ComboBox;

    public DeviceUI() {
        try {
            this.deviceConfigurator = DeviceConfigurator.getInstance();
        } catch (ConfigurationException e) {
            LOGGER.error(loadConfigFileErrorTips,e);
            JOptionPane.showMessageDialog(null,loadConfigFileErrorTips,"错误",JOptionPane.OK_OPTION);
        }

        打开Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try{
                    final String powerOffBytes = deviceConfigurator.getPowerOffBytes();
                    final String powerOffColor = deviceConfigurator.getPowerOffColor();
                    sendMessage(powerOffBytes,powerOffColor,"关闭");
                    LOGGER.info("关闭成功");
                }catch (Exception ex){
                    LOGGER.error("关闭失败",e);
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"操作失败",JOptionPane.OK_OPTION);
                }
            }
        });
        关闭Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try{
                    final String powerOffBytes = deviceConfigurator.getPowerOffBytes();
                    final String powerOffColor = deviceConfigurator.getPowerOffColor();
                    sendMessage(powerOffBytes,powerOffColor,"关闭");
                    LOGGER.info("关闭成功");
                }catch (Exception ex){
                    LOGGER.error("关闭失败",e);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "操作失败", JOptionPane.OK_OPTION);
                }
            }
        });
        单色模式Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try{
                    final String oneModelBytes = deviceConfigurator.getOneModelBytes();
                    final String oneModelColor = deviceConfigurator.getOneModelColor();
                    sendMessage(oneModelBytes,oneModelColor,"单色模式");
                    LOGGER.info("开始切换单色模式成功");
                }catch (Exception ex){
                    LOGGER.error("开始切换单色模式失败", e);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "操作失败", JOptionPane.OK_OPTION);
                }
            }
        });
        自由模式Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try{
                    final String freedomModelBytes = deviceConfigurator.getFreedomModelBytes();
                    final String freedomModelColor = deviceConfigurator.getFreedomModelColor();
                    sendMessage(freedomModelBytes,freedomModelColor,"自由模式");
                    LOGGER.info("开始切换自由模式成功");
                }catch (Exception ex){
                    LOGGER.error("开始切换自由模式失败",e);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "操作失败", JOptionPane.OK_OPTION);
                }
            }
        });
        六色模式Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try{
                    final String noneModelBytes = deviceConfigurator.getNoneModelBytes();
                    final String noneModelColor = deviceConfigurator.getNoneModelColor();
                    sendMessage(noneModelBytes,noneModelColor,"六色模式");
                    LOGGER.info("开始切换无色模式成功");
                }catch (Exception ex){
                    LOGGER.error("开始切换无色模式失败",e);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "操作失败", JOptionPane.OK_OPTION);
                }
            }
        });
    }

    public void sendMessage(String byteString,String color,String state) throws Exception {
        LOGGER.info("发送消息 ：{}  界面颜色 ： {}" , byteString,color);
        final MessageTransport messageTransport = MessageTransportFactory.create(new DeviceAddress(DeviceAddress.LinkType.COM, 串口ComboBox.getSelectedItem().toString()));
        try{
            try {
                messageTransport.open();
            } catch (IOException e) {
                throw new Exception("打开指定串口失败，可能该串口本身不存在",e);
            } catch (PortInUseException e) {
                throw new Exception("打开指定串口失败，该串口可能正在被使用",e);
            } catch (UnsupportedCommOperationException e) {
                throw new Exception("打开指定串口失败，不允许操作该串口",e);
            }

            final byte[] bytes = ByteUtils.hexStringToByteArray(byteString);
            try {
                messageTransport.sendMessageNoReturn(bytes);
            } catch (IOException e) {
                throw new Exception("发送消息失败",e);
            }

            panel1.setBackground(Color.decode(color));
//            lbl_state.setText("当前状态：" + state);
        }finally {
            try {
                if(messageTransport != null){
                    messageTransport.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
