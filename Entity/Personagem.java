package Entity;

public class Personagem {
    private String nome;
    private String descricao;
    private int vida;
    private Habilidade[] habilidades = new Habilidade[4];
    private Habilidade[] habilidadesInimigoAtivas; // aqui pode ser um arrayList
    private int stunned;
    private int defended;

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

    public Habilidade[] getHabilidadesInimigoAtivas() {
        return habilidadesInimigoAtivas;
    }

    public void setHabilidadesInimigoAtivas(Habilidade[] habilidadesInimigoAtivas) {
        this.habilidadesInimigoAtivas = habilidadesInimigoAtivas;
    }

    public int getStunned() {
        return stunned;
    }

    public void setStunned(int stunned) {
        this.stunned = stunned;
    }
    
    public int getDefended() {
        return defended;
    }

    public void setDefended(int defended) {
        this.defended = defended;
    }
}
