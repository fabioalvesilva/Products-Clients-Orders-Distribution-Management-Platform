/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.List;
import lapr.project.utils.Constantes;
import lapr.project.utils.Utils;
import java.util.Map;
import lapr.project.utils.*;

/**
 *
 * @author Grupo 11
 */
public class Rota {

    /**
     * total de energia
     */
    private Double totalEnergia;

    /**
     * total de tempo
     */
    private Double totalTempo;
    /**
     * *
     * Lista de ponto de Rota
     */
    private List<LigacaoLocais> listaLigacoesRota;

    /**
     * Inicializa o construtor
     *
     * @param listaLigacoesRota
     */
    public Rota(List<LigacaoLocais> listaLigacoesRota) {
        this.listaLigacoesRota = listaLigacoesRota;
        totalEnergia = null;
        totalTempo = null;

    }

    /**
     * retorna total de energia
     *
     * @return total de energia
     */
    public Double getTotalEnergia() {
        return totalEnergia;
    }

    /**
     * retorna total de tempo
     *
     * @return total de tempo
     */
    public Double getTotalTempo() {
        return totalTempo;
    }

    /**
     * Modifica o total de tempo
     *
     * @param totalTempo
     */
    private void setTotalTempo(Double totalTempo) {
        this.totalTempo = totalTempo;
    }

    /**
     * Modifica o total de energia
     *
     * @param totalEnergia
     */
    private void setTotalEnergia(Double totalEnergia) {
        this.totalEnergia = totalEnergia;
    }

    /**
     * Modificar a lista de ligações
     * @param listaLigacoesRota lista de ligações
     */
    public void setListaLigacoesRota(List<LigacaoLocais> listaLigacoesRota) {
        this.listaLigacoesRota = listaLigacoesRota;
    }

    /**
     * Calculo da rota
     * @return true or false
     */
    public boolean calcularRota() {
        if (!listaLigacoesRota.isEmpty()) {
            this.totalEnergia = 0D;
            this.totalTempo = 0D;
            
            for (LigacaoLocais l : listaLigacoesRota) {
                
                this.totalEnergia = totalEnergia + l.getTipoPeso(TipoPesoEnum.ENERGIA);
                this.totalTempo = totalTempo + l.getTipoPeso(TipoPesoEnum.TEMPO);
            }
            return true;
        }
        return false;
    }

    /**
     * Devolve a descrição textual
     *
     * @return Devolve a descrição textual
     */
    @Override
    public String toString() {
        if (calcularRota()) {
            String result = "Ligacoes rota: \n";
            for (LigacaoLocais l : listaLigacoesRota) {
                
                result += l.toString();
                result += "\n";
            }
            return String.format("Total tempo Rota: %.2f horas \n Total Energia para Rota %.2f Wh \n Rota: \n %s ", this.totalTempo / 3600, this.totalEnergia, result);

        } else {
            return "Sem rota";
        }

    }

}
