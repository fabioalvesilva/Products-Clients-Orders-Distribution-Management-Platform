/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lapr.project.controller.BaseController;
import lapr.project.controller.NotificacaoController;
import lapr.project.model.Artigo;
import lapr.project.model.Cliente;
import lapr.project.model.Drone;
import lapr.project.model.Farmacia;

import lapr.project.model.Scooter;
import lapr.project.ui.assessement.Facade;
import static lapr.project.utils.Constantes.*;

import lapr.project.controller.GraphController;
import lapr.project.model.Local;
import lapr.project.model.Rota;
import lapr.project.model.TipoPesoEnum;
import lapr.project.utils.Constantes;

/**
 *
 * @author Fábio Silva
 */
public class Menu implements Runnable {

    private BaseController baseController = new BaseController();

    private NotificacaoController notificacaoController = new NotificacaoController();

    private static final Scanner scanner = new Scanner(System.in);

    private static final String SEPARATOR
            = "//////////////////////////////////////////////////////////////////////";
    private static final String MARCA
            = "           RIDE SHARIN - GRUPO 11     ";
    private static final String LOGIN_MENU
            = "           LOGIN            ";
    private static final String REGISTO_MENU
            = "           REGISTO            ";
    private static final String REMOVER_MENU
            = "           REMOVER            ";
    private static final String ATUALIZAR_MENU
            = "           ATUALIZAR            ";
    private static final String ADMINISTRADOR_MENU
            = "           ADMINISTRADOR            ";
    private static final String CLIENTE_MENU
            = "           CLIENTE DASHBOARD            ";

    private static final String SELECIONAR_OPCAO
            = "Selecione uma das opções!";

    private static final String SEPARADOR_ENDERECO
            = "           ENDEREÇO            ";
    private static final String LISTA_FARMACIAS
            = "           LISTA DE FARMÁCIAS            ";

    @Override
    public void run() {
        menu();
    }

    public void menu() {

        Log.print(SEPARATOR);
        Log.print(MARCA);
        Log.print(SEPARATOR);
        Log.print(SELECIONAR_OPCAO);
        Log.print("1. Modo automático");
        Log.print("2. Modo manual");
        Log.print("0. Sair");

        int escolhaUtilizador = scanner.nextInt();

        switch (escolhaUtilizador) {
            case 1:
                // 1: Cenários
                cenario_T_1();
                cenario_A_1();
                cenario_T_2();
//                cenario_A_2();
//                cenario_T_3();
//                cenario_transferencia_produto();
                break;
            case 2:
                // 1: Login
                menuPrincipal();
                break;
            case 0:
                // 0: Sair
                System.exit(0);
                break;
            default:
                menu();
        }
    }

    private void menuPrincipal() {

        Log.print("1. Login");
        Log.print("2. Registar-me");
        Log.print("0. Sair");

        int escolhaUtilizador = scanner.nextInt();

        switch (escolhaUtilizador) {

            case 1:
                // 1: Login
                loginMenu();
                break;
            case 2:
                // 2: Registo de Cliente
                registoClienteMenu();
                break;
            case 0:
                // 0: Sair
                System.exit(0);
                break;
            default:
                menuPrincipal();
        }

    }

    private void loginMenu() {

        Log.print(LOGIN_MENU);

        // 1: Login
        Log.print("Introduza o seu email:");
        String email = scanner.next();
        Log.print("Password:");
        String password = scanner.next();

        if (baseController.loginAdmin(email, password)) {
            menuAdministrador();
        } else if (baseController.loginUtilizador(email, password)) {
            menuCliente(email);
        } else {
            Log.print("Não encontrado. Email ou password inválidos.");
        }

    }

    private void menuAdministrador() {

        Log.print(ADMINISTRADOR_MENU);
        Log.print(SELECIONAR_OPCAO);
        Log.print("1. Registar Nova Farmacia");
        Log.print("2. Registar Scooter");
        Log.print("3. Atualizar Scooter");
        Log.print("4. Remover Scooter");
        Log.print("5. Registar Drones");
        Log.print("6. Ver todas as Farmacias");
        Log.print("0. Voltar ao menu principal");

        int escolhaUtilizador = scanner.nextInt();
        Log.print("Aguarde...");

        switch (escolhaUtilizador) {
            case 1:
                // 1: Registar Farmacia
                registoFarmaciaMenu();
                menuAdministrador();
                break;
            case 2:
                // 2: Registar Scooter
                registoScooterMenu();
                menuAdministrador();
                break;
            case 3:
                // 1: Registar Farmacia
                atualizarScooterMenu();
                menuAdministrador();
                break;
            case 4:
                // 2: Registar Scooter
                removerScooterMenu();
                menuAdministrador();
                break;
            case 5:
                // Registar Drone
                registoDroneMenu();
                menuAdministrador();
                break;
            case 6:
                // Ver Farmacias
                verFarmacias();
                menuAdministrador();
                break;
            case 0:
                // 0: Voltar ao menu principal
                menu();
                break;
            default:
                menu();
        }

    }

    private void menuCliente(String email) {

        Log.print(CLIENTE_MENU);
        Log.print(SELECIONAR_OPCAO);
        Log.print("1. Produtos Farmácia");
        Log.print("0. Voltar ao menu principal");

        int escolhaUtilizador = scanner.nextInt();
        Log.print("Aguarde...");

        switch (escolhaUtilizador) {
            case 1:
                // 1: 
                int idFarmacia = baseController.getIdFarmaciaPorUtilizador(email);

                for (Artigo a : baseController.getAllArtigos(idFarmacia)) {
                    Log.printArtigo(a);
                }
                menu();
                break;
            case 0:
                // 0: Voltar ao menu principal
                menu();
                break;
            default:
                menu();
        }

    }

    private void registoClienteMenu() {
        try {

            Cliente c = new Cliente();

            Log.print(REGISTO_MENU);

            // Introduzir os dados
            Log.print("Nome:");
            c.setNome(scanner.next());
            Log.print("Email:");
            c.setEmail(scanner.next());
            Log.print("NIF:");
            c.setNif(scanner.nextInt());
            Log.print("Password:");
            c.setPassword(scanner.next());
            Log.print(SEPARADOR_ENDERECO);
            Log.print("Rua:");
            c.getEndereco().setRua(scanner.next());
            Log.print("Latitude:");
            c.getEndereco().setLatitude(scanner.nextDouble());
            Log.print("Longitude:");
            c.getEndereco().setLongitude(scanner.nextDouble());
            Log.print("Altitude:");
            c.getEndereco().setAltitude(scanner.nextDouble());
            Log.print("Número Cartão Crédito:");
            c.setCartaoCredito(scanner.nextInt());

            if (baseController.novoCliente(c)) {
                Log.print("Registo efetuado com sucesso! Iremos enviar um email de confirmação com os seus dados de acesso.");
                String mensagem = "\nOs seus dados de acesso são: "
                        + "\n-Email: " + c.getEmail()
                        + "\n-Password: " + c.getPassword();
                notificacaoController.notificacaoRegistoCliente(c.getEmail(), mensagem);
                menuPrincipal();
            } else {
                Log.print("Erro no registo!");
                menuPrincipal();
            }

        } catch (Exception ex) {
            Log.print(ex);
            Log.print(ex.getMessage());
        }

    }

    private void registoFarmaciaMenu() {

        try {

            Farmacia f = new Farmacia();

            Log.print(REGISTO_MENU);

            // Introduzir os dados
            Log.print("Nome:");
            String desc = scanner.nextLine();
            while (desc == null || desc.isEmpty()) {
                desc = scanner.nextLine();
            }
            f.setDescricao(desc);
            Log.print("NIF:");
            f.setNif(scanner.nextInt());
            Log.print(SEPARADOR_ENDERECO);
            Log.print("Rua:");
            String rua = scanner.nextLine();
            while (rua == null || rua.isEmpty()) {
                rua = scanner.nextLine();
            }
            f.getEndereco().setRua(rua);
            Log.print("Latitude:");
            f.getEndereco().setLatitude(scanner.nextDouble());
            Log.print("Longitude:");
            f.getEndereco().setLongitude(scanner.nextDouble());
            Log.print("Altitude:");
            f.getEndereco().setAltitude(scanner.nextDouble());

            Integer idFarmacia = baseController.novaFarmacia(f);
            f.setId(idFarmacia);
            f.getEndereco().setIdEndereco(baseController.getIdEnderecoPorFarmacia(idFarmacia));

            if (idFarmacia != null) {
                Log.print("Farmácia registada com sucesso.");
                menuAdministrador();
            } else {
                Log.print("Erro no registo!");
                menuAdministrador();
            }

        } catch (Exception ex) {
            Log.print(ex);
            Log.print(ex.getMessage());
        }

    }

    private void registoScooterMenu() {

        try {

            Scooter s = new Scooter();

            Log.print(REGISTO_MENU);

            // Introduzir os dados
            Log.print("Matricula:");
            s.setMatricula(scanner.next());

            if (baseController.adicionarScooter(s, 1)) {
                Log.print("Scooter registada com sucesso.");
            } else {
                Log.print("Erro no registo!");
            }

        } catch (Exception ex) {
            Log.print(ex);
            Log.print(ex.getMessage());
        }

    }

    private void registoDroneMenu() {

        try {

            Drone d = new Drone();

            Log.print(REGISTO_MENU);

            // Introduzir os dados
            Log.print("Número de Registo:");
            d.setNrRegisto(scanner.nextInt());
            Log.print("Id da Farmacia:");
            d.setIdFarmacia(scanner.nextInt());

            if (baseController.adicionarDrone(d)) {
                Log.print("Drone registado com sucesso.");
            } else {
                Log.print("Erro no registo!");
            }

        } catch (Exception ex) {
            Log.print(ex);
            Log.print(ex.getMessage());
        }

    }

    private void atualizarScooterMenu() {

        try {

            Log.print(ATUALIZAR_MENU);

            // Introduzir os dados
            Log.print("Matrícula:");
            String matricula = scanner.next();

            Log.print("Estado da Scooter:");
            String estadoScooter = scanner.next();

            if (baseController.atualizarScooter(matricula, estadoScooter)) {
                Log.print("Estado da Scooter atualizada com sucesso.");
            } else {
                Log.print("Erro na atualização do estado!");
            }

        } catch (Exception ex) {
            Log.print(ex);
            Log.print(ex.getMessage());
        }

    }

    private void removerScooterMenu() {

        try {

            Log.print(REMOVER_MENU);

            // Introduzir os dados
            Log.print("Matricula:");
            String matricula = scanner.next();

            if (baseController.removerScooter(matricula)) {
                Log.print("Scooter removida com sucesso.");
            } else {
                Log.print("Erro!");
            }

        } catch (Exception ex) {
            Log.print(ex);
            Log.print(ex.getMessage());
        }

    }

    private void verFarmacias() {

        try {

            Log.print(LISTA_FARMACIAS);
            List<Farmacia> farmacias = baseController.getAllFarmacias();

            for (Farmacia f : farmacias) {
                Log.print("Id: " + f.getId());
                Log.print("Nome: " + f.getDescricao());
                Log.print("NIF: " + f.getNif());
                Log.print(" ");
            }
        } catch (Exception ex) {
            Log.print(ex);
            Log.print(ex.getMessage());
        }
    }
    private void cenario_T_1() {

        GraphController graphController = new GraphController();
        graphController.carregarLocaisBD();

        Log.logInfo("////////////////////////////"
                + "\nA Iniciar o Cenário Terrestre 1");
        Facade facade = new Facade();
        //Norte Shopping
        Cliente clienteNorteShopping = facade.getClienteByNIF(123456543);

        /////////////////////////////////////////////////////////
        Log.logInfo("Iniciar a encomenda do cliente Norte Shopping...");

        Log.logInfo("Adicionar produtos ao carrinho...");

        // Adicionar os produtos ao carrinho.
        if (facade.adicionarArtigoCarrinho(1, 1)) {
            Log.logInfo("Adicionado uma unidade do produto 1 ao carrinho com sucesso.");

            Log.logInfo("A finalizar a encomenda...");

            if (facade.finalizarEncomenda(clienteNorteShopping.getNif())) {
                Log.logInfo("Encomenda finalizada com sucesso!");
            } else {
                Log.logWarning("Erro ao finalizar a encomenda!");
            }
        } else {
            Log.logWarning("Não foi possível adicionar o produto ao carrinho!");
        }

        Local farmaciaOrigem = graphController.getLocalPorNome("Farmacia Guifoes");
        Local local1 = graphController.getLocalPorNome("NorteShopping");
        
        
        //Rota com intermedios
        ArrayList<Local> intermedios = new ArrayList<>();
        intermedios.add(local1);
        
        
        Scooter scooter = new Scooter(6, "scooterMat", "Mod scooter", 1, 1000, 1, 100, 100, Constantes.PESOMEDIOSCOOTER, Constantes.AREAFRONTALMEDIASCOOTER);
        
        graphController.lerLigacoesDeFicheiro(CONEXOES_CENARIO_T_1);
        
        Log.logInfo("A calcular a rota DISTÂNCIA...");
        Rota rotaDistancia = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.DISTANCIA, scooter);
        Log.logInfo(rotaDistancia.toString());
        
        Log.logInfo("A calcular a rota TEMPO...");
        Rota rotaTempo = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.TEMPO, scooter);
        Log.logInfo(rotaTempo.toString());
        
        Log.logInfo("A calcular a rota ENERGIA...");
        Rota rotaEnergia = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.ENERGIA, scooter);
        Log.logInfo(rotaEnergia.toString());
    }
    
    private void cenario_A_1() {

        GraphController graphController = new GraphController();
        graphController.carregarLocaisBD();

        Log.logInfo("////////////////////////////"
                + "\nA Iniciar o Cenário Aereo 1");
        Facade facade = new Facade();
        //Norte Shopping
        Cliente clienteNorteShopping = facade.getClienteByNIF(123456543);

        /////////////////////////////////////////////////////////
        Log.logInfo("Iniciar a encomenda do cliente Norte Shopping...");

        Log.logInfo("Adicionar produtos ao carrinho...");

        // Adicionar os produtos ao carrinho.
        if (facade.adicionarArtigoCarrinho(1, 1)) {
            Log.logInfo("Adicionado uma unidade do produto 1 ao carrinho com sucesso.");

            Log.logInfo("A finalizar a encomenda...");

            if (facade.finalizarEncomenda(clienteNorteShopping.getNif())) {
                Log.logInfo("Encomenda finalizada com sucesso!");
            } else {
                Log.logWarning("Erro ao finalizar a encomenda!");
            }
        } else {
            Log.logWarning("Não foi possível adicionar o produto ao carrinho!");
        }

        Local farmaciaOrigem = graphController.getLocalPorNome("Farmacia Guifoes");
        Local local1 = graphController.getLocalPorNome("NorteShopping");
        
        //Rota com intermedios
        ArrayList<Local> intermedios = new ArrayList<>();
        intermedios.add(local1);
        
        Drone drone = new Drone(5, 1, 1234, "MOd Dron", 1, 2000.0, 80D, 1, 100D);
        
        graphController.lerLigacoesDeFicheiro(CONEXOES_CENARIO_A_1);
        
        Log.logWarning("A calcular a rota DISTÂNCIA...");
        Rota rotaDistancia = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.DISTANCIA, drone);
        Log.logInfo(rotaDistancia.toString());
        
        Log.logWarning("A calcular a rota TEMPO...");
        Rota rotaTempo = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.TEMPO, drone);
        Log.logInfo(rotaTempo.toString());
        
        Log.logWarning("A calcular a rota ENERGIA...");
        Rota rotaEnergia = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.ENERGIA, drone);
        Log.logInfo(rotaEnergia.toString());
    }
    
    private void cenario_T_2() {

        GraphController graphController = new GraphController();
        graphController.carregarLocaisBD();

        Log.logInfo("////////////////////////////"
                + "\nA Iniciar o Cenário Terrestre 2");
        Facade facade = new Facade();
        //Norte Shopping
        Cliente clienteNorteShopping = facade.getClienteByNIF(123456543);

        /////////////////////////////////////////////////////////
        Log.logInfo("Iniciar a encomenda do cliente Norte Shopping...");

        Log.logInfo("Adicionar produtos ao carrinho...");

        // Adicionar os produtos ao carrinho.
        if (facade.adicionarArtigoCarrinho(1, 1)) {
            Log.logInfo("Adicionado uma unidade do produto 1 ao carrinho com sucesso.");

            Log.logInfo("A finalizar a encomenda...");

            if (facade.finalizarEncomenda(clienteNorteShopping.getNif())) {
                Log.logInfo("Encomenda finalizada com sucesso!");
            } else {
                Log.logWarning("Erro ao finalizar a encomenda!");
            }
        } else {
            Log.logWarning("Não foi possível adicionar o produto ao carrinho!");
        }

        Local farmaciaOrigem = graphController.getLocalPorNome("Farmacia Guifoes");
        Local local1 = graphController.getLocalPorNome("NorteShopping");
        
        //Rota com intermedios
        ArrayList<Local> intermedios = new ArrayList<>();
        intermedios.add(local1);
        
        Scooter scooter = new Scooter(6, "scooterMat", "Mod scooter", 1, 1000, 1, 100, 100, Constantes.PESOMEDIOSCOOTER, Constantes.AREAFRONTALMEDIASCOOTER);
        
        graphController.lerLigacoesDeFicheiro(CONEXOES_CENARIO_T_2);
        
        Log.logInfo("A calcular a rota DISTÂNCIA...");
        Rota rotaDistancia = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.DISTANCIA, scooter);
        Log.logInfo(rotaDistancia.toString());
        
        Log.logInfo("A calcular a rota TEMPO...");
        Rota rotaTempo = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.TEMPO, scooter);
        Log.logInfo(rotaTempo.toString());
        
        Log.logInfo("A calcular a rota ENERGIA...");
        Rota rotaEnergia = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.ENERGIA, scooter);
        Log.logInfo(rotaEnergia.toString());
    }
    
    private void cenario_T_3() {

        GraphController graphController = new GraphController();
        graphController.carregarLocaisBD();

        Log.logInfo("////////////////////////////"
                + "\nA Iniciar o Cenário Terrestre 3");
        Facade facade = new Facade();
        //Norte Shopping
        Cliente clienteNorteShopping = facade.getClienteByNIF(123456543);

        /////////////////////////////////////////////////////////
        Log.logInfo("Iniciar a encomenda do cliente Norte Shopping...");

        Log.logInfo("Adicionar produtos ao carrinho...");

        // Adicionar os produtos ao carrinho.
        if (facade.adicionarArtigoCarrinho(1, 1)) {
            Log.logInfo("Adicionado uma unidade do produto 1 ao carrinho com sucesso.");

            Log.logInfo("A finalizar a encomenda...");

            if (facade.finalizarEncomenda(clienteNorteShopping.getNif())) {
                Log.logInfo("Encomenda finalizada com sucesso!");
            } else {
                Log.logWarning("Erro ao finalizar a encomenda!");
            }
        } else {
            Log.logWarning("Não foi possível adicionar o produto ao carrinho!");
        }

        Local farmaciaOrigem = graphController.getLocalPorNome("Farmacia Guifoes");
        Local local1 = graphController.getLocalPorNome("NorteShopping");
        
        //Rota com intermedios
        ArrayList<Local> intermedios = new ArrayList<>();
        intermedios.add(local1);
        
        Scooter scooter = new Scooter(6, "scooterMat", "Mod scooter", 1, 1000, 1, 100, 100, Constantes.PESOMEDIOSCOOTER, Constantes.AREAFRONTALMEDIASCOOTER);
        
        graphController.lerLigacoesDeFicheiro(CONEXOES_CENARIO_T_2);
        
        Log.logWarning("A calcular a rota DISTÂNCIA...");
        Rota rotaDistancia = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.DISTANCIA, scooter);
        Log.logInfo(rotaDistancia.toString());
        
        Log.logWarning("A calcular a rota TEMPO...");
        Rota rotaTempo = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.TEMPO, scooter);
        Log.logInfo(rotaTempo.toString());
        
        Log.logWarning("A calcular a rota ENERGIA...");
        Rota rotaEnergia = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.ENERGIA, scooter);
        Log.logInfo(rotaEnergia.toString());
    }
    
    private void cenario_A_2() {

        GraphController graphController = new GraphController();
        graphController.carregarLocaisBD();

        Log.logInfo("////////////////////////////"
                + "\nA Iniciar o Cenário Aereo 2");
        
        
        Facade facade = new Facade();
        //Norte Shopping
        Cliente clienteNorteShopping = facade.getClienteByNIF(123456543);

        /////////////////////////////////////////////////////////
        Log.logInfo("Iniciar a encomenda do cliente Norte Shopping...");

        Log.logInfo("Adicionar produtos ao carrinho...");

        // Adicionar os produtos ao carrinho.
        if (facade.adicionarArtigoCarrinho(1, 1)) {
            Log.logInfo("Adicionado uma unidade do produto 1 ao carrinho com sucesso.");

            Log.logInfo("A finalizar a encomenda...");

            if (facade.finalizarEncomenda(clienteNorteShopping.getNif())) {
                Log.logInfo("Encomenda finalizada com sucesso!");
            } else {
                Log.logWarning("Erro ao finalizar a encomenda!");
            }
        } else {
            Log.logWarning("Não foi possível adicionar o produto ao carrinho!");
        }

        Local farmaciaOrigem = graphController.getLocalPorNome("Farmacia Guifoes");
        Local local1 = graphController.getLocalPorNome("NorteShopping");
        
        //Rota com intermedios
        ArrayList<Local> intermedios = new ArrayList<>();
        intermedios.add(local1);
        
        Drone drone = new Drone(5, 1, 1234, "MOd Dron", 1, 2000.0, 80D, 1, 100D);
        
        graphController.lerLigacoesDeFicheiro(CONEXOES_CENARIO_A_2);
        
        Log.logWarning("A calcular a rota DISTÂNCIA...");
        Rota rotaDistancia = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.DISTANCIA, drone);
        Log.logInfo(rotaDistancia.toString());
        
        Log.logWarning("A calcular a rota TEMPO...");
        Rota rotaTempo = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.TEMPO, drone);
        Log.logInfo(rotaTempo.toString());
        
        Log.logWarning("A calcular a rota ENERGIA...");
        Rota rotaEnergia = graphController.getRota(farmaciaOrigem, farmaciaOrigem, intermedios, TipoPesoEnum.ENERGIA, drone);
        Log.logInfo(rotaEnergia.toString());
    }
    
    private void cenario_transferencia_produto(){
        
        GraphController graphController = new GraphController();
        graphController.carregarLocaisBD();
        
        graphController.lerLigacoesDeFicheiro(FICHEIRO_CONEXOES_TERRESTRE);
        
        Log.logInfo("////////////////////////////"
                + "\nCenário de transferência de produtos entre farmácias"
                + "\n"
                + "\n Farmacia um: Guinfões"
                + "\n Resultado Expectável:"
                + "\n  1. Listar podutos"
                + "\n  2. Fazer o pedido de transferências de produto à farmácia mais próxima."
                + "\n  3. A transferência é feita com sucesso e os dados são atualizados na base de dados.");
        
        Facade facade = new Facade();
        
        if(facade.loginAdmin(Constantes.ADMIN_EMAIL, Constantes.ADMINPW)){
            Log.logInfo("Login do Admin " + Constantes.ADMIN_EMAIL + " feito com sucesso.");
            Log.logInfo("A listar todos os produtos da farmacia guifões");
            
            Map<Artigo,Integer> listaArtigos = facade.listarProdutosStock(1);
            
            for (Map.Entry<Artigo, Integer> entry : listaArtigos.entrySet()){
                Log.logInfo("Artigo: " + entry.getKey().getDesignacao() 
                + "\nQuantidade: " + entry.getValue());
                
                if(entry.getValue() == 0){
                    if(facade.transferenciaProduto(1, entry.getKey().getIdArtigo(), 2)){
                        Log.logInfo("Transferência processada com sucesso!");
                    }
                }
            }
        }else{
            Log.logInfo("Login Inválido!");
        }
    }
}
