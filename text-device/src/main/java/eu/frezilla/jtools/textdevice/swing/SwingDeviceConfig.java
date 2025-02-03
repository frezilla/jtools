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

    public SwingDeviceConfig(Color backgroundColor, Color caretColor, int columns, Color foreGroundColor, int rows) {
        this.backgroundColor = backgroundColor;
        this.caretColor = caretColor;
        this.columns = columns;
        this.foreGroundColor = foreGroundColor;
        this.rows = rows;
    }
    
    
    public static final class Builder {
        
        public static SwingDeviceConfig buildDefault() {
            return new SwingDeviceConfig(
            );
        }
        
        public SwingDeviceConfig build() {
            return new SwingDeviceConfig();
        }
        
    }
    
}
