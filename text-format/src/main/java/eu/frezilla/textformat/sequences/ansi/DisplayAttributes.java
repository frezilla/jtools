package eu.frezilla.textformat.sequences.ansi;

import eu.frezilla.textformat.sequences.ansi.custom.ColorParameters;
import java.util.Objects;

public enum DisplayAttributes {
    RESET(0, null, "Reset or normal", "All attributes become turned off."),
    BOLD(1, null, "Bold or increased intensity", "As with faint, the color change is a PC (SCO / CGA) invention."),
    FAINT(2, null, "Faint, decreased intensity, or dim", "May be implemented as a light font weight like bold."),
    ITALIC(3, null, "Italic", "Not widely supported. Sometimes treated as inverse or blink."),
    UNDERLINE(4, null, "Underline", "Style extensions exist for Kitty, VTE, mintty, iTerm2 and Konsole."),
    SLOW_BLINK(5, null, "Slow blink", "Sets blinking to less than 150 times per minute."),
    RAPID_BLINK(6, null, "Rapid blik", "MS-DOS ANSI.SYS, 150+ per minute; not widely supported."),
    INVERT(7, null, "Reverse video or invert", "Swap foreground and background colors; inconsistent emulation."),
    CONCEAL(8, null, "Conceal or histe", "Not widely supported."),
    CROSSED_OUT(9, null, "Crossed-out or strike", "Characters legible but marked as if for deletion. Not supported in Terminal.app."),
    PRIMARY_FONT(10, null, "Primary (default) font", ""),
    ALTERNATIVE_FONT_1(11, null, "Alternative font 1", "Select alternative font 1."),
    ALTERNATIVE_FONT_2(12, null, "Alternative font 2", "Select alternative font 2."),
    ALTERNATIVE_FONT_3(13, null, "Alternative font 3", "Select alternative font 3."),
    ALTERNATIVE_FONT_4(14, null, "Alternative font 4", "Select alternative font 4."),
    ALTERNATIVE_FONT_5(15, null, "Alternative font 5", "Select alternative font 5."),
    ALTERNATIVE_FONT_6(16, null, "Alternative font 6", "Select alternative font 6."),
    ALTERNATIVE_FONT_7(17, null, "Alternative font 7", "Select alternative font 7."),
    ALTERNATIVE_FONT_8(18, null, "Alternative font 8", "Select alternative font 8."),
    ALTERNATIVE_FONT_9(19, null, "Alternative font 9", "Select alternative font 9."),
    FRAKTUR(20, null, "Fraktur (Gothic)", "Rarely supported."),
    DOUBLY_UNDERLINE(21, null, "Doubly underlined; or: not bold", "Double-underline per ECMA-48, but instead disables bold intensity on several terminals, including in the Linux kernel's console before version 4.17."),
    NORMAL_INTENSITY(22, null, "Normal intensity", "Neither bold nor faint; color changes where intensity is implemented as such."),
    NEITHER_ITALIC(23, null, "Neither italic, nor blackletter", ""),
    NOT_UNDERLINED(24, null, "Not underlined", "Neither singly nor doubly underlined."),
    NOT_BLINKING(25, null, "Not blinking", "Turn blinking off."),
    PROPORTIONAL_SPACING(26, null, "Proportional spacing", "TU T.61 and T.416, not known to be used on terminals."),
    NOT_REVERSED(27, null, "Not reversed", ""),
    REVEAL(28, null, "Reveal", "Not concealed"),
    NOT_CROSSED_OUT(29, null, "Not crossed out", ""),
    FOREGROUND_COLOR_BLACK(30, null, "Foreground color to black", "Foreground color to black"),
    FOREGROUND_COLOR_RED(31, null, "Foreground color to red", "Foreground color to red"),
    FOREGROUND_COLOR_GREEN(32, null, "Foreground color to green", "Foreground color to green"),
    FOREGROUND_COLOR_YELLOW(33, null, "Foreground color to yellow", "Foreground color to yellow"),
    FOREGROUND_COLOR_BLUE(34, null, "Foreground color to blue", "Foreground color to blue"),
    FOREGROUND_COLOR_MAGENTA(35, null, "Foreground color to magenta", "Foreground color to magenta"),
    FOREGROUND_COLOR_CYAN(36, null, "Foreground color to cyan", "Foreground color to cyan"),
    FOREGROUND_COLOR_WHITE(37, null, "Foreground color to white", "Foreground color to white"),
    DEFAULT_FOREGROUND_COLOR(39, null, "Default foreground color", "Implementation defined (according to standard)"),
    BACKGROUND_COLOR_BLACK(40, null, "Background color to black", "Background color to black"),
    BACKGROUND_COLOR_RED(41, null, "Background color to red", "Background color to red"),
    BACKGROUND_COLOR_GREEN(42, null, "Background color to green", "Background color to green"),
    BACKGROUND_COLOR_YELLOW(43, null, "Background color to yellow", "Background color to yellow"),
    BACKGROUND_COLOR_BLUE(44, null, "Background color to blue", "Background color to blue"),
    BACKGROUND_COLOR_MAGENTA(45, null, "Background color to magenta", "Background color to magenta"),
    BACKGROUND_COLOR_CYAN(46, null, "Background color to cyan", "Background color to cyan"),
    BACKGROUND_COLOR_WHITE(47, null, "Background color to white", "Background color to white"),
    DEFAULT_BACKGROUND_COLOR(49, null, "Default background color", "Implementation defined (according to standard)"),
    DISABLE_PROPORTIONAL_SPACING(50, null, "Disable proportional spacing", ""),
    FRAMED(51, null, "Framed", "Implemented as \"emoji variation selector\" in mintty"),
    ENCIRCLED(52, null, "Encircled", "Implemented as \"emoji variation selector\" in mintty"),
    OVERLINED(53, null, "Overlined", "Not supported in Terminal.app"),
    NEITHER_FRAMED_NOR_ENCIRCLED(54, null, "Neither framed nor encircled", ""),
    NOT_OVERLINED(55, null, "Not overlined", ""),
    DEFAULT_UNDERLINE_COLOR(59, null, "Default underline color", "Not in standard; implemented in Kitty, VTE, mintty and iTerm2."),
    IDEOGRAM_UNDERLINE(60, null, "Ideogram underline or rightside line", "Rarely supported."),
    IDEOGRAM_DOUBLE_UNDERLINE(61, null, "Ideogram double underline, or double line on the right side", "Rarely supported."),
    IDEOGRAM_OVERLINE(62, null, "Ideogram overline or left side line", "Rarely supported."),
    IDEOGRAM_DOUBLIE_OVERLINE(63, null, "Ideogram double overline, or double line on the left side", "Rarely supported."),
    NO_IDEOGRAM_ATTRIBUTES(64, null, "No ideogram attributes", "Reset the effects of attributes IDEOGRAM_UNDERLINE, IDEOGRAM_DOUBLE_UNDERLINE, IDEOGRAM_OVERLINE, IDEOGRAM_DOUBLE_OVERLINE."),
    SUPERSCRIPT(73, null, "Superscript", "Implement only in mintty."),
    SUBSCRIPT(74, null, "Subscript", "Implement only in mintty."),
    NEITHER_SUPERSCRIPT_NOR_SUBSCRIPT(75, null, "Neither superscript nor subscript", "Implement only in mintty."),
    BRIGHT_FOREGROUND_COLOR_BACK(90, null, "Set bright foreground color to black", "Not in standard; originally implemented by aixterm"),
    BRIGHT_FOREGROUND_COLOR_RED(91, null, "Set bright foreground color to red", "Not in standard; originally implemented by aixterm"),
    BRIGHT_FOREGROUND_COLOR_GREEN(92, null, "Set bright foreground color to green", "Not in standard; originally implemented by aixterm"),
    BRIGHT_FOREGROUND_COLOR_YELLOW(93, null, "Set bright foreground color to yellow", "Not in standard; originally implemented by aixterm"),
    BRIGHT_FOREGROUND_COLOR_BLUE(94, null, "Set bright foreground color to blue", "Not in standard; originally implemented by aixterm"),
    BRIGHT_FOREGROUND_COLOR_MAGENTA(95, null, "Set bright foreground color to magenta", "Not in standard; originally implemented by aixterm"),
    BRIGHT_FOREGROUND_COLOR_CYAN(96, null, "Set bright foreground color to cyan", "Not in standard; originally implemented by aixterm"),
    BRIGHT_FOREGROUND_COLOR_WHITE(97, null, "Set bright foreground color to white", "Not in standard; originally implemented by aixterm"),
    BRIGHT_BACKGROUND_COLOR_BACK(100, null, "Set bright background color to black", "Not in standard; originally implemented by aixterm"),
    BRIGHT_BACKGROUND_COLOR_RED(101, null, "Set bright background color to red", "Not in standard; originally implemented by aixterm"),
    BRIGHT_BACKGROUND_COLOR_GREEN(102, null, "Set bright background color to green", "Not in standard; originally implemented by aixterm"),
    BRIGHT_BACKGROUND_COLOR_YELLOW(103, null, "Set bright background color to yellow", "Not in standard; originally implemented by aixterm"),
    BRIGHT_BACKGROUND_COLOR_BLUE(104, null, "Set bright background color to blue", "Not in standard; originally implemented by aixterm"),
    BRIGHT_BACKGROUND_COLOR_MAGENTA(105, null, "Set bright background color to magenta", "Not in standard; originally implemented by aixterm"),
    BRIGHT_BACKGROUND_COLOR_CYAN(106, null, "Set bright background color to cyan", "Not in standard; originally implemented by aixterm"),
    BRIGHT_BACKGROUND_COLOR_WHITE(107, null, "Set bright background color to white", "Not in standard; originally implemented by aixterm");
    
    private final DisplayAttribute displayAttribute;

    private DisplayAttributes(int n, ColorParameters colorParameters, String name, String note) {
        this.displayAttribute = new DisplayAttribute(n, colorParameters, name, note);
    }
    
    public static DisplayAttributes getByCode(int code) {
        DisplayAttributes resultEnum = null;
        for (DisplayAttributes currentEnum : DisplayAttributes.values()) {
            if (Objects.equals(currentEnum.getDisplayAttribute().getCode(), code)) {
                resultEnum = currentEnum;
                break;
            }
        }
        return resultEnum;
    }
    
    public DisplayAttribute getDisplayAttribute() { return this.displayAttribute; }
    
}
