package Views;

import Controllers.ClienteReservaController;
import Models.ClienteReserva;

public class ClienteReservaView {
    ClienteReservaController crc = new ClienteReservaController();

    public void mostrarClientesReserva() {
        ClienteReserva[] clientes = crc.getReservas();
        for(int i = 0; i < clientes.length; i++) {
            if(clientes[i] != null) {
                System.out.println("➤ Nome cliente: " + clientes[i].getNome());
                System.out.println("➤ Numero de pessoas: " + clientes[i].getNumPessoas());
                System.out.println("➤ Hora de chegada: " + clientes[i].getHoraChegada());
                System.out.println("➤ Tempo maximo de espera: " + clientes[i].getTempoMaxEspera());
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            }
        }
    }

}
