package Entity;
import java.time.Duration;

public class Partida {
    private Jogador [] jogadores = new Jogador[2];
    private int vencerdor;//0 para nenhum,1 para jogador 1, 2 para jogador 2
    private Duration tempoPorTurno;
    private int turnos;
    private int turnoMaximo;

    public Partida(Jogador[] jogadores) {
        setJogadores(jogadores);
        setTurno(0);
        setTempoPorTurno();
        setTurnoMaximo();
        setVencerdor(0);
    }

    public Jogador[] getJogadores() {
        return jogadores;
    }
    public Duration getTempo() {
        return tempoPorTurno;
    }
    public int getTurno() {
        return turnos;
    }
    public int getTurnoMaximo() {
        return turnoMaximo;
    }
    public int getVencerdor() {
        return vencerdor;
    }
    public void setJogadores(Jogador[] jogadores) {
        if (jogadores.length==2) {
            if(jogadores[0] != null && jogadores[1] != null) {
                if(jogadores[0].getNick() != jogadores[1].getNick()) {
        this.jogadores = jogadores;
           }
         }
        }
    }
    public void setVencerdor(int vencerdor) {
        this.vencerdor = vencerdor;
    }
    public void setTurno(int turnos) {
        if (turnos>=0 && turnos<= getTurnoMaximo()) {
        this.turnos = turnos;
        }
    }
    public void setTurnoMaximo() {
        this.turnoMaximo = 35;
    }
    public void turnoAcabou() {
        int turnoAtual = getTurno() + 1;
        setTurno(turnoAtual);
    }
    public void setTempoPorTurno() {
        this.tempoPorTurno = Duration.ofSeconds(30);
    }
}
