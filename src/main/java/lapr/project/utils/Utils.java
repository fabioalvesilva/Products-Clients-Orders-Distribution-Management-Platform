/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.ui.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.ScooterAPI;
import lapr.project.model.Cliente;
import lapr.project.model.Drone;
import lapr.project.model.Estafeta;
import lapr.project.model.Farmacia;
import lapr.project.model.Parque;
import lapr.project.model.Scooter;
import static lapr.project.utils.Constantes.*;

/**
 *
 * @author Grupo 11
 */
public class Utils {

    /**
     * Tipo UTF-8
     */
    private static final String UTF8 = "UTF-8";

    /**
     * Metodo que verifica se existe ficheiro .flag
     * @param nome nome ficheiro
     * @param pasta local da pasta
     * @return true or false
     */
    public static boolean verificarFicheiroFlag(String nome, File pasta) {

        String[] ficheiros;

        // Preencher o array com os nomes de todos os ficheiros existentes na pasta
        ficheiros = pasta.list();
        for (String ficheiro : ficheiros) {
            if (ficheiro.equalsIgnoreCase(nome + ".flag")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Escrever em ficheiro
     * @param nomeFicheiro nome do ficheiro
     * @param scooter scooter
     * @param numScooters numero de scooter
     * @param cargaDebitada carga debitada
     * @param eficiencia eficiencia
     * @return true or false
     * @throws IOException
     */
    public static boolean escreverFicheiroScooter(String nomeFicheiro, Scooter scooter, int numScooters,int cargaDebitada,int eficiencia) throws IOException {

        File ficheiroLock = new File(nomeFicheiro);

        try (
                FileOutputStream fos = new FileOutputStream(ficheiroLock);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos))
                ) {
            //Escreve no ficheiro a informação da scooter
            //  1 capacidade
            //  2 eficiencia da bateria
            //  3 carga restante
            //  4 corrente debitada 
            ScooterAPI scapi = new ScooterAPI();
            
            cargaDebitada = (int)(cargaDebitada / numScooters);
//            int cargaDebitada = 10 / numScooters;
            System.out.println(cargaDebitada);
            bw.write(scooter.getCapacidadeBateria()
                    + "\n" +  eficiencia
                    + "\n" + scooter.getCapacidadeRestante()
                    + "\n" + cargaDebitada);
            return true;
        }
    }

    /**
     * Escrever guia transporte e nota de encomenda
     * @param diretorio diretorio
     * @param mensagem mensagem
     */
    public static void escreverGuiaTransporteNotaEncomenda(String diretorio, String mensagem) {
        
        try (PrintWriter printWriter = new PrintWriter(diretorio, UTF8)) {

            printWriter.println();

            printWriter.print(mensagem);

        } catch (Exception e) {
            Log.logError(e.toString());
        }
    }

    /**
     * Ler ficheiro
     * @param nomeFicheiro nome do ficheiro
     * @return linha do ficheiro
     * @throws IOException
     */
    public static String lerFicheiro(String nomeFicheiro) throws IOException {

        String currentLine;
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFicheiro))) {
            currentLine = reader.readLine();
        }
        return currentLine;
    }

    /**
     * Remover ficheiro
     * @param nomeFicheiro nome do ficheiro
     * @return true or false
     */
    public static boolean removerFicheiro(String nomeFicheiro) {
        try {
            Files.delete(Paths.get(nomeFicheiro));
            return true;
        } catch (IOException e) {
            Log.logError(e.toString());
            return false;
        }
    }

    /**
     * Leitura de ficheiro CSV
     * @param caminhoFicheiro caminho do ficheiro
     * @return array de dados
     * @throws FileNotFoundException
     */
    public static ArrayList<String[]> lerCSV(String caminhoFicheiro) throws FileNotFoundException {
        ArrayList<String[]> lines = new ArrayList<>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoFicheiro))) {

            while ((line = br.readLine()) != null) {
                if (!line.startsWith("#")) {

                    line = line.replaceAll("\"", "");
                    String[] tempLine = line.trim().split(DELIMITADOR_PONTO_VIRGULA);
                    lines.add(tempLine);
                }

            }

        } catch (IOException e) {
            Log.logError(e.toString());
        }
        return lines;
    }
}