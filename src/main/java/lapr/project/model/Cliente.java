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
public class Cliente extends Utilizador implements Local {

    /**
     * credito da conta cliente
     */
    private Integer credito;

    /**
     * cartao credito do cliente
     */
    private Integer cartaoCredito;

    /**
     * endereco
     */
    private Endereco endereco;

    /**
     * Inicializa o construtor completo
     *
     * @param nome nome do cliente
     * @param email email do cliente
     * @param nif nf do cliente
     * @param password password da conta cliente
     * @param rua rua do cliente
     * @param latitude latitude da morada do cliente
     * @param longitude longitude da morada do cliente
     * @param altitude altitude da morada do cliente
     * @param numeroCartaoCredito numero do cartão de credito do cliente
     */
    public Cliente(String nome, String email, Integer nif, String password, Integer idEndereco, String rua, Double latitude, Double longitude, Double altitude,
            Integer numeroCartaoCredito) {

        super(nome, email, nif, password);
        this.credito = 0;
        this.cartaoCredito = numeroCartaoCredito;
        this.endereco = new Endereco(idEndereco, rua, latitude, longitude, altitude);
    }

    /**
     * Inicializa o construtor vazio
     */
    public Cliente() {
        super();
        this.credito = null;
        this.cartaoCredito = null;
        this.endereco = new Endereco();
    }

    /**
     * Retorna o credito
     *
     * @return
     */
    public Integer getCredito() {
        return credito;
    }

    /**
     * Retorna o cartao de credito
     *
     * @return
     */
    public int getCartaoCredito() {
        return cartaoCredito;
    }

    /**
     * Modifica o credito
     *
     * @param credito
     */
    public void setCredito(Integer credito) {
        this.credito = credito;
    }

    /**
     * Modifica o numero de cartao de credito
     *
     * @param cartaoCredito
     */
    public void setCartaoCredito(int cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    /**
     * Modifica o endereco
     *
     * @param endereco
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Retorna endereco
     *
     * @return endereco
     */
    @Override
    public Endereco getEndereco() {
        return this.endereco;
    }

    /**
     * retorna id de cliente
     *
     * @return id de cliente
     */
    @Override
    public Integer getId() {
        return super.getId();
    }
    
    
}
