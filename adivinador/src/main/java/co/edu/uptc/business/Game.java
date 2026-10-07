package co.edu.uptc.business;

public class Game {
    private TreeBinaryQuestion treeBinary;
    private NodeTree parent; 
    private boolean wentLeft; 
    private NodeTree current; 

    public Game(TreeBinaryQuestion tree) {
        treeBinary = tree;
    }

    public void startGame() {
        current = treeBinary.getRoot();
        parent = null;
        wentLeft = false;
    }

    public boolean hasTree() {
        return treeBinary.getRoot() != null;
    }

    public void advance(boolean answer) {
        parent = current;
        wentLeft = answer;
        current = answer ? current.getLeft() : current.getRight();
    }

    public String getCurrentText() {
        return current.getMessage().split(",", 2)[1];
    }

    public boolean isLeaf() {
        return current.isLeaf();
    }

    public void starGame(boolean answer) {
        while (!current.isLeaf()) {
            if (answer) {
                parent = current;
                wentLeft = true;
                current = current.getLeft();
            } else {
                parent = current;
                wentLeft = false;
                current = current.getRight();
            }
        }
    }

    public void guessCharacter(String correctAnswer, String correctQuestion) {
        NodeTree nodeQuestion = new NodeTree("PREGUNTA," + correctQuestion);
        nodeQuestion.setQuestion(true);
        NodeTree nodeAnswer = new NodeTree("PERSONAJE," + correctAnswer);
        nodeQuestion.setLeft(nodeAnswer);
        nodeQuestion.setRight(current);
        if (wentLeft) {
            parent.setLeft(nodeQuestion);
        } else {
            parent.setRight(nodeQuestion);
        }
    }

//      // PRUEBAAAAAAAA
//     public void starGame1() {
//         NodeTree current = treeBinary.getRoot();
//         NodeTree parent = null;
//         boolean wentLeft = false;
//         if (current == null) {
//             System.out.println("El árbol está vacío.");
//             return;
//         }
//         while (!current.isLeaf()) {
//             String option = util.read(current.getMessage().split(",")[1]);
//             if (option.equalsIgnoreCase("si")) {
//                 parent = current;
//                 wentLeft = true;
//                 current = current.getLeft();
//             } else if (option.equalsIgnoreCase("no")) {
//                 parent = current;
//                 wentLeft = false;
//                 current = current.getRight();
//             } else {
//                 System.out.println("Respuesta no válida. Por favor responde 'si' o 'no'.");
//             }
//         }

//         String answer = util.read("¿Estas pensando en: " + current.getMessage().split(",")[1] + " ?");
//         if (answer.equalsIgnoreCase("no")) {
//             String correctAnswer = util.read("¿En que estabas pensando? ");
//             String correctQuestion = util
//                     .read("¿Que pregunta hace la diferencia entre lo que te dije y lo que estabas pensando? ");
//             NodeTree nodeQuestion = new NodeTree("PREGUNTA," + correctQuestion);
//             nodeQuestion.setQuestion(true);
//             NodeTree nodeAnswer = new NodeTree("PERSONAJE," + correctAnswer);
//             nodeQuestion.setLeft(nodeAnswer);
//             nodeQuestion.setRight(current);
//             if (wentLeft) {
//                 parent.setLeft(nodeQuestion);
//             } else {
//                 parent.setRight(nodeQuestion);
//             }
//         } else {
//             System.out.println("Adivine");
//         }
//     }
}
