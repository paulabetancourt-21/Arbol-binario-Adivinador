package co.edu.uptc.data;

import java.io.*;
import java.nio.file.*;
import co.edu.uptc.business.NodeTree;
import co.edu.uptc.libraries.DoublyLinkedList;
import lombok.Getter;

@Getter 
public class FilePersistance {
    private DoublyLinkedList<NodeTree> list; 

    public FilePersistance(){
        list = new DoublyLinkedList<>(); 
    }

    public void readFile(String name) {
        try (InputStream is = open(name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            reader.lines().forEach(linea -> {
                classify(linea);
            });
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private InputStream open(String name) throws IOException {
        Path externo = Path.of(name);
        if (Files.exists(externo)) {
            return Files.newInputStream(externo);              
        }
        return getClass().getResourceAsStream("/" + name);   
    }  
    
    public void classify(String line){
        String[] lines = line.split(","); 
        NodeTree node = new NodeTree(line); 
            if (lines[0].equals("PREGUNTA")) {
                node.setQuestion(true);
            }else{
                node.setQuestion(false);
            }
        list.addTheEnd(node);
    }


}

