import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.time.Duration;
import java.util.ArrayList;

import Entity.Jogador;
import Entity.Partida;
import Entity.Personagem;
import Entity.Personagens.Fade;
import Entity.Personagens.Sage;
import Entity.Personagens.Viper;

public class Main extends Application {
    private Duration tempoPorTurno = Duration.ofSeconds(30);
    private Timeline timeline;
    private Label labelTempo;

    public static void main(String[] args) {
        launch(args);
        
        Jogador[] jogadores = new Jogador[2];

        ArrayList<Personagem> personagensA = new ArrayList<>();
        personagensA.add(new Fade());
        personagensA.add(new Sage());
        personagensA.add(new Viper());
        jogadores[0] = new Jogador ("Ariel", personagensA);

        ArrayList<Personagem> personagensB = new ArrayList<>();
        personagensB.add(new Viper());
        personagensB.add(new Sage());
        personagensB.add(new Fade());
        jogadores[1] = new Jogador ("Lucas", personagensB);

        Partida partidaA = new Partida(jogadores);
        partidaA.turno();
    }

    @Override
    public void start(Stage primaryStage) {
        labelTempo = new Label(formatarTempo(tempoPorTurno));

        timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(1), (javafx.event.ActionEvent event) -> {
                    tempoPorTurno = tempoPorTurno.minusSeconds(1);
                    labelTempo.setText(formatarTempo(tempoPorTurno));

                    if (tempoPorTurno.isZero()) {
                        // O tempo do turno acabou, adicione lógica aqui
                        timeline.stop();
                    }
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);

        Scene scene = new Scene(labelTempo,  200,  100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cronômetro de Turno");
        primaryStage.show();

        // Iniciar o cronômetro quando a tela for mostrada
        timeline.play();
    }

    private String formatarTempo(Duration duration) {
        long segundos = duration.getSeconds();
        return String.format("%02d:%02d", segundos /  60, segundos %  60);
    }
}
