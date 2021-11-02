/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.SQLException;
import java.util.Map;
import lapr.project.model.Artigo;
import lapr.project.model.Encomenda;
import lapr.project.ui.Log;
import oracle.jdbc.OracleTypes;

/**
 * Classe responsável às ligações à BD para as funções relativas às encomendas.
 */
public class EncomendaAPI extends DataHandler {

    /**
     * inicilização do construtor vazio
     */
    public EncomendaAPI() {
        super();
    }

    /**
     * Método para criar uma encomenda e adicionar os respectivos artigos na
     * encomenda na Base de Dados.
     *
     * @param e encomenda
     * @return id de encomenda recem criada
     */
    public Integer criarEncomenda(Encomenda e) {
        // Chama fuction criar encomenda 
        // if not null 
        Integer idEncomenda = novaEncomenda(e);
        if (idEncomenda != null && idEncomenda > 0) {
            e.setId(idEncomenda);
            Map<Artigo, Integer> artigos = e.getListaArtigos();
            Boolean result = false;
            for (Map.Entry<Artigo, Integer> entry : artigos.entrySet()) {
                Integer idArtigo = entry.getKey().getIdArtigo();
                Integer quantidade = entry.getValue();
                result = adicionarArtigo(idEncomenda, idArtigo, quantidade);

            }
            if (result) {
                result = gerarFatura(idEncomenda, false);
            }
        }
        return idEncomenda;

    }

    /**
     * nova encomenda
     * @param e encomenda
     * @return id da encomenda recem criada
     */
    private Integer novaEncomenda(Encomenda e) {

        Integer idEncomenda = -1;
        long nif = e.getNifCliente();

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncRegistarEncomenda(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setLong(2, e.getNifCliente());
            this.getCallStmt().execute();

            idEncomenda = this.getCallStmt().getInt(1);

        } catch (SQLException ex) {
            Log.logError(ex.toString());
        } finally {
            closeAll();
        }
        return idEncomenda;

    }

    /**
     * adicionar artigo
     * @param idEncomenda id da encomenda
     * @param idArtigo id do artigo
     * @param quantidade quantidade do artigo
     * @return true or false
     */
    private boolean adicionarArtigo(Integer idEncomenda, Integer idArtigo, Integer quantidade) {

        Boolean resultado = false;
        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcPreencherEncomenda(?,?,?)}"));
            this.getCallStmt().setInt(1, idEncomenda);
            this.getCallStmt().setInt(2, idArtigo);
            this.getCallStmt().setInt(3, quantidade);
            this.getCallStmt().execute();

            resultado = true;

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return resultado;
    }

    /**
     * gerar fatura
     * @param idEncomenda id da encomenda
     * @param descontaCreditos desconto de creditos
     * @return true or false
     */
    private boolean gerarFatura(Integer idEncomenda, Boolean descontaCreditos) {
        int descontarCreditos = (descontaCreditos) ? 1 : 0;

        Boolean resultado = false;
        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcRegistarFatura(?,?)}"));
            this.getCallStmt().setInt(1, idEncomenda);
            this.getCallStmt().setInt(2, descontarCreditos);
            this.getCallStmt().execute();
            resultado = true;

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return resultado;
    }

    /**
     * Método que retorna o email do cliente através do NIF do cliente.
     *
     * @param e encomenda
     * @return email do cliente
     */
    public String getEmailClienteByNif(Encomenda e) {

        //fncRegistarEncomenda
        String email = "";
        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetEmailClienteByNif(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.VARCHAR);
            this.getCallStmt().setLong(2, e.getNifCliente());
            this.getCallStmt().execute();

            email = this.getCallStmt().getString(1);

        } catch (SQLException ex) {
            Log.logError(ex.toString());
        } finally {
            closeAll();
        }
        return email;

    }

}
