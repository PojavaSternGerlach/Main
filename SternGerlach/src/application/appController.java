package application;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class appController implements Initializable{

	//ustawienia jezyka
	Locale Polish = new Locale("Polish", "Poland","pl");
	Locale lang = Locale.ENGLISH;
	private ResourceBundle bundle;
	
	//obecnie modyfikowane zmienne
	private Boolean settingMode = true;
	
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
	
	// FXML
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
	@FXML private Button start;
	
	@FXML private Tab state;
	@FXML private Tab sphere;
	
	@FXML private AnchorPane magnetsAnimate;
	@FXML private AnchorPane magnetsRot;
	
	@FXML private Box firstMagnet;
	@FXML private Box secondMagnet;
	@FXML private Box thirdMagnet;	
	@FXML private Box ekran;
	@FXML private Sphere charge;
	
	@FXML private BarChart<String, Number> chart=new BarChart<>(xAxis, yAxis);
	
	// inicjalizacja zmiennych
	private Stage dialogStage;
	private AnchorPane layout;
	
	boolean vM2 = true;
	boolean vM3 = true;
		
	double theta1 = 0;
	double theta2 = 0;
	double theta3 = 0;
	double x,y;
	double newX, newY;
	int on2=1, on3=1;

    DecimalFormat df = new DecimalFormat("#.###");
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//poczatkowe ustawienia wyswietlania
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
		
		charge.setVisible(false);

		//wykres - TODO
		XYChart.Data<String, Integer> up = new XYChart.Data<String, Integer>("up", 60);
        XYChart.Data<String, Integer> down = new XYChart.Data<String, Integer>("down", 40);
        
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(up);
        series1.getData().add(down);
        // czysci: series1.getData().clear();
        chart.setMaxHeight(180);
        chart.setMaxWidth(200);
        chart.getData().addAll(series1);
        chart.setLegendVisible(false);
       
		
		
		// Kolorystyka animacji
		final PhongMaterial material = new PhongMaterial();
	    material.setSpecularColor(Color.LIGHTGREY);
	    material.setDiffuseColor(Color.LIGHTYELLOW);
	    ekran.setMaterial(material);
	       
	    final PhongMaterial magnets = new PhongMaterial();
	    magnets.setSpecularColor(Color.RED);
	    magnets.setDiffuseColor(Color.RED);
	    firstMagnet.setMaterial(magnets);
	    secondMagnet.setMaterial(magnets);
	    thirdMagnet.setMaterial(magnets);

		// Menu
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
		
		// sterowanie liczb¹ magnesów
		check2.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {
				if(!check2.isSelected()){
					p3.setText("");
					p4.setText("");
					vM2 = false;
					check3.setDisable(true);
					on2 = 0;
					repaint();
				}
				else{
					p3.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta2/2)*Math.PI/180)), 2)))));
					p4.setText("p="+(df.format((0.5*Math.pow((Math.sin((theta2/2)*Math.PI/180)), 2)))));
					vM2 = true;
					check3.setDisable(false);
					on2 = 1;
					repaint();
					
			}}
		});
		check3.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {
				if(check3.isSelected()){
					p5.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)))));
					p6.setText("p="+(df.format((0.5*Math.pow((Math.sin((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)))));
					vM3= true;
					check2.setDisable(true);
					on3 = 1;
					
					repaint();
				}
				else{
					p5.setText("");
					p6.setText("");
					vM3 = false;
					check2.setDisable(false);
					on3 = 0;
					
					repaint();
				}
			}
		});
		
		// instrukcja i opis eksperymentu
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
		
		// wyœwietlanie prawdopodobieñstwa
		enable.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {
				if(!enable.isSelected()){
					p1.setVisible(false);
					p2.setVisible(false);
					p3.setVisible(false);
					p4.setVisible(false);
					p5.setVisible(false);
					p6.setVisible(false);
					
					repaint();
				}
				else{
					p1.setVisible(true);
					p2.setVisible(true);
					p3.setVisible(true);
					p4.setVisible(true);
					p5.setVisible(true);
					p6.setVisible(true);
					
					repaint();

			}}
		});
		start.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {

				startCalculation();
			/*
				charge.setVisible(false);
				charge.setTranslateY(0);
				
				check2.setDisable(false);
				check3.setDisable(false);
				settingMode = true;
			*/	
				

			}}
		);

		
	}
	
	// jêzyk
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

	
	//poruszanie magnesami
	@FXML
    public void mousePressed(MouseEvent e) {
        x = e.getSceneX();
        y = e.getSceneY();
    }
	
	@FXML
    public void mouseDragged(MouseEvent e) {
		
		if(settingMode){
			
			newY = e.getSceneY();
        
			double dy = newY-y;
			Rotate rotation = new Rotate(dy/180);
			rotation.setAxis(Rotate.Y_AXIS);
        
			if ((y<340 && y>280)&&(x<265 && x>210)&&(on2==1)){
				secondMagnet.getTransforms().add(rotation);
				theta2 -= dy/180;
				if (theta2>360)
					theta2-=360;
				if (theta2<-360)
					theta2+=360;
				ang2.setText(Integer.toString((int)theta2) + " " + ResourceBundle.getBundle("application.lang.lang",lang).getString("deg"));
				repaint();
			}
			if ((y<300 && y>220)&&(x<380 && x>320)&&(on3==1)){
				thirdMagnet.getTransforms().add(rotation);
				theta3 -=dy/180;
				if (theta3>360)
					theta3-=360;
				if (theta3<-360)
					theta3+=360;
				ang3.setText(Integer.toString((int)theta3) + " " + ResourceBundle.getBundle("application.lang.lang",lang).getString("deg"));
				repaint();
			}
        
			p1.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta1/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta1/2)*Math.PI/180)),2)))));
			p2.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta1/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta1/2)*Math.PI/180)),2)))));
			if (on2 == 1){
				p3.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta2/2)*Math.PI/180)), 2)))));
				p4.setText("p="+(df.format((0.5*Math.pow((Math.sin((theta2/2)*Math.PI/180)), 2)))));
			}
			else if (on2 == 0){
				p3.setText("");
				p4.setText("");
			}
			if (on3 == 1){
				p5.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)))));
				p6.setText("p="+(df.format((0.5*Math.pow((Math.sin((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)))));
			}
			else if (on3 == 0){
				p5.setText("");
				p6.setText("");
			}
       	}
	}
	
	
	void repaint(){//odswiezanie ekranu
		firstMagnet.setVisible(false);
		secondMagnet.setVisible(false);
		thirdMagnet.setVisible(false);
		ekran.setVisible(false);
		
		ekran.setVisible(true);
		thirdMagnet.setVisible(vM3);
		secondMagnet.setVisible(vM2);
		firstMagnet.setVisible(true);
	}
	
	void startCalculation(){//inicjalizacja animacji - TODO: ogarniecie animacji do konca
		charge.setVisible(true);
		
		check2.setDisable(true);
		check3.setDisable(true);
		settingMode = false;
		
		Task<Void> task = new Task<Void>() {
		    @Override public Void call() {
		        for (int i=1; i<380; i++) {
		            if (isCancelled()) {
		               break;
		            }
		            charge.setTranslateY(charge.getTranslateY()+1);
		    		firstMagnet.setVisible(false);
		    		secondMagnet.setVisible(false);
		    		thirdMagnet.setVisible(false);
		    		ekran.setVisible(false);
		    		
		    		ekran.setVisible(true);
		    		thirdMagnet.setVisible(vM3);
		    		secondMagnet.setVisible(vM2);
		    		firstMagnet.setVisible(true);
		            try {
		            	Thread.sleep(10);
		            } catch (InterruptedException e) {
		            	e.printStackTrace();
		            }
		        }
		        return null;
		    }
		};
		
		new Thread(task).start();
		
		}
				
	
	
	void resize(int width, int height){//zmiana rozdzielczosci ekranu - TODO
		
	}
    }

