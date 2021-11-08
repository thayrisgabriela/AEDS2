import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;

class Serie {

  private String nome;
  private String formato;
  private String duracao;
  private String pais;
  private String idioma;
  private String emissora;
  private String transmissao;
  private int temp;
  private int ep;

  public Serie() {
    nome = "";
    formato = "";
    duracao = "";
    pais = "";
    idioma = "";
    emissora = "";
    transmissao = "";
    temp = 0;
    ep = 0;
  }

  public Serie(
    String nome,
    String formato,
    String duracao,
    String pais,
    String idioma,
    String emissora,
    String transmissao,
    int temp,
    int ep
  ) {
    this.nome = nome;
    this.formato = formato;
    this.duracao = duracao;
    this.pais = pais;
    this.idioma = idioma;
    this.emissora = emissora;
    this.transmissao = transmissao;
    this.temp = temp;
    this.ep = ep;
  }

  //método para setar os atributos
  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setFormato(String formato) {
    this.formato = formato;
  }

  public void setDuracao(String duracao) {
    this.duracao = duracao;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public void setIdioma(String idioma) {
    this.idioma = idioma;
  }

  public void setEmissora(String emissora) {
    this.emissora = emissora;
  }

  public void setTransmissao(String transmissao) {
    this.transmissao = transmissao;
  }

  public void setTemp(int temp) {
    this.temp = temp;
  }

  public void setEp(int ep) {
    this.ep = ep;
  }

  //método para retornar os atributos
  public String getNome() {
    return this.nome;
  }

  public String getFormato() {
    return this.formato;
  }

  public String getDuracao() {
    return this.duracao;
  }

  public String getPais() {
    return this.pais;
  }

  public String getIdioma() {
    return this.idioma;
  }

  public String getEmissora() {
    return this.emissora;
  }

  public String getTransmissao() {
    return this.transmissao;
  }

  public int getTemp() {
    return this.temp;
  }

  public int getEp() {
    return this.ep;
  }

  public Serie clone() {
    Serie resp = new Serie();
    resp.nome = this.nome;
    resp.formato = this.formato;
    resp.duracao = this.duracao;
    resp.pais = this.pais;
    resp.idioma = this.idioma;
    resp.emissora = this.emissora;
    resp.transmissao = this.transmissao;
    resp.temp = this.temp;
    resp.ep = this.ep;
    return resp;
  }

  //método para printar a classe
  public void printClass() {
    System.out.println(
      this.nome +
      " " +
      this.formato +
      " " +
      this.duracao +
      " " +
      this.pais +
      " " +
      this.idioma +
      " " +
      this.emissora +
      " " +
      this.transmissao +
      " " +
      this.temp +
      " " +
      this.ep
    );
  }

  //método para tratar a linha, deixar apenas números e converter o retorno de String para Inteiro
  public int justInt(String line) {
    String resp = "";
    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) >= '0' && line.charAt(i) <= '9') { //caso o caracter seja um número ele é concatenado a variável resp
        resp += line.charAt(i);
      } else { //caso seja outro caracter, o i recebe o valor da condição de parada e o método de repetição é encerrado
        i = line.length();
      }
    }
    return Integer.parseInt(resp);
  }

  //método para a remoção das tags da linha lida do arquivo para retornar apenas o que é desejado
  public String removeTags(String line) {
    String resp = "";
    int i = 0;
    while (i < line.length()) {
      if (line.charAt(i) == '<') {
        i++;
        while (line.charAt(i) != '>') i++;
      } else if (line.charAt(i) == '&') {
        i++;
        while (line.charAt(i) != ';') i++;
      } else {
        resp += line.charAt(i);
      }
      i++;
    }

    resp = resp.replaceAll("&nbsp;", "");
    resp = resp.replaceAll("&#160;", "");
    resp = resp.trim();

    resp = resp.replaceAll("(lista de episódios)", "");
    resp = resp.replaceAll("(Lista de episódios)", "");
    resp = resp.replaceAll("(até o momento)", "");

    return resp;
  }

  //método para tratar o nome do arquivo e retornar o nome da série
  public String searchNome(String nomeArq) {
    String resp = "";
    for (int i = 0; i < nomeArq.length(); i++) {
      if (nomeArq.charAt(i) == '_') { //caso o caracter na posição i seja igual ao '_' a variável resp recebe um espaço em branco
        resp += ' ';
      } else { //caso não tenha espaço em branco o caracter é concatenado à string resp
        resp += nomeArq.charAt(i);
      }
    }
    return resp.substring(0, resp.length() - 5); //retorno da substring resp retirando os 5 últimos caracteres relacionados à extensão do arquivo
  }

  //método para leitura do arquivo .html e tratamento das linhas
  public void readClass(String nomeArq) {
    String file = "/tmp/series/" + nomeArq;
    try {
      FileReader fileReader = new FileReader(file); //declaração da variável fileReader que será recebida pelo bufferedReader

      BufferedReader br = new BufferedReader(fileReader); //declaração do bufferedReader para leitura do arquivo

      //set nome da série
      this.nome = searchNome(nomeArq);

      //set formato da série
      while (!br.readLine().contains("Formato"));
      this.formato = removeTags(br.readLine());

      //set duração da série
      while (!br.readLine().contains("Duração"));
      this.duracao = removeTags(br.readLine());

      //set país da série
      while (!br.readLine().contains("País de origem"));
      this.pais = removeTags(br.readLine());

      //set idioma da série
      while (!br.readLine().contains("Idioma original"));
      this.idioma = removeTags(br.readLine());

      //set emissora da série
      while (!br.readLine().contains("Emissora de televisão"));
      this.emissora = removeTags(br.readLine());

      //set transmissão original da série
      while (!br.readLine().contains("Transmissão original"));
      this.transmissao = removeTags(br.readLine());

      //set temporadas da série
      while (!br.readLine().contains("N.º de temporadas"));
      this.temp = justInt(removeTags(br.readLine()));

      //set episódios da série
      while (!br.readLine().contains("N.º de episódios"));
      this.ep = justInt(removeTags(br.readLine()));

      br.close();
      //Tratamento de exceções
    } catch(FileNotFoundException e) {
                    
    } catch(IOException e) {
        
    }
  }
}

class Lista {

  private Serie[] listaSerie;
  private int n;
  private int compare;
  private int swapContador;

  public Lista() {
    this(100);
  }

  public Lista(int tamanho) {
    listaSerie = new Serie[tamanho];
    n = 0;
  }

  public Serie[] getArray() {
    return this.listaSerie;
}

  public void inserirFim(Serie item) {
    if (n < listaSerie.length) {
      listaSerie[n] = item;
      n++;
    }
  }    

//Procura um elemento e retorna se ele existe.
  public boolean pesquisar(Serie item) {
    for (int i = 0; i < n; i++) {
        compare++;
        if (listaSerie[i].equals(item)) {
            return true;
        }
    }
    return false;
}
//Compara duas séries de acordo com o atributo ep, e caso eles sejam iguais, compara de acordo com o atributo nome
public int comparar(Serie a, Serie b) {
  int num1 = a.getEp(), num2 = b.getEp();

  int retorno = num1 - num2;
  if (retorno == 0) {
      String str1 = a.getNome(), str2 = b.getNome();
      retorno = str1.compareTo(str2);

      compare++;
  }

  compare++;
  return retorno;
}

  public void swap(int i, int j) {
        Serie temp = listaSerie[i];
        listaSerie[i] = listaSerie[j];
        listaSerie[j] = temp;

        swapContador++;
    }

    public void sort() {
        mergeSort(0, n-1);
     }
 
public void mergeSort(int esq, int dir) {
    if (esq < dir){
        int meio = (esq + dir)/2;
        mergeSort(esq, meio);
        mergeSort(meio + 1, dir);
        intercalar(esq, meio, dir);
     }
  }
  
  public void intercalar(int esq, int meio, int dir){
    int n1, n2;

    //Definir tamanho dos dois subarrays
    n1 = meio-esq+1;
    n2 = dir - meio;

    Serie[] a1 = new Serie[n1+1];
    Serie[] a2 = new Serie[n2+1];

    //Inicializar primeiro subarray
    for(int i = 0; i < n1; i++){
       a1[i] = listaSerie[esq+i];
    }
  
      //Inicializar segundo subarray
    for(int j = 0; j < n2; j++){
        a2[j] = listaSerie[meio+j+1];
     }
     
     int i = 0, j = 0, k = esq;

        while (i < n1 && j < n2) {
            listaSerie[k] = (comparar(a1[i], a2[j]) <= 0) ? a1[i++] : a2[j++];

            swapContador++;
            k++;
        }

        while (i < n1) {
            listaSerie[k++] = a1[i++];
            swapContador++;
        }

        while (j < n2) {
            listaSerie[k++] = a2[j++];
            swapContador++;
        }
        }
    
  public void mostrar() {
    for (int i = 0; i < n; i++) {
      this.listaSerie[i].printClass();
    }
  }
}




class MergeSort {

  public static boolean isFim(String s) {
    return (
      s.length() == 3 &&
      s.charAt(0) == 'F' &&
      s.charAt(1) == 'I' &&
      s.charAt(2) == 'M'
    );
  }

  public static void matricula(long duracao, int contador) { // GERADOR DE ARQUIVO DE LOG
    try {
      File myObj = new File("matrícula_mergesorttxt");
      if (myObj.createNewFile()) {} else {}
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      FileWriter myWriter = new FileWriter("matrícula_mergesort.txt");
      myWriter.write("737163" + "\t" + duracao + "\t" + contador);
      myWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Scanner leitor = new Scanner(System.in);
    //Inicialização do timer de execução
    long tempoEntrada = System.nanoTime();
    String entrada = "";
    int numEntrada = 0;
    int contador = 0;
    Lista lista = new Lista();


    while (isFim(entrada) == false) {
        Serie serie = new Serie();
        entrada = MyIO.readLine();
        try {
            lista.inserirFim(serie);
            serie.readClass(entrada);
            numEntrada++;
        } catch (Exception e) {
        }
    }

    // ordenar e printar os objetos em ordem
    lista.sort();

    for (int i = 1; i < numEntrada; i++) {
        lista.getArray()[i].printClass();
    }

        long endTime = System.nanoTime(); //Fim do timer
        long duracao = (endTime - tempoEntrada);  //Cálculo do tempo
        matricula(duracao, contador);

        leitor.close();

}
}