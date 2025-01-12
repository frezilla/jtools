package eu.frezilla.tools.textdevice;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SwingDevice {
    
    private final JFrame frame;
    private final JTextArea jTextArea;
    
    public SwingDevice() {
        frame = new JFrame("SwingDevice");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jTextArea = new JTextArea(20, 20);
        
        frame.setContentPane(createContentPane());
        frame.pack();
        frame.setVisible(true);
    }
    
    private JPanel createContentPane() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 1.0; gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.BOTH;
        
        JScrollPane scrollableTextArea = new JScrollPane(jTextArea);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        panel.add(scrollableTextArea, gbc);
        
        return panel;
    }
    
    
}
