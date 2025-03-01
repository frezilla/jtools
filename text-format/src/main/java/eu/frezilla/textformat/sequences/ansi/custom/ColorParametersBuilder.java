package eu.frezilla.textformat.sequences.ansi.custom;

public final class ColorParametersBuilder {
    
    private ColorParametersBuilder() { }
    
    public static ColorParameters newInstance(int n) {
        return new Color256(n);
    }
    
    public static ColorParameters newInstance(int r, int g, int b) {
        return new TrueColor(r, g, b);
    }
    
}
