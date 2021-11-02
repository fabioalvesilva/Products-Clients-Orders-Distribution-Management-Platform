/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Scooter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.ScooterAPI;
import lapr.project.data.UtilizadorAPI;
import static lapr.project.utils.Constantes.*;
import lapr.project.ui.Log;
import lapr.project.utils.Utils;

/**
 *
 * @author Groupo 11
 */
public class AcopoladorController implements Runnable {

    /**
     * Scooter API
     */
    ScooterAPI scooterAPI;

    /**
     * Utilizador API
     */
    UtilizadorAPI utilizadorAPI;

    /**
     * Controller de notificação
     */
    NotificacaoController notificacaoController;

    /**
     * Diretorio
     */
    Path diretorio;

    /**
     * Watch Service
     */
    WatchService watchService;

    /**
     * Ficheiros existentes
     */
    Set<String> ficheirosExistentes;

    /**
     * Mapa das scooters acopladas
     */
    Map<Scooter, String> scootersAcopoladas;

    /**
     * Scooter
     */
    Scooter scooter;

    /**
     * Inicialização do construtor vazio
     */
    public AcopoladorController() {

        try {
            this.scooterAPI = new ScooterAPI();
            this.utilizadorAPI = new UtilizadorAPI();
            this.diretorio = Paths.get(DIRETORIO_INPUT);
            this.watchService = diretorio.getFileSystem().newWatchService();
            this.diretorio.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            this.ficheirosExistentes = new HashSet<>();
            this.notificacaoController = new NotificacaoController();
        } catch (IOException e) {
            Log.logError(e.toString());
        }

    }

    /**
     * Verificação do acoplamento
     * @throws InterruptedException
     * @throws IOException
     */
    public void verificarAcopolacao() throws InterruptedException, IOException {

        WatchKey key;
        this.scootersAcopoladas = new HashMap<>();
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    //Atrasa a execução do método.
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Mudanças detetadas no diretório de input.");
                    acopolamento();
                }
            }
            key.reset();
        }
    }

    /**
     * Acoplamento
     * @throws IOException
     * @throws InterruptedException
     */
    private void acopolamento() throws IOException, InterruptedException {

        File pasta = new File(DIRETORIO_INPUT);
        
        //Get nome do ficheiro .data
        String ficheiroData = ficheiroData(DIRETORIO_INPUT);

        if (ficheiroData != null && ficheiroData.substring(0, 4).equalsIgnoreCase("lock")) {
            if (!ficheirosExistentes.contains(ficheiroData)) {
                ficheirosExistentes.add(ficheiroData);
                //Get ficheiro .flag
                if (Utils.verificarFicheiroFlag(ficheiroData, pasta)) {

                    if (escreverFicheiro(ficheiroData)) {
                        if (this.scootersAcopoladas.size()==0) {
                        TimeUnit.SECONDS.sleep(20);
                        String estimativa = Utils.lerFicheiro(DIRETORIO_ESTIMATIVA + "estimate_" + ficheiroData.substring(5));

                        Log.logInfo("Scooter bem acopolada! A enviar notificação...");
                         
                        notificacaoController.notificacaoAcopolamento(this.scooterAPI.getEmailByScooterId(scooter.getId()), scooter.getMatricula(), estimativa);
                        Log.logInfo("OK");
                        }else  {
                        int index=1;
                        for (Scooter scoot : scootersAcopoladas.keySet()) {
                            
                        TimeUnit.SECONDS.sleep(20);
                        String estimativa = Utils.lerFicheiro(DIRETORIO_ESTIMATIVA + "estimate_" + scootersAcopoladas.get(scoot).substring(5));

                        Log.logInfo("Múltiplas scooters acopoladas! A enviar notificação...");

                        notificacaoController.notificacaoAcopolamento(this.scooterAPI.getEmailByScooterId(scoot.getId()), scoot.getMatricula(), estimativa);
                        Log.logInfo("OK");
                            
                        
                        index++;
                            }

                        
                        }
                        
                    } else {
                        System.out.println("Erro ao ler Scooter.");
                    }
                    //Eliminar o ficheiro e guardar a info da scooter noutro ficheiro

                } else {
                    Log.logInfo("Scooter mal acopolada! A enviar notificação...");
                    notificacaoController.notificacaoMaulAcopolamento("1170912@isep.ipp.pt", scooter.getMatricula());
                    Log.logInfo("OK");

                }
            }
        }

    }

    /**
     * Ficheiro Data
     * @param diretorio diretorio do ficheiro
     * @return nome do ficheiro
     */
    private String ficheiroData(String diretorio) {

        File dir = new File(diretorio);
        File[] files = dir.listFiles();
        String ficheiroData = null;

        if (files == null || files.length == 0) {
            return null;
        }

        for (File file : files) {
            //Verifica se o ficheiro já existe na lista.
            if (!ficheirosExistentes.contains(file.getName())) {
                //Verifica se o ficheiro é uma pasta e se contém a extensão .flag
                if (!file.isDirectory() && !file.getName().contains(".flag")) {
                    if (file.getName().contains(".data")) {
                        ficheiroData = file.getName();
                    }
                }
            }
        }
        return ficheiroData;
    }

    /**
     * Escrever no ficheiro
     * @param nomeFicheiro nome do ficheiro
     * @return true or false
     * @throws IOException
     */
    public boolean escreverFicheiro(String nomeFicheiro) throws IOException {

        boolean resultado = false;

        String scooterId = Utils.lerFicheiro(DIRETORIO_INPUT + nomeFicheiro);

        //Ler os Ids das scooters do ficheiro que foi colocado no diretorio
        this.scooter = scooterAPI.getScooterById(Integer.parseInt(scooterId));
        if (this.scooter != null) {

            this.scooter.setEficienciaBateria(scooterAPI.getScooterBateriaEficiencia(this.scooter.getId()));
            Log.logInfo("A remover o ficheiro " + nomeFicheiro);
            if (Utils.removerFicheiro(DIRETORIO_INPUT + nomeFicheiro) && Utils.removerFicheiro(DIRETORIO_INPUT + nomeFicheiro + ".flag")) {

                Log.logInfo("OK");
                Log.logInfo("A criar novo ficheiro em " + DIRETORIO_OUTPUT + " com informação completa...");

                resultado = Utils.escreverFicheiroScooter((DIRETORIO_OUTPUT + nomeFicheiro), scooter, (this.scootersAcopoladas.size() + 1),scooterAPI.getCorrenteDebitada(scooter.getId()),scooterAPI.getScooterBateriaEficiencia(scooter.getId()));

                Log.logInfo("OK");
            } else {
                Log.logInfo("Erro ao remover o ficheiro!");
            }
            for (Scooter scoot : scootersAcopoladas.keySet()) {

                resultado = Utils.escreverFicheiroScooter((DIRETORIO_OUTPUT + scootersAcopoladas.get(scoot)), scoot, (this.scootersAcopoladas.size() + 1),scooterAPI.getCorrenteDebitada(scoot.getId()),scooterAPI.getScooterBateriaEficiencia(scoot.getId()));

            }

            this.scootersAcopoladas.put(scooter, nomeFicheiro);
        } else {
            System.out.println("Scooter inexistente na base de dados");
        }

        return resultado;
    }

    /**
     * Run 
     */
    @Override
    public void run() {

        try {
            verificarAcopolacao();
        } catch (InterruptedException ex) {
            Logger.getLogger(AcopoladorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AcopoladorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
