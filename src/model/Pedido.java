package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens;
    private double totalPedido;
    private double frete;
    private double totalComFrete;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.totalPedido = 0;
        this.frete = 0;
        this.totalComFrete = 0;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        itens.add(new ItemPedido(produto, quantidade));
        calcularTotalPedido();
    }

    private void calcularTotalPedido() {
        double subtotal = 0;
        for (ItemPedido item : itens) {
            subtotal += item.getSubtotal();
        }
        this.totalPedido = subtotal;
        this.totalComFrete = this.totalPedido + this.frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
        this.totalComFrete = this.totalPedido + this.frete;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public double getFrete() {
        return frete;
    }

    public double getTotalComFrete() {
        return totalComFrete;
    }

    public double getPesoTotal() {
        double pesoTotal = 0;
        for (ItemPedido item : itens) {
            pesoTotal += item.getPesoTotalItem();
        }
        return pesoTotal;
    }
}