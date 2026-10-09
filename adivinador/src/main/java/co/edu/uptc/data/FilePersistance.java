package co.edu.uptc.data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import co.edu.uptc.libraries.DoublyLinkedList;
import co.edu.uptc.business.*;
import lombok.Getter;

@Getter
public class FilePersistance {
    private static final String CONFIG = "config.properties";
    private static final String DEFAULT_FILE = "tree.csv";
    private final String fileName;
    private DoublyLinkedList<NodeTree> list;

    public FilePersistance() {
        list = new DoublyLinkedList<>();
        fileName = loadFileName();
    }

    private String loadFileName() {
        Properties props = new Properties();
        Path path = Path.of(CONFIG);
        if (Files.exists(path)) {
            try (InputStream is = Files.newInputStream(path)) {
                props.load(is);
            } catch (IOException e) {
                System.err.println("Error al leer la configuración: " + e.getMessage());
            }
        }
        return props.getProperty("file.name", DEFAULT_FILE);
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
        InputStream is = getClass().getResourceAsStream("/" + name);
        if (is != null) {
            return is;
        }
        System.err.println("No se encontró " + name + ", se carga el archivo por defecto.");
        is = getClass().getResourceAsStream("/" + DEFAULT_FILE);
        if (is == null) {
            throw new FileNotFoundException("No se encontró el archivo por defecto: " + DEFAULT_FILE);
        }
        return is;
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

    public DoublyLinkedList<NodeTree> readFile() {
        FilePersistance file = new FilePersistance();
        file.readFile(fileName);
        return file.getList();
    }

    public void saveTree(TreeBinaryQuestion tree) {
        List<String> lines = new ArrayList<>();
        collect(tree.getRoot(), lines);
        try {
            Files.write(Path.of(fileName), lines, StandardCharsets.UTF_8);
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
            Files.deleteIfExists(Path.of(fileName));
        } catch (IOException e) {
            System.err.println("Error al reiniciar: " + e.getMessage());
        }
    }
}
