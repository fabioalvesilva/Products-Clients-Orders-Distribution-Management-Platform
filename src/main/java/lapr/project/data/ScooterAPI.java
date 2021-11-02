/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.model.Scooter;
import lapr.project.ui.Log;
import oracle.jdbc.OracleTypes;

/**
 * Classe responsável às ligações à BD para as funções relativas ao Scooter.
 */
public class ScooterAPI extends DataHandler {

    /**
     * Inicialização do construtor vazio
     */
    public ScooterAPI() {
    }

    /**
     * Inicialização do construtor completo
     * @param jdbcUrl ligação a base de dados
     * @param username utilizador
     * @param password password
     */
    public ScooterAPI(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     * Método que adiciona uma Scooter a uma Farmácia dado um ID.
     *
     * @param s scooter
     * @param idFarmacia id da farmacia
     * @return true or false
     */
    public boolean adicionarScooter(Scooter s, int idFarmacia) {
        return adicionarScooter(s.getMatricula(), idFarmacia);
    }

    /**
     * adicionar scooter
     * @param matricula matricula
     * @param idFarmacia id da farmacia
     * @return true or false
     */
    private boolean adicionarScooter(String matricula, int idFarmacia) {

        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcRegistarScooter(?,?)}"));
            this.getCallStmt().setString(1, matricula);
            this.getCallStmt().setInt(2, idFarmacia);
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
     * Método que atualiza o estado de uma Scooter dado uma matrícula.
     *
     * @param matricula matricula
     * @param designacaoEstadoScooter estado da scooter
     * @return true or false
     */
    public boolean atualizarScooter(String matricula, String designacaoEstadoScooter) {

        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcAtualizarScooter(?,?)}"));
            this.getCallStmt().setString(1, matricula);
            this.getCallStmt().setString(2, designacaoEstadoScooter);

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
     * Método que remove uma Scooter da Base de Dados dado uma matrícula.
     *
     * @param matricula matricula
     * @return true or false
     */
    public boolean removerScooter(String matricula) {

        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcRemoverScooter(?)}"));
            this.getCallStmt().setString(1, matricula);

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
     * Método que devolve uma Scooter dado uma ID.
     *
     * @param id id da scooter
     * @return scooter
     */
    public Scooter getScooterById(Integer id) {

        Scooter tmp = new Scooter();

        tmp.setId(id);
        String matricula;
        String modelo;
        Integer capacidadeBateria;
        Integer capacidadeAtual;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetScooterPorId(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().setInt(2, id);
            this.getCallStmt().execute();

            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);

            while (rs.next()) {

                matricula = rs.getString("MATRICULA");
                modelo = rs.getString("MODELO");
                capacidadeBateria = rs.getInt("CAPACIDADE_BATERIA");
                capacidadeAtual = rs.getInt("CAPACIDADE_ATUAL");

                tmp.setMatricula(matricula);
                tmp.setModelo(modelo);
                tmp.setCapacidadeBateria(capacidadeBateria);
                tmp.setCapacidadeAtual(capacidadeAtual);

            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return tmp;
    }

    /**
     * Método que devolve uma Scooter dado uma matrícula.
     *
     * @param matricula matricula
     * @return scooter
     */
    public Scooter getScooterByMatricula(String matricula) {

        Scooter tmp = new Scooter();

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetScooterByMatricula(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().setString(2, matricula);
            this.getCallStmt().execute();

            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);
            rs.next();

            Integer id = rs.getInt("ID_SCOOTER");
            String m = rs.getString("MATRICULA");
            String modelo = rs.getString("MODELO");
            Integer idTipoBateria = rs.getInt("ID_TIPOBATERIA");
            Integer capacidadeBateria = rs.getInt("CAPACIDADE");
            Integer estado = rs.getInt("ID_ESTADO");
            Integer capacidadeAtual = rs.getInt("BATERIA_ATUAL");
            Integer eficienciaBateria = rs.getInt("EFICIENCIA");

            tmp.setId(id);
            tmp.setMatricula(m);
            tmp.setModelo(modelo);
            tmp.setIdTipoBateria(idTipoBateria);
            tmp.setIdEstado(estado);
            tmp.setCapacidadeBateria(capacidadeBateria);
            tmp.setCapacidadeAtual(capacidadeAtual);
            tmp.setEficienciaBateria(eficienciaBateria);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return tmp;
    }

    /**
     * obter email do estafeta pelo email
     * @param scooterId id da scooter
     * @return email
     */
    public String getEmailByScooterId(Integer scooterId) {
     
        
        String email = "";
        
        
        try {
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncgetemailbyscooterid (?) }"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.VARCHAR);
            this.getCallStmt().setInt(2, scooterId);
            this.getCallStmt().execute();
            
            email = this.getCallStmt().getString(1);

        } catch (SQLException e) {
             Log.logError(e.toString());
        } finally {

            closeAll();
        }
        
        return email;
    }

    /**
     * obter eficiencia da scooter
     * @param scooterId id da scooter
     * @return eficiencia
     */
    public int getScooterBateriaEficiencia(int scooterId) {
        
        int eficiencia = 0;
        
        
        try {
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncgetscootereficienciaporid(?)}"));

            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setInt(2, scooterId);
            this.getCallStmt().execute();
            
            eficiencia = this.getCallStmt().getInt(1);

        } catch (SQLException e) {
             Log.logError(e.toString());
        } finally {

            closeAll();
        }
        
        return eficiencia;
    
    }
    
    /**
     * obter a corrente debitada
     * @param farmaciaId id da farmacia
     * @return corrente debitada
     */
    public int getCorrenteDebitada(Integer farmaciaId){
        
        int eficiencia = 0;
        
        
        try {
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncgetcorrentedebitada (?) }"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.NUMBER);
            this.getCallStmt().setInt(2, farmaciaId);

            this.getCallStmt().execute();
            
            eficiencia = this.getCallStmt().getInt(1);

        } catch (SQLException e) {
             Log.logError(e.toString());
        } finally {

            closeAll();
        }
        
        return eficiencia;
    
    }

}
