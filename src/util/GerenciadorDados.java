package util;

import model.Cliente;
import model.Pedido;
import model.Produto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GerenciadorDados {
    private static GerenciadorDados instance;
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private List<Pedido> pedidos;

    private GerenciadorDados() {
        clientes = new ArrayList<>();
        produtos = new ArrayList<>();
        pedidos = new ArrayList<>();
    }

    public static synchronized GerenciadorDados getInstance() {
        if (instance == null) {
            instance = new GerenciadorDados();
        }
        return instance;
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return Collections.unmodifiableList(clientes);
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> getProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return Collections.unmodifiableList(pedidos);
    }
}