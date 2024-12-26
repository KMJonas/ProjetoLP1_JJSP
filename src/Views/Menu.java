package Views;

import Controllers.ClienteReservaController;
import Controllers.MesaController;

import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);

    public void menu() {
        System.out.println("━━━━━━━ Restaurante JJSP ━━━━━━━");
        System.out.println("1 ➤ Gerir mesas e menus");
        System.out.println("2 ➤ Gerir Reservas");
        System.out.println("3 ➤ Dia-a-dia");
        System.out.println("4 ➤ Sair");
        int resposta = sc.nextInt();

        switch (resposta) {
            case 1:
                menuGerirMesasMenus();
            case 2:
                menuGerirReservas();
            case 3:
                System.out.println("Pratos");
            case 4:
                System.out.println("Sair");
            default:
                System.out.println("⚠ Opção inválida ⚠");
        }
    }

    public void menuGerirMesasMenus() {
        System.out.println("━━━━━━━ Mesas e Menus ━━━━━━━");
        System.out.println("1 ➤ Gerir mesas");
        System.out.println("2 ➤ Gerir menus");
        System.out.println("3 ➤ Voltar");
        int resposta = sc.nextInt();

        switch (resposta) {
            case 1:
                menuGerirMesas();
            case 2:
                menuGerirMenus();
            case 3:
                menu();
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
        System.out.println("5 ➤ Voltar");
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
            case 5:
                menuGerirMesasMenus();
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
        System.out.println("5 ➤ Voltar");
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
            case 5:
                menuGerirMesasMenus();
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
        System.out.println("5 ➤ Voltar");
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
            case 5:
                menu();
            default:
                System.out.println("⚠ Opção inválida ⚠");
        }
    }

}
