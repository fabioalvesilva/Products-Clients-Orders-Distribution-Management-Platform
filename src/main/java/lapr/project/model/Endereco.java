/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author Groupo 11
 */
public class Endereco {

    /**
     * endereco por defeito
     */
    private static final Integer IDENDERECODEFEITO = 0;

    /**
     * rua por defeito
     */
    private static final String RUADEFEITO = "";

    /**
     * latitude por defeito
     */
    private static final Double LATITUDEDEFEITO = 0D;

    /**
     * longitude por defeito
     */
    private static final Double LONGITUDEDEFEITO = 0D;

    /**
     * altitude por defeito
     */
    private static final Double ALTITUDEDEFEITO = 0D;

    /**
     * id de endereco
     */
    private Integer idEndereco;

    /**
     * rua
     */
    private String rua;

    /**
     * latitude
     */
    private Double latitude;

    /**
     * longitude
     */
    private Double longitude;

    /**
     * altitude
     */
    private Double altitude;

    /**
     * Inicializa o construtor vazio
     */
    public Endereco() {
        this.idEndereco = IDENDERECODEFEITO;
        this.rua = RUADEFEITO;
        this.latitude = LATITUDEDEFEITO;
        this.longitude = LONGITUDEDEFEITO;
        this.altitude = ALTITUDEDEFEITO;
    }

    /**
     * Inicializa o construtor completo
     *
     * @param idEndereco id de endereco
     * @param rua rua
     * @param latitude latitude
     * @param longitude longitude
     * @param altitude altitude
     */
    public Endereco(Integer idEndereco, String rua, Double latitude, Double longitude, Double altitude) {
        this.idEndereco = idEndereco;
        this.rua = rua;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    /**
     * Inicializa o construtor
     *
     * @param rua rua
     * @param latitude latitude
     * @param longitude longitude
     * @param altitude altitude
     */
    public Endereco(String rua, Double latitude, Double longitude, Double altitude) {
        this.idEndereco = IDENDERECODEFEITO;
        this.rua = rua;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    /**
     * Inicializa o construtor
     *
     * @param rua rua
     * @param latitude latitude
     * @param longitude longitude
     */
    public Endereco(String rua, Double latitude, Double longitude) {
        this.idEndereco = IDENDERECODEFEITO;
        this.rua = rua;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = ALTITUDEDEFEITO;
    }

    /**
     * Inicializa o construtor
     *
     * @param e endereco
     */
    public Endereco(Endereco e) {
        this.idEndereco = e.idEndereco;
        this.rua = e.rua;
        this.latitude = e.latitude;
        this.longitude = e.longitude;
        this.altitude = e.altitude;
    }

    /**
     * retorna id de endereco
     *
     * @return id de endereco
     */
    public Integer getIdEndereco() {
        return idEndereco;
    }

    /**
     * retorna rua
     *
     * @return rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * retorna latitude
     *
     * @return latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * retorna longitude
     *
     * @return longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * retorna altitude
     *
     * @return altitude
     */
    public Double getAltitude() {
        return altitude;
    }

    /**
     * Modifica o id de endereco
     *
     * @param idEndereco
     */
    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    /**
     * Modifica a rua
     *
     * @param rua
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * Modifica a latitude
     *
     * @param latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Modifica a longitude
     *
     * @param longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Modifica a altitude
     *
     * @param altitude
     */
    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.idEndereco);
        hash = 61 * hash + Objects.hashCode(this.rua);
        return hash;
    }

    /**
     * Método que compara a igualdade do objeto atual com o objeto do mesmo tipo
     *
     * @param obj
     * @return True or false
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
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.rua, other.rua)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.altitude, other.altitude)) {
            return false;
        }
        return true;
    }

    /**
     * Devolve a descrição textual
     *
     * @return Devolve a descrição textual
     */
    @Override
    public String toString() {
        return "Endereco{" + "rua=" + rua + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
