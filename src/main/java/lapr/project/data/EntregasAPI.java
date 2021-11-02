/**
 *
 * @author grupo 11
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Entrega;
import java.sql.SQLException;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Scooter;
import lapr.project.ui.Log;
import oracle.jdbc.OracleTypes;

/**
 * Classe responsável às ligações à BD para as funções relativas às entregas.
 */
public class EntregasAPI extends DataHandler {

    /**
     * Inicialização do construtor vazio
     */
    public EntregasAPI() {
        super();
    }

    /**
     * Método que retorna lista de encomendas pendentes de uma farmácia.
     *
     * @param idFarmacia id da farmacia
     * @return lista de encomendas pendentes
     */
    public List<Encomenda> getEncomendasPendentes(Integer idFarmacia) {

        ArrayList<Encomenda> encomendas = new ArrayList<>();

        try {

            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetEncomendasPendentesByFarmacia(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().setInt(2, idFarmacia);
            this.getCallStmt().execute();

            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);

            Integer idEncomenda;
            Double peso;
            Integer nifCliente;
            Integer idEndereco;
            String rua;
            Double latitude;
            Double longitude;
            Double altitude;

            while (rs.next()) {

                Encomenda tmp = new Encomenda();

                idEncomenda = rs.getInt("id_encomenda");
                peso = rs.getDouble("peso");
                nifCliente = rs.getInt("nif");
                idEndereco = rs.getInt("id_endreco");
                rua = rs.getString("rua");
                latitude = rs.getDouble("latitude");
                longitude = rs.getDouble("longitude");
                altitude = rs.getDouble("altitude");

                tmp.setId(idEncomenda);
                tmp.setPeso(peso);
                tmp.setEndereco(new Endereco(rua, latitude, longitude, altitude));
                tmp.getEndereco().setIdEndereco(idEndereco);
                tmp.setNifCliente(nifCliente);

                encomendas.add(tmp);
            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {

            closeAll();
        }

        return encomendas;
    }

    /**
     * Método que retorna e regista uma Entrega na Base de Dados
     *
     * @param e entrega
     * @return entrega
     */
    public Entrega registarEntrega(Entrega e) {

        Integer id = novaEntrega();

        if (id != null && id > 0) {
            e.setId(id);

            if (e.getVeiculo() instanceof Scooter) {

                for (Encomenda encomenda : e.getSetEncomendas()) {
                    adicionarLinhaEntrega(e.getVeiculo().getId(), e.getId(), e.getIdEstafeta(), encomenda.getId());
                }
            } else {
                for (Encomenda encomenda : e.getSetEncomendas()) {
                    adicionarLinhaEntrega(e.getVeiculo().getId(), e.getId(), null, encomenda.getId());
                }
            }
        }
        return e;
    }

    /**
     * Nova entrega
     * @return id da nova entrega
     */
    private Integer novaEntrega() {

        Integer idEntrega = -1;
        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncCriarEntrega()}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().execute();

            idEntrega = this.getCallStmt().getInt(1);

        } catch (SQLException ex) {
            Log.logError(ex.toString());;
        } finally {
            closeAll();
        }
        return idEntrega;
    }

    /**
     * Adicionar uma linha na entrega
     * @param idVeiculo id do veiculo
     * @param IdEntrega id da entrega
     * @param idEstafeta id do estafeta
     * @param IdEncomenda id da encomenda
     * @return true or false
     */
    private boolean adicionarLinhaEntrega(Integer idVeiculo, Integer IdEntrega, Integer idEstafeta, Integer IdEncomenda) {

        Boolean resultado = false;
        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcAdicionarEncomendaEntrega(?, ?, ?, ?)}"));
            this.getCallStmt().setInt(1, idVeiculo);
            this.getCallStmt().setInt(2, IdEntrega);
            this.getCallStmt().setInt(3, idEstafeta);
            this.getCallStmt().setInt(4, IdEncomenda);
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
     * Método que devolve o ID de Scooter de uma Fármacia que esteja disponível
     * para uma Entrega.
     *
     * @param idFarmacia id da farmacia
     * @return id da scooter disponivel para entrega
     */
    public Integer getScooterParaEntrega(Integer idFarmacia) {

        Integer id = -1;
        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetScooterDisponivel(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setInt(2, idFarmacia);
            this.getCallStmt().execute();

            id = this.getCallStmt().getInt(1);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return id;
    }

    /**
     * Método que retorna o ID de um Drone disponível de uma Fármacia para uma
     * entrega
     *
     * @param idFarmacia id da farmacia
     * @return id do drone disponivel para entrega
     */
    public Integer getDroneParaEntrega(Integer idFarmacia) {

        Integer id = -1;
        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetDroneDisponivel(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setInt(2, idFarmacia);
            this.getCallStmt().execute();

            id = this.getCallStmt().getInt(1);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }
        return id;
    }

}
