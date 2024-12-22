package Views;

import Controllers.PratoController;
import Models.Prato;

public class PratoView {
    PratoController pc = new PratoController();

    public void mostrarPratos() {
        Prato[] pratos = pc.getPratos();
        for (int i = 0; i < pratos.length; i++) {
            if (pratos[i] != null) {
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
}
