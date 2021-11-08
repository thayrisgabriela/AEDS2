import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;
 
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
  

class CCelula {
	public Serie item;
	public CCelula prox;    	
    public CCelula(Serie valorItem, CCelula proxCelula)
    {
        item = valorItem;
        prox = proxCelula;
    }    			
    public CCelula(Serie valorItem)
    {
        item = valorItem;
        prox = null;
    }    			        	
    public CCelula()
    {
    	item = null;
        prox = null;
    }    			        	
}

/**
 * @(#)Lista.java
 *
 *
 * @author Rodrigo Richard Gomes
 * @version 1.00 2018/3/16
 */

class Lista {

	private CCelula primeira; // Referencia a c�lula cabe�a
	private CCelula ultima; // Referencia a �ltima c�lula da lista
	private int qtde; // Contador de itens na lista. Deve ser incrementado sempre que um item for
						// inserido e decrementado quando um item for exclu�do.

	// Fun��o construtora. Aloca a c�lula cabe�a e faz todas as refer�ncias
	// apontarem para ela.
	public Lista() {
		primeira = new CCelula();
		ultima = primeira;
	}

	// Verifica se a lista est� vazia. Retorna TRUE se a lista estiver vazia e FALSE
	// se ela tiver elementos.
	public boolean vazia() {
		return primeira == ultima;
	}

	// Insere um novo Item no fim da lista.
	public void insereFim(Serie valorItem) {
		ultima.prox = new CCelula(valorItem);
		ultima = ultima.prox;
		qtde++;
	}

	// Insere um novo Item no come�o da lista
	public void insereComeco(Serie valorItem) {
		CCelula aux = primeira.prox;
		primeira.prox = new CCelula(valorItem, aux);
		if (aux == null)
			ultima = primeira.prox;
		qtde++;
	}

	public boolean insereIndice(Serie valorItem, int posicao) {
		// Verifica se � uma posi��o v�lida e se a lista possui elementos
		if ((posicao >= 1) && (posicao <= qtde) && (primeira != ultima)) {
			int i = 0;
			CCelula aux = primeira;
			while (i < posicao - 1) {
				aux = aux.prox;
				i++;
			}
            CCelula nova = new CCelula(valorItem, aux.prox);
			aux.prox = nova;
			qtde++;
			return true;
		}
		return false;
	}

	// Imprime todos os elementos da lista usando o comando while
	public void imprime() {
		CCelula aux = primeira.prox;

		while (aux != null) {
            aux.item.printClass();
			aux = aux.prox;
		}
	}


	// Retorna o Item contido na posi��o passada por par�metro
	public Serie retornaIndice(int posicao) {
		// EXERC�CIO : deve retornar o elemento da posi��o p passada por par�metro
		// [cabe�a]->[7]->[21]->[13]->null
		// retornaIndice(2) deve retornar o elemento 21. retornaIndice de uma posi�ao
		// inexistente deve retornar null.
		// Verifica se � uma posi��o v�lida e se a lista possui elementos
		if ((posicao >= 1) && (posicao <= qtde) && (primeira != ultima)) {

			// Procura a posicao a ser retornada
			CCelula aux = primeira.prox;
			for (int i = 1; i < posicao; i++, aux = aux.prox)
				;
			return aux.item;
		}
		return null;
	}

	// Remove e retorna o primeiro item da lista (remo��o f�sica, ou seja, elimina a
	// c�lula que cont�m o elemento).
	// Retorna um Serie contendo o Item removido ou null caso a lista esteja vazia.
	public Serie removeRetornaComeco() {
		// Verifica se h� elementos na lista
		if (primeira != ultima) {
			CCelula aux = primeira.prox;
			primeira.prox = aux.prox;
			if (primeira.prox == null) // Se a c�lula cabe�a est� apontando para null, significa que o �nico elemento
										// da lista foi removido
				ultima = primeira;
			qtde--;
			return aux.item;
		}
		return null;
	}


	// Remove o �ltimo Item da lista.
	// Retorna um Serie contendo o Item removido ou null caso a lista esteja vazia.
	public Serie removeRetornaFim() {
		if (primeira != ultima) {
			CCelula aux = primeira;
			while (aux.prox != ultima)
				aux = aux.prox;
			CCelula aux2 = aux.prox;
			ultima = aux;
			ultima.prox = null;
			qtde--;
			return aux2.item;
		}
		return null;
	}


	// Localiza o Item passado por par�metro e o remove da Lista
	// O par�metro "valorItem" � o item a ser removido da lista.
	public void remove(Serie valorItem) {
		if (primeira != ultima) {
			CCelula aux = primeira;
			boolean achou = false;
			while (aux.prox != null && !achou) {
				achou = aux.prox.item.equals(valorItem);
				if (!achou)
					aux = aux.prox;
			}
			if (achou) {
				// achou o elemento
				aux.prox = aux.prox.prox;
				if (aux.prox == null)
					ultima = aux;
				qtde--;
			}
		}
	}

	public boolean removeIndice(int posicao) {
		// Verifica se � uma posi��o v�lida e se a lista possui elementos
		if ((posicao >= 1) && (posicao <= qtde) && (primeira != ultima)) {
			int i = 0;
			CCelula aux = primeira;
			while (i < posicao - 1) {
				aux = aux.prox;
				i++;
			}
			aux.prox = aux.prox.prox;
			if (aux.prox == null)
				ultima = aux;
			qtde--;
			return true;
		}
		return false;
	}

	public Serie removeRetornaIndice(int posicao) {
		// Verifica se � uma posi��o v�lida e se a lista possui elementos
		if ((posicao >= 1) && (posicao <= qtde) && (primeira != ultima)) {
			int i = 0;
			CCelula aux = primeira;
			while (i < posicao - 1) {
				aux = aux.prox;
				i++;
			}
			CCelula aux2 = aux.prox;
			aux.prox = aux.prox.prox;
			if (aux.prox == null)
				ultima = aux;
			qtde--;
			return aux2.item;
		}
		return null;
	}

}



public class ListaFlexivel{

    public static boolean isFim(String s) {
        return (
          s.length() == 3 &&
          s.charAt(0) == 'F' &&
          s.charAt(1) == 'I' &&
          s.charAt(2) == 'M'
        );
      }
    
      public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        String[] in = new String[1000];
        int numEntrada = 0; 
        int breakpoint;
        int contador = 0;
        Lista lista = new Lista();
    
            while(true){
                in [numEntrada] = leitor.nextLine();
                if(isFim(in[numEntrada]) == true){
                    numEntrada++;
                    break;
                }
                Serie serie = new Serie();
                try{
                    serie.readClass(in [numEntrada]);
                } catch (Exception e){ }
                lista.insereFim(serie);
                numEntrada ++;
            }
    
            numEntrada++;
            in[numEntrada] = leitor.nextLine();
            breakpoint = Integer.parseInt(in[numEntrada]);
    
            for(int i = 0; i < breakpoint; i++){
                Serie serie = new Serie();
                int position = 0;
                numEntrada++;
                in [numEntrada] = leitor.nextLine();
    
                if((in[numEntrada].substring(0, 2)).equals("II") == true){
                    serie.readClass(in[numEntrada].substring(3));
                    lista.insereComeco(serie);
                }
                
                //Caso o comando dado seja INSERIR EM QUALUQER POISÇÃO 
                else if((in[numEntrada].substring(0, 2)).equals("I*") == true){
                    position = Integer.parseInt(in[numEntrada].substring(3, 5));
                    serie.readClass(in[numEntrada].substring(6));
                    lista.insereIndice(serie, position+1);
                }
    
                //Caso o comando dado seja INSERIR NO FIM
                else if((in[numEntrada].substring(0, 2)).equals("IF") == true){
                    serie.readClass(in[numEntrada].substring(3));
                    lista.insereFim(serie);
                }
    
                //Caso o comando dado seja RETIRAR NO INICIO
                else if((in[numEntrada].substring(0, 2)).equals("RI") == true){
                    System.out.print("(R) ");
                    serie = lista.removeRetornaComeco();
                    System.out.println(serie.getNome());
                }
    
                //Caso o comando dado seja RETIRAR EM QUALQUER POSIÇÃO
                else if((in[numEntrada].substring(0, 2)).equals("R*") == true){
                    position = Integer.parseInt(in[numEntrada].substring(3, 5));
                    System.out.print("(R) ");
                    serie = lista.removeRetornaIndice(position+1);
                    System.out.println(serie.getNome());
                }
    
                //Caso o comando seja RETIRAR NO FIM
                else if((in[numEntrada].substring(0, 2)).equals("RF") == true){
                    System.out.print("(R) ");
                    serie = lista.removeRetornaFim();
                    System.out.println(serie.getNome());
                }
            }
            lista.imprime();
            leitor.close();
        }
}













