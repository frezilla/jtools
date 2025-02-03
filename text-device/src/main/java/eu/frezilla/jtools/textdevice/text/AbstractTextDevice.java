package eu.frezilla.jtools.textdevice.text;

import eu.frezilla.jtools.textdevice.TextDevice;
import eu.frezilla.jtools.textdevice.TextDeviceException;
import java.io.PrintWriter;
import java.io.Reader;

abstract class AbstractTextDevice implements TextDevice {
    
    protected abstract void flushImpl() throws Exception;
    
    @Override
    public TextDevice printf(String fmt, Object... params) throws TextDeviceException {
        try {
            printfImpl(fmt, params);
            flushImpl();
            return this;
        } catch (Exception e) {
            throw new TextDeviceException(e);
        }
    }
    
    protected abstract void printfImpl(String fmt, Object... params) throws Exception;
        
    @Override
    public String readLine(String fmt, Object... params) throws TextDeviceException {
        try {
            return readLineImpl(fmt, params);
        } catch (Exception e) {
            throw new TextDeviceException(e);
        }
    }
    
    protected abstract String readLineImpl(String fmt, Object... params) throws Exception;

    @Override
    public char[] readPassword(String fmt, Object... params) throws TextDeviceException {
        try {
            return readPasswordImpl(fmt, params);
        } catch (Exception e) {
            throw new TextDeviceException(e);
        }
    }
    
    protected abstract char[] readPasswordImpl(String fmt, Object... params) throws Exception;
    
}
