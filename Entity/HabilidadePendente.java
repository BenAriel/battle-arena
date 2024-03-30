package Entity;

import Controller.Dados;

public class HabilidadePendente {
    boolean ativa = false;
    private int idPersonagem;
    private int idHabilidade;
    private int idJogadorAlvo;
    private int idPersonagemAlvo;
    private int energiasPrincipais;
    private int energiasPretas;

    public HabilidadePendente(int idPersonagem, int idHabilidade, int idJogadorAlvo, int idPersonagemAlvo) {
        this.idPersonagem = idPersonagem;
        this.idHabilidade = idHabilidade;
        this.idJogadorAlvo = idJogadorAlvo;
        this.idPersonagemAlvo = idPersonagemAlvo;
        calcularCusto();
    }

    public void calcularCusto() {
        int idJogadorAtual = Dados.partida.getJogadorAtual();

        Jogador jog;
        if (idJogadorAtual == 0) {
            jog = Dados.jogadorA;
        }
        else {
            jog = Dados.jogadorB;
        }

        int[] energias = jog.getEnergia();
        int custo = jog.getPersonagens().get(idPersonagem).getHabilidades()[idHabilidade].getEnergia();

        ativa = true;
        if (custo > energias[idPersonagem] + energias[3]) {
            ativa = false;
        }
        else if (custo <= energias[idPersonagem]) {
            energiasPrincipais = custo;
            energias[idPersonagem] -= energiasPrincipais;
        }
        else {
            energiasPrincipais = energias[idPersonagem];
            energias[idPersonagem] = 0;
            custo -= energiasPrincipais;

            energiasPretas = custo;
            energias[3] -= energiasPretas;
        }

        jog.setEnergia(energias);
    }

    public void liberar() {
        Jogador[] jogadores = Dados.partida.getJogadores();

        int[] energias = jogadores[0].getEnergia();
        energias[idPersonagem] += energiasPrincipais;
        energias[3] += energiasPretas;
        jogadores[0].setEnergia(energias);

        Dados.partida.setJogadores(jogadores);
    }

    public boolean isAtiva() {
        return ativa;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public int getIdJogadorAlvo() {
        return idJogadorAlvo;
    }

    public int getIdHabilidade() {
        return idHabilidade;
    }

    public void setIdHabilidade(int idHabilidade) {
        this.idHabilidade = idHabilidade;
    }

    public int getIdPersonagemAlvo() {
        return idPersonagemAlvo;
    }

    public void setIdPersonagemAlvo(int idPersonagemAlvo) {
        this.idPersonagemAlvo = idPersonagemAlvo;
    }

    public int getEnergiasPrincipais() {
        return energiasPrincipais;
    }

    public void setEnergiasPrincipais(int energiasPrincipais) {
        this.energiasPrincipais = energiasPrincipais;
    }

    public int getEnergiasPretas() {
        return energiasPretas;
    }

    public void setEnergiasPretas(int energiasPretas) {
        this.energiasPretas = energiasPretas;
    }

    public String toString() {
        String str = "";

        str += "idPersonagem ("+idPersonagem+") / ";
        str += "idHabilidade ("+idHabilidade+") / ";
        str += "idJogadorAlvo ("+idJogadorAlvo+") / ";
        str += "idPersonagemAlvo ("+idPersonagemAlvo+") / ";
        str += "energiasPrincipais ("+energiasPrincipais+") / ";
        str += "energiasPretas ("+energiasPretas+")";

        return str;
    }
}
