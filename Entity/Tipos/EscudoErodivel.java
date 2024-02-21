package Entity.Tipos;

import Entity.Tipo;

public class EscudoErodivel extends Tipo {
    private int defesa;

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int receberDano(int dano) {
        if (defesa >= dano) {
            defesa -= dano;
            return 0;
        }
        else {
            int danoSofrido = dano-defesa;
            defesa = 0;
            return danoSofrido;
        }
    }
    
}
