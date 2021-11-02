
#include <stdio.h>

#include <stdlib.h>

#include "estimate.h"

int estimateC(char *nomeFich)
{

FILE * fPointer;


  char line[150];

  struct dados * d;
  //allocate 4 integers in the heap for struct dados
  d = (struct dados *)malloc (sizeof ( struct dados ));
  
  //if 0 then file is ok
  int flag = 0;

  int capacidadev = 0;

  int eficienciav = 0;

  int cargarestantev = 0;

  int correntedebitadav = 0;

  int estimation = 0;


//opens file
  fPointer = fopen(nomeFich, "r");
  
  //reads from file a line with a value to use in the assembly
  
  fgets(line, 150, fPointer);
  capacidadev = atoi(line);
  d->capacidade = capacidadev;
  printf("Capacidade = %d\n", capacidadev);
  
  //reads from file a line with a value to use in the assembly
  fgets(line, 150, fPointer);

  eficienciav = atoi(line);
  d->eficiencia = eficienciav;
  printf("Eficiencia = %d\n", eficienciav);
  //reads from file a line with a value to use in the assembly
  fgets(line, 150, fPointer);

  cargarestantev = atoi(line);
  d->cargarestante = cargarestantev;
   printf("Carga Restante = %d \n", cargarestantev);
  printf("Carga Restante = %d \n", cargarestantev);

  //reads from file a line with a value to use in the assembly
  fgets(line,150,fPointer);
  
  correntedebitadav = atoi(line);
  
  d->correntedebitada =  correntedebitadav;
  printf("correntedebitadav = %d",correntedebitadav);
     
      
  //closes file
  fclose(fPointer);

  if (capacidadev == 0 || eficienciav == 0 || cargarestantev == 0) {

    flag = 1;

  }

  if (eficienciav > 100 || eficienciav < 0) {

    flag = 1;

  }
  if (cargarestantev > 100 || cargarestantev < 0) {

    flag = 1;

  }

  //if file is ok
  if (!flag) {

    
    //estimation = estimate(capacidadev, eficienciav, cargarestantev);
     //Irá passar por parâmetro o pointer para a struct d
    estimation = estimate(d);
   
    free(d);

  }
    return estimation;
}
