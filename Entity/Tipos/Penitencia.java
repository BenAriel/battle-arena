package Entity.Tipos;

import Entity.Tipo;

public class Penitencia extends Tipo {
    private int danoSofrido;

    public Penitencia(int danoSofrido) {
        this.danoSofrido = danoSofrido;
    }

    public int getDanoSofrido() {
        return danoSofrido;
    }

    public void setDanoSofrido(int danoSofrido) {
        this.danoSofrido = danoSofrido;
    }
}
