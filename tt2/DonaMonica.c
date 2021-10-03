#include <stdio.h>

int main(void)
{
    int id_filho1, id_filho2, id_mae;
    int soma, id_filho3;
    scanf("%d", &id_mae);

    scanf("%d", &id_filho1);

    scanf("%d", &id_filho2);

    soma = id_filho1 + id_filho2; 
    id_filho3 = id_mae - soma ;
    if (id_filho1 > id_filho2 && id_filho1 > id_filho3)  //estrutura para testa qual idade é maior 
    {
        printf("%d", id_filho1);
    }
    else if (id_filho2 > id_filho1 && id_filho2 > id_filho3)
    {
        printf("%d", id_filho2);
    }
    else
    {
        printf("%d", id_filho3);
    }
    return 0;
}
