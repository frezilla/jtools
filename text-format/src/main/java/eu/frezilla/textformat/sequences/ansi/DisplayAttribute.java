package eu.frezilla.textformat.sequences.ansi;

import java.util.Objects;

public final class DisplayAttribute {
    
    private final ColorMode colorMode;
    private final String description;
    
    private final Integer n;
    private final Integer r;
    private final Integer g;
    private final Integer b;

    DisplayAttribute(int n) {
        this(null, n);
    }

    DisplayAttribute(ColorMode colorMode, int...parameters) {
        this(colorMode, null, parameters);
    }
    
    DisplayAttribute(ColorMode colorMode, String description, int...parameters) {
        if (parameters == null) throw new IllegalArgumentException();
        
        if (colorMode == null || Objects.equals(colorMode, ColorMode.COLOR_256)) {
            if (parameters.length != 1) throw new IllegalArgumentException();
            
            if (colorMode == null) {
                
            } else if (parameters[0] < 0 || parameters[0] > 255) throw new IllegalArgumentException("La valeur du paramètre doit appartenir à l'intervace [0;255]");
            
            this.n = parameters[0];
            this.r = null;
            this.g = null;
            this.b = null;
            
        } else if (Objects.equals(colorMode, ColorMode.TRUE_COLOR)) {
            if (parameters.length != 3) throw new IllegalArgumentException();
            
            this.n 
            this.r = parameters[0];
            this.g = parameters[1];
            this.b = parameters[2];
        }
        
        this.colorMode = colorMode;
        this.description = description;
    }
    
    public String stringValue() {
        return String.format("%d", n);
    }
    
}
