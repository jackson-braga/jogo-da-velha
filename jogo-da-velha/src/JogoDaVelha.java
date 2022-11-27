public class JogoDaVelha {
    // Constants
    private static final int TAMANHO_LINHA = 3;
    private static final int TAMANHO_COLUNA = 3;
    private static final int ULTIMA_LINHA = TAMANHO_LINHA - 1;
    private static final int ULTIMA_COLUNA = TAMANHO_COLUNA - 1;
    public static final String LINHA_INVALIDA = "A linha selecionada é inválida";
    public static final String COLUNA_INVALIDA = "A coluna selecionada é inválida";

    private String[][] tabuleiro = new String[TAMANHO_LINHA][TAMANHO_COLUNA];

    private EJogador jogador;

    private boolean finalizado = false;
    private boolean empatado = false;

    public JogoDaVelha() {
        jogador = EJogador.JOGADOR_1;
    }

    public void jogada(int linha, int coluna) throws CampoInvalidoException, CampoPreenchidoException, PartidaFinalizadaException {
        validarJogada(linha, coluna);
        preencherCampo(linha, coluna);
        boolean isGanhador = verificarGanhador();
        boolean isPreechido = verificarTabuleiroPreenchido();

        atualizarJogoFinalizado(isGanhador, isPreechido);
        atualizarJogoEmpatado(isGanhador, isPreechido);

        if (!isFinalizado()) {
            proximoJogador();
        }
    }

    private void validarJogada(int linha, int coluna) throws CampoInvalidoException, CampoPreenchidoException, PartidaFinalizadaException {
        validarPartidaFinalizada();
        validarLinha(linha);
        validarColuna(coluna);
        validarCampoPreenchido(linha, coluna);
    }

    private void validarPartidaFinalizada() throws PartidaFinalizadaException {
        if (finalizado) {
            throw new PartidaFinalizadaException();
        }
    }

    private void validarLinha(int linha) throws CampoInvalidoException {
        if (linha < 0 || linha > ULTIMA_LINHA) {
            throw new CampoInvalidoException(LINHA_INVALIDA);
        }
    }

    private void validarColuna(int coluna) throws CampoInvalidoException {
        if (coluna < 0 || coluna > ULTIMA_COLUNA) {
            throw new CampoInvalidoException(COLUNA_INVALIDA);
        }
    }

    private void validarCampoPreenchido(int linha, int coluna) throws CampoPreenchidoException {
        if (tabuleiro[linha][coluna] != null) {
            throw new CampoPreenchidoException();
        }
    }

    private void preencherCampo(int linha, int coluna) {
        tabuleiro[linha][coluna] = jogador.getMarcador();
    }

    private boolean verificarGanhador() {
        return verificarLinhas() || verificarColunas() || verificarDiagonais();
    }

    private boolean verificarLinhas() {
        return verificarLinhasTabuleiro(tabuleiro);
    }

    private boolean verificarColunas() {
        String[][] tabuleiroTemp = new String[TAMANHO_COLUNA][TAMANHO_LINHA];

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiroTemp[j][i] = tabuleiro[i][j];
            }
        }

        return verificarLinhasTabuleiro(tabuleiroTemp);
    }

    private boolean verificarLinhasTabuleiro(String[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            String[] linha = tabuleiro[i];
            if (verificarLinha(linha)) {
                return true;
            }
        }
        return false;
    }

    private static boolean verificarLinha(String[] linha) {
        for (int i = 0; i < linha.length; i++) {
            String coluna = linha[i];
            if (coluna == null) {
                return false;
            }
            if (i == 0) {
                continue;
            }
            String colunaAnterior = linha[i - 1];
            if (!coluna.equals(colunaAnterior)) {
                return false;
            }
        }
        return true;
    }

    private boolean verificarDiagonais() {
        return verificarDiagonalPrincipal() || verificarDiagonalSecundaria();
    }

    private boolean verificarDiagonalPrincipal() {
        for (int i = 0, j = 0; i < tabuleiro.length && j < tabuleiro[i].length; i++, j++) {
            if (j < tabuleiro[i].length) {
                if (tabuleiro[i][j] == null) {
                    return false;
                }
                if (i == 0 && j == 0) {
                    continue;
                }
                if (!tabuleiro[i][j].equals(tabuleiro[i - 1][j - 1])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean verificarDiagonalSecundaria() {
        for (int i = 0, j = tabuleiro[i].length - 1; i < tabuleiro.length && j >= 0; i++, j--) {
            if (tabuleiro[i][j] == null) {
                return false;
            }
            if (i == 0 && j == tabuleiro[i].length - 1) {
                continue;
            }
            if (!tabuleiro[i][j].equals(tabuleiro[i - 1][j + 1])) {
                return false;
            }
        }
        return true;
    }

    private boolean verificarTabuleiroPreenchido() {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private void atualizarJogoEmpatado(boolean isGanhador, boolean isPreechido) {
        empatado = !isGanhador && isPreechido;
    }

    private void atualizarJogoFinalizado(boolean isGanhador, boolean isPreechido) {
        finalizado = isGanhador || isPreechido;
    }

    private void proximoJogador() {
        if (jogador == EJogador.JOGADOR_1) {
            jogador = EJogador.JOGADOR_2;
        } else {
            jogador = EJogador.JOGADOR_1;
        }
    }

    public String getTabuleiroImpresso() {
        String tabuleiroImpresso = "";
        for (int idxLinha = 0; idxLinha < tabuleiro.length; idxLinha++) {
            String linha = getLinha(tabuleiro[idxLinha]);
            tabuleiroImpresso += linha + "\n";
            if (idxLinha < tabuleiro.length - 1) {
                tabuleiroImpresso += "---+---+---\n";
            }
        }
        return tabuleiroImpresso;
    }

    private String getLinha(String[] linhaTabuleiro) {
        String linha = "";
        for (int idxCol = 0; idxCol < linhaTabuleiro.length; idxCol++) {
            String valor = linhaTabuleiro[idxCol] != null ? linhaTabuleiro[idxCol] : " ";

            linha += " " + valor + " ";

            if (idxCol < linhaTabuleiro.length - 1) {
                linha += "|";
            }
        }
        return linha;
    }

    public EJogador getJogador() {
        return jogador;
    }

    public boolean isEmpatado() {
        return empatado;
    }

    public boolean isFinalizado() {
        return finalizado;
    }
}