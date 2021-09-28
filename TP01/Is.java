    public class Is {

    public static void main(String[] args) {   //Funão principal e mostra  "SIM" ou "NÃO"
        String entrada = "";
        
    do{
        entrada = MyIO.readLine();

        if (contaVogal(entrada))
        MyIO.print("SIM ");
        else
        MyIO.print("NAO ");

        if (contaConsoante(entrada))
        MyIO.print("SIM ");
        else
        MyIO.print("NAO ");
        
        if (contaNInteiro(entrada))
        MyIO.print("SIM ");
        else
        MyIO.print("NAO ");

        if (contaNReal(entrada))
        MyIO.print("SIM\n");
        else
        MyIO.print("NAO\n");

    }
while (!stringEquals(entrada, "FIM"));    // Parar a repetição quando a string digitada for "FIM"
}

public static boolean contaVogal(String s) {  //Retorna se a string só contém vogal
    boolean eVogal = true;
    int i = 0;

    while (eVogal && i < s.length()) {  //Retorna se a string contem uma das opçôes apresentadas usando as funçôes
        if (eConsoante(s.charAt(i)) || eNumero(s.charAt(i)) || eVirgula(s.charAt(i)) || ePonto(s.charAt(i)) || !eLetra(s.charAt(i)))
        eVogal = false;
        i++;
    }
    return eVogal;
}

public static boolean contaConsoante(String s) {  //Retorna se a string só contém consoante
    boolean eConsoante = true;
    int i = 0;
    while (eConsoante && i < s.length()) {
        if (eVogal(s.charAt(i)) || eNumero(s.charAt(i)) || eVirgula(s.charAt(i)) || ePonto(s.charAt(i)))
        eConsoante = false;
        i++;
    }
    return eConsoante;
}

public static boolean contaNInteiro(String s) {  //Retorna se a string contém números inteiros
    boolean eNumeroInteiro = true;
    int i = 0;
    while (eNumeroInteiro && i < s.length()) {
        if (ePonto(s.charAt(i)) || eVirgula(s.charAt(i)) || eLetra(s.charAt(i)))
        eNumeroInteiro = false;
        i++;
    }
    return eNumeroInteiro;
}

public static boolean contaNReal(String s) {   //Retorna se a string contém númeors reais, sem "," ou "."
    boolean eNumeroReal = true;
    int i = 0;
    while (eNumeroReal && i < s.length()) {
        if (eLetra(s.charAt(i)))
        eNumeroReal = false;
        i++;
    }
    
    i = 0;
    boolean encontPonto = false;
    boolean encontVirgurla = false;

    while (eNumeroReal && i < s.length()) {
        if (eVirgula(s.charAt(i)) && !encontVirgurla)
        encontVirgurla = true;
        else if (eVirgula(s.charAt(i)) && encontVirgurla)
        eNumeroReal = false;
        else if (ePonto(s.charAt(i)) && !encontPonto)
        encontPonto = true;
        else if (ePonto(s.charAt(i)) && encontPonto)
        eNumeroReal = false;
        i++;
    }
    return eNumeroReal;
}

public static boolean eConsoante(char c) {   //Armazena quais letras são consoantes
    boolean consoante = false;
    if (eLetra(c) && (!eVogal(c)))
    consoante = true;
    return consoante;
}

public static boolean eVogal(char c) {  //Armazena quais letras são consoantes
    boolean vogal = false;
    if (c == 'a' || c == 'e' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
    vogal = true;
    return vogal;
}

public static boolean eLetra(char c) {   //Retorna se a string é uma letra e não um número
    boolean letra = false;
    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
    letra = true;
    return letra;
}

public static boolean eNumero(char c) {  //Retorna se a string é um número e não uma letra
    boolean numero = false;
    if (c >= '0' && c <= '9')
    numero = true;
    return numero;
}

public static boolean eVirgula(char c) {  //Retorna se a string contém virgula
    boolean virgula = false;
    if (c == ',')
    virgula = true;
    return virgula;
}

public static boolean ePonto(char c) {  //Verifica se a string contém virgula
    boolean ponto = false;
    if (c == '.')
    ponto = true;
    return ponto;
}

public static boolean stringEquals(String s1, String s2) {  //Verifica se a string é igual a "fim"
    boolean igual = false;

    if (s1.length() == s2.length()) {
        int i = 0;
        boolean fim = false;
        while ((!fim) && (i < s1.length())) {
            if (s1.charAt(i) == s2.charAt(i))
            igual = true;
    else {
        igual = false;
        fim = true;
    }
    i++;
}
}
return igual;
}
}