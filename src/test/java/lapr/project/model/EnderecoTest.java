/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Fábio Silva
 */
public class EnderecoTest {

    private Endereco endereco1;
    private Endereco endereco2;
    private Endereco endereco3;
    private Endereco endereco4;
    private Endereco endereco5;

    public EnderecoTest() {
    }

    @BeforeEach
    public void setUp() {

        endereco1 = new Endereco("Rua 19", 150.5, 200.4, 10.0);
        endereco2 = new Endereco("Rua 21", 155.5, 205.4, 10.0);
        endereco3 = new Endereco(1223, "Rua 23", 160.5, 210.4, 10.0);
        endereco4 = new Endereco("Rua 24", 165.5, 210.4);
        endereco5 = new Endereco(endereco4);
    }

    /**
     * Test of getRua method, of class Endereco.
     */
    @Test
    public void testGetRua() {
        System.out.println("getRua");
        String expResult = "Rua 19";
        assertEquals(expResult, endereco1.getRua());
        assertNotEquals(expResult, endereco2.getRua());
    }

    /**
     * Test of getLatitude method, of class Endereco.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Double expResult = 150.5;
        assertEquals(expResult, endereco1.getLatitude(), 0.0);
        assertNotEquals(expResult, endereco2.getLatitude(), 0.0);
    }

    /**
     * Test of getLongitude method, of class Endereco.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Double expResult = 200.4;
        assertEquals(expResult, endereco1.getLongitude(), 0.0);
        assertNotEquals(expResult, endereco2.getLongitude(), 0.0);
    }

    /**
     * Test of setRua method, of class Endereco.
     */
    @Test
    public void testSetRua() {
        System.out.println("setRua");
        String rua = "Rua 2";
        Endereco instance = new Endereco();
        instance.setRua(rua);
        String expResult = "Rua 2";
        assertEquals(expResult, instance.getRua());
    }

    /**
     * Test of setLatitude method, of class Endereco.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        Double latitude = 0.0;
        Endereco instance = new Endereco();
        instance.setLatitude(latitude);
        Double expResult = 0.0;
        assertEquals(expResult, instance.getLatitude(), 0.0);
    }

    /**
     * Test of setLongitude method, of class Endereco.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        Double longitude = 5.0;
        Endereco instance = new Endereco();
        instance.setLongitude(longitude);
        Double expResult = 5.0;
        assertEquals(expResult, instance.getLongitude(), 0.0);
    }

    /**
     * Test of toString method, of class Endereco.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertEquals(endereco1.toString(), endereco1.toString());
        assertNotEquals(endereco2.toString(), endereco1.toString());
    }

    /**
     * Test of getIdEndereco method, of class Endereco.
     */
    @Test
    public void testGetIdEndereco() {
        System.out.println("getIdEndereco");
        Endereco instance = new Endereco();
        Integer expResult = 0;
        Integer result = instance.getIdEndereco();
        assertEquals(expResult, result);

    }

    /**
     * Test of getAltitude method, of class Endereco.
     */
    @Test
    public void testGetAltitude() {
        System.out.println("getAltitude");
        Endereco instance = new Endereco();
        Double expResult = 0.0;
        Double result = instance.getAltitude();
        assertEquals(expResult, result);

    }

    /**
     * Test of setIdEndereco method, of class Endereco.
     */
    @Test
    public void testSetIdEndereco() {
        System.out.println("setIdEndereco");
        Integer idEndereco = null;
        Endereco instance = endereco3;
        instance.setIdEndereco(idEndereco);
        Integer expResult = null;
        Integer result = instance.getIdEndereco();
        assertEquals(expResult, result);

    }

    /**
     * Test of setAltitude method, of class Endereco.
     */
    @Test
    public void testSetAltitude() {
        System.out.println("setAltitude");
        Double altitude = null;
        Endereco instance = endereco5;
        instance.setAltitude(altitude);
        Double expResult = null;
        Double result = instance.getAltitude();
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode method, of class Endereco.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Endereco instance = new Endereco();
        int expResult = 11163;
        int expResult2 = (61 / (61 / 3)) + 10980;
        int expResult3 = (61 / (61 * 3)) + 10980;
        int expResult4 = (61 / (61 * 3)) - 10980;
        int expResult5 = (61 / (61 / 3)) - 10980;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        assertNotEquals(expResult2, result);
        assertNotEquals(expResult3, result);
        assertNotEquals(expResult4, result);
        assertNotEquals(expResult5, result);
    }

    /**
     * Test of equals method, of class Endereco.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Endereco instance = new Endereco();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
   
}
