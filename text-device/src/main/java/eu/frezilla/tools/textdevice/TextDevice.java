package eu.frezilla.tools.textdevice;

import java.io.PrintWriter;
import java.io.Reader;

public interface TextDevice {
    
    TextDevice print(String s) throws TextDeviceException;
    
    TextDevice printf(String fmt, Object...params) throws TextDeviceException;
    
    TextDevice println(String s) throws TextDeviceException;
    
    Reader reader() throws TextDeviceException;
    
    String readLine() throws TextDeviceException;
    
    String readLine(String fmt, Object...params) throws TextDeviceException;
    
    char[] readPassword() throws TextDeviceException;
    
    char[] readPassword(String fmt, Object...params) throws TextDeviceException;
    
    PrintWriter writer() throws TextDeviceException;
    
}
