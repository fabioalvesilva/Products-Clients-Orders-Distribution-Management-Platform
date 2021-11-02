/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Parque;

/**
 *
 * @author Groupo 11
 */
public class GestaoParqueController {
    
    /**
     * Base controller
     */
    BaseController baseController = new BaseController();   

    /**
     * Parque
     */
    private final Parque parque = new Parque();
    
    /**
     * Obter todos os parques em sistema
     * @return lista de parques
     */
    public List<Parque> getAllParques(){
        return baseController.getAllParques();
    }
    
    /**
     * Atualização da capacidade de um determinado parque
     * @param idParque id do parque
     * @param nrLugaresNormal numero de lugares normais de estacionamento
     * @param nrLugaresEletricos numero de lugares de estacionamento com posto de carregamento
     * @return true or false
     */
    public boolean updateCapacidadeParque (Integer idParque, int nrLugaresNormal, int nrLugaresEletricos){
        parque.setId(idParque);
        return baseController.updateCapacidadeParque(idParque, nrLugaresNormal, nrLugaresEletricos);
    }
    
    /**
     * Criar parque
     * @param idFarmacia id de farmacia
     * @param p parque
     * @return true or false
     */
    public boolean criarParque(int idFarmacia, Parque p) { 
         
         if (idFarmacia>0 && p.getMaxLugaresNormais()>0 &&  p.getMaxLugaresEletricos()>=0){
          Integer id = baseController.criarParque(idFarmacia, p); 
          
          if(id>0){
           return baseController.updateCapacidadeParque(id, p.getMaxLugaresNormais(), p.getMaxLugaresEletricos());
          }
          
         }           
        return false;       
     }
        
    /**
     * Remover Parque
     * @param idParque id de parque
     * @return true or false
     */
    public boolean removerParque(int idParque) {
        
         if (idParque >=1){
             return this.baseController.removerParque(idParque); 
         }
         
        return false;
    }
}
