package eu.frezilla.jtools.textdevice;

public interface TextDevice {
    
    default TextDevice print(String s) throws TextDeviceException { return printf("%s", s); }
    
    TextDevice printf(String fmt, Object...params) throws TextDeviceException;
    
    default TextDevice println(String s) throws TextDeviceException { return printf("%s" + System.lineSeparator(), s); }
    
    default String readLine() throws TextDeviceException { return readLine(""); }
    
    String readLine(String fmt, Object...params) throws TextDeviceException;
    
    default char[] readPassword() throws TextDeviceException { return readPassword(""); }
    
    char[] readPassword(String fmt, Object...params) throws TextDeviceException;
    
}
