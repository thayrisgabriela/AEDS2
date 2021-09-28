#include<stdio.h>
#include<stdbool.h>
#include<string.h>

bool isPalindromo (char palavra[], int i, int a){  // Retorna true ou false para palindromo
    bool isP = true;

    if(a < (i/2)){
        if(palavra[a] != palavra[i-1-a]){
            isP = false;
            } 
        else{
            isP = true;
            isP =isPalindromo(palavra, i, a+1);
            }
    }
    return(isP);   
}

bool isFim(char s[], int i){  // Testa se a palavra digitada foi "FIM"
    return (i == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

int main( ){
    int tam = 0;
    char palavra[1000];
    int i = 0;

    do{
        fgets(palavra, 1000, stdin);
        tam = strlen(palavra)-1; 
        
        if (!isFim(palavra, tam))
        {
            if (isPalindromo(palavra, tam, 0))
            {
                printf("SIM\n");
            } 
            else
            {
                printf("NAO\n");
            } 
        }
    }while (!isFim(palavra, tam));
return 0;
}