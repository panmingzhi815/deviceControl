package org.pan;

import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.apache.commons.configuration.ConfigurationException;
import org.pan.message.DeviceAddress;
import org.pan.message.MessageTransport;
import org.pan.message.MessageTransportFactory;
import org.pan.utils.Alerts;
import org.pan.utils.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
    private final String loadConfigFileErrorTips = "加载必须要设备命令配置参数失败！请检查文件内容是否完整：" + DeviceConfigurator.configFileName;

    public VBox root;
    public Label lbl_state;
    public ComboBox cb_serialList;

    private DeviceConfigurator deviceConfigurator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.deviceConfigurator = DeviceConfigurator.getInstance();
        } catch (ConfigurationException e) {
            LOGGER.error(loadConfigFileErrorTips,e);
            Alerts.create(Alert.AlertType.ERROR).setHeaderText(loadConfigFileErrorTips).show();
        }

        for (int i = 1; i <= 15; i++) {
            cb_serialList.getItems().add("COM"+i);
        }
    }

    public void oneModel_onAction(ActionEvent actionEvent) {
        try{
            final String oneModelBytes = this.deviceConfigurator.getOneModelBytes();
            final String oneModelColor = this.deviceConfigurator.getOneModelColor();
            sendMessage(oneModelBytes,oneModelColor,"单色模式");
            LOGGER.info("开始切换单色模式成功");
        }catch (Exception e){
            LOGGER.error("开始切换单色模式失败", e);
            Alerts.create(Alert.AlertType.ERROR).setTitle("操作失败").setHeaderText(e.getMessage()).showAndWait();
        }
    }

    public void freedomModel_onAction(ActionEvent actionEvent) {
        try{
            final String freedomModelBytes = this.deviceConfigurator.getFreedomModelBytes();
            final String freedomModelColor = this.deviceConfigurator.getFreedomModelColor();
            sendMessage(freedomModelBytes,freedomModelColor,"自由模式");
            LOGGER.info("开始切换自由模式成功");
        }catch (Exception e){
            LOGGER.error("开始切换自由模式失败",e);
            Alerts.create(Alert.AlertType.ERROR).setTitle("操作失败").setHeaderText(e.getMessage()).showAndWait();
        }
    }

    public void noneModel_onAction(ActionEvent actionEvent) {
        try{
            final String noneModelBytes = this.deviceConfigurator.getNoneModelBytes();
            final String noneModelColor = this.deviceConfigurator.getNoneModelColor();
            sendMessage(noneModelBytes,noneModelColor,"无色模式");
            LOGGER.info("开始切换无色模式成功");
        }catch (Exception e){
            LOGGER.error("开始切换无色模式失败",e);
            Alerts.create(Alert.AlertType.ERROR).setTitle("操作失败").setHeaderText(e.getMessage()).showAndWait();
        }
    }

    public void powerOn_onAction(ActionEvent actionEvent) {
        try{
            final String powerOnBytes = this.deviceConfigurator.getPowerOnBytes();
            final String powerOnColor = this.deviceConfigurator.getPowerOnColor();
            sendMessage(powerOnBytes,powerOnColor,"打开");
            LOGGER.info("打开成功");
        }catch (Exception e){
            LOGGER.error("打开失败",e);
            Alerts.create(Alert.AlertType.ERROR).setTitle("操作失败").setHeaderText(e.getMessage()).showAndWait();
        }
    }

    public void powerOff_onAction(ActionEvent actionEvent) {
        try{
            final String powerOffBytes = this.deviceConfigurator.getPowerOffBytes();
            final String powerOffColor = this.deviceConfigurator.getPowerOffColor();
            sendMessage(powerOffBytes,powerOffColor,"关闭");
            LOGGER.info("关闭成功");
        }catch (Exception e){
            LOGGER.error("关闭失败",e);
            Alerts.create(Alert.AlertType.ERROR).setTitle("操作失败").setHeaderText(e.getMessage()).showAndWait();
        }
    }

    public void sendMessage(String byteString,String color,String state) throws Exception {
        LOGGER.info("发送消息 ：{}  界面颜色 ： {}" , byteString,color);
        final MessageTransport messageTransport = MessageTransportFactory.create(new DeviceAddress(DeviceAddress.LinkType.COM, (String) cb_serialList.getValue()));
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

            root.setStyle("-fx-background-color: "+color + ";");
            lbl_state.setText("当前状态：" + state);
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

}
