package servico;

import model.Cliente;

public class NotificadorSMS {
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Enviando um SMS para " + cliente.getCpf() + " : " + mensagem);
    }
}