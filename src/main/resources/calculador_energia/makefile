SOURCES = main.c match.c estimateC.c estimate.s existeFlag.c EndsWith.c writeEstimation.c
OBJFILES = main.o match.o estimateC.o estimate.o existeFlag.o EndsWith.o writeEstimation.o
EXEC = estimate

.SUFFIXES : .c .o

.c.o:
	gcc -Wall -g -c $<

.s.o:
	gcc -Wall -g -c $<

${EXEC}: ${OBJFILES}
	gcc -Wall -g -o ${EXEC} ${OBJFILES}

${OBJFILES} : ${SOURCES} 

run: ${EXEC}
	./${EXEC}

clean:
	rm -f ${OBJFILES} ${EXEC}
