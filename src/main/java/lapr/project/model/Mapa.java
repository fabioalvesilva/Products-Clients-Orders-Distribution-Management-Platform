/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lapr.project.utils.Pair;

/**
 *
 * @author Grupo 11
 */
public class Mapa {

    /**
     * grafo
     */
    private GrafoMatrix grafo;

    /**
     * losta de localidades
     */
    private List<Local> locaisList;

    /**
     * mapa com ligações
     */
    private Map<Pair, LigacaoLocais> ligacoesMap;

    /**
     * numero de ligações
     */
    private int numligacoes;

    /**
     * Inicializar o construtor completo
     * @param locaisList lista de localidades
     */
    public Mapa(List<Local> locaisList) {
        this.locaisList = locaisList;
        ligacoesMap = new HashMap<>();
        this.grafo = new GrafoMatrix(locaisList.size());
        this.numligacoes = ligacoesMap.size();

    }

    /**
     * obter numero de ligações
     * @return numero de ligações
     */
    public int getNumligacoes() {
        return numligacoes;
    }

    /**
     * obter numero de localidades
     * @return numero de localidades
     */
    public int getNumLocais() {
        return locaisList.size();
    }

    ///////////////Locais///////////////////////////

    /**
     * index de local
     * @param l localidade
     * @return index
     */
    public int localIndex(Local l) {
        if (l != null) {
            int index = locaisList.indexOf(l);
            return (index > -1) ? index + 1 : index;
        }
        return -1;

    }

    /**
     * localidade pelo index
     * @param index index 
     * @return localidade
     */
    public Local getLocalfromIndex(int index) {
        if (index > -1 && index < locaisList.size()) {
            return locaisList.get(index);
        }
        return null;

    }

    /**
     * Verifica se local existe
     * @param l localidade
     * @return true or false
     */
    public boolean localExiste(Local l) {
        return localIndex(l) > -1;

    }

    ///////////////Ligacoes///////////////////////////

    /**
     * Adicionar Ligacao
     * @param ligacao ligacao
     * @return true or false
     */
    public boolean adicionarLigacao(LigacaoLocais ligacao) {
        if (ligacao != null && ligacao.getFrom() != null && ligacao.getTo() != null) {
            if (localExiste(ligacao.getFrom()) && localExiste(ligacao.getTo())) {
                int from = localIndex(ligacao.getFrom());
                int to = localIndex(ligacao.getTo());

                Pair key = new Pair(to, from);
                if (!ligacoesMap.containsKey(key)) {
                    ligacoesMap.put(key, ligacao);
                    numligacoes++;
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Adicionar ligações
     * @param lista lista de localidades
     * @return true or false
     */
    public boolean adicionarLigacoes(List<LigacaoLocais> lista) {
        int temp = numligacoes;
        if (lista != null && !lista.isEmpty()) {
            for (LigacaoLocais l : lista) {
                adicionarLigacao(l);
                if (l.isBidirecional()) {
                    LigacaoLocais ligacaoInvertida = new LigacaoLocais(l.getTo(), l.getFrom(), 1, l.getVentoVelocidade(), l.getVentoDirecao(), l.getVentoVelocidade());
                    adicionarLigacao(ligacaoInvertida);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * remover ligação
     * @param from origem 
     * @param to destino
     * @return true or false
     */
    public boolean removerLigacao(Local from, Local to) {
        if (localExiste(from) && localExiste(to)) {
            int f = localIndex(from);
            int t = localIndex(to);
            Pair key = new Pair(f, t);
            if (!ligacoesMap.containsKey(key)) {
                ligacoesMap.remove(key);
                numligacoes = numligacoes - 1;
                return true;
            }
        }
        return false;
    }

    //Para recaregar Edges no grafo tipo Aereo & Terrestre

    /**
     * remover todas as ligações
     * @return true or false
     */
    public boolean removerTodasLigacoes() {
        if (!ligacoesMap.isEmpty()) {
            this.ligacoesMap = new HashMap<>();
            numligacoes = 0;
            return true;
        }
        return false;
    }

    /**
     * obter ligações entre localidades
     * @param from origem
     * @param to destino
     * @return ligacoes locais
     */
    public LigacaoLocais getLigacaoLocais(int from, int to) {
        Pair pair = new Pair(to, from);
        LigacaoLocais temp = this.ligacoesMap.get(pair);
        return temp;
    }

    ////////////////Grafo/////////////////
    //Colocar pesos de um dado tipo no grafo

    /**
     * Criar grafo 
     * @param tipo tipo
     * @param v veiculo
     * @return true or false
     */
    public boolean criarGrafoTipo(TipoPesoEnum tipo, Veiculo v) {
        if (!ligacoesMap.isEmpty() && !locaisList.isEmpty()) {
            for (LigacaoLocais ligacao : ligacoesMap.values()) {
                int from = localIndex(ligacao.getFrom());
                int to = localIndex(ligacao.getTo());

                //Fazer Calculos Ligacoes 
                if (!ligacao.isPesosCalculados()) {
                    ligacao.calcularPesos(v); //efetuar calculos se ainda nao efetuados 
                }

                if (from != -1 && to != -1) {
                    if (this.grafo.isCalculada()) {
                        grafo.resetMatrices();
                    }
                    this.grafo.inserirPeso(from, to, ligacao.getTipoPeso(tipo)); //insere peso tipo no grafo
                }
            }
            return grafo.calcularAllPairs();
        }

        return false;
    }

    /**
     * obter rota
     * @param orig origem
     * @param dest destino
     * @return lista de ligações
     */
    public List<LigacaoLocais> getRota(Local orig, Local dest) {
        List<LigacaoLocais> caminho = new LinkedList<>();
        if (orig != null && dest != null) {
            int f = localIndex(orig);
            int t = localIndex(dest);
            List<Integer> tmp = grafo.getCaminho(f, t);
            for (int i = 0; i < tmp.size() - 1; i++) {
                caminho.add(getLigacaoLocais(tmp.get(i), tmp.get(i + 1)));  //Adicionar Ligacao entre locais ao caminho pelo Index              

            }
        }
        return caminho;
    }

    /**
     * obter rota
     * @param orig origem
     * @param dest destino
     * @param intermedios localidades intermedias
     * @return lista de ligações
     */
    public List<LigacaoLocais> getRota(Local orig, Local dest, List<Local> intermedios) {
        List<LigacaoLocais> caminho = new LinkedList<>();
        if (orig != null && dest != null) {
            int f = localIndex(orig);
            int t = localIndex(dest);
            //index intermedios
            List<Integer> intermediosIndex = new ArrayList<>();
            for (Local l : intermedios) {
                intermediosIndex.add(localIndex(l));
            }
            List<Integer> tmp = grafo.getCaminhoComIntermedios(f, t, intermediosIndex);

            for (int i = 0; i < tmp.size() - 1; i++) {
                caminho.add(getLigacaoLocais(tmp.get(i), tmp.get(i + 1)));  //Adicionar Ligacao entre locais ao caminho pelo Index              

            }
        }
        return caminho;
    }

    ///////////////
    /**
     * *
     * DE um conjunto de locais de um tipo , retorna o mais proximo
     *
     * @param orig origem
     * @param tipoLocal localidade
     * @return localidade
     */
    public Local getLocalMaisProximo(Local orig, Local tipoLocal) {
        List<List<LigacaoLocais>> caminhos = new LinkedList<>();

        // FAz aa rota e devolve consjunto Ligacoes
        if (tipoLocal instanceof Farmacia) {
            for (Local l : locaisList) {
                if (l instanceof Farmacia) {
                    List<LigacaoLocais> tempRota = getRota(orig, l);
                    if (!tempRota.isEmpty()) {
                        caminhos.add(tempRota);
                    }
                }

            }
        }

        if (!caminhos.isEmpty()) {
            List<LigacaoLocais> cam = melhorCaminho(caminhos);

            return cam.get(cam.size() - 1).getTo();
        }

        return null;
    }

    /**
     * obter localidade mais proxima
     * @param orig origem
     * @param tipoLocal localidade
     * @return lista de localidades
     */
    public List<Local> getLocaisMaisProximo(Local orig, Local tipoLocal) {
        List<List<LigacaoLocais>> caminhos = new LinkedList<>();

        for (Local l : locaisList) {
            caminhos.add(getRota(orig, l));
        }
        if (tipoLocal instanceof Farmacia) {

        }

        List<Local> listaLocal = new ArrayList<>();
        for (List<LigacaoLocais> lista : caminhos) {
            listaLocal.add(lista.get(lista.size() - 1).getTo());
        }

        return listaLocal;

    }

    /**
     * melhor caminho
     * @param listaCaminhos lista de caminhos
     * @return lista de ligações
     */
    public List<LigacaoLocais> melhorCaminho(List<List<LigacaoLocais>> listaCaminhos) {
        List<LigacaoLocais> melhorCaminho = new LinkedList<>();
        double custoAnterior = Double.POSITIVE_INFINITY;
        double custoAtual = 0;
        for (List<LigacaoLocais> caminho : listaCaminhos) {
            custoAtual = 0;

            for (LigacaoLocais ligacao : caminho) {
                custoAtual = custoAtual + ligacao.getTipoPeso(TipoPesoEnum.DISTANCIA);
            }
            if (custoAnterior > custoAtual) {
                custoAnterior = custoAtual;
                melhorCaminho.clear();
                melhorCaminho.addAll(caminho);
            }

        }
        return melhorCaminho;
    }

    /**
     * imprimir o resultado
     */
    public void printResult() {

        for (Local l : locaisList) {
            System.out.println("LocalIndex:" + this.localIndex(l) + l.getEndereco());
        }
        grafo.getCaminho(1, 2);
        grafo.printResult();

    }
}
