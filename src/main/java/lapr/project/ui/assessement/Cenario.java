/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui.assessement;

import java.util.List;
import lapr.project.controller.BaseController;
import lapr.project.model.Artigo;
import lapr.project.model.Cliente;
import lapr.project.model.Drone;
import lapr.project.model.Encomenda;
import lapr.project.model.Rota;
import lapr.project.model.Scooter;
import lapr.project.ui.Log;

/**
 *
 * @author Fábio Silva
 */
public class Cenario {

    private final Facade facade;
    private final BaseController baseController = new BaseController();

    private Cliente clienteCenario;

    public Cenario(String ficheiroConexoes) {

        this.facade = new Facade();
        
        Log.logInfo("A inserir as conexões...");
        facade.inserirConexoes(ficheiroConexoes);
        Log.logInfo("OK");
        this.clienteCenario = baseController.getClienteByNIF(555555555);
    }

    public void executarCenario() {
        
        Log.logInfo("///// CENÁRIO UM /////" +
                "\n" +
                "\n - Farmacia: Trindade" + 
                "\n - Cliente: Cais da Ribeira" +
                "\n - Resultado expectavel: " + 
                "\n     1: Cliente faz login com sucesso na plataforma." + 
                "\n     2: São listados os produtos da farmácia mais próxima." +
                "\n     3: Cliente adiciona ao carrinho produtos com sucesso, da farmacia mais próxima de si." + 
                "\n     4: Cliente finaliza a encomenda com sucesso." + 
                "\n     5: O processamento da encomenda é efetuado com sucesso e é atribuída a rota mais eficiente." +
                "\n");
        
        
        if (fazerEncomendaCliente(clienteCenario.getNif())) {
            executarEntregaEncomendas();
        }else{
            Log.logInfo("Não foi possível processar a encomenda do cliente...");
        }

    }
    
    private boolean fazerEncomendaCliente(Integer nifCliente) {

        boolean resultado = false;

        Log.logInfo("Iniciar a encomenda do cliente...");

        Log.logInfo("A listar os produtos da farmacia mais proxima do cliente...");
        List<Artigo> artigosFarmacia = this.facade.getListaProdutosFarmacia(nifCliente);

        for (Artigo a : artigosFarmacia) {
            Log.logInfo(a.toString());
        }

        Log.logInfo("Adicionar produtos ao carrinho...");

        // Adicionar os produtos ao carrinho.
        if (this.facade.adicionarArtigoCarrinho(1, 1)) {
            Log.logInfo("Adicionado uma unidade do produto 1 ao carrinho com sucesso.");

            Log.logInfo("A finalizar a encomenda...");

            if (this.facade.finalizarEncomenda(nifCliente)) {
                Log.logInfo("Encomenda finalizada com sucesso!");
                resultado = true;
            } else {
                Log.logWarning("Erro ao finalizar a encomenda!");
            }

        } else {
            Log.logWarning("Não foi possível adicionar o produto ao carrinho!");
        }

        return resultado;
    }

    private void executarEntregaEncomendas() {

        Log.logInfo("A processar a entrega da encomenda...");

        int idFarmacia = this.baseController.getIdFarmaciaPorUtilizador(clienteCenario.getEmail());

        Rota rotaScooter = null;
        Rota rotaDrone = null;

        Log.logInfo("A processar as encomendas pendentes da farmacia " + idFarmacia + " ...");
        List<Encomenda> encomendas = this.facade.getEncomendasPendentesByFarmacia(idFarmacia);

        if (this.facade.adicionarEncomendas(encomendas)) {

            Log.logInfo("A processar a rota a entrega...");

           // rotaScooter = this.facade.calcularRotaScooter(this.facade.getEntrega());
           // rotaDrone = this.facade.calcularRotaDrone(this.facade.getEntrega());

            if (rotaScooter != null && rotaDrone != null) {

                Log.logInfo(rotaScooter.toString());
                Log.logInfo(rotaDrone.toString());

                Drone drone;
                Scooter scooter;

                Log.logInfo("A escolher o melhor veículo para a entrega...");

                if (rotaScooter.getTotalEnergia() > rotaDrone.getTotalEnergia()) {

                    Integer idDroneDisponivel = this.facade.getDroneParaEntrega(idFarmacia);
                    drone = this.baseController.getDroneById(idDroneDisponivel);

                    Log.logInfo("A definir drone " + drone.getId() + " para a entrega...");
                    this.facade.setVeiculoParaEntrega(drone);
                    Log.logInfo("A registar a entrega...");
                    this.facade.registarEntrega();

                } else if (rotaScooter.getTotalEnergia() < rotaDrone.getTotalEnergia()) {

                    Integer idScooterDisponivel = this.facade.getScooterParaEntrega(idFarmacia);
                    scooter = this.baseController.getScooterById(idScooterDisponivel);

                    Log.logInfo("A definir scooter " + scooter.getId() + " para a entrega...");
                    this.facade.setVeiculoParaEntrega(scooter);
                    Log.logInfo("A definir o estafeta para a entrega...");
                    this.facade.setIdEstafetaEntrega();
                    Log.logInfo("A registar a entrega...");
                    this.facade.registarEntrega();

                } else {

                    Integer idDroneDisponivel = this.facade.getDroneParaEntrega(idFarmacia);
                    scooter = this.baseController.getScooterById(idFarmacia);
                    drone = this.baseController.getDroneById(idDroneDisponivel);

                    
                        Log.logInfo("A definir a scooter " + scooter.getId() + " para a entrega...");
                        if (this.facade.setIdEstafetaEntrega()) {
                            Log.logInfo("A definir estafeta para a entrega...");
                            Log.logInfo("A registar a entrega...");
                            this.facade.registarEntrega();
                        } else {
                            Log.logInfo("Não há estafetas disponíveis para a entrega!");
                            
                                Log.logInfo("A definir drone " + drone.getId() + " para a entrega...");
                                Log.logInfo("A registar a entrega...");
                                this.facade.registarEntrega();
                            
                                Log.logInfo("Não há veículos ou estafetas disponíveis para a entrega!");
                            
                        }
                    
                        Log.logInfo("Não há estafetas disponíveis para a entrega!");
                        
                            Log.logInfo("A definir drone " + drone.getId() + " para a entrega...");
                            this.facade.registarEntrega();
                       
                            Log.logInfo("Não há veículos disponíveis para a entrega!");
                        
                    
                }
            } else {
                Log.logInfo("Não é possível criar rota para a entrega...");
            }
        }
    }
}
