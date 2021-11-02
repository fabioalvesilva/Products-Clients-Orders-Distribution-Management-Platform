/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Fábio Silva
 */
public class EntregaTest {

    private Entrega entrega1;
    private Entrega entrega2;
    private Entrega entrega3;
    private Encomenda encomenda1;
    private Encomenda encomenda2;
    private Scooter s1;
    private Scooter s2;
    private Drone d1;
    private Drone d2;

    public EntregaTest() {
    }

    @BeforeEach
    public void setUp() {
        s1 = new Scooter();
        s2 = new Scooter(2, "ZZ-12-ZZ", "DR300x", 1, 200, 1, 100, 90, 45.0, 0.6);
        d1 = new Drone();
        d2 = new Drone(2, 2, 3, "DR300", 2, 100.0, 80.0, 1, 0.9);
        entrega1 = new Entrega(1, s1, 1, 10, "01-02-2021", "01-02-2021", 500.0, 1);
        entrega2 = new Entrega(2, d2, 2, 10, "05-01-2021", "05-01-2021", 250.0, 2);
        entrega3 = new Entrega(entrega2);
        encomenda1 = new Encomenda(1, "01-02-2021", 123456789, 3, 10.0, 20.0, 10.0, 500);
        encomenda2 = new Encomenda(2, "05-01-2021", 123456789, 3, 10.0, 20.0, 10.0, 500);

    }

    /**
     * Test of getId method, of class Entrega.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 1;
        int expResult2 = 0;
        int result = entrega1.getId();
        
        assertEquals(expResult, result);
        assertNotEquals(expResult2, result);
    }

    /**
     * Test of getIdEstafeta method, of class Entrega.
     */
    @Test
    public void testGetIdEstafeta() {
        System.out.println("getIdEstafeta");
        int expResult = 1;
        int result = entrega1.getIdEstafeta();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTaxaEntrega method, of class Entrega.
     */
    @Test
    public void testGetTaxaEntrega() {
        System.out.println("getTaxaEntrega");
        double expResult = 10d;
        double result = entrega1.getTaxaEntrega();
        assertEquals(expResult, result);
        assertEquals(expResult, entrega2.getTaxaEntrega());
    }

    /**
     * Test of getDataInicio method, of class Entrega.
     */
    @Test
    public void testGetDataInicio() {
        System.out.println("getDataInicio");
        String expResult = "01-02-2021";
        String result = entrega1.getDataInicio();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDataFim method, of class Entrega.
     */
    @Test
    public void testGetDataFim() {
        System.out.println("getDataFim");
        String expResult = "01-02-2021";
        String result = entrega1.getDataFim();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPesoTotalCarga method, of class Entrega.
     */
    @Test
    public void testGetPesoTotalCarga() {
        System.out.println("getPesoTotalCarga");
        Double expResult = 500.0;
        Double result = entrega1.getPesoTotalCarga();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetEncomendas method, of class Entrega.
     */
    @Test
    public void testGetSetEncomendas() {
        System.out.println("getSetEncomendas");
        Set<Encomenda> expResult = new HashSet<>();
        expResult.add(encomenda1);
        expResult.add(encomenda2);
        entrega1.setSetEncomendas(expResult);
        assertEquals(expResult, entrega1.getSetEncomendas());
        assertNotEquals(expResult, entrega2.getSetEncomendas());
    }

    /**
     * Test of getVeiculo method, of class Entrega.
     */
    @Test
    public void testGetVeiculo() {
        System.out.println("getVeiculo");
        Entrega instance = new Entrega();
        Veiculo expResult = null;
        Veiculo result = instance.getVeiculo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdFarmacia method, of class Entrega.
     */
    @Test
    public void testGetIdFarmacia() {
        System.out.println("getIdFarmacia");
        Entrega instance = new Entrega();
        Integer expResult = null;
        Integer result = instance.getIdFarmacia();
        assertEquals(expResult, result);

    }

    /**
     * Test of setId method, of class Entrega.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Entrega instance = new Entrega();
        instance.setId(id);
        int expResult = 0;
        assertEquals(expResult, instance.getId());
    }

    /**
     * Test of setScooter method, of class Entrega.
     */
    @Test
    public void testSetVeiculo() {
        System.out.println("setVeiculo");
        Scooter idScooter = s2;
        Entrega instance = entrega1;
        instance.setVeiculo(idScooter);
        Scooter expResult = s2;
        assertEquals(expResult, instance.getVeiculo());
    }

    /**
     * Test of setIdEstafeta method, of class Entrega.
     */
    @Test
    public void testSetIdEstafeta() {
        System.out.println("setIdEstafeta");
        int idEstafeta = 1;
        Entrega instance = new Entrega();
        instance.setIdEstafeta(idEstafeta);
        int expResult = 1;
        assertEquals(expResult, instance.getIdEstafeta());

    }

    /**
     * Test of setTaxaEntrega method, of class Entrega.
     */
    @Test
    public void testSetTaxaEntrega() {
        System.out.println("setTaxaEntrega");
        int taxaEntrega = 20;
        Entrega instance = new Entrega();
        instance.setTaxaEntrega(taxaEntrega);
        int expResult = 20;
        assertEquals(expResult, instance.getTaxaEntrega());
    }

    /**
     * Test of setDataInicio method, of class Entrega.
     */
    @Test
    public void testSetDataInicio() {
        System.out.println("setDataInicio");
        String dataInicio = "16-12-2020";
        Entrega instance = new Entrega();
        instance.setDataInicio(dataInicio);
        String expResult = "16-12-2020";
        assertEquals(expResult, instance.getDataInicio());
    }

    /**
     * Test of setDataFim method, of class Entrega.
     */
    @Test
    public void testSetDataFim() {
        System.out.println("setDataFim");
        String dataFim = "17-12-2020";
        Entrega instance = new Entrega();
        instance.setDataFim(dataFim);
        String expResult = "17-12-2020";
        assertEquals(expResult, instance.getDataFim());
    }

    /**
     * Test of setPesoTotalCarga method, of class Entrega.
     */
    @Test
    public void testSetPesoTotalCarga() {
        System.out.println("setPesoTotalCarga");
        Double pesoTotalCarga = 100.0;
        Entrega instance = new Entrega();
        instance.setPesoTotalCarga(pesoTotalCarga);
        Double expResult = 100.0;
        assertEquals(expResult, instance.getPesoTotalCarga());
    }

    /**
     * Test of setSetEncomendas method, of class Entrega.
     */
    @Test
    public void testSetSetEncomendas() {
        System.out.println("setSetEncomendas");
        Set<Encomenda> setEncomendas = new HashSet<>();
        setEncomendas.add(encomenda1);
        setEncomendas.add(encomenda2);
        Entrega instance = new Entrega();
        instance.setSetEncomendas(setEncomendas);
        Set<Encomenda> expResult = setEncomendas;
        assertEquals(expResult, instance.getSetEncomendas());
    }

    /**
     * Test of setIdFarmacia method, of class Entrega.
     */
    @Test
    public void testSetIdFarmacia() {
        System.out.println("setIdFarmacia");
        Integer idFarmacia = null;
        Entrega instance = new Entrega();
        instance.setIdFarmacia(idFarmacia);

    }

    /**
     * Test of toString method, of class Entrega.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertEquals(entrega1.toString(), entrega1.toString());
        assertEquals(entrega2.toString(), entrega2.toString());
        assertNotEquals(entrega1.toString(), entrega2.toString());
    }

    /**
     * Test of adicionarEncomenda method, of class Entrega.
     */
    @Test
    public void testAdicionarEncomenda() {
        System.out.println("adicionarEncomenda");
        int idEncomenda = 1;
        double pesoEncomenda = 1.0;
        String rua = "123";
        double latitude = 1.0;
        double longitude = -10.0;
        double altitude = 10.0;
        Endereco e1 = new Endereco(5, rua, latitude, longitude, altitude);
        Entrega instance = new Entrega();
        boolean expResult = true;
        boolean result = instance.adicionarEncomenda(idEncomenda, pesoEncomenda, e1.getIdEndereco(), e1.getRua(), e1.getLatitude(), e1.getLongitude(), e1.getAltitude());
        assertEquals(expResult, result);

    }

    /**
     * Test of adicionarEncomenda method, of class Entrega.
     */
    @Test
    public void testAdicionarEncomendaFalse() {
        System.out.println("adicionarEncomendaFalse");
        int idEncomenda = 1;
        double pesoEncomenda = 100000000000.0;
        String rua = "123";
        double latitude = 1.0;
        double longitude = -10.0;
        double altitude = 10.0;
        Endereco e1 = new Endereco(5, rua, latitude, longitude, altitude);
        Entrega instance = new Entrega();
        boolean expResult = false;
        boolean result = instance.adicionarEncomenda(idEncomenda, pesoEncomenda, e1.getIdEndereco(), rua, latitude, longitude, altitude);
        assertEquals(expResult, result);

    }

    /**
     * Test of removerEncomenda method, of class Entrega.
     */
    @Test
    public void testRemoverEncomenda() {
        System.out.println("removerEncomenda");
        Integer idEncomenda = null;
        Entrega instance = new Entrega();
        boolean expResult = false;
        boolean result = instance.removerEncomenda(idEncomenda);
        assertEquals(expResult, result);

    }

    /**
     * Test of removerEncomenda method, of class Entrega.
     */
    @Test
    public void testRemoverEncomendaTrue() {
        System.out.println("removerEncomendaTrue");
        Entrega instance = new Entrega();

        Encomenda e = new Encomenda(10000, 20, new Endereco("Rua Teste", 10.20, -10.20, 10.0));
        Encomenda e1 = new Encomenda(10001, 20, new Endereco("Rua Teste", 10.20, -10.20, 10.0));
        Set<Encomenda> setEncom = new HashSet<>();
        this.entrega1.setSetEncomendas(setEncom);
        this.entrega1.getSetEncomendas().add(e);
        this.entrega1.getSetEncomendas().add(e1);
        boolean expResult = true;
        boolean result = entrega1.removerEncomenda(10000);
        //boolean result = entrega1.getSetEncomendas().remove(e);
        assertEquals(expResult, result);

    }

}
