package Controllers;

import Models.GlobalStorage;

public class GlobalStorageController {

    public static boolean verificarPassword(String password) {
       if(password.equals(GlobalStorage.getPasswordConfiguracao())) {
           return true;
       }
       return false;
    }
}
