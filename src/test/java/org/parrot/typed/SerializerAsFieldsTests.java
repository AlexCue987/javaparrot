package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;
import org.parrot.testobjects.Thing;

public class SerializerAsFieldsTests {
    @Test
    public void works(){
        Thing thing = new Thing("Shoe", 12);
        TypedObjectFactory typedObjectFactory = new TypedObjectFactory();
        SerializerAsFields serializerAsFields = new SerializerAsFields(typedObjectFactory);
        TypedObject typedObject = serializerAsFields.serialize(thing);
        String actual = typedObject.toString().replace(",", ",\n");
//        System.out.println(actual);
        String expected = "TypedObject(className=org.parrot.testobjects.Thing,\n" +
                " persistingMethod=FIELDS,\n" +
                " value=[TypedObject(className=java.lang.String,\n" +
                " persistingMethod=VALUE,\n" +
                " value=Shoe),\n" +
                " TypedObject(className=java.lang.Integer,\n" +
                " persistingMethod=VALUE,\n" +
                " value=12)])";
        Assert.assertEquals(expected, actual);
    }
}
