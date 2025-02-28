package eu.frezilla.textformat.sequences.ansi;

public enum DisplayAttributes {
    RESET(0);
    
    private final DisplayAttribute displayAttribute;

    private DisplayAttributes(int n) {
        this.displayAttribute = new DisplayAttribute(n);
    }
    
}
