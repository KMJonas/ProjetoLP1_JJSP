import Controllers.*;
import Models.ClienteReserva;
import Models.Prato;
import Models.Mesa;

import Views.*;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;



public class Main {
    public static void main(String[] args) throws IOException {
        MesaController mesaController = new MesaController();
        PratoController pratoController = new PratoController();
        ClienteReservaController crc = new ClienteReservaController();

        Passando os arrays diretamente para o construtor
        SimulacaoDiaADia simulacao = new SimulacaoDiaADia(
                MesaController.getMesas(),
                ClienteReservaController.getReservas(),
                PratoController.getPratos()
        );
        simulacao.iniciarSimulacao();


    }

}