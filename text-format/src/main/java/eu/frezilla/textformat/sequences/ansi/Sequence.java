package eu.frezilla.textformat.sequences.ansi;

import java.util.ArrayList;
import java.util.List;

public class Sequence {

    private static final String SEQUENCE_END = "m";
    private static final String SEQUENCE_START = "\033[";

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

    public String stringValue() {
        if (attributes.isEmpty()) {
            throw new IllegalStateException("La liste des attributs n'est pas renseignée");
        }

        StringBuilder sb = new StringBuilder(SEQUENCE_START);
        for (DisplayAttribute attribute : attributes) {
            sb.append(attribute.stringValue());
        }
        sb.append(SEQUENCE_END);
        return sb.toString();
    }
}
