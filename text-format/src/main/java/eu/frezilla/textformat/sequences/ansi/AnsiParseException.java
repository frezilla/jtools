package eu.frezilla.textformat.sequences.ansi;

import java.text.ParseException;

public class AnsiParseException extends ParseException {
    
    public AnsiParseException(String s, int errorOffset) {
        super(s, errorOffset);
    }
    
}
