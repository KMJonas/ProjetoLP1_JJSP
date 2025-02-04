package Controllers;

import Models.*;
import Views.ClienteReservaView;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.jar.JarOutputStream;

public  class SimulacaoDiaADia {
    static Scanner sc = new Scanner(System.in);

    public static void iniciarSimulacao() {

        System.out.println("Bem-vindo à simulação de gestão de restaurante!");
        System.out.print("Quantos momentos (unidades de tempo) deseja simular? ");
        int totalMomentos = sc.nextInt();

        int momentoAtual = 0;

        while (momentoAtual <= totalMomentos) {
            System.out.println("\n \uD83D\uDD03 Momento " + momentoAtual + " iniciado \uD83D\uDD03");
            verificarPreparacaoPronta(momentoAtual);
            verificarConsumoFinalizado(momentoAtual);
            verificarTemposDeEsperaAtendimento(momentoAtual);
            exibirReservasNoMomento(momentoAtual);
            exibirMesasDisponiveis();
            exibirOcupacoes();


            boolean avancarMomento = false;
            while (!avancarMomento) {
                System.out.println("\n \uD83D\uDD0D O que deseja fazer? \uD83D\uDD0D");
                System.out.println("1 - Atribuir mesa a reserva");
                System.out.println("2 - Criar um novo pedido");
                System.out.println("3 - Entregar comida");
                System.out.println("4 - Finalizar pedido de uma mesa");
                System.out.println("5 - Adicionar Reserva");
                System.out.println("6 - Avançar para o próximo momento");
                System.out.print("Escolha: ");
                int escolha = sc.nextInt();

                switch (escolha) {
                    case 1 -> atribuirMesaAReserva(momentoAtual);
                    case 2 -> criarPedido(momentoAtual);
                    case 3 -> entregarComida(momentoAtual);
                    case 4 -> finalizarPedido(momentoAtual);
                    case 5 -> ClienteReservaView.adicionarReserva(momentoAtual);
                    case 6 -> {
                        avancarMomento = true;
                        System.out.println("⏳ Avançando para o próximo momento... ⏳");
                    }
                    default -> System.out.println("⚠\uFE0F Opção inválida. ⚠\uFE0F");
                }

                if (!avancarMomento) {
                    System.out.println("\n \uD83D\uDD03 Atualizações no Momento " + momentoAtual + " \uD83D\uDD03");
                    exibirReservasNoMomento(momentoAtual);
                    exibirMesasDisponiveis();
                    exibirOcupacoes();
                }
            }
            momentoAtual++;
        }
        System.out.println("\n \uD83D\uDD14 Simulação finalizada! \uD83D\uDD14");
    }

    public static void exibirReservasNoMomento(int momento) {
        System.out.println("\n \uD83D\uDCDD Reservas no momento " + momento + ": \uD83D\uDCDD");
        ClienteReserva[] reservas = ClienteReservaController.getReservas();
        Pedido[] pedidos = PedidoController.getListaPedidos();

        for (int i = 0; i < reservas.length; i++) {

            if (reservas[i] != null) {

                // Verifica se a reserva já está associada a um pedido com mesa
                boolean reservaTemMesa = false;
                for (int j = 0; j < pedidos.length; j++) {
                    if (pedidos[j] != null && pedidos[j].getReserva() != null &&
                            pedidos[j].getReserva().getIdReserva() == reservas[i].getIdReserva() &&
                            pedidos[j].getMesaAssociada() != null) {
                        reservaTemMesa = true;
                        break;
                    }
                }

                // Se a reserva já tiver uma mesa atribuída, não exibe novamente
                if (reservaTemMesa) {
                    continue;
                }

                if (pedidos[i] == null) {
                    if(reservas[i].getHoraChegada() == momento){
                        System.out.println("➜ Reserva ID: " + reservas[i].getIdReserva() +
                                " | Nome: " + reservas[i].getNome() +
                                " | Nº Pessoas: " + reservas[i].getNumPessoas());
                        Pedido criarPedido = new Pedido(reservas[i], null, null, 0, 0, 0, 0, 1);
                        PedidoController.adicionarPedido(criarPedido);

                    }
                } else{
                    if (reservas[i].getHoraChegada() == momento && pedidos[i].getStatus() != 7) {
                        System.out.println("➜ Reserva ID: " + reservas[i].getIdReserva() +
                                " | Nome: " + reservas[i].getNome() +
                                " | Nº Pessoas: " + reservas[i].getNumPessoas());

                    } else if (reservas[i].getHoraChegada() < momento && pedidos[i].getStatus() != 7) {
                        System.out.println("➜ Reserva ID: " + reservas[i].getIdReserva() +
                                " | Nome: " + reservas[i].getNome() +
                                " | Nº Pessoas: " + reservas[i].getNumPessoas() +
                                " (Momento original: " + reservas[i].getHoraChegada() + ")");
                    }
                }
            }
        }
    }

    public static void exibirMesasDisponiveis() {
        System.out.println("\n \uD83D\uDCCB Mesas disponíveis: \uD83D\uDCCB");
        Mesa[] mesas = MesaController.getMesas();

        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i] != null && mesas[i].getStatus() == 1) {
                System.out.println("➜ Mesa ID: " + mesas[i].getIdMesa() +
                        " | Capacidade: " + mesas[i].getCapacidade());
            }
        }
    }

    public static void exibirOcupacoes() {
        System.out.println("\n \uD83D\uDCCC Ocupações atuais: \uD83D\uDCCC");
        Pedido[] pedidos = PedidoController.getListaPedidos();

        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null && pedidos[i].getMesaAssociada() != null && pedidos[i].getStatus() != 7) {
                System.out.print(" ➜ Mesa ID: " + pedidos[i].getMesaAssociada().getIdMesa() +
                        " | Nome Reserva: " + pedidos[i].getReserva().getNome() +
                        " | Nº Pessoas: " + pedidos[i].getReserva().getNumPessoas());

                if (pedidos[i].getMomentoCriacao() == 0) {
                    System.out.println(" | Status Pedido: À espera de ser atendido");
                    continue; // Pula para o próximo pedido
                }

                if (!pedidos[i].isPreparado()) {
                    System.out.println(" | Status Pedido: À espera da comida");
                    continue;
                }

                if (pedidos[i].getStatus() == 5 || pedidos[i].getStatus() == 6) {
                    if (pedidos[i].isConsumido()) {
                        System.out.println(" | Status Pedido: Consumido (Pronto a pagar)");
                    } else {
                        System.out.println(" | Entregue (A consumir)");
                    }
                } else if (pedidos[i].getStatus() == 4) {
                    System.out.println(" | Status Pedido: Pronto para Entrega");
                }
            }
        }
    }

    public static void verificarConsumoFinalizado(int momentoAtual) {
        Pedido[] pedidos = PedidoController.getListaPedidos();

        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null && pedidos[i].getStatus() == 5) {
                if (momentoAtual == pedidos[i].getMomentoCriacao() + pedidos[i].getTempoPreparacao() + pedidos[i].getTempoConsumo() + 2) {
                    System.out.println(momentoAtual);
                    System.out.println(pedidos[i].getMomentoCriacao());
                    System.out.println(" ");
                    System.out.println("⚠\uFE0F Pedido associado à mesa " +
                            pedidos[i].getMesaAssociada().getIdMesa() +
                            " foi consumido e pode ser finalizado. ⚠\uFE0F");
                    pedidos[i].setConsumido(true);
                    pedidos[i].setStatus(6); // Status: Consumido
                }
            }
        }
    }

    public static void atribuirMesaAReserva(int momentoAtual) {
        System.out.print("\n➤ Digite o ID da reserva: ");
        int idReserva = sc.nextInt();
        ClienteReserva reserva = ClienteReservaController.buscarReservaPorId(idReserva);

        if (reserva == null) {
            System.out.println("⚠\uFE0F Reserva não encontrada. ⚠\uFE0F");
            return;
        }

        Pedido pedidoReserva = PedidoController.getPedidoByReserva(reserva);

        if (reserva == null && pedidoReserva.getReserva().getHoraChegada() > momentoAtual) {
            System.out.println("⚠\uFE0F A reserva só pode ser atribuída no momento de chegada ou depois. ⚠\uFE0F");
            return;
        }

        if (pedidoReserva != null) {
            if (pedidoReserva.getMesaAssociada() != null) {
                System.out.println("⚠\uFE0F Reserva já associada a uma mesa. ⚠\uFE0F");

            }else {
                System.out.println("➤ Insira o ID da mesa para associar a reserva: ");
                int idMesa = sc.nextInt();
                Mesa mesa = MesaController.buscarMesaPorId(idMesa);
                if (mesa.getStatus() == 1) {
                    if (mesa.getCapacidade() >= reserva.getNumPessoas()) {
                        pedidoReserva.setMesaAssociada(mesa);
                        pedidoReserva.setStatus(2);
                        pedidoReserva.settMomentoAtribuicaoMesa(momentoAtual);
                        mesa.setStatus(0); //Desabilita a mesa
                        System.out.println("\uD83D\uDCE2 Mesa " + pedidoReserva.getMesaAssociada().getIdMesa() + " atribuída à reserva com o nome " + pedidoReserva.getReserva().getNome() + ". \uD83D\uDCE2");
                    } else {
                        System.out.println("⚠\uFE0F Mesa com capacidade insuficiente. ⚠\uFE0F");
                    }
                } else {
                    System.out.println("⚠\uFE0F Mesa não disponível. ⚠\uFE0F");
                }
            }

        }
    }

    public static void criarPedido (int momentoAtual){
        System.out.print("➤ Digite o ID da mesa para criar um pedido: ");
        int idMesa = sc.nextInt();

        Mesa mesa = MesaController.buscarMesaPorId(idMesa);

        if (mesa == null || mesa.getStatus() != 0) {
            System.out.println("⚠\uFE0F Mesa não encontrada ou não está reservada. ⚠\uFE0F");
            return;
        }

        Pedido pedido = PedidoController.getPedidoByMesa(mesa);


        // Verifica se já existe um pedido associado à mesa
        if (pedido.getStatus() >= 3) {
            System.out.println("⚠\uFE0F Já existe um pedido em andamento para esta mesa. ⚠\uFE0F");
            return;
        }

        // Impede criação do pedido no mesmo momento da atribuição (momento de chegada)
        if (momentoAtual == pedido.gettMomentoAtribuicaoMesa()) {
            System.out.println("⚠\uFE0F O pedido não pode ser criado no mesmo momento em que a mesa foi atribuída. ⚠\uFE0F");
            return;
        }

        Prato [] pratos = PratoController.getPratos();
        System.out.println("\uD83C\uDF7D\uFE0F Pratos disponíveis: \uD83C\uDF7D\uFE0F");
        for (int i = 0; i < pratos.length; i++) {
            if (pratos[i] != null) {
                System.out.println("➜ Prato ID: " + pratos[i].getIdPrato() +
                        " | Nome: " + pratos[i].getNome() +
                        " | Categoria: " + pratos[i].getCategoria() +
                        " | Tempo de Preparação: " + pratos[i].getunidadeTempoPreparacao() + " momentos" +
                        " | Tempo de Consumo: " + pratos[i].getunidadeTempoConsumo() + " momentos");
            }
        }

        Prato[] pratosSelecionados = new Prato[50];
        int pratoCount = 0;
        int tempoTotalPreparacao = 0;
        int maiorTempoConsumo = 0;
        while (true) {
            System.out.print("➤ Digite o ID do prato a incluir no pedido (ou 0 para finalizar): ");
            int idPrato = sc.nextInt();
            if (idPrato == 0){
                int countPratosPrincipais = 0;
                if (countPratosPrincipais == 0 ){

                    for (int i = 0; i < pratosSelecionados.length; i++) {
                        if (pratosSelecionados[i] != null && pratosSelecionados[i].getCategoria().equals("Principal")) {
                            countPratosPrincipais++;
                        }
                    }

                }else {

                    countPratosPrincipais = 0;
                }

                System.out.println(countPratosPrincipais);
                if(pedido.getReserva().getNumPessoas() > countPratosPrincipais){
                    System.out.println("⚠\uFE0F O número de pratos principais tem de ser igual ao número de pessoas presentes na mesa. ⚠\uFE0F");
                } else if (pedido.getReserva().getNumPessoas() < countPratosPrincipais){
                    pratosSelecionados = new Prato[50];
                    System.out.println("⚠\uFE0F O número de pratos principais tem de ser igual ao número de pessoas presentes na mesa. Por favor volte a introduzir os pratos que deseja! ⚠\uFE0F");
                    System.out.println("\uD83C\uDF7D\uFE0F Pratos disponíveis: \uD83C\uDF7D\uFE0F");
                    for (int i = 0; i < pratos.length; i++) {
                        if (pratos[i] != null) {
                            System.out.println("➜ Prato ID: " + pratos[i].getIdPrato() +
                                    " | Nome: " + pratos[i].getNome() +
                                    " | Categoria: " + pratos[i].getCategoria() +
                                    " | Tempo de Preparação: " + pratos[i].getunidadeTempoPreparacao() + " momentos" +
                                    " | Tempo de Consumo: " + pratos[i].getunidadeTempoConsumo() + " momentos");
                        }
                    }

                }else {
                    break;
                }
            } else {
                Prato pratoSelecionado = PratoController.buscarPratoPorId(idPrato);
                if (pratoSelecionado != null) {
                    pratosSelecionados[pratoCount++] = pratoSelecionado;
                    tempoTotalPreparacao = Math.max(tempoTotalPreparacao, pratoSelecionado.getunidadeTempoPreparacao());
                    maiorTempoConsumo = Math.max(maiorTempoConsumo, pratoSelecionado.getunidadeTempoConsumo());
                } else {
                    System.out.println("⚠\uFE0F Prato não encontrado. ⚠\uFE0F");
                }
            }
        }

        System.out.println("\uD83D\uDCE2 Tempo total de preparação: " + tempoTotalPreparacao +" \uD83D\uDCE2");
        System.out.println("\uD83D\uDCE2 Maior tempo de consumo: " + maiorTempoConsumo + " \uD83D\uDCE2");

        pedido.setPratos(pratosSelecionados);
        pedido.setMomentoCriacao(momentoAtual);
        pedido.setTempoPreparacao(tempoTotalPreparacao);
        pedido.setTempoConsumo(maiorTempoConsumo);
        pedido.setStatus(3);

        System.out.println("✅ Pedido criado com sucesso para a mesa " + idMesa + "! ✅");
    }

    public static void entregarComida (int momentoAtual){
        System.out.print("➤ Digite o ID da mesa para entregar comida: ");
        int idMesa = sc.nextInt();
        Mesa mesa = MesaController.buscarMesaPorId(idMesa);
        if (mesa == null || mesa.getStatus() == 1) {
            System.out.println("⚠\uFE0F Mesa não encontrada ou não está ocupada. ⚠\uFE0F");
            return;
        }

        Pedido pedido = PedidoController.getPedidoByMesa(mesa);
        if (pedido == null || pedido.getStatus() < 3) {
            System.out.println("⚠\uFE0F Não há pedido associado a esta mesa. ⚠\uFE0F");
            return;
        }

        if (momentoAtual < pedido.getMomentoCriacao() + pedido.getTempoPreparacao() + 1) {
            System.out.println("⚠\uFE0F A comida ainda não está pronta para ser entregue. ⚠\uFE0F");
            return;
        }

        System.out.println("✅ Comida entregue com sucesso na mesa " + idMesa + "! ✅");
        pedido.setStatus(5); // Status: Comida entregue
        pedido.setmomentoEntregaPrato(momentoAtual);
    }

    public static void verificarPreparacaoPronta(int momentoAtual) {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        System.out.println("\uD83D\uDCE2 Estados dos pedidos no momento: \uD83D\uDCE2");

        for (int i = 0; i < pedidos.length; i++) {

            // Só processa pedidos que ainda estão aguardando a entrega (statusPedido == 3)
            if (pedidos[i] != null && pedidos[i].getStatus() == 3 && pedidos[i].getPratos() != null) {
                int momentoPronto = pedidos[i].getMomentoCriacao() + pedidos[i].getTempoPreparacao() + 1;

                if (momentoAtual == momentoPronto) {
                    System.out.println("➤ Comida do pedido associado à mesa " +
                            pedidos[i].getMesaAssociada().getIdMesa() +
                            " está pronta para ser entregue (no momento: " + momentoPronto + ").");
                    pedidos[i].setPreparado(true);
                    pedidos[i].setStatus(4); // Status: Pronto para entrega
                } else if (momentoAtual < momentoPronto) {
                    System.out.println("➤ Comida do pedido associado à mesa " +
                            pedidos[i].getMesaAssociada().getIdMesa() +
                            " ainda não está pronta. Tempo restante: " +
                            (momentoPronto - momentoAtual) + " momento(s).");
                } else {
                    // Mesmo que o momento de entrega já tenha passado, mostramos que está pronta,
                    // mas só se o pedido ainda estiver aguardando a entrega.
                    System.out.println("➤ Comida do pedido associado à mesa " +
                            pedidos[i].getMesaAssociada().getIdMesa() +
                            " está pronta para ser entregue (desde o momento: " + momentoPronto + ").");
                }
            }
        }
    }

    public static void finalizarPedido (int momentoAtual){
        System.out.print("\n➤ Digite o ID da mesa cujo pedido deseja finalizar: ");
        int idMesa = sc.nextInt();
        Mesa mesa = MesaController.buscarMesaPorId(idMesa);
        if (mesa == null || mesa.getStatus() != 0) {
            System.out.println("⚠\uFE0F Mesa não encontrada ou mesa não atribuida. ⚠\uFE0F");
            return;
        }

        Pedido pedido = PedidoController.getPedidoByMesa(mesa);

        if (pedido == null || pedido.gettMomentoAtribuicaoMesa() == 0) {
            System.out.println("⚠\uFE0F A reserva ainda não tem mesa atribuida ⚠\uFE0F");
            return;
        }

        if (pedido == null || pedido.getMomentoCriacao() == 0) {
            System.out.println("⚠\uFE0F A mesa ainda não fez um pedido. ⚠\uFE0F");
            return;
        }

        if (pedido == null || !pedido.isConsumido()) {
            System.out.println("⚠\uFE0F A mesa ainda não consumiu o pedido por completo. ⚠\uFE0F");
            return;
        }

        double totalPagar = 0;
        for (int i = 0; i < pedido.getPratos().length; i++) {
            if (pedido.getPratos()[i] != null) {
                totalPagar += pedido.getPratos()[i].getPrecoVenda();
            }
        }

        System.out.println("✅ Pedido da mesa " + idMesa + " na reserva com o nome " + pedido.getReserva().getNome() + " finalizado com sucesso, tendo um total a pagar de " + totalPagar + "€. ✅");
        pedido.setStatus(7);// Status: Finalizado
        pedido.setHoraFinalizacao(momentoAtual);
        mesa.setStatus(1);
    }

    public static void verificarTemposDeEsperaAtendimento(int momentoAtual) {
        Pedido[] pedidos = PedidoController.getListaPedidos();
        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null && pedidos[i].getStatus() != 7) {
                Mesa mesaAssociada = null;

                if (pedidos[i].getMesaAssociada() != null) { // Verifica se há uma mesa associada
                    mesaAssociada = MesaController.buscarMesaPorId(pedidos[i].getMesaAssociada().getIdMesa());
                }

                // Verifica se o tempo de espera da reserva já foi excedido para atribuição de mesa
                if (pedidos[i].gettMomentoAtribuicaoMesa() == 0) {
                    if ((pedidos[i].getReserva().getHoraChegada() + GlobalStorage.getTempoMaxEsperaEntrada() + 1) == momentoAtual) {
                        System.out.print("⚠️ AVISO ⚠️");
                        System.out.println(" ➤ A reserva com o nome " + pedidos[i].getReserva().getNome() + " ultrapassou o tempo máximo de espera para ser atribuída uma mesa. ❌");
                        pedidos[i].setPrejuizo(true);
                        pedidos[i].setStatus(7);
                    }
                }

                if (pedidos[i].gettMomentoAtribuicaoMesa() != 0 && pedidos[i].getStatus() == 2) {
                    if ((pedidos[i].gettMomentoAtribuicaoMesa() + GlobalStorage.getTempoMaxEsperaAtendimento() + 1) == momentoAtual) {
                        System.out.print("⚠️ AVISO ⚠️");
                        System.out.println(" ➤ A mesa " + pedidos[i].getMesaAssociada().getIdMesa() + " (" + pedidos[i].getReserva().getNome() + ") ultrapassou o tempo máximo de espera para ser atendido. ❌");
                        pedidos[i].setPrejuizo(true);
                        pedidos[i].setStatus(7);
                        mesaAssociada.setStatus(1);
                    }
                }


                // Verifica se o tempo de espera para pagamento foi excedido
                if (pedidos[i].isConsumido()) {
                    if ((pedidos[i].getMomentoCriacao() + pedidos[i].getTempoPreparacao() + pedidos[i].getTempoConsumo() + GlobalStorage.getTempoMaxEsperaPagamento() + 1) == momentoAtual) {
                        System.out.print("⚠️ AVISO ⚠️");
                        System.out.println(" ➤ A mesa " + pedidos[i].getMesaAssociada().getIdMesa() + " (" + pedidos[i].getReserva().getNome() + ") ultrapassou o tempo máximo de espera para pagar. ❌");
                        pedidos[i].setPrejuizo(true);
                        pedidos[i].setStatus(7);
                        mesaAssociada.setStatus(1);
                    }
                }
            }
        }
    }
}