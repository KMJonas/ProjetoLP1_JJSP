package Controllers;

import Models.ClienteReserva;
import Models.Mesa;
import Models.Prato;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ClienteReserva[] devolverClientesReserva(String path) {
        String[] lista = devolverConteudoFicheiro(path);
        ClienteReserva[] clientes = new ClienteReserva[100];
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null && !lista[i].trim().isEmpty()) {
                String[] cliente = lista[i].split("//");
                clientes[i] = new ClienteReserva(Integer.parseInt(cliente[0]), cliente[1], Integer.parseInt(cliente[2]), Integer.parseInt(cliente[3]), Integer.parseInt(cliente[4]), Integer.parseInt(cliente[5]), Integer.parseInt(cliente[6]), Integer.parseInt(cliente[7]));
            }
        }
        return clientes;
    }

    public Mesa[] devolverMesas(String path) {
        String[] lista = devolverConteudoFicheiro(path);
        Mesa[] mesas = new Mesa[100];
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null && !lista[i].trim().isEmpty()) {
                String[] mesa = lista[i].split("//");
                mesas[i] = new Mesa(Integer.parseInt(mesa[0]), Integer.parseInt(mesa[1]), Integer.parseInt(mesa[2]));
            }
        }
        return mesas;
    }

    public Prato[] devolverPratos(String path) {
        String[] lista = devolverConteudoFicheiro(path);
        Prato[] pratos = new Prato[100];
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null && !lista[i].trim().isEmpty()) {
                String[] prato = lista[i].split("//");
                pratos[i] = new Prato(Integer.parseInt(prato[0]), prato[1], prato[2], Double.parseDouble(prato[3]), Double.parseDouble(prato[4]), Integer.parseInt(prato[5]), Integer.parseInt(prato[6]));
            }
        }
        return pratos;
    }

}
