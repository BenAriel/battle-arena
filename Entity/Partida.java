package Entity;

import java.time.Duration;
import java.util.Scanner;
import java.util.ArrayList;

public class Partida {
    private Jogador [] jogadores = new Jogador[2];
    private ArrayList<HabilidadePendente> habilidadesPendentes = new ArrayList<>();
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

    public ArrayList<HabilidadePendente> getHabilidadesPendentes() {
        return habilidadesPendentes;
    }

    public void setHabilidadesPendentes(ArrayList<HabilidadePendente> habilidadesPendentes) {
        this.habilidadesPendentes = habilidadesPendentes;
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

                    // adicição de habilidade a controlador
                    int escolhaAlvo = scanner.nextInt();
                    if (escolhaAlvo >= 0 && escolhaAlvo < 3 && (receptoresDisponiveis[0][escolhaAlvo] || receptoresDisponiveis[1][escolhaAlvo])) {
                        boolean unicaHabilidadePersonagemTurno = true;
                        for (int i = 0; i < habilidadesPendentes.size(); i++) {
                            if (habilidadesPendentes.get(i).getIdPersonagem() == escolhaPersonagem) {
                                unicaHabilidadePersonagemTurno = false;
                            }
                        }

                        if (unicaHabilidadePersonagemTurno) {
                            int[] energiasJogador = jogadores[idJogador].getEnergia();
                            int custoHabilidadeAtual = personagem.getHabilidades()[escolhaHabilidade].getEnergia();
                            int[] custoCalculadoPorEnergia = jogadores[idJogador].calcularCustoEnergia(custoHabilidadeAtual, energiasJogador[escolhaPersonagem]);

                            habilidadesPendentes.add(new HabilidadePendente(escolhaPersonagem, escolhaHabilidade, escolhaAlvo, custoCalculadoPorEnergia[0], custoCalculadoPorEnergia[1]));
                        
                            // remove "provisoriamente" as energias, até a habilidade, possivelmente, ser cancelada
                            jogadores[idJogador].removerEnergias(escolhaPersonagem, custoCalculadoPorEnergia[0]);
                            jogadores[idJogador].removerEnergias(3, custoCalculadoPorEnergia[1]);
                        }
                    }
                }
        }
    }

    public void habilidadePendentes() {
        if (habilidadesPendentes.size() > 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("---");
    
            for (int i = 0; i < habilidadesPendentes.size(); i++) {
                System.out.println(i+") "+habilidadesPendentes.get(i));
            }
            System.out.println("Outro) Voltar");
            int escolha = scanner.nextInt();

            if (escolha < habilidadesPendentes.size()) {
                int idPersonagem = habilidadesPendentes.get(escolha).getIdPersonagem();
                int custoPrincipal = habilidadesPendentes.get(escolha).getEnergiasPrincipais();
                int custoPreto = habilidadesPendentes.get(escolha).getEnergiasPretas();

                habilidadesPendentes.remove(escolha);

                jogadores[idJogador].adicionarEnergias(idPersonagem, custoPrincipal);
                jogadores[idJogador].adicionarEnergias(3, custoPreto);
            }

        }
    }

    public void passarTurno() {
        // troca de jogador
        int aux = idJogador;
        idJogador = idAdversario;
        idAdversario = aux;
    }

    public void turno() {
        Scanner scanner = new Scanner (System.in);

        jogadores[idJogador].meuTurno();

        int escolha = 0;
        
        while (escolha != -1) {
            System.out.println(jogadores[idJogador]);
            System.out.println("---");
            jogadores[idAdversario].exibirPersonagensSemEnergia();
            System.out.println("---");

            System.out.println("1. Passar Turno | 2. Utilizar Habilidade | 3. Habilidade Pendentes");
            escolha = scanner.nextInt();

            if (escolha == 1) {
                System.out.println("---");
                while (habilidadesPendentes.size() > 0) {
                    int idPersonagem = habilidadesPendentes.get(0).getIdPersonagem();
                    int idHabilidade = habilidadesPendentes.get(0).getIdHabilidade();
                    int idAlvo = habilidadesPendentes.get(0).getIdPersonagemAlvo();

                    Jogador[] recepJogadores = jogadores[idJogador].utilizarHabilidade(jogadores[idJogador], jogadores[idAdversario], idPersonagem, idHabilidade, idAlvo);
                    jogadores[idJogador] = recepJogadores[0];
                    jogadores[idAdversario] = recepJogadores[1];

                    habilidadesPendentes.remove(0);
                }
                escolha = 0;
                scanner.nextLine();
                scanner.nextLine();

                jogadores[idJogador].passarTurno();
                jogadores[idAdversario].meuTurno();
                int idAux = idJogador;
                idJogador = idAdversario;
                idAdversario = idAux;

            }

            if (escolha == 2) {
                utilizarHabilidade();
                escolha = 0;
            }
            else if (escolha == 3) {
                habilidadePendentes();
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
