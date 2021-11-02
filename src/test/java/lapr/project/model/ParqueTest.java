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
public class ParqueTest {
    
    Endereco endereco1 = new Endereco("Rua do Ah e Tal", 10.0, 12.0, 10.0);
    Endereco endereco2 = new Endereco("Rua do Ah", 11.0, 13.0, 15.0);
    Parque parque1 = new Parque(3, 5, 5, 10, 10, 15, endereco1);
    Parque parque2 = new Parque(2, 5, 5, 15, 15, 15, endereco2);
    
    public ParqueTest() {
    }

    /**
     * Test of getId method, of class Parque.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Parque instance = new Parque();
        Parque instance2 = new Parque(21, 3, 5, 5, 10, 10, 15, endereco1);
        Integer expResult = 0;
        Integer expResult2 = 21;
        Integer result = instance.getId();
        Integer result2 =instance2.getId();
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }  

    /**
     * Test of getOcupacaoLugaresEletricos method, of class Parque.
     */
    @Test
    public void testGetOcupacaoLugaresEletricos() {
        System.out.println("getOcupacaoLugaresEletricos");
        Parque instance = new Parque();
        Parque instance2 = new Parque(3, 5, 5, 10, 10, 15, endereco1);
        Integer expResult = null;
        Integer expResult2 = 5;
        Integer result = instance.getOcupacaoLugaresEletricos();
        Integer result2 = instance2.getOcupacaoLugaresEletricos();
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
      
    }

    /**
     * Test of getOcupacaoLugaresNormais method, of class Parque.
     */
    @Test
    public void testGetOcupacaoLugaresNormais() {
        System.out.println("getOcupacaoLugaresNormais");
        Parque instance = new Parque();
        Parque instance2 = new Parque(3, 5, 5, 10, 10, 15, endereco1);
        Integer expResult = null;
        Integer expResult2 = 5;
        Integer result = instance.getOcupacaoLugaresNormais();
        Integer result2 = instance2.getOcupacaoLugaresNormais();
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
      
    }

    /**
     * Test of getMaxLugaresEletricos method, of class Parque.
     */
    @Test
    public void testGetMaxLugaresEletricos() {
        System.out.println("getMaxLugaresEletricos");
        Parque instance = new Parque();
        Parque instance2 = new Parque(3, 5, 5, 10, 10, 15, endereco1);
        Integer expResult = null;
        Integer expResult2 = 10;
        Integer result = instance.getMaxLugaresEletricos();
        Integer result2 = instance2.getMaxLugaresEletricos();
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    
    }

    /**
     * Test of getMaxLugaresNormais method, of class Parque.
     */
    @Test
    public void testGetMaxLugaresNormais() {
        System.out.println("getMaxLugaresNormais");
        Parque instance = new Parque();
        Parque instance2 = new Parque(3, 5, 5, 10, 10, 15, endereco1);
        Integer expResult = null;
        Integer expResult2 = 10;
        Integer result = instance.getMaxLugaresNormais();
        Integer result2 =instance2.getMaxLugaresNormais();
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
  
    }

    /**
     * Test of setId method, of class Parque.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = 20;
        Parque instance = new Parque();
        instance.setId(id);
        Integer result = instance.getId();
        assertEquals(id, result);
    }

    /**
     * Test of setOcupacaoLugaresEletricos method, of class Parque.
     */
    @Test
    public void testSetOcupacaoLugaresEletricos() {
        System.out.println("setOcupacaoLugaresEletricos");
        Integer ocupacaoLugaresEletricos = 5;
        Parque instance = new Parque();
        instance.setOcupacaoLugaresEletricos(ocupacaoLugaresEletricos);
        Integer result = instance.getOcupacaoLugaresEletricos();
        assertEquals(ocupacaoLugaresEletricos, result);
    }

    /**
     * Test of setOcupacaoLugaresNormais method, of class Parque.
     */
    @Test
    public void testSetOcupacaoLugaresNormais() {
        System.out.println("setOcupacaoLugaresNormais");
        Integer ocupacaoLugaresNormais = 6;
        Parque instance = new Parque();
        instance.setOcupacaoLugaresNormais(ocupacaoLugaresNormais);
        Integer result = instance.getOcupacaoLugaresNormais();
        assertEquals(ocupacaoLugaresNormais, result);
    }

    /**
     * Test of setMaxLugaresEletricos method, of class Parque.
     */
    @Test
    public void testSetMaxLugaresEletricos() {
        System.out.println("setMaxLugaresEletricos");
        Integer maxLugaresEletricos = 11;
        Parque instance = new Parque();
        instance.setMaxLugaresEletricos(maxLugaresEletricos);
        Integer result = instance.getMaxLugaresEletricos();
        assertEquals(maxLugaresEletricos, result);
    }

    /**
     * Test of setMaxLugaresNormais method, of class Parque.
     */
    @Test
    public void testSetMaxLugaresNormais() {
        System.out.println("setMaxLugaresNormais");
        Integer maxLugaresNormais = 12;
        Parque instance = new Parque();
        instance.setMaxLugaresNormais(maxLugaresNormais);
        Integer result = instance.getMaxLugaresNormais();
        assertEquals(maxLugaresNormais, result);
     
    }
    
    /**
     * Test of setCapacidadeTensao method, of class Parque.
     */
    @Test
    public void testSetCapacidadeTensao() {
        System.out.println("setCapacidadeTensao");
        Integer capacidadeTensao = 12;
        Parque instance = new Parque();
        instance.setCapacidadeTensao(capacidadeTensao);
        Integer result = instance.getCapacidadeTensao();
        assertEquals(capacidadeTensao, result);
    }
    
    /**
     * Test of setEndereco method, of class Parque.
     */
    @Test
    public void testSetEndereco() {
        System.out.println("setEndereco");
        Endereco endereco = new Endereco();
        Parque instance = new Parque();
        instance.setEndereco(endereco);
        Endereco result = instance.getEndereco();
        assertEquals(endereco, result);
    }

    /**
     * Test of toString method, of class Parque.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        assertEquals(parque1.toString(), parque1.toString());
        assertEquals(parque2.toString(),parque2.toString());
        assertNotEquals(parque2.toString(),parque1.toString());
     
    }
    
    /**
     * Test of getId_TipoParque method, of class Fatura.
     */
    @Test
    public void testGetId_TipoParque() {
        System.out.println("valorPago");
        Parque instance = new Parque();
        instance.setIdTipoParque(2);
        int expResult = 2;
        assertEquals(expResult,  instance.getIdTipoParque());

    }
    
}
