package Entity;

public class Habilidade {
    private int id;
    private String nome;
    private String descricao;
    private int custo;
    private int countdown;
    private Tipo[] tipos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
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
