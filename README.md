## Como Executar
No package br.com.totem, execute a classe App

## Dados Fictícios
O projeto utiliza dados fictícios para simular o funcionamento do sistema. Para visualizar a simulação, você pode acessar a classe `br.com.totem.infrastructure.FakeDatabase` que contém uma implementação simulada de um banco de dados.

Para essa fase inicial, você pode utilizar o token de acesso como `valid-token` para simular o funcionário do sistema.

Os dados fictícios utilizados são:

- Insumos:
    - Seringa
    - Gaze
    - Luvas
    - Álcool Gel
- Funcionário:
    - ID: 1,
    Nome: Funcionário Teste,
    Token: valid-token
- Unidade:
    - ID: 1,
    Nome: Unidade Teste,
    Endereço: Endereço Teste
- Totem:
    - ID: 1,
    UnidadeId: 1

## Sobre o Projeto


A proposta deste projeto parte da necessidade de tornar mais eficiente o processo de controle de entrada e saída de insumos nos almoxarifados das unidades da Dasa. Atualmente, esse processo é propenso a erros operacionais, atrasos e baixa rastreabilidade, especialmente quando realizado de forma manual ou descentralizada. A compreensão do desafio nos levou a buscar uma solução que permitisse registrar de forma prática, segura e auditável as movimentações de insumos realizadas por funcionários.
        
A ideia central consiste na implantação de totens nos almoxarifados (semelhantes aos presentes em restaurantes), que servirão como pontos de acesso para que os funcionários autorizados possam realizar entradas e baixas de insumos diretamente no sistema. Ao interagir com o totem, se autentica, seleciona os insumos desejados, informa as quantidades e finaliza a operação. Com isso, espera-se reduzir falhas humanas, agilizar os processos e garantir maior controle sobre o estoque.

A solução foi pensada para operar em conjunto com os sistemas corporativos já existentes na organização, como o SAP (responsável pelos dados de insumos) e um possível banco interno de funcionários e unidades. Isso permite que o foco do sistema esteja exclusivamente no registro das movimentações, evitando redundância de dados e facilitando a integração.

Com esse projeto, visamos aumentar a confiabilidade dos registros de estoque, padronizar o processo de movimentação de insumos nas unidades e disponibilizar uma base de dados clara e precisa para auditoria e análises futuras. O sistema foi concebido para ser simples de usar no dia a dia dos almoxarifados, mas robusto o suficiente para garantir consistência nas informações e aderência aos fluxos operacionais da Dasa.
