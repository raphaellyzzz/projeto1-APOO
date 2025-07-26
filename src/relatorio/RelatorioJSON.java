package relatorio;

import model.ItemPedido;
import model.Pedido;

public class RelatorioJSON implements Relatorio {
    @Override
    public void gerar(Pedido pedido) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"cliente\": \"").append(pedido.getCliente().getNome()).append("\",\n");
        sb.append("  \"produtos\": [\n");
        for (int i = 0; i < pedido.getItens().size(); i++) {
            ItemPedido item = pedido.getItens().get(i);
            sb.append("    {\n");
            sb.append("      \"nome\": \"").append(item.getProduto().getNome()).append("\",\n");
            sb.append("      \"quantidade\": ").append(item.getQuantidade()).append(",\n");
            sb.append("      \"preco\": ").append(String.format("%.2f", item.getSubtotal())).append("\n");
            sb.append("    }");
            if (i < pedido.getItens().size() - 1) {
                sb.append(",\n");
            } else {
                sb.append("\n");
            }
        }
        sb.append("  ],\n");
        sb.append("  \"total\": ").append(String.format("%.2f", pedido.getTotalPedido())).append(",\n");
        sb.append("  \"frete\": ").append(String.format("%.2f", pedido.getFrete())).append(",\n");
        sb.append("  \"total_com_frete\": ").append(String.format("%.2f", pedido.getTotalComFrete())).append("\n");
        sb.append("}");
        System.out.println(sb.toString());
    }
}