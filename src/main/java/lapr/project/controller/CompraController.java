/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import java.util.Map;
import lapr.project.model.Artigo;
import lapr.project.model.Cliente;
import lapr.project.model.Encomenda;

/**
 *
 * @author Groupo 11
 */
public class CompraController {

    /**
     * Encomenda
     */
    Encomenda encomenda = new Encomenda();  

    /**
     * Controller de grafos
     */
    public GraphController graphController = new GraphController();

    /**
     * Base controller
     */
    BaseController baseController = new BaseController();

    /**
     * controller de notificações
     */
    NotificacaoController notificacaoController = new NotificacaoController();

    /**
     * controller de fatura
     */
    FaturaController faturaController = new FaturaController();
    
    /**
     * inicializa o construtor vazio
     */
    public CompraController() {
    }
    
    /**
     * Inicializa o construtor completo
     * @param graphContoller
     */
    public CompraController(GraphController graphContoller){
        this.graphController = graphController;
    }

    /**
     * Obter lista de produtos para farmacia
     * @param nifCliente nif do cliente
     * @return lista de artigos por farmacia
     */
    public List<Artigo> getListaProdutosFarmacia(Integer nifCliente) {
        
        Cliente cliente = baseController.getClienteByNIF(nifCliente);
        int idFarmacia = graphController.getidFarmaciaMaisProximaCliente(cliente);
        List<Artigo> listaArtigos = baseController.getAllArtigos(idFarmacia);
        encomenda = new Encomenda();
        encomenda.setNifCliente(nifCliente);
        return listaArtigos;//Conexao BD Lista PRodutos FArmacia
    }

    /**
     * Lista de artigos por encomendas
     * @return mapa com o artigos das encomendas
     */
    public Map<Artigo, Integer> getListaArtigosCompra() {
        return encomenda.getListaArtigos();
    }

    /**
     * Adicionar artigo ao carrinho 
     * @param idArtigo id do artigo 
     * @param quantidade quantidade do artigo a adicionar
     * @return True or false
     */
    public boolean adicionarArtigoCarrinho(int idArtigo, int quantidade) {
        int idFarmacia = 1;
        if ((idArtigo >= 1 && idArtigo < Integer.MAX_VALUE) && (quantidade >= 1 && quantidade < Integer.MAX_VALUE)) {
            if (getStockArtigo(1, idArtigo) >= quantidade) {//add ao carrinho
                Artigo a = baseController.getArtigoById(idFarmacia, idArtigo);
                if (a != null) {
                    return this.encomenda.adicionarArtigo(a, quantidade);
                }
            }
        }
        return false;
    }

    /**
     * Atualizar os artigos do carrinho
     * @param idArtigo id do artigo
     * @param quantidade quantidade do artigo a atualizar
     * @return True or false
     */
    public boolean atualizarArtigoCarrinho(int idArtigo, int quantidade) {
        int idFarmacia = 1;
        if ((idArtigo >= 1 && idArtigo < Integer.MAX_VALUE) && (quantidade >= 1 && quantidade < Integer.MAX_VALUE)) {
            if (getStockArtigo(1, idArtigo) >= quantidade) {
                Artigo a = baseController.getArtigoById(idFarmacia, idArtigo);
                if (a != null) {
                    return this.encomenda.atualizarQuantidade(a, quantidade);
                }
            }
        }
        return false;
    }

    /**
     * Remover artigo do carrinho
     * @param idArtigo id do artigo
     * @return True or false
     */
    public boolean removerArtigoCarrinho(int idArtigo) {
        int idFarmacia = 1;
        if ((idArtigo >= 1 && idArtigo < Integer.MAX_VALUE)) {

                Artigo a = baseController.getArtigoById(idFarmacia, idArtigo);
                if (a != null) {
                    return this.encomenda.removerArtigo(a);
                }

        }
        return false;
    }

    /** 
     * Obter o stock de um determinado artigo para uma determinada farmacia
     * @param idFarmacia id da farmacia
     * @param idArtigo id do artigo
     * @return stock do artigo
     */
    private int getStockArtigo(int idFarmacia, int idArtigo) {
        return baseController.getStockArtigo(idFarmacia, idArtigo);

    }

    /**
     * Criar a encomenda
     * @param nifCliente nif do cliente
     * @return true or false
     */
    public boolean finalizarEncomenda(Integer nifCliente) {
        
        this.encomenda.setNifCliente(nifCliente);
        
        Integer idEncomenda = baseController.criarEncomenda(this.encomenda);
        if (idEncomenda != null && idEncomenda >= 1) {
            this.encomenda.setId(idEncomenda);
            
            return faturaController.gerarFaturaEncomenda(encomenda);
        }

        return false;
    }

    /**
     * Obter Encomenda
     * @return Encomenda
     */
    public Encomenda getEncomenda() {
        return this.encomenda;
    }

}
