/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.IOException;
import lapr.project.model.Artigo;
import java.util.logging.FileHandler;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import static lapr.project.utils.Constantes.DIRETORIO_CSV_OUTPUT;

/**
 *
 * @author rmarinho
 */
public final class Log {

    private static Logger logger = Logger.getLogger(Log.class.getName());

    public Log() {
        try {
            FileHandler fileHandler = new FileHandler(DIRETORIO_CSV_OUTPUT + "app.log", true);
            logger.addHandler(fileHandler);
            SimpleFormatter formato = new SimpleFormatter();
            fileHandler.setFormatter(formato);
        }catch(IOException e){
            Log.logError(e.toString());
        }
    }

    public static void logInfo(String mensagem) {
        logger.info(mensagem);
    }

    public static void logWarning(String mensagem) {
        logger.warning(mensagem);
    }

    public static void logError(String mensagem) {
        logger.log(SEVERE, mensagem);
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void printArtigo(Artigo a) {

        System.out.println("-----------------------");
        System.out.println("    -> ID: " + a.getIdArtigo());
        System.out.println("    -> Designação: " + a.getDesignacao());
        System.out.println("    -> Preco: " + (a.getPrecoUnitario() * (1.0 + a.getIva())) + "€");
    }

}
