package Views;

import Models.GlobalStorage;

import java.util.Scanner;

public class GlobalStorageView {
    static Scanner sc = new Scanner(System.in);

    public static void modificarPassword() {
        System.out.println("━━━━━━━ Modificar Password ━━━━━━━");
        System.out.println("Insira a nova password: ");
        String password = sc.nextLine();
        GlobalStorage.setPasswordConfiguracao(password);
    }
}
