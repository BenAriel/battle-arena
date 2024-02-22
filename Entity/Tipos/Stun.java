package Entity.Tipos;

import Entity.Tipo;

public class Stun extends Tipo {
    private int turnos;

    public Stun(int turnos) {
        this.turnos = turnos;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }
}
