package eu.frezilla.textformat.sequences.ansi.custom;

public enum ColorPrefixEnum {
    COLOR256("5"),
    TRUECOLOR("2");
    
    private final String prefix;
    
    private ColorPrefixEnum(String prefix) {
        this.prefix = prefix;
    }
    
    public String getPrefix() {
        return prefix;
    }
    
}
