package Controllers;

import Models.GlobalStorage;
import Models.Mesa;

public class MesaController {
    static LeituraFicheirosController lf = new LeituraFicheirosController();

    //Array global que irá conter todas as mesas
    static Mesa[] mesas;

    //Construtor que irá ler todas as mesas
    public MesaController(){
        lerMesas();
    }

    public static void lerMesas(){
        mesas = lf.devolverMesas(GlobalStorage.getPathMesas());
    }

    public static Mesa[] getMesas() {
        return mesas;
    }

    public static void setMesas(Mesa[] mesas) {
        MesaController.mesas = mesas;
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
        Mesa mesaModificada = buscarMesaPorId(numMesa);

        if (opcao == 1){
            mesaModificada.setCapacidade(capacidade);
            return true;
        } else if (opcao == 2) {
            mesaModificada.setStatus(status);
            return true;
        } else if (opcao == 3) {
            mesaModificada.setCapacidade(capacidade);
            mesaModificada.setStatus(status);
            return true;
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

    public static Mesa buscarMesaPorId(int id) {
        for (Mesa mesa : mesas) {
            if (mesa != null && mesa.getIdMesa() == id) {
                return mesa;
            }
        }
        return null;
    }
}