/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Endereco;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Cliente;
import lapr.project.model.Drone;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Farmacia;
import lapr.project.model.Local;
import lapr.project.model.Parque;
import lapr.project.model.Rota;
import lapr.project.model.Scooter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author rmarinho
 */
public class RotaControllerTest {

    ArrayList<Endereco> enderecos;
    ArrayList<Local> locaisBD;
    ArrayList<int[]> conexoesBD1;
    ArrayList<int[]> conexoesBD3;
    ArrayList<int[]> conexoesBD4;
    RotaController ctrl;
    Scooter scooter;
    Drone drone;
    Entrega entrega;
    GraphController graphCtrl;
    BaseController b;
    List<Endereco> listaEnderecos;
    List<Local> locaisretorno;

    public RotaControllerTest() {
        this.enderecos = new ArrayList<>();
        this.conexoesBD1 = new ArrayList<>();
        this.conexoesBD4 = new ArrayList<>();
        this.conexoesBD3 = new ArrayList<>();
        this.locaisBD = new ArrayList<>();
    }

    @BeforeEach
    public void setUp() {

    }

//    /**
//     * Test of calcularRotaScooter method, of class RotaController.
//     */
//    @Test
//    public void testCalcularRotaScooter() {
//        System.out.println("calcularRotaScooter");
//        locaisBD.clear();
//        conexoesBD1.clear();
//        conexoesBD3.clear();
//        conexoesBD4.clear();
//
//        Endereco eC1 = new Endereco(1, "Clerigos", 41.14582, -8.61398, 87D);
//        Endereco eC2 = new Endereco(2, "Majestic", 41.14723, -8.60657, 91D);
//        Endereco eC3 = new Endereco(3, "Bolhao", 41.14871, -8.60746, 87D);
//        Endereco eC4 = new Endereco(4, "Sé", 41.14331, -8.60914, 82D);
//        Endereco eC5 = new Endereco(5, "Cais da Ribeira", 41.14063, -8.61118, 25D);
//        Endereco eF1 = new Endereco(6, "Trindade", 41.15227, -8.60929, 104D);
//        Endereco eF2 = new Endereco(7, "Castelo do Queijo", 41.16875, -8.68995, 4D);
//
//        Endereco eDP1 = new Endereco(8, "Trindade Parque Drone", 41.15228, -8.60929, 104D);
//        Endereco eSP1 = new Endereco(9, "Trindade Parque Scooter", 41.15229, -8.60929, 104D);
//
//        Endereco eDP2 = new Endereco(10, "Castelo do Queijo Parque Drone", 41.16876, -8.68995, 4D);
//        Endereco eSP2 = new Endereco(11, "Castelo do Queijo Parque Scooter", 41.16877, -8.68995, 4D);
//
//        Cliente c1 = new Cliente("Cliente1", "email1", 12345, "aaaa", 1, "Clerigos", 41.14582, -8.61398, 87D, 1233);
//        c1.setEndereco(eC1);
//        Cliente c2 = new Cliente("Cliente2", "email2", 12346, "aaaa", 2, "Majestic", 41.14723, -8.60657, 91D, 1233);
//        c2.setEndereco(eC2);
//        Cliente c3 = new Cliente("Cliente3", "email1", 12345, "aaaa", 3, "Bolhao", 41.14871, -8.60746, 87D, 1233);
//        c3.setEndereco(eC3);
//        Cliente c4 = new Cliente("Cliente4", "email2", 12346, "aaaa", 4, "Sé", 41.14331, -8.60914, 82D, 1233);
//        c4.setEndereco(eC4);
//        Cliente c5 = new Cliente("Cliente5", "email2", 12346, "aaaa", 5, "Cais da Ribeira", 41.14063, -8.61118, 25D, 1233);
//        c5.setEndereco(eC5);
//
//        Farmacia f1 = new Farmacia("Trindade", 123456789, "emailFarmacia", 9123455, eF1);
//        f1.setId(20);
//        Farmacia f2 = new Farmacia("Castelo do Queijo", 123456799, "emailFarmacia2", 9123455, eF2);
//        f2.setId(21);
//        Parque f1PD = new Parque(1, 2, 0, 0, 2, 2, eDP1);
//        Parque f1PS = new Parque(2, 1, 0, 0, 2, 2, eSP1);
//        Parque f2PD = new Parque(3, 2, 0, 0, 2, 2, eDP2);
//        Parque f2PS = new Parque(4, 1, 0, 0, 2, 2, eSP2);
//
//        locaisBD.add(c1);
//        locaisBD.add(c2);
//        locaisBD.add(c3);
//        locaisBD.add(c4);
//        locaisBD.add(c5);
//        locaisBD.add(f1);
//        locaisBD.add(f2);
//        locaisBD.add(f1PD);
//        locaisBD.add(f1PS);
//        locaisBD.add(f2PD);
//        locaisBD.add(f2PS);
//        graphCtrl = Mockito.mock(GraphController.class);
//        ctrl = Mockito.spy(new RotaController(graphCtrl));
//        b = Mockito.mock(BaseController.class);
//        ctrl.baseController = b;
//        scooter = new Scooter(20, "1234", "dada", 123, 123, 123, 123, 123, 10D, 10D);
//        drone = new Drone(123, 13, 123, "abds", 123, 10D, 11D, 123, 13D);
//        entrega = new Entrega(12, scooter, 123, 20, "2020", "2021", 11D, 20);
//        entrega.adicionarEncomenda(1, 2D, 1, "Clerigos", 41.14582, -8.61398, 87D);
//        entrega.adicionarEncomenda(2, 2D, 2, "Majestic", 41.14723, -8.60657, 91D);
//
//        Local l = locaisBD.get(5);
//        Integer id = entrega.getIdFarmacia();
//        Mockito.when(b.getLocalFarmaciaById(entrega.getIdFarmacia())).thenReturn(locaisBD.get(5));
//
//        listaEnderecos = new ArrayList<>();
//        listaEnderecos.add(eC1);
//        listaEnderecos.add(eC2);
//        locaisretorno = new ArrayList<>();
//        locaisretorno.add(c1);
//        locaisretorno.add(c2);
//        Mockito.when(graphCtrl.getLocaisByEnderecos(listaEnderecos)).thenReturn(locaisretorno);
//
//        Encomenda encomenda1 = new Encomenda(1, "09-12-1984", 111000111, 2, 10.0, 12.0, 10.0, 40.0);
//        Encomenda encomenda2 = new Encomenda(2, "09-12-1984", 111000113, 2, 10.0, 12.0, 10.0, 35.0);
//
//        
//        Rota result = ctrl.calcularRotaScooter(entrega);
//
//        Rota expResult = null;
//
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of calcularRotaDrone method, of class RotaController.
//     */
//    @Test
//    public void testCalcularRotaDrone() {
//        System.out.println("calcularRotaScooter");
//        locaisBD.clear();
//        conexoesBD1.clear();
//        conexoesBD3.clear();
//        conexoesBD4.clear();
//
//        Endereco eC1 = new Endereco(1, "Clerigos", 41.14582, -8.61398, 87D);
//        Endereco eC2 = new Endereco(2, "Majestic", 41.14723, -8.60657, 91D);
//        Endereco eC3 = new Endereco(3, "Bolhao", 41.14871, -8.60746, 87D);
//        Endereco eC4 = new Endereco(4, "Sé", 41.14331, -8.60914, 82D);
//        Endereco eC5 = new Endereco(5, "Cais da Ribeira", 41.14063, -8.61118, 25D);
//        Endereco eF1 = new Endereco(6, "Trindade", 41.15227, -8.60929, 104D);
//        Endereco eF2 = new Endereco(7, "Castelo do Queijo", 41.16875, -8.68995, 4D);
//
//        Endereco eDP1 = new Endereco(8, "Trindade Parque Drone", 41.15228, -8.60929, 104D);
//        Endereco eSP1 = new Endereco(9, "Trindade Parque Scooter", 41.15229, -8.60929, 104D);
//
//        Endereco eDP2 = new Endereco(10, "Castelo do Queijo Parque Drone", 41.16876, -8.68995, 4D);
//        Endereco eSP2 = new Endereco(11, "Castelo do Queijo Parque Scooter", 41.16877, -8.68995, 4D);
//
//        Cliente c1 = new Cliente("Cliente1", "email1", 12345, "aaaa", 1, "Clerigos", 41.14582, -8.61398, 87D, 1233);
//        c1.setEndereco(eC1);
//        Cliente c2 = new Cliente("Cliente2", "email2", 12346, "aaaa", 2, "Majestic", 41.14723, -8.60657, 91D, 1233);
//        c2.setEndereco(eC2);
//        Cliente c3 = new Cliente("Cliente3", "email1", 12345, "aaaa", 3, "Bolhao", 41.14871, -8.60746, 87D, 1233);
//        c3.setEndereco(eC3);
//        Cliente c4 = new Cliente("Cliente4", "email2", 12346, "aaaa", 4, "Sé", 41.14331, -8.60914, 82D, 1233);
//        c4.setEndereco(eC4);
//        Cliente c5 = new Cliente("Cliente5", "email2", 12346, "aaaa", 5, "Cais da Ribeira", 41.14063, -8.61118, 25D, 1233);
//        c5.setEndereco(eC5);
//
//        Farmacia f1 = new Farmacia("Trindade", 123456789, "emailFarmacia", 9123455, eF1);
//        f1.setId(20);
//        Farmacia f2 = new Farmacia("Castelo do Queijo", 123456799, "emailFarmacia2", 9123455, eF2);
//        f2.setId(21);
//        Parque f1PD = new Parque(1, 2, 0, 0, 2, 2, eDP1);
//        Parque f1PS = new Parque(2, 1, 0, 0, 2, 2, eSP1);
//        Parque f2PD = new Parque(3, 2, 0, 0, 2, 2, eDP2);
//        Parque f2PS = new Parque(4, 1, 0, 0, 2, 2, eSP2);
//
//        locaisBD.add(c1);
//        locaisBD.add(c2);
//        locaisBD.add(c3);
//        locaisBD.add(c4);
//        locaisBD.add(c5);
//        locaisBD.add(f1);
//        locaisBD.add(f2);
//        locaisBD.add(f1PD);
//        locaisBD.add(f1PS);
//        locaisBD.add(f2PD);
//        locaisBD.add(f2PS);
//        graphCtrl = Mockito.mock(GraphController.class);
//        ctrl = Mockito.spy(new RotaController(graphCtrl));
//        b = Mockito.mock(BaseController.class);
//        ctrl.baseController = b;
//        scooter = new Scooter(20, "1234", "dada", 123, 123, 123, 123, 123, 10D, 10D);
//        drone = new Drone(123, 13, 123, "abds", 123, 10D, 11D, 123, 13D);
//        entrega = new Entrega(12, drone, 0, 20, "2020", "2021", 11D, 20);
//        entrega.adicionarEncomenda(1, 2D, 1, "Clerigos", 41.14582, -8.61398, 87D);
//        entrega.adicionarEncomenda(2, 2D, 2, "Majestic", 41.14723, -8.60657, 91D);
//
//        Local l = locaisBD.get(5);
//        Integer id = entrega.getIdFarmacia();
//        Mockito.when(b.getLocalFarmaciaById(entrega.getIdFarmacia())).thenReturn(locaisBD.get(5));
//
//        listaEnderecos = new ArrayList<>();
//        listaEnderecos.add(eC1);
//        listaEnderecos.add(eC2);
//        locaisretorno = new ArrayList<>();
//        locaisretorno.add(c1);
//        locaisretorno.add(c2);
//        Mockito.when(graphCtrl.getLocaisByEnderecos(listaEnderecos)).thenReturn(locaisretorno);
//
//        Encomenda encomenda1 = new Encomenda(1, "09-12-1984", 111000111, 2, 10.0, 12.0, 10.0, 40.0);
//        Encomenda encomenda2 = new Encomenda(2, "09-12-1984", 111000113, 2, 10.0, 12.0, 10.0, 35.0);
//
//        ////////////
//        Rota result = ctrl.calcularRotaDrone(entrega);
//
//        Rota expResult = null;
//
//        assertEquals(expResult, result);
//    }

}