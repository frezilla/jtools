package eu.frezilla.jtools.textdevice.swing;

import eu.frezilla.jtools.textdevice.TextDevice;
import eu.frezilla.jtools.textdevice.TextDeviceException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;

public class PlainTextSwingDevice extends JComponent implements TextDevice {
    
    private final JTextArea jTextArea;
    private final PlainTextEngine engine;
    
    public PlainTextSwingDevice() {
        this(25, 80);
    }
    
    public PlainTextSwingDevice(int rows, int columns) {
        this(SwingDeviceConfig.getBuilder().rows(rows).columns(columns).build());
    }
    
    public PlainTextSwingDevice(SwingDeviceConfig config) {
        jTextArea = new JTextArea(config.getRows(), config.getColumns());
        jTextArea.setBackground(config.getBackgroundColor());
        jTextArea.setForeground(config.getForegroundColor());
        jTextArea.setCaretColor(config.getCaretColor());
        jTextArea.setCaretPosition(0);
        jTextArea.setEditable(false);
        
        engine = new PlainTextEngine(jTextArea);
        
        ((AbstractDocument) jTextArea.getDocument()).setDocumentFilter(engine);
        jTextArea.addKeyListener(engine);
        
        createGUI();
    }
    
    private void createGUI() {
        super.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 1.0; gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.BOTH;

        JScrollPane scrollableTextArea = new JScrollPane(jTextArea);
        scrollableTextArea.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);

        component.add(scrollableTextArea, gbc);
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