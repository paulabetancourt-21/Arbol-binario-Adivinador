package co.edu.uptc.libraries;

import java.util.function.Consumer;

public class DoublyLinkedList<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void connect(Node<T> before, Node<T> after){
        before.setNext(after);
        after.setPrevious(before);
    }

    public void addTheEnd(T element){
        Node<T> node = new Node<>(element);
        if (isEmpty()) {
            head = node;
            tail = node;
        }else{
            connect(tail, node);
            tail = node;
        }
        size++;
    }

    public void addAtBeginning(T element){
        Node<T> node = new Node<>(element);
        if (isEmpty()) {
            head = node;
            tail = node;
        }else{
            connect(node, head);
            head = node;
        }
        size++;
    }

    public void forEach(Consumer<T> accion) {
        Node<T> pointer = head;
        while (pointer != null) {
            accion.accept(pointer.getValue());
            pointer = pointer.getNext();
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

}
