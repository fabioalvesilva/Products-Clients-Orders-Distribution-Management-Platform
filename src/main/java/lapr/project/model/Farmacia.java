/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 *
 * @author Groupo 11
 */
public class Farmacia implements Local{
    
    /**
     * id da farmacia
     */
    private Integer id = 0;

    /**
     * nome da farmacia
     */
    private String descricao;

    /**
     * nipc da farmacia
     */
    private Integer nif;

    /**
     * endereco da farmacia
     */
    private Endereco endereco;

    /**
     *id dos seus parques de estacionamento
     */
    private Set<Integer> idsParqueEstacionamento;

    /**
     * Inicializa o construtor completo
     * @param descricao nome da farmacia
     * @param nif NIPC da farmacia
     * @param email email da farmacia
     * @param telefone telefone da farmacia
     * @param endereco endereco da farmacia
     * @param parquesEstacionamento parque da farmacia
     */
    public Farmacia(String descricao, Integer nif, String email, Integer telefone, Endereco endereco, Set<Integer> parquesEstacionamento) {
        this.descricao = descricao;
        this.nif = nif;
        this.endereco = endereco;
        this.idsParqueEstacionamento = parquesEstacionamento;
    }
    
    /**
     * Inicializa o construtor 
     * @param descricao nome da farmacia
     * @param nif NIPC da farmacia
     * @param email email da farmacia
     * @param telefone telefone da farmacia
     * @param endereco endereco da farmacia
     */
    public Farmacia(String descricao, Integer nif, String email, Integer telefone, Endereco endereco) {
        
        this.descricao = descricao;
        this.nif = nif;
        this.endereco = endereco;
        this.idsParqueEstacionamento = new HashSet<>();
    } 

    /**
     * Inicializa o construtor vazio
     */
    public Farmacia() {
        this.endereco = new Endereco();
        this.idsParqueEstacionamento = new HashSet<>();
    }

    /**
     * retorna id da farmacia
     * @return id da farmacia
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * retorna ids dos parques
     * @return set de ids dos parques
     */
    public Set<Integer> getIdsParqueEstacionamento() {
        return idsParqueEstacionamento;
    }

    /**
     * retorna nome
     * @return nome
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * retorna NIPC
     * @return NIPC
     */
    public Integer getNif() {
        return nif;
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
     * Modifica o id da farmacia
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Modifica a descricao
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Modifica o NIF
     * @param nif
     */
    public void setNif(Integer nif) {
        this.nif = nif;
    }

    /**
     * Modifica o endereco
     * @param endereco
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Modifica a lista de parques
     * @param parquesEstacionamento
     */
    public void setIdsParqueEstacionamento(Set<Integer> parquesEstacionamento) {
        this.idsParqueEstacionamento = parquesEstacionamento;
    }

    /**
     * Devolve a descrição textual
     * @return Devolve a descrição textual
     */
    @Override
    public String toString() {
        return "Farmacia{" + "id=" + id + ", descricao=" + descricao + ", nif=" + nif + ", endereco=" + endereco + ", idsParqueEstacionamento=" + idsParqueEstacionamento + '}';
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.descricao);
        hash = 53 * hash + Objects.hashCode(this.endereco);
        return hash;
    }

    /**
     * Método que compara a igualdade do objeto atual com o objeto do mesmo tipo
     * @param obj
     * @return TRue or False
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Farmacia other = (Farmacia) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        /*if (!Objects.equals(this.id, other.id)) {
            return false;
        }*/
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        return true;
    }
    
}
