import model.*;
import servico.*;
import relatorio.Relatorio;
import relatorio.RelatorioFactory;
import relatorio.RelatorioTextoFactory;
import relatorio.RelatorioJSONFactory;
import util.GerenciadorDados;
import java.util.stream.Collectors;

import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    static GerenciadorDados gerenciadorDados = GerenciadorDados.getInstance();

    public static void main(String[] args) {
        int opcao = 0;
        while (opcao != 5) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Produto");
            System.out.println("3. Criar Pedido");
            System.out.println("4. Listar Pedidos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número para a opção do menu.");
                continue;
            }


            switch (opcao) {
                case 1:
                    executarCadastroCliente();
                    break;
                case 2:
                    executarCadastroProduto();
                    break;
                case 3:
                    executarCriacaoPedido();
                    break;
                case 4:
                    executarListagemPedidos();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        input.close();
    }

    static void executarCadastroCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");

        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("CPF: ");
        String cpf = input.nextLine();

        System.out.print("E-mail: ");
        String email = input.nextLine();

        System.out.print("Telefone: ");
        String telefone = input.nextLine();

        gerenciadorDados.adicionarCliente(new Cliente(nome, cpf, email, telefone));
        System.out.println("O cliente foi cadastrado com sucesso!");
        System.out.println("----------");
    }

    static void executarCadastroProduto() {
        System.out.println("\n--- Cadastro de Produto ---");

        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("Preço: ");
        double preco;
        try {
            preco = Double.parseDouble(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Preço inválido. O produto não será cadastrado.");
            return;
        }


        System.out.print("Peso (kg): ");
        double peso;
        try {
            peso = Double.parseDouble(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Peso inválido. O produto não será cadastrado.");
            return;
        }

        gerenciadorDados.adicionarProduto(new Produto(nome, preco, peso));
        System.out.println("O Produto foi cadastrado com sucesso!");
        System.out.println("------");
    }

    static void executarCriacaoPedido() {
        System.out.println("\n--- Criação de Pedido ---");
        if (gerenciadorDados.getClientes().isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }
        if (gerenciadorDados.getProdutos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado. Cadastre produtos primeiro.");
            return;
        }

        Cliente clienteSelecionado = selecionarCliente();
        if (clienteSelecionado == null) {
            System.out.println("Seleção de cliente cancelada.");
            return;
        }


        Pedido novoPedido = new Pedido(clienteSelecionado);
        adicionarItensAoPedido(novoPedido);


        if (novoPedido.getItens().isEmpty()) {
            System.out.println("Pedido cancelado: Nenhum produto adicionado.");
            return;
        }

        calcularEAtribuirFrete(novoPedido);

        System.out.println("\nPedido criado com sucesso!");
        gerenciadorDados.adicionarPedido(novoPedido);

        simularNotificacao(clienteSelecionado, novoPedido);
        System.out.println("--------------");
    }

    static Cliente selecionarCliente() {
        System.out.println("\nClientes disponíveis:");
        List<Cliente> clientesDisponiveis = gerenciadorDados.getClientes();
        if (clientesDisponiveis.isEmpty()) {
            System.out.println("Nenhum cliente disponível.");
            return null;
        }

        for (int i = 0; i < clientesDisponiveis.size(); i++) {
            System.out.println(i + ": " + clientesDisponiveis.get(i).getNome() + " (" + clientesDisponiveis.get(i).getCpf() + ")");
        }

        int indice;
        while (true) {
            System.out.print("Selecione o número do cliente: ");
            try {
                indice = Integer.parseInt(input.nextLine());
                if (indice >= 0 && indice < clientesDisponiveis.size()) {
                    break;
                } else {
                    System.out.println("Índice inválido. Por favor, digite o número correto (o índice, não o número em parênteses).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
        return clientesDisponiveis.get(indice);
    }

    static void adicionarItensAoPedido(Pedido pedido) {
        List<Produto> produtosDisponiveis = gerenciadorDados.getProdutos();
        if (produtosDisponiveis.isEmpty()) {
            System.out.println("Nenhum produto disponível para adicionar.");
            return;
        }

        while (true) {
            System.out.println("\nProdutos disponíveis:");
            for (int i = 0; i < produtosDisponiveis.size(); i++) {
                System.out.println(i + ": " + produtosDisponiveis.get(i).getNome() + " (R$ " + String.format("%.2f", produtosDisponiveis.get(i).getPreco()) + " / " + produtosDisponiveis.get(i).getPeso() + "kg)");
            }
            System.out.print("Selecione o número do produto (ou -1 para sair): ");

            int produtoIndice;
            try {
                produtoIndice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                continue;
            }

            if (produtoIndice == -1) {
                break;
            }


            if (produtoIndice < 0 || produtoIndice >= produtosDisponiveis.size()) {
                System.out.println("Índice de produto inválido. Por favor, digite um número da lista.");
                continue;
            }

            System.out.print("Quantidade: ");
            int quantidade;
            try {
                quantidade = Integer.parseInt(input.nextLine());
                if (quantidade <= 0) {
                    System.out.println("Quantidade deve ser um número positivo.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número para a quantidade.");
                continue;
            }

            pedido.adicionarItem(produtosDisponiveis.get(produtoIndice), quantidade);
            System.out.println("Produto adicionado ao pedido.");
        }
        System.out.println("--------");
    }

    static void calcularEAtribuirFrete(Pedido pedido) {
        double valorFrete = 0;
        FreteCalculadora calculadoraFrete;

        while (true) {
            System.out.println("\n--- Cálculo de Frete ---");
            System.out.println("1. Por peso total (R$ 5,00/kg)");
            System.out.println("2. Fixo por distância (R$ 0,50/km)");

            System.out.print("Escolha a opção de frete: ");
            int opcaoFrete;
            try {
                opcaoFrete = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite 1 ou 2.");
                continue;
            }

            if (opcaoFrete == 1) {
                calculadoraFrete = new FreteCalculadoraPeso();
                valorFrete = calculadoraFrete.calcular(pedido);
                break;
            } else if (opcaoFrete == 2) {
                double distancia;
                while (true) {
                    System.out.print("Por favor, Informe a distância EM KM: ");
                    try {
                        distancia = Double.parseDouble(input.nextLine());
                        if (distancia < 0) {
                            System.out.println("Distância não pode ser negativa. Por favor, informe um valor válido.");
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Distância inválida. Por favor, digite um número.");
                    }
                }
                calculadoraFrete = new FreteCalculadoraDistancia(distancia);
                valorFrete = calculadoraFrete.calcular(pedido);
                break;
            } else {
                System.out.println("Opção de frete inválida. Por favor, digite 1 ou 2.");
            }
        }
        pedido.setFrete(valorFrete);
        System.out.println("----------");
    }

    static void simularNotificacao(Cliente cliente, Pedido pedido) {
        System.out.println("\n--- Notificação de Pedido ---");
        System.out.println("1. E-mail");
        System.out.println("2. SMS");
        System.out.println("3. WhatsApp");

        int tipoNotificacao;
        while (true) {
            System.out.print("Escolha o tipo de notificação desejado: ");
            try {
                tipoNotificacao = Integer.parseInt(input.nextLine());
                if (tipoNotificacao >= 1 && tipoNotificacao <= 3) {
                    break;
                } else {
                    System.out.println("Tipo de notificação inválida. Por favor, digite 1, 2 ou 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }

        String mensagem = "Seu pedido foi confirmado! Total com frete ficou: R$ " + String.format("%.2f", pedido.getTotalComFrete());
        Notificador notificador;

        switch (tipoNotificacao) {
            case 1:
                notificador = new NotificadorEmail();
                notificador.notificar(cliente, mensagem);
                break;
            case 2:
                notificador = new NotificadorSMS();
                notificador.notificar(cliente, mensagem);
                break;
            case 3:
                notificador = new NotificadorWhatsApp();
                notificador.notificar(cliente, mensagem);
                break;
            default:
                System.out.println("Erro interno na seleção de notificação.");
                break;
        }
        System.out.println("---------------------------");
    }

    static void executarListagemPedidos() {
        System.out.println("\n--- Lista de Pedidos ---");
        List<Pedido> todosPedidos = gerenciadorDados.getPedidos();
        if (todosPedidos.isEmpty()) {
            System.out.println("Nenhum pedido foi cadastrado.");
            return;
        }

        Cliente clienteSelecionado = selecionarCliente();
        if (clienteSelecionado == null) {
            System.out.println("Listagem de pedidos cancelada.");
            return;
        }

        List<Pedido> pedidosDoCliente = todosPedidos.stream()
                .filter(pedido -> pedido.getCliente().getCpf().equals(clienteSelecionado.getCpf()))
                .collect(Collectors.toList());

        if (pedidosDoCliente.isEmpty()) {
            System.out.println("Nenhum pedido encontrado para o cliente " + clienteSelecionado.getNome() + ".");
            return;
        }

        System.out.println("\nPedidos de " + clienteSelecionado.getNome() + ":");
        for (int i = 0; i < pedidosDoCliente.size(); i++) {
            Pedido pedido = pedidosDoCliente.get(i);
            System.out.println(i + ": Pedido (Total: R$ " + String.format("%.2f", pedido.getTotalComFrete()) + ")");
        }

        int pedidoIndice;
        while (true) {
            System.out.print("Selecione o número do pedido para gerar o relatório: ");
            try {
                pedidoIndice = Integer.parseInt(input.nextLine());
                if (pedidoIndice >= 0 && pedidoIndice < pedidosDoCliente.size()) {
                    break;
                } else {
                    System.out.println("Índice inválido. Por favor, digite o número correto.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }

        Pedido pedidoParaRelatorio = pedidosDoCliente.get(pedidoIndice);

        int formato;
        while (true) {
            System.out.println("Escolha o formato do relatório para este pedido:");
            System.out.println("1. Texto Simples");
            System.out.println("2. JSON");
            System.out.print("Escolha: ");
            try {
                formato = Integer.parseInt(input.nextLine());
                if (formato >= 1 && formato <= 2) {
                    break;
                } else {
                    System.out.println("Formato inválido. Por favor, digite 1 ou 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }

        RelatorioFactory factory;
        switch (formato) {
            case 1:
                factory = new RelatorioTextoFactory();
                break;
            case 2:
                factory = new RelatorioJSONFactory();
                break;
            default:
                System.out.println("Erro interno na seleção de formato de relatório.");
                return;
        }
        Relatorio relatorio = factory.criarRelatorio();
        relatorio.gerar(pedidoParaRelatorio);
        System.out.println("----------------");
    }
}