package Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
}
