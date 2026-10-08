package co.edu.uptc.data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.libraries.DoublyLinkedList;
import co.edu.uptc.business.*;
import lombok.Getter;

@Getter
public class FilePersistance {
    private DoublyLinkedList<NodeTree> list;

    public FilePersistance() {
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

    public void classify(String line) {
        String[] lines = line.split(",");
        NodeTree node = new NodeTree(line);
        if (lines[0].equals("PREGUNTA")) {
            node.setQuestion(true);
        } else {
            node.setQuestion(false);
        }
        list.addTheEnd(node);
    }

    // REVISAR DONDE PONER EL NOMBRE DEL ARCHIVO
    public DoublyLinkedList<NodeTree> readFile() {
        FilePersistance file = new FilePersistance();
        file.readFile("tree.csv");
        return file.getList();
    }

    public void saveTree(TreeBinaryQuestion tree) {
        List<String> lines = new ArrayList<>();
        collect(tree.getRoot(), lines);
        try {
            Files.write(Path.of("tree.csv"), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    private void collect(NodeTree node, List<String> lines) {
        if (node == null)
            return;
        lines.add(node.getMessage());
        collect(node.getLeft(), lines);
        collect(node.getRight(), lines);
    }

    public void reset() {
        try {
            Files.deleteIfExists(Path.of("tree.csv"));
        } catch (IOException e) {
            System.err.println("Error al reiniciar: " + e.getMessage());
        }
    }
}
