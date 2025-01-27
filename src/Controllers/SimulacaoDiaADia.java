                package Controllers;

                import Models.ClienteReserva;
                import Models.Mesa;
                import Models.Prato;
                import Models.Pedido;

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
                        this.pedidos = new Pedido[100]; // Capacidade máxima de 100 pedidos
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

                            boolean avancarMomento = false;

                            while (!avancarMomento) {
                                System.out.println("\nO que deseja fazer?");
                                System.out.println("1 - Atribuir mesa a reserva");
                                System.out.println("2 - Criar um novo pedido");
                                System.out.println("3 - Entregar comida");
                                System.out.println("4 - Finalizar pedido de uma mesa");
                                System.out.println("5 - Avançar para o próximo momento");
                                System.out.print("Escolha: ");
                                int escolha = scanner.nextInt();

                                switch (escolha) {
                                    case 1 -> atribuirMesaAReserva(scanner, momentoAtual);
                                    case 2 -> criarPedido(scanner, momentoAtual);
                                    case 3 -> entregarComida(scanner, momentoAtual);
                                    case 4 -> finalizarPedido(scanner, momentoAtual);
                                    case 5 -> {
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
                            if (reserva != null) {
                                if (reserva.getHoraChegada() == momento) {
                                    // Exibe reservas para o momento atual
                                    System.out.println("- Reserva ID: " + reserva.getIdReserva() + " | Nome: " + reserva.getNome() + " | Nº Pessoas: " + reserva.getNumPessoas());
                                } else if (reserva.getHoraChegada() < momento && reserva.getMesaAssociada() == null) {
                                    // Exibe reservas não atribuídas no momento correto
                                    System.out.println("- Reserva ID: " + reserva.getIdReserva() + " | Nome: " + reserva.getNome() + " | Nº Pessoas: " + reserva.getNumPessoas() + " (Momento original: " + reserva.getHoraChegada() + ")");
                                }
                            }
                        }
                    }


                    private void exibirMesasDisponiveis() {
                        System.out.println("\nMesas disponíveis:");
                        for (Mesa mesa : mesas) {
                            if (mesa != null && mesa.getStatus() == 1) { // Livre
                                System.out.println("- Mesa ID: " + mesa.getIdMesa() + " | Capacidade: " + mesa.getCapacidade());
                            }
                        }
                    }

                    private void exibirOcupacoes() {
                        System.out.println("\nOcupacões atuais:");
                        for (Mesa mesa : mesas) {
                            if (mesa != null && mesa.getStatus() == 0) { // Ocupado
                                ClienteReserva reserva = mesa.getReservaAssociada();
                                if (reserva != null) {
                                    System.out.println("- Mesa ID: " + mesa.getIdMesa() + " está ocupada pela reserva ID: " + reserva.getIdReserva() + " (" + reserva.getNome() + ")");
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

                        mesa.setStatus(0);
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

                        Pedido pedidoExistente = mesa.getPedidoAssociado();
                        if (pedidoExistente != null) {
                            if (pedidoExistente.getStatusPedido() == 1) { // 1 -> Pedido em andamento
                                System.out.println("Já existe um pedido em andamento para esta mesa.");
                                return;
                            } else if (pedidoExistente.getStatusPedido() == 0) { // 0 -> Pedido finalizado
                                System.out.println("O pedido da mesa foi finalizado. Você pode criar um novo pedido.");
                                mesa.setPedidoAssociado(null);
                            }
                        }

                        ClienteReserva reserva = mesa.getReservaAssociada();
                        if (reserva == null) {
                            System.out.println("Não há reserva associada à mesa.");
                            return;
                        }

                        // Alteração: Verificar o momento de atribuição da reserva
                        if (reserva.getMomentoAtribuicao() > momentoAtual) {
                            System.out.println("A reserva não pode ser utilizada antes do momento de atribuição.");
                            return;
                        }

                        System.out.println("Pratos disponíveis:");
                        for (Prato prato : pratos) {
                            if (prato != null) {
                                System.out.println("- Prato ID: " + prato.getIdPrato() + " | Nome: " + prato.getNome() + " | Tempo de Preparação: " + prato.getTempoPreparacao() + " minutos.");
                            }
                        }

                        Prato[] pratosSelecionados = new Prato[10];
                        int pratoCount = 0;
                        int tempoTotalPreparacao = 0;
                        int maiorTempoConsumo = 0;  // Variável para armazenar o maior tempo de consumo

                        while (true) {
                            System.out.print("Digite o ID do prato a incluir no pedido (ou 0 para finalizar): ");
                            int idPrato = scanner.nextInt();
                            if (idPrato == 0) {
                                break;
                            }

                            Prato pratoSelecionado = buscarPratoPorId(idPrato);
                            if (pratoSelecionado != null) {
                                pratosSelecionados[pratoCount++] = pratoSelecionado;
                                // Atualize o tempo de preparo com o maior valor
                                tempoTotalPreparacao = Math.max(tempoTotalPreparacao, pratoSelecionado.getTempoPreparacao());
                                maiorTempoConsumo = Math.max(maiorTempoConsumo, pratoSelecionado.getTempoConsumo()); // Cálculo do maior tempo de consumo
                            } else {
                                System.out.println("Prato não encontrado.");
                            }
                        }

                        // Alteração: corrigir incremento do pedidoAtual
                        int idPedido = pedidoAtual++;  // Garantir que o ID seja correto

                        System.out.println("tempo total de preparação: " + tempoTotalPreparacao);
                        System.out.println("maior tempo de consumo: " + maiorTempoConsumo);

                        // Criar novo pedido com base nos dados coletados
                        Pedido novoPedido = new Pedido(idPedido, mesa, pratosSelecionados, momentoAtual, tempoTotalPreparacao, maiorTempoConsumo, 1);

                        // Atribuir o pedido à mesa e adicionar à lista de pedidos
                        mesa.setPedidoAssociado(novoPedido);
                        pedidos[idPedido] = novoPedido;
                        System.out.println("Pedido criado com sucesso para a mesa " + idMesa + "!");
                        System.out.println("Teste tempo:" + maiorTempoConsumo);
                    }







                    private void entregarComida(Scanner scanner, int momentoAtual) {
                        System.out.print("Digite o ID da mesa para entregar comida: ");
                        int idMesa = scanner.nextInt();


                        Mesa mesa = null;
                        for (Mesa m : mesas) {
                            if (m != null && m.getIdMesa() == idMesa && m.getStatus() == 0) { // Mesa ocupada
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

                    }




                    private void verificarPreparacaoPronta(int momentoAtual) {
                        for (Pedido pedido : pedidos) {
                            if (pedido != null && pedido.getStatus() == 1) { // Pedido ativo
                                int tempoPreparacao = pedido.getTempoPreparacao();
                                if (momentoAtual >= pedido.getMomentoCriacao() + tempoPreparacao) {
                                    System.out.println("Comida do pedido associado à mesa " + pedido.getMesaAssociada().getIdMesa() + " está pronta para ser entregue.");
                                    pedido.setPreparado(true);
                                }
                            }
                        }
                    }










                    private void finalizarPedido(Scanner scanner, int momentoAtual) {
                        System.out.print("\nDigite o ID da mesa cujo pedido deseja finalizar: ");
                        int idMesa = scanner.nextInt();

                        Mesa mesa = buscarMesaPorId(idMesa);

                        if (mesa == null || mesa.getStatus() != 0) {
                            System.out.println("Mesa não está ocupada ou não encontrada.");
                            return;
                        }

                        Pedido pedido = buscarPedidoPorMesa(mesa);

                        if (pedido == null || pedido.isFinalizado()) {
                            System.out.println("Não há pedido para finalizar ou já foi finalizado.");
                            return;
                        }


                        int maiorTempoConsumo = 0;
                        for (Prato prato : pedido.getPratos()) {
                            if (prato != null && prato.getTempoConsumo() > maiorTempoConsumo) {
                                maiorTempoConsumo = prato.getTempoConsumo();
                            }
                        }


                        if (momentoAtual < pedido.getMomentoEntrega() + pedido.getTempoConsumo()) {
                            System.out.println("O pedido ainda não foi consumido por completo.");
                            return;
                        }

                        pedido.setFinalizado(true);
                        mesa.setStatus(1);
                        mesa.setReservaAssociada(null);
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
