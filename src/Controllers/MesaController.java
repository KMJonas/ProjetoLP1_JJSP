package Controllers;

import Models.Mesa;

public class MesaController {
    static LeituraFicheirosController lf = new LeituraFicheirosController();
    static private String pathLeituraMesa = null;

    //Array global que irá conter todas as mesas
    static Mesa[] mesas;

    //Construtor que irá ler todas as mesas
    public MesaController(){

    }

    public static void lerMesas(String path){
        mesas = lf.devolverMesas(path);
        if(mesas[0].getCapacidade() != 0){
            pathLeituraMesa = path;
        }
    }

    public static Mesa[] getMesas() {
        return mesas;
    }

    public static void setMesas(Mesa[] mesas) {
        MesaController.mesas = mesas;
    }

    public static String getPathLeituraMesa() {
        return pathLeituraMesa;
    }

    public static Mesa[] adicionarMesa(int capacidade) {
        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i] == null) {
                mesas[i] = new Mesa(i + 1, capacidade, 1);
                break;
            }
        }
        return mesas;
    }

    public static boolean verificarMesa(int numMesa) {
        Mesa[] listaMesas = getMesas();

        if (numMesa < 1 || numMesa > listaMesas.length) {
            return false;
        }

        for (int i = 0; i < listaMesas.length; i++) {
            if (listaMesas[i] != null && listaMesas[i].getIdMesa() == numMesa) {
                return true;
            }
        }

        return false;
    }


    public static boolean modificarMesa (int numMesa, int opcao, int capacidade, int status) {
        Mesa[] listaMesas = getMesas();
        Mesa mesaModificada = null;

        if (opcao == 1){
            for (int i = 0; i < mesas.length; i++) {
                if (listaMesas[i].getIdMesa() == numMesa) {
                    listaMesas[i].setCapacidade(capacidade);
                    return true;
                }
            }
        } else if (opcao == 2) {
            for (int i = 0; i < listaMesas.length; i++) {
                if (listaMesas[i].getIdMesa() == numMesa) {
                    listaMesas[i].setStatus(status);
                    return true;
                }
            }
        } else if (opcao == 3) {
            for (int i = 0; i < listaMesas.length; i++) {
                if (listaMesas[i].getIdMesa() == numMesa) {
                    listaMesas[i].setCapacidade(capacidade);
                    listaMesas[i].setStatus(status);
                    return true;
                }
            }
        }

        return false;
    }

    public static void removerMesa(int numMesa){
        Mesa[] listaMesas = getMesas();

        int indexRemover = 0;
        for (int i = 0; i < listaMesas.length; i++) {
            if (listaMesas[i] != null && listaMesas[i].getIdMesa() == numMesa) {
                indexRemover = i;
                break;
            }
        }

        for (int i = indexRemover; i < listaMesas.length - 1; i++) {
            listaMesas[i] = listaMesas[i + 1];
            if (listaMesas[i] != null) {
                listaMesas[i].setIdMesa(i + 1);  // Atualiza o ID da mesa para refletir a nova posição
            }
        }

        listaMesas[listaMesas.length - 1] = null;
    }
}
