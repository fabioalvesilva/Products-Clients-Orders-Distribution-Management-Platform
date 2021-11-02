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
import lapr.project.model.Cliente;
import lapr.project.model.Endereco;
import lapr.project.model.Farmacia;
import lapr.project.model.Local;
import lapr.project.model.Parque;
import lapr.project.ui.Log;
import oracle.jdbc.OracleTypes;

/**
 * Classe responsável às ligações à BD para as funções relativas aos Locais.
 */
public class LocaisAPI extends DataHandler {

    /**
     * Inicialização do construtor vazio
     */
    public LocaisAPI() {
        super();
    }

    /**
     * Método que retona uma lista de Local de todas as Farmácias.
     *
     * @return lista dos locais das farmacias
     */
    public List<Local> getAllFarmaciasLocal() {

        ArrayList<Local> listaFarmacias = new ArrayList<>();

        try {
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetAllFarmaciaInfo()}"));

            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().execute();
            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);

            while (rs.next()) {

                Farmacia farmacia = new Farmacia();

                Integer idFarmacia = rs.getInt("id_farmacia");
                Integer nif = rs.getInt("NIPC");
                String descricao = rs.getString("designacao");

                Integer idEndereco = rs.getInt("id_localidade");
                String rua = rs.getString("rua");
                Double latitude = rs.getDouble("latitude");
                Double longitude = rs.getDouble("longitude");
                Double altitude = rs.getDouble("altitude");

                Endereco endereco = new Endereco(idEndereco, rua, latitude, longitude, altitude);
                farmacia.setEndereco(endereco);

                farmacia.setId(idFarmacia);
                farmacia.setNif(nif);
                farmacia.setDescricao(descricao);

                listaFarmacias.add(farmacia);
            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {

            closeAll();
        }

        return listaFarmacias;

    }

    /**
     * Método que retona uma lista de Local de todas os Parques.
     *
     * @return lista dos locais dos parques
     */
    public List<Local> getAllParquesLocal() {

        ArrayList<Local> listaParques = new ArrayList<>();

        try {
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetAllParqueInfo()}"));

            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().execute();
            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);

            while (rs.next()) {

                Parque parque = new Parque();

                Integer idParque = rs.getInt("id_parque");
                Integer tipoParque = rs.getInt("tipo_parque");
                Integer maxLugaresEletricos = rs.getInt("numPostoCarregamento");
                Integer maxLugaresNormais = rs.getInt("numLugaresEstacionamentoNormal");

                Integer idEndereco = rs.getInt("id_localidade");
                String rua = rs.getString("designacao");
                Double latitude = rs.getDouble("latitude");
                Double longitude = rs.getDouble("longitude");
                Double altitude = rs.getDouble("altitude");

                Endereco endereco = new Endereco(idEndereco, rua, latitude, longitude, altitude);
                parque.setEndereco(endereco);
                parque.setId(idParque);
                parque.setMaxLugaresEletricos(maxLugaresEletricos);
                parque.setMaxLugaresNormais(maxLugaresNormais);
                parque.setIdTipoParque(tipoParque);
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
     * Método que retona uma lista de Local de todas os Clientes.
     *
     * @return lista dos locais dos clientes
     */
    public List<Local> getAllClientesLocal() {

        ArrayList<Local> listaClientes = new ArrayList<>();

        try {
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetAllClientes()}"));

            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().execute();
            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);

            while (rs.next()) {

                Cliente cliente = new Cliente();

                Integer idCliente = rs.getInt("id_cliente");
                String nome = rs.getString("nome");
                String email = rs.getString("email");;
                Integer nif = rs.getInt("nif");
                Integer idEndereco = rs.getInt("id_endreco");
                String rua = rs.getString("designacao");
                Double latitude = rs.getDouble("latitude");
                Double longitude = rs.getDouble("longitude");
                Double altitude = rs.getDouble("altitude");

                cliente.setId(idCliente);
                cliente.setNome(nome);
                cliente.setEmail(email);
                cliente.setNif(nif);

                Endereco endereco = new Endereco(idEndereco, rua, latitude, longitude, altitude);
                cliente.setEndereco(endereco);
                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {

            closeAll();
        }

        return listaClientes;

    }

    /**
     * Método que retorna o ID de Endereçõ dado uma Rua.
     *
     * @param designacao nome da rua
     * @return id de endereco
     */
    public int getIdEnderecoByRua(String designacao) {

        int idEndereco = 0;

        try {
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetIdEnderecoByRua(?)}"));

            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setString(2, designacao);
            this.getCallStmt().execute();

            idEndereco = this.getCallStmt().getInt(1);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {

            closeAll();
        }

        return idEndereco;
    }

    /**
     * Método que retorna um Local de uma Farmácia dado um ID.
     *
     * @param id id de farmacia
     * @return local da farmacia
     */
    public Local getFarmaciaById(Integer id) {

        Farmacia farmacia = new Farmacia();

        try {

            // Ligação à base de dados
            getConnection();
            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetAllFarmaciaInfoById(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().setInt(2, id);
            this.getCallStmt().execute();

            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);
            rs.next();
            Integer idFarmacia = rs.getInt("id_farmacia");
            Integer nif = rs.getInt("NIPC");
            String descricao = rs.getString("designacao");
            //Endereco 
            Integer idEndereco = rs.getInt("id_localidade"); //Set Id endereco 
            String rua = rs.getString("rua");
            Double latitude = rs.getDouble("latitude");
            Double longitude = rs.getDouble("longitude");
            Double altitude = rs.getDouble("altitude");
            //SetInfo
            Endereco endereco = new Endereco(idEndereco, rua, latitude, longitude, altitude);
            farmacia.setEndereco(endereco);

            farmacia.setId(idFarmacia);
            farmacia.setNif(nif);
            farmacia.setDescricao(descricao);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return farmacia;
    }
}
