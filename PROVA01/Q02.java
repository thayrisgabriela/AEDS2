import java.util.Scanner;
class Q02 {
   public static boolean isFim(String s) {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }
   public static void main(String[] args) {
      Scanner leitor = new Scanner(System.in);
      String op = "";
      int count1 = 0, count2 = 0;
      while(true){
         count1 = 0; count2 = 0; 
			op = leitor.nextLine();
			if(isFim(op) == true) {
            break; 
         }
         for(int i = 0; i < op.length(); i++) {
            if(op.charAt(0) == ')' || op.charAt(op.length() - 1) == '(') {
               count1++;
               break;
            }
            if(op.charAt(i) == '(') {
               count1++;
            }
            else if (op.charAt(i) == ')') {
               count2++;
            }
         }
         if (count1 == count2) {
            System.out.println("CORRETO");
         }
         else {
            System.out.println("INCORRETO");
         }
      }
      leitor.close();
   }
}
