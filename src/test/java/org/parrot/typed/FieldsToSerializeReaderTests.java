package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;
import org.parrot.testobjects.Thing;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class FieldsToSerializeReaderTests {
    @Test
    public void getFieldsToSerialize_worksForOneField(){
        Integer integer = 123;
        List<Field> actual = FieldsToSerializeReader.getFieldsToSerialize(integer);
        String actualStr = getListAsLines(actual);
        Assert.assertEquals("private final int java.lang.Integer.value", actualStr);
    }

    public String getListAsLines(List<Field> actual) {
        return actual.stream().
                    map(Object::toString).
                    collect(Collectors.joining("\n"));
    }

    @Test
    public void getFieldsToSerialize_worksForMultipleFields(){
        Thing thing = new Thing("bolt", 3);
        List<Field> actual = FieldsToSerializeReader.getFieldsToSerialize(thing);
        String actualStr = getListAsLines(actual);
        String expected = "private final java.lang.String org.parrot.testobjects.Thing.name\n" +
                "private final int org.parrot.testobjects.Thing.size";
        Assert.assertEquals(expected, actualStr);
    }
}
