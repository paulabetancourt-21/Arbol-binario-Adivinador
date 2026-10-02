package co.edu.uptc.libraries;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data 
@RequiredArgsConstructor 
public class Node<T> {
    private final T value;
    private Node<T> next;
    private Node<T> previous; 
    
}
