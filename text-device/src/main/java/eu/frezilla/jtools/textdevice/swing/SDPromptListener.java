package eu.frezilla.jtools.textdevice.swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

final class SDPromptListener implements KeyListener {
    
    private volatile boolean enterPressed;
    
    public SDPromptListener() {
        this.enterPressed = false;
    }
    
    public boolean enterPressed() {
        return enterPressed;
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
    public void keyTyped(KeyEvent e) {}
    
    public void resetEnterPressed() {
        enterPressed = false;
    }    
}
