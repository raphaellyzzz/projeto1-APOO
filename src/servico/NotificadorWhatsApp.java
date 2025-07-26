package servico;

import model.Cliente;

public class NotificadorWhatsApp implements Notificador {
    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Enviando mensagem no zap para " + cliente.getTelefone() + " : " + mensagem);
    }
}