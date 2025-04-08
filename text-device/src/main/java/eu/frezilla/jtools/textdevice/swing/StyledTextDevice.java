package eu.frezilla.jtools.textdevice.swing;

import eu.frezilla.jtools.textdevice.TextDevice;
import eu.frezilla.jtools.textdevice.TextDeviceException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class StyledTextDevice extends JComponent implements TextDevice {
    
    private final JTextPane jTextPane;
    
    public StyledTextDevice(TextDeviceConfig config) {
        jTextPane = new JTextPane();
        jTextPane.setBackground(config.getBackgroundColor());
        jTextPane.setForeground(config.getForegroundColor());
        jTextPane.setCaretColor(config.getCaretColor());
        jTextPane.setCaretPosition(0);
        jTextPane.setEditable(false);
        
        createGUI();
    }
    
    private void createGUI() {
        super.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 1.0; gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.BOTH;

        JScrollPane scrollableTextArea = new JScrollPane(jTextPane);
        scrollableTextArea.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        
        super.add(scrollableTextArea, gbc);
    }

    @Override
    public TextDevice printf(String fmt, Object... params) throws TextDeviceException {
        String text = String.format(fmt, params);
        
        StyledDocument doc = jTextPane.getStyledDocument();
        try {
            doc.insertString(
                    doc.getLength(), 
                    text, 
                    StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE)
            );
        } catch (BadLocationException e) {
            
        }
        
        return this;
    }

    @Override
    public String readLine(String fmt, Object... params) throws TextDeviceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public char[] readPassword(String fmt, Object... params) throws TextDeviceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public TextDevice toTextDevice() {
        return this;
    }
    
    
}
