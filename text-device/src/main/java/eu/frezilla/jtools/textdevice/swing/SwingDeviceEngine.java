package eu.frezilla.jtools.textdevice.swing;

import eu.frezilla.jtools.textdevice.TextDeviceException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import lombok.NonNull;

final class SwingDeviceEngine extends DocumentFilter implements KeyListener {
    
    private final char echoPassword;
    private boolean enterPressed;
    private final JTextArea jTextArea;
    private int offset;
    private StringBuilder passwordRecord;
    private boolean passwordMode;
    
    public SwingDeviceEngine(JTextArea jTextArea) {
        this(jTextArea, '#');
    }
    
    public SwingDeviceEngine(JTextArea jTextArea, char echoPassword) {
        this.echoPassword = echoPassword;
        this.enterPressed = false;
        this.jTextArea = Objects.requireNonNull(jTextArea);
        if (offset < 0) throw new IllegalArgumentException("La valeur du paramètre offset n'est pas autorisée");
        this.offset = 0;        
        this.passwordMode = false;
    }
    
    @Override
    public void insertString(final FilterBypass fb, final int offset, final String string, final AttributeSet attr) throws BadLocationException {
        if (offset >= this.offset) {
            super.insertString(fb, offset, string, attr);
        }
    }    

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.enterPressed = true;            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {
        if (passwordMode && !enterPressed) {
            passwordRecord.append(e.getKeyChar());
            
            SwingUtilities.invokeLater(() -> {
                jTextArea.append(String.valueOf(echoPassword));
                try {
                    jTextArea.getDocument().remove(jTextArea.getCaretPosition() - 2, 1);
                } catch (BadLocationException ex) {}
            });
            
        }
    }
    
    public String read() throws TextDeviceException {
        String inputString = "";        
        TextDeviceException exception = null;
        
        try {
            jTextArea.setEditable(true);

            while (!enterPressed) TimeUnit.MILLISECONDS.sleep(10);
            
            int caretPosition = jTextArea.getCaretPosition();
            int length = (caretPosition - 1) - offset;
            inputString = jTextArea.getDocument().getText(offset, length);
            
            offset = caretPosition;
        } catch (BadLocationException | InterruptedException e) {
            exception = new TextDeviceException("Erreur à la lecture de la saisie de l'utilisateur", e);
        } finally {
            jTextArea.setEditable(false);
            enterPressed = false;
        }
        
        if (exception != null) throw exception;
        
        return inputString;
    }
    
    public String readPassword() {
        String inputString = "";
        
        try {
            jTextArea.setEditable(true);
            passwordMode = true;
            passwordRecord = new StringBuilder();
            
            while (!enterPressed) TimeUnit.MILLISECONDS.sleep(10);
        
            inputString = passwordRecord.toString();
        } catch (InterruptedException e) {       
            
        } finally {
            jTextArea.setEditable(false);
            enterPressed = false;
            passwordMode = false;
        }
        
        return inputString;
    }
    
    @Override
    public void remove(final FilterBypass fb, final int offset, final int length) throws BadLocationException {
        if (offset >= this.offset) {
            super.remove(fb, offset, length);
        }
    }    
    
    @Override
    public void replace(final FilterBypass fb, final int offset, final int length, final String text, final AttributeSet attrs) throws BadLocationException {
        if (offset >= this.offset) {
            super.replace(fb, offset, length, text, attrs);
        }
    }    
    
    public void write(@NonNull String text) {
        SwingUtilities.invokeLater(() -> {
            jTextArea.append(text);
            offset = offset + text.length();
            jTextArea.setCaretPosition(offset);
        });
    }
}
