public class Palindromo {

  public static boolean isFim(String s) {
    return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
  }

  public static void main(String[] args) {
    String palavra = "";
    String invertida = "";
    do {     // Testa se a palavra digitada foi "FIM", para assim parar a repetição
      palavra = MyIO.readLine();

      for (int i = (palavra.length() - 1); i >= 0; i--) { // Encontra o comprimento da String depois a inverte
        invertida += palavra.charAt(i);
      }
      if (invertida.equals(palavra)) { // Testa se a String contida na variavel nova é igual a da antiga
        MyIO.println("SIM");
      } else {
        MyIO.println("NAO");
      }
    } 
    while (!isFim(palavra));
  }
}
