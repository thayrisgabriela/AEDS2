#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define N 2000
int testaPalindromo(char palavra[]);
int TesteFim(char palavra[]);

int main()
{
    char palavra[N];

    do
    {
        scanf("%[^\n]", palavra);


        if (testaPalindromo(palavra) == 1)
            printf("SIM\n");
        else
            printf("NAO\n");

    } while (TesteFim(palavra) != 1); //testa se a palavra digitada foi "FIM", para assim parar a repetição
    return 0;
}

int testaPalindromo(char palavra[]) // função para testar se a palavra é um palindromo
{
    int i;
    int Nreal;
    Nreal = strlen(palavra);
    for (int i = 0; i < Nreal; i++)
    {
        if (palavra[i] != palavra[Nreal - 1 - i])
        {
            return 0;
        }
    }
    return 1;
}

int TesteFim(char palavra[])
{ // testa se a próxima palavra é fim
    int result = 0;
    if (strcmp(palavra, "FIM") == 0)
        result = 1;
    return result;
}