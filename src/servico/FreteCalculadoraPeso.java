package servico;

import model.Pedido;

public class FreteCalculadoraPeso implements FreteCalculadora {
    @Override
    public double calcular(Pedido pedido) {
        return pedido.getPesoTotal() * 5.00;
    }
}