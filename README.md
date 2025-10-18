# CIMA

## Pré-requisitos
- Docker
- Java 21

## Como Executar
Na raíz do projeto, execute o comando `docker compose up`. Isso fará com que o banco seja criado, as migrations executadas e a aplicação iniciada em seguida.

## Dados Fictícios
O projeto utiliza dados fictícios para simular o funcionamento do sistema. Para visualizar a simulação, você pode acessar os arquivos `V1__create_general_tables.sql` e `V2__insert_into_tables.sql`, que contém uma implementação simulada de um banco de dados.

## Sobre o Projeto

A proposta deste projeto parte da necessidade de tornar mais eficiente o processo de controle de entrada e saída de insumos nos almoxarifados das unidades da Dasa. Atualmente, esse processo é propenso a erros operacionais, atrasos e baixa rastreabilidade, especialmente quando realizado de forma manual ou descentralizada. A compreensão do desafio nos levou a buscar uma solução que permitisse registrar de forma prática, segura e auditável as movimentações de insumos realizadas por funcionários.
        
A ideia central consiste na implantação de totens nos almoxarifados (semelhantes aos presentes em restaurantes), que servirão como pontos de acesso para que os funcionários autorizados possam realizar entradas e baixas de insumos diretamente no sistema. Ao interagir com o totem, se autentica, seleciona os insumos desejados, informa as quantidades e finaliza a operação. Com isso, espera-se reduzir falhas humanas, agilizar os processos e garantir maior controle sobre o estoque.

Com esse projeto, visamos aumentar a confiabilidade dos registros de estoque, padronizar o processo de movimentação de insumos nas unidades e disponibilizar uma base de dados clara e precisa para auditoria e análises futuras. O sistema foi concebido para ser simples de usar no dia a dia dos almoxarifados, mas robusto o suficiente para garantir consistência nas informações e aderência aos fluxos operacionais da Dasa.