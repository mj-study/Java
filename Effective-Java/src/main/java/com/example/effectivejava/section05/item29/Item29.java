package com.example.effectivejava.section05.item29;

public class Item29 {

    class StackOrigin {

        private Object[] elements;
        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        public StackOrigin() {
            elements = new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(Object o) {
            ensureCapacity();
            elements[size++] = o;
        }

        public Object pop() {
            if (size == 0) {
                throw new IllegalStateException();
            }
            Object result = elements[--size];
            elements[size] = null;
            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void ensureCapacity() {
            if (elements.length == size) {
                elements = new Object[elements.length * 2 + 1];
            }
        }

    }

    /*
    제네릭을 사용함으로써 형변환을 하지 않아도되고, 안전성이 보장됨
     */
    class StackGeneric<E> {

        private E[] elements;
        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        /**
         * 안전성 보장 런타임시에는 E타입이 아닌 Object 타입임
         */
        @SuppressWarnings("unchecked")
        public StackGeneric() {
            elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(E e) {
            ensureCapacity();
            elements[size++] = e;
        }

        public E pop() {
            if (size == 0) {
                throw new IllegalStateException();
            }
            E result = elements[--size];
            elements[size] = null;
            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void ensureCapacity() {
            if (elements.length == size) {
                //noinspection unchecked
                elements = (E[]) new Object[elements.length * 2 + 1];
            }
        }
    }
}
