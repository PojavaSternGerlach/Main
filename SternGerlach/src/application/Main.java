package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
    private AnchorPane layout;
    
    @Override
    public void start(Stage primaryStage) {
    	
    	// ustawienie tytu�u i rozmiaru okna
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Stern-Gerlach Experiment");
        this.primaryStage.setMinHeight(600);
        this.primaryStage.setMinWidth(800);
        
        initRootLayout();

        this.primaryStage.setResizable(false);
    }

    // wczytanie informacji z plik�w fxml i css
    public void initRootLayout() {
        try {
        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Test.fxml"));
            layout = (AnchorPane) loader.load();

            Scene scene = new Scene(layout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            String css = this.getClass().getResource("application.css").toExternalForm(); 
            scene.getStylesheets().add(css);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}