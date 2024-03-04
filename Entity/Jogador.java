package Entity;

import java.util.ArrayList;
import java.util.List;
import Resources.Aleatory;

public class Jogador {
    private int[] energias = new int[4];
    private String nick;
    private List<Personagem> personagens;

    public Jogador(String nick, List<Personagem> personagens) {
        setNick(nick);
        setPersonagens(personagens);
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        if(!nick.isEmpty() && nick.length() > 3) {
            this.nick = nick;
        }
    }

    public List<Personagem> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<Personagem> personagens) {
        this.personagens= new ArrayList<Personagem>();
        this.personagens = personagens;
    }

    public int[] getEnergia() {
        return energias;
    }

    public void setEnergia(int[] energias) {
        this.energias = energias;
    }

    public void adicionarEnergias(int index, int valor) {
        this.energias[index] += valor;
    }

    public void removerEnergias(int index, int valor) {
        this.energias[index] -= valor;
    }

    public boolean[] obterVivos() {
        boolean[] vivos = new boolean[personagens.size()];

        for (int i = 0; i < personagens.size(); i++) {
            if (personagens.get(i).getVida() > 0) {
                vivos[i] = true;
            }
        }

        return vivos;
    }

    public boolean[][] obterVivos(List<Personagem> personagensAdversario) {
        boolean[][] vivos = new boolean[2][personagens.size()];

        for (int i = 0; i < personagens.size(); i++) {
            if (personagens.get(i).getVida() > 0) {
                vivos[0][i] = true;
            }
        }

        for (int i = 0; i < personagensAdversario.size(); i++) {
            if (personagensAdversario.get(i).getVida() > 0) {
                vivos[1][i] = true;
            }
        }

        return vivos;
    }

    public boolean[] obterInvulneraveis(List<Personagem> personagensAdversario) {
        boolean[] invulneraveis = new boolean[personagensAdversario.size()];

        for (int i = 0; i < personagensAdversario.size(); i++) {
            invulneraveis[i] = personagensAdversario.get(i).getInvulneravel() > 0;
        }

        return invulneraveis;
    }

    public int[] calcularCustoEnergia(int custo, int energiasPersonagem) {
        int[] energiasGastas = new int[2];

        if (custo <= energiasPersonagem) {
            energiasGastas[0] = custo;
        }
        else {
            energiasGastas[0] = energiasPersonagem;
            custo -= energiasPersonagem;
            energiasGastas[1] = custo;
        }

        return energiasGastas;
    }

    public boolean[][] verificarHabilidade(int idPersonagem, int idHabilidade, List<Personagem> personagensAdversario) {
        boolean[][] disponiveis;
        boolean[][] vivos = obterVivos(personagensAdversario);
        boolean[] invulneraveis = obterInvulneraveis(personagensAdversario);

        disponiveis = personagens.get(idPersonagem).verificarHabilidade(vivos, invulneraveis, idPersonagem, idHabilidade);

        return disponiveis;
    }

    public Jogador[] utilizarHabilidade(Jogador jogador, Jogador alvo, int idPersonagem, int idHabilidade, int idPersonagemAlvo) {
        Personagem personagem = jogador.getPersonagens().get(idPersonagem);

        return personagem.utilizarHabilidade(jogador, alvo, idHabilidade, idPersonagem, idPersonagemAlvo);
    }

    public void meuTurno() {
        for (int i = 0; i < personagens.size(); i++) {
            personagens.get(i).meuTurno();
        }

        boolean[] vivos = obterVivos();
        int numVivos = 0;

        for (int i = 0; i < vivos.length; i++) {
            if (vivos[i]) {
                numVivos++;
            }
        }


        if (numVivos == 1) {
            for (int i = 0; i < vivos.length; i++) {
                if (vivos[i]) {
                    energias[i]++;
                }
            }
        }
        else {
            Aleatory<Integer> random = new Aleatory<>();

            int repeticoes = 0;
            while (repeticoes != numVivos) {
                int index = random.randrange(4);

                if (index == 3 || vivos[index]) {
                    energias[index]++;

                    repeticoes++;
                }
            }
        }
    }

    public void passarTurno() {
        for (int i = 0; i < personagens.size(); i++) {
            personagens.get(i).passarTurno();
        }
    }

    public boolean[] exibirPersonagensVivosNaoStunnados() {
        boolean[] disponiveis = new boolean[personagens.size()];

        if (personagens.get(0).getVida() > 0 && personagens.get(0).getStunned() == 0) {
            disponiveis[0] = true;
            System.out.print("0) Energia Vermelha ("+energias[0]+") | ");
            System.out.print(personagens.get(0));
        }
        if (personagens.get(1).getVida() > 0 && personagens.get(1).getStunned() == 0) {
            disponiveis[1] = true;
            System.out.print("\n1) Energia Amarela ("+energias[1]+") | ");
            System.out.print(personagens.get(1));
        }
        if (personagens.get(2).getVida() > 0 && personagens.get(2).getStunned() == 0) {
            disponiveis[2] = true;
            System.out.print("\n2) Energia Azul ("+energias[2]+") | ");
            System.out.print(personagens.get(2));
        }
        System.out.println("\nEnergia Preta ("+energias[3]+")");

        return disponiveis;
    }

    public void exibirPersonagensSemEnergia() {
        System.out.println("=== "+nick+" ===");
        System.out.println(personagens.get(0));
        System.out.println(personagens.get(1));
        System.out.println(personagens.get(2));
    }

    public String toString() {
        String str = "";

        str += "===== "+nick+" =====\n";

        str += "Energia Vermelha ("+energias[0]+") | ";
        str += personagens.get(0)+"\n";
        

        str += "Energia Amarela ("+energias[1]+") | ";
        str += personagens.get(1)+"\n";
        str += "Energia Amarela ("+energias[2]+") | ";
        str += personagens.get(2)+"\n";
        str += "Energia Preta ("+energias[3]+")";

        return str;
    }
}
