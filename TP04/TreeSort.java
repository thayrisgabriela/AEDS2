import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
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
	public No esq, dir;  // Filhos da esq e dir.

	public No(Serie elemento) {
		this(elemento, null, null);
	}

	public No(Serie elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
}


 class TreeSortt {
	private No raiz; 
   private int n;

	public TreeSortt() {
		raiz = null;
      n = 0;
	}

	public Serie[] sort() {
      Serie[] array = new Serie[n];
      n = 0;
		sort(raiz, array);
      return array;
	}

	private void sort(No i, Serie[] array) {
		if (i != null) {
			sort(i.esq, array);
			array[n++] = i.elemento;
			sort (i.dir, array);
		}
	}

	public void inserir(Serie x) {
      n++;
		raiz = inserir(x, raiz);
	}

	private No inserir(Serie x, No i) {
		if (i == null) {
         i = new No(x);

      } else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
         i.esq = inserir(x, i.esq);
      } else if (x.getNome().compareTo(i.elemento.getNome()) >= 0) {
         i.dir = inserir(x, i.dir);
      }
		return i;
	}

}

public class TreeSort{

    public static boolean isFim(String s){
        boolean result;

        result = (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');

        return result;
    }
    public static void matricula(long duracao, int contador){ // GERADOR DE ARQUIVO DE LOG

        try {
            File myObj = new File("matrícula_treesort.txt");
            if (myObj.createNewFile()) {
            } else {
            }
          } catch (IOException e) {
            e.printStackTrace();
          }

          try {
            FileWriter myWriter = new FileWriter("matrícula_treesort.txt");
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
        String entrada = MyIO.readLine();
        String entrada2 = "";
        int contador = 0;
        TreeSortt arvore = new TreeSortt();


        while (isFim(entrada) == false) {
            serie = new Serie();
            try {
                serie.readClass(entrada);
                arvore.inserir(serie);
            } catch (Exception e) {
        }
        entrada = MyIO.readLine();
    }

    Serie[] a = arvore.sort();
    for(int i = 0; i < a.length; i++){
        a[i].printClass();
    }

            long endTime = System.nanoTime(); //Fim do timer
            long duracao = (endTime - tempoEntrada);  //Cálculo do tempo
            matricula(duracao, contador);
        }
}