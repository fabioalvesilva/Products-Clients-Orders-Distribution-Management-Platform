/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author grupo 11
 */
public enum TipoPesoEnum {
    
    /**
     * distancia
     */
    DISTANCIA(1, "DISTANCIA"),

    /**
     * tempo
     */
    TEMPO(2, "TEMPO"),

    /**
     * energia
     */
    ENERGIA(3, "ENERGIA")
    ;
    
    /**
     * id
     */
    private int id;

    /**
     * tipo
     */
    private String tipo;
    
    /**
     * Enum
     * @param id id
     * @param tipo tipo
     */
    TipoPesoEnum(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    
}
