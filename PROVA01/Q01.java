import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;
class CCelula {
	public Object item;
	public CCelula prox;    
    public CCelula(Object valorItem, CCelula proxCelula)
    {
        item = valorItem;
        prox = proxCelula;
    }    			
    public CCelula(Object valorItem)
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

class CPilha {
	private CCelula topo = null;
    public void mostra() {
		for (CCelula c = topo; c != null; c = c.prox)
			System.out.println("[ " + c.item + " ]");
	}

	public void empilha(Object valorItem) {
		topo = new CCelula(valorItem, topo);
	}
	

	public Object desempilha() {
		Object item = null;
		if (topo != null) {
			item = topo.item;
			topo = topo.prox;
		}
		return item;
	}
    public void Imprimetopo(Object valorItem) {
		System.out.println(topo);
	}

}

public class Q01 {

	//Método que retorna o true caso o input seja fim
	public static boolean isFim(String s){
		boolean result;

		result = (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');

		return result;
	}

	public static void main(String[] args){
		Scanner leitor = new Scanner(System.in);
		String[] in = new String[1000];
        int numEntrada = 0;
		CPilha pilha = new CPilha();
		String auxValor;
		int valor = 0;

		while(true){
            in [numEntrada] = leitor.nextLine();
            if(isFim(in[numEntrada]) == true){
                numEntrada++;
                break;
            }

			if(in[numEntrada].charAt(0)== 'I'){
				auxValor = in[numEntrada].substring(2);
				valor = Integer.parseInt(auxValor);
				pilha.empilha(valor);
			}
			
			else if(in[numEntrada].charAt(0)== 'D'){
				System.out.println(pilha.desempilha());
			}

			else if(in[numEntrada].charAt(0)== 'T'){
				System.out.println(pilha.peek());
			}

			else if(in[numEntrada].charAt(0)== 'M'){
				System.out.println(pilha.getMenor());
			}
			
            
            numEntrada ++;
        }

		while(true){
			if(pilha.verificaVazia() == false){
                int saida = (int)pilha.desempilha();
                
                System.out.println(saida);
            }
			else	
				break;
		}
		leitor.close();
	}

}