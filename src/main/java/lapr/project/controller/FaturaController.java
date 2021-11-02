/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import lapr.project.model.Artigo;
import lapr.project.model.Encomenda;
import lapr.project.model.Fatura;

/**
 *
 * @author Groupo 11
 */
public class FaturaController {

    /**
     * Fatura
     */
    Fatura fatura = new Fatura();

    /**
     * Base controller
     */
    BaseController baseController = new BaseController();

    /**
     * Controller de notificação
     */
    NotificacaoController notificacaoController = new NotificacaoController();

    /**
     * Controller de fatura
     */
    public FaturaController() {
    }

    /**
     * gerar fatura
     * @param e encomenda
     * @return true or false
     */
    public boolean gerarFaturaEncomenda(Encomenda e) {
        if (e != null && e.getId() >= 1 && e.getNifCliente() >= 1) {
            
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date d = new Date();
            fatura.setData(df.format(d));
            fatura.setNif(e.getNifCliente());
            fatura.setId(123); // Alterar para getID
            for (Map.Entry<Artigo, Integer> en : e.getListaArtigos().entrySet()) {
                Artigo artigo = en.getKey();
                Double preco = e.custoArtigo(en.getKey());
                String descricao = artigo.toString();
                fatura.adicionarLinhaFatura(descricao, preco);
            }
            String emailCliente = baseController.getEmailClienteByEncomenda(e);
            return enviarFaturaCliente(emailCliente);

        }
        return false;
    }

    /**
     * Enviar fatura ao cliente
     * @param email email cliente
     * @return true or false
     */
    public boolean enviarFaturaCliente(String email) {
        return notificacaoController.notificacaoFaturaCliente(email, fatura);
    }
}
