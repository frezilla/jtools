package eu.frezilla.textformat.sequences.ansi.custom;

abstract class AbstractCP implements ColorParameters {
    
    protected final int checkIsByte(int b, String msg) {        
        if (b < 0 || b > 255) throw new IllegalArgumentException(msg);
        return b;
    }
    
}
