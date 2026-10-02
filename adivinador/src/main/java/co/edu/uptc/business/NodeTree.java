package co.edu.uptc.business;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data 
@RequiredArgsConstructor 
public class NodeTree{
    private final String message;
    private boolean isQuestion;
    private NodeTree right;
    private NodeTree left; 
    
}


