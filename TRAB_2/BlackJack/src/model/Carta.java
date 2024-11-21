package model;

class Carta {
    public enum Naipe {
        COPAS, OUROS, ESPADAS, PAUS
    }

    private final Naipe naipe;
    private final int valor;

    public Carta(Naipe naipe, int valor) {
        this.naipe = naipe;
        this.valor = valor;
    }

    public int getValor() {
        return Math.min(valor, 10); // J, Q, K valem 10
    }

    public Naipe getNaipe() {
        return naipe;
    }

    @Override
    public String toString() {
        return valor + " de " + naipe;
    }
}
