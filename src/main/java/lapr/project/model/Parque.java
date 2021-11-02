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
public class Parque implements Local {
    
    /**
     * id do parque
     */
    private Integer id = 0;

    /**
     * id do tipo de parque
     */
    private Integer idTipoParque;

    /**
     * numero de lugares de estacionamento eletrico
     */
    private Integer ocupacaoLugaresEletricos;

    /**
     * numero de lugares de estacionamento de lugares normais
     */
    private Integer ocupacaoLugaresNormais;

    /**
     * maximo de lugares eletricos
     */
    private Integer maxLugaresEletricos;

    /** 
     * maximo de lugares normais
     */
    private Integer maxLugaresNormais;

    /**
     * capacidade de tensao do parque
     */
    private Integer capacidadeTensao;

    /**
     * endereco do parque
     */
    private Endereco endereco = new Endereco();

    /**
     * Inicializa o construtor vazio
     */
    public Parque() {
    }

    /**
     * Inicializa o construtor completo
     * @param idTipoParque id do tipo de parque
     * @param ocupacaoLugaresEletricos numero de lugares com posto de carregamento
     * @param ocupacaoLugaresNormais numero de lugares normais
     * @param maxLugaresEletricos max de lugares com posto de carregamento
     * @param maxLugaresNormais max de lugares normais
     * @param capacidadeTensao capacidade de tensao do parque
     * @param endereco endereco do parque
     */
    public Parque(Integer idTipoParque, Integer ocupacaoLugaresEletricos, Integer ocupacaoLugaresNormais, Integer maxLugaresEletricos, Integer maxLugaresNormais, Integer capacidadeTensao, Endereco endereco) {
        this.idTipoParque = idTipoParque;
        this.ocupacaoLugaresEletricos = ocupacaoLugaresEletricos;
        this.ocupacaoLugaresNormais = ocupacaoLugaresNormais;
        this.maxLugaresEletricos = maxLugaresEletricos;
        this.maxLugaresNormais = maxLugaresNormais;
        this.capacidadeTensao = capacidadeTensao;
        this.endereco = endereco;
    }
    
    /**
     * Inicializa o construtor 
     * @param idParque id do parque
     * @param idTipoParque id do tipo de parque
     * @param ocupacaoLugaresEletricos numero de liugares com posto de carregamento
     * @param ocupacaoLugaresNormais numero de lugares com lugares normais
     * @param maxLugaresEletricos max lugares eletricos
     * @param maxLugaresNormais max de lugares normais
     * @param capacidadeTensao capacidade de tensao do parque
     * @param endereco endereco do parque
     */
    public Parque(Integer idParque, Integer idTipoParque, Integer ocupacaoLugaresEletricos, Integer ocupacaoLugaresNormais, Integer maxLugaresEletricos, Integer maxLugaresNormais, Integer capacidadeTensao, Endereco endereco) {
        this.id = idParque;
        this.idTipoParque = idTipoParque;
        this.ocupacaoLugaresEletricos = ocupacaoLugaresEletricos;
        this.ocupacaoLugaresNormais = ocupacaoLugaresNormais;
        this.maxLugaresEletricos = maxLugaresEletricos;
        this.maxLugaresNormais = maxLugaresNormais;
        this.capacidadeTensao = capacidadeTensao;
        this.endereco = endereco;
    }

    /**
     * retorna id de parque
     * @return id de parque
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * retorna id de tipo de parque
     * @return id de tipo de parque
     */
    public Integer getIdTipoParque() {
        return idTipoParque;
    }

    /**
     * retorna ocupação de lugares eletricos
     * @return ocupação de lugares eletricos
     */
    public Integer getOcupacaoLugaresEletricos() {
        return ocupacaoLugaresEletricos;
    }

    /**
     * retorna ocupação de lugares normais
     * @return ocupação de lugares normais
     */
    public Integer getOcupacaoLugaresNormais() {
        return ocupacaoLugaresNormais;
    }

    /**
     * retorna max lugares eletricos
     * @return max lugares eletricos
     */
    public Integer getMaxLugaresEletricos() {
        return maxLugaresEletricos;
    }

    /**
     * retorna max lugares normais
     * @return max lugares normais
     */
    public Integer getMaxLugaresNormais() {
        return maxLugaresNormais;
    }

    /**
     * retorna capacidade tensao
     * @return capacidade tensao
     */
    public Integer getCapacidadeTensao() {
        return capacidadeTensao;
    }

    /**
     * retorna endereco
     * @return endereco
     */
    @Override
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * Modifica o id de parque 
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Modifica o id de tipo de parque
     * @param idTipoParque
     */
    public void setIdTipoParque(Integer idTipoParque) {
        this.idTipoParque = idTipoParque;
    }
    
    /**
     * Modifica a ocupação de lugares eletricos
     * @param ocupacaoLugaresEletricos
     */
    public void setOcupacaoLugaresEletricos(Integer ocupacaoLugaresEletricos) {
        this.ocupacaoLugaresEletricos = ocupacaoLugaresEletricos;
    }

    /**
     * Modifica a ocupação de lugares normais
     * @param ocupacaoLugaresNormais
     */
    public void setOcupacaoLugaresNormais(Integer ocupacaoLugaresNormais) {
        this.ocupacaoLugaresNormais = ocupacaoLugaresNormais;
    }

    /**
     * Modifica o maximos de lugares eletricos
     * @param maxLugaresEletricos
     */
    public void setMaxLugaresEletricos(Integer maxLugaresEletricos) {
        this.maxLugaresEletricos = maxLugaresEletricos;
    }

    /**
     * Modifica o maximo de lugares normais
     * @param maxLugaresNormais
     */
    public void setMaxLugaresNormais(Integer maxLugaresNormais) {
        this.maxLugaresNormais = maxLugaresNormais;
    }

    /**
     * Modifica a capacidade de tensao
     * @param capacidadeTensao
     */
    public void setCapacidadeTensao(Integer capacidadeTensao) {
        this.capacidadeTensao = capacidadeTensao;
    }

    /**
     * Modifica o endereco
     * @param endereco
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Devolve a descrição textual
     * @return Devolve a descrição textual
     */
    @Override
    public String toString() {
        return "Parque{" + "id=" + id + ", id_TipoParque=" + idTipoParque + ", ocupacaoLugaresEletricos=" + ocupacaoLugaresEletricos + ", ocupacaoLugaresNormais=" + ocupacaoLugaresNormais + ", maxLugaresEletricos=" + maxLugaresEletricos + ", maxLugaresNormais=" + maxLugaresNormais + '}';
    }
    
}
