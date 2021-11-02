/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashMap;
import java.util.Map;
import static lapr.project.utils.Constantes.*;

/**
 *
 * @author Groupo 11
 */
public class Encomenda implements Local {

    /**
     * id da encomenda
     */
    private int id;

    /**
     * data da encomenda
     */
    private String dataEncomenda;

    /**
     * nif do cliente
     */
    private int nifCliente;

    /**
     * estado da encomenda
     */
    private int estado;

    /**
     * endereco do cliente
     */
    private Endereco endereco;

    /**
     * peso da encomenda
     */
    private double peso;

    /**
     * mapa com artigo e quantidade do mesmo 
     */
    Map<Artigo, Integer> artigos;

    /**
     * Inicializa o construtor vazio
     */
    public Encomenda() {
        this.id = 0;
        this.dataEncomenda = "";
        this.nifCliente = 0;
        this.estado = 1;
        this.endereco = new Endereco();
        this.peso = 0;
        artigos = new HashMap<>();
    }

    /**
     * Inicializa o construtor completo
     * @param id id da encomenda
     * @param dataEncomenda data da encomenda
     * @param nifCliente nif do cliente
     * @param estado estado da encomenda
     * @param latitude latitude da encomenda
     * @param longitude longitude da encomenda
     * @param altitude altitude da encomenda
     * @param peso peso da encomenda
     */
    public Encomenda(int id, String dataEncomenda, int nifCliente, int estado, double latitude, double longitude, double altitude, double peso) {
        this.id = id;
        this.dataEncomenda = dataEncomenda;
        this.nifCliente = nifCliente;
        this.estado = estado;
        this.endereco = new Endereco("", latitude, longitude, altitude);
        this.peso = peso;
        artigos = new HashMap<>();
    }

    /**
     * Inicializa o construtor 
     * @param id id da encomenda
     * @param peso peso da encomenda
     * @param endereco endereco da encomenda
     */
    public Encomenda(int id, double peso, Endereco endereco) {
        this.id = id;
        this.endereco = new Endereco(endereco);
        this.peso = peso;
        artigos = new HashMap<>();
    }

    /**
     * Retorna id da encomenda
     * @return id da encomenda
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Retorna a data de encomenda
     * @return data de encomenda
     */
    public String getDataEncomenda() {
        return dataEncomenda;
    }

    /**
     * Retorna o NIF
     * @return NIF
     */
    public int getNifCliente() {
        return nifCliente;
    }

    /**
     * Retorna o estado
     * @return estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Retorna o peso
     * @return peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Retorna o endereco
     * @return endereco
     */
    @Override
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * Retorna a lista de artigos
     * @return lista de artigos
     */
    public Map<Artigo, Integer> getListaArtigos() {
        return artigos;
    }

    /**
     * Modifica o id de encomenda
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Modifica a data de encomenda
     * @param dataEncomenda
     */
    public void setDataEncomenda(String dataEncomenda) {
        this.dataEncomenda = dataEncomenda;
    }

    /**
     * Modifica o NIF
     * @param nifCliente
     */
    public void setNifCliente(int nifCliente) {
        this.nifCliente = nifCliente;
    }

    /**
     * Modifica o estado
     * @param estado
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * Modifica o endereco
     * @param endereco
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Modifica o peso
     * @param peso
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Modifica o peso
     */
    public void setPeso() {
        peso = calcularPeso();
    }

    /**
     * calculo do peso
     * @return peso
     */
    private double calcularPeso() {
        double pesotmp = 0;
        for (Map.Entry<Artigo, Integer> e : artigos.entrySet()) {
            if (e.getValue() != null && e.getValue() >= 1) {
                pesotmp = pesotmp + (e.getValue() * e.getKey().getPeso());
            }
        }
        return pesotmp;
    }

    /**
     * boolean : adiciona ou nao adiciona artigo à encomenda
     * @param artigo
     * @param quantidade
     * @return true or false
     */
    public boolean adicionarArtigo(Artigo artigo, int quantidade) {

        if ((getPeso() + artigo.getPeso()) < PESOMAXENCOMENDADEFAULT && quantidade >= 1) {
            if (!artigos.containsKey(artigo)) {
                artigos.put(artigo, quantidade);
                calcularPeso();
                return true;
            }else return false;
            
        }else return false;
    }

    /**
     * Boolean_ remove atrigo
     * @param artigo
     * @return True or False
     */
    public boolean removerArtigo(Artigo artigo) {
        if (artigos.containsKey(artigo)) {
            artigos.remove(artigo);
            calcularPeso();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Boolean: atualiza a quantidade
     * @param artigo
     * @param quantidade
     * @return True or False
     */
    public boolean atualizarQuantidade(Artigo artigo, int quantidade) {
        if (artigos.containsKey(artigo)) {
            artigos.put(artigo, quantidade);
            calcularPeso();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculo do custo
     * @return custo
     */
    public double calcularCusto() {
        double custo = 0;
        for (Map.Entry<Artigo, Integer> e : artigos.entrySet()) {
            if (e.getValue() != null && e.getValue() >= 1 && e.getKey() != null) {
                custo = custo + e.getKey().custoArtigo(e.getValue());
            }
        }
        return custo;
    }

    /**
     * custo do artigo
     * @param a
     * @return custo
     */
    public Double custoArtigo(Artigo a) {

        if (artigos.containsKey(a)) {
            return a.custoArtigo(artigos.get(a));
        } else {
            return 0.0;
        }
    }

    /**
     * Devolve a descrição textual
     * @return Devolve a descrição textual
     */
    @Override
    public String toString() {
        return "Encomenda{" + "id=" + id + ", endereco=" + endereco + '}';
    }

}
