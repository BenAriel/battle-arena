package Entity;

public class Habilidade {
    private String nome;
    private String descricao;
    private int energia;
    private int energiaPreta;
    private int countdown;
    private Tipo[] tipos;

    public Habilidade(String nome, String descricao, int energia, int energiaPreta, int countdown, Tipo[] tipos) {
        this.nome = nome;
        this.descricao = descricao;
        this.energia = energia;
        this.energiaPreta = energiaPreta;
        this.countdown = countdown;
        this.tipos = tipos;
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

    public int getEnergiaPreta() {
        return energiaPreta;
    }

    public void setEnergiaPreta(int energiaPreta) {
        this.energiaPreta = energiaPreta;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public Tipo[] getTipos() {
        return tipos;
    }

    public void setTipos(Tipo[] tipos) {
        this.tipos = tipos;
    }

    
}
