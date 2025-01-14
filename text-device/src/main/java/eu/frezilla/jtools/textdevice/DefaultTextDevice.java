package eu.frezilla.jtools.textdevice;

public class DefaultTextDevice {
    
    private static final TextDevice DEFAULT = (System.console() == null) ? StreamDevice.getInstance() : ConsoleDevice.getInstance();
    
    public static TextDevice getInstance() {
        return DEFAULT;
    }
    
}
