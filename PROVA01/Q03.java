import java.util.*;

class CFila {
  Object[] array;
  int primeiro, ultimo;

  CFila() {
    this(5);
  }

  CFila(int tamanho) {
    array = new Object[tamanho + 1];
    primeiro = ultimo = 0;
  }

  public boolean inserir(Object str) {
    if (((ultimo + 1) % array.length) == primeiro)
      return false;

    array[ultimo] = str;
    ultimo = (ultimo + 1) % array.length;
    return true;
  }

  public Object remover() throws Exception {
    if (primeiro == ultimo) {
      throw new Exception("Fila vazia");
    }

    return array[primeiro++];
  }

  public boolean inserirDuplicado (Object item) {
    if (((ultimo + 1) % array.length) == primeiro)
      return false;
    
    MyIO.println("debug ID1");
    array[ultimo] = item;
    ultimo = (ultimo + 1) % array.length;
    
    if (((ultimo + 1) % array.length) == primeiro)
      return false;

    
    MyIO.println("debug ID2");
    for (int i = array.length - 1 + ultimo; i > primeiro; i--) {
    MyIO.println("debug ID3");
      array[i % array.length] = array[(i - 1) % array.length];
    }
    array[primeiro] = item;
    MyIO.println("debug ID4");
    return true;
  }


  public void mostrar() {
    int i = primeiro;
    MyIO.println("debug mostrar");
    do {
      MyIO.println((String)array[i]);
      i = (i + 1) % array.length;
    } while (i != primeiro);
  }
}

public class Q03 {
    
  public static boolean isFim(String s) {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
  }

  public static void main(String[] args) {
    MyIO.setCharset("UTF-8");
    String[] entrada = new String[1000];
    int numEntrada = 0;

    do {
      entrada[numEntrada] = MyIO.readLine();
    } while (isFim(entrada[numEntrada++]) == false);
    numEntrada--;

    CFila fila = new CFila(numEntrada * 2);

    for (int i = 0; i < numEntrada; i++) {
      String nome = "", pedido = "";
      int j = 2;
      MyIO.println("debug loop1");
      while (entrada[i].charAt(j) != ' ') {
        MyIO.println("debug while1");
        nome += entrada[i].charAt(j++);
      }

      j = entrada[i].length() - 1;
      while (entrada[i].charAt(j) != ' ') {
        MyIO.println("debug while2");
        j--;
      }

      while (j < entrada[i].length()) {
        pedido += entrada[i].charAt(j);
      }

      if (entrada[i].charAt(0) == 'I') {
        fila.inserir(nome + pedido);
      } else if (entrada[i].charAt(0) == 'D') { 
        fila.inserirDuplicado(nome + pedido);
      }
      MyIO.println("debug loop2");
    }
    
    fila.mostrar();

  }
}