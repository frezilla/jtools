package eu.frezilla.jtools.main;

import eu.frezilla.jtools.textdevice.SwingDevice;
import eu.frezilla.jtools.textdevice.TextDevice;
import eu.frezilla.jtools.textdevice.TextDeviceException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) throws TextDeviceException {
        JFrame jFrame = new JFrame("Test");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SwingDevice swingDevice = new SwingDevice();
        
        SwingUtilities.invokeLater(() -> {
            jFrame.getContentPane().add(swingDevice);
            jFrame.pack();
            jFrame.setVisible(true);
        });
        
        TextDevice textDevice = swingDevice.toTextDevice();
        textDevice.println("Bonjour");
        textDevice.println("Comment ça va ?");
    }
    
}
