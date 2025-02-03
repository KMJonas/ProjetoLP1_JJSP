package Controllers;

import Models.Mesa;
import Models.Pedido;
import Models.Prato;

public class EstatisticasController {
    private static Mesa[] mesas = MesaController.getMesas();

    // 1. Prato mais pedido no menu
    public static Prato pratoMaisPedido() {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        if (pedidos == null || pedidos.length == 0) return null;

        Prato pratoMaisPedido = null;
        int maiorContagem = 0;

        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null && pedidos[i].getPratos() != null) { // Evita NullPointerException
                Prato[] pratosNoPedido = pedidos[i].getPratos();
                for (int j = 0; j < pratosNoPedido.length; j++) {
                    if (pratosNoPedido[j] != null) {
                        int contagemAtual = contarOcorrenciasPrato(pratosNoPedido[j]);
                        if (contagemAtual > maiorContagem) {
                            maiorContagem = contagemAtual;
                            pratoMaisPedido = pratosNoPedido[j];
                        }
                    }
                }
            }
        }
        return pratoMaisPedido;
    }
    public static int contarOcorrenciasPrato(Prato prato) {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        int contagem = 0;
        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null && pedidos[i].getPratos() != null) {
                Prato[] pratosNoPedido = pedidos[i].getPratos();
                for (int j = 0; j < pratosNoPedido.length; j++) {
                    if (pratosNoPedido[j] != null && pratosNoPedido[j].getIdPrato() == prato.getIdPrato()) {
                        contagem++;
                    }
                }
            }
        }
        return contagem;
    }
    // 2. Quantidade total de clientes atendidos por período
    public static int totalClientesAtendidos() {
        Pedido[] pedidos = PedidoController.getListaPedidos();

        if (pedidos == null) return 0;

        int totalClientes = 0;
        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null && pedidos[i].getStatus() == 7 && pedidos[i].getHoraFinalizacao() != 0) {
                totalClientes++;
            }
        }
        return totalClientes;
    }

    public static int totalClientesAtendidosIntervalo(int intervaloInicial, int intervaloFinal) {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        if (pedidos == null) return 0;

        int totalClientes = 0;
        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null && pedidos[i].getStatus() == 7 && pedidos[i].getHoraFinalizacao() != 0) {
                if (pedidos[i].getReserva().getHoraChegada() >= intervaloInicial && pedidos[i].getHoraFinalizacao() <= intervaloFinal) {
                    totalClientes++;
                }
            }
        }
        return totalClientes;
    }
    // 3. Tempo médio de espera por mesa
    public static double[] tempoMedioEsperaAtendimentoMesa() {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        if (pedidos == null) return null;

        int tempoTotal = 0;


        int[] temposMesas = new int[mesas.length];
        int[] contagemPedidosMesa = new int[mesas.length];

        for (int i = 1; i < mesas.length; i++) { //Mesa 1
            int pedidosAtendidos = 0;
            for (int j = 0; j < pedidos.length; j++) { //Rodar for todos pedidos
                if (pedidos[j] != null && pedidos[j].getmomentoEntregaPrato() != 0 && pedidos[j].getMesaAssociada().getIdMesa() == mesas[i].getIdMesa()) {
                    tempoTotal = tempoTotal + (pedidos[j].getmomentoEntregaPrato() - pedidos[j].getMomentoCriacao());
                    temposMesas[i] = tempoTotal;
                    pedidosAtendidos++;
                }
            }
            contagemPedidosMesa[i] = pedidosAtendidos;
        }

        double[] tempoTotalMedioMesas = new double[mesas.length];
        for (int i = 1; i <= mesas.length; i++) {
            tempoTotalMedioMesas[i] = temposMesas[i] / contagemPedidosMesa[i];
        }

        return tempoTotalMedioMesas;
    }
    // 4. Tempo médio total de serviço
    public static double[] tempoMedioTotalMesa() {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        if (pedidos == null) return null;

        int tempoTotal = 0;

        int[] temposMesas = new int[mesas.length];
        int[] contagemPedidosMesa = new int[mesas.length];

        for (int i = 1; i < mesas.length; i++) { //Mesa 1
            int pedidosAtendidos = 0;
            for (int j = 0; j < pedidos.length; j++) { //Rodar for todos pedidos
                if (pedidos[j] != null && pedidos[j].getStatus() == 7 && pedidos[j].getMesaAssociada().getIdMesa() == mesas[i].getIdMesa()) {
                    tempoTotal = tempoTotal + (pedidos[j].getHoraFinalizacao() - pedidos[j].getReserva().getHoraChegada());
                    temposMesas[i] = tempoTotal;
                    pedidosAtendidos++;
                }
            }
            contagemPedidosMesa[i] = pedidosAtendidos;
        }

        double[] tempoTotalMedioMesas = new double[mesas.length];
        for (int i = 1; i <= mesas.length; i++) {
            tempoTotalMedioMesas[i] = temposMesas[i] / contagemPedidosMesa[i];
        }

        return tempoTotalMedioMesas;
    }
}
