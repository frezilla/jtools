package eu.frezilla.jtools.textdevice.swing;

import java.awt.Color;
import lombok.Getter;

@Getter
public final class SwingDeviceConfig {
    
    private final Color backgroundColor;
    private final Color caretColor;
    private final int columns;
    private final Color foreGroundColor;
    private final int rows;

    private SwingDeviceConfig(Color backgroundColor, Color caretColor, int columns, Color foreGroundColor, int rows) {
        this.backgroundColor = backgroundColor;
        this.caretColor = caretColor;
        this.columns = columns;
        this.foreGroundColor = foreGroundColor;
        this.rows = rows;
    }
    
    public static Builder getBuilder() {
        return new Builder();
    }
    
    public static final class Builder {
        
        private Color backgroundColor = Color.BLACK;
        private Color caretColor = Color.GREEN;
        private int columns = 80;
        private Color foreGroundColor = Color.GREEN;
        private int rows = 25;
        
        public Builder backGroundColor(Color c) { this.backgroundColor = c; return this; }
        public Builder caretColor(Color c) { this.caretColor = c; return this; }
        public Builder columns(int columns) { this.columns = columns; return this; }
        public Builder foreGroundColor(Color c) { this.foreGroundColor = c; return this; }
        public Builder rows(int rows) { this.rows = rows; return this; }
        
        public SwingDeviceConfig build() {
            return new SwingDeviceConfig(backgroundColor, caretColor, columns, foreGroundColor, rows);
        }
    }
}
