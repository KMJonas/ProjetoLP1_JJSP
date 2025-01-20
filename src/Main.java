import Controllers.ClienteReservaController;
import Controllers.LeituraFicheirosController;
import Controllers.MesaController;
import Controllers.PratoController;
import Models.ClienteReserva;
import Models.GlobalStorage;
import Views.ClienteReservaView;
import Views.Menu;
import Views.MesaView;
import Views.PratoView;

import java.text.ParseException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ParseException {
        LeituraFicheirosController lfc = new LeituraFicheirosController();
        lfc.devolverConfigGerais();
        PratoController pt = new PratoController();
        ClienteReservaController cr = new ClienteReservaController();
        MesaController mc = new MesaController();
        Menu menu = new Menu();
        menu.menu();
    }
}