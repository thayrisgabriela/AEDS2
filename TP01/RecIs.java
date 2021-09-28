class RecIs {

    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean contaVogal (String s){
        return contaVogal(s, 0);
    }
    
    public static boolean contaVogal (String s, int i){ //Retorna se a string só contém vogal
        boolean result = true;
        boolean vog = false;
        String vogais = "aeiouAEIOU";
        int j = 0;
        
        if(i < s.length()){
        j = 0;
        vog = false;

        do{
            if(s.charAt(i) == vogais.charAt(j) && !vog){
            vog = true;
            result = true && contaVogal(s, i+1);
        } 
            else{
            result = false;
        }
        j++;
        } while(j < 10 && !vog);
        if(!result){
        i = s.length();
    }
    }
    return result;
}

    public static boolean contaConsoante (String s){
    return contaConsoante(s, 0);
}

    public static boolean contaConsoante (String s, int i){   //Retorna se a string só contém consoante
        boolean result = true;
        boolean vog = false;
        String vogais = "aeiouAEIOU";
        String num = "0123456789";
        int j = 0;

        if(i < s.length()){
        j = 0;
        do{
            if((s.charAt(i) != vogais.charAt(j)) && (s.charAt(i) != num.charAt(j)) && !vog && (s.charAt(i)>'a'|| s.charAt(i)>'A') && (s.charAt(i)<='z'|| s.charAt(i)<='Z')){
            result = true && contaConsoante(s, i+1);
            }
            else {
            result = false;
            vog = true;
        }
        j++;
    } while(j < 10 && !vog);
    if(!result){
        i = s.length();
    }
}
return result;
}

    public static boolean contaInteiro (String s){   //Retorna se a string contém números inteiros
    return contaInteiro(s, 0);
}

    public static boolean contaInteiro (String s, int i){
        boolean result = true;
        boolean n = false;
        String nums = "0123456789";
        int j = 0;

        if(i < s.length()){
        j = 0;
        n = false;

        do{
            if(s.charAt(i) == nums.charAt(j) && !n){
            n = true;
            result = true && contaInteiro(s, i+1);
        }
        else{
            result = false;
        }
        j++;
    } while(j < 10 && !n);
    if(!result){
        i = s.length();
    }
}
return result;
}

    public static boolean contaReais (String s){    //Retorna se a string contém númeors reais, sem "," ou "."
    return contaReais(s, 0, false);
}

    public static boolean contaReais (String s, int i, boolean point){
        boolean result = true;
        boolean n = false;
        String nums = "0123456789";
        int j = 0;

        if(i < s.length()){
        j = 0;
        n = false;
        
        do{
            if(s.charAt(i) == nums.charAt(j) && !n){
            n = true;
            result = true && contaReais(s, i+1, false);
        }
        else if(!point && (s.charAt(i) == '.' || s.charAt(i) == ',')){
            n = true;
            result = true && contaReais(s, i+1, true);
        }
        else{
            result = false;
        }
        j++;
    } while(j < 10 && !n && !point);
    if(!result){
        i = s.length();
    }
}
return result;
}

    public static String isAll (String s){   //retorna o SIM ou NAO para cada chamada de função
        String result = new String();
        result = "";

        if(contaVogal(s)){
            result = result + "SIM ";
        }
        else {
            result = result + "NAO ";
        }

        if(contaConsoante(s)){
            result = result + "SIM ";
        }
        else {
            result = result + "NAO ";
        }

        if(contaInteiro(s)){
            result = result + "SIM ";
        } else {
            result = result + "NAO ";
        }

        if(contaReais(s)){
            result = result + "SIM";
        } else {
            result = result + "NAO";
        }
        return result;
    }

    public static void main (String[] args){
        String[] entrada = new String[1000];
        int numEntrada = 0;

       //Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
       numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

       //Para cada linha de entrada, gerando uma de saida SIM e NAO dos resultados
       //X1 - vogais | X2 - consoantes | X3 - numero int | X4 - numero real
        for(int i = 0; i < numEntrada; i++){
            MyIO.println(isAll(entrada[i]));
        }
    }
}