package Views;

import Controllers.*;
import Models.GlobalStorage;
import Models.Prato;

import java.text.ParseException;
import java.sql.SQLOutput;
import java.util.Scanner;


import static Controllers.SimulacaoDiaADia.iniciarSimulacao;
import static Views.GlobalStorageView.modificarPassword;

public class Menu {
    Scanner sc = new Scanner(System.in);
    LeituraFicheirosController lt = new LeituraFicheirosController();

    public void menu() throws ParseException {
        System.out.println("━━━━━━━ Restaurante JJSP ━━━━━━━");
        System.out.println("1 ➤ Gerir mesas e menus");
        System.out.println("2 ➤ Gerir Reservas");
        System.out.println("3 ➤ Dia-a-dia");
        System.out.println("4 ➤ Configurações");
        System.out.println("5 ➤ Estatisticas");
        System.out.println("6 ➤ Guardar Ficheiros");
        System.out.println("9 ➤ Sair");
        int resposta = sc.nextInt();

        switch (resposta) {
            case 1:
                menuGerirMesasMenus();
                break;
            case 2:
                menuGerirReservas();
                break;
            case 3:
                if(GlobalStorage.getTempoMaxEsperaEntrada() == 0 || GlobalStorage.getTempoMaxEsperaAtendimento() == 0 || GlobalStorage.getTempoMaxEsperaPagamento() == 0){
                    System.out.println("⚠ Tempo de espera não foi devidamente definido ⚠");
                }else {
                    iniciarSimulacao();
                }
                menu();
                break;
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
            case 5:

                break;
            case 6:
                menuGuardarFichieros();
            case 9:
                System.out.println("Deseja guardar os ficheiros antes de sair? (s/n)");
                String respostaSair = sc.next();
                if (respostaSair.equals("s")) {
                    if (lt.guardarClientesReserva()){
                        System.out.println("✅ Reservas guardadas com sucesso ✅");
                    }
                    if (lt.guardarMesas()){
                        System.out.println("✅ Mesas guardadas com sucesso ✅");
                    }
                    if (lt.guardarPratos()){
                        System.out.println("✅ Pratos guardados com sucesso ✅");
                    }
                    if (lt.guardarConfigGerais()) {
                        System.out.println("✅ Configurações guardadas com sucesso ✅");
                    }
                }
                System.exit(0);
            default:
                System.out.println("⚠ Opção inválida ⚠");
                menu();
        }
    }


    public void menuGerirMesasMenus() throws ParseException {
        System.out.println("━━━━━━━ Mesas e Menus ━━━━━━━");
        System.out.println("1 ➤ Gerir mesas");
        System.out.println("2 ➤ Gerir menus");
        System.out.println("8 ➤ Voltar");
        int resposta = sc.nextInt();

        switch (resposta) {
            case 1:
                menuGerirMesas();
                break;
            case 2:
                menuGerirMenus();
                break;
            case 8:
                menu();
                break;
            case 9:
                System.exit(0);
            default:
                System.out.println("⚠ Opção inválida ⚠");
                menuGerirMesasMenus();
                break;
        }
    }

    public void menuGerirMesas() throws ParseException {
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
                break;
            case 2:
                MesaView.adicionarMesa();
                menuGerirMesas();
                break;
            case 3:
                MesaView.editarMesa();
                menuGerirMesas();
                break;
            case 4:
                MesaView.eliminarMesa();
                menuGerirMesas();
                break;
            case 8:
                menuGerirMesasMenus();
                break;
            case 9:
                menu();
                break;
            default:
                System.out.println("⚠ Opção inválida ⚠");
                menuGerirMesas();
                break;
        }
    }

    public void menuGerirMenus() throws ParseException {
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
                break;
            case 2:
                PratoView.adicionarPrato();
                menuGerirMenus();
                break;
            case 3:
                PratoView.modificarPrato();
                menuGerirMenus();
                break;
            case 4:
                PratoView.eliminarPrato();
                menuGerirMenus();
                break;
            case 8:
                menuGerirMesasMenus();
                break;
            case 9:
                menu();
                break;
            default:
                System.out.println("⚠ Opção inválida ⚠");
                menuGerirMenus();
                break;
        }
    }

    public void menuGerirReservas() throws ParseException {
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
                break;
            case 2:
                ClienteReservaView.adicionarReserva(0);
                menuGerirReservas();
                break;
            case 3:
                ClienteReservaView.editarReserva();
                menuGerirReservas();
                break;
            case 4:
                ClienteReservaView.eliminarReserva();
                menuGerirReservas();
                break;
            case 8:
                menu();
                break;
            default:
                System.out.println("⚠ Opção inválida ⚠");
                menuGerirReservas();
                break;
        }
    }

    public void menuConfiguracoes() throws ParseException {
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
                break;
            case 2:
                menuDefinirSeparadorConteudo();
                menuConfiguracoes();
                break;
            case 3:
                menuDefinirUnidadesTempo();
                menuConfiguracoes();
                break;
            case 6:
                modificarPassword();
                menu();
                break;
            case 8:
                menu();
                break;
            default:
                System.out.println("⚠ Opção inválida ⚠");
                break;
        }
    }

    public void menuDefinirCaminhoLeitura() throws ParseException {
        System.out.println("━━━━━━━ Definir Caminho Leitura ━━━━━━━");
        if(GlobalStorage.getPathPratos() != null){
            System.out.println("Caminho Pratos ➤ " + GlobalStorage.getPathPratos());
        }else {
            System.out.println("Caminho Pratos ➤ Nenhum caminho definido.");
        }
        if(GlobalStorage.getPathMesas() != null){
            System.out.println("Caminho Mesas ➤ " + GlobalStorage.getPathMesas());
        }else {
            System.out.println("Caminho Mesas ➤ Nenhum caminho definido.");
        }
        if (GlobalStorage.getPathClientesReserva() != null){
            System.out.println("Caminho Reservas ➤ " + GlobalStorage.getPathClientesReserva());
        }else {
            System.out.println("Caminho Reservas ➤ Nenhum caminho definido.");
        }
        System.out.println(" ");
        System.out.println("1 ➤ Ficheiro Pratos");
        System.out.println("2 ➤ Ficheiro Mesas");
        System.out.println("3 ➤ Ficheiro Reservas");
        System.out.println("8 ➤ Voltar");
        System.out.println("9 ➤ Voltar menu principal");
        int resposta = sc.nextInt();

        switch (resposta){
            case 1:
                PratoView.caminhoLeituraPrato();
                menuDefinirCaminhoLeitura();
                break;
            case 2:
                MesaView.caminhoLeituraMesa();
                menuDefinirCaminhoLeitura();
                break;
            case 3:
                ClienteReservaView.caminhoLeituraClientesReserva();
                menuDefinirCaminhoLeitura();
                break;
            case 8:
                menuConfiguracoes();
                break;
            case 9:
                menu();
                break;
            default:
                System.out.println("⚠ Opção inválida ⚠");
                menuDefinirCaminhoLeitura();
                break;
        }
    }

    public void menuDefinirSeparadorConteudo(){
        System.out.println("━━━━━━━ Definir Separador Conteudo ━━━━━━━");
        if(GlobalStorage.getSeparadorConteudo() != null) {
            System.out.println("Separador atual ➤ " + GlobalStorage.getSeparadorConteudo());
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
        GlobalStorage.setSeparadorConteudo(separador);
    }

    public void menuDefinirUnidadesTempo() throws ParseException {
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
                break;
            case 2:
                ClienteReservaView.tempoEspera(resposta);
                menuDefinirUnidadesTempo();
                break;
            case 3:
                ClienteReservaView.tempoEspera(resposta);
                menuDefinirUnidadesTempo();
                break;
            case 8:
                menuConfiguracoes();
                break;
            case 9:
                menu();
                break;
            default:
                System.out.println("⚠ Opção inválida ⚠");
                menuDefinirUnidadesTempo();
                break;
        }
    }

    public void menuGuardarFichieros(){
        System.out.println("━━━━━━━ Guardar Ficheiros ━━━━━━━");
        System.out.println("1 ➤ Guardar mesas");
        System.out.println("2 ➤ Guardar pratos");
        System.out.println("3 ➤ Guardar reservas");
        System.out.println("4 ➤ Guardar configurações");
        System.out.println("5 ➤ Guardar tudo");
        System.out.println("8 ➤ Voltar");
        int resposta = sc.nextInt();

        switch (resposta){
            case 1:
                if(lt.guardarMesas()){
                    System.out.println("✅ Mesas guardadas com sucesso ✅");
                    menuGuardarFichieros();
                }
            case 2:
                if (lt.guardarPratos()){
                    System.out.println("✅ Pratos guardados com sucesso ✅");
                    menuGuardarFichieros();
                }
            case 3:
                if(lt.guardarClientesReserva()){
                    System.out.println("✅ Reservas guardadas com sucesso ✅");
                    menuGuardarFichieros();
                }
            case 4:
                if(lt.guardarConfigGerais()){
                    System.out.println("✅ Configurações guardadas com sucesso ✅");
                    menuGuardarFichieros();
                }
            case 5:
                if(lt.guardarMesas() && lt.guardarPratos() && lt.guardarClientesReserva() && lt.guardarConfigGerais()){
                    System.out.println("✅ Ficheiros guardados com sucesso ✅");
                    menuGuardarFichieros();
                }
            case 8:
                menu();
            default:
                System.out.println("⚠ Opção inválida ⚠");
                menuGuardarFichieros();
        }
    }

}
