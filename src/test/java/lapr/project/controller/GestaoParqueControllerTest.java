/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.data.ParqueAPI;
import lapr.project.model.Endereco;
import lapr.project.model.Parque;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author Fábio Silva
 */
public class GestaoParqueControllerTest {

    private Parque parque;
    private Endereco endereco;

    public GestaoParqueControllerTest() {

        endereco = new Endereco("Rua 2", 1100.0, -300.0, 10.0);
        parque = new Parque(2, 5, 5, 10, 10, 15, endereco);
    }

    /**
     * Test of getAllParques method, of class GestaoParqueController.
     */
    @Test
    public void testGetAllParques() {
        System.out.println("getAllParques");
        BaseController b;
        ParqueAPI api = Mockito.mock(ParqueAPI.class);

        GestaoParqueController ctrl = Mockito.spy(new GestaoParqueController());
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = b;
        List<Parque> lista = new ArrayList<>();
        lista.add(parque);
        when(b.getAllParques()).thenReturn(lista);

        List<Parque> result = ctrl.getAllParques();
        assertArrayEquals(lista.toArray(), result.toArray());
    }

    /**
     * Test of getAllParques method, of class GestaoParqueController.
     */
    @Test
    public void testGetAllParquesInexistentes() {
        System.out.println("getAllParques");
        BaseController b;
        ParqueAPI api = Mockito.mock(ParqueAPI.class);

        GestaoParqueController ctrl = Mockito.spy(new GestaoParqueController());
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = b;
        List<Parque> lista = new ArrayList<>();
        //lista.add(parque);
        when(b.getAllParques()).thenReturn(lista);

        List<Parque> result = ctrl.getAllParques();
        assertEquals(lista.size(), result.size());
    }

    /**
     * Test of updateCapacidadeParque method, of class GestaoParqueController.
     */
    @Test
    public void testUpdateCapacidadeParqueTrue() {
        System.out.println("updateCapacidadeParque True");
        Integer idParque = 1;
        int nrLugaresNormal = 10;
        int nrLugaresEletricos = 10;
        BaseController b;
        b = Mockito.mock(BaseController.class);
        GestaoParqueController ctrl = Mockito.spy(new GestaoParqueController());
        ctrl.baseController = b;
        //BaseController instance = Mockito.spy(BaseController.class);
        //ParqueAPI api = Mockito.mock(ParqueAPI.class);
        when(b.updateCapacidadeParque(idParque, nrLugaresNormal, nrLugaresEletricos)).thenReturn(true);
        //instance.parqueAPI = api;
        //when(api.updateCapacidadeParque(idParque, nrLugaresNormal, nrLugaresEletricos)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = ctrl.updateCapacidadeParque(idParque, nrLugaresNormal, nrLugaresEletricos);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateCapacidadeParque method, of class GestaoParqueController.
     */
    @Test
    public void testUpdateCapacidadeParqueFalse() {
        System.out.println("updateCapacidadeParque False");
        Integer idParque = 0;
        int nrLugaresNormal = 10;
        int nrLugaresEletricos = 10;
        BaseController b;
        b = Mockito.mock(BaseController.class);
        GestaoParqueController ctrl = Mockito.spy(new GestaoParqueController());
        ctrl.baseController = b;
        //BaseController instance = Mockito.spy(BaseController.class);
        //ParqueAPI api = Mockito.mock(ParqueAPI.class);
        when(b.updateCapacidadeParque(idParque, nrLugaresNormal, nrLugaresEletricos)).thenReturn(false);
        //instance.parqueAPI = api;
        //when(api.updateCapacidadeParque(idParque, nrLugaresNormal, nrLugaresEletricos)).thenReturn(Boolean.TRUE);
        boolean expResult = false;
        boolean result = ctrl.updateCapacidadeParque(idParque, nrLugaresNormal, nrLugaresEletricos);
        assertEquals(expResult, result);
    }

    /**
     * Test of CriarParque method, of class GestaoParqueController.
     */
    @Test
    public void testCriarParque() {
        System.out.println("CriarParque");
        ParqueAPI api = Mockito.mock(ParqueAPI.class);

        int idFarmacia = 1;
        int nrLugaresNormal = 10;
        int nrLugaresEletricos = 10;
        BaseController b;
        b = Mockito.mock(BaseController.class);
        GestaoParqueController ctrl = Mockito.spy(new GestaoParqueController());
        ctrl.baseController = b;
        when(api.updateCapacidadeParque(idFarmacia, nrLugaresNormal, nrLugaresEletricos)).thenReturn(Boolean.TRUE);

        boolean expResult = false;
        boolean result = ctrl.criarParque(idFarmacia, parque);
        assertEquals(expResult, result);
    }

    /**
     * Test of CriarParque method, of class GestaoParqueController.
     */
    @Test
    public void testCriarParqueFalse() {
        System.out.println("CriarParque False");
        Integer idFarmacia = -1;
        BaseController b;
        b = Mockito.mock(BaseController.class);
        GestaoParqueController ctrl = Mockito.spy(new GestaoParqueController());
        ctrl.baseController = b;
        boolean expResult = false;
        boolean result = ctrl.criarParque(idFarmacia, parque);
        assertEquals(expResult, result);
    }

    /**
     * Test of CriarParque method, of class GestaoParqueController.
     */
    @Test
    public void testRemoverParque() {
        System.out.println("RemoverParque");
        ParqueAPI api = Mockito.mock(ParqueAPI.class);
        Integer idParque = 1;
        BaseController b;
        b = Mockito.mock(BaseController.class);
        GestaoParqueController ctrl = Mockito.spy(new GestaoParqueController());
        ctrl.baseController = b;
        when(api.removerParque(idParque)).thenReturn(Boolean.TRUE);
        boolean expResult = false;
        boolean result = ctrl.removerParque(idParque);
        assertEquals(expResult, result);
    }

    /**
     * Test of CriarParque method, of class GestaoParqueController.
     */
    @Test
    public void testRemoverParqueFalse() {
        System.out.println("RemoverParque False");
        ParqueAPI api = Mockito.mock(ParqueAPI.class);
        Integer idParque = -11;
        BaseController b;
        b = Mockito.mock(BaseController.class);
        GestaoParqueController ctrl = Mockito.spy(new GestaoParqueController());
        ctrl.baseController = b;
        when(api.removerParque(idParque)).thenReturn(Boolean.TRUE);
        boolean expResult = false;
        boolean result = ctrl.removerParque(idParque);
        assertEquals(expResult, result);
    }
    
      /**
     * Test of updateCapacidadeParque method, of class GestaoParqueController.
     */
    @Test
    public void testUpdateCapacidadeParque() {
        System.out.println("updateCapacidadeParque");
         System.out.println("RemoverParque False");
        ParqueAPI api = Mockito.mock(ParqueAPI.class);
        Integer idParque = -11;
        BaseController b;
        b = Mockito.mock(BaseController.class);
        GestaoParqueController ctrl = Mockito.spy(new GestaoParqueController());
        ctrl.baseController = b;
        
        Integer idParque1  =1;
                int nrLugaresNormal = 1;
                        int nrLugaresEletricos = 1;
        when(b.updateCapacidadeParque(idParque1, nrLugaresNormal, nrLugaresEletricos)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = ctrl.removerParque(idParque);
        assertEquals(expResult, result);
    }
}
