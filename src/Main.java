import model.*;
import servico.*;
import relatorio.RelatorioTexto;
import relatorio.RelatorioJSON;

import java.util.*;

public class Main {
    static List<Cliente> clientes = new ArrayList<>();
    static List<Produto> produtos = new ArrayList<>();
    static List<Pedido> pedidos = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

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
            opcao = Integer.parseInt(input.nextLine());

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

        clientes.add(new Cliente(nome, cpf, email));

        System.out.println("O cliente foi cadastrado com sucesso!");
        System.out.println("----------");
    }

    static void executarCadastroProduto() {
        System.out.println("\n--- Cadastro de Produto ---");

        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("Preço: ");
        double preco = Double.parseDouble(input.nextLine());

        System.out.print("Peso (kg): ");
        double peso = Double.parseDouble(input.nextLine());

        produtos.add(new Produto(nome, preco, peso));

        System.out.println("O Produto foi cadastrado com sucesso!");
        System.out.println("------");
    }

    static void executarCriacaoPedido() {
        System.out.println("\n--- Criação de Pedido ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado. Cadastre produtos primeiro.");
            return;
        }

        Cliente clienteSelecionado = selecionarCliente();

        Pedido novoPedido = new Pedido(clienteSelecionado);
        adicionarItensAoPedido(novoPedido);

        calcularEAtribuirFrete(novoPedido);

        System.out.println("\nPedido criado com sucesso!");
        pedidos.add(novoPedido);

        simularNotificacao(clienteSelecionado, novoPedido);
        System.out.println("--------------");
    }

    static Cliente selecionarCliente() {
        System.out.println("\nClientes disponíveis:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + ": " + clientes.get(i).getNome() + " (" + clientes.get(i).getCpf() + ")");
        }

        System.out.print("Selecione o número do cliente: ");
        int indice = Integer.parseInt(input.nextLine());
        return clientes.get(indice);
    }

    static void adicionarItensAoPedido(Pedido pedido) {
        while (true) {
            System.out.println("\nProdutos disponíveis:");
            for (int i = 0; i < produtos.size(); i++) {
                System.out.println(i + ": " + produtos.get(i).getNome() + " (R$ " + String.format("%.2f", produtos.get(i).getPreco()) + " / " + produtos.get(i).getPeso() + "kg)");
            }
            System.out.print("Selecione o número do produto (ou -1 para sair): ");

            int produtoIndice = Integer.parseInt(input.nextLine());

            if (produtoIndice == -1) {
                break;
            }

            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(input.nextLine());

            pedido.adicionarItem(produtos.get(produtoIndice), quantidade);
            System.out.println("Produto adicionado ao pedido.");
        }
        System.out.println("--------");
    }

    static void calcularEAtribuirFrete(Pedido pedido) {
        System.out.println("\n--- Cálculo de Frete ---");
        System.out.println("1. Por peso total (R$ 5,00/kg)");
        System.out.println("2. Fixo por distância (R$ 0,50/km)");

        System.out.print("Escolha a opção de frete: ");
        int opcaoFrete = Integer.parseInt(input.nextLine());

        double valorFrete = 0;
        switch (opcaoFrete) {
            case 1:
                FreteCalculadoraPeso calcPeso = new FreteCalculadoraPeso();
                valorFrete = calcPeso.calcular(pedido);
                break;
            case 2:
                System.out.print("Por favor, Informe a distância EM KM: ");
                double distancia = Double.parseDouble(input.nextLine());
                FreteCalculadoraDistancia calcDistancia = new FreteCalculadoraDistancia();
                valorFrete = calcDistancia.calcular(distancia);
                break;
            default:
                System.out.println("Opção de frete inválida. Frete será R$ 0,00, Valeu!");
                break;
        }
        pedido.setFrete(valorFrete);
        System.out.println("----------");
    }

    static void simularNotificacao(Cliente cliente, Pedido pedido) {
        System.out.println("\n--- Notificação de Pedido ---");
        System.out.println("1. E-mail");
        System.out.println("2. SMS");
        System.out.println("3. WhatsApp");

        System.out.print("Escolha o tipo de notificação desejado: ");
        int tipoNotificacao = Integer.parseInt(input.nextLine());

        String mensagem = "Seu pedido foi confirmado! Total com frete ficou: R$ " + String.format("%.2f", pedido.getTotalComFrete());

        switch (tipoNotificacao) {
            case 1:
                NotificadorEmail notificadorEmail = new NotificadorEmail();
                notificadorEmail.notificar(cliente, mensagem);
                break;
            case 2:
                NotificadorSMS notificadorSMS = new NotificadorSMS();
                notificadorSMS.notificar(cliente, mensagem);
                break;
            case 3:
                NotificadorWhatsApp notificadorWhatsApp = new NotificadorWhatsApp();
                notificadorWhatsApp.notificar(cliente, mensagem);
                break;
            default:
                System.out.println("Tipo de notificação inválida. Nenhuma notificação enviada.");
                break;
        }
        System.out.println("---------------------------");
    }

    static void executarListagemPedidos() {
        System.out.println("\n--- Lista de Pedidos ---");
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido foi cadastrado.");
            return;
        }

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            System.out.println("\nPedido #" + (i + 1) + " - Nome Cliente: " + pedido.getCliente().getNome());

            System.out.println("Escolha o formato do relatório para este pedido:");
            System.out.println("1. Texto Simples");
            System.out.println("2. JSON");
            System.out.print("Escolha: ");
            int formato = Integer.parseInt(input.nextLine());

            switch (formato) {
                case 1:
                    RelatorioTexto relatorioTexto = new RelatorioTexto();
                    relatorioTexto.gerar(pedido);
                    break;
                case 2:
                    RelatorioJSON relatorioJSON = new RelatorioJSON();
                    relatorioJSON.gerar(pedido);
                    break;
                default:
                    System.out.println("Formato inválido.");
                    break;
            }
        }
        System.out.println("----------------");
    }
}