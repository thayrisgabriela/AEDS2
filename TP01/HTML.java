import java.io.*;
import java.net.*;
import java.util.*;

class HTML {

  public static Scanner sc = new Scanner(System.in);

  public static boolean isFim(String s) {
    return (
      s.length() == 3 &&
      s.charAt(0) == 'F' &&
      s.charAt(1) == 'I' &&
      s.charAt(2) == 'M'
    );
  }

  public static int VogalA(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'a') {
        cont++;
      }
    }
    return cont;
  }

  public static int VogalE(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'e') {
        cont++;
      }
    }
    return cont;
  }

  public static int VogalI(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'i') {
        cont++;
      }
    }
    return cont;
  }

  public static int VogalO(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'o') {
        cont++;
      }
    }
    return cont;
  }

  public static int VogalU(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'u') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal1(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'á') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal2(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'é') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal3(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'í') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal4(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'ó') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal5(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'ú') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal6(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'à') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal7(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'è') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal8(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'ì') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal9(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'ò') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal10(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'ù') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal11(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'ã') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal12(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'õ') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal13(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'â') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal14(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'ê') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal15(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'î') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal16(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'ô') {
        cont++;
      }
    }
    return cont;
  }

  public static int Vogal17(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (html.charAt(i) == 'û') {
        cont++;
      }
    }
    return cont;
  }

  public static int Consoantes(String html) {
    int cont = 0;

    for (int i = 0; i < html.length(); i++) {
      // verifica se ? uma vogal de a a u
      if (
        html.charAt(i) == 'b' ||
        html.charAt(i) == 'c' ||
        html.charAt(i) == 'd' ||
        html.charAt(i) == 'f' ||
        html.charAt(i) == 'g' ||
        html.charAt(i) == 'h' ||
        html.charAt(i) == 'j' ||
        html.charAt(i) == 'k' ||
        html.charAt(i) == 'l' ||
        html.charAt(i) == 'm' ||
        html.charAt(i) == 'n' ||
        html.charAt(i) == 'p' ||
        html.charAt(i) == 'q' ||
        html.charAt(i) == 'r' ||
        html.charAt(i) == 's' ||
        html.charAt(i) == 't' ||
        html.charAt(i) == 'v' ||
        html.charAt(i) == 'w' ||
        html.charAt(i) == 'x' ||
        html.charAt(i) == 'y' ||
        html.charAt(i) == 'z'
      ) {
        cont++;
      }
    }
    return cont;
  }

  public static int isBr(String html) {
    int cont = 0;
    if (
      html.charAt(0) == '<' &&
      html.charAt(1) == 'b' &&
      html.charAt(2) == 'r' &&
      html.charAt(3) == '>'
    ) {
      cont++;
    }
    return cont;
  }

  public static int isTable(String html) {
    int cont = 0;
    for (int i = 0; i < html.length(); i++) {
      if (
        html.charAt(0) == '<' &&
        html.charAt(1) == 't' &&
        html.charAt(2) == 'a' &&
        html.charAt(3) == 'b' &&
        html.charAt(4) == 'l' &&
        html.charAt(5) == 'e' &&
        html.charAt(6) == '>'
      ) {
        cont++;
      }
    }
    return cont;
  }

  public static String getHtml(String endereco) {
    URL url;
    InputStream is = null;
    BufferedReader br;
    String resp = "", line;

    try {
      url = new URL(endereco);
      is = url.openStream(); // throws an IOException
      br = new BufferedReader(new InputStreamReader(is));

      while ((line = br.readLine()) != null) {
        resp += line + "\n";
      }
    } catch (MalformedURLException mue) {
      mue.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    try {
      is.close();
    } catch (IOException ioe) {
      // nothing to see here

    }

    return resp;
  }

  public static void main(String[] args) {
    String nome = "", endereco = "";
    int auxil = 0;
    String html = "";
    do {
      // ve se esta digitando o nome ou o endereco
      if (auxil % 2 == 0) {
        nome = MyIO.readLine();
      } else {
        endereco = MyIO.readLine();
        // pega o html
        html = getHtml(endereco);
        html = html.replaceAll("\\s+", "");
        System.out.print("a" + "(" + VogalA(html) + ")" + " ");
        System.out.print("e" + "(" + VogalE(html) + ")" + " ");
        System.out.print("i" + "(" + VogalI(html) + ")" + " ");
        System.out.print("o" + "(" + VogalO(html) + ")" + " ");
        System.out.print("u" + "(" + VogalU(html) + ")" + " ");
        System.out.print("á" + "(" + Vogal1(html) + ")" + " ");
        System.out.print("é" + "(" + Vogal2(html) + ")" + " ");
        System.out.print("í" + "(" + Vogal3(html) + ")" + " ");
        System.out.print("ó" + "(" + Vogal4(html) + ")" + " ");
        System.out.print("ú" + "(" + Vogal5(html) + ")" + " ");
        System.out.print("à" + "(" + Vogal6(html) + ")" + " ");
        System.out.print("è" + "(" + Vogal7(html) + ")" + " ");
        System.out.print("ì" + "(" + Vogal8(html) + ")" + " ");
        System.out.print("ò" + "(" + Vogal9(html) + ")" + " ");
        System.out.print("ù" + "(" + Vogal10(html) + ")" + " ");
        System.out.print("ã" + "(" + Vogal11(html) + ")" + " ");
        System.out.print("õ" + "(" + Vogal12(html) + ")" + " ");
        System.out.print("â" + "(" + Vogal13(html) + ")" + " ");
        System.out.print("ê" + "(" + Vogal14(html) + ")" + " ");
        System.out.print("î" + "(" + Vogal15(html) + ")" + " ");
        System.out.print("ô" + "(" + Vogal16(html) + ")" + " ");
        System.out.print("û" + "(" + Vogal17(html) + ")" + " ");
        System.out.print("consoantes" + "(" + Consoantes(html) + ")" + " ");
        System.out.print("<br>" + "(" + isBr(html) + ")" + " ");
        System.out.print("<table>" + "(" + isTable(html) + ")" + " ");
        System.out.print(nome + "\n");
      }

      auxil++;
      // para com a palavra fim
    } while (isFim(nome) == false);
  }
}
