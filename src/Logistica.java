import java.util.Scanner;

public class Logistica {
    Scanner scanner = new Scanner(System.in);
    int maxTam = 100;
    String [][] matLogistica = new String[maxTam][maxTam];
    float[][] matCustoLog = new float[maxTam][maxTam];


    public void insereFilial(String[][] matLogistica, int maxTam){

        System.out.println("::::::::::Inserir Filial::::::::::\nInsira a filial: ");
        String filial = scanner.nextLine();

        // Encontre a primeira linha vazia na matriz
        int linhaVazia = -1;
        for (int i = 0; i < maxTam; i++) {
            if (matLogistica[i][0] == null) {
                linhaVazia = i;
                break;
            }
        }

        if (linhaVazia != -1) {
            matLogistica[linhaVazia][0] = filial;
            System.out.println("Filial inserida com sucesso\n");
        } else {
            System.out.println("Erro: Não há mais linhas vazias na matriz.");
        }
    }

    public void listaFiliais(String[][] matLogistica){
        for(int i = 0; i < maxTam; i++){
            if(matLogistica[i][0] != null){
                System.out.print("\nFilial nº " + i + ": " + matLogistica[i][0]);
            }
        }
    }

    public void insereCusto(float[][] matCustoLog, String[][] matLogistica, int maxTam){
        int filial1, filial2, custo, atualizar;

        System.out.print("::::::::::Inserir Custo de Deslocamento::::::::::\n");
        System.out.print("Selecione as duas filiais pelo seu numero:\n");

        listaFiliais(matLogistica);
        System.out.println("\nPrimeira filial: ");
        filial1 = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Segunda filial: ");
        filial2 = scanner.nextInt();
        scanner.nextLine();

        if(matCustoLog[filial1][filial2] != 0.0){
            System.out.println("Ja existe um custo cadastrado para essas filiais. Atualizar cuusto?\n1. SIM | 2. NAO\nOpcao: ");
            atualizar = scanner.nextInt();
            scanner.nextLine();

            if(atualizar == 1){
                System.out.println("Insira o custo entre " + matLogistica[filial1][0] + " e " + matLogistica[filial2][0] + ": ");
                custo = scanner.nextInt();
                scanner.nextLine();

                matCustoLog[filial1][filial2] = custo;
            } else {
                System.out.println("custo nao atualizado.");
            }
        } else {
            System.out.println("\nInsira o custo entre " + matLogistica[filial1][0] + " e " + matLogistica[filial2][0] + ": ");
            custo = scanner.nextInt();
            scanner.nextLine();

            matCustoLog[filial1][filial2] = custo;

            System.out.println("Custo inserido com sucesso.");
        }
    }

    public void menuLogistica(){
        int opc;

        System.out.println(":::::::::: Menu Logistica ::::::::::");
        System.out.print("1. Insere filial\n" +
                        "2. Insere custo de movimentação\n" +
                        "3. Lista filiais\n" +
                        "4. Lista destinos\n" +
                        "5. Lista origens\n" +
                        "6. Atualiza movimentação\n" +
                        "7. Remove filial\n" +
                        "8. Remove tempo\n" +
                        "9. Calcula custos\n" +
                        "0. Voltar ao menu inicial\n");

        System.out.println("Opção: ");
        opc = scanner.nextInt();
        scanner.nextLine();

        switch (opc){
            case 1:
                int insere = 1;
                while (insere == 1) {
                    insereFilial(matLogistica, maxTam);
                    System.out.print("Inserir mais uma filial? 1. SIM | 2. NAO\nOpcao: ");
                    insere = scanner.nextInt();
                    scanner.nextLine();
                }
                menuLogistica();
                break;
            case 2:
                insereCusto(matCustoLog, matLogistica, maxTam);

                break;
            case 3:
                listaFiliais(matLogistica);
                menuLogistica();
        }
    }
}
