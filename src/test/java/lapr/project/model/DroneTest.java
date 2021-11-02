/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Rafael
 */
public class DroneTest {

    public DroneTest() {
    }

    @BeforeEach
    public void setUp() {
        Drone drone1 = new Drone(1, 1, 1, "teste", 1, 2.0, 3.0, 1, 2.0);
        Drone drone2 = new Drone(2, 2, 2, "teste", 1, 2.0, 3.0, 1, 2.0);
    }

    /**
     * Test of getId method, of class Drone.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Drone instance = new Drone();
        instance.setId(1);
        Integer expResult = 1;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdFarmacia method, of class Drone.
     */
    @Test
    public void testGetIdFarmacia() {
        System.out.println("getIdFarmacia");
        Drone instance = new Drone();
        instance.setIdFarmacia(1);
        Integer expResult = 1;
        Integer result = instance.getIdFarmacia();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNrRegisto method, of class Drone.
     */
    @Test
    public void testGetNrRegisto() {
        System.out.println("getNrRegisto");
        Drone instance = new Drone();
        instance.setNrRegisto(1);
        Integer expResult = 1;
        Integer result = instance.getNrRegisto();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDesignacaoModelo method, of class Drone.
     */
    @Test
    public void testGetDesignacaoModelo() {
        System.out.println("getDesignacaoModelo");
        Drone instance = new Drone();
        instance.setDesignacaoModelo("teste");
        String expResult = "teste";
        String result = instance.getDesignacaoModelo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdEstado method, of class Drone.
     */
    @Test
    public void testGetIdEstado() {
        System.out.println("getIdEstado");
        Drone instance = new Drone();
        instance.setIdEstado(1);
        Integer expResult = 1;
        Integer result = instance.getIdEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCargaBateriaAtual method, of class Drone.
     */
    @Test
    public void testGetCargaBateriaAtual() {
        System.out.println("getCargaBateriaAtual");
        Drone instance = new Drone();
        instance.setCargaBateriaAtual(100.0);
        Double expResult = 100.0;
        Double result = instance.getCargaBateriaAtual();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCapacidadeBateria method, of class Drone.
     */
    @Test
    public void testGetCapacidadeBateria() {
        System.out.println("getCapacidadeBateria");
        Drone instance = new Drone();
        instance.setCapacidadeBateria(20.0);
        Double expResult = 20.0;
        Double result = instance.getCapacidadeBateria();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEficiencia method, of class Drone.
     */
    @Test
    public void testGetEficiencia() {
        System.out.println("getEficiencia");
        Drone instance = new Drone();
        instance.setEficiencia(20.0);
        Double expResult = 20.0;
        Double result = instance.getEficiencia();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Drone.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = 1;
        Drone instance = new Drone();
        instance.setId(id);
        assertTrue(instance.getId() == 1, "O id deve ser 1");
    }

    /**
     * Test of setIdFarmacia method, of class Drone.
     */
    @Test
    public void testSetIdFarmacia() {
        System.out.println("setIdFarmacia");
        Integer idFarmacia = 1;
        Drone instance = new Drone();
        instance.setIdFarmacia(idFarmacia);
        assertTrue(instance.getIdFarmacia() == 1, "O id da farmacia deve ser 1");
    }

    /**
     * Test of setNrRegisto method, of class Drone.
     */
    @Test
    public void testSetNrRegisto() {
        System.out.println("setNrRegisto");
        Integer nrRegisto = 1;
        Drone instance = new Drone();
        instance.setNrRegisto(nrRegisto);
        assertTrue(instance.getNrRegisto() == 1, "O número de registo deve ser 1");
    }

    /**
     * Test of setDesignacaoModelo method, of class Drone.
     */
    @Test
    public void testSetDesignacaoModelo() {
        System.out.println("setDesignacaoModelo");
        String designacaoModelo = "teste";
        Drone instance = new Drone();
        instance.setDesignacaoModelo(designacaoModelo);
        assertTrue(instance.getDesignacaoModelo().equals("teste"), "A designacao do modelo deve ser 'teste'");
    }

    /**
     * Test of setIdEstado method, of class Drone.
     */
    @Test
    public void testSetIdEstado() {
        System.out.println("setIdEstado");
        Integer idEstado = 1;
        Drone instance = new Drone();
        instance.setIdEstado(idEstado);
        assertTrue(instance.getIdEstado() == 1, "O id do estacionamento deve ser 1");
    }

    /**
     * Test of setCargaBateriaAtual method, of class Drone.
     */
    @Test
    public void testSetCargaBateriaAtual() {
        System.out.println("setCargaBateriaAtual");
        Double cargaBateriaAtual = 100.0;
        Drone instance = new Drone();
        instance.setCargaBateriaAtual(cargaBateriaAtual);
        assertTrue(instance.getCargaBateriaAtual() == 100.0, "A carga de bateria atual deve ser 100.0");
    }

    /**
     * Test of setCapacidadeBateria method, of class Drone.
     */
    @Test
    public void testSetCapacidadeBateria() {
        System.out.println("setCapacidadeBateria");
        Double capacidadeBateria = 20.0;
        Drone instance = new Drone();
        instance.setCapacidadeBateria(capacidadeBateria);
        assertTrue(instance.getCapacidadeBateria() == 20.0, "A capacidade da bateria deve ser 20.0");
    }

    /**
     * Test of setEficiencia method, of class Drone.
     */
    @Test
    public void testSetEficiencia() {
        System.out.println("setEficiencia");
        Double eficiencia = 4.0;
        Drone instance = new Drone();
        instance.setEficiencia(eficiencia);
        assertTrue(instance.getEficiencia() == 4.0, "A eficiente deve ser 4.0");
    }

    /**
     * Test of toString method, of class Drone.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Drone instance = new Drone();
        String expResult = "Drone{id=null, idFarmacia=null, nrRegisto=null, designacaoModelo=null, idTipoBateria=null, capacidadeBateria=null, cargaBateriaAtual=null,"
                + " idEstado=null, eficiencia=null}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdTipoBateria method, of class Drone.
     */
    @Test
    public void testGetIdTipoBateria() {
        System.out.println("getIdTipoBateria");
        Drone instance = new Drone();
        instance.setIdTipoBateria(1);
        Integer expResult = 1;
        Integer result = instance.getIdTipoBateria();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdTipoBateria method, of class Drone.
     */
    @Test
    public void testSetIdTipoBateria() {
        System.out.println("setIdTipoBateria");
        Integer idTipoBateria = 1;
        Drone instance = new Drone();
        instance.setIdTipoBateria(idTipoBateria);
        assertTrue(instance.getIdTipoBateria() == 1, "O id tipo bateria deve ser 4.0");
    }

}
