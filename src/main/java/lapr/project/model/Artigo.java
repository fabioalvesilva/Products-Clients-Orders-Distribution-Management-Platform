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
public class Artigo {
   
    /**
     * id do artigo
     */
    private int idArtigo;

    /**
     * designação do artigo (nome)
     */
    private String designacao;

    /**
     * preço unitário do artigo
     */
    private double precoUnitario;

    /**
     * valor do IVA em percentagem
     */
    private double iva;

    /**
     * peso do artigo
     */
    private double peso;

    /**
     * Inicializa o construtor completo
     * @param idArtigo id do artigo
     * @param designacao designação (nome) do artigo
     * @param precoUnitario preço unitário do artigo
     * @param iva valor do iva (em percentagem)
     * @param peso peso do artigo
     */
    public Artigo(int idArtigo, String designacao, double precoUnitario, double iva, double peso) {
        
        this.idArtigo = idArtigo;
        this.designacao = designacao;
        this.precoUnitario = precoUnitario;
        this.iva = iva;
        this.peso = peso;
    }

    /**
     * Inicializa o construtor vazio
     */
    public  Artigo (){
        
    }

    /**
     * Retorna o id do artigo
     * @return id do artigo
     */
    public int getIdArtigo() {
        return idArtigo;
    }

    /**
     * Retorna o nome do artigo
     * @return nome do artigo
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Retorna o preço unitário
     * @return preço unitário
     */
    public double getPrecoUnitario() {
        return precoUnitario;
    }

    /**
     * Retorna o iva do produto (em percentagem)
     * @return  iva
     */
    public double getIva() {
        return iva;
    }

    /**
     * Retorna o peso
     * @return peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Modifica o id de artigo
     * @param idArtigo
     */
    public void setIdArtigo(int idArtigo) {
        this.idArtigo = idArtigo;
    }

    /**
     * Modifica o nome
     * @param designacao
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * Modifica o preco unitario
     * @param precoUnitario
     */
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    /**
     * Modifica o iva
     * @param iva
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * Modifica o peso
     * @param peso
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * calculo do custo do artigo
     * @param quantidade
     * @return custo 
     */
    public double custoArtigo (int quantidade){
       if (quantidade >= 1 ) {
           return quantidade*(precoUnitario + (precoUnitario * (iva/100 ) ));
       }
       else return 0;
   }

    /**
     * Devolve a descrição textual
     * @return Devolve a descrição textual
     */
    @Override
    public String toString() {
        return String.format("Artigo %s  ", designacao);
    } 
}
