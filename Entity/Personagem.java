package Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Personagem {
    private int turno;
    private String nome;
    private String descricao;
    private int vida;
    private Habilidade[] habilidades = new Habilidade[4];
    private List<Habilidade> habilidadesAtivas = new ArrayList<>();
    private List<Habilidade> habilidadesAtivasInimigo = new ArrayList<>();
    private int stunned;
    private int invulneravel;
    private int defesa;
    boolean[][] habilidadesVerificadas;

    public Personagem(String nome, String descricao, int vida, Habilidade[] habilidades) {
        turno = 0;
        this.nome = nome;
        this.descricao = descricao;
        this.vida = vida;
        this.habilidades = habilidades;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Habilidade[] getHabilidades() {
        return habilidades;
    }
    
    public void setHabilidades(Habilidade[] habilidades) {
        this.habilidades = habilidades;
    }

    public List<Habilidade> getHabilidadesAtivas() {
        return habilidadesAtivas;
    }

    public void setHabilidadesAtivas(ArrayList<Habilidade> habilidadesAtivas) {
        this.habilidadesAtivas = habilidadesAtivas;
    }

    public List<Habilidade> getHabilidadesAtivasInimigo() {
        return habilidadesAtivasInimigo;
    }

    public void setHabilidadesAtivasInimigo(ArrayList<Habilidade> habilidadesAtivasInimigo) {
        this.habilidadesAtivasInimigo = habilidadesAtivasInimigo;
    }

    public int getStunned() {
        return stunned;
    }

    public void setStunned(int stunned) {
        this.stunned = stunned;
    }

    public int getInvulneravel() {
        return invulneravel;
    }

    public void setInvulneravel(int invulneravel) {
        this.invulneravel = invulneravel;
    }
    
    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getTurno() {
        return turno;
    };

    public void setTurno(int turno) {this.turno = turno;};

    public void setHabilidadesVerificadas(boolean[][] habilidadesVerificadas) {
        this.habilidadesVerificadas = habilidadesVerificadas;
    }

    public boolean[][] getHabilidadesVerificadas() {
        return habilidadesVerificadas;
    }
    
    public boolean[][] bloquearAliados(boolean[][] personagens) {
        for (int i = 0; i < personagens[0].length; i++) {
            personagens[0][i] = false;
        }

        return personagens;
    }

    public boolean[][] inverterAliados(boolean[][] personagens) {
        for (int i = 0; i < personagens[0].length; i++) {
            personagens[0][i] = !personagens[0][i];
        }
        return personagens;
    }
    
    public boolean[][] bloquearInimigos(boolean[][] personagens) {
        for (int i = 0; i < personagens[1].length; i++) {
            personagens[1][i] = false;
        }

        return personagens;
    }
    
    public boolean[][] bloquearInvulneraveis(boolean[][] personagens, boolean[] invulneraveis) {
        for (int i = 0; i < personagens[1].length; i++) {
            if (invulneraveis[i]) {
                personagens[1][i] = false;
            }
        }

        return personagens;
    }

    public boolean[][] permitirUsuario(boolean[][] vivos, int idPersonagem) {
        vivos[0][idPersonagem] = true;
        return vivos;
    }

    public void ficarInvulneravel(int turnos) {
        if (this.invulneravel < turnos) {
            invulneravel = turnos;
        }
    }

    public void stunnar(int turnos) {
        if (stunned < turnos) {
            stunned = turnos;
        }
    }

    public void dano(int dano) {
        if (defesa > dano) {
            defesa -= dano;
        }
        else if (defesa > 0) {
            int danoInicial = dano;

            dano -= defesa;
            vida -= dano;

            defesa -= danoInicial;
            if (defesa < 0) {
                defesa = 0;
            }
        }
        else {
            vida -= dano;
        }

        if (vida < 0) {
            vida = 0;
        }
    }

    public void danoDireto(int dano) {
        vida -= dano;

        if (vida < 0) {
            vida = 0;
        }
    }

    public void defender(int defesa) {
        this.defesa += defesa;
    }

    public void curar(int cura) {
        vida += cura;

        if (vida > 100) {
            vida = 100;
        }
    }

    public void ressuscitar(int vida) {
        this.vida = vida;
    }

    public boolean[][] verificarHabilidade(boolean[][] vivos, boolean[] invulneraveis, int idPersonagem, int idHabilidade) {
        return vivos;
    }

    public void utilizarHabilidade(HabilidadePendente habilidade) {
    }

    public void meuTurno() {
        turno++;

        if (invulneravel > 0) {
            invulneravel--;
        }
    }

    public void passarTurno() {
        for (int i = 0; i < habilidades.length; i++) {
            habilidades[i].passarTurno();
        }

        if (stunned > 0) {
            stunned--;
        }
    }

    public String toString() {
        String str = "";

        str += nome+", ";
        str += descricao+". ";
        str += "Vida ("+vida+")";

        if (invulneravel > 0) {
            str += ", Invulnerável ("+invulneravel+")";
        }

        if (stunned > 0) {
            str += ", Stunnado ("+stunned+")";
        }

        return str;
    }
}
