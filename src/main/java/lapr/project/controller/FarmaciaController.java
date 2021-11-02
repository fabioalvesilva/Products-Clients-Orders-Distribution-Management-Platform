/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lapr.project.model.Artigo;
import lapr.project.model.Farmacia;
import lapr.project.utils.Utils;
import static lapr.project.utils.Constantes.DIRETORIO_CSV_OUTPUT;

/**
 *
 * @author Groupo 11
 */
public class FarmaciaController {

    /**
     * Base controller
     */
    BaseController baseController = new BaseController();

    /**
     * Controller de notidicações
     */
    NotificacaoController notificacaoController = new NotificacaoController();

    /**
     * COntroller de grafos
     */
    public GraphController graphController = new GraphController();

    /**
     * Inicializa construtor vazio
     */
    public FarmaciaController() {
    }

    /**
     * Inicializa o construtor completo
     * @param graphController
     */
    public FarmaciaController(GraphController graphController) {
        this.graphController = graphController;
    }

    /**
     * Listar stock de produtos em determinada farmacia
     * @param idFarmacia id de farmacia
     * @return mapa com lista de artigo e o sei respetivo stock
     */
    public Map<Artigo, Integer> listarProdutosStock(int idFarmacia) {

        Map<Artigo, Integer> mapArtigoStock = new HashMap<>();

        List<Artigo> artigos = baseController.getAllArtigos(idFarmacia);
        Integer stock = -1;

        for (Artigo a : artigos) {
            stock = baseController.getStockArtigo(idFarmacia, a.getIdArtigo());
            mapArtigoStock.put(a, stock);
        }

        return mapArtigoStock;
    }

    /**
     * Update do stock de um determinado artigo de uma determinada farmacia
     * @param idFarmacia id da farmacia
     * @param idArt id do artigo
     * @param quantidade quantidade de stock
     * @return True or False
     */
    public boolean updateStockArtigo(int idFarmacia, int idArt, int quantidade) {
        return baseController.updateStockArtigo(idFarmacia, idArt, quantidade);
    }

    /**
     * Transferencia de produtos 
     * @param idFarmaciaOrigem farmacia de origem
     * @param idProduto id do produto
     * @param quantidade quantidade a transferir
     * @return True or false
     */
    public boolean transferenciaProduto(int idFarmaciaOrigem, int idProduto, int quantidade) {

        if (idFarmaciaOrigem >= 1 && idProduto >= 1 && quantidade >= 1) {

            int stock = 0;

            List<Farmacia> farmaciasMaisProximas = graphController.getFarmaciasProximas(idFarmaciaOrigem);

            for (Farmacia f : farmaciasMaisProximas) {
                stock = baseController.getStockArtigo(f.getId(), idProduto);
                if (stock > quantidade && transferirProduto(f.getId(), idFarmaciaOrigem, idProduto, quantidade)) {

                    String mensagem = "Foi transferido para a farmacia " + f.getDescricao() + ", " + quantidade + " do produto ID: " + idProduto;
                    notificacaoController.notificacaoTransferenciaProduto(mensagem);

                    String nomeGuiaTransporte = "guiaTransporte_" + idProduto + "_" + idFarmaciaOrigem + "_" + f.getId() + ".csv";
                    String notaEncomenda = "notaEncomenda" + idProduto + "_" + idFarmaciaOrigem + "_" + f.getId() + ".csv";

                    Utils.escreverGuiaTransporteNotaEncomenda(DIRETORIO_CSV_OUTPUT + nomeGuiaTransporte, mensagem);
                    Utils.escreverGuiaTransporteNotaEncomenda(DIRETORIO_CSV_OUTPUT + notaEncomenda, mensagem);

                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Transferir produto entre farmacia
     * @param idFarmaciaRemetente id da farmacia remetente
     * @param idFarmaciaDestinatario id da farmacia destinataria
     * @param idProduto id do produto
     * @param quantidade quantidade do produto a transferir
     * @return true or false
     */
    public boolean transferirProduto(int idFarmaciaRemetente, int idFarmaciaDestinatario, int idProduto, int quantidade) {
        return baseController.transferirProduto(idFarmaciaRemetente, idFarmaciaDestinatario, idProduto, quantidade);
    }
}
