package eu.frezilla.jtools.textdevice;

public class TextDeviceException extends Exception {
    
    public TextDeviceException() {
        super();
    }
    
    public TextDeviceException(String message) {
        super(message);
    }
    
    public TextDeviceException(Throwable t) {
        super(t);
    }
    
    public TextDeviceException(String message, Throwable t) {
        super(message, t);
    }
    
}
