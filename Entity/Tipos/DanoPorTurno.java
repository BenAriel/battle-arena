package Entity.Tipos;

import Entity.Tipo;
import Entity.Personagem;

public class DanoPorTurno extends Tipo {
    private int dano;
    private String tipo; // normal ou perfurante
    private int turnos;

    public DanoPorTurno(int dano, String tipo, int turnos) {
        this.dano = dano;
        this.tipo = tipo;
        this.turnos = turnos;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public boolean[] verificarAtacaveis(Personagem[] inimigos) {
        boolean[] possiveis = new boolean[inimigos.length];

        for (int i = 0; i < inimigos.length; i++) {
            if (tipo == "Normal") {
                possiveis[i] = inimigos[i].getDefended() == 0;
            }
            else {
                possiveis[i] = true;
            }
        }

        return possiveis;
    }

}
