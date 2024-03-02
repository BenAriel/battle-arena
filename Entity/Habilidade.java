package Entity;

public class Habilidade {
    private String nome;
    private String descricao;
    private int energia;
    private int countdown;

    public Habilidade(String nome, String descricao, int energia, int countdown) {
        this.nome = nome;
        this.descricao = descricao;
        this.energia = energia;
        this.countdown = countdown;
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

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }
}
