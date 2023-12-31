import java.sql.Struct;
import java.util.Scanner;

public class Logistica {
    Scanner scanner = new Scanner(System.in);
    MenuPrincipal menuPrincipal = new MenuPrincipal();
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
    public void insereCusto(float[][] matCustoLog, String[][] matLogistica, int maxTam){ /* Insere Movimentação*/
        int filial1, filial2, custo, atualizar;

        System.out.print("::::::::::Inserir Custo de Deslocamento::::::::::\n");
        System.out.print("Selecione as duas filiais pelo seu numero:\n");

        listaFiliais(matLogistica);
        System.out.println("Origem: ");
        filial1 = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Destino: ");
        filial2 = scanner.nextInt();
        scanner.nextLine();

        boolean filialNaoINserida = false;
        for(int i = 0; i < maxTam; i++){
            if (matLogistica[filial1][0] == null || matLogistica[filial2][0] == null){
                filialNaoINserida = true;
            }
        }

        if(filialNaoINserida == true){
            System.out.println("Erro. Filial nao inserida.");
            menuLogistica();
        }

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
    public void atualizaMovimentacao(String[][] matLogistica){
        int filialP1, filialP2, novoCusto, escolha;

        System.out.print("::::::::::Atualizar Custo de Deslocamento::::::::::\n");
        System.out.print("Selecione as duas filiais pelo seu numero:\n");

        listaFiliais(matLogistica);

        System.out.println("Nao encontrou a filial?\n1. Encontrei minha filial\n2. Inserir filial");
        escolha = scanner.nextInt();
        scanner.nextLine();

        if(escolha == 1){
            System.out.println("Filial origem: ");
            filialP1 = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Filial destino: ");
            filialP2 = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Insira o novo custo entre " + matLogistica[filialP1][0] + " e " + matLogistica[filialP2][0] + ": ");
            novoCusto = scanner.nextInt();
            scanner.nextLine();

            matCustoLog[filialP1][filialP2] = novoCusto;

            System.out.println("Custo atualizado com sucesso.\n" + filialP1 + " - " + filialP2 + " = " + matCustoLog[filialP1][filialP2]);
        } else {
            insereFilial(matLogistica, maxTam);
            atualizaMovimentacao(matLogistica);
        }
    }
    public void calculaCustosFiliais(float[][] matCustoLog, int maxTam){
        int custoTotal = 0;

        for(int i = 0; i < maxTam; i++){
            for(int j = 0; j < maxTam; j++){
                if(matCustoLog[i][j] != 0.0){
                    custoTotal += matCustoLog[i][j];
                }
            }
        }
        System.out.println("Custo de deslocamento entre todas as filiais = " + custoTotal);
    }
    public void listaFiliaisProximas(String[][] matLogistica, float[][] matCustoLog, int maxTam, int filialP1) {
        System.out.println("Filiais próximas a " + matLogistica[filialP1][0] + ": ");

        for (int i = 0; i < maxTam; i++) {
            float custo = matCustoLog[filialP1][i];
            if (custo != 0.0) {
                String nomeFilial = matLogistica[i][0];
                System.out.println(nomeFilial + ": " + custo + "km");
            }
        }
    }
    public void removeFilial(String[][] matLogistica, int maxTam){
        String deletaFilial;

        System.out.println("::::::::::Remover Filial::::::::::\nProcure o nome da filial a ser removida: ");
        listaFiliais(matLogistica);
        System.out.println("\nInsira o nome da filial a ser excluida: ");
        deletaFilial = scanner.nextLine();

        for(int i = 0; i < maxTam; i++) {
            if (deletaFilial.equals(matLogistica[i][0])) {
                matLogistica[i][0] = null;
            }
        }
        System.out.println("Filial removida com sucesso.\nFiliais no sistema: ");
        listaFiliais(matLogistica);
    }
    public void removeCusto(String[][] matLogistica){
        int l1, l2;

        System.out.println("::::::::::Remover custo de deslocamento::::::::::\nEscolha quais os locais que deseja remover:");
        listaFiliais(matLogistica);
        System.out.println("Primeira filial:");
        l1 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Segunda filial:");
        l2 = scanner.nextInt();
        scanner.nextLine();

        matCustoLog[l1][l2] = 0;

        System.out.println("Custo entre " + matLogistica[l1][0] + " e " + matLogistica[l2][0] + " removido com sucesso.");

    }
    public void listaLocaisDestino(String[][] matLogistica, float[][] matCustoLog, int maxTam, int filialD1) {

        boolean filialNaoINserida = false;
        for(int i = 0; i < maxTam; i++){
            if (matLogistica[filialD1][0] == null){
                filialNaoINserida = true;
            }
        }

        if(filialNaoINserida == true){
            System.out.println("Erro. Filial nao inserida.");
            menuLogistica();
        }

        System.out.println("Locais próximos a " + matLogistica[filialD1][0] + ":\n");

        for (int i = 0; i < maxTam; i++) {
            float custo = matCustoLog[filialD1][i];
            if (custo != 0.0) {
                String nomeLocal = matLogistica[i][0];
                System.out.println(nomeLocal + ": " + custo + " km");
            }
        }
    }
    public void listaLocaisOrigem(String[][] matLogistica, float[][] matCustoLog, int maxTam, int filialO1){

        boolean filialNaoINserida = false;
        for(int i = 0; i < maxTam; i++){
            if (matLogistica[filialO1][0] == null){
                filialNaoINserida = true;
            }
        }

        if(filialNaoINserida == true){
            System.out.println("Erro. Filial nao inserida.");
            menuLogistica();
        }

        System.out.println("Locais próximos a " + matLogistica[filialO1][0] + ":\n");

        for (int i = 0; i < maxTam; i++) {
            float custo = matCustoLog[i][filialO1];
            if (custo != 0.0) {
                String nomeLocal = matLogistica[i][0];
                System.out.println(nomeLocal + ": R$" + custo);
            }
        }
    }
    public void menuLogistica(){
        int opc;

        System.out.println("\n:::::::::: Menu Logistica ::::::::::");
        System.out.print("1. Insere filial\n" +
                        "2. Insere custo de movimentação\n" +
                        "3. Lista filiais proximas\n" +
                        "4. Lista destinos\n" +
                        "5. Lista origens\n" +
                        "6. Atualiza custo de movimentação\n" +
                        "7. Remove filial\n" +
                        "8. Remove custo\n" +
                        "9. Calcula custos filiais\n" +
                        "0. Voltar ao menu inicial\n");

        System.out.println("Opção: ");
        opc = scanner.nextInt();
        scanner.nextLine();

        switch (opc){
            case 1:
                int insereFilial = 1;
                while (insereFilial == 1) {
                    insereFilial(matLogistica, maxTam);
                    System.out.print("Inserir mais uma filial? 1. SIM | 2. NAO\nOpcao: ");
                    insereFilial = scanner.nextInt();
                    scanner.nextLine();
                }
                menuLogistica();
                break;
            case 2:
                int insereCusto = 1;
                while (insereCusto == 1){
                    insereCusto(matCustoLog, matLogistica, maxTam);
                    System.out.println("Inserir mais um custo? 1. SIM | 2. NAO\nOpcao: ");
                    insereCusto = scanner.nextInt();
                    scanner.nextLine();
                }
                menuLogistica();
                break;
            case 3:
                int fil1;
                System.out.print("::::::::::Filiais proximas::::::::::\n");
                listaFiliais(matLogistica);
                System.out.println("Insira o nº da filial: ");
                fil1 = scanner.nextInt();
                scanner.nextLine();

                listaFiliaisProximas(matLogistica, matCustoLog, maxTam, fil1);
                menuLogistica();
                break;
            case 4:
                int f1;
                System.out.print("::::::::::Locais destino::::::::::\n");
                listaFiliais(matLogistica);
                System.out.println("Insira o nº do local de origem: ");
                f1 = scanner.nextInt();
                scanner.nextLine();

                listaLocaisDestino(matLogistica, matCustoLog, maxTam, f1);
                menuLogistica();
                break;
            case 5:
                int localD;
                System.out.print("::::::::::Locais origem::::::::::\n");
                listaFiliais(matLogistica);
                System.out.println("Insira o nº do local: ");
                localD = scanner.nextInt();
                scanner.nextLine();

                listaLocaisOrigem(matLogistica, matCustoLog, maxTam, localD);
                menuLogistica();
                break;
            case 6:
                atualizaMovimentacao(matLogistica);
                menuLogistica();
                break;
            case 7:
                removeFilial(matLogistica, maxTam);
                menuLogistica();
                break;
            case 8:
                removeCusto(matLogistica);
                menuLogistica();
                break;
            case 9:
                calculaCustosFiliais(matCustoLog, maxTam);
                menuLogistica();
                break;
            case 0:
                menuPrincipal.Menu();
                break;
        }
    }
}
