import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.print.attribute.SetOfIntegerSyntax;

public class Series {
    
    private String nome;
    private String formato;
    private String duracao;
    private String pais;
    private String idioma;
    private String emissora;
    private String transmissao;
    private int temp;
    private int ep;


    Series() {
        nome = "";
    }
    public Series clone(){
        Series resp = new Series();
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
        public Series(String data[]) {
        this.nome = data[0];
        this.formato = data[1];
        this.duracao = data[2];
        this.pais = data[3];
        this.idioma = data[4];
        this.emissora = data[5];
        this.transmissao = data[6];
        this.temp = Integer.parseInt(data[7]);
        this.ep = Integer.parseInt(data[8]);
    }

    public String getnome() {
        return nome;
    }

    public String getformato() {
        return formato;
    }

    public String getduracao() {
        return duracao;
    }

    public String getpais() {
        return pais;
    }

    public String getemissora() {
        return emissora;
    }

    public String gettransmissao() {
        return transmissao;
    }

    public int gettemp() {
        return temp;
    }

    public int getep() {
        return ep;
    }


    public void setnome(String nome) {
        this.nome = nome;
    }

    public void setformato(String formato) {
        this.formato = formato;
    }

    public void setduracao(String duracao) {
        this.duracao = duracao;
    }

    
    public void setpais(String pais) {
        this.pais = pais;
    }

    public void setemissora(String emissora) {
        this.emissora = emissora;
    }

    public void settransmissao(String transmissao) {
        this.transmissao = transmissao;
    }

    public void settemp(int temp) {
        this.temp = temp;
    }

    public void setep(int ep) {
        this.ep = ep;
    }

    public String removeTags(String line) {
        String newLine = "";
        int i = 0;
        while (i < line.length()) {
            if (line.charAt(i) == '<') {
                i++;
                while (line.charAt(i) != '>')
                    i++;
            } 
            else {
                newLine += line.charAt(i);
            }
            i++;
        }
        while (newLine.contains("&") && newLine.contains(";"))
            newLine = newLine.split(";")[1];

        return newLine;
    }
    /* Lê todos os dados necessarios */
    public void ler(String nomeArq) throws Exception {
        File file = new File(nomeArq);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

        String[] tmp = nomeArq.split("/");
        this.nome = tmp[tmp.length - 1];
        this.nome = this.nome.replace(".html", "").replaceAll("_", " ");

        while (!reader.readLine().contains("Formato"))
            ;
        this.formato = this.removeTags(reader.readLine());

        while (!reader.readLine().contains("Duração"))
            ;
        this.duracao = this.removeTags(reader.readLine());

        while (!reader.readLine().contains("País de origem"))
            ;
        this.pais = this.removeTags(reader.readLine());

        while (!reader.readLine().contains("Idioma original"))
            ;
        this.idioma = this.removeTags(reader.readLine());

        while (!reader.readLine().contains("Emissora de televisão original"))
            ;
        this.emissora = this.removeTags(reader.readLine());

        while (!reader.readLine().contains("Transmissão original"))
            ;
        this.transmissao = this.removeTags(reader.readLine());
        if (this.transmissao.contains("&#160;"))
            this.transmissao.replace("&#160;", "");

        while (!reader.readLine().contains("temporadas"))
            ;
        this.temp = Integer.parseInt(this.removeTags(reader.readLine()).split(" ")[0]);

        while (!reader.readLine().contains("episódios"))
            ;
        this.ep = Integer.parseInt(this.removeTags(reader.readLine()).split(" ")[0]);

        reader.close();
    }

    /* Mostra na tela todos os dados necessarios */
        public void print() {
            String out = this.nome + ' ' + this.formato + ' ' + this.duracao + ' ' + this.pais + ' '
                    + this.idioma + ' ' + this.emissora + ' ' + this.transmissao + ' '
                    + this.temp + ' ' + this.ep;
            out = out.replaceAll("  ", " ");
            System.out.println(out);
        }




    public static void main(String args[]) throws Exception {
        Scanner s = new Scanner(System.in);
        String[] seriesList = new String[200];
        int count = 0;
        while (true) {
            String line = s.nextLine();

            if (line.equals("FIM"))
                break;

            seriesList[count] = line;
            count++;
        }

        Series serie = new Series();

        for (int i = 0; i < count; i++) {
            String file = seriesList[i];
            String filePath = "/tmp/series/" + file;
            serie.ler(filePath);
            serie.print();
        }
    }

}