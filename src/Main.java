import Controllers.*;
import Models.ClienteReserva;
import Models.Prato;
import Models.Mesa;

import Views.ClienteReservaView;
import Views.Menu;
import Views.MesaView;
import Views.PratoView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MesaController mesaController = new MesaController();
        PratoController pratoController = new PratoController();
        ClienteReservaController crc = new ClienteReservaController();

        // Passando os arrays diretamente para o construtor
        SimulacaoDiaADia simulacao = new SimulacaoDiaADia(
                MesaController.getMesas(),
                ClienteReservaController.getReservas(),
                PratoController.getPratos()
        );
        simulacao.iniciarSimulacao();
    }
}



