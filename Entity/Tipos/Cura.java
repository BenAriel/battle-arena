package Entity.Tipos;

import Entity.Tipo;
import Entity.Personagem;
public class Cura extends Tipo {
    private int cura;

    public Cura(int cura) {
        this.cura = cura;
    }

    public int curar() {
        return this.cura;
    }
}
