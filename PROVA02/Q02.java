import java.util.Scanner;

class Pais {

  private String nome;
  private int ouro;
  private int prata;
  private int bronze;
  private int totalMedalhas;

  Pais(String nome, int ouro, int prata, int bronze) {
    this.nome = nome;
    this.ouro = ouro;
    this.prata = prata;
    this.bronze = bronze;
    this.totalMedalhas = ouro + prata + bronze;
  }

  public String getNome() {
    return nome;
  }

  public int getOuro() {
    return ouro;
  }

  public int getPrata() {
    return prata;
  }

  public int getBronze() {
    return bronze;
  }

  public int getTotalMedalhas() {
    return totalMedalhas;
  }

  public void setOuro(int ouro) {
    this.ouro = ouro;
  }

  public void setPrata(int prata) {
    this.prata = prata;
  }

  public void setBronze(int bronze) {
    this.bronze = bronze;
  }

  public void setTotalMedalhas(int totalMedalhas) {
    this.totalMedalhas = totalMedalhas;
  }
}

class Q02 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Pais[] paises = new Pais[500];
    int num = 0;

    num = Integer.parseInt(sc.nextLine());
    for (int i = 0; i < num; i++) {
      String pais[] = new String[4];
      pais = sc.nextLine().split(" ");
      paises[i] =
        new Pais(
          pais[0],
          Integer.parseInt(pais[1]),
          Integer.parseInt(pais[2]),
          Integer.parseInt(pais[3])
        );
    }
    for (int i = 0; i < num; i++) {
      for (int j = 0; j < num; j++) {
        if (paises[i].getTotalMedalhas() > paises[j].getTotalMedalhas()) {
          Pais aux = paises[i];
          paises[i] = paises[j];
          paises[j] = aux;
        }
        if (paises[i].getOuro() > paises[j].getOuro()) {
          Pais aux = paises[i];
          paises[i] = paises[j];
          paises[j] = aux;
        } else if (paises[i].getOuro() == paises[j].getOuro()) {
          if (paises[i].getPrata() > paises[j].getPrata()) {
            Pais aux = paises[i];
            paises[i] = paises[j];
            paises[j] = aux;
          } else if (paises[i].getPrata() == paises[j].getPrata()) {
            if (paises[i].getBronze() > paises[j].getBronze()) {
              Pais aux = paises[i];
              paises[i] = paises[j];
              paises[j] = aux;
            }
          }
        }
      }
    }

    for (int i = 0; i < num; i++) {
      System.out.println(
        paises[i].getNome() +
        " " +
        paises[i].getOuro() +
        " " +
        paises[i].getPrata() +
        " " +
        paises[i].getBronze()
      );
    }
  }
}
