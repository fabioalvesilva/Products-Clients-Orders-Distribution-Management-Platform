/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

/**
 *
 * @author grupo 11
 */
public class Constantes {

    /**
     * email default
     */
    public static final String EMAILDEFAULT = "rmarinho.pt@gmail.com";
    
    // Peso Max de Drone e Scooter
    //============================================================

    /**
     * taxa de entrega default
     */
    public static final double TAXAENTREGADEFAULT = 3;

    /**
     * peso maxima da encomenda default
     */
    public static final double PESOMAXENCOMENDADEFAULT = 4.9;// kg

    /**
     * peso maximo da entrega por default
     */
    public static final double PESOMAXENTREGADEFAULT = 200; //kg

    /**
     * peso maximo da entrega do drone
     */
    public static final double PESOMAXENTREGADRONE = 4.9; //kg

    /**
     * peso maximo da entrega do scooter
     */
    public static final double PESOMAXENTREGASCOOTER = 70.0; //kg

    /**
     * peso medio do drone
     */
    public static final double PESOMEDIODRONE = 3.3; // em kg

    /**
     * peso medio da scooter
     */
    public static final double PESOMEDIOSCOOTER = 50.0; // em kg

    /**
     * peso medio do estafeta
     */
    public static final double PESOMEDIOESTAFETA = 80.0; // em kg

    /**
     * area frontal media do scooter
     */
    public static final double AREAFRONTALMEDIASCOOTER = 0.3; //m²

    /**
     * area frontal media do drone
     */
    public static final double AREAFRONTALMEDIADRONE = 0.1212; // m², assumindo o Hover Power de 1000W para 6.8kg de massa transportada

    /**
     * delimitador por ponto e virgula
     */
    public static final String DELIMITADOR_PONTO_VIRGULA = ";";

    /**
     * delimitador por virgla
     */
    public static final String DELIMITADOR_VIRGULA = ",";
    
    
    

    // Credenciais de Login do Admin
    //============================================================

    /**
     * email de administrador por defeito
     */
    public static final String ADMIN_EMAIL = "admin@farmacia.com";

    /**
     * password do administrador
     */
    public static final String ADMINPW = "123";
    
    
    
    

    // Localização dos ficheiros
    //============================================================

    /**
     * diretorio do ficheiro CSV de output
     */
    
    public static final String DIRETORIO_CSV_OUTPUT = "./src/main/resources/Ficheiros/Csv/Output/";

    /**
     * diretorio do ficheiro de input
     */
    public static final String DIRETORIO_INPUT = "./src/main/resources/Ficheiros/Input/";

    /**
     * diretorio do ficheiro de output
     */
    public static final String DIRETORIO_OUTPUT = "./src/main/resources/Ficheiros/Output/";
    
    /**
     * diretorio do ficheiro de estimativa
     */
    public static final String DIRETORIO_ESTIMATIVA = "./src/main/resources/Ficheiros/Input/";
    
    /**
     * Ficheiro CSV com farmacias
     */
    public static final String FICHEIRO_FARMACIAS = "./src/main/resources/Ficheiros/Csv/Input/farmacias.csv";

    /**
     * Ficheiro CSV com estafetas
     */
    public static final String FICHEIRO_ESTAFETAS = "./src/main/resources/Ficheiros/Csv/Input/estafetas.csv";

    /**
     * Ficheiro CSV com clientes
     */
    public static final String FICHEIRO_CLIENTES = "./src/main/resources/Ficheiros/Csv/Input/clientes.csv";

    /**
     * Ficheiro CSV com parques
     */
    public static final String FICHEIRO_PARQUES = "./src/main/resources/Ficheiros/Csv/Input/parques.csv";

    /**
     * Ficheiro CSV com scooters
     */
    public static final String FICHEIRO_SCOOTERS = "./src/main/resources/Ficheiros/Csv/Input/escooters.csv"; 

    /**
     * Ficheiro CSV com produtos
     */
    public static final String FICHEIRO_PRODUTOS = "./src/main/resources/Ficheiros/Csv/Input/produtos.csv";

    /**
     * Ficheiro CSV com drones
     */
    public static final String FICHEIRO_DRONES = "./src/main/resources/Ficheiros/Csv/Input/drones.csv";
    
    
    
    
    
    
    // Ficheiro Conexões dos Cenários Terrestres
    //============================================================

    /**
     * Ficheiro com as conexoes ao cenario 1
     */
    public static final String CONEXOES_CENARIO_T_1 = "./src/main/resources/Ficheiros/Csv/Input/cenarios_conexoes/conexoes_cenario_t_1.csv";
    public static final String CONEXOES_CENARIO_A_1 = "./src/main/resources/Ficheiros/Csv/Input/cenarios_conexoes/conexoes_cenario_a_1.csv";
    
    /**
     * Ficheiro com as conexoes ao cenario 2
     */
    public static final String CONEXOES_CENARIO_T_2 = "./src/main/resources/Ficheiros/Csv/Input/cenarios_conexoes/conexoes_cenario_t_2.csv";
    public static final String CONEXOES_CENARIO_A_2 = "./src/main/resources/Ficheiros/Csv/Input/cenarios_conexoes/conexoes_cenario_a_2.csv";
   
    


// Ficheiro Ligaçoes Terrestre
    //============================================================

    /**
     * Ficheiro de conexoes terrestres
     */
    
    public static final String FICHEIRO_CONEXOES_TERRESTRE = "./src/main/resources/Ficheiros/Csv/Input/ConexoesTerrestre.csv";
    
    // Ficheiro Ligaçoes Aereo
    //============================================================

    /**
     * Ficheiro de conexoes aéreas
     */
     
    public static final String FICHEIRO_CONEXOES_AEREO = "./src/main/resources/Ficheiros/Csv/Input/ConexoesAereo.csv";
    



    
    
    // Cálculos de energia, tempo e distancias
    //============================================================

    /**
     * Aceleração gravitacional médio da Terra
     */
    public static final double G = 9.81; //aceleração gravitacional média da Terra m/s2

    /**
     * Velocidade media da scooter
     */
    public static final double VELOCIDADEMEDSCOOTER = 30.0; // em km/h

    /**
     * Velocidade media do drone
     */
    public static final double VELOCIDADEMEDDRONE = 16.0; // em m/s

    /**
     * Velocidade media do drone em subida e descida
     */
    public static final double VELOCMEDDRONESUBIDADESC = 6.0; // em m/s

    /**
     * coeficiente de resistencia
     */
    public static final double COEFICENTERESISTENCIAAR = 1.1; // drag da scooter com estafeta

    /**
     * dendidade do ar
     */
    public static final double DENSIDADEAR = 1.225; // kg/m^3 ao nível do mar (dentro das cidades é desprezável)

    /**
     * valor médido da coeficiente de resistencia
     */
    public static final double RRCDEFEITO = 0.5; // valor médido da coeficiente de resistencia de rolamento que assumimos constante 

    /**
     * velocidade do vento por defeito
     */
    public static final double VELOCVENTODEFEITO = 0.0; // assumimos por defeito, que a velociadade do vento é 0.0 km/h;

    /**
     * eficiencia de transferência de potência
     */
    public static final double EFICIENCIATRANSFPOTENC = 0.5; // assumimos uma eficiencia de transferência de potência média igual a 0.5

    /**
     * consumo de potencia
     */
    public static final double CONSUMOPOTENCIA = 100; //em W, assumindo que é equivalente a um bom computador portátil, como o artigo sugerido indicava

    /**
     * Drag
     */
    public static final double LIFTDRAGRATIO = 3; // tendo por base um mau desempenho, como o artigo sugerido indicava, já que o dos helicópeteros é 4 

    /**
     * Valor para erro
     */
    public static final double ERRO = Double.POSITIVE_INFINITY;
    
    
    
    
    
        // Constantes Para Grafo
    //============================================================    
    
    /**
     * raio maximo para mapa
     */
    public static final double RAIOMAXMAPA = 10; //(em Kms) raio max construcao do Mapa apartir Local Orig
    
}
