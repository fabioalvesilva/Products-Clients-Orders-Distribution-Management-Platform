/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.Artigo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author carlos
 */
public class PairTest {
    
    public PairTest() {
    }

     @BeforeEach
    public void setUp() {
        
        Pair p1 = new Pair(1,2);
        Pair p2 = new Pair(2,3);
        
    }
    /**
     * Test of getFirst method, of class Pair.
     */
    @Test
    public void testGetFirst() {
        System.out.println("getFirst");
        Pair p1 = new Pair(1,2);
                 
       
        int expResult1 = 1;
               
        int result1 = p1.getFirst();
    
        assertEquals(expResult1, result1);
     
    }

    /**
     * Test of getSecond method, of class Pair.
     */
     @Test
    public void testGetSecond() {
        System.out.println("getSecond");
        Pair p1 = new Pair(1,2);
                 
       
        int expResult1 = 2;
               
        int result1 = p1.getSecond();
    
        assertEquals(expResult1, result1);
     
  
    }

    /**
     * Test of hashCode method, of class Pair.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        
        Pair p1 = new Pair(1,2);
                 
        int expResult1 = 17466;   
         
               
        int result1 = p1.hashCode();
      
        assertEquals(expResult1, result1);
    
    }

    /**
     * Test of equals method, of class Pair.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Object obj = null;
        Pair instance = new Pair(1,2);
        Pair p1 = new Pair (1,2);
        Pair p2 = new Pair (2,3);
        Pair p4 = new Pair (2,1);
        Artigo a1 = new Artigo();
        
        boolean expResult = false;
        boolean expResult1 = true;
        
        boolean result = instance.equals(obj);
        boolean result1 = instance.equals(p1);
        boolean result2 = instance.equals(p2);
        boolean result3 = instance.equals(a1);
        
        assertEquals(false, p1.equals(p4));
        
        assertEquals(expResult, result);
        assertEquals(expResult1, result1);
        assertEquals(expResult, result3);
        assertEquals(expResult,result2);
        assertNotEquals(expResult1,result2);
        assertNotEquals(expResult1,result3);
        
        
    }
    
}
