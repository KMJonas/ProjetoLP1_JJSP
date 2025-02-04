package Controllers;

import Models.ClienteReserva;
import Models.GlobalStorage;
import Models.Mesa;
import Models.Prato;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class LeituraFicheirosController {

    public String[] devolverConteudoFicheiro(String path) {
        String[] lista = new String[100];
        int index = 0;
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String line = bf.readLine();
            while (line != null) {
                lista[index] = line;
                index++;
                line = bf.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("⚠ Ficheiro não encontrado ⚠");
        } catch (IOException e) {
            System.out.println("⚠ Ocorreu um erro ao ler o ficheiro ⚠");
        }
        return lista;
    }

    public ClienteReserva[] devolverClientesReserva(String path) {
        String[] lista = devolverConteudoFicheiro(path);
        ClienteReserva[] clientes = new ClienteReserva[100];
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null && !lista[i].trim().isEmpty()) {
                String[] cliente = lista[i].split(GlobalStorage.getSeparadorConteudo());
                String nomeReserva = cliente[0];
                int numPessoas = Integer.parseInt(cliente[1]);
                int horaChegada = Integer.parseInt(cliente[2]);
                clientes[i] = new ClienteReserva(i+1, nomeReserva, numPessoas, horaChegada);
            }
        }
        return clientes;
    }

    public Mesa[] devolverMesas(String path) {
        String[] lista = devolverConteudoFicheiro(path);
        Mesa[] mesas = new Mesa[100];
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null && !lista[i].trim().isEmpty()) {
                String[] mesa = lista[i].split(GlobalStorage.getSeparadorConteudo());
                int idMesa = Integer.parseInt(mesa[0]);
                int capacidade = Integer.parseInt(mesa[1]);
                mesas[i] = new Mesa(idMesa, capacidade, 1);
            }
        }
        return mesas;
    }

    public Prato[] devolverPratos(String path) throws ParseException {
        String[] lista = devolverConteudoFicheiro(path);
        Prato[] pratos = new Prato[100];
        NumberFormat nf = NumberFormat.getInstance();

        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null && !lista[i].trim().isEmpty()) {
                String[] prato = lista[i].split(GlobalStorage.getSeparadorConteudo());
                String nomePrato = prato[0];
                String categoria = prato[1];
                double precoCusto = nf.parse(prato[2].replace(",", ".")).doubleValue();
                double precoVenda = nf.parse(prato[3].replace(",", ".")).doubleValue();
                int unidadeTempoPreparacao = Integer.parseInt(prato[4]);
                int unidadeTempoConsumo = Integer.parseInt(prato[5]);
                int estado = prato[6].equals("True") ? 1 : 0;
                pratos[i] = new Prato(i+1, nomePrato, categoria, precoCusto, precoVenda, unidadeTempoPreparacao, unidadeTempoConsumo, estado);
            }
        }
        return pratos;
    }

    public GlobalStorage devolverConfigGerais(){
        String[] lista = devolverConteudoFicheiro("src/Data/ConfigGerais");
        String pathMesas = lista[0];
        String pathPratos = lista[1];
        String pathClientesReserva = lista[2];
        String passwordConfig = lista[3];
        String separadorConteudo = lista[4];
        return new GlobalStorage(pathMesas, pathPratos, pathClientesReserva, passwordConfig, separadorConteudo);
    }

    public boolean guardarClientesReserva() {
        ClienteReserva[] clientes = ClienteReservaController.getReservas();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GlobalStorage.getPathClientesReserva()))) {
            for (int i = 0; i < clientes.length; i++) {
                if (clientes[i] != null) {
                    String linha = clientes[i].getNome() + GlobalStorage.getSeparadorConteudo() +
                            clientes[i].getNumPessoas() + GlobalStorage.getSeparadorConteudo() +
                            clientes[i].getHoraChegada();
                    bw.write(linha);
                    bw.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("⚠ Ocorreu um erro ao escrever no ficheiro ⚠");
        }
        return false;
    }

    public boolean guardarMesas() {
        Mesa[] mesas = MesaController.getMesas();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GlobalStorage.getPathMesas()))) {
            for (int i = 0; i < mesas.length; i++) {
                if (mesas[i] != null) {
                    String linha = mesas[i].getIdMesa() + GlobalStorage.getSeparadorConteudo() +
                            mesas[i].getCapacidade();
                    bw.write(linha);
                    bw.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("⚠ Ocorreu um erro ao escrever no ficheiro ⚠");
        }
        return false;
    }

    public boolean guardarPratos() {
        Prato[] pratos = PratoController.getPratos();
        DecimalFormat df = new DecimalFormat("0.00");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GlobalStorage.getPathPratos()))) {
            for (int i = 0; i < pratos.length; i++) {
                if (pratos[i] != null) {
                    String linha = pratos[i].getNome() + GlobalStorage.getSeparadorConteudo() +
                            pratos[i].getCategoria() + GlobalStorage.getSeparadorConteudo() +
                            df.format(pratos[i].getPrecoCusto()).replace('.', ',') + GlobalStorage.getSeparadorConteudo() +
                            df.format(pratos[i].getPrecoVenda()).replace('.', ',') + GlobalStorage.getSeparadorConteudo() +
                            pratos[i].getUnidadeTempoPreparacao() + GlobalStorage.getSeparadorConteudo() +
                            pratos[i].getUnidadeTempoConsumo() + GlobalStorage.getSeparadorConteudo() +
                            (pratos[i].getEstado() == 1 ? "True" : "False");
                    bw.write(linha);
                    bw.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("⚠ Ocorreu um erro ao escrever no ficheiro ⚠");
        }
        return false;
    }

    public boolean guardarConfigGerais() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/Data/ConfigGerais"))) {
            String linha = GlobalStorage.getPathMesas();
            bw.write(linha);
            bw.newLine();
            linha = GlobalStorage.getPathPratos();
            bw.write(linha);
            bw.newLine();
            linha = GlobalStorage.getPathClientesReserva();
            bw.write(linha);
            bw.newLine();
            linha = GlobalStorage.getPasswordConfiguracao();
            bw.write(linha);
            bw.newLine();
            linha = GlobalStorage.getSeparadorConteudo();
            bw.write(linha);
            return true;
        } catch (IOException e) {
            System.out.println("⚠ Ocorreu um erro ao escrever no ficheiro ⚠");
        }
        return false;
    }
}
