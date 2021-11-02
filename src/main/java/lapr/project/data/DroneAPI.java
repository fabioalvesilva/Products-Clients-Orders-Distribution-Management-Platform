/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.model.Drone;
import lapr.project.ui.Log;
import oracle.jdbc.OracleTypes;

/**
 * Classe responsável às ligações à BD para as funções relativas ao Drone.
 */
public class DroneAPI extends DataHandler {

    /**
     * Inicializa construtor vazio
     */
    public DroneAPI() {
    }

    /**
     * Inicializa construtor completo
     * @param jdbcUrl O URL da BD
     * @param username username
     * @param password password
     */
    public DroneAPI(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     * Método para adicionar um Drone na Base de Dados
     *
     * @param d drone
     * @return true or false
     */
    public boolean adicionarDrone(Drone d) {
        return adicionarDrone(d.getNrRegisto(), d.getIdFarmacia());
    }

    /**
     * Adicionar drone
     * @param nrRegisto numero de registo
     * @param idFarmacia id da farmacia
     * @return true or false
     */
    private boolean adicionarDrone(Integer nrRegisto, Integer idFarmacia) {

        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcRegistarDrone(?,?)}"));
            this.getCallStmt().setInt(1, nrRegisto);
            this.getCallStmt().setInt(2, idFarmacia);
            this.getCallStmt().execute();

            resultado = true;

        } catch (SQLException e) {
            Log.logError(e.toString());
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return resultado;
    }

    /**
     * Método para atualizar a informação de um Drone na Base de Dados
     *
     * @param nrRegisto numero de registo
     * @param designacaoEstadoDrone estado do drone
     * @return true or false
     */
    public boolean atualizarDrone(Integer nrRegisto, String designacaoEstadoDrone) {

        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcAtualizarDrone(?,?)}"));
            this.getCallStmt().setInt(1, nrRegisto);
            this.getCallStmt().setString(2, designacaoEstadoDrone);

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
     * Método para remover um Drone da Base de Dados
     *
     * @param nrRegisto numero de registo
     * @return true or false
     */
    public boolean removerDrone(Integer nrRegisto) {

        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcRemoverScooter(?)}"));
            this.getCallStmt().setInt(1, nrRegisto);

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
     * Método para retornar um Drone por ID.
     *
     * @param id id do drone
     * @return drone
     */
    public Drone getDroneById(Integer id) {

        Drone tmp = new Drone();

        tmp.setId(id);
        Integer nrRegisto;
        String modelo;
        Double capacidadeBateria;
        Double capacidadeAtual;
        Double eficienciaBateria;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetDroneById(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().setInt(2, id);
            this.getCallStmt().execute();

            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);
            rs.next();

            nrRegisto = rs.getInt("NUMREGISTO");
            modelo = rs.getString("MODELO");
            capacidadeBateria = rs.getDouble("CAPACIDADE_BATERIA");
            capacidadeAtual = rs.getDouble("CAPACIDADE_ATUAL");
            eficienciaBateria = rs.getDouble("EFICIENCIA");

            tmp.setNrRegisto(nrRegisto);
            tmp.setDesignacaoModelo(modelo);
            tmp.setCapacidadeBateria(capacidadeBateria);
            tmp.setCargaBateriaAtual(capacidadeAtual);
            tmp.setEficiencia(eficienciaBateria);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return tmp;
    }

    /**
     * Método para retornar um Drone por número de registo.
     *
     * @param nrRegisto  numero de registo
     * @return drone
     */
    public Drone getDroneByNrRegisto(Integer nrRegisto) {

        Drone tmp = new Drone();

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetDroneByNrRegisto(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().setInt(2, nrRegisto);
            this.getCallStmt().execute();

            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);
            rs.next();

            Integer id = rs.getInt("ID_DRONE");
            Integer m = rs.getInt("NUMREGISTO");
            String modelo = rs.getString("MODELO");
            Double capacidadeBateria = rs.getDouble("CAPACIDADE_BATERIA");
            Double capacidadeAtual = rs.getDouble("CAPACIDADE_ATUAL");
            Double eficienciaBateria = rs.getDouble("EFICIENCIA");

            tmp.setId(id);
            tmp.setNrRegisto(m);
            tmp.setDesignacaoModelo(modelo);
            tmp.setCapacidadeBateria(capacidadeBateria);
            tmp.setCargaBateriaAtual(capacidadeAtual);
            tmp.setEficiencia(eficienciaBateria);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }
        return tmp;
    }

}
