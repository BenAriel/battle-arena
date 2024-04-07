import view.*;
import javafx.application.Application;
import javafx.stage.Stage;
import Controller.Dados;


public class Main extends Application  {
    public static void main(String[] args) {
        Dados.gerarJogadores();
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Telas telas = new Telas(primaryStage);
        Telas.switchScene("/view/ve/TelaSelecionarPersonagens.fxml/");
        //Telas.switchScene("/view/ve/TelaPartida.fxml/");
    }
}