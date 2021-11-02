/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import java.util.Set;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Veiculo;

/**
 *
 * @author Groupo 11
 */
public class GestaoEntregasController {
    
    /**
     * notificação controller
     */
    NotificacaoController notificacaoController;

    /**
     * base controller
     */
    BaseController baseController = new BaseController();

    /**
     * entrega
     */
    Entrega entrega = new Entrega();
    
    /**
     * Inicialização do construtor vazio
     */
    public GestaoEntregasController() {
        
        notificacaoController = new NotificacaoController();
        entrega.setId(0);
    }

    /**
     * verificar  a ocupação do estafeta
     * @param idEstafeta id do estafeta
     * @return true or false
     */
    public boolean verificarOcupacaoEstafeta(Integer idEstafeta){
        if(idEstafeta == null) return false;
        return baseController.getOcupacaoEstafeta(idEstafeta);
    }

    /**
     * obter a entrega
     * @return entrega
     */
    public Entrega getEntrega() {
        Entrega eTemp = new Entrega(entrega); 
        return eTemp;
    }
    
    /**
     * modificar o id estafeta da entrega
     * @return true or false
     */
    public boolean setIdEstafetaEntrega() {
        
        Integer idEstafeta =  baseController.getEstafetaDisponivelParaEntrega(entrega.getIdFarmacia());
        if (idEstafeta != null && idEstafeta > 0) {
            this.entrega.setIdEstafeta(idEstafeta);
            return true;
        }
        return false;
    }
    
    /**
     * obter uma scooter para entrega
     * @param idFarmacia id da farmacia
     * @return id da scooter
     */
    public Integer getScooterParaEntrega (Integer idFarmacia){
        return this.baseController.getScooterParaEntrega(idFarmacia);
    } 
    
    /**
     * obter drone para entrega
     * @param idFarmacia id da farmacia
     * @return id de drone
     */
    public Integer getDroneParaEntrega (Integer idFarmacia){
        return this.baseController.getDroneParaEntrega(idFarmacia);
    }

    /**
     * obter encomendas pendentes
     * @return lista de encomendas
     */
    public List<Encomenda> getEncomendasPendentes() {
        int idFarmacia = 1;
        List<Encomenda> listaEncomendas = baseController.getEncomendasPendentes(idFarmacia);
        entrega = new Entrega();
        return listaEncomendas;
    }
    
    /**
     * obter as encomendas pendentes por farmacia
     * @param idFarmacia id da farmacia
     * @return lista de encomendas
     */
    public List<Encomenda> getEncomendasPendentesByFarmacia (Integer idFarmacia) {
        
        List<Encomenda> listaEncomendas = baseController.getEncomendasPendentes(idFarmacia);
        entrega = new Entrega();
        entrega.setIdFarmacia(idFarmacia);
        
        return listaEncomendas;
    }

    /**
     * adicionar encomendas
     * @param listEncomendas lista de encomendas
     * @return true or false
     */
    public boolean adicionarEncomendas(List<Encomenda> listEncomendas) {
        boolean result = false;
        
        if (listEncomendas != null && listEncomendas.size() > 0) {
            
            for (Encomenda e : listEncomendas) {
                this.entrega.adicionarEncomenda(e.getId(), e.getPeso(), e.getEndereco().getIdEndereco(), e.getEndereco().getRua(), e.getEndereco().getLatitude(), e.getEndereco().getLongitude(), e.getEndereco().getAltitude());
            }
        if (this.entrega.getSetEncomendas().size()>0 ) {
            result=true;
        }    
        }
        return result;
    }
    
    /**
     * método para verificar o peso da entrega
     * @param listaEncomendas lista de encomendas
     * @return true or false
     */
    public boolean verificarPesoEntrega(List<Encomenda> listaEncomendas){
        
        Double pesoEncomendar = 0.0;
        
        this.entrega.getPesoTotalCarga();
        
        for(Encomenda e : listaEncomendas){
            
        }
        
        return false;
    }
    
    /**
     * registar entrega
     * @return true or false
     */
    public boolean registarEntrega(){
        
        Entrega e = baseController.registarEntrega(entrega);
        
        notificarEntregas(e.getSetEncomendas());
        
        if (e.getId() > 0){
            return true;
        }
        return false;
    }
    
    /**
     * modificar o veiculo para entrega
     * @param v veiculo
     */
    public void setVeiculoParaEntrega(Veiculo v){
        this.entrega.setVeiculo(v);
    } 
    
    /**
     * Método que permite notificar entregas
     * @param listEncomendas lista de encomendas
     * @return true or false
     */
    public boolean notificarEntregas(Set<Encomenda> listEncomendas){
        
        boolean result = false;
        if (listEncomendas != null && listEncomendas.size() > 0) {
            
            for (Encomenda e : listEncomendas) {
                
                String corpoEmail = "A sua encomenda está a ser entregue";
                String assunto = "Estado Encomenda";
            
            this.notificacaoController.notificacaoEnvioEncomenda(e);
            
            }
        if (this.entrega.getSetEncomendas().size()>0 ) {
            result=true;
        }    
        }
        return result;
    }


}
