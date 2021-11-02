/*
 *
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.LerCSV;
import lapr.project.model.Cliente;
import lapr.project.model.Endereco;
import lapr.project.model.Farmacia;
import lapr.project.model.LigacaoLocais;
import lapr.project.model.Local;
import lapr.project.model.Mapa;
import lapr.project.model.Rota;
import lapr.project.model.Scooter;
import lapr.project.model.TipoPesoEnum;
import lapr.project.model.Veiculo;
import lapr.project.utils.Calculos;
import lapr.project.utils.Constantes;

/**
 *
 * @author Groupo 11
 */
public class GraphController {

    /////////////NEW/////////////

    /**
     * lista de locais
     */
    List<Local> listaLocais;

    /**
     * base controller
     */
    BaseController baseController = new BaseController();

    /**
     * lista de ligações
     */
    List<LigacaoLocais> listaLigacoes;

    /**
     * mapa
     */
    Mapa mapa;
    
    /**
     * Locais a partir de BD
     */
    public GraphController() {
        this.listaLocais = new ArrayList<>();
        this.listaLigacoes = new ArrayList<>();
        this.mapa = new Mapa(listaLocais);

    }
    /**
     * locais Apartir de lista
     * @param listaLocais lista de locais
     */
    public GraphController(List<Local> listaLocais) {
        this.listaLocais = listaLocais;
        this.listaLigacoes = new ArrayList<>();
        this.mapa = new Mapa(listaLocais);

    }

    /**
     * carregar locais na base de dadso
     * @return true or false
     */
    public boolean carregarLocaisBD() {
        this.listaLocais = baseController.getAllLocais();
        this.mapa = new Mapa(listaLocais);
        return this.listaLocais.size() > 0;
        
    }


     /**
     * Carregar Ligacoes Para Mapa;
     *
     * @param listaLigacoes lista de ligações
     */
    public void carregarLigacoesMapa(List<LigacaoLocais> listaLigacoes) {
        this.listaLigacoes = listaLigacoes;
        mapa.removerTodasLigacoes();
        mapa.adicionarLigacoes(listaLigacoes);

    }

     /**
      * carregar Ligacoes apartir para mapa apartir de ficheiro
     * @param ficheiro fichiero com a ligações
     * @return  boolean 
     */
    public boolean lerLigacoesDeFicheiro(String ficheiro) {
        listaLigacoes = new ArrayList<>();
        List<String[]> listaString = new ArrayList<>();
        LerCSV lr = new LerCSV();

        try {
            listaString = lr.leituraLigacaoLocais(ficheiro);
        } catch (IOException ex) {
            Logger.getLogger(GraphController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!listaString.isEmpty()) {
            for (String[] s : listaString) {
                Local from = getLocalPorNome(s[0]);
                Local to = getLocalPorNome(s[1]);
                if (from != null && to != null) {
                    int bidirectional = Integer.parseInt(s[2]);
                    double ventoVelcidade = Double.parseDouble(s[3]);
                    double ventoDirecao = Double.parseDouble(s[4]);
                    double coeficienteAtrito = Double.parseDouble(s[5]);
                    LigacaoLocais ligacao = new LigacaoLocais(from, to, bidirectional, ventoVelcidade, ventoDirecao, coeficienteAtrito);
                    listaLigacoes.add(ligacao);
                }

            }
            mapa.removerTodasLigacoes();
            mapa.adicionarLigacoes(listaLigacoes);

        }
        return false;

    }

     /**
      * Return ListaLigacoes Para Rota mais eficiente entre Vorig e vdest 
     * @param tipo tipo
     * @param v veiculo
     * @param orig localidade de origem
     * @param dest localidade de destino
     * @return Lista com ligações entre locais    */
    public List<LigacaoLocais> rotaMaisEficiente(TipoPesoEnum tipo, Veiculo v, Local orig, Local dest) {
        mapa.criarGrafoTipo(tipo, v);
        List<LigacaoLocais> ligacoes = mapa.getRota(orig, dest);
        return ligacoes;
    }
 
    /**
     * Para um dado Id de Farmacia verificar qual é a farmacia mais proxima;
     * @param idFarmaciaOrigem id da farmacia de origem
     * @return  lista de farmacias
     */
    public List<Farmacia> getFarmaciasProximas(Integer idFarmaciaOrigem) {
        Local l = baseController.getLocalFarmaciaById(idFarmaciaOrigem);
        
        carregarLigacoesMapa(listaLigacoes); //Lista Default Ligacoes 
        List<Local> temp = mapa.getLocaisMaisProximo(l, new Farmacia());
        List<Farmacia> listaFarmacias = new ArrayList<>();

        for (Local i : temp) {
            if (i instanceof Farmacia) {
                listaFarmacias.add((Farmacia) i);
            }
        }

        return listaFarmacias;

    }

    /**
     * Obter o id da farmacia mais proxima ao cliente
     * @param c cliente
     * @return id da farmacia
     */
    public Integer getidFarmaciaMaisProximaCliente(Cliente c) {
        // Local l = baseController.getLocalFarmaciaById(idFarmaciaOrigem);

        carregarLigacoesMapa(listaLigacoes); //Lista Default Ligacoes 
        Scooter scooter = new Scooter(6, "scooterMat", "Mod scooter", 1, 1000, 1, 100, 100, Constantes.PESOMEDIOSCOOTER, Constantes.AREAFRONTALMEDIASCOOTER);
        mapa.criarGrafoTipo(TipoPesoEnum.DISTANCIA, scooter);
        
        Local temp   =  mapa.getLocalMaisProximo(c, new Farmacia());

        if (temp != null){
            return temp.getId();
        }
        return -1;
    }

    /////////////////helper Methods///////////////////
    /***
     * Retorna Local por nome se existente , ou entao null;
     * @param nome rua
     * @return localidade
     */
    public Local getLocalPorNome(String nome) {
        for (Local l : listaLocais) {
            if (l.getEndereco().getRua().equals(nome)) {
                return l;
            }
        }
        return null;
    }

    /***
     * Print resultado das matrizes atuais para a consola 
     */
    public void printResult() {
       // this.mapa.getRota(getLocalPorNome("Farmacia Guifoes"), "Mar ")
        this.mapa.printResult();
        
    }
    /**
     * Converte uma dada Lista de enderecos em Locais
     * @param listaEnderecos lista de enderecos
     * @return  lista de locais
     */
    public List<Local>  converterEnderecosEmLocais(List<Endereco> listaEnderecos){
        List<Local> listalLocal =  new ArrayList<>();
        for(Local l : listaLocais){
            for (Endereco e : listaEnderecos){
                if (l.getEndereco().equals(e)){
                    listalLocal.add(l);
                }
            }
        }
        return listalLocal;
    }
    
    /**
     * obter a rota
     * @param from localidade de origem
     * @param to localidade de destino
     * @param tipo tipo
     * @param v veiculo
     * @return rota
     */
    public Rota getRota(Local from, Local to, TipoPesoEnum tipo, Veiculo v){
        mapa.criarGrafoTipo(tipo, v);
       List<LigacaoLocais> caminho =  mapa.getRota(from, to);
       if (caminho.size()> 0){
           Rota rota = new Rota(caminho);
                 return rota;
       }

       return null;
    }
    
    /**
     * obter a rota
     * @param from localidade de origem
     * @param to localidade de destino
     * @param intermedios localidades intermedias
     * @param tipo tipo
     * @param v veiculo
     * @return rota
     */
    public Rota getRota(Local from, Local to, List<Local> intermedios ,TipoPesoEnum tipo, Veiculo v){
        mapa.criarGrafoTipo(tipo, v);
       List<LigacaoLocais> caminho =  mapa.getRota(from, to,intermedios);
       if (caminho.size()> 0){
           Rota rota = new Rota(caminho);
                 return rota;
       }

       return null;
    }
    
    
    
}