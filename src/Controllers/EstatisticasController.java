package Controllers;

import Models.GlobalStorage;
import Models.Mesa;
import Models.Pedido;
import Models.Prato;

public class EstatisticasController {

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
        Mesa[] mesas = MesaController.getMesas();

        if (pedidos == null || pedidos.length == 0  || mesas == null || mesas.length == 0) return null;

        double[] tempoTotalMedioMesas = new double[mesas.length];
        int[] tempoTotal = new int[mesas.length];
        int[] contagemPedidosMesa = new int[mesas.length];

        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i] != null) {
                for (int j = 0; j < pedidos.length; j++) {
                    Pedido pedido = pedidos[j];
                    if (pedido != null &&
                            pedido.getMesaAssociada() != null &&
                            pedido.getmomentoEntregaPrato() > 0 &&
                            pedido.getMesaAssociada().getIdMesa() == mesas[i].getIdMesa()) {
                        tempoTotal[i] += pedido.getmomentoEntregaPrato() - pedido.getMomentoCriacao();
                        contagemPedidosMesa[i]++;
                    }
                }
                tempoTotalMedioMesas[i] = contagemPedidosMesa[i] > 0 ?
                        (double) tempoTotal[i] / contagemPedidosMesa[i] : 0;
            }
        }
        return tempoTotalMedioMesas;
    }




    // 4. Tempo médio total de serviço
    public static double[] tempoMedioTotalMesa() {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        Mesa[] mesas = MesaController.getMesas();
        if (pedidos == null || mesas == null) return null;

        double[] tempoTotalMedioMesas = new double[mesas.length];
        int[] tempoTotal = new int[mesas.length];
        int[] contagemPedidosMesa = new int[mesas.length];

        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i] != null) {
                for (int j = 0; j < pedidos.length; j++) {
                    if (pedidos[j] != null &&
                            pedidos[j].getMesaAssociada() != null &&
                            pedidos[j].getStatus() == 7 &&
                            pedidos[j].getReserva() != null &&
                            pedidos[j].getMesaAssociada().getIdMesa() == mesas[i].getIdMesa()) {

                        tempoTotal[i] += pedidos[j].getHoraFinalizacao() - pedidos[j].getReserva().getHoraChegada();
                        contagemPedidosMesa[i]++;
                    }
                }
                tempoTotalMedioMesas[i] = contagemPedidosMesa[i] > 0 ?
                        (double) tempoTotal[i] / contagemPedidosMesa[i] : 0;
            }
        }
        return tempoTotalMedioMesas;
    }

    //Parte de Gestão Financeira
    public static double calcularTotalFaturadoIntervalo(int intervaloInicial, int intervaloFinal) {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        double totalFaturado = 0;

        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null && pedidos[i].getHoraFinalizacao() != 0) {
                if (pedidos[i].getReserva().getHoraChegada() >= intervaloInicial &&
                        pedidos[i].getHoraFinalizacao() <= intervaloFinal) {

                    Prato[] pratos = pedidos[i].getPratos();
                    for (int j = 0; j < pratos.length; j++) {
                        if (pratos[j] != null) {
                            totalFaturado += pratos[j].getPrecoVenda();
                        }
                    }
                }
            }
        }
        return totalFaturado;
    }

    public static double calcularTotalFaturado() {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        double totalFaturado = 0;


        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null && pedidos[i].getHoraFinalizacao() != 0) {
                Prato[] pratos = pedidos[i].getPratos();
                for (int j = 0; j < pratos.length; j++) {
                    if (pratos[j] != null) {
                        totalFaturado += pratos[j].getPrecoVenda();
                    }
                }

            }
        }
        return totalFaturado;
    }

    public static double calcularTotalCustoIntervalo(int intervaloInicial, int intervaloFinal) {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        double totalCusto = 0;

        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null && pedidos[i].getHoraFinalizacao() != 0) {
                if (pedidos[i].getReserva().getHoraChegada() >= intervaloInicial &&
                        pedidos[i].getHoraFinalizacao() <= intervaloFinal) {

                    Prato[] pratos = pedidos[i].getPratos();
                    for (int j = 0; j < pratos.length; j++) {
                        if (pratos[j] != null) {
                            totalCusto = totalCusto + pratos[j].getPrecoCusto();
                        }
                    }
                }
            }
        }
        return totalCusto;
    }

    public static double calcularTotalCustoTotal() {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        if (pedidos == null) return 0;

        double totalCusto = 0;
        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null && pedidos[i].getStatus() == 7 &&
                    pedidos[i].getHoraFinalizacao() != 0 && pedidos[i].getPratos() != null) {

                Prato[] pratos = pedidos[i].getPratos();
                for (int j = 0; j < pratos.length; j++) {
                    if (pratos[j] != null) {
                        totalCusto += pratos[j].getPrecoCusto();
                    }
                }
            }
        }
        return totalCusto;
    }

    public static double calcularLucroIntervalo(int diaInicio, int diaFim) {
        double totalFaturado = calcularTotalFaturadoIntervalo(diaInicio, diaFim);
        double totalGastos = calcularTotalCustoIntervalo(diaInicio, diaFim);
        return totalFaturado - totalGastos;
    }

    public static double calcularTotalLucro() {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        double valorPrejuizo = GlobalStorage.getPrejuizoClienteNaoAtendido();
        int countPrejuizo = 0;
        for(int i = 0; i < pedidos.length; i++){
            if(pedidos[i] != null && pedidos[i].isPrejuizo()){
                countPrejuizo ++;
            }
        }

        double totalFaturado = calcularTotalFaturado();
        double totalCustos = calcularTotalCustoTotal();

        return totalFaturado - totalCustos - (countPrejuizo * valorPrejuizo);
    }

    public static int contarPedidosAtendidosTotal() {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        int pedidosAtendidos = 0;

        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null) {
                if (pedidos[i].getMomentoCriacao() != 0) {
                    pedidosAtendidos++;
                }
            }
        }
        return pedidosAtendidos;
    }

    public static int contarPedidosNaoAtendidosTotal() {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        int pedidosAtendidos = 0;

        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null) {
                if (pedidos[i].getMomentoCriacao() == 0) {
                    pedidosAtendidos++;
                }
            }
        }
        return pedidosAtendidos;
    }
}


