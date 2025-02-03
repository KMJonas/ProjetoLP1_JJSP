package Controllers;

import Views.LogsView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


public class FicheirosLogController {

    public static void criaFicheirosLog() throws IOException {
        Path logs = Paths.get("src/Data/Logs");

        if (!Files.exists(logs)) {
            Files.createDirectory(logs);
        }

        try {
            String caminhoLog = "src/Data/Logs";
            int numLog = contarFicheirosLog(caminhoLog) + 1;

            //Obter a data e formataala
            LocalDate data = LocalDate.now();
            DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String dataFormatada = data.format(dataFormat);

            String nomeFicheiro = dataFormatada + "-" + String.format("%02d", numLog) + ".txt";
            Path ficheiroLog = Paths.get(caminhoLog + "/" + nomeFicheiro );

            // Criar arquivo Log
            if (!Files.exists(ficheiroLog)) {
                Files.createFile(ficheiroLog);
                System.out.println("Ficheiro criado com sucesso!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escreverLog (int unidadeTempo ,  String mensagem) {
        File ultimoArquivo = obterUltimoFicheiroLog();

        if (ultimoArquivo != null) {
            try (FileWriter fw = new FileWriter(ultimoArquivo, true)) {
                if (ultimoArquivo.length() > 0) {
                    fw.write(System.lineSeparator());
                }
                fw.write("unidade " + unidadeTempo + ": " + mensagem + " ;");
            } catch (IOException e) {
                System.err.println("Erro ao escrever no ficheiro.");
            }
        } else {
            System.err.println("Erro ao escrever no ficheiro.");
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
        File diretorio = new File("src/Data/Logs");

        if(diretorio.exists() && diretorio.isDirectory()){
            //Filtrar ficheiros
            File[] ficheirosLog = diretorio.listFiles((_, name) -> name.endsWith(".txt"));

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

    public static void apagarLog (String ficheiroApagar) throws IOException {
        String caminhoLog = "src/Data/Logs";

        Path ficheiroSelecionado = Paths.get(caminhoLog + "/" + ficheiroApagar );

        if (Files.exists(ficheiroSelecionado) && Files.isRegularFile(ficheiroSelecionado)) {
            Files.delete(ficheiroSelecionado);
        } else {
            System.err.println("Ficheiro não encontrado.");
        }
    }

    public static String lerFicheiroLog (Path caminhoFicheiro) throws IOException {
        StringBuilder conteudo = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(String.valueOf(caminhoFicheiro)));
            String linha;

            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return conteudo.toString();
    }
}
