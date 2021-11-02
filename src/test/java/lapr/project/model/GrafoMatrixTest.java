/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author rmarinho
 */
public class GrafoMatrixTest {
    private int vertices;
    private ArrayList<Double> pesos;
    private GrafoMatrix grafo;
    int from ;
    int to;
    
    public GrafoMatrixTest() {
    }

    @BeforeEach
    public void setUp() {
        vertices = 4;
        grafo = new GrafoMatrix(4);
        
        grafo.inserirPeso(1, 3, -2);
        grafo.inserirPeso(2, 1, 4);
        grafo.inserirPeso(2, 3, 3);
        grafo.inserirPeso(3, 4, 2);
        grafo.inserirPeso(4, 2, -1);
        from = 0 ;
        to = 0;
        
    }

    /**
     * Test of getPeso method, of class GrafoMatrix.
     */
    @Test
    public void testGetPeso() {
         System.out.println("getPeso");

         from = 1;
         to = 3;
         double res = grafo.getPeso(from, to);
         double exp = -2;
        assertEquals(exp , res);
    }
    @Test
    public void testGetPeso2() {        
         from = -2;
          to=10;
        double res = grafo.getPeso(from, to);
        double exp = -1;
        assertEquals(exp , res);
}
      @Test
    public void testGetPeso3() {      
        from = 0;
        to=0;
        double res = grafo.getPeso(from, to);
        double exp = -1;
        assertEquals(exp , res);
}
       @Test
    public void testGetPeso4() {     
        from = 1;
        to=1;
        double res = grafo.getPeso(from, to);
        double exp = Double.POSITIVE_INFINITY;
        assertEquals(exp , res);

    }

    /**
     * Test of temConexao method, of class GrafoMatrix.
     */
    @Test
    public void testTemConexao1() {
        System.out.println("temConexao");
        int from = 1;
        int to = 1;
        boolean expResult = false;
        boolean result = grafo.temConexao(from, to);
        assertEquals(expResult, result);

    }
    
        /**
     * Test of temConexao method, of class GrafoMatrix.
     */
    @Test
    public void testTemConexao2() {
        System.out.println("temConexao");
        int from = 1;
        int to = 3;
        boolean expResult = true;
        boolean result = grafo.temConexao(from, to);
        assertEquals(expResult, result);

    }
        /**
     * Test of temConexao method, of class GrafoMatrix.
     */
    @Test
    public void testTemConexao3() {
        System.out.println("temConexao");
        int from = -10;
        int to = 100;
        boolean expResult = false;
        boolean result = grafo.temConexao(from, to);
        assertEquals(expResult, result);

    }
            /**
     * Test of temConexao method, of class GrafoMatrix.
     */
    @Test
    public void testTemConexao4() {
        System.out.println("temConexao");
        int from = 2;
        int to = 3;
        boolean expResult = true;
        boolean result = grafo.temConexao(from, to);
        assertEquals(expResult, result);

    }

    /**
     * Test of inserirPeso method, of class GrafoMatrix.
     */
    @Test
    public void testInserirPeso() {
        System.out.println("inserirPeso");
         from = 1;
         to = 3;
        double peso = 3.0;
        
        boolean expResult = true;
        boolean result = grafo.inserirPeso(from, to , peso);
        assertEquals(expResult, result);
        assertEquals(peso , grafo.getPeso(from, to));
        grafo.removerPeso(from, to);
        assertEquals(Double.POSITIVE_INFINITY, grafo.getPeso(from, to));
        

    }

    /**
     * Test of calcularAllPairs method, of class GrafoMatrix.
     */
    @Test
    public void testCalcularAllPairs() {
        System.out.println("calcularAllPairs");
        grafo.calcularAllPairs();
        //1 -> 4     0     1 -> 3 -> 4
        //2 -> 1     4     2 -> 1
        from = 1;
        to = 4;        
        
        Double result = grafo.getPeso(from, to);
        double exp = 0;
        List<Integer> result1 = grafo.getCaminho(from, to);
        
        List<Integer> exp1 = new ArrayList<>();
        exp1.add(from);
        exp1.add(3);
        exp1.add(to);
        
        assertArrayEquals(exp1.toArray(), result1.toArray());
        assertEquals(exp, result);
                
                
        from = 2;
        to = 1;    
        exp1.clear();
        exp1.add(from);
        exp1.add(to);
        result = grafo.getPeso(from, to);
        exp = 4;
        result1 = grafo.getCaminho(from, to);
        assertArrayEquals(exp1.toArray(), result1.toArray());
        assertEquals(exp, result);
        
        
                        
        from = 0;
        to = 0;    
        exp1.clear();
        result = grafo.getPeso(from, to);
        exp = -1;
        result1 = grafo.getCaminho(from, to);
        assertArrayEquals(exp1.toArray(), result1.toArray());
        assertEquals(exp, result);
        
    }



//    /**
//     * Test of getCaminhoComIntermedios method, of class GrafoMatrix.
//     */
//    @Test
//    public void testGetCaminhoComIntermedios() {
//        System.out.println("getCaminhoComIntermedios");
//        int from = 0;
//        int to = 0;
//        List<Integer> intermedios = null;
//        GrafoMatrix instance = null;
//        List<Integer> expResult = null;
//        List<Integer> result = instance.getCaminhoComIntermedios(from, to, intermedios);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    /*
    pair     dist    path
1 -> 2    -1     1 -> 3 -> 4 -> 2
1 -> 3    -2     1 -> 3
1 -> 4     0     1 -> 3 -> 4
2 -> 1     4     2 -> 1
2 -> 3     2     2 -> 1 -> 3
2 -> 4     4     2 -> 1 -> 3 -> 4
3 -> 1     5     3 -> 4 -> 2 -> 1
3 -> 2     1     3 -> 4 -> 2
3 -> 4     2     3 -> 4
4 -> 1     3     4 -> 2 -> 1
4 -> 2    -1     4 -> 2
4 -> 3     1     4 -> 2 -> 1 -> 3

    */

    /**
     * Test of isCalculada method, of class GrafoMatrix.
     */
    @Test
    public void testIsCalculada() {
        System.out.println("isCalculada");
        
        
        

        assertEquals(false,grafo.isCalculada());
        
        ArrayList<Integer> intermedios = new ArrayList<>();
        intermedios.add(2);
        //grafo.getCaminhoComIntermedios(1, 1, intermedios);
        assertEquals(false,grafo.isCalculada());
        grafo.calcularAllPairs();
        
        grafo.getCaminhoComIntermedios(1, 1, intermedios);
        assertEquals(true,grafo.isCalculada());
        
        grafo.inserirPeso(1, 4, 10D);
        assertEquals(false,grafo.isCalculada());
        
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of temConexao method, of class GrafoMatrix.
     */
    @Test
    public void testTemConexao() {
        System.out.println("temConexao");
        int from = 1;
        int to = 1;
        boolean expResult = false;
        boolean result = grafo.temConexao(from, to);
        assertEquals(expResult, result);
        
        
        assertEquals(true, grafo.temConexao(from, 3));
        grafo.calcularAllPairs();
        
         result = grafo.temConexao(from, to);
        assertEquals(true, result);
        assertEquals(true,grafo.temConexao(1, 4));

    }

    /**
     * Test of removerPeso method, of class GrafoMatrix.
     */
    @Test
    public void testRemoverPeso() {
        System.out.println("removerPeso");
        assertEquals(true,grafo.temConexao(1, 3));
        grafo.removerPeso(1, 3);
        assertEquals(false,grafo.temConexao(1, 3));
    }

    /**
     * Test of getCaminho method, of class GrafoMatrix.
     */
    @Test
    public void testGetCaminho() {
        System.out.println("getCaminho");

        List<Integer> expResult = new ArrayList<>();
        //form 1 - 2 -> 1 -> 3 -> 4 -> 2
        expResult.add(1);
        expResult.add(3);
        expResult.add(4);
        expResult.add(2);
        grafo.calcularAllPairs();
        List<Integer> result = grafo.getCaminho(1, 2);
        
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of getCaminhoComIntermedios method, of class GrafoMatrix.
     */
    @Test
    public void testGetCaminhoComIntermedios() {
        System.out.println("getCaminhoComIntermedios");
        List<Integer> expResult = new ArrayList<>();
        //form 1 - 2  -> 1 -> 3 -> 4 -> 2
        //intermedio 2 -> 1      4     2 -> 1
        List<Integer> intermedios = new ArrayList<>();
        intermedios.add(2);
        expResult.add(1);
        expResult.add(3);
        expResult.add(4);
        expResult.add(2);
        expResult.add(1);
        grafo.calcularAllPairs();
        List<Integer> result = grafo.getCaminhoComIntermedios(1, 1, intermedios);
        
        assertArrayEquals(expResult.toArray(), result.toArray());
        

    }

    /**
     * Test of printResult method, of class GrafoMatrix.
     */
    @Test
    public void testPrintResult() {
        System.out.println("printResult");
        GrafoMatrix instance = grafo;
        instance.printResult();

    }
    
}
