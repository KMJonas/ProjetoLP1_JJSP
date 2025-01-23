package Controllers;

import Models.GlobalStorage;

public class GlobalStorageController {
    static LeituraFicheirosController lf = new LeituraFicheirosController();

    public static void lerConfigGerais() {
        lf.devolverConfigGerais();
    }

    public static boolean verificarPassword(String password) {
       if(password.equals(GlobalStorage.getPasswordConfiguracao())) {
           return true;
       }
       return false;
    }
}
