/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lapr.project.model.Artigo;
import lapr.project.model.Encomenda;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 *
 * @author rmarinho
 */
public class CompraControllerTest {

    public CompraControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getListaProdutosFarmacia method, of class CompraController.
     */
//    @Test
//    public void testGetListaProdutosFarmacia() {
//        CompraController ctrl = Mockito.spy(new CompraController());
//
//        BaseController baseController;
//        baseController = Mockito.mock(BaseController.class);
//        //simular classe Basecontroller
//
//        ctrl.baseController = (baseController);
//
//        //BAse controller devolve
//        List<Artigo> artigos = new ArrayList<>();
//        artigos.add(new Artigo(123, "designacao", 3, 1, 2));
//
//        int i = 0;
//        when(baseController.getAllArtigos(i)).thenReturn(artigos);
//        List<Artigo> test = ctrl.getListaProdutosFarmacia(i, 2134);
//
//        assertEquals(test.size(), artigos.size());
//
//    }

//    /**
//     * Test of getListaProdutosFarmacia method, of class CompraController.
//     */
//    @Test
//    public void testGetListaProdutosFarmaciaEmpty() {
//        CompraController ctrl = Mockito.spy(new CompraController());
//
//        BaseController baseController;
//        baseController = Mockito.mock(BaseController.class);
//        //simular classe Basecontroller
//
//        ctrl.baseController = (baseController);
//
//        //BAse controller devolve
//        List<Artigo> artigos = new ArrayList<>();
//        //artigos.add(new Artigo(123, "designacao", 3, 1, 2));
//
//        int i = 0;
//        when(baseController.getAllArtigos(i)).thenReturn(artigos);
//        List<Artigo> test = ctrl.getListaProdutosFarmacia(i, 2134);
//
//        assertEquals(test.size(), artigos.size());
//
//    }

    /**
     * Test of getListaArtigosCompra method, of class CompraController.
     */
    @Test
    public void testGetListaArtigosCompra() {
        System.out.println("getListaArtigosCompra");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        //List<Artigo> listaArtigos = baseController.getAllArtigos(farmacia);

        //Data 
        List<Artigo> listaArtigos = new ArrayList<>();
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 1);
        Artigo a2 = new Artigo(2, "art 2", 1D, 5D, 1.5);
        Artigo a3 = new Artigo(2, "art 2", 1D, 5D, 1.5);
        Encomenda e = new Encomenda(10, "09-12-1994", 1234, 2, 10D, 11D, 15, 0.3);
        e.adicionarArtigo(a1, 1);
        e.adicionarArtigo(a2, 2);
        e.adicionarArtigo(a3, 2);
        ctrl.encomenda = e;

        Map<Artigo, Integer> expectedMap = new HashMap<>();
        expectedMap.put(a1, 1);
        expectedMap.put(a2, 2);
        expectedMap.put(a3, 2);
        Map<Artigo, Integer> resultMap = ctrl.getListaArtigosCompra();
        assertEquals(resultMap.size(), expectedMap.size());
        assertEquals(resultMap.containsKey(a1), true);
        assertEquals(resultMap.containsKey(a2), true);
        assertEquals(resultMap.containsKey(a3), true);

    }

    /**
     * Test of adicionarArtigoCarrinho method, of class CompraController.
     */
    @Test
    public void testAdicionarArtigoCarrinho() {
        System.out.println("adicionarArtigoCarrinho");
        System.out.println("getListaArtigosCompra");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        int idArtigo = 1;
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 4);
        when(b.getStockArtigo(1, idArtigo)).thenReturn(1);
        when(b.getArtigoById(1, idArtigo)).thenReturn(a1);

        boolean expResult = true;
        boolean result = ctrl.adicionarArtigoCarrinho(idArtigo, 1);
        assertEquals(expResult, result);

    }

    /**
     * Test of adicionarArtigoCarrinho method, of class CompraController.
     */
    @Test
    public void testAdicionarArtigoCarrinhoSemStock() {
        System.out.println("adicionarArtigoCarrinho");
        System.out.println("getListaArtigosCompra");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        int idArtigo = 1;
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 4);
        when(b.getStockArtigo(1, idArtigo)).thenReturn(0);
        when(b.getArtigoById(1, idArtigo)).thenReturn(a1);

        boolean expResult = false;
        boolean result = ctrl.adicionarArtigoCarrinho(idArtigo, 1);
        assertEquals(expResult, result);

    }

    /**
     * Test of adicionarArtigoCarrinho method, of class CompraController.
     */
    @Test
    public void testAdicionarArtigoCarrinhoQuantidadeInvalida() {
        System.out.println("adicionarArtigoCarrinho");
        System.out.println("getListaArtigosCompra");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        int idArtigo = 1;
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 4);
        when(b.getStockArtigo(1, idArtigo)).thenReturn(0);
        when(b.getArtigoById(1, idArtigo)).thenReturn(a1);

        boolean expResult = false;
        boolean result = ctrl.adicionarArtigoCarrinho(idArtigo, 0);
        assertEquals(expResult, result);

    }

    /**
     * Test of adicionarArtigoCarrinho method, of class CompraController.
     */
    @Test
    public void testAdicionarArtigoCarrinhoIdArtigoInvalido() {
        System.out.println("adicionarArtigoCarrinho");
        System.out.println("getListaArtigosCompra");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        int idArtigo = 1;
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 5);
        when(b.getStockArtigo(1, idArtigo)).thenReturn(0);
        when(b.getArtigoById(1, idArtigo)).thenReturn(a1);

        boolean expResult = false;
        boolean result = ctrl.adicionarArtigoCarrinho(0, 1);
        assertEquals(expResult, result);

    }

    /**
     * Test of atualizarArtigoCarrinho method, of class CompraController.
     */
    @Test
    public void testAtualizarArtigoCarrinho() {
        System.out.println("adicionarArtigoCarrinho");
        System.out.println("getListaArtigosCompra");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        int idArtigo = 1;
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 4);
        when(b.getStockArtigo(1, idArtigo)).thenReturn(2);
        when(b.getArtigoById(1, idArtigo)).thenReturn(a1);
        ctrl.adicionarArtigoCarrinho(idArtigo, 1);
        boolean expResult = true;
        boolean result = ctrl.atualizarArtigoCarrinho(idArtigo, 2);
        assertEquals(expResult, result);
    }

    /**
     * Test of atualizarArtigoCarrinho method, of class CompraController.
     */
    @Test
    public void testAtualizarArtigoCarrinhoIdArtigoInvalido() {
        System.out.println("adicionarArtigoCarrinho");
        System.out.println("getListaArtigosCompra");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        int idArtigo = 1;
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 5);
        when(b.getStockArtigo(1, idArtigo)).thenReturn(2);
        when(b.getArtigoById(1, idArtigo)).thenReturn(a1);
        ctrl.adicionarArtigoCarrinho(idArtigo, 1);
        boolean expResult = false;
        boolean result = ctrl.atualizarArtigoCarrinho(0, 2);
        assertEquals(expResult, result);
    }

    /**
     * Test of atualizarArtigoCarrinho method, of class CompraController.
     */
    @Test
    public void testAtualizarArtigoCarrinhoIdInexistente() {
        System.out.println("adicionarArtigoCarrinho");
        System.out.println("getListaArtigosCompra");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        int idArtigo = 1;
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 5);
        when(b.getStockArtigo(1, idArtigo)).thenReturn(2);
        when(b.getArtigoById(1, idArtigo)).thenReturn(null);
        ctrl.adicionarArtigoCarrinho(idArtigo, 1);
        boolean expResult = false;
        boolean result = ctrl.atualizarArtigoCarrinho(idArtigo, 2);
        assertEquals(expResult, result);
    }

    @Test
    public void testAtualizarArtigoCarrinhoQuantidadeInvalida() {
        System.out.println("adicionarArtigoCarrinho");
        System.out.println("getListaArtigosCompra");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        int idArtigo = 1;
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 5);
        when(b.getStockArtigo(1, idArtigo)).thenReturn(2);
        when(b.getArtigoById(1, idArtigo)).thenReturn(a1);
        ctrl.adicionarArtigoCarrinho(idArtigo, 1);
        boolean expResult = false;
        boolean result = ctrl.atualizarArtigoCarrinho(idArtigo, 0);
        assertEquals(expResult, result);
    }

    @Test
    public void testAtualizarArtigoCarrinhoSemStock() {
        System.out.println("adicionarArtigoCarrinho");
        System.out.println("getListaArtigosCompra");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        int idArtigo = 1;
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 5);
        when(b.getStockArtigo(1, idArtigo)).thenReturn(2);
        when(b.getArtigoById(1, idArtigo)).thenReturn(a1);
        ctrl.adicionarArtigoCarrinho(idArtigo, 1);
        boolean expResult = false;
        boolean result = ctrl.atualizarArtigoCarrinho(idArtigo, 3);
        assertEquals(expResult, result);
    }

    /**
     * Test of removerArtigoCarrinho method, of class CompraController.
     */
    @Test
    public void testRemoverArtigoCarrinho() {
        System.out.println("removerArtigoCarrinho");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        int idArtigo = 1;
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 4);
        when(b.getStockArtigo(1, idArtigo)).thenReturn(1);
        when(b.getArtigoById(1, idArtigo)).thenReturn(a1);
        ctrl.adicionarArtigoCarrinho(idArtigo, 1);
        boolean expResult = true;
        boolean result = ctrl.removerArtigoCarrinho(idArtigo);
        assertEquals(expResult, result);
    }

    /**
     * Test of removerArtigoCarrinho method, of class CompraController.
     */
    @Test
    public void testRemoverArtigoCarrinhoArtigoInxeistenteNoCarrinho() {
        System.out.println("removerArtigoCarrinho");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        int idArtigo = 1;
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 5);
        when(b.getStockArtigo(1, idArtigo)).thenReturn(1);
        when(b.getArtigoById(1, idArtigo)).thenReturn(a1);
        ctrl.adicionarArtigoCarrinho(idArtigo, 1);
        boolean expResult = false;
        boolean result = ctrl.removerArtigoCarrinho(2);
        assertEquals(expResult, result);
    }

    /**
     * Test of removerArtigoCarrinho method, of class CompraController.
     */
    @Test
    public void testRemoverArtigoCarrinhoArtigoInxeistente() {
        System.out.println("removerArtigoCarrinho");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        int idArtigo = 1;
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 5);
        when(b.getStockArtigo(1, idArtigo)).thenReturn(1);
        when(b.getArtigoById(1, idArtigo)).thenReturn(null);
        ctrl.adicionarArtigoCarrinho(idArtigo, 1);
        boolean expResult = false;
        boolean result = ctrl.removerArtigoCarrinho(2);
        assertEquals(expResult, result);
    }

    /**
     * Test of finalizarEncomenda method, of class CompraController.
     */
    @Test
    public void testFinalizarEncomenda() {
        System.out.println("finalizarEncomenda");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        FaturaController f;
        f = Mockito.mock(FaturaController.class);
        ctrl.faturaController = (f);
        ctrl.baseController = (b);
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 5);
        Artigo a2 = new Artigo(2, "art 2", 1D, 5D, 5);
        Artigo a3 = new Artigo(2, "art 2", 1D, 5D, 5);
        Encomenda e = new Encomenda(10, "09-12-1994", 1234, 2, 10D, 11D, 15, 10.0);
        e.adicionarArtigo(a1, 1);
        e.adicionarArtigo(a2, 2);
        e.adicionarArtigo(a3, 2);
        ctrl.encomenda = e;
        Integer nif = e.getNifCliente();
        when(b.criarEncomenda(e)).thenReturn(10);
        when(f.gerarFaturaEncomenda(e)).thenReturn(true);

        boolean expResult = true;
        boolean result = ctrl.finalizarEncomenda(nif);
        assertEquals(expResult, result);

    }
    @Test
    public void testFinalizarEncomendaIdInvalido() {
        System.out.println("finalizarEncomenda");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        FaturaController f;
        f = Mockito.mock(FaturaController.class);
        ctrl.faturaController = (f);
        ctrl.baseController = (b);
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 5);
        Artigo a2 = new Artigo(2, "art 2", 1D, 5D, 5);
        Artigo a3 = new Artigo(2, "art 2", 1D, 5D, 5);
        Encomenda e = new Encomenda(10, "09-12-1994", 1234, 2, 10D, 11D, 15, 10.0);
        e.adicionarArtigo(a1, 1);
        e.adicionarArtigo(a2, 2);
        e.adicionarArtigo(a3, 2);
        ctrl.encomenda = e;
        Integer nif = e.getNifCliente();
        when(b.criarEncomenda(e)).thenReturn(null);
        when(f.gerarFaturaEncomenda(e)).thenReturn(true);

        boolean expResult = false;
        boolean result = ctrl.finalizarEncomenda(nif);
        assertEquals(expResult, result);

    }

    
        @Test
    public void testFinalizarEncomendaIdInexistente() {
        System.out.println("finalizarEncomenda");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        FaturaController f;
        f = Mockito.mock(FaturaController.class);
        ctrl.faturaController = (f);
        ctrl.baseController = (b);
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 5);
        Artigo a2 = new Artigo(2, "art 2", 1D, 5D, 5);
        Artigo a3 = new Artigo(2, "art 2", 1D, 5D, 5);
        Encomenda e = new Encomenda(10, "09-12-1994", 1234, 2, 10D, 11D, 15, 10.0);
        e.adicionarArtigo(a1, 1);
        e.adicionarArtigo(a2, 2);
        e.adicionarArtigo(a3, 2);
        ctrl.encomenda = e;
        Integer nif = e.getNifCliente();
        when(b.criarEncomenda(e)).thenReturn(0);
        when(f.gerarFaturaEncomenda(e)).thenReturn(true);

        boolean expResult = false;
        boolean result = ctrl.finalizarEncomenda(nif);
        assertEquals(expResult, result);

    }
    
        @Test
    public void testFinalizarEncomendaFaturaNaoGerada() {
        System.out.println("finalizarEncomenda");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        FaturaController f;
        f = Mockito.mock(FaturaController.class);
        ctrl.faturaController = (f);
        ctrl.baseController = (b);
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 5);
        Artigo a2 = new Artigo(2, "art 2", 1D, 5D, 5);
        Artigo a3 = new Artigo(2, "art 2", 1D, 5D, 5);
        Encomenda e = new Encomenda(10, "09-12-1994", 1234, 2, 10D, 11D, 15, 10.0);
        e.adicionarArtigo(a1, 1);
        e.adicionarArtigo(a2, 2);
        e.adicionarArtigo(a3, 2);
        ctrl.encomenda = e;
        Integer nif = e.getNifCliente();
        when(b.criarEncomenda(e)).thenReturn(10);
        when(f.gerarFaturaEncomenda(e)).thenReturn(false);

        boolean expResult = false;
        boolean result = ctrl.finalizarEncomenda(nif);
        assertEquals(expResult, result);

    }
    /**
     * Test of getEncomenda method, of class CompraController.
     */
    @Test
    public void testGetEncomenda() {
        System.out.println("getEncomenda");
        CompraController ctrl = Mockito.spy(new CompraController());
        BaseController b;
        b = Mockito.mock(BaseController.class);
        ctrl.baseController = (b);
        //List<Artigo> listaArtigos = baseController.getAllArtigos(farmacia);

        //Data 
        List<Artigo> listaArtigos = new ArrayList<>();
        Artigo a1 = new Artigo(1, "art 1", 1D, 5D, 5);
        Artigo a2 = new Artigo(2, "art 2", 1D, 5D, 5);
        Artigo a3 = new Artigo(2, "art 2", 1D, 5D, 5);
        Encomenda e = new Encomenda(10, "09-12-1994", 1234, 2, 10D, 11D, 15, 10.0);
        e.adicionarArtigo(a1, 1);
        e.adicionarArtigo(a2, 2);
        e.adicionarArtigo(a3, 2);
        ctrl.encomenda = e;
        
       assertEquals(ctrl.getEncomenda(),e);
    }

}
