package eu.frezilla.textformat.sequences.ansi;

import java.util.ArrayList;
import java.util.List;

public final class Sequence {

    static final String SEQUENCE_END = "m";
    static final String SEQUENCE_SEPARATOR = ";";
    static final String SEQUENCE_START = "\033[";

    private final List<DisplayAttribute> attributes;

    public Sequence() {
        attributes = new ArrayList<>();
    }

    public Sequence add(DisplayAttribute attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("L'attribut est null");
        }
        
        attributes.add(attribute);
        return this;
    }
    
    public Sequence add(DisplayAttributes attribueEnum) {
        if (attribueEnum == null) {
            throw new IllegalArgumentException("L'attribut est null");
        }
        
        return add(attribueEnum.getDisplayAttribute());        
    }
    
    public static Sequence fromString(String s) throws AnsiParseException {
        return new StringParser().parse(s);
    }

    public String stringValue() {
        if (attributes.isEmpty()) {
            throw new IllegalStateException("La liste des attributs n'est pas renseignée");
        }

        StringBuilder sb = new StringBuilder(SEQUENCE_START);
        for (DisplayAttribute attribute : attributes) {
            sb.append(attribute.stringValue());
            sb.append(SEQUENCE_SEPARATOR);
        }
        String s = sb.toString();
        if (s.endsWith(SEQUENCE_SEPARATOR)) {
            sb = new StringBuilder(s.substring(0, s.length() - SEQUENCE_SEPARATOR.length()));
        }
        sb.append(SEQUENCE_END);
        return sb.toString();
    }
}
