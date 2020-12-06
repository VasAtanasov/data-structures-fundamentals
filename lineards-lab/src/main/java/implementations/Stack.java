package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {

  private Node<E> top;
  private int size;

  private static class Node<E> {
    private E element;
    private Node<E> previous;

    private static <T> Node<T> empty() {
      return new Node<>();
    }

    private static <T> Node<T> of(T element, Node<T> previous) {
      Node<T> newTop = Node.empty();
      newTop.element = element;
      newTop.previous = previous;
      return newTop;
    }
  }

  @Override
  public void push(E element) {
    top = Node.of(element, top);
    size += 1;
  }

  @Override
  public E pop() {
    ensureNotEmpty();
    E element = top.element;
    Node<E> temp = top.previous;
    top.previous = null;
    top = temp;
    size -= 1;
    return element;
  }

  private void ensureNotEmpty() {
    if (size == 0) {
      throw new IllegalStateException("Illegal pop for empty Stack");
    }
  }

  @Override
  public E peek() {
    ensureNotEmpty();
    return top.element;
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
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private Node<E> current = top;

      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public E next() {
        E element = current.element;
        current = current.previous;
        return element;
      }
    };
  }
}
