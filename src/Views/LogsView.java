package Views;

import Controllers.FicheirosLogController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class LogsView {
    private static final String caminhoPasta = "src/Data/Logs";

    public static <caminhoPasta> void listarLogs() {
        Path caminho = Paths.get(caminhoPasta);

        System.out.println("━━━━━━━ Ficheiros Log ━━━━━━━");
        if (Files.exists(caminho) && Files.isDirectory(caminho)) {
            try (Stream<Path> stream = Files.list(caminho)) {
                stream.filter(Files::isRegularFile).sorted().forEach(file -> System.out.println(file.getFileName()));

            } catch (IOException e) {
                System.err.println("Erro ao listar arquivo: " + caminhoPasta);
            }
        } else {
            System.err.println("A pasta especificada no caminho não existe.");
        }
    }

    public static void mostrarLog(String ficheiroInserido) {
        Path caminhoFicheiro = Paths.get(caminhoPasta, ficheiroInserido);

        if (Files.exists(caminhoFicheiro) && Files.isRegularFile(caminhoFicheiro)) {
            try {
                System.out.println("━━━━━━━ " + ficheiroInserido + " ━━━━━━━");
                System.out.println(FicheirosLogController.lerFicheiroLog(caminhoFicheiro));
            } catch (IOException e) {
                System.err.println("Erro ao ler arquivo: " + caminhoFicheiro);
            }

        } else{
            System.err.println("O ficheiro:" + ficheiroInserido + " não existe na pasta!");
        }
    }
}
