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
public class Estafeta extends Utilizador {
    
    /**
     * id de estafeta
     */
    private Integer id;

    /**
     * niss do estafeta
     */
    private Integer niss;


    /**
     * Inicializa o construtor completo
     * @param nome nome
     * @param email email
     * @param nif nif
     * @param password password
     * @param id id de estafeta
     * @param niss niss
     */
    public Estafeta(String nome, String email, Integer nif, String password,
            Integer id, Integer niss, Double pesoMax) {
        
        super(nome, email, nif, password);
        this.id = id;
        this.niss = niss;
    }
    
    /**
     * Inicializa o construtor vazio
     */
    public Estafeta() {
    
        super();
        this.id=null;
        this.niss =null;
    }
    
    /**
     * retorna id de estafeta
     * @return id de estafeta
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * retorna NISS
     * @return NISS
     */
    public Integer getNiss() {
        return niss;
    }

    /**
     * modifica o id do estafeta
     * @param id
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Modifica o NISS
     * @param niss
     */
    public void setNiss(Integer niss) {
        this.niss = niss;
    }

    /**
     * Devolve a descrição textual
     * @return Devolve a descrição textual
     */
    @Override
    public String toString() {
        return "Estafeta{" + "id=" + id + ", niss=" + niss + '}';
    }
}
