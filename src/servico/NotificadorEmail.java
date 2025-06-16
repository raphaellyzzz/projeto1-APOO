package servico;

import model.Cliente;

public class NotificadorEmail {
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Enviando em email para " + cliente.getEmail() + " : " + mensagem);
    }
}