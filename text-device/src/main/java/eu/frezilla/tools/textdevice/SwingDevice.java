package eu.frezilla.tools.textdevice;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingDevice {
    
    private final JFrame frame;
    
    public SwingDevice() {
        frame = new JFrame("SwingDevice");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setContentPane(createContentPane());
        frame.pack();
        frame.setVisible(true);
    }
    
    private JPanel createContentPane() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 1.0; gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.BOTH;
        
        return panel;
    }
    
    
}
