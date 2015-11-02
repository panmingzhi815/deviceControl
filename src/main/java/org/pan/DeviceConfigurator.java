package org.pan;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * 默认配置文件
 * Created by fangjinliu on 2015/10/26.
 */
public class DeviceConfigurator {

    public final static String configFileName = "deviceConfigurator.config";
    private PropertiesConfiguration propertiesConfiguration;

    final String powerOnBytes = "powerOnBytes";
    final String powerOnColor = "powerOnColor";
    final String powerOffBytes = "powerOffBytes";
    final String powerOffColor = "powerOffColor";
    final String oneModelBytes = "oneModelBytes";
    final String oneModelColor = "oneModelColor";
    final String freedomModelBytes = "freedomModelBytes";
    final String freedomModelColor = "freedomModelColor";
    final String noneModelBytes = "noneModelBytes";
    final String noneModelColor = "noneModelColor";

    private DeviceConfigurator(PropertiesConfiguration propertiesConfiguration) {
        this.propertiesConfiguration = propertiesConfiguration;
    }

    public static DeviceConfigurator getInstance() throws ConfigurationException {
        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(configFileName);
        propertiesConfiguration.setEncoding("UTF-8");
        return new DeviceConfigurator(propertiesConfiguration);
    }

    public String getPowerOnBytes() {
        return this.propertiesConfiguration.getString(powerOnBytes);
    }

    public String getPowerOnColor() {
        return this.propertiesConfiguration.getString(powerOnColor);
    }

    public String getPowerOffBytes() {
        return this.propertiesConfiguration.getString(powerOffBytes);
    }

    public String getPowerOffColor() {
        return this.propertiesConfiguration.getString(powerOffColor);
    }

    public String getOneModelBytes() {
        return this.propertiesConfiguration.getString(oneModelBytes);
    }

    public String getOneModelColor() {
        return this.propertiesConfiguration.getString(oneModelColor);
    }

    public String getFreedomModelBytes() {
        return this.propertiesConfiguration.getString(freedomModelBytes);
    }

    public String getFreedomModelColor() {
        return this.propertiesConfiguration.getString(freedomModelColor);
    }

    public String getNoneModelBytes() {
        return this.propertiesConfiguration.getString(noneModelBytes);
    }

    public String getNoneModelColor() {
        return this.propertiesConfiguration.getString(noneModelColor);
    }

    public static void main(String[] args) {
        try {
            File file = new File(configFileName);
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("创建配置文件时发生错误",e);
        }

        final String powerOnBytes = "powerOnBytes";
        final String powerOnColor = "powerOnColor";
        final String powerOffBytes = "powerOffBytes";
        final String powerOffColor = "powerOffColor";
        final String oneModelBytes = "oneModelBytes";
        final String oneModelColor = "oneModelColor";
        final String freedomModelBytes = "freedomModelBytes";
        final String freedomModelColor = "freedomModelColor";
        final String noneModelBytes = "noneModelBytes";
        final String noneModelColor = "noneModelColor";

        try {
            final DeviceConfigurator instance = DeviceConfigurator.getInstance();
            final PropertiesConfiguration propertiesConfiguration = instance.propertiesConfiguration;
            propertiesConfiguration.addProperty(powerOnBytes,"00 00 00 00");
            propertiesConfiguration.addProperty(powerOnColor,"#aabbcc");

            propertiesConfiguration.addProperty(powerOffBytes,"11 22 33 44");
            propertiesConfiguration.addProperty(powerOffColor,"#aabb00");

            propertiesConfiguration.addProperty(oneModelBytes,"11 22 33 55");
            propertiesConfiguration.addProperty(oneModelColor,"#aabb11");

            propertiesConfiguration.addProperty(freedomModelBytes,"46 BE 23 F2");
            propertiesConfiguration.addProperty(freedomModelColor,"#aabb22");

            propertiesConfiguration.addProperty(noneModelBytes,"46 BE 23 F3");
            propertiesConfiguration.addProperty(noneModelColor,"#aabb33");

            propertiesConfiguration.setEncoding("utf-8");
            propertiesConfiguration.save(configFileName);
        } catch (ConfigurationException e) {
            throw new RuntimeException("写入配置文件时发生错误",e);
        }
    }
}
