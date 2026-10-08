package co.edu.uptc.presentation;

import co.edu.uptc.business.Game;
import co.edu.uptc.business.NodeTree;
import co.edu.uptc.business.TreeBinaryQuestion;
import co.edu.uptc.libraries.DoublyLinkedList;
import co.edu.uptc.data.*;

public class ConsoleView {
    private ConsoleMenu menu;
    private TreeBinaryQuestion treeBinary;
    private DoublyLinkedList<NodeTree> list;
    private Game game;
    private Utils utils;
    private FilePersistance file; 

    public ConsoleView() {
        menu = new ConsoleMenu();
        treeBinary = new TreeBinaryQuestion();
        file = new FilePersistance(); 
        list = new DoublyLinkedList<>();
        game = new Game(treeBinary);
        utils = new Utils(); 
    }

    private void game() {
        if (!game.hasTree()) {
            System.out.println("El árbol está vacío.");
            return;
        }
        game.startGame();
        while (!game.isLeaf()) {
            String option = utils.read(game.getCurrentText() + " (si/no): ");
            if (option.equalsIgnoreCase("si") || option.equalsIgnoreCase("no")) {
                game.advance(utils.yesOrNot(option));
            } else {
                System.out.println("Responde 'si' o 'no'.");
            }
        }
        String guess = utils.read("¿Estás pensando en " + game.getCurrentText() + "? (si/no): ");
        if (utils.yesOrNot(guess)) {
            System.out.println("¡Adiviné!");
        } else {
            String correctAnswer = utils.read("¿En qué estabas pensando? ");
            String correctQuestion = utils.read("¿Qué pregunta diferencia lo que dije de lo que pensabas? ");
            game.guessCharacter(correctAnswer, correctQuestion);
        }
    }

    public void menu() {
        int option;
        do {
            option = menu.menu();
            switch (option) {
                case 1:
                if (treeBinary.getRoot() == null) {
                    list = file.readFile(); 
                    treeBinary.insert(list);
                }
                game();
                file.saveTree(treeBinary);
                break;
                case 2:
                    treeBinary.preOrder();
                    break;
                case 3:
                    treeBinary.inOrder();
                    break;
                case 4:
                    treeBinary.postOrder();
                    break;
                case 5:
                    System.out.println(treeBinary.show());
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Ingrese una opción valida");
                    break;
            }
        } while (option != 6);

    }
}
