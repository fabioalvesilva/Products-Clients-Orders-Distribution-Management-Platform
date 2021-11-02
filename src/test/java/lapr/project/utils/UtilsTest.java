/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.File;
import java.io.IOException;
import static java.lang.Math.PI;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Cliente;
import lapr.project.model.Endereco;
import lapr.project.model.Estafeta;
import lapr.project.model.Farmacia;
import lapr.project.model.Parque;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author rmarinho
 */
public class UtilsTest {

    public UtilsTest() {
    }

    @BeforeEach
    public void setUp() {
        Utils utils = new Utils();
    }
    /**
     * Test of verificarFicheiroFlag method, of class Utils.
     */
    @Test
    public void testVerificarFicheiroFlag() {
        System.out.println("verificarFicheiroFlag");
        String nome = "testeVerificarFicheiroFlag";
        File pasta = new File(System.getProperty("user.dir"));
        boolean expResult = true;
        boolean result = Utils.verificarFicheiroFlag(nome, pasta);
        assertEquals(expResult, result);
    }

    /**
     * Test of verificarFicheiroFlag method, of class Utils.
     */
    @Test
    public void testVerificarFicheiroFlagFalse() {
        System.out.println("verificarFicheiroFlagFalse");
        String nome = "esteFicheiroNaoExiste";
        File pasta = new File(System.getProperty("user.dir"));
        boolean expResult = false;
        boolean result = Utils.verificarFicheiroFlag(nome, pasta);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of escreverFicheiroScooter method, of class Utils.
     */
    @Test
    public void testEscreverFicheiroScooter() throws IOException {
        String dir_output_testes = "./src/main/resources/Ficheiros/OutputTest/";
        System.out.println("distance");
        Scooter testScooter = new Scooter(123, "12-ab-23", "modelo A", 1, 2, 3, 4, 5, 22.1, 22.1);
        testScooter.setEficienciaBateria(90);
        testScooter.setCapacidadeBateria(100);
        testScooter.setCapacidadeAtual(20);
        //simular classe Basecontroller

        String nomeFicheiro = "testeEscreverFicheiroScooter.lock";
        boolean expResult = true;
        boolean result = Utils.escreverFicheiroScooter(dir_output_testes + nomeFicheiro, testScooter, 1, 50, 90);
        assertEquals(expResult, result);
    }

    /**
     * Test of lerFicheiro method, of class Utils.
     */
    @Test
    public void testLerFicheiro() throws IOException {
        System.out.println("lerFicheiro");
        String nomeFicheiro = "testeLerFicheiro.txt";
        String expResult = "123";
        String result = Utils.lerFicheiro(nomeFicheiro);
        assertEquals(expResult, result);
    }

    /**
     * Test of removerFicheiro method, of class Utils.
     */
    @Test
    public void testRemoverFicheiro() throws IOException {
        String dir_output_testes = "./src/main/resources/Ficheiros/OutputTest/";
        System.out.println("removerFicheiro");
        Scooter testScooter = new Scooter(123, "12-ab-23", "modelo A", 1, 2, 3, 4, 5, 22.1, 22.1);
        String nomeFicheiro = "testeRemoverFicheiro.txt";

        System.out.println(testScooter.toString());

        Utils.escreverFicheiroScooter(dir_output_testes + nomeFicheiro, testScooter, 1, 50, 90);

        boolean expResult = true;
        boolean result = Utils.removerFicheiro(dir_output_testes + nomeFicheiro);
        assertEquals(expResult, result);
    }

    
       
    /**
     * Test of removerFicheiro method, of class Utils.
     */
    @Test
    public void testRemoverFicheiroFalse() throws IOException {
        System.out.println("removerFicheiroFalse");
        String nomeFicheiro = "esteFicheiroNaoExiste.txt";

        boolean expResult = false;
        boolean result = Utils.removerFicheiro(nomeFicheiro);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of removerFicheiro method, of class Utils.
     */
    @Test
    public void testEscreverGuiaTransporteNotaNoFicheiro() throws IOException {
        System.out.println("testEscreverGuiaTransporteNotaNoFicheiro");
        String diretorio = "./testFile/scooterTest.txt";
        String msg = "msg";
        boolean expResult = false;
        Utils.escreverGuiaTransporteNotaEncomenda(diretorio,msg);
        String diretorio2 = "./testFile/";
        Utils.escreverGuiaTransporteNotaEncomenda(diretorio2,msg);
        assertEquals(expResult, expResult);
    }
     @Test
    public void testLerCSV() throws IOException {
        System.out.println("testLerCsv");
        String caminho = "./testFile/test.csv";
        ArrayList<String[]> testArray;
        int expResult = 1;
        testArray = Utils.lerCSV(caminho);
        
        String caminho2 = "$";
        Utils.lerCSV(caminho2);
        assertEquals(expResult, testArray.size());
    }
}



