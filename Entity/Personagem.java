package Entity;

import java.util.ArrayList;

public abstract class Personagem {
    private String nome;
    private String descricao;
    private int vida;
    private Habilidade[] habilidades = new Habilidade[4];
    private ArrayList<Habilidade> habilidadesAtivas = new ArrayList<>();
    private ArrayList<Habilidade> habilidadesAtivasInimigo = new ArrayList<>();
    private int stunned;
    private int invulneravel;
    private int defesa;

    public Personagem(String nome, String descricao, int vida, Habilidade[] habilidades) {
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

    public ArrayList<Habilidade> getHabilidadesAtivas() {
        return habilidadesAtivas;
    }

    public void setHabilidadesAtivas(ArrayList<Habilidade> habilidadesAtivas) {
        this.habilidadesAtivas = habilidadesAtivas;
    }

    public ArrayList<Habilidade> getHabilidadesAtivasInimigo() {
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
            dano -= defesa;
            vida -= dano;
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

    public Jogador[] utilizarHabilidade(Jogador jogador, Jogador alvo, int idHabilidade, int idPersonagem, int idPersonagemAlvo) {
        Jogador[] jogadores = {jogador, alvo};
        return jogadores;
    }

    public boolean[] exibirHabilidadesDisponiveis(int energiasDisponiveis) {
        int[] custoHabilidades = {habilidades[0].getEnergia(), habilidades[1].getEnergia(), habilidades[2].getEnergia(), habilidades[3].getEnergia()};
        boolean[] habilidadesDisponiveis = new boolean[4];

        for (int i = 0; i < custoHabilidades.length; i++) {
            if (energiasDisponiveis >= custoHabilidades[i] && habilidades[i].getCountdownAtual() == 0) {
                habilidadesDisponiveis[i] = true;
                System.out.print(i+". "+habilidades[i].getNome()+" ("+custoHabilidades[i]+" energia");

                if (custoHabilidades[i] > 1) {
                    System.out.print('s');
                }

                System.out.println(") / "+habilidades[i].getDescricao());
            }
        }

        boolean exibiuCoutdown = false;
        for (int i = 0; i < custoHabilidades.length; i++) {
            if (habilidades[i].getCountdownAtual() > 0) {
                if (!exibiuCoutdown) {
                    exibiuCoutdown = true;
                    System.out.println("Countdown:");
                }

                System.out.println(habilidades[i].getNome()+" ("+habilidades[i].getCountdownAtual()+" Turnos)");
            }
        }
        
        return habilidadesDisponiveis;
    }

    public void meuTurno() {
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
