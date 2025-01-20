package Controllers;

import Models.GlobalStorage;
import Models.Mesa;
import Models.Prato;

import java.text.ParseException;

public class PratoController {
    static LeituraFicheirosController lf = new LeituraFicheirosController();

    //Array global que irá conter todos os pratos
    static Prato[] pratos;

    public PratoController() throws ParseException {
        lerPratos();
    }

    public static void lerPratos() throws ParseException {
        pratos = lf.devolverPratos(GlobalStorage.getPathPratos());
    }

    public static Prato[] getPratos() {
        return pratos;
    }

    public static void setPratos(Prato[] pratos) {
        PratoController.pratos = pratos; // Usa o nome da classe para referenciar o atributo estático
    }


    public static Prato[] adicionarPrato(int idPrato, String nome, String categoria, double precoCusto, double precoVenda, int unidadeTempoPreparacao, int unidadeTempoConsumo) {
        for (int i = 0; i < pratos.length; i++) {
            if (pratos[i] == null) {
                pratos[i] = new Prato(idPrato, nome, categoria, precoCusto, precoVenda, unidadeTempoPreparacao,unidadeTempoConsumo, 1);
                break;
            }
        }
        return pratos;
    }

    public static boolean confirmarCategoriaPrato(String categoria){
        if(categoria.equals("Entrada") || categoria.equals("Prato principal") || categoria.equals("Sobremesa")){
            return true;
        }
    return false;
    }

    public static boolean confirmarPreco(double preco) {
        if (preco > 0) {
            return true;
        }
        return false;
    }

    public static boolean modificarPrato(Prato prato){
        Prato[] listaPratos = getPratos();
        for (int i = 0; i < listaPratos.length; i++) {
            if (listaPratos[i] != null && listaPratos[i].getIdPrato() == prato.getIdPrato()) {
                listaPratos[i].setNome(prato.getNome());
                listaPratos[i].setCategoria(prato.getCategoria());
                listaPratos[i].setPrecoCusto(prato.getPrecoCusto());
                listaPratos[i].setPrecoVenda(prato.getPrecoVenda());
                listaPratos[i].setunidadeTempoPreparacao(prato.getunidadeTempoPreparacao());
                listaPratos[i].setEstado(prato.getEstado());
                return true;
            }
        }
        return false;
    }

    public static void removerPrato(int idPrato){
        Prato[] listaPratos = getPratos();

        int indexRemover = 0;
        for (int i = 0; i < listaPratos.length; i++) {
            if (listaPratos[i] != null && listaPratos[i].getIdPrato() == idPrato) {
                indexRemover = i;
                break;
            }
        }

        for (int i = indexRemover; i < listaPratos.length - 1; i++) {
            listaPratos[i] = listaPratos[i + 1];
            if (listaPratos[i] != null) {
                listaPratos[i].setIdPrato(i + 1);  // Atualiza o ID da mesa para refletir a nova posição
            }
        }

        listaPratos[listaPratos.length - 1] = null;
    }

    public static boolean verificarPrato(int idPrato) {
        Prato[] listaPratos = getPratos();

        if (idPrato < 1 || idPrato > listaPratos.length) {
            return false;
        }

        for (int i = 0; i < listaPratos.length; i++) {
            if (listaPratos[i] != null && listaPratos[i].getIdPrato() == idPrato) {
                return true;
            }
        }

        return false;
    }
}
