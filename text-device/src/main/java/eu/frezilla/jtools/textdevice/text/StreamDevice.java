package eu.frezilla.jtools.textdevice.text;

import eu.frezilla.jtools.textdevice.TextDevice;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Objects;

public class StreamDevice extends AbstractTextDevice {
    
    private static final TextDevice DEFAULT = new StreamDevice(System.in, System.out);
    
    private final BufferedReader reader;
    private final Object readLock;
    private final PrintWriter writer;
    private final Object writeLock;
    
    public StreamDevice(InputStream is, OutputStream os) {
        reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is, "is can't be null")));
        readLock = new Object();
        writer = new PrintWriter(Objects.requireNonNull(os, "os can't be null"), true);
        writeLock = new Object();
    }
    
    @Override
    protected void flushImpl() throws Exception {
        writer.flush();
    }
    
    public static TextDevice getInstance() {
        return DEFAULT;
    }

    @Override
    protected void printfImpl(String fmt, Object... params) throws Exception {
        writer.printf(fmt, params);
    }

    @Override
    protected String readLineImpl(String fmt, Object... params) throws Exception {
        String line = null;
        synchronized (writeLock) {
            synchronized (readLock) {
                if (!fmt.isEmpty()) writer.format(fmt, params);
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    throw new IOError(e);
                }
            }
        }
        return line;
    }

    @Override
    protected char[] readPasswordImpl(String fmt, Object... params) throws Exception {
        char[] passwd ;
        synchronized (writeLock) {
            synchronized (readLock) {
                if (!fmt.isEmpty()) writer.format(fmt, params);
                passwd = reader.readLine().toCharArray();
            }
        }
        return passwd;
    }

}
