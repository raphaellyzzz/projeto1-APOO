package servico;

import model.Cliente;
import model.Pedido;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}