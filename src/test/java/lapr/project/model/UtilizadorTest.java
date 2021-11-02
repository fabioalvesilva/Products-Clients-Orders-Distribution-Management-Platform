/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Fábio Silva
 */
public class UtilizadorTest {
    
    private Utilizador utilizador1;
    private Utilizador utilizador2;
    
    
    public UtilizadorTest() {
        
    }
    
    @BeforeEach
    public void setUp() {
        
        utilizador1 = new Utilizador(1, "Nome 1", "utilizador1@gmail.com", 999999999, "1234");
        utilizador2 = new Utilizador(2, "Nome 2", "utilizador2@gmail.com", 98654321, "1234");
    }

    /**
     * Test of getNome method, of class Utilizador.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Utilizador instance = new Utilizador();
        instance.setNome("Fabio");
        String expResult = "Fabio";
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Utilizador.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Utilizador instance = new Utilizador();
        instance.setEmail("isep@isep.ipp.pt");
        String expResult = "isep@isep.ipp.pt";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNif method, of class Utilizador.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");
        Utilizador instance = new Utilizador();
        instance.setNif(999999999);
        Integer expResult = 999999999;
        Integer result = instance.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class Utilizador.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Utilizador instance = new Utilizador();
        instance.setPassword("1234");
        String expResult = "1234";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNome method, of class Utilizador.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "Fabio";
        Utilizador instance = new Utilizador();
        instance.setNome(nome);
        String expResult = "Fabio";
        assertEquals(expResult, instance.getNome());
    }

    /**
     * Test of setEmail method, of class Utilizador.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "isep@isep.ipp.pt";
        Utilizador instance = new Utilizador();
        instance.setEmail(email);
        String expResult = "isep@isep.ipp.pt";
        assertEquals(expResult, instance.getEmail());
    }

    /**
     * Test of setNif method, of class Utilizador.
     */
    @Test
    public void testSetNif() {
        System.out.println("setNif");
        Integer nif = 123456789;
        Utilizador instance = new Utilizador();
        instance.setNif(nif);
        Integer expResult = 123456789;
        assertEquals(expResult, instance.getNif());
    }

    /**
     * Test of setPassword method, of class Utilizador.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "12345";
        Utilizador instance = new Utilizador();
        instance.setPassword(password);
        String expResult = "12345";
        assertEquals(expResult, instance.getPassword());
    }

    /**
     * Test of toString method, of class Utilizador.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        assertEquals(utilizador1.toString(), utilizador1.toString());
        assertNotEquals(utilizador1.toString(), utilizador2.toString());
    }

    /**
     * Test of getId method, of class Utilizador.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Utilizador instance = new Utilizador();
        Integer expResult = 0;
        Integer result = instance.getId();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setId method, of class Utilizador.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = null;
        Utilizador instance = new Utilizador();
        instance.setId(id);
       
    }

    /**
     * Test of hashCode method, of class Utilizador.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Utilizador instance = new Utilizador();
        int expResult = -2144473827;
        int result = instance.hashCode();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of equals method, of class Utilizador.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Utilizador instance = new Utilizador();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Utilizador.
     */
    @Test
    public void testEqualsNotOk() {
        System.out.println("equals");
        boolean expResult = false;
        boolean result = utilizador1.equals(utilizador2);
        assertEquals(expResult, result);
    }
    
}
