package co.edu.uptc.business;

import co.edu.uptc.libraries.DoublyLinkedList;
import lombok.Getter;

@Getter
public class TreeBinaryQuestion {
    private NodeTree root;

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

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(NodeTree node) {
        if (node == null) {
            return;
        }
        System.out.println(node.getMessage().split(",")[1]);
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(NodeTree node) {
        if (node == null) {
            return;
        }
        inOrder(node.getRight());
        System.out.println(node.getMessage().split(",")[1]);
        inOrder(node.getLeft());
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(NodeTree node) {
        if (node == null) {
            return;
        }
        postOrder(node.getRight());
        postOrder(node.getLeft());
        System.out.println(node.getMessage().split(",")[1]);
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