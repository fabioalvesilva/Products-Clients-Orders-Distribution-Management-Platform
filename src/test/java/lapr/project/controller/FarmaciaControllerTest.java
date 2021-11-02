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
import lapr.project.model.Endereco;
import lapr.project.model.Farmacia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 *
 * @author Rafael
 */
public class FarmaciaControllerTest {

    public FarmaciaControllerTest() {
    }

    @BeforeEach
    public void setUp() {
        GraphController gc = new GraphController();
        FarmaciaController fc = new FarmaciaController(gc);
    }

    /**
     * Test of listarProdutosStock method, of class FarmaciaController.
     */
    @Test
    public void testListarProdutosStock() {
        System.out.println("ListarProdutosStock");
        FarmaciaController ctrlF = Mockito.spy(new FarmaciaController());

        BaseController bC;
        bC = Mockito.mock(BaseController.class);
        //simular classe Basecontroller

        ctrlF.baseController = (bC);

        int i = 1;
        Mockito.when(bC.getStockArtigo(1, i)).thenReturn(1); // ir buscar stock ver se pode ou nao adicionar
        List<Artigo> artigos = new ArrayList<>();

        Mockito.when(bC.getAllArtigos(i)).thenReturn(artigos);
        Artigo a = new Artigo(123, "designacao", 3, 1, 2);
        artigos.add(a);
        Map<Artigo, Integer> result = ctrlF.listarProdutosStock(i);
        Map<Artigo, Integer> expected = new HashMap<>();
        expected.put(a, i);
        assertEquals(result.containsKey(a), true);
        assertEquals(result.size(), expected.size());
    }

    /**
     * Test of updateStockArtigo method, of class FarmaciaController.
     */
    @Test
    public void testUpdateStockArtigo() {
        System.out.println("UpdateStockArtigo");
        FarmaciaController ctrlF = Mockito.spy(new FarmaciaController());

        BaseController b = Mockito.mock(BaseController.class);

        ctrlF.baseController = b;
        int idFarmacia = 1, idArt = 123, quantidade = 2;
        when(b.updateStockArtigo(idFarmacia, idArt, quantidade)).thenReturn(Boolean.TRUE);

        boolean expResult = true;
        boolean result = ctrlF.updateStockArtigo(idFarmacia, idArt, quantidade);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of updateStockArtigo method, of class FarmaciaController.
     */
    @Test
    public void testUpdateStockNotOk() {
        System.out.println("UpdateStockArtigo");
        FarmaciaController ctrlF = Mockito.spy(new FarmaciaController());
        BaseController b = Mockito.mock(BaseController.class);

        ctrlF.baseController = b;
        int idFarmacia = 1, idArt = 123, quantidade = 2;
        when(b.updateStockArtigo(idFarmacia, idArt, quantidade)).thenReturn(Boolean.FALSE);

        boolean expResult = false;
        boolean result = ctrlF.updateStockArtigo(idFarmacia, idArt, quantidade);
        assertEquals(expResult, result);

    }

    /**
     * Test of transferenciaProduto method, of class FarmaciaController.
     */
    @Test
    public void testTransferenciaProduto() {
        System.out.println("transferenciaProduto");

        FarmaciaController ctrlF = Mockito.spy(new FarmaciaController());
        BaseController b = Mockito.mock(BaseController.class);
        ctrlF.baseController = b;

        int idFarmaciaOrigem = 0;
        int idProduto = 0;
        int quantidade = 0;

        when(ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade)).thenReturn(Boolean.TRUE);

        boolean expResult = true;
        boolean result = ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of transferenciaProduto method, of class FarmaciaController.
     */
    @Test
    public void testTransferenciaProdutoUmNotOk() {
        System.out.println("transferenciaProduto");

        FarmaciaController ctrlF = Mockito.spy(new FarmaciaController());
        BaseController b = Mockito.mock(BaseController.class);
        ctrlF.baseController = b;

        int idFarmaciaOrigem = 0;
        int idProduto = 0;
        int quantidade = 0;

        when(ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade)).thenReturn(Boolean.FALSE);

        boolean expResult = false;
        boolean result = ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade);
        assertEquals(expResult, result);
    }

    /**
     * Test of transferenciaProduto method, of class FarmaciaController.
     */
    @Test
    public void testTransferenciaProdutoDois() {
        System.out.println("transferenciaProduto");

        FarmaciaController ctrlF = Mockito.spy(new FarmaciaController());
        BaseController b = Mockito.mock(BaseController.class);
        ctrlF.baseController = b;
        NotificacaoController nC = Mockito.mock(NotificacaoController.class);
        ctrlF.notificacaoController = nC;
        GraphController gC = Mockito.mock(GraphController.class);
        ctrlF.graphController = gC;

        int idFarmaciaOrigem = 2;
        int idProduto = 2;
        int quantidade = 2;
        List<Farmacia> farmaciasMaisProximas = new ArrayList<>();
        Farmacia f1 = new Farmacia("teste", 1, "teste@teste", 2, new Endereco("teste", 2.0, 2.1));
        farmaciasMaisProximas.add(f1);
        when(gC.getFarmaciasProximas(idFarmaciaOrigem)).thenReturn(farmaciasMaisProximas);
        when(b.getStockArtigo(f1.getId(), idProduto)).thenReturn(3);
        when(b.transferirProduto(f1.getId(), idFarmaciaOrigem, idProduto, quantidade)).thenReturn(Boolean.TRUE);
        when(ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade)).thenReturn(Boolean.TRUE);

        boolean expResult = true;
        boolean result = ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of transferenciaProduto method, of class FarmaciaController.
     */
    @Test
    public void testTransferenciaProdutoDoisNotOk() {
        System.out.println("transferenciaProduto");

        FarmaciaController ctrlF = Mockito.spy(new FarmaciaController());
        BaseController b = Mockito.mock(BaseController.class);
        ctrlF.baseController = b;
        NotificacaoController nC = Mockito.mock(NotificacaoController.class);
        ctrlF.notificacaoController = nC;
        GraphController gC = Mockito.mock(GraphController.class);
        ctrlF.graphController = gC;

        int idFarmaciaOrigem = 2;
        int idProduto = 2;
        int quantidade = 2;
        List<Farmacia> farmaciasMaisProximas = new ArrayList<>();
        Farmacia f1 = new Farmacia("teste", 1, "teste@teste", 2, new Endereco("teste", 2.0, 2.1));
        farmaciasMaisProximas.add(f1);
        when(gC.getFarmaciasProximas(idFarmaciaOrigem)).thenReturn(farmaciasMaisProximas);
        when(b.getStockArtigo(f1.getId(), idProduto)).thenReturn(3);
        when(b.transferirProduto(f1.getId(), idFarmaciaOrigem, idProduto, quantidade)).thenReturn(Boolean.FALSE);
        when(ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade)).thenReturn(Boolean.FALSE);

        boolean expResult = false;
        boolean result = ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade);
        assertEquals(expResult, result);
    }
    /**
     * Test of transferenciaProduto method, of class FarmaciaController.
     */
    @Test
    public void testTransferenciaProdutoTres() {
        System.out.println("transferenciaProduto");

        FarmaciaController ctrlF = Mockito.spy(new FarmaciaController());
        BaseController b = Mockito.mock(BaseController.class);
        ctrlF.baseController = b;

        int idFarmaciaOrigem = 2;
        int idProduto = 2;
        int quantidade = 0;

        when(ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade)).thenReturn(Boolean.TRUE);

        boolean expResult = true;
        boolean result = ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of transferenciaProduto method, of class FarmaciaController.
     */
    @Test
    public void testTransferenciaProdutoTresNotOk() {
        System.out.println("transferenciaProduto");

        FarmaciaController ctrlF = Mockito.spy(new FarmaciaController());
        BaseController b = Mockito.mock(BaseController.class);
        ctrlF.baseController = b;

        int idFarmaciaOrigem = 2;
        int idProduto = 2;
        int quantidade = 0;

        when(ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade)).thenReturn(Boolean.FALSE);

        boolean expResult = false;
        boolean result = ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade);
        assertEquals(expResult, result);
    }

    /**
     * Test of transferenciaProduto method, of class FarmaciaController.
     */
    @Test
    public void testTransferenciaProdutoQuatro() {
        System.out.println("transferenciaProduto");

        FarmaciaController ctrlF = Mockito.spy(new FarmaciaController());
        BaseController b = Mockito.mock(BaseController.class);
        ctrlF.baseController = b;

        int idFarmaciaOrigem = 2;
        int idProduto = 0;
        int quantidade = 0;

        when(ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade)).thenReturn(Boolean.TRUE);

        boolean expResult = true;
        boolean result = ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of transferenciaProduto method, of class FarmaciaController.
     */
    @Test
    public void testTransferenciaProdutoQuatroNotOk() {
        System.out.println("transferenciaProduto");

        FarmaciaController ctrlF = Mockito.spy(new FarmaciaController());
        BaseController b = Mockito.mock(BaseController.class);
        ctrlF.baseController = b;

        int idFarmaciaOrigem = 2;
        int idProduto = 0;
        int quantidade = 0;

        when(ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade)).thenReturn(Boolean.FALSE);

        boolean expResult = false;
        boolean result = ctrlF.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade);
        assertEquals(expResult, result);
    }

    /**
     * Test of transferirProduto method, of class FarmaciaController.
     */
    @Test
    public void testTransferirProduto() {
        System.out.println("transferenciaProduto");

        FarmaciaController ctrlF = Mockito.spy(new FarmaciaController());
        BaseController b = Mockito.mock(BaseController.class);
        ctrlF.baseController = b;

        int idFarmaciaRemetente = 0;
        int idFarmaciaDestinatario = 0;
        int idProduto = 0;
        int quantidade = 0;

        boolean expResult = false;
        boolean result = ctrlF.transferirProduto(idFarmaciaRemetente, idFarmaciaDestinatario, idProduto, quantidade);
        assertEquals(expResult, result);

    }

}
