package eu.frezilla.jtools.textdevice.swing;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

final class SDDocumentFilter extends DocumentFilter {
    
    private int currentPosition;
    
    public SDDocumentFilter() {
        this.currentPosition = 0;
    }
    
    @Override
    public void insertString(final FilterBypass fb, final int offset, final String string, final AttributeSet attr) throws BadLocationException {
        if (offset >= currentPosition) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void remove(final FilterBypass fb, final int offset, final int length) throws BadLocationException {
        if (offset >= currentPosition) {
            super.remove(fb, offset, length);
        }
    }

    @Override
    public void replace(final FilterBypass fb, final int offset, final int length, final String text, final AttributeSet attrs) throws BadLocationException {
        if (offset >= currentPosition) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
    
    public void setPromptPosition(int promptPosition) {
        if (promptPosition < 0) throw new IllegalArgumentException("La valeur du paramètre promptPosition n'est pas autorisée");
        this.currentPosition = promptPosition;
    }
    
}
