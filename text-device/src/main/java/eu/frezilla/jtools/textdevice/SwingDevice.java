package eu.frezilla.jtools.textdevice;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.PrintWriter;
import java.io.Reader;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;

public class SwingDevice extends JComponent implements TextDevice {
    
    private final SwingDeviceDocumentFilter documentFilter;
    private final JTextArea jTextArea;
    
    public SwingDevice() {
        this(20, 80);
    }
    
    public SwingDevice(int rows, int columns) {
        documentFilter = new SwingDeviceDocumentFilter();
        jTextArea = new JTextArea(rows, columns);
        ((AbstractDocument) jTextArea.getDocument()).setDocumentFilter(documentFilter);
        
        createGUI();
    }
    
    private void createGUI() {
        SwingUtilities.invokeLater(() -> {
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 1.0; gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.BOTH;
        
            JScrollPane scrollableTextArea = new JScrollPane(jTextArea);
            scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
            this.add(scrollableTextArea, gbc);
        });
    }

    @Override
    public TextDevice printf(String fmt, Object... params) throws TextDeviceException {
        String text = String.format(fmt, params);
        
        SwingUtilities.invokeLater(() -> {
            jTextArea.append(text);
            int position = jTextArea.getDocument().getStartPosition().getOffset();
            documentFilter.setPromptPosition(position + text.length());
        });
        
        return this;
    }

    @Override
    public Reader reader() throws TextDeviceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    @Override
    public PrintWriter writer() throws TextDeviceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
