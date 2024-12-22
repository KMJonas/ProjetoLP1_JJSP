package Controllers;

import Models.Prato;

public class PratoController {
    LeituraFicheirosController lf = new LeituraFicheirosController();

    //Array global que irá conter todos os pratos
    Prato[] pratos;

    public PratoController(){
        lerPratos();
    }

    public void lerPratos(){
        pratos = lf.devolverPratos("src/Data/Pratos");
    }

    public Prato[] getPratos() {
        return pratos;
    }

    public void setPratos(Prato[] pratos) {
        this.pratos = pratos;
    }
}
