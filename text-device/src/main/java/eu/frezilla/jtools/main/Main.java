package eu.frezilla.jtools.main;

import eu.frezilla.jtools.textdevice.TextDevice;
import eu.frezilla.jtools.textdevice.TextDeviceException;
import eu.frezilla.jtools.textdevice.swing.StyledTextDevice;
import eu.frezilla.jtools.textdevice.swing.TextDeviceConfig;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) throws TextDeviceException {
        JFrame jFrame = new JFrame("Test");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StyledTextDevice swingDevice = new StyledTextDevice(TextDeviceConfig.getDefault());
        
        SwingUtilities.invokeLater(() -> {
            
            jFrame.getContentPane().add(swingDevice);
            jFrame.pack();
            jFrame.setVisible(true);
        });
        
        TextDevice textDevice = swingDevice.toTextDevice();
        textDevice.print("Bonjour");
        textDevice.print("Toto");
        //System.out.println("Texte saisie " + textDevice.readLine(">"));
        //System.out.println("Texte saisie " + textDevice.readLine(">"));
        System.out.println("Texte saisie " + new String(textDevice.readPassword(">")));
    }
    
}
