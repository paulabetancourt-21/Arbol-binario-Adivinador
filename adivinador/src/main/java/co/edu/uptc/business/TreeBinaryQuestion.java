package co.edu.uptc.business;

import co.edu.uptc.data.FilePersistance;
import co.edu.uptc.libraries.DoublyLinkedList;

public class TreeBinaryQuestion{
    private NodeTree root;

    public void insert(DoublyLinkedList<NodeTree> list) {
        root = insert(root, list);
    }

    private NodeTree insert(NodeTree node, DoublyLinkedList<NodeTree> list) {
        String data;
        NodeTree aux = list.deletefirstNode();
        node.setRight(insert(node.getRight(),list));
        node.setLeft(insert(node.getLeft(),list));
        return node;
    }

    public DoublyLinkedList<NodeTree> readFile(){
        FilePersistance file = new FilePersistance(); 
        file.readFile("tree.csv");
        return file.getList(); 
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

    public void start() {
        DoublyLinkedList<NodeTree> list =  readFile();
        insert(list);
        preOrder();

    }

    
}