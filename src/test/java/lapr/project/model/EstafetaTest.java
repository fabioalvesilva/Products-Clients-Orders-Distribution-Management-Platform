/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author carlos
 */
public class EstafetaTest {

    public EstafetaTest() {
    }

    /**
     * Test of getId method, of class Estafeta.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Estafeta instance = new Estafeta();
        Estafeta instance2 = new Estafeta("Eu", "estafome@email.pt", 111000111, "T3x45R4nG3R", 21, 1110001112, 82.0);
        Integer expResult = null;
        Integer expResult2 = 21;
        Integer result = instance.getId();
        Integer result2 = instance2.getId();
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getNiss method, of class Estafeta.
     */
    @Test
    public void testGetNiss() {
        System.out.println("getNiss");
        Estafeta instance = new Estafeta();
        Estafeta instance2 = new Estafeta("Eu", "estafome@email.pt", 111000111, "T3x45R4nG3R", 21, 1110001112, 82.0);
        Integer expResult = null;
        Integer expResult2 = 1110001112;
        Integer result = instance.getNiss();
        Integer result2 = instance2.getNiss();
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of setId method, of class Estafeta.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = 20;
        Estafeta instance = new Estafeta();
        instance.setId(id);
        Integer result = instance.getId();
        assertEquals(id, result);

    }

    /**
     * Test of setNiss method, of class Estafeta.
     */
    @Test
    public void testSetNiss() {
        System.out.println("setNiss");
        Integer niss = 1110001113;
        Estafeta instance = new Estafeta();
        instance.setNiss(niss);
        Integer result = instance.getNiss();
        assertEquals(niss, result);
    }
 

    /**
     * Test of toString method, of class Estafeta.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Estafeta instance = new Estafeta();
        String expResult = "Estafeta{id=null, niss=null}";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }

}
