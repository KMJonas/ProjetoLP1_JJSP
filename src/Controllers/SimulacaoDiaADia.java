package Controllers;

import Models.*;
import Views.ClienteReservaView;

import java.util.Scanner;

public class SimulacaoDiaADia {

    private final Mesa[] mesas;
    private final ClienteReserva[] reservas;
    private final Prato[] pratos;
    private final Pedido[] pedidos;
    private int pedidoAtual;

    public SimulacaoDiaADia(Mesa[] mesas, ClienteReserva[] reservas, Prato[] pratos) {
        this.mesas = mesas;
        this.reservas = reservas;
        this.pratos = pratos;
        this.pedidos = new Pedido[100];
        this.pedidoAtual = 0;
    }

    public void iniciarSimulacao() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à simulação de gestão de restaurante!");
        System.out.print("Quantos momentos (unidades de tempo) deseja simular? ");
        int totalMomentos = scanner.nextInt();

        int momentoAtual = 0;

        while (momentoAtual < totalMomentos) {

            System.out.println("\nMomento " + momentoAtual + " iniciado.");
            exibirReservasNoMomento(momentoAtual);
            exibirMesasDisponiveis();
            exibirOcupacoes();

            verificarPreparacaoPronta(momentoAtual);
            verificarConsumoFinalizado(momentoAtual);
            atualizarStatusConsumo(momentoAtual);
            verificarTemposExcedidos(momentoAtual);

            boolean avancarMomento = false;
            while (!avancarMomento) {
                System.out.println("\nO que deseja fazer?");
                System.out.println("1 - Atribuir mesa a reserva");
                System.out.println("2 - Criar um novo pedido");
                System.out.println("3 - Entregar comida");
                System.out.println("4 - Finalizar pedido de uma mesa");
                System.out.println("5 - Adicionar Reserva");
                System.out.println("6 - Avançar para o próximo momento");
                System.out.print("Escolha: ");
                int escolha = scanner.nextInt();

                switch (escolha) {
                    case 1 -> atribuirMesaAReserva(scanner, momentoAtual);
                    case 2 -> criarPedido(scanner, momentoAtual);
                    case 3 -> entregarComida(scanner, momentoAtual);
                    case 4 -> finalizarPedido(scanner, momentoAtual);
                    case 5 -> ClienteReservaView.adicionarReserva(momentoAtual);
                    case 6 -> {
                        avancarMomento = true;
                        System.out.println("Avançando para o próximo momento...");
                    }
                    default -> System.out.println("Opção inválida.");
                }

                if (!avancarMomento) {
                    System.out.println("\n--- Atualizações no Momento " + momentoAtual + " ---");
                    exibirReservasNoMomento(momentoAtual);
                    exibirMesasDisponiveis();
                    exibirOcupacoes();
                }
            }
            momentoAtual++;
        }
        System.out.println("\nSimulação finalizada!");
        scanner.close();
    }

    private void exibirReservasNoMomento(int momento) {
        System.out.println("\nReservas no momento " + momento + ":");
        for (ClienteReserva reserva : reservas) {
            // Se a reserva não possui uma mesa atribuída (verificada via o mapa da classe Pedido)
            if (reserva != null && Pedido.getMesaAssociada(reserva) == null) {
                if (reserva.getHoraChegada() == momento) {
                    System.out.println("- Reserva ID: " + reserva.getIdReserva() +
                            " | Nome: " + reserva.getNome() +
                            " | Nº Pessoas: " + reserva.getNumPessoas());
                } else if (reserva.getHoraChegada() < momento) {
                    System.out.println("- Reserva ID: " + reserva.getIdReserva() +
                            " | Nome: " + reserva.getNome() +
                            " | Nº Pessoas: " + reserva.getNumPessoas() +
                            " (Momento original: " + reserva.getHoraChegada() + ")");
                }
            }
        }
    }

    private void exibirMesasDisponiveis() {
        System.out.println("\nMesas disponíveis:");
        for (Mesa mesa : mesas) {
            // Considera a mesa disponível se seu status for 1 (livre) e não houver pedido associado
            if (mesa != null && mesa.getStatus() == 1 && Pedido.getPedidoAssociado(mesa) == null) {
                System.out.println("- Mesa ID: " + mesa.getIdMesa() +
                        " | Capacidade: " + mesa.getCapacidade());
            }
        }
    }

    private void exibirOcupacoes() {
        System.out.println("\nOcupações atuais:");
        for (Mesa mesa : mesas) {
            if (mesa != null) {
                // Primeiro, verifica se há um pedido associado à mesa
                Pedido pedido = Pedido.getPedidoAssociado(mesa);
                if (pedido != null) {
                    // Se houver pedido, use a reserva do pedido para exibir os dados
                    ClienteReserva reserva = pedido.getReservaAssociada();
                    String statusTexto;
                    switch (mesa.getStatus()) {
                        case 1 -> statusTexto = "À espera da comida";
                        case 2 -> statusTexto = "A Comer";
                        case 3 -> statusTexto = "Acabou de Comer! À espera para pagar";
                        default -> statusTexto = "Ocupada";
                    }
                    System.out.println("- Mesa ID: " + mesa.getIdMesa() +
                            " está ocupada pela reserva ID: " + reserva.getIdReserva() +
                            " (" + reserva.getNome() + ") - Status: " + statusTexto);
                } else if (mesa.getStatus() != 1) {
                    // Se não há pedido, mas a mesa não está livre, procure a reserva associada
                    ClienteReserva reservaAssociada = null;
                    for (ClienteReserva r : reservas) {
                        if (r != null && Pedido.getMesaAssociada(r) != null &&
                                Pedido.getMesaAssociada(r).getIdMesa() == mesa.getIdMesa()) {
                            reservaAssociada = r;
                            break;
                        }
                    }
                    if (reservaAssociada != null) {
                        System.out.println("- Mesa ID: " + mesa.getIdMesa() +
                                " está ocupada pela reserva ID: " + reservaAssociada.getIdReserva() +
                                " (" + reservaAssociada.getNome() + ") - Status: Reservada");
                    } else {
                        System.out.println("- Mesa ID: " + mesa.getIdMesa() + " - Status: Desconhecido");
                    }
                } else {
                    System.out.println("- Mesa ID: " + mesa.getIdMesa() + " - Status: Vazia");
                }
            }
        }
    }

    private void verificarConsumoFinalizado(int momentoAtual) {
        for (Pedido pedido : pedidos) {
            if (pedido != null && pedido.isPreparado() && !pedido.isFinalizado()) {
                int tempoConsumo = pedido.getTempoConsumo();
                if (momentoAtual >= pedido.getMomentoEntrega() + tempoConsumo) {
                    System.out.println("Pedido associado à mesa " +
                            pedido.getMesaAssociada().getIdMesa() +
                            " foi consumido e pode ser finalizado.");
                    pedido.setConsumido(true);
                    pedido.getMesaAssociada().setStatus(3);
                }
            }
        }
    }

    private void atribuirMesaAReserva(Scanner scanner, int momentoAtual) {
        System.out.print("\nDigite o ID da reserva: ");
        int idReserva = scanner.nextInt();
        ClienteReserva reserva = buscarReservaPorId(idReserva);
        if (reserva == null) {
            System.out.println("Reserva não encontrada.");
            return;
        }
        if (reserva.getHoraChegada() > momentoAtual) {
            System.out.println("A reserva só pode ser atribuída no momento de chegada ou depois.");
            return;
        }
        // Verifica se a reserva já possui uma mesa atribuída
        if (Pedido.getMesaAssociada(reserva) != null) {
            System.out.println("A reserva já está associada a uma mesa.");
            return;
        }
        System.out.print("Digite o ID da mesa: ");
        int idMesa = scanner.nextInt();
        Mesa mesa = buscarMesaPorId(idMesa);
        if (mesa == null || mesa.getStatus() != 1) {
            System.out.println("Mesa não disponível ou não encontrada.");
            return;
        }
        if (reserva.getNumPessoas() > mesa.getCapacidade()) {
            System.out.println("A mesa não tem capacidade suficiente para atender a reserva.");
            return;
        }
        // Registra a associação entre reserva e mesa (via Pedido)
        Pedido.atribuirMesaAReserva(reserva, mesa);
        // Atualiza o status da mesa para “reservada” (por exemplo, 0)
        mesa.setStatus(0);
        System.out.println("Mesa " + idMesa + " atribuída à reserva " + idReserva + ".");
    }

    private void criarPedido(Scanner scanner, int momentoAtual) {
        System.out.print("Digite o ID da mesa para criar um pedido: ");
        int idMesa = scanner.nextInt();
        Mesa mesa = buscarMesaPorId(idMesa);
        if (mesa == null || mesa.getStatus() != 0) {
            System.out.println("Mesa não encontrada ou não está reservada.");
            return;
        }



        // Verifica se já existe um pedido associado à mesa
        if (Pedido.getPedidoAssociado(mesa) != null) {
            System.out.println("Já existe um pedido em andamento para esta mesa.");
            return;
        }


        ClienteReserva reserva = null;
        for (ClienteReserva r : reservas) {
            if (r != null && Pedido.getMesaAssociada(r) != null &&
                    Pedido.getMesaAssociada(r).getIdMesa() == mesa.getIdMesa()) {
                reserva = r;
                break;
            }
        }
        if (reserva == null) {
            System.out.println("Não há reserva associada à mesa.");
            return;
        }


        for (ClienteReserva r : reservas) {
            if (r != null && Pedido.getMesaAssociada(r) != null &&
                    Pedido.getMesaAssociada(r).getIdMesa() == mesa.getIdMesa()) {
                reserva = r;
                break;
            }
        }
        if (reserva == null) {
            System.out.println("Não há reserva associada à mesa.");
            return;
        }

// Impede criação do pedido no mesmo momento da atribuição (momento de chegada)
        if (momentoAtual == reserva.getHoraChegada()) {
            System.out.println("O pedido não pode ser criado no mesmo momento em que a mesa foi atribuída.");
            return;
        }



        System.out.println("Pratos disponíveis:");
        for (Prato prato : pratos) {
            if (prato != null) {
                System.out.println("- Prato ID: " + prato.getIdPrato() +
                        " | Nome: " + prato.getNome() +
                        " | Tempo de Preparação: " + prato.getunidadeTempoPreparacao() + " momentos" +
                        " | Tempo de Consumo: " + prato.getunidadeTempoConsumo() + " momentos");
            }
        }

        Prato[] pratosSelecionados = new Prato[10];
        int pratoCount = 0;
        int tempoTotalPreparacao = 0;
        int maiorTempoConsumo = 0;
        while (true) {
            System.out.print("Digite o ID do prato a incluir no pedido (ou 0 para finalizar): ");
            int idPrato = scanner.nextInt();
            if (idPrato == 0) break;
            Prato pratoSelecionado = buscarPratoPorId(idPrato);
            if (pratoSelecionado != null) {
                pratosSelecionados[pratoCount++] = pratoSelecionado;
                tempoTotalPreparacao = Math.max(tempoTotalPreparacao, pratoSelecionado.getunidadeTempoPreparacao());
                maiorTempoConsumo = Math.max(maiorTempoConsumo, pratoSelecionado.getunidadeTempoConsumo());
            } else {
                System.out.println("Prato não encontrado.");
            }
        }

        int idPedido = pedidoAtual++;
        System.out.println("Tempo total de preparação: " + tempoTotalPreparacao);
        System.out.println("Maior tempo de consumo: " + maiorTempoConsumo);
        Pedido novoPedido = new Pedido(idPedido, reserva, mesa, pratosSelecionados,
                momentoAtual, tempoTotalPreparacao, maiorTempoConsumo, 1, 0);
        novoPedido.setStatusPedido(1);
        Pedido.setPedidoAssociado(mesa, novoPedido);
        pedidos[idPedido] = novoPedido;
        System.out.println("Pedido criado com sucesso para a mesa " + idMesa + "!");
        mesa.setStatus(1); // Status: À espera da comida
    }

    private void entregarComida(Scanner scanner, int momentoAtual) {
        System.out.print("Digite o ID da mesa para entregar comida: ");
        int idMesa = scanner.nextInt();
        Mesa mesa = buscarMesaPorId(idMesa);
        if (mesa == null || !(mesa.getStatus() == 1 || mesa.getStatus() == 2)) {
            System.out.println("Mesa não encontrada ou não está ocupada.");
            return;
        }
        Pedido pedido = Pedido.getPedidoAssociado(mesa);
        if (pedido == null) {
            System.out.println("Não há pedido associado a esta mesa.");
            return;
        }
        if (momentoAtual < pedido.getMomentoCriacao() + pedido.getTempoPreparacao()) {
            System.out.println("A comida ainda não está pronta para ser entregue.");
            return;
        }
        System.out.println("Comida entregue com sucesso na mesa " + idMesa + "!");
        pedido.setMomentoEntrega(momentoAtual);
        pedido.setStatusPedido(2);
        mesa.setStatus(2);
    }


    private void verificarPreparacaoPronta(int momentoAtual) {
        for (Pedido pedido : pedidos) {
            // Só processa pedidos que ainda estão aguardando a entrega (statusPedido == 1)
            if (pedido != null && pedido.getStatusPedido() == 1) {
                int momentoPronto = pedido.getMomentoCriacao() + pedido.getTempoPreparacao();
                if (momentoAtual == momentoPronto) {
                    System.out.println("Comida do pedido associado à mesa " +
                            pedido.getMesaAssociada().getIdMesa() +
                            " está pronta para ser entregue (no momento: " + momentoPronto + ").");
                    pedido.setPreparado(true);
                } else if (momentoAtual < momentoPronto) {
                    System.out.println("Comida do pedido associado à mesa " +
                            pedido.getMesaAssociada().getIdMesa() +
                            " ainda não está pronta. Tempo restante: " +
                            (momentoPronto - momentoAtual) + " momento(s).");
                } else if (momentoAtual > momentoPronto) {
                    // Mesmo que o momento de entrega já tenha passado, mostramos que está pronta,
                    // mas só se o pedido ainda estiver aguardando a entrega.
                    System.out.println("Comida do pedido associado à mesa " +
                            pedido.getMesaAssociada().getIdMesa() +
                            " está pronta para ser entregue (desde o momento: " + momentoPronto + ").");
                }
            }
        }
    }




    private void atualizarStatusConsumo(int momentoAtual) {
        for (Pedido pedido : pedidos) {
            if (pedido != null && pedido.getStatusPedido() == 2) { // Pedido com comida entregue
                int tempoConsumo = pedido.getTempoConsumo();
                int momentoEntrega = pedido.getMomentoEntrega();
                int momentoFinalConsumo = momentoEntrega + tempoConsumo;

                if (momentoAtual < momentoFinalConsumo) {
                    // Durante o consumo
                    System.out.println("Mesa " + pedido.getMesaAssociada().getIdMesa() +
                            " está consumindo. Tempo restante para terminar: " + (momentoFinalConsumo - momentoAtual) + " momento(s).");
                    pedido.getMesaAssociada().setStatus(2); // Status: A Comer
                } else if (momentoAtual >= momentoFinalConsumo && !pedido.isConsumido()) {
                    // Quando o tempo de consumo termina
                    System.out.println("Pedido associado à mesa " +
                            pedido.getMesaAssociada().getIdMesa() +
                            " acabou de comer! À espera para pagar.");
                    pedido.setConsumido(true);
                    pedido.getMesaAssociada().setStatus(3); // Status: Acabou de Comer! À espera para pagar
                }
            }
        }
    }



    private void finalizarPedido(Scanner scanner, int momentoAtual) {
        System.out.print("\nDigite o ID da mesa cujo pedido deseja finalizar: ");
        int idMesa = scanner.nextInt();
        Mesa mesa = buscarMesaPorId(idMesa);
        if (mesa == null || mesa.getStatus() != 3) {
            System.out.println("Mesa não encontrada ou o pedido ainda não foi consumido por completo.");
            return;
        }
        Pedido pedido = Pedido.getPedidoAssociado(mesa);
        if (pedido == null || pedido.isFinalizado()) {
            System.out.println("Não há pedido para finalizar ou já foi finalizado.");
            return;
        }
        if (!pedido.isConsumido()) {
            System.out.println("O pedido ainda não foi consumido por completo.");
            return;
        }
        pedido.setFinalizado(true);
        // Remove as associações para que este pedido não seja mais considerado ativo.
        Pedido.removerAssociacoes(mesa, pedido.getReservaAssociada());
        // Define a mesa como "pendente de liberação" (status 4)
        mesa.setStatus(4);
        System.out.println("Pedido da mesa " + idMesa + " finalizado com sucesso (Momento: " + momentoAtual + ").");
    }



    private void verificarTemposExcedidos(int momentoAtual) {
        // Verifica mesas aguardando atendimento (status 0)
        for (Mesa mesa : mesas) {
            if (mesa != null && mesa.getStatus() == 0) {
                // Procura a reserva associada à mesa
                ClienteReserva reservaAssociada = null;
                for (ClienteReserva r : reservas) {
                    if (r != null && Pedido.getMesaAssociada(r) != null &&
                            Pedido.getMesaAssociada(r).getIdMesa() == mesa.getIdMesa()) {
                        reservaAssociada = r;
                        break;
                    }
                }
                if (reservaAssociada != null) {
                    int tempoEsperaAtendimento = momentoAtual - reservaAssociada.getHoraChegada();
                    if (tempoEsperaAtendimento > GlobalStorage.getTempoMaxEsperaAtendimento()) {
                        System.out.println("Mesa " + mesa.getIdMesa() + " foi se embora, prejuízo de 50 euros (tempo de espera para atendimento excedido).");
                        // Remove as associações entre a reserva e a mesa
                        Pedido.removerAssociacoes(mesa, reservaAssociada);
                        // Libera a mesa (por exemplo, definindo-a como disponível – status 1)
                        mesa.setStatus(1);
                    }
                }
            }
        }

        // Verifica mesas aguardando pagamento (status 3)
        for (Mesa mesa : mesas) {
            if (mesa != null && mesa.getStatus() == 3) {
                Pedido pedido = Pedido.getPedidoAssociado(mesa);
                if (pedido != null) {
                    // Consideramos que o cliente terminou de comer no momento:
                    // pedido.getMomentoEntrega() + pedido.getTempoConsumo()
                    int momentoFimConsumo = pedido.getMomentoEntrega() + pedido.getTempoConsumo();
                    int tempoEsperaPagamento = momentoAtual - momentoFimConsumo;
                    if (tempoEsperaPagamento > GlobalStorage.getTempoMaxEsperaPagamento()) {
                        System.out.println("Mesa " + mesa.getIdMesa() + " foi se embora, prejuízo de 50 euros (tempo de espera para pagamento excedido).");
                        // Finaliza o pedido e remove as associações
                        pedido.setFinalizado(true);
                        Pedido.removerAssociacoes(mesa, pedido.getReservaAssociada());
                        // Atualiza o status da mesa (por exemplo, para pendente de liberação ou livre)
                        mesa.setStatus(4); // status 4 pode indicar que a mesa ficou pendente ou foi abandonada
                    }
                }
            }
        }
    }




    private Mesa buscarMesaPorId(int id) {
        for (Mesa mesa : mesas) {
            if (mesa != null && mesa.getIdMesa() == id) {
                return mesa;
            }
        }
        return null;
    }

    private ClienteReserva buscarReservaPorId(int id) {
        for (ClienteReserva reserva : reservas) {
            if (reserva != null && reserva.getIdReserva() == id) {
                return reserva;
            }
        }
        return null;
    }

    private Prato buscarPratoPorId(int id) {
        for (Prato prato : pratos) {
            if (prato != null && prato.getIdPrato() == id) {
                return prato;
            }
        }
        return null;
    }
}
