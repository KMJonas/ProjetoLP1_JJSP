package Views;

import Controllers.ClienteReservaController;
import Controllers.PratoController;
import Models.ClienteReserva;
import Models.Prato;

import java.util.ResourceBundle;
import java.util.Scanner;

public class ClienteReservaView {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarClientesReserva() {
        ClienteReserva[] reserva = ClienteReservaController.getReservas();
        System.out.println("━━━━━━━ Mostrar Reservas ━━━━━━━");
        for(int i = 0; i < reserva.length; i++) {
            if(reserva[i] != null) {
                System.out.println("➤ ID Reserva: " + reserva[i].getIdReserva());
                System.out.println("➤ Nome cliente: " + reserva[i].getNome());
                System.out.println("➤ Numero de pessoas: " + reserva[i].getNumPessoas());
                System.out.println("➤ Hora de chegada: " + reserva[i].getHoraChegada());
                System.out.println("➤ Tempo maximo de espera: " + reserva[i].getTempoMaxEspera());
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            }
        }
    }

    public static void adicionarReserva(){
        ClienteReserva[] listaClientes = ClienteReservaController.getReservas();
        int idCount = 0;
        // Calcula o maior ID já existente
        for (int i = 0; i < listaClientes.length; i++) {
            if (listaClientes[i] != null) {
                idCount++;
            }
        }

        System.out.println("━━━━━━━ Adicionar Reserva ━━━━━━━");
        char resposta;
        do {
            String nome;
            do {
                System.out.println("➤ Nome do cliente: ");
                nome = sc.nextLine();

                if (!nome.matches("[a-zA-Z\\s]+")) {
                    System.out.println("⚠ Nome inválido ⚠");
                }

            } while (!nome.matches("[a-zA-Z\\s]+"));

            int numPessoas;
            do {
                System.out.println("➤ Numero de pessoas: ");
                while (!sc.hasNextInt()) {
                    System.out.println("⚠ Insira um número válido ⚠");
                    sc.next();
                }

                numPessoas = sc.nextInt();

                if (numPessoas < 0) {
                    System.out.println("⚠ Número tem de ser superior ou igual a 0 ⚠");
                }

            } while (numPessoas < 0);

            int horaChegada;
            do {
                System.out.println("➤ Hora de chegada: ");
                while (!sc.hasNextInt()) {
                    System.out.println("⚠ Insira um número válido ⚠");
                    sc.next();
                }

                horaChegada = sc.nextInt();

                if (horaChegada < 0) {
                    System.out.println("⚠ Número tem de ser superior a 0 ⚠");
                }

            } while (horaChegada < 0);

            int tempoMaxEspera;
            do {
                System.out.println("➤ Tempo maximo de espera: ");
                while (!sc.hasNextInt()) {
                    System.out.println("⚠ Insira um número válido ⚠");
                    sc.next();
                }

                tempoMaxEspera = sc.nextInt();

                if (tempoMaxEspera < 0) {
                    System.out.println("⚠ Número tem de ser superior a 0 ⚠");
                }

            } while (tempoMaxEspera < 0);

            ClienteReservaController.adicionarReserva(nome, numPessoas, horaChegada, tempoMaxEspera);

            System.out.println("➤ Deseja adicionar mais reservas? (S/N)");
            resposta = sc.next().charAt(0);
            sc.nextLine();
        } while (resposta == 'S' || resposta == 's');

    }

    public static void editarReserva(){
        ClienteReserva [] reservas = ClienteReservaController.getReservas();

        System.out.println("━━━━━━━ Editar Reserva ━━━━━━━");
        System.out.println("➤ Insira o ID da reserva que deseja editar: ");
        for(int i = 0; i < reservas.length; i++){
            if(reservas[i] != null){
                System.out.println("➤ ID Reserva: " + reservas[i].getIdReserva() + " (" + reservas[i].getNome() + ")");
            }
        }

        int resposta;
        while (true) {
            if (sc.hasNextInt()) {
                resposta = sc.nextInt();
                sc.nextLine();
                if (resposta >= 1 && resposta <= reservas.length && reservas[resposta - 1] != null) {
                    break;
                } else {
                    System.out.println("⚠ Número inválido. Escolha uma das opções acima ⚠");
                }
            } else {
                System.out.println("⚠ Entrada inválida. Por favor, insira um número válido ⚠");
                sc.next();
            }
        }

        ClienteReserva reservaSelecionada = reservas[resposta - 1];

        String nome = reservaSelecionada.getNome();
        int numPessoas = reservaSelecionada.getNumPessoas();
        int horaChegada = reservaSelecionada.getHoraChegada();
        int tempoMaxEspera = reservaSelecionada.getTempoMaxEspera();

        System.out.println("➤ Caso não deseje alterar algum destes tópicos, por favor insira 'n'");

        do {
            System.out.println("➤ Insira o nome da pessoa: ");
            nome = sc.nextLine();
            if (nome.equals("n")) {
                nome = reservaSelecionada.getNome();
                break;
            }
            if (!nome.matches("[a-zA-Z\\s]+")) {
                System.out.println("⚠ Nome inválido ⚠");
            }
        } while (!nome.matches("[a-zA-Z\\s]+"));

        do {
            System.out.println("➤ Insira o número de pessoas: ");
            String numPessoasN = sc.nextLine();
            if (numPessoasN.equals("n")) {
                numPessoas = reservaSelecionada.getNumPessoas(); // Não altera
                break;
            }

            do {
                if (!numPessoasN.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("⚠ Preço inválido ⚠");
                    System.out.println("➤ Insira o número de pessoas: ");
                    numPessoasN = sc.nextLine();
                }
            }while (!numPessoasN.matches("\\d+(\\.\\d+)?"));

            numPessoas = Integer.parseInt(numPessoasN);

            if(numPessoas < 0){
                System.out.println("⚠ Número de pessoas tem de ser maior que 0 ⚠");
            }

        } while (numPessoas < 0);

        do {
            System.out.println("➤ Insira a hora de chegada: ");
            String horaChegadaN = sc.nextLine();
            if (horaChegadaN.equals("n")) {
                horaChegada = reservaSelecionada.getNumPessoas(); // Não altera
                break;
            }

            do {
                if (!horaChegadaN.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("⚠ Preço inválido ⚠");
                    System.out.println("➤ Insira a hora de chegada: ");
                    horaChegadaN = sc.nextLine();
                }
            }while (!horaChegadaN.matches("\\d+(\\.\\d+)?"));

            horaChegada = Integer.parseInt(horaChegadaN);

            if(horaChegada < 0){
                System.out.println("⚠ Número tem de ser maior que 0 ⚠");
            }

        } while (horaChegada < 0);

        do {
            System.out.println("➤ Insira o tempo de espera: ");
            String tempoMaxEsperaN = sc.nextLine();
            if (tempoMaxEsperaN.equals("n")) {
                tempoMaxEspera = reservaSelecionada.getNumPessoas(); // Não altera
                break;
            }

            do {
                if (!tempoMaxEsperaN.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("⚠ Preço inválido ⚠");
                    System.out.println("➤ Insira o tempo de espera: ");
                    tempoMaxEsperaN = sc.nextLine();
                }
            }while (!tempoMaxEsperaN.matches("\\d+(\\.\\d+)?"));

            tempoMaxEspera = Integer.parseInt(tempoMaxEsperaN);

            if(tempoMaxEspera < 0){
                System.out.println("⚠ Número tem de ser maior que 0 ⚠");
            }

        } while (tempoMaxEspera < 0);

        ClienteReserva reservaAtualizada = new ClienteReserva(reservaSelecionada.getIdReserva(), nome, numPessoas, horaChegada, tempoMaxEspera);

        if (ClienteReservaController.modificarReserva(reservaAtualizada)){
            System.out.println("➤ Reserva editada com sucesso");
        } else {
            System.out.println("⚠ Erro ao editar a reserva ⚠");
        };
    }

    public static void eliminarReserva(){
        int idReserva;
        ClienteReserva[] listaReservas = ClienteReservaController.getReservas();
        System.out.println("━━━━━━━ Eliminar Reserva ━━━━━━━");
        System.out.println("➤ Escolha a reserva que deseja eliminar");

        for(int i = 0; i < listaReservas.length; i++){
            if(listaReservas[i] != null){
                System.out.println("➤ ID Reserva: " + listaReservas[i].getIdReserva() + " (" + listaReservas[i].getNome() + ")");
            }
        }

        int resposta;
        while (true) {
            if (sc.hasNextInt()) {
                resposta = sc.nextInt();
                sc.nextLine();
                if (resposta >= 1 && resposta <= listaReservas.length && listaReservas[resposta - 1] != null) {
                    break;
                } else {
                    System.out.println("⚠ Número inválido. Escolha uma das opções acima ⚠");
                }
            } else {
                System.out.println("⚠ Entrada inválida. Por favor, insira um número válido ⚠");
                sc.next();
            }
        }

        ClienteReserva reservaSelecionada = listaReservas[resposta - 1];

        idReserva = reservaSelecionada.getIdReserva();

        if (ClienteReservaController.verificarReserva(idReserva)){
            ClienteReservaController.removerReserva(idReserva);
            System.out.println("Reserva removida com sucesso!");
        } else {
            System.out.println("⚠ Não existe nenhum prato com esse número ⚠");
        }
    }
}
