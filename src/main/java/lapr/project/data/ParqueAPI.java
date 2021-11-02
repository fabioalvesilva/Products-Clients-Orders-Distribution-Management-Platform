/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Parque;
import lapr.project.ui.Log;
import oracle.jdbc.OracleTypes;

/**
 * Classe responsável às ligações à BD para as funções relativas ao Parque.
 */
public class ParqueAPI extends DataHandler {

    /**
     * Inicialização do construtor vazio
     */
    public ParqueAPI() {
        super();
    }

    /**
     * Método que retorna uma lista de todos os Parques
     *
     * @return lista de todos os parques em sistema
     */
    public List<Parque> getAllParques() {

        ArrayList<Parque> listaParques = new ArrayList<>();

        try {
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetAllParqueInfo()}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().execute();
            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);

            Integer id;

            Integer maxLugaresNormais;
            Integer maxLugaresEletricos;

            while (rs.next()) {

                Parque parque = new Parque();
                id = rs.getInt("id_parque");
                maxLugaresNormais = rs.getInt("numLugaresEstacionamentoNormal");
                maxLugaresEletricos = rs.getInt("numPostoCarregamento");

                parque.setIdTipoParque(id);
                parque.setMaxLugaresNormais(maxLugaresNormais);
                parque.setMaxLugaresEletricos(maxLugaresEletricos);

                listaParques.add(parque);
            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {

            closeAll();
        }

        return listaParques;
    }

    /**
     * Método que atualiza a capacidade de lugares de uma parque.
     *
     * @param idParque id do parque
     * @param nrLugaresNormal numero de lugares de estacionamento normais
     * @param nrLugaresEletricos numero de lugares de estacionamento com posto de carregamento
     * @return true or false
     */
    public boolean updateCapacidadeParque(int idParque, int nrLugaresNormal, int nrLugaresEletricos) {
        Boolean resultado = false;
        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcAtualizarCapacidadeParque(?,?,?)}"));
            this.getCallStmt().setInt(1, idParque);
            this.getCallStmt().setInt(2, nrLugaresNormal);
            this.getCallStmt().setInt(3, nrLugaresEletricos);
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
     * Método que adiciona um Parque a uma Farmácia dado um ID.
     *
     * @param idFarmacia id da farmacia
     * @param p parque
     * @return id do parque recem criado
     */
    public Integer adicionarParque(int idFarmacia, Parque p) {
        return adicionarParque(idFarmacia, p.getIdTipoParque(), p.getMaxLugaresNormais(), p.getMaxLugaresEletricos(), p.getEndereco().getRua(),
                p.getEndereco().getLatitude(), p.getEndereco().getLongitude(), p.getEndereco().getAltitude(), p.getCapacidadeTensao());
    }

    /**
     * adicionar parque
     * @param idFarmacia id da farmacia
     * @param id_TipoParque id do tipo de parque
     * @param lugaresNormais numero de lugares de estacionamento normais
     * @param lugaresEletricos numero de lugares de estacionamento com posto de carregamento
     * @param rua rua
     * @param latitude latitude
     * @param longitude longitude
     * @param altitude altitude
     * @param capacidadeTensao capacidade de tensao do parque
     * @return id do parque recem criado
     */
    private Integer adicionarParque(int idFarmacia, int id_TipoParque, int lugaresNormais, int lugaresEletricos, String rua, Double latitude, Double longitude, Double altitude, int capacidadeTensao) {

        Integer idParque = -1;
        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncRegistarParque(?,?,?,?,?,?,?,?,?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setInt(2, idFarmacia);
            this.getCallStmt().setInt(3, id_TipoParque);
            this.getCallStmt().setInt(4, lugaresNormais);
            this.getCallStmt().setInt(5, lugaresEletricos);
            this.getCallStmt().setString(6, rua);
            this.getCallStmt().setDouble(7, latitude);
            this.getCallStmt().setDouble(8, longitude);
            this.getCallStmt().setDouble(9, altitude);
            this.getCallStmt().setDouble(10, capacidadeTensao);

            this.getCallStmt().execute();

            idParque = (Integer) this.getCallStmt().getObject(1);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return idParque;
    }

    /**
     * Método para remover um paraque da Base de Dados.
     *
     * @param idParque id do parque
     * @return true or false
     */
    public boolean removerParque(int idParque) {

        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcRemoverParque(?)}"));
            this.getCallStmt().setInt(1, idParque);

            resultado = true;

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return resultado;
    }

}
