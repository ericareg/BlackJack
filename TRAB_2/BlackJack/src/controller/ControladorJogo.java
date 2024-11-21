package controller;

import model.Jogo;

public class ControladorJogo {
    private final Jogo jogo;

    public ControladorJogo() {
        this.jogo = Jogo.getInstancia();
    }

    public void iniciarNovaRodada(int aposta) {
        if (aposta < 50) {
            System.out.println("Aposta mínima é de $50.");
            return;
        }

        try {
            jogo.iniciarNovaRodada(aposta);
            System.out.println("Nova rodada iniciada. Boa sorte!");
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void jogadorPedirCarta() {
        try {
            jogo.jogadorPedirCarta();
            exibirEstadoJogador();

            if (!jogo.isRodadaAtiva()) {
                System.out.println(jogo.getUltimoResultado());
            }
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void jogadorManter() {
        try {
            jogo.jogadorManter();
            System.out.println(jogo.getUltimoResultado());
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void jogadorDobrar() {
        try {
            jogo.jogadorDobrar();
            exibirEstadoJogador();
            if (!jogo.isRodadaAtiva()) {
                System.out.println(jogo.getUltimoResultado());
            }
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void jogadorRender() {
        try {
            jogo.jogadorRender();
            System.out.println(jogo.getUltimoResultado());
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void exibirEstadoJogador() {
        System.out.println("Valor atual da mão do jogador: " + jogo.getValorMaoJogador());
    }

    public void exibirEstadoDealer() {
        System.out.println("Valor da mão do dealer: " + jogo.getValorMaoDealer());
    }

    public boolean isRodadaAtiva() {
        return jogo.isRodadaAtiva();
    }
}
