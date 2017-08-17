package org.parrot.typed;

import java.util.ArrayList;
import java.util.List;

public class TypedObjectFactory {

    private final List<Serializer> serializers;

    public TypedObjectFactory() {
        serializers = new ArrayList<>(2);
        serializers.add(new SerializerAsValue());
        serializers.add(new SerializerAsList(this));
        serializers.add(new SerializerAsMap(this));
        serializers.add(new SerializerAsFields(this));
    }

    public TypedObject of(Object o){
        for(Serializer serializer: serializers){
            if(serializer.canSerialize(o)){
                return serializer.serialize(o);
            }
        }
        throw new RuntimeException("Unsupported: " + o);
    }
}
