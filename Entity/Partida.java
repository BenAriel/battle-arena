package Entity;

import java.time.Duration;
import java.util.Scanner;

public class Partida {
    private Jogador [] jogadores = new Jogador[2];
    private int vencedor; //0 para nenhum,1 para jogador 1, 2 para jogador 2
    private Duration tempoPorTurno;
    private int turnos;
    private int idJogador;
    private int idAdversario;
    private int turnoMaximo;

    public Partida(Jogador[] jogadores) {
        setJogadores(jogadores);
        setTurnos(0);
        setTurnoMaximo(100);
        setVencedor(0);
        idJogador = 0;
        idAdversario = 1;
    }

    public Jogador[] getJogadores() {
        return jogadores;
    }

    public void setJogadores(Jogador[] jogadores) {
        this.jogadores = jogadores;
    }

    public int getVencedor() {
        return vencedor;
    }

    public void setVencedor(int vencerdor) {
        this.vencedor = vencerdor;
    }

    public Duration getTempoPorTurno() {
        return tempoPorTurno;
    }

    public void setTempoPorTurno(Duration tempoPorTurno) {
        this.tempoPorTurno = tempoPorTurno;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public int getTurnoMaximo() {
        return turnoMaximo;
    }

    public void setTurnoMaximo(int turnoMaximo) {
        this.turnoMaximo = turnoMaximo;
    }

    public void utilizarHabilidade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---");
        boolean[] personagensDisponiveis = jogadores[idJogador].exibirPersonagensVivos();
        int escolhaPersonagem = scanner.nextInt();
        System.out.println("---");

        if (escolhaPersonagem >= 0 && escolhaPersonagem < personagensDisponiveis.length && personagensDisponiveis[escolhaPersonagem]) {
                Personagem personagem = jogadores[idJogador].getPersonagens().get(escolhaPersonagem);

                int[] energias = jogadores[idJogador].getEnergia();
                boolean[] habilidadesDisponiveis = personagem.exibirHabilidadesDisponiveis(energias[escolhaPersonagem]+energias[3]);

                int escolhaHabilidade = scanner.nextInt();

                if (escolhaHabilidade >= 0 && escolhaHabilidade < habilidadesDisponiveis.length && habilidadesDisponiveis[escolhaHabilidade]) {
                    boolean[][] receptoresDisponiveis = jogadores[idJogador].verificarHabilidade(escolhaPersonagem, escolhaHabilidade, jogadores[idAdversario].getPersonagens());

                    // exibição de personagens "receptores" disponíveis
                    System.out.println("---");
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < receptoresDisponiveis[i].length; j++) {
                            if (receptoresDisponiveis[i][j]) {
                                Personagem personagemReceptor = jogadores[i].getPersonagens().get(j);
                                System.out.println(j+") "+personagemReceptor.getNome()+" ("+personagemReceptor.getVida()+")");

                                
                            }
                        }
                    }
                    int escolhaAlvo = scanner.nextInt();
                    if (escolhaAlvo >= 0 && escolhaAlvo < 3 && (receptoresDisponiveis[0][escolhaAlvo] || receptoresDisponiveis[1][escolhaAlvo])) {
                        Jogador[] recepJogadores = jogadores[idJogador].utilizarHabilidade(jogadores[idJogador], jogadores[idAdversario], escolhaPersonagem, escolhaHabilidade, escolhaAlvo);
                        
                        jogadores[idJogador] = recepJogadores[0];
                        jogadores[idAdversario] = recepJogadores[1];
                    }


                }


        }


        //jogadores[idJogador].verificarHabilidade(idPersonagem, idHabilidade, jogB.getPersonagens());
    }

    public void passarTurno() {
        // troca de jogador
        int aux = idJogador;
        idJogador = idAdversario;
        idAdversario = aux;
    }

    public void turno() {
        Scanner scanner = new Scanner (System.in);

        jogadores[idJogador].getPersonagens().get(0).setVida(0);
        jogadores[idJogador].getPersonagens().get(1).setVida(12);
        jogadores[idJogador].getPersonagens().get(2).setVida(90);

        jogadores[idAdversario].getPersonagens().get(0).setVida(20);
        jogadores[idAdversario].getPersonagens().get(1).setVida(0);
        jogadores[idAdversario].getPersonagens().get(2).setVida(40);

        jogadores[idJogador].meuTurno();
        jogadores[idJogador].meuTurno();
        jogadores[idJogador].meuTurno();
        jogadores[idJogador].meuTurno();
        jogadores[idJogador].meuTurno();
        jogadores[idJogador].meuTurno();

        jogadores[idJogador].meuTurno(); // lógica de iniciar turno do jogador

        int[] copiaEnergias = jogadores[idJogador].getEnergia();
        int escolha = 0;
        
        while (escolha != 1) {
            System.out.println(jogadores[idJogador]);
            System.out.println("---");
            jogadores[idAdversario].exibirPersonagensSemEnergia();
            System.out.println("---");

            System.out.println("1. Passar Turno | 2. Utilizar Habilidade");
            escolha = scanner.nextInt();

            if (escolha == 2) {
                utilizarHabilidade();
                escolha = 0;
            }
        }

        scanner.close();

    }

    public String toString() {
        String str = "";

        return str;
    }
}
