package model;

public class Jogo {
    private static Jogo instancia;
    private final Baralho baralho;
    private final Jogador jogador;
    private final Dealer dealer;
    private int aposta;
    private boolean rodadaAtiva;
    private String ultimoResultado;

    private Jogo() {
        baralho = new Baralho();
        jogador = new Jogador();
        dealer = new Dealer();
        rodadaAtiva = false;
    }

    public static Jogo getInstancia() {
        if (instancia == null) {
            instancia = new Jogo();
        }
        return instancia;
    }

    public void iniciarNovaRodada(int apostaInicial) {
        if (rodadaAtiva) {
            throw new IllegalStateException("Rodada já está ativa!");
        }
        this.aposta = apostaInicial;
        rodadaAtiva = true;

        baralho.embaralhar();
        jogador.limparMao();
        dealer.limparMao();

        jogador.receberCarta(baralho.comprar());
        jogador.receberCarta(baralho.comprar());
        dealer.receberCarta(baralho.comprar());
        dealer.receberCarta(baralho.comprar());
    }

    public void jogadorPedirCarta() {
        if (!rodadaAtiva) {
            throw new IllegalStateException("Nenhuma rodada ativa!");
        }
        jogador.receberCarta(baralho.comprar());
        if (jogador.estourou()) {
            finalizarRodada("Jogador estourou! Dealer vence!");
        }
    }

    public void jogadorManter() {
        if (!rodadaAtiva) {
            throw new IllegalStateException("Nenhuma rodada ativa!");
        }
        while (dealer.devePedirCarta()) {
            dealer.receberCarta(baralho.comprar());
        }
        determinarVencedor();
    }

    public void jogadorDobrar() {
        if (!rodadaAtiva) {
            throw new IllegalStateException("Nenhuma rodada ativa!");
        }
        aposta *= 2;
        jogador.receberCarta(baralho.comprar());
        if (jogador.estourou()) {
            finalizarRodada("Jogador estourou! Dealer vence!");
        } else {
            jogadorManter();
        }
    }

    public void jogadorRender() {
        if (!rodadaAtiva) {
            throw new IllegalStateException("Nenhuma rodada ativa!");
        }
        finalizarRodada("Jogador se rendeu. Metade da aposta foi devolvida.");
    }

    private void determinarVencedor() {
        if (dealer.estourou() || jogador.getValorMao() > dealer.getValorMao()) {
            finalizarRodada("Jogador venceu!");
        } else if (jogador.getValorMao() < dealer.getValorMao()) {
            finalizarRodada("Dealer venceu!");
        } else {
            finalizarRodada("Empate!");
        }
    }

    private void finalizarRodada(String resultado) {
        rodadaAtiva = false;
        ultimoResultado = resultado;
    }

    public boolean isRodadaAtiva() {
        return rodadaAtiva;
    }

    public String getUltimoResultado() {
        return ultimoResultado;
    }

    public int getValorMaoJogador() {
        return jogador.getValorMao();
    }

    public int getValorMaoDealer() {
        return dealer.getValorMao();
    }
}
