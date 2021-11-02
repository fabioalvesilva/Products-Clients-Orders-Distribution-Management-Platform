/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.data.*;
import lapr.project.model.*;

/**
 *
 * @author Groupo 11
 */
public class BaseController {

    /**
     * Utilizador API
     */
    UtilizadorAPI utilizadorAPI;

    /**
     * Farmacia API
     */
    FarmaciaAPI farmaciaAPI;

    /**
     * Scooter API
     */
    ScooterAPI scooterAPI;

    /**
     * Encomenda API
     */
    EncomendaAPI encomendaAPI;

    /**
     * Parque API
     */
    ParqueAPI parqueAPI;

    /**
     * Entrega API
     */
    EntregasAPI entregaAPI;

    /**
     * Drone API
     */
    DroneAPI droneAPI;

    /**
     * Locais API
     */
    LocaisAPI locaisAPI;

    /**
     * Inicializa construtor vazio
     */
    public BaseController() {
        this.utilizadorAPI = new UtilizadorAPI();
        this.farmaciaAPI = new FarmaciaAPI();
        this.scooterAPI = new ScooterAPI();
        this.encomendaAPI = new EncomendaAPI();
        this.parqueAPI = new ParqueAPI();
        this.entregaAPI = new EntregasAPI();
        this.droneAPI = new DroneAPI();
        this.locaisAPI = new LocaisAPI();
    }

    /**
     * Método que permite validar o se utilizador é administrador
     * @param email
     * @return True or false
     */
    public boolean validarAdmin(String email) {
        boolean resultado = false;
        if (!email.isEmpty()) {
            if (this.utilizadorAPI.verificarAdmin(email)) {
                resultado = true;
            } else {
                resultado = false;
            }
        } else {
            resultado = false;
        }
        return resultado;
    }

    /**
     * Método que verifica o login do administrador
     * @param email email do admin
     * @param password password a validar
     * @return True or False
     */
    public boolean loginAdmin(String email, String password) {
        boolean resultado = false;

        if (!email.isEmpty() && !password.isEmpty()) {
            if (this.utilizadorAPI.validarLoginAdmin(email, password)) {
                resultado = true;
            } else {
                resultado = false;
            }
        } else {
            resultado = false;
        }
        return resultado;
    }

    /**
     * Método que valida o longin do utilizador
     * @param email email
     * @param password pass a validar
     * @return True or False
     */
    public boolean loginUtilizador(String email, String password) {
        boolean resultado = false;

        if (!email.isEmpty() && !password.isEmpty()) {
            if (this.utilizadorAPI.validarLoginCliente(email, password)) {
                resultado = true;
            } else {
                resultado = false;
            }
        } else {
            resultado = false;
        }
        return resultado;
    }

    ////////////////////CLIENTES/////////////////

    /**
     *Adição de um novo cliente
     * @param c
     * @return True or False
     */
    public boolean novoCliente(Cliente c) {
        return this.utilizadorAPI.adicionarCliente(c);
    }

    /**
     * Metodo que retorna a lista de cliente em sistema
     * @return Lista de clientes
     */
    public List<Cliente> getAllClientes() {
        return utilizadorAPI.getAllClientes();
    }

    /**
     * Metodo que permite obter o mail do cliente responsavel por determinada encomenda
     * @param e encomenda
     * @return Mail do cliente
     */
    public String getEmailClienteByEncomenda(Encomenda e) {
        return encomendaAPI.getEmailClienteByNif(e);
    }

    /**
     * Método que permite obter a informação do cliente através do seu NIF
     * @param nif NIF do cliente
     * @return 
     */
    public Cliente getClienteByNIF(Integer nif) {
        return utilizadorAPI.getClienteByNif(nif);
    }

    ////////////////////FARMACIAS/////////////////

    /**
     * Adicionar uma nova farmacia
     * @param f farmacia
     * @return id da farmacia recem criada
     */
    public Integer novaFarmacia(Farmacia f) {
        return this.farmaciaAPI.adicionarFarmacia(f);
    }

    /**
     * Remover uma farmacia
     * @param nif NiF da farmacia a remover
     * @return True or False
     */
    public boolean removerFarmacia(Integer nif) {
        return this.farmaciaAPI.removerFarmacia(nif);
    }

    /**
     * Método que permite atualizar a farmacia
     * @param f farmacia
     * @return True or False
     */
    public boolean atualizarFarmacia(Farmacia f) {
        return this.farmaciaAPI.atualizarFarmacia(f);
    }

    /**
     * Metodo que retorna o id do enderenco de uma farmacia
     * @param idFarmacia id de uma farmacia
     * @return id de endereco da farmacia
     */
    public Integer getIdEnderecoPorFarmacia(Integer idFarmacia) {
        return this.farmaciaAPI.getIdEnderecoByFarmacia(idFarmacia);
    }

    /**
     * Metodo que permite obter o id de farmacia por utilizador
     * @param email email do utilizador
     * @return id da farmacia
     */
    public int getIdFarmaciaPorUtilizador(String email) {
        return utilizadorAPI.getIdFarmaciaPorUtilizador(email);
    }

    /**
     * Metodo que permite obter uma lista de farmacia
     * @return lista de farmacia
     */
    public List<Farmacia> getAllFarmacias() {
        return farmaciaAPI.getAllFarmacias();
    }

    /**
     * Transferencia produtos
     * @param idFarmaciaRemetente id da farmacia Remetente
     * @param idFarmaciaDestinatario id da farmacia destinatária
     * @param idProduto id do produto
     * @param quantidade quantidade de produto a transferir
     * @return True or False
     */
    public boolean transferirProduto(int idFarmaciaRemetente, int idFarmaciaDestinatario, int idProduto, int quantidade) {
        return farmaciaAPI.transferirProduto(idFarmaciaRemetente, idFarmaciaDestinatario, idProduto, quantidade);
    }

    ////////////////////ARTIGOS/////////////////

    /**
     * Metodo que permite obter a lista de artigos por farmacia
     * @param idFarmacia id de farmacia
     * @return lista de artigos
     */
    public List<Artigo> getAllArtigos(Integer idFarmacia) {
        return farmaciaAPI.getAllArtigos(idFarmacia);
    }

    /**
     * Metodo que permite obter um artigo pelo id
     * @param idFarmacia id de farmacia
     * @param idArtigo id do artigo
     * @return artigo
     */
    public Artigo getArtigoById(Integer idFarmacia, Integer idArtigo) {
        return farmaciaAPI.getArtigo(idFarmacia, idArtigo);
    }

    /**
     * Metodo que permite obter o stock de um determinado artigo em determinada farmacia
     * @param idFarmacia id da farmacia
     * @param idProduto id do produto
     * @return o stock de um artigo numa farmacia
     */
    public int getStockArtigo(int idFarmacia, int idProduto) {
        return farmaciaAPI.getStockArtigo(idFarmacia, idProduto);
    }

    /**
     * Criar encomenda
     * @param e encomenda
     * @return o id de encomenda recém criada
     */
    public Integer criarEncomenda(Encomenda e) {
        return encomendaAPI.criarEncomenda(e);
    }

    /**
     * Atualização do stock do artigo
     * @param idFarmacia id da farmacia
     * @param idArt id de artigo
     * @param quantidade quantidade
     * @return True or False
     */
    public boolean updateStockArtigo(int idFarmacia, int idArt, int quantidade) {
        return this.farmaciaAPI.updateStockArtigo(idFarmacia, idArt, quantidade);
    }

    ////////////////////ESTAFETAS/////////////////

    /**
     * Adicao de um novo estafeta
     * @param e estafeta
     * @param idFarmacia id da farmacia
     * @return id do estafeta recem criado
     */
    public Integer novoEstafeta(Estafeta e, Integer idFarmacia) {
        return this.utilizadorAPI.adicionarEstafeta(e, idFarmacia);
    }

    /**
     * permite obter o estado de ocupacao do estafeta
     * @param idEstafeta id do estafeta
     * @return True or False
     */
    public boolean getOcupacaoEstafeta(int idEstafeta) {
        return this.farmaciaAPI.verificarOcupacaoEstafeta(idEstafeta);
    }

    ////////////////////ENTREGAS/////////////////

    /**
     * Obter encomendas pendentes 
     * @param idFarmacia id da farmacia
     * @return lista de encomendas pendentes
     */
    public List<Encomenda> getEncomendasPendentes(Integer idFarmacia) {
        return this.entregaAPI.getEncomendasPendentes(idFarmacia);
    }

    /**
     * Obter estafeta disponivel para entrega
     * @param idFarmacia id da farmacia
     * @return o id de um estafeta disponivel
     */
    public Integer getEstafetaDisponivelParaEntrega(Integer idFarmacia) {
        return this.farmaciaAPI.getEstafetaDisponivelPorFarmacia(idFarmacia);
    }

    /**
     * obter scooter para entrega
     * @param idFarmacia id da farmacia
     * @return o id de uma scooter
     */
    public Integer getScooterParaEntrega(Integer idFarmacia) {
        return this.entregaAPI.getScooterParaEntrega(idFarmacia);
    }

    /**
     * obter drone para entrega
     * @param idFarmacia id da farmacia
     * @return o id de um drone
     */
    public Integer getDroneParaEntrega(Integer idFarmacia) {
        return this.entregaAPI.getDroneParaEntrega(idFarmacia);
    }

    /**
     * Registar a entrega
     * @param e entrega
     * @return entrega
     */
    public Entrega registarEntrega(Entrega e) {
        return this.entregaAPI.registarEntrega(e);
    }

    ////////////////////PARQUES//////////////////////

    /**
     * Criação de um parque
     * @param idFarmacia id da farmacia 
     * @param p parque
     * @return id do parque recem criado
     */
    public int criarParque(int idFarmacia, Parque p) { // so devolv id parque
        return this.parqueAPI.adicionarParque(idFarmacia, p);
    }

    /**
     * Atualização da capacidade do parque
     * @param idParque id do parque
     * @param nrLugaresNormal numero de lugares de estacionamento normais
     * @param nrLugaresEletricos numero de lugares de estacionamento com posto de carregamento
     * @return True or False
     */
    public boolean updateCapacidadeParque(int idParque, int nrLugaresNormal, int nrLugaresEletricos) {
        return this.parqueAPI.updateCapacidadeParque(idParque, nrLugaresNormal, nrLugaresEletricos);

    }

    /**
     * Remover parque
     * @param idParque id do parque a remover
     * @return true or false
     */
    public boolean removerParque(int idParque) {
        return this.parqueAPI.removerParque(idParque);
    }

    /**
     * Obter a lista de todos os parques em sistema
     * @return lista de parques
     */
    public List<Parque> getAllParques() {
        return this.parqueAPI.getAllParques();
    }

    //////////////////// SCOOTER //////////////////////

    /**
     * Adicionar uma scooter
     * @param s scooter
     * @param idFarmacia id de farmacia
     * @return true or false
     */
    public boolean adicionarScooter(Scooter s, int idFarmacia) {
        return this.scooterAPI.adicionarScooter(s, idFarmacia);
    }

    /**
     * Atualizar uma scooter
     * @param matricula matricula da scooter
     * @param estadoScooter estado da scooter
     * @return true or false
     */
    public boolean atualizarScooter(String matricula, String estadoScooter) {
        return this.scooterAPI.atualizarScooter(matricula, estadoScooter);
    }

    /**
     * Remover uma scooter
     * @param matricula matricula da scooter
     * @return True or false
     */
    public boolean removerScooter(String matricula) {
        return this.scooterAPI.removerScooter(matricula);
    }

    /**
     * Õbter a scooter atreves da matricula
     * @param matricula matricula do scooter
     * @return scooter
     */
    public Scooter getScooterByMatricula(String matricula) {
        return this.scooterAPI.getScooterByMatricula(matricula);
    }

    /**
     * Ob2ter a scooter pelo id
     * @param idScooter id da scooter
     * @return scooter
     */
    public Scooter getScooterById(Integer idScooter) {
        return this.scooterAPI.getScooterById(idScooter);
    }

    //////////////////// DRONES //////////////////////

    /**
     * Adicionar drone
     * @param d drone
     * @return true or false
     */
    public boolean adicionarDrone(Drone d) {
        return this.droneAPI.adicionarDrone(d);
    }

    /**
     * Atualizar o drone
     * @param nrRegisto numero de registo do drone
     * @param designacaoEstadoDrone estado do drone
     * @return true or false
     */
    public boolean atualizarDrone(Integer nrRegisto, String designacaoEstadoDrone) {
        return this.droneAPI.atualizarDrone(nrRegisto, designacaoEstadoDrone);
    }

    /**
     * Remover o drone
     * @param nrRegisto numero de registo do drone
     * @return true or false
     */
    public boolean removerDrone(Integer nrRegisto) {
        return this.droneAPI.removerDrone(nrRegisto);
    }

    /**
     * Obter o drone pelo seu id
     * @param idDrone id de um drone
     * @return drone
     */
    public Drone getDroneById(Integer idDrone) {
        return this.droneAPI.getDroneById(idDrone);
    }

    /**
     * Obter o drone pelo seu numero de registo
     * @param nRegisto numero de registo
     * @return drone
     */
    public Drone getDroneByNrRegisto(Integer nRegisto) {
        return this.droneAPI.getDroneByNrRegisto(nRegisto);
    }

    //////////// GRAFO ///////////////

    /**
     * Obter todos os locais
     * @return lista de locais
     */
    public List<Local> getAllLocais() {
        List<Local> locais = new ArrayList<>();
        locais.addAll(this.locaisAPI.getAllClientesLocal());
//        locais.addAll(this.locaisAPI.getAllParquesLocal());
        locais.addAll(this.locaisAPI.getAllFarmaciasLocal());
        return locais;
    }

    /**
     * obter uma lista de conexoes
     * @param conexoes conexoes
     * @return lista de conexoes
     */
    public List<int[]> getConnections(List<String[]> conexoes) {

        List<int[]> listaConexoes = new ArrayList<>();

        for (String[] s : conexoes) {
            int[] temp = new int[2];
            temp[0] = this.locaisAPI.getIdEnderecoByRua(s[0]);
            temp[1] = this.locaisAPI.getIdEnderecoByRua(s[1]);
            listaConexoes.add(temp);
        }

        return listaConexoes;
    }

    /**
     * obter um local pelo id da farmacia
     * @param idFarmacia id de uma farmacia
     * @return local
     */
    public Local getLocalFarmaciaById(Integer idFarmacia) {
        return locaisAPI.getFarmaciaById(idFarmacia);
    }

}
