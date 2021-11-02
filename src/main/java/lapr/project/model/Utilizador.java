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
public class Utilizador {
   
    /**
     * id do utilizador
     */
    private Integer id = 0;

    /**
     * nome
     */
    private String nome;

    /**
     * email
     */
    private String email;

    /**
     * NIF
     */
    private Integer nif;

    /**
     * password
     */
    private String password;
    
    /**
     * Inicializa o construtor completo
     * @param nome nome
     * @param email email
     * @param nif NIF 
     * @param password password
     */
    public Utilizador(String nome, String email, Integer nif, String password) {
        
        this.nome = nome;
        this.email = email;
        this.nif = nif;
        this.password = password;
    }
    
    /**
     * Inicializa o construtor completo
     * @param id id
     * @param nome nome
     * @param email email
     * @param nif NIF 
     * @param password password
     */
    public Utilizador(Integer id, String nome, String email, Integer nif, String password) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.nif = nif;
        this.password = password;
    }

    /**
     * Inicializa o construtor vazio
     */
    public Utilizador() {
        
        this.nome = null;
        this.email = null;
        this.nif = null;
        this.password = null;
        
    }

    /**
     * retorna id de utilizador
     * @return id de utilizador
     */
    public Integer getId() {
        return id;
    }

    /**
     * retorna nome
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * retorna email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * retorna NIF
     * @return NIF
     */
    public Integer getNif() {
        return nif;
    }

    /**
     * retorna password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifica o id de utilizador
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Modifica o nome
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Modifica o email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Modifica o nif
     * @param nif
     */
    public void setNif(Integer nif) {
        this.nif = nif;
    }

    /**
     * Modifica a password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Devolve a descrição textual
     * @return Devolve a descrição textual
     */
    @Override
    public String toString() {
        return "Utilizador{" + "nome=" + nome + ", email=" + email + ", nif=" + nif + ", password=" + password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.nif);
        hash = 89 * hash + Objects.hashCode(this.password);
        return hash;
    }

    /** 
     * Método que compara a igualdade do objeto atual com o objeto do mesmo tipo
     * @param obj
     * @return True or False
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
        final Utilizador other = (Utilizador) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
