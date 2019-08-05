package ru.otus.homework2;

import java.util.*;

public class DiyLIST<E> implements List<E> {

    private List list = new ArrayList();
    private int INITIAL_SIZE = 15;
    private Object[] elements;
    private int size;

    public DiyLIST(int startSize) {
        elements = new Object[size];
    }

    public DiyLIST() {
        elements = new Object[INITIAL_SIZE];
    }


    public void sort(Comparator<? super E> c) {
        Arrays.sort((E[]) elements, 0, size, c);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return elements;
    }

    public int getLength() {
        return elements.length;
    }

    @Override
    public boolean add(Object o) {
        if (size == elements.length) {
            elements = grow();
        }
        elements[size] = o;
        size++;
        return true;
    }

    private Object[] grow() {
        return elements = Arrays.copyOf(elements, size < INITIAL_SIZE ? INITIAL_SIZE : size * 2);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        E prevValue = (E) elements[index];
        elements[index] = element;
        return prevValue;

    }

    @Override
    public void add(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }


    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListItr(index);
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    private class MyIterator implements Iterator<E> {

        int nextElementIndex;
        int lastReturnedIndex = -1;


        @Override
        public boolean hasNext() {
            return nextElementIndex != size;
        }

        @Override
        public E next() {
            if (nextElementIndex > size) throw new ArrayIndexOutOfBoundsException();
            Object[] objects = DiyLIST.this.elements;
            lastReturnedIndex = nextElementIndex;
            nextElementIndex++;
            return (E) objects[lastReturnedIndex];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
//            if (lastReturnedIndex<0) throw new IllegalStateException();

        }

//        @Override
//        public void forEachRemaining(Consumer<? super E> action) {
//            throw new UnsupportedOperationException();
//        }
    }

    private class MyListItr extends MyIterator implements ListIterator<E> {
        MyListItr(int i) {
            super();
            nextElementIndex = i;
        }

        @Override
        public boolean hasPrevious() {
            return nextElementIndex != 0;
        }

        @Override
        public int nextIndex() {
            return nextElementIndex;
        }

        @Override
        public int previousIndex() {
            return nextElementIndex - 1;
        }

        @Override
        public E previous() {
            int index = nextElementIndex - 1;
            if (index < 0) throw new NoSuchElementException();
            Object[] elementData = DiyLIST.this.elements;
            nextElementIndex = index;
            return (E) elements[lastReturnedIndex = index];
        }

        @Override
        public void set(E o) {
            DiyLIST.this.set(lastReturnedIndex, o);

        }

        @Override
        public void add(Object o) {

        }
    }
}
