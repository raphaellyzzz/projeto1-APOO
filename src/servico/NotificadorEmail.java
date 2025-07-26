package servico;

import model.Cliente;

public class NotificadorEmail implements Notificador {
    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Enviando e-mail para " + cliente.getEmail() + " : " + mensagem);
    }
}