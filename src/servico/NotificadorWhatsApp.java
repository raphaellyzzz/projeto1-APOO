package servico;

import model.Cliente;

public class NotificadorWhatsApp {
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Enviando mensagem no zap para " + cliente.getCpf() + " : " + mensagem);
    }
}