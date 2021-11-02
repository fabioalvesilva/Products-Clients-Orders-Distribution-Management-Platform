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
public class ScooterTest {
    
    public ScooterTest() {
    }

    /**
     * Test of getMatricula method, of class Scooter.
     */
    @Test
    public void testGetMatricula() {
        System.out.println("getMatricula");
        Scooter instance = new Scooter();
        instance.setMatricula("44-HD-93");
        String expResult = "44-HD-93";
        String result = instance.getMatricula();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Scooter.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Scooter instance = new Scooter();
        instance.setId(1);
        Integer expResult = 1;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getIdTipoBateria method, of class Scooter.
     */
    @Test
    public void testGetIdTipoBateria() {
        System.out.println("getIdTipoBateria");
        Scooter instance = new Scooter();
        instance.setIdTipoBateria(1);
        Integer expResult = 1;
        Integer result = instance.getIdTipoBateria();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdEstado method, of class Scooter.
     */
    @Test
    public void testGetIdEstado() {
        System.out.println("getIdEstado");
        Scooter instance = new Scooter();
        instance.setIdEstado(1);
        Integer expResult = 1;
        Integer result = instance.getIdEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCapacidadeAtual method, of class Scooter.
     */
    @Test
    public void testGetCapacidadeAtual() {
        System.out.println("getCapacidadeAtual");
        Scooter instance = new Scooter();
        instance.setCapacidadeAtual(100);
        instance.setCapacidadeBateria(100);
        Integer expResult = 100;
        Integer result = instance.getCapacidadeAtual();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setId method, of class Scooter.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = 1;
        Scooter instance = new Scooter();
        instance.setId(id);
        Integer expResult = 1; 
        assertEquals(expResult, instance.getId());
    }

    /**
     * Test of setMatricula method, of class Scooter.
     */
    @Test
    public void testSetMatricula() {
        System.out.println("setMatricula");
        String matricula = "44-HD-93";
        Scooter instance = new Scooter();
        instance.setMatricula(matricula);
        String expResult = "44-HD-93"; 
        assertEquals(expResult, instance.getMatricula());
    }
    
    /**
     * Test of setIdTipoBateria method, of class Scooter.
     */
    @Test
    public void testSetIdTipoBateria() {
        System.out.println("setIdTipoBateria");
        Integer idTipoBateria = 1;
        Scooter instance = new Scooter();
        instance.setIdTipoBateria(idTipoBateria);
        Integer expResult = 1; 
        assertEquals(expResult, instance.getIdTipoBateria());
    }

    /**
     * Test of setIdEstado method, of class Scooter.
     */
    @Test
    public void testSetIdEstado() {
        System.out.println("setIdEstado");
        Integer idEstado = 1;
        Scooter instance = new Scooter();
        instance.setIdEstado(idEstado);
        Integer expResult = 1; 
        assertEquals(expResult, instance.getIdEstado());
    }

    /**
     * Test of setCapacidadeAtual method, of class Scooter.
     */
    
    @Test
    public void testSetCapacidadeAtual() {
        System.out.println("setCapacidadeAtual");
        Integer capacidadeAtual = 10;
        Integer capacidadeBateria = 100;
        Scooter instance = new Scooter();
        instance.setCapacidadeAtual(capacidadeAtual);
        instance.setCapacidadeBateria(capacidadeBateria);
        Integer expResult = 10; 
        assertEquals(expResult, instance.getCapacidadeAtual());
    }
    
    /**
     * Test of getModelo method, of class Scooter.
     */
    @Test
    public void testGetModelo() {
        System.out.println("getModelo");
        Scooter instance = new Scooter();
        instance.setModelo("abc");
        String expResult = "abc";
        String result = instance.getModelo();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getEficiencia method, of class Scooter.
     */
    @Test
    public void testGetEficiencia() {
        System.out.println("getEficiencia");
        Scooter instance = new Scooter();
        instance.setEficienciaBateria(70);
        Integer expResult = 70;
        Integer result = instance.getEficienciaBateria();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCapacidadeBateria method, of class Scooter.
     */
    @Test
    public void testGetCapacidadeBateria() {
        System.out.println("getCapacidadeBateria");
        Scooter instance = new Scooter();
        Integer expResult = null;
        Integer result = instance.getCapacidadeBateria();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getEficienciaBateria method, of class Scooter.
     */
    @Test
    public void testGetEficienciaBateria() {
        System.out.println("getEficienciaBateria");
        Scooter instance = new Scooter();
        Integer expResult = null;
        Integer result = instance.getEficienciaBateria();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPeso method, of class Scooter.
     */
     @Test
    public void testGetPeso() {
        System.out.println("getPeso");
        Scooter instance = new Scooter();
        Double expResult = null;
        Double result = instance.getPeso();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of getAreaFrontal method, of class Scooter.
     */
     @Test
    public void testGetAreaFrontal() {
        System.out.println("getAreaFrontal");
        Scooter instance = new Scooter();
        Double expResult = null;
        Double result = instance.getAreaFrontal();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setModelo method, of class Scooter.
     */
     @Test
    public void testSetModelo() {
        System.out.println("setModelo");
        String modelo = "";
        Scooter instance = new Scooter();
        instance.setModelo(modelo);
     
    }

    /**
     * Test of setCapacidadeBateria method, of class Scooter.
     */
      @Test
    public void testSetCapacidadeBateria() {
        System.out.println("setCapacidadeBateria");
        Integer capacidadeBateria = null;
        Scooter instance = new Scooter();
        instance.setCapacidadeBateria(capacidadeBateria);
    
    }

    /**
     * Test of setEficienciaBateria method, of class Scooter.
     */
     @Test
    public void testSetEficienciaBateria() {
        System.out.println("setEficienciaBateria");
        Integer eficienciaBateria = null;
        Scooter instance = new Scooter();
        instance.setEficienciaBateria(eficienciaBateria);
       
    }

    /**
     * Test of setPeso method, of class Scooter.
     */
     @Test
    public void testSetPeso() {
        System.out.println("setPeso");
        Double peso = null;
        Scooter instance = new Scooter();
        instance.setPeso(peso);
       
    }

    /**
     * Test of setAreaFrontal method, of class Scooter.
     */
      @Test
    public void testSetAreaFrontal() {
        System.out.println("setAreaFrontal");
        Double areaFrontal = null;
        Scooter instance = new Scooter();
        instance.setAreaFrontal(areaFrontal);
    
    }

    /**
     * Test of calcularCapacidadeAtual method, of class Scooter.
     */
     @Test
    public void testCalcularCapacidadeAtual() {
        System.out.println("calcularCapacidadeAtual");
        Scooter instance = new Scooter();
        Integer expResult = null;
        Integer result = instance.calcularCapacidadeAtual();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of toString method, of class Scooter.
     */
      @Test
    public void testToStringEmpty() {
        System.out.println("toString");
        Scooter instance = new Scooter();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of toString method, of class Scooter.
     */
      @Test
    public void testToString() {
        System.out.println("toString");
        Scooter instance = new Scooter(2, "ZZ-12-ZZ", "DR300x", 1, 200, 1, 100, 90, 45.0, 0.6);
        String expResult = "Scooter{" + "id=" + instance.getId() + ", matricula=" + instance.getMatricula() + ", modelo=" + instance.getModelo() + ", idTipoBateria=" + instance.getIdTipoBateria() + ", capacidadeBateria=" + instance.getCapacidadeBateria()+ ", idEstado=" + instance.getIdEstado() + ", capacidadeAtual=" + instance.getCapacidadeAtual() + ", eficienciaBateria=" + instance.getEficienciaBateria() + '}';
        String result = instance.toString();
        assertEquals(expResult, result);
       
    }
    
}
