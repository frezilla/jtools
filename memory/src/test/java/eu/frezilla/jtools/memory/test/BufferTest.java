package eu.frezilla.jtools.memory.test;

import eu.frezilla.jtools.memory.Buffer;
import eu.frezilla.jtools.memory.BufferException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BufferTest {

    @Test
    public void test() throws BufferException {
        Buffer<String> buffer = new Buffer(5);

        Assertions.assertTrue(buffer.isEmpty());
        Assertions.assertFalse(buffer.isFull());

        buffer.add("chaine1");
        buffer.add("chaine2");

        Assertions.assertFalse(buffer.isEmpty());
        Assertions.assertFalse(buffer.isFull());

        buffer.add("chaine3");
        buffer.add("chaine4");
        buffer.add("chaine5");
        
        Assertions.assertFalse(buffer.isEmpty());
        Assertions.assertTrue(buffer.isFull());
        
        Assertions.assertEquals("chaine1", buffer.removeFirst());
        Assertions.assertEquals("chaine5", buffer.removeLast());
        
        buffer.add("chaine6");
        buffer.add("chaine7");
        buffer.add("chaine8");
    }

}
