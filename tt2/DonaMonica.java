

public class DonaMonica {

    public static void main(String[] args){


        int id_filho1;
        int id_filho2;
        int id_mae;
        int soma;
        int id_filho3;


        id_mae = MyIO.readInt();

        id_filho1 = MyIO.readInt();

        id_filho2 = MyIO.readInt();

        soma = id_filho1 + id_filho2;
        id_filho3 = id_mae - soma;

        if (id_filho1 > id_filho2 && id_filho1 > id_filho3) //estrutura para testa qual idade é maior 
        {
            MyIO.println( +id_filho1);
        }
        else if (id_filho2 > id_filho1 && id_filho2 > id_filho3)
        {
            MyIO.println( +id_filho2);
        }
        else if (id_filho3 > id_filho1 && id_filho3 > id_filho2)
        {
            MyIO.println( +id_filho3);
        }
    }
}