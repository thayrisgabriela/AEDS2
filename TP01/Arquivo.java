import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;


public class Arquivo{


    public static void main(String [] args) throws IOException{
        RandomAccessFile arq = new RandomAccessFile("saida.txt", "rw");

        int numEntrada = MyIO.readInt();        //Leitura da quantidade de valores

        for(int i = 0; i < numEntrada; i++){       //Leitura da entrada padrao e gravar em arquivo numeros reais
            arq.seek(i * 8);
            arq.writeDouble(MyIO.readDouble());
        }

        arq.close();       //Fechar arquivo, indispensavel para gravar

        RandomAccessFile arqN = new RandomAccessFile("saida.txt", "r");

        for(int i = numEntrada - 1; i>-1; i--){      //Para cada linha gravada, ler e mostrar a saída com os números invertidos
            arqN.seek(i * 8);
            double num = arqN.readDouble();
            if (num % 1 != 0){
            MyIO.println(num);
            }
            else
            MyIO.println((int)num);
        }

        arqN.close();
    }


}