package co.edu.uptc.business;

import co.edu.uptc.libraries.DoublyLinkedList;
import lombok.Getter;

@Getter
public class TreeBinaryQuestion {
    private NodeTree root;
    private int leaf;
    private int question;

    private void countQuestion(boolean oneMore) {
        if (oneMore) {
            question++;
        } else {
            leaf++;
        }
    }

    public void iterateAndCount() {
        leaf = 0;
        question = 0;
        interate(root);
    }

    private void interate(NodeTree node) {
        if (node == null) {
            return;
        }
        interate(node.getRight());
        interate(node.getLeft());   
        countQuestion(node.isQuestion());
    }

    public void insert(DoublyLinkedList<NodeTree> list) {
        root = buildTree(list);
    }

    private NodeTree buildTree(DoublyLinkedList<NodeTree> list) {
        if (list.size() == 0)
            return null;
        NodeTree aux = list.deletefirstNode();
        if (aux.isQuestion()) {
            aux.setLeft(buildTree(list));
            aux.setRight(buildTree(list));
        }
        return aux;
    }

    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(NodeTree node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(text(node)).append("\n");
        preOrder(node.getLeft(), sb);
        preOrder(node.getRight(), sb);
    }

    public String inOrder() {
        StringBuilder sb = new StringBuilder(); 
        inOrder(root, sb);
        return sb.toString();
    }

    private void inOrder(NodeTree node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft(), sb);
        sb.append(text(node)).append("\n");
        inOrder(node.getRight(), sb);
    }

    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);
        return sb.toString();
    }

    private void postOrder(NodeTree node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft(),sb);
        postOrder(node.getRight(),sb);
        sb.append(text(node)).append("\n");
    }

    public String show() {
        if (root == null) {
            return "El árbol está vacío.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(text(root)).append("\n");
        show(root, "", sb);
        return sb.toString();
    }

    private void show(NodeTree node, String prefix, StringBuilder sb) {
        if (node == null || node.isLeaf()) {
            return;
        }
        sb.append(prefix).append("├── (si) ").append(text(node.getLeft())).append("\n");
        show(node.getLeft(), prefix + "│   ", sb);
        sb.append(prefix).append("└── (no) ").append(text(node.getRight())).append("\n");
        show(node.getRight(), prefix + "    ", sb);
    }

    private String text(NodeTree node) {
        return node.getMessage().split(",", 2)[1];
    }

}