/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.BaseController;
import lapr.project.model.Cliente;
import lapr.project.model.Drone;
import lapr.project.model.Estafeta;
import lapr.project.model.Farmacia;
import lapr.project.model.LigacaoLocais;
import lapr.project.model.Parque;
import lapr.project.model.Scooter;
import lapr.project.ui.Log;
import lapr.project.utils.Utils;
import static lapr.project.utils.Constantes.*;

/**
 * Classe responsável pela leitura dos ficheiros com a informação dos objetos e
 * instanciação dos mesmos.
 */
public class LerCSV {

    /**
     * base controller
     */
    private final BaseController baseController;

    /**
     * ler ficheiro csv
     */
    public LerCSV() {
        this.baseController = new BaseController();
    }

    /**
     * Método responsável pela leitura e instanciação de Farmácias.
     *
     * @param ficheiro nome do ficheiro
     * @return lista de farmacias
     * @throws IOException
     */
    public List<Farmacia> leituraFarmacias(String ficheiro) throws IOException {

        List<Farmacia> farmacias = new ArrayList<>();

        ArrayList<String[]> listaFarmacias = Utils.lerCSV(ficheiro);

        Log.logInfo("Instanciar farmacias...");
        for (String[] s : listaFarmacias) {

            String descricao = s[0];
            Integer nif = Integer.parseInt(s[1]);
            String rua = s[2];
            Double latitude = Double.parseDouble(s[3]);
            Double longitude = Double.parseDouble(s[4]);
            Double altitude = Double.parseDouble(s[5]);

            Farmacia f = new Farmacia();

            f.setDescricao(descricao);
            f.setNif(nif);
            f.getEndereco().setRua(rua);
            f.getEndereco().setLatitude(latitude);
            f.getEndereco().setLongitude(longitude);
            f.getEndereco().setAltitude(altitude);

            int id = baseController.novaFarmacia(f);
            f.setId(id);
            farmacias.add(f);

            Log.logInfo(f.toString());
        }
        Log.logInfo("OK");

        return farmacias;
    }

    /**
     * Método responsável pela leitura e instanciação de Estafetas.
     *
     * @param ficheiro ficheiro
     * @return lista de estafetas
     * @throws IOException
     */
    public List<Estafeta> leituraEstafetas(String ficheiro) throws IOException {

        List<Estafeta> estafetas = new ArrayList<>();

        ArrayList<String[]> listaEstafetas = Utils.lerCSV(ficheiro);

        Log.logInfo("Instanciar estafetas...");
        for (String[] s : listaEstafetas) {

            String nome = s[0];
            String email = s[1];
            Integer nif = Integer.parseInt(s[2]);
            Integer niss = Integer.parseInt(s[3]);
            String password = s[4];
            Integer idFarmacia = Integer.parseInt(s[5]);

            Estafeta e = new Estafeta();

            e.setNome(nome);
            e.setEmail(email);
            e.setNif(nif);
            e.setNiss(niss);
            e.setPassword(password);

            int id = baseController.novoEstafeta(e, idFarmacia);
            e.setId(id);
            estafetas.add(e);

            Log.logInfo(e.toString());
        }
        Log.logInfo("OK");

        return estafetas;
    }

    /**
     * Método responsável pela leitura e instanciação de Clientes.
     *
     * @param ficheiro nome do ficheiro
     * @return lista de clientes
     * @throws IOException
     */
    public List<Cliente> leituraClientes(String ficheiro) throws IOException {

        List<Cliente> clientes = new ArrayList<>();

        ArrayList<String[]> listaClientes = Utils.lerCSV(ficheiro);

        Log.logInfo("Instanciar clientes...");
        for (String[] s : listaClientes) {

            String nome = s[0];
            String email = s[1];
            Integer nif = Integer.parseInt(s[2]);
            String password = s[3];
            String rua = s[4];
            Double latitude = Double.parseDouble(s[5]);
            Double longitude = Double.parseDouble(s[6]);
            Double altitude = Double.parseDouble(s[7]);
            Integer numeroCartao = Integer.parseInt(s[8]);

            Cliente c = new Cliente();

            c.setNome(nome);
            c.setEmail(email);
            c.setNif(nif);
            c.setPassword(password);
            c.getEndereco().setRua(rua);
            c.getEndereco().setLatitude(latitude);
            c.getEndereco().setLongitude(longitude);
            c.getEndereco().setAltitude(altitude);
            c.setCartaoCredito(numeroCartao);

            if (baseController.novoCliente(c)) {
                clientes.add(c);
                Log.logInfo(c.toString());
            }
        }
        Log.logInfo("OK");

        return clientes;
    }

    /**
     * Método responsável pela leitura e instanciação de Parques.
     *
     * @param ficheiro nome de ficheiro
     * @return lista de parques
     * @throws IOException
     */
    public List<Parque> leituraParques(String ficheiro) throws IOException {

        List<Parque> parques = new ArrayList<>();

        ArrayList<String[]> listaParques = Utils.lerCSV(ficheiro);

        Log.logInfo("Instanciar parques...");
        for (String[] s : listaParques) {

            int id_TipoParque = Integer.parseInt(s[0]);
            int ocupacaoLugaresEletricos = Integer.parseInt(s[1]);
            int ocupacaoLugaresNormais = Integer.parseInt(s[2]);
            int maxLugaresEletricos = Integer.parseInt(s[3]);
            int maxLugaresNormais = Integer.parseInt(s[4]);
            String rua = s[5];
            Double latitude = Double.parseDouble(s[6]);
            Double longitude = Double.parseDouble(s[7]);
            Double altitude = Double.parseDouble(s[8]);
            int idFarmacia = Integer.parseInt(s[9]);
            int capacidadeTensao = Integer.parseInt(s[10]);

            Parque p = new Parque();

            p.setIdTipoParque(id_TipoParque);
            p.setOcupacaoLugaresEletricos(ocupacaoLugaresEletricos);
            p.setOcupacaoLugaresNormais(ocupacaoLugaresNormais);
            p.setMaxLugaresEletricos(maxLugaresEletricos);
            p.setMaxLugaresNormais(maxLugaresNormais);
            p.getEndereco().setRua(rua);
            p.getEndereco().setLatitude(latitude);
            p.getEndereco().setLongitude(longitude);
            p.getEndereco().setAltitude(altitude);
            p.setCapacidadeTensao(capacidadeTensao);

            int id = baseController.criarParque(idFarmacia, p);
            p.setId(id);
            parques.add(p);

            Log.logInfo(p.toString());
        }
        Log.logInfo("OK");

        return parques;
    }

    /**
     * Método responsável pela leitura e instanciação de Scooters.
     *
     * @param ficheiro nome ficheiro
     * @return lista de scooters
     * @throws IOException
     */
    public List<Scooter> leituraScooters(String ficheiro) throws IOException {

        List<Scooter> scooters = new ArrayList<>();

        ArrayList<String[]> listaScooters = Utils.lerCSV(ficheiro);

        Log.logInfo("Instanciar scooters...");
        for (String[] s : listaScooters) {

            String matricula = s[0];
            int idFarmacia = Integer.parseInt(s[1]);

            Scooter p = new Scooter();

            p.setMatricula(matricula);

            if (baseController.adicionarScooter(p, idFarmacia)) {
                Scooter tmp = baseController.getScooterByMatricula(p.getMatricula());

                p.setId(tmp.getId());
                p.setIdEstado(tmp.getIdEstado());
                p.setCapacidadeAtual(tmp.getCapacidadeAtual());
                p.setCapacidadeBateria(tmp.getCapacidadeBateria());
                p.setEficienciaBateria(tmp.getEficienciaBateria());
                p.setIdTipoBateria(tmp.getIdTipoBateria());
                p.setModelo(tmp.getModelo());

                scooters.add(p);
            }
            Log.logInfo(p.toString());
        }
        Log.logInfo("OK");

        return scooters;
    }

    /**
     * Método responsável pela leitura de Produtos.
     *
     * @param ficheiro nome do ficheiro
     * @throws IOException
     */
    public void leituraProdutos(String ficheiro) throws IOException {

        ArrayList<String[]> artigos = Utils.lerCSV(ficheiro);

        Log.logInfo("Adicionar produtos nas farmacias...");

        for (String[] s : artigos) {

            int idProduto = Integer.parseInt(s[0]);
            int idFarmacia = Integer.parseInt(s[1]);
            int quantidade = Integer.parseInt(s[2]);

            if (this.baseController.updateStockArtigo(idFarmacia, idProduto, quantidade)) {
                Log.logInfo("Produto adicionado com sucesso.");
            } else {
                Log.logInfo("Erro adicionar produto.");
            }
        }
        Log.logInfo("OK");
    }

    /**
     * Método responsável pela leitura e instanciação de Drones.
     *
     * @param ficheiro nome do ficheiro
     * @return lista de drones
     * @throws IOException
     */
    public List<Drone> leituraDrones(String ficheiro) throws IOException {

        List<Drone> drones = new ArrayList<>();

        ArrayList<String[]> listaDrones = Utils.lerCSV(ficheiro);

        Log.logInfo("Instanciar drones...");
        for (String[] s : listaDrones) {

            int nrRegisto = Integer.parseInt(s[0]);
            int idFarmacia = Integer.parseInt(s[1]);

            Drone d = new Drone();

            d.setNrRegisto(nrRegisto);
            d.setIdFarmacia(idFarmacia);

            if (baseController.adicionarDrone(d)) {

                Drone tmp = baseController.getDroneByNrRegisto(d.getNrRegisto());

                d.setId(tmp.getId());
                d.setIdEstado(tmp.getIdEstado());
                d.setDesignacaoModelo(tmp.getDesignacaoModelo());
                d.setCapacidadeBateria(tmp.getCapacidadeBateria());
                d.setCargaBateriaAtual(tmp.getCargaBateriaAtual());
                d.setEficiencia(tmp.getEficiencia());

                drones.add(d);
            }
            Log.logInfo(d.toString());
        }
        Log.logInfo("OK");

        return drones;
    }
      /**
     * Método responsável pela leitura e instanciação de Ligacoes.
     * 
     * @param ficheiro nome do ficheiro
     * @return List<LigacaoLocais>
     * @throws IOException
     */
    public List<String []> leituraLigacaoLocais(String ficheiro) throws IOException {

        List<String[]> locais = new ArrayList<>();
//From;To; Bidirecional; VelocidadeVento; DirecaoVento;CoeficienteAtrito
        ArrayList<String[]> listaLocais = Utils.lerCSV(ficheiro);
        return listaLocais;
    }
}
