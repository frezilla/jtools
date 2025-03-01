package eu.frezilla.textformat.sequences.ansi.custom;

final class Color256 extends AbstractCP {

    private final int n;
    
    public Color256(int n) {
        this.n = checkIsByte(n, "La valeur de la couleur n'est pas valide");
    }

    @Override
    public String stringValue() {
        return String.format("5;%d", n);
    }
    
    
}

