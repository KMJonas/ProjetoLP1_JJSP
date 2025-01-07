package Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FicheirosLogController {
    //private static final Path logsDir = Paths.get(dataDir.toAbsolutePath().toString() + "/src/Data/Logs");
    private static final String caminho = System.getProperty("user.dir");
    private static final Path logs = Paths.get(caminho + "/src/Data/Logs");

    public static void criaFicheirosLog() throws IOException {

        if (!Files.exists(logs)) {
            Files.createDirectory(logs);
        }

        try {
            String caminhoLog = caminho + "/src/Data/Logs";
            int numLog = contarFicheirosLog(caminhoLog);
            String nomeFicheiro = "FicheiroLog_";

            File ficheiro = new File(caminho + "/src/Data/Logs/" + nomeFicheiro + numLog + ".txt");

            if (!ficheiro.exists()) {
                System.out.println("Criar ficheiro");
                System.out.println(numLog);
                ficheiro.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escreverLog(String mensagem) throws IOException {
        File ultimoArquivo = obterUltimoFicheiroLog();

        if (ultimoArquivo != null) {
            // Tenta escrever no arquvio
            try (FileWriter fw = new FileWriter(ultimoArquivo, true)) {
                fw.write(mensagem);
                System.out.println("Escrevendo no arquivo");
            } catch (IOException e) {
                System.err.println("Erro ao escrever o arquivo");
            }
        } else {
            System.out.println("Nenhum arquivo encontrado");
        }
    }

    public static int contarFicheirosLog(String caminhoLog) throws IOException {
        File diretorio = new File(caminhoLog);

        if(diretorio.exists() && diretorio.isDirectory()){
            // Listar arquivos do diretorio
            File[] ficheiros = diretorio.listFiles();

            int contador = 0;

            if (ficheiros != null) {
                for (File f : ficheiros) {
                    if (f.isFile()) {
                        contador++;
                    }
                }
            }
            return contador;
        } else {
            return 0;
        }
    }

    public static File obterUltimoFicheiroLog() {
        File diretorio = new File(caminho + "/src/Data/Logs");

        if(diretorio.exists() && diretorio.isDirectory()){
            //Filtrar ficheiros
            File[] ficheirosLog = diretorio.listFiles((dir, name) -> name.endsWith(".txt"));

            if (ficheirosLog != null && ficheirosLog.length > 0) {
                Arrays.sort(ficheirosLog, (a,b) -> {
                    int comparar = Long.compare(b.lastModified(), a.lastModified());
                    if (comparar == 0) {
                        return a.getName().compareTo(b.getName());
                    }
                    return comparar;
                });

                // Retorna o primeiro arquivo (mais recente ou alfabetico no desempate)
                return ficheirosLog[0];
            }
        }

        System.out.println("Nenhum ficheiro encontrado");
        return null; // Retorna Null se nenhum arquivo for encontrado
    }
}
