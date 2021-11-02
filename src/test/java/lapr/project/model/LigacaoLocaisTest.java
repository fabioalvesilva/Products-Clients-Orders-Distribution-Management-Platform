/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import static java.lang.Double.NEGATIVE_INFINITY;
import static lapr.project.model.TipoPesoEnum.*;
import lapr.project.utils.Constantes;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author carlos
 */
public class LigacaoLocaisTest {

    private Farmacia f1;
    private Farmacia f2;
    private Farmacia f3;
    private Cliente c3;
    private Cliente c4;
    private LigacaoLocais ligA;
    private LigacaoLocais ligB;
    private LigacaoLocais ligC ;
    private LigacaoLocais ligD;
    private Drone drone;
    private Scooter scooter;
    public LigacaoLocaisTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() {
        f1 = new Farmacia("Farmacia1", 111111111, "farm1@mail.com", 999999999, new Endereco(1, "Farmacia Sousa Torres", 41.2196991, -8.5608657, 83D));
        f1.setId(1);
        f2 = new Farmacia("Farmacia2", 222222222, "farm2@mail.com", 888888888, new Endereco(2, "Farmacia Guifoes", 41.2000, -8.66610, 74D));
        f1.setId(2);
        f3 = new Farmacia("Farmacia3", 333333333, "farm3@mail.com", 777777777, new Endereco(3, "Farmacia Guifoes Laboratorio", 41.2000, -8.66610, 74D));
        f1.setId(2);
        
        c3 = new Cliente("Cliente 3", "cli3@email.com", 777777777, "132", 3, "centro comercial parque", 41.1845917, -8.6843009, 27.0, 1234567);
        c3.setId(3);
        c4 = new Cliente("Cliente 4", "cl4@email.com", 666666666, "12345", 4, "centro comercial parque", 41.2333905, -8.623724, 110.0, 2315);
        c4.setId(4);

        //DAdos vento 
        //Atrito 0,5, com vento lateral de 11 km/h com um angulo de 20º coma trajetória,
        //com um coeficiente de atrito de 0,5, sem vento 
        ligA = new LigacaoLocais(f1, c3, 0, 10D, 267D, 0.5D);
        ligB = new LigacaoLocais(c3, c4, 1, 1D, 100D, 0.0D);
        ligC = new LigacaoLocais(f2, f3, 1, 1D, 100D, 0.0D);
        ligD = new LigacaoLocais(f1, f3, 1, 1D, 100D, 0.0D);
        
        drone = new Drone(5, 1, 1234, "MOd Dron", 1, 2000.0, 80D, 1, 100D);
        scooter = new Scooter(6, "scooterMat", "Mod scooter", 1, 1000, 1, 100, 100, Constantes.PESOMEDIOSCOOTER, Constantes.AREAFRONTALMEDIASCOOTER);
        
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getFrom method, of class LigacaoLocais.
     */
    @Test
    public void testGetFrom() {
        System.out.println("getFrom");

        Local expResult = f1;
        Local result = ligA.getFrom();
        assertEquals(expResult, result);

    }

    /**
     * Test of getTo method, of class LigacaoLocais.
     */
    @Test
    public void testGetTo() {
        System.out.println("getTo");
        Local expResult = c3;

        Local result = ligA.getTo();
        assertEquals(expResult, result);

    }

    /**
     * Test of isBidirecional method, of class LigacaoLocais.
     */
    @Test
    public void testIsBidirecional() {
        System.out.println("isBidirecional");

        boolean expResult = false;
        boolean expResult1 = true;

        boolean result = ligA.isBidirecional();
        boolean result1 = ligB.isBidirecional();
        assertEquals(expResult, result);
        assertNotEquals(expResult1, result);
        assertEquals(expResult1, result1);
        assertNotEquals(expResult, result1);
    }

    /**
     * Test of getVentoVelocidade method, of class LigacaoLocais.
     */
    @Test
    public void testGetVentoVelocidade() {
        System.out.println("getVentoVelocidade");

        Double expResult = 10D;
        Double result = ligA.getVentoVelocidade();
        assertEquals(expResult, result);

    }

    /**
     * Test of getVentoDirecao method, of class LigacaoLocais.
     */
    @Test
    public void testGetVentoDirecao() {
        System.out.println("getVentoDirecao");

        Double expResult = 267D;
        Double result = ligA.getVentoDirecao();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCoeficienteAtrito method, of class LigacaoLocais.
     */
    @Test
    public void testGetCoeficienteAtrito() {
        System.out.println("getCoeficienteAtrito");

        Double expResult = 0.5D;
        Double result = ligA.getCoeficienteAtrito();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDeclive method, of class LigacaoLocais.
     */
    @Test
    public void testGetDeclive() {
        System.out.println("getDeclive");

        Double expResult = -0.0051;
        Double result = ligA.getDeclive();
        assertEquals(expResult, result, 0.0001);

    }

    /**
     * Test of isPesosCalculados method, of class LigacaoLocais.
     */
    @Test
    public void testIsPesosCalculados() {
        System.out.println("isPesosCalculados");

        boolean expResult = false;
        boolean result = ligB.isPesosCalculados();
        assertEquals(expResult, result);
        assertNotEquals(true,result);
        
        
        ligB.calcularPesos(drone);
        boolean result1 = ligB.isPesosCalculados();
        boolean expResult1 = true;
        
        assertEquals(expResult1, result1);
        

    }

    /**
     * Test of setFrom method, of class LigacaoLocais.
     */
    @Test
    public void testSetFrom() {
        System.out.println("setFrom");
        Local from = f2;

        ligB.setFrom(from);
        Local expResult = f2;
        Local result = ligB.getFrom();
        assertEquals(expResult, result);

    }

    /**
     * Test of setTo method, of class LigacaoLocais.
     */
    @Test
    public void testSetTo() {
        System.out.println("setTo");
        Local to = f1;

        ligB.setTo(to);
        Local expResult = f1;
        Local result = ligB.getTo();
        assertEquals(expResult, result);

    }

    /**
     * Test of getTipoPeso method, of class LigacaoLocais.
     */
    @Test
    public void testGetTipoPeso() {
        System.out.println("getTipoPeso");
        TipoPesoEnum tipo1 = ENERGIA;
        TipoPesoEnum tipo2 = TEMPO;

        Double expResult = NEGATIVE_INFINITY;
        Double result = ligA.getTipoPeso(tipo1);
        Double result1 = ligB.getTipoPeso(tipo2);
        assertEquals(expResult, result);
        assertEquals(expResult, result1);

    }

    /**
     * Test of calcularPesos method, of class LigacaoLocais.
     */
    @Test
    public void testCalcularPesos() {
        System.out.println("calcularPesos");
        Veiculo v = drone;
        Veiculo v1 = null;
        
        boolean expResult = false;
        boolean expResult1 = true;
        
        boolean result = ligA.calcularPesos(v);
        boolean result1 = ligB.calcularPesos(v1);
        boolean result2 = ligC.calcularPesos(v);
        
        assertEquals(expResult, result2);
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
        assertNotEquals(expResult1, result1);
    }

    
    
    /**
     * Test of hashCode method, of class LigacaoLocais.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        
        int expResult = 1762251875;
        int result = ligA.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class LigacaoLocais.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Artigo a1 = new Artigo();
        
        boolean expResult = false;
        boolean result = ligA.equals(obj);
        boolean result1 = ligA.equals(a1);
        boolean result2 = ligA.equals(ligB);
        boolean result3 = ligA.equals(ligD);
        assertEquals(expResult, result);
        assertEquals (expResult, result1);
        assertEquals (expResult, result2);
        assertEquals (expResult, result3);
        
        assertNotEquals(true,result);
        assertNotEquals(true,result1);
        assertNotEquals(true,result2);
         assertNotEquals(true,result3);
        
    }

    /**
     * Test of toString method, of class LigacaoLocais.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        String expResult = "LigacaoLocais{from=Farmacia Sousa Torres, to=centro comercial parque, ventoVelocidade=10.0, ventoDirecao=267.0, coeficienteAtrito=0.5, pesoDistancia=-Infinity, pesoEnergia=-Infinity, pesoTempo=-Infinity}";
        String result = ligA.toString();
        assertEquals(expResult, result);

    }

}
