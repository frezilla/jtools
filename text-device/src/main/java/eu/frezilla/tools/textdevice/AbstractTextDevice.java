package eu.frezilla.tools.textdevice;

import java.io.PrintWriter;
import java.io.Reader;

abstract class AbstractTextDevice implements TextDevice {
    
    protected abstract void flushImpl() throws Exception;
        
    public TextDevice print(String s) throws TextDeviceException {
        return printf("%s", s);
    }
    
    public TextDevice println(String s) throws TextDeviceException {
        return printf("%s" + System.lineSeparator(), s);
    }
    
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
    public String readLine() throws TextDeviceException {
        return readLine("");
    }

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
    public char[] readPassword() throws TextDeviceException {
        return readPassword("");
    }

    @Override
    public char[] readPassword(String fmt, Object... params) throws TextDeviceException {
        try {
            return readPasswordImpl(fmt, params);
        } catch (Exception e) {
            throw new TextDeviceException(e);
        }
    }
    
    protected abstract char[] readPasswordImpl(String fmt, Object... params) throws Exception;

    @Override
    public Reader reader() throws TextDeviceException {
        try {
            return readerImpl();
        } catch (Exception e) {
            throw new TextDeviceException(e);
        }
    }
    
    protected abstract Reader readerImpl() throws Exception;

    @Override
    public PrintWriter writer() throws TextDeviceException {
        try {
            return writerImpl();
        } catch (Exception e) {
            throw new TextDeviceException(e);
        }
    }
    
    protected abstract PrintWriter writerImpl() throws Exception;
    
}
