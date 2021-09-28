

public class Ciframento {

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) {
        char ascii;
        String mensagem = "";
        char x, y;
        int chave = 3;
        do {
            mensagem = MyIO.readLine();

            for (int i = 0; i < mensagem.length(); i++) {
                if (mensagem.charAt(i) >= 0 && mensagem.charAt(i) <= 255) {      //repetição para percorrer a tabela ascII e verificar se o caracter está contido nela
                    if (mensagem.charAt(i) + chave > 255) {               //verifica se o caracter mais a chave vai precisar percorrer a tabela inteira e depois voltar
                        x = (char) (mensagem.charAt(i) + chave);
                        y = (char) (x - 255);
                        ascii = (char) (0 + y);
                        MyIO.print(ascii + "");
                    } else {
                        ascii = (char) (mensagem.charAt(i) + chave);             // caso não precise, já mostra o resultado do caracter mais a chave
                        MyIO.print(ascii + "");
                    }
                }
            }
            MyIO.println("");
        } 
        while (isFim(mensagem) == false);                    // testa se a palavra digitada foi "FIM", para assim parar a repetição
    }
}
