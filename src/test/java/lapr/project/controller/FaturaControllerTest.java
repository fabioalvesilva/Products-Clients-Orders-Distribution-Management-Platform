/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Artigo;
import lapr.project.model.Encomenda;
import lapr.project.model.Fatura;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

/**
 *
 * @author Fábio Silva
 */
public class FaturaControllerTest {
    
    private Encomenda encomenda;
    private Artigo artigo;
    private Fatura f;
    
    public FaturaControllerTest() {
        
        encomenda = new Encomenda(55, "16-12-2020", 123456789, 3, 100.0, -100.0, 10.0, 5);
        artigo = new Artigo(1,"Alglucosidase alfa", 2.59, 6, 94.00);
        f = new  Fatura(1, 12345, "cliente@cliente.pt", 2D);
    }

    /**
     * Test of gerarFaturaEncomenda method, of class FaturaController.
     */
    @Test
    public void testGerarFaturaEncomendaTrue() {
        System.out.println("gerarFaturaEncomenda True");
        
        FaturaController instance = Mockito.spy(new FaturaController());
        BaseController b = Mockito.mock(BaseController.class);
        instance.baseController = b;
        
        when(b.getEmailClienteByEncomenda(encomenda)).thenReturn("cliente@cliente.pt");
        
        NotificacaoController n = Mockito.mock(NotificacaoController.class);
        instance.notificacaoController = n;
        when(n.notificacaoFaturaCliente("cliente@cliente.pt", f)).thenReturn(Boolean.TRUE);
        
        Encomenda e = encomenda;
        encomenda.adicionarArtigo(artigo, 1);
        when(instance.gerarFaturaEncomenda(encomenda)).thenReturn(Boolean.TRUE);
        
        boolean expResult = true;
        boolean result = instance.gerarFaturaEncomenda(e);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of gerarFaturaEncomenda method, of class FaturaController.
     */
    @Test
    public void testGerarFaturaEncomendaFalse() {
        System.out.println("gerarFaturaEncomenda False");
        
        FaturaController instance = Mockito.spy(new FaturaController());
        BaseController b = Mockito.mock(BaseController.class);
        instance.baseController = b;
        
        when(b.getEmailClienteByEncomenda(encomenda)).thenReturn("cliente@cliente.pt");
        
        NotificacaoController n = Mockito.mock(NotificacaoController.class);
        instance.notificacaoController = n;
        when(n.notificacaoFaturaCliente("cliente@cliente.pt", f)).thenReturn(Boolean.FALSE);
        
        Encomenda e = encomenda;
        encomenda.adicionarArtigo(artigo, 1);
        when(instance.gerarFaturaEncomenda(encomenda)).thenReturn(Boolean.FALSE);
        
        boolean expResult = false;
        boolean result = instance.gerarFaturaEncomenda(e);
        assertEquals(expResult, result);
    }
    
    
    
    /**
     * Test of gerarFaturaEncomenda method, of class FaturaController.
     */
    @Test
    public void testGerarFaturaEncomendaEnviarNotificacaoTrue() {
        System.out.println("gerarFaturaEncomenda Enviar Notificação - True");
        
        String email = "cliente@cliente.pt";
        FaturaController instance = Mockito.spy(new FaturaController());
        
        NotificacaoController n = Mockito.mock(NotificacaoController.class);
        instance.notificacaoController = n;
        when(n.notificacaoFaturaCliente(email, f)).thenReturn(Boolean.TRUE);
        
        boolean expResult = true;
        boolean result = n.notificacaoFaturaCliente(email, f);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of gerarFaturaEncomenda method, of class FaturaController.
     */
    @Test
    public void testGerarFaturaEncomendaEnviarNotificacaoFalse() {
        System.out.println("gerarFaturaEncomenda Enviar Notificação - False");
        
        String email = "cliente@cliente.pt";
        FaturaController instance = Mockito.spy(new FaturaController());
        
        NotificacaoController n = Mockito.mock(NotificacaoController.class);
        instance.notificacaoController = n;
        when(n.notificacaoFaturaCliente(email, f)).thenReturn(Boolean.FALSE);
        
        boolean expResult = false;
        boolean result = n.notificacaoFaturaCliente(email, f);
        assertEquals(expResult, result);
    }
    /*
         * Test of gerarFaturaEncomenda method, of class FaturaController.
     */
    @Test
    public void testGerarFaturaEncomendaMutation() {
        System.out.println("gerarFaturaEncomenda True");
        
        FaturaController instance = Mockito.spy(new FaturaController());
        BaseController b = Mockito.mock(BaseController.class);
        instance.baseController = b;
        Fatura  f1 = new  Fatura(1, 12345, "cliente@cliente.pt", 2D);
        when(b.getEmailClienteByEncomenda(encomenda)).thenReturn("cliente@cliente.pt");
        
        NotificacaoController n = Mockito.mock(NotificacaoController.class);
        instance.notificacaoController = n;
        when(n.notificacaoFaturaCliente("cliente@cliente.pt", f)).thenReturn(Boolean.TRUE);
        
        Encomenda e = null;
        encomenda.adicionarArtigo(artigo, 1);
        when(instance.gerarFaturaEncomenda(encomenda)).thenReturn(false);
        
        boolean expResult = false;
        boolean result = instance.gerarFaturaEncomenda(e);
        assertEquals(expResult, result);
    }
    
        @Test
    public void testGerarFaturaEncomendaMutation2() {
        System.out.println("gerarFaturaEncomenda True");
        
        FaturaController instance = Mockito.spy(new FaturaController());
        BaseController b = Mockito.mock(BaseController.class);
        instance.baseController = b;
        Fatura  f1 = new  Fatura(1, 12345, "cliente@cliente.pt", 2D);
        when(b.getEmailClienteByEncomenda(encomenda)).thenReturn("cliente@cliente.pt");
        
        NotificacaoController n = Mockito.mock(NotificacaoController.class);
        instance.notificacaoController = n;
        when(n.notificacaoFaturaCliente("cliente@cliente.pt", f)).thenReturn(Boolean.TRUE);
        encomenda = new Encomenda(0, "16-12-2020", 123456789, 3, 100.0, -100.0, 10.0, 5);
        
        encomenda.adicionarArtigo(artigo, 1);
        when(instance.gerarFaturaEncomenda(encomenda)).thenReturn(false);
        
        boolean expResult = false;
        boolean result = instance.gerarFaturaEncomenda(encomenda);
        assertEquals(expResult, result);
    }
    
            @Test
    public void testGerarFaturaEncomendaMutation3() {
        System.out.println("gerarFaturaEncomenda True");
        
        FaturaController instance = Mockito.spy(new FaturaController());
        BaseController b = Mockito.mock(BaseController.class);
        instance.baseController = b;
        Fatura  f1 = new  Fatura(1, 12345, "cliente@cliente.pt", 2D);
        when(b.getEmailClienteByEncomenda(encomenda)).thenReturn("cliente@cliente.pt");
        
        NotificacaoController n = Mockito.mock(NotificacaoController.class);
        instance.notificacaoController = n;
        when(n.notificacaoFaturaCliente("cliente@cliente.pt", f)).thenReturn(Boolean.TRUE);
        encomenda = new Encomenda(2, "16-12-2020", 0, 3, 100.0, -100.0, 10.0, 5);
        
        encomenda.adicionarArtigo(artigo, 1);
        when(instance.gerarFaturaEncomenda(encomenda)).thenReturn(false);
        
        boolean expResult = false;
        boolean result = instance.gerarFaturaEncomenda(encomenda);
        assertEquals(expResult, result);
    }
    
}
