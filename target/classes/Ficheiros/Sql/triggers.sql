create or replace trigger UPDATE_ESTADO_ENCOMENDA
    after insert or update of DATAFIM
    on entrega
    for each row

declare

    v_idsEncomenda sys_refcursor;

begin

    v_idsEncomenda := fncGetIdsEncomendaByIdEntrega(:NEW.ID_ENTREGA);

    prcUpdateEncomendasByListIds(v_idsEncomenda);
	
	prcColocarEstafetaLivre(:NEW.ID_ENTREGA);
	
end;
