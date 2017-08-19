package org.parrot;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

public class EmptyInstanceFactory {
    public Object of(String typeName){
        try {
            Objenesis objenesis = new ObjenesisStd();
            ClassLoader classLoader = EmptyInstanceFactory.class.getClassLoader();
            Class clazz = classLoader.loadClass(typeName);
            ObjectInstantiator thingyInstantiator = objenesis.getInstantiatorOf(clazz);
            return thingyInstantiator.newInstance();
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
