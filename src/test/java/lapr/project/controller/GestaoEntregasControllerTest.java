/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Drone;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 *
 * @author carlos
 */
public class GestaoEntregasControllerTest {

    public GestaoEntregasControllerTest() {

    }

    /**
     * Test of verificarOcupacaoEstafeta method, of class
     * GestaoEntregasController, when true
     */
    @Test
    public void testVerificarOcupacaoEstafetaTrue() {
        System.out.println("VerificarOcupacaoEstafeta");
        GestaoEntregasController ctrlGE = Mockito.spy(new GestaoEntregasController());
        BaseController bGE;
        bGE = Mockito.mock(BaseController.class);

        int idEstafeta = 1;
        ctrlGE.baseController = (bGE);
        Mockito.when(bGE.getOcupacaoEstafeta(idEstafeta)).thenReturn(true);

        boolean expResult = true;
        boolean result = ctrlGE.verificarOcupacaoEstafeta(idEstafeta);
        assertEquals(expResult, result);

    }

    /**
     * Test of verificarOcupacaoEstafeta method, of class
     * GestaoEntregasController, when false
     */
    @Test
    public void testVerificarOcupacaoEstafetaFalse() {
        System.out.println("VerificarOcupacaoEstafeta");
        GestaoEntregasController ctrlGE = Mockito.spy(new GestaoEntregasController());
        BaseController bGE;
        bGE = Mockito.mock(BaseController.class);

        int idEstafeta = 1;
        ctrlGE.baseController = (bGE);
        Mockito.when(bGE.getOcupacaoEstafeta(idEstafeta)).thenReturn(false);
        boolean expResult = false;
        boolean result = ctrlGE.verificarOcupacaoEstafeta(idEstafeta);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEntrega method, of class GestaoEntregasController.
     */
    @Test
    public void testGetEntrega() {
        System.out.println("getEntrega");

        GestaoEntregasController ctrlGE = Mockito.spy(new GestaoEntregasController());
        BaseController bGE;
        bGE = Mockito.mock(BaseController.class);
        ctrlGE.baseController = (bGE);
        Entrega e = new Entrega();
        e.setId(0);
        assertEquals(ctrlGE.getEntrega().getId(), e.getId());

    }

    /**
     * Test of setIdEstafetaEntrega method, of class GestaoEntregasController.
     * when idEstafeta!=null and IdEstafeta>0
     */
    @Test
    public void testSetIdEstafetaEntregaSuccess() {
        System.out.println("SetIdEstafetaEntregaSuccess");

        GestaoEntregasController ctrlGE = Mockito.spy(new GestaoEntregasController());
        BaseController bGE;
        bGE = Mockito.mock(BaseController.class);
        when(bGE.getEstafetaDisponivelParaEntrega(1)).thenReturn(2);
        ctrlGE.baseController = (bGE);
        Entrega e = new Entrega();
        e.setIdEstafeta(2);
        ctrlGE.entrega.setIdFarmacia(1);
        ctrlGE.setIdEstafetaEntrega();
        
        assertEquals(ctrlGE.getEntrega().getIdEstafeta(), e.getIdEstafeta());
    }

    /**
     * Test of getEncomendasPendentes method, of class GestaoEntregasController.
     */
    @Test
    public void testGetEncomendasPendentes() {
        System.out.println("GetEncomendasPendentes");
        GestaoEntregasController ctrlGE = Mockito.spy(new GestaoEntregasController());
        BaseController bGE;
        bGE = Mockito.mock(BaseController.class);
        ctrlGE.baseController = (bGE);

        List<Encomenda> encomendas = new ArrayList<>();
        //int id, String dataEncomenda, int nifCliente, String estado, double latitude, double longitude, double peso
        Encomenda encomenda1 = new Encomenda(1, "09-12-1984", 111000111, 2, 10.0, 12.0, 10.0, 40.0);
        Encomenda encomenda2 = new Encomenda(2, "09-12-1984", 111000113, 2, 10.0, 12.0, 10.0, 35.0);
        encomendas.add(encomenda1);
        encomendas.add(encomenda2);

        Mockito.when(bGE.getEncomendasPendentes(1)).thenReturn(encomendas);
        List<Encomenda> result = ctrlGE.getEncomendasPendentes();
        assertEquals(encomendas.size(), result.size());
        assertArrayEquals(encomendas.toArray(), result.toArray());
    }

    /**
     * Test of adicionarEncomendas method, of class GestaoEntregasController,
     * when method is successful.
     */
    @Test
    public void testAdicionarEncomendasSuccess() {
        System.out.println("AdicionarEncomendasSuccess");
        GestaoEntregasController ctrlGE = Mockito.spy(new GestaoEntregasController());
        BaseController bGE;
        bGE = Mockito.mock(BaseController.class);
        ctrlGE.baseController = (bGE);

        List<Encomenda> encomendas = new ArrayList<>();
        //int id, String dataEncomenda, int nifCliente, String estado, double latitude, double longitude, double peso
        Encomenda encomenda1 = new Encomenda(1, "09-12-1984", 111000111, 2, 10.0, 12.0, 10.0, 40.0);
        Encomenda encomenda2 = new Encomenda(2, "09-12-1984", 111000113, 2, 10.0, 12.0, 10.0, 35.0);
        encomendas.add(encomenda1);
        encomendas.add(encomenda2);

        Boolean result = ctrlGE.adicionarEncomendas(encomendas);
        assertEquals(result, true);

    }

    @Test
    public void testAdicionarEncomendasSuccessNull() {
        System.out.println("AdicionarEncomendasSuccess");
        GestaoEntregasController ctrlGE = Mockito.spy(new GestaoEntregasController());
        BaseController bGE;
        bGE = Mockito.mock(BaseController.class);
        ctrlGE.baseController = (bGE);

        List<Encomenda> encomendas = null;

        Boolean result = ctrlGE.adicionarEncomendas(encomendas);
        assertEquals(result, false);

    }

    @Test
    public void testAdicionarEncomendasSuccessEmpty() {
        System.out.println("AdicionarEncomendasSuccess");
        GestaoEntregasController ctrlGE = Mockito.spy(new GestaoEntregasController());
        BaseController bGE;
        bGE = Mockito.mock(BaseController.class);
        ctrlGE.baseController = (bGE);

        List<Encomenda> encomendas = new ArrayList<>();

        Boolean result = ctrlGE.adicionarEncomendas(encomendas);
        assertEquals(result, false);

    }

    /**
     * Test of setIdEstafetaEntrega method, of class GestaoEntregasController.
     */
    @Test
    public void testSetIdEstafetaEntrega() {
        System.out.println("setIdEstafetaEntrega");
        Integer idEstafeta = 1;
        GestaoEntregasController instance = new GestaoEntregasController();
        BaseController bGE;
        bGE = Mockito.mock(BaseController.class);
        when(bGE.getEstafetaDisponivelParaEntrega(1)).thenReturn(2);
        instance.baseController = (bGE);
        instance.entrega.setIdFarmacia(1);
        instance.setIdEstafetaEntrega();

        int id = instance.getEntrega().getIdEstafeta();
        assertTrue(id == 2);
 
        

        
    }

    /**
     * Test of getEncomendasPendentesByFarmacia method, of class
     * GestaoEntregasController.
     */
    @Test
    public void testGetEncomendasPendentesByFarmacia() {
        System.out.println("getEncomendasPendentesByFarmacia");

        GestaoEntregasController ctrlGE = Mockito.spy(new GestaoEntregasController());
        BaseController bGE;
        bGE = Mockito.mock(BaseController.class);
        ctrlGE.baseController = (bGE);

        Integer idFarmacia = 1;
        List<Encomenda> encomendas = new ArrayList<>();
        Encomenda encomenda1 = new Encomenda(1, "09-12-1984", 111000111, 2, 10.0, 12.0, 10.0, 40.0);
        encomendas.add(encomenda1);

        when(bGE.getEncomendasPendentes(idFarmacia)).thenReturn(encomendas);

        List<Encomenda> expResult = new ArrayList<>();
        expResult.add(encomenda1);

        List<Encomenda> result = new ArrayList<>();

        result = ctrlGE.getEncomendasPendentesByFarmacia(idFarmacia);

        assertEquals(expResult, result);
    }

    /**
     * Test of getEncomendasPendentesByFarmacia method, of class
     * GestaoEntregasController.
     */
    @Test
    public void testGetEncomendasPendentesByFarmaciaEmpty() {
        System.out.println("getEncomendasPendentesByFarmacia");

        GestaoEntregasController ctrlGE = Mockito.spy(new GestaoEntregasController());
        BaseController bGE;
        bGE = Mockito.mock(BaseController.class);
        ctrlGE.baseController = (bGE);

        Integer idFarmacia = 1;

        List<Encomenda> expResult = new ArrayList<>();
        List<Encomenda> result = new ArrayList<>();
        result = ctrlGE.getEncomendasPendentesByFarmacia(idFarmacia);

        assertEquals(true, result.isEmpty());
    }

    /**
     * Test of adicionarEncomendas method, of class GestaoEntregasController.
     */
    @Test
    public void testAdicionarEncomendas() {
        System.out.println("adicionarEncomendas");
        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda(1, "2012-12-13", 0, 3, 0, 0, 0, 0));
        listEncomendas.add(new Encomenda(1, "2012-12-13", 0, 3, 0, 0, 0, 0));

        GestaoEntregasController instance = new GestaoEntregasController();

        boolean expResult = true;
        boolean result = instance.adicionarEncomendas(listEncomendas);

        assertEquals(expResult, result);

    }

    /**
     * Test of verificarPesoEntrega method, of class GestaoEntregasController.
     */
    @Test
    public void testVerificarPesoEntrega() {
        System.out.println("verificarPesoEntrega");

        List<Encomenda> listEncomendas = new ArrayList<>();
        listEncomendas.add(new Encomenda(1, "2012-12-13", 0, 3, 0, 0, 0, 20));

        GestaoEntregasController instance = new GestaoEntregasController();
        boolean expResult = false;
        boolean result = instance.verificarPesoEntrega(listEncomendas);
        assertEquals(expResult, result);
    }

    /**
     * Test of notificarEntregas method, of class GestaoEntregasController.
     */
//    @Test
//    public void testNotificarEntregas() {
//        System.out.println("notificarEntregas");
//
//        Set<Encomenda> listEncomendas = new HashSet<>();
//        listEncomendas.add(new Encomenda(1, "2012-12-13", 0, "ok", 0, 0, 0, 0));
//        listEncomendas.add(new Encomenda(2, "2012-12-14", 1, "ok", 1, 1, 1, 1));
//
//        GestaoEntregasController ctrlGE = Mockito.spy(new GestaoEntregasController());
//        BaseController bGE;
//        bGE = Mockito.mock(BaseController.class);
//        ctrlGE.baseController = (bGE);
//        NotificacaoController nCtrl;
//        nCtrl = Mockito.mock(NotificacaoController.class);
//        ctrlGE.notificacaoController = (nCtrl);
//
//        when(bGE.(idFarmacia)).thenReturn(encomendas);
//
//        boolean result = ctrlGE.notificarEntregas(listEncomendas);
//
//        assertEquals(result, true);
//    }
    /**
     * Test of registarEntrega method, of class GestaoEntregasController.
     */
    @Test
    public void testRegistarEntrega() {

        System.out.println("testRegistarEntrega");

        BaseController bGE;
        bGE = Mockito.mock(BaseController.class);

        GestaoEntregasController ctrlGE = Mockito.spy(new GestaoEntregasController());

        ctrlGE.baseController = (bGE);

        Entrega e = new Entrega();
        e.setId(0);

        Scooter s1 = new Scooter(1, "123", "teste", 1, 1, 1, 1, 1, 2.0, 2.0);
        Drone d1 = new Drone(1, 1, 1, "teste", 1, 2.0, 2.0, 1, 2.0);
        Entrega e1 = new Entrega(0, d1, 1, 1, "2012-12-13", "2012-12-14", 2.0, 1);

        ctrlGE.entrega = e;
        when(bGE.registarEntrega(e)).thenReturn(e1);

        boolean result = ctrlGE.registarEntrega();
        assertEquals(false, result);

    }

    /**
     * Test of registarEntrega method, of class GestaoEntregasController.
     */
    @Test
    public void testRegistarEntregaFail() {

        BaseController b;
        b = Mockito.mock(BaseController.class);

        GestaoEntregasController ctrl = Mockito.spy(new GestaoEntregasController());

        //simular classe Basecontroller
        ctrl.baseController = (b);
        Entrega e = new Entrega();
        e.setId(0);
        Scooter s1 = new Scooter(1, "123", "teste", 1, 1, 1, 1, 1, 2.0, 2.0);
        Drone d1 = new Drone(1, 1, 1, "teste", 1, 2.0, 2.0, 1, 2.0);
        Entrega e1 = new Entrega(0, s1, 1, 1, "2012-12-13", "2012-12-14", 2.0, 1);
        ctrl.entrega = e;
        when(b.registarEntrega(e)).thenReturn(e1);

        boolean result = ctrl.registarEntrega();
        assertEquals(result, false);

    }

}
