import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.time.Duration;

public class Main extends Application {
    private Duration tempoPorTurno = Duration.ofSeconds(30);
    private Timeline timeline;
    private Label labelTempo;

    public static void main(String[] args) {
        launch(args);

        System.out.println("Hello World!\n");
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
