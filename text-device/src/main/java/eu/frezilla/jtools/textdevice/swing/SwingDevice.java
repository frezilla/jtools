package eu.frezilla.jtools.textdevice.swing;

import eu.frezilla.jtools.textdevice.TextDevice;
import eu.frezilla.jtools.textdevice.TextDeviceException;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;

public class SwingDevice extends JComponent implements TextDevice {
    
    private final JTextArea jTextArea;
    private final SwingDeviceEngine engine;
    
    public SwingDevice() {
        this(25, 80);
    }
    
    public SwingDevice(int rows, int columns) {
        this(SwingDeviceConfig.getBuilder().rows(rows).columns(columns).build());
    }
    
    public SwingDevice(SwingDeviceConfig config) {
        jTextArea = new JTextArea(config.getRows(), config.getColumns());
        jTextArea.setBackground(config.getBackgroundColor());
        jTextArea.setForeground(config.getForeGroundColor());
        jTextArea.setCaretColor(config.getCaretColor());
        jTextArea.setCaretPosition(0);
        jTextArea.setEditable(false);
        
        engine = new SwingDeviceEngine(jTextArea);
        
        ((AbstractDocument) jTextArea.getDocument()).setDocumentFilter(engine);
        jTextArea.addKeyListener(engine);
        
        createGUI();
    }
    
    private void createGUI() {
        super.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 1.0; gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.BOTH;

        JScrollPane scrollableTextArea = new JScrollPane(jTextArea);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        super.add(scrollableTextArea, gbc);
    }

    @Override
    public TextDevice printf(String fmt, Object... params) throws TextDeviceException {
        String text = String.format(fmt, params);
        
        SwingUtilities.invokeLater(() -> {
            engine.write(text);
        });
        
        return this;
    }

    @Override
    public String readLine(String fmt, Object... params) throws TextDeviceException {
        printf(fmt, params);
        return engine.read();
    }

    @Override
    public char[] readPassword(String fmt, Object... params) throws TextDeviceException {
        printf(fmt, params);
        return engine.readPassword().toCharArray();
    }
    
    public TextDevice toTextDevice() {
        return this;
    }

}