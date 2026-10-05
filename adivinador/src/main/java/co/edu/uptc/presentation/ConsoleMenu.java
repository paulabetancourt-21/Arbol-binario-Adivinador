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
            " 2. Mostrar en postOrder\n" +
            " 4. Salir\n" +
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
