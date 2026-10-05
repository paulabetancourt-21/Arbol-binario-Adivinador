package co.edu.uptc.business;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data 
@Getter 
@RequiredArgsConstructor 
public class NodeTree{
    private final String message;
    private boolean isQuestion;
    private NodeTree right;
    private NodeTree left; 
    
//Es un nodo hoja? 
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }


}


