/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author rmarinho
 */
public class RotaTest {

    ArrayList<Endereco> enderecos;
    ArrayList<Local> locaisBD;
    ArrayList<int[]> conexoesBD1;
    ArrayList<int[]> conexoesBD3;
    ArrayList<int[]> conexoesBD4;

    public RotaTest() {
        this.enderecos = new ArrayList<>();
        this.conexoesBD1 = new ArrayList<>();
        this.conexoesBD4 = new ArrayList<>();
        this.conexoesBD3 = new ArrayList<>();
        this.locaisBD = new ArrayList<>();
    }

//    @BeforeEach
//    public void setUp() {
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
//    }
//
//    /**
//     * Test of getLocais method, of class Rota.
//     */
//    @Test
//    public void testGetLocais() {
//        System.out.println("null instance");
//        Rota instance = null;
//        List<List<Local>> expResult = null;
//        Rota result = instance;
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//    }
//
//    /**
//     * Test of getLocais method, of class Rota.
//     */
//    @Test
//    public void testGetLocais2() {
//        System.out.println("getLocais");
//        List<List<Local>> listaExpected = new ArrayList<>();
//        List<Local> lista1 = new ArrayList<>();
//        List<Local> lista2 = new ArrayList<>();
//        lista1.add(locaisBD.get(0));
//        lista1.add(locaisBD.get(1));
//        lista2.add(locaisBD.get(1));
//        lista2.add(locaisBD.get(0));
//
//        listaExpected.add(lista1);
//        listaExpected.add(lista2);
//
//        Rota instance = new Rota(listaExpected);
//        List<List<Local>> expResult = listaExpected;
//        List<List<Local>> result = instance.getLocais();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of setLocais method, of class Rota.
//     */
//    @Test
//    public void testSetLocais() {
//        System.out.println("setLocais");
//        List<List<Local>> listaExpected = new ArrayList<>();
//        List<Local> lista1 = new ArrayList<>();
//        List<Local> lista2 = new ArrayList<>();
//        lista1.add(locaisBD.get(0));
//        lista1.add(locaisBD.get(1));
//        lista2.add(locaisBD.get(1));
//        lista2.add(locaisBD.get(0));
//
//        listaExpected.add(lista1);
//        listaExpected.add(lista2);
//
//        Rota instance = new Rota(listaExpected);
//        List<List<Local>> expResult = new ArrayList<>();
//        expResult.add(lista2);
//        instance.setLocais(expResult);
//        List<List<Local>> result = instance.getLocais();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of getTotalEnergia method, of class Rota.
//     */
//    @Test
//    public void testGetTotalEnergia() {
//        System.out.println("getTotalEnergia");
//        List<List<Local>> listaExpected = new ArrayList<>();
//        List<Local> lista1 = new ArrayList<>();
//        List<Local> lista2 = new ArrayList<>();
//        lista1.add(locaisBD.get(0));
//        lista1.add(locaisBD.get(1));
//        lista2.add(locaisBD.get(1));
//        lista2.add(locaisBD.get(0));
//
//        listaExpected.add(lista1);
//        listaExpected.add(lista2);
//
//        Rota instance = new Rota(listaExpected);
//
//        Double result = instance.getTotalEnergia();
//        Double expResult = null;
//        assertEquals(expResult, result);
//    }
//
////    /**
////     * Test of getTotalEnergia method, of class Rota.
////     */
////    @Test
////    public void testGetTotalEnergia2() {
////        System.out.println("getTotalEnergia");
////        List<List<Local>>  listaExpected = new ArrayList<>();
////        List<Local> lista1 = new ArrayList<>();
////        List<Local> lista2 = new ArrayList<>();
////        lista1.add(locaisBD.get(0));
////        lista1.add(locaisBD.get(1));
////        lista2.add(locaisBD.get(1));
////        lista2.add(locaisBD.get(0));
////        
////        listaExpected.add(lista1);
////        listaExpected.add(lista2);
////        
////        Rota instance = new Rota(listaExpected);
////        instance.calcularRotaScooter(200);
////        
////        double result = instance.getTotalEnergia();
////        double expResult = 2872.5;
////        //assertThat(result).isCloseTo(expResult, within(0.1));
////        assertEquals(expResult, result, 0.1);
////        
////    }
//    /**
//     * Test of getTotalTempo method, of class Rota.
//     */
//    @Test
//    public void testGetTotalTempo() {
//        List<List<Local>> listaExpected = new ArrayList<>();
//        List<Local> lista1 = new ArrayList<>();
//        List<Local> lista2 = new ArrayList<>();
//        lista1.add(locaisBD.get(0));
//        lista1.add(locaisBD.get(1));
//        lista2.add(locaisBD.get(1));
//        lista2.add(locaisBD.get(0));
//
//        listaExpected.add(lista1);
//        listaExpected.add(lista2);
//
//        Rota instance = new Rota(listaExpected);
//        instance.calcularRotaScooter(200);
//
//        double result = instance.getTotalTempo();
//        double expResult = 153.60;
//        //assertThat(result).isCloseTo(expResult, within(0.1));
//        assertEquals(expResult, result, 0.1);
//    }
//
////    /**
////     * Test of toString method, of class Rota.
////     */
////    @Test
////    public void testToString() {
////        System.out.println("toString");
////        List<List<Local>>  listaExpected = new ArrayList<>();
////        List<Local> lista1 = new ArrayList<>();
////        List<Local> lista2 = new ArrayList<>();
////        lista1.add(locaisBD.get(0));
////        lista1.add(locaisBD.get(1));
////        lista2.add(locaisBD.get(1));
////        lista2.add(locaisBD.get(0));
////        
////        listaExpected.add(lista1);
////        listaExpected.add(lista2);
////        
////        Rota instance = new Rota(listaExpected);
////        instance.calcularRotaScooter(200);
////        
////        String expResult1 = "04 horas";
////        String expResult2 =        "Rota 2872";
////        String expResult3 =        "Endereco{rua=Clerigos, latitude=41.14582, longitude=-8.61398}";
////        String expResult4 =               "Endereco{rua=Majestic, latitude=41.14723, longitude=-8.60657}";
////        String expResult5 =       "Endereco{rua=Majestic, latitude=41.14723, longitude=-8.60657}";
////        String expResult6 =       "Endereco{rua=Clerigos, latitude=41.14582, longitude=-8.61398}";
////        String result = instance.toString();
////
////        assertEquals(true, result.contains(expResult1));
////        assertEquals(true, result.contains(expResult2));
////        assertEquals(true, result.contains(expResult3));
////        assertEquals(true, result.contains(expResult4));
////        assertEquals(true, result.contains(expResult5));
////        assertEquals(true, result.contains(expResult6));
////
////    }
////    /**
////     * Test of calcularRotaScooter method, of class Rota.
////     */
////    @Test
////    public void testCalcularRotaScooter() {
////        System.out.println("calcularRotaScooter");
////        List<List<Local>> listaExpected = new ArrayList<>();
////        List<Local> lista1 = new ArrayList<>();
////        List<Local> lista2 = new ArrayList<>();
////        lista1.add(locaisBD.get(0));
////        lista1.add(locaisBD.get(1));
////        lista2.add(locaisBD.get(1));
////        lista2.add(locaisBD.get(0));
////
////        listaExpected.add(lista1);
////        listaExpected.add(lista2);
////
////        Rota instance = new Rota(listaExpected);
////        boolean result = instance.calcularRotaScooter(200);
////        boolean expResult = true;
////        assertEquals(expResult, result);
////    }
//
//    @Test
//    public void testCalcularRotaScooterFalse() {
//        System.out.println("calcularRotaScooter");
//        List<List<Local>> listaExpected = new ArrayList<>();
//
//        Rota instance = new Rota(listaExpected);
//        boolean result = instance.calcularRotaScooter(200);
//        boolean expResult = false;
//        assertEquals(expResult, result);
//    }
//
////    /**
////     * Test of calcularRotaScooter method, of class Rota.
////     */
////    @Test
////    public void testCalcularRotaDrone() {
////        System.out.println("calcularRotaScooter");
////        List<List<Local>> listaExpected = new ArrayList<>();
////        List<Local> lista1 = new ArrayList<>();
////        List<Local> lista2 = new ArrayList<>();
////        lista1.add(locaisBD.get(0));
////        lista1.add(locaisBD.get(1));
////        lista2.add(locaisBD.get(1));
////        lista2.add(locaisBD.get(0));
////
////        listaExpected.add(lista1);
////        listaExpected.add(lista2);
////
////        Rota instance = new Rota(listaExpected);
////        boolean result = instance.calcularRotaDrone(200);
////        boolean expResult = true;
////        assertEquals(expResult, result);
////    }
//
//    @Test
//    public void testCalcularRotaDroneFalse() {
//        System.out.println("calcularRotaScooter");
//        List<List<Local>> listaExpected = new ArrayList<>();
//
//        Rota instance = new Rota(listaExpected);
//        boolean result = instance.calcularRotaDrone(200);
//        boolean expResult = false;
//        assertEquals(expResult, result);
//    }
}
