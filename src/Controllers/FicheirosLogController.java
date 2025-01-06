package Controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FicheirosLogController {
    private static final Path dataDir = Paths.get("./src/Data");
    private static final Path logsDir = Paths.get(dataDir.toAbsolutePath().toString() + "/src/Data/Logs");

    public static void criaFicheirosLog() throws IOException {
        //Cria a pasta "Logs caso não exista"
        File pasta = new File(dataDir.toAbsolutePath().toString() + "/Logs");
        if (!pasta.exists()) {
            System.out.println("criando a subpasta");
            pasta.mkdir();
        }

        // Conta o numero de ficheiros
        int numLogs = contarNumeroLogs(pasta);
        String nomeFicheiro = "Ficheiro" + numLogs + ".txt";
        File log = new File(pasta.toString() + "/" + nomeFicheiro);
        String conteudo = "Ficheiro Log criado com sucesso";

        try (FileWriter fw = new FileWriter(log, true)) {
            fw.write(conteudo);
            fw.close();
        }
    }

    // Metodo para contar o nunero de ficheiros
    private static int contarNumeroLogs (File logsDir){
        File[] logs = logsDir.listFiles((dir, name) ->name.startsWith("FicheiroLog") && name.endsWith(".txt"));
        return logs == null ? 0 : logs.length;
    }
}
