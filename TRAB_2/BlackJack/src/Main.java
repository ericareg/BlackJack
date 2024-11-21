import controller.ControladorJogo;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControladorJogo controlador = new ControladorJogo();

        System.out.println("Bem-vindo ao Blackjack!");
        System.out.println("Você começa com $2400 em fichas.");
        System.out.println("Aposta mínima: $50.");

        boolean jogarNovamente = true;

        while (jogarNovamente) {
            // Solicitar aposta inicial
            System.out.print("Digite o valor da sua aposta: ");
            System.out.print("\n");

            int aposta = scanner.nextInt();
            controlador.iniciarNovaRodada(aposta);

            boolean rodadaEmAndamento = true;

            // Exibir estados iniciais
            controlador.exibirEstadoJogador();
            controlador.exibirEstadoDealer();

            // Ciclo da rodada
            while (rodadaEmAndamento) {
                System.out.println("\nEscolha sua ação:");
                System.out.println("1 - Pedir Carta (Hit)");
                System.out.println("2 - Manter (Stand)");
                System.out.println("3 - Dobrar (Double)");
                System.out.println("4 - Render-se (Surrender)");

                int escolha = scanner.nextInt();

                switch (escolha) {
                    case 1 -> {
                        controlador.jogadorPedirCarta();
                        if (!controlador.isRodadaAtiva()) {
                            rodadaEmAndamento = false; // Encerra o loop
                        }
                    }
                    case 2 -> {
                        controlador.jogadorManter();
                        rodadaEmAndamento = false;
                    }
                    case 3 -> controlador.jogadorDobrar();
                    case 4 -> {
                        controlador.jogadorRender();
                        rodadaEmAndamento = false;
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            }

            // Perguntar se o jogador quer jogar novamente
            System.out.print("\nDeseja jogar outra rodada? (S/N): ");
            char resposta = scanner.next().toUpperCase().charAt(0);
            jogarNovamente = resposta == 'S';

            if (!jogarNovamente) {
                System.out.println("Obrigado por jogar! Até a próxima.");
            }
        }

        scanner.close();
    }
}
