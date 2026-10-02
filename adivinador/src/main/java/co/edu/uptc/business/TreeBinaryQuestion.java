package co.edu.uptc.business;

public class TreeBinaryQuestion{
    private NodeTree root;

    public void insert(String data) {
        root = insert(root, data);
    }

    private NodeTree insert(NodeTree node, String data) {
        if (node == null) {
            return new NodeTree(data); 
        }
        node.setRight(insert(node.getRight(),data));
        node.setLeft(insert(node.getLeft(),data));
        return node;
    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(NodeTree node){
        if (node == null) {
            return; 
        }
        System.out.println(node.getMessage());
        preOrder(node.getRight());
        preOrder(node.getLeft());
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(NodeTree node){
        if (node == null) {
            return; 
        }
        inOrder(node.getRight());
        System.out.println(node.getMessage());
        inOrder(node.getLeft());
    }


    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(NodeTree node){
        if (node == null) {
            return; 
        }
        postOrder(node.getRight());
        postOrder(node.getLeft());
        System.out.println(node.getMessage());
    }

    
}