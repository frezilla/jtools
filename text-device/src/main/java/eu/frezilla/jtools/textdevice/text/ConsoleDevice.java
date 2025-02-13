package eu.frezilla.jtools.textdevice.text;

import eu.frezilla.jtools.textdevice.TextDevice;
import java.io.Console;
import java.util.Objects;

public class ConsoleDevice extends AbstractTextDevice {
    
    private static final TextDevice DEFAULT = new ConsoleDevice(System.console());

    private final Console console;

    private ConsoleDevice(Console console) {
        this.console = Objects.requireNonNull(console, "console can't be null");
    }
    
    @Override
    protected void flushImpl() throws Exception {
        console.flush();
    }    
    
    public static TextDevice getInstance() {
        return DEFAULT;
    }

    @Override
    protected void printfImpl(String fmt, Object... params) throws Exception {
        console.printf(fmt, params);
    }

    @Override
    protected String readLineImpl(String fmt, Object... params) throws Exception {
        return console.readLine(fmt, params);
    }

    @Override
    protected char[] readPasswordImpl(String fmt, Object... params) throws Exception {
        return console.readPassword(fmt, params);
    }

}
