package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// klasa obs³uguj¹ca wyœwietlanie okien z informacjami
public class manController implements Initializable{

	@FXML private Button ok;	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ok.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) ok.getScene().getWindow();
                stage.close();
            }
        });
	}
}
