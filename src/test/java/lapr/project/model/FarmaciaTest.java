/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author carlos
 */
public class FarmaciaTest {
    
    Endereco endereco = new Endereco("Rua do Ah e Tal", 10.0, 12.0, 10.0);
    Parque parque = new Parque(1, 5, 5, 10, 10, 15, endereco);
    Set<Integer> idParques = new HashSet<>();
    Farmacia farmacia1 = new Farmacia("Farmacia 1", 123456789,"farmaci1@f.pt", 123456789, endereco, idParques);
    Farmacia farmacia2 = new Farmacia("Farmacia 2", 987654321,"farmaci2@f.pt", 987654312, endereco, idParques);
    
    public FarmaciaTest() {
        this.idParques.add(1);
        this.idParques.add(2);
        this.idParques.add(3);
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of getDescricao method, of class Farmacia.
     */
    @Test
    public void testGetDescricao() {
        System.out.println("getDescricao");
        Farmacia instance = new Farmacia();
        Farmacia instance2 = new Farmacia("AquiSeCuram", 111000111, "ahetaleisso@tontos.pt", 990011223, endereco, idParques);
        String expResult = null;
        String expResult2 = "AquiSeCuram";
        String result = instance.getDescricao();
        String result2 = instance2.getDescricao();
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
   
    }

    /**
     * Test of getNif method, of class Farmacia.
     */
    @Test
    public void testGetNif() {
        System.out.println("getNif");
        Farmacia instance = new Farmacia();
        Farmacia instance2 = new Farmacia("AquiSeCuram", 111000111, "ahetaleisso@tontos.pt", 990011223, endereco, idParques);
        Integer expResult = null;
        Integer expResult2 = 111000111;
        Integer result = instance.getNif();
        Integer result2 =instance2.getNif();
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
     
    }

    /**
     * Test of getEndereco method, of class Farmacia.
     */
    @Test
    public void testGetEndereco() {
        System.out.println("getEndereco");
        Farmacia instance = new Farmacia();
        Farmacia instance2 = new Farmacia("AquiSeCuram", 111000111, "ahetaleisso@tontos.pt", 990011223, endereco, idParques) ;
        Endereco expResult = null;
        Endereco expResult2 = endereco;
        Endereco result = instance.getEndereco();
        Endereco result2 = instance2.getEndereco();
        assertNotEquals(expResult, result);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of setDescricao method, of class Farmacia.
     */
    @Test
    public void testSetDescricao() {
        System.out.println("setDescricao");
        String descricao = "";
        Farmacia instance = new Farmacia();
        instance.setDescricao(descricao);
        String resultado= instance.getDescricao();
          assertEquals(descricao, resultado);
       
    }

    /**
     * Test of setNif method, of class Farmacia.
     */
    @Test
    public void testSetNif() {
        System.out.println("setNif");
        Integer nif = null;
        Farmacia instance = new Farmacia();
        instance.setNif(nif);
       Integer result= instance.getNif();
          assertEquals(nif, result);
    }

    /**
     * Test of setEndereco method, of class Farmacia.
     */
    @Test
    public void testSetEndereco() {
        System.out.println("setEndereco");
        Endereco endereco = null;
        Farmacia instance = new Farmacia();
        instance.setEndereco(endereco);
        Endereco result= instance.getEndereco();
          assertEquals(endereco, result);
      
    }

  /**
     * Test of getIdsParqueEstacionamento method, of class Farmacia.
     */
    @Test
    public void testGetIdsParqueEstacionamento() {
        System.out.println("getIdsParqueEstacionamento");
        Farmacia instance = new Farmacia();
        instance.setIdsParqueEstacionamento(idParques);
        Set<Integer> expResult = idParques;
        Set<Integer> result = instance.getIdsParqueEstacionamento();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdsParqueEstacionamento method, of class Farmacia.
     */
    @Test
    public void testSetIdsParqueEstacionamento() {
        System.out.println("setIdsParqueEstacionamento");
        Farmacia instance = new Farmacia();
        instance.setIdsParqueEstacionamento(idParques);
        Set<Integer> result = instance.getIdsParqueEstacionamento();
        Set<Integer> expResult = idParques;
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Farmacia.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertEquals(farmacia1.toString(), farmacia1.toString());
        assertNotEquals(farmacia1.toString(), farmacia2.toString());
    }

    /**
     * Test of getId method, of class Farmacia.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Farmacia instance = new Farmacia();
        Farmacia instance2 = new Farmacia("AquiSeCuram", 111000111, "aquisecuram@farmacias.com", 990011223, endereco, idParques);
        instance2.setId(1223);
        Integer expResult = 0;
        Integer expresult2 =1223;
        Integer result = instance.getId();
        Integer result2 = instance2.getId();
        assertEquals(expResult, result);
        assertEquals(expresult2,result2);
     
    }

 

    /**
     * Test of setId method, of class Farmacia.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = null;
        Farmacia instance = new Farmacia();
        instance.setId(id);
    
    }

  

    /**
     * Test of hashCode method, of class Farmacia.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Farmacia instance = new Farmacia();
        int expResult = 755548;
        int expResult2 = 733222;
        int expResult3 = 40938;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        assertNotEquals(expResult2,result);
         assertNotEquals(expResult3,result);
    }

    /**
     * Test of equals method, of class Farmacia.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Farmacia instance = new Farmacia();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    
    }
    
}
