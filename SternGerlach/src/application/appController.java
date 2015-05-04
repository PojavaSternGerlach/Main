package application;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

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
	@FXML private Label ang1;
	@FXML private Label ang2;
	@FXML private Label ang3;	
	@FXML private Label p1;
	@FXML private Label p2;
	@FXML private Label p3;
	@FXML private Label p4;
	@FXML private Label p5;
	@FXML private Label p6;

	
	@FXML private CheckBox enable;
	@FXML private CheckBox check1;
	@FXML private CheckBox check2;
	@FXML private CheckBox check3;
	
	@FXML private Button example;	
	
	@FXML private Tab state;
	@FXML private Tab sphere;
	
	@FXML private AnchorPane magnetsAnimate;
	
	@FXML private Box firstMagnet;
	@FXML private Box secondMagnet;
	@FXML private Box thirdMagnet;	
	@FXML private Box ekran;
	
	private Stage dialogStage;
	private AnchorPane layout;
		
	double theta1 = 0;
	double theta2 = 0;
	double theta3 = 0;
	
	double x,y;
	double newX, newY;


	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		check1.setSelected(true);
		check1.setDisable(true);
		check2.setSelected(true);
		check2.setDisable(true);
		check3.setSelected(true);
		
		p1.setVisible(false);
		p2.setVisible(false);
		p3.setVisible(false);
		p4.setVisible(false);
		p5.setVisible(false);
		p6.setVisible(false);
		
		
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
		manual.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {
				if(lang == Locale.ENGLISH){
					dialogStage = new Stage();
					dialogStage.setTitle("Manual");
					 try {
				            FXMLLoader loader = new FXMLLoader();
				            loader.setLocation(Main.class.getResource("manual.fxml"));
				            layout = (AnchorPane) loader.load();

				            Scene scene = new Scene(layout);
				            dialogStage.setScene(scene);
				            dialogStage.show();
				            
				            String css = this.getClass().getResource("application.css").toExternalForm(); 
				            scene.getStylesheets().add(css);
				            
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }
				}
				else{
					dialogStage = new Stage();
					dialogStage.setTitle("Instrukcja");
					 try {
				            FXMLLoader loader = new FXMLLoader();
				            loader.setLocation(Main.class.getResource("instrukcja.fxml"));
				            layout = (AnchorPane) loader.load();

				            Scene scene = new Scene(layout);
				            dialogStage.setScene(scene);
				            dialogStage.show();
				            
				            String css = this.getClass().getResource("application.css").toExternalForm(); 
				            scene.getStylesheets().add(css);
				            
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }
				}
			}
		});
		menuAbout.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {
				if(lang == Locale.ENGLISH){
					dialogStage = new Stage();
					dialogStage.setTitle("About experiment");
					 try {
				            FXMLLoader loader = new FXMLLoader();
				            loader.setLocation(Main.class.getResource("about.fxml"));
				            layout = (AnchorPane) loader.load();

				            Scene scene = new Scene(layout);
				            dialogStage.setScene(scene);
				            dialogStage.show();
				            
				            String css = this.getClass().getResource("application.css").toExternalForm(); 
				            scene.getStylesheets().add(css);
				            
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }
				}
				else{
					dialogStage = new Stage();
					dialogStage.setTitle("O eksperymencie");
					 try {
				            FXMLLoader loader = new FXMLLoader();
				            loader.setLocation(Main.class.getResource("exp.fxml"));
				            layout = (AnchorPane) loader.load();

				            Scene scene = new Scene(layout);
				            dialogStage.setScene(scene);
				            dialogStage.show();
				            
				            String css = this.getClass().getResource("application.css").toExternalForm(); 
				            scene.getStylesheets().add(css);
				            
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }
				}
			}
		});
		enable.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {
				if(!enable.isSelected()){
					p1.setVisible(false);
					p2.setVisible(false);
					p3.setVisible(false);
					p4.setVisible(false);
					p5.setVisible(false);
					p6.setVisible(false);
				}
				else{
					p1.setVisible(true);
					p2.setVisible(true);
					p3.setVisible(true);
					p4.setVisible(true);
					p5.setVisible(true);
					p6.setVisible(true);

			}}
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
		ang1.setText(Integer.toString((int)theta1) + " " + bundle.getString("deg"));
		ang2.setText(Integer.toString((int)theta2) + " " + bundle.getString("deg"));
		ang3.setText(Integer.toString((int)theta3) + " " + bundle.getString("deg"));
	}

	@FXML
    public void mousePressed(MouseEvent e) {
        x = e.getSceneX();
        y = e.getSceneY();
    }
	
	@FXML
    public void mouseDragged(MouseEvent e) {
		
		
		newY = e.getSceneY();
        
        double dy = newY-y;
        Rotate rotation = new Rotate(dy/180);
        rotation.setAxis(Rotate.Y_AXIS);
        
        if ((y<340 && y>280)&&(x<265 && x>210)){
          	 secondMagnet.getTransforms().add(rotation);
          	 theta2 -= dy/180;
          	 if (theta2>360)
          		 theta2-=360;
          	if (theta2<-360)
         		 theta2+=360;
          	 ang2.setText(Integer.toString((int)theta2) + " " + ResourceBundle.getBundle("application.lang.lang",lang).getString("deg"));
           }
        if ((y<300 && y>220)&&(x<380 && x>320)){
          	 thirdMagnet.getTransforms().add(rotation);
          	 theta3 -=dy/180;
          	 if (theta3>360)
          		 theta3-=360;
          	if (theta3<-360)
         		 theta3+=360;
          	 ang3.setText(Integer.toString((int)theta3) + " " + ResourceBundle.getBundle("application.lang.lang",lang).getString("deg"));
           }
        }
    }

