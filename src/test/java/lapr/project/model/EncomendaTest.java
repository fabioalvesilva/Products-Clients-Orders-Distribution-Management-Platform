/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Fábio Silva
 */
public class EncomendaTest {

    private Encomenda encomenda1;
    private Encomenda encomenda2;
    private Endereco a;

    public EncomendaTest() {
    }

    @BeforeEach
    public void setUp() {

        encomenda1 = new Encomenda(1, "12-12-2020", 123456789, 3, 100.0, 200.0, 10.0, 5);
        encomenda2 = new Encomenda(2, "01-01-2021", 98764321, 2, 50.0, 150.0, 10.0, 10);
        a = new Endereco();
    }

    /**
     * Test of getId method, of class Encomenda.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 1;
        int result = encomenda1.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDataEncomenda method, of class Encomenda.
     */
    @Test
    public void testGetDataEncomenda() {
        System.out.println("getDataEncomenda");
        String expResult = "12-12-2020";
        String result = encomenda1.getDataEncomenda();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNifCliente method, of class Encomenda.
     */
    @Test
    public void testGetNifCliente() {
        System.out.println("getNifCliente");
        long expResult = 123456789;
        long result = encomenda1.getNifCliente();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado method, of class Encomenda.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        int expResult = 3;
        int result = encomenda1.getEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPeso method, of class Encomenda.
     */
    @Test
    public void testGetPeso() {
        System.out.println("getPeso");
        double expResult = 5.0;
        double result = encomenda1.getPeso();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getEndereco method, of class Encomenda.
     */
    @Test
    public void testGetEndereco() {
        System.out.println("getEndereco");
        Endereco expResult = new Endereco("", 100.0, 200.0, 10.0);
        Endereco result = encomenda1.getEndereco();
        assertEquals(expResult.getLatitude(), result.getLatitude());
        assertEquals(expResult.getLongitude(), result.getLongitude());
    }

    /**
     * Test of setId method, of class Encomenda.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 1;
        Encomenda instance = new Encomenda();
        instance.setId(id);
        int expResult = 1;
        assertEquals(expResult, instance.getId());
    }

    /**
     * Test of setDataEncomenda method, of class Encomenda.
     */
    @Test
    public void testSetDataEncomenda() {
        System.out.println("setDataEncomenda");
        String dataEncomenda = "20-12-2020";
        Encomenda instance = new Encomenda();
        instance.setDataEncomenda(dataEncomenda);
        String expResult = "20-12-2020";
        assertEquals(expResult, instance.getDataEncomenda());
    }

    /**
     * Test of setNifCliente method, of class Encomenda.
     */
    @Test
    public void testSetNifCliente() {
        System.out.println("setNifCliente");
        int nifCliente = 123456789;
        Encomenda instance = new Encomenda();
        instance.setNifCliente(nifCliente);
        int expResult = 123456789;
        assertEquals(expResult, instance.getNifCliente());
    }

    /**
     * Test of setEstado method, of class Encomenda.
     */
    @Test
    public void testSetEstado() {
        System.out.println("setEstado");
        int estado = 3;
        Encomenda instance = new Encomenda();
        instance.setEstado(estado);
        int expResult = 3;
        assertEquals(expResult, instance.getEstado());
    }

    /**
     * Test of setPeso method, of class Encomenda.
     */
    @Test
    public void testSetPeso() {
        System.out.println("setPeso");
        double peso = 5.0;
        Encomenda instance = new Encomenda();
        instance.setPeso(peso);
        double expResult = 5.0;
        assertEquals(expResult, instance.getPeso());
    }

    /**
     * Test of setEndereco method, of class Encomenda.
     */
    @Test
    public void testSetEndereco() {
        System.out.println("setEndereco");
        Endereco endereco = new Endereco("Rua 1", 10.00, 20.00, 10.0);
        Encomenda instance = new Encomenda();
        instance.setEndereco(endereco);
        Endereco expResult = endereco;
        assertEquals(expResult, instance.getEndereco());
        assertEquals(expResult.getRua(), instance.getEndereco().getRua());
    }

    /**
     * Test of toString method, of class Encomenda.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertEquals(encomenda1.toString(), encomenda1.toString());
        assertNotEquals(encomenda1.toString(), encomenda2.toString());
    }

    /**
     * Test of getListaArtigos method, of class Encomenda.
     */
    @Test
    public void testGetListaArtigos() {
        System.out.println("getListaArtigos");
        Encomenda instance = new Encomenda();
        Map<Artigo, Integer> expResult = null;
        Artigo a1 = new Artigo(19, "test", 12, 5, 3);
        Artigo a2 = new Artigo(17, "test2", 10, 1, 1);
        instance.adicionarArtigo(a1, 2);
        Map<Artigo, Integer> expected = new HashMap<>();
        expected.put(a1, 2);
        Map<Artigo, Integer> result = instance.getListaArtigos();
        assertEquals(expected, result);

    }

    /**
     * Test of setPeso method, of class Encomenda.
     */
    @Test
    public void testSetPeso_double() {
        System.out.println("setPeso");
        double peso = 1.0;
        Encomenda instance = new Encomenda();
        instance.setPeso(peso);
    }

    /**
     * Test of setPeso method, of class Encomenda.
     */
    @Test
    public void testSetPeso_0args() {
        System.out.println("setPeso");
        Encomenda instance = new Encomenda();
        Artigo a1 = new Artigo(19, "test", 12, 5, 3);
        Artigo a2 = new Artigo(19, "test", 12, 5, 3);
        instance.adicionarArtigo(a1, 0);
        instance.adicionarArtigo(a2, 2);
        instance.setPeso();
        assertEquals(6, instance.getPeso());
    }

    /**
     * Test of calcularPeso method, of class Encomenda.
     */
    @Test
    public void testCalcularPeso() {
        System.out.println("calcularPeso");
        Encomenda instance = new Encomenda();
        Artigo a1 = new Artigo(19, "test", 12, 5, 3);
        Artigo a2 = new Artigo(19, "test", 12, 5, 3);
        instance.adicionarArtigo(a1, 0);
        instance.adicionarArtigo(a2, 2);
        instance.setPeso();
        assertEquals(6, instance.getPeso());
    }

    /**
     * Test of calcularPeso method, of class Encomenda.
     */
    @Test
    public void testCalcularPesoGetValueNull() {
        System.out.println("calcularPeso");
        Encomenda instance = new Encomenda();
        Artigo a1 = new Artigo(19, "test", 12, 5, 3);
        Artigo a2 = new Artigo(19, "test", 12, 5, 3);
        instance.adicionarArtigo(a1, 0);
        instance.adicionarArtigo(a2, 2);
        instance.setPeso();
        assertEquals(6, instance.getPeso());
    }
    
    /**
     * Test of calcularPeso method, of class Encomenda.
     */
    @Test
    public void testCalcularPesoEmptyList() {
        System.out.println("calcularPeso");
        Encomenda instance = new Encomenda();
        instance.setPeso();
        assertEquals(0, instance.getPeso());
    }

    /**
     * Test of adicionarArtigo method, of class Encomenda.
     */
    @Test
    public void testAdicionarArtigo() {
        System.out.println("adicionarArtigo");
        Artigo a1 = new Artigo(19, "test", 12, 5, 3);
        Artigo a2 = new Artigo(20, "test2", 12, 5, 101);
        int quantidade = 3;
        int quantidade2 = 0;
        Encomenda instance = new Encomenda();
        boolean expResult = true;
        boolean result = instance.adicionarArtigo(a1, quantidade);
        boolean result2 = instance.adicionarArtigo(a1, quantidade2);
        boolean result3 = instance.adicionarArtigo(a2, quantidade2);
        boolean expResult2 = false;

        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
        assertEquals(expResult2, result3);
    }

    /**
     * Test of adicionarArtigo method, of class Encomenda.
     */
    @Test
    public void testAdicionarArtigoDois() {
        System.out.println("adicionarArtigo");
        Artigo a1 = new Artigo(19, "test", 12, 5, 3);
        Artigo a2 = new Artigo(20, "test2", 12, 5, 101);
        int quantidade = 3;
        int quantidade2 = 0;
        Encomenda instance = new Encomenda();
        boolean expResult = true;

        boolean result2 = instance.adicionarArtigo(a1, quantidade2);
        boolean result3 = instance.adicionarArtigo(a2, quantidade2);

        assertNotEquals(expResult, result2);
        assertNotEquals(expResult, result3);
    }
    
    /**
     * Test of adicionarArtigo method, of class Encomenda.
     */
    @Test
    public void testAdicionarArtigoTres() {
        System.out.println("adicionarArtigo");
        Artigo a2 = new Artigo(20, "test2", 12, 5, 101);
        int quantidade2 = 0;
        Encomenda instance = new Encomenda();
        instance.adicionarArtigo(a2, 1);
        boolean expResult = false;
        boolean result3 = instance.adicionarArtigo(a2, quantidade2);
        
        assertEquals(expResult, result3);
    }

    /**
     * Test of removerArtigo method, of class Encomenda.
     */
    @Test
    public void testRemoverArtigo() {
        System.out.println("removerArtigo");
        Encomenda instance = new Encomenda();

        boolean expected = true;

        Artigo a1 = new Artigo(19, "test", 12, 5, 3);
        Artigo a2 = new Artigo(18, "test", 12, 5, 3);
        instance.adicionarArtigo(a1, 1);
        instance.adicionarArtigo(a2, 2);
        boolean result = instance.removerArtigo(a2);

        instance.setPeso();
        assertEquals(1, instance.getListaArtigos().size());
        assertEquals(3, instance.getPeso());
        assertEquals(expected, result);

    }

    /**
     * Test of removerArtigo False method, of class Encomenda.
     */
    @Test
    public void testRemoverArtigoFalse() {
        System.out.println("removerArtigo");
        Encomenda instance = new Encomenda();

        boolean expected = false;

        Artigo a1 = new Artigo(19, "test", 12, 5, 3);
        Artigo a2 = new Artigo(18, "test", 12, 5, 3);
        Artigo a3 = new Artigo(20, "test", 12, 5, 3);
        instance.adicionarArtigo(a1, 1);
        instance.adicionarArtigo(a2, 2);
        boolean result = instance.removerArtigo(a3);

        assertEquals(expected, result);

    }

    /**
     * Test of atualizarQuantidade method, of class Encomenda.
     */
    @Test
    public void testAtualizarQuantidade() {
        System.out.println("atualizarQuantidade");
        Encomenda instance = new Encomenda();

        boolean expected = true;

        Artigo a1 = new Artigo(19, "test", 12, 5, 3);
        Artigo a2 = new Artigo(18, "test", 12, 5, 3);
        instance.adicionarArtigo(a1, 1);
        instance.adicionarArtigo(a2, 2);
        boolean result = instance.atualizarQuantidade(a1, 4);

        instance.setPeso();
        assertEquals(2, instance.getListaArtigos().size());
        assertEquals(18, instance.getPeso());
        assertEquals(expected, result);

    }

    /**
     * Test of atualizarQuantidade False method, of class Encomenda.
     */
    @Test
    public void testAtualizarQuantidadeFalse() {
        System.out.println("atualizarQuantidade");
        Encomenda instance = new Encomenda();

        boolean expected = false;

        Artigo a1 = new Artigo(19, "test", 12, 5, 3);
        Artigo a2 = new Artigo(18, "test", 12, 5, 3);
        Artigo a3 = new Artigo(20, "test", 12, 5, 3);
        instance.adicionarArtigo(a1, 1);
        instance.adicionarArtigo(a2, 2);
        boolean result = instance.atualizarQuantidade(a3, 4);

        assertEquals(expected, result);

    }

    /**
     * Test of calcularCusto method, of class Encomenda.
     */
    @Test
    public void testCalcularCusto() {
        System.out.println("calcularCusto");
        Encomenda instance = new Encomenda();
        double expResult = 43.05;

        Artigo a1 = new Artigo(19, "test", 13, 5, 3);
        Artigo a2 = new Artigo(18, "test2", 14, 5, 3);
        instance.adicionarArtigo(a1, 1);
        instance.adicionarArtigo(a2, 2);
        double result = instance.calcularCusto();

        assertEquals(expResult, result);
    }

    /**
     * Test of custoArtigo method, of class Encomenda.
     */
    @Test
    public void testCustoArtigo() {
        System.out.println("custoArtigo");
        Artigo a = null;
        Encomenda instance = new Encomenda();
        Double expResult = 0.0;
        Double result = instance.custoArtigo(a);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of custoArtigo method, of class Encomenda.
     */
    @Test
    public void testCustoArtigoDois() {
        System.out.println("custoArtigo");
        Artigo a = new Artigo(19, "test", 13, 5, 3);
        Encomenda instance = new Encomenda();
        instance.adicionarArtigo(a, 1);
        Double expResult = 13.65;
        Double result = instance.custoArtigo(a);
        assertEquals(expResult, result);

    }

}
