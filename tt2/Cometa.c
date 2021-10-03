#include <stdio.h>

int  main (void) {
	int ano, vezes;

	scanf( "%d", &ano);
	
	vezes = (ano - 10 ) / 76 ;
	vezes++;
	printf( "%d", vezes * 76 + 10 );
	
	return  0 ;
}