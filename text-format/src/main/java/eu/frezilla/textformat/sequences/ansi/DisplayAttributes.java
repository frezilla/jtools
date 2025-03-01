package eu.frezilla.textformat.sequences.ansi;

import eu.frezilla.textformat.sequences.ansi.custom.ColorParameters;
import lombok.Getter;

public enum DisplayAttributes {
    RESET(0, null, "Reset or normal", "All attributes become turned off"),
    BOLD(1, null, "Bold or increased intensity", "As with faint, the color change is a PC (SCO / CGA) invention");
    
    @Getter private final DisplayAttribute displayAttribute;

    private DisplayAttributes(int n, ColorParameters colorParameters, String name, String note) {
        this.displayAttribute = new DisplayAttribute(n, colorParameters, name, note);
    }
    
}
