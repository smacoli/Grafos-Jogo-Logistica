import java.util.Scanner;

public class Jogo {
    Scanner scanner = new Scanner(System.in);
    int maxTam = 100;
    String [][] matJogo = new String[maxTam][maxTam];
    float[][] matCustoJogo = new float[maxTam][maxTam];

    public void insereLocalJogo(String[][] matJogo, int maxTam) {
        System.out.println("::::::::::Inserir Local de Jogo::::::::::\nInsira o local: ");
        String local = scanner.nextLine();

        boolean localJaInserido = false;
        for (int i = 0; i < maxTam; i++) {
            if (matJogo[i][0] != null && matJogo[i][0].equals(local)) {
                localJaInserido = true;
                System.out.println("Erro. Local já cadastrado.");
                break;
            }
        }

        if (!localJaInserido) {
            int linhaVazia = -1;
            for (int i = 0; i < maxTam; i++) {
                if (matJogo[i][0] == null) {
                    linhaVazia = i;
                    break;
                }
            }

            if (linhaVazia != -1) {
                matJogo[linhaVazia][0] = local;
                System.out.println("Local inserido com sucesso\n");
            } else {
                System.out.println("Erro: armazenamento cheio.");
            }
        }
    }
    public void listaLocais(String[][] matJogo){
        for(int i = 0; i < maxTam; i++){
            if(matJogo[i][0] != null){
                System.out.print("\nLocal nº " + i + ": " + matJogo[i][0]);
            }
        }
        System.out.println("\n");
    }
    public void insereTempo(float[][] matCustoJogo, String[][] matJogo, int maxTam){
        int local1, local2, tempo, atualizar;

        System.out.print("::::::::::Inserir Tempo de Deslocamento::::::::::\n");
        System.out.print("Selecione os dois locais pelo seu numero:\n");

        listaLocais(matJogo);
        System.out.println("Origem: ");
        local1 = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Destino: ");
        local2 = scanner.nextInt();
        scanner.nextLine();

        if(matCustoJogo[local1][local2] != 0.0){
            System.out.println("Ja existe um tempo cadastrado para esses locais. Atualizar tempo?\n1. SIM | 2. NAO\nOpcao: ");
            atualizar = scanner.nextInt();
            scanner.nextLine();

            if(atualizar == 1){
                System.out.println("Insira o tempo entre " + matJogo[local1][0] + " e " + matJogo[local2][0] + ": ");
                tempo = scanner.nextInt();
                scanner.nextLine();

                matCustoJogo[local1][local2] = tempo;
            } else {
                System.out.println("tempo nao atualizado.");
            }
        } else {
            System.out.println("\nInsira o tempo entre " + matJogo[local1][0] + " e " + matJogo[local2][0] + ": ");
            tempo = scanner.nextInt();
            scanner.nextLine();

            matCustoJogo[local1][local2] = tempo;

            System.out.println("Custo inserido com sucesso.");
        }
    }
    public void atualizaTempo(String[][] matLogistica){
        int filialP1, filialP2, novoCusto, escolha;

        System.out.print("::::::::::Atualizar Custo de Deslocamento::::::::::\n");
        System.out.print("Selecione as duas filiais pelo seu numero:\n");

        listaLocais(matLogistica);

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

            matCustoJogo[filialP1][filialP2] = novoCusto;
        } else {
            insereLocalJogo(matLogistica, maxTam);
            atualizaTempo(matLogistica);
        }

    }
    public void calculaTemposLocais(float[][] matCustoJogo, int maxTam){
        int custoTotal = 0;

        for(int i = 0; i < maxTam; i++){
            for(int j = 0; j < maxTam; j++){
                if(matCustoJogo[i][j] != 0.0){
                    custoTotal += matCustoJogo[i][j];
                }
            }
        }
        System.out.println("Tempo de deslocamento entre todos os locais = " + custoTotal);
    }
    public void listaLocaisDestino(String[][] matJogo, float[][] matCustoJogo, int maxTam, int localP1) {
        System.out.println("Locais próximos a " + matJogo[localP1][0] + ":\n");

        for (int i = 0; i < maxTam; i++) {
            float custo = matCustoJogo[localP1][i];
            if (custo != 0.0) {
                String nomeLocal = matJogo[i][0];
                System.out.println(nomeLocal + ": " + custo + " minutos");
            }
        }
    }
    public void listaLocaisOrigem(String[][] matJogo, float[][] matCustoJogo, int maxTam, int localO1){
        System.out.println("Locais próximos a " + matJogo[localO1][0] + ":\n");

        for (int i = 0; i < maxTam; i++) {
            float custo = matCustoJogo[i][localO1];
            if (custo != 0.0) {
                String nomeLocal = matJogo[i][0];
                System.out.println(nomeLocal + ": " + custo + " minutos");
            }
        }
    }
    public void removeLocal(String[][] matJogo, int maxTam){
        int deletaLocal;

        System.out.println("::::::::::Remover local::::::::::\nProcure o numero do local a ser removido: ");
        listaLocais(matJogo);
        System.out.println("\nDeletar local nº: ");
        deletaLocal = scanner.nextInt();
        scanner.nextLine();

        matJogo[deletaLocal][0] = null;

        System.out.println("Local removido com sucesso.\nLocais no sistema: ");

        listaLocais(matJogo);
    }
    public void removeTempo(){}
    public void menuJogo(){
        int opc;

        System.out.println("\n:::::::::: Menu Jogo ::::::::::");
        System.out.print("1. Insere local\n" +
                "2. Insere tempo de movimentação\n" +
                "3. Lista destinos\n" +
                "4. Lista origens\n" +
                "5. Atualiza tempo de movimentação\n" +
                "6. Remove local\n" +
                "7. Remove tempo\n" +
                "8. Calcula custos locais\n" +
                "0. Voltar ao menu inicial\n");

        System.out.println("Opção: ");
        opc = scanner.nextInt();
        scanner.nextLine();

        switch (opc){
            case 1:
                int insereLocal = 1;
                while (insereLocal == 1) {
                    insereLocalJogo(matJogo, maxTam);
                    System.out.print("Inserir mais um local? 1. SIM | 2. NAO\nOpcao: ");
                    insereLocal = scanner.nextInt();
                    scanner.nextLine();
                }
                menuJogo();
                break;
            case 2:
                int insereTempo = 1;
                while (insereTempo == 1){
                    insereTempo(matCustoJogo, matJogo, maxTam);
                    System.out.println("Inserir mais um tempo? 1. SIM | 2. NAO\\nOpcao: \"");
                    insereTempo = scanner.nextInt();
                    scanner.nextLine();
                }
                menuJogo();
                break;
            case 3:
                int loc1;
                System.out.print("::::::::::Locais destino::::::::::\n");
                listaLocais(matJogo);
                System.out.println("Insira o nº do local: ");
                loc1 = scanner.nextInt();
                scanner.nextLine();

                listaLocaisDestino(matJogo, matCustoJogo, maxTam, loc1);
                menuJogo();
                break;
            case 4:
                int localD;
                System.out.print("::::::::::Locais origem::::::::::\n");
                listaLocais(matJogo);
                System.out.println("Insira o nº do local: ");
                localD = scanner.nextInt();
                scanner.nextLine();

                listaLocaisOrigem(matJogo, matCustoJogo, maxTam, localD);
                menuJogo();
                break;
            case 5:

            case 6:
                atualizaTempo(matJogo);
                menuJogo();
                break;
            case 7:
                removeLocal(matJogo, maxTam);
                menuJogo();
            case 9:
                calculaTemposLocais(matCustoJogo, maxTam);
        }
    }
}
