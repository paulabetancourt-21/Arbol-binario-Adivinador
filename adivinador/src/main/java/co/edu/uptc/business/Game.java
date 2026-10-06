package co.edu.uptc.business;

import co.edu.uptc.presentation.Utils;

public class Game {
    private TreeBinaryQuestion treeBinary;
    private Utils util;

    public Game(TreeBinaryQuestion tree) {
        treeBinary = tree; 
        util = new Utils(); 
    }

    // PRUEBAAAAAAAA
    public void starGame() {
        NodeTree current = treeBinary.getRoot();
        if (current == null) {
            System.out.println("El árbol está vacío.");
            return;
        }
        while (!current.isLeaf()) {
            String option = util.read(current.getMessage());
            if (option.equalsIgnoreCase("si")) {
                current = current.getLeft();
            } else if (option.equalsIgnoreCase("no")) {
                current = current.getRight();
            } else {
                System.out.println("Respuesta no válida. Por favor responde 'si' o 'no'.");
            }
        }
        System.out.println("¿Estas pensando en: " + current.getMessage().split(",")[1] + " ?");
    }
}
