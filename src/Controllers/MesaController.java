package Controllers;

import Models.Mesa;

public class MesaController {
    LeituraFicheirosController lf = new LeituraFicheirosController();

    //Array global que irá conter todas as mesas
    Mesa[] mesas;

    //Construtor que irá ler todas as mesas
    public MesaController(){
        lerMesas();
    }

    public void lerMesas(){
        mesas = lf.devolverMesas("src/Data/Mesas");
    }

    public Mesa[] getMesas() {
        return mesas;
    }

    public void setMesas(Mesa[] mesas) {
        this.mesas = mesas;
    }
}
