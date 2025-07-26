package servico;

import model.Cliente;

public class NotificadorSMS implements Notificador {
    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Enviando um SMS para " + cliente.getTelefone() + " : " + mensagem);
    }
}