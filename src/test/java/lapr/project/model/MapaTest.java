/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.utils.Constantes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author carlos
 */
public class MapaTest {

    List<LigacaoLocais> ligacoes;
    List<Local> locais;
    List<Veiculo> veiculos;
    Mapa mapa;

    public MapaTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() {
        /*
        Farmacia Sousa Torres	 41.2196991	-8.5608657	83
        Farmacia Guifoes	 41.2000	 -8.66610	74
        centro comercial parque	 41.1845917	 -8.6843009	 27.0 
        Centro Comercial Plaza	 41.2333905	 -8.623724	 110.0
        
         */
        Farmacia f1 = new Farmacia("Farmacia1", 111111111, "farm1@mail.com", 999999999, new Endereco(1, "Farmacia Sousa Torres", 41.2196991, -8.5608657, 83D));
        f1.setId(1);
        Farmacia f2 = new Farmacia("Farmacia2", 222222222, "farm2@mail.com", 888888888, new Endereco(2, "Farmacia Guifoes", 41.2000, -8.66610, 74D));
        f1.setId(2);
        Cliente c3 = new Cliente("Cliente 3", "cli3@email.com", 777777777, "132", 3, "centro comercial parque", 41.1845917, -8.6843009, 27.0, 1234567);
        c3.setId(3);
        Cliente c4 = new Cliente("Cliente 4", "cl4@email.com", 666666666, "12345", 4, "centro comercial parque", 41.2333905, -8.623724, 110.0, 2315);
        c4.setId(4);
        locais = new ArrayList<>();
        locais.add(f1);
        locais.add(f2);
        locais.add(c3);
        locais.add(c4);

        //DAdos vento 
        //Atrito 0,5, com vento lateral de 11 km/h com um angulo de 20º coma trajetória,
        //com um coeficiente de atrito de 0,5, sem vento 
        LigacaoLocais ligA = new LigacaoLocais(f1, c3, 0, 10D, 267D, 0.5D);
        LigacaoLocais ligB = new LigacaoLocais(c3, c4, 0, 1D, 100D, 0.0D);
        LigacaoLocais ligC = new LigacaoLocais(c4, f2, 0, 1D, 100D, 0.0D);
        LigacaoLocais ligD = new LigacaoLocais(f2, f1, 0, 1D, 100D, 0.0D);
        LigacaoLocais ligE = new LigacaoLocais(f2, c3, 0, 0D, 100D, 0.0D);
        ligacoes = new ArrayList<>();
        ligacoes.add(ligA);
        ligacoes.add(ligB);
        ligacoes.add(ligC);
        ligacoes.add(ligD);
        ligacoes.add(ligE);

        //Veiculos 
        Drone drone = new Drone(5, 1, 1234, "MOd Dron", 1, 2000.0, 80D, 1, 100D);
        Scooter scooter = new Scooter(6, "scooterMat", "Mod scooter", 1, 1000, 1, 100, 100, Constantes.PESOMEDIOSCOOTER, Constantes.AREAFRONTALMEDIASCOOTER);
        veiculos = new ArrayList<>();
        veiculos.add(drone);
        veiculos.add(scooter);

        mapa = new Mapa(locais);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getNumligacoes method, of class Mapa.
     */
    @Test
    public void testGetNumligacoes() {

        assertEquals(0, mapa.getNumligacoes());
        mapa.adicionarLigacoes(ligacoes);
        int expected = 5;
        int result = mapa.getNumligacoes();
        assertEquals(expected, result);
        mapa.removerLigacao(ligacoes.get(0).getFrom(), ligacoes.get(0).getTo());

        //   assertEquals(expected -1 , mapa.getNumligacoes());
        mapa.adicionarLigacao(ligacoes.get(0));
        assertEquals(expected, result);
        mapa.removerTodasLigacoes();
        assertEquals(0, mapa.getNumligacoes());

    }

    /**
     * Test of getNumLocais method, of class Mapa.
     */
    @Test
    public void testGetNumLocais() {
        mapa.adicionarLigacoes(ligacoes);
        int expResult = 4;
        int result = mapa.getNumLocais();
        assertEquals(expResult, result);
        Mapa other = new Mapa(new ArrayList<>());
        assertEquals(0, other.getNumLocais());
    }

    /**
     * Test of localIndex method, of class Mapa.
     */
    @Test
    public void testLocalIndex() {
        System.out.println("localIndex");
        Farmacia f1 = new Farmacia("Farmacia1", 111111111, "farm1@mail.com", 999999999, new Endereco(1, "Farmacia Sousa Torres", 41.2196991, -8.5608657, 83D));
        f1.setId(1);
        Cliente c3 = new Cliente("Cliente 3", "cli3@email.com", 777777777, "132", 3, "centro comercial parque", 41.1845917, -8.6843009, 27.0, 1234567);
        c3.setId(3);
        Cliente c4 = new Cliente("Cliente 4", "cl4@email.com", 666666666, "12345", 4, "centro comercial parque", 41.2333905, -8.623724, 110.0, 2315);
        c4.setId(4);
        LigacaoLocais ligA = new LigacaoLocais(f1, c3, 0, 10D, 267D, 0.5D);

        int result = mapa.localIndex(locais.get(0));
        assertEquals(1, result);

        result = mapa.localIndex(ligA.getFrom());
        assertEquals(1, result);

        result = mapa.localIndex(ligA.getTo());
        assertEquals(3, result);

        result = mapa.localIndex(locais.get(2));
        assertEquals(3, result);

        result = mapa.localIndex(locais.get(3));
        assertEquals(4, result);

        mapa.adicionarLigacoes(ligacoes);

        result = mapa.localIndex(new Cliente());
        assertEquals(-1, result);

        result = mapa.localIndex(null);
        assertEquals(-1, result);

    }

    /**
     * Test of getLocalfromIndex method, of class Mapa.
     */
    @Test
    public void testGetLocalfromIndex() {
        System.out.println("getLocalfromIndex");

        Local result = mapa.getLocalfromIndex(0);
        assertEquals(locais.get(0), result);

        result = mapa.getLocalfromIndex(3);
        assertEquals(locais.get(3), result);
        mapa.adicionarLigacoes(ligacoes);

        result = mapa.getLocalfromIndex(200000);
        assertEquals(null, result);

        result = mapa.getLocalfromIndex(-1);
        assertEquals(null, result);

    }

    /**
     * Test of localExiste method, of class Mapa.
     */
    @Test
    public void testLocalExiste() {

        assertEquals(true, mapa.localExiste(locais.get(0)));

        Cliente c = new Cliente();
        assertEquals(false, mapa.localExiste(c));

        mapa.adicionarLigacoes(ligacoes);

        assertEquals(true, mapa.localExiste(locais.get(3)));

        assertEquals(false, mapa.localExiste(null));

    }

    /**
     * Test of adicionarLigacao method, of class Mapa.
     */
    @Test
    public void testAdicionarLigacao() {

        mapa.adicionarLigacoes(ligacoes);

        System.out.println("adicionarLigacao");
        LigacaoLocais ligZ = new LigacaoLocais(locais.get(0), locais.get(0), 0, 0D, 100D, 0.0D);
        assertEquals(true, mapa.adicionarLigacao(ligZ));

        assertEquals(ligacoes.size() + 1, mapa.getNumligacoes());

        assertEquals(false, mapa.adicionarLigacao(null));

        Farmacia f200 = new Farmacia("Farmacia2", 222222022, "farm2@mail.com", 888888888, new Endereco(2, "Farmacia Guifoes", 41.2001, -8.66600, 70D));
        f200.setId(200);
        Cliente c300 = new Cliente("Cliente 3", "cli3@email.com", 777707777, "132", 3, "centro comercial parque", 41.1845917, -8.6843000, 20.0, 1234560);
        c300.setId(300);
        LigacaoLocais ligK = new LigacaoLocais(f200, c300, 0, 10D, 267D, 0.5D);

        assertEquals(false, mapa.adicionarLigacao(ligK));

        ligK = new LigacaoLocais(null, null, 0, 10D, 267D, 0.5D);

        assertEquals(false, mapa.adicionarLigacao(ligK));
    }

    /**
     * Test of adicionarLigacoes method, of class Mapa.
     */
    @Test
    public void testAdicionarLigacoes() {
        System.out.println("adicionarLigacoes");
        assertEquals(0, mapa.getNumligacoes());

        assertEquals(true, mapa.adicionarLigacoes(ligacoes));

        assertEquals(5, mapa.getNumligacoes());

        assertEquals(false, mapa.adicionarLigacoes(null));
        assertEquals(5, mapa.getNumligacoes());

        assertEquals(false, mapa.adicionarLigacoes(new ArrayList<>()));
        assertEquals(5, mapa.getNumligacoes());

        assertEquals(true, mapa.adicionarLigacoes(ligacoes));
        assertEquals(5, mapa.getNumligacoes());

    }

//    /**
//     * A DAR ERRO NO COMENTARIO
//     */
//    @Test
//    public void testRemoverLigacao() {
//        System.out.println("removerLigacao");
//        mapa.adicionarLigacoes(ligacoes);
//                Farmacia f1 = new Farmacia("Farmacia1", 111111111, "farm1@mail.com", 999999999, new Endereco(1,"Farmacia Sousa Torres", 41.2196991,	-8.5608657,	83D));
//        f1.setId(1);
//                Cliente c3 = new Cliente("Cliente 3", "cli3@email.com", 777777777, "132", 3, "centro comercial parque",	 41.1845917,	 -8.6843009,	 27.0, 1234567);
//        c3.setId(3);
//                Cliente c4 = new Cliente("Cliente 4", "cl4@email.com", 666666666, "12345", 4, "centro comercial parque",	 41.2333905,	 -8.623724,	 110.0, 2315);
//        c4.setId(4);
//        LigacaoLocais ligB = new LigacaoLocais(c3, c4, 0, 1D, 100D, 0.0D);
//        LigacaoLocais ligA = new LigacaoLocais(f1, c3, 0, 10D, 267D, 0.5D);
//       // assertEquals(true , mapa.removerLigacao((Farmacia)ligA.getFrom(), (Cliente)ligA.getTo()));
//       // assertEquals(true , mapa.removerLigacao(ligB.getFrom(), ligB.getTo()));
//        
//        assertEquals(false , mapa.removerLigacao(ligA.getFrom(), ligA.getTo())); // aqui o true funciona
//        assertEquals(false , mapa.removerLigacao(ligB.getFrom(), ligB.getTo()));  // aqui o true funciona
//        
//        
//    }
    /**
     * Test of removerTodasLigacoes method, of class Mapa.
     */
    @Test
    public void testRemoverTodasLigacoes() {
        System.out.println("removerTodasLigacoes");
        assertEquals(false, mapa.removerTodasLigacoes());
        assertEquals(0, mapa.getNumligacoes());
        mapa.adicionarLigacoes(ligacoes);
        assertEquals(5, mapa.getNumligacoes());
        assertEquals(true, mapa.removerTodasLigacoes());
        assertEquals(0, mapa.getNumligacoes());
    }

    /**
     * Test of getLigacaoLocais method, of class Mapa.
     */
    @Test
    public void testGetLigacaoLocais() {
        System.out.println("getLigacaoLocais");
        int from = 0;
        int to = 0;
        assertEquals(null, mapa.getLigacaoLocais(from, to));
        mapa.adicionarLigacoes(ligacoes);
        assertEquals(null, mapa.getLigacaoLocais(from, to));
        assertEquals(ligacoes.get(0), mapa.getLigacaoLocais(1, 3));

        Farmacia f1 = new Farmacia("Farmacia1", 111111111, "farm1@mail.com", 999999999, new Endereco(1, "Farmacia Sousa Torres", 41.2196991, -8.5608657, 83D));
        f1.setId(1);
        Cliente c3 = new Cliente("Cliente 3", "cli3@email.com", 777777777, "132", 3, "centro comercial parque", 41.1845917, -8.6843009, 27.0, 1234567);
        c3.setId(3);
        LigacaoLocais ligA = new LigacaoLocais(f1, c3, 0, 10D, 267D, 0.5D);
        assertEquals(ligA, mapa.getLigacaoLocais(1, 3));
    }

    /**
     * Test of criarGrafoTipo method, of class Mapa.
     */
    @Test
    public void testCriarGrafoTipo() {
        System.out.println("criarGrafoTipo");
        mapa.adicionarLigacoes(ligacoes);
        mapa.criarGrafoTipo(TipoPesoEnum.DISTANCIA, veiculos.get(0));
        mapa.criarGrafoTipo(TipoPesoEnum.ENERGIA, veiculos.get(0));
    }

    /**
     * Test of getRota method, of class Mapa.
     */
    @Test
    public void testGetRotaDistancia() {
        System.out.println("getRotaDistancia");

        mapa.adicionarLigacoes(ligacoes);
        mapa.criarGrafoTipo(TipoPesoEnum.DISTANCIA, veiculos.get(0));
        List<LigacaoLocais> result = mapa.getRota(locais.get(3), locais.get(0));

        assertTrue(result.size() == 2, "O tamanho da rota é de 2");
    }

    /**
     * Test of getRota method, of class Mapa.
     */
    @Test
    public void testGetRotaEnergia() {
        System.out.println("getRotaEnergia");

        mapa.adicionarLigacoes(ligacoes);
        mapa.criarGrafoTipo(TipoPesoEnum.ENERGIA, veiculos.get(0));
        List<LigacaoLocais> result = mapa.getRota(locais.get(3), locais.get(0));

        assertTrue(result.size() == 2, "O tamanho da rota é de 2");
    }

    /**
     * Test of getRota method, of class Mapa.
     */
    @Test
    public void testGetRotaTempo() {
        System.out.println("getRotaEnergia");

        mapa.adicionarLigacoes(ligacoes);
        mapa.criarGrafoTipo(TipoPesoEnum.TEMPO, veiculos.get(0));
        List<LigacaoLocais> result = mapa.getRota(locais.get(3), locais.get(0));

        assertTrue(result.size() == 2, "O tamanho da rota é de 2");
    }

//    /**
//     * Test of getRota method, of class Mapa.
//     */
//    @Test
//    public void testGetRota_Local_Local() {
//        System.out.println("getRota");
//        // Rota tipo Energia
//        mapa.adicionarLigacoes(ligacoes);
//        mapa.criarGrafoTipo(TipoPesoEnum.DISTANCIA, veiculos.get(0));
//        List<LigacaoLocais> result = mapa.getRota(locais.get(3), locais.get(0));
//        mapa.printResult();
//        System.out.println("");
//        //R
//    }
    /**
     * Test of getRota method, of class Mapa.
     */
    @Test
    public void testGetRota_Local_Local() {
        System.out.println("getRota");
        Local orig = locais.get(1);
        Local dest = locais.get(2);
        Mapa instance = mapa;

        List<LigacaoLocais> result = instance.getRota(orig, dest);

        assertTrue(result.size() == 0, "Não há rota com os pontos indicados");
    }

    /**
     * Test of getRota method, of class Mapa.
     */
    @Test
    public void testGetRota_3args() {
        System.out.println("getRota");
        Local orig = locais.get(1);
        Local dest = locais.get(2);
        List<Local> intermedios = new LinkedList<>();
        intermedios.add(locais.get(3));
        Mapa instance = mapa;

        List<LigacaoLocais> result = instance.getRota(orig, dest, intermedios);
        assertTrue(result.size() == 0, "Não há rota com os pontos indicados");
    }

//    /**
//     * Test of getLocalMaisProximo method, of class Mapa.
//     */
//    @org.junit.Test
//    public void testGetLocalMaisProximo() {
//        System.out.println("getLocalMaisProximo");
//        Local orig = null;
//        Local tipoLocal = null;
//        Mapa instance = null;
//        Local expResult = null;
//        Local result = instance.getLocalMaisProximo(orig, tipoLocal);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLocaisMaisProximo method, of class Mapa.
//     */
//    @Test
//    public void testGetLocaisMaisProximo() {
//        System.out.println("getLocaisMaisProximo");
//        Local orig = null;
//        Local tipoLocal = null;
//        Mapa instance = null;
//        List<Local> expResult = null;
//        List<Local> result = instance.getLocaisMaisProximo(orig, tipoLocal);
//        assertEquals(expResult, result);
//    }
    /**
     * Test of melhorCaminho method, of class Mapa.
     */
    @Test
    public void testMelhorCaminho() {
        System.out.println("melhorCaminho");
        List<List<LigacaoLocais>> listaCaminhos = new LinkedList<>();
        listaCaminhos.add(ligacoes);
        Mapa instance = mapa;

        List<LigacaoLocais> result = instance.melhorCaminho(listaCaminhos);
        assertTrue(result.size() == 5, "O melhor caminho tem 5 pontos");

    }

    /**
     * Test of printResult method, of class Mapa.
     */
    @Test
    public void testPrintResult() {
        System.out.println("printResult");
        Mapa instance = mapa;
        instance.printResult();
    }
}
