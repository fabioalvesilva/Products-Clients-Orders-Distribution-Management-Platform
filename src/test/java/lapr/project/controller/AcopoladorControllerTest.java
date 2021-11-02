///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package lapr.project.controller;
//
//import com.sun.javafx.iio.common.ImageTools.createInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.FileSystem;
//import java.nio.file.Path;
//import java.nio.file.WatchService;
//import java.nio.file.spi.FileSystemProvider;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
///**
// *
// * @author Fábio Silva
// */
//public class AcopoladorControllerTest {
//
//    private WatchService watchService;
//    private Path diretorio;
//    public AcopoladorControllerTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() throws IOException {
//
//        // Set up mock watch service and path
//        watchService = mock(WatchService.class);
//        diretorio = mock(Path.class);
//        
//        // Need to also set up mocks for absolute parent path...
//        Path absolutePath = mock(Path.class);
//        Path parentPath = mock(Path.class);
//
//        // Mock the path's methods...
//        when(diretorio.toAbsolutePath()).thenReturn(absolutePath);
//        when(absolutePath.getParent()).thenReturn(parentPath);
//        
//        // Mock enough of the path so that it can load the test file.
//        // On the first load, the loaded data will be "[INITIAL DATA]", any subsequent call it will be "[UPDATED DATA]"
//        // (this is probably the smellyest bit of this test...)
//        InputStream initialInputStream = createInputStream("[INITIAL DATA]");
//        InputStream updatedInputStream = createInputStream("[UPDATED DATA]");
//        FileSystem fileSystem = mock(FileSystem.class);
//        FileSystemProvider fileSystemProvider = mock(FileSystemProvider.class);
//
//        when(diretorio.getFileSystem()).thenReturn(fileSystem);
//        when(fileSystem.provider()).thenReturn(fileSystemProvider);
//        when(fileSystemProvider.newInputStream(diretorio)).thenReturn(initialInputStream, updatedInputStream);
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of verificarAcopolacao method, of class AcopoladorController.
//     */
//    @Test
//    public void testVerificarAcopolacao() throws InterruptedException, IOException {
//        System.out.println("verificarAcopolacao");
//        AcopoladorController instance = new AcopoladorController();
//        instance.verificarAcopolacao();
//    }
//
//    /**
//     * Test of escreverFicheiro method, of class AcopoladorController.
//     */
//    @Test
//    public void testEscreverFicheiro() throws IOException {
//        System.out.println("escreverFicheiro");
//        String nomeFicheiro = "";
//        AcopoladorController instance = new AcopoladorController();
//        boolean expResult = false;
//        boolean result = instance.escreverFicheiro(nomeFicheiro);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of run method, of class AcopoladorController.
//     */
//    @Test
//    public void testRun() {
//        System.out.println("run");
//        AcopoladorController instance = new AcopoladorController();
//        instance.run();
//    }
//
//}
