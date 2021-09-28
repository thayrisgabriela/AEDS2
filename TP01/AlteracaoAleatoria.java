
import java.util.Random;

class AlteracaoAleatoria{
    public static Random gerador = new Random();

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static char charAleatorio(){   //Gera um número aleatório entre 'a' e 'z' da tabela ASCII
        return (char)('a'+(Math.abs(gerador.nextInt())%26));
    }

    public static void main(String [] args){
        gerador.setSeed(4);
        char letra[] = new char[2];
        String mensagem = MyIO.readLine();
        String mensagemFinal = "";
    
        while (!isFim(mensagem)) {     /// Testa se a palavra digitada foi "FIM", para assim parar a repetição
            for(int i = 0; i < 2; i++){         //Pega duas letras sorteadas aleatoriamente na função "charAleatorio" e armazena na variavel
                letra[i] = charAleatorio();
            } 

            for(int i = 0; i < mensagem.length(); i++){    //Realiza a troca de letra
                if (mensagem.charAt(i) == letra[0]) {
                    mensagemFinal += letra[1];
                } else {
                    mensagemFinal += mensagem.charAt(i);
                }
            }
        
            MyIO.println(mensagemFinal);
            mensagemFinal = "";
            mensagem = MyIO.readLine();
        }  
    }
    
}
