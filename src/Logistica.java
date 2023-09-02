import java.util.Scanner;

public class Logistica {
    Scanner scanner = new Scanner(System.in);
    int maxTam = 100;
    String [][] matLogistica = new String[maxTam][maxTam];
    float[][] matCustoLog = new float[maxTam][maxTam];


    public void insereFilial(String[][] matLogistica, int maxTam) {
        System.out.println("::::::::::Inserir Filial::::::::::\nInsira a filial: ");
        String filial = scanner.nextLine();

        boolean filialJaInserida = false;
        for (int i = 0; i < maxTam; i++) {
            if (matLogistica[i][0] != null && matLogistica[i][0].equals(filial)) {
                filialJaInserida = true;
                System.out.println("Erro. Filial já cadastrada.");
                break;
            }
        }

        if (!filialJaInserida) {
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
                System.out.println("Erro: armazenamento cheio.");
            }
        }
    }

    public void listaFiliais(String[][] matLogistica){
        for(int i = 0; i < maxTam; i++){
            if(matLogistica[i][0] != null){
                System.out.print("\nFilial nº " + i + ": " + matLogistica[i][0]);
            }
        }
        System.out.println("\n");
    }

    public void atualizaCusto(String[][] matLogistica){
        int filialP1, filialP2, novoCusto, escolha;

        System.out.print("::::::::::Atualizar Custo de Deslocamento::::::::::\n");
        System.out.print("Selecione as duas filiais pelo seu numero:\n");

        listaFiliais(matLogistica);

        System.out.println("Nao encontrou a filial?\n1. Encontrei minha filial\n2. Inserir filial");
        escolha = scanner.nextInt();
        scanner.nextLine();

        if(escolha == 1){
            System.out.println("\n\nPrimeira filial: ");
            filialP1 = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Segunda filial: ");
            filialP2 = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Insira o novo custo entre " + matLogistica[filialP1][0] + " e " + matLogistica[filialP2][0] + ": ");
            novoCusto = scanner.nextInt();
            scanner.nextLine();

            matCustoLog[filialP1][filialP2] = novoCusto;
        } else {
            insereFilial(matLogistica, maxTam);
            atualizaCusto(matLogistica);
        }

    }
    public void insereCusto(float[][] matCustoLog, String[][] matLogistica, int maxTam){
        int filial1, filial2, custo, atualizar;

        System.out.print("::::::::::Inserir Custo de Deslocamento::::::::::\n");
        System.out.print("Selecione as duas filiais pelo seu numero:\n");

        listaFiliais(matLogistica);
        System.out.println("Primeira filial: ");
        filial1 = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Segunda filial: ");
        filial2 = scanner.nextInt();
        scanner.nextLine();

        if(matCustoLog[filial1][filial2] != 0.0){
            System.out.println("Ja existe um custo cadastrado para essas filiais. Atualizar custo?\n1. SIM | 2. NAO\nOpcao: ");
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

    public void removeFilial(String[][] matLogistica, int maxTam){
        int deletaFilial;

        System.out.println("::::::::::Remover Filial::::::::::\nProcure o numero da filial a ser removida: ");
        listaFiliais(matLogistica);
        System.out.println("\nDeletar filial nº: ");
        deletaFilial = scanner.nextInt();
        scanner.nextLine();

        matLogistica[deletaFilial][0] = null;

        System.out.println("Filial removida com sucesso.\nFiliais no sistema: ");

        listaFiliais(matLogistica);

    }

    public void menuLogistica(){
        int opc;

        System.out.println("\n:::::::::: Menu Logistica ::::::::::");
        System.out.print("1. Insere filial\n" +
                        "2. Insere custo de movimentação\n" +
                        "3. Lista filiais\n" +
                        "4. Lista destinos\n" +
                        "5. Lista origens\n" +
                        "6. Atualiza custo de movimentação\n" +
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
                menuLogistica();
                break;
            case 3:
                listaFiliais(matLogistica);
                menuLogistica();
                break;
            case 6:
                atualizaCusto(matLogistica);
                menuLogistica();
                break;
            case 7:
                removeFilial(matLogistica, maxTam);
                menuLogistica();
        }
    }
}
