package Views;

import Controllers.PratoController;
import Models.GlobalStorage;

import java.util.Scanner;

import static Models.GlobalStorage.setPrejuizoClienteNaoAtendido;
import static Models.GlobalStorage.setUnidadesDia;

public class GlobalStorageView {
    static Scanner sc = new Scanner(System.in);

    public static void modificarPassword() {
        System.out.println("━━━━━━━ Modificar Password ━━━━━━━");
        System.out.println("➤ Insira a nova password: ");
        String password = sc.nextLine();
        GlobalStorage.setPasswordConfiguracao(password);
    }

    public static void valorPrejuizoClienteNaoAtendido(){
        System.out.println("━━━━━━━ Valor Prejuízo Cliente Não Atendido ━━━━━━━");
        double valor;
        do {
            System.out.println("➤ Insira o valor de prejuizo por cliente: ");
            while (!sc.hasNextDouble()) {
                System.out.println("⚠ Valor inválido ⚠");
                sc.next();
            }

            valor = sc.nextDouble();

            if (valor <= 0) {
                System.out.println("⚠ Valor inválido ⚠");
            }else {
                setPrejuizoClienteNaoAtendido(valor);
            }

        } while (valor <= 0);

        System.out.println("✅ Valor de prejuízo por cliente atualizado com sucesso! ✅");
    }

    public static void valorUnidadeDia(){
        System.out.println("━━━━━━━ Valor Unidade dia ━━━━━━━");
        int valor;
        do {
            System.out.println("➤ Insira o valor para a unidade do dia: ");
            while (!sc.hasNextDouble()) {
                System.out.println("⚠ Valor inválido ⚠");
                sc.next();
            }

            valor = sc.nextInt();

            if (valor <= 0) {
                System.out.println("⚠ Valor inválido ⚠");
            }else {
                setUnidadesDia(valor);
            }

        } while (valor <= 0);
    }
}
