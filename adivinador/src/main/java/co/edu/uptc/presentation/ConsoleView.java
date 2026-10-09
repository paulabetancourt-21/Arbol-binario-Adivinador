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
            game.advance(utils.yesOrNot(game.getCurrentText()));
        }
        if (utils.yesOrNot("¿Estás pensando en " + game.getCurrentText() + "?")) {
            System.out.println("¡Adiviné!");
        } else {
            String correctAnswer = utils.readNotEmpty("¿En qué estabas pensando? ");
            String correctQuestion = utils.readNotEmpty("Escribe una pregunta que se responda 'si' para " + correctAnswer+ " y 'no' para " + game.getCurrentText() + ": ");
            game.guessCharacter(correctAnswer, correctQuestion);
        }
    }

    private void loadTree() {
        list = file.readFile();
        treeBinary.insert(list);
    }

    public void menu() {
        loadTree();
        int option;
        do {
            option = menu.menu();
            switch (option) {
                case 1:
                    game();
                    file.saveTree(treeBinary);
                    break;
                case 2:
                    System.out.println(treeBinary.preOrder());
                    break;
                case 3:
                    System.out.println(treeBinary.inOrder());
                    break;
                case 4:
                    System.out.println(treeBinary.postOrder());
                    break;
                case 5:
                    System.out.println(treeBinary.show());
                    break;
                case 6:
                    treeBinary.iterateAndCount();
                    System.out.println("Hojas: " + treeBinary.getLeaf());
                    System.out.println("Preguntas: " + treeBinary.getQuestion());
                    break;
                case 7:
                    file.reset();
                    treeBinary = new TreeBinaryQuestion();
                    game = new Game(treeBinary);
                    loadTree();
                    System.out.println("Juego reiniciado.");
                    break;
                case 8:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Ingrese una opción valida");
                    break;
            }
        } while (option != 8);

    }
}
