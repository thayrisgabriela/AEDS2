import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PagHTML {

    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    static String htmls;

    public String capturaHTML(String url) {
    htmls = "";
    
    try {
        URL u = new URL(url);
        URLConnection uc = u.openConnection();
        InputStreamReader isr = new InputStreamReader(uc.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            htmls += (inputLine + "\n");
        }
            br.close();
            isr.close();
            
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return url;
}

    public static String contagem(String html){
        String result = new String();
        int[] somas = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i = 0; i < html.length(); i++){
        int a = (int)html.charAt(i);

        switch (a){

            case 97:
            somas[0] += 1;
            break;

            case 101:
            somas[1] += 1;
            break;
            
            case 105:
            somas[2] += 1;
            break;

            case 111:
            somas[3] += 1;
            break;

            case 117:
            somas[4] += 1;
            break;

            case 225:
            somas[5] += 1;
            break;

            case 233:
            somas[6] += 1;
            break;
            
            case 237:
            somas[7] += 1;
            break;

            case 243:
            somas[8] += 1;
            break;

            case 250:
            somas[9] += 1;
            break;

            case 224:
            somas[10] += 1;
            break;

            case 232:
            somas[11] += 1;
            break;
            
            case 236:
            somas[12] += 1;
            break;

            case 242:
            somas[13] += 1;
            break;

            case 249:
            somas[14] += 1;
            break;

            case 227:
            somas[15] += 1;
            break;

            case 245:
            somas[16] += 1;
            break;

            case 226:
            somas[17] += 1;
            break;

            case 234:
            somas[18] += 1;
            break;
            
            case 238:
            somas[19] += 1;
            break;

            case 244:
            somas[20] += 1;
            break;

            case 251:
            somas[21] += 1;
            break;
            default:

            if(html.charAt(i) > 'a' && html.charAt(i) <= 'z'){
            somas[22] = somas[22] + 1;
            }
            if(html.charAt(i) == '<' && html.charAt(i+1) == 'b' && html.charAt(i+2) == 'r' && html.charAt(i+3) == '>'){
            somas[23] = somas[23] + 1;
            } else if(html.charAt(i) == '<' && html.charAt(i+1) == 't' && html.charAt(i+2) == 'a'&& html.charAt(i+3) == 'b'&& html.charAt(i+4) == 'l'&& html.charAt(i+5) == 'e' && html.charAt(i+6) == '>'){
            somas[24] = somas[24] + 1;
            }
        }
    }
    result = "a("+somas[0]+") e("+somas[1]+") i("+somas[2]+") o("+somas[3]+") u("+somas[4]+") á("+somas[5]+") é("+somas[6]+") í("+somas[7]+") ó("+somas[8]+") ú("+somas[9]+") à("+somas[10]+") è("+somas[11]+") ì("+somas[12]+") ò("+somas[13]+") ù("+somas[14]+") ã("+somas[15]+") õ("+somas[16]+") â("+somas[17]+") ê("+somas[18]+") î("+somas[19]+") ô("+somas[20]+") û("+somas[21]+") consoante("+somas[22]+") <br>("+somas[23]+") <table>("+somas[24]+") ";
    return result;
}
    
public static void main (String[] args){
    String[] entrada = new String[1000];
    int numEntrada = 0;
    String th = new String();
    String url = "";
    String texto = new String();


      //Leitura da entrada padrao
    do {
        entrada[numEntrada] = MyIO.readLine();
    } while (isFim(entrada[numEntrada++]) == false);
    numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

      //Para cada site, gerando uma de saida da quantidade de cada caractere
    for(int i = 0; i < numEntrada; i+=2){
        url = entrada[i+1];

        
        MyIO.print(contagem(htmls));
        MyIO.print(entrada[i]+"\n");
    }
}
}