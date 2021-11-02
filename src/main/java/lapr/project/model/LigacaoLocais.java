/**
 *
 * @author rmarinho
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
import lapr.project.utils.Calculos;
import lapr.project.utils.Constantes;

/**
 *
 * @author grupo 11
 */
public class LigacaoLocais {

    //From file  

    /**
     * localidade de partida
     */
    private Local from;

    /**
     * localidade de destino
     */
    private Local to;

    /**
     * Boolean: direcção
     */
    private boolean bidirecional;

    /**
     * velocidade do vento
     */
    private Double ventoVelocidade; //(Kms)

    /**
     * direção do vento
     */
    private Double ventoDirecao; //(º) em graus

    /**
     * coeficiente do atrito
     */
    private Double coeficienteAtrito;

    //Calculados

    /**
     * declive (percentual)
     */
    private Double declive; //(Percentual) %

    //tipo de peso

    /**
     * peso da distancia
     */
    private Double pesoDistancia; //(KMS) 

    /**
     * peso da energia
     */
    private Double pesoEnergia; // Watt Hora

    /**
     * peso do tempo
     */
    private Double pesoTempo;// segundos 

    /**
     * pesos calculados
     */
    private boolean pesosCalculados;

    //

    /**
     * ligações entre localidades
     * @param from localidade de partida
     * @param to localidade de destino
     * @param bidirecional direção
     * @param ventoVelocidade velocidade do vento
     * @param ventoDirecao direção do vento
     * @param coeficienteAtrito coeficiente do atrito
     */
    public LigacaoLocais(Local from, Local to, int bidirecional, Double ventoVelocidade, Double ventoDirecao, Double coeficienteAtrito) {
        this.from = from;
        this.to = to;
        this.bidirecional = bidirecional > 0;
        this.ventoVelocidade = ventoVelocidade;
        this.ventoDirecao = ventoDirecao;
        this.coeficienteAtrito = coeficienteAtrito;

        this.declive = Double.NEGATIVE_INFINITY;
        this.pesoDistancia = Double.NEGATIVE_INFINITY;
        this.pesoEnergia = Double.NEGATIVE_INFINITY;
        this.pesoTempo = Double.NEGATIVE_INFINITY;
        pesosCalculados = false;
    }

    /**
     * obter localidade de partida
     * @return localidade de partida
     */
    public Local getFrom() {
        return from;
    }

    /**
     * localidade de destino
     * @return localidade de destino
     */
    public Local getTo() {
        return to;
    }

    /**
     * verifica se é bidirecional
     * @return true or false
     */
    public boolean isBidirecional() {
        return bidirecional;
    }

    /**
     * obter a velocidade do vento
     * @return velocidade do vento
     */
    public Double getVentoVelocidade() {
        return ventoVelocidade;
    }

    /**
     * obter a direção do vento
     * @return direção do vento
     */
    public Double getVentoDirecao() {
        return ventoDirecao;
    }

    /**
     * obter o coeficiente de atrito
     * @return coeficiente de atrito
     */
    public Double getCoeficienteAtrito() {
        return coeficienteAtrito;
    }

    /**
     * obter o declive
     * @return declive
     */
    public Double getDeclive() {
        calcularDeclive();
        return declive;
    }

    /**
     * pesos calculados
     * @return true or false
     */
    public boolean isPesosCalculados() {
        return pesosCalculados;
    }

    /**
     * modifica o local de partida
     * @param from local de partida
     */
    public void setFrom(Local from) {
        this.from = from;
    }

    /**
     * modifica o local de destino
     * @param to local de destino
     */
    public void setTo(Local to) {
        this.to = to;
    }

    /**
     * cálculo de declive
     */
    private void calcularDeclive() {
        this.declive = Calculos.calcularInclinacaoAPartirDeCoordenadas(from.getEndereco().getLatitude(), from.getEndereco().getLongitude(), from.getEndereco().getAltitude(),
                to.getEndereco().getLatitude(), to.getEndereco().getLongitude(), to.getEndereco().getAltitude());

    }

    /**
     * Cálculo do peso das distancias
     * @return true or false
     */
    private boolean calcularPesoDistancia() {
        this.pesoDistancia = Calculos.distance(from.getEndereco().getLatitude(), from.getEndereco().getLongitude(), from.getEndereco().getAltitude(),
                to.getEndereco().getLatitude(), to.getEndereco().getLongitude(), to.getEndereco().getAltitude());
        return (pesoDistancia > 0);
    }

    /**
     * calculo do peso da energia
     * @param v veiculo
     * @return true or false
     */
    private boolean calcularPesoEnergia(Veiculo v) {
        if (v != null) {
            if (v instanceof Drone) {
                Drone d = (Drone) v;
                this.pesoEnergia = Calculos.calcularEnergiaNecessariaDrone(Constantes.PESOMEDIODRONE, Constantes.PESOMAXENTREGADRONE, Constantes.VELOCIDADEMEDDRONE,
                        this.ventoVelocidade, this.ventoDirecao,
                        from.getEndereco().getLatitude(), from.getEndereco().getLongitude(), from.getEndereco().getAltitude(),
                        to.getEndereco().getLatitude(), to.getEndereco().getLongitude(), to.getEndereco().getAltitude());

                //Calculos.calcularEnergiaNecessariaDrone(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                /*            
           (double drnMassa,                /!\ Em Falta no DRONE
            double cargaMassa,              7!\ Como juntar por Constante ??????
            double velocVeiculo,            /!\ Em Falta no DRONE
            double velocVentoLocal,
            double direcaoVento,
            double e1Lat,
            double e1Lon,
            double e1Alt,
            double e2Lat,
            double e2Lon,
            double e2Alt)
            }*/
                return pesoEnergia > -1;
            }
            if (v instanceof Scooter) {
                Scooter s = (Scooter) v;

                this.pesoEnergia = Calculos.calcularEnergiaNecessariaScooter(Constantes.PESOMEDIOESTAFETA, s.getPeso(), Constantes.PESOMAXENTREGASCOOTER, Constantes.AREAFRONTALMEDIASCOOTER, Constantes.VELOCIDADEMEDSCOOTER,
                        this.ventoVelocidade, this.ventoDirecao,
                        from.getEndereco().getLatitude(), from.getEndereco().getLongitude(), from.getEndereco().getAltitude(),
                        to.getEndereco().getLatitude(), to.getEndereco().getLongitude(), to.getEndereco().getAltitude());

                //this.pesoEnergia = Calculos.calcularEnergiaNecessariaScooter(s.getPeso(), s., 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
                /*            
            double sctMassa,+-
            double cargaMassa,                          7!\ Como juntar por Constante ??????
            double sctAreaFrontal,                     /!\ Em Falta no Scooter
            double velocVeiculo,                     /!\ Em Falta no Scooter
            double velocVentoLocal,                  
            double direcaoVentoEstrada,
            double e1Lat,
            double e1Lon,
            double e1Alt,
            double e2Lat,
            double e2Lon,
            double e2Alt)*/
                return pesoEnergia > -1;

            }
        }
        return false;

    }

    /**
     * calcular peso tempo
     * @param v veiculo
     * @return true or false
     */
    private boolean calcularPesoTempo(Veiculo v) {
        if (v != null) {
            if (v instanceof Drone) {
                // Calculos.calcularEnergiaNecessariaDrone(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                this.pesoTempo = Calculos.calcularTempoPercursoDrone(this.ventoVelocidade, this.ventoDirecao,
                        from.getEndereco().getLatitude(), from.getEndereco().getLongitude(), from.getEndereco().getAltitude(),
                        to.getEndereco().getLatitude(), to.getEndereco().getLongitude(), to.getEndereco().getAltitude());

                return this.pesoTempo > -1;
            }

            if (v instanceof Scooter) {
                this.pesoTempo = Calculos.calcularTempoPercursoScooter(Constantes.VELOCIDADEMEDSCOOTER,
                        from.getEndereco().getLatitude(), from.getEndereco().getLongitude(), from.getEndereco().getAltitude(),
                        to.getEndereco().getLatitude(), to.getEndereco().getLongitude(), to.getEndereco().getAltitude());
                return this.pesoTempo > -1;

                /*
            double veloc, 
            double e1Lat,
            double e1Lon,
            double e1Alt,
            double e2Lat,
            double e2Lon,
            double e2Alt
                 */
            }
        }
        return false;
    }

    /**
     * obter o tipo de peso
     * @param tipo tipo
     * @return peso
     */
    public Double getTipoPeso(TipoPesoEnum tipo) {
        switch (tipo) {
            case ENERGIA:
                return this.pesoEnergia;
            case TEMPO:
                return this.pesoTempo;
            default:
                return this.pesoDistancia;
        }
    }

    /**
     * calcular pesos
     * @param v veiculo
     * @return true or false
     */
    public boolean calcularPesos(Veiculo v) {
        if (v != null) {
            if (calcularPesoDistancia()
                    && calcularPesoEnergia(v)
                    && calcularPesoTempo(v)) {
                pesosCalculados = true;
            }
        }
        return this.pesosCalculados;
    }

    /**
     * hashcode
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.from);
        hash = 79 * hash + Objects.hashCode(this.to);
        return hash;
    }

    /**
     * Método que compara a igualdade do objeto atual com o objeto do mesmo tipo
     * @param obj objeto a comparar
     * @return True or False
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LigacaoLocais other = (LigacaoLocais) obj;
        if (!Objects.equals(this.from, other.from)) {
            return false;
        }
        if (!Objects.equals(this.to.getEndereco(), other.to.getEndereco())) {
            return false;
        }
        return true;
    }

    /**
     * Devolve a descrição textual
     * @return Devolve a descrição textual
     */
    @Override
    public String toString() {
        return "LigacaoLocais{" + "from=" + from.getEndereco().getRua() + ", to=" + to.getEndereco().getRua() + ", ventoVelocidade=" + ventoVelocidade + ", ventoDirecao=" + ventoDirecao + ", coeficienteAtrito=" + coeficienteAtrito + ", pesoDistancia=" + pesoDistancia + ", pesoEnergia=" + pesoEnergia + ", pesoTempo=" + pesoTempo + '}';
    }

}
