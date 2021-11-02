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
import lapr.project.model.*;
import lapr.project.ui.Log;
import oracle.jdbc.OracleTypes;

/**
 * Classe responsável às ligações à BD para as funções relativas ao Utilizador.
 */
public class UtilizadorAPI extends DataHandler {

    /**
     * Inicialização do construtor vazio
     */
    public UtilizadorAPI() {
        super();
    }

    /**
     * Inicialização do construtor completo
     * @param url url
     * @param username utilizador
     * @param password password
     */
    public UtilizadorAPI(String url, String username, String password) {
        super(url, username, password);
    }

    /**
     * Método que verifica através de um email se o utilizador é Administrador.
     *
     * @param email email do administrador
     * @return true or false
     */
    public boolean verificarAdmin(String email) {

        boolean resultado = false;
        if (!email.isEmpty()) {
            int integer = 0;

            try {

                // Ligação à base de dados
                getConnection();

                // Query
                this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncValidarAdmin(?)}"));
                this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
                this.getCallStmt().setString(2, email);
                this.getCallStmt().execute();

                integer = this.getCallStmt().getInt(1);

                if (integer == 1) {
                    resultado = true;
                }

            } catch (SQLException e) {
                Log.logError(e.toString());
            } finally {
                closeAll();
            }
        }

        return resultado;

    }

    /**
     * Método responsável pelo login do Administrador.
     *
     * @param email email do administrador
     * @param password pass do administrador
     * @return true or false
     */
    public boolean validarLoginAdmin(String email, String password) {

        boolean resultado = false;
        int integer = 0;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncValidarLoginAdmin(?,?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setString(2, email);
            this.getCallStmt().setString(3, password);
            this.getCallStmt().execute();

            integer = this.getCallStmt().getInt(1);

            if (integer == 1) {
                resultado = true;
            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return resultado;

    }

    /**
     * Método responsável pelo login do Cliente.
     *  
     * @param email email do cliente
     * @param password password do cliente
     * @return true or false
     */
    public boolean validarLoginCliente(String email, String password) {

        boolean resultado = false;
        int integer = 0;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncValidarLoginCliente(?,?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setString(2, email);
            this.getCallStmt().setString(3, password);
            this.getCallStmt().execute();

            integer = this.getCallStmt().getInt(1);

            if (integer == 1) {
                resultado = true;
            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return resultado;

    }

    /**
     * Método que regista um Cliente à Base de Dados.
     *
     * @param c cliente
     * @return true or false
     */
    public boolean adicionarCliente(Cliente c) {
        return adicionarCliente(c.getNome(), c.getEmail(), c.getNif(), c.getPassword(), c.getEndereco().getRua(),
                c.getEndereco().getLatitude(), c.getEndereco().getLongitude(), c.getEndereco().getAltitude(),
                c.getCartaoCredito());
    }

    /**
     * Metodo que permite adicionar clientes
     * @param nome nome
     * @param email email
     * @param nif NIF
     * @param password password
     * @param rua rua
     * @param latitude latitude
     * @param longitude longitude
     * @param altitude altitude
     * @param numeroCartaoCredito numero de cartão de credito
     * @return true or false
     */
    private boolean adicionarCliente(String nome, String email, Integer nif, String password,
            String rua, Double latitude, Double longitude, Double altitude,
            Integer numeroCartaoCredito) {

        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcRegistarCliente(?,?,?,?,?,?,?,?,?)}"));
            this.getCallStmt().setString(1, nome);
            this.getCallStmt().setInt(2, nif);
            this.getCallStmt().setString(3, password);
            this.getCallStmt().setString(4, rua);
            this.getCallStmt().setDouble(5, latitude);
            this.getCallStmt().setDouble(6, longitude);
            this.getCallStmt().setDouble(7, altitude);
            this.getCallStmt().setString(8, email);
            this.getCallStmt().setInt(9, numeroCartaoCredito);

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
     * Método que atualiza os dados de um Cliente na Base de Dados.
     *
     * @param c cliente
     * @return true or false
     */
    public boolean atualizarCliente(Cliente c) {
        return atualizarCliente(c.getNome(), c.getEmail(), c.getNif(), c.getPassword(), c.getEndereco().getRua(),
                c.getEndereco().getLatitude(), c.getEndereco().getLongitude(), c.getCartaoCredito());
    }

    /**
     * Atualizar cliente
     * @param nome nome
     * @param email email
     * @param nif NIF
     * @param password password
     * @param rua rua
     * @param latitude latitude
     * @param longitude longitude
     * @param numeroCartaoCredito numero de credito
     * @return true or false
     */
    private boolean atualizarCliente(String nome, String email, Integer nif, String password,
            String rua, Double latitude, Double longitude, Integer numeroCartaoCredito) {

        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcAtualizarCliente(?,?,?,?,?,?,?,?)}"));
            this.getCallStmt().setInt(1, nif);
            this.getCallStmt().setString(2, nome);
            this.getCallStmt().setString(3, password);
            this.getCallStmt().setString(4, rua);
            this.getCallStmt().setDouble(5, latitude);
            this.getCallStmt().setDouble(6, longitude);
            this.getCallStmt().setString(7, email);
            this.getCallStmt().setInt(8, numeroCartaoCredito);

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
     * Método que regista um Estafeta à Base de Dados.
     *
     * @param e estafeta
     * @param idFarmacia id de farmacia
     * @return 1id de estafeta recem criado
     */
    public int adicionarEstafeta(Estafeta e, Integer idFarmacia) {
        return adicionarEstafeta(e.getNome(), e.getEmail(), e.getNif(), e.getPassword(), e.getNiss(), idFarmacia);
    }

    /**
     * Adicionar estafeta
     * @param nome nome
     * @param email email
     * @param nif NIF
     * @param password password
     * @param niss NISS
     * @param idFarmacia id da farmacia
     * @return id do estafeta recem criado
     */
    private int adicionarEstafeta(String nome, String email, Integer nif, String password,
             Integer niss, Integer idFarmacia) {

        int idEstafeta = 0;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncRegistarEstafeta(?,?,?,?,?,?) }"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setString(2, nome);
            this.getCallStmt().setInt(3, nif);
            this.getCallStmt().setInt(4, niss);
            this.getCallStmt().setString(5, email);
            this.getCallStmt().setString(6, password);
            this.getCallStmt().setInt(7, idFarmacia);

            this.getCallStmt().execute();

            idEstafeta = this.getCallStmt().getInt(1);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }
        return idEstafeta;
    }

    /**
     * Método que devolve o ID de uma Farmácia dado um email de um Utilizador.
     *
     * @param email email
     * @return id da farmacia do utilizador
     */
    public int getIdFarmaciaPorUtilizador(String email) {

        int resultado = 0;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetFarmaciaUtilizador(?) }"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setString(2, email);
            this.getCallStmt().execute();

            resultado = this.getCallStmt().getInt(1);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return resultado;
    }

    /**
     * Método que devolve um Cliente dado um NIF do Cliente.
     *
     * @param nifCliente nif do cliente
     * @return cliente
     */
    public Cliente getClienteByNif(Integer nifCliente) {

        Cliente tmp = new Cliente();

        tmp.setNif(nifCliente);

        Integer id;
        String nome;
        String email;
        Integer idEndereco;
        String rua;
        Double latitude;
        Double longitude;
        Double altitude;
        Integer numeroCartao;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetClienteByNif(?) }"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().setInt(2, nifCliente);
            this.getCallStmt().execute();

            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);

            while (rs.next()) {

                id = rs.getInt("ID_CLIENTE");
                nome = rs.getString("NOME");
                email = rs.getString("EMAIL");
                numeroCartao = rs.getInt("CARTAOCREDITO");
                idEndereco = rs.getInt("ID_ENDRECO");
                rua = rs.getString("DESIGNACAO");
                latitude = rs.getDouble("LATITUDE");
                longitude = rs.getDouble("LONGITUDE");
                altitude = rs.getDouble("ALTITUDE");

                tmp.setId(id);
                tmp.setNome(nome);
                tmp.setEmail(email);
                tmp.setCartaoCredito(numeroCartao);
                tmp.getEndereco().setIdEndereco(idEndereco);
                tmp.getEndereco().setRua(rua);
                tmp.getEndereco().setLongitude(longitude);
                tmp.getEndereco().setLatitude(latitude);
                tmp.getEndereco().setAltitude(altitude);
            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return tmp;
    }

    /**
     * Método que devolve uma lista de todos os Clientes.
     *
     * @return lista de clientes em sistema
     */
    public List<Cliente> getAllClientes() {

        List<Cliente> clientes = new ArrayList<>();

        Integer id;
        Integer nif;
        String nome;
        String email;
        String password;
        Integer idEndereco;
        String rua;
        Double latitude;
        Double longitude;
        Double altitude;
        Integer numeroCartao;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetAllClientes() }"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().execute();

            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);

            while (rs.next()) {

                id = rs.getInt("ID_CLIENTE");
                nome = rs.getString("NOME");
                nif = rs.getInt("NIF");
                email = rs.getString("EMAIL");
                password = rs.getString("PASSWORD");
                numeroCartao = rs.getInt("CARTAOCREDITO");
                idEndereco = rs.getInt("ID_ENDRECO");
                rua = rs.getString("DESIGNACAO");
                latitude = rs.getDouble("LATITUDE");
                longitude = rs.getDouble("LONGITUDE");
                altitude = rs.getDouble("ALTITUDE");

                Cliente tmp = new Cliente();

                tmp.setId(id);
                tmp.setNif(nif);
                tmp.setNome(nome);
                tmp.setPassword(password);
                tmp.setEmail(email);
                tmp.setCartaoCredito(numeroCartao);
                tmp.getEndereco().setIdEndereco(idEndereco);
                tmp.getEndereco().setRua(rua);
                tmp.getEndereco().setLongitude(longitude);
                tmp.getEndereco().setLatitude(latitude);
                tmp.getEndereco().setAltitude(altitude);

                clientes.add(tmp);
            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }
        return clientes;
    }

    /**
     * Método que devolve o email do Estafeta dado um ID de uma Scooter.
     *
     * @param scooterId scooter id
     * @return email do estafeta
     */
    public String getEmailEstafetaByScooterId(Integer scooterId) {

        String email = null;

        try {
            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetEmailEstafetaByScooterId(?) }"));
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

}
