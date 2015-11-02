package org.pan;

/**
 * Created by Administrator on 2015/11/3.
 */
public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DeviceUI.createAndShowGUI();
            }
        });
    }
}
