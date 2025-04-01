package eu.frezilla.jtools.textdevice.swing;

import java.awt.Color;
import java.util.Objects;

public final class PlainTextSwingDeviceConfig {
    
    private static final PlainTextSwingDeviceConfig DEFAULT = new Builder().build();
        
    private final Color backgroundColor;
    private final Color caretColor;
    private final int columns;
    private final Color foregroundColor;
    private final int rows;

    private PlainTextSwingDeviceConfig(Color backgroundColor, Color caretColor, int columns, Color foregroundColor, int rows) {
        this.backgroundColor = Objects.requireNonNull(backgroundColor, "backgroundColor is null");
        this.caretColor = Objects.requireNonNull(caretColor, "carterColor is null");
        this.columns = columns;
        this.foregroundColor = Objects.requireNonNull(foregroundColor, "foregroundColor is null");
        this.rows = rows;
    }
    
    public static Builder getBuilder() {
        return new Builder();
    }
    
    public static PlainTextSwingDeviceConfig getDefault() {
        return DEFAULT;
    }
    
    public Color getBackgroundColor() { return backgroundColor; }
    
    public Color getCaretColor() { return caretColor; }
    
    public int getColumns() { return columns; }
    
    public Color getForegroundColor() { return foregroundColor; }
    
    public int getRows()  { return rows; }
    
    public static final class Builder {
        
        private Color backgroundColor = Color.BLACK;
        private Color caretColor = Color.GREEN;
        private int columns = 80;
        private Color foregroundColor = Color.GREEN;
        private int rows = 25;
        
        public Builder backgroundColor(Color c) { this.backgroundColor = c; return this; }
        public Builder caretColor(Color c) { this.caretColor = c; return this; }
        public Builder columns(int columns) { this.columns = columns; return this; }
        public Builder foregroundColor(Color c) { this.foregroundColor = c; return this; }
        public Builder rows(int rows) { this.rows = rows; return this; }
        
        public PlainTextSwingDeviceConfig build() {
            return new PlainTextSwingDeviceConfig(backgroundColor, caretColor, columns, foregroundColor, rows);
        }
    }
}
