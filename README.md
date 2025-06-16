# Projeto 1 - Sistema de Pedidos com Simulação de Notificações

Projeto de Análise de Projetos Orientada a Objetos, <a href="https://github.com/delanohelio/aps/blob/main/projeto/projeto1.md" target="_blank">Link da proposta</a>.

## Funcionalidades

1.  **Cadastro de Clientes:** Permite registrar clientes com Nome, CPF e E-mail.
2.  **Cadastro de Produtos:** Permite registrar produtos com Nome, Preço e Peso (em kg).
3.  **Criação de Pedido:**
    * Associa o pedido a um cliente existente.
    * Permite adicionar múltiplos produtos com suas respectivas quantidades.
    * Calcula o total do pedido, o frete e o total final com frete.
4.  **Cálculo de Frete:** Oferece duas opções de cálculo:
    * **Por peso total:** R$ 5,00 por quilo.
    * **Fixo por distância:** R$ 0,50 por km (o usuário informa a distância).
5.  **Notificação de Pedido:** Simula o envio de confirmação do pedido por E-mail, SMS ou WhatsApp, exibindo a mensagem no terminal.
6.  **Relatórios:** Permite gerar relatórios de pedidos em dois formatos:
    * **Texto Simples:** Exibido diretamente no terminal.
    * **JSON (Simulado):** Uma string formatada como JSON.

## Dificuldades
- Fazer o JSON manualmente, não podendo usar a biblioteca.

## Saída no terminal testando as funcionalidades:
```bash
--- Menu Principal ---

1. Cadastrar Cliente

2. Cadastrar Produto

3. Criar Pedido

4. Listar Pedidos

5. Sair

Escolha uma opção: 1



--- Cadastro de Cliente ---

Nome: ari

CPF: 135

E-mail: dev

O cliente foi cadastrado com sucesso!

----------



--- Menu Principal ---

1. Cadastrar Cliente

2. Cadastrar Produto

3. Criar Pedido

4. Listar Pedidos

5. Sair

Escolha uma opção: 2



--- Cadastro de Produto ---

Nome: cacau

Preço: 5

Peso (kg): 1

O Produto foi cadastrado com sucesso!

------



--- Menu Principal ---

1. Cadastrar Cliente

2. Cadastrar Produto

3. Criar Pedido

4. Listar Pedidos

5. Sair

Escolha uma opção: 3



--- Criação de Pedido ---



Clientes disponíveis:

0: ari (135)

Selecione o número do cliente: 0



Produtos disponíveis:

0: cacau (R$ 5,00 / 1.0kg)

Selecione o número do produto (ou -1 para sair): 0

Quantidade: 2

Produto adicionado ao pedido.



Produtos disponíveis:

0: cacau (R$ 5,00 / 1.0kg)

Selecione o número do produto (ou -1 para sair): -1

--------



--- Cálculo de Frete ---

1. Por peso total (R$ 5,00/kg)

2. Fixo por distância (R$ 0,50/km)

Escolha a opção de frete: 1

----------



Pedido criado com sucesso!



--- Notificação de Pedido ---

1. E-mail

2. SMS

3. WhatsApp

Escolha o tipo de notificação desejado: 2

Enviando um SMS para 135 : Seu pedido foi confirmado! Total com frete ficou: R$ 20,00

---------------------------

--------------



--- Menu Principal ---

1. Cadastrar Cliente

2. Cadastrar Produto

3. Criar Pedido

4. Listar Pedidos

5. Sair

Escolha uma opção: 4



--- Lista de Pedidos ---



Pedido #1 - Nome Cliente: ari

Escolha o formato do relatório para este pedido:

1. Texto Simples

2. JSON

Escolha: 1

Cliente: ari

Produtos:

- cacau (2x) - R$ 10,00

Total: R$ 10,00

Frete: R$ 10,00

Total com frete: R$ 20,00

----------------



--- Menu Principal ---

1. Cadastrar Cliente

2. Cadastrar Produto

3. Criar Pedido

4. Listar Pedidos

5. Sair

Escolha uma opção: 4



--- Lista de Pedidos ---



Pedido #1 - Nome Cliente: ari

Escolha o formato do relatório para este pedido:

1. Texto Simples

2. JSON

Escolha: 2

{

  "cliente": "ari",

  "produtos": [

    {

      "nome": "cacau",

      "quantidade": 2,

      "preco": 10,00

    }

  ],

  "total": 10,00,

  "frete": 10,00,

  "total_com_frete": 20,00

}

----------------



--- Menu Principal ---

1. Cadastrar Cliente

2. Cadastrar Produto

3. Criar Pedido

4. Listar Pedidos

5. Sair

Escolha uma opção: 3



--- Criação de Pedido ---



Clientes disponíveis:

0: ari (135)

Selecione o número do cliente: 0



Produtos disponíveis:

0: cacau (R$ 5,00 / 1.0kg)

Selecione o número do produto (ou -1 para sair): 0

Quantidade: 5

Produto adicionado ao pedido.



Produtos disponíveis:

0: cacau (R$ 5,00 / 1.0kg)

Selecione o número do produto (ou -1 para sair): -1

--------



--- Cálculo de Frete ---

1. Por peso total (R$ 5,00/kg)

2. Fixo por distância (R$ 0,50/km)

Escolha a opção de frete: 2

Por favor, Informe a distância EM KM: 5

----------



Pedido criado com sucesso!



--- Notificação de Pedido ---

1. E-mail

2. SMS

3. WhatsApp

Escolha o tipo de notificação desejado: 1

Enviando em email para dev : Seu pedido foi confirmado! Total com frete ficou: R$ 27,50

---------------------------

--------------



--- Menu Principal ---

1. Cadastrar Cliente

2. Cadastrar Produto

3. Criar Pedido

4. Listar Pedidos

5. Sair

Escolha uma opção: 4



--- Lista de Pedidos ---



Pedido #1 - Nome Cliente: ari

Escolha o formato do relatório para este pedido:

1. Texto Simples

2. JSON

Escolha: 1

Cliente: ari

Produtos:

- cacau (2x) - R$ 10,00

Total: R$ 10,00

Frete: R$ 10,00

Total com frete: R$ 20,00



Pedido #2 - Nome Cliente: ari

Escolha o formato do relatório para este pedido:

1. Texto Simples

2. JSON

Escolha: 1

Cliente: ari

Produtos:

- cacau (5x) - R$ 25,00

Total: R$ 25,00

Frete: R$ 2,50

Total com frete: R$ 27,50

----------------



--- Menu Principal ---

1. Cadastrar Cliente

2. Cadastrar Produto

3. Criar Pedido

4. Listar Pedidos

5. Sair

Escolha uma opção: 4



--- Lista de Pedidos ---



Pedido #1 - Nome Cliente: ari

Escolha o formato do relatório para este pedido:

1. Texto Simples

2. JSON

Escolha: 2

{

  "cliente": "ari",

  "produtos": [

    {

      "nome": "cacau",

      "quantidade": 2,

      "preco": 10,00

    }

  ],

  "total": 10,00,

  "frete": 10,00,

  "total_com_frete": 20,00

}



Pedido #2 - Nome Cliente: ari

Escolha o formato do relatório para este pedido:

1. Texto Simples

2. JSON

Escolha: 2

{

  "cliente": "ari",

  "produtos": [

    {

      "nome": "cacau",

      "quantidade": 5,

      "preco": 25,00

    }

  ],

  "total": 25,00,

  "frete": 2,50,

  "total_com_frete": 27,50

}

----------------



--- Menu Principal ---

1. Cadastrar Cliente

2. Cadastrar Produto

3. Criar Pedido

4. Listar Pedidos

5. Sair

Escolha uma opção: 5

Saindo...



Process finished with exit code 0

```
