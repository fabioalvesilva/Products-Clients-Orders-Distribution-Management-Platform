#include <stdio.h>

#include <stdlib.h>

#include "estimate.h"
#include <dirent.h>

#include <time.h>

#include <string.h>
int existeFlag(char *nomeFichData){

char finalFile[100] = "";

 strcat(finalFile,nomeFichData );
 
 strcat(finalFile,".flag");


 DIR *d;
  struct dirent *dir;
  d = opendir("../Ficheiros/Output");
  if (d)
    {
      while ((dir = readdir(d)) != NULL)
	{
	   
	  if(match(finalFile,dir->d_name)){

	    
	 
	    return 1;
	    
	    
	    
	  }
	 
        }
      closedir(d);
    }
  
  return 0;
}
