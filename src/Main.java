import Controllers.*;
import Models.ClienteReserva;
import Views.ClienteReservaView;
import Views.Menu;
import Views.MesaView;
import Views.PratoView;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        /*
        MesaController mesaController = new MesaController();
        PratoController pratoController = new PratoController();
        ClienteReservaController crc = new ClienteReservaController();

        Menu menu = new Menu();
        menu.menu();
         */

        FicheirosLogController ficheirosLogController = new FicheirosLogController();

        FicheirosLogController.criaFicheirosLog();
    }
}