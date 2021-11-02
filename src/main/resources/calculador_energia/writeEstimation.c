#include <stdio.h>
#include "estimate.h"
#include <stdlib.h>
#include <string.h>

int writeEstimation(int estimation,  char *outputDir,  char *finalFile){
	
	
  FILE * fPointer2;
    
  printf("\nEstimation = %d seg\n", estimation);

  
      char line[150];
      strcat(outputDir, finalFile);
        

      fPointer2 = fopen(outputDir, "w");
      
      char str[40];
      sprintf(str, "%d", estimation);
      
      fprintf(fPointer2, str);
      fclose(fPointer2);
      
      
      
      fPointer2 = fopen(outputDir, "r");
      
      fgets(line, 150, fPointer2);
      int verificacao = 0;
      verificacao = atoi(line);
      
      if (verificacao == estimation ) {

	char suffixFlag[] = ".flag";
	strcat(outputDir, suffixFlag);
	fclose(fPointer2);
	fPointer2 = fopen(outputDir, "w");
      }
      
      fclose(fPointer2);
    return 1;
}
