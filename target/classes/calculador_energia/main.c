#include <stdio.h>

#include <stdlib.h>

#include "estimate.h"

#include <dirent.h>

#include <time.h>

#include <unistd.h>

#include <string.h>

int main() {

  int tempo = 0;

  int sleepTime = 15;

  int ciclos = 45;

  while (tempo < ciclos) {
	
    printf("\nSearching for lock file.\n");

    sleep(1);

    printf("\nSearching for lock file..\n");

    sleep(1);

    printf("\nSearching for lock file...\n");

    sleep(2);

    char finalFile[100] = "estimate";

    char inputDir[100] = "../Ficheiros/Output/";

    char outputDir[100] = "../Ficheiros/Input/";

    int estimation = 0;

    char testFlag[100] = "";

    int fileNotFound = 1;

    int print = 0;

    int a = 0;

    //FILE * fPointer;

    //FILE * fPointer2;

    DIR * d;

    struct dirent * dir;

    d = opendir(inputDir);

    char * regexp = "lock_*";

    if (d)

    {

      while ((dir = readdir(d)) != NULL)

      {
		  strcpy(outputDir, "../Ficheiros/Input/");
		  strcpy(inputDir, "../Ficheiros/Output/");
		  strcpy(finalFile, "estimate");
		  
        if (match(regexp, dir -> d_name)) {
		
          //printf("\n%s\n", dir -> d_name);

          strcat(testFlag, dir -> d_name);

          a = EndsWith(testFlag, ".flag");

          fileNotFound = 0;

          strcat(inputDir, dir -> d_name);
          if (!a) {

            printf("\nFile and flag found!\n");
            printf("\n%s\n", inputDir);
            estimation = estimateC(inputDir);
			
			
			
            char * subString = dir -> d_name; // the "result"

            subString = strtok(subString, "lock");

            strcat(finalFile, subString);
            
            
            writeEstimation(estimation, outputDir, finalFile);
	     if( !remove(inputDir)) printf("\nRemoved file %s\n",inputDir);
          }
	
         
        }

      }

      closedir(d);

    }

    if (a) {

      fileNotFound = 1;

    }

    if (fileNotFound == 1 && a == 0) {

      printf("\nNo lock file found.\n");

    };
    
    tempo++;
	if (tempo< ciclos)
	{
		
    printf("\nSleeping for %d seconds\n", sleepTime);
		sleep(sleepTime);
	} else {
		printf("\nGoodBye\n");
		}
	

    

    

    * finalFile = "estimate";

  }

  return 0;

}
