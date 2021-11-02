/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;


import lapr.project.model.Encomenda;
import lapr.project.model.Fatura;
import lapr.project.utils.Constantes;
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
public class NotificacaoControllerTest {
    
    public NotificacaoControllerTest() {
    }

    /**
     * Test of notificacaoFaturaCliente method, of class NotificacaoController.
     */
    @Test
    public void testNotificacaoFaturaClienteNull() {
        System.out.println("NotificacaoFaturaClienteNull");
        NotificacaoController ctrl =  new NotificacaoController();
                
        String emailPara = "test@test.com";

        Fatura f = null;
        boolean expResult = false;
        boolean result = ctrl.notificacaoFaturaCliente(emailPara, f);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of notificacaoFaturaCliente method, of class NotificacaoController.
     */
    @Test
    public void testNotificacaoFaturaClienteEmailEmpty() {
        System.out.println("NotificacaoFaturaClienteEmailEmpty");
        NotificacaoController ctrl =  new NotificacaoController();
              
        String emailPara = "";
        Fatura f = new  Fatura(1, 12345, emailPara, 2D);
        boolean expResult = false;
        boolean result = ctrl.notificacaoFaturaCliente(emailPara, f);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of notificacaoFaturaCliente method, of class NotificacaoController.
     */
    @Test
    public void testNotificacaoFaturaClienteEmpty() {
        System.out.println("NotificacaoFaturaClienteEmailEmpty");
        NotificacaoController ctrl =  new NotificacaoController();
              
        String emailPara = "";
        Fatura f = null;
        boolean expResult = false;
        boolean result = ctrl.notificacaoFaturaCliente(emailPara, f);
        assertEquals(expResult, result);
    }

    /**
     * Test of notificacaoMaulAcopolamentoFalse method, of class NotificacaoController.
     */
    @Test
    public void testNotificacaoMaulAcopolamentoEmailEmpty() {
        System.out.println("notificacaoMaulAcopolamento");
        NotificacaoController ctrl = new NotificacaoController();
                
        String email = "";
        String matricula = "12-qw-12";;

        boolean expResult = false;
        boolean result = ctrl.notificacaoMaulAcopolamento(email, matricula);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of notificacaoMaulAcopolamentoFalse method, of class NotificacaoController.
     */
    @Test
    public void testNotificacaoMaulAcopolamentoMatriculaEmpty() {
        System.out.println("notificacaoMaulAcopolamento");
        NotificacaoController ctrl = new NotificacaoController();
                
        String email = "123@isep.ipp.pt";
        String matricula = "";

        boolean expResult = false;
        boolean result = ctrl.notificacaoMaulAcopolamento(email, matricula);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of notificacaoMaulAcopolamentoFalse method, of class NotificacaoController.
     */
    @Test
    public void testNotificacaoMaulAcopolamentoEmpty() {
        System.out.println("notificacaoMaulAcopolamento");
        NotificacaoController ctrl = new NotificacaoController();
                
        String email = "";
        String matricula = "";

        boolean expResult = false;
        boolean result = ctrl.notificacaoMaulAcopolamento(email, matricula);
        assertEquals(expResult, result);
    }   
    
    /**
     * Test of notificacaoAcopolamento false method, of class NotificacaoController.
     */
    @Test
    public void notificacaoAcopolamentoEmailEmpty() {
        System.out.println("notificacaoAcopolamento");
        NotificacaoController ctrl =  new NotificacaoController();
                
        String email = "";
        String matricula = "12-qw-12";
        String estimativa="123";

        boolean expResult = false;
        boolean result = ctrl.notificacaoAcopolamento(email, matricula,estimativa);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of notificacaoAcopolamento false method, of class NotificacaoController.
     */
    @Test
    public void notificacaoAcopolamentoMatriculaEmpty() {
        System.out.println("notificacaoAcopolamento");
        NotificacaoController ctrl =  new NotificacaoController();
                
        String email = "dasf@asdf.com";
        String matricula = "";
        String estimativa="123";

        boolean expResult = false;
        boolean result = ctrl.notificacaoAcopolamento(email, matricula,estimativa);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of notificacaoAcopolamento false method, of class NotificacaoController.
     */
    @Test
    public void notificacaoAcopolamentoEmpty() {
        System.out.println("notificacaoAcopolamento");
        NotificacaoController ctrl =  new NotificacaoController();
                
        String email = "";
        String matricula = "";
        String estimativa="123";

        boolean expResult = false;
        boolean result = ctrl.notificacaoAcopolamento(email, matricula,estimativa);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of notificacaoRegistoCliente false method, of class NotificacaoController.
     */
    @Test
    public void notificacaoRegistoClienteEmpty() {
        System.out.println("notificacaoRegistoCliente");
        NotificacaoController ctrl =  new NotificacaoController();
                
        String email = "";
        String mensagem = "";

        boolean expResult = false;
        boolean result = ctrl.notificacaoRegistoCliente(email, mensagem);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of notificacaoRegistoCliente false method, of class NotificacaoController.
     */
    @Test
    public void notificacaoRegistoClienteEmailEmpty() {
        System.out.println("notificacaoRegistoCliente");
        NotificacaoController ctrl =  new NotificacaoController();
                
        String email = "";
        String mensagem = "Teste";

        boolean expResult = false;
        boolean result = ctrl.notificacaoRegistoCliente(email, mensagem);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of notificacaoRegistoCliente false method, of class NotificacaoController.
     */
    @Test
    public void notificacaoRegistoClienteMensagemEmpty() {
        System.out.println("notificacaoRegistoCliente");
        NotificacaoController ctrl =  new NotificacaoController();
                
        String email = "teste@teste.com";
        String mensagem = "";

        boolean expResult = false;
        boolean result = ctrl.notificacaoRegistoCliente(email, mensagem);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of notificacaoEnvioEncomenda false method, of class NotificacaoController.
     */
    @Test
    public void notificacaoEnvioEncomendaEmpty() {
        System.out.println("notificacaoEnvioEncomenda");
        NotificacaoController ctrl =  new NotificacaoController();
        
        Encomenda e = null;

        boolean expResult = false;
        boolean result = ctrl.notificacaoEnvioEncomenda(e);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of notificacaoTransferenciaProduto false method, of class NotificacaoController.
     */
    @Test
    public void notificacaoTransferenciaProdutoEmpty() {
        System.out.println("notificacaoTransferenciaProduto");
        NotificacaoController ctrl =  new NotificacaoController();
        
        String mensagem = "";

        boolean expResult = false;
        boolean result = ctrl.notificacaoTransferenciaProduto(mensagem);
        assertEquals(expResult, result);
    }

}
