package Views;

import Controllers.ClienteReservaController;
import Controllers.LeituraFicheirosController;
import Models.ClienteReserva;
import Models.GlobalStorage;

import java.text.ParseException;
import java.util.Scanner;

public class ClienteReservaView {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarClientesReserva() {
        if(GlobalStorage.getPathClientesReserva() != null){
            ClienteReserva[] reserva = ClienteReservaController.getReservas();
            System.out.println("━━━━━━━ Mostrar Reservas ━━━━━━━");
            for(int i = 0; i < reserva.length; i++) {
                if(reserva[i] != null) {
                    System.out.println("➤ ID Reserva: " + reserva[i].getIdReserva());
                    System.out.println("➤ Nome cliente: " + reserva[i].getNome());
                    System.out.println("➤ Numero de pessoas: " + reserva[i].getNumPessoas());
                    System.out.println("➤ Numero de pessoas que comem entrada: " + reserva[i].getNumPessoasEntrada());
                    System.out.println("➤ Numero de pessoas que comem sobremesa: " + reserva[i].getNumPessoasSobremesa());
                    System.out.println("➤ Hora de chegada: " + reserva[i].getHoraChegada());
                    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                }
            }
            System.out.println(" ");
            System.out.println("➤ Tempo maximo de espera para entrar: " + GlobalStorage.getTempoMaxEsperaEntrada());
            System.out.println("➤ Tempo maximo de espera para ser atendido: " + GlobalStorage.getTempoMaxEsperaAtendimento());
            System.out.println("➤ Tempo maximo de espera para pagamento: " + GlobalStorage.getTempoMaxEsperaPagamento());
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

                if (!nome.matches("[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+")) {
                    System.out.println("⚠ Nome inválido ⚠");
                }

            } while (!nome.matches("[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+"));

            int numPessoas;
            do {
                System.out.println("➤ Numero de pessoas: ");
                while (!sc.hasNextInt()) {
                    System.out.println("⚠ Insira um número válido ⚠");
                    sc.next();
                }

                numPessoas = sc.nextInt();

                if (numPessoas <= 0) {
                    System.out.println("⚠ Número tem de ser superior ou igual a 0 ⚠");
                }

            } while (numPessoas <= 0);

            int numPessoasComemEntrada;
            do {
                System.out.println("➤ Numero de pessoas que comem entrada: ");
                while (!sc.hasNextInt()) {
                    System.out.println("⚠ Insira um número válido ⚠");
                    sc.next();
                }

                numPessoasComemEntrada = sc.nextInt();

                if (numPessoasComemEntrada <= 0) {
                    System.out.println("⚠ Número tem de ser superior ou igual a 0 ⚠");
                } else if (numPessoasComemEntrada > numPessoas) {
                    System.out.println("⚠ Número de pessoas que comem entrada não pode ser superior ao número de pessoas ⚠");
                }

            } while (numPessoasComemEntrada <= 0);

            int numPessoasComemSobremesa;
            do {
                System.out.println("➤ Numero de pessoas que comem sobremesa: ");
                while (!sc.hasNextInt()) {
                    System.out.println("⚠ Insira um número válido ⚠");
                    sc.next();
                }

                numPessoasComemSobremesa = sc.nextInt();

                if (numPessoasComemSobremesa <= 0) {
                    System.out.println("⚠ Número tem de ser superior ou igual a 0 ⚠");
                } else if (numPessoasComemSobremesa > numPessoas) {
                    System.out.println("⚠ Número de pessoas que comem sobremesa não pode ser superior ao número de pessoas ⚠");
                }
            } while (numPessoasComemSobremesa <= 0);

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

            /*
            int tempoMaxEsperaEntrada;
            do {
                System.out.println("➤ Tempo maximo de espera para entrar: ");
                while (!sc.hasNextInt()) {
                    System.out.println("⚠ Insira um número válido ⚠");
                    sc.next();
                }

                tempoMaxEsperaEntrada = sc.nextInt();

                if (tempoMaxEsperaEntrada < 0) {
                    System.out.println("⚠ Número tem de ser superior a 0 ⚠");
                }

            } while (tempoMaxEsperaEntrada < 0);
             */

            ClienteReservaController.adicionarReserva(nome, numPessoas, numPessoasComemEntrada, numPessoasComemSobremesa, horaChegada);

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
        int numPessoasComemEntrada = reservaSelecionada.getNumPessoasEntrada();
        int numPessoasComemSobremesa = reservaSelecionada.getNumPessoasSobremesa();
        int horaChegada = reservaSelecionada.getHoraChegada();
        int tempoMaxEsperaEntrada = GlobalStorage.getTempoMaxEsperaEntrada();
        int tempoMaxEsperaAtendimento = GlobalStorage.getTempoMaxEsperaAtendimento();
        int tempoMaxEsperaPagamento = GlobalStorage.getTempoMaxEsperaPagamento();

        System.out.println("➤ Caso não deseje alterar algum destes tópicos, por favor insira 'n'");

        do {
            System.out.println("➤ Insira o nome da pessoa: ");
            nome = sc.nextLine();
            if (nome.equals("n")) {
                nome = reservaSelecionada.getNome();
                break;
            }
            if (!nome.matches("[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+")) {
                System.out.println("⚠ Nome inválido ⚠");
            }
        } while (!nome.matches("[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+"));

        do {
            System.out.println("➤ Insira o número de pessoas: ");
            String numPessoasN = sc.nextLine();
            if (numPessoasN.equals("n")) {
                numPessoas = reservaSelecionada.getNumPessoas(); // Não altera
                break;
            }

            do {
                if (!numPessoasN.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("⚠ Número de pessoas inválido. ⚠");
                    System.out.println("➤ Insira o número de pessoas: ");
                    numPessoasN = sc.nextLine();
                }
            }while (!numPessoasN.matches("\\d+(\\.\\d+)?"));

            numPessoas = Integer.parseInt(numPessoasN);

            if(numPessoas <= 0){
                System.out.println("⚠ Número de pessoas tem de ser maior que 0 ⚠");
            }

        } while (numPessoas <= 0);

        do {
            System.out.println("➤ Insira o número de pessoas que comem entrada: ");
            String numPessoasComemEntradaN = sc.nextLine();
            if (numPessoasComemEntradaN.equals("n")) {
                numPessoasComemEntrada = reservaSelecionada.getNumPessoasEntrada(); // Não altera
                break;
            }

            do {
                if (!numPessoasComemEntradaN.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("⚠ Número de pessoas inválido. ⚠");
                    System.out.println("➤ Insira o número de pessoas que comem entrada: ");
                    numPessoasComemEntradaN = sc.nextLine();
                }
            } while (!numPessoasComemEntradaN.matches("\\d+(\\.\\d+)?"));

            numPessoasComemEntrada = Integer.parseInt(numPessoasComemEntradaN);

            if (numPessoasComemEntrada < 0) {
                System.out.println("⚠ Número de pessoas tem de ser maior que 0 ⚠");
            } else if (numPessoasComemEntrada > numPessoas) {
                System.out.println("⚠ Número de pessoas que comem entrada não pode ser superior ao número de pessoas ⚠");
            }

        } while (numPessoasComemEntrada < 0 || numPessoasComemEntrada > numPessoas); ;

        do {
            System.out.println("➤ Insira o número de pessoas que comem sobremesa: ");
            String numPessoasComemSobremesaN = sc.nextLine();
            if (numPessoasComemSobremesaN.equals("n")) {
                numPessoasComemSobremesa = reservaSelecionada.getNumPessoasEntrada(); // Não altera
                break;
            }

            do {
                if (!numPessoasComemSobremesaN.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("⚠ Número de pessoas inválido. ⚠");
                    System.out.println("➤ Insira o número de pessoas que comem sobremesa: ");
                    numPessoasComemSobremesaN = sc.nextLine();
                }
            } while (!numPessoasComemSobremesaN.matches("\\d+(\\.\\d+)?"));

            numPessoasComemSobremesa = Integer.parseInt(numPessoasComemSobremesaN);

            if (numPessoasComemSobremesa < 0) {
                System.out.println("⚠ Número de pessoas tem de ser maior que 0 ⚠");
            } else if (numPessoasComemSobremesa > numPessoas) {
                System.out.println("⚠ Número de pessoas que comem sobremesa não pode ser superior ao número de pessoas ⚠");
                {

                }

            }
        } while (numPessoasComemSobremesa < 0 || numPessoasComemSobremesa > numPessoas);

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
            } while (!horaChegadaN.matches("\\d+(\\.\\d+)?"));

            horaChegada = Integer.parseInt(horaChegadaN);

            if (horaChegada < 0) {
                System.out.println("⚠ Número tem de ser maior que 0 ⚠");
            }

        } while (horaChegada < 0);

        /*
        do {
            System.out.println("➤ Insira o tempo máximo de espera para entrar: ");
            String tempoMaxEsperaEntradaN = sc.nextLine();
            if (tempoMaxEsperaEntradaN.equals("n")) {
                tempoMaxEsperaEntrada = reservaSelecionada.getNumPessoas(); // Não altera
                break;
            }

            do {
                if (!tempoMaxEsperaEntradaN.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("⚠ Número inválido. ⚠");
                    System.out.println("➤ Insira o tempo máximo de espera para entrar: ");
                    tempoMaxEsperaEntradaN = sc.nextLine();
                }
            } while (!tempoMaxEsperaEntradaN.matches("\\d+(\\.\\d+)?"));

            tempoMaxEsperaEntrada = Integer.parseInt(tempoMaxEsperaEntradaN);

            if (tempoMaxEsperaEntrada < 0) {
                System.out.println("⚠ Número tem de ser maior que 0 ⚠");
            }

        } while (tempoMaxEsperaEntrada < 0);

        do {
            System.out.println("➤ Insira o tempo máximo de espera para ser atendido: ");
            String tempoMaxEsperaAtendimentoN = sc.nextLine();
            if (tempoMaxEsperaAtendimentoN.equals("n")) {
                tempoMaxEsperaAtendimento = reservaSelecionada.getNumPessoas(); // Não altera
                break;
            }

            do {
                if (!tempoMaxEsperaAtendimentoN.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("⚠ Número inválido. ⚠");
                    System.out.println("➤ Insira o tempo máximo de espera para ser atendido: ");
                    tempoMaxEsperaAtendimentoN = sc.nextLine();
                }
            } while (!tempoMaxEsperaAtendimentoN.matches("\\d+(\\.\\d+)?"));

            tempoMaxEsperaAtendimento = Integer.parseInt(tempoMaxEsperaAtendimentoN);

            if (tempoMaxEsperaAtendimento < 0) {
                System.out.println("⚠ Número tem de ser maior que 0 ⚠");
            }

        } while (tempoMaxEsperaAtendimento < 0);

        do {
            System.out.println("➤ Insira o tempo máximo de espera para pagamento: ");
            String tempoMaxEsperaPagamentoN = sc.nextLine();
            if (tempoMaxEsperaPagamentoN.equals("n")) {
                tempoMaxEsperaPagamento = reservaSelecionada.getNumPessoas(); // Não altera
                break;
            }

            do {
                if (!tempoMaxEsperaPagamentoN.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("⚠ Número inválido. ⚠");
                    System.out.println("➤ Insira o tempo máximo de espera para pagamento: ");
                    tempoMaxEsperaPagamentoN = sc.nextLine();
                }
            } while (!tempoMaxEsperaPagamentoN.matches("\\d+(\\.\\d+)?"));

            tempoMaxEsperaPagamento = Integer.parseInt(tempoMaxEsperaPagamentoN);

            if (tempoMaxEsperaPagamento < 0) {
                System.out.println("⚠ Número tem de ser maior que 0 ⚠");
            }

        } while (tempoMaxEsperaPagamento < 0);
        */


        ClienteReserva reservaAtualizada = new ClienteReserva(reservaSelecionada.getIdReserva(), nome, numPessoas, numPessoasComemEntrada, numPessoasComemSobremesa, horaChegada);

        if (ClienteReservaController.modificarReserva(reservaAtualizada)) {
            System.out.println("➤ Reserva editada com sucesso");
        } else {
            System.out.println("⚠ Erro ao editar a reserva ⚠");
        }
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

    public static void caminhoLeituraClientesReserva(){
        LeituraFicheirosController lf = new LeituraFicheirosController();
        System.out.println("━━━━━━━ Alterar Caminho Leitura Reserva ━━━━━━━");

        try{
            if(GlobalStorage.getSeparadorConteudo() != null){
                System.out.println("Insira o caminho desejado do ficheiro: ");
                String path = sc.nextLine();
                if(lf.devolverClientesReserva(path)[0].getIdReserva() != 0){
                    GlobalStorage.setPathClientesReserva(path);
                    ClienteReservaController.lerReservas();
                    ClienteReservaController.lerReservas();
                    System.out.println("✔ Caminho do ficheiro atualizado com sucesso!");
                }else {
                    System.out.println("⚠ O ficheiro fornecido não contém pratos válidos. Verifique o caminho e tente novamente. ⚠");
                }
            }else {
                System.out.println("⚠ O separador de conteúdo não está definido, por favor configure o separador nas configurações ⚠");
            }
        } catch (Exception e) {
            System.out.println("⚠ Ocorreu um erro ao ler o ficheiro. Por favor, verifique o formato do conteúdo. ⚠");
        }
    }

    public static void tempoEspera(int resposta){
        ClienteReserva[] reserva = ClienteReservaController.getReservas();
        if(resposta == 1){
            System.out.println("➤ Tempo de espera para entrar: ");
            int tempo = sc.nextInt();
            if(tempo > 0){
                GlobalStorage.setTempoMaxEsperaEntrada(tempo);
            }else{
                System.out.println("⚠ Tempo tem de ser superior a 0 ⚠");
            }
        } else if (resposta == 2) {
            System.out.println("➤ Tempo de espera para ser atendido: ");
            int tempo = sc.nextInt();
            if (tempo > 0) {
                GlobalStorage.setTempoMaxEsperaAtendimento(tempo);
            } else {
                System.out.println("⚠ Tempo tem de ser superior a 0 ⚠");
            }
        } else if (resposta == 3) {
            System.out.println("➤ Tempo de espera para pagamento: ");
            int tempo = sc.nextInt();
            if (tempo > 0) {
                GlobalStorage.setTempoMaxEsperaPagamento(tempo);
            } else {
                System.out.println("⚠ Tempo tem de ser superior a 0 ⚠");
            }
        }

    }

}
