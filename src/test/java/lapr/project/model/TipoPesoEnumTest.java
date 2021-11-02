/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fábio Silva
 */
public class TipoPesoEnumTest {
    
    public TipoPesoEnumTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of valueOf method, of class TipoPesoEnum.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String string = "TEMPO";
        TipoPesoEnum expResult = TipoPesoEnum.TEMPO;
        TipoPesoEnum result = TipoPesoEnum.valueOf(string);
        assertEquals(expResult, result);
    }
    
    
}
