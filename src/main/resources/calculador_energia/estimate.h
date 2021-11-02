#ifndef ESTIMATE_H// Avoid duplicates
#define ESTIMATE_H
// function implemented in Assembly
//int estimate(int capacidade, int eficiencia, int cargarestante);
struct dados {
    int capacidade;
    int eficiencia;
    int cargarestante;
    int correntedebitada;
    } ;
int estimate( struct dados *d);
int match(char *regexp, char *text);
int estimateC(char *nomeFich);
int existeFlag(char *nomeFichData);
int EndsWith(const char *str, const char *suffix);
int writeEstimation(int estimation,  char *outputDir,  char *finalFile);
#endif 
