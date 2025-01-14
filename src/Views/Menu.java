package Views;

import Controllers.*;
import Models.Prato;

import java.util.Scanner;

import static Views.GlobalStorageView.modificarPassword;

public class Menu {
    Scanner sc = new Scanner(System.in);

    public void menu() {
        System.out.println("━━━━━━━ Restaurante JJSP ━━━━━━━");
        System.out.println("1 ➤ Gerir mesas e menus");
        System.out.println("2 ➤ Gerir Reservas");
        System.out.println("3 ➤ Dia-a-dia");
        System.out.println("4 ➤ Configurações");
        System.out.println("9 ➤ Sair");
        int resposta = sc.nextInt();

        switch (resposta) {
            case 1:
                menuGerirMesasMenus();
            case 2:
                menuGerirReservas();
            case 3:
                System.out.println("Pratos");
            case 4:
                String password;
                do {
                    System.out.println("Insira a password de acesso: ");
                    password = sc.next();
                    if (GlobalStorageController.verificarPassword(password)) {
                        menuConfiguracoes();
                        break;
                    } else {
                        if (password.equals(".")) {
                            menu();
                        }
                        System.out.println("⚠ Password incorreta, insira a tecla '.' se deseja sair ⚠");
                    }
                } while (!password.equals("."));

            case 9:
                System.exit(0);
            default:
                System.out.println("⚠ Opção inválida ⚠");
        }
    }

    public void menuGerirMesasMenus() {
        System.out.println("━━━━━━━ Mesas e Menus ━━━━━━━");
        System.out.println("1 ➤ Gerir mesas");
        System.out.println("2 ➤ Gerir menus");
        System.out.println("8 ➤ Voltar");
        int resposta = sc.nextInt();

        switch (resposta) {
            case 1:
                menuGerirMesas();
            case 2:
                menuGerirMenus();
            case 8:
                menu();
            case 9:
                System.exit(0);
            default:
                System.out.println("⚠ Opção inválida ⚠");
        }
    }

    public void menuGerirMesas() {
        System.out.println("━━━━━━━ Mesas ━━━━━━━");
        System.out.println("1 ➤ Mostrar mesas");
        System.out.println("2 ➤ Adicionar mesa");
        System.out.println("3 ➤ Editar mesa");
        System.out.println("4 ➤ Eliminar mesa");
        System.out.println("8 ➤ Voltar");
        System.out.println("9 ➤ Voltar Menu Principal");
        int resposta = sc.nextInt();

        switch (resposta) {
            case 1:
                MesaView.mostrarMesas();
                menuGerirMesas();
            case 2:
                MesaView.adicionarMesa();
                menuGerirMesas();
            case 3:
                MesaView.editarMesa();
                menuGerirMesas();
            case 4:
                MesaView.eliminarMesa();
                menuGerirMesas();
            case 8:
                menuGerirMesasMenus();
            case 9:
                menu();
            default:
                System.out.println("⚠ Opção inválida ⚠");
        }
    }

    public void menuGerirMenus() {
        System.out.println("━━━━━━━ Mesas ━━━━━━━");
        System.out.println("1 ➤ Mostrar pratos no menu");
        System.out.println("2 ➤ Adicionar prato ao menu");
        System.out.println("3 ➤ Editar prato no menu");
        System.out.println("4 ➤ Eliminar prato no menu");
        System.out.println("8 ➤ Voltar");
        System.out.println("9 ➤ Voltar Menu Principal");
        int resposta = sc.nextInt();

        switch (resposta) {
            case 1:
                PratoView.mostrarPratos();
                menuGerirMenus();
            case 2:
                PratoView.adicionarPrato();
                menuGerirMenus();
            case 3:
                PratoView.modificarPrato();
                menuGerirMenus();
            case 4:
                PratoView.eliminarPrato();
                menuGerirMenus();
            case 8:
                menuGerirMesasMenus();
            case 9:
                menu();
            default:
                System.out.println("⚠ Opção inválida ⚠");
        }
    }

    public void menuGerirReservas() {
        System.out.println("━━━━━━━ Reservas ━━━━━━━");
        System.out.println("1 ➤ Mostrar Reservas");
        System.out.println("2 ➤ Adicionar Reserva");
        System.out.println("3 ➤ Editar Reserva");
        System.out.println("4 ➤ Eliminar Reserva");
        System.out.println("8 ➤ Voltar");
        int resposta = sc.nextInt();

        switch (resposta) {
            case 1:
                ClienteReservaView.mostrarClientesReserva();
                menuGerirReservas();
            case 2:
                ClienteReservaView.adicionarReserva();
                menuGerirReservas();
            case 3:
                ClienteReservaView.editarReserva();
                menuGerirReservas();
            case 4:
                ClienteReservaView.eliminarReserva();
                menuGerirReservas();
            case 8:
                menu();
            default:
                System.out.println("⚠ Opção inválida ⚠");
        }
    }

    public void menuConfiguracoes(){
        System.out.println("━━━━━━━ Configurações ━━━━━━━");
        System.out.println("1 ➤ Definir caminho leitura de ficheiros.");
        System.out.println("2 ➤ Definir separador de campos.");
        System.out.println("3 ➤ Definir unidades tempo.");
        System.out.println("4 ➤ ");
        System.out.println("5 ➤ ");
        System.out.println("6 ➤ Alterar password de configuração.");
        System.out.println("8 ➤ Voltar");
        int resposta = sc.nextInt();

        switch (resposta) {
            case 1:
                menuDefinirCaminhoLeitura();
                menuConfiguracoes();
            case 2:
                menuDefinirSeparadorConteudo();
                menuConfiguracoes();
            case 3:
                menuDefinirUnidadesTempo();
                menuConfiguracoes();
            case 6:
                modificarPassword();
                menu();
            case 8:
                menu();
            default:
                System.out.println("⚠ Opção inválida ⚠");
        }
    }

    public void menuDefinirCaminhoLeitura(){
        System.out.println("━━━━━━━ Definir Caminho Leitura ━━━━━━━");
        if(PratoController.getPathLeituraPrato() != null){
            System.out.println("Caminho Pratos ➤ " + PratoController.getPathLeituraPrato());
        }else {
            System.out.println("Caminho Pratos ➤ Nenhum caminho definido.");
        }
        if(MesaController.getPathLeituraMesa() != null){
            System.out.println("Caminho Mesas ➤ " + MesaController.getPathLeituraMesa());
        }else {
            System.out.println("Caminho Mesas ➤ Nenhum caminho definido.");
        }
        if (ClienteReservaController.getPathLeituraClienteReserva() != null){
            System.out.println("Caminho Reservas ➤ " + ClienteReservaController.getPathLeituraClienteReserva());
        }else {
            System.out.println("Caminho Reservas ➤ Nenhum caminho definido.");
        }
        System.out.println(" ");
        System.out.println("1 ➤ Ficheiro Pratos");
        System.out.println("2 ➤ Ficheiro Mesas");
        System.out.println("3 ➤ Ficheiro Reservas");
        System.out.println("4 ➤ Voltar");
        System.out.println("5 ➤ Sair");
        int resposta = sc.nextInt();

        switch (resposta){
            case 1:
                PratoView.caminhoLeituraPrato();
                menuDefinirCaminhoLeitura();
            case 2:
                MesaView.caminhoLeituraMesa();
                menuDefinirCaminhoLeitura();
            case 3:
                ClienteReservaView.caminhoLeituraClientesReserva();
                menuDefinirCaminhoLeitura();
            case 4:
                menuConfiguracoes();
            default:
                System.out.println("⚠ Opção inválida ⚠");

        }
    }

    public void menuDefinirSeparadorConteudo(){
        System.out.println("━━━━━━━ Definir Separador Conteudo ━━━━━━━");
        if(LeituraFicheirosController.getSeparadorConteudo() != null) {
            System.out.println("Separador atual ➤ " + LeituraFicheirosController.getSeparadorConteudo());
        }else {
            System.out.println("➤ Separador atual ➤ Nenhum separador definido.");
        }
        System.out.println(" ");
        String separador;
        sc.nextLine();
        do {
            System.out.println("➤ Insira o separador de conteudo desejado: ");
            separador = sc.nextLine();

            if (!separador.matches("[\\p{P}\\p{S}]+")) {
                System.out.println("⚠ Separador inválido ⚠");
            }

        } while (!separador.matches("[\\p{P}\\p{S}]+"));
        LeituraFicheirosController.setSeparadorConteudo(separador);
    }

    public void menuDefinirUnidadesTempo(){
        System.out.println("━━━━━━━ Definir Unidades Tempo ━━━━━━━");
        System.out.println("1 ➤ Definir tempo de espera para entrar.");
        System.out.println("2 ➤ Definir tempo de espera para atendimento.");
        System.out.println("3 ➤ Definir tempo de espera para pagamento.");
        System.out.println("8 ➤ Voltar");
        System.out.println("9 ➤ Voltar menu principal");
        int resposta = sc.nextInt();

        switch (resposta){
            case 1:
                ClienteReservaView.tempoEspera(resposta);
                menuDefinirUnidadesTempo();
            case 2:
                ClienteReservaView.tempoEspera(resposta);
                menuDefinirUnidadesTempo();
            case 3:
                ClienteReservaView.tempoEspera(resposta);
                menuDefinirUnidadesTempo();
            case 8:
                menuConfiguracoes();
            case 9:
                menu();
            default:
                System.out.println("⚠ Opção inválida ⚠");
        }
    }

}
