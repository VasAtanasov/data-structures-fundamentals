package implementations;

import interfaces.LinkedList;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SinglyLinkedList<E> implements LinkedList<E> {

  private Node<E> head;
  private Node<E> tail;
  private int size;

  private static class Node<E> {
    private E element;
    private Node<E> left;
    private Node<E> right;

    private static <T> Node<T> empty() {
      return new Node<>();
    }

    private static <T> Node<T> of(T element) {
      Node<T> node = Node.empty();
      node.element = element;
      return node;
    }
  }

  @Override
  public void addFirst(E element) {
    add(
        element,
        node -> {
          node.right = head;
          head.left = node;
          head = node;
        });
  }

  @Override
  public void addLast(E element) {
    add(
        element,
        node -> {
          node.left = tail;
          tail.right = node;
          tail = node;
        });
  }

  private void add(E element, Consumer<Node<E>> nodeConsumer) {
    Node<E> node = Node.of(element);
    if (size == 0) {
      head = node;
      tail = node;
    } else {
      nodeConsumer.accept(node);
    }
    size += 1;
  }

  @Override
  public E removeFirst() {
    return remove(
        () -> {
          E element = head.element;
          head = head.right;
          head.left = null;
          return element;
        });
  }

  @Override
  public E removeLast() {
    return remove(
        () -> {
          E element = tail.element;
          tail = tail.left;
          tail.right = null;
          return element;
        });
  }

  private E remove(Supplier<E> nodeConsumer) {
    E element;
    if (size == 1) {
      element = head.element;
      head = tail = null;
    } else {
      element = nodeConsumer.get();
    }
    size -= 1;
    return element;
  }

  @Override
  public E getFirst() {
    ensureNotEmpty();
    return head.element;
  }

  @Override
  public E getLast() {
    ensureNotEmpty();
    return tail.element;
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
    return null;
  }

  private void ensureNotEmpty() {
    if (size == 0) {
      throw new IllegalStateException("List is empty");
    }
  }
}
