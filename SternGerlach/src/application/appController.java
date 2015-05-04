package application;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class appController implements Initializable{

	Locale Polish = new Locale("Polish", "Poland","pl");
	Locale lang = Locale.ENGLISH;
	private ResourceBundle bundle;
	
	@FXML private MenuItem menuSave;
	@FXML private MenuItem menuClose;
	@FXML private MenuItem polishMenu;
	@FXML private MenuItem englishMenu;
	@FXML private MenuItem menuAbout;
	@FXML private MenuItem manual;

	@FXML private Menu file;
	@FXML private Menu help;
	@FXML private Menu language;
	
	@FXML private Label magnets;
	@FXML private Label probs;
	@FXML private Label settings;
	@FXML private Label magn1;
	@FXML private Label magn2;
	@FXML private Label magn3;
	
	@FXML private CheckBox enable;
	@FXML private CheckBox check1;
	@FXML private CheckBox check2;
	@FXML private CheckBox check3;
	
	@FXML private Button example;	
	
	@FXML private Tab state;
	@FXML private Tab sphere;
	
	@FXML private Box firstMagnet;
	@FXML private Box secondMagnet;
	@FXML private Box thirdMagnet;	
	@FXML private Box ekran;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		check1.setSelected(true);
		check1.setDisable(true);
		check2.setSelected(true);
		check2.setDisable(true);
		check3.setSelected(true);
		
		final PhongMaterial material = new PhongMaterial();
	    material.setSpecularColor(Color.LIGHTGREY);
	    material.setDiffuseColor(Color.LIGHTYELLOW);
	    ekran.setMaterial(material);
	       
	    final PhongMaterial magnets = new PhongMaterial();
	    magnets.setSpecularColor(Color.GREEN);
	    magnets.setDiffuseColor(Color.CHARTREUSE);
	    firstMagnet.setMaterial(magnets);
	    secondMagnet.setMaterial(magnets);
	    thirdMagnet.setMaterial(magnets);

		
		menuSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //TODO: zapisywanie do pliku
            }
        });
		menuClose.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
		firstMagnet.setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            System.out.println("mouse click detected! "+event.getSource());
	        }
	    });
		polishMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {
				lang = Polish;
				SetLanguage();
			}
		});
		englishMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {
				lang = Locale.ENGLISH;
				SetLanguage();
			}
		});
		check2.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {
				if(!check2.isSelected()){
					secondMagnet.setVisible(false);
					check3.setDisable(true);
				}
				else{
					secondMagnet.setVisible(true);
					check3.setDisable(false);
			}}
		});
		check3.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {
				if(check3.isSelected()){
					thirdMagnet.setVisible(true);
					check2.setDisable(true);
				}
				else{
					thirdMagnet.setVisible(false);
					check2.setDisable(false);
				}
			}
		});
		
	}
	
	private void SetLanguage(){
		
		bundle = ResourceBundle.getBundle("application.lang.lang",lang);
		
		menuSave.setText(bundle.getString("save"));
		menuClose.setText(bundle.getString("close"));
		polishMenu.setText(bundle.getString("polish"));
		englishMenu.setText(bundle.getString("english"));
		file.setText(bundle.getString("file"));
		help.setText(bundle.getString("help"));
		language.setText(bundle.getString("language"));
		example.setText(bundle.getString("example"));
		enable.setText(bundle.getString("enable"));
		probs.setText(bundle.getString("prob"));
		menuAbout.setText(bundle.getString("about"));
		state.setText(bundle.getString("states"));
		sphere.setText(bundle.getString("sphere"));
		manual.setText(bundle.getString("manual"));
		magn1.setText(bundle.getString("component")+" 1:");
		magn2.setText(bundle.getString("component")+" 2:");
		magn3.setText(bundle.getString("component")+" 3:");
	}

}
