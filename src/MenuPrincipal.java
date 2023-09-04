import java.util.Scanner;

public class MenuPrincipal {
    public void Menu(){
        Scanner scanner = new Scanner(System.in);
        Logistica logistica = new Logistica();
        Jogo jogo = new Jogo();

        int modulo = 0;

        System.out.println("\n\n::::::::::Menu principal::::::::::\n1. Modulo logistica\n2. Modo jogo\n3.Sair\nOpcao: ");
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
