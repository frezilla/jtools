package eu.frezilla.textformat.sequences.ansi;

import eu.frezilla.textformat.sequences.ansi.custom.ColorParameters;
import java.util.Arrays;
import java.util.List;

public final class DisplayAttribute {
    
    private static final List<Integer> AVALAIBLE_CODES = Arrays.asList(
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 
        21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
        39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 58,
        59, 60, 61, 62, 63, 64, 65, 73, 74, 75, 90, 91, 92, 93, 94, 95, 96, 97,
        100, 101, 102, 103, 104, 105, 106, 107
    );
    
    private static final List<Integer> AVALAIBLE_CODES_WITH_PARAMETERS = Arrays.asList(38, 48, 58);
    
    private final int code;
    private final ColorParameters colorParameters;
    private final String name;
    private final String note;

    public DisplayAttribute(int code) {
        this(code, null, null, null);
    }

    public DisplayAttribute(int code, String name) {
        this(code, null, name, null);
    }
    
    public DisplayAttribute(int code, String name, String note) {
        this(code, null, name, note);
    }
    
    public DisplayAttribute(int code, ColorParameters colorParameters) {
        this(code, colorParameters, null, null);
    }
    
    public DisplayAttribute(int code, ColorParameters colorParameters, String name) {
        this(code, colorParameters, name, null);
    }
    
    public DisplayAttribute(int code, ColorParameters colorParameters, String name, String note) {
        this.code = checkCode(code, "Le code de l'attribut n'est pas supporté");
        this.colorParameters = checkParameters(code, colorParameters, "Les paramètres ne sont pas valides pour ce code d'attribut");
        this.name = name;
        this.note = note;
    }
    
    private int checkCode(int code, String msg) {
        if (!isAvalaiblesCodes(code)) throw new IllegalArgumentException(msg);
        return code;
    }
    
    private ColorParameters checkParameters(int code, ColorParameters colorParameters, String msg) {
        if ((colorParameters == null) && isAvailablesCodesWithParameters(code)) { 
            throw new IllegalArgumentException(msg);
        }
        return colorParameters;
    }
    
    public int getCode() {
        return code;
    }
    
    public ColorParameters getColorParameters() {
        return colorParameters;
    }
    
    public String getName() {
        return name;
    }
    
    public String getNote() {
        return note;
    }
    
    public static boolean isAvalaiblesCodes(int code) {
        return (AVALAIBLE_CODES.stream().filter(i -> i == code).count() == 1L);
    }
    
    public static boolean isAvailablesCodesWithParameters(int code) {
        return (AVALAIBLE_CODES_WITH_PARAMETERS.stream().filter(i -> i == code).count() == 1L);
    }
    
    public String stringValue() {
        if (colorParameters == null) {
            return String.format("%d", code);
        } else {
            return String.format("%d;%s", code, colorParameters.stringValue());
        }
    }

}
