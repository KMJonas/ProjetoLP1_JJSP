package Controllers;

import Models.ClienteReserva;
import Models.Mesa;
import Models.Pedido;

public class PedidoController {
    static Pedido[] listaPedidos = new Pedido[100];
    static int indice = 0;

    public static void adicionarPedido(Pedido pedido) {
        if (indice < listaPedidos.length) {
            listaPedidos[indice] = pedido;
            indice++;
        } else {
            System.out.println("Lista de pedidos cheia!");
        }
    }

    public static Pedido[] getListaPedidos() {
        return listaPedidos;
    }

    public static void setListaPedidos(Pedido[] listaPedidos) {
        PedidoController.listaPedidos = listaPedidos;
    }

    public static Pedido getPedidoByReserva(ClienteReserva reserva) {
        for (int i = 0; i < listaPedidos.length; i++) {
            if (listaPedidos[i] != null && listaPedidos[i].getReserva().equals(reserva)) {
                return listaPedidos[i];
            }
        }
        return null;
    }

    public static Pedido getPedidoByMesa(Mesa mesa) {
        Pedido [] pedidos = getListaPedidos();
        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null && pedidos[i].getMesaAssociada() != null) {
                if (pedidos[i].getMesaAssociada().equals(mesa) && pedidos[i].getStatus() != 7) {
                    return pedidos[i];
                }
            }
        }
        return null;
    }

    public static boolean getPedidoByReserva(int id) {
        ClienteReserva reserva = ClienteReservaController.buscarReservaPorId(id);
        Pedido[] pedidos = getListaPedidos();
        for(int i = 0; i < listaPedidos.length; i++) {
            if(pedidos[i] != null && pedidos[i].getReserva().equals(reserva)) {
                return true;
            }
        }
        return false;
    }
}