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
import lapr.project.model.Farmacia;
import lapr.project.model.LigacaoLocais;
import lapr.project.model.Local;
import lapr.project.model.Parque;
import lapr.project.model.Rota;
import lapr.project.model.TipoPesoEnum;
import lapr.project.model.Veiculo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

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
public class GraphControllerTest {
//    GraphController 
    GraphController ctrl = new GraphController();
    ArrayList<Endereco> enderecos;
    ArrayList<Local> locaisBD;
    ArrayList<int[]> conexoesBD1;
    BaseController b;
////       

    public GraphControllerTest() {
        this.enderecos = new ArrayList<>();
        this.conexoesBD1 = new ArrayList<>();
        this.locaisBD = new ArrayList<>();
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
////
////    @BeforeAll
///    public static void setUpClass() {
////
////    }
////
////    @AfterAll
////    public static void tearDownClass() {
////    }
////
    @BeforeEach
    public void setUp() {
        locaisBD.clear();
        conexoesBD1.clear();
         b = Mockito.mock(BaseController.class);
         ctrl.baseController = b; 

        Endereco eC1 = new Endereco(1, "Clerigos", 41.14582, -8.61398, 87D);
        Endereco eC2 = new Endereco(2, "Majestic", 41.14723, -8.60657, 91D);
        Endereco eC3 = new Endereco(3, "Bolhao", 41.14871, -8.60746, 87D);
        Endereco eC4 = new Endereco(4, "Sé", 41.14331, -8.60914, 82D);
        Endereco eC5 = new Endereco(5, "Cais da Ribeira", 41.14063, -8.61118, 25D);
        Endereco eF1 = new Endereco(6, "Trindade", 41.15227, -8.60929, 104D);
        Endereco eF2 = new Endereco(7, "Castelo do Queijo", 41.16875, -8.68995, 4D);

        Endereco eDP1 = new Endereco(8, "Trindade Parque Drone", 41.15228, -8.60929, 104D);
        Endereco eSP1 = new Endereco(9, "Trindade Parque Scooter", 41.15229, -8.60929, 104D);

        Endereco eDP2 = new Endereco(10, "Castelo do Queijo Parque Drone", 41.16876, -8.68995, 4D);
        Endereco eSP2 = new Endereco(11, "Castelo do Queijo Parque Scooter", 41.16877, -8.68995, 4D);

        Cliente c1 = new Cliente("Cliente1", "email1", 12345, "aaaa", 1, "Clerigos", 41.14582, -8.61398, 87D, 1233);
        c1.setEndereco(eC1);
        Cliente c2 = new Cliente("Cliente2", "email2", 12346, "aaaa", 2, "Majestic", 41.14723, -8.60657, 91D, 1233);
        c2.setEndereco(eC2);
        Cliente c3 = new Cliente("Cliente3", "email1", 12345, "aaaa", 3, "Bolhao", 41.14871, -8.60746, 87D, 1233);
        c3.setEndereco(eC3);
        Cliente c4 = new Cliente("Cliente4", "email2", 12346, "aaaa", 4, "Sé", 41.14331, -8.60914, 82D, 1233);
        c4.setEndereco(eC4);
        Cliente c5 = new Cliente("Cliente5", "email2", 12346, "aaaa", 5, "Cais da Ribeira", 41.14063, -8.61118, 25D, 1233);
        c5.setEndereco(eC5);

        Farmacia f1 = new Farmacia("Trindade", 123456789, "emailFarmacia", 9123455, eF1);
        f1.setId(20);
        Farmacia f2 = new Farmacia("Castelo do Queijo", 123456799, "emailFarmacia2", 9123455, eF2);
        f2.setId(21);
        Parque f1PD = new Parque(1, 2, 0, 0, 2, 2, eDP1);
        Parque f1PS = new Parque(2, 1, 0, 0, 2, 2, eSP1);
        Parque f2PD = new Parque(3, 2, 0, 0, 2, 2, eDP2);
        Parque f2PS = new Parque(4, 1, 0, 0, 2, 2, eSP2);

        locaisBD.add(c1);
        locaisBD.add(c2);
        locaisBD.add(c3);
        locaisBD.add(c4);
        locaisBD.add(c5);
        locaisBD.add(f1);
        locaisBD.add(f2);
        locaisBD.add(f1PD);
        locaisBD.add(f1PS);
        locaisBD.add(f2PD);
        locaisBD.add(f2PS);
        ctrl = Mockito.spy(new GraphController());

        //Conexoes cenario 1 
        conexoesBD1.add(new int[]{6, 5});
        conexoesBD1.add(new int[]{5, 6});

        //conexoes cenario 3

        ///////////////////

    }


    @Test
    public void testCarregarLocaisBD() {
                 b = Mockito.mock(BaseController.class);
         ctrl.baseController = b; 
        when(b.getAllLocais()).thenReturn(locaisBD);
        
        ctrl.carregarLocaisBD();
        assertEquals(true, ctrl.carregarLocaisBD());

    }
    
        /**
     * Test of getLocalPorNome method, of class GraphController.
     */
    @Test
    public void testGetLocalPorNome() {
                         b = Mockito.mock(BaseController.class);
         ctrl.baseController = b; 
        when(b.getAllLocais()).thenReturn(locaisBD);
        ctrl.carregarLocaisBD();
        
        Local test = ctrl.getLocalPorNome("Clerigos");
        
        assertEquals("Clerigos", test.getEndereco().getRua());
    }

 

//    /**
//     * Test of carregarLigacoesMapa method, of class GraphController.
//     */
//    @Test
//    public void testCarregarLigacoesMapa() {
//        System.out.println("carregarLigacoesMapa");
//        List<LigacaoLocais> listaLigacoes = null;
//        GraphController instance = new GraphController();
//        instance.carregarLigacoesMapa(listaLigacoes);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of lerLigacoesDeFicheiro method, of class GraphController.
//     */
//    @Test
//    public void testLerLigacoesDeFicheiro() {
//        System.out.println("lerLigacoesDeFicheiro");
//        String ficheiro = "";
//        GraphController instance = new GraphController();
//        boolean expResult = false;
//        boolean result = instance.lerLigacoesDeFicheiro(ficheiro);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of rotaMaisEficiente method, of class GraphController.
//     */
//    @Test
//    public void testRotaMaisEficiente() {
//        System.out.println("rotaMaisEficiente");
//        TipoPesoEnum tipo = null;
//        Veiculo v = null;
//        Local orig = null;
//        Local dest = null;
//        GraphController instance = new GraphController();
//        List<LigacaoLocais> expResult = null;
//        List<LigacaoLocais> result = instance.rotaMaisEficiente(tipo, v, orig, dest);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFarmaciasProximas method, of class GraphController.
//     */
//    @Test
//    public void testGetFarmaciasProximas() {
//        System.out.println("getFarmaciasProximas");
//        Integer idFarmaciaOrigem = null;
//        GraphController instance = new GraphController();
//        List<Farmacia> expResult = null;
//        List<Farmacia> result = instance.getFarmaciasProximas(idFarmaciaOrigem);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getidFarmaciaMaisProximaCliente method, of class GraphController.
//     */
//    @Test
//    public void testGetidFarmaciaMaisProximaCliente() {
//        System.out.println("getidFarmaciaMaisProximaCliente");
//        Cliente c = null;
//        GraphController instance = new GraphController();
//        Integer expResult = null;
//        Integer result = instance.getidFarmaciaMaisProximaCliente(c);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//
//
//    /**
//     * Test of printResult method, of class GraphController.
//     */
//    @Test
//    public void testPrintResult() {
//        System.out.println("printResult");
//        GraphController instance = new GraphController();
//        instance.printResult();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of converterEnderecosEmLocais method, of class GraphController.
//     */
//    @Test
//    public void testConverterEnderecosEmLocais() {
//        System.out.println("converterEnderecosEmLocais");
//        List<Endereco> listaEnderecos = null;
//        GraphController instance = new GraphController();
//        List<Local> expResult = null;
//        List<Local> result = instance.converterEnderecosEmLocais(listaEnderecos);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRota method, of class GraphController.
//     */
//    @Test
//    public void testGetRota_4args() {
//        System.out.println("getRota");
//        Local from = null;
//        Local to = null;
//        TipoPesoEnum tipo = null;
//        Veiculo v = null;
//        GraphController instance = new GraphController();
//        Rota expResult = null;
//        Rota result = instance.getRota(from, to, tipo, v);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRota method, of class GraphController.
//     */
//    @Test
//    public void testGetRota_5args() {
//        System.out.println("getRota");
//        Local from = null;
//        Local to = null;
//        List<Local> intermedios = null;
//        TipoPesoEnum tipo = null;
//        Veiculo v = null;
//        GraphController instance = new GraphController();
//        Rota expResult = null;
//        Rota result = instance.getRota(from, to, intermedios, tipo, v);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
   
}
