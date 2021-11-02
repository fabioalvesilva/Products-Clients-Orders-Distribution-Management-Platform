/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.Objects;

/**
 *
 * @author grupo 11
 */
public class Pair {

    /**
     * f
     */
    int f;

    /**
     * s
     */
    int s;

    /**
     * Inicialização de construtor completo
     * @param first
     * @param second
     */
    public Pair(int first, int second) {
        this.f = first;
        this.s = second;
    }

    /**
     * obter primeiro
     * @return primeiro
     */
    public int getFirst() {
        return f;
    }

    /**
     * obter segundo
     * @return segundo
     */
    public int getSecond() {
        return s;
    }

    /**
     * hascode
     * @return hascode
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.f;
        hash = 59 * hash + this.s;
        return hash;
    }

    /**
     * Método que compara a igualdade do objeto atual com o objeto do mesmo tipo
     * 
     * @param obj objeto a comparar
     * @return boolean, true se this object é igual ao object passado por
     * parametro, falso se diferente
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (this.f != other.f) {
            return false;
        }
        if (this.s != other.s) {
            return false;
        }
        return true;
    }

}
