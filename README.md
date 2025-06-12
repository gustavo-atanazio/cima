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


A proposta deste projeto parte da necessidade de tornar maiseficiente o processo de controle de entrada e saída de insumosnos almoxarifados das unidades da Dasa. Atualmente, esse processoé propenso a erros operacionais, atrasos e baixa rastreabilidade,especialmente quando realizado de forma manual oudescentralizada. A compreensão do desafio nos levou a buscar umasolução que permitisse registrar de forma prática, segura eauditável as movimentações de insumos realizadas por funcionários.

A ideia central consiste na implantação de totens nosalmoxarifados (semelhantes aos presentes em restaurantes), queservirão como pontos de acesso para que os funcionáriosautorizados possam realizar entradas e baixas de insumosdiretamente no sistema. Ao interagir com o totem, se autentica,seleciona os insumos desejados, informa as quantidades e finalizaa operação. Com isso, espera-se reduzir falhas humanas, agilizaros processos e garantir maior controle sobre o estoque.

A solução foi pensada para operar em conjunto com os sistemascorporativos já existentes na organização, como o SAP(responsável pelos dados de insumos) e um possível banco internode funcionários e unidades. Isso permite que o foco do sistemaesteja exclusivamente no registro das movimentações, evitandoredundância de dados e facilitando a integração.

Com esse projeto, visamos aumentar a confiabilidade dos registrosde estoque, padronizar o processo de movimentação de insumos nasunidades e disponibilizar uma base de dados clara e precisa paraauditoria e análises futuras. O sistema foi concebido para sersimples de usar no dia a dia dos almoxarifados, mas robusto osuficiente para garantir consistência nas informações e aderênciaaos fluxos operacionais da Dasa.
