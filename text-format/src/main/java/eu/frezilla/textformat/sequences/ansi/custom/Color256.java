package eu.frezilla.textformat.sequences.ansi.custom;

import static eu.frezilla.textformat.sequences.ansi.custom.ColorPrefixEnum.COLOR256;

final class Color256 extends AbstractCP {

    public static final String PREFIX = "5";
    
    private final int n;
    
    public Color256(int n) {
        this.n = checkIsByte(n, "La valeur de la couleur n'est pas valide");
    }

    @Override
    public String stringValue() {
        return String.format("%s;%d", COLOR256.getPrefix(), n);
    }
    
}

