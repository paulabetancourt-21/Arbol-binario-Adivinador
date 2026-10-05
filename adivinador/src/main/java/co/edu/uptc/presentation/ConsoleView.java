package co.edu.uptc.presentation;

import co.edu.uptc.business.Game;
import co.edu.uptc.business.NodeTree;
import co.edu.uptc.business.TreeBinaryQuestion;
import co.edu.uptc.libraries.DoublyLinkedList;

public class ConsoleView {
    private ConsoleMenu menu; 
    private TreeBinaryQuestion treeBinary; 
    private DoublyLinkedList<NodeTree> list; 

    public ConsoleView(){
        menu = new ConsoleMenu(); 
        treeBinary = new TreeBinaryQuestion(); 
        list = new DoublyLinkedList<>(); 
    }

    public void menu(){
        int option; 
        do {
            option = menu.menu(); 
            switch (option) {
            case 1:
                list =  treeBinary.readFile(); 
                treeBinary.insert(list);
                Game game = new Game(); 
                game.starGame();
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
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Ingrese una opción valida");
                break;
            }
        } while (option!=5);
        
    }
}
