/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui.assessement;

import java.util.List;
import java.util.Map;
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

/**
 *
 * @author Fábio Silva
 */
public interface Servicos {

    List<Farmacia> adicionarFarmacias(String ficheiro);

    List<Parque> adicionarParques(String ficheiro);

    List<Scooter> adicionarScooters(String ficheiro);

    List<Estafeta> adicionarEstafetas(String ficheiro);

    List<Cliente> adicionarClientes(String ficheiro);

    void adicionarProdutos(String ficheiro);

    boolean loginAdmin(String email, String password);

    boolean loginUtilizador(String email, String password);

    boolean inserirConexoes(String ficheiro);

    boolean adicionarArtigoCarrinho(int idArtigo, int quantidade);

    boolean finalizarEncomenda(Integer nifCliente);

    List<Artigo> getListaProdutosFarmacia(Integer nifCliente);

    boolean getOcupacaoEstafeta(int idEstafeta);

    List<Encomenda> getEncomendasPendentesByFarmacia(Integer idFarmacia);

    boolean adicionarEncomendas(List<Encomenda> listEncomendas);

    boolean registarEntrega();

    Entrega getEntrega();

    boolean transferenciaProduto(int idFarmaciaOrigem, int idProduto, int quantidade);

    Map<Artigo, Integer> listarProdutosStock(int idFarmacia);

    Integer getScooterParaEntrega(Integer idFarmacia);

    Integer getDroneParaEntrega(Integer idFarmacia);

    void setVeiculoParaEntrega(Veiculo v);

    boolean setIdEstafetaEntrega();

    public Cliente getClienteByNIF(Integer nif);

    public List<Artigo> getAllArtigos(Integer idFarmacia);

    ///GRAFO/////
    public boolean lerLigacoesDeFicheiro(String ficheiro);

    public void printResult();

}
