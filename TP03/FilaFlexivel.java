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

		resp = resp.replaceAll("(fila de episódios)", "");
		resp = resp.replaceAll("(fila de episódios)", "");
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

class Fila {
    private Serie[] filaSerie;
    private int n;
  
    public Fila() {
      this(5);
    }
  
    public Fila(int tamanho) {
      filaSerie = new Serie[tamanho];
      n = 0;
    }

    public int getN() {
      return this.n;
  }

    public Serie[] getArray() {
        return this.filaSerie;
    }
  
    public void inserirFim(Serie x) throws Exception {
        if (n >= filaSerie.length) {
            removerInicio();
        }
        filaSerie[n] = x;
        n++;
    }
    
    public Serie removerInicio() throws Exception {
      if (n == 0) {
          throw new Exception("Erro ao remover!");
      }
      Serie resp = filaSerie[0];
      n--;
      for (int i = 0; i < n; i++) {
          filaSerie[i] = filaSerie[i + 1];
      }
      return resp;
  }

  
    public void mostrar() {
      System.out.print("[ ");
      for (int i = 0; i < n; i++) {
          System.out.print(filaSerie[i] + " ");
      }
      System.out.println("]");
    }
}


public class FilaFlexivel{

    public static boolean isFim(String s){
        boolean result;

        result = (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');

        return result;
    }

    public static void main(String [] args) throws Exception {
		Scanner leitor = new Scanner(System.in);
        String entrada = "";
        int numEntrada = 0;
        Fila fila = new Fila();


        while (isFim(entrada) == false) {
            Serie serie = new Serie();
            entrada = MyIO.readLine();
            double mediaTemporadas = 0;
            try {
                serie.readClass(entrada);
                if (serie.getEp() != 0){
                  fila.inserirFim(serie);
                  for (int i = 0; i < fila.getN(); i++){
                    mediaTemporadas += (fila.getArray()[i].getTemp());
                  }
                  MyIO.println((int) Math.round(mediaTemporadas / fila.getN()));
                }
            } catch (Exception e) {
            }
        }

        int numComandos = MyIO.readInt();

        for (int i = 0; i < numComandos; i++) {

            Serie serie = new Serie();
            String comando = MyIO.readLine();
            double mediaTemporadas = 0;

            if (comando.charAt(0) == 'I') {

                String objeto = comando.substring(2);
                serie.readClass(objeto);
                fila.inserirFim(serie);

                for (int j = 0; j < fila.getN(); j++) {
                    mediaTemporadas += (fila.getArray()[j].getTemp());
                }
                MyIO.println((int) Math.round(mediaTemporadas / fila.getN()));

            } else if (comando.charAt(0) == 'R') {

                serie = fila.removerInicio();
            }

        }
        leitor.close();
}
}
