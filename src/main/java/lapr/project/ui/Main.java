package lapr.project.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import lapr.project.controller.AcopoladorController;
import lapr.project.data.DataHandler;
import lapr.project.data.LerCSV;
import static lapr.project.utils.Constantes.*;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        
        Log log = new Log();
        
        Log.logInfo("A ler as propriedades do sistema...");

        // Ler propriedades da base de dados
        //============================================================
        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream("./src/main/resources/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);
        } catch (IOException e) {
            Log.logError(e.toString());
        }

        LerCSV lerCSV = new LerCSV();

        Log.logInfo("OK");
        Log.logInfo("A criar a base de dados e inserir os dados...");

        // Drop, Create e Insert
        //============================================================
        DataHandler dataHandler = new DataHandler();
        dataHandler.scriptRunner("./src/main/resources/Ficheiros/Sql/DROP, CREATE and INSERTS.sql");

        lerCSV.leituraFarmacias(FICHEIRO_FARMACIAS);
        lerCSV.leituraParques(FICHEIRO_PARQUES);
        lerCSV.leituraScooters(FICHEIRO_SCOOTERS);
        lerCSV.leituraEstafetas(FICHEIRO_ESTAFETAS);
        lerCSV.leituraClientes(FICHEIRO_CLIENTES);
        lerCSV.leituraProdutos(FICHEIRO_PRODUTOS);
        lerCSV.leituraDrones(FICHEIRO_DRONES);
        
        dataHandler.scriptRunner("./src/main/resources/Ficheiros/Sql/UPDATE.sql");

        Log.logInfo("OK");

        // Inicio
        //============================================================
        Log.logInfo("OK");
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                AcopoladorController ac = new AcopoladorController();
                ac.run();
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {

                Menu menu = new Menu();
                menu.run();
            }
        };
        thread1.start();
        thread2.start();

    }
}
