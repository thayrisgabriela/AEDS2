import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;

public class Q01 {
    public static boolean returnEnd(String fim){
        boolean result;

        result = (fim.length() == 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');

        return result;
    }

    public static void main (String [] args){
        Scanner leitor = new Scanner(System.in);
        String[] in = new String[1000];
        int numEntrada = 0;
        String[] assassino = new String [10000];
        int inputAssassino = 0;
        String[] assassinado = new String [10000];
        int inputAssassinado = 0;
        int tamanho = 0;
        int contador = 1;
        int flag1 =0;
        int flag2 =0;
        String temp;

        while(true){
            in [numEntrada] = leitor.nextLine();

            if(returnEnd(in[numEntrada]) == true){
                break;
            }
            numEntrada++;
        }
        for(int z = numEntrada - 1; z >= 0; z--) 
        {
    
            for(int i = 0; i < z; i++)
            {
                if(in[i].compareTo(in[i + 1]) > 0)
                {
                    String aux = in[i];
                    in[i] = in[i + 1];
                    in[i + 1] = aux;
                }
            }
    
    
        }

        for(int i = 0; i < numEntrada; i++){
            for(int j = 0; j < in[i].length(); j++){
                tamanho = in[i].length();
                if (in[i].charAt(j) == ' '){
                    assassino[i] = in[i].substring(0, j);
                    assassinado[i] = in[i].substring(j +1, tamanho);
                }
            }
        }
        System.out.println("HALL OF MURDERERS");
        for (int i = 0; i < numEntrada- 1; i++){
            for(int j = i + 1; j < numEntrada; j++){
                if (assassino[i].equals(assassino[j]) == true){
                    contador ++;
                }
            }
            for(int j = 0; j < numEntrada; j++){
                if(assassino[i].equals(assassinado[j]) == true){
                    flag1 ++;
                }
            }
            if(i > 0)
                for(int j = 0; j < i; j++){
                    if(assassino[j].equals(assassino[i]) == true){
                        flag2 ++;
                    }
                }

            if (flag1 == 0 && flag2 == 0){
                System.out.println(assassino[i] + " " + contador);
                contador = 1;
            }
            else{
                flag1 = 0;
                flag2 = 0;
                contador = 1;
            }
        }
        leitor.close();
    }

}