public class RecCiframento {

    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String cifra(String mensagem) {
        return cifra(mensagem, 3, mensagem.length()-1);
    }

    public static String cifra(String mensagem, int chave, int i) {
        String novaMensagem = "";
        
        if(i >= 0){
            novaMensagem = cifra(mensagem, 3, i-1) + (char)(mensagem.charAt(i) + chave);
        }
        return novaMensagem;
    }

    public static void main (String[] args){
        String[] entrada = new String[1000];
        int numEntrada = 0;

       //Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

       //Para cada linha de entrada, gerando a saida sendo o texto criptografado.
        for(int i = 0; i < numEntrada; i++){
            MyIO.println(cifra(entrada[i]));
        }
    }
}