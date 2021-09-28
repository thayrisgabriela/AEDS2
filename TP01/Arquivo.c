#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>


int main(){
    int numEntrada;
    double aux;
    double scan;

    FILE * arq = fopen("saida.txt", "wb");
    
    scanf("%d", &numEntrada);   //Leitura da quantidade de valores

    for(int i = 0; i < numEntrada; i++){  //Leitura da entrada padrao e gravar em arquivo numeros reais
        scanf("%lf", &aux);
        fwrite(&aux, sizeof(double), 1, arq);
    }
    fclose(arq);      //Fechar arquivo, indispensavel para gravar

    FILE * arqN = fopen("saida.txt", "r");

    for(int i = 1; i <= numEntrada; i++){     //Para cada linha gravada, ler e mostrar a saída com os números invertidos
        fseek(arqN,-i*(sizeof(double)),SEEK_END);
        fread(&scan, sizeof(double), 1, arqN);
        printf("%g\n", scan);
    }
    
    fclose(arqN);
    return 0;
}