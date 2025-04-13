package eu.frezilla.jtools.memory;

import java.util.ArrayList;
import java.util.List;

public final class Buffer<T> {

    private final int capacity;
    private final List<T> datas;
    private int indexEnd;
    private int indexStart;
    
    public Buffer(int capacity) {
        this.datas = new ArrayList<>(capacity);
        this.capacity = capacity;
        this.indexEnd = 0;
        this.indexStart = 0;
        for (int i = 0; i < capacity; i++) this.datas.add(null);
    }
    
    public Buffer() {
        this(10);
    }
    
    public void add(T element) {
        datas.set(indexEnd, element);
        indexEnd = incIndex(indexEnd);
        if (indexEnd == indexStart) indexStart = incIndex(indexStart);
    }
    
    private int decIndex(int index) {
        if (index - 1 < 0) return capacity - 1;
        else return index - 1;
    }
    
    private int incIndex(int index) {
        return (index + 1) % capacity;
    }
        
    public boolean isEmpty() {
        return indexEnd == indexStart;
    }
    
    public boolean isFull() {
        if (indexEnd > indexStart) return (indexEnd - indexStart) == (capacity - 1);
        else return (capacity + indexEnd - indexStart) == (capacity - 1);
    }
    
    public T removeFirst() throws BufferException {
        if (isEmpty()) throw new BufferException("Le buffer est vide");
        
        int targetIndex = decIndex(indexStart);
        
        T element = datas.get(targetIndex);
        datas.set(targetIndex, null);
        
        return element;        
    }
    
    public T removeLast() throws BufferException {
        if (isEmpty()) throw new BufferException("Le buffer est vide");
        
        indexEnd = decIndex(indexEnd);
        
        T element = datas.get(indexEnd);
        datas.set(indexEnd, null);
        
        return element;
    }
    
}