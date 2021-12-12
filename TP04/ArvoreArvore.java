import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;

class Serie{

    private String nome;
    private String formato;
    private String duracao;
    private String pais;
    private String idioma;
    private String emissora;
    private String transmissao;
    private int temp;
    private int ep;


    public Serie(){
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

    public Serie(String nome, String formato, String duracao, String pais, String idioma, String emissora, String transmissao, int temp, 
    int ep){
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
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setFormato(String formato){
        this.formato = formato;
    }

    public void setDuracao(String duracao){
        this.duracao = duracao;
    }

    public void setPais(String pais){
        this.pais = pais;
    }

    public void setIdioma(String idioma){
        this.idioma = idioma;
    }

    public void setEmissora(String emissora){
        this.emissora = emissora;
    }

    public void setTransmissao(String transmissao){
        this.transmissao = transmissao;
    }

    public void setTemp(int temp){
        this.temp = temp;
    }

    public void setEp(int ep){
        this.ep = ep;
    }
    //método para retornar os atributos
    public String getNome(){ 
        return this.nome; 
    }

    public String getFormato(){ 
        return this.formato; 
    }

    public String getDuracao(){ 
        return this.duracao; 
    }

    public String getPais(){ 
        return this.pais; 
    }

    public String getIdioma(){ 
        return this.idioma; 
    }

    public String getEmissora(){ 
        return this.emissora; 
    }

    public String getTransmissao(){ 
        return this.transmissao; 
    }

    public int getTemp(){ 
        return this.temp; 
    }

    public int getEp(){ 
        return this.ep; 
    }

    public Serie clone(){
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
    public void printClass(){
        System.out.println (this.nome + " " + this.formato + " " + this.duracao + " " + this.pais + " " + this.idioma + " " + this.emissora + " " +
        this.transmissao + " " + this.temp + " " + this.ep);
    }

    //método para tratar a linha, deixar apenas números e converter o retorno de String para Inteiro
    public int justInt(String line){
        String resp = "";
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9'){ //caso o caracter seja um número ele é concatenado a variável resp
                resp += line.charAt(i);
            } else { //caso seja outro caracter, o i recebe o valor da condição de parada e o método de repetição é encerrado
                i = line.length();
            }
        }
        return Integer.parseInt(resp);
    }
    
    //método para a remoção das tags da linha lida do arquivo para retornar apenas o que é desejado
    public static String removeTags(String line){
        String resp = "";
        int i = 0;
        while(i < line.length()){ 
            if(line.charAt(i) == '<'){ 
                i++;
                while(line.charAt(i) != '>') 
                i++; 
            } else if(line.charAt(i) == '&'){ 
                i++;
                while(line.charAt(i) != ';') i++;
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
    public static String searchName(String nomeArq) {
        String resp = "";
        for(int i = 0; i < nomeArq.length(); i++){
            if(nomeArq.charAt(i)  == '_'){ //caso o caracter na posição i seja igual ao '_' a variável resp recebe um espaço em branco
                resp += ' ';
            } else { //caso não tenha espaço em branco o caracter é concatenado à string resp
                resp += nomeArq.charAt(i);
            }
        }
        return resp.substring(0, resp.length()-5); //retorno da substring resp retirando os 5 últimos caracteres relacionados à extensão do arquivo
    }
    //método para leitura do arquivo .html e tratamento das linhas
    public void readClass(String nomeArq) {
        try{
            FileReader fileReader = new FileReader("/tmp/series/" + nomeArq); //declaração da variável fileReader que será recebida pelo bufferedReader

            BufferedReader br = new BufferedReader(fileReader); //declaração do bufferedReader para leitura do arquivo
            
            //set nome da série
            this.nome = searchName(nomeArq);
            
            //set formato da série
            while(!br.readLine().contains("Formato"));
            this.formato = removeTags(br.readLine());

            //set duração da série
            while(!br.readLine().contains("Duração"));
            this.duracao = removeTags(br.readLine());

            //set país da série
            while(!br.readLine().contains("País de origem"));
            this.pais = removeTags(br.readLine());

            //set idioma da série
            while(!br.readLine().contains("Idioma original"));
            this.idioma = removeTags(br.readLine());

            //set emissora da série
            while(!br.readLine().contains("Emissora de televisão"));
            this.emissora = removeTags(br.readLine());

            //set transmissão original da série
            while(!br.readLine().contains("Transmissão original"));
            this.transmissao = removeTags(br.readLine());

            //set temporadas da série
            while(!br.readLine().contains("N.º de temporadas"));
            this.temp = justInt(removeTags(br.readLine()));

            //set episódios da série
            while(!br.readLine().contains("N.º de episódios"));
            this.ep = justInt(removeTags(br.readLine()));
            
            
            br.close();
    } catch (FileNotFoundException e) {
                    
    } catch(IOException e) {
        
    }                

    }
}

class No {
	public Serie elemento; // Conteudo do no.
	public No esq; // No da esquerda.
	public No dir; // No da direita.
    public No2 outro;

	No(Serie elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
      this.outro = null;
	}

	No(Serie elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
      this.outro = null;
	}
}

class No2 {
	public Serie elemento; // Conteudo do no.
	public No2 esq; // No da esquerda.
	public No2 dir; // No da direita.

	No2(Serie elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
	}

	No2(Serie elemento, No2 esq, No2 dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
}

class Arvore {
	private No raiz; // Raiz da arvore.

	public Arvore() {
		raiz = null;
      inserir('M');
      inserir('T');
      inserir('F');
      //os outros 23 caracteres.
	}

   public void inserir(Serie letra){
    raiz = inserir(x, raiz);  
}


   public void inserir(String s){
      inserir(s, raiz);
   }

   public void inserir(String s, No i) throws Exception {
		if (i == null) {
         throw new Exception("Erro ao inserir: caractere invalido!");

      } else if (s.getNome().charAt(0) < i.elemento) {
         inserir(x, i.esq);

      } else if (s.getNome().charAt(0) > i.elemento) {
         inserir(x, i.dir);

      } else {
         i.outro = inserir(s, i.outro);
      }
   }


	private No2 inserir(Serie s, No2 i) throws Exception {
		if (i == null) {
         i = new No2(x);

      } else if (s.getNome().compareTo(i.elemento.getNome()) < 0compareTo(i.elemento) < 0) {
         i.esq = inserir(x, i.esq);

      } else if (s.getNome().compareTo(i.elemento.getNome()) > 0) {
         i.dir = inserir(x, i.dir);

      } else {
         throw new Exception("Erro ao inserir: elemento existente!");
      }

		return i;
	}


   public void mostrar(){
      mostrar(raiz);
   }

   public void mostrar(No i){
      if (i != null){
         mostrar(i.esq);
         //System.out.println("Letra: " + i.elemento);
         mostrar(i.outra);
         mostrar(i.dir);
      }
   }

   public void mostrar(No2 i){
      if (i != null){
         mostrar(i.esq);
         System.out.println(i.elemento);
         mostrar(i.dir);
      }
   }

   public boolean hasStringTam10(){
      return hasStringTam10(raiz);
   }

   public boolean hasStringTam10(No i){
      boolean resp = false;
      if(i != null){
         resp = hasStringTam10(i.outro) || hasStringTam10(i.esq) || hasStringTam10(i.dir);
      }
      return resp;
   }

   public boolean hasStringTam10(No2 i){
      boolean resp = false;
      if(i != null){
         resp = i.elemento.length() == 10 || hasStringTam10(i.esq) || hasStringTam10(i.dir);
      }
      return resp;
   }


   public boolean hasStringTam10(char c){
      return hasStringTam10(raiz, c);
   }

   public boolean hasStringTam10(No i, char c){
      boolean resp;
		if (i == null) { 
         resp = false;

      } else if (c < i.elemento) { 
         resp = hasStringTam10(i.esq, c); 

      } else if (c > i.elemento) { 
         resp = hasStringTam10(i.dir, c); 
      
      } else { 
         resp = hasStringTam10(i.outro); 
      }
      return resp;
   } 


	public boolean pesquisar(String elemento) {
		return pesquisar(raiz, elemento);
	}

	private boolean pesquisar(No no, String x) {
      boolean resp;
		if (no == null) { 
         resp = false;

      } else if (x.charAt(0) < no.elemento) { 
         resp = pesquisar(no.esq, x); 

      } else if (x.charAt(0) > no.elemento) { 
         resp = pesquisar(no.dir, x); 
      
      } else { 
         resp = pesquisarSegundaArvore(no.outro, x); 
      }
      return resp;
	}

	private boolean pesquisarSegundaArvore(No2 no, String x) {
      boolean resp;
		if (no == null) { 
         resp = false;

      } else if (x.compareTo(no.elemento) < 0) { 
         resp = pesquisarSegundaArvore(no.esq, x); 

      } else if (x.compareTo(no.elemento) > 0) { 
         resp = pesquisarSegundaArvore(no.dir, x); 

      } else { 
         resp = true; 
      }
      return resp;
	}


   public int contPalavra(char letra){
      return contPalavra(letra, raiz);
   }

   public int contPalavra(char letra, No i) throws Exception {
      int resp = 0;

		if (i == null) {
         throw new Exception("Erro ao pesquisar: caractere invalido!");

      } else if (letra < i.elemento) {
         resp = contPalavra(letra, i.esq);

      } else if (letra > i.elemento) {
         resp = contPalavra(letra, i.dir);

      } else {
         resp = contPalavra(i.outro);
      }

      return resp;
   }

   public int contPalavra(No2 i){
      int resp = 0;
      if(i != null){
         resp = 1 + contPalavra(i.esq) + contPalavra(i.dir);
      }
      return resp;
   }
}

 public class ArvoreArvore{

    public static boolean isFim(String s){
        boolean result;

        result = (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');

        return result;
    }
    public static void matricula(long duracao, int contador){ // GERADOR DE ARQUIVO DE LOG

        try {
            File myObj = new File("matrícula_arvoreBinaria.txt");
            if (myObj.createNewFile()) {
            } else {
            }
          } catch (IOException e) {
            e.printStackTrace();
          }

          try {
            FileWriter myWriter = new FileWriter("matrícula_arvoreBinaria.txt");
            myWriter.write("737163" + "\t" + duracao + "\t" + contador);
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }

    }

    public static void main(String [] args) throws IOException{
        Serie serie = new Serie();
		//Inicialização do timer de execução
		long tempoEntrada = System.nanoTime();
        String entrada = "";
        String entrada2 = "";
        int contador = 0;
        ArvoreBin arvore = new ArvoreBin();


        while (isFim(entrada) == false) {
            serie = new Serie();
            try {
                serie.readClass(entrada);
                arvore.inserir(serie);
            } catch (Exception e) {
        }
        entrada = MyIO.readLine();
    }

        int n = MyIO.readInt();
        for(int m=0; m < n; m++){
            entrada = MyIO.readLine();
            if(entrada.charAt(0)=='I'){
                //insere no inicio a serie desejada
                entrada = entrada.replace("I", "");
                entrada=entrada.trim();
                serie = new Serie();
                try{
                serie.readClass(entrada);
                }
                catch(Exception e){}
                try {
                    arvore.inserir(serie);
                } catch (Exception e) {
                
                }

            }
            else if(entrada.charAt(0)=='R'){
                //insere no fim a serie desejada
                serie = new Serie();
                try {
                   serie = arvore.remover();
                } catch (Exception e) {}   
            }
        }

        do {
            entrada2 = MyIO.readLine(); 
            if(isFim(entrada2) != true){
            if(arvore.pesquisar(entrada2)==true)
            System.out.print("SIM");
            else
            System.out.print("NAO");
            }
            System.out.print("\n");
        } while (isFim(entrada2) != true);
        entrada = "";

            long endTime = System.nanoTime(); //Fim do timer
            long duracao = (endTime - tempoEntrada);  //Cálculo do tempo
            matricula(duracao, contador);
        }
}


