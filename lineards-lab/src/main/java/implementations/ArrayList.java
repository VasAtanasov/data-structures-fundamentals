package implementations;

import interfaces.List;

import java.io.Serializable;
import java.util.*;

public class ArrayList<E> implements List<E>, Serializable {

  private static final int DEFAULT_CAPACITY = 10;
  private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
  private static final Object[] EMPTY_ELEMENTDATA = {};
  private int size;
  transient Object[] elementData;

  public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
      this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
      this.elementData = EMPTY_ELEMENTDATA;
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }
  }

  private void add(E e, Object[] elementData, int s) {
    if (s == elementData.length) elementData = grow();
    elementData[s] = e;
    size = s + 1;
  }

  @Override
  public boolean add(E element) {
    add(element, elementData, size);
    return true;
  }

  @Override
  public boolean add(int index, E element) {
    rangeCheckForAdd(index);
    final int s = size;
    Object[] elementData = this.elementData;
    if (s == elementData.length) elementData = grow();
    System.arraycopy(elementData, index, elementData, index + 1, s - index);
    elementData[index] = element;
    size = s + 1;
    return true;
  }

  @SuppressWarnings("unchecked")
  E elementData(int index) {
    return (E) elementData[index];
  }

  @Override
  public E get(int index) {
    Objects.checkIndex(index, size);
    return elementData(index);
  }

  @Override
  public E set(int index, E element) {
    Objects.checkIndex(index, size);
    E oldValue = elementData(index);
    elementData[index] = element;
    return oldValue;
  }

  @Override
  public E remove(int index) {
    Objects.checkIndex(index, size);
    final Object[] es = elementData;
    @SuppressWarnings("unchecked")
    E oldValue = (E) es[index];
    removeAndShift(es, index);
    return oldValue;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public int indexOf(E element) {
    return indexOfRange(element, 0, size);
  }

  int indexOfRange(Object o, int start, int end) {
    Object[] es = elementData;
    if (o == null) {
      for (int i = start; i < end; i++) {
        if (es[i] == null) {
          return i;
        }
      }
    } else {
      for (int i = start; i < end; i++) {
        if (o.equals(es[i])) {
          return i;
        }
      }
    }
    return -1;
  }

  @Override
  public boolean contains(E element) {
    return indexOf(element) >= 0;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public Iterator<E> iterator() {
    return new ArrayListIterator();
  }

  private class ArrayListIterator implements Iterator<E> {
    int cursor;

    @Override
    public boolean hasNext() {
      return cursor != size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E next() {
      int i = cursor;
      if (i >= size) throw new NoSuchElementException();
      Object[] elementData = ArrayList.this.elementData;
      cursor = i + 1;
      return (E) elementData[i];
    }
  }

  private void rangeCheckForAdd(int index) {
    if (index > size || index < 0) throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
  }

  private String outOfBoundsMsg(int index) {
    return "Index: " + index + ", Size: " + size;
  }

  private Object[] grow(int minCapacity) {
    return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
  }

  private Object[] grow() {
    return grow(size + 1);
  }

  private int newCapacity(int minCapacity) {
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity * 2;
    if (newCapacity - minCapacity <= 0) {
      if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
        return Math.max(DEFAULT_CAPACITY, minCapacity);
      if (minCapacity < 0) // overflow
      throw new OutOfMemoryError();
      return minCapacity;
    }
    return newCapacity;
  }

  private void removeAndShift(Object[] es, int i) {
    final int newSize;
    if ((newSize = size - 1) > i) System.arraycopy(es, i + 1, es, i, newSize - i);
    es[size = newSize] = null;
    shrinkIfNeeded();
  }

  private void shrinkIfNeeded() {
    if (elementData.length / 3 > size) {
      elementData = Arrays.copyOf(elementData, elementData.length / 2);
    }
  }
}
