package eu.frezilla.jtools.textdevice;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;

public class SwingDevice extends JComponent implements TextDevice {
    
    private final SwingDeviceDocumentFilter documentFilter;
    private boolean enterIsPressed;
    private final JTextArea jTextArea;
    
    public SwingDevice() {
        this(20, 80);
    }
    
    public SwingDevice(int rows, int columns) {
        documentFilter = new SwingDeviceDocumentFilter();
        
        enterIsPressed = false;
        
        jTextArea = new JTextArea(rows, columns);
        jTextArea.setEditable(false);
        ((AbstractDocument) jTextArea.getDocument()).setDocumentFilter(documentFilter);
        jTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {}

            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    enterIsPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {}
        });
        
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
            int offset = documentFilter.getPromptPosition();
            documentFilter.setPromptPosition(offset + text.length());
            jTextArea.setCaretPosition(jTextArea.getDocument().getLength());
        });
        
        return this;
    }

    @Override
    public Reader reader() throws TextDeviceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String readLine(String fmt, Object... params) throws TextDeviceException {
        String inputString = "";
        
        printf(fmt, params);
        
        TextDeviceException exception = null;
        try {
            jTextArea.setEditable(true);
            
            while (!enterIsPressed) {
                TimeUnit.MILLISECONDS.sleep(10);
            }
            
            enterIsPressed = false;
            
            int offset = documentFilter.getPromptPosition();
            int caretPosition = jTextArea.getCaretPosition();
            int length = caretPosition - 1 - offset;
            
            documentFilter.setPromptPosition(caretPosition);
            
            inputString = jTextArea.getDocument().getText(offset, length);
            
            jTextArea.setEditable(false);
        } catch (BadLocationException | InterruptedException e) {
            exception = new TextDeviceException("Erreur à la lecture de l'entrée saisie", e);
        } finally {
            jTextArea.setEditable(false);
        }
        
        if (exception != null) throw exception;
            
        return inputString;
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
