create PROCEDURE prcRegistarVeiculo(p_tipoVeiculo VEICULO.ID_TIPOVEICULO%type,
                                    p_idFarmacia VEICULO.ID_VEICULO%type)
AS
    v_tipoVeiculo NUMBER;
    v_existe      BOOLEAN;

    excep_paramentroInvalidos EXCEPTION;
    excep_tipoVeiculoDesconhecido EXCEPTION;
    excep_farmaciaDesconhecida EXCEPTION;

BEGIN
    -- verifica parametro
    IF (p_tipoVeiculo IS NOT NULL AND p_idFarmacia IS NOT NULL) THEN
        -- verifica se farmacia Existe:
        v_existe := fncFarmaciaExisteById(p_idFarmacia);

        -- se existir
        IF (v_existe) THEN
            -- verificar se tipo de veiculo existe
            SELECT COUNT(id_tipoVeiculo) INTO v_tipoVeiculo FROM tipo_veiculo WHERE id_tipoVeiculo = p_tipoVeiculo;
            -- se existir:
            IF (v_tipoVeiculo > 0) THEN
                -- Criar o veiculo
                INSERT INTO VEICULO (ID_TIPOVEICULO, ID_FARMACIA)
                VALUES (p_tipoVeiculo, p_idFarmacia);
            ELSE
                RAISE excep_tipoVeiculoDesconhecido;
            END IF;
        ELSE
            RAISE excep_farmaciaDesconhecida;
        END IF;
    ELSE
        RAISE excep_paramentroInvalidos;
    END IF;


EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
    WHEN excep_paramentroInvalidos THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
    WHEN excep_tipoVeiculoDesconhecido THEN
        raise_application_error(-20003, 'Tipo de veiculo desconhecido');
    WHEN excep_farmaciaDesconhecida THEN
        raise_application_error(-20003, 'Farmacia desconhecida em sistema');
END;
/

create PROCEDURE prcRegistarScooter(p_matricula scooter.MATRICULA%type,
                                    p_farmacia farmacia.id_farmacia%type)
AS
    v_scooterExiste          BOOLEAN;
    v_farmaciaExiste         BOOLEAN;
    v_parqueExiste           BOOLEAN;
    v_tipoVeiculo            INTEGER := 1; -- tipo de veiculo -> scooter
    v_idModelo               INTEGER := 1; -- há apenas um modelo de scooter
    v_idTipoBateria          INTEGER := 1; -- há apenas um tipo de bateria
    v_idEstado               INTEGER := 1; -- por defeito, o estado é 'funcional'
    v_cargaBateriaAtual      INTEGER := 100; -- por defeito, a carga da bateria atual = 100%
    v_idVeiculoMaisRecente   VEICULO.ID_VEICULO%type;
    v_numVeiculoPorFarmacia  NUMBER;
    v_numTotalEstacionamento NUMBER;
    v_lugarParaEstacionar    NUMBER;
    v_idParque               NUMBER;
    v_existeParque           NUMBER;


    excep_parametroInvalido EXCEPTION;
    excep_lotacaoDoParqueCompleto EXCEPTION;
    excep_naoHaParqueParaFarmacia EXCEPTION;
    excep_FarmaciaNaoExiste EXCEPTION;
    excep_scooterJaExiste EXCEPTION;

BEGIN
    --Verifica os parametros
    IF (p_matricula IS NOT NULL AND p_farmacia IS NOT NULL) THEN

        --verifica se scooter existe
        v_scooterExiste := fncScooterExiste(p_matricula);
        --se existir
        IF (v_scooterExiste) THEN
            RAISE excep_scooterJaExiste;
        ELSE
            --Verifica se existe farmacia
            v_farmaciaExiste := fncFarmaciaExisteById(p_farmacia);
            --se existir farmacia:
            IF (v_farmaciaExiste) THEN
                --verifica se existe parque para a farmacia
                v_parqueExiste := fncExisteParque(p_farmacia);
                --se existe:
                IF (v_parqueExiste) THEN
                    --Verificar se existe lugar para mais scooters no parque
                    -- contar o numero de veiculos do tipo Scooter para determinada farmacia
                    SELECT COUNT(id_veiculo)
                    INTO v_numVeiculoPorFarmacia
                    FROM Veiculo
                    WHERE id_tipoVeiculo = v_tipoVeiculo
                      AND id_farmacia = p_farmacia;

                    -- contar o numero de estacionamento nos parques da farmacia
                    v_numTotalEstacionamento := fncNumLugaresNoParqueScooterPorFarmacia(p_farmacia);

                    --compara se o numero de veiculos da farmacia é inferior ao numero de lugares de estacionamento da farmacia
                    IF (v_numVeiculoPorFarmacia < v_numTotalEstacionamento) THEN

                        -- Criar o veiculo
                        prcRegistarVeiculo(v_tipoVeiculo, p_farmacia);

                        -- Get id do veículo mais recente (veiculo criado anteriormente)
                        SELECT MAX(ID_VEICULO) INTO v_idVeiculoMaisRecente FROM VEICULO;

                        -- Criar scooter
                        INSERT INTO SCOOTER (ID_SCOOTER, MATRICULA, ID_MODELO, ID_TIPOBATERIA, ID_ESTADO,
                                             CAPACIDADE_ATUAL)
                        VALUES (v_idVeiculoMaisRecente, p_matricula, v_idModelo, v_idTipoBateria, v_idEstado,
                                v_cargaBateriaAtual);

                        -- Estacionar Veiculo           
                        -- obter o id do parque de scooter da farmacia
                        --v_idParque:= fncGetIdParque(p_farmacia);

                        -- obter o id de um lugar de estacionamento livre

                        SELECT id_lugarEstacionamento
                        INTO v_lugarParaEstacionar
                        FROM (SELECT *
                              FROM (SELECT id_lugarEstacionamento
                                    FROM (SELECT id_parque FROM FARMACIA_PARQUE WHERE id_farmacia = p_farmacia) a
                                             LEFT JOIN lugar_estacionamento ON lugar_estacionamento.id_parque = a.id_parque) b
                              MINUS
                              (SELECT DISTINCT c.id_lugarEstacionamento
                               FROM (SELECT * FROM lugar_estacionamento) c --WHERE id_parque=v_idParque
                                        INNER JOIN r_veiculo_lugarEstacionamento
                                                   ON r_veiculo_lugarEstacionamento.id_lugarEstacionamento =
                                                      c.id_lugarEstacionamento
                               WHERE data_fim IS NULL)) d
                        WHERE ROWNUM = 1;

                        -- preencher a tabela r_veiculo_lugarEstacionamento
                        INSERT INTO r_veiculo_lugarEstacionamento(id_veiculo, id_lugarEstacionamento, data_inicio, data_fim)
                        VALUES (v_idVeiculoMaisRecente, v_lugarParaEstacionar, sysdate, NULL);

                    ELSE
                        RAISE excep_lotacaoDoParqueCompleto;
                    END IF;
                ELSE
                    RAISE excep_naoHaParqueParaFarmacia;
                END IF;
            ELSE
                RAISE excep_FarmaciaNaoExiste;
            END IF;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;


EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
    WHEN excep_lotacaoDoParqueCompleto THEN
        raise_application_error(-20003, 'Parque completo');
    WHEN excep_naoHaParqueParaFarmacia THEN
        raise_application_error(-20003, 'Não existe parque para esta farmacia');
    WHEN excep_FarmaciaNaoExiste THEN
        raise_application_error(-20003, 'Id de farmacia desconhecido - farmácia inexistente em sistema');
    WHEN excep_scooterJaExiste THEN
        raise_application_error(-20003, 'Scooter já existe em sistema');

END;
/

create PROCEDURE prcAtualizarScooter(p_matricula scooter.MATRICULA%type,
                                     p_estadoVeiculo ESTADO_VEICULO.DESIGNACAO%type)
AS
    v_scooterExiste BOOLEAN;
    idEstadoVeiculo ESTADO_VEICULO.ID_ESTADOVEICULO%type;
    counter         INTEGER;

    excep_parametrosInvalidos EXCEPTION;
    excep_matriculaDesconhecida EXCEPTION;
    excep_estadoScooterInvalido EXCEPTION;

BEGIN

    -- Verifica a validade dos parametros
    IF (p_matricula IS NOT NULL AND p_estadoVeiculo IS NOT NULL) THEN

        --Verificar se a scooter existe
        v_scooterExiste := fncScooterExiste(p_matricula);
        --se scooter existe:
        IF (v_scooterExiste) THEN

            -- Get id estado veiculo atraves da designacao
            Select COUNT(ID_ESTADOVEICULO)
            INTO counter
            FROM ESTADO_VEICULO
            WHERE upper(DESIGNACAO) = upper(p_estadoVeiculo);

            -- Caso exista o estado do veiculo
            IF (counter > 0) THEN

                -- Get id estado veiculo atraves da designacao
                SELECT ID_ESTADOVEICULO
                INTO idEstadoVeiculo
                FROM ESTADO_VEICULO
                WHERE UPPER(DESIGNACAO) = UPPER(p_estadoVeiculo);

                -- Atualizar id do estado scooter
                UPDATE SCOOTER
                SET ID_ESTADO = idEstadoVeiculo
                WHERE UPPER(MATRICULA) = UPPER(p_matricula);
            ELSE
                RAISE excep_estadoScooterInvalido;
            END IF;
        ELSE
            RAISE excep_matriculaDesconhecida;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'Morada desconhecida');
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
    WHEN excep_matriculaDesconhecida THEN
        raise_application_error(-20003, 'Matricula desconhecida em sistema');
    WHEN excep_estadoScooterInvalido THEN
        raise_application_error(-20003, 'Estado de VEICULO desconhecido');
END;
/

create PROCEDURe prcInserirEndereco(p_rua localidade.designacao%type, p_latitude localidade.latitude%type,
                                    p_longitude localidade.longitude%type)
AS

BEGIN

    INSERT INTO localidade(designacao, latitude, longitude) VALUES (p_rua, p_latitude, p_longitude);

END;
/

create PROCEDURE prcPreencherEncomenda(p_idEncomenda ENCOMENDA.ID_ENCOMENDA%type,
                                       p_idArtigo ARTIGO.ID_ARTIGO%type,
                                       p_quantidade R_ENCOMENDA_ARTIGO.QUANTIDADE%type)
AS
    nrLinhas          integer;
    maxLinha          integer := 0;
    v_existeEncomenda NUMBER;
    v_existeArtigo    NUMBER;
    v_farmacia        NUMBER;
    v_stock           NUMBER;

    excep_parametrosInvalidos EXCEPTION;
    excep_quantidadeInvalida EXCEPTION;
    excep_encomendaInexistente EXCEPTION;
    excep_artigoInexistente EXCEPTION;
    excep_naoExisteStockSuficiente EXCEPTION;

BEGIN

    -- verificar parametros
    IF (p_idEncomenda IS NOT NULL AND p_idArtigo IS NOT NULL AND p_quantidade IS NOT NULL) THEN
        -- verifica se encomenda existe
        SELECT COUNT(id_encomenda) INTO v_existeEncomenda FROM encomenda WHERE id_encomenda = p_idEncomenda;
        -- se existir:
        IF (v_existeEncomenda > 0) THEN
            -- verifica se artigo existe
            SELECT COUNT(id_artigo) INTO v_existeArtigo FROM Artigo WHERE id_artigo = p_idArtigo;
            -- se existir
            IF (v_existeArtigo > 0) THEN
                -- verificar se a quantidade é válida
                IF (p_quantidade > 0) THEN

                    --verificar em qual farmacia é feita a emcomenda
                    SELECT DISTINCT id_farmaciamaisproxima
                    INTO v_farmacia
                    FROM (SELECT a.id_encomenda, id_cliente
                          FROM (SELECT id_encomenda FROM encomenda) a
                                   LEFT JOIN r_encomenda_conta ON r_encomenda_conta.id_encomenda = a.id_encomenda) b
                             INNER JOIN conta ON conta.id_cliente = b.id_cliente;
                    -- verificar qual o stock do produto na tal farmacia
                    SELECT stock INTO v_stock FROM r_artigo_farmacia WHERE id_artigo = p_idArtigo
                                                                       AND id_farmacia = v_farmacia;
                    --verificar se tem stock suficiente
                    IF (v_stock >= p_quantidade) THEN
                        -- fazer o update dos stocks da tabela
                        UPDATE r_artigo_farmacia
                        SET stock = v_stock - p_quantidade
                        WHERE id_farmacia = v_farmacia
                          AND id_artigo = p_idartigo;

                        -- Verifica se a encomenda tem linhas
                        select count(LINHA) INTO nrLinhas FROM R_ENCOMENDA_ARTIGO WHERE ID_ENCOMENDA = p_idEncomenda;

                        -- Caso tenha linhas, verifica qual o nr da linha maior
                        IF nrLinhas > 0 THEN
                            SELECT max(LINHA) INTO maxLinha FROM R_ENCOMENDA_ARTIGO WHERE ID_ENCOMENDA = p_idEncomenda;
                        end if;

                        -- Adiciona mais um a variavel temporaria
                        maxLinha := maxLinha + 1;

                        -- Preenche a encomenda
                        INSERT INTO R_ENCOMENDA_ARTIGO (ID_ENCOMENDA, LINHA, ID_ARTIGO, QUANTIDADE)
                        VALUES (p_idEncomenda, maxLinha, p_idArtigo, p_quantidade);
                    ELSE
                        RAISE excep_naoExisteStockSuficiente;
                    END IF;
                ELSE
                    RAISE excep_quantidadeInvalida;
                END IF;
            ELSE
                RAISE excep_artigoInexistente;
            END IF;
        ELSE
            RAISE excep_encomendaInexistente;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION

    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data found');
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
    WHEN excep_encomendaInexistente THEN
        raise_application_error(-20003, 'Encomenda inexistente em sistema');
    WHEN excep_artigoInexistente THEN
        raise_application_error(-20003, 'Artigo inexistente em sistema');
    WHEN excep_quantidadeInvalida THEN
        raise_application_error(-20003, 'Quantidade inválida. O número deve ser superior a 0');
    WHEN excep_naoExisteStockSuficiente THEN
        raise_application_error(-20003, 'Não existe stock suficiente para efetuar essa encomenda');

END;
/

create PROCEDURE prcRemoverScooter(p_matricula scooter.MATRICULA%type)
AS

    v_scooterExiste BOOLEAN;
    v_idVeiculo     NUMBER;
    excep_scooterInexistente EXCEPTION;
    excep_parametroInvalido EXCEPTION;

BEGIN

    --Verifica os parametros
    IF (p_matricula IS NOT NULL) THEN
        -- verificar se a matricula existe
        v_scooterExiste := fncScooterExiste(p_matricula);
        -- se existir:
        IF (v_scooterExiste) THEN

            -- Colocar id de veiculo em variavel temporaria
            SELECT ID_SCOOTER INTO v_idVeiculo FROM SCOOTER WHERE UPPER(MATRICULA) = UPPER(p_matricula);

            -- Retirar o Veiculo do lugar de estacionamento
            DELETE FROM r_veiculo_lugarEstacionamento WHERE ID_VEICULO = v_idVeiculo;

            -- Eliminar scooter
            DELETE FROM SCOOTER WHERE UPPER(MATRICULA) = UPPER(p_matricula);

            -- Eliminar veiculo
            DELETE FROM VEICULO WHERE ID_VEICULO = v_idVeiculo;

        ELSE
            RAISE excep_scooterInexistente;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'Sem dados');
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
    WHEN excep_scooterInexistente THEN
        raise_application_error(-20003, 'Matricula desconhecida em sistema');

END;
/

create PROCEDURE prcAssociarEncomendaEntregaEstafeta(p_idVeiculo R_ESTAFETA_VEICULO_ENTREGA_ENCOMENDA.ID_VEICULO%type,
                                                     p_idEntrega R_ESTAFETA_VEICULO_ENTREGA_ENCOMENDA.ID_ENTREGA%type,
                                                     p_idEstafeta R_ESTAFETA_VEICULO_ENTREGA_ENCOMENDA.ID_ESTAFETA%type,
                                                     p_idEncomenda R_ESTAFETA_VEICULO_ENTREGA_ENCOMENDA.ID_ENCOMENDA%type)
AS

BEGIN

    -- Associar Entrega Estafeta
    INSERT INTO R_ESTAFETA_VEICULO_ENTREGA_ENCOMENDA (ID_VEICULO, ID_ENTREGA, ID_ESTAFETA, ID_ENCOMENDA)
    VALUES (p_idVeiculo, p_idEntrega, p_idEstafeta, p_idEncomenda);

END;
/

create PROCEDURE prcatualizarstock(p_idartigo r_artigo_farmacia.id_artigo%TYPE,
                                   p_idfarmacia r_artigo_farmacia.id_farmacia%TYPE,
                                   p_quantidade r_artigo_farmacia.stock%TYPE)
AS
    v_artigoExiste   BOOLEAN;
    v_farmaciaExiste BOOLEAN;
    v_existerelacao  NUMBER;

    excep_farmaciaNaoExiste EXCEPTION;
    excep_produtoNaoExiste EXCEPTION;
    excep_parametroInvalido EXCEPTION;


BEGIN
    -- Verificar parametros
    IF (p_idartigo IS NOT NULL AND p_idfarmacia IS NOT NULL AND p_quantidade IS NOT NULL) THEN

        -- verificar se artigo existe
        v_artigoExiste := fncArtigoExisteById(p_idartigo);
        -- se existir
        IF (v_artigoExiste) THEN

            -- verificar se farmacia existe:
            v_farmaciaExiste := fncFarmaciaExisteById(p_idfarmacia);
            -- se existir:
            IF (v_farmaciaExiste) THEN

                -- verificar se existe esse produto na farmacia
                SELECT COUNT(id_artigo)
                INTO v_existerelacao
                from r_artigo_farmacia
                WHERE id_farmacia = p_idfarmacia
                  AND id_artigo = p_idartigo;
                -- se ja existir fazer update:
                IF (v_existerelacao > 0) THEN
                    -- Atualizar stock
                    UPDATE r_artigo_farmacia
                    SET stock = p_quantidade
                    WHERE id_farmacia = p_idfarmacia
                      AND id_artigo = p_idartigo;
                    -- se nao existir criar:
                ELSE
                    INSERT INTO r_artigo_farmacia (id_artigo, id_farmacia, stock)
                    VALUES (p_idartigo, p_idfarmacia, p_quantidade);
                END IF;
            ELSE
                RAISE excep_farmaciaNaoExiste;
            END IF;
        ELSE
            RAISE excep_produtoNaoExiste;
        END iF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

EXCEPTION

    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
    WHEN excep_farmaciaNaoExiste THEN
        raise_application_error(-20003, 'Farmacia Inexistente em sistema');
    WHEN excep_produtoNaoExiste THEN
        raise_application_error(-20003, 'Artigo Inexistente em sistema');

END;
/

create PROCEDURE prcInserirArtigo(p_designacao artigo.designacao%type,
                                  p_precoUnitario artigo.precoUnitario%type,
                                  p_iva artigo.iva%type, p_peso artigo.peso%type)
AS

BEGIN
    INSERT INTO artigo (designacao, precoUnitario, iva, peso) VALUES (p_designacao, p_precoUnitario, p_iva, p_peso);
END;
/

create PROCEDURE prcRemoverArtigo(p_idArtigo artigo.id_artigo%type)
AS
    v_existe BOOLEAN;

    excep_artigoNaoExiste EXCEPTION;
    excep_parametroInvalido EXCEPTION;

BEGIN
    -- verificar parametro
    IF (p_idArtigo IS NOT NULL) THEN

        -- verificar se id artigo é valido
        v_existe := fncArtigoExisteById(p_idArtigo);

        IF (v_existe) THEN
            DELETE FROM r_artigo_farmacia WHERE id_artigo = p_idArtigo;
            DELETE FROM artigo WHERE id_artigo = p_idArtigo;
        ELSE
            RAISE excep_artigoNaoExiste;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

EXCEPTION

    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
    WHEN excep_artigoNaoExiste THEN
        raise_application_error(-20003, 'Artigo Inexistente em sistema');

END;
/

create PROCEDURE prcInserirCliente(p_nome cliente.nome%type, p_nif cliente.NIF%type,
                                   p_password conta.PASSWORD%type, p_rua LOCALIDADE.DESIGNACAO%type,
                                   p_latitude LOCALIDADE.LATITUDE%type,
                                   p_longitude LOCALIDADE.LONGITUDE%type,
                                   p_email cliente.EMAIL%type, p_cartaoCredito cliente.CARTAOCREDITO%type)
AS

    idEndereco localidade.id_localidade%type;
    idCliente  cliente.id_cliente%type;

BEGIN

    idEndereco := fncGetIdEndereco(p_rua, p_latitude, p_longitude);

    INSERT INTO cliente (NOME, NIF, ID_ENDRECO, EMAIL, CARTAOCREDITO)
    VALUES (p_nome, p_nif, idEndereco, p_email, p_cartaoCredito);

    SELECT MAX(ID_CLIENTE) INTO idCliente FROM CLIENTE;

    prcInserirNovaConta(idCliente, p_password);
END;
/

create PROCEDURE prcAtualizarCliente(p_nif cliente.NIF%type,
                                     p_nome cliente.nome%type,
                                     p_password conta.PASSWORD%type,
                                     p_rua LOCALIDADE.DESIGNACAO%type,
                                     p_latitude LOCALIDADE.LATITUDE%type,
                                     p_longitude LOCALIDADE.LONGITUDE%type,
                                     p_altitude LOCALIDADE.LONGITUDE%type,
                                     p_email cliente.EMAIL%type,
                                     p_cartaoCredito cliente.CARTAOCREDITO%type)
AS
    v_existeCliente NUMBER;
    v_idCliente     NUMBER;
    v_idEndereco    NUMBER;

    excep_parametrosInvalidos EXCEPTION;
    excep_clienteInexistente EXCEPTION;

BEGIN

    --verificar parametros
    IF (p_nif IS NOT NULL AND p_nome IS NOT NULL AND p_password IS NOT NULL AND p_rua IS NOT NULL AND
        p_latitude IS NOT NULL AND p_longitude IS NOT NULL AND
        p_altitude IS NOT NULL AND p_email IS NOT NULL AND p_cartaoCredito IS NOT NULL) THEN

        -- verificar se cliente existe
        SELECT COUNT(id_cliente) INTO v_existeCliente FROM cliente WHERE NIF = p_nif;
        -- se existe:
        IF (v_existeCliente > 0) THEN
            -- obter o id de cliente
            v_idCliente := fncGetIdClienteByNif(p_nif);
            -- obter o endereco pelo id_cliente
            SELECT id_endreco INTO v_idEndereco FROM cliente WHERE NIF = p_nif;

            -- Endereco - update da tabela localidade   
            UPDATE localidade
            SET designacao=p_rua,
                latitude=p_latitude,
                longitude=p_longitude,
                altitude=p_altitude
            WHERE id_localidade = v_idEndereco;

            -- update da tabela cliente
            UPDATE cliente
            SET NOME          = p_nome,
                ID_ENDRECO    = v_idEndereco,
                EMAIL         = p_email,
                CARTAOCREDITO = p_cartaoCredito
            WHERE NIF = p_nif;

            -- update da tabela conta
            UPDATE CONTA
            SET PASSWORD = p_password
            WHERE ID_CLIENTE = v_idCliente;

        ELSE
            RAISE excep_clienteInexistente;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
    WHEN excep_clienteInexistente THEN
        raise_application_error(-20004, 'Cliente inexistente em sistema . Impossivel atualizar dados');

END;
/

create PROCEDURE prcatualizarcapacidadeparque(p_id NUMBER,
                                              p_numestacionamentonormal NUMBER,
                                              p_numpostocarregamento NUMBER) AS
    excep_nenhumparametro EXCEPTION;
BEGIN
    IF (
            p_id IS NOT NULL
            AND p_numestacionamentonormal IS NOT NULL
            AND p_numpostocarregamento IS NOT NULL
        ) THEN
        UPDATE parque
        SET numlugaresestacionamentonormal = p_numestacionamentonormal,
            numpostocarregamento           = p_numpostocarregamento
        WHERE id_parque = p_id;

    ELSE
        RAISE excep_nenhumparametro;
    END IF;
EXCEPTION
    WHEN excep_nenhumparametro THEN
        raise_application_error(-20001, 'Parametro inserido inválido');
END;
/

create PROCEDURE prcAtualizarDataFimEntrega(p_id entrega.id_entrega%type,
                                            p_dataFim entrega.DATAFIM%type)
AS
    excep_nenhumParametro EXCEPTION;

BEGIN

    IF (p_id IS NOT NULL AND p_dataFim IS NOT NULL) THEN
        UPDATE ENTREGA
        SET DATAFIM = p_dataFim
        WHERE ID_ENTREGA = p_id;
    ELSE
        RAISE excep_nenhumParametro;
    END IF;

EXCEPTION
    WHEN excep_nenhumParametro THEN
        raise_application_error(-20001, 'Parametro inserido inválido');
END;
/

create PROCEDURE prcAtualizarEstadoEncomendaEntregue(p_id ENCOMENDA.ID_ENCOMENDA%type)
AS
    excep_nenhumParametro EXCEPTION;

BEGIN

    IF (p_id IS NOT NULL) THEN
        UPDATE ENCOMENDA
        SET ID_ESTADOENCOMENDA = 3
        WHERE ID_ESTADOENCOMENDA = p_id;
    ELSE
        RAISE excep_nenhumParametro;
    END IF;

EXCEPTION
    WHEN excep_nenhumParametro THEN
        raise_application_error(-20001, 'Parametro inserido inválido');
END;
/

create procedure prcUpdateEncomendasByListIds(p_cursorIds sys_refcursor)
AS

    idEncomenda encomenda.ID_ENCOMENDA%type;

BEGIN

    LOOP
        FETCH p_cursorIds INTO idEncomenda;
        EXIT WHEN p_cursorIds%NOTFOUND;

        prcAtualizarEstadoEncomendaEntregue(idEncomenda);

    END LOOP;
END;
/

create PROCEDURE prcInserirEntrega(p_dataInicio TIMESTAMP)
AS

BEGIN
    INSERT INTO entrega (dataInicio) VALUES (p_dataInicio);
END;
/

create procedure prcColocarEstafetaLivre(p_idEntrega entrega.id_entrega%type)
AS

    idEstafeta ESTAFETA.ID_FUNCIONARIO%type;

BEGIN

    select max(ID_ESTAFETA) into idEstafeta from R_ESTAFETA_VEICULO_ENTREGA_ENCOMENDA where ID_ENTREGA = p_idEntrega;

    -- Colocar estafeta com tipo de disponibilidade 'livre'
    Update ESTAFETA
    set ID_TIPODISPONIBILIDADE = 1
    where ID_FUNCIONARIO = idEstafeta;

END;
/

create PROCEDURE prcAdicionarEncomendaEntrega(p_idVeiculo VEICULO.ID_VEICULO%type,
                                              p_idEntrega ENTREGA.ID_ENTREGA%type,
                                              p_idEstafeta ESTAFETA.ID_FUNCIONARIO%type,
                                              p_idEncomenda ENCOMENDA.ID_ENCOMENDA%type)
AS
    excep_nenhumParametro EXCEPTION;

BEGIN

    IF (p_idVeiculo IS NOT NULL and p_idEntrega IS NOT NULL and p_idEstafeta IS NOT NULL and
        p_idEncomenda IS NOT NULL) THEN
        INSERT INTO R_ESTAFETA_VEICULO_ENTREGA_ENCOMENDA(ID_VEICULO, ID_ENTREGA, ID_ESTAFETA, ID_ENCOMENDA)
        VALUES (p_idVeiculo, p_idEntrega, p_idEstafeta, p_idEncomenda);
    ELSE
        RAISE excep_nenhumParametro;
    END IF;

EXCEPTION
    WHEN excep_nenhumParametro THEN
        raise_application_error(-20001, 'Parametro inserido inválido');
END;
/

create PROCEDURE prcAtualizarEstadoEncomendaProcessada(p_id ENCOMENDA.ID_ENCOMENDA%type)
AS
    excep_nenhumParametro EXCEPTION;

BEGIN

    IF (p_id IS NOT NULL) THEN
        UPDATE ENCOMENDA
        SET ID_ESTADOENCOMENDA = 2
        WHERE ID_ESTADOENCOMENDA = p_id;
    ELSE
        RAISE excep_nenhumParametro;
    END IF;

EXCEPTION
    WHEN excep_nenhumParametro THEN
        raise_application_error(-20001, 'Parametro inserido inválido');
END;
/

create PROCEDURE prcupdateestafetaentrega(p_idestafeta estafeta.id_funcionario%TYPE) AS
BEGIN

    -- Atualizar stock
    UPDATE estafeta
    SET id_tipodisponibilidade = 2
    WHERE estafeta.id_funcionario = p_idestafeta;

END;
/

create PROCEDURE prcupdateencomendaentrega(
    p_idencomenda encomenda.id_encomenda%TYPE
) AS
BEGIN

    -- Atualizar stock
    UPDATE encomenda
    SET id_estadoencomenda = 2
    WHERE encomenda.id_encomenda = p_idencomenda;

END;
/

create PROCEDURE prcRegistarFarmacia(p_designacao farmacia.designacao%type,
                                     p_NIPC farmacia.NIPC%type,
                                     p_rua localidade.designacao%type,
                                     p_latitude localidade.latitude%type,
                                     p_longitude localidade.longitude%type,
                                     p_altitude localidade.altitude%type)
AS
    idEndereco     localidade.id_localidade%type;
    farmaciaExiste BOOLEAN;
    moradaExiste   BOOLEAN;

    excep_parametrosInvalidos EXCEPTION;
    excep_farmaciaExistenteEmSistema EXCEPTION;


BEGIN

    --validar os parametros
    IF (p_designacao IS NOT NULL AND p_NIPC IS NOT NULL AND p_rua IS NOT NULL AND p_latitude IS NOT NULL AND
        p_longitude IS NOT NULL AND p_altitude IS NOT NULL) THEN

        -- verificar se farmacia ja existe em sistema
        farmaciaExiste := fncFarmaciaExiste(p_NIPC);

        IF (farmaciaExiste) THEN
            RAISE excep_farmaciaExistenteEmSistema;
        ELSE
            -- verificar se a morada existe na BDDAD
            moradaExiste := fncEnderecoExiste(p_rua, p_latitude, p_longitude);

            -- se existir, devolver o id
            IF (moradaExiste) THEN
                SELECT id_localidade
                INTO idEndereco
                FROM localidade
                WHERE designacao = p_rua
                  AND latitude = p_latitude
                  AND longitude = p_longitude
                  AND altitude = p_altitude;
            ELSE
                -- se nao existir, criar
                INSERT INTO localidade(designacao, latitude, longitude, altitude)
                VALUES (p_rua, p_latitude, p_longitude, p_altitude);

                SELECT MAX(id_localidade) INTO idEndereco FROM localidade;
            END IF;


            --Preenche a tabela farmacia
            INSERT INTO farmacia(NIPC, id_endereco, designacao)
            VALUES (p_NIPC, idEndereco, p_designacao);

        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
    WHEN excep_farmaciaExistenteEmSistema THEN
        raise_application_error(-20004, 'Farmacia registada em sistema');

END;
/

create PROCEDURE prcAtualizarFarmacia(p_designacao farmacia.designacao%type,
                                      p_NIPC farmacia.NIPC%type,
                                      p_rua localidade.designacao%type,
                                      p_latitude localidade.latitude%type,
                                      p_longitude localidade.longitude%type,
                                      p_altitude localidade.altitude%type)
AS
    moradaExiste   BOOLEAN;
    farmaciaExiste BOOLEAN;
    idEndereco     INTEGER;

    excep_parametrosInvalidos EXCEPTION;
    excep_NaoExisteFarmaciaEmSistema EXCEPTION;

BEGIN
    --verificar os parametros
    IF (p_designacao IS NOT NULL AND p_NIPC IS NOT NULL AND p_rua IS NOT NULL AND p_latitude IS NOT NULL AND
        p_longitude IS NOT NULL AND p_altitude IS NOT NULL) THEN

        -- verificar se farmacia ja existe em sistema
        farmaciaExiste := fncFarmaciaExiste(p_NIPC);
        -- se existe:
        IF (farmaciaExiste) THEN

            -- Endereco - procurar pelo ID
            SELECT id_endereco INTO idEndereco FROM farmacia WHERE NIPC = p_NIPC;

            -- Endereco - update da tabela     
            UPDATE localidade
            SET designacao=p_rua,
                latitude=p_latitude,
                longitude=p_longitude,
                altitude=p_altitude
            WHERE id_localidade = idEndereco;

            -- ALTERACAO A FARMACIA (id_endereco e designacao)
            UPDATE farmacia
            SET id_endereco=idEndereco,
                designacao=p_designacao
            WHERE NIPC = p_NIPC;
        ELSE
            RAISE excep_NaoExisteFarmaciaEmSistema;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
    WHEN excep_NaoExisteFarmaciaEmSistema THEN
        raise_application_error(-20004, 'Farmacia inexistente em sistema . Impossivel atualizar dados');

END;
/

create PROCEDURE prcRegistarDrone(p_numRegisto drone.numregisto%type,
                                  p_farmacia farmacia.id_farmacia%type)
AS
    v_droneExiste            BOOLEAN;
    v_farmaciaExiste         BOOLEAN;
    v_parqueExiste           BOOLEAN;
    v_tipoVeiculo            INTEGER := 2; -- tipo de veiculo -> drone
    v_idModelo               INTEGER := 1; -- há apenas um modelo de drone
    v_idTipoBateria          INTEGER := 2; -- associado à bateria de drone
    v_idEstado               INTEGER := 1; -- por defeito, o estado é 'funcional'
    v_cargaBateriaAtual      INTEGER := 100; -- por defeito, a carga da bateria atual = 100%

    v_idVeiculoMaisRecente   NUMBER;
    v_numVeiculoPorFarmacia  NUMBER;
    v_numTotalEstacionamento NUMBER;
    v_lugarParaEstacionar    NUMBER;
    v_idParque               NUMBER;

    excep_parametroInvalido EXCEPTION;
    excep_lotacaoDoParqueCompleto EXCEPTION;
    excep_naoHaParqueParaFarmacia EXCEPTION;
    excep_FarmaciaNaoExiste EXCEPTION;
    excep_droneJaExiste EXCEPTION;

BEGIN
    --Verifica os parametros
    IF (p_numRegisto IS NOT NULL AND p_farmacia IS NOT NULL) THEN

        --verifica se drone existe
        v_droneExiste := fncDroneExiste(p_numRegisto);

        --se existir
        IF (v_droneExiste) THEN
            RAISE excep_droneJaExiste;
        ELSE
            --Verifica se existe farmacia
            v_farmaciaExiste := fncFarmaciaExisteById(p_farmacia);
            --se existir farmacia:
            IF (v_farmaciaExiste) THEN
                --verifica se existe parque para a farmacia
                v_parqueExiste := fncParqueExiste(p_farmacia);
                --se existe:
                IF (v_parqueExiste) THEN
                    --Verificar se existe lugar para mais drones no parque
                    -- contar o numero de veiculos do tipo drone para determinada farmacia
                    SELECT COUNT(id_veiculo)
                    INTO v_numVeiculoPorFarmacia
                    FROM Veiculo
                    WHERE id_tipoVeiculo = v_tipoVeiculo
                      AND id_farmacia = p_farmacia;

                    -- contar o numero de estacionamento nos parques da farmacia
                    v_numTotalEstacionamento := fncNumLugaresNoParqueDronesPorFarmacia(p_farmacia);

                    --compara se o numero de veiculos da farmacia é inferior ao numero de lugares de estacionamento da farmacia
                    IF (v_numVeiculoPorFarmacia < v_numTotalEstacionamento) THEN

                        -- Criar o veiculo
                        prcRegistarVeiculo(v_tipoVeiculo, p_farmacia);

                        -- Get id do veículo mais recente (veiculo criado anteriormente)
                        SELECT MAX(ID_VEICULO) INTO v_idVeiculoMaisRecente FROM VEICULO;

                        -- Criar drone
                        INSERT INTO DRONE (ID_DRONE, NUMREGISTO, ID_MODELO, ID_TIPOBATERIA, ID_ESTADO, CAPACIDADE_ATUAL)
                        VALUES (v_idVeiculoMaisRecente, p_numRegisto, v_idModelo, v_idTipoBateria, v_idEstado,
                                v_cargaBateriaAtual);

                        -- Estacionar Veiculo           
                        -- obter o id do parque de drone da farmacia


                        v_idParque := fncGetIdParqueDrone(p_farmacia);

                        -- obter o id de um lugar de estacionamento livre
                        SELECT id_lugarEstacionamento
                        INTO v_lugarParaEstacionar
                        FROM (SELECT *
                              FROM (SELECT id_lugarEstacionamento
                                    FROM (SELECT id_parque FROM FARMACIA_PARQUE WHERE id_farmacia = p_farmacia) a
                                             LEFT JOIN lugar_estacionamento ON lugar_estacionamento.id_parque = a.id_parque) b
                              MINUS
                              (SELECT DISTINCT c.id_lugarEstacionamento
                               FROM (SELECT * FROM lugar_estacionamento WHERE id_parque = v_idParque) c
                                        INNER JOIN r_veiculo_lugarEstacionamento
                                                   ON r_veiculo_lugarEstacionamento.id_lugarEstacionamento =
                                                      c.id_lugarEstacionamento
                               WHERE data_fim IS NULL)) d
                        WHERE ROWNUM = 1;

                        -- preencher a tabela r_veiculo_lugarEstacionamento
                        INSERT INTO r_veiculo_lugarEstacionamento(id_veiculo, id_lugarEstacionamento, data_inicio, data_fim)
                        VALUES (v_idVeiculoMaisRecente, v_lugarParaEstacionar, sysdate, NULL);

                    ELSE
                        RAISE excep_lotacaoDoParqueCompleto;
                    END IF;
                ELSE
                    RAISE excep_naoHaParqueParaFarmacia;
                END IF;
            ELSE
                RAISE excep_FarmaciaNaoExiste;
            END IF;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;


EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
    WHEN excep_lotacaoDoParqueCompleto THEN
        raise_application_error(-20003, 'Parque completo');
    WHEN excep_naoHaParqueParaFarmacia THEN
        raise_application_error(-20003, 'Não existe parque para esta farmacia');
    WHEN excep_FarmaciaNaoExiste THEN
        raise_application_error(-20003, 'Id de farmacia desconhecido - farmácia inexistente em sistema');
    WHEN excep_droneJaExiste THEN
        raise_application_error(-20003, 'Scooter já existe em sistema');

END;
/

create PROCEDURE prcAtualizarDrone(p_numRegisto drone.numRegisto%type,
                                   p_estadoVeiculo ESTADO_VEICULO.DESIGNACAO%type)
AS
    v_droneExiste   BOOLEAN;
    idEstadoVeiculo ESTADO_VEICULO.ID_ESTADOVEICULO%type;
    counter         INTEGER;

    excep_parametrosInvalidos EXCEPTION;
    excep_numRegistoDesconhecida EXCEPTION;
    excep_estadoDroneInvalido EXCEPTION;

BEGIN

    -- Verifica a validade dos parametros
    IF (p_numRegisto IS NOT NULL AND p_estadoVeiculo IS NOT NULL) THEN

        --Verificar se a scooter existe
        v_droneExiste := fncDroneExiste(p_numRegisto);

        --se scooter existe:
        IF (v_droneExiste) THEN

            -- Get id estado veiculo atraves da designacao
            Select COUNT(ID_ESTADOVEICULO)
            INTO counter
            FROM ESTADO_VEICULO
            WHERE upper(DESIGNACAO) = upper(p_estadoVeiculo);

            -- Caso exista o estado do veiculo
            IF (counter > 0) THEN

                -- Get id estado veiculo atraves da designacao
                SELECT ID_ESTADOVEICULO
                INTO idEstadoVeiculo
                FROM ESTADO_VEICULO
                WHERE UPPER(DESIGNACAO) = UPPER(p_estadoVeiculo);

                -- Atualizar id do estado scooter
                UPDATE SCOOTER
                SET ID_ESTADO = idEstadoVeiculo
                WHERE p_numRegisto = p_numRegisto;
            ELSE
                RAISE excep_estadoDroneInvalido;
            END IF;
        ELSE
            RAISE excep_numRegistoDesconhecida;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data found');
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
    WHEN excep_numRegistoDesconhecida THEN
        raise_application_error(-20003, 'Numero de registo desconhecido em sistema');
    WHEN excep_estadoDroneInvalido THEN
        raise_application_error(-20003, 'Estado de veículo desconhecido');
END;
/

create PROCEDURE prcRemoverDrone(p_numRegisto drone.numRegisto%type)
AS

    v_droneExiste BOOLEAN;
    v_idVeiculo   NUMBER;
    excep_droneInexistente EXCEPTION;
    excep_parametroInvalido EXCEPTION;

BEGIN

    --Verifica os parametros
    IF (p_numRegisto IS NOT NULL) THEN
        -- verificar se a matricula existe
        v_droneExiste := fncDroneExiste(p_numRegisto);
        -- se existir:
        IF (v_droneExiste) THEN

            -- Colocar id de veiculo em variavel temporaria
            SELECT id_drone INTO v_idVeiculo FROM drone WHERE numRegisto = p_numRegisto;

            -- Retirar o Veiculo do lugar de estacionamento
            DELETE FROM r_veiculo_lugarEstacionamento WHERE ID_VEICULO = v_idVeiculo;

            -- Eliminar scooter
            DELETE FROM drone WHERE numRegisto = p_numRegisto;

            -- Eliminar veiculo
            DELETE FROM veiculo WHERE id_veiculo = v_idVeiculo;

        ELSE
            RAISE excep_droneInexistente;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'Sem dados');
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
    WHEN excep_droneInexistente THEN
        raise_application_error(-20003, 'Numero de registo desconhecido em sistema');

END;
/

create PROCEDURE prcRemoverFarmacia(p_NIPC farmacia.nipc%type)
AS

    farmaciaExiste BOOLEAN;

    excep_farmaciaNaoExiste EXCEPTION;
    excep_parametrosInvalidos EXCEPTION;

BEGIN

    --verificar se o parametro é valido
    IF (p_NIPC IS NOT NULL) THEN
        -- verificar se farmacia ja existe em sistema
        farmaciaExiste := fncFarmaciaExiste(p_NIPC);
        --se existe remove a farmacia
        IF (farmaciaExiste) THEN
            DELETE FROM Farmacia WHERE nipc = p_NIPC;
            --caso contrário, devolve um erro dizendo que a farmacia nao existe em sistema
        ELSE
            RAISE excep_farmaciaNaoExiste;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;


EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
    WHEN excep_farmaciaNaoExiste THEN
        raise_application_error(-20003, 'NIPC desconhecido em sistema - Nenhuma farmacia encontrada');
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20004, 'Parametro inserido invalido');

END;
/

create PROCEDURE prcadicionarcreditos(p_idcliente conta.id_cliente%TYPE,
                                      p_valorpago INTEGER) AS
BEGIN
    UPDATE conta
    SET credito = credito + mod(p_valorpago, 5)
    WHERE id_cliente = p_idcliente;

END;
/

create PROCEDURE prcRegistarFatura(p_idEncomenda ENCOMENDA.ID_ENCOMENDA%type, p_gastarCreditos integer)
AS

    idCliente          CLIENTE.ID_CLIENTE%type;
    totalValorFaturado number  := 0;
    precoArtigo        number;
    precoUnitarioTemp  number;
    valorIVA           number;
    valorTotalIVA      number;
    creditosAdicionar  number;
    idArtigo           ARTIGO.ID_ARTIGO%type;
    transporte         integer := 2; -- por defeito, valor transporte = 2
    nrFatura           integer := 0;
    counter            integer;
    temp_cursor        sys_refcursor;
    registos           R_ENCOMENDA_ARTIGO%rowtype;
    valorCreditos      CONTA.CREDITO%TYPE;
    excep_semCreditos EXCEPTION;
BEGIN
    -- Percorrer todas as linhas da encomenda para calcular valor total faturado
    open temp_cursor for
        select * from R_ENCOMENDA_ARTIGO where ID_ENCOMENDA = p_idEncomenda;

    loop
        fetch temp_cursor
            into registos;
        exit when temp_cursor%notfound;

        -- Get id Artigo
        idArtigo := registos.ID_ARTIGO;

        -- Get Valor do iva do artigo
        SELECT IVA INTO valorIVA FROM ARTIGO WHERE ID_ARTIGO = idArtigo;

        -- Calculo do valor do artigo com iva
        SELECT PRECOUNITARIO INTO precoUnitarioTemp FROM ARTIGO WHERE ID_ARTIGO = idArtigo;
        valorTotalIVA := precoUnitarioTemp * (valorIVA / 100);
        precoArtigo := precoUnitarioTemp + valorTotalIVA;

        -- Calculo do valor do artigo com iva * quatidade
        totalValorFaturado := totalValorFaturado + (precoArtigo * registos.QUANTIDADE);
    end loop;

-- Get id cliente atraves do id encomenda
    SELECT ID_CLIENTE INTO idCliente FROM R_ENCOMENDA_CONTA WHERE ID_ENCOMENDA = p_idEncomenda;

-- Verifica se há faturas
    SELECT COUNT(ID_FATURA)
    INTO counter
    FROM FATURA;

-- Caso tenha faturas, get ultima fatura
    IF counter > 0 THEN
        SELECT MAX(ID_FATURA)
        INTO nrFatura
        FROM FATURA;
    end if;

    -- Atribuir proxima fatura
    nrFatura := nrFatura + 1;

    -- Se utilizador pretende, caso tenha, gastar creditos
    IF p_gastarCreditos = 1 THEN

        -- Get valor creditos
        SELECT CREDITO INTO valorCreditos FROM CONTA WHERE ID_CLIENTE = idCliente;

        IF valorCreditos >= 10 then
            -- Cria fatura com valor de creditos a descontar (se existirem 10 ou mais creditos o valor de transporte é = 0)
            INSERT INTO FATURA (NUMERO, DATA, ID_ENCOMENDA, VALORFATURADO, VALORTRANSPORTE, VALORDESCONTADO)
            VALUES (nrFatura, sysdate, p_idEncomenda, totalValorFaturado, 0, valorCreditos);

            -- Adiciona valores de creditos
            UPDATE CONTA
            SET CREDITO = (CREDITO - 10)
            WHERE ID_CLIENTE = idCliente;
        else
            RAISE excep_semCreditos;
        end if;

    ELSE
        -- Cria fatura sem valor de creditos a descontar
        INSERT INTO FATURA (NUMERO, DATA, ID_ENCOMENDA, VALORFATURADO, VALORTRANSPORTE, VALORDESCONTADO)
        VALUES (nrFatura, sysdate, p_idEncomenda, totalValorFaturado, transporte, 0);
        prcadicionarcreditos(idCliente, totalValorFaturado);
    end if;

    -- Get valor creditos
    SELECT CREDITO INTO valorCreditos FROM CONTA WHERE ID_CLIENTE = idCliente;

    -- Valor dos creditos a adicionar
    creditosAdicionar := totalValorFaturado * (1 / 100);
    creditosAdicionar := creditosAdicionar + valorCreditos;

    -- Adiciona valores de creditos
    UPDATE CONTA
    SET CREDITO = creditosAdicionar
    WHERE ID_CLIENTE = idCliente;


EXCEPTION
    WHEN excep_semCreditos THEN
        raise_application_error(-20001, 'Sem créditos suficientes');
END;
/

create PROCEDURE prcRemoverParque(p_idParque PARQUE.ID_PARQUE%type)
AS
    excep_parametroInvalido EXCEPTION;
    excep_parqueDesconhecido EXCEPTION;
    excep_ExistemVeiculosEstacionados EXCEPTION;

    cursor_idEstacionamento sys_refcursor;
    c_idLugaresParque       sys_refcursor;
    v_idEstacionamento      NUMBER;
    v_numLugaresOcupados    NUMBER;
    counter                 integer;

BEGIN
    -- verifica se existe id de parque
    SELECT COUNT(ID_PARQUE) INTO counter FROM PARQUE WHERE ID_PARQUE = p_idParque;

    IF (p_idParque IS NOT NULL) THEN
        -- se parque existe:
        IF (counter > 0) THEN

            -- ver quantos veiculos estao estacionados no parque
            v_numLugaresOcupados := fncGetNumLugaresOcupados(p_idParque);

            IF (v_numLugaresOcupados = 0) THEN

                -- remove os lugares de estacionamento na tabela spotCarregamento
                cursor_idEstacionamento := fncGetAllEstacionamentoComCarregador(p_idParque);

                LOOP
                    FETCH cursor_idEstacionamento
                        INTO v_idEstacionamento;
                    EXIT WHEN cursor_idEstacionamento%NOTFOUND;

                    DELETE FROM spotCarregamento WHERE id_lugarEstacionamento = v_idEstacionamento;
                END LOOP;

                -- remove as linhas com o id_parque da tabela lugar_estacionamento
                DELETE FROM lugar_estacionamento WHERE ID_PARQUE = p_idParque;
                -- remove a linha com o id_parque da tabela farmacia_parque
                DELETE FROM FARMACIA_PARQUE WHERE ID_PARQUE = p_idParque;
                -- remove a linha com o id_parque da tabela parque
                DELETE FROM PARQUE WHERE ID_PARQUE = p_idParque;
            ELSE
                RAISE excep_ExistemVeiculosEstacionados;
            END IF;

        ELSE
            RAISE excep_parqueDesconhecido;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

EXCEPTION
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20001, 'Parametro de entrada invalido');
    WHEN excep_parqueDesconhecido THEN
        raise_application_error(-20001, 'Parque desconhecido em sistema');
    WHEN excep_ExistemVeiculosEstacionados THEN
        raise_application_error(-20001,
                                'Remoção de parque impossivel. Existem veiculos estacionados no parque (deverão ser removidos/deslocados previamente à remoção do parque.');
END;
/

create PROCEDURE prcInserirContaCliente(p_IdCliente NUMBER, p_password VARCHAR)
AS

    v_creditos        NUMBER := 0;
    v_farmaciaProxima NUMBER := 1;

    excep_parametrosInvalidos EXCEPTION;

BEGIN
    --verifica parametros
    IF (p_IdCliente IS NOT NULL AND p_password IS NOT NULL) THEN
        -- inserir nova linha na tabela conta
        INSERT INTO conta (id_cliente, password, credito, id_farmaciamaisproxima)
        VALUES (p_IdCliente, p_password, v_creditos, v_farmaciaProxima);
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');

END;
/

create PROCEDURE prcRegistarCliente(p_nome cliente.nome%type,
                                    p_nif cliente.NIF%type,
                                    p_password conta.PASSWORD%type,
                                    p_rua LOCALIDADE.DESIGNACAO%type,
                                    p_latitude LOCALIDADE.LATITUDE%type,
                                    p_longitude LOCALIDADE.LONGITUDE%type,
                                    p_altitude LOCALIDADE.altitude%type,
                                    p_email cliente.EMAIL%type,
                                    p_cartaoCredito cliente.cartaoCredito%type)
AS
    v_existeCliente NUMBER;
    v_moradaExiste  BOOLEAN;
    v_idEndereco    NUMBER;
    v_idCliente     NUMBER;

    excep_clienteJaExiste EXCEPTION;
    excep_parametrosInvalidos EXCEPTION;

BEGIN
    -- verificar os parametros
    IF (p_nome IS NOT NULL AND p_nif IS NOT NULL AND p_password IS NOT NULL AND p_latitude IS NOT NULL AND
        p_longitude IS NOT NULL AND p_altitude IS NOT NULL AND
        p_email IS NOT NULL AND p_cartaoCredito IS NOT NULL) THEN

        -- verificar se cliente já existe:
        SELECT COUNT(id_cliente) INTO v_existeCliente FROM cliente WHERE NIF = p_NIF;
        -- se nao existir:
        IF (v_existeCliente = 0) THEN
            -- ENDERECO
            -- verificar se endereco existe
            v_moradaExiste := fncEnderecoExiste(p_rua, p_latitude, p_longitude, p_altitude);
            IF (v_moradaExiste) THEN
                --guardra o id em variavel
                v_idEndereco := fncGetIdEndereco(p_rua, p_latitude, p_longitude, p_latitude);
            ELSE
                --inserir nova morada na tabela localidade
                INSERT INTO localidade(designacao, latitude, longitude, altitude)
                VALUES (p_rua, p_latitude, p_longitude, p_altitude);

                SELECT MAX(id_localidade) INTO v_idEndereco FROM localidade;
            END IF;

            -- preecher a tabela cliente
            INSERT INTO cliente (NOME, NIF, ID_ENDRECO, EMAIL, CARTAOCREDITO)
            VALUES (p_nome, p_nif, v_idEndereco, p_email, p_cartaoCredito);

            SELECT MAX(ID_CLIENTE) INTO v_idCliente FROM CLIENTE;

            -- preencher tabela conta
            prcInserirContaCliente(v_idCliente, p_password);
        ELSE
            RAISE excep_clienteJaExiste;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
    WHEN excep_clienteJaExiste THEN
        raise_application_error(-20003, 'Cliente já existente em Farmacia');

END;
/


