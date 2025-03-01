package eu.frezilla.textformat.sequences.ansi.custom;

final class TrueColor extends AbstractCP {
    
    private final int r;
    private final int g;
    private final int b;
    
    public TrueColor(int r, int g, int b) {
        this.r = checkIsByte(r, "La valeur de la composante rouge n'est pas valide");
        this.g = checkIsByte(g, "La valeur de la composante verte n'est pas valide");
        this.b = checkIsByte(b, "La valeur de la composante bleue n'est pas valide");
    }

    @Override
    public String stringValue() {
        return String.format("2;%d;%d;%d", r, g, b);
    }
    
}
