import java.util.Scanner;

public class JogoDaVelha {

    static char velha[][] = new char[3][3];
    static int auxiliar[][] = new int[3][3];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite a quanditade de jogos desejados");

        int jogos = scan.nextInt();
        while(jogos<1){
            System.out.println("O número de jogos deve ser maior que 0");
            jogos = scan.nextInt();
        }
        int vitoria1 = 0;
        int vitoria2 = 0;
        int empate = 0;
        int partida = 0;
        int validacao;//Exeption entra aqui para verificar a jogada se foi valida ou se ja foi escolido o campo
        int jogador=0;
        int linha=0;
        int coluna=0;
        int termino = 0;// Controla o fim do jogo
        do{
            zera();
            do{
                desenha();
                //jogada do jogador 1
                System.out.println("Digite a sua jogada jogador 1");
                jogador=1;
                do{
                    System.out.println("Digite a linha");
                    linha = scan.nextInt();
                    linha--;
                    System.out.println("Digite a coluna");
                    coluna = scan.nextInt();
                    coluna--;
                    validacao = jogada(jogador,linha,coluna);
                    if(validacao==0) {
                        System.out.println("Campo já preeenchido\nJogue novamente");//Verificar o erro com base na aula de quarta
                    }
                    if(validacao==1) {
                        System.out.println("Campo inexistente\nJogue novamente");//Verificar o erro com base na aula de quarta
                    }}while(validacao!=2);

                termino=resultado(partida);
                if (termino!=0) {
                    System.out.println("\n********************************");
                    desenha();
                    System.out.println("\n********************************\n");
                    if (termino == 1) {
                        vitoria1++;
                        System.out.println("Jogador 1 venceu");
                        break;
                    }
                    if (termino == 2) {
                        vitoria2++;
                        System.out.println("Jogador 2 venceu");
                        break;
                    }
                    if (termino == 3) {
                        empate++;
                        System.out.println("Empate");
                        break;
                    }
                }
                //jogada do jogador 2
                desenha();
                System.out.println("Digite a sua jogada jogador 2");
                jogador=2;
                do{
                    System.out.println("Digite a linha");
                    linha = scan.nextInt();
                    linha--;
                    System.out.println("Digite a coluna");
                    coluna = scan.nextInt();
                    coluna--;
                    validacao = jogada(jogador,linha,coluna);
                    if(validacao==0) {
                        System.out.println("Campo já preeenchido\nJogue novamente");//Verificar o erro com base na aula de quarta
                    }
                    if(validacao==1) {
                        System.out.println("Campo inexistente\nJogue novamente");//Verificar o erro com base na aula de quarta
                    }}while(validacao!=2);
                termino=resultado(partida);
                if (termino!=0) {
                    System.out.println("\n********************************");
                    desenha();
                    System.out.println("\n********************************\n");
                    if (termino == 1) {
                        vitoria1++;
                        System.out.println("Jogador 1 venceu");
                    }
                    if (termino == 2) {
                        vitoria2++;
                        System.out.println("Jogador 2 venceu");
                    }
                    if (termino == 3) {
                        empate++;
                        System.out.println("Empate");
                    }
                }
            }while(termino == 0);
            partida++;
            if(jogos == partida && vitoria1 == vitoria2) {
                System.out.println("O jogo está empatado agora será a partida de desempate");
                jogos++;
            }
        }while(partida < jogos);
        System.out.println("Foram disputados "+jogos+" jogos");
        System.out.println("O jogador 1 ganhou "+vitoria1+" jogos");
        System.out.println("O jogador 2 ganhou "+vitoria2+" jogos");
        if(empate>0)
            System.out.println("Foram empatados "+empate+" jogos");
    }



    private static void zera() {
        for(int linha = 0; linha < auxiliar.length; linha++){
            for(int coluna = 0; coluna < velha[linha].length; coluna++){
                velha[linha][coluna] = ' ';
                auxiliar[linha][coluna] = 0;
            }
        }

    }

    private static void desenha() {
        for(int linha = 0; linha < velha.length; linha++) {
            if(linha==0)
                System.out.println("\n   1   2   3 ");
            for (int coluna = 0; coluna < velha[linha].length; coluna++) {
                if(coluna==0)
                    System.out.printf((linha+1)+" ");
                if(coluna==1)
                    System.out.printf(" | " + velha[linha][coluna] + " |");
                else
                    System.out.printf(" " + velha[linha][coluna]);

            }
            if(linha < (velha.length-1))
                System.out.println("\n  ----------- ");
        }
        System.out.println();
    }

    private static int jogada(int jogador, int linha, int coluna) {
        try {
            if (auxiliar[linha][coluna] != 0)
                return 0;
            if (jogador == 1) {
                velha[linha][coluna] = 'X';
                auxiliar[linha][coluna] = 1;
            } else {
                velha[linha][coluna] = 'O';
                auxiliar[linha][coluna] = 2;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return 1;
        }
        return 2;
    }

    private static int resultado(int partida) {
        //Verificação linha
        if (auxiliar[0][0] == auxiliar[0][1] && auxiliar[0][1] == auxiliar[0][2]) {
            if (auxiliar[0][0] == 1)
                return 1;
            if (auxiliar[0][0] == 2)
                return 2;
        }
        if (auxiliar[1][0] == auxiliar[1][1] && auxiliar[1][1] == auxiliar[1][2]) {
            if (auxiliar[1][0] == 1)
                return 1;
            if (auxiliar[1][0] == 2)
                return 2;
        }
        if (auxiliar[2][0] == auxiliar[2][1] && auxiliar[2][1] == auxiliar[2][2]) {
            if (auxiliar[2][0] == 1)
                return 1;
            if (auxiliar[2][0] == 2)
                return 2;
        }
        //Verificação coluna
        if (auxiliar[0][0] == auxiliar[1][0] && auxiliar[1][0] == auxiliar[2][0]) {
            if (auxiliar[0][0] == 1)
                return 1;
            if (auxiliar[0][0] == 2)
                return 2;
        }
        if (auxiliar[0][1] == auxiliar[1][1] && auxiliar[1][1] == auxiliar[2][1]) {
            if (auxiliar[0][1] == 1)
                return 1;
            if (auxiliar[0][1] == 2)
                return 2;
        }
        if (auxiliar[0][2] == auxiliar[1][2] && auxiliar[1][2] == auxiliar[2][2]) {
            if (auxiliar[0][2] == 1)
                return 1;
            if (auxiliar[0][2] == 2)
                return 2;
        }
        //Verificação diagonal
        if (auxiliar[0][0] == auxiliar[1][1] && auxiliar[1][1] == auxiliar[2][2]) {
            if (auxiliar[1][1] == 1)
                return 1;
            if (auxiliar[1][1] == 2)
                return 2;
        }
        if (auxiliar[2][0] == auxiliar[1][1] && auxiliar[1][1] == auxiliar[0][2]) {
            if (auxiliar[1][1] == 1)
                return 1;
            if (auxiliar[1][1] == 2)
                return 2;
        }
        //Condição de velha
        if(auxiliar[0][0]!=0&&auxiliar[0][1]!=0&&auxiliar[0][2]!=0&&auxiliar[1][0]!=0&&auxiliar[1][1]!=0&&auxiliar[1][2]!=0&&auxiliar[2][0]!=0&&auxiliar[2][1]!=0&&auxiliar[2][2]!=0)
            return 3;

        return 0;
    }
}