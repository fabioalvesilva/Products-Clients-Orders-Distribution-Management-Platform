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
 * @author Fábio Silva
 */
public class ClienteTest {

    public ClienteTest() {
    }

    /**
     * Test of getCredito method, of class Cliente.
     */
    @Test
    public void testGetCredito() {
        System.out.println("getCredito");
        Cliente instance = new Cliente();
        instance.setCredito(10);
        Integer expResult = 10;
        Integer result = instance.getCredito();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCartaoCredito method, of class Cliente.
     */
    @Test
    public void testGetCartaoCredito() {
        System.out.println("getCartaoCredito");
        Cliente instance = new Cliente();
        Integer cartao = 123456789;
        instance.setCartaoCredito(cartao);
        Integer expResult = cartao;
        Integer result = instance.getCartaoCredito();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCredito method, of class Cliente.
     */
    @Test
    public void testSetCredito() {
        System.out.println("setCredito");
        Integer credito = 10;
        Cliente instance = new Cliente();
        instance.setCredito(credito);
        Integer expResult = 10;
        assertEquals(expResult, instance.getCredito());
    }

    /**
     * Test of setCartaoCredito method, of class Cliente.
     */
    @Test
    public void testSetCartaoCredito() {
        System.out.println("setCartaoCredito");
        Integer cartaoCredito = 123456789;
        Cliente instance = new Cliente();
        instance.setCartaoCredito(cartaoCredito);
        Integer expResult = cartaoCredito;
        assertEquals(expResult, instance.getCartaoCredito());
    }

    /**
     * Test of getEndereco method, of class Cliente.
     */
    @Test
    public void testGetEndereco() {
        System.out.println("getEndereco");

        Cliente instance = new Cliente();
        Endereco expResult = new Endereco();
        Endereco result = instance.getEndereco();

        Cliente instance2 = new Cliente("nome", "email@teste.pt", 919191923, "passe", 1, "Rua do Teste", 41.0, -8.0, 10.0, 1117374);
        Endereco expResult2 = new Endereco(1, "Rua do Teste", 41.0, -8.0, 10.0);
        Endereco result2 = instance2.getEndereco();

        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getId method, of class Cliente.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");

        Cliente instance = new Cliente();
        Integer expResult = 0;
        Integer result = instance.getId();

        Cliente instance2 = new Cliente("nome", "email@teste.pt", 919191923, "passe", 1, "Rua do Teste", 41.0, -8.0, 10.0, 1117374);
        instance2.setId(1223);
        Integer expResult2 = 1223;
        Integer result2 = instance2.getId();

        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }

}
