package eu.frezilla.textformat.sequences.ansi;

import static eu.frezilla.textformat.sequences.ansi.DisplayAttribute.isAvailablesCodesWithParameters;
import static eu.frezilla.textformat.sequences.ansi.DisplayAttribute.isAvalaiblesCodes;
import static eu.frezilla.textformat.sequences.ansi.DisplayAttributes.getByCode;
import static eu.frezilla.textformat.sequences.ansi.Sequence.SEQUENCE_END;
import static eu.frezilla.textformat.sequences.ansi.Sequence.SEQUENCE_SEPARATOR;
import static eu.frezilla.textformat.sequences.ansi.Sequence.SEQUENCE_START;
import eu.frezilla.textformat.sequences.ansi.custom.ColorParameters;
import eu.frezilla.textformat.sequences.ansi.custom.ColorParametersBuilder;
import static eu.frezilla.textformat.sequences.ansi.custom.ColorPrefixEnum.COLOR256;
import static eu.frezilla.textformat.sequences.ansi.custom.ColorPrefixEnum.TRUECOLOR;
import java.util.StringTokenizer;
import org.apache.commons.lang3.tuple.Pair;

final class StringParser {
    
   private Integer checkColor(String s, String msg, int offset) throws AnsiParseException {
        try {
            Integer i = Integer.valueOf(s);
            if (i.compareTo(0) < 0 || i.compareTo(255) > 0) throw new AnsiParseException(msg, offset);
            return i;
        } catch (NumberFormatException e) {
            throw new AnsiParseException(msg, offset);
        }
    }
    
    private String checkToken(String s, String msg, int offset) throws AnsiParseException {
        if (s == null) throw new AnsiParseException(msg, offset);
        else return s;
    }

    public Sequence parse(String string) throws AnsiParseException {
        if (string == null) {
            throw new IllegalArgumentException("La chaine de caractères n'est pas valide");
        }
        int offset = 0;

        if (string.startsWith(SEQUENCE_START) && string.endsWith(SEQUENCE_END)) {
            int indexStart = SEQUENCE_START.length();
            int indexEnd = string.indexOf(SEQUENCE_END);

            Sequence sequence = new Sequence();
            offset += indexStart;
            
            StringTokenizer token = new StringTokenizer(string.substring(indexStart, indexEnd), SEQUENCE_SEPARATOR);
            while (token.hasMoreTokens()) {
                String codeStr = token.nextToken();
                
                DisplayAttribute displayAttribute;
                
                int code = Integer.parseInt(codeStr);
                if (isAvailablesCodesWithParameters(code)) {
                    Pair<ColorParameters, Integer> pair = parseDisplayAttributeWithParameters(offset, token);
                    displayAttribute = new DisplayAttribute(code, pair.getLeft(), "Couleur personalisée");
                    offset = pair.getRight();
                } else if (isAvalaiblesCodes(code)) {
                    displayAttribute = getByCode(code).getDisplayAttribute();
                    offset += codeStr.length() + SEQUENCE_SEPARATOR.length();
                } else {
                    throw new AnsiParseException("", offset);
                }
                sequence.add(displayAttribute);
                
            }
            
            return sequence;
        } else {
            throw new AnsiParseException("La chaine de caractères doit commencer par " + SEQUENCE_START + " et terminer par " + SEQUENCE_END , 0);
        }
    }

    private Pair<ColorParameters, Integer> parseDisplayAttributeWithParameters(int offset, StringTokenizer token) throws AnsiParseException {
        String prefix = checkToken(token.nextToken(), "Le type de couleur est absent", offset);
        offset += prefix.length();
                    
        if (COLOR256.getPrefix().equals(prefix)) {
            String s = checkToken(token.nextToken(), "La valeur de la couleur est absente", offset);
            Integer color = checkColor(s, "La valeur de la couleur n'est pas valide", offset);
            return Pair.of(ColorParametersBuilder.newInstance(color), offset + s.length() + SEQUENCE_SEPARATOR.length());
        } else if (TRUECOLOR.getPrefix().equals(prefix)) {
            String s = checkToken(token.nextToken(), "La valeur de la composante rouge est absente", offset);
            Integer r = checkColor(s, "La valeur de la composante rouge n'est pas valide", offset);
            offset += s.length() + SEQUENCE_SEPARATOR.length();
            
            s = checkToken(token.nextToken(), "La valeur de la composante verte est absente", offset);
            Integer g = checkColor(s, "La valeur de la composante verte n'est pas valide", offset);
            offset += s.length() + SEQUENCE_SEPARATOR.length();
            
            s = checkToken(token.nextToken(), "La valeur de la composante bleue est absente", offset);
            Integer b = checkColor(s, "La valeur de la composante bleue n'est pas valide", offset);
            offset += s.length() + SEQUENCE_SEPARATOR.length();
            
            return Pair.of(ColorParametersBuilder.newInstance(r, g, b), offset);
        } else {
            throw new AnsiParseException("La valeur du type de couleur n'est pas valide", offset);
        }
    }
}
