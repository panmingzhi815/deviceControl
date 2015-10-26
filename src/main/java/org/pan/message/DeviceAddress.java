package org.pan.message;

/**
 * Created by xiaopan on 2015/10/14 0014.
 */
public class DeviceAddress {
    public enum LinkType{
        TCP,COM
    }

    private LinkType linkType;
    private String linkAddress;

    public DeviceAddress(String linkType, String linkAddress) {
        if(linkType.equalsIgnoreCase("com")){
            this.linkType = LinkType.COM;
        }else{
            this.linkType = LinkType.TCP;
        }
        this.linkAddress = linkAddress;
    }

    public DeviceAddress(LinkType linkType, String linkAddress) {
        this.linkType = linkType;
        this.linkAddress = linkAddress;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

}
