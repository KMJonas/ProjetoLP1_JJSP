                package Controllers;

                import Models.ClienteReserva;
                import Models.Mesa;
                import Models.Prato;
                import Models.Pedido;
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
                            if (reserva != null && reserva.getMesaAssociada() == null) {
                                if (reserva.getHoraChegada() == momento) {
                                    System.out.println("- Reserva ID: " + reserva.getIdReserva() + " | Nome: " + reserva.getNome() + " | Nº Pessoas: " + reserva.getNumPessoas());
                                } else if (reserva.getHoraChegada() < momento) {
                                    System.out.println("- Reserva ID: " + reserva.getIdReserva() + " | Nome: " + reserva.getNome() + " | Nº Pessoas: " + reserva.getNumPessoas() + " (Momento original: " + reserva.getHoraChegada() + ")");
                                }
                            }
                        }
                    }


                    private void exibirMesasDisponiveis() {
                        System.out.println("\nMesas disponíveis:");
                        for (Mesa mesa : mesas) {
                            if (mesa != null && mesa.getStatus() == 1) {
                                System.out.println("- Mesa ID: " + mesa.getIdMesa() + " | Capacidade: " + mesa.getCapacidade());
                            }
                        }
                    }

                    private void exibirOcupacoes() {
                        System.out.println("\nOcupações atuais:");
                        for (Mesa mesa : mesas) {
                            if (mesa != null) {
                                ClienteReserva reserva = mesa.getReservaAssociada();
                                String statusTexto = "Vazia";

                                if (reserva != null) {
                                    switch (mesa.getStatus()) {
                                        case 1:
                                            statusTexto = "Á espera da comida";
                                            break;
                                        case 2:
                                            statusTexto = "A Comer";
                                            break;
                                        case 3:
                                            statusTexto = "Acabou de Comer!Á espera para poder pagar!";
                                            break;

                                        default:
                                            statusTexto = "Ocupada";
                                    }

                                    System.out.println("- Mesa ID: " + mesa.getIdMesa() + " está ocupada pela reserva ID: "
                                            + reserva.getIdReserva() + " (" + reserva.getNome() + ") - Status: " + statusTexto);


                                    Pedido pedido = mesa.getPedidoAssociado();
                                    if (pedido != null && pedido.isPreparado()) {
                                        System.out.println("  → A comida está pronta para entrega!");
                                    }
                                } else {

                                    System.out.println("- Mesa ID: " + mesa.getIdMesa() + " - Status: " + statusTexto);
                                }
                            }
                        }
                    }


                    private void verificarConsumoFinalizado(int momentoAtual) {
                        for (Pedido pedido : pedidos) {
                            if (pedido != null && pedido.isPreparado() && !pedido.isFinalizado()) {
                                int tempoConsumo = pedido.getTempoConsumo();
                                if (momentoAtual >= pedido.getMomentoEntrega() + tempoConsumo) {
                                    System.out.println("Pedido associado à mesa " + pedido.getMesaAssociada().getIdMesa() + " foi consumido e pode ser finalizado.");
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

                        if (reserva.getMesaAssociada() != null) {
                            System.out.println("A reserva já está associada à mesa " + reserva.getMesaAssociada().getIdMesa() + ".");
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

                        mesa.setStatus(0); // Mesa ocupada
                        mesa.setReservaAssociada(reserva);
                        reserva.setMesaAssociada(mesa);
                        reserva.setMomentoAtribuicao(momentoAtual);
                        System.out.println("Mesa " + idMesa + " atribuída à reserva " + idReserva + ".");
                    }



                    private void criarPedido(Scanner scanner, int momentoAtual) {
                        System.out.print("Digite o ID da mesa para criar um pedido: ");
                        int idMesa = scanner.nextInt();

                        Mesa mesa = buscarMesaPorId(idMesa);

                        if (mesa == null || mesa.getStatus() != 0) {
                            System.out.println("Mesa não encontrada ou não está ocupada.");
                            return;
                        }

                        ClienteReserva reserva = mesa.getReservaAssociada();
                        if (reserva == null) {
                            System.out.println("Não há reserva associada à mesa.");
                            return;
                        }

                        // Verifica se o momento atual é o mesmo que o momento de atribuição da mesa
                        if (reserva.getMomentoAtribuicao() == momentoAtual) {
                            System.out.println("Não é possível criar um pedido no mesmo momento em que a mesa foi atribuída.");
                            return;
                        }

                        // Verifica se já existe um pedido em andamento para esta mesa
                        Pedido pedidoExistente = mesa.getPedidoAssociado();
                        if (pedidoExistente != null && pedidoExistente.getStatusPedido() == 1) {
                            System.out.println("Já existe um pedido em andamento para esta mesa.");
                            return;
                        }

                        System.out.println("Pratos disponíveis:");
                        for (Prato prato : pratos) {
                            if (prato != null) {
                                System.out.println("- Prato ID: " + prato.getIdPrato() + " | Nome: " + prato.getNome() + " | Tempo de Preparação: " + prato.getunidadeTempoPreparacao() + " momentos." + "| Tempo de consumo: " + prato.getunidadeTempoConsumo() + "momentos.");
                            }
                        }

                        Prato[] pratosSelecionados = new Prato[10];
                        int pratoCount = 0;
                        int tempoTotalPreparacao = 0;
                        int maiorTempoConsumo = 0;

                        while (true) {
                            System.out.print("Digite o ID do prato a incluir no pedido (ou 0 para finalizar): ");
                            int idPrato = scanner.nextInt();
                            if (idPrato == 0) {
                                break;
                            }

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

                        System.out.println("tempo total de preparação: " + tempoTotalPreparacao);
                        System.out.println("maior tempo de consumo: " + maiorTempoConsumo);

                        Pedido novoPedido = new Pedido(idPedido, mesa, pratosSelecionados, momentoAtual, tempoTotalPreparacao, maiorTempoConsumo, 1);
                        novoPedido.setStatusPedido(1);


                        mesa.setPedidoAssociado(novoPedido);
                        pedidos[idPedido] = novoPedido;
                        System.out.println("Pedido criado com sucesso para a mesa " + idMesa + "!");

                        mesa.setStatus(1);





                        System.out.println("Pedido criado com sucesso para a mesa " + idMesa + "!");
                    }


                    private void entregarComida(Scanner scanner, int momentoAtual) {
                        System.out.print("Digite o ID da mesa para entregar comida: ");
                        int idMesa = scanner.nextInt();

                        Mesa mesa = null;
                        for (Mesa m : mesas) {
                            if (m != null && m.getIdMesa() == idMesa && (m.getStatus() == 1 || m.getStatus() == 2)) {
                                mesa = m;
                                break;
                            }
                        }

                        if (mesa == null) {
                            System.out.println("Mesa não encontrada ou não está ocupada.");
                            return;
                        }

                        Pedido pedido = mesa.getPedidoAssociado();
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

                        mesa.setStatus(2); // Atualiza o status da mesa para "A Comer"
                    }





                    private void verificarPreparacaoPronta(int momentoAtual) {
                        for (Pedido pedido : pedidos) {
                            if (pedido != null && pedido.getStatus() == 1) { // Pedido ativo
                                int tempoPreparacao = pedido.getTempoPreparacao();
                                if (momentoAtual >= pedido.getMomentoCriacao() + tempoPreparacao) {
                                    System.out.println("Comida do pedido associado à mesa " + pedido.getMesaAssociada().getIdMesa() + " está pronta para ser entregue.");
                                    pedido.setPreparado(true);
                                } else {
                                    System.out.println("Comida do pedido associado à mesa " + pedido.getMesaAssociada().getIdMesa() + " ainda não está pronta. Tempo restante: " + (pedido.getMomentoCriacao() + tempoPreparacao - momentoAtual));
                                }
                            }
                        }
                    }

                    private void atualizarStatusConsumo(int momentoAtual) {
                        for (Pedido pedido : pedidos) {
                            if (pedido != null && pedido.getStatusPedido() == 2) {
                                int tempoConsumo = pedido.getTempoConsumo();

                                if (momentoAtual >= pedido.getMomentoEntrega() + tempoConsumo) {
                                    System.out.println("Pedido associado à mesa " + pedido.getMesaAssociada().getIdMesa() + " foi consumido.");
                                    pedido.setStatusPedido(3);
                                    pedido.setConsumido(true);
                                    pedido.getMesaAssociada().setStatus(3);
                                }
                            }
                        }
                    }



                    private void finalizarPedido(Scanner scanner, int momentoAtual) {
                        System.out.print("\nDigite o ID da mesa cujo pedido deseja finalizar: ");
                        int idMesa = scanner.nextInt();

                        Mesa mesa = buscarMesaPorId(idMesa);

                        if (mesa == null || mesa.getStatus() != 3) { // Verifica se a mesa está com status "Acabou de Comer"
                            System.out.println("Mesa não encontrada ou o pedido ainda não foi consumido por completo.");
                            return;
                        }

                        Pedido pedido = buscarPedidoPorMesa(mesa);

                        if (pedido == null || pedido.isFinalizado()) {
                            System.out.println("Não há pedido para finalizar ou já foi finalizado.");
                            return;
                        }

                        if (!pedido.isConsumido()) {
                            System.out.println("O pedido ainda não foi consumido por completo.");
                            return;
                        }

                        pedido.setFinalizado(true);
                        mesa.setReservaAssociada(null);
                        mesa.setPedidoAssociado(null);
                        mesa.setStatus(4); // Libera a mesa
                        System.out.println("Pedido da mesa " + idMesa + " finalizado com sucesso.");
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

                    private Pedido buscarPedidoPorMesa(Mesa mesa) {
                        for (Pedido pedido : pedidos) {
                            if (pedido != null && pedido.getMesaAssociada().equals(mesa)) {
                                return pedido;
                            }
                        }
                        return null;
                    }
                }
