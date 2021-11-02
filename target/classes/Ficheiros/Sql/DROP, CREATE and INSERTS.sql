-- DROP TABLES
DROP TABLE administrador CASCADE CONSTRAINTS;
DROP TABLE artigo CASCADE CONSTRAINTS;
DROP TABLE cliente CASCADE CONSTRAINTS;
DROP TABLE conta CASCADE CONSTRAINTS;
DROP TABLE drone CASCADE CONSTRAINTS;
DROP TABLE encomenda CASCADE CONSTRAINTS;
DROP TABLE encomendaInterna CASCADE CONSTRAINTS;
DROP TABLE entrega CASCADE CONSTRAINTS;
DROP TABLE estado_encomenda CASCADE CONSTRAINTS;
DROP TABLE estado_veiculo CASCADE CONSTRAINTS;
DROP TABLE estafeta CASCADE CONSTRAINTS;
DROP TABLE farmacia CASCADE CONSTRAINTS;
DROP TABLE farmacia_parque CASCADE CONSTRAINTS;
DROP TABLE fatura CASCADE CONSTRAINTS;
DROP TABLE funcionario CASCADE CONSTRAINTS;
DROP TABLE guiaTransporte CASCADE CONSTRAINTS;
DROP TABLE localidade CASCADE CONSTRAINTS;
DROP TABLE lugar_estacionamento CASCADE CONSTRAINTS;
DROP TABLE modelo_drone CASCADE CONSTRAINTS;
DROP TABLE modelo_scooter CASCADE CONSTRAINTS;
DROP TABLE modo_pagamento CASCADE CONSTRAINTS;
DROP TABLE notaEncomenda CASCADE CONSTRAINTS;
DROP TABLE pagamento CASCADE CONSTRAINTS;
DROP TABLE parque CASCADE CONSTRAINTS;
DROP TABLE r_artigo_farmacia CASCADE CONSTRAINTS;
DROP TABLE r_encomenda_artigo CASCADE CONSTRAINTS;
DROP TABLE r_encomenda_conta CASCADE CONSTRAINTS;
DROP TABLE r_encomendaInterna_artigo CASCADE CONSTRAINTS;
DROP TABLE r_estafeta_veiculo_entrega_encomenda CASCADE CONSTRAINTS;
DROP TABLE r_farmacia_encomendaInterna CASCADE CONSTRAINTS;
DROP TABLE r_funcionario_farmacia CASCADE CONSTRAINTS;
DROP TABLE r_veiculo_lugarEstacionamento CASCADE CONSTRAINTS;
DROP TABLE scooter CASCADE CONSTRAINTS;
DROP TABLE spotCarregamento CASCADE CONSTRAINTS;
DROP TABLE tipo_bateria CASCADE CONSTRAINTS;
DROP TABLE tipo_disponibilidade CASCADE CONSTRAINTS;
DROP TABLE tipo_Estacionamento CASCADE CONSTRAINTS;
DROP TABLE tipo_funcionario CASCADE CONSTRAINTS;
DROP TABLE tipo_mochila CASCADE CONSTRAINTS;
DROP TABLE tipo_parque CASCADE CONSTRAINTS;
DROP TABLE tipo_veiculo CASCADE CONSTRAINTS;
DROP TABLE veiculo CASCADE CONSTRAINTS;


--CREATE TABLE

CREATE TABLE administrador (id_administrador number(10) GENERATED AS IDENTITY, nome varchar2(255) NOT NULL, password varchar2(255) NOT NULL, email varchar2(255) NOT NULL, PRIMARY KEY (id_administrador));
CREATE TABLE artigo (id_artigo number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, precoUnitario number(19, 2) NOT NULL, iva number(10) NOT NULL, peso number(19, 2) NOT NULL, PRIMARY KEY (id_artigo));
CREATE TABLE cliente (id_cliente number(10) GENERATED AS IDENTITY, nome varchar2(255) NOT NULL, NIF number(10) NOT NULL UNIQUE, id_endreco number(10) NOT NULL, email varchar2(255) NOT NULL, cartaoCredito number(10) NOT NULL, PRIMARY KEY (id_cliente));
CREATE TABLE conta (id_cliente number(10) NOT NULL, password varchar2(255) NOT NULL, credito number(19, 2) DEFAULT 0 NOT NULL, id_farmaciaMaisProxima number(10) NOT NULL, PRIMARY KEY (id_cliente));
CREATE TABLE drone (id_drone number(10) NOT NULL, numRegisto number(10) NOT NULL UNIQUE, id_modelo number(10) NOT NULL, id_tipoBateria number(10) NOT NULL, id_estado number(10) NOT NULL, capacidade_atual number(10) NOT NULL, PRIMARY KEY (id_drone));
CREATE TABLE encomenda (id_encomenda number(10) GENERATED AS IDENTITY, data_encomenda date NOT NULL, id_estadoEncomenda number(10) NOT NULL, PRIMARY KEY (id_encomenda));
CREATE TABLE encomendaInterna (id_encomendaInterna number(10) GENERATED AS IDENTITY, data timestamp(0) NOT NULL, id_farmaciaForncecedor number(10) NOT NULL, PRIMARY KEY (id_encomendaInterna));
CREATE TABLE entrega (id_entrega number(10) GENERATED AS IDENTITY, dataInicio timestamp(0) NOT NULL, dataFim timestamp(0), PRIMARY KEY (id_entrega));
CREATE TABLE estado_encomenda (id_estadoEncomenda number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, PRIMARY KEY (id_estadoEncomenda));
CREATE TABLE estado_veiculo (id_estadoVeiculo number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, PRIMARY KEY (id_estadoVeiculo));
CREATE TABLE estafeta (id_funcionario number(10) NOT NULL, id_tipoMochila number(10) NOT NULL, id_tipoDisponibilidade number(10) NOT NULL, id_scooterPreferencial number(10), password varchar2(255) NOT NULL, PRIMARY KEY (id_funcionario));
CREATE TABLE farmacia (id_farmacia number(10) GENERATED AS IDENTITY, NIPC number(10) NOT NULL UNIQUE, id_endereco number(10) NOT NULL, designacao varchar2(255) NOT NULL, PRIMARY KEY (id_farmacia));
CREATE TABLE farmacia_parque (id_farmacia number(10) NOT NULL, id_parque number(10) NOT NULL, PRIMARY KEY (id_farmacia, id_parque));
CREATE TABLE fatura (id_fatura number(10) GENERATED AS IDENTITY, numero number(10) NOT NULL UNIQUE, data date NOT NULL, id_encomenda number(10) NOT NULL, valorFaturado number(19, 2) NOT NULL, valorTransporte number(19, 2) NOT NULL, valorDescontado number(19, 2) NOT NULL, PRIMARY KEY (id_fatura));
CREATE TABLE funcionario (id_funcionario number(10) GENERATED AS IDENTITY, nome varchar2(255) NOT NULL, NIF number(10) NOT NULL, NISS number(10) NOT NULL, email varchar2(255) NOT NULL, id_tipoFuncionario number(10) NOT NULL, PRIMARY KEY (id_funcionario));
CREATE TABLE guiaTransporte (id_guiaTransporte number(10) GENERATED AS IDENTITY, id_encomendaInterna number(10) NOT NULL, PRIMARY KEY (id_guiaTransporte));
CREATE TABLE localidade (id_localidade number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, latitude number(19, 7) NOT NULL, longitude number(19, 7) NOT NULL, altitude number(10, 2) NOT NULL, PRIMARY KEY (id_localidade));
CREATE TABLE lugar_estacionamento (id_lugarEstacionamento number(10) GENERATED AS IDENTITY, id_tipoEstacionamento number(10) NOT NULL, id_parque number(10) NOT NULL, PRIMARY KEY (id_lugarEstacionamento));
CREATE TABLE modelo_drone (id_modeloDrone number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, peso number(19, 2) NOT NULL, cargaMaxima number(10) NOT NULL, areaFrontal number(10, 2) NOT NULL, PRIMARY KEY (id_modeloDrone));
CREATE TABLE modelo_scooter (id_modeloScooter number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, peso number(19, 2) NOT NULL, areaFrontal number(19, 2) NOT NULL, capacidadeMotor number(19, 2) NOT NULL, PRIMARY KEY (id_modeloScooter));
CREATE TABLE modo_pagamento (id_modoPagamento number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, PRIMARY KEY (id_modoPagamento));
CREATE TABLE notaEncomenda (id_notaEncomenda number(10) GENERATED AS IDENTITY, id_encomendaInterna number(10) NOT NULL, PRIMARY KEY (id_notaEncomenda));
CREATE TABLE pagamento (id_fatura number(10) NOT NULL, id_modoPagamento number(10) NOT NULL, valorTotalFaturado number(19, 2), PRIMARY KEY (id_fatura, id_modoPagamento));
CREATE TABLE parque (id_parque number(10) GENERATED AS IDENTITY, id_tipoParque number(10) NOT NULL, numLugaresEstacionamentoNormal number(10) DEFAULT 0 NOT NULL, NumPostoCarregamento number(10) DEFAULT 0 NOT NULL, id_endereco number(10) NOT NULL, capacidadeTensao number(10) NOT NULL, PRIMARY KEY (id_parque));
CREATE TABLE r_artigo_farmacia (id_artigo number(10) NOT NULL, id_farmacia number(10) NOT NULL, stock number(10) NOT NULL, PRIMARY KEY (id_artigo, id_farmacia));
CREATE TABLE r_encomenda_artigo (id_encomenda number(10) NOT NULL, linha number(10) NOT NULL, id_artigo number(10) NOT NULL, quantidade number(10) NOT NULL, PRIMARY KEY (id_encomenda, linha, id_artigo));
CREATE TABLE r_encomenda_conta (id_encomenda number(10) NOT NULL, id_cliente number(10) NOT NULL, PRIMARY KEY (id_encomenda, id_cliente));
CREATE TABLE r_encomendaInterna_artigo (id_encomendaInterna number(10) NOT NULL, id_artigo number(10) NOT NULL, linha number(10) NOT NULL, quantidade number(10) NOT NULL, PRIMARY KEY (id_encomendaInterna, id_artigo, linha));
CREATE TABLE r_estafeta_veiculo_entrega_encomenda (id_veiculo number(10) NOT NULL, id_entrega number(10) NOT NULL, id_estafeta number(10), id_encomenda number(10) NOT NULL, PRIMARY KEY (id_veiculo, id_entrega, id_encomenda));
CREATE TABLE r_farmacia_encomendaInterna (id_farmacia number(10) NOT NULL, id_encomendaInterna number(10) NOT NULL, PRIMARY KEY (id_farmacia, id_encomendaInterna));
CREATE TABLE r_funcionario_farmacia (id_funcionario number(10) NOT NULL, id_farmacia number(10) NOT NULL, PRIMARY KEY (id_funcionario, id_farmacia));
CREATE TABLE r_veiculo_lugarEstacionamento (id_veiculo number(10) NOT NULL, id_lugarEstacionamento number(10) NOT NULL, data_inicio timestamp(0) NOT NULL, data_fim timestamp(0), PRIMARY KEY (id_veiculo, id_lugarEstacionamento, data_inicio));
CREATE TABLE scooter (id_scooter number(10) NOT NULL, matricula varchar2(255) NOT NULL UNIQUE, id_modelo number(10) NOT NULL, id_tipoBateria number(10) NOT NULL, id_estado number(10) NOT NULL, capacidade_atual number(19, 2) NOT NULL, PRIMARY KEY (id_scooter));
CREATE TABLE spotCarregamento (id_lugarEstacionamento number(10) NOT NULL, output_energetico number(10) DEFAULT 0 NOT NULL, PRIMARY KEY (id_lugarEstacionamento));
CREATE TABLE tipo_bateria (id_tipoBateria number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, capacidade number(10) NOT NULL, eficiencia number(10) NOT NULL, PRIMARY KEY (id_tipoBateria));
CREATE TABLE tipo_disponibilidade (id_tipoDisponibilidade number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, PRIMARY KEY (id_tipoDisponibilidade));
CREATE TABLE tipo_Estacionamento (id_tipoEstacionamento number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, PRIMARY KEY (id_tipoEstacionamento));
CREATE TABLE tipo_funcionario (id_tipoFuncionario number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, PRIMARY KEY (id_tipoFuncionario));
CREATE TABLE tipo_mochila (id_tipoMochila number(10) GENERATED AS IDENTITY, cargaMaxima number(19, 2) NOT NULL, designacao varchar2(255) NOT NULL, PRIMARY KEY (id_tipoMochila));
CREATE TABLE tipo_parque (id_tipoParque number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, PRIMARY KEY (id_tipoParque));
CREATE TABLE tipo_veiculo (id_tipoVeiculo number(10) GENERATED AS IDENTITY, designacao varchar2(255) NOT NULL, PRIMARY KEY (id_tipoVeiculo));
CREATE TABLE veiculo (id_veiculo number(10) GENERATED AS IDENTITY, id_tipoVeiculo number(10) NOT NULL, id_farmacia number(10) NOT NULL, PRIMARY KEY (id_veiculo));
ALTER TABLE drone ADD CONSTRAINT FKdrone773708 FOREIGN KEY (id_tipoBateria) REFERENCES tipo_bateria (id_tipoBateria);
ALTER TABLE r_funcionario_farmacia ADD CONSTRAINT FKr_funciona676876 FOREIGN KEY (id_farmacia) REFERENCES farmacia (id_farmacia);
ALTER TABLE r_funcionario_farmacia ADD CONSTRAINT FKr_funciona68731 FOREIGN KEY (id_funcionario) REFERENCES funcionario (id_funcionario);
ALTER TABLE guiaTransporte ADD CONSTRAINT FKguiaTransp126485 FOREIGN KEY (id_encomendaInterna) REFERENCES encomendaInterna (id_encomendaInterna);
ALTER TABLE notaEncomenda ADD CONSTRAINT FKnotaEncome937065 FOREIGN KEY (id_encomendaInterna) REFERENCES encomendaInterna (id_encomendaInterna);
ALTER TABLE r_encomendaInterna_artigo ADD CONSTRAINT FKr_encomend860796 FOREIGN KEY (id_encomendaInterna) REFERENCES encomendaInterna (id_encomendaInterna);
ALTER TABLE r_encomendaInterna_artigo ADD CONSTRAINT FKr_encomend510677 FOREIGN KEY (id_artigo) REFERENCES artigo (id_artigo);
ALTER TABLE r_farmacia_encomendaInterna ADD CONSTRAINT FKr_farmacia383112 FOREIGN KEY (id_encomendaInterna) REFERENCES encomendaInterna (id_encomendaInterna);
ALTER TABLE r_farmacia_encomendaInterna ADD CONSTRAINT FKr_farmacia909341 FOREIGN KEY (id_farmacia) REFERENCES farmacia (id_farmacia);
ALTER TABLE lugar_estacionamento ADD CONSTRAINT FKlugar_esta710242 FOREIGN KEY (id_parque) REFERENCES parque (id_parque);
ALTER TABLE spotCarregamento ADD CONSTRAINT FKspotCarreg916377 FOREIGN KEY (id_lugarEstacionamento) REFERENCES lugar_estacionamento (id_lugarEstacionamento);
ALTER TABLE veiculo ADD CONSTRAINT FKveiculo17877 FOREIGN KEY (id_farmacia) REFERENCES farmacia (id_farmacia);
ALTER TABLE farmacia_parque ADD CONSTRAINT FKfarmacia_p166214 FOREIGN KEY (id_farmacia) REFERENCES farmacia (id_farmacia);
ALTER TABLE farmacia_parque ADD CONSTRAINT FKfarmacia_p848617 FOREIGN KEY (id_parque) REFERENCES parque (id_parque);
ALTER TABLE r_encomenda_conta ADD CONSTRAINT FKr_encomend72424 FOREIGN KEY (id_cliente) REFERENCES conta (id_cliente);
ALTER TABLE drone ADD CONSTRAINT FKdrone119041 FOREIGN KEY (id_modelo) REFERENCES modelo_drone (id_modeloDrone);
ALTER TABLE drone ADD CONSTRAINT FKdrone537844 FOREIGN KEY (id_estado) REFERENCES estado_veiculo (id_estadoVeiculo);
ALTER TABLE r_estafeta_veiculo_entrega_encomenda ADD CONSTRAINT FKr_estafeta577865 FOREIGN KEY (id_veiculo) REFERENCES veiculo (id_veiculo);
ALTER TABLE scooter ADD CONSTRAINT FKscooter228628 FOREIGN KEY (id_scooter) REFERENCES veiculo (id_veiculo);
ALTER TABLE drone ADD CONSTRAINT FKdrone950293 FOREIGN KEY (id_drone) REFERENCES veiculo (id_veiculo);
ALTER TABLE r_veiculo_lugarEstacionamento ADD CONSTRAINT FKr_veiculo_441205 FOREIGN KEY (id_veiculo) REFERENCES veiculo (id_veiculo);
ALTER TABLE veiculo ADD CONSTRAINT FKveiculo705797 FOREIGN KEY (id_tipoVeiculo) REFERENCES tipo_veiculo (id_tipoVeiculo);
ALTER TABLE conta ADD CONSTRAINT FKconta624793 FOREIGN KEY (id_farmaciaMaisProxima) REFERENCES farmacia (id_farmacia);
ALTER TABLE parque ADD CONSTRAINT FKparque223875 FOREIGN KEY (id_tipoParque) REFERENCES tipo_parque (id_tipoParque);
ALTER TABLE funcionario ADD CONSTRAINT FKfuncionari624859 FOREIGN KEY (id_tipoFuncionario) REFERENCES tipo_funcionario (id_tipoFuncionario);
ALTER TABLE estafeta ADD CONSTRAINT FKestafeta477595 FOREIGN KEY (id_funcionario) REFERENCES funcionario (id_funcionario);
ALTER TABLE r_veiculo_lugarEstacionamento ADD CONSTRAINT FKr_veiculo_907614 FOREIGN KEY (id_lugarEstacionamento) REFERENCES lugar_estacionamento (id_lugarEstacionamento);
ALTER TABLE lugar_estacionamento ADD CONSTRAINT FKlugar_esta963847 FOREIGN KEY (id_tipoEstacionamento) REFERENCES tipo_Estacionamento (id_tipoEstacionamento);
ALTER TABLE r_artigo_farmacia ADD CONSTRAINT FKr_artigo_f671876 FOREIGN KEY (id_farmacia) REFERENCES farmacia (id_farmacia);
ALTER TABLE r_artigo_farmacia ADD CONSTRAINT FKr_artigo_f402010 FOREIGN KEY (id_artigo) REFERENCES artigo (id_artigo);
ALTER TABLE encomenda ADD CONSTRAINT FKencomenda366107 FOREIGN KEY (id_estadoEncomenda) REFERENCES estado_encomenda (id_estadoEncomenda);
ALTER TABLE pagamento ADD CONSTRAINT FKpagamento108141 FOREIGN KEY (id_modoPagamento) REFERENCES modo_pagamento (id_modoPagamento);
ALTER TABLE pagamento ADD CONSTRAINT FKpagamento272182 FOREIGN KEY (id_fatura) REFERENCES fatura (id_fatura);
ALTER TABLE estafeta ADD CONSTRAINT FKestafeta819080 FOREIGN KEY (id_tipoDisponibilidade) REFERENCES tipo_disponibilidade (id_tipoDisponibilidade);
ALTER TABLE r_estafeta_veiculo_entrega_encomenda ADD CONSTRAINT FKr_estafeta382457 FOREIGN KEY (id_encomenda) REFERENCES encomenda (id_encomenda);
ALTER TABLE r_estafeta_veiculo_entrega_encomenda ADD CONSTRAINT FKr_estafeta836914 FOREIGN KEY (id_entrega) REFERENCES entrega (id_entrega);
ALTER TABLE r_estafeta_veiculo_entrega_encomenda ADD CONSTRAINT FKr_estafeta96586 FOREIGN KEY (id_estafeta) REFERENCES estafeta (id_funcionario);
ALTER TABLE r_encomenda_artigo ADD CONSTRAINT FKr_encomend727726 FOREIGN KEY (id_artigo) REFERENCES artigo (id_artigo);
ALTER TABLE r_encomenda_artigo ADD CONSTRAINT FKr_encomend842900 FOREIGN KEY (id_encomenda) REFERENCES encomenda (id_encomenda);
ALTER TABLE r_encomenda_conta ADD CONSTRAINT FKr_encomend505505 FOREIGN KEY (id_encomenda) REFERENCES encomenda (id_encomenda);
ALTER TABLE conta ADD CONSTRAINT FKconta681167 FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente);
ALTER TABLE cliente ADD CONSTRAINT FKcliente656967 FOREIGN KEY (id_endreco) REFERENCES localidade (id_localidade);
ALTER TABLE fatura ADD CONSTRAINT FKfatura478275 FOREIGN KEY (id_encomenda) REFERENCES encomenda (id_encomenda);
ALTER TABLE scooter ADD CONSTRAINT FKscooter472857 FOREIGN KEY (id_modelo) REFERENCES modelo_scooter (id_modeloScooter);
ALTER TABLE estafeta ADD CONSTRAINT FKestafeta40706 FOREIGN KEY (id_tipoMochila) REFERENCES tipo_mochila (id_tipoMochila);
ALTER TABLE scooter ADD CONSTRAINT FKscooter532040 FOREIGN KEY (id_estado) REFERENCES estado_veiculo (id_estadoVeiculo);
ALTER TABLE scooter ADD CONSTRAINT FKscooter127997 FOREIGN KEY (id_tipoBateria) REFERENCES tipo_bateria (id_tipoBateria);



-- INSERT DATA INTO TABLES

--## Tabela localidade##

--## Tabela tipo_funcionario##

INSERT INTO tipo_funcionario(designacao)VALUES('estafeta');

--## Tabela artigo##

INSERT INTO artigo(designacao, precoUnitario, IVA, peso)VALUES('Alglucosidase alfa', 2.592, 6, 0.940);
INSERT INTO artigo(designacao, precoUnitario, IVA, peso)VALUES('Ambrisentano', 1.073, 6, 0.710);
INSERT INTO artigo(designacao, precoUnitario, IVA, peso)VALUES('Amifampridina', 2.33, 6, 0.940);
INSERT INTO artigo(designacao, precoUnitario, IVA, peso)VALUES('Anagrelida', 5.7, 6, 0.770);
INSERT INTO artigo(designacao, precoUnitario, IVA, peso)VALUES('Azacitidina', 2.533, 6, 0.660);
INSERT INTO artigo(designacao, precoUnitario, IVA, peso)VALUES('Aztreonam', 3.93, 6, 0.660);
INSERT INTO artigo(designacao, precoUnitario, IVA, peso)VALUES('Betaína', 3.99, 6, 0.700);
INSERT INTO artigo(designacao, precoUnitario, IVA, peso)VALUES('Bosentano', 6.93, 6, 0.740);
INSERT INTO artigo(designacao, precoUnitario, IVA, peso)VALUES('Bosutinib', 51, 6, 0.105);
INSERT INTO artigo(designacao, precoUnitario, IVA, peso)VALUES('Brentuximab vedotina', 2.99, 6, 0.710);
INSERT INTO artigo(designacao, precoUnitario, IVA, peso)VALUES('Cladribina', 8, 6, 0.650);


--## Tabela farmacia##

--## Tabela funcionario##

--## Tabela tipo_mochila##

INSERT INTO tipo_mochila(cargaMaxima, designacao)VALUES(20, 'mala strandart');

--## Tabela administrador##
INSERT INTO administrador(nome, password, email)VALUES('Andreia Silva', '123', 'admin@farmacia.com');

--## Tabela cliente##

--## Tabela conta##

--## Tabela tipo_parque##

INSERT INTO tipo_parque(designacao)VALUES('parque de scooter');
INSERT INTO tipo_parque(designacao)VALUES('parque de drones');

--## Tabela parque##

--## Tabela farmacia_parque##

--## Tabela r_artigo_farmacia##

--## Tabela tipo_disponibilidade##

INSERT INTO tipo_disponibilidade(designacao)VALUES('Livre');
INSERT INTO tipo_disponibilidade(designacao)VALUES('Ocupado');
INSERT INTO tipo_disponibilidade(designacao)VALUES('Não disponivel');

--## Tabela estafeta##

--## Tabela estado_encomenda##

INSERT INTO estado_encomenda(designacao)VALUES('pendente');
INSERT INTO estado_encomenda(designacao)VALUES('processada');
INSERT INTO estado_encomenda(designacao)VALUES('entregue');

--## Tabela encomenda##

--## Tabela r_encomenda_conta##

--## Tabela r_encomenda_artigo##

--## Tabela tipo_estacionamento##

INSERT INTO tipo_estacionamento(designacao)VALUES('Estacionamento regular');
INSERT INTO tipo_estacionamento(designacao)VALUES('Estacionamento com posto de carregamento');

--## Tabela lugar_estacionamento##

--## Tabela r_parque_lugar_Estacionamento##

--## Tabela fatura##

--## Tabela modo_pagamento##

INSERT INTO modo_pagamento(designacao)VALUES('cartão crédito');

--## Tabela pagamento##

--## Tabela entrega##

--## Tabela tipo_veiculo##

INSERT INTO tipo_veiculo(designacao)VALUES('scooter');
INSERT INTO tipo_veiculo(designacao)VALUES('drone');

--## Tabela veiculo##

--## Tabela tipo_bateria##

INSERT INTO tipo_bateria(designacao, capacidade, eficiencia)VALUES('KYOTO YTX14BS', 12, 40);
INSERT INTO tipo_bateria(designacao, capacidade, eficiencia)VALUES('DJI TELLO', 1, 55);

--## Tabela modelo_scooter##

INSERT INTO modelo_scooter(designacao, peso, areaFrontal, capacidadeMotor)VALUES('Sym HD 300', 179, 12, 12.6);

--## Tabela modelo_drone##
INSERT INTO modelo_drone(designacao, peso, cargaMaxima, areaFrontal)VALUES('DR-300', 1.3, 500, 5.0);

--## Tabela estado_veiculo##

INSERT INTO estado_veiculo(designacao)VALUES('funcional');
INSERT INTO estado_veiculo(designacao)VALUES('avariada');