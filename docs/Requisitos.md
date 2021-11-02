# Requisitos

## De Negócio

- Tarifa de Entrega Fixa, mas preparada para ser mutavel.
- O pagamento NAO é feito ao estafeta.
- Os créditos do cliente são proporcionais ao valor da encomenda.
- O critério da farmácia para escolher um drone ou um estafeta para realizar a entrega, poderá ser tempo mínimo de entrega, gasto mínimo energético, ou uma combinação de ambos.
- Podem haver vários parques para uma farmácia. Contudo, neste momento, apenas há um para scooters e no futuro outro para drones.
- O "parque de estacionamento" deve emitir uma notificação quando a scooter esteja carregada. Contudo, ainda não é certo quem recebe a notificação.
- Neste momento é uma tarifa fixa para atrair os clientes. Contudo, poderá mudar no futuro.
- Neste momento a carga máxima da mochila será a mesma para todos os estafetas.

## Administrador

- O Administrador regista farmácias.

## Utilizador Não Registado

- Utilizador nao registado pode ver produtos.

## Cliente

- Pode haver clientes com a mesma morada.
- Um cliente possui apenas uma só morada.
- Um cliente não pode fazer uma encomenda que exceda a capacidade de entrega.

## Estafeta

- A entrega é atribuída a um estafeta que esteja disponível. Cada estafeta deve optimizar o percurso.
- Carga maxima mochila é igual para todos estafetas.
- Estafeta pode ter scooter preferida, caso esta esteja sem carga outra deve ser -lhe atribuida.
- Estafeta é funcionario de farmacia e não recusa pedidos.
- As entregas são criadas pelo estafeta com base nas encomendas pendente.
- A entrega é atribuída a um estafeta que esteja disponível. Cada estafeta deve optimizar o percurso.


## Nice to have

- Um cliente deve conseguir visualizar o estado da sua encomenda? Por exemplo: Em processamento, enviada, etc.
