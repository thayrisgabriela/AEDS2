import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;

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
        System.out.println(this.nome + " " + this.formato + " " + this.duracao + " " + this.pais + " " + this.idioma + " " + this.emissora + " " +
        this.transmissao + " " + this.temp + " " + this.ep);
    }

    //método para tratar a linha, deixar apenas números e converter o retorno de String para Inteiro
    public int converteStr(String line){
        String resp = "";
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9'){ //caso o caracter seja um número ele é concatenado a variável resp
                resp += line.charAt(i);
            } else { //caso seja outro caracter, o i recebe o valor da condição de parada e o método de repetição é encerrado
                i = line.length();
            }
        }
        return Integer.parseInt(resp); //conversão da string resp para número inteiro a ser retornado
    }
    
    //método para a remoção das tags da linha lida do arquivo para retornar apenas o que é desejado
    public String removeTags(String line){
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

        return resp;
    }
    //método para tratar o nome do arquivo e retornar o nome da série
    public String searchnome(String fileNome){
        String resp = "";
        for(int i = 0; i < fileNome.length(); i++){
            if(fileNome.charAt(i)  == '_'){ //caso o caracter na posição i seja igual ao '_' a variável resp recebe um espaço em branco
                resp += ' ';
            } else { //caso não tenha espaço em branco o caracter é concatenado à string resp
                resp += fileNome.charAt(i);
            }
        }
        return resp.substring(0, resp.length()-5); //retorno da substring resp retirando os 5 últimos caracteres relacionados à extensão do arquivo
    }
    //método para leitura do arquivo .html e tratamento das linhas
    public void readClass(String fileNome){
        String line;
        String resp = "";
        String file = "/tmp/series/" + fileNome;
        try {
            FileReader fileReader = new FileReader(file); //declaração da variável fileReader que será recebida pelo bufferedReader

            BufferedReader br = new BufferedReader(fileReader); //declaração do bufferedReader para leitura do arquivo
            
            //set nome da série
            this.nome = searchnome(fileNome);
            
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
            this.temp = converteStr(removeTags(br.readLine()));

            //set episódios da série
            while(!br.readLine().contains("N.º de episódios"));
            this.ep = converteStr(removeTags(br.readLine()));
            
            
            br.close();         
        //Tratamento de exceções
        } catch(FileNotFoundException e) {
                    
        } catch(IOException e) {
            
        }
    }
}   

//Classe lista feita pelo professor Rodrigo Richard
class Lista {
	private Serie[] array;
	private int n;
    private int compareCounter;

	/**
	 * Construtor da classe.
	 */
	public Lista() {
		this(6);
	}

	/**
	 * Construtor da classe.
	 * 
	 * @param tamanho Tamanho da lista.
	 */
	public Lista(int tamanho) {
		array = new Serie[tamanho];
		n = 0;
	}

	/**
	 * Insere um elemento na primeira posicao da lista e desloca os demais elementos
	 * para o fim da lista.
	 * 
	 * @param Elemento a ser inserido.
	 */
	public boolean inserirInicio(Serie item) {
		if (n < array.length) {
			// Desloca elementos para o fim do array
			for (int i = n; i > 0; i--)
				array[i] = array[i - 1];

			array[0] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
	 * 
	 * @param Elemento a ser inserido.
	 */
	public boolean inserirFim(Serie item) {
		// validar insercao
		if (n < array.length) {
			array[n] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Insere um elemento em uma posicao especifica e move os demais elementos para
	 * o fim da lista.
	 * 
	 * @param item: elemento a ser inserido.
	 * @param pos:  Posicao de insercao.
	 */
	public boolean inserir(Serie item, int pos) {

		// validar insercao
		if (n < array.length && pos >= 0 && pos <= n) {
			// Desloca elementos para o fim do array
			for (int i = n; i > pos; i--)
				array[i] = array[i - 1];

			array[pos] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Remove um elemento da primeira posicao da lista e movimenta os demais
	 * elementos para o inicio da mesma.
	 * 
	 * @return Elemento a ser removido.
	 */
	public Serie removerInicio() {
		if (n > 0) {
			Serie item = array[0];
			n--;

			for (int i = 0; i < n; i++)
				array[i] = array[i + 1];

            return item;
		}
		return null;
	}

	/**
	 * Remove um elemento da ultima posicao da lista.
	 * 
	 * @return Elemento a ser removido.
	 */
	public Serie removerFim() {
		if (n > 0){
            return array[--n];
        }
		return null;
	}

	/**
	 * Remove um elemento de uma posicao especifica da lista e movimenta os demais
	 * elementos para o inicio da mesma.
	 * 
	 * @param pos: Posicao de remocao.
	 * @return Elemento a ser removido.
	 */
	public Serie remover(int pos) {
		if (n > 0 && pos >= 0 && pos < n) {
			Serie item = array[pos];
			n--;

			for (int i = pos; i < n; i++)
				array[i] = array[i + 1];

			return item;
		}
		return null;
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {

		for (int i = 0; i < n; i++) {
			array[i].printClass();
		}
		
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * 
	 * @param x int elemento a ser pesquisado.
	 * @return true se o item existir, false caso contrário.
	 */
    public boolean pesquisar(Serie item) {
		for (int i = 0; i < n; i++){
            compareCounter++;
			if (array[i].equals(item)) {return true;}
        }
		return false;
	}

    public void matricula(long time){ // GERADOR DE ARQUIVO DE LOG

        try {
            File myObj = new File("matrícula_sequencial.txt");
            if (myObj.createNewFile()) {
            } else {
            }
          } catch (IOException e) {
            e.printStackTrace();
          }

          try {
            FileWriter myWriter = new FileWriter("matrícula_sequencial.txt");
            myWriter.write("737163" + "\t" + time + "\t" + compareCounter);
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }

    }
}

public class ListaSequencial{

    public static boolean isFim(String s){
        boolean result;

        result = (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');

        return result;
    }

    public static void main(String [] args){
        Scanner leitor = new Scanner(System.in);

        Lista lista = new Lista(50);
        String[] in = new String[1000];
        int inputNumber = 0;
        int breakpoint;

        while(true){
            in [inputNumber] = leitor.nextLine();
            if(isFim(in[inputNumber]) == true){
                inputNumber++;
                break;
            }
            Serie serie = new Serie();
            try{
                serie.readClass(in [inputNumber]);
            } catch (Exception e){ }
            lista.inserirFim(serie);
            inputNumber ++;
        }

        inputNumber++;
        in[inputNumber] = leitor.nextLine();
        breakpoint = Integer.parseInt(in[inputNumber]);

        for(int i = 0; i < breakpoint; i++){
            Serie serie = new Serie();
            int position = 0;
            inputNumber++;
            in [inputNumber] = leitor.nextLine();

            if((in[inputNumber].substring(0, 2)).equals("II") == true){
                serie.readClass(in[inputNumber].substring(3));
                lista.inserirInicio(serie);
            }
            else if((in[inputNumber].substring(0, 2)).equals("I*") == true){
                position = Integer.parseInt(in[inputNumber].substring(3, 5));
                serie.readClass(in[inputNumber].substring(6));
                lista.inserir(serie, position);
            }
            else if((in[inputNumber].substring(0, 2)).equals("IF") == true){
                serie.readClass(in[inputNumber].substring(3));
                lista.inserirFim(serie);
            }
            else if((in[inputNumber].substring(0, 2)).equals("RI") == true){
                System.out.print("(R) ");
                serie = lista.removerInicio();
                System.out.println(serie.getNome());
            }
            else if((in[inputNumber].substring(0, 2)).equals("R*") == true){
                position = Integer.parseInt(in[inputNumber].substring(3, 5));
                System.out.print("(R) ");
                serie = lista.remover(position);
                System.out.println(serie.getNome());
            }
            else if((in[inputNumber].substring(0, 2)).equals("RF") == true){
                System.out.print("(R) ");
                serie = lista.removerFim();
                System.out.println(serie.getNome());
            }
        }
        lista.mostrar();
        leitor.close();
    }

}