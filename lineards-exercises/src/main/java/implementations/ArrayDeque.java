package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {

  private Object[] elements;
  private int head;
  private int tail;

  public ArrayDeque() {
    elements = new Object[16];
  }

  static int inc(int i, int modulus) {
    if (++i >= modulus) i = 0;
    return i;
  }

  static int dec(int i, int modulus) {
    if (--i < 0) i = modulus - 1;
    return i;
  }

  @Override
  public void add(E Element) {}

  @Override
  public void offer(E element) {}

  @Override
  public void addFirst(E element) {
    final Object[] es = elements;
    es[head = dec(head, es.length)] = element;
//    if (head == tail) grow(1);
  }

  @Override
  public void addLast(E element) {
    final Object[] es = elements;
    es[tail] = element;
//    if (head == (tail = inc(tail, es.length))) grow(1);
  }

  @Override
  public void push(E element) {}

  @Override
  public void insert(int index, E element) {}

  @Override
  public void set(int index, E element) {}

  @Override
  public E peek() {
    return null;
  }

  @Override
  public E poll() {
    return null;
  }

  @Override
  public E pop() {
    return null;
  }

  @Override
  public E get(int index) {
    return null;
  }

  @Override
  public E get(Object object) {
    return null;
  }

  @Override
  public E remove(int index) {
    return null;
  }

  @Override
  public E remove(Object object) {
    return null;
  }

  @Override
  public E removeFirst() {
    return null;
  }

  @Override
  public E removeLast() {
    return null;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public int capacity() {
    return 0;
  }

  @Override
  public void trimToSize() {}

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return null;
  }
}
