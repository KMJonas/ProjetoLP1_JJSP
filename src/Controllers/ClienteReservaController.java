package Controllers;

import Models.ClienteReserva;

public class ClienteReservaController {
    LeituraFicheirosController lf = new LeituraFicheirosController();

    //Array global que irá conter todas as reservas
    ClienteReserva [] reservas;

    //Construtor que irá ler todas as reservas
    public ClienteReservaController(){
        lerReservas();
    }

    public void lerReservas(){
        reservas = lf.devolverClientesReserva("src/Data/Reservas");
    }

    public ClienteReserva[] getReservas() {
        return reservas;
    }

    public void setReservas(ClienteReserva[] reservas) {
        this.reservas = reservas;
    }
}
