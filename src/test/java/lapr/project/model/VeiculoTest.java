/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author carlos
 */
public class VeiculoTest {
    
    public VeiculoTest() {
    }

    /**
     * Test of getId method, of class Veiculo.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Veiculo instance = new VeiculoImpl();
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getVeiculo method, of class Veiculo.
     */
    @Test
    public void testGetVeiculo() {
        System.out.println("getVeiculo");
        Veiculo instance = new VeiculoImpl();
        Veiculo expResult = null;
        Veiculo result = instance.getVeiculo();
        assertEquals(expResult, result);
        
    }

    public class VeiculoImpl implements Veiculo {

        public Integer getId() {
            return null;
        }

        public Veiculo getVeiculo() {
            return null;
        }
    }
    
}
