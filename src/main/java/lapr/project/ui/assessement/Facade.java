/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui.assessement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lapr.project.controller.BaseController;
import lapr.project.controller.CompraController;
import lapr.project.controller.FarmaciaController;
import lapr.project.controller.GestaoEntregasController;
import lapr.project.controller.GraphController;
import lapr.project.controller.NotificacaoController;
import lapr.project.controller.RotaController;
import lapr.project.data.LerCSV;
import lapr.project.model.Artigo;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;
import lapr.project.model.Entrega;
import lapr.project.model.Estafeta;
import lapr.project.model.Farmacia;
import lapr.project.model.Parque;
import lapr.project.model.Rota;
import lapr.project.model.Scooter;
import lapr.project.model.Veiculo;
import lapr.project.ui.Log;
import lapr.project.utils.Utils;

/**
 *
 * @author Fábio Silva
 */
public class Facade implements Servicos {

    private final LerCSV csvController;
    private final BaseController baseController;
    private final GraphController graphController;
    private final CompraController compraController;
    private final FarmaciaController farmaciaController;
    private final NotificacaoController notificacaController;
    private final GestaoEntregasController entregaController;
    private final RotaController rotaController;

    public Facade() {
        
        this.csvController = new LerCSV();
        this.baseController = new BaseController();
        this.graphController = new GraphController();
        this.farmaciaController = new FarmaciaController(this.graphController);
        this.notificacaController = new NotificacaoController();
        this.compraController = new CompraController(this.graphController);
        this.compraController.graphController = this.graphController;
        this.entregaController = new GestaoEntregasController ();
        this.rotaController = new RotaController(this.graphController);
    }
    
    @Override
    public boolean loginAdmin(String email, String password) {
        return this.baseController.loginAdmin(email, password);
    }
    
    @Override
    public boolean loginUtilizador(String email, String password) {
        return this.baseController.loginUtilizador(email, password);
    }

    @Override
    public List<Farmacia> adicionarFarmacias(String ficheiro) {
        List<Farmacia> farmacias = new ArrayList<>();
        try {
            farmacias = this.csvController.leituraFarmacias(ficheiro);
        } catch (IOException ex) {
            Log.logError(ex.toString());
        }
        return farmacias;
    }
    
    @Override
    public void adicionarProdutos(String ficheiro) {
        try {
            this.csvController.leituraProdutos(ficheiro);
        } catch (IOException ex) {
            Log.logError(ex.toString());
        }
    }

    @Override
    public List<Parque> adicionarParques(String ficheiro) {
        List<Parque> parques = new ArrayList<>();
        try {
            parques = this.csvController.leituraParques(ficheiro);
        } catch (IOException ex) {
            Log.logError(ex.toString());
        }
        return parques;
    }

    @Override
    public List<Scooter> adicionarScooters(String ficheiro) {
        List<Scooter> scooters = new ArrayList<>();
        try {
            scooters = this.csvController.leituraScooters(ficheiro);
        } catch (IOException ex) {
            Log.logError(ex.toString());
        }
        return scooters;
    }

    @Override
    public List<Estafeta> adicionarEstafetas(String ficheiro) {
        List<Estafeta> estafetas = new ArrayList<>();
        try {
            estafetas = this.csvController.leituraEstafetas(ficheiro);
        } catch (IOException ex) {
            Log.logError(ex.toString());
        }
        return estafetas;
    }

    @Override
    public List<Cliente> adicionarClientes(String ficheiro) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = this.csvController.leituraClientes(ficheiro);
        } catch (IOException ex) {
            Log.logError(ex.toString());
        }
        return clientes;
    }

    @Override
    public boolean inserirConexoes(String ficheiro) {
        this.graphController.lerLigacoesDeFicheiro(ficheiro);
        return true ;
    }
    
    @Override
    public boolean adicionarArtigoCarrinho(int idArtigo, int quantidade){
        return this.compraController.adicionarArtigoCarrinho(idArtigo, quantidade);
    }

    @Override
    public boolean finalizarEncomenda(Integer nifCliente) {
        return this.compraController.finalizarEncomenda(nifCliente);
    }

    @Override
    public List<Artigo> getListaProdutosFarmacia(Integer nifCliente) {
        return this.compraController.getListaProdutosFarmacia(nifCliente);
    }

    @Override
    public boolean getOcupacaoEstafeta(int idEstafeta) {
        return this.baseController.getOcupacaoEstafeta(idEstafeta);
    }

    @Override
    public List<Encomenda> getEncomendasPendentesByFarmacia(Integer idFarmacia) {
        return this.entregaController.getEncomendasPendentesByFarmacia(idFarmacia);
    }

    @Override
    public boolean adicionarEncomendas(List<Encomenda> listEncomendas) {
        return this.entregaController.adicionarEncomendas(listEncomendas);
    }

    @Override
    public boolean registarEntrega() {
        return this.entregaController.registarEntrega();
    }



    @Override
    public Entrega getEntrega() {
        return this.entregaController.getEntrega();
    }

    @Override
    public boolean transferenciaProduto(int idFarmaciaOrigem, int idProduto, int quantidade) {
        return this.farmaciaController.transferenciaProduto(idFarmaciaOrigem, idProduto, quantidade);
    }

    @Override
    public Map<Artigo, Integer> listarProdutosStock(int idFarmacia) {
        return this.farmaciaController.listarProdutosStock(idFarmacia);
    }
    
    @Override
    public Integer getScooterParaEntrega (Integer idFarmacia){
        return this.entregaController.getScooterParaEntrega(idFarmacia);
    } 
    
    @Override
    public Integer getDroneParaEntrega (Integer idFarmacia){
        return this.entregaController.getDroneParaEntrega(idFarmacia);
    }

    @Override
    public void setVeiculoParaEntrega(Veiculo v) {
        this.entregaController.setVeiculoParaEntrega(v);
    }

    @Override
    public boolean setIdEstafetaEntrega() {
        return this.entregaController.setIdEstafetaEntrega();
    }

    @Override
    public Cliente getClienteByNIF(Integer nif) {
        return this.baseController.getClienteByNIF(nif);
    }
    
    @Override
        public boolean lerLigacoesDeFicheiro(String ficheiro){
            return graphController.lerLigacoesDeFicheiro(ficheiro);
        }
    
    public void printResult(){
        graphController.printResult();
    }

    @Override
    public List<Artigo> getAllArtigos(Integer idFarmacia) {
        return this.baseController.getAllArtigos(idFarmacia);
    }
    
    
}
