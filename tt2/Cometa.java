public class Cometa {
    public static void main(String[] args){
        int ano, vezes;

        ano = MyIO.readInt();
        
        vezes = (ano - 10 ) / 76 ;
        vezes++;

        MyIO.println( +vezes * 76 + 10);
    }
}
