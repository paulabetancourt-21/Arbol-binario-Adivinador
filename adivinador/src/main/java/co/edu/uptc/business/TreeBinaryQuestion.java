package co.edu.uptc.business;

import co.edu.uptc.data.FilePersistance;
import co.edu.uptc.libraries.DoublyLinkedList;

public class TreeBinaryQuestion{
    private NodeTree root;

    public void insert(DoublyLinkedList<NodeTree> list) {
        root = buildTree(list);
    }

    private NodeTree buildTree(DoublyLinkedList<NodeTree> list) {
        if (list.size() == 0) return null;
        NodeTree aux = list.deletefirstNode();
        if (aux.isQuestion()) {
            aux.setLeft(buildTree(list));
            aux.setRight(buildTree(list));
        }
        return aux;
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
        System.out.println(node.getMessage().split(",")[1]);
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(NodeTree node){
        if (node == null) {
            return; 
        }
        inOrder(node.getRight());
        System.out.println(node.getMessage().split(",")[1]);
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
        System.out.println(node.getMessage().split(",")[1]);
    }

    public void start() {
        DoublyLinkedList<NodeTree> list =  readFile();
        insert(list);
        preOrder();
    }

    
}