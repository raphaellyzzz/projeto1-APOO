package servico;

import model.Pedido;

public class FreteCalculadoraDistancia implements FreteCalculadora {
    private double distanciaKm;

    public FreteCalculadoraDistancia(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    @Override
    public double calcular(Pedido pedido) {
        return this.distanciaKm * 0.50;
    }
}