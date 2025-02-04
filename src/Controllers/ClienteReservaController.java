package Controllers;

import Models.ClienteReserva;
import Models.GlobalStorage;
import Models.Prato;

public class ClienteReservaController {
    static LeituraFicheirosController lf = new LeituraFicheirosController();

    //Array global que irá conter todas as reservas
    static ClienteReserva [] reservas;

    public ClienteReservaController() {
        lerReservas();
    }

    public static void lerReservas(){
        reservas = lf.devolverClientesReserva(GlobalStorage.getPathClientesReserva());
    }

    public static ClienteReserva[] getReservas() {
        return reservas;
    }

    public static void setReservas(ClienteReserva[] reservas) {
        ClienteReservaController.reservas = reservas;
    }

    public static ClienteReserva[] adicionarReserva(String nome, int numPessoas, int horaChegada) {
        for (int i = 0; i < reservas.length; i++) {
            if (reservas[i] == null) {
                reservas[i] = new ClienteReserva(i+1, nome, numPessoas, horaChegada);
                break;
            }
        }
        return reservas;
    }

    public static boolean modificarReserva(ClienteReserva reserva){
        ClienteReserva[] listaReservas = getReservas();

        for (int i = 0; i < listaReservas.length; i++) {
            if (listaReservas[i] != null && listaReservas[i].getIdReserva() == reserva.getIdReserva()) {
                listaReservas[i].setNome(reserva.getNome());
                listaReservas[i].setNumPessoas(reserva.getNumPessoas());
                listaReservas[i].setHoraChegada(reserva.getHoraChegada());
                return true;
            }
        }
        return false;
    }

    public static void removerReserva(int idReserva){
        ClienteReserva[] listaReservas = getReservas();

        int indexRemover = 0;
        for (int i = 0; i < listaReservas.length; i++) {
            if (listaReservas[i] != null && listaReservas[i].getIdReserva() == idReserva) {
                indexRemover = i;
                break;
            }
        }

        for (int i = indexRemover; i < listaReservas.length - 1; i++) {
            listaReservas[i] = listaReservas[i + 1];
            if (listaReservas[i] != null) {
                listaReservas[i].setIdReserva(i + 1);  // Atualiza o ID da mesa para refletir a nova posição
            }
        }

        listaReservas[listaReservas.length - 1] = null;
    }

    public static boolean verificarReserva(int idReserva) {
        ClienteReserva[] listaReservas = getReservas();

        if (idReserva < 1 || idReserva > listaReservas.length) {
            return false;
        }

        for (int i = 0; i < listaReservas.length; i++) {
            if (listaReservas[i] != null && listaReservas[i].getIdReserva() == idReserva) {
                return true;
            }
        }

        return false;
    }

    public static ClienteReserva buscarReservaPorId(int id) {
        for (int i = 0; i < reservas.length; i++) {
            if (reservas[i] != null && reservas[i].getIdReserva() == id) {
                return reservas[i];
            }
        }
        return null;
    }


}