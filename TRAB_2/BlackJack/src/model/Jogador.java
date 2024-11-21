package model;

import java.util.ArrayList;
import java.util.List;

class Jogador {
    private final List<Carta> mao = new ArrayList<>();

    public void receberCarta(Carta carta) {
        mao.add(carta);
    }

    public int getValorMao() {
        int total = 0;
        int ases = 0;

        for (Carta carta : mao) {
            total += carta.getValor();
            if (carta.getValor() == 1) {
                ases++;
            }
        }

        while (ases > 0 && total + 10 <= 21) {
            total += 10;
            ases--;
        }

        return total;
    }

    public boolean estourou() {
        return getValorMao() > 21;
    }

    public void limparMao() {
        mao.clear();
    }
}

class Dealer extends Jogador {
    public boolean devePedirCarta() {
        return getValorMao() < 17;
    }
}
