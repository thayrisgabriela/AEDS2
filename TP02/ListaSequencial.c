#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define TAM_MAX 100

typedef struct {
    char nome[TAM_MAX];
    char formato[TAM_MAX];
    char duracao[TAM_MAX];
    char pais[TAM_MAX];
    char idioma[TAM_MAX];
    char emissora[TAM_MAX];
    char transmissao[TAM_MAX];
    int temp;
    int ep;
} Serie;

char *elimina_queblinha(char *linha) {
    while (*linha != '\r' && *linha != '\n') linha++;
    *linha = '\0';
    return linha;
}

char *freadline(char *linha, int tam_max, FILE *arq) {
    return elimina_queblinha(fgets(linha, tam_max, arq));
}

char *readline(char *linha, int tam_max) {
    return freadline(linha, tam_max, stdin);
}

void print_serie(Serie *serie) {
    printf("%s %s %s %s %s %s %s %d %d\n",
        serie->nome,
        serie->formato,
        serie->duracao,
        serie->pais,
        serie->idioma,
        serie->emissora,
        serie->transmissao,
        serie->temp,
        serie->ep
    );
}

// Retorna o tamanho em bytes de um arquivo.
long tam_arquivo(FILE *arq) {
    fseek(arq, 0L, SEEK_END);
    long size = ftell(arq);
    rewind(arq);
    return size;
}

// Retorna todo o conteúdo do arquivo numa string.
char *ler_html(char nomearq[]) {
    FILE *arq = fopen(nomearq, "r");
    if (!arq) {
        fprintf(stderr, "Falha ao abrir arquivo %s\n", nomearq);
        exit(1);
    }
    long tam = tam_arquivo(arq);
    char *html = (char *) malloc(sizeof(char) * (tam + 1));
    fread(html, 1, tam, arq);
    fclose(arq);
    html[tam] = '\0';
    return html;
}


char *extrair_texto(char *html, char *texto) {
    char *inicio = texto;
    int contagem = 0;
    while (*html != '\0') {
        if (*html == '<') {
            if (
                (*(html + 1) == 'p') ||
                (*(html + 1) == 'b' && *(html + 2) == 'r') ||
                (*(html + 1) == '/' && *(html + 2) == 'h' && *(html + 3) == '1') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'h') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'd')
            ) break;
            else contagem++;
        }
        else if (*html == '>') contagem--;
        else if (contagem == 0 && *html != '"') {
            if (*html == '&') html = strchr(html, ';');
            else if (*html != '\r' && *html != '\n') *texto++ = *html;
        }
        html++;
    }
    *texto = '\0';
    return *inicio == ' ' ? inicio + 1 : inicio;
}


void ler_serie(Serie *serie, char *html) {
    char texto[TAM_MAX];

    char *ptr = strstr(html, "<h1");
    extrair_texto(ptr, texto);

    char *parenteses_ptr = strchr(texto, '(');
    if (parenteses_ptr != NULL) *(parenteses_ptr - 1) = '\0';

    strcpy(serie->nome, texto);

    ptr = strstr(ptr, "<table class=\"infobox_v2\"");

    ptr = strstr(ptr, "Formato");
    ptr = strstr(ptr, "<td");
    strcpy(serie->formato, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Duração");
    ptr = strstr(ptr, "<td");
    strcpy(serie->duracao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "País de origem");
    ptr = strstr(ptr, "<td");
    strcpy(serie->pais, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Idioma original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->idioma, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Emissora de televisão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->emissora, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Transmissão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->transmissao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "N.º de temporadas");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->temp);

    ptr = strstr(ptr, "N.º de episódios");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->ep);
}

//Atributos e metódos da lista
Serie array[50];
int n;

void inicio(){
    n = 0;
}
void inserirInicio(Serie x) {
    if (n >= 50) 
        exit(1);

    //levar elementos para o fim do array
    for (int i = n; i > 0; i--){
        array[i] = array[i-1];
    }
    array[0] = x;
    n++;
}
void inserirFim(Serie x) {
    if (n >= 50) 
        exit(1);
    array[n] = x;
    n++;
}
void inserir(Serie x, int pos) {
    if (n >= 50 || pos < 0 || pos > n)
        exit(1);

    //levar elementos para o fim do array
    for (int i = n; i > pos; i--){
        array[i] = array[i-1];
    }
    array[pos] = x;
    n++;
}

Serie removerInicio() {
    if (n == 0)
        exit(1);
    Serie resp = array[0];
    n--;
    for (int i = 0; i < n; i++){
        array[i] = array[i+1];
    }
    return resp;
}

Serie removerFim() {
    if (n == 0)
        exit(1); 

    return array[--n];
}

Serie remover(int pos) {
    if (n == 0 || pos < 0 || pos >= n)
        exit(1);
    Serie resp = array[pos];
    n--;
    for (int i = pos; i < n; i++){
        array[i] = array[i+1];
    }
    return resp;
}

void mostrar(){
    for (int i = 0; i < n; i++){
        print_serie(&array[i]);
    }
    
}

#define TAM_MAX_LINHA 250
#define PREFIXO "/tmp/series/"
// #define PREFIXO "../entrada e saida/tp02/series/"

//Função que finaliza o programa
int isFim(char arq[]) {
    return arq[0] == 'F' && arq[1] == 'I' && arq[2] == 'M';
}

// Função que retorna uma substring
char* substring(char *substring, const char *string, int inicio, int n)
{
    //Copia caractere por caractere até n e envia para a substring
    while (n > 0)
    {
        *substring = *(string + inicio);

        substring++;
        string++;
        n--;
    }

    //adiciona o null no final
    *substring = '\0';
 
    // retorna substring
    return substring;
}

int main(void){
    int breakpoint = 0; //Variável que finaliza o programa
    size_t tam_prefixo = strlen(PREFIXO);
    char arq[TAM_MAX_LINHA];
    char auxComando[TAM_MAX_LINHA]; //Variável que armazena o comando
    char auxPosicao[TAM_MAX_LINHA]; //Variável que armazena a posição
    char auxArquivo[TAM_MAX_LINHA]; 
    char caminhoArquivo[TAM_MAX_LINHA]; //Variável que armazena o caminho do arquivo
    int posicao = 0;

    strcpy(arq, PREFIXO);
    readline(arq + tam_prefixo, TAM_MAX_LINHA);

    while (!isFim(arq + tam_prefixo)) {
        Serie serie;
        char *html = ler_html(arq);
        ler_serie(&serie, html);
        free(html);
        inserirFim(serie);
        readline(arq + tam_prefixo, TAM_MAX_LINHA);
    }

    scanf("%d", &breakpoint);
    breakpoint++;
    
    for(int i = 0; i <breakpoint; i++){
        Serie serie;
        fgets(arq, TAM_MAX_LINHA, stdin);
        substring(auxComando, arq, 0, 2);
        
        //Caso o comando dado seja INSERIR NO INICIO
        if(auxComando[0] == 'I' && auxComando[1] == 'I'){
            substring(auxArquivo, arq, 3, strlen(arq)-1);
            strcpy(caminhoArquivo, PREFIXO);
            strcat(caminhoArquivo, auxArquivo);
            elimina_queblinha(caminhoArquivo);
            char *html = ler_html(caminhoArquivo);
            ler_serie(&serie, html);
            free(html);
            inserirInicio(serie);
        }

        //Caso o comando dado seja INSERIR EM QUALUQER POISÇÃO
        else if(auxComando[0] == 'I' && auxComando[1] == '*'){
            substring(auxPosicao, arq, 3, 5);
            posicao = atoi(auxPosicao);
            substring(auxArquivo, arq, 6, strlen(arq));
            strcpy(caminhoArquivo, PREFIXO);
            strcat(caminhoArquivo, auxArquivo);
            elimina_queblinha(caminhoArquivo);
            char *html = ler_html(caminhoArquivo);
            ler_serie(&serie, html);
            free(html);
            inserir(serie, posicao);
        }

        //Caso o comando dado seja INSERIR NO FIM
        else if(auxComando[0] == 'I' && auxComando[1] == 'F'){
            substring(auxArquivo, arq, 3, strlen(arq));
            strcpy(caminhoArquivo, PREFIXO);
            strcat(caminhoArquivo, auxArquivo);
            elimina_queblinha(caminhoArquivo);
            char *html = ler_html(caminhoArquivo);
            ler_serie(&serie, html);
            free(html);
            inserirFim(serie);
        }

        //Caso o comando dado seja RETIRAR NO INICIO
        else if(auxComando[0] == 'R' && auxComando[1] == 'I'){
            printf("(R) %s\n", removerInicio().nome);
        }

        //Caso o comando dado seja RETIRAR EM QUALQUER POSIÇÃO
        else if(auxComando[0] == 'R' && auxComando[1] == '*'){
            substring(auxPosicao, arq, 3, 5);
            posicao = atoi(auxPosicao);
            printf("(R) %s\n", remover(posicao).nome);
            
        }

        //Caso o comando seja RETIRAR NO FIM
        else if(auxComando[0] == 'R' && auxComando[1] == 'F'){
            printf("(R) %s\n", removerFim().nome);
        }

    }
    mostrar();
    return EXIT_SUCCESS;
}