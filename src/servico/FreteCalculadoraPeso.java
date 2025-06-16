package servico;

import model.Pedido;

public class FreteCalculadoraPeso {
    public double calcular(Pedido pedido) {
        return pedido.getPesoTotal() * 5.00;
    }
}