/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import static lapr.project.utils.Constantes.PESOMAXENTREGADEFAULT;

/**
 *
 * @author Groupo 11
 */
public class Entrega {

    /**
     * id de entrega
     */
    private int id;

    /**
     * objeto veiculo
     */
    private Veiculo veiculo;

    /**
     * id de estafeta
     */
    private Integer idEstafeta;

    /**
     * taxa de entrega
     */
    private double taxaEntrega;

    /**
     * data de inicio da entrega
     */
    private String dataInicio;

    /**
     * data de fim da entrega
     */
    private String dataFim;

    /**
     * peso total da carga
     */
    private Double pesoTotalCarga;

    /**
     * encomenda
     */
    private Set<Encomenda> setEncomendas;

    /**
     * id por defeito
     */
    private static final int IDDEFEITO = -1;

    /**
     * data inicio por defeito
     */
    private static final String DATAINICIODEFEITO = "";

    /**
     * data de fim por defeito
     */
    private static final String DATAFIMDEFEITO = "";

    /**
     * id de farmacia
     */
    private Integer idFarmacia;

    /**
     * Inicializa o construtor vazio
     */
    public Entrega() {

        this.id = IDDEFEITO;
        this.veiculo = null;
        this.dataInicio = DATAINICIODEFEITO;
        this.dataFim = DATAFIMDEFEITO;
        this.setEncomendas = new HashSet<>();
        this.pesoTotalCarga = 0.0;
    }

    /**
     * Inicializa o construtor completo
     * @param id id da entrega
     * @param idEstafeta id de estafeta
     * @param taxaEntrega taxa de entrega da entrega
     * @param dataInicio data de inicio da entrega
     * @param dataFim data de fim da entrega
     * @param pesoTotalCarga peso total da carga
     * @param idFarmacia is da farmacia
     */
    public Entrega(int id, Veiculo v, int idEstafeta, int taxaEntrega, String dataInicio, String dataFim, Double pesoTotalCarga, int idFarmacia) {
        this.id = id;
        this.veiculo = v;
        this.idEstafeta = idEstafeta;
        this.taxaEntrega = taxaEntrega;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.pesoTotalCarga = pesoTotalCarga;
        this.setEncomendas = new HashSet<>();
        this.idFarmacia = idFarmacia;

    }

    /**
     * Inicializa o construtor 
     * @param e entrega
     */
    public Entrega(Entrega e) {
        this.id = e.id;
        this.veiculo = e.getVeiculo();
        this.idEstafeta = e.idEstafeta;
        this.taxaEntrega = e.taxaEntrega;
        this.dataInicio = e.dataInicio;
        this.dataFim = e.dataFim;
        this.pesoTotalCarga = e.pesoTotalCarga;
        this.setEncomendas = new HashSet<>();
        this.setEncomendas.addAll(e.setEncomendas);
        this.idFarmacia = e.getIdFarmacia();
    }

    /**
     * retorna id entrega
     * @return id entrega
     */
    public int getId() {
        return id;
    }

    /**
     * retorna veiculo
     * @return scooter
     */
    public Veiculo getVeiculo() {
        if (this.veiculo == null) {
            return null;
        } else {
            return veiculo;
        }
    }

    /**
     * retorna id do estafeta
     * @return id do estafeta
     */
    public int getIdEstafeta() {
        return idEstafeta;
    }

    /**
     * retorna a taxa de entrega
     * @return a taxa de entrega
     */
    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    /**
     * retorna data de inicio
     * @return data de inicio
     */
    public String getDataInicio() {
        return dataInicio;
    }

    /**
     * retorna data de fim
     * @return data de fim
     */
    public String getDataFim() {
        return dataFim;
    }

    /**
     * retorna peso total da carga
     * @return peso total da carga
     */
    public Double getPesoTotalCarga() {
        return pesoTotalCarga;
    }

    /**
     * retorna um set de encomendas
     * @return um set de encomendas
     */
    public Set<Encomenda> getSetEncomendas() {
        return setEncomendas;
    }

    /**
     * retorna id de farmacia
     * @return id de farmacia
     */
    public Integer getIdFarmacia() {
        return idFarmacia;
    }

    /**
     * Modifica o id de entrega
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Modifica o veiculo
     * @param idDrone
     */
    public void setVeiculo(Veiculo v) {
        this.veiculo = v;
    }

    /**
     * Modifica o id de estafeta
     * @param idEstafeta
     */
    public void setIdEstafeta(int idEstafeta) {
        this.idEstafeta = idEstafeta;
    }

    /**
     * Modifica o id da farmacia
     * @param idFarmacia
     */
    public void setIdFarmacia(Integer idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    /**
     * Modifica a taxa de entrega
     * @param taxaEntrega
     */
    public void setTaxaEntrega(double taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    /**
     * Modifica a data de inicio
     * @param dataInicio
     */
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Modifica a data de fim
     * @param dataFim
     */
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Modifica o peso total da carga
     * @param pesoTotalCarga
     */
    public void setPesoTotalCarga(Double pesoTotalCarga) {
        this.pesoTotalCarga = pesoTotalCarga;
    }

    /**
     * Modifica uma lista de encomendas
     * @param setEncomendas
     */
    public void setSetEncomendas(Set<Encomenda> setEncomendas) {
        this.setEncomendas = setEncomendas;
    }

    /**
     * Boolean: adicionar encomendas
     * @param idEncomenda id de encomenda
     * @param pesoEncomenda peso da encomenda
     * @param idEndereco id de endereco
     * @param rua rua
     * @param latitude latitude
     * @param longitude longitude
     * @param altitude altitude
     * @return True or False
     */
    public boolean adicionarEncomenda(int idEncomenda, double pesoEncomenda, int idEndereco, String rua, double latitude, double longitude, double altitude) {
        calcularPeso();
        if ((this.pesoTotalCarga + pesoEncomenda) < PESOMAXENTREGADEFAULT) {
            return this.setEncomendas.add(new Encomenda(idEncomenda, pesoEncomenda, new Endereco(idEndereco, rua, latitude, longitude, altitude)));
        }
        return false;
    }

    /**
     * calcula o peso
     */
    private void calcularPeso() {
        double peso = 0;
        for (Encomenda e : this.setEncomendas) {
            peso = peso + e.getPeso();
        }
        this.pesoTotalCarga = peso;
    }

    /**
     * Boolean: remove uma encomenda
     * @param idEncomenda
     * @return True or False
     */
    public boolean removerEncomenda(Integer idEncomenda) {

        if (idEncomenda != null && idEncomenda > 0) {

            for (Encomenda e : this.setEncomendas) {
                if (Objects.equals(e.getId(), idEncomenda) && this.setEncomendas.remove(e)) {

                    calcularPeso();
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * Devolve a descrição textual
     * @return Devolve a descrição textual
     */
    @Override
    public String toString() {
        return "Entrega{" + "id=" + id + ", veiculo=" + veiculo + ", idEstafeta=" + idEstafeta + ", taxaEntrega=" + taxaEntrega + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", pesoTotalCarga=" + pesoTotalCarga + ", setEncomendas=" + setEncomendas + '}';
    }

}
