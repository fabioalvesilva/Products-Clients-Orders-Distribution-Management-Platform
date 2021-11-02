# Products-Clients-Orders-Distribution-Management-Platform
The platform was developed for a theoretical pharmacy, to give them support for COVID-19 vaccines distribution.

This application allows collaborators to manage their orders, clients, and stocks of their products. Developed in Java, following the best practices and patterns of
software development. TDD approach was used during this development. In this project, I used data structures (e.g Graphs and Trees) to calculate the best route possible for the courier to deliver a product to a client with intermediate points. JDBC API was used to connect with an Oracle database to persist all the data. To deliver the highest quality, I used SonarQube and Jenkins to measure the quality of the code and the tests. All the technical / analysis documentation such as domain model, classes, sequences diagrams and relational model were made to ensure that some patterns of software development were correctly applied.

## Abstract ##

Este projeto foi desenvolvido para a disciplina LAPR3, do segundo ano do curso licenciatura em Engenharia Informática.

Para isso, foi desenvolvida uma aplicação de suporte a entrega de produtos farmacêuticos.

Com esta aplicação a empresa poderá gestão de farmácias, clientes, produtos, encomendas, scooters, drones, estafetas, entregas.

## Introdução ##

O objetivo deste projeto, foi o desenvolvimento de uma aplicação capaz de apoiar as farmácias e o respetivo suporte a todo o processo de entregas.

A aplicação de entrega de produtos farmacêuticos é um serviço que utiliza scooters e drones, que são partilhados por estafetas, para a entrega de produtos anteriormente encomendados pelos clientes.

Este sistema, é responsável por gerir a utilização e o carregamento das scooters e dos drones disponíveis, assim como os parques onde estão armazenados.
O principal objetivo deste negócio é proporcionar aos clientes um serviço de entrega de medicamentos ao domicílio, de modo a que todo o processo seja o mais simples, comodo e eficiente.

Durante três semanas foi desenvolvida a aplicação. Foi possível aplicar conceitos de análise, design e programação orientada a objetos, utilizando Java.

O projeto foi dividido em três Sprints. Utilizou-se o Jira para criação, planeamento e atribuição de tarefas.
Cada User Story foi criada e atribuída a um elemento da equipa, com foco em:

  - Análise, onde foram desenvolvidos o Diagrama de Caso de Uso e o Diagrama de Sequência do Sistema.
  - Design, onde foram desenvolvidos o Diagrama de Classes, o Diagrama de Sequência.
  - Implementação, onde o código e o código de teste foram implementados.
  - Revisão, onde foi possível rever toda a implementação.

Durante o projeto, o Scrum Master, organizava reuniões diárias com a equipa para saber o status de cada User Story, e no final de cada Sprint, criava uma retrospetiva do mesmo.

## Problem Statement ##

É necessário que uma empresa de entrega de produtos farmacêuticos, tenha um serviço/aplicação que faça a gestão dos mesmos, assim como a gestão dos veículos elétricos e dos respetivos parques.

Esta empresa possui várias farmácias, que contém vários parques de veículos elétricos, numa determinada cidade, que servem para estacionar e carregar os mesmos.
A empresa possui dois tipos de veículos diferentes: scooters elétricas e drones.

Um cliente para encomendar um produto, primeiro, terá de se registar na aplicação. Para criar conta, necessita de introduzir os dados de endereço, assim como o nome e o email.
A partir do momento em que o cliente está registado na aplicação, terá a opção para fazer a encomenda, adicionando os produtos que pretende no carrinho. Após a conclusão do pedido, será notificado ao longo dos vários estados do mesmo.
No processo de compra e pagamento o cliente poderá utilizar créditos adquiridos em compras anteriores.

Após o(s) pedido(s) estar(em) registado(s) no sistema, o administrador, seleciona uma ou várias encomendas, até ao limite de carga de uma mochila de um estafeta ou drone, são geradas simulações de custos/tempo de entrega da(s) encomenda(s) com diferentes modelos de veículos.
O administrador após escolher o método e a rota de entrega que pretende utilizar, é iniciada a mesma. Caso seja escolhida uma scooter, a tarefa é atribuída a um estafeta que esteja disponível no momento.

Concluída a entrega, o veículo terá de regressar à farmácia de origem e caso necessite, será estacionado num dos postos de carregamento disponíveis. Durante o processo de alocação e carregamento dos veículos, são geradas notificações com o respetivo estado.

## Solução ##

Neste projeto, para trabalhar na aplicação solicitada pelo cliente, a equipa utilizou o método ágil scrum.

A aplicação foi desenvolvida em Java ligada a uma base de dados Oracle. A base de dados serviu de suporte para todos os dados necessários para o negócio.

A plataforma Jira foi utilizada para gerir o desenvolvimento do projeto, ou seja, gerir todas as tarefas e informações sobre o estado de cada User Story.

## Engenharia de Requisitos ##

- [UseCaseDiagram](docs/UseCaseDiagram.jpg)

# User Stories #

**US01 - Eu como administrador deve ser capaz de adicionar scooters elétricos para assim conseguir gerir a minha frota de scooters.**

- [SSD](docs/US01-AdicionarScooter/US01-SSD.jpg)


**US02 - Eu como administrador deve ser capaz de atualizar scooters elétricos para assim conseguir gerir a minha frota de scooters.**

- [SSD](docs/US02-AtualizarScooter/US02-SSD.jpg)


**US03 - Eu como administrador deve ser capaz de remover scooters elétricos para assim conseguir gerir a minha frota de scooters.**

- [SSD](docs/US03-RemoverScooter/US03-SSD.jpg)


**US04 - Eu como administrador, pretendo registar farmácia na plataforma, para que ter as farmácias disponíveis no sistema.**

- [SSD](docs/US04-RegistarFarmacia/US04-SSD.jpg)


**US05 - Eu como administrador, pretendo atualizar a informação das farmácias, para que as mesmas se mantenham atualizadas.**

- [SSD](docs/US05-AtualizarFarmacia/US05-SSD.jpg)


**US06 - Eu como estafeta, devo conseguir entregar diversas encomendas de diferentes clientes numa só corrida, para otimizar os tempos de entrega.**

- [SSD](docs/US06-EntregarEncomendas/US06-SSD.jpg)


**US07 - Eu como estafeta, pretendo receber a rota mais eficiente com a quantidade de energia necessária, para que a entrega seja mais rápida e eficaz.**

- [SSD](docs/US07-ReceberRotaMaisEficiente/US07-SSD.jpg)


**US08 - Eu como estafeta pretendo um sistema que me indique a disponibilidade das scooters para saber quais estão disponíveis a utilizar.**

- [SSD](docs/US08-IndicarDisponibilidadeScooter/US08-SSD.jpg)


**US09 – Eu como estafeta, quero receber uma notificação no email quando acoplar a scooter para ter a certeza de que este está devidamente trancada.**

- [SSD](docs/US09-ReceberNotificacao/US09-SSD.jpg)


**US10 - Eu como estafeta, pretendo receber uma estimativa de tempo para o carregamento completo da scooter, para saber qual o tempo de espera.**

- [SSD](docs/US10-EstimarTempoCarregamento/US10-SSD.jpg)


**US11 - Eu como Utilizador não registado, pretendo registar-me na aplicação com o meu endereço e a minha localização geográfica para poder usufruir dos serviços da aplicação.**

- [SSD](docs/US11-RegistarUtilizador/US11-SSD.jpg)


**US12 – Eu como cliente, pretendo encomendar produtos da farmácia e receber créditos, para adquirir os mesmos e ter descontos nas próximas compras.**

- [SSD](docs/US12-EncomendarProdutos/US12-SSD.jpg)


**US13 – Eu como administrador, pretendo que o sistema notifique o cliente e remova o item que não tem stock suficiente, para que seja alertado que não pode encomendar o produto.**

- [SSD](docs/US13-RemoverItemSemStock/US13-SSD.jpg)


**US14 – Eu como administrador, pretendo que seja emitida uma fatura/recibo para cada pedido, para que os pedidos sejam faturados.**

- [SSD](docs/US14-EmissaoFaturaRecibo/US14-SSD.jpg)


**US15 - Eu como administrador, pretendo que o sistema faça a atualização dos stocks quando recebe produtos de outra farmácia, para que mantenha as quantidades atualizadas.**

- [SSD](docs/US15-AtualizacaoStocks/US15-SSD.jpg)


**US16 - Eu como administrador, pretendo que gerir os parques de scooters e drones das farmácias, para que se mantenham atualizados.**

- [SSD](docs/US16-GestaoParques/US16-SSD.jpg)


**US17 – Eu como cliente, quero ser notificado quando a execução de entrega é iniciada, para me manter atualizado em relação a encomenda.**

- [SSD](docs/US17-NotificacaoEntregaIniciada/US17-SSD.jpg)


**US18 – Eu como cliente, pretendo ter a opção de usar os créditos para pagar as compras online, para descontar o valor em créditos.**

- [SSD](docs/US18-UtilizacaoCreditos/US18-SSD.jpg)


**US19 - Eu como administrador devo ser capaz de adicionar um drone, para assim conseguir gerir a minha frota de drones.**

- [SSD](docs/US19-AdicionarDrone/US19-SSD.jpg)


**US20 - Eu como administrador devo ser capaz de atualizar um drone, para assim conseguir gerir a minha frota de drones.**

- [SSD](docs/US20-AtualizarDrone/US20-SSD.jpg)


**US21 - Eu como administrador devo ser capaz de remover um drone, para assim conseguir gerir a minha frota de drones.**

- [SSD](docs/US21-RemoverDrone/US21-SSD.jpg)


**US22 - Eu como administrador, pretendo que a farmácia verifique automaticamente se existe stock numa farmácia próxima, para que os stocks se mantenham atualizados.**

- [SSD](docs/US22-VerificarStock/US22-SSD.jpg)


**US23 - Eu como administrador, pretendo que a farmácia faça o pedido do produto em espera por falta de stock, para conseguir avançar no pedido de encomenda.**

- [SSD](docs/US23-PedidoProduto/US23-SSD.jpg)


**US24 - Eu como administrador, pretendo que a farmácias emitam documentos para transferência de produtos, para registar todas as transações.**

- [SSD](docs/US24-EmissaoDocumentos/US24-SSD.jpg)


**US25 - Eu como administrador, pretendo remover farmácia na plataforma, para que as mesmas se mantenham atualizadas.**

- [SSD](docs/US25-RemoverFarmacia/US25-SSD.jpg)


**US26 - Eu como administrador, pretendo simular os custos de entrega (terrestre vs aéreo) para escolher o mais rentável.**

- [SSD](docs/US26-SimularCustosEntrega/US26-SSD.jpg)


**US27 - Eu como administrador, pretendo saber, dadas determinadas encomendas, qual o tipo de entrega mais eficiente em termos energéticos (terrestre vs aéreo) para escolher o mais rentável.**

- [SSD](docs/US27-DeterminarEntregaMaisEficiente/US27-SSD.jpg)


**US28 - Eu como administrador, pretendo que o pedido seja reencaminhado para a farmácia mais próxima do endereço do cliente para que entrega seja mais rápida.**

- [SSD](docs/US28-ReencaminharPedidoFarmaciaProxima/US28-SSD.jpg)


**US29 - Eu como administrador, pretendo que a energia seja uniformemente partilhada por todos os postos de carregamento, para que a energia seja gerida corretamente.**

- [SSD](docs/US29-EnergiaPartilhadaPostos/US29-SSD.jpg)


**US30 - Eu como administrador, pretendo ser notificado quando há alterações nas estimativas de tempo de carregamento, para me manter informado.**

- [SSD](docs/US30-NotificacaoAlteracaoEstimativa/US30-SSD.jpg)


## Análise ##

- [ModeloDominio](docs/ModeloDominio.jpg)


# Design #

- [ModeloRelacional](docs/ModeloRelacional.jpg)
- [Diagramadeclasses](docs/Diagramadeclasses.jpg)

# User Stories #

**US01 - Eu como administrador deve ser capaz de adicionar scooters elétricos para assim conseguir gerir a minha frota de scooters.**

- [Class Diagram](docs/US01-AdicionarScooter/US01-CD.jpg)

- [Sequence Diagram](docs/US01-AdicionarScooter/US01-SD.jpg)


**US02 - Eu como administrador deve ser capaz de atualizar scooters elétricos para assim conseguir gerir a minha frota de scooters.**

- [Class Diagram](docs/US02-AtualizarScooter/US02-CD.jpg)

- [Sequence Diagram](docs/US02-AtualizarScooter/US02-SD.jpg)


**US03 - Eu como administrador deve ser capaz de remover scooters elétricos para assim conseguir gerir a minha frota de scooters.**

- [Class Diagram](docs/US03-RemoverScooter/US03-CD.jpg)

- [Sequence Diagram](docs/US03-RemoverScooter/US03-SD.jpg)


**US04 - Eu como administrador, pretendo registar farmácia na plataforma, para que ter as farmácias disponíveis no sistema.**

- [Class Diagram](docs/US04-RegistarFarmacia/US04-CD.jpg)

- [Sequence Diagram](docs/US04-RegistarFarmacia/US04-SD.jpg)


**US05 - Eu como administrador, pretendo atualizar a informação das farmácias, para que as mesmas se mantenham atualizadas.**

- [Class Diagram](docs/US05-AtualizarFarmacia/US05-CD.jpg)

- [Sequence Diagram](docs/US05-AtualizarFarmacia/US05-SD.jpg)


**US06 - Eu como estafeta, devo conseguir entregar diversas encomendas de diferentes clientes numa só corrida, para otimizar os tempos de entrega.**

- [Class Diagram](docs/US06-EntregarEncomendas/US06-CD.jpg)

- [Sequence Diagram](docs/US06-EntregarEncomendas/US06-SD.jpg)

**US07 - Eu como estafeta, pretendo receber a rota mais eficiente com a quantidade de energia necessária, para que a entrega seja mais rápida e eficaz.**

- [Class Diagram](docs/US07-ReceberRotaMaisEficiente/US07-CD.jpg)

- [Sequence Diagram](docs/US07-ReceberRotaMaisEficiente/US07-SD.jpg)


**US08 - Eu como estafeta pretendo um sistema que me indique a disponibilidade das scooters para saber quais estão disponíveis a utilizar.**

- [Class Diagram](docs/US08-IndicarDisponibilidadeScooter/US08-CD.jpg)

- [Sequence Diagram](docs/US08-IndicarDisponibilidadeScooter/US08-SD.jpg)


**US09 – Eu como estafeta, quero receber uma notificação no email quando acoplar a scooter para ter a certeza de que este está devidamente trancada.**

- [Class Diagram](docs/US09-ReceberNotificacao/US09-CD.jpg)

- [Sequence Diagram](docs/US09-ReceberNotificacao/US09-SD.jpg)


**US10 - Eu como estafeta, pretendo receber uma estimativa de tempo para o carregamento completo da scooter, para saber qual o tempo de espera.**

- [Class Diagram](docs/US10-EstimarTempoCarregamento/US10-CD.jpg)

- [Sequence Diagram](docs/US10-EstimarTempoCarregamento/US10-SD.jpg)


**US11 - Eu como Utilizador não registado, pretendo registar-me na aplicação com o meu endereço e a minha localização geográfica para poder usufruir dos serviços da aplicação.**

- [Class Diagram](docs/US11-RegistarUtilizador/US11-CD.jpg)

- [Sequence Diagram](docs/US11-RegistarUtilizador/US11-SD.jpg)


**US12 – Eu como cliente, pretendo encomendar produtos da farmácia e receber créditos, para adquirir os mesmos e ter descontos nas próximas compras.**

- [Class Diagram](docs/US12-EncomendarProdutos/US12-CD.jpg)

- [Sequence Diagram](docs/US12-EncomendarProdutos/US12-SD.jpg)


**US13 – Eu como administrador, pretendo que o sistema notifique o cliente e remova o item que não tem stock suficiente, para que seja alertado que não pode encomendar o produto.**

- [Class Diagram](docs/US13-RemoverItemSemStock/US13-CD.jpg)

- [Sequence Diagram](docs/US13-RemoverItemSemStock/US13-SD.jpg)


**US14 – Eu como administrador, pretendo que seja emitida uma fatura/recibo para cada pedido, para que os pedidos sejam faturados.**

- [Class Diagram](docs/US14-EmissaoFaturaRecibo/US14-CD.jpg)

- [Sequence Diagram](docs/US14-EmissaoFaturaRecibo/US14-SD.jpg)


**US15 - Eu como administrador, pretendo que o sistema faça a atualização dos stocks quando recebe produtos de outra farmácia, para que mantenha as quantidades atualizadas.**

- [Class Diagram](docs/US15-AtualizacaoStocks/US15-CD.jpg)

- [Sequence Diagram](docs/US15-AtualizacaoStocks/US15-SD.jpg)


**US16 - Eu como administrador, pretendo que gerir os parques de scooters e drones das farmácias, para que se mantenham atualizados.**

- [Class Diagram](docs/US16-GestaoParques/US16-CD.jpg)

- [Sequence Diagram](docs/US16-GestaoParques/US16-SD.jpg)


**US17 – Eu como cliente, quero ser notificado quando a execução de entrega é iniciada, para me manter atualizado em relação a encomenda.**

- [Class Diagram](docs/US17-NotificacaoEntregaIniciada/US17-CD.jpg)

- [Sequence Diagram](docs/US17-NotificacaoEntregaIniciada/US17-SD.jpg)


**US18 – Eu como cliente, pretendo ter a opção de usar os créditos para pagar as compras online, para descontar o valor em créditos.**

- [Class Diagram](docs/US18-UtilizacaoCreditos/US18-CD.jpg)

- [Sequence Diagram](docs/US18-UtilizacaoCreditos/US18-SD.jpg)


**US19 - Eu como administrador devo ser capaz de adicionar um drone, para assim conseguir gerir a minha frota de drones.**

- [Class Diagram](docs/US19-AdicionarDrone/US19-CD.jpg)

- [Sequence Diagram](docs/US19-AdicionarDrone/US19-SD.jpg)


**US20 - Eu como administrador devo ser capaz de atualizar um drone, para assim conseguir gerir a minha frota de drones.**

- [Class Diagram](docs/US20-AtualizarDrone/US20-CD.jpg)

- [Sequence Diagram](docs/US20-AtualizarDrone/US20-SD.jpg)


**US21 - Eu como administrador devo ser capaz de remover um drone, para assim conseguir gerir a minha frota de drones.**

- [Class Diagram](docs/US21-RemoverDrone/US21-CD.jpg)

- [Sequence Diagram](docs/US21-RemoverDrone/US21-SD.jpg)


**US22 - Eu como administrador, pretendo que a farmácia verifique automaticamente se existe stock numa farmácia próxima, para que os stocks se mantenham atualizados.**

- [Class Diagram](docs/US22-VerificarStock/US22-CD.jpg)

- [Sequence Diagram](docs/US22-VerificarStock/US22-SD.jpg)


**US23 - Eu como administrador, pretendo que a farmácia faça o pedido do produto em espera por falta de stock, para conseguir avançar no pedido de encomenda.**

- [Class Diagram](docs/US23-PedidoProduto/US23-CD.jpg)

- [Sequence Diagram](docs/US23-PedidoProduto/US23-SD.jpg)


**US24 - Eu como administrador, pretendo que a farmácias emitam documentos para transferência de produtos, para registar todas as transações.**

- [Class Diagram](docs/US24-EmissaoDocumentos/US24-CD.jpg)

- [Sequence Diagram](docs/US24-EmissaoDocumentos/US24-SD.jpg)


**US25 - Eu como administrador, pretendo remover farmácia na plataforma, para que as mesmas se mantenham atualizadas.**

- [Class Diagram](docs/US25-RemoverFarmacia/US25-CD.jpg)

- [Sequence Diagram](docs/US25-RemoverFarmacia/US25-SD.jpg)


**US26 - Eu como administrador, pretendo simular os custos de entrega (terrestre vs aéreo) para escolher o mais rentável.**

- [Class Diagram](docs/US26-SimularCustosEntrega/US26-CD.jpg)

- [Sequence Diagram](docs/US26-SimularCustosEntrega/US26-SD.jpg)


**US27 - Eu como administrador, pretendo saber, dadas determinadas encomendas, qual o tipo de entrega mais eficiente em termos energéticos (terrestre vs aéreo) para escolher o mais rentável.**

- [Class Diagram](docs/US27-DeterminarEntregaMaisEficiente/US27-CD.jpg)

- [Sequence Diagram](docs/US27-DeterminarEntregaMaisEficiente/US27-SD.jpg)


**US28 - Eu como administrador, pretendo que o pedido seja reencaminhado para a farmácia mais próxima do endereço do cliente para que entrega seja mais rápida.**

- [Class Diagram](docs/US28-ReencaminharPedidoFarmaciaProxima/US28-CD.jpg)

- [Sequence Diagram](docs/US28-ReencaminharPedidoFarmaciaProxima/US28-SD.jpg)


**US29 - Eu como administrador, pretendo que a energia seja uniformemente partilhada por todos os postos de carregamento, para que a energia seja gerida corretamente.**

- [Class Diagram](docs/US29-EnergiaPartilhadaPostos/US29-CD.jpg)

- [Sequence Diagram](docs/US29-EnergiaPartilhadaPostos/US29-SD.jpg)


**US30 - Eu como administrador, pretendo ser notificado quando há alterações nas estimativas de tempo de carregamento, para me manter informado.**

- [Class Diagram](docs/US30-NotificacaoAlteracaoEstimativa/US30-CD.jpg)

- [Sequence Diagram](docs/US30-NotificacaoAlteracaoEstimativa/US30-SD.jpg)


## Conclusão ##
O projeto consiste em aplicar os conceitos aprendidos durante o semestre, nomeadamente Arquitetura de Computadores, Bases de Dados, Estruturas de Informação e Física Aplicada, através do desenvolvimento de uma aplicação que permite a uma empresa farmacêutica fazer a gestão de farmácias, clientes, produtos, encomendas, scooters, drones, estafetas e respetivas entregas. Este projeto foi desenvolvido em três Sprints.

No primeiro sprint, foram desenvolvidas algumas funções como, adicionar, atualizar e remover scooters. Em relação aos utilizadores, foi adicionada a função de registar o cliente na plataforma. Outras funções desenvolvidas foram: execução de uma entrega com um ou vários pedidos, estimar a quantidade de energia para uma entrega de modo a sugerir uma rota mais económica. Todo o carregamento e alocação das scooters e respetivas notificações ao estafeta foram desenvolvidas neste sprint. Por fim, a gestão de stocks, as faturas/recibos e os créditos respetivos a cada compra.

No segundo semestre, foi adicionada a opção de a empresa ter várias farmácias e aumentar a frota no que diz a novos veículos, foram adicionados drones. Por consequência, foram desenvolvidas as opções adicionar, remover ou atualizar a lista de drones, assim como, a gestão de vários parques dos diferentes veículos, adicionando a opção de gerir os lugares de estacionamento e carregamento.

No terceiro e último sprint, de modo a entender qual é o tipo de entrega, terrestre ou aéreo, mais eficiente em termos de energia, foi implementada a funcionalidade de simular os custos de funcionamento de cada modo de entrega, terrestre ou aéreo.
Em relação aos pedidos, sofreram uma atualização, cada pedido deve ser encaminhado para a farmácia mais próxima da localização do cliente.
Por fim, uma alteração no processo de carregamento, ou seja, pelo facto de cada posto de carregamento dividir a energia pelos vários veículos a carregar no momento, foi implementada a função de recalcular a estimativa de tempo de carregamento e a respetiva notificação.

Apesar de dificuldades técnicas e falta de tempo, todos os recursos estimados e discutidos no início do projeto foram desenvolvidos.

## Referencias ##
https://rosettacode.org/wiki/Floyd-Warshall_algorithm#Java
https://x-engineer.org/automotive-engineering/vehicle/electric-vehicles/ev-design-energy-consumption/
https://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=6827242
