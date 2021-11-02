/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Fábio Silva
 */
public class ArtigoTest {
    
    
    private Artigo artigo1;
    private Artigo artigo2;
    
    public ArtigoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        artigo1 = new Artigo(1,"Artigo 1", 50.0, 23.0, 10.0);
        artigo2 = new Artigo(2, "Artigo 2", 55.0, 23.0, 5.0);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getId_artigo method, of class Artigo.
     */
    @Test
    public void testGetId_artigo() {
        System.out.println("getId_artigo");
        int expResult = 1;
        int result = artigo1.getIdArtigo();
        Artigo instance = new Artigo();
        instance.setIdArtigo(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDesignacao method, of class Artigo.
     */
    @Test
    public void testGetDesignacao() {
        System.out.println("getDesignacao");
        Artigo instance = new Artigo();
        instance.setDesignacao("Artigo 1");
        String expResult = "Artigo 1";
        String result = instance.getDesignacao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrecoUnitario method, of class Artigo.
     */
    @Test
    public void testGetPrecoUnitario() {
        System.out.println("getPrecoUnitario");
        Artigo instance = new Artigo();
        instance.setPrecoUnitario(55.0);
        double expResult = 55.0;
        double result = instance.getPrecoUnitario();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getIva method, of class Artigo.
     */
    @Test
    public void testGetIva() {
        System.out.println("getIva");
        Artigo instance = new Artigo();
        instance.setIva(23.0);
        double expResult = 23.0;
        double result = instance.getIva();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getPeso method, of class Artigo.
     */
    @Test
    public void testGetPeso() {
        System.out.println("getPeso");
        Artigo instance = new Artigo();
        instance.setPeso(50.0);
        double expResult = 50.0;
        double result = instance.getPeso();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setId_artigo method, of class Artigo.
     */
    @Test
    public void testSetId_artigo() {
        System.out.println("setId_artigo");
        int id_artigo = 2;
        Artigo instance = new Artigo();
        instance.setIdArtigo(id_artigo);
        Integer expResult = 2;
        assertEquals(expResult, instance.getIdArtigo());
    }

    /**
     * Test of setDesignacao method, of class Artigo.
     */
    @Test
    public void testSetDesignacao() {
        System.out.println("setDesignacao");
        String designacao = "Artigo 0";
        Artigo instance = new Artigo();
        instance.setDesignacao(designacao);
        String expResult = "Artigo 0";
        assertEquals(expResult, instance.getDesignacao());
    }

    /**
     * Test of setPrecoUnitario method, of class Artigo.
     */
    @Test
    public void testSetPrecoUnitario() {
        System.out.println("setPrecoUnitario");
        double precoUnitario = 50.0;
        Artigo instance = new Artigo();
        instance.setPrecoUnitario(precoUnitario);
        double expResult = 50.0;
        assertEquals(expResult, instance.getPrecoUnitario());
    }

    /**
     * Test of setIva method, of class Artigo.
     */
    @Test
    public void testSetIva() {
        System.out.println("setIva");
        double iva = 23.0;
        Artigo instance = new Artigo();
        instance.setIva(iva);
        double expResult = 23.0;
        assertEquals(expResult, instance.getIva());
    }

    /**
     * Test of setPeso method, of class Artigo.
     */
    @Test
    public void testSetPeso() {
        System.out.println("setPeso");
        double peso = 10.0;
        Artigo instance = new Artigo();
        instance.setPeso(peso);
        double expResult = 10.0;
        assertEquals(expResult, instance.getPeso());
    }

    /**
     * Test of toString method, of class Artigo.
     */
    @Test
    public void testToString() {
        String resultado1 = null;
        String resultado2 = null;
        assertNull(resultado1);
        assertNull(resultado2);
        assertEquals(resultado1, resultado2);
        assertEquals(artigo1.toString(), artigo1.toString());
        assertNotEquals(artigo1.toString(), artigo2.toString());
    }

    /**
     * Test of custoArtigo method, of class Artigo.
     */
    @Test
    public void testCustoArtigo() {
        System.out.println("custoArtigo");
        int quantidade = 0;
        Artigo a1 = new Artigo(19, "test", 13, 5, 3);
        Artigo a2 = new Artigo(18, "test2", 14, 5, 3);
        double expResult = 0;
        double expResult2 = (13.65*2);
        double result = a1.custoArtigo(0);
        double result2 = a1.custoArtigo(2);
        assertEquals(0, result, 0.0);
        assertEquals(expResult2, result2, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
