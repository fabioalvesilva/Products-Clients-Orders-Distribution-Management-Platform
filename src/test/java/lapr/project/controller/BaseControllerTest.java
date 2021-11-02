/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lapr.project.data.DroneAPI;
import lapr.project.data.EncomendaAPI;
import lapr.project.data.EntregasAPI;
import lapr.project.data.FarmaciaAPI;
import lapr.project.data.LocaisAPI;
import lapr.project.data.ParqueAPI;
import lapr.project.data.ScooterAPI;
import lapr.project.data.UtilizadorAPI;
import lapr.project.model.Artigo;
import lapr.project.model.Cliente;
import lapr.project.model.Drone;
import lapr.project.model.Encomenda;
import lapr.project.model.Endereco;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Farmacia;
import lapr.project.model.Local;
import lapr.project.model.Parque;
import lapr.project.model.Scooter;
import lapr.project.model.Utilizador;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author Fábio Silva
 */
public class BaseControllerTest {

    private Cliente cliente;
    private Estafeta estafeta;
    private Farmacia farmacia;
    private Scooter scooter;
    private Encomenda encomenda;
    private Entrega entrega;
    private Endereco endereco;
    private Parque parque;
    private Set<Integer> parques;
    private Drone drone;
    private Artigo artigo;
    private List<Farmacia> farmacias;

    public BaseControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() {

        cliente = new Cliente("Cliente", "cliente@cliente.pt", 123456789, "123", 1, "Rua 1", 100.0, -100.0, 10.0, 1234567899);
        estafeta = new Estafeta("Estafeta", "estafeta@estafeta.pt", 987654321, "1234", 45, 13432123, 10.0);
        endereco = new Endereco("Rua 2", 1100.0, -300.0, 10.0);
        parques = new HashSet<>();
        parques.add(1);
        parques.add(2);
        parque = new Parque(2, 5, 5, 10, 10, 15, endereco);
        farmacia = new Farmacia("Farmacia", 123456784, "farmacia@farmacia.pt", 221234567, endereco, parques);
        scooter = new Scooter(9, "53-LL-93", "Agility", 1, 40, 1, 20, 5, 150.0, 1.0);
        encomenda = new Encomenda(55, "16-12-2020", 123456789, 3, 100.0, -100.0, 10.0, 5);
        artigo = new Artigo(1, "Alglucosidase alfa", 2.59, 6, 94.00);
        drone = new Drone(1, 2, 2, "Dji", 1, 10.0, 10.0, 1, 5.0);
        entrega = new Entrega(100, scooter, 9, 1, "16-12-2020", "16-12-2020", 5.0, 1);
        farmacias = new ArrayList<>();
        farmacias.add(farmacia);

    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of validarAdmin method, of class BaseController.
     */
    @Test
    public void testValidarAdmin() {
        System.out.println("validarAdmin");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "admin@farmacia.com";
        instance.utilizadorAPI = api;
        when(api.verificarAdmin(email)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.validarAdmin(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of validarAdmin method, of class BaseController.
     */
    @Test
    public void testValidarAdminNotOk() {
        System.out.println("validarAdmin");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "admin@farmacia.pt";
        instance.utilizadorAPI = api;
        when(api.verificarAdmin(email)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.validarAdmin(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of validarAdmin method, of class BaseController.
     */
    @Test
    public void testValidarAdminEmptyEmail() {
        System.out.println("validarAdmin");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "";
        instance.utilizadorAPI = api;
        when(api.verificarAdmin(email)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.validarAdmin(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginAdmin method, of class BaseController.
     */
    @Test
    public void testLoginAdmin() {
        System.out.println("login");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "admin@farmacia.com";
        String password = "123";
        instance.utilizadorAPI = api;
        when(api.validarLoginAdmin(email, password)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.loginAdmin(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginAdmin method, of class BaseController.
     */
    @Test
    public void testLoginAdminNotOk() {
        System.out.println("login");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "admin@farmacia.pt";
        String password = "1234";
        instance.utilizadorAPI = api;
        when(api.validarLoginAdmin(email, password)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.loginAdmin(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginAdmin method, of class BaseController.
     */
    @Test
    public void testLoginAdminEmailEmpty() {
        System.out.println("login");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "";
        String password = "1234";
        instance.utilizadorAPI = api;
        when(api.validarLoginAdmin(email, password)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.loginAdmin(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginAdmin method, of class BaseController.
     */
    @Test
    public void testLoginAdminPasswordEmpty() {
        System.out.println("login");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "admin@farmacia.com";
        String password = "";
        instance.utilizadorAPI = api;
        when(api.validarLoginAdmin(email, password)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.loginAdmin(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginAdmin method, of class BaseController.
     */
    @Test
    public void testLoginAdminEmpty() {
        System.out.println("login");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "";
        String password = "";
        instance.utilizadorAPI = api;
        when(api.validarLoginAdmin(email, password)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.loginAdmin(email, password);
        assertEquals(expResult, result);
    }
    /**
     * Test of loginUtilizador method, of class BaseController.
     */
    @Test
    public void testLoginUtilizador() {
        System.out.println("login");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "cliente@farmacia.com";
        String password = "123";
        instance.utilizadorAPI = api;
        when(api.validarLoginCliente(email, password)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.loginUtilizador(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUtilizador method, of class BaseController.
     */
    @Test
    public void testLoginUtilizadorNotOk() {
        System.out.println("login");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "cliente@farmacia.pt";
        String password = "1234";
        instance.utilizadorAPI = api;
        when(api.validarLoginCliente(email, password)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.loginAdmin(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUtilizador method, of class BaseController.
     */
    @Test
    public void testLoginUtilizadorEmailEmpty() {
        System.out.println("login");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "";
        String password = "1234";
        instance.utilizadorAPI = api;
        when(api.validarLoginCliente(email, password)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.loginAdmin(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUtilizador method, of class BaseController.
     */
    @Test
    public void testLoginUtilizadorPasswordEmpty() {
        System.out.println("login");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "cliente@farmacia.com";
        String password = "";
        instance.utilizadorAPI = api;
        when(api.validarLoginCliente(email, password)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.loginAdmin(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUtilizador method, of class BaseController.
     */
    @Test
    public void testLoginUtilizadorEmpty() {
        System.out.println("login");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        String email = "";
        String password = "";
        instance.utilizadorAPI = api;
        when(api.validarLoginCliente(email, password)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.loginAdmin(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of novoCliente method, of class BaseController.
     */
    @Test
    public void testNovoCliente() {
        System.out.println("novoCliente");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        Cliente c = cliente;
        instance.utilizadorAPI = api;
        when(api.adicionarCliente(c)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.novoCliente(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of novoCliente method, of class BaseController.
     */
    @Test
    public void testNovoClienteNotOk() {
        System.out.println("novoCliente");
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        Cliente c = cliente;
        instance.utilizadorAPI = api;
        when(api.adicionarCliente(c)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.novoCliente(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of novoEstafeta method, of class BaseController.
     */
    @Test
    public void testNovoEstafeta() {
        System.out.println("novoEstafeta");

        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        Estafeta e = estafeta;
        instance.utilizadorAPI = api;
        when(api.adicionarEstafeta(e, 1)).thenReturn(1);
        int expResult = 1;
        int result = instance.novoEstafeta(e, 1);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of getEstafetaDisponivelParaEntrega method, of class BaseController.
     */
    @Test
    public void testGetEstafetaDisponivelParaEntrega() {
        System.out.println("getEstafetaDisponivelParaEntrega");
        BaseController instance = Mockito.spy(new BaseController());
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        instance.farmaciaAPI = api;
        when(api.getEstafetaDisponivelPorFarmacia(1)).thenReturn(1);
        int expResult = 1;
        int result = instance.getEstafetaDisponivelParaEntrega(1);
        assertEquals(expResult, result);
    }
    
    

    /**
     * Test of novaFarmacia method, of class BaseController.
     */
    @Test
    public void testNovaFarmacia() {
        System.out.println("novaFarmacia");
        BaseController instance = Mockito.spy(new BaseController());
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        Farmacia f = farmacia;
        instance.farmaciaAPI = api;
        when(api.adicionarFarmacia(f)).thenReturn(1);
        Integer expResult = 1;
        Integer result = instance.novaFarmacia(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of removerFarmacia method, of class BaseController.
     */
    @Test
    public void testRemoverFarmacia() {
        System.out.println("removerFarmacia");
        BaseController instance = Mockito.spy(new BaseController());
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        Farmacia f = farmacia;
        instance.farmaciaAPI = api;
        when(api.removerFarmacia(f.getNif())).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.removerFarmacia(f.getNif());
        assertEquals(expResult, result);
    }

    /**
     * Test of removerFarmacia method, of class BaseController.
     */
    @Test
    public void testRemoverFarmaciaNotOk() {
        System.out.println("removerFarmacia");
        BaseController instance = Mockito.spy(new BaseController());
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        Farmacia f = farmacia;
        instance.farmaciaAPI = api;
        when(api.removerFarmacia(f.getNif())).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.removerFarmacia(f.getNif());
        assertEquals(expResult, result);
    }

    /**
     * Test of atualizarFarmacia method, of class BaseController.
     */
    @Test
    public void testAtualizarFarmacia() {
        System.out.println("atualizarFarmacia");
        BaseController instance = Mockito.spy(new BaseController());
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        Farmacia f = farmacia;
        instance.farmaciaAPI = api;
        when(api.atualizarFarmacia(f)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.atualizarFarmacia(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of atualizarFarmacia method, of class BaseController.
     */
    @Test
    public void testAtualizarFarmaciaNotOk() {
        System.out.println("atualizarFarmacia");
        BaseController instance = Mockito.spy(new BaseController());
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        Farmacia f = farmacia;
        instance.farmaciaAPI = api;
        when(api.atualizarFarmacia(f)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.atualizarFarmacia(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of atualizarScooter method, of class BaseController.
     */
    @Test
    public void testAtualizarScooter() {
        System.out.println("atualizarScooter");
        String matricula = "53-LL-93";
        String estadoScooter = "Avariada";
        BaseController instance = Mockito.spy(new BaseController());
        ScooterAPI api = Mockito.mock(ScooterAPI.class);
        instance.scooterAPI = api;
        when(api.atualizarScooter(matricula, estadoScooter)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.atualizarScooter(matricula, estadoScooter);
        assertEquals(expResult, result);
    }

    /**
     * Test of atualizarScooter method, of class BaseController.
     */
    @Test
    public void testAtualizarScooterNotOk() {
        System.out.println("atualizarScooter");
        String matricula = "53-LL-93";
        String estadoScooter = "Avariada";
        BaseController instance = Mockito.spy(new BaseController());
        ScooterAPI api = Mockito.mock(ScooterAPI.class);
        instance.scooterAPI = api;
        when(api.atualizarScooter(matricula, estadoScooter)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.atualizarScooter(matricula, estadoScooter);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of removerScooter method, of class BaseController.
     */
    @Test
    public void testRemoverScooter() {
        System.out.println("removerScooter");
        String matricula = "53-LL-93";
        BaseController instance = Mockito.spy(new BaseController());
        ScooterAPI api = Mockito.mock(ScooterAPI.class);
        instance.scooterAPI = api;
        when(api.removerScooter(matricula)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.removerScooter(matricula);
        assertEquals(expResult, result);
    }

    /**
     * Test of removerScooter method, of class BaseController.
     */
    @Test
    public void testRemoverScooterNotOk() {
        System.out.println("removerScooter");
        String matricula = "53-LL-93";
        BaseController instance = Mockito.spy(new BaseController());
        ScooterAPI api = Mockito.mock(ScooterAPI.class);
        instance.scooterAPI = api;
        when(api.removerScooter(matricula)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.removerScooter(matricula);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getScooterByMatricula method, of class BaseController.
     */
    @Test
    public void testGetScooterByMatricula() {
        System.out.println("getScooterByMatricula");
        String matricula = "53-LL-93";
        BaseController instance = Mockito.spy(new BaseController());
        ScooterAPI api = Mockito.mock(ScooterAPI.class);
        instance.scooterAPI = api;
        when(api.getScooterByMatricula(matricula)).thenReturn(scooter);
        Scooter expResult = scooter;
        Scooter result = instance.getScooterByMatricula(matricula);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getScooterById method, of class BaseController.
     */
    @Test
    public void testGetScooterById() {
        System.out.println("getScooterById");
        Integer id = 1;
        BaseController instance = Mockito.spy(new BaseController());
        ScooterAPI api = Mockito.mock(ScooterAPI.class);
        instance.scooterAPI = api;
        when(api.getScooterById(id)).thenReturn(scooter);
        Scooter expResult = scooter;
        Scooter result = instance.getScooterById(id);
        assertEquals(expResult, result);
    }
    
    

    /**
     * Test of getIdFarmaciaPorUtilizador method, of class BaseController.
     */
    @Test
    public void testGetIdFarmaciaPorUtilizador() {
        System.out.println("getIdFarmaciaPorUtilizador");
        String email = "Antonio_Silva@gmail.com";
        BaseController instance = Mockito.spy(new BaseController());
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        instance.utilizadorAPI = api;
        when(api.getIdFarmaciaPorUtilizador(email)).thenReturn(1);
        int expResult = 1;
        int result = instance.getIdFarmaciaPorUtilizador(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllArtigos method, of class BaseController.
     */
    @Test
    public void testGetAllArtigos() {
        System.out.println("getAllArtigos");
        Integer idFarmacia = 1;
        List<Artigo> lista = new ArrayList<>();
        lista.add(artigo);
        BaseController instance = Mockito.spy(BaseController.class);
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        instance.farmaciaAPI = api;
        when(api.getAllArtigos(idFarmacia)).thenReturn(lista);
        List<Artigo> result = instance.getAllArtigos(idFarmacia);
        assertEquals(lista.size(), result.size());
    }

    /**
     * Test of getArtigoById method, of class BaseController.
     */
    @Test
    public void testGetArtigoById() {
        System.out.println("getArtigoById");
        Integer idFarmacia = 1;
        Integer idArtigo = 1;
        Artigo a = artigo;
        BaseController instance = Mockito.spy(BaseController.class);
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        instance.farmaciaAPI = api;
        when(api.getArtigo(idFarmacia, idArtigo)).thenReturn(a);
        Artigo expResult = artigo;
        Artigo result = instance.getArtigoById(idFarmacia, idArtigo);
        assertEquals(expResult.toString(), result.toString());
    }
    
    /**
     * Test of transferirProduto method, of class BaseController.
     */
    @Test
    public void testTransferirProduto() {
        System.out.println("transferirProduto");
        Integer idFarmaciaRemetente = 1;
        Integer idFarmaciaDestinatario = 1;
        Integer idProduto = 1;
        Integer quantidade = 2;
        BaseController instance = Mockito.spy(BaseController.class);
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        instance.farmaciaAPI = api;
        when(api.transferirProduto(idFarmaciaRemetente, idFarmaciaDestinatario, idProduto, quantidade)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.transferirProduto(idFarmaciaRemetente, idFarmaciaDestinatario, idProduto, quantidade);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of transferirProduto method, of class BaseController.
     */
    @Test
    public void testTransferirProdutoNotOk() {
        System.out.println("transferirProduto");
        Integer idFarmaciaRemetente = 1;
        Integer idFarmaciaDestinatario = 1;
        Integer idProduto = 1;
        Integer quantidade = 2;
        BaseController instance = Mockito.spy(BaseController.class);
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        instance.farmaciaAPI = api;
        when(api.transferirProduto(idFarmaciaRemetente, idFarmaciaDestinatario, idProduto, quantidade)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.transferirProduto(idFarmaciaRemetente, idFarmaciaDestinatario, idProduto, quantidade);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStockArtigo method, of class BaseController.
     */
    @Test
    public void testGetStockArtigo() {
        System.out.println("getStockArtigo");
        int idProduto = 1;
        BaseController instance = Mockito.spy(BaseController.class);
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        instance.farmaciaAPI = api;
        when(api.getStockArtigo(1, idProduto)).thenReturn(124);
        int expResult = 124;
        int result = instance.getStockArtigo(1, idProduto);
        assertEquals(expResult, result);
    }

    /**
     * Test of criarEncomenda method, of class BaseController.
     */
    @Test
    public void testCriarEncomenda() {
        System.out.println("criarEncomenda");
        BaseController instance = Mockito.spy(BaseController.class);
        EncomendaAPI api = Mockito.mock(EncomendaAPI.class);
        instance.encomendaAPI = api;
        when(api.criarEncomenda(encomenda)).thenReturn(55);
        Integer expResult = 55;
        Integer result = instance.criarEncomenda(encomenda);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmailClienteByEncomenda method, of class BaseController.
     */
    @Test
    public void testGetEmailClienteByEncomenda() {
        System.out.println("getEmailClienteByEncomenda");
        BaseController instance = Mockito.spy(BaseController.class);
        EncomendaAPI api = Mockito.mock(EncomendaAPI.class);
        instance.encomendaAPI = api;
        when(api.getEmailClienteByNif(encomenda)).thenReturn("cliente@cliente.pt");
        String expResult = "cliente@cliente.pt";
        String result = instance.getEmailClienteByEncomenda(encomenda);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClienteByNif method, of class BaseController.
     */
    @Test
    public void testGetClienteByNif() {
        System.out.println("getEmailClienteByNif");
        Integer nif = 123456789;
        BaseController instance = Mockito.spy(BaseController.class);
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        instance.utilizadorAPI = api;
        when(api.getClienteByNif(nif)).thenReturn(cliente);
        Cliente expResult = cliente;
        Cliente result = instance.getClienteByNIF(nif);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getClienteByNif method, of class BaseController.
     */
    @Test
    public void testGetClienteByNifNull() {
        System.out.println("getEmailClienteByNif");
        Integer nif = 123456789;
        Cliente c = null;
        BaseController instance = Mockito.spy(BaseController.class);
        UtilizadorAPI api = Mockito.mock(UtilizadorAPI.class);
        instance.utilizadorAPI = api;
        when(api.getClienteByNif(nif)).thenReturn(c);
        Cliente expResult = c;
        Cliente result = instance.getClienteByNIF(nif);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllParques method, of class BaseController.
     */
    @Test
    public void testGetAllParques() {
        System.out.println("getAllParques");
        BaseController instance = Mockito.spy(BaseController.class);
        ParqueAPI api = Mockito.mock(ParqueAPI.class);
        instance.parqueAPI = api;
        List<Parque> p = new ArrayList<>();
        p.add(parque);
        when(api.getAllParques()).thenReturn(p);
        assertEquals(p.size(), instance.getAllParques().size());
    }

    /**
     * Test of updateCapacidadeParque method, of class BaseController.
     */
    @Test
    public void testUpdateCapacidadeParque() {
        System.out.println("updateCapacidadeParque");
        int idParque = 1;
        int nrLugaresNormal = 0;
        int nrLugaresEletricos = 0;
        BaseController instance = Mockito.spy(BaseController.class);
        ParqueAPI api = Mockito.mock(ParqueAPI.class);
        instance.parqueAPI = api;
        when(api.updateCapacidadeParque(idParque, nrLugaresNormal, nrLugaresEletricos)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.updateCapacidadeParque(idParque, nrLugaresNormal, nrLugaresEletricos);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateCapacidadeParque method, of class BaseController.
     */
    @Test
    public void testUpdateCapacidadeParqueNotOk() {
        System.out.println("updateCapacidadeParque");
        int idParque = 1;
        int nrLugaresNormal = 0;
        int nrLugaresEletricos = 0;
        BaseController instance = Mockito.spy(BaseController.class);
        ParqueAPI api = Mockito.mock(ParqueAPI.class);
        instance.parqueAPI = api;
        when(api.updateCapacidadeParque(idParque, nrLugaresNormal, nrLugaresEletricos)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.updateCapacidadeParque(idParque, nrLugaresNormal, nrLugaresEletricos);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateStockArtigo method, of class BaseController.
     */
    @Test
    public void testUpdateStockArtigo() {
        System.out.println("updateStockArtigo");
        int idFarmacia = 1;
        int idArt = 1;
        int quantidade = 50;
        BaseController instance = Mockito.spy(BaseController.class);
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        instance.farmaciaAPI = api;
        when(api.updateStockArtigo(idFarmacia, idArt, quantidade)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.updateStockArtigo(idFarmacia, idArt, quantidade);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateStockArtigo method, of class BaseController.
     */
    @Test
    public void testUpdateStockArtigoNotOk() {
        System.out.println("updateStockArtigo");
        int idFarmacia = 1;
        int idArt = 1;
        int quantidade = 50;
        BaseController instance = Mockito.spy(BaseController.class);
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        instance.farmaciaAPI = api;
        when(api.updateStockArtigo(idFarmacia, idArt, quantidade)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.updateStockArtigo(idFarmacia, idArt, quantidade);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOcupacaoEstafeta method, of class BaseController.
     */
    @Test
    public void testGetOcupacaoEstafeta() {
        System.out.println("getOcupacaoEstafeta");
        int idEstafeta = 1;
        BaseController instance = Mockito.spy(BaseController.class);
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        instance.farmaciaAPI = api;
        when(api.verificarOcupacaoEstafeta(idEstafeta)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.getOcupacaoEstafeta(idEstafeta);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOcupacaoEstafeta method, of class BaseController.
     */
    @Test
    public void testGetOcupacaoEstafetaNotOk() {
        System.out.println("getOcupacaoEstafeta");
        int idEstafeta = 1;
        BaseController instance = Mockito.spy(BaseController.class);
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        instance.farmaciaAPI = api;
        when(api.verificarOcupacaoEstafeta(idEstafeta)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.getOcupacaoEstafeta(idEstafeta);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEncomendasPendentes method, of class BaseController.
     */
    @Test
    public void testGetEncomendasPendentes() {
        System.out.println("getEncomendasPendentes");
        Integer idFarmacia = 1;
        BaseController instance = Mockito.spy(BaseController.class);
        EntregasAPI api = Mockito.mock(EntregasAPI.class);
        instance.entregaAPI = api;
        List<Encomenda> lista = new ArrayList<>();
        lista.add(encomenda);
        when(api.getEncomendasPendentes(idFarmacia)).thenReturn(lista);
        List<Encomenda> expResult = lista;
        List<Encomenda> result = instance.getEncomendasPendentes(idFarmacia);
        assertEquals(expResult.size(), result.size());
    }

//    /**
//     * Test of registarEntrega method, of class BaseController.
//     */
//    @Test
//    public void testRegistarEntrega() {
//        System.out.println("registarEntrega");
//        BaseController instance = Mockito.spy(BaseController.class);
//        EntregasAPI api = Mockito.mock(EntregasAPI.class);
//        instance.entregaAPI = api;
//        when(api.registarEntrega(entrega)).thenReturn(entrega);
//        Entrega expResult = entrega;
//        Entrega result = instance.registarEntrega(entrega);
//        assertEquals(expResult.toString(), result.toString());
//    }
    /**
     * Test of getIdEnderecoPorFarmacia method, of class BaseController.
     */
    @Test
    public void testGetIdEnderecoPorFarmacia() {
        System.out.println("getIdEnderecoPorFarmacia");
        Integer idFarmacia = 1;
        BaseController instance = Mockito.spy(BaseController.class);
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        instance.farmaciaAPI = api;
        when(api.getIdEnderecoByFarmacia(idFarmacia)).thenReturn(1);
        Integer expResult = 1;
        Integer result = instance.getIdEnderecoPorFarmacia(idFarmacia);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllFarmacias method, of class BaseController.
     */
    @Test
    public void testGetAllFarmacias() {
        System.out.println("getAllFarmacias");
        BaseController instance = Mockito.spy(BaseController.class);
        FarmaciaAPI api = Mockito.mock(FarmaciaAPI.class);
        instance.farmaciaAPI = api;
        when(api.getAllFarmacias()).thenReturn(farmacias);
        List<Farmacia> expResult = farmacias;
        List<Farmacia> result = instance.getAllFarmacias();
        assertEquals(expResult, result);
    }

    /**
     * Test of criarParque method, of class BaseController.
     */
    @Test
    public void testCriarParque() {
        System.out.println("criarParque");
        int idFarmacia = 0;
        BaseController instance = Mockito.spy(BaseController.class);
        ParqueAPI api = Mockito.mock(ParqueAPI.class);
        instance.parqueAPI = api;
        when(api.adicionarParque(idFarmacia, parque)).thenReturn(1);
        int expResult = 1;
        int result = instance.criarParque(idFarmacia, parque);
        assertEquals(expResult, result);
    }

    /**
     * Test of removerParque method, of class BaseController.
     */
    @Test
    public void testRemoverParque() {
        System.out.println("removerParque");
        int idParque = 0;
        BaseController instance = Mockito.spy(BaseController.class);
        ParqueAPI api = Mockito.mock(ParqueAPI.class);
        instance.parqueAPI = api;
        when(api.removerParque(idParque)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.removerParque(idParque);
        assertEquals(expResult, result);
    }

    /**
     * Test of removerParque method, of class BaseController.
     */
    @Test
    public void testRemoverParqueNotOk() {
        System.out.println("removerParque");
        int idParque = 0;
        BaseController instance = Mockito.spy(BaseController.class);
        ParqueAPI api = Mockito.mock(ParqueAPI.class);
        instance.parqueAPI = api;
        when(api.removerParque(idParque)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.removerParque(idParque);
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarScooter method, of class BaseController.
     */
    @Test
    public void testAdicionarScooter() {
        System.out.println("adicionarScooter");
        BaseController instance = Mockito.spy(new BaseController());
        ScooterAPI api = Mockito.mock(ScooterAPI.class);
        Scooter s = scooter;
        instance.scooterAPI = api;
        when(api.adicionarScooter(s, 1)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.adicionarScooter(s, 1);
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarScooter method, of class BaseController.
     */
    @Test
    public void testAdicionarScooterNotOk() {
        System.out.println("adicionarScooter");
        BaseController instance = Mockito.spy(new BaseController());
        ScooterAPI api = Mockito.mock(ScooterAPI.class);
        Scooter s = scooter;
        instance.scooterAPI = api;
        when(api.adicionarScooter(s, 1)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.adicionarScooter(s, 1);
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarDrone method, of class BaseController.
     */
    @Test
    public void testAdicionarDrone() {
        System.out.println("adicionarDrone");
        BaseController instance = Mockito.spy(new BaseController());
        DroneAPI api = Mockito.mock(DroneAPI.class);
        Drone d = drone;
        instance.droneAPI = api;
        when(api.adicionarDrone(d)).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.adicionarDrone(d);
        assertEquals(expResult, result);
    }

    /**
     * Test of adicionarDrone method, of class BaseController.
     */
    @Test
    public void testAdicionarDroneNotOk() {
        System.out.println("adicionarDrone");
        BaseController instance = Mockito.spy(new BaseController());
        DroneAPI api = Mockito.mock(DroneAPI.class);
        Drone d = drone;
        instance.droneAPI = api;
        when(api.adicionarDrone(d)).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.adicionarDrone(d);
        assertEquals(expResult, result);
    }

    /**
     * Test of atualizarDrone method, of class BaseController.
     */
    @Test
    public void testAtualizarDrone() {
        System.out.println("atualizarDrone");
        BaseController instance = Mockito.spy(new BaseController());
        DroneAPI api = Mockito.mock(DroneAPI.class);
        Drone d = drone;
        instance.droneAPI = api;
        when(api.atualizarDrone(d.getNrRegisto(), "Phantom")).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.atualizarDrone(d.getNrRegisto(), "Phantom");
        assertEquals(expResult, result);
    }

    /**
     * Test of atualizarDrone method, of class BaseController.
     */
    @Test
    public void testAtualizarDroneNotOk() {
        System.out.println("atualizarDrone");
        BaseController instance = Mockito.spy(new BaseController());
        DroneAPI api = Mockito.mock(DroneAPI.class);
        Drone d = drone;
        instance.droneAPI = api;
        when(api.atualizarDrone(d.getNrRegisto(), "Phantom")).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.atualizarDrone(d.getNrRegisto(), "Phantom");
        assertEquals(expResult, result);
    }

    /**
     * Test of removerDrone method, of class BaseController.
     */
    @Test
    public void testRemoverDrone() {
        System.out.println("removerDrone");
        BaseController instance = Mockito.spy(new BaseController());
        DroneAPI api = Mockito.mock(DroneAPI.class);
        Drone d = drone;
        instance.droneAPI = api;
        when(api.removerDrone(d.getNrRegisto())).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.removerDrone(d.getNrRegisto());
        assertEquals(expResult, result);
    }

    /**
     * Test of removerDrone method, of class BaseController.
     */
    @Test
    public void testRemoverDroneNotOk() {
        System.out.println("removerDrone");
        BaseController instance = Mockito.spy(new BaseController());
        DroneAPI api = Mockito.mock(DroneAPI.class);
        Drone d = drone;
        instance.droneAPI = api;
        when(api.removerDrone(d.getNrRegisto())).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.removerDrone(d.getNrRegisto());
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDroneByNrRegisto method, of class BaseController.
     */
    @Test
    public void testGetDroneByNrRegisto() {
        System.out.println("getDroneByNrRegisto");
        Integer matricula = 12345;
        BaseController instance = Mockito.spy(new BaseController());
        DroneAPI api = Mockito.mock(DroneAPI.class);
        instance.droneAPI = api;
        when(api.getDroneByNrRegisto(matricula)).thenReturn(drone);
        Drone expResult = drone;
        Drone result = instance.getDroneByNrRegisto(matricula);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDroneById method, of class BaseController.
     */
    @Test
    public void testGetDroneById() {
        System.out.println("getDroneById");
        Integer id = 1;
        BaseController instance = Mockito.spy(new BaseController());
        DroneAPI api = Mockito.mock(DroneAPI.class);
        instance.droneAPI = api;
        when(api.getDroneById(id)).thenReturn(drone);
        Drone expResult = drone;
        Drone result = instance.getDroneById(id);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getLocalFarmaciaById method, of class BaseController.
     */
    @Test
    public void testGetLocalFarmaciaById() {
        System.out.println("getLocalFarmaciaById");
        Integer idFarmacia = 1;
        Artigo a = artigo;
        BaseController instance = Mockito.spy(BaseController.class);
        LocaisAPI api = Mockito.mock(LocaisAPI.class);
        instance.locaisAPI = api;
        when(api.getFarmaciaById(idFarmacia)).thenReturn(farmacia);
        Local expResult = farmacia;
        Local result = instance.getLocalFarmaciaById(idFarmacia);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getScooterParaEntrega method, of class BaseController.
     */
    @Test
    public void testGetScooterParaEntrega() {
        System.out.println("getScooterParaEntrega");
        BaseController instance = Mockito.spy(BaseController.class);
        EntregasAPI api = Mockito.mock(EntregasAPI.class);
        instance.entregaAPI = api;
        when(api.getScooterParaEntrega(1)).thenReturn(1);
        int expResult = 1;
        int result = instance.getScooterParaEntrega(1);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDroneParaEntrega method, of class BaseController.
     */
    @Test
    public void testGetDroneParaEntrega() {
        System.out.println("getDroneParaEntrega");
        BaseController instance = Mockito.spy(BaseController.class);
        EntregasAPI api = Mockito.mock(EntregasAPI.class);
        instance.entregaAPI = api;
        when(api.getDroneParaEntrega(1)).thenReturn(1);
        int expResult = 1;
        int result = instance.getDroneParaEntrega(1);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of registarEntrega method, of class BaseController.
     */
    @Test
    public void testRegistarEntrega() {
        System.out.println("registarEntrega");
        
        BaseController instance = Mockito.spy(BaseController.class);
        EntregasAPI api = Mockito.mock(EntregasAPI.class);
        instance.entregaAPI = api;
        when(api.registarEntrega(entrega)).thenReturn(entrega);
        Entrega expResult = entrega;
        Entrega result = instance.registarEntrega(entrega);
        assertEquals(expResult, result);
    }

}
