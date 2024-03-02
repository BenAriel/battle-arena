package Entity;

public class HabilidadePendente {
    private int idPersonagem;
    private int idHabilidade;
    private int idPersonagemAlvo;
    private int energiasPrincipais;
    private int energiasPretas;

    public HabilidadePendente(int idPersonagem, int idHabilidade, int idPersonagemAlvo, int energiasPrincipais, int energiasPretas) {
        this.idPersonagem = idPersonagem;
        this.idHabilidade = idHabilidade;
        this.idPersonagemAlvo = idPersonagemAlvo;
        this.energiasPrincipais = energiasPrincipais;
        this.energiasPretas = energiasPretas;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
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
        str += "idPersonagemAlvo ("+idPersonagemAlvo+") / ";
        str += "energiasPrincipais ("+energiasPrincipais+") / ";
        str += "energiasPretas ("+energiasPretas+")";

        return str;
    }
}
