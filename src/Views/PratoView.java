package Views;

import Controllers.MesaController;
import Controllers.PratoController;
import Models.Prato;

import java.util.Scanner;

public class PratoView {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarPratos() {
        Prato[] pratos = PratoController.getPratos();
        for (int i = 0; i < pratos.length; i++) {
            if (pratos[i] != null) {
                System.out.println("➤ ID Prato: " + pratos[i].getIdPrato());
                System.out.println("➤ Nome: " + pratos[i].getNome());
                System.out.println("➤ Categoria: " + pratos[i].getCategoria());
                System.out.println("➤ Preço custo: " + pratos[i].getPrecoCusto());
                System.out.println("➤ Preço venda: " + pratos[i].getPrecoVenda());
                System.out.println("➤ Unidade tempo: " + pratos[i].getUnidadeTempo());
                System.out.println("➤ Estado: " + pratos[i].getEstado());
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            }
        }
    }

    public static void adicionarPrato() {
        Prato[] listaPratos = PratoController.getPratos();
        int idCount = 0;

        // Calcula o maior ID já existente
        for (int i = 0; i < listaPratos.length; i++) {
            if (listaPratos[i] != null) {
                idCount++;
            }
        }

        char resposta;
        do {
            String nome;
            do {
                System.out.println("➤ Insira o nome do prato: ");
                nome = sc.nextLine();

                if (!nome.matches("[a-zA-Z\\s]+")) {
                    System.out.println("⚠ Nome inválido ⚠");
                }

            } while (!nome.matches("[a-zA-Z\\s]+"));

            String categoria;
            do {
                System.out.println("➤ Insira a categoria do prato (Entrada, Prato principal, Sobremesa): ");
                categoria = sc.nextLine();

                if (!PratoController.confirmarCategoriaPrato(categoria) || !categoria.matches("[a-zA-Z\\s]+")) {
                    System.out.println("⚠ Categoria inválida ⚠");
                }

            } while (!categoria.matches("[a-zA-Z\\s]+") || !PratoController.confirmarCategoriaPrato(categoria));

            double precoCusto;
            do {
                System.out.println("➤ Insira o preço de custo do prato: ");
                while (!sc.hasNextDouble()) {
                    System.out.println("⚠ Preço inválido ⚠");
                    sc.next();
                }

                precoCusto = sc.nextDouble();

                if (!PratoController.confirmarPreco(precoCusto)) {
                    System.out.println("⚠ Preço inválido ⚠");
                }

            } while (!PratoController.confirmarPreco(precoCusto));

            double precoVenda;
            do {
                System.out.println("➤ Insira o preço de venda do prato: ");
                while (!sc.hasNextDouble()) {
                    System.out.println("⚠ Preço inválido ⚠");
                    sc.next();
                }
                precoVenda = sc.nextDouble();

                if (!PratoController.confirmarPreco(precoVenda)) {
                    System.out.println("⚠ Preço inválido ⚠");
                }
            } while (!PratoController.confirmarPreco(precoVenda));

            int unidadeTempo;
            do {
                System.out.println("➤ Insira a unidade de tempo do prato: ");
                while (!sc.hasNextInt()) {
                    System.out.println("⚠ Unidade de tempo inválida ⚠");
                    sc.next();
                }

                unidadeTempo = sc.nextInt();

                if (unidadeTempo <= 0) {
                    System.out.println("⚠ Unidade de tempo inválida ⚠");
                }

            } while (unidadeTempo <= 0);

            PratoController.setPratos(PratoController.adicionarPrato(++idCount, nome, categoria, precoCusto, precoVenda, unidadeTempo));

            System.out.println("➤ Deseja adicionar mais pratos? (S/N)");
            resposta = sc.next().charAt(0);
            sc.nextLine();
        } while (resposta == 'S' || resposta == 's');
    }


    public static void modificarPrato() {
        Prato[] listaPratos = PratoController.getPratos();
        System.out.println("➤ Escolha um dos pratos que deseja modificar");

        for (int i = 0; i < listaPratos.length; i++) {
            if (listaPratos[i] != null) {
                System.out.println(listaPratos[i].getIdPrato() + " ➤ " + listaPratos[i].getNome());
            }
        }

        int resposta;
        while (true) {
            if (sc.hasNextInt()) {
                resposta = sc.nextInt();
                sc.nextLine();
                if (resposta >= 1 && resposta <= listaPratos.length && listaPratos[resposta - 1] != null) {
                    break;
                } else {
                    System.out.println("⚠ Número inválido. Escolha uma das opções acima ⚠");
                }
            } else {
                System.out.println("⚠ Entrada inválida. Por favor, insira um número válido ⚠");
                sc.next();
            }
        }

        Prato pratoSelecionado = listaPratos[resposta - 1];

        String nome = pratoSelecionado.getNome();
        String categoria = pratoSelecionado.getCategoria();
        double precoCusto = pratoSelecionado.getPrecoCusto();
        double precoVenda = pratoSelecionado.getPrecoVenda();
        int unidadeTempo = pratoSelecionado.getUnidadeTempo();
        int estado = pratoSelecionado.getEstado();

        System.out.println("➤ Caso não deseje alterar algum destes tópicos, por favor insira 'n'");

        do {
            System.out.println("➤ Insira o nome do prato: ");
            nome = sc.nextLine();
            if (nome.equals("n")) {
                nome = pratoSelecionado.getNome();
                break;
            }
            if (!nome.matches("[a-zA-Z\\s]+")) {
                System.out.println("⚠ Nome inválido ⚠");
            }
        } while (!nome.matches("[a-zA-Z\\s]+"));

        do {
            System.out.println("➤ Insira a categoria do prato (Entrada, Prato principal, Sobremesa): ");
            categoria = sc.nextLine();
            if (categoria.equals("n")) {
                categoria = pratoSelecionado.getCategoria();
                break;
            }
            if (!PratoController.confirmarCategoriaPrato(categoria)) {
                System.out.println("⚠ Categoria inválida ⚠");
            }
        } while (!PratoController.confirmarCategoriaPrato(categoria));

        do {
            System.out.println("➤ Insira o preço de custo do prato: ");
            String precoN = sc.nextLine();

            if (precoN.equals("n")) {
                precoCusto = pratoSelecionado.getPrecoCusto();
                break;
            }

            precoN = precoN.replace(',', '.');

            do {
                if (!precoN.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("⚠ Preço inválido ⚠");
                    System.out.println("➤ Insira o preço de custo do prato: ");
                    precoN = sc.nextLine();
                    precoN = precoN.replace(',', '.');
                }
            } while (!precoN.matches("\\d+(\\.\\d+)?"));

            precoCusto = Double.parseDouble(precoN);


            if (!PratoController.confirmarPreco(precoCusto)) {
                System.out.println("⚠ Preço inválido ⚠");
            }

        } while (!PratoController.confirmarPreco(precoCusto));

        do {
            System.out.println("➤ Insira o preço de venda do prato: ");
            String precoN = sc.nextLine();
            if (precoN.equals("n")) {
                precoVenda = pratoSelecionado.getPrecoVenda();
                break;
            }

            precoN = precoN.replace(',', '.');

            do {
                if (!precoN.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("⚠ Preço inválido ⚠");
                    System.out.println("➤ Insira o preço de venda do prato: ");
                    precoN = sc.nextLine();
                    precoN = precoN.replace(',', '.');
                }
            } while (!precoN.matches("\\d+(\\.\\d+)?"));

            precoVenda = Double.parseDouble(precoN);

            if (!PratoController.confirmarPreco(precoVenda)) {
                System.out.println("⚠ Preço inválido ⚠");
            }

        } while (!PratoController.confirmarPreco(precoVenda));

        do {
            System.out.println("➤ Insira a unidade de tempo do prato: ");
            String unidadeTempoN = sc.nextLine();
            if (unidadeTempoN.equals("n")) {
                unidadeTempo = pratoSelecionado.getUnidadeTempo(); // Não altera
                break;
            }

            do {
                if (!unidadeTempoN.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("⚠ Preço inválido ⚠");
                    System.out.println("➤ Insira a unidade de tempo do prato: ");
                    unidadeTempoN = sc.nextLine();
                }
            }while (!unidadeTempoN.matches("\\d+(\\.\\d+)?"));

            unidadeTempo = Integer.parseInt(unidadeTempoN);

            if(unidadeTempo <= 0){
                System.out.println("⚠ Unidade de tempo inválida ⚠");
            }

        } while (unidadeTempo <= 0);

        do {
            System.out.println("➤ Insira o estado que pretende (Indisponível(0) ou Disponível(1)): ");
            String estadoN = sc.nextLine();

            if (estadoN.equals("n")) {
                estado = pratoSelecionado.getEstado(); // Não altera
                break;
            }


            if (!estadoN.matches("[01]")) {
                System.out.println("⚠ Estado inválido. Insira 0 para Indisponível ou 1 para Disponível ⚠");
                continue;
            }

            estado = Integer.parseInt(estadoN);

        } while (estado < 0 || estado > 1);

        Prato pratoAtualizado = new Prato(resposta,nome, categoria, precoCusto, precoVenda, unidadeTempo, estado);

        PratoController.modificarPrato(pratoAtualizado);
        System.out.println("Prato atualizado com sucesso!");
    }

    public static void eliminarPrato(){
        int idPrato;

        Prato[] listaPratos = PratoController.getPratos();
        System.out.println("➤ Escolha o prato que deseja eliminar");

        for (int i = 0; i < listaPratos.length; i++) {
            if (listaPratos[i] != null) {
                System.out.println(listaPratos[i].getIdPrato() + " ➤ " + listaPratos[i].getNome());
            }
        }

        int resposta;


        while (true) {
            if (sc.hasNextInt()) {
                resposta = sc.nextInt();
                sc.nextLine();
                if (resposta >= 1 && resposta <= listaPratos.length && listaPratos[resposta - 1] != null) {
                    break;
                } else {
                    System.out.println("⚠ Número inválido. Escolha uma das opções acima ⚠");
                }
            } else {
                System.out.println("⚠ Entrada inválida. Por favor, insira um número válido ⚠");
                sc.next();
            }
        }

        Prato pratoSelecionado = listaPratos[resposta - 1];

        idPrato = pratoSelecionado.getIdPrato();

        if (PratoController.verificarPrato(idPrato)){
            PratoController.removerPrato(idPrato);
            System.out.println("Prato removido com sucesso!");
        } else {
            System.out.println("⚠ Não existe nenhum prato com esse número ⚠");
        }
    }
}
