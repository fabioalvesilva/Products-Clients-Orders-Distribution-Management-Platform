/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Artigo;
import java.sql.SQLException;
import lapr.project.model.Estafeta;
import lapr.project.model.Farmacia;
import lapr.project.ui.Log;
import oracle.jdbc.OracleTypes;

/**
 * Classe responsável às ligações à BD para as funções relativas à Farmacia.
 */
public class FarmaciaAPI extends DataHandler {

    /**
     * Inicialização do construtor vazio
     */
    public FarmaciaAPI() {
        super();
    }

    //////////////////////FARMACIA///////////////////////
    /**
     * Método para adicionar uma Farmacia na Base de Dados
     *
     * @param f farmacia
     * @return id da farmacia recem criada
     */
    public Integer adicionarFarmacia(Farmacia f) {
        return adicionarFarmacia(f.getDescricao(), f.getNif(), f.getEndereco().getRua(),
                f.getEndereco().getLatitude(), f.getEndereco().getLongitude(), f.getEndereco().getAltitude());
    }

    /**
     * adicionar farmacia
     * @param descricao nome da farmacia
     * @param nif NIPC da farmacia
     * @param rua rua da farmacia
     * @param latitude latitude da farmacia
     * @param longitude longitude da farmacia
     * @param altitude altitude da farmacia
     * @return id da farmacia recem criada
     */
    private Integer adicionarFarmacia(String descricao, Integer nif,
            String rua, Double latitude, Double longitude, Double altitude) {

        Integer idFarmacia = -1;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncRegistarFarmacia(?,?,?,?,?,?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setString(2, descricao);
            this.getCallStmt().setInt(3, nif);
            this.getCallStmt().setString(4, rua);
            this.getCallStmt().setDouble(5, latitude);
            this.getCallStmt().setDouble(6, longitude);
            this.getCallStmt().setDouble(7, altitude);
            this.getCallStmt().execute();

            idFarmacia = (Integer) this.getCallStmt().getObject(1);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return idFarmacia;
    }

    /**
     * Método para atualizar uma Farmácia na Base de Dados
     *
     * @param f farmacia
     * @return true or false
     */
    public boolean atualizarFarmacia(Farmacia f) {
        return atualizarFarmacia(f.getDescricao(), f.getNif(), f.getEndereco().getRua(),
                f.getEndereco().getLatitude(), f.getEndereco().getLongitude(), f.getEndereco().getAltitude());
    }

    /**
     * Atualizar farmacia
     * @param descricao nome da farmacia
     * @param nif NIPC
     * @param rua rua
     * @param latitude latitude 
     * @param longitude longitude
     * @param altitude altitude
     * @return true or false
     */
    private boolean atualizarFarmacia(String descricao, Integer nif,
            String rua, Double latitude, Double longitude, Double altitude) {

        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcAtualizarFarmacia(?,?,?,?,?,?)}"));
            this.getCallStmt().setString(1, descricao);
            this.getCallStmt().setInt(2, nif);
            this.getCallStmt().setString(3, rua);
            this.getCallStmt().setDouble(4, latitude);
            this.getCallStmt().setDouble(5, longitude);
            this.getCallStmt().setDouble(6, altitude);
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
     * Método para remover uma Farmácia na Base de Dados
     *
     * @param nif NIPC da farmacia
     * @return true or false
     */
    public boolean removerFarmacia(Integer nif) {

        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcRemoverFarmacia(?)}"));
            this.getCallStmt().setInt(1, nif);

            resultado = true;

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return resultado;
    }

    /**
     * Método que retorna o ID do Endereço de uma Farmácia
     *
     * @param idFarmacia id da farmacia
     * @return id de endereco da farmacia
     */
    public int getIdEnderecoByFarmacia(Integer idFarmacia) {

        Integer idEndereco = -1;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetIdEnderecoByFarmacia(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setInt(2, idFarmacia);
            this.getCallStmt().execute();

            idEndereco = (Integer) this.getCallStmt().getObject(1);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return idEndereco;

    }

    /**
     * Método que retorna uma lista com todas as Farmácias da Base de Dados.
     *
     * @return lista de farmacias em sistema
     */
    public List<Farmacia> getAllFarmacias() {

        List<Farmacia> farmacias = new ArrayList<>();

        try {

            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetAllFarmacia()}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().execute();

            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);

            Integer idFarmacia;
            String designacao;
            Integer idEndereco;
            Integer nif;

            while (rs.next()) {

                Farmacia tmp = new Farmacia();

                idFarmacia = rs.getInt("ID_FARMACIA");
                designacao = rs.getString("DESIGNACAO");
                idEndereco = rs.getInt("ID_ENDERECO");
                nif = rs.getInt("NIPC");

                tmp.setId(idFarmacia);
                tmp.setDescricao(designacao);
                tmp.setNif(nif);
                tmp.getEndereco().setIdEndereco(idEndereco);

                farmacias.add(tmp);
            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {

            closeAll();
        }

        return farmacias;
    }

    //////////////////////ARTIGOS///////////////////////
    /**
     * Método que retorna uma lista de todos os artigos de uma Farmácia.
     *
     * @param idFarmacia id de farmacia
     * @return lista dos artigos de uma determinada farmacia
     */
    public List<Artigo> getAllArtigos(Integer idFarmacia) {

        ArrayList<Artigo> artigos = new ArrayList<>();

        try {

            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncListaProdutosFarmacia(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().setInt(2, idFarmacia);
            this.getCallStmt().execute();
            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);

            Integer idArtigo;
            String designacao;
            Double precoUnitario;
            Integer iva;
            Double peso;

            while (rs.next()) {

                Artigo tmp = new Artigo();

                idArtigo = rs.getInt("ID_ARTIGO");
                designacao = rs.getString("DESIGNACAO");
                precoUnitario = rs.getDouble("PRECOUNITARIO");
                iva = rs.getInt("IVA");
                peso = rs.getDouble("PESO");

                tmp.setIdArtigo(idArtigo);
                tmp.setDesignacao(designacao);
                tmp.setPrecoUnitario(precoUnitario);
                tmp.setIva(iva);
                tmp.setPeso(peso);

                artigos.add(tmp);
            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {

            closeAll();
        }

        return artigos;
    }

    /**
     * Método que devolve um Artigo através do ID.
     *
     * @param idFarmacia id da farmacia
     * @param idArt id do artigo
     * @return artigo
     */
    public Artigo getArtigo(int idFarmacia, int idArt) {
        Artigo a = new Artigo();

        try {

            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetArtigoById(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);
            this.getCallStmt().setInt(2, idArt);
            this.getCallStmt().execute();
            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);

            Integer idArtigo;
            String designacao;
            Double precoUnitario;
            Integer iva;
            Double peso;

            while (rs.next()) {

                idArtigo = rs.getInt("ID_ARTIGO");
                designacao = rs.getString("DESIGNACAO");
                precoUnitario = rs.getDouble("PRECOUNITARIO");
                iva = rs.getInt("IVA");
                peso = rs.getDouble("PESO");
                a.setIdArtigo(idArtigo);
                a.setDesignacao(designacao);
                a.setPrecoUnitario(precoUnitario);
                a.setIva(iva);
                a.setPeso(peso);
            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return a;
    }

    /**
     * Método que atualiza o stock de um dado Artigo de uma Farmácia.
     *
     * @param idFarmacia id da farmacia
     * @param idArt id do artigo
     * @param quantidade quantidade do artigo
     * @return true or false
     */
    public boolean updateStockArtigo(int idFarmacia, int idArt, int quantidade) {

        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcAtualizarStock(?, ? ,? )}"));
            this.getCallStmt().setInt(1, idArt);
            this.getCallStmt().setInt(2, idFarmacia);
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
     * Método que remove um Artigo da Base de Dados.
     *
     * @param idArt id do artigo
     * @return true or false
     */
    public boolean RemoverArtigo(int idArt) {
        boolean resultado = false;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{call prcRemoverArtigo(?)}"));
            this.getCallStmt().setInt(1, idArt);
            resultado = this.getCallStmt().execute();

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return resultado;
    }

    /**
     * Método que devolve o stock de um dado Artigo de uma Farmácia.
     *
     * @param idFarmacia id de farmacia
     * @param idArtigo id do artigo
     * @return stock de um determinado artigo de uma determinada farmacia
     */
    public int getStockArtigo(int idFarmacia, int idArtigo) {

        Integer qtdStock = -1;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetStock(?, ?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setInt(2, idArtigo);
            this.getCallStmt().setInt(3, idFarmacia);
            this.getCallStmt().execute();

            qtdStock = (Integer) this.getCallStmt().getObject(1);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }
        if (qtdStock != null && qtdStock > -1) {
            return qtdStock;
        }
        return 0;
    }

    /**
     * Método responsável pela transferência de produtos entre farmácias.
     *
     * @param idFarmaciaRemetente id de farmacia remetente
     * @param idFarmaciaDestinatario id da farmacia destinataria
     * @param idProduto id do produto a transferir
     * @param idQuantidade quandidada a transferir
     * @return true or false
     */
    public boolean transferirProduto(int idFarmaciaRemetente, int idFarmaciaDestinatario, int idProduto, int idQuantidade) {

        int resultado = 0;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncTransferenciaDeProdutos(?,?,?,?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setInt(2, idFarmaciaRemetente);
            this.getCallStmt().setInt(3, idFarmaciaDestinatario);
            this.getCallStmt().setInt(4, idProduto);
            this.getCallStmt().setInt(5, idQuantidade);

            this.getCallStmt().execute();

            resultado = this.getCallStmt().getInt(1);

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }

        return resultado == 1 ? true : false;
    }

    //////////////////////Estafeta///////////////////////
    /**
     * Método para verificar se um Estafeta está ocupado.
     *
     * @param idEstafeta id de estafeta
     * @return  true or false
     */
    public boolean verificarOcupacaoEstafeta(int idEstafeta) {

        Boolean ocupacao = true;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncestafetadisponivel(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setInt(2, idEstafeta);
            this.getCallStmt().execute();
            Integer result = (Integer) this.getCallStmt().getObject(1);
            if (result != null) {
                ocupacao = result > 0;
            }
        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }
        return ocupacao;
    }

    /**
     * Método que retorna uma lista de Estafetas de uma dada farmácia.
     *
     * @param idFarmacia id de farmacia
     * @return lista de estafetas de uma determinada farmacia
     */
    public List<Estafeta> getEstafetasByFarmacia(int idFarmacia) {

        List<Estafeta> estafetas = new ArrayList<>();

        try {

            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetEstafetasByFarmacia(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.CURSOR);

            this.getCallStmt().execute();

            ResultSet rs = (ResultSet) this.getCallStmt().getObject(1);

            String nome;
            String email;
            Integer nif;
            Integer idEstafeta;
            Integer niss;

            while (rs.next()) {

                Estafeta tmp = new Estafeta();

                idEstafeta = rs.getInt("ID_FARMACIA");
                nome = rs.getString("DESIGNACAO");
                email = rs.getString("ID_ENDERECO");
                nif = rs.getInt("NIF");
                niss = rs.getInt("NISS");

                tmp.setId(idEstafeta);
                tmp.setNome(nome);
                tmp.setEmail(email);
                tmp.setNif(nif);
                tmp.setNiss(niss);

                estafetas.add(tmp);
            }

        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {

            closeAll();
        }

        return estafetas;
    }

    /**
     * Método que devolve um ID de um Estafeta disponível de uma dada Farmácia.
     *
     * @param idFarmacia id de farmacia
     * @return id de estafeta disponivel
     */
    public Integer getEstafetaDisponivelPorFarmacia(Integer idFarmacia) {
        Integer idEstafeta = 0;

        try {

            // Ligação à base de dados
            getConnection();

            // Query
            this.setCallStmt(this.getConnection().prepareCall("{ ? = call fncGetEstafetaDisponivelPorFarmacia(?)}"));
            this.getCallStmt().registerOutParameter(1, OracleTypes.INTEGER);
            this.getCallStmt().setInt(2, idFarmacia);
            this.getCallStmt().execute();
            Integer result = (Integer) this.getCallStmt().getObject(1);
            if (result != null) {
                idEstafeta = result;
            }
        } catch (SQLException e) {
            Log.logError(e.toString());
        } finally {
            closeAll();
        }
        return idEstafeta;

    }
}
