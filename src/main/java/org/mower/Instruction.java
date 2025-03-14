package org.mower;

public enum Instruction {

    Droite('D'),
    Gauche('G'),
    Avance('A');

    public final char value;

    Instruction(char value) {
        this.value = value;
    }

    public static Instruction convertTo(char value) {
        switch (value) {
            case 'D' -> {
                return Droite;
            }
            case 'G' -> {
                return Gauche;
            }
            case 'A' -> {
                return Avance;
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }
}
