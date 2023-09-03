import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int modulo = 0;

        Logistica logistica = new Logistica();
        Jogo jogo = new Jogo();

        System.out.println("::::::::::Menu principal::::::::::\n1. Modulo logistica\n2. Modo jogo\n3.Sair\nOpcao: ");
        modulo = scanner.nextInt();
        scanner.nextLine();

        if(modulo == 1){
            logistica.menuLogistica();
        } else if (modulo == 2){
            jogo.menuJogo();
        } else {
            System.out.println("Ate logo!");
        }


    }
}