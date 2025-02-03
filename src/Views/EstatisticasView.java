package Views;

import Controllers.EstatisticasController;
import Controllers.MesaController;
import Models.Mesa;

import java.util.Scanner;

public class EstatisticasView {
    static Scanner sc = new Scanner(System.in);

    public static void pratoMaisPedido(){
        System.out.println("Prato Mais pedido: " + EstatisticasController.pratoMaisPedido().getNome());
    }

    public static void totalClienteAtendidos(){
        System.out.println("Total Cliente Atendidos: " + EstatisticasController.totalClientesAtendidos());
    }

    public static void tempoMedioEspera(){
        System.out.println("Intervalo Inicial: ");
        int intervaloInicial = sc.nextInt();
        System.out.println("Intervalo Final: ");
        int intervaloFinal = sc.nextInt();

        System.out.println("Total Cliente Atendidos Intervalo: " + EstatisticasController.totalClientesAtendidosIntervalo(intervaloInicial,intervaloFinal));
    }

    public static void tempoMedioEsperaAtendimentoMesa(){
        double [] tempoMedioMesa = EstatisticasController.tempoMedioEsperaAtendimentoMesa();
        Mesa [] mesas = MesaController.getMesas();
        for(int i = 0; i < mesas.length; i++){
            System.out.println("Mesa id: " + mesas[i].getIdMesa() + " | Tempo Medio: " + tempoMedioMesa[i+1]);
        }
    }

    public static void tempoTotalEsperaMesa(){
        double [] tempoMedioMesa = EstatisticasController.tempoMedioTotalMesa();
        Mesa [] mesas = MesaController.getMesas();

        for(int i = 0; i < mesas.length; i++){
            System.out.println("Mesa id: " + mesas[i].getIdMesa() + " | Tempo Medio: " + tempoMedioMesa[i+1]);
        }
    }
}
