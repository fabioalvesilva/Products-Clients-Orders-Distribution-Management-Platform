create FUNCTION fncValidarAdmin(p_email cliente.email%type) RETURN INTEGER
AS
    v_admin                NUMBER;
    v_tipoFuncionarioAdmin NUMBER;

    excep_nenhumParametro EXCEPTION;
    excep_emailDesconhecido EXCEPTION;

BEGIN
    -- verificar parametro
    IF (p_email IS NOT NULL) THEN
        -- seleciona o id do tipoFuncionario referente ao Administrador
        SELECT Id_tipofuncionario
        INTO v_tipoFuncionarioAdmin
        FROM tipo_funcionario
        WHERE UPPER(designacao) LIKE UPPER('administrador');
        -- retorna o tipoFuncionario de um determinado mail
        SELECT funcionario.id_tipoFuncionario INTO v_admin FROM funcionario WHERE UPPER(email) = UPPER(p_email);
        -- se email nao for reconhecido:
        IF (v_admin IS NULL) THEN
            RAISE excep_emailDesconhecido;
        END IF;
        -- se o id corresponder ao id do tipo de funcionario Administrador:
        IF (v_admin = v_tipoFuncionarioAdmin) THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    ELSE
        RAISE excep_nenhumParametro;
    END IF;


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20003, 'Email desconhecido');
        RETURN 0;
    WHEN excep_nenhumParametro THEN
        raise_application_error(-20001, 'Nenhum parametro inserido');
        RETURN 0;
    WHEN excep_emailDesconhecido THEN
        raise_application_error(-20002, 'Email desconhecido');
        RETURN 0;

END;
/

create FUNCTION fncValidarLogin(p_email cliente.email%type, p_password conta.password%type) RETURN BOOLEAN
AS
    excep_parametroInvalido EXCEPTION;
    v_conta    NUMBER;
    v_password VARCHAR2(255);

BEGIN

    IF (p_email IS NOT NULL AND p_password IS NOT NULL) THEN
        SELECT cliente.id_cliente into v_conta FROM cliente WHERE upper(email) = upper(p_email);
        SELECT conta.password into v_password FROM conta WHERE id_cliente = v_conta;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

    IF (v_password = p_password) THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20003, 'Email desconhecido');
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20001, 'Nenhum parametro inserido');
END;
/

create FUNCTION fncValidarLoginAdmin(p_email funcionario.email%type,
                                     p_password administrador.password%type) RETURN INTEGER
AS

    v_id       NUMBER;
    v_password VARCHAR2(255);

    excep_parametroInvalido EXCEPTION;

BEGIN
    -- verificar parametros
    IF (p_email IS NOT NULL AND p_password IS NOT NULL) THEN
        -- buscar id_funcionario
        SELECT funcionario.id_funcionario INTO v_id FROM funcionario WHERE UPPER(email) = UPPER(p_email);
        -- procurar a password do funcionario
        SELECT administrador.password INTO v_password FROM administrador WHERE id_funcionario = v_id;
        --se a password for igual:
        IF (v_password = p_password) THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20003, 'Email desconhecido');
        RETURN 0;
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20001, 'Nenhum parametro inserido');
        RETURN 0;

END;
/

create FUNCTION fncEnderecoExiste(p_rua localidade.designacao%type, p_latitude localidade.latitude%type,
                                  p_longitude localidade.longitude%type,
                                  p_altitude localidade.altitude%type) RETURN BOOLEAN
AS

    excep_parametrosInvalidos EXCEPTION;
    counter NUMBER;

BEGIN
    -- contar o numero de ocorrencias com o mesmo nome de rua, latitude e longitude
    SELECT COUNT(id_localidade)
    INTO counter
    FROM localidade
    WHERE UPPER(designacao) = UPPER(p_rua)
      AND latitude = p_latitude
      AND longitude = p_longitude
      AND altitude = p_altitude;

    -- verificar os parametros
    IF (p_rua IS NOT NULL AND p_latitude IS NOT NULL AND p_longitude IS NOT NULL AND p_altitude IS NOT NULL) THEN
        --verificar se a morada já existe 
        IF (counter = 1) THEN
            RETURN TRUE;
        ELSE
            RETURN FALSE;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'Morada desconhecida');
        RETURN FALSE;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
        RETURN FALSE;
END;
/

create FUNCTION fncGetIdEndereco(p_rua localidade.designacao%type, p_latitude localidade.latitude%type,
                                 p_longitude localidade.longitude%type,
                                 p_altitude localidade.altitude%type) RETURN NUMBER
AS


    v_id NUMBER;

    excep_parametrosInvalidos EXCEPTION;

BEGIN
    -- verificar os paramentros
    IF (p_rua IS NOT NULL AND p_latitude IS NOT NULL AND p_longitude IS NOT NULL AND p_latitude IS NOT NULL) THEN

        SELECT id_localidade
        INTO v_id
        FROM localidade
        WHERE UPPER(designacao) = UPPER(p_rua)
          AND latitude = p_latitude
          AND longitude = p_longitude
          AND altitude = p_altitude;

        RETURN v_id;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        --raise_application_error(-20001, 'Data not found');
        RETURN 0;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
        RETURN 0;

END;
/

create FUNCTION fncGetClientInfo RETURN sys_refcursor
AS

    v_infoCliente sys_refcursor;
BEGIN
    OPEN v_infoCliente FOR
        SELECT id_cliente,
               nome,
               NIF,
               email,
               cartaoCredito,
               designacao,
               latitude,
               longitude
        FROM (SELECT*FROM cliente ORDER BY 1 ASC) a
                 LEFT JOIN localidade ON a.id_endreco = id_localidade;
    RETURN v_infoCliente;
END;
/

create FUNCTION fncGetIdClienteByNif(p_nif cliente.NIF%type) RETURN cliente.ID_CLIENTE%type
AS
    idCliente cliente.ID_CLIENTE%type;

BEGIN

    SELECT ID_CLIENTE INTO idCliente FROM CLIENTE WHERE NIF = p_nif;

    RETURN idCliente;

END;
/

create FUNCTION fncRegistarEncomenda(p_nif cliente.nif%type) RETURN INTEGER
AS
    idEstadoEncomenda      INTEGER := 1; -- 1 corresponde a estado 'pendente'
    idEncomendaMaisRecente INTEGER;
    idCliente              INTEGER;

BEGIN

    idCliente := fncGetIdClienteByNif(p_nif);

    -- Criar encomenda
    INSERT INTO ENCOMENDA (DATA_ENCOMENDA, ID_ESTADOENCOMENDA)
    VALUES (sysdate, idEstadoEncomenda);

    -- Get id mais recente (encomenda criada anteriormente)
    SELECT MAX(ID_ENCOMENDA) INTO idEncomendaMaisRecente FROM ENCOMENDA;

    -- Criar relação encomenda/cliente
    INSERT INTO R_ENCOMENDA_CONTA (ID_ENCOMENDA, ID_CLIENTE)
    VALUES (idEncomendaMaisRecente, idCliente);

    RETURN idEncomendaMaisRecente;
END;
/

create FUNCTION fncGetFaturaByIdEncomenda(p_id encomenda.id_encomenda%TYPE) RETURN sys_refcursor
AS
    v_idFatura sys_refcursor;


BEGIN
    OPEN v_idFatura FOR
        SELECT fatura.id_fatura,
               fatura.numero,
               fatura.data,
               a.id_encomenda,
               fatura.valorFaturado,
               fatura.valorTransporte,
               fatura.valorDescontado
        FROM (SELECT id_encomenda FROM encomenda WHERE id_encomenda = p_id) a
                 LEFT JOIN fatura ON fatura.id_encomenda = a.id_encomenda;

    RETURN v_idFatura;
END;
/

create FUNCTION fncGetEstafetaById(p_id NUMBER) RETURN sys_refcursor
AS
    v_infoEstafeta sys_refcursor;


BEGIN
    OPEN v_infoEstafeta FOR
        SELECT nome, NIF, NISS, peso, email
        FROM (SELECT * FROM estafeta WHERE estafeta.id_funcionario = p_id) a
                 LEFT JOIN funcionario ON funcionario.id_funcionario = a.id_funcionario;
    RETURN v_infoEstafeta;
END;
/

create FUNCTION fncGetAllParqueInfo RETURN sys_refcursor
AS
    v_infoParque sys_refcursor;


BEGIN
    OPEN v_infoParque FOR
        SELECT id_parque, tipo_parque, numLugaresEstacionamentoNormal, numPostoCarregamento, designacao AS endereco
        FROM (SELECT id_parque,
                     designacao AS tipo_parque,
                     numLugaresEstacionamentoNormal,
                     numPostoCarregamento,
                     id_endereco
              FROM (SELECT * FROM parque) a
                       LEFT JOIN tipo_parque ON tipo_parque.id_tipoParque = a.id_tipoParque) b
                 LEFT JOIN localidade ON localidade.id_localidade = b.id_endereco;

    RETURN v_infoParque;
END;
/

create FUNCTION fncGetPesoEncomenda RETURN sys_refcursor
AS
    c_peso sys_refcursor;

BEGIN
    OPEN c_peso FOR
        SELECT id_encomenda, SUM(peso) AS peso
        FROM (SELECT *
              FROM (SELECT a.id_encomenda, id_estadoEncomenda, linha, id_artigo, quantidade
                    FROM (SELECT * FROM encomenda WHERE id_estadoEncomenda = 1) a
                             LEFT JOIN r_encomenda_artigo ON r_encomenda_artigo.id_encomenda = a.id_encomenda) b
                       LEFT JOIN artigo on artigo.id_artigo = b.id_artigo) c
        GROUP BY id_encomenda
        ORDER BY id_encomenda;

    RETURN c_peso;
END;
/

create FUNCTION fncGetEmailClienteByNif(p_nif cliente.NIF%type) RETURN cliente.email%type
AS

    excep_parametroInvalido EXCEPTION;
    emailCliente cliente.email%type;

BEGIN
    IF (p_nif IS NOT NULL) THEN
        SELECT EMAIL into emailCliente FROM CLIENTE WHERE NIF = p_nif;
        RETURN emailCliente;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'Artigo desconhecido');

    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parâmetro inválido');

END;
/

create FUNCTION fncGetIdsEncomendaByIdEntrega(p_idEntrega entrega.id_entrega%type) RETURN sys_refcursor
AS
    v_idsEncomenda sys_refcursor;

BEGIN
    OPEN v_idsEncomenda FOR
        select ID_ENCOMENDA
        from R_ESTAFETA_VEICULO_ENTREGA_ENCOMENDA
        where ID_ENTREGA = p_idEntrega;

    RETURN v_idsEncomenda;
END;
/

create FUNCTION fncGetVeiculoDisponivel(p_capacidade scooter.capacidade_atual%type) RETURN INTEGER -- obter um veiculo disponivel
AS
    v_veiculo INTEGER;

BEGIN

    SELECT id_veiculo AS id_veiculo
    INTO v_veiculo
    FROM (
             SELECT id_veiculo, capacidade_atual
             FROM (SELECT id_veiculo
                   FROM veiculo
                   WHERE id_farmacia = 1
                   MINUS
                   SELECT id_veiculo
                   FROM (SELECT a.id_veiculo, id_entrega
                         FROM (SELECT * FROM veiculo WHERE id_farmacia = 1) a
                                  INNER JOIN r_estafeta_veiculo_entrega_encomenda
                                             ON r_estafeta_veiculo_entrega_encomenda.id_veiculo = a.id_veiculo) b
                            LEFT JOIN entrega ON entrega.id_entrega = b.id_entrega
                   WHERE DataFim IS NULL) c
                      LEFT JOIN scooter ON scooter.id_scooter = c.id_veiculo
                  --WHERE capacidade_atual>=p_capacidade
             WHERE capacidade_atual >= p_capacidade) d

    WHERE ROWNUM = 1;

    RETURN v_veiculo;

END;
/

create FUNCTION fncGetEstafetaDisponivel RETURN INTEGER
AS
    v_estafeta INTEGER;

BEGIN
    SELECT id_funcionario INTO v_estafeta FROM estafeta WHERE id_tipoDisponibilidade = 1 AND ROWNUM = 1;
    RETURN v_estafeta;
END;
/

create FUNCTION fncgetUltimaReserva RETURN INTEGER
AS
    v_idEntrega INTEGER;

BEGIN
    SELECT * INTO v_idEntrega FROM (SELECT MAX(id_entrega) FROM entrega);
    RETURN v_idEntrega;
END;
/

create FUNCTION fncgetUltimaEntrega RETURN INTEGER
AS
    v_idEntrega INTEGER;

BEGIN
    SELECT * INTO v_idEntrega FROM (SELECT MAX(id_entrega) FROM entrega);
    RETURN v_idEntrega;
END;
/

create FUNCTION fncGetEncomendasPendentes RETURN sys_refcursor -- devolve um cursor com todas as encomendas em standby e o seu respetivo peso

AS
    c_encomendas sys_refcursor;

BEGIN
    OPEN c_encomendas FOR
        SELECT f.id_encomenda, peso, designacao AS Rua, latitude, longitude
        FROM (SELECT e.id_encomenda, peso, id_endreco
              FROM (
                       SELECT d.id_encomenda, peso, id_cliente
                       FROM (
                                SELECT id_encomenda, SUM(peso) AS peso
                                FROM (SELECT *
                                      FROM (SELECT a.id_encomenda, id_estadoEncomenda, linha, id_artigo, quantidade
                                            FROM (SELECT * FROM encomenda WHERE id_estadoEncomenda = 1) a
                                                     LEFT JOIN r_encomenda_artigo ON r_encomenda_artigo.id_encomenda = a.id_encomenda) b
                                               LEFT JOIN artigo on artigo.id_artigo = b.id_artigo) c
                                GROUP BY id_encomenda
                                ORDER BY id_encomenda) d
                                LEFT JOIN r_encomenda_conta ON r_encomenda_conta.id_encomenda = d.id_encomenda) e
                       LEFT JOIN cliente ON cliente.id_cliente = e.id_cliente) f
                 LEFT JOIN localidade ON localidade.id_localidade = f.id_endreco;

    RETURN c_encomendas;
END;
/

create FUNCTION fncCriarEntrega RETURN INTEGER
AS

    v_entrega INTEGER;

BEGIN
    INSERT INTO entrega (dataInicio) VALUES (SYSDATE);
    SELECT MAX(ID_ENTREGA) INTO v_entrega FROM ENTREGA;

    RETURN v_entrega;
END;
/

create FUNCTION fncGetEnderecoCliente RETURN sys_refcursor
AS
    v_enderecos sys_refcursor;


BEGIN
    OPEN v_enderecos FOR
        SELECT id_endreco, designacao AS rua, latitude, longitude
        FROM (SELECT id_endreco FROM cliente) a
                 LEFT JOIN localidade ON localidade.id_localidade = a.id_endreco;

    RETURN v_enderecos;
END;
/

create FUNCTION fncFarmaciaExiste(p_NIPC farmacia.NIPC%type) RETURN BOOLEAN
AS

    excep_parametrosInvalidos EXCEPTION;
    counter NUMBER;

BEGIN
    -- contar o numero de id's de farmacia com aquele numero
    SELECT COUNT(id_farmacia)
    INTO counter
    FROM farmacia
    WHERE NIPC = p_NIPC;

    --verificar os parametros
    IF (p_NIPC IS NOT NULL) THEN
        --verificar se a farmacia existe já na bddad
        IF (counter = 1) THEN
            RETURN TRUE;
        ELSE
            RETURN FALSE;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
        RETURN FALSE;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
        RETURN FALSE;
END;
/

create FUNCTION fncgetemailclientebyencomenda(
    p_idencomenda encomenda.id_encomenda%TYPE
) RETURN VARCHAR AS
    v_emailcliente INTEGER;
    excep_parametroinvalido EXCEPTION;
BEGIN
    IF (p_idencomenda IS NOT NULL) THEN
        SELECT c.email
        INTO v_emailcliente
        FROM cliente c
        WHERE c.id_cliente = (
            SELECT id_cliente
            FROM r_encomenda_conta r
            WHERE r.id_encomenda = p_idencomenda
        );

    ELSE
        RAISE excep_parametroinvalido;
    END IF;

    RETURN v_emailcliente;
EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'id de encomenda desconhecido');
        RETURN 0;
    WHEN excep_parametroinvalido THEN
        raise_application_error(-20003, 'id de encomenda desconhecido');
        RETURN 0;
END;
/

create FUNCTION fncGetDroneById(p_id drone.ID_DRONE%TYPE) RETURN sys_refcursor
AS
    infoDrone sys_refcursor;

BEGIN
    OPEN infoDrone FOR
        SELECT c.NUMREGISTO, modelo, c.capacidade AS capacidade_bateria, c.capacidade_atual, c.EFICIENCIA
        FROM (
                 SELECT b.NUMREGISTO, b.modelo, capacidade, b.id_estado, b.capacidade_atual, b.peso, EFICIENCIA
                 FROM (
                          SELECT a.NUMREGISTO,
                                 DESIGNACAO AS modelo,
                                 a.ID_TIPOBATERIA,
                                 a.ID_ESTADO,
                                 CAPACIDADE_ATUAL,
                                 PESO
                          FROM (SELECT * FROM DRONE WHERE ID_DRONE = p_id) a -- tabela drone
                                   LEFT JOIN modelo_drone ON a.ID_MODELO = modelo_drone.ID_MODELODRONE) b
                          LEFT JOIN tipo_bateria ON b.id_tipoBateria = tipo_bateria.id_tipoBateria) c
                 LEFT JOIN estado_veiculo ON c.id_estado = estado_veiculo.id_estadoVeiculo;
    RETURN infoDrone;
END;
/

create FUNCTION fncGetDroneByNrRegisto(p_nrRegisto drone.NUMREGISTO%TYPE) RETURN sys_refcursor
AS
    infoDrone sys_refcursor;

BEGIN
    OPEN infoDrone FOR
        SELECT c.ID_DRONE, c.NUMREGISTO, modelo, c.capacidade AS capacidade_bateria, c.capacidade_atual, c.EFICIENCIA
        FROM (
                 SELECT b.ID_DRONE,
                        b.NUMREGISTO,
                        b.modelo,
                        capacidade,
                        b.id_estado,
                        b.capacidade_atual,
                        b.peso,
                        EFICIENCIA
                 FROM (
                          SELECT ID_DRONE,
                                 a.NUMREGISTO,
                                 DESIGNACAO AS modelo,
                                 a.ID_TIPOBATERIA,
                                 a.ID_ESTADO,
                                 CAPACIDADE_ATUAL,
                                 PESO
                          FROM (SELECT * FROM DRONE WHERE NUMREGISTO = p_nrRegisto) a -- tabela drone
                                   LEFT JOIN modelo_drone ON a.ID_MODELO = modelo_drone.ID_MODELODRONE) b
                          LEFT JOIN tipo_bateria ON b.id_tipoBateria = tipo_bateria.id_tipoBateria) c
                 LEFT JOIN estado_veiculo ON c.id_estado = estado_veiculo.id_estadoVeiculo;
    RETURN infoDrone;
END;
/

create FUNCTION fncParqueExiste(p_idParque parque.id_parque%type) RETURN BOOLEAN
AS

    excep_parametrosInvalidos EXCEPTION;
    counter NUMBER;

BEGIN
    -- contar o numero de id's de parque com aquele numero
    SELECT COUNT(id_parque)
    INTO counter
    FROM parque
    WHERE id_parque = p_idParque;

    --verificar os parametros
    IF (p_idParque IS NOT NULL) THEN
        --verificar se o parque existe já na bddad
        IF (counter = 1) THEN
            RETURN TRUE;
        ELSE
            RETURN FALSE;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
        RETURN FALSE;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
        RETURN FALSE;
END;
/

create FUNCTION fncNumLugaresNoParqueScooterPorFarmacia(p_farmacia farmacia.id_farmacia%type) RETURN NUMBER
AS

    v_tipoVeiculo            NUMBER := 1 ; -- tipo de veiculo Scooter
    v_tipoParque             NUMBER := 1; -- tipo de parque Scooter
    v_numTotalEstacionamento NUMBER;
    v_numVeiculoPorFarmacia  NUMBER;

    excep_parametrosInvalidos EXCEPTION;

BEGIN
    --verificar os parametros
    IF (p_farmacia IS NOT NULL) THEN

        -- contar o numero de estacionamento para determinada farmacia e de tipo Scooter
        SELECT SUM(TotalnumlugaresEstacionamentoNormal + TotalnumPostoCarregamento) AS numEstacionamentoPorFarmacia
        INTO v_numTotalEstacionamento
        FROM (SELECT SUM(numlugaresEstacionamentoNormal) AS TotalnumlugaresEstacionamentoNormal,
                     SUM(numPostoCarregamento)           AS TotalnumPostoCarregamento
              FROM (SELECT a.id_farmacia,
                           parque.id_parque,
                           parque.id_tipoparque,
                           parque.numlugaresEstacionamentoNormal,
                           parque.numpostoCarregamento
                    FROM (SELECT * FROM Farmacia_parque) a
                             LEFT JOIN parque ON parque.id_parque = a.id_parque) b
              WHERE id_farmacia = p_farmacia
                AND id_tipoParque = v_tipoParque) c;

        RETURN v_numTotalEstacionamento;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
        RETURN 0;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
        RETURN 0;
END;
/

create FUNCTION fncgetcreditoscliente(
    p_idcliente conta.id_cliente%TYPE
) RETURN NUMBER AS
    v_numcreditos NUMBER; -- numero creditos
    excep_parametrosinvalidos EXCEPTION;
BEGIN
    --verificar os parametros
    IF (p_idcliente IS NOT NULL) THEN

        -- adicionar a v_numCreditos o numero de creditos do cliente com o id inserido por parametro e retornar esse valor
        SELECT c.credito
        INTO v_numcreditos
        FROM conta c
        WHERE c.id_cliente = p_idcliente;

        RETURN v_numcreditos;
    ELSE
        RAISE excep_parametrosinvalidos;
    END IF;
EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'No data Found');
        RETURN 0;
    WHEN excep_parametrosinvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
        RETURN 0;
END;
/

create FUNCTION fncGetAllFarmacia RETURN sys_refcursor
AS
    v_infoFarmacia sys_refcursor;


BEGIN
    OPEN v_infoFarmacia FOR
        SELECT * FROM farmacia;

    RETURN v_infoFarmacia;
END;
/

create FUNCTION fncRegistarFarmacia(p_designacao farmacia.designacao%type,
                                    p_NIPC farmacia.NIPC%type,
                                    p_rua localidade.designacao%type,
                                    p_latitude localidade.latitude%type,
                                    p_longitude localidade.longitude%type,
                                    p_altitude localidade.altitude%type)
    RETURN NUMBER
AS
    idEndereco     localidade.id_localidade%type;
    farmaciaExiste BOOLEAN;
    moradaExiste   BOOLEAN;
    v_idFarmacia   NUMBER;

    excep_parametrosInvalidos EXCEPTION;
    excep_farmaciaExistenteEmSistema EXCEPTION;

BEGIN

    --validar os parametros
    IF (p_designacao IS NOT NULL AND p_NIPC IS NOT NULL AND p_rua IS NOT NULL AND p_latitude IS NOT NULL AND
        p_longitude IS NOT NULL AND p_altitude IS NOT NULL) THEN

        -- verificar se farmacia ja existe em sistema
        farmaciaExiste := fncFarmaciaExiste(p_NIPC);
        -- se existe:
        IF (farmaciaExiste) THEN
            RAISE excep_farmaciaExistenteEmSistema;
        ELSE
            -- verificar se a morada existe na BDDAD
            moradaExiste := fncEnderecoExiste(p_rua, p_latitude, p_longitude, p_altitude);

            -- se existir, devolver o id
            IF (moradaExiste) THEN
                SELECT id_localidade
                INTO idEndereco
                FROM localidade
                WHERE UPPER(designacao) = UPPER(p_rua)
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

            SELECT MAX(id_farmacia) INTO v_idFarmacia FROM farmacia;

        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

    RETURN v_idFarmacia;

EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
        RETURN 0;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
        RETURN 0;
    WHEN excep_farmaciaExistenteEmSistema THEN
        raise_application_error(-20004, 'Farmacia registada em sistema');
        RETURN 0;
END;
/

create FUNCTION fncFarmaciaExisteById(p_idFarmacia farmacia.id_farmacia%type) RETURN BOOLEAN
AS

    excep_parametrosInvalidos EXCEPTION;
    counter NUMBER;

BEGIN
    -- contar o numero de id's de farmacia com aquele numero
    SELECT COUNT(id_farmacia)
    INTO counter
    FROM farmacia
    WHERE id_farmacia = p_idFarmacia;

    --verificar os parametros
    IF (p_idFarmacia IS NOT NULL) THEN
        --verificar se a farmacia existe já na bddad
        IF (counter = 1) THEN
            RETURN TRUE;
        ELSE
            RETURN FALSE;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
        RETURN FALSE;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
        RETURN FALSE;
END;
/

create FUNCTION fncListaProdutosFarmacia(p_idFarmacia farmacia.id_farmacia%TYPE) RETURN sys_refcursor
AS
    infoArtigosPorFarmacia sys_refcursor;
    v_existe               BOOLEAN;

    excep_parametroInvalido EXCEPTION;
    excep_farmaciaNaoExiste EXCEPTION;

BEGIN

    IF (p_idFarmacia IS NOT NULL) THEN

        -- verificar se farmacia existe
        v_existe := fncFarmaciaExisteById(p_idFarmacia);
        -- se existir:
        IF (v_existe) THEN
            OPEN infoArtigosPorFarmacia FOR
                SELECT a.id_artigo, artigo.designacao, artigo.precoUnitario, artigo.iva, artigo.peso
                FROM (
                         SELECT *
                         FROM r_artigo_farmacia
                         WHERE id_farmacia = p_idFarmacia) a
                         LEFT JOIN artigo ON a.id_artigo = artigo.id_artigo;

            RETURN infoArtigosPorFarmacia;
        ELSE
            RAISE excep_farmaciaNaoExiste;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
    WHEN excep_farmaciaNaoExiste THEN
        raise_application_error(-20003, 'Farmacia inexistente em sistema');
END;
/

create FUNCTION fncRegistarParque(p_idFarmacia farmacia.designacao%type,
                                  p_idTipoParque parque.id_tipoParque%type,
                                  p_numLugaresEstacionamentoNormal parque.numLugaresEstacionamentoNormal%type,
                                  p_numPostoCarregamento parque.numPostoCarregamento%type,
                                  p_rua localidade.designacao%type,
                                  p_latitude localidade.latitude%type,
                                  p_longitude localidade.longitude%type,
                                  p_altitude localidade.altitude%type,
                                  p_capacidadeTensao parque.capacidadeTensao%type)
    RETURN NUMBER
AS

    moradaExiste                          BOOLEAN;
    v_farmaciaExiste                      BOOLEAN;
    idEndereco                            NUMBER;
    v_idParque                            NUMBER;
    v_tipoEstacionamentoNormal            NUMBER := 1; -- Estacionamento normal
    v_tipoEstacionamentoPostoCarregamento NUMBER := 2; -- Estacionamento com posto de carregamento    
    v_outputEnergetico                    NUMBER := 100;
    v_lugarEstacionamento                 NUMBER;

    excep_parametrosInvalidos EXCEPTION;
    excep_farmaciaInexistente EXCEPTION;
    counterNormal            NUMBER;
    counterCarregamento      NUMBER;
    counterCarregamentoPosto NUMBER;

BEGIN
    -- verificar parametros
    IF (p_idFarmacia IS NOT NULL AND p_idTipoParque IS NOT NULL AND p_numLugaresEstacionamentoNormal IS NOT NULL AND
        p_numPostoCarregamento IS NOT NULL AND
        p_rua IS NOT NULL AND p_latitude IS NOT NULL AND p_longitude IS NOT NULL AND p_altitude IS NOT NULL AND
        p_capacidadeTensao IS NOT NULL) THEN
        -- verificar se farmacia existe
        v_farmaciaExiste := fncFarmaciaExisteById(p_idFarmacia);
        -- se existe:
        IF (v_farmaciaExiste) THEN
            -- ENDERECO
            -- verificar se a morada existe na BDDAD
            moradaExiste := fncEnderecoExiste(p_rua, p_latitude, p_longitude, p_altitude);

            -- se existir, devolver o id
            IF (moradaExiste) THEN
                SELECT id_localidade
                INTO idEndereco
                FROM localidade
                WHERE UPPER(designacao) = UPPER(p_rua)
                  AND latitude = p_latitude
                  AND longitude = p_longitude
                  AND altitude = p_altitude;
            ELSE
                -- se nao existir, criar e colocar o ID na variavel id_endereco
                INSERT INTO localidade(designacao, latitude, longitude, altitude)
                VALUES (p_rua, p_latitude, p_longitude, p_altitude);

                SELECT MAX(id_localidade) INTO idEndereco FROM localidade;
            END IF;

            --PARQUE
            -- criar o parque
            INSERT INTO parque(id_tipoParque, numLugaresEstacionamentoNormal, numPostoCarregamento, id_endereco,
                               capacidadeTensao)
            VALUES (p_idTipoParque, p_numLugaresEstacionamentoNormal, p_numPostoCarregamento, idEndereco,
                    p_capacidadeTensao);

            SELECT MAX(id_parque) INTO v_idParque FROM parque;

            -- FARMACIA_PARQUE
            INSERT INTO farmacia_parque (id_farmacia, id_parque)
            VALUES (p_idFarmacia, v_idParque);

            -- LUGAR_ESTACIONAMENTO

            counterNormal := p_numLugaresEstacionamentoNormal;
            counterCarregamento := p_numPostoCarregamento;
            counterCarregamentoPosto := p_numPostoCarregamento;

            -- criar lugares de estacionamento Normais
            LOOP
                counterNormal := counterNormal - 1;
                IF counterNormal >= 0 THEN
                    INSERT INTO lugar_estacionamento (id_tipoEstacionamento, id_parque)
                    VALUES (v_tipoEstacionamentoNormal, v_idParque);
                ELSE
                    EXIT;
                END IF;
            END LOOP;

            -- criar lugares de estacionamento com carregamento
            LOOP
                counterCarregamento := counterCarregamento - 1;
                IF counterCarregamento >= 0 THEN

                    INSERT INTO lugar_estacionamento (id_tipoEstacionamento, id_parque)
                    VALUES (v_tipoEstacionamentoPostoCarregamento, v_idParque);
                    SELECT MAX(id_lugarEstacionamento) INTO v_lugarEstacionamento FROM lugar_estacionamento;
                    -- criar entradas na tabela SpotCarregamento
                    INSERT INTO spotCarregamento (id_lugarEstacionamento, output_energetico)
                    VALUES (v_lugarEstacionamento, v_outputEnergetico);
                ELSE
                    EXIT;
                END IF;
            END LOOP;


        ELSE
            RAISE excep_farmaciaInexistente;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

    RETURN v_idParque;


EXCEPTION

    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
    WHEN excep_farmaciaInexistente THEN
        raise_application_error(-20003, 'Farmacia inexistente em sistema');

END;
/

create FUNCTION fncGetIdEnderecoByFarmacia(p_idFarmacia farmacia.id_farmacia%type)
    RETURN NUMBER
AS
    farmaciaExiste BOOLEAN;
    v_idFarmacia   NUMBER;
    v_idEndereco   NUMBER;

    excep_parametrosInvalidos EXCEPTION;
    excep_farmaciaNaoExistenteEmSistema EXCEPTION;


BEGIN
    --validar o parametro
    IF (p_idFarmacia IS NOT NULL) THEN

        -- verificar se farmacia ja existe em sistema
        farmaciaExiste := fncFarmaciaExisteById(p_idFarmacia);
        -- se existir
        IF (farmaciaExiste) THEN
            SELECT id_endereco INTO v_idEndereco FROM farmacia WHERE id_farmacia = p_idFarmacia;
        ELSE
            RAISE excep_farmaciaNaoExistenteEmSistema;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

    RETURN v_idEndereco;

EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
        RETURN 0;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
        RETURN 0;
    WHEN excep_farmaciaNaoExistenteEmSistema THEN
        raise_application_error(-20004, 'id de farmacia inexistente em sistema - Farmacia não registada em sistema');
        RETURN 0;
END;
/

create FUNCTION fncExisteParque(p_farmacia farmacia.id_farmacia%type) RETURN BOOLEAN
AS

    v_existeParque NUMBER;
    excep_parametrosInvalidos EXCEPTION;

BEGIN
    --verificar os parametros
    IF (p_farmacia IS NOT NULL) THEN
        --conta o numero de id de parque associado a uma determinada farmacia
        SELECT COUNT(id_farmacia) INTO v_existeParque FROM farmacia_parque WHERE id_farmacia = p_farmacia;

        IF (v_existeParque > 0) THEN
            RETURN TRUE;
        ELSE
            RETURN FALSE;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
        RETURN FALSE;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
        RETURN FALSE;
END;
/

create FUNCTION fncScooterExiste(p_matricula scooter.MATRICULA%type) RETURN BOOLEAN
AS

    v_existe NUMBER;

    excep_parametrosInvalidos EXCEPTION;

BEGIN
    --verificar os parametros
    IF (p_matricula IS NOT NULL) THEN

        --conta o numero de id de scooter com a matricula que entra por parametro
        SELECT COUNT(id_scooter) INTO v_existe FROM Scooter WHERE UPPER(matricula) = UPPER(p_matricula);
        -- se ja existir
        IF (v_existe > 0) THEN
            RETURN TRUE;
        ELSE
            RETURN FALSE;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
        RETURN FALSE;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
        RETURN FALSE;
END;
/

create FUNCTION fncGetScooterByMatricula(p_matricula scooter.MATRICULA%TYPE) RETURN sys_refcursor
AS
    infoScooter sys_refcursor;
    v_Existe    BOOLEAN;

    excep_scooterNaoExiste EXCEPTION;
    excep_parametroInvalido EXCEPTION;

BEGIN

    IF (p_matricula IS NOT NULL) THEN
        v_Existe := fncScooterExiste(p_matricula);
        IF (v_Existe) THEN

            OPEN infoScooter FOR
                SELECT c.ID_SCOOTER,
                       c.matricula,
                       modelo,
                       c.ID_TIPOBATERIA,
                       c.capacidade,
                       c.ID_ESTADO,
                       c.capacidade_atual as bateria_atual,
                       c.EFICIENCIA
                FROM (
                         SELECT b.ID_SCOOTER,
                                b.matricula,
                                b.modelo,
                                b.ID_TIPOBATERIA,
                                capacidade,
                                b.id_estado,
                                b.capacidade_atual,
                                b.peso,
                                EFICIENCIA
                         FROM (
                                  SELECT a.ID_SCOOTER,
                                         a.matricula,
                                         designacao AS modelo,
                                         a.id_tipobateria,
                                         a.id_estado,
                                         capacidade_atual,
                                         peso
                                  FROM (SELECT * FROM SCOOTER WHERE upper(MATRICULA) = upper(p_matricula)) a -- tabela scooter
                                           LEFT JOIN modelo_scooter ON a.id_modelo = modelo_scooter.id_modeloScooter) b
                                  LEFT JOIN tipo_bateria ON b.id_tipoBateria = tipo_bateria.id_tipoBateria) c
                         LEFT JOIN estado_veiculo ON c.id_estado = estado_veiculo.id_estadoVeiculo;
            RETURN infoScooter;
        ELSE
            RAISE excep_scooterNaoExiste;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
    WHEN excep_scooterNaoExiste THEN
        raise_application_error(-20003, 'Scooter inexistente em sistema');

END;
/

create FUNCTION fncGetEnergiaDebitadaByLugarEstacionamente(p_idLugarEstacionamento lugar_estacionamento.id_lugarEstacionamento%TYPE) RETURN NUMBER
AS
    v_energiaDebitada                 NUMBER;
    v_tipoEstacionamentoComCarregador INTEGER := 2; -- tipo de estacionamento com carregador
    v_outputEnergetico                NUMBER;
    v_idValido                        NUMBER;

    excep_parametroInvalido EXCEPTION;
    excep_EstacionamentoSemCarregador EXCEPTION;

BEGIN
    -- verifica os parametros
    IF (p_idLugarEstacionamento IS NOT NULL) THEN

        --verificar se o estacionamento tem carregador
        SELECT COUNT(id_lugarEstacionamento)
        INTO v_idValido
        FROM lugar_estacionamento
        WHERE id_lugarEstacionamento = p_idLugarEstacionamento
          AND id_tipoEstacionamento = v_tipoEstacionamentoComCarregador;
        -- se existe:
        IF (v_idValido > 0) THEN
            SELECT spotcarregamento.output_energetico
            INTO v_outputEnergetico
            FROM (SELECT * FROM lugar_estacionamento WHERE id_tipoestacionamento = v_tipoEstacionamentoComCarregador) a
                     LEFT JOIN spotCarregamento ON spotCarregamento.id_lugarEstacionamento = a.id_lugarEstacionamento
            WHERE a.id_lugarEstacionamento = p_idLugarEstacionamento;

            RETURN v_outputEnergetico;
        ELSE
            RAISE excep_EstacionamentoSemCarregador;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'Morada desconhecida');
        RETURN 0;
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
        RETURN 0;
    WHEN excep_EstacionamentoSemCarregador THEN
        raise_application_error(-20003, 'Estacionamento sem posto de carregamento');
        RETURN 0;
END;
/

create FUNCTION fncGetAllEstacionamentoComCarregador(p_idParque parque.id_parque%type) RETURN sys_refcursor
AS
    v_existe             BOOLEAN;
    v_idEstacionamento   sys_refcursor;
    v_tipoEstacionamento NUMBER := 2; -- tipo de estacionamento com carregador

    excep_parqueNaoExiste EXCEPTION;
    excep_parametreoIvalido EXCEPTION;

BEGIN

    -- verificar os parametros
    IF (p_idParque IS NOT NULL) THEN
        -- verifica se o parque existe
        v_existe := fncParqueExiste(p_idParque);
        -- se existe:
        IF (v_existe) THEN

            OPEN v_idEstacionamento FOR
                SELECT id_lugarEstacionamento
                FROM lugar_estacionamento
                WHERE id_tipoEstacionamento = v_tipoEstacionamento
                  AND id_parque = p_idParque;
            RETURN v_idEstacionamento;

        ELSE
            RAISE excep_parqueNaoExiste;
        END IF;
    ELSE
        RAISE excep_parametreoIvalido;
    END IF;


EXCEPTION

    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
    WHEN excep_parametreoIvalido THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
    WHEN excep_parqueNaoExiste THEN
        raise_application_error(-20003, 'Parque inexistente em sistema');

END;
/

create FUNCTION fncGetEnderecoFarmacia RETURN sys_refcursor
AS
    v_EnderecoFarmacia sys_refcursor;


BEGIN
    OPEN v_EnderecoFarmacia FOR
        SELECT a.id_farmacia AS id_entidade, a.id_endereco, designacao, latitude, longitude, altitude
        FROM (SELECT id_farmacia, id_endereco FROM farmacia) a
                 LEFT JOIN localidade ON localidade.id_localidade = a.id_endereco;

    RETURN v_EnderecoFarmacia;
END;
/

create FUNCTION fncGetEnderecoParque RETURN sys_refcursor
AS
    v_EnderecoParque sys_refcursor;


BEGIN
    OPEN v_EnderecoParque FOR
        SELECT a.id_parque AS id_entidade, a.id_endereco, designacao, latitude, longitude, altitude
        FROM (SELECT id_parque, id_endereco FROM parque) a
                 LEFT JOIN localidade ON localidade.id_localidade = a.id_endereco;

    RETURN v_EnderecoParque;

END;
/

create FUNCTION fncGetEnderecoClientes
    RETURN sys_refcursor
AS
    v_EnderecoCliente sys_refcursor;

BEGIN
    OPEN v_EnderecoCliente FOR
        SELECT a.id_cliente AS id_entidade, a.id_endreco, designacao, latitude, longitude, altitude
        FROM (SELECT id_cliente, id_endreco FROM cliente) a
                 LEFT JOIN localidade ON localidade.id_localidade = a.id_endreco;

    RETURN v_EnderecoCliente;

END;
/

create FUNCTION fncArtigoExisteById(p_idArtigo artigo.id_artigo%type) RETURN BOOLEAN
AS

    counter NUMBER;

    excep_parametrosInvalidos EXCEPTION;

BEGIN
    -- contar o numero de id's de artigos com aquele numero
    SELECT COUNT(id_artigo)
    INTO counter
    FROM Artigo
    WHERE id_artigo = p_idArtigo;

    --verificar os parametros
    IF (p_idArtigo IS NOT NULL) THEN
        --verificar se o artigo existe já na bddad
        IF (counter = 1) THEN
            RETURN TRUE;
        ELSE
            RETURN FALSE;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
        RETURN FALSE;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
        RETURN FALSE;
END;
/

create FUNCTION fncGetStock(p_idArtigo artigo.id_artigo%type, p_idFarmacia farmacia.id_farmacia%TYPE) RETURN NUMBER
AS
    v_stock          NUMBER;
    v_artigoExiste   BOOLEAN;
    v_farmaciaExiste BOOLEAN;
    excep_parametroInvalido EXCEPTION;
    excep_farmaciaNaoExiste EXCEPTION;
    excep_artigoNaoExiste EXCEPTION;

BEGIN
    -- verificar parametros
    IF (p_idArtigo IS NOT NULL AND p_idFarmacia IS NOT NULL) THEN

        --verificar se existe id de artigo
        v_artigoExiste := fncArtigoExisteById(p_idArtigo);
        -- se existir;
        IF (v_artigoExiste) THEN

            -- verificar se id_farmacia existe
            v_farmaciaExiste := fncFarmaciaExisteById(p_idFarmacia);
            -- se existe:
            IF (v_farmaciaExiste) THEN
                SELECT r_artigo_farmacia.stock
                into v_stock
                FROM r_artigo_farmacia
                WHERE id_artigo = p_idArtigo
                  AND id_farmacia = p_idFarmacia;
                RETURN v_stock;
            ELSE
                RAISE excep_farmaciaNaoExiste;
            END IF;
        ELSE
            RAISE excep_artigoNaoExiste;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'Artigo desconhecido');
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parâmetro inválido');
    WHEN excep_artigoNaoExiste THEN
        raise_application_error(-20003, 'Artigo inexistente em sistema');
    WHEN excep_farmaciaNaoExiste THEN
        raise_application_error(-20003, 'Farmacia inexistente em sistema');

END;
/

create FUNCTION fncGetArtigoById(p_idArtigo artigo.id_artigo%TYPE) RETURN sys_refcursor
AS
    v_infoArtigo sys_refcursor;
    v_existe     BOOLEAN;

    excep_artigoDesconhecido EXCEPTION;
    excep_parametroInvalido EXCEPTION;

BEGIN

    -- verificar parametro
    IF (p_idArtigo IS NOT NULL) THEN

        -- verificar se id de produto existe
        v_existe := fncArtigoExisteById(p_idArtigo);

        -- se existir:
        IF (v_existe) THEN
            OPEN v_infoArtigo FOR
                SELECT id_artigo, designacao, precoUnitario, iva, peso FROM Artigo WHERE id_artigo = p_idArtigo;
            RETURN v_infoArtigo;
            -- caso contrário:
        ELSE
            RAISE excep_artigoDesconhecido;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
    WHEN excep_artigoDesconhecido THEN
        raise_application_error(-20003, 'Artigo desconhecido em sistema');
END;
/

create FUNCTION fncTransferenciaDeProdutos(p_idFarmaciaRemetente farmacia.id_farmacia%type,
                                           p_idFarmaciaDestinatario farmacia.id_farmacia%type,
                                           p_idArtigo artigo.id_artigo%type,
                                           p_stock r_artigo_farmacia.stock%type)
    RETURN BOOLEAN
AS

    v_stockRemetente                 NUMBER;
    v_stockDestinatario              NUMBER;
    v_controleStock                  NUMBER;
    v_StockFinalFarmaciaRemetente    NUMBER := 0;
    v_StockFinalFarmaciaDestinataria NUMBER := 0;
    v_farmaciaRemetenteExiste        BOOLEAN;
    v_farmaciaDestinatariaExiste     BOOLEAN;
    v_ArtigoExiste                   BOOLEAN;

    excep_parametrosInvalidos EXCEPTION;
    excep_NaoHaProdutoNaFarmacia EXCEPTION;
    excep_NaoHaStockDoProdutoNaFarmacia EXCEPTION;
    excep_farmaciaOuArtigoDesconhecidoEmSistema EXCEPTION;

BEGIN

    -- verificar a validade dos parametros
    IF (p_idFarmaciaRemetente IS NOT NULL AND p_idFarmaciaDestinatario IS NOT NULL AND p_idArtigo IS NOT NULL AND
        p_Stock IS NOT NULL) THEN

        v_farmaciaRemetenteExiste := fncFarmaciaExisteById(p_idFarmaciaRemetente);
        v_farmaciaDestinatariaExiste := fncFarmaciaExisteById(p_idFarmaciaDestinatario);
        v_ArtigoExiste := fncArtigoExisteById(p_idArtigo);

        IF (v_farmaciaRemetenteExiste AND v_farmaciaDestinatariaExiste AND v_ArtigoExiste) THEN

            -- ver o stock de um determinado artigo na farmacia remetente
            SELECT COUNT(stock)
            INTO v_stockRemetente
            FROM r_artigo_farmacia
            WHERE id_artigo = p_idArtigo
              AND id_farmacia = p_idFarmaciaRemetente;

            -- se existe artigo naquela farmacia:
            IF (v_stockRemetente > 0) THEN

                SELECT stock
                INTO v_stockRemetente
                FROM r_artigo_farmacia
                WHERE id_artigo = p_idArtigo
                  AND id_farmacia = p_idFarmaciaRemetente;

                --fazer o controlo de stock, ie, verificar se existe stock suficiente para poder transferir uma determinada quantidade de artigos
                v_StockFinalFarmaciaRemetente := v_stockRemetente - p_stock;

                -- Se houver:
                IF (v_StockFinalFarmaciaRemetente >= 0) THEN

                    -- atualizar o stock da farmacia remetente
                    UPDATE r_artigo_farmacia
                    SET stock = v_StockFinalFarmaciaRemetente
                    WHERE id_artigo = p_idArtigo
                      AND id_farmacia = p_idFarmaciaRemetente;

                    -- ver o stock de um determinado artigo na farmacia destinataria
                    SELECT COUNT(stock)
                    INTO v_stockDestinatario
                    FROM r_artigo_farmacia
                    WHERE id_artigo = p_idArtigo
                      AND id_farmacia = p_idFarmaciaDestinatario;

                    -- Se houver:
                    IF (v_stockDestinatario > 0) THEN

                        SELECT stock
                        INTO v_stockDestinatario
                        FROM r_artigo_farmacia
                        WHERE id_artigo = p_idArtigo
                          AND id_farmacia = p_idFarmaciaDestinatario;

                        -- atualizar o stock da farmacia destinataria
                        v_StockFinalFarmaciaDestinataria := v_stockDestinatario + p_stock;

                        UPDATE r_artigo_farmacia
                        SET stock = v_StockFinalFarmaciaDestinataria
                        WHERE id_artigo = p_idArtigo
                          AND id_farmacia = p_idFarmaciaDestinatario;

                        RETURN TRUE;
                        -- se nao houver, criar linha na tabela r_artigo_farmacia        
                    ELSE
                        INSERT INTO r_artigo_farmacia(id_artigo, id_farmacia, stock)
                        VALUES (p_idArtigo, p_idFarmaciaDestinatario, p_stock);

                        RETURN TRUE;
                    END IF;
                ELSE
                    RAISE excep_NaoHaStockDoProdutoNaFarmacia;
                END IF;
            ELSE
                RAISE excep_NaoHaProdutoNaFarmacia;
            END IF;
        ELSE
            RAISE excep_farmaciaOuArtigoDesconhecidoEmSistema;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;


EXCEPTION

    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data found');
        RETURN FALSE;
    WHEN excep_NaoHaProdutoNaFarmacia THEN
        raise_application_error(-20001, 'Não existe o artigo pretendido na farmacia');
        RETURN FALSE;
    WHEN excep_NaoHaStockDoProdutoNaFarmacia THEN
        raise_application_error(-20001,
                                'Não existe stock suficiente na farmacia remetente para poder ser feita a transferência');
        RETURN FALSE;
    WHEN excep_farmaciaOuArtigoDesconhecidoEmSistema THEN
        raise_application_error(-20001, 'Farmacias ou artigo desconhecido em sistema');
        RETURN FALSE;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20001, 'Parametros inseridos inválidos');
        RETURN FALSE;
END;
/

create FUNCTION fncGetIdParque(p_farmacia farmacia.id_farmacia%type) RETURN NUMBER
AS

    v_idParque          NUMBER;
    v_tipoParqueScooter NUMBER := 1; -- tipo de parque Scooter
    excep_parametrosInvalidos EXCEPTION;

BEGIN
    --verificar os parametros
    IF (p_farmacia IS NOT NULL) THEN

        --conta o numero de id de parque de scooter associado a uma determinada farmacia 
        SELECT COUNT(a.id_parque)
        INTO v_idParque
        FROM (SELECT * FROM farmacia_parque WHERE id_farmacia = p_farmacia) a
                 LEFT JOIN parque ON parque.id_parque = a.id_parque
        WHERE id_tipoParque = v_tipoParqueScooter;

        IF (v_idParque > 0) THEN

            SELECT (a.id_parque)
            INTO v_idParque
            FROM (SELECT * FROM farmacia_parque WHERE id_farmacia = p_farmacia) a
                     LEFT JOIN parque ON parque.id_parque = a.id_parque
            WHERE id_tipoParque = v_tipoParqueScooter
              AND ROWNUM = 1;


            RETURN v_idParque;
        ELSE
            RETURN 0;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
        RETURN 0;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
        RETURN 0;
END;
/

create FUNCTION fncGetIdLugaresDoParque(p_idParque PARQUE.ID_PARQUE%type) RETURN sys_refcursor
AS

    c_idLugaresParque sys_refcursor;
    --v_tipoVeiculo NUMBER :=1 ;   -- tipo de veiculo Scooter
    --v_tipoParque NUMBER :=1;     -- tipo de parque Scooter
    --v_numTotalEstacionamento NUMBER;
    --v_numVeiculoPorFarmacia NUMBER;

    excep_parametrosInvalidos EXCEPTION;

BEGIN
    --verificar os parametros
    IF (p_idParque IS NOT NULL) THEN

        -- colocar no cursor os id's dos lugares de estacionamento
        OPEN c_idLugaresParque FOR
            SELECT id_lugarEstacionamento FROM Lugar_estacionamento WHERE id_parque = p_idParque;
        RETURN c_idLugaresParque;

    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
END;
/

create FUNCTION fncGetNumLugaresOcupados(p_idParque PARQUE.ID_PARQUE%type) RETURN NUMBER
AS
    v_numLugaresOcupados NUMBER;

    excep_parametrosInvalidos EXCEPTION;

BEGIN
    --verificar os parametros
    IF (p_idParque IS NOT NULL) THEN
        -- contar numero de lugares ocupados
        SELECT COUNT(a.id_lugarEstacionamento)
        INTO v_numLugaresOcupados
        FROM (SELECT id_lugarEstacionamento FROM Lugar_estacionamento WHERE id_parque = p_idParque) a
                 LEFT JOIN r_veiculo_LugarEstacionamento
                           ON r_veiculo_LugarEstacionamento.id_lugarEstacionamento = a.id_lugarEstacionamento
        WHERE data_inicio IS NOT NULL
          AND data_fim IS NULL;

        RETURN v_numLugaresOcupados;

    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
END;
/

create FUNCTION fncFuncionarioEstafetaExiste(p_idfuncionario funcionario.id_funcionario%TYPE) RETURN BOOLEAN
AS

    v_idTipoFuncionario NUMBER;

    excep_parametroInvalido EXCEPTION;

BEGIN
    -- verificar o parametro
    IF (p_idfuncionario IS NOT NULL) THEN
        -- verificar o numero
        SELECT COUNT(id_funcionario)
        INTO v_idTipoFuncionario
        FROM FUNCIONARIO
        WHERE id_funcionario = p_idfuncionario
          AND id_tipoFuncionario =
              (SELECT id_tipoFuncionario FROM tipo_funcionario WHERE UPPER(designacao) LIKE upper('estafeta'));

        IF (v_idTipoFuncionario = 0) THEN
            RETURN FALSE;
        ELSE
            RETURN TRUE;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'id de funcionario desconhecido');
        RETURN FALSE;
    WHEN excep_parametroinvalido THEN
        raise_application_error(-20003, 'parametro invalido');
        RETURN FALSE;
END;
/

create FUNCTION fncEstafetaDisponivel(p_idfuncionario funcionario.id_funcionario%TYPE) RETURN INTEGER
AS
    v_disponivel INTEGER;
    v_existe     BOOLEAN;

    excep_parametroinvalido EXCEPTION;
    excep_naoEFuncionarioEstafeta EXCEPTION;

BEGIN
    -- verifica parametros
    IF (p_idfuncionario IS NOT NULL) THEN

        -- verificar se id de funcionario corresponde a um estafeta
        v_existe := fncFuncionarioEstafetaExiste(p_idfuncionario);
        -- se existir:
        IF (v_existe) THEN
            SELECT COUNT(id_funcionario)
            INTO v_disponivel
            FROM (SELECT *
                  FROM estafeta
                  WHERE id_funcionario = p_idfuncionario
                    AND id_tipodisponibilidade = (SELECT id_tipoDisponibilidade
                                                  FROM tipo_disponibilidade
                                                  WHERE UPPER(designacao) LIKE UPPER('livre')));

        ELSE
            RAISE excep_naoEFuncionarioEstafeta;
        END IF;
    ELSE
        RAISE excep_parametroinvalido;
    END IF;

    IF (v_disponivel > 0) THEN
        RETURN 1;
    ELSE
        RETURN 0;
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        raise_application_error(-20001, 'data not found');
        RETURN 0;
    WHEN excep_parametroinvalido THEN
        raise_application_error(-20003, 'Parametro Invalido');
        RETURN 0;
    WHEN excep_naoEFuncionarioEstafeta THEN
        raise_application_error(-20003, 'id de funcionario nao corresponde a funcionario estafeta');
        RETURN 0;
END;
/

create FUNCTION fncScooterExisteById(p_idScooter scooter.id_scooter%type) RETURN BOOLEAN
AS

    v_existe NUMBER;

    excep_parametrosInvalidos EXCEPTION;

BEGIN
    --verificar os parametros
    IF (p_idScooter IS NOT NULL) THEN

        --conta o numero de id de scooter com a matricula que entra por parametro
        SELECT COUNT(id_scooter) INTO v_existe FROM Scooter WHERE id_scooter = p_idScooter;
        -- se ja existir
        IF (v_existe > 0) THEN
            RETURN TRUE;
        ELSE
            RETURN FALSE;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
        RETURN FALSE;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
        RETURN FALSE;
END;
/

create FUNCTION fncGetScooterPorId(p_idScooter scooter.id_scooter%TYPE) RETURN sys_refcursor
AS
    infoScooter sys_refcursor;
    v_existe    BOOLEAN;

    excep_parametroInvalido EXCEPTION;
    excep_ScooterNaoExiste EXCEPTION;

BEGIN
    -- verifica parametro
    IF (p_idScooter IS NOT NULL) THEN
        -- verifica se scooter existe
        v_existe := fncScooterExisteById(p_idScooter);
        -- se existe:
        IF (v_existe) THEN
            OPEN infoScooter FOR
                SELECT c.matricula, modelo, c.capacidade AS capacidade_bateria, c.capacidade_atual
                FROM (
                         SELECT b.matricula, b.modelo, capacidade, b.id_estado, b.capacidade_atual, b.peso
                         FROM (
                                  SELECT a.matricula,
                                         designacao AS modelo,
                                         a.id_tipobateria,
                                         a.id_estado,
                                         capacidade_atual,
                                         peso
                                  FROM (SELECT * FROM SCOOTER WHERE id_scooter = p_idScooter) a -- tabela scooter
                                           LEFT JOIN modelo_scooter ON a.id_modelo = modelo_scooter.id_modeloScooter) b
                                  LEFT JOIN tipo_bateria ON b.id_tipoBateria = tipo_bateria.id_tipoBateria) c
                         LEFT JOIN estado_veiculo ON c.id_estado = estado_veiculo.id_estadoVeiculo;
            RETURN infoScooter;
        ELSE
            RAISE excep_ScooterNaoExiste;
        END IF;
    ELSE
        RAISE excep_parametroInvalido;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        raise_application_error(-20001, 'No data Found');
    WHEN excep_parametroInvalido THEN
        raise_application_error(-20003, 'Parâmetros inválidos');
    WHEN excep_ScooterNaoExiste THEN
        raise_application_error(-20003, 'Scooter inexistente em sistema');

END;
/

create FUNCTION fncRegistarEstafeta(p_nome funcionario.nome%type,
                                    p_nif funcionario.nif%type,
                                    p_niss funcionario.niss%type,
                                    p_email funcionario.email%type,
                                    p_rua localidade.designacao%type,
                                    p_latitude localidade.latitude%type,
                                    p_longitude localidade.longitude%type,
                                    p_altitude localidade.altitude%type,
                                    p_peso estafeta.peso%type,
                                    p_password estafeta.password%type,
                                    p_idfarmacia farmacia.id_farmacia%type)
    RETURN NUMBER
AS
    v_idFuncionario       NUMBER;
    v_tipoFuncionario     NUMBER;
    v_existeFuncionario   NUMBER;
    v_moradaExiste        BOOLEAN;
    v_farmaciaExiste      BOOLEAN;
    idEndereco            NUMBER;
    v_tipoDisponibilidade NUMBER := 1; -- Disponibilidade LIVRE
    v_tipoMochila         NUMBER := 1; -- Mochila do tipo 1

    excep_parametrosInvalidos EXCEPTION;
    excep_farmaciaInexistente EXCEPTION;
    excep_funcionarioJaExiste EXCEPTION;


BEGIN
    -- variaveis
    SELECT id_tipoFuncionario
    INTO v_tipoFuncionario
    FROM tipo_funcionario
    WHERE UPPER(designacao) LIKE UPPER('estafeta');
    SELECT id_tipoDisponibilidade
    INTO v_tipoDisponibilidade
    FROM Tipo_disponibilidade
    WHERE UPPER(designacao) LIKE UPPER('livre');

    -- verificar parametros
    IF (p_nome IS NOT NULL AND p_nif IS NOT NULL AND p_niss IS NOT NULL AND p_email IS NOT NULL AND
        p_rua IS NOT NULL AND p_latitude IS NOT NULL AND
        p_longitude IS NOT NULL AND p_altitude IS NOT NULL AND p_peso IS NOT NULL AND p_password IS NOT NULL AND
        p_idfarmacia IS NOT NULL) THEN

        -- verificar se farmacia existe
        v_farmaciaExiste := fncFarmaciaExisteById(p_idFarmacia);
        -- se existir:
        IF (v_farmaciaExiste) THEN
            --verificar se NIF ja existe
            SELECT COUNT(Id_funcionario) INTO v_existeFuncionario FROM funcionario WHERE NIF = p_nif;
            -- se não existir:
            IF (v_existeFuncionario = 0) THEN
                -- verificar se a morada existe na BDDAD
                v_moradaExiste := fncEnderecoExiste(p_rua, p_latitude, p_longitude, p_altitude);

                -- se existir, devolver o id
                IF (v_moradaExiste) THEN
                    SELECT id_localidade
                    INTO idEndereco
                    FROM localidade
                    WHERE UPPER(designacao) = UPPER(p_rua)
                      AND latitude = p_latitude
                      AND longitude = p_longitude
                      AND altitude = p_altitude;
                ELSE
                    -- se nao existir, criar e colocar o ID na variavel id_endereco
                    INSERT INTO localidade(designacao, latitude, longitude, altitude)
                    VALUES (p_rua, p_latitude, p_longitude, p_altitude);

                    SELECT MAX(id_localidade) INTO idEndereco FROM localidade;
                END IF;

                -- PREENCHER A TABELA FUNCIONARIO
                INSERT INTO funcionario (nome, nif, niss, email, id_endreco, id_tipoFuncionario)
                VALUES (p_nome, p_nif, p_niss, p_email, idEndereco, v_tipoFuncionario);

                SELECT MAX(id_funcionario) INTO v_idFuncionario FROM funcionario;

                -- PREENCHER A TABELA ESTAFETA
                INSERT INTO estafeta (id_funcionario, peso, id_tipoMochila, id_tipoDisponibilidade,
                                      id_scooterPreferencial, password)
                VALUES (v_idFuncionario, p_peso, v_tipoMochila, v_tipoDisponibilidade, NULL, p_password);

                -- PRENCHER R_FUNCIONARIO-FARMACIA
                INSERT INTO r_funcionario_farmacia (id_funcionario, id_farmacia)
                VALUES (v_idFuncionario, p_idfarmacia);
            ELSE
                RAISE excep_funcionarioJaExiste;
            END IF;
        ELSE
            RAISE excep_farmaciaInexistente;
        END IF;
    ELSE
        RAISE excep_parametrosInvalidos;
    END IF;

    RETURN v_idFuncionario;


EXCEPTION

    WHEN no_data_found THEN
        raise_application_error(-20001, 'Data not found');
        RETURN 0;
    WHEN excep_parametrosInvalidos THEN
        raise_application_error(-20003, 'Parametros inseridos invalidos');
        RETURN 0;
    WHEN excep_farmaciaInexistente THEN
        raise_application_error(-20003, 'Farmacia inexistente em sistema');
        RETURN 0;
    WHEN excep_funcionarioJaExiste THEN
        raise_application_error(-20003, 'Funcionário já existe em sistema');
        RETURN 0;
END;
/


