/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Local;
import lapr.project.model.Rota;

/**
 *
 * @author Groupo 11
 */
public class RotaController {

    /**
     * rota
     */
    Rota rota;

    /**
     * Graph controller
     */
    GraphController graphController;

    /**
     * base controller
     */
    BaseController baseController = new BaseController();

    /**
     * Inicialização do construtor completo
     * @param graphController
     */
    public RotaController(GraphController graphController) {
        this.graphController = graphController;
    }



}
