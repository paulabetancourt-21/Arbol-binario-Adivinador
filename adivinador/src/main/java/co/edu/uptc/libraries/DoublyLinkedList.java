package co.edu.uptc.libraries;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

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

    private Node<T> findCenter(){
        int middleIndex = size()/2;
        Node<T> pointer = head;
        for (int i = 0; i < middleIndex && pointer.getNext() != null; i++) {
            pointer = pointer.getNext();
        }
        return pointer; 
    }

    public void addInTheMiddle(T element) {
        if (isEmpty()) {
            addTheEnd(element);
        } else {
            Node<T> center = findCenter();
            if (center == tail) {
                addTheEnd(element);
            } else {
                Node<T> nextCenter = center.getNext();
                Node<T> newCenter = new Node<>(element);
                connect(center, newCenter);
                connect(newCenter, nextCenter);
                size++;
            }
        }
    }

    public <K> Map<K, DoublyLinkedList<T>> splitByCriteria(Function<T, K> sorter) {
        Map<K, DoublyLinkedList<T>> groups = new HashMap<>();
        Node<T> pointer = head;
        while (pointer != null) {
            T element = pointer.getValue();
            K key = sorter.apply(element);
            if (!groups.containsKey(key)) {
                groups.put(key, new DoublyLinkedList<>()); 
            }
            DoublyLinkedList<T> list = groups.get(key); 
            list.addTheEnd(element);
            pointer = pointer.getNext();
        }
        return groups;
    }


    public void deleteNode(int index) {
        Node<T> pointer = head;
        for (int i = 1; pointer != null && i != index; i++) {
            pointer = pointer.getNext();
        }
        if (pointer == null) return;
        Node<T> prev = pointer.getPrevious();
        Node<T> next = pointer.getNext();
        if (prev != null) prev.setNext(next); else head = next;
        if (next != null) next.setPrevious(prev); else tail = prev;
    }

    public T deletefirstNode(){
        Node<T> node = head;
        if (head!=null){
            head = head.getNext();
        }
        return node != null ? node.getValue() : null;
    }

    public void logicalDeletion(Predicate<T> logic) {
        Node<T> pointer = head;
        int i = 1;
        while (pointer != null) {
            Node<T> next = pointer.getNext();
            if (logic.test(pointer.getValue())) {
                deleteNode(i);
            } else {
                i++;
            }
            pointer = next;
        }
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