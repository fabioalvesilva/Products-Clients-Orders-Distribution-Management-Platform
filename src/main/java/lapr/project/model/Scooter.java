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
public class Scooter implements Veiculo{

    /**
     * id do scooter
     */
    private Integer id;

    /**
     * matricula do scooter
     */
    private String matricula;

    /**
     * modelo da scooter
     */
    private String modelo;

    /**
     * id do tipo de bateria
     */
    private Integer idTipoBateria;

    /**
     * capacidade de bateria
     */
    private Integer capacidadeBateria;

    /**
     * id do estado do scooter
     */
    private Integer idEstado;

    /**
     * capacidade atual do scooter
     */
    private Integer capacidadeAtual;

    /**
     * eficiencia da bateria
     */
    private Integer eficienciaBateria;

    /**
     * peso da scooter
     */
    private Double peso;

    /**
     * area frontal da scooter
     */
    private Double areaFrontal;
    
    private static Integer PERCENTAGEM_MAX = 100;
    /**
     * Inicializa o construtor completo
     * @param id id da scooter
     * @param matricula matricula
     * @param modelo modelo
     * @param idTipoBateria id do tipo de bateria
     * @param capacidadeBateria capacidade de bateria
     * @param idEstado id do estado da scooter
     * @param capacidadeAtual capacidade atual da scooter
     * @param eficienciaBateria eficiencia da bateria
     * @param peso peso da scooter
     * @param areaFrontal area frontal da scooter
     */
    public Scooter(Integer id, String matricula, String modelo, Integer idTipoBateria, Integer capacidadeBateria, Integer idEstado, Integer capacidadeAtual, Integer eficienciaBateria, Double peso, Double areaFrontal) {
        this.id = id;
        this.matricula = matricula;
        this.modelo = modelo;
        this.idTipoBateria = idTipoBateria;
        this.capacidadeBateria = capacidadeBateria;
        this.idEstado = idEstado;
        this.capacidadeAtual = capacidadeAtual;
        this.eficienciaBateria = eficienciaBateria;
        this.peso = peso;
        this.areaFrontal = areaFrontal;
    }

    /**
     * Inicializa o construtor vazio
     */
    public Scooter() {
    }

    /**
     * retorna matricula
     * @return matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * retorna id de scooter
     * @return id de scooter
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * retorna modelo
     * @return modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * retorna tipo de bateria
     * @return tipo de bateria
     */
    public Integer getIdTipoBateria() {
        return idTipoBateria;
    }

    /**
     * retorna capacidade de bateria
     * @return capacidade de bateria
     */
    public Integer getCapacidadeBateria() {
        return capacidadeBateria;
    }

    /**
     * retorna id do estado
     * @return  id do estado
     */
    public Integer getIdEstado() {
        return idEstado;
    }

    /**
     * retorna capacidade atual
     * @return capacidade atual
     */
    public Integer getCapacidadeAtual() {
        return this.capacidadeAtual;
    }
    /**
     * retorna capacidade de bateria
     * @return capacidade de bateria
     */
    public Integer getCapacidadeRestante() {
        return PERCENTAGEM_MAX - this.capacidadeAtual;
    }


    /**
     * retorna eficiencia da bateria
     * @return eficiencia da bateria
     */
    public Integer getEficienciaBateria() {
        return eficienciaBateria;
    }

    /**
     * retorna peso
     * @return peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * retorna area frontal
     * @return area frontal
     */
    public Double getAreaFrontal() {
        return areaFrontal;
    }

    /**
     * Modifica o id de scooter
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Modifica a matricula
     * @param matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Modifica o modelo
     * @param modelo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Modifica o id do tipo de bateria
     * @param idTipoBateria
     */
    public void setIdTipoBateria(Integer idTipoBateria) {
        this.idTipoBateria = idTipoBateria;
    }

    /**
     * Modifica a capacidade de bateria
     * @param capacidadeBateria
     */
    public void setCapacidadeBateria(Integer capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }

    /**
     * Modifica o id do estado
     * @param idEstado
     */
    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    /**
     * Modifica a capacidade atual
     * @param capacidadeAtual
     */
    public void setCapacidadeAtual(Integer capacidadeAtual) {
        this.capacidadeAtual = capacidadeAtual;
    }

    /**
     * Modifica a eficiencia da bateria
     * @param eficienciaBateria
     */
    public void setEficienciaBateria(Integer eficienciaBateria) {
        this.eficienciaBateria = eficienciaBateria;
    }

    /**
     * Modifica o peso
     * @param peso
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * Modifica a area frontal
     * @param areaFrontal
     */
    public void setAreaFrontal(Double areaFrontal) {
        this.areaFrontal = areaFrontal;
    }

    /**
     * calcula a capacidade atual
     * @return capacidade atual
     */
    public Integer calcularCapacidadeAtual() {
        if (this.id == null){
            return null;
        }
        Integer capacidade = this.capacidadeBateria - (this.capacidadeAtual * this.capacidadeBateria);
        return capacidade;
    }

    /**
     * Devolve a descrição textual
     * @return Devolve a descrição textual
     */
    @Override
    public String toString() {
        if (this.id == null) {
            return "";
        } else {
            return "Scooter{" + "id=" + id + ", matricula=" + matricula + ", modelo=" + modelo + ", idTipoBateria=" + idTipoBateria + ", capacidadeBateria=" + capacidadeBateria + ", idEstado=" + idEstado + ", capacidadeAtual=" + capacidadeAtual + ", eficienciaBateria=" + eficienciaBateria + '}';
        }
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
