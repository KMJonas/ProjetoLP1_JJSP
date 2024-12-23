import Controllers.ClienteReservaController;
import Controllers.LeituraFicheirosController;
import Models.ClienteReserva;
import Views.ClienteReservaView;
import Views.MesaView;
import Views.PratoView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ClienteReservaView crv = new ClienteReservaView();
        MesaView mv = new MesaView();
        PratoView pv = new PratoView();

        System.out.println("━━━━━━━ Reservas ━━━━━━━");
        crv.mostrarClientesReserva();
        System.out.println("━━━━━━━ Mesas ━━━━━━━");
        mv.mostrarMesas();
        System.out.println("━━━━━━━ Pratos ━━━━━━━");
        pv.mostrarPratos();

    }
}