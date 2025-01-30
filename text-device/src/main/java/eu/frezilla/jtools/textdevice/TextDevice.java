package eu.frezilla.jtools.textdevice;

import java.io.PrintWriter;
import java.io.Reader;

public interface TextDevice {
    
    default TextDevice print(String s) throws TextDeviceException { return printf("%s", s); }
    
    TextDevice printf(String fmt, Object...params) throws TextDeviceException;
    
    default TextDevice println(String s) throws TextDeviceException { return printf("%s" + System.lineSeparator(), s); }
    
    Reader reader() throws TextDeviceException;
    
    default String readLine() throws TextDeviceException { return readLine(""); }
    
    String readLine(String fmt, Object...params) throws TextDeviceException;
    
    default char[] readPassword() throws TextDeviceException { return readPassword(""); }
    
    char[] readPassword(String fmt, Object...params) throws TextDeviceException;
    
    PrintWriter writer() throws TextDeviceException;
    
}
