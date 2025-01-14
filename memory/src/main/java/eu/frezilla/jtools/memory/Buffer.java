package eu.frezilla.jtools.memory;

import java.util.ArrayList;
import java.util.List;

public final class Buffer<T> {

    private final List<T> buffer;
    private int indexEnd;
    private int indexStart;
    private final int capacity;
    
    public Buffer(int capacity) {
        this.buffer = new ArrayList<>(capacity);
        this.capacity = capacity;
        this.indexEnd = 0;
        this.indexStart = 0;
    }
    
    public Buffer() {
        this(10);
    }
    
    public void add(T element) {
        buffer.add(indexEnd, element);
        indexEnd = indexEnd++ % capacity;
        if (indexEnd == indexStart) indexStart = indexStart++ % capacity;        
    }
    
    public boolean isEmpty() {
        return indexEnd == indexStart;
    }
    
    public boolean isFull() {
        int distance;
        
        if (indexEnd < indexStart) distance = indexEnd + capacity - indexStart;
        else distance = indexEnd - indexStart;
        
        return distance == capacity - 1;
    }
    
    public T removeFirst() throws BufferException {
        if (isEmpty()) throw new BufferException("Le buffer est vide");
        
        T element = buffer.get(indexStart);
        buffer.set(indexStart, null);
        
        indexStart = indexStart++ % capacity;
        
        return element;        
    }
    
    public T removeLast() throws BufferException {
        if (isEmpty()) throw new BufferException("Le buffer est vide");
        
        T element = buffer.get(indexEnd);
        buffer.set(indexEnd, null);
        
        indexEnd = indexEnd--;
        if (indexEnd < 0) indexEnd = capacity - 1;
        
        return element;
    }
    
}
