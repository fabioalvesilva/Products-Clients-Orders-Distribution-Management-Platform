/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import static java.lang.Math.PI;
import lapr.project.model.Endereco;
import static lapr.project.utils.Constantes.AREAFRONTALMEDIASCOOTER;
import static lapr.project.utils.Constantes.ERRO;
import static lapr.project.utils.Constantes.PESOMEDIOSCOOTER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.jupiter.api.Test;


/**
 *
 * @author carlos
 */
public class CalculosTest {
    
    public CalculosTest() {
    }
    
    
    /**
     * Test of distance method, of class Calculos.
     */
    @Test
    public void testDistance() {
        System.out.println("distance");
        double lat1 = 0.0;
        double lon1 = 0.0;
        double lat2 = 0.0;
        double lon2 = 0.0;
        double alt1 = 0.0;
        double alt2 = 0.0;
        double lat3 = 2.0;
        double lon3 = -1.0;
        double lat4 = 2.0;
        double lon4 = -2.0;
        double alt3 = 4.0;
        double alt4 = 4.0;

        double expResult = 0.0;
        double result = Calculos.distance(lat1, lon1, alt1, lat2, lon2, alt2);
        double result2 = Calculos.distance(lat1, lon3, alt3, lat2, lon4, alt4);
        double result3 = Calculos.distance(lat1, lon1, alt1, lat2, lon2, alt3);
        double result4 = Calculos.distance(lat1, lon2, alt1, lat2, lon3, alt4);
        double result5 = Calculos.distance(lat1, lon1, alt1, lat3, lon2, alt2);
        double result6 = Calculos.distance(lat1, lon1, alt1, lat3, lon3, alt2);
        double result7 = Calculos.distance(lat1, lon1, alt1, lat3, lon2, alt3);
        double result8 = Calculos.distance(lat1, lon1, alt1, lat3, lon3, alt3);

        assertEquals(expResult, result, 0.0);
        assertNotEquals(expResult, result2, 0.0);
        assertNotEquals(expResult, result3, 0.0);
        assertNotEquals(expResult, result3, 0.0);
        assertNotEquals(expResult, result4, 0.0);
        assertNotEquals(expResult, result5, 0.0);
        assertNotEquals(expResult, result6, 0.0);
        assertNotEquals(expResult, result7, 0.0);
        assertNotEquals(expResult, result8, 0.0);
    }

    /**
     * Test of distance method, of class Calculos.
     */
    @Test
    public void testDistance2() {
        System.out.println("##Teste distancias##");

        double lat1 = -58.37723;
        double lon1 = -34.61315;
        double lat2 = -33.45694;
        double lon2 = -70.64827;

        double lat3 = -57.63591;
        double lon3 = -25.30066;
        double lat4 = -68.15;
        double lon4 = -16.5;

        double e1Lat2 = 41.15069754317418;
        double e1Lon2 = -8.59292000165335;
        double e1Alt2 = 20.0;
        double e2Lat2 = 41.148996;
        double e2Lon2 = -8.586592;
        double e2Alt2 = 20.0;
        double e3Alt2 = 70.0;
      

        double expResult = 3846711.13;
        double expResult1 = 1248353.22;
        double expResult2 = 562.6;
        double expResult3 = 564.82;

        double result = Calculos.distance(lat1, lon1, 0.0, lat2, lon2, 0.0);
        double result1 = Calculos.distance(lat3, lon3, 0.0, lat4, lon4, 0.0);
        double result2 = Calculos.distance(e1Lat2, e1Lon2, e1Alt2, e2Lat2, e2Lon2, e2Alt2);
        double result3 = Calculos.distance(e1Lat2, e1Lon2, e1Alt2, e2Lat2, e2Lon2, e3Alt2);
        

        assertEquals(expResult, result, 0.0);
        assertEquals(expResult1, result1, 0.0);
        assertEquals(expResult2, result2, 0.0);
        assertEquals(expResult3, result3, 0.0);
       
    }

    /**
     * Test of calcularInclinacaoAPartirDeCoordenadas method, of class Calculos.
     */
    @Test
    public void testCalcularInclinacaoAPartirDeCoordenadas() {
        System.out.println("##Teste Calcular Inclinação ##");

        Endereco e1 = new Endereco(1, "Rua do Chão", 10.0, 10.0, 100.0);
        Endereco e2 = new Endereco(1, "Rua do Adro", 12.0, 19.0, 100.0);
        Endereco e3 = new Endereco(1, "Rua do Chão de Baixo", 12.0, 19.0, 0.0);
        Endereco e4 = new Endereco(1, "Rua do Adro de Cima", 12.0, 20.0, 108764.99);

        double expResult = 0.0;
        double expResult1 = PI;
        double expResult2 = PI / 4;
        double result = Calculos.calcularInclinacaoAPartirDeCoordenadas(e1.getLatitude(), e1.getLongitude(), e1.getAltitude(), e2.getLatitude(), e2.getLongitude(), e2.getAltitude());
        double result1 = Calculos.calcularInclinacaoAPartirDeCoordenadas(e2.getLatitude(), e2.getLongitude(), e2.getAltitude(), e3.getLatitude(), e3.getLongitude(), e3.getAltitude());
        double result2 = Calculos.calcularInclinacaoAPartirDeCoordenadas(e3.getLatitude(), e3.getLongitude(), e3.getAltitude(), e4.getLatitude(), e4.getLongitude(), e4.getAltitude());

        assertEquals(expResult, result, 0.0);
        assertEquals(expResult1, result1, 0.0);
        assertEquals(expResult2, result2, 0.0);

    }

    /**
     * Test of determinarInclinacaoAPartirDeSlopePercentage method, of class
     * Calculos.
     */
    @Test
    public void testDeterminarInclinacaoAPartirDeSlopePercentage() {
        System.out.println("determinarInclinacaoAPartirDeSlopePercentage");
        double slope = 0.0;
        double expResult = 0.0;
        double result = Calculos.determinarInclinacaoAPartirDeSlopePercentage(slope);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of determinarSlopeAPartirDeInclinacao method, of class Calculos.
     */
    @Test
    public void testDeterminarSlopeAPartirDeInclinacao() {
        System.out.println("determinarSlopeAPartirDeInclinacao");
        double inclinacao = 0.0;
        double expResult = 0.0;
        double result = Calculos.determinarSlopeAPartirDeInclinacao(inclinacao);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of determinarInclinacaoAPartirDeSlopePercentage method, of class
     * Calculos.
     */
    @Test
    public void testDeterminarInclinacaoAPartirDeSlopePercentage2() {
        System.out.println("##Teste Determinar Inclinacao A Partir De Slope Percentage ##");

        double slopeP = 10.0;

        double expResult = 0.0996686525;
        double expResult1 = 1.569796327;
        double expResult2 = 0.0;
        double result = Calculos.determinarInclinacaoAPartirDeSlopePercentage(slopeP);

        assertEquals(expResult, result, 0.000001);
        assertNotEquals(expResult1, result, 0.000001);
        assertNotEquals(expResult2, result, 0.000001);

    }

    /**
     * Test of determinarSlopeAPartirDeInclinacao method, of class Calculos.
     */
    @Test
    public void testDeterminarSlopeAPartirDeInclinacao2() {
        System.out.println("##Teste Determinar Slope A Partir De Inclinacao ##");

        double inclinacao = PI / 4;

        double expResult = 100.00;
        double expResult1 = 0.0078541431;
        double expResult2 = 0.0;
        double result = Calculos.determinarSlopeAPartirDeInclinacao(inclinacao);

        assertEquals(expResult, result, 0.000001);
        assertNotEquals(expResult1, result, 0.000001);
        assertNotEquals(expResult2, result, 0.000001);

    }

    /**
     * Test of calcularMassaTotalTransporte method, of class Calculos.
     */
    @Test
    public void testCalcularMassaTotalTransporte() {
        System.out.println("calcularMassaTotalTransporte");
        double massaCarga = 0.0;
        double massaVeiculo = 0.0;
        double massaCondutor = 0.0;
        double massaCarga1 = 6.0;
        double massaVeiculo1 = 7.0;
        double massaCondutor1 = 8.0;
        double expResult = 0.0;
        double expResult2 = 21.0;
        double expResult3 = 13.0;
        double result = Calculos.calcularMassaTotalTransporte(massaCarga, massaVeiculo, massaCondutor);
        double result2 = Calculos.calcularMassaTotalTransporte(massaCarga1, massaVeiculo1, massaCondutor1);
        double result3 = Calculos.calcularMassaTotalTransporte(massaCarga1, massaVeiculo1, massaCondutor);
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult2, result2, 0.0);
        assertEquals(expResult3, result3, 0.0);
        assertNotEquals(-1.0, result3);
        assertNotEquals(7.0, result2);
        assertNotEquals(5.0, result2);

    }

    /**
     * Test of calcularForcaGraviticaThrust method, of class Calculos.
     */
    @Test
    public void testCalcularForcaGravitica() {
        System.out.println("calcularForcaGravitica");
        double massa = 0.0;
        double massa2 = 9.81;
        double expResult = 0.0;
        double expResult2 = 96.2361;
        double result = Calculos.calcularForcaGraviticaThrust(massa);
        double result2 = Calculos.calcularForcaGraviticaThrust(massa2);
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult2, result2, 0.0001);
        assertNotEquals(1, result2, 0.0001);

    }

    /**
     * Test of calcularComponenteVelocidade method, of class Calculos.
     */
    @Test
    public void testCalcularComponenteVelocidade() {
        System.out.println("calcularComponenteVelocidade");
        double angulo = 0.0;
        double veloc = 2.0;
        double veloc2 = 0.0;
        double veloc3 = 40.0;
        boolean flag = false;
        boolean flag2 = true;
        double expResult = 2.0;
        double expResult2 = 0.0;
        double expResult3 = 0.5;
        double expResult4 = 40.0;

        double result = Calculos.calcularComponenteVelocidade(angulo, veloc, flag);
        double result2 = Calculos.calcularComponenteVelocidade(angulo, veloc, flag2);
        double result3 = Calculos.calcularComponenteVelocidade(angulo, veloc2, flag2);
        double result4 = Calculos.calcularComponenteVelocidade(angulo, veloc3, flag2);
        double result5 = Calculos.calcularComponenteVelocidade(angulo, veloc3, flag);
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult2, result2, 0.0);
        assertEquals(expResult2, result3, 0.0);
        assertEquals(expResult4, result5, 0.0);
        assertEquals(expResult2, result4, 0.0);
        assertNotEquals(expResult3, result2, 0.0);

    }

    /**
     * Test of calcularPotenciaInclinacao method, of class Calculos.
     */
    @Test
    public void testCalcularPotenciaInclinacao() {
        System.out.println("calcularPotenciaInclinacaoFriccao");
        double forcaG = 0.0;
        double velocComponente = 0.0;
        double forcaG2 = 10.0;
        double velocComponente2 = 2.0;
        double forcaG3 = 1236.06;
        double expResult = 0.0;
        double expResult2 = 20.0;
        double result = Calculos.calcularPotenciaInclinacao(forcaG, velocComponente);
        double result2 = Calculos.calcularPotenciaInclinacao(forcaG2, velocComponente2);
        double result3 = Calculos.calcularPotenciaInclinacao(forcaG3, velocComponente);
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult, result3, 0.01);
        assertEquals(expResult2, result2, 0.0);
        assertNotEquals(5.0, result2, 0.0);

    }

    /**
     * Test of calcularPotenciaFriccao method, of class Calculos.
     */
    @Test
    public void testCalcularPotenciaFriccao() {
        System.out.println("calcularPotenciaFriccao");
        double forcaG = 0.0;
        double forcaG2 = 1236.06;
        double velocX = 0.0;
        double velocX2 = 40;
        double expResult = 0.0;
        double expResult2 = 24721.2;
        double expResult3 = 98884.8;
        double expResult4 = 61.803;
        double expResult5 = 15.45075;
        double result = Calculos.calcularPotenciaFriccao(forcaG, velocX);
        double result2 = Calculos.calcularPotenciaFriccao(forcaG2, velocX);
        double result3 = Calculos.calcularPotenciaFriccao(forcaG2, velocX2);
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult, result2, 0.0);
        assertEquals(expResult2, result3, 0.1);
        assertNotEquals(expResult3, result3, 0.0);
        assertNotEquals(expResult4, result3, 0.0);
        assertNotEquals(expResult5, result3, 0.0);
    }

    /**
     * Test of calcularFactorConstantesResAr method, of class Calculos.
     */
    @Test
    public void testCalcularFactorConstantesResAr() {
        System.out.println("calcularFactorConstantesResAr");
        double expResult = 0.67375;
        double expResult2 = 0.4489795918;
        double expResult3 = 0.5568181818;
        double expResult4 = 0.3710575139;
        double result = Calculos.calcularFactorConstantesResAr();
        assertEquals(expResult, result, 0.0000001);
        assertNotEquals(expResult2, result, 0.0000001);
        assertNotEquals(expResult3, result, 0.0000001);
        assertNotEquals(expResult4, result, 0.0000001);
    }

    /**
     * Test of calcularAnguloBearingCaminhoEntrePontos method, of class Calculos
     */
    @Test
    public void testcalcularAnguloBearingCaminhoEntrePontos() {
        System.out.println("calcularAnguloBearingCaminhoEntrePontos");
        double lat1 = 0.0;
        double lon1 = 0.0;
        double lat2 = 0.0;
        double lon2 = 0.0;

        double e1Lat2 = 41.15069754317418;
        double e1Lon2 = -8.59292000165335;
        double e2Lat2 = 41.148996;
        double e2Lon2 = -8.586592;

        double expResult = 0.0;
        double expResult2 = 109.649423846;
        double result = Calculos.calcularAnguloBearingCaminhoEntrePontos(lat1, lon1, lat2, lon2);
        double result2 = Calculos.calcularAnguloBearingCaminhoEntrePontos(e1Lat2, e1Lon2, e2Lat2, e2Lon2);
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult2, result2, 0.000001);

    }

    /**
     * Test of calcularVelocidadeRelativaVeiculoVento method, of class Calculos.
     */
    @Test
    public void testCalcularVelocidadeRelativaVeiculoVento() {
        System.out.println("calcularVelocidadeRelativaVeiculoVento");
        double velocVeiculo = 0.0;
        double velocVentoLocal = 0.0;
        double direcaoVentoEstrada = 0.0;
        double lat1 = 0.0;
        double lon1 = 0.0;
        double lat2 = 0.0;
        double lon2 = 0.0;

        double velocVeiculo2 = 40.0;
        double velocVentoLocal2 = 2.0;
        double direcaoVento2 = 60.0;

        double e1Lat = 41.15069754317418;
        double e1Lon = -8.59292000165335;
        double e2Lat = 41.148996;
        double e2Lon = -8.586592;

        double expResult = 0.0;
        double expResult2 = 40.0;
        double expResult3 = 51.79702093;
        double expResult4 = 12.94025523;
        double expResult5 = 0.0323731381;
        double expResult6 = 0.0080932845;

        double result = Calculos.calcularVelocidadeRelativaVeiculoVento(velocVeiculo, direcaoVentoEstrada, velocVentoLocal, lat1, lon1, lat2, lon2);
        double result2 = Calculos.calcularVelocidadeRelativaVeiculoVento(velocVeiculo2, direcaoVentoEstrada, velocVentoLocal, lat1, lon1, lat2, lon2);
        double result3 = Calculos.calcularVelocidadeRelativaVeiculoVento(velocVeiculo2, direcaoVento2, velocVentoLocal2, e1Lat, e1Lon, e2Lat, e2Lon);
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult2, result2, 0.0);
        assertEquals(expResult3, result3, 0.00001);
        assertNotEquals(expResult4, result3, 0.00001);
        assertNotEquals(expResult5, result3, 0.00001);
        assertNotEquals(expResult6, result3, 0.00001);
    }

    /**
     * Test of calcularFactorAreaFrontalVelocidadeRelativa method, of class
     * Calculos.
     */
    @Test
    public void testCalcularFactorAreaFrontalVelocidadeRelativa() {
        System.out.println("calcularFactorAreaFrontalVelocidadeRelativa");
        double areaFrontalVeiculo = 0.3;
        double velocRelativa = 38.85;
        double velocRelativa2 = -38.85;
        double expResult = -603.729;
        double expResult2 = 301.8645;
        double expResult3 = 0.4;
        double expResult4 = -3773.30625;
        double expResult5 = -2.5;
        double expResult6 = -0.0016563723;
        double result = Calculos.calcularFactorAreaFrontalVelocidadeRelativa(areaFrontalVeiculo, velocRelativa);
        double result2 = Calculos.calcularFactorAreaFrontalVelocidadeRelativa(areaFrontalVeiculo, velocRelativa2);
        assertEquals(expResult, result, 0.000001);
        assertEquals(-expResult, result2, 0.000001);
        assertNotEquals(expResult2, result, 0.000001);
        assertNotEquals(-expResult2, result2, 0.000001);
        assertNotEquals(expResult3, result, 0.000001);
        assertNotEquals(-expResult3, result2, 0.000001);
        assertNotEquals(expResult4, result2, 0.000001);
        assertNotEquals(expResult5, result2, 0.000001);
        assertNotEquals(expResult6, result2, 0.000001);
    }

    /**
     * Test of calcularPotenciaResistenciaAr method, of class Calculos.
     */
    @Test
    public void testCalcularPotenciaResistenciaAr() {
        System.out.println("calcularPotenciaResistenciaAr");

        double areaFrontalVeiculo = 0.0;
        double velocRelativa = 0.0;
        double velocVeicul = 0.0;
        double areaFrontalVeiculo2 = 0.3;
        double velocRelativa2 = 51.79702093;
        double velocVeicul2 = 40.0;
        double expResult = 0.0;
        double expResult2 = -28922.00025;
        double expResult3 = -0.0251124574;
        double expResult4 = -18.07625016;
        double expResult5 = -0.000015695;
        double result = Calculos.calcularPotenciaResistenciaAr(areaFrontalVeiculo, velocRelativa, velocVeicul);
        double result2 = Calculos.calcularPotenciaResistenciaAr(areaFrontalVeiculo2, velocRelativa2, velocVeicul2);
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult2, result2, 0.00001);
        assertNotEquals(expResult3, result2, 0.0001);
        assertNotEquals(expResult4, result2, 0.0001);
        assertNotEquals(expResult5, result2, 0.0001);
    }

    /**
     * Test of calcularPotenciaNecessariaScooter method, of class Calculos.
     */
    @Test
    public void testCalcularPotenciaNecessariaScooter() {
        System.out.println("calcularPotenciaNecessariaScooter");
        double estfMassa = 0.0;
        double sctMassa = 0.0;
        double cargaMassa = 0.0;
        double sctAreaFrontal = 0.0;
        double velocVeiculo = 0.0;
        double velocVentoLocal = 0.0;
        double direcaoVentoEstrada = 0.0;
        double e1Lat = 0.0;
        double e1Lon = 0.0;
        double e1Alt = 0.0;
        double e2Lat = 0.0;
        double e2Lon = 0.0;
        double e2Alt = 0.0;
        double expResult = 0.0;
        double result = Calculos.calcularPotenciaNecessariaScooter(estfMassa, sctMassa, cargaMassa, sctAreaFrontal, velocVeiculo, velocVentoLocal, direcaoVentoEstrada, e1Lat, e1Lon, e1Alt, e2Lat, e2Lon, e2Alt);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calcularPotenciaNecessariaScooter method, of class Calculos.
     */
    @Test
    public void testCalcularPotenciaNecessariaScooter2() {
        System.out.println("calcularPotenciaNecessariaScooter2");
        double estfMassa2 = 75.0;
        double sctMassa2 = PESOMEDIOSCOOTER;
        double cargaMassa2 = 1.0;
        double sctAreaFrontal2 = AREAFRONTALMEDIASCOOTER;
        double velocVeiculo2 = 40.0;
        double velocVentoLocal2 = 2.0;
        double direcaoVentoEstrada2 = 60.0;
        double e1Lat2 = 41.15069754317418;
        double e1Lon2 = -8.59292000165335;
        double e1Alt2 = 20.0;
        double e2Lat2 = 41.148996;
        double e2Lon2 = -8.586592;
        double e2Alt2 = 20.0;
        double e2Alt3 = 70.0;

        double expResult = -4200.79924;
        double expResult2 = 29184.04396;
        double expResult3 = 78.99303389;
        double expResult4 = -8674.70241;
        double expResult5 = -57922.99295;
        double expResult6 = 49169.29751;
           
        

        double result = Calculos.calcularPotenciaNecessariaScooter(estfMassa2, sctMassa2, cargaMassa2, sctAreaFrontal2, velocVeiculo2, velocVentoLocal2, direcaoVentoEstrada2, e1Lat2, e1Lon2, e1Alt2, e2Lat2, e2Lon2, e2Alt2);
        double result2 = Calculos.calcularPotenciaNecessariaScooter(estfMassa2, sctMassa2, cargaMassa2, sctAreaFrontal2, velocVeiculo2, velocVentoLocal2, direcaoVentoEstrada2, e1Lat2, e1Lon2, e1Alt2, e2Lat2, e2Lon2, e2Alt3);

        assertEquals(expResult, result, 0.001);
        assertEquals(expResult3, result2, 0.01);
        assertNotEquals(expResult2, result, 0.001);
        assertNotEquals(expResult3, result, 0.001);
         assertNotEquals(expResult4, result2, 0.1);
         assertNotEquals(-expResult4, result2, 0.1);
         assertNotEquals(expResult5, result2, 0.1);
         assertNotEquals(-expResult5, result2, 0.1);
         assertNotEquals(-expResult3, result2, 0.1);
         assertNotEquals(expResult6, result2, 0.1);
         assertNotEquals(-expResult6, result2, 0.1);
    }

    /**
     * Test of calcularTempoPercursoScooter method, of class Calculos.
     */
    @Test
    public void testCalcularTempoPercursoScooter() {
        System.out.println("calcularTempoEntreDoisPontos");
        double veloc = 0.0;
        double e1Lat = 0.0;
        double e1Lon = 0.0;
        double e1Alt = 0.0;
        double e2Lat = 0.0;
        double e2Lon = 0.0;
        double e2Alt = 0.0;
        double expResult = ERRO;
        double result = Calculos.calcularTempoPercursoScooter(veloc, e1Lat, e1Lon, e1Alt, e2Lat, e2Lon, e2Alt);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calcularTempoPercursoScooter method, of class Calculos.
     */
    @Test
    public void testCalcularTempoEntreDoisPontos2() {
        System.out.println("calcularTempoEntreDoisPontos2");
        double veloc = 40.0;
        double e1Lat = 41.15069754317418;
        double e1Lon = -8.59292000165335;
        double e1Alt = 20.0;
        double e2Lat = 41.148996;
        double e2Lon = -8.586592;
        double e2Alt = 20.0;
        double expResult = 50.6;
        double result = Calculos.calcularTempoPercursoScooter(veloc, e1Lat, e1Lon, e1Alt, e2Lat, e2Lon, e2Alt);

        assertEquals(expResult, result, 0.1);

    }

    /**
     * Test of calcularEnergiaNecessariaScooter method, of class Calculos.
     */
    @Test
    public void testCalcularEnergiaNecessariaScooter() {
        System.out.println("calcularEnergiaEntreDoisPontosScooter");
        double estfMassa = 0.0;
        double sctMassa = 0.0;
        double cargaMassa = 0.0;
        double sctAreaFrontal = 0.0;
        double velocVeiculo = 0.0;
        double velocVentoLocal = 0.0;
        double direcaoVentoEstrada = 0.0;
        double e1Lat = 0.0;
        double e1Lon = 0.0;
        double e1Alt = 0.0;
        double e2Lat = 0.0;
        double e2Lon = 0.0;
        double e2Alt = 0.0;
        Double expResult = ERRO;
        Double result = Calculos.calcularEnergiaNecessariaScooter(estfMassa, sctMassa, cargaMassa, sctAreaFrontal, velocVeiculo, velocVentoLocal, direcaoVentoEstrada, e1Lat, e1Lon, e1Alt, e2Lat, e2Lon, e2Alt);
        assertEquals(expResult, result);
    }

    /**
     * Test of calcularEnergiaNecessariaScooter method, of class Calculos.
     */
    @Test
    public void testCalcularEnergiaEntreDoisPontosScooter2() {
        System.out.println("calcularEnergiaEntreDoisPontosScooter2");
        double estfMassa2 = 75.0;
        double sctMassa2 = 50.0;
        double cargaMassa2 = 1.0;
        double sctAreaFrontal2 = 0.3;
        double velocVeiculo2 = 40.0;
        double velocVentoLocal2 = 2.0;
        double direcaoVentoEstrada2 = 60.0;
        double e1Lat2 = 41.15069754317418;
        double e1Lon2 = -8.59292000165335;
        double e1Alt2 = 20.0;
        double e2Lat2 = 41.148996;
        double e2Lon2 = -8.586592;
        double e2Alt2 = 20.0;
        
        double expResult = 59.084241;
        double expResult1 = 0.0230455559;
        double expResult2 = 298670.4046;
        double expResult3 = 765731767.4;
        
        double result = Calculos.calcularEnergiaNecessariaScooter(estfMassa2, sctMassa2, cargaMassa2, sctAreaFrontal2, velocVeiculo2, velocVentoLocal2, direcaoVentoEstrada2, e1Lat2, e1Lon2, e1Alt2, e2Lat2, e2Lon2, e2Alt2);
        assertEquals(expResult, result, 0.01);
         assertNotEquals(expResult1, result, 0.01);
         assertNotEquals(expResult2, result, 0.01);
         assertNotEquals(expResult3, result, 0.01);
    }

    /**
     * Test of calcularPotenciaNecessariaSubidaDescidaDrone method, of class
     * Calculos.
     */
    @Test
    public void testcalcularPotenciaNecessariaSubidaDescidaDrone() {
        System.out.println("calcularPotenciaNecessariaSubidaDescidaDrone");
        double massa = 0.0;
        double areaFrontal = 0.0;
        double massa1 = 6.0;
        double areaFrontal1 = 0.7;

        double expResult = ERRO;
        double expResult1 = 344.8246663;
        double expResult2 = 422.410216;
        double expResult3 = 295.6871512;
        double expResult4 = 241.3772664;
        double expResult5 = 844.820432;
        double expResult6 = 689.6493326;
        double expResult7 = 482.7545328;

        double result = Calculos.calcularPotenciaNecessariaSubidaDescidaDrone(massa, areaFrontal);
        double result1 = Calculos.calcularPotenciaNecessariaSubidaDescidaDrone(massa1, areaFrontal1);

        assertEquals(expResult, result, 0.0);
        assertEquals(expResult1, result1, 0.00001);
        assertNotEquals(expResult2, result1, 0.00001);
        assertNotEquals(expResult3, result1, 0.00001);
        assertNotEquals(expResult4, result1, 0.00001);
        assertNotEquals(expResult5, result1, 0.00001);
        assertNotEquals(expResult6, result1, 0.00001);
        assertNotEquals(expResult7, result1, 0.00001);
    }

    /**
     * Test of calcularPotenciaNecessariaMovHorizontalDrone method, of class
     * Calculos.
     */
    @Test
    public void testcalcularPotenciaNecessariaMovHorizontalDrone() {
        System.out.println("calcularPotenciaNecessariaMovHorizontalDrone");
        double massaTotal = 0.0;
        double velocVeiculo = 0.0;
        double massaTotal1 = 6.8;
        double velocVeiculo1 = 10.0;

        double expResult = 100;
        double expResulta = 544.72;
        double expResult1 = 1100.62;
        double expResult2 = 344.72;
        double expResult3 = 900.62;
        double expResult4 = 144.472;
        double expResult5 = 200.062;
        double expResult6 = -55.528;
        double expResult7 = 0.062;
        double expResult8 = 500.249601;
        double expResult9 = 111.1179555;
        double expResult10 = 300.249601;
        double expResult11 = -88.88204447;
        double expResult12 = 11.17955528;
        double expResult13 = 3902.49601;
        double expResult14 = 211.1795553;
        double expResult15 = 4102.49601;

        double result = Calculos.calcularPotenciaNecessariaMovHorizontalDrone(massaTotal, velocVeiculo);
        double result1 = Calculos.calcularPotenciaNecessariaMovHorizontalDrone(massaTotal1, velocVeiculo1);
        assertEquals(expResult, result, 0.0);
        assertEquals(expResulta, result1, 0.0);
        assertNotEquals(expResult1, result1, 0.0);
        assertNotEquals(expResult2, result1, 0.0);
        assertNotEquals(expResult3, result1, 0.0);
        assertNotEquals(expResult4, result1, 0.0);
        assertNotEquals(expResult5, result1, 0.0);
        assertNotEquals(expResult6, result1, 0.0);
        assertNotEquals(expResult7, result1, 0.0);
        assertNotEquals(expResult8, result1, 0.0);
        assertNotEquals(expResult9, result1, 0.0);
        assertNotEquals(expResult10, result1, 0.0);
        assertNotEquals(expResult11, result1, 0.0);
        assertNotEquals(expResult12, result1, 0.0);
        assertNotEquals(expResult13, result1, 0.0);
        assertNotEquals(expResult14, result1, 0.0);
        assertNotEquals(expResult15, result1, 0.0);

    }

    /**
     * Test of calcularPotenciaNecessariaDrone method, of class Calculos.
     */
    @Test
    public void testcalcularPotenciaNecessariaDrone() {
        System.out.println("calcularPotenciaNecessariaDrone");
        double drnMassa = 5.3;
        double cargaMassa = 6.0;
        double velocVeiculo = 25.0;

        double cargaMassa2 = 0.7;

        double expResult = ERRO;
        double expResult1 = 2738.395022;
        double expResult2 = 1495.348756;
        double expResult3 = 576.3950224;
        double expResult4 = -666.6512444;

        double result = Calculos.calcularPotenciaNecessariaDrone(drnMassa, cargaMassa, velocVeiculo);
        double result1 = Calculos.calcularPotenciaNecessariaDrone(drnMassa, cargaMassa2, velocVeiculo);
        assertEquals(expResult, result, 0.001);
        assertNotEquals(expResult, result1, 0.001);
        assertEquals(expResult1, result1, 0.0001);
        assertNotEquals(expResult2, result1, 0.001);
        assertNotEquals(expResult3, result1, 0.001);
        assertNotEquals(expResult4, result1, 0.001);

    }

    /**
     * Test of calcularTempoSubidaOuDescidaDrone method, of class Calculos.
     */
    @Test
    public void calcularTempoSubidaOuDescidaDrone() {

        double alt = 86.0;
        double alt1 = 142.0;

        double expResult = 9.0;
        double expResult1 = ERRO;
        double expResult2 = 41.0;
        double expResult3 = 36.66666;
        double expResult4 = 12.333333;
        double expResult5 = 1476.0;
        double expResult6 = 1356.0;
        double expResult7 = 444.0;
        double expResult8 = 324.0;

        double result = Calculos.calcularTempoSubidaOuDescidaDrone(alt);
        double result1 = Calculos.calcularTempoSubidaOuDescidaDrone(alt1);
        assertEquals(expResult, result, 0.001);
        assertEquals(expResult1, result1, 0.001);
        assertNotEquals(expResult2, result, 0.001);
        assertNotEquals(expResult3, result, 0.001);
        assertNotEquals(expResult4, result, 0.001);
        assertNotEquals(expResult5, result, 0.001);
        assertNotEquals(expResult6, result, 0.001);
        assertNotEquals(expResult7, result, 0.001);
        assertNotEquals(expResult8, result, 0.001);
    }

    /**
     * Test of calcularTempoHorizontalDrone method, of class Calculos.
     */
    @Test
    public void calcularTempoHorizontalDrone() {

        double velocVento = 0.0;
        double direcaoVento = 0.0;
        double e1Lat = 0.0;
        double e1Lon = 0.0;
        double e2Lat = 0.0;
        double e2Lon = 0.0;
        double velocVento1 = 25.0;
        double e1Lat1 = 41.148996;
        double e1Lon1 = -8.586592;
        double e2Lat1 = 41.15069754317418;
        double e2Lon1 = -8.59292000165335;
        double direcVento1 = Calculos.calcularAnguloBearingCaminhoEntrePontos(e1Lat1, e1Lon1, e2Lat1, e2Lon1);

        double expResult = ERRO;
        double expResult1 = 1.4065;
        double expResult2 = 879.0625;
        double expResult3 = 225040;

        double result = Calculos.calcularTempoHorizontalDrone(velocVento, direcaoVento, e1Lat, e1Lon, e2Lat, e2Lon);
        double result1 = Calculos.calcularTempoHorizontalDrone(velocVento1, direcVento1, e1Lat1, e1Lon1, e2Lat1, e2Lon1);
        assertEquals(expResult, result, 0.001);
        assertEquals(expResult1, result1, 0.001);
        assertNotEquals(expResult2, result1, 0.001);
        assertNotEquals(expResult3, result1, 0.001);

    }

    /**
     * Test of calcularTempoPercursoDrone method, of class Calculos.
     */
    @Test
    public void testCalcularTempoPercursoDrone() {
        System.out.println("calcularTempoPercursoDrone");
        double velocVento = 0.0;
        double direcVento = 0.0;
        double e1Lat = 0.0;
        double e1Lon = 0.0;
        double e1Alt = 0.0;
        double e2Lat = 0.0;
        double e2Lon = 0.0;
        double e2Alt = 0.0;
        double expResult = ERRO;
        double result = Calculos.calcularTempoPercursoDrone(velocVento, direcVento, e1Lat, e1Lon, e1Alt, e2Lat, e2Lon, e2Alt);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calcularTempoPercursoDrone method, of class Calculos.
     */
    @Test
    public void testCalcularTempoPercursoDrone2() {
        System.out.println("calcularTempoPercursoDrone");
        double velocVento = 25.0;
        double e1Lat = 41.148996;
        double e1Lon = -8.586592;
        double e1Alt = 20.0;
        double e2Lat = 41.15069754317418;
        double e2Lon = -8.59292000165335;
        double e2Alt = 20.0;
        double direcVento = Calculos.calcularAnguloBearingCaminhoEntrePontos(e1Lat, e1Lon, e2Lat, e2Lon);
        double expResult = 41.4065;
        double result = Calculos.calcularTempoPercursoDrone(velocVento, direcVento, e1Lat, e1Lon, e1Alt, e2Lat, e2Lon, e2Alt);
        assertEquals(expResult, result, 0.001);

    }

    /**
     * Test of calcularEnergiaDroneSubidaDescida method, of class Calculos.
     */
    @Test
    public void testCalcularEnergiaDroneSubidaDescida() {

        double drnMassa = 0.0;
        double cargaMassa = 0.0;
        double alt = 0.0;
        double alt1 = 142.0;
        double drnMassa1 = 3.3;
        double cargaMassa1 = 2.5;
        double alt2 = 20.0;

        double expResult = 0.0;
        double expResult1 = ERRO;
        double expResult2 = 4.375610383;
        double expResult3 = 0.000007053689618;
        double expResult4 = 91.41581745;
        double expResult5 = 56707910.56;
        double result = Calculos.calcularEnergiaDroneSubidaDescida(drnMassa, cargaMassa, alt);
        double result1 = Calculos.calcularEnergiaDroneSubidaDescida(drnMassa, cargaMassa, alt1);
        double result2 = Calculos.calcularEnergiaDroneSubidaDescida(drnMassa1, cargaMassa1, alt2);
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult1, result1, 0.0);
        assertEquals(expResult2, result2, 0.00001);
        assertNotEquals(expResult3, result2, 0.00001);
         assertNotEquals(expResult4, result2, 0.00001);
          assertNotEquals(expResult5, result2, 0.00001);
    }

    /**
     * Test of calcularquocienteVentoFrontalContraMovimento method, of class
     * Calculos.
     */
    @Test
    public void testCalcularquocienteVentoFrontalContraMovimento() {
        double velocVeiculo = 0.0;
        double velocVentoLocal = 0.0;
        double direcaoVento = 0.0;
        double e1Lat = 0.0;
        double e1Lon = 0.0;
        double e2Lat = 0.0;
        double e2Lon = 0.0;
        double velocVeiculo1 = 14.0;
        double velocVentoLocal1 = 2.0;
        double e1Lat1 = 41.148996;
        double e1Lon1 = -8.586592;
        double e2Lat1 = 41.15069754317418;
        double e2Lon1 = -8.59292000165335;
        double direcaoVento1 = Calculos.calcularAnguloBearingCaminhoEntrePontos(e1Lat1, e1Lon1, e2Lat1, e2Lon1);

        double velocVeiculo2 = 12.5;
        double e1Lat2 = 41.148996;
        double e1Lon2 = -8.586592;
        double e2Lat2 = 41.238940;
        double e2Lon2 = -8.586591;
        double direcaoVento2 = Calculos.calcularAnguloBearingCaminhoEntrePontos(e1Lat2, e1Lon2, e2Lat2, e2Lon2);
        double direcaoVento3 = direcaoVento2 - 70.528779365509;

        double expResult = ERRO;
        double expResult1 = 2.0;
        double expResult2 = 392.0;
        double expResult3 = 0.6666666666666;
        double expResult4 = 104.166666666666;

        double result = Calculos.calcularquocienteVentoFrontalContraMovimento(velocVeiculo, velocVentoLocal, direcaoVento, e1Lat, e1Lon, e2Lat, e2Lon);
        double result1 = Calculos.calcularquocienteVentoFrontalContraMovimento(velocVeiculo1, velocVentoLocal1, direcaoVento1, e1Lat1, e1Lon1, e2Lat1, e2Lon1);
        double result2 = Calculos.calcularquocienteVentoFrontalContraMovimento(velocVeiculo2, velocVentoLocal1, direcaoVento3, e1Lat2, e1Lon2, e2Lat2, e2Lon2);

        assertEquals(expResult, result, 0.0);
        assertEquals(expResult1, result1, 0.0);
        assertEquals(expResult3, result2, 0.0001);
        assertNotEquals(expResult2, result1);
        assertNotEquals(expResult4, result2);

    }

    /**
     * Test of calcularCoeficienteAtritoDrone method, of class
     * Calculos.
     */
    @Test
    public void testCalcularCoeficienteAtritoDrone() {
        double velocVeiculo = 0.0;
        double velocVentoLocal = 0.0;
        double direcaoVento = 0.0;
        double e1Lat = 0.0;
        double e1Lon = 0.0;
        double e2Lat = 0.0;
        double e2Lon = 0.0;
        double velocVeiculo1 = 12.5;
        double velocVentoLocal1 = 2.0;

        double e1Lat1 = 41.148996;
        double e1Lon1 = -8.586592;
        double e2Lat1 = 41.238940;
        double e2Lon1 = -8.586591;
        double direcaoVento1 = Calculos.calcularAnguloBearingCaminhoEntrePontos(e1Lat1, e1Lon1, e2Lat1, e2Lon1);
        double direcaoVento2 = direcaoVento1 - 70.528779365509;

        double expResult = 0.0;
        double expResult1 = 30003.96;
        double expResult2 = 1.0;
        double expResult3 = 6000.791996;
        double expResult4 = 3333.773323;
        double expResult5 = 16668.86668;

        double result = Calculos.calcularCoeficienteAtritoDrone(velocVeiculo, velocVentoLocal, direcaoVento, e1Lat, e1Lon, e2Lat, e2Lon);
        double result1 = Calculos.calcularCoeficienteAtritoDrone(velocVeiculo1, velocVentoLocal1, direcaoVento2, e1Lat1, e1Lon1, e2Lat1, e2Lon1);
        double result2 = Calculos.calcularCoeficienteAtritoDrone(velocVeiculo1, velocVentoLocal, direcaoVento1, e1Lat1, e1Lon1, e2Lat1, e2Lon1);
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult1, result1, 0.0001);
        assertEquals(expResult2, result2, 0.0001);
        assertNotEquals(expResult3, result1, 0.001);
        assertNotEquals(expResult4, result1, 0.001);
        assertNotEquals(expResult5, result1, 0.001);

//    
    }

    /**
     * Test of calcularEnergiaNecessariaMovHorizDrone method, of class
     * Calculos.
     */
    @Test
    public void testCalcularEnergiaNecessariaMovHorizDrone() {
        double drnMassa = 4.0;
        double cargaMassa = 2.0;
        double velocVeiculo = 0.0;
        double velocVentoLocal = 0.0;
        double direcaoVento = 0.0;
        double e1Lat = 0.0;
        double e1Lon = 0.0;

        double e2Lat = 0.0;
        double e2Lon = 0.0;

        double velocVeiculo1 = 12.5;
        double velocVentoLocal1 = 2.0;

        double e1Lat1 = 41.148996;
        double e1Lon1 = -8.586592;
        double e2Lat1 = 41.238940;
        double e2Lon1 = -8.586591;
        double direcaoVento1 = Calculos.calcularAnguloBearingCaminhoEntrePontos(e1Lat1, e1Lon1, e2Lat1, e2Lon1);
        double direcaoVento2 = direcaoVento1 - 70.528779365509;

        double expResult = ERRO;
        double expResult1 = 393.7186318;
        double expResult2 = 0.0000004375;
        double expResult3 = 7972802.295;
        double expResult4 = 35102593469.0;
        double expResult5 = 885.6330938;
        double expResult6 = 0.000000437349676;
        double expResult7 = 0.00006833588687;
        double expResult8 = 5.6680518;

        double result = Calculos.calcularEnergiaNecessariaMovHorizDrone(drnMassa, cargaMassa, velocVeiculo, velocVentoLocal, direcaoVento, e1Lat, e1Lon, e2Lat, e2Lon);
        double result1 = Calculos.calcularEnergiaNecessariaMovHorizDrone(drnMassa, cargaMassa, velocVeiculo1, velocVentoLocal, direcaoVento, e1Lat, e1Lon, e2Lat, e2Lon);
        double result2 = Calculos.calcularEnergiaNecessariaMovHorizDrone(drnMassa, cargaMassa, velocVeiculo, velocVentoLocal, direcaoVento, e1Lat1, e1Lon1, e2Lat1, e2Lon1);
        double result3 = Calculos.calcularEnergiaNecessariaMovHorizDrone(drnMassa, cargaMassa, velocVeiculo1, velocVentoLocal1, direcaoVento2, e1Lat1, e1Lon1, e2Lat1, e2Lon1);

        assertEquals(expResult, result, 0.0);
        assertEquals(expResult, result1, 0.0);
        assertEquals(expResult, result2,0.0);
        assertEquals(expResult1, result3, 0.00001);
        assertNotEquals(expResult2, result3, 0.00001);
        assertNotEquals(expResult3, result3, 0.00001);
        assertNotEquals(expResult4, result3, 0.00001);
        assertNotEquals(expResult5, result3, 0.00001);
        assertNotEquals(expResult6, result3, 0.00001);
        assertNotEquals(expResult7, result3, 0.00001);
        assertNotEquals(expResult8, result3, 0.00001);
    }

    /**
     * Test of calcularEnergiaNecessariaDrone method, of class Calculos.
     */
    @Test
    public void testCalcularEnergiaNecessariaDrone() {
        System.out.println("calcularEnergiaNecessariaDrone");
        double drnMassa = 0.0;
        double cargaMassa = 0.0;
        double velocVeiculo = 0.0;
        double velocVentoLocal = 0.0;
        double direcaoVento = 0.0;
        double e1Lat = 0.0;
        double e1Lon = 0.0;
        double e1Alt = 0.0;
        double e2Lat = 0.0;
        double e2Lon = 0.0;
        double e2Alt = 0.0;
        double e1Alt1 = 142.0;

        double drnMassa1 = 4.0;
        double cargaMassa1 = 2.0;
        double velocVeiculo1 = 12.5;
        double velocVentoLocal1 = 2.0;

        double e1Lat1 = 41.148996;
        double e1Lon1 = -8.586592;
        double e2Lat1 = 41.238940;
        double e2Lon1 = -8.586591;
        double direcaoVento1 = Calculos.calcularAnguloBearingCaminhoEntrePontos(e1Lat1, e1Lon1, e2Lat1, e2Lon1);
        double direcaoVento2 = direcaoVento1 - 70.528779365509;

        double expResult = ERRO;
        double expResult1 = 404.46;

        double result = Calculos.calcularEnergiaNecessariaDrone(drnMassa, cargaMassa, velocVeiculo, velocVentoLocal, direcaoVento, e1Lat, e1Lon, e1Alt, e2Lat, e2Lon, e2Alt);
        double result1 = Calculos.calcularEnergiaNecessariaDrone(drnMassa, cargaMassa, velocVeiculo, velocVentoLocal, direcaoVento, e1Lat, e1Lon, e1Alt1, e2Lat, e2Lon, e2Alt);
        double result2 = Calculos.calcularEnergiaNecessariaDrone(drnMassa, cargaMassa, velocVeiculo, velocVentoLocal, direcaoVento, e1Lat, e1Lon, e1Alt, e2Lat, e2Lon, e1Alt1);
        double result3 = Calculos.calcularEnergiaNecessariaDrone(drnMassa1, cargaMassa1, velocVeiculo, velocVentoLocal, direcaoVento, e1Lat, e1Lon, e1Alt1, e2Lat, e2Lon, e2Alt);
        double result4 = Calculos.calcularEnergiaNecessariaDrone(drnMassa1, cargaMassa1, velocVeiculo1, velocVentoLocal, direcaoVento, e1Lat, e1Lon, e1Alt, e2Lat, e2Lon, e1Alt1);
        double result5 = Calculos.calcularEnergiaNecessariaDrone(drnMassa1, cargaMassa1, velocVeiculo1, velocVentoLocal1, direcaoVento2, e1Lat1, e1Lon1, e1Alt, e2Lat1, e2Lon1, e2Alt);

        assertEquals(expResult, result, 0.0);
        assertEquals(expResult, result1, 0.0);
        assertEquals(expResult, result2, 0.0);
        assertEquals(expResult, result3, 0.0);
        assertEquals(expResult, result4, 0.0);
        assertEquals(expResult1, result5, 0.01);
    }



}

