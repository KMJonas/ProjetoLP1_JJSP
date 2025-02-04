package Views;

import Controllers.EstatisticasController;
import Controllers.MesaController;
import Models.Mesa;
import Models.Prato;

import java.util.Scanner;

public class EstatisticasView {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarMenuEstatisticas() {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n━━━━━━━ Menu de Estatísticas ━━━━━━━");
            System.out.println("1 ➤ Estatísticas de Pratos");
            System.out.println("2 ➤ Estatísticas de Clientes");
            System.out.println("3 ➤ Estatísticas de Tempo");
            System.out.println("4 ➤ Estatísticas Financeiras");
            System.out.println("5 ➤ Estatísticas de Pedidos");
            System.out.println("0 ➤ Voltar ao menu principal");

            System.out.print("Escolha: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> mostrarEstatisticasPratos();
                case 2 -> mostrarEstatisticasClientes();
                case 3 -> mostrarEstatisticasTempo();
                case 4 -> mostrarEstatisticasFinanceiras();
                case 5 -> mostrarEstatisticasPedidos();
                case 0 -> sair = true;
                default -> System.out.println("⚠ Opção inválida ⚠");
            }
        }
    }

    private static void mostrarEstatisticasPratos() {
        System.out.println("\n━━━━━━━ Estatísticas de Pratos ━━━━━━━");
        Prato pratoMaisPedido = EstatisticasController.pratoMaisPedido();
        if (pratoMaisPedido != null) {
            System.out.println("➤ Prato mais pedido: " + pratoMaisPedido.getNome());
            System.out.println("➤ Categoria: " + pratoMaisPedido.getCategoria());
            System.out.println("➤ Número de pedidos: " + EstatisticasController.contarOcorrenciasPrato(pratoMaisPedido));
        } else {
            System.out.println("⚠ Ainda não existem pratos pedidos ⚠");
        }
    }

    private static void mostrarEstatisticasClientes() {
        System.out.println("\n━━━━━━━ Estatísticas de Clientes ━━━━━━━");
        System.out.println("1 ➤ Total de clientes atendidos");
        System.out.println("2 ➤ Clientes atendidos por intervalo");
        System.out.print("Escolha: ");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1 -> {
                int total = EstatisticasController.totalClientesAtendidos();
                System.out.println("➤ Total de clientes atendidos: " + total);
            }
            case 2 -> {
                System.out.println("➤ Insira o momento inicial: ");
                int inicio = sc.nextInt();
                System.out.println("➤ Insira o momento final: ");
                int fim = sc.nextInt();
                int total = EstatisticasController.totalClientesAtendidosIntervalo(inicio, fim);
                System.out.println("➤ Total de clientes no intervalo: " + total);
            }
            default -> System.out.println("⚠ Opção inválida ⚠");
        }
    }

    private static void mostrarEstatisticasTempo() {
        System.out.println("\n━━━━━━━ Estatísticas de Tempo ━━━━━━━");
        System.out.println("1 ➤ Tempo médio de espera por mesa");
        System.out.println("2 ➤ Tempo médio total de serviço");
        System.out.print("Escolha: ");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1 -> mostrarTempoMedioEspera();
            case 2 -> mostrarTempoMedioTotal();
            default -> System.out.println("⚠ Opção inválida ⚠");
        }
    }

    private static void mostrarEstatisticasFinanceiras() {
        System.out.println("\n━━━━━━━ Estatísticas Financeiras ━━━━━━━");
        System.out.println("1 ➤ Total faturado");
        System.out.println("2 ➤ Total faturado por intervalo");
        System.out.println("3 ➤ Total custos");
        System.out.println("4 ➤ Total custos por intervalo");
        System.out.println("5 ➤ Lucro total");
        System.out.println("6 ➤ Lucro por intervalo");
        System.out.print("Escolha: ");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1 -> {
                double total = EstatisticasController.calcularTotalFaturado();
                System.out.println("➤ Total faturado: " + total + "€");
            }
            case 2 -> {
                System.out.println("➤ Insira o momento inicial: ");
                int inicio = sc.nextInt();
                System.out.println("➤ Insira o momento final: ");
                int fim = sc.nextInt();
                double total = EstatisticasController.calcularTotalFaturadoIntervalo(inicio, fim);
                System.out.println("➤ Total faturado no intervalo: " + total + "€");
            }
            case 3 -> {
                double total = EstatisticasController.calcularTotalCustoTotal();
                System.out.println("➤ Total custos: " + total + "€");
            }
            case 4 -> {
                System.out.println("➤ Insira o momento inicial: ");
                int inicio = sc.nextInt();
                System.out.println("➤ Insira o momento final: ");
                int fim = sc.nextInt();
                double total = EstatisticasController.calcularTotalCustoIntervalo(inicio, fim);
                System.out.println("➤ Total custos no intervalo: " + total + "€");
            }
            case 5 -> {
                double total = EstatisticasController.calcularTotalLucro();
                System.out.println("➤ Lucro total: " + total + "€");
            }
            case 6 -> {
                System.out.println("➤ Insira o momento inicial: ");
                int inicio = sc.nextInt();
                System.out.println("➤ Insira o momento final: ");
                int fim = sc.nextInt();
                double total = EstatisticasController.calcularLucroIntervalo(inicio, fim);
                System.out.println("➤ Lucro no intervalo: " + total + "€");
            }
            default -> System.out.println("⚠ Opção inválida ⚠");
        }
    }

    private static void mostrarEstatisticasPedidos() {
        System.out.println("\n━━━━━━━ Estatísticas de Pedidos ━━━━━━━");
        System.out.println("1 ➤ Total de pedidos atendidos");
        System.out.println("2 ➤ Total de pedidos não atendidos");
        System.out.print("Escolha: ");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1 -> {
                int total = EstatisticasController.contarPedidosAtendidosTotal();
                System.out.println("➤ Total de pedidos atendidos: " + total);
            }
            case 2 -> {
                int total = EstatisticasController.contarPedidosNaoAtendidosTotal();
                System.out.println("➤ Total de pedidos não atendidos: " + total);
            }
            default -> System.out.println("⚠ Opção inválida ⚠");
        }
    }

    private static void mostrarTempoMedioEspera() {
        System.out.println("\n━━━━━━━ Tempo Médio de Espera por Mesa ━━━━━━━");
        double[] tempos = EstatisticasController.tempoMedioEsperaAtendimentoMesa();
        Mesa[] mesas = MesaController.getMesas();

        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i] != null) {
                System.out.println("➤ Mesa " + mesas[i].getIdMesa() + ": " + tempos[i + 2] + " momentos");
            }
        }
    }

    private static void mostrarTempoMedioTotal() {
        System.out.println("\n━━━━━━━ Tempo Médio Total de Serviço por Mesa ━━━━━━━");
        double[] tempos = EstatisticasController.tempoMedioTotalMesa();
        Mesa[] mesas = MesaController.getMesas();

        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i] != null) {
                System.out.println("➤ Mesa " + mesas[i].getIdMesa() + ": " + tempos[i + 1] + " momentos");
            }
        }
    }
}
