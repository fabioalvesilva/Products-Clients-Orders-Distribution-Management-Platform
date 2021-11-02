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
 * @author rmarinho
 */
public class FaturaTest {
    
    public FaturaTest() {
    }

    /**
     * Test of getId method, of class Fatura.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Fatura instance = new Fatura();
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNif method, of class Fatura.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");
        Fatura instance = new Fatura();
        Integer expResult = null;
        Integer result = instance.getNif();
        assertEquals(expResult, result);

    }

    /**
     * Test of getData method, of class Fatura.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        Fatura instance = new Fatura();
        String expResult = null;
        String result = instance.getData();
        assertEquals(expResult, result);

    }

    /**
     * Test of getValorPago method, of class Fatura.
     */
    @Test
    public void testGetValorPago() {
        System.out.println("getValorPago");
        Fatura instance = new Fatura();
        Double expResult = null;
        Double result = instance.getValorPago();
        assertEquals(expResult, result);

    }

    /**
     * Test of getValorTotal method, of class Fatura.
     */
    @Test
    public void testGetValorTotal() {
        System.out.println("getValorTotal");
        Fatura instance = new Fatura();
        Double expResult = null;
        Double result = instance.getValorTotal();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Fatura.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = null;
        Fatura instance = new Fatura();
        instance.setId(id);

    }

    /**
     * Test of setNif method, of class Fatura.
     */
    @Test
    public void testSetNif() {
        System.out.println("setNif");
        Integer nif = null;
        Fatura instance = new Fatura();
        instance.setNif(nif);

    }

    /**
     * Test of setData method, of class Fatura.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        String data = "";
        Fatura instance = new Fatura();
        instance.setData(data);

    }

    /**
     * Test of setValorTotal method, of class Fatura.
     */
    @Test
    public void testSetValorTotal() {
        System.out.println("setValorTotal");
        Fatura instance = new Fatura();
        instance.setValorTotal();

    }

    /**
     * Test of calcularValorTotal method, of class Fatura.
     */
    @Test
    public void testCalcularValorTotal() {
        System.out.println("calcularValorTotal");
        Fatura instance = new Fatura();
        instance.adicionarLinhaFatura("abc", 20);
        double expResult = 23.0;//VALOR ENTREGADEFAULT
        double result = instance.calcularValorTotal();
        assertEquals(expResult, result, expResult); //VALOR ENTREGADEFAULT

    }

    /**
     * Test of adicionarLinhaFatura method, of class Fatura.
     */
    @Test
    public void testAdicionarLinhaFatura() {
        System.out.println("adicionarLinhaFatura");
        String descricao = "";
        double valor = 0.0;
        Fatura instance = new Fatura();
        instance.adicionarLinhaFatura(descricao, valor);

    }

    /**
     * Test of toString method, of class Fatura.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Fatura instance = new Fatura();
        instance.adicionarLinhaFatura("abc", 20);
        String result = instance.toString();
        System.out.println(result);
        assertEquals(true,  result.contains("Fatura"));

    }
    
    /**
     * Test of setValorPago method, of class Fatura.
     */
    @Test
    public void testSetValorPago() {
        System.out.println("valorPago");
        Fatura instance = new Fatura();
        instance.setValorPago(23.2);
         double expResult = 23.2;
        assertEquals(expResult,  instance.getValorPago());

    }
    
}
