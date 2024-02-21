package Entity.Tipos;

import Entity.Tipo;

public class EscudoTemporario extends Tipo {
    private int defesa;
    private int countdown;

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public int receberDano(int dano) {
        if (dano <= defesa) {
            return 0;
        }
        else {
            return dano-defesa;
        }
    }

    public void passarTurno() {
        if (countdown > 0) {
            countdown--;
        }
    }
}