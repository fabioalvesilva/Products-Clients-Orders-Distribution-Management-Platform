/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grupo 11
 */
public class GrafoMatrix {

    /**
     * vertices
     */
    private int vertices;

    /**
     * peso
     */
    private double[][] pesoM;

    /**
     * sequencia
     */
    private int[][] sequenciaM;

    /**
     * calculada
     */
    private boolean calculada;

    /**
     * Inicialização do construtor completo
     * @param vertices vertices
     */
    public GrafoMatrix(int vertices) {
        this.vertices = vertices;
        this.pesoM = new double[vertices][vertices];
        this.sequenciaM = new int[vertices][vertices];
        setMatrices();
    }

    /**
     * boolean calculada
     * @return true or false
     */
    public boolean isCalculada() {
        return calculada;
    }

    /**
     * reset matriz
     */
    public void resetMatrices (){
        setMatrices();
    }
    
    /**
     * modifica a matriz
     */
    private void setMatrices() {
        for (int i = 0; i < sequenciaM.length; i++) {
            for (int j = 0; j < sequenciaM.length; j++) {
                if (i != j){
                    sequenciaM[i][j] = j + 1;
                }
                pesoM[i][j] = Double.POSITIVE_INFINITY;
                
            }
        }
    }

    /**
     * obter o peso
     * @param from origem
     * @param to destino
     * @return peso
     */
    public double getPeso(int from, int to) {
        if (from > 0 && to > 0 && from <= vertices && to <= vertices) {
            return pesoM[from - 1][to - 1];
        }
        return -1;
    }
    
    /**
     * Verifica conexao
     * @param from origem
     * @param to destino
     * @return true or false
     */
    public boolean temConexao(int from, int to){
        if (from > 0 && to > 0 && from <= vertices && to <= vertices) {
            return pesoM[from - 1][to - 1] != Double.POSITIVE_INFINITY;
        }
        return false;
    }

    /**
     * Inserir peso
     * @param from origem
     * @param to destino
     * @param peso peso
     * @return true or false
     */
    public boolean inserirPeso(int from, int to, double peso) {
        if (from > 0 && to > 0 && from <= vertices && to <= vertices) {
            pesoM[from - 1][to - 1] = peso;
            calculada = false;
            return true;

        }
        return false;
    }
    
    /**
     * Remover peso
     * @param from origem 
     * @param to destino
     * @return true or false
     */
    public boolean removerPeso(int from, int to) {
        if (from > 0 && to > 0 && from <= vertices && to <= vertices) {
            pesoM[from - 1][to - 1] = Double.POSITIVE_INFINITY;
            calculada = false;
            return true;

        }
        return false;
    } 

    /**
     * calcular os pares
     * @return true or false
     */
    public boolean calcularAllPairs() {
        GrafoAlgoritmos.floydWarshall(pesoM, sequenciaM, vertices);
        calculada = true;
        return calculada;
    }

    /**
     * Obter o caminho
     * @param from origem
     * @param to destino
     * @return lista 
     */
    public List<Integer> getCaminho(int from, int to) {
        if (from > 0 && to > 0 && from <= vertices && to <= vertices) {
            if (temConexao(from, to))
            return GrafoAlgoritmos.pathToList(pesoM, sequenciaM, from, to);
        }
        return new ArrayList<>();
    }

    /**
     * obter caminho com pontos intermedios
     * @param from origem
     * @param to destino
     * @param intermedios lista de pontos intermedios
     * @return lista
     */
    public List<Integer> getCaminhoComIntermedios(int from, int to, List<Integer> intermedios) {
        if (from > 0 && to > 0 && from <= vertices && to <= vertices) {
            return GrafoAlgoritmos.caminhoComIntermedios(pesoM, sequenciaM, from, to, intermedios);
        }
        return new ArrayList<>();

    }

    /**
     * imprimir resultado
     */
    public void printResult (){
        GrafoAlgoritmos.printResult(pesoM, sequenciaM);
    }

}
