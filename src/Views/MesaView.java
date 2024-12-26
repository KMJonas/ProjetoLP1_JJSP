package Views;

import Controllers.MesaController;
import Models.ClienteReserva;
import Models.Mesa;

import java.util.Scanner;

public class MesaView {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarMesas() {
        Mesa[] mesas = MesaController.getMesas();
        for(int i = 0; i < mesas.length; i++) {
            if(mesas[i] != null) {
                System.out.println("➤ Mesa Nº: " + mesas[i].getIdMesa());
                System.out.println("➤ Capacidade: " + mesas[i].getCapacidade());
                System.out.println("➤ Status: " + mesas[i].getStatus());
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            }
        }
    }

    public static void adicionarMesa(){
        System.out.println("➤ Criar Mesa");
        char resposta = 0;
        int capacidade;

        do {
            do {
                System.out.println("➤ Insira a capacidade da mesa: ");
                while (!sc.hasNextInt()) {
                    System.out.println("⚠ Capacidade inválida ⚠");
                    sc.next();
                    System.out.println("➤ Insira a capacidade da mesa: ");
                }

                capacidade = sc.nextInt();

                if (capacidade <= 0) {
                    System.out.println("⚠ Capacidade inválida ⚠");
                }

            } while (capacidade <= 0);


            MesaController.setMesas(MesaController.adicionarMesa(capacidade));

            System.out.println("➤ Deseja adicionar mais mesas? (S/N)");
            resposta = sc.next().charAt(0);
        } while (resposta == 'S' || resposta == 's');
    }

    public static void editarMesa(){
        int numMesa;

        System.out.println("➤ Insira o número da mesa:");
        while (!sc.hasNextInt()) {
            System.out.println("⚠ Número inválido ⚠");
            sc.next();
            System.out.println("➤ Insira o número da mesa: ");
        }

        numMesa = sc.nextInt();
        if (MesaController.verificarMesa(numMesa)){
            System.out.println("➤ Qual desta opções deseja alterar?");
            System.out.println("● 1 → Capacidade da mesa.");
            System.out.println("● 2 → Disponibilidade da mesa.");
            System.out.println("● 3 → Ambas as opções.");

            int opcao;
            while (true) {
                if (sc.hasNextInt()) {
                    opcao = sc.nextInt();
                    if (opcao >= 1 && opcao <= 3) {
                        break;
                    } else {
                        System.out.println("⚠ Número inválido. Escolha entre 1 e 3 ⚠");
                    }
                } else {
                    System.out.println("⚠ Entrada inválida. Por favor, insira um número ⚠");
                    sc.next();
                }
                System.out.println("Escolha uma das opções abaixo: ");
                System.out.println("● 1 → Capacidade da mesa.");
                System.out.println("● 2 → Disponibilidade da mesa.");
                System.out.println("● 3 → Ambas as opções.");
            }

            switch (opcao) {
                case 1:
                    int capacidade;
                    System.out.println("Insira a capacidade da mesa: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("⚠ Número inválido ⚠");
                        sc.next();
                        System.out.println("➤ Insira o capacidade da mesa: ");
                    }

                    capacidade = sc.nextInt();

                    if(MesaController.modificarMesa(numMesa,opcao,capacidade,0)){
                        System.out.println("➤ Capacidade da mesa modificada com sucesso!");
                    }else {
                        System.out.println("⚠ Erro ao modificar a capacidade da mesa ⚠");
                    };
                    break;
                case 2:
                    int status;
                    System.out.println("Defina o status da mesa: ");
                    System.out.println("● 0 → Indisponivel.");
                    System.out.println("● 1 → Disponivel.");

                    while (true) {
                        if (sc.hasNextInt()) {
                            status = sc.nextInt();
                            if (status >= 0 && status <= 1) {
                                break;
                            } else {
                                System.out.println("⚠ Número inválido. Escolha entre 0 e 1 ⚠");
                            }
                        } else {
                            System.out.println("⚠ Entrada inválida. Por favor, insira um número ⚠");
                            sc.next();
                        }
                        System.out.println("Escolha uma das opções abaixo: ");
                        System.out.println("● 0 → Indisponivel.");
                        System.out.println("● 1 → Disponivel.");
                    }

                    if(MesaController.modificarMesa(numMesa,opcao,0,status)){
                        System.out.println("➤ Status da mesa modificado com sucesso!");
                    }else {
                        System.out.println("⚠ Erro ao modificar o status da mesa ⚠");
                    };
                    break;
                case 3:
                    int capacidade2;
                    System.out.println("Insira a capacidade da mesa: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("⚠ Número inválido ⚠");
                        sc.next(); // Limpa entrada inválida
                        System.out.println("➤ Insira a capacidade da mesa: ");
                    }
                    capacidade2 = sc.nextInt();

                    int status2;
                    System.out.println("Defina o status da mesa: ");
                    System.out.println("● 0 → Indisponível.");
                    System.out.println("● 1 → Disponível.");

                    while (true) {
                        if (sc.hasNextInt()) {
                            status2 = sc.nextInt();
                            if (status2 >= 0 && status2 <= 1) {
                                break;
                            } else {
                                System.out.println("⚠ Número inválido. Escolha entre 0 e 1 ⚠");
                            }
                        } else {
                            System.out.println("⚠ Entrada inválida. Por favor, insira um número ⚠");
                            sc.next();
                        }
                        System.out.println("● 0 → Indisponível.");
                        System.out.println("● 1 → Disponível.");
                    }

                    if (MesaController.modificarMesa(numMesa, opcao, capacidade2, status2)) {
                        System.out.println("➤ Capacidade da mesa modificada com sucesso!");
                    } else {
                        System.out.println("⚠ Erro ao modificar a capacidade da mesa ⚠");
                    }
            }
        }else{
            System.out.println("⚠ Não existe nenhuma mesa com esse número ⚠");
        }
    }

    public static void eliminarMesa(){
        int numMesa;

        System.out.println("➤ Insira o número da mesa:");
        while (!sc.hasNextInt()) {
            System.out.println("⚠ Número inválido ⚠");
            sc.next();
            System.out.println("➤ Insira o número da mesa: ");
        }

        numMesa = sc.nextInt();

        if (MesaController.verificarMesa(numMesa)){
            MesaController.removerMesa(numMesa);
            System.out.println("Mesa removida com sucesso!");
        } else {
            System.out.println("⚠ Não existe nenhuma mesa com esse número ⚠");
        }
    }
}
