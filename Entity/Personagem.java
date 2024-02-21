package Entity;

public class Personagem {
    private String nome;
    private String descricao;
    private Habilidade[] habilidades = new Habilidade[4];

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

    public Habilidade[] getHabilidades() {
        return habilidades;
    }
    
    public void setHabilidades(Habilidade[] habilidades) {
        this.habilidades = habilidades;
    }

    
}
