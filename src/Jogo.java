import java.util.Scanner;

public class Jogo {
    Scanner scanner = new Scanner(System.in);
    MenuPrincipal menuPrincipal = new MenuPrincipal();
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

        boolean localNaoInserido = false;
        for(int i = 0; i < maxTam; i++){
            if (matJogo[local1][0] == null || matJogo[local2][0] == null){
                localNaoInserido = true;
            }
        }

        if(localNaoInserido == true){
            System.out.println("Erro. Local nao inserido.");
            menuJogo();
        }

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
    public void atualizaTempo(String[][] matJogo){
        int localP1, localP2, novoTempo, escolha;

        System.out.print("::::::::::Atualizar tempo de Deslocamento::::::::::\n");
        System.out.print("Selecione os dois locais pelo seu numero:\n");

        listaLocais(matJogo);

        System.out.println("Nao encontrou seu local?\n1. Encontrei meu local\n2. Inserir local");
        escolha = scanner.nextInt();
        scanner.nextLine();

        if(escolha == 1){
            System.out.println("\n\nPrimeiro local: ");
            localP1 = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Segundo local: ");
            localP2 = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Insira o novo tempo entre " + matJogo[localP1][0] + " e " + matJogo[localP2][0] + ": ");
            novoTempo = scanner.nextInt();
            scanner.nextLine();

            matCustoJogo[localP1][localP2] = novoTempo;
        } else {
            insereLocalJogo(matJogo, maxTam);
            atualizaTempo(matJogo);
        }

    }
    public void removeTempo(String[][] matJogo){
        int l1, l2;

        System.out.println("::::::::::Remover tempo de deslocamento::::::::::\nEscolha quais os locais que deseja remover:");
        listaLocais(matJogo);
        System.out.println("Primeiro local:");
        l1 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Segundo local:");
        l2 = scanner.nextInt();
        scanner.nextLine();

        matCustoJogo[l1][l2] = 0;

        System.out.println("Custo de " + matJogo[l1][0] + " e " + matJogo[l2][0] + " removido com sucesso.");

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

        boolean localNaoInserido = false;
        for(int i = 0; i < maxTam; i++){
            if (matJogo[localP1][0] == null){
                localNaoInserido = true;
            }
        }

        if(localNaoInserido == true){
            System.out.println("Erro. Local nao inserido.");
            menuJogo();
        }

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

        boolean localNaoInserido = false;
        for(int i = 0; i < maxTam; i++){
            if (matJogo[localO1][0] == null){
                localNaoInserido = true;
            }
        }

        if(localNaoInserido == true){
            System.out.println("Erro. Local nao inserido.");
            menuJogo();
        }

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
        String deletaLocal;

        System.out.println("::::::::::Remover local::::::::::\nProcure o nome do local a ser removido: ");
        listaLocais(matJogo);
        System.out.println("\nInsira o nome do local a ser excluidoº: ");
        deletaLocal = scanner.nextLine();

        for(int i = 0; i < maxTam; i++) {
            if (deletaLocal.equals(matJogo[i][0])) {
                matJogo[i][0] = null;
            }
        }

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
                    System.out.println("Inserir mais um tempo? 1. SIM | 2. NAO\nOpcao: \"");
                    insereTempo = scanner.nextInt();
                    scanner.nextLine();
                }
                menuJogo();
                break;
            case 3:
                int loc1;
                System.out.print("::::::::::Locais destino::::::::::\n");
                listaLocais(matJogo);
                System.out.println("Insira o nº do local de origem: ");
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
                atualizaTempo(matJogo);
                menuJogo();
                break;
            case 6:
                removeLocal(matJogo, maxTam);
                menuJogo();
                break;
            case 7:
                removeTempo(matJogo);
                menuJogo();
                break;
            case 8:
                calculaTemposLocais(matCustoJogo, maxTam);
                menuJogo();
                break;
            case 0:
                menuPrincipal.Menu();
                break;
        }
    }
}
