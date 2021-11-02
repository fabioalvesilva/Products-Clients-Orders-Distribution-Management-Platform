/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Groupo 11
 */
public class Drone implements Veiculo{

    /**
     * id de veiculo
     */
    private Integer id;

    /**
     *id da farmacia
     */
    private Integer idFarmacia;

    /**
     * numero de registo do drone
     */
    private Integer nrRegisto;

    /**
     * modelo do drone
     */
    private String designacaoModelo;

    /**
     * id de tipo de bateria
     */
    private Integer idTipoBateria;

    /**
     * capacidade da bateria
     */
    private Double capacidadeBateria;

    /**
     * carga da bateria atual
     */
    private Double cargaBateriaAtual;

    /**
     * id do estado do drone
     */
    private Integer idEstado;

    /**
     * eficiencia
     */
    private Double eficiencia;

    /**
     * Inicializa o construtor completo
     * @param id id do drone
     * @param idFarmacia id da farmacia
     * @param nrRegisto numero do registo do drone
     * @param designacaoModelo designação do modelo do drine
     * @param idTipoBateria id do tipo de bateria
     * @param capacidadeBateria capacidade da bateria
     * @param cargaBateriaAtual carga da bateria atual
     * @param idEstado id do estado do drone
     * @param eficiencia eficiencia
     */
    public Drone(Integer id, Integer idFarmacia, Integer nrRegisto, String designacaoModelo, Integer idTipoBateria, Double capacidadeBateria, Double cargaBateriaAtual, Integer idEstado, Double eficiencia) {
        this.id = id;
        this.idFarmacia = idFarmacia;
        this.nrRegisto = nrRegisto;
        this.designacaoModelo = designacaoModelo;
        this.idTipoBateria = idTipoBateria;
        this.capacidadeBateria = capacidadeBateria;
        this.cargaBateriaAtual = cargaBateriaAtual;
        this.idEstado = idEstado;
        this.eficiencia = eficiencia;
    }

    /**
     * Inicializa o construtor vazio
     */
    public Drone() {
    }

    /**
     * Retorna o id do drone
     * @return id do drone
     */
    @Override
    public Integer getId() {
        return id;
    }
    
    /**
     * Retorna o id da farmacia
     * @return id da farmacia
     */
    public Integer getIdFarmacia() {
        return idFarmacia;
    }

    /**
     * Retorna numero de registo
     * @return numero de registo
     */
    public Integer getNrRegisto() {
        return nrRegisto;
    }

    /**
     * Retorna nome do modelo
     * @return nome do modelo
     */
    public String getDesignacaoModelo() {
        return designacaoModelo;
    }

    /**
     * Retorna id do tipo de bateria
     * @return id do tipo de bateria
     */
    public Integer getIdTipoBateria() {
        return idTipoBateria;
    }

    /**
     * Retorna capacidade de bateria
     * @return capacidade de bateria
     */
    public Double getCapacidadeBateria() {
        return capacidadeBateria;
    }

    /**
     * Retorna carga da bateria atual
     * @return carga da bateria atual
     */
    public Double getCargaBateriaAtual() {
        return cargaBateriaAtual;
    }

    /**
     * Retorna  id do estado
     * @return  id do estado
     */ 
    public Integer getIdEstado() {
        return idEstado;
    }

    /**
     * Retorna a eficiencia
     * @return a eficiencia
     */
    public Double getEficiencia() {
        return eficiencia;
    }

    /**
     * Modifica o id do drone
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Modifica o id da farmacia
     * @param idFarmacia
     */
    public void setIdFarmacia(Integer idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    /**
     * Modifica o numero de registo
     * @param nrRegisto
     */
    public void setNrRegisto(Integer nrRegisto) {
        this.nrRegisto = nrRegisto;
    }

    /**
     * Modifica o nome do modelo
     * @param designacaoModelo
     */
    public void setDesignacaoModelo(String designacaoModelo) {
        this.designacaoModelo = designacaoModelo;
    }

    /**
     * Modifica o id do tipo de bateria
     * @param idTipoBateria
     */
    public void setIdTipoBateria(Integer idTipoBateria) {
        this.idTipoBateria = idTipoBateria;
    }

    /**
     * Modifica a capacidade da bateria
     * @param capacidadeBateria
     */
    public void setCapacidadeBateria(Double capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }

    /**
     * Modifica carga da bateria ataual
     * @param cargaBateriaAtual
     */
    public void setCargaBateriaAtual(Double cargaBateriaAtual) {
        this.cargaBateriaAtual = cargaBateriaAtual;
    }

    /** 
     * Modifica o id do estado
     * @param idEstado
     */
    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    /**
     * Modifica a eficiencia
     * @param eficiencia
     */
    public void setEficiencia(Double eficiencia) {
        this.eficiencia = eficiencia;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Drone{" + "id=" + id + ", idFarmacia=" + idFarmacia + ", nrRegisto=" + nrRegisto + ", designacaoModelo=" + designacaoModelo + ", idTipoBateria=" + idTipoBateria + ", capacidadeBateria=" + capacidadeBateria + ", cargaBateriaAtual=" + cargaBateriaAtual + ", idEstado=" + idEstado + ", eficiencia=" + eficiencia + '}';
    }

    /**
     * retorna o veiculo
     * @return veiculo
     */
    @Override
    public Veiculo getVeiculo() {
        return this; 
    }

}
