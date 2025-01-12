import Controllers.ClienteReservaController;
import Controllers.LeituraFicheirosController;
import Controllers.MesaController;
import Controllers.PratoController;
import Models.ClienteReserva;
import Views.ClienteReservaView;
import Views.Menu;
import Views.MesaView;
import Views.PratoView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menu();
    }
}