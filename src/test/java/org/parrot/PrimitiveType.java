package org.parrot;

public enum PrimitiveType {
    INT{
        @Override
        public Object of(String value) {
            return Integer.valueOf(value);
        }
    },

    LONG{
        @Override
        public Object of(String value) {
            return Long.valueOf(value);
        }
    },

    BOOLEAN{
        @Override
        public Object of(String value) {
            return Boolean.valueOf(value);
        }
    },

    DOUBLE{
        @Override
        public Object of(String value) {
            return Double.valueOf(value);
        }
    },

    STRING{
        @Override
        public Object of(String value) {
            return value;
        }
    };

    public abstract Object of(String value);
}
