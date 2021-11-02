/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Encomenda;
import lapr.project.model.Fatura;
import lapr.project.utils.Constantes;
import static lapr.project.utils.Constantes.EMAILDEFAULT;
import lapr.project.ui.Log;
import lapr.project.data.EmailSender;

/**
 *
 * @author Groupo 11
 */
public class NotificacaoController {

    /**
     * Construtor vazio
     */
    public NotificacaoController() {
        //Construtor vazio
    }

    /**
     * Notificação da fatura Cliente
     * @param email email cliente
     * @param f fatura
     * @return true or false
     */
    public boolean notificacaoFaturaCliente(String email, Fatura f) {
        if (!email.isEmpty() && f != null) {
            //REMOVER PARA ENVIAR AO CLIENTE
            email = Constantes.EMAILDEFAULT;
            //REMOVER PARA ENVIAR AO CLIENTE
            String titulo = ("Encomenda Fatura" + f.getId() + "Cliente " + f.getNif());
            
            enviarEmail(email, titulo, f.toString());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Notificação de envio de encomenda
     * @param e encomenda
     * @return true or false
     */
    public boolean notificacaoEnvioEncomenda(Encomenda e) {
        if (e != null) {

            //REMOVER PARA ENVIAR AO CLIENTE
            String titulo = ("Envio de encomenda");
            
            this.enviarEmail(EMAILDEFAULT, titulo, e.toString());
            return true;
        } else {
            return false;
        }
    }

    /**
     * notificação de mau acoplamento
     * @param email email do estafeta
     * @param matricula matricula do veiculo
     * @return true or false
     */
    public boolean notificacaoMaulAcopolamento(String email, String matricula) {
        if (!email.isEmpty() && !matricula.isEmpty()) {

            String titulo = ("AVISO! " + matricula + " mal estacionada!");
            
            this.enviarEmail(email, titulo, matricula);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Notificação de acoplamento
     * @param email email do estafeta
     * @param matricula matricula do veiculo
     * @param estimativa estimativa
     * @return true or false
     */
    public boolean notificacaoAcopolamento(String email, String matricula, String estimativa) {
        if (!email.isEmpty() && !matricula.isEmpty()) {

            int horas = (int) Math.floor(Integer.parseInt(estimativa) / 3600);    //horas  
            int minutos = (int) Math.floor((Integer.parseInt(estimativa) - (horas * 3600)) / 60);  //mins
            int segundos = (int) Integer.parseInt(estimativa) - (horas * 3600) - (minutos * 60);   //segundos
            String titulo = ("Acopolamento scooter matricúla " + matricula + ".");
            String corpoTexto = ("A Scooter estará totalmente carregada em aproximadamente "
                    + horas + " horas "
                    + minutos + " minutos e "
                    + segundos + " segundos.");

            Log.logInfo("Notificação 'Acopolamento' enviada para - " + email + " - com o assunto: " + titulo);
            this.enviarEmail(email, titulo, corpoTexto);

            return true;
        } else {
            return false;
        }
    }

    /**
     * notificacao de Transferencia de Produto intra farmacia
     * @param mensagem mensagem
     * @return true or false
     */
    public boolean notificacaoTransferenciaProduto(String mensagem) {

        if (!mensagem.isEmpty()) {

            String titulo = ("Transferência de Produtos");
            this.enviarEmail(EMAILDEFAULT, titulo, mensagem);
            
            return true;
        } else {
            return false;
        }
    }

    /**
     * Notificação do registo de cliente
     * @param email email do cliente
     * @param mensagem mensagem
     * @return True or false
     */
    public boolean notificacaoRegistoCliente(String email, String mensagem) {

        if (!mensagem.isEmpty() && !email.isEmpty()) {

            String titulo = ("Novo Registo!");
            this.enviarEmail(email, titulo, mensagem);
            
            return true;
        } else {
            return false;
        }
    }

    /**
     * Enviar email
     * @param emailPara email destinatário
     * @param tituloEmail assunto do email
     * @param corpoEmail corpo do email
     */
    public static void enviarEmail(String emailPara, String tituloEmail, String corpoEmail) {
        EmailSender.sendMail(emailPara, tituloEmail, corpoEmail);
    }

}
