/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import static lapr.project.utils.Constantes.*;

/**
 *
 * @author grupo 11
 */
public class Calculos {

   
    /**
     * Método que calcula a distancia através da longitude, latitude e altitude
     * recebidas (em metros)
     *
     * @param lat1 latitude do endereco 1
     * @param lon1 latitude do endereco 2
     * @param lat2 longitude do endereco 1
     * @param lon2 longitude do endereco 2
     * @param alt1 altitude do endereco 1
     * @param alt2 altitude do endereco 2
     * @return distancia entre dois locais em metros
     */
    public static double distance(double lat1, double lon1, double alt1, double lat2, double lon2, double alt2) {

        if ((lat1 == lat2) && (lon1 == lon2) && (alt1 == alt2)) {
            return 0;
        } else {
            final int raioTerra = 6371000;
            double latDist = Math.toRadians(lat2 - lat1);
            double lonDist = Math.toRadians(lon2 - lon1);
            double distanciaAltura = Math.abs(alt1 - alt2);
            double distAux = Math.sin(latDist / 2) * Math.sin(latDist / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(lonDist / 2) * Math.sin(lonDist / 2);

            double distAux2 = 2 * Math.atan2(Math.sqrt(distAux), Math.sqrt(1 - distAux));
            double dist = raioTerra * distAux2;

            dist = Math.pow(dist, 2) + Math.pow(distanciaAltura, 2);

            return Math.round(Math.sqrt(dist) * 100.0) / 100.0;

        }

    }

    /**
     * Método que permite calcular a massa total do transporte em kg
     * 
     * @param massaCarga massa da carga em kg
     * @param massaVeiculo massa do veiculo em kg
     * @param massaCondutor massa do condutor em kg
     * @return massa total
     */
    public static double calcularMassaTotalTransporte(double massaCarga, double massaVeiculo, double massaCondutor) {

        double massaTransportada;

        if (massaCondutor != 0.0) {
            massaTransportada = massaCarga + massaVeiculo + massaCondutor;
        } else {
            massaTransportada = massaCarga + massaVeiculo;
        }
        return massaTransportada;
    }

    /**
     * Método que permite calcular a força gravitacional ou o thrust exercidos sobre
     * um corpo de massa igual ao valor introduzido por parâmetro.
     * 
     * @param massa em kg
     * @return força gravitacional  em Newtons (ou kgm/seg², o que é o mesmo)
     */
    public static double calcularForcaGraviticaThrust(double massa) {

        return massa * G;

    }

    /**
     * Determina a direção (bearing) entre pontos (em graus por causa da
     * subtração necessária no método calcularVelocidadeRelativaVeiculoVento)
     *
     * @param lat1 latitude do endereco 1
     * @param lon1 longitude do endereco 1
     * @param lat2 latitude do endereco 2
     * @param lon2 longitude do endereco 2
     * @return direção (bearing) entre pontos
     */
    public static Double calcularAnguloBearingCaminhoEntrePontos(double lat1, double lon1, double lat2, double lon2) {

        double longitude1 = lon1;
        double longitude2 = lon2;
        double latitude1 = Math.toRadians(lat1);
        double latitude2 = Math.toRadians(lat2);
        double difLongitudes = Math.toRadians(longitude2 - longitude1);

        double y = Math.sin(difLongitudes) * Math.cos(latitude2);

        double x = Math.cos(latitude1) * Math.sin(latitude2) - Math.sin(latitude1) * Math.cos(latitude2) * Math.cos(difLongitudes);

        return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
    }

    /**
     * Devolve a inclinação (o ângulo) entre duas posições em radianos para uso
     * nos métodos das potências
     *
     * @param lat1 latitude do endereco 1
     * @param lon1 longitude do endereco 1
     * @param alt1 altitude do endereco 1
     * @param lat2 latitude do endereco 2
     * @param lon2 longitude do endereco 2
     * @param alt2 altitude do endereco 2
     * @return inclinação (o ângulo) entre duas posições em radianos
     */
    public static double calcularInclinacaoAPartirDeCoordenadas(double lat1, double lon1, double alt1, double lat2, double lon2, double alt2) {

        double slopeAngle;
        double difEixoHorizontal;
        double difAltitude;
        double tangente;

        difEixoHorizontal = distance(lat1, lon1, 0.0, lat2, lon2, 0.0);

        difAltitude = alt2 - alt1;

        if (difAltitude == 0) {
            slopeAngle = 0.0;
        } else {
            if (difEixoHorizontal == 0) {
                slopeAngle = Math.PI;
            } else {
                tangente = difAltitude / difEixoHorizontal;

                slopeAngle = Math.atan(tangente);
            }
        }

        return slopeAngle;
    }

    /**
     * Devolve a inclinação em radianos a partir do slope em percentagem
     * num valor de 0 a 100 sendo transformado num valor de 0 a 1, por divisão
     * por 100.
     *
     * @param slope
     * @return inclinação em radianos
     */
    public static double determinarInclinacaoAPartirDeSlopePercentage(double slope) {

        double slopeEntreZeroEUm = slope / 100;

        return Math.atan(slopeEntreZeroEUm);

    }

    /**
     * Devolve o slope, em unidades de percentagem,a partir da inclinação em
     * radianos
     *
     * @param inclinacao inclinação
     * @return slope
     */
    public static double determinarSlopeAPartirDeInclinacao(double inclinacao) {

        double slopeDecimal = Math.tan(inclinacao);

        return Math.round(slopeDecimal * 100);

    }

    /**
     * Método que permite calcular a componente vertical ou horizontal
     * do vetor velocidade, dado o ângulo em relação à superfície terrrestre, 
     * a norma do vetor (o valor da velocidade veloc) e um parâmetro flag,
     * que identifica se se pretende obter a vertical ou a horizontal
     * 
     * @param angulo angulo em radianos
     * @param veloc velocidade em km/h
     * @param flag  True se for na vertical, false na horizontal
     * @return velocidade
     */
    public static double calcularComponenteVelocidade(double angulo, double veloc, boolean flag) {

        double velocComponente;

        if (veloc == 0.0) {
            velocComponente = 0.0;
        } else {

            if (flag) {
                velocComponente = Math.sin(angulo) * veloc;

            } else {
                velocComponente = Math.cos(angulo) * veloc;
            }
        }

        return velocComponente;
    }

    /**
     * Determina a velocidade relativa do veiculo em relação à do vento no
     * percurso entre dois pontos. Deve receber duas velocidades na mesma
     * unidade e devolve outra velocidade nessa unidade.
     *
     * @param velocVeiculo velocidade do veiculo
     * @param direcaoVentoEstrada direção do vento (em graus)
     * @param velocVentoLocal velocidade do vento local
     * @param lat1 latitude 1 do ponto de partida
     * @param lon1 longitude 1 do ponto de partida
     * @param lat2 latitude 2 do ponto de chegada
     * @param lon2 longitude 2 do ponto de chegada
     * @return velocidade relativa do veiculo em relação ao vento
     */
    public static double calcularVelocidadeRelativaVeiculoVento(double velocVeiculo, double direcaoVentoEstrada, double velocVentoLocal, double lat1, double lon1, double lat2, double lon2) {

        double velocidadeRVeiculoVento;

        if (velocVentoLocal == 0) {
            
            velocidadeRVeiculoVento = velocVeiculo;
            
        } else {
            double direcaoCaminho = calcularAnguloBearingCaminhoEntrePontos(lat1, lon1, lat2, lon2);

            double direcaoRelativaVento = direcaoCaminho - direcaoVentoEstrada;

            velocidadeRVeiculoVento = Math.cos(Math.toRadians(Math.abs(direcaoRelativaVento))) * velocVentoLocal * velocVeiculo;
        }

        return velocidadeRVeiculoVento;
    }

    /**
     * Método que permite calcular a potencia necessária para executar um movimento
     * com uma certa inclinação
     * @param forcaG força gravítica sobre o corpo (N)
     * @param velocY componente vertical do do vetor velocidade 
     * @return potencia para executar um movimento em inclinação (W)
     */
    public static double calcularPotenciaInclinacao(double forcaG, double velocY) {

        return forcaG * velocY;

    }

    /**
     * Método que calcula a potencia necessária para vencer a fricção de rolamento
     * /atrito.
     * 
     * @param forcaG força gravítica sobre o corpo (N)
     * @param velocX componente horizontal do vetor velocidade 
     * 
     * @return potencia para executar um movimento com fricção/ atrito (W)
     */
    public static double calcularPotenciaFriccao(double forcaG, double velocX) {

        return forcaG * RRCDEFEITO * velocX;

    }

    /**
     * Metodo que calcula o fator de resistencia do ar a ser aplicado num movimento
     * retilíneo sobre estrada (com várias aproximações por simplicidade de cálculo)
     * 
     * @return fator de resistencia do ar
     */
    public static double calcularFactorConstantesResAr() {

        return 0.5 * DENSIDADEAR * COEFICENTERESISTENCIAAR;
    }

    /**
     * Metodo que permite calcular o fator que envolve a interferência da area frontal
     * do veículo na determinação da potência da força de resistência aerodinâmica
     * 
     * 
     * @param areaFrontalVeiculo area frontal do veiculo (m²)
     * @param velocRelativa velocidade relativa 
     * @return fator da area frontal na velocidade relativa 
     */
    public static double calcularFactorAreaFrontalVelocidadeRelativa(double areaFrontalVeiculo, double velocRelativa) {

        double areaFrontalTotal = areaFrontalVeiculo + 0.1; // assumindo que 0.1 m² é o excedente do estafeta em relação à área frontal scooter

        if (velocRelativa < 0) {
            return areaFrontalTotal * Math.pow(velocRelativa, 2);
        } else {
            return -1 * areaFrontalTotal * Math.pow(velocRelativa, 2);
        }

    }

    /**
     * Metodo que permite calcular a potencia da resistencia aerodinãmica
     * 
     * @param areaFrontalVeiculo area frontal do veiculo (m²)
     * @param velocRelativa velocidade relativa
     * @param velocVeic velocidade veiculo
     * @return potencia da resistencia do ar (W)
     */
    public static double calcularPotenciaResistenciaAr(double areaFrontalVeiculo, double velocRelativa, double velocVeic) {

        return calcularFactorConstantesResAr() * calcularFactorAreaFrontalVelocidadeRelativa(areaFrontalVeiculo, velocRelativa) * velocVeic;

    }

    /**
     * Calcula a potência necessária para o trajeto entre os dois pontos
     * assinalados, usando scooter (W)
     *
     * @param estfMassa massa do estafeta
     * @param sctMassa massa do scooter
     * @param cargaMassa massa da carga
     * @param sctAreaFrontal area frontal da scooter
     * @param velocVeiculo velocidado do veiculo
     * @param velocVentoLocal velocidade do vento local
     * @param direcaoVentoEstrada direção do venta na estrada
     * @param e1Lat latitude do endereço origem
     * @param e1Lon longitude do endereço origem
     * @param e1Alt altitude do endereço origem
     * @param e2Lat latitude do endereço destino
     * @param e2Lon longitude do endereço destino
     * @param e2Alt altitude do endereço destino
     * @return potência necessária
     */
    public static double calcularPotenciaNecessariaScooter(double estfMassa,
            double sctMassa,
            double cargaMassa,
            double sctAreaFrontal,
            double velocVeiculo,
            double velocVentoLocal,
            double direcaoVentoEstrada,
            double e1Lat,
            double e1Lon,
            double e1Alt,
            double e2Lat,
            double e2Lon,
            double e2Alt) {

        double slopeA = calcularInclinacaoAPartirDeCoordenadas(e1Lat, e1Lon, e1Alt, e2Lat, e2Lon, e2Alt);

        double velocMedia = velocVeiculo;

        double massa = calcularMassaTotalTransporte(cargaMassa, sctMassa, estfMassa);

        double velRelVento = calcularVelocidadeRelativaVeiculoVento(velocMedia, direcaoVentoEstrada, velocVentoLocal, e1Lat, e1Lon, e2Lat, e2Lon);

        double velocY = calcularComponenteVelocidade(slopeA, velocMedia, true);

        double velocX = calcularComponenteVelocidade(slopeA, velocMedia, false);

        double forcaG = calcularForcaGraviticaThrust(massa);

        double potenciaInercia = 0.0;

        double potenciaInclinacao = calcularPotenciaInclinacao(forcaG, velocY);

        double potenciaFriccao = calcularPotenciaFriccao(forcaG, velocX);

        double potenciaResistenciaAr;
        if (sctAreaFrontal == AREAFRONTALMEDIASCOOTER){
             potenciaResistenciaAr = calcularPotenciaResistenciaAr(AREAFRONTALMEDIASCOOTER, velRelVento, velocMedia);
        }    else  potenciaResistenciaAr = calcularPotenciaResistenciaAr(sctAreaFrontal, velRelVento, velocMedia);
        
        

        // total power (W)
        return potenciaInercia + potenciaInclinacao + potenciaFriccao + potenciaResistenciaAr;
    }

    /**
     * Determina o tempo para o percurso entre dois pontos (em segundos)
     *
     * @param veloc velocidade do veiculo (km/h)
     * @param e1Lat latitude do endereço origem
     * @param e1Lon longitude do endereço origem
     * @param e1Alt altitude do endereço origem
     * @param e2Lat latitude do endereço destino
     * @param e2Lon longitude do endereço destino
     * @param e2Alt altitude do endereço destino
     * @return
     */
    public static double calcularTempoPercursoScooter(double veloc, double e1Lat,
            double e1Lon,
            double e1Alt,
            double e2Lat,
            double e2Lon,
            double e2Alt) {

        double velocMedia = veloc * 1000 / 3600; //as velocidades são introduzidas em km/h
        double tempoPercurso;
        double distanciaPercorrida;
        if (velocMedia != 0.0) {
            distanciaPercorrida = Calculos.distance(e1Lat, e1Lon, e1Alt, e2Lat, e2Lon, e2Alt);
            tempoPercurso = distanciaPercorrida / velocMedia;
        } else {
            tempoPercurso = ERRO;
        }

        return tempoPercurso;
    }

    /**
     * Determina a Energia necessária para executar um movimento entre dois pontos (Wh)
     *dadas certas condições.
     * 
     * @param estfMassa massa do estafeta
     * @param sctMassa massa da scooter
     * @param cargaMassa massa da carga
     * @param sctAreaFrontal area frontal da scooter
     * @param velocVeiculo velocidade do veiculo
     * @param velocVentoLocal velocidade do vento local
     * @param direcaoVentoEstrada direção do vento na estrada
     * @param e1Lat latitude do endereço origem
     * @param e1Lon longitude do endereço origem
     * @param e1Alt altitude do endereço origem
     * @param e2Lat latitude do endereço destino
     * @param e2Lon longitude do endereço destino
     * @param e2Alt altitude do endereço destino
     * @return Energia entre dois pontos
     */
    public static Double calcularEnergiaNecessariaScooter(double estfMassa,
            double sctMassa,
            double cargaMassa,
            double sctAreaFrontal,
            double velocVeiculo,
            double velocVentoLocal,
            double direcaoVentoEstrada,
            double e1Lat,
            double e1Lon,
            double e1Alt,
            double e2Lat,
            double e2Lon,
            double e2Alt) {

        double velocTemp = velocVeiculo;
        double energia ;

        if (velocTemp != 0.0) {
            double potencia = calcularPotenciaNecessariaScooter(estfMassa,
                    sctMassa,
                    cargaMassa,
                    sctAreaFrontal,
                    velocTemp,
                    velocVentoLocal,
                    direcaoVentoEstrada,
                    e1Lat,
                    e1Lon,
                    e1Alt,
                    e2Lat,
                    e2Lon,
                    e2Alt);

            double tempoEntrePontos = calcularTempoPercursoScooter(velocTemp, e1Lat, e1Lon, e1Alt, e2Lat, e2Lon, e2Alt);

            //          W     *     h
            energia = Math.abs(potencia * tempoEntrePontos / 3600);

        } else energia = ERRO;

        return energia;
    }

    /**
     * Método que permite calcular a potencia necessaria na subida e descida do drone
     * @param massaTotal massa total do drone mais carga (kg)
     * @param areaFrontal area frontal do drone (m²)
     * @return potencia necessaria na subida e descida do drone (W)
     */
    public static double calcularPotenciaNecessariaSubidaDescidaDrone(double massaTotal, double areaFrontal) {

        double thrust = calcularForcaGraviticaThrust(massaTotal);

        double numerador = Math.pow(Math.sqrt(thrust), 3);

        if (areaFrontal == 0) {
            
            return ERRO;
            
        } else {
            double denominador = Math.sqrt(2 * DENSIDADEAR * areaFrontal);

            return numerador / denominador;

        }

    }

    /**
     * Devolve a potencia do movimento horizontal do drone em W
     *
     * @param massaTotal massa total do drone mais carga (kg)
     * @param velocVeiculo velocidade do veiculo (m/s)
     * @return potencia do movimento horizontal em W
     */
    public static double calcularPotenciaNecessariaMovHorizontalDrone(double massaTotal,
            double velocVeiculo) {

        double thrust = calcularForcaGraviticaThrust(massaTotal);

        return (thrust * velocVeiculo / (EFICIENCIATRANSFPOTENC * LIFTDRAGRATIO)) + CONSUMOPOTENCIA;

    }

    /**
     * Determina a Potencia aproximada em W para o movimento do drone
     *
     * @param drnMassa massa do drone em kg
     * @param cargaMassa massa da carga em kg
     * @param velocVeiculo velocidade do veiculo em m/s
     *
     * @return Potencia aproximada em W para o movimento do drone
     */
    public static double calcularPotenciaNecessariaDrone(double drnMassa,
            double cargaMassa,
            double velocVeiculo) {

        double massaTotal = calcularMassaTotalTransporte(cargaMassa, drnMassa, 0.0);

        if (massaTotal > PESOMEDIODRONE + PESOMAXENTREGADRONE) {
            
            return ERRO;

        } else {

            double areaFrontal = AREAFRONTALMEDIADRONE;

            double potenciaSubidaDescida = calcularPotenciaNecessariaSubidaDescidaDrone(massaTotal, areaFrontal) * 2;

            double potenciaHorizontal = calcularPotenciaNecessariaMovHorizontalDrone(massaTotal,
                    velocVeiculo);

            return potenciaSubidaDescida + potenciaHorizontal;
        }
    }

    /**
     * calcular o tempo de subida ou descida do drone
     * @param altitude altitude (m)
     * @return tempo de subida ou descida do drone (segundos)
     */
    public static double calcularTempoSubidaOuDescidaDrone(double altitude) {

        
        double subtrator = altitude + 10.0;

        double tempo;
        
        if (altitude < 140.0 && altitude >= 0.0) {
            
            tempo = (150.0 - subtrator) / VELOCMEDDRONESUBIDADESC;
            
        } else tempo = ERRO;

        return tempo;
    }

    /**
     * Método que calcula o tempo necessário em movimento horizontal do drone
     * para percorrer o espaço entre dois pontos, dadas certas condições.
     * 
     * @param velocVento velocidade do vento em m/s
     * @param direcaoVento direção do vento
     * @param e1Lat latitude do endereço origem
     * @param e1Lon longitude do endereço origem
     * @param e2Lat latitude do endereço destino
     * @param e2Lon longitude do endereço destino
     * @return tempo Horizontal do drone
     */
    public static double calcularTempoHorizontalDrone(double velocVento, double direcaoVento, double e1Lat,
            double e1Lon,
            double e2Lat,
            double e2Lon) {

        double distanciaEntrePontosVoo = distance(e1Lat, e1Lon, 150.0, e2Lat, e2Lon, 150.0);

        if (distanciaEntrePontosVoo == 0) {

            return ERRO;

        } else {

            double velocHoriz = calcularVelocidadeRelativaVeiculoVento(VELOCIDADEMEDDRONE, direcaoVento, velocVento, e1Lat, e1Lon, e2Lat, e2Lon);

            return distanciaEntrePontosVoo / velocHoriz;
        }
    }

    /**
     * Determina o tempo a gastar no percurso total (subida, voo, descida) pelo 
     * Drone (em segundos)
     *
     * @param velocVento velocidade do vento em m/s
     * @param direcaoVento direção do vento em graus
     * @param e1Lat latitude do endereço origem (graus)
     * @param e1Lon longitude do endereço origem (graus)
     * @param e1Alt altitude do endereço origem (metros)
     * @param e2Lat latitude do endereço destino (graus)
     * @param e2Lon longitude do endereço destino (graus)
     * @param e2Alt altitude do endereço destino (metros)
     * @return tempo a gastar no percurso pelo Drone (segundos)
     */
    public static double calcularTempoPercursoDrone(double velocVento, double direcaoVento, double e1Lat,
            double e1Lon,
            double e1Alt,
            double e2Lat,
            double e2Lon,
            double e2Alt) {

        double tempoFlag = calcularTempoHorizontalDrone(velocVento, direcaoVento, e1Lat, e1Lon, e2Lat, e2Lon);

        if (tempoFlag == ERRO) {
            
            return ERRO;
            
        } else {
            return calcularTempoSubidaOuDescidaDrone(e1Alt) + tempoFlag + calcularTempoSubidaOuDescidaDrone(e2Alt);
        }

    }

    /**
     * Método que calcula a energia do drone em subida e descida até uma altitude de
     * 150 metros ou desde uma altitude de 150 metros, respectivamente.
     * 
     * @param drnMassa massa do drone
     * @param cargaMassa massa da carga
     * @param alt altitude do ponto origem ou do ponto destino
     * @return energia do drone em subida e descida
     */
    public static double calcularEnergiaDroneSubidaDescida(double drnMassa,
            double cargaMassa,
            double alt) {

        double massaTotal = calcularMassaTotalTransporte(cargaMassa, drnMassa, 0.0);

        double aF = AREAFRONTALMEDIADRONE;

        double potenciaSubDesc = calcularPotenciaNecessariaSubidaDescidaDrone(massaTotal, aF);

        double tempoSubDesc = calcularTempoSubidaOuDescidaDrone(alt);

        if (tempoSubDesc == ERRO) {

            return ERRO;

        } else {
            return tempoSubDesc * potenciaSubDesc /3600;
        }

    }

    /**
     * Devolve o ratio of headwind to airspeed ao qual chamamos 
     * quocienteVentoFrontalContraMovimento.
     * 
     * @param velocVeiculo velocidade do veiculo (m/seg)
     * @param velocVentoLocal velocidade do vento local (m/seg)
     * @param direcaoVento direção do vento (em graus)
     * @param e1Lat latitude do endereço origem (graus)
     * @param e1Lon longitude do endereço origem (graus)
     * @param e2Lat latitude do endereço destino (graus)
     * @param e2Lon longitude do endereço destino (graus)
     * @return ratio of headwind to airspeed
     */
    public static double calcularquocienteVentoFrontalContraMovimento(double velocVeiculo,
            double velocVentoLocal,
            double direcaoVento,
            double e1Lat,
            double e1Lon,
            double e2Lat,
            double e2Lon) {

        double velocVentoFrontalRelativa = calcularVelocidadeRelativaVeiculoVento(velocVeiculo, direcaoVento, velocVentoLocal, e1Lat, e1Lon, e2Lat, e2Lon);

        if (velocVeiculo == 0.0) {
            
            return ERRO;
            
        } else {
            return Math.abs(velocVentoFrontalRelativa / velocVeiculo);
        }

    }

    /**
     * Método que calcula o coeficiente de atrito a multiplicar pela potência 
     * do drone, com o objetivo de determinar a energia necessária 
     * 
     * @param velocVeiculo velocidade do veiculo (m/s)
     * @param velocVentoLocal velocidade do vento local (m/s)
     * @param direcaoVento direção do vento (graus)
     * @param e1Lat latitude do endereço origem (graus)
     * @param e1Lon longitude do endereço origem (graus)
     * @param e2Lat latitude do endereço destino (graus)
     * @param e2Lon longitude do endereço destino (graus)
     * @return coeficiente de atrito do drone
     */
    public static double calcularCoeficienteAtritoDrone(double velocVeiculo,
            double velocVentoLocal,
            double direcaoVento,
            double e1Lat,
            double e1Lon,
            double e2Lat,
            double e2Lon) {

        double distanciaPercurso = distance(e1Lat, e1Lon, 0.0, e2Lat, e2Lon, 0.0);

        double qVFCM = calcularquocienteVentoFrontalContraMovimento(velocVeiculo, velocVentoLocal, direcaoVento, e1Lat, e1Lon, e2Lat, e2Lon);

        if (qVFCM != 1) {

            return distanciaPercurso / (1 - qVFCM);

        } else {

            return 1.0;
        }

    }

    /**
     * Método que permite calcular a energia necessaria para movimentar 
     * horizontalmente o drone
     * 
     * @param drnMassa massa do drone (kg)
     * @param cargaMassa massa da carga (kg)
     * @param velocVeiculo velocidade do veiculo (m/s)
     * @param velocVentoLocal velocidade do vento local (m/s)
     * @param direcaoVento direção do vento  (graus)
     * @param e1Lat latitude do endereço origem (graus)
     * @param e1Lon longitude do endereço origem (graus)
     * @param e2Lat latitude do endereço destino (graus)
     * @param e2Lon longitude do endereço destino (graus)
     * @return energia necessaria para movimentar horizontalmente o drone
     */
    public static double calcularEnergiaNecessariaMovHorizDrone(double drnMassa,
            double cargaMassa,
            double velocVeiculo,
            double velocVentoLocal,
            double direcaoVento,
            double e1Lat,
            double e1Lon,
            double e2Lat,
            double e2Lon) {

        double energia;

        double distanciaPercurso = distance(e1Lat, e1Lon, 0.0, e2Lat, e2Lon, 0.0);

        double coefAD = calcularCoeficienteAtritoDrone(velocVeiculo, velocVentoLocal, direcaoVento, e1Lat, e1Lon, e2Lat, e2Lon);

        if (velocVeiculo != 0.0 && distanciaPercurso != 0.0) {

            double massaTotal = calcularMassaTotalTransporte(cargaMassa, drnMassa, 0.0);

            double potenciaHoriz = calcularPotenciaNecessariaMovHorizontalDrone(massaTotal, velocVeiculo);

            // para devolver em Wh, o resultado deve ser dividido por 3600, já que com os valores introduzidos nas unidades usadas, o resultado seria em Wseg
            energia = potenciaHoriz * coefAD / (velocVeiculo * 3600);

        } else energia = ERRO;
        
        return energia;
    }

    /**
     * Determina a energia necessária para um percurso entre dois pontos (Wh)
     *
     * @param drnMassa massa do drone (kg)
     * @param cargaMassa massa da carga (kg)
     * @param velocVeiculo velocidade do veiculo (m/s)
     * @param velocVentoLocal velocidade do vento local (m/s)
     * @param direcaoVento direção do vento  (graus)
     * @param e1Lat latitude do endereço origem (graus)
     * @param e1Lon longitude do endereço origem (graus)
     * @param e1Alt altitude do endereço origem (metros)
     * @param e2Lat latitude do endereço destino (graus)
     * @param e2Lon longitude do endereço destino (graus)
     * @param e2Alt altitude do endereço destino (metros)
     * @return energia em (Wh)
     */
    public static double calcularEnergiaNecessariaDrone(double drnMassa,
            double cargaMassa,
            double velocVeiculo,
            double velocVentoLocal,
            double direcaoVento,
            double e1Lat,
            double e1Lon,
            double e1Alt,
            double e2Lat,
            double e2Lon,
            double e2Alt) {

        double energia;

        double energiaSubida = calcularEnergiaDroneSubidaDescida(drnMassa, cargaMassa, e1Alt);
        double energiaDescida = calcularEnergiaDroneSubidaDescida(drnMassa, cargaMassa, e2Alt);

        double energiaMovHoriz = calcularEnergiaNecessariaMovHorizDrone(drnMassa, cargaMassa, velocVeiculo, velocVentoLocal, direcaoVento, e1Lat, e1Lon,  e2Lat, e2Lon);

        if (energiaSubida != ERRO && energiaDescida != ERRO && energiaMovHoriz != ERRO) {

            energia = Math.abs(energiaSubida + energiaDescida + energiaMovHoriz);
            
        } else energia = ERRO;

        return energia;

    }

}
