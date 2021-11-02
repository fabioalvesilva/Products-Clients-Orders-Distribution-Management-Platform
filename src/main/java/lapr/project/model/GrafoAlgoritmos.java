/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grupo 11
 */
public class GrafoAlgoritmos {

    /**
     * All pairs floyd Warshall constructing distance and sequence Matrics
     *
     * @param distanceMatrix matriz de distancias
     * @param sequenceMatrix mariz de sequencia
     * @param numVertices numero de vertices
     */
    public static void floydWarshall(double[][] distanceMatrix, int[][] sequenceMatrix, int numVertices) {
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (distanceMatrix[i][k] + distanceMatrix[k][j] < distanceMatrix[i][j]) {
                        distanceMatrix[i][j] = distanceMatrix[i][k] + distanceMatrix[k][j];
                        sequenceMatrix[i][j] = sequenceMatrix[i][k];
                    }
                }
            }
        }
    }

    /**
     * Imprimir caminho
     *
     * @param dist distancia
     * @param seq sequencia
     * @param from origem
     * @param to destino
     */
    public static void printPath(double[][] dist, int[][] seq, int from, int to) {
        if (from <= dist[0].length) {
            System.out.println("pair     dist    path");
        }
        if (from != to) {

            int u = from;
            int v = to;
            String path = String.format("%d -> %d    %2d     %s", u, v,
                    0, u);
            do {
                u = seq[u - 1][v - 1];
                path += " -> " + u;
            } while (u != v);
            System.out.println(path);
        }
    }

    /**
     * Caminho para lista
     *
     * @param dist destancia
     * @param next proximo
     * @param from origem
     * @param to destino
     * @return Lista
     */
    public static List<Integer> pathToList(double[][] dist, int[][] next, int from, int to) {
        List<Integer> path = new LinkedList<>();
        if (from > 0 && to > 0 && from <= dist.length && to <= dist.length) {
            if (dist[from - 1][to - 1] != Double.POSITIVE_INFINITY) {
                if (from != to) {
                    int u = from;
                    int v = to;
                    path.add(u);
                    do {
                        u = next[u - 1][v - 1];
                        path.add(u);
                    } while (u != v);
                    // path.add(v);

                }
            }

        }
        return path;

    }

    /**
     * Caminho com pontos intermedios
     *
     * @param dist distancia
     * @param next proximo
     * @param from origem
     * @param to destino
     * @param intermedios pontos intermedios
     * @return lista
     */
    static List<Integer> caminhoComIntermedios(double[][] dist, int[][] next, int from, int to, List<Integer> intermedios) {
        if (intermedios.isEmpty()) {
            return pathToList(dist, next, from, to);
        }

        List<Integer> prevCaminho = new LinkedList<>();
        List<Integer> currCaminho = new LinkedList<>();

        double prevCost = Double.POSITIVE_INFINITY;
        double currCost = Double.POSITIVE_INFINITY;

        List<List<Integer>> permutacoes = permute(intermedios.toArray(new Integer[0]));

        for (List<Integer> permutacao : permutacoes) {

            //Add orig & Dest 
            permutacao.add(0, from);
            permutacao.add(permutacao.size(), to);
            currCost = 0;

            //Verificar Peso Caminho
            for (int i = 0; i < permutacao.size() - 1; i++) {
                if (dist[permutacao.get(i)][permutacao.get(i)] != Double.POSITIVE_INFINITY) {
                    currCost += dist[permutacao.get(i)][permutacao.get(i + 1)];
                } else {
                    currCost = Double.POSITIVE_INFINITY;
                    break;
                }
            }
            //Get Caminho
            if (currCost < prevCost) {
                prevCost = currCost;
                prevCaminho = permutacao;
            }
        }

        return pathToList(dist, next, prevCaminho);
    }

    /**
     * lista de caminhos
     *
     * @param dist distancias
     * @param next proximo
     * @param listVerts lista de vertices
     * @return lista
     */
    public static List<Integer> pathToList(double[][] dist, int[][] next, List<Integer> listVerts) {
        List<Integer> path = new LinkedList<>();
        if (listVerts.isEmpty()) {
            return path;
        }
        for (int i = 0; i < listVerts.size() - 1; i++) {
            int from = listVerts.get(i);
            int to = listVerts.get(i + 1);
            if (from > 0 && to > 0 && from <= dist.length && to <= dist.length) {

                if (dist[from - 1][to - 1] != Double.POSITIVE_INFINITY) {
                    if (from != to) {
                        int u = from;
                        int v = to;
                        
                        if (path.size() > 1) {
                                if (path.get(path.size() - 1).equals(u)) {
                                    path.remove(path.size() - 1);
                                }
                            }
                        path.add(u);
                        do {
                            u = next[u - 1][v - 1];
                            if (path.size() > 1) {
                                if (path.get(path.size() - 1).equals(u)) {
                                    path.remove(path.size() - 1);
                                }
                            }

                            path.add(u);
                        } while (u != v);

                    }

                }

            }

        }

        return path;

    }

    //Print resultado 
    /**
     * imprimir resultado
     *
     * @param dist distancia
     * @param next proximo
     */
    static void printResult(double[][] dist, int[][] next) {
        System.out.println("pair     dist    path");
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++) {
                if (i != j) {
                    int u = i + 1;
                    int v = j + 1;
                    String path = String.format("%d -> %d    %.2f     %s", u, v,
                            dist[i][j], u);
                    do {
                        u = next[u - 1][v - 1];
                        path += " -> " + u;
                    } while (u != v);
                    System.out.println(path);
                }
            }
        }
    }

    /**
     * permutação
     *
     * @param nums nums
     * @return lista de permutações
     */
    public static List<List<Integer>> permute(Integer[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        int[] count = new int[nums.length];
        Arrays.fill(count, 1);
        permute(nums, count, new int[nums.length], 0, permutations);
        return permutations;
    }

    /**
     * permutações
     *
     * @param nums nums
     * @param count contador
     * @param permutation permutações
     * @param level nivel
     * @param permutations permutações
     */
    private static void permute(Integer[] nums, int[] count, int[] permutation, int level, List<List<Integer>> permutations) {
        if (level == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for (int i : permutation) {
                perm.add(i);
            }
            permutations.add(perm);
            return;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                permutation[level] = nums[i];
                count[i]--;
                permute(nums, count, permutation, level + 1, permutations);
                count[i]++;
            }
        }
    }

}
