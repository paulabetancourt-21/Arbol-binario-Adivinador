package co.edu.uptc.presentation;

public class ConsoleMenu {
    private Utils reader; 

    public ConsoleMenu(){
        reader = new Utils(); 
    }

    public int menu(){
        String menu =
            "\n-------------------------------------\n" +
            "            Arbol binario\n" +
            "-------------------------------------\n" +
            " 1. Iniciar juego\n" +
            " 2. Mostrar en preOrder\n" +
            " 3. Mostrar en inOrder\n" +
            " 4. Mostrar en postOrder\n" +
            " 5. Mostrar arbol\n" +
            " 6. Contar hojas y preguntas\n" +
            " 7. Restablecer al estado por defecto\n" +
            " 8. Salir\n" +
            "-------------------------------------\n" +
            "Seleccione una opción: ";
        return reader.readInt(menu);
    }

    
    public int menu2(){
        String menu =
            "\n-------------------------------------\n" +
            "            Arbol binario\n" +
            "-------------------------------------\n" +
            " 1. Iniciar sesión de juego.\n" +
            " 2. Desplegar la estructura mediante recorridos jerárquicos\n" +
            " 3. Calcular la cantidad total de soluciones (nodos hoja) e interrogantes (nodos internos).\n" +
            " 4. Restablecer la información al estado por defecto. \n" +
            " 5. Salir\n" +
            "-------------------------------------\n" +
            "Seleccione una opción: ";
        return reader.readInt(menu);
    }
}
