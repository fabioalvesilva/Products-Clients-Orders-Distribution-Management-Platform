CREATE OR REPLACE TRIGGER UPDATE_ESTADO_ENCOMENDA
    after insert or update of DATAFIM
    on entrega
    for each row

DECLARE

    v_idsEncomenda sys_refcursor;

BEGIN

    v_idsEncomenda := fncGetIdsEncomendaByIdEntrega(:NEW.ID_ENTREGA);

    prcUpdateEncomendasByListIds(v_idsEncomenda);
	
	prcColocarEstafetaLivre(:NEW.ID_ENTREGA);
	
END;
/

CREATE OR REPLACE TRIGGER trgatualizarestafetaencomeda AFTER
    INSERT OR UPDATE ON R_ESTAFETA_VEICULO_ENTREGA_ENCOMENDA
    FOR EACH ROW
DECLARE
    v_id_estafeta INTEGER := :new.ID_ESTAFETA;
    v_id_encomenda INTEGER := :new.ID_ENCOMENDA;
BEGIN
    prcupdateestafetaentrega(v_id_estafeta);
    prcupdateencomendaentrega(v_id_encomenda);
      
END;
/
