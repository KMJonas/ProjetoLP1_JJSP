package Views;

import Controllers.MesaController;
import Models.ClienteReserva;
import Models.Mesa;

public class MesaView {
    MesaController mc = new MesaController();

    public void mostrarMesas() {
        Mesa[] mesas = mc.getMesas();
        for(int i = 0; i < mesas.length; i++) {
            if(mesas[i] != null) {
                System.out.println("➤ Mesa Nº: " + mesas[i].getIdMesa());
                System.out.println("➤ Capacidade: " + mesas[i].getCapacidade());
                System.out.println("➤ Status: " + mesas[i].getStatus());
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            }
        }
    }


}
