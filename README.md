# Projeto 1 - Sistema de Pedidos com Simulação de Notificações

Projeto de Análise de Projetos Orientada a Objetos, <a href="https://github.com/delanohelio/aps/blob/main/projeto/projeto1.md" target="_blank">Link da proposta</a>.

## Estrutura do projeto

```bash
projeto1-APOO/
├── src/
│   ├── model/                     # classes
│   │   ├── Cliente.java
│   │   ├── ItemPedido.java
│   │   ├── Pedido.java
│   │   └── Produto.java
│   ├── relatorio/                 # classes para gerar os relatorios
│   │   ├── Relatorio.java         # interface para gerar relatórios
│   │   ├── RelatorioFactory.java  # interface para fábrica de relatórios
│   │   ├── RelatorioJSONFactory.java # implementação para fábrica de relatórios JSON
│   │   ├── RelatorioTextoFactory.java # implementação para fábrica de relatórios de texto
│   │   ├── RelatorioJSON.java     # implementação de relatório JSON
│   │   └── RelatorioTexto.java    # implementação de relatório de texto
│   ├── servico/                   # classes de serviço e lógica de negócio
│   │   ├── FreteCalculadora.java  # interface para cálculo de frete
│   │   ├── FreteCalculadoraDistancia.java
│   │   ├── FreteCalculadoraPeso.java
│   │   ├── Notificador.java       # interface para notificadores
│   │   ├── NotificadorEmail.java
│   │   ├── NotificadorSMS.java
│   │   └── NotificadorWhatsApp.java
│   ├── util/                      # classes utilitárias que foram necessárias
│   │   └── GerenciadorDados.java
│   └── Main.java                 
└── README.md
```

## Funcionalidades

1.  **Cadastro de Clientes:** Permite cadastrar clientes com Nome, CPF, E-mail e Telefone.
2.  **Cadastro de Produtos:** Permite cadastrar produtos com Nome, Preço e Peso (em kg).
3.  **Criação de Pedido:**
    * Associa o pedido a um cliente existente.
    * Permite adicionar múltiplos produtos com suas respectivas quantidades.
    * Calcula o total do pedido, o frete e o total final com frete.
4.  **Cálculo de Frete:** Oferece duas opções para o cálculo:
    * **Por peso total:** R$ 5,00 por KG.
    * **Fixo por distância:** R$ 0,50 por KM (o usuário informa a distância depois).
5.  **Notificação de Pedido:** Simula o envio de confirmação do pedido por E-mail, SMS ou WhatsApp, exibindo a mensagem no terminal. Para enviar vai usar o número de telefone cadastrado pelo cliente para SMS/WhatsApp e e-mail cadastrado para enviar por E-mail.
6.  **Relatórios:** Permite gerar relatórios de pedidos em dois formatos:
    * **texto simples** 
    * **JSON (simulado):** Uma string formatada como JSON.

## Atualizações
   * O fluxo de listagem de pedidos foi melhorado, permitindo assim a seleção de um cliente e, em seguida, a visualização e seleção de pedidos específicos daquele cliente, mostrando o nome do primeiro produto na listagem.

## Padrões Aplicados

* **Factory Method:**
    * **Contexto:** Utilizado para criar diferentes tipos de relatórios (texto e JSON) de forma desacoplada do cliente que os solicita.
    * **Classes Envolvidas:**
        * `relatorio/RelatorioFactory.java` 
        * `relatorio/RelatorioTextoFactory.java` 
        * `relatorio/RelatorioJSONFactory.java`
        * `relatorio/Relatorio.java` 
        * `relatorio/RelatorioTexto.java` 
        * `relatorio/RelatorioJSON.java`

* **Strategy:**
    * **Contexto:** Empregado para permitir diferentes algoritmos de cálculo de frete a serem selecionados em tempo de execução.
    * **Classes Envolvidas:**
        * `servico/FreteCalculadora.java` 
        * `servico/FreteCalculadoraPeso.java` 
        * `servico/FreteCalculadoraDistancia.java` 
        * `model/Pedido.java` (vai utilizar a `FreteCalculadora`)

* **Observer (Simulado / Publicador-Assinante):**
    * **Contexto:** Embora não seja uma implementação clássica do Observer (sem uma lista de assinantes explícita para o `Pedido`), a lógica de notificação opera como um publicador-assinante, onde o sistema "observa" a criação de um pedido e "notifica" o cliente através de diferentes canais.
    * **Classes Envolvidas:**
        * `servico/Notificador.java` 
        * `servico/NotificadorEmail.java`
        * `servico/NotificadorSMS.java` 
        * `servico/NotificadorWhatsApp.java` 
        * `Main.java` 

* **Singleton:**
    * **Contexto:** Vai garantir que tenha apenas uma instância do `GerenciadorDados` em toda a aplicação, fornecendo um ponto de acesso global para o armazenamento e recuperação de dados de clientes, produtos e pedidos.
    * **Classes Envolvidas:**
        * `util/GerenciadorDados.java`
