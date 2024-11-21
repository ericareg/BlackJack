package controller;

import model.Jogo;

public class ControladorJogo {
    private final Jogo jogo;

    public ControladorJogo() {
        // Obtém a instância do Model (Singleton)
        this.jogo = Jogo.getInstancia();
    }

    /**
     * Inicia uma nova rodada com a aposta definida.
     * 
     * @param aposta valor da aposta inicial.
     */
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

    /**
     * Solicita uma nova carta para o jogador.
     */
    public void jogadorPedirCarta() {
        try {
            jogo.jogadorPedirCarta();
            exibirEstadoJogador();
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Finaliza a rodada com o jogador escolhendo manter sua mão.
     */
    public void jogadorManter() {
        try {
            jogo.jogadorManter();
            exibirEstadoDealer();
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * O jogador decide dobrar a aposta.
     */
    public void jogadorDobrar() {
        try {
            jogo.jogadorDobrar();
            exibirEstadoJogador();
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * O jogador opta por render-se.
     */
    public void jogadorRender() {
        try {
            jogo.jogadorRender();
            System.out.println("Jogador se rendeu. Metade da aposta foi devolvida.");
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Exibe o estado atual do jogador.
     */
    private void exibirEstadoJogador() {
        System.out.println("Valor atual da mão do jogador: " + jogo.getValorMaoJogador());
    }

    /**
     * Exibe o estado atual do dealer.
     */
    private void exibirEstadoDealer() {
        System.out.println("Valor da mão do dealer: " + jogo.getValorMaoDealer());
    }
}
