/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;
import java.util.HashMap;
import java.util.Map;
import lapr.project.utils.Constantes;

/**
 *
 * @author Groupo 11
 */
public class Fatura {
    
    /**
     * id da fatura
     */
    private static final Integer ID = null;

    /**
     * NIF do cliente
     */
    private static final Integer NIF = null;

    /**
     * data da fatura
     */
    private static final String DATA = null;

    /**
     * valor da pago
     */
    private static final Double VALORPAGO = null; //Valor Com pontos 

    /**
     * valor total
     */
    private static final Double VALORTOTAL = null;//Valor Sem pontos 

    /**
     * taxa de entrega
     */
    private static final double TAXAENTREGA = Constantes.TAXAENTREGADEFAULT;
    
    /**
     * id da fatura
     */
    private Integer id;

    /**
     * NIF do cliente
     */
    private Integer nif;

    /**
     * data da fatura
     */
    private String data;

    /**
     * valor pago com pontos
     */
    private Double valorPago; //Valor Com pontos 

    /**
     * valor total da fatura
     */
    private Double valorTotal; //Valor Sem pontos 

    /**
     * taxa da entrega
     */
    private double taxaentrega;

    /**
     * mapa com linhas da fatura
     */
    private Map<String, Double> linhaFatura;  

    /**
     * Inicializa o construtor vazio
     */
    public Fatura() {
        this.id = ID;
        this.nif = NIF;
        this.data = DATA;
        this.valorPago = VALORPAGO;
        this.valorTotal = VALORTOTAL;
        this.taxaentrega = TAXAENTREGA;
       linhaFatura = new HashMap<>();
    }

    /**
     * Inicializa o construtor completo
     * @param id id da fatura
     * @param nif NIF do cliente
     * @param data data da fatura
     * @param valorTotal valor total da fatura
     */
    public Fatura(Integer id, Integer nif, String data, Double valorTotal) {
        this.id = id;
        this.nif = nif;
        this.data = data;
        this.valorTotal = valorTotal;
        this.taxaentrega = TAXAENTREGA;
        this.valorPago = VALORPAGO;
        linhaFatura = new HashMap<>();
    }

    /**
     * retorna id da fatura
     * @return id da fatura
     */
    public Integer getId() {
        return id;
    }

    /**
     * retorna NIF
     * @return NIF
     */
    public Integer getNif() {
        return nif;
    }

    /**
     * retorna Data
     * @return Data
     */
    public String getData() {
        return data;
    }

    /**
     * retorna valor pago
     * @return valor pago
     */
    public Double getValorPago() {
        return valorPago;
    }

    /**
     * retorna valor total
     * @return valor total
     */
    public Double getValorTotal() {
        return valorTotal;
    }

    /**
     * Modifica o id da fatura
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Modifica o NIF
     * @param nif
     */
    public void setNif(Integer nif) {
        this.nif = nif;
    }

    /**
     * Modifica a data
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Modifica o valor pago
     * @param valorPago
     */
    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    /**
     * Modifica o valor total
     */
    public void setValorTotal() {
        this.valorTotal = calcularValorTotal();
    }

    /**
     * calcular o valor total
     * @return valor total
     */
    public double calcularValorTotal() {
        double tmp = taxaentrega;
        for (Map.Entry<String, Double> e : this.linhaFatura.entrySet()) {
            tmp = tmp + e.getValue();
        }
        
        return tmp;
    }

    /**
     * Adiciona uma linha na fatura
     * @param descricao
     * @param valor
     */
    public void adicionarLinhaFatura(String descricao, double valor) {
        linhaFatura.put(descricao, valor);
        setValorTotal();
    }

    /**
     * Devolve a descrição textual
     * @return Devolve a descrição textual
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fatura: ").append(id).append(" Data: ").append(data).append("\n");
        sb.append("NIF: ").append(nif).append("\n");
        sb.append("Descriçao\t\t\t\tPreço \n");
        if (!linhaFatura.isEmpty()) {
            for (Map.Entry<String, Double> e : this.linhaFatura.entrySet()) {
                sb.append(e.getKey()).append(" \t ").append(e.getValue()).append("\n");
            }
        }
        sb.append("Valor Total: ").append(valorTotal).append("\n");
        return sb.toString();
    }

}
