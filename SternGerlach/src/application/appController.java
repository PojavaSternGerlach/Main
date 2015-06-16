package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.SubScene;
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
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class appController implements Initializable{

	//ustawienia jêzyka
	Locale Polish = new Locale("Polish", "Poland","pl");
	Locale lang = Locale.ENGLISH;
	private ResourceBundle bundle;
	
	//obecnie modyfikowane zmienne
	private Boolean settingMode = true;

    boolean upordown;
	
	// inicjalizacja osi wykresu
	CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis(0,50,10);
    
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

	@FXML private Label countUp;
	@FXML private Label countDown;
	@FXML private Label detected;
	@FXML private Label up;
	@FXML private Label down;
	
	@FXML private Tab sim;

	@FXML private CheckBox enable;
	@FXML private CheckBox check1;
	@FXML private CheckBox check2;
	@FXML private CheckBox check3;
	
	@FXML private Button example;
	@FXML private Button start;
	@FXML private Button reset;
	
	@FXML private Tab states;
	@FXML private Tab state;
	@FXML private Tab sphere;
	@FXML private Tab sphere2;
	@FXML private Tab sphere3;
	@FXML private Tab sphere4;
	
	@FXML private SubScene scene;
	
	@FXML private AnchorPane magnetsAnimate;
	
	@FXML private Group magnetsRot;
	@FXML private Group firstMagnet;
	@FXML private Group secondMagnet;
	@FXML private Group thirdMagnet;
		
	@FXML private Box firstMagnet1;
	@FXML private Box firstMagnet2;
	@FXML private Box secondMagnet1;
	@FXML private Box secondMagnet2;
	@FXML private Box thirdMagnet1;
	@FXML private Box thirdMagnet2;	
	@FXML private Box secondMagnet1p;
	@FXML private Box secondMagnet2p;
	@FXML private Box thirdMagnet1p;
	@FXML private Box thirdMagnet2p;	
	@FXML private Box ekran;
	
	@FXML private Sphere charge;
	@FXML private Sphere charge2;
	@FXML private Sphere charge3;
	@FXML private Sphere charge4;
	
	@FXML private BarChart<String,Number> chart = new BarChart<String,Number>(xAxis,yAxis);  
	
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

	int probup = 0;
	int probdown = 0;
	
	int countup = 0;
	int countdown = 0;
	
    DecimalFormat df = new DecimalFormat("#.###");

    // losowanie koloru
    Random rand = new Random();
    float r = rand.nextFloat();
    float gr = rand.nextFloat();
    float b = rand.nextFloat();
    Color randomColor = new Color(r, gr, b, 1);	
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// poczatkowe ustawienia wyswietlania
		check1.setSelected(true);
		check1.setDisable(true);
		check2.setSelected(true);
		check2.setDisable(true);
		check3.setSelected(true);
		
		// domyœlnie wy³¹czone wyœwietlanie prawdopodobieñstwa
		p1.setVisible(false);
		p2.setVisible(false);
		p3.setVisible(false);
		p4.setVisible(false);
		p5.setVisible(false);
		p6.setVisible(false);
		
		//wykres
        XYChart.Series series1 = new XYChart.Series();
        
		XYChart.Data<String, Integer> up = new XYChart.Data<String, Integer>("up", 0);
        XYChart.Data<String, Integer> down = new XYChart.Data<String, Integer>("down", 0);
        
        series1.getData().add(up);
        series1.getData().add(down);
        
        chart.setMaxHeight(250);
        chart.setMaxWidth(250);
        chart.getData().addAll(series1);
        chart.setLegendVisible(false);
    	yAxis.setAutoRanging(false);
    	
		// kolorystyka animacji
		final PhongMaterial material = new PhongMaterial();
	    material.setSpecularColor(Color.LIGHTGREY);
	    material.setDiffuseColor(Color.LIGHTYELLOW);
	    ekran.setMaterial(material);
	       
	    final PhongMaterial magnets = new PhongMaterial();
	    magnets.setSpecularColor(Color.RED);
	    magnets.setDiffuseColor(Color.RED);
	    
	    final PhongMaterial magnets2 = new PhongMaterial();
	    magnets2.setSpecularColor(Color.CORNFLOWERBLUE);
	    magnets2.setDiffuseColor(Color.DEEPSKYBLUE);
	    
	    firstMagnet1.setMaterial(magnets);
	    firstMagnet2.setMaterial(magnets2);
	    secondMagnet1.setMaterial(magnets);
	    secondMagnet2.setMaterial(magnets2);
	    thirdMagnet1.setMaterial(magnets);
	    thirdMagnet2.setMaterial(magnets2);
	    secondMagnet1p.setMaterial(magnets);
	    secondMagnet2p.setMaterial(magnets2);
	    thirdMagnet1p.setMaterial(magnets);
	    thirdMagnet2p.setMaterial(magnets2);

		// Menu
		menuSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	chart.setAnimated(false);
            	WritableImage image = (chart.getParent()).snapshot(new SnapshotParameters(), null);
                chart.setAnimated(true);
                
                FileChooser fileChooser = new FileChooser();
                
                configureFileChooser(fileChooser);
                
                File file = fileChooser.showSaveDialog(dialogStage);
                

                if (file != null) {
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(image,
                            null), "png", file);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                
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
		
		// wyœwietlanie prawdopodobieñstw w zale¿noœci od liczby magnesów
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
		
		// przyciski start i example
		start.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {

				if (on3 == 1){
					series1.getData().clear();
					probup = (int)(100*(0.5*Math.pow((Math.cos((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)));
					probdown = (int)(100*(0.5*Math.pow((Math.sin((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)));
					series1.getData().add(new XYChart.Data<String, Integer>("up", probup));
					series1.getData().add(new XYChart.Data<String, Integer>("down", probdown));
				}
				else if (on2 == 1){
					series1.getData().clear();
					probup = (int)(100*(((0.5*Math.pow((Math.cos((theta2/2)*Math.PI/180)), 2)))));
					probdown = (int)(100*(((0.5*Math.pow((Math.sin((theta2/2)*Math.PI/180)), 2)))));
					series1.getData().add(new XYChart.Data<String, Integer>("up", probup));
					series1.getData().add(new XYChart.Data<String, Integer>("down", probdown));
				}
				
				upordown = upordown();
				
				startCalculation();
				
				if(upordown){
					countup++;
					countUp.setText(Integer.toString(countup));
				}
				else{
					countdown++;
					countDown.setText(Integer.toString(countdown));
				}
				
			}}
		);
		
		example.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {

				exampleCalc();

				if (on3 == 1){
					series1.getData().clear();
					probup = (int)(100*(0.5*Math.pow((Math.cos((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)));
					probdown = (int)(100*(0.5*Math.pow((Math.sin((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)));
					series1.getData().add(new XYChart.Data<String, Integer>("up", probup));
					series1.getData().add(new XYChart.Data<String, Integer>("down", probdown));
				}
				else if (on2 == 1){
					series1.getData().clear();
					probup = (int)(100*(((0.5*Math.pow((Math.cos((theta2/2)*Math.PI/180)), 2)))));
					probdown = (int)(100*(((0.5*Math.pow((Math.sin((theta2/2)*Math.PI/180)), 2)))));
					series1.getData().add(new XYChart.Data<String, Integer>("up", probup));
					series1.getData().add(new XYChart.Data<String, Integer>("down", probdown));
				}
		        
		        example.setDisable(false);	
			}}
		);
		
		reset.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e) {
				reset();
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
		states.setText(bundle.getString("states"));
		sphere.setText(bundle.getString("sphere"));
		manual.setText(bundle.getString("manual"));
		up.setText(bundle.getString("up"));
		down.setText(bundle.getString("down"));
		state.setText(bundle.getString("state"));
		sim.setText(bundle.getString("sim"));
		chart.setTitle(bundle.getString("chart"));
		detected.setText(bundle.getString("detected"));
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
			Point3D axis = new Point3D(0,0,0); 
			rotation.setAxis(axis.midpoint(0, 304, 0));
        
			if ((y<340 && y>280)&&(x<265 && x>210)&&(on2==1)){
				secondMagnet1.getTransforms().add(rotation);
				secondMagnet1.setTranslateZ(15*Math.sin(Math.toRadians(-theta2)));
				secondMagnet1.setTranslateX(15-15*Math.cos(Math.toRadians(-theta2)));
				secondMagnet2.getTransforms().add(rotation);
				secondMagnet2.setTranslateX(-15+15*Math.cos(Math.toRadians(theta2)));
				secondMagnet2.setTranslateZ(15*Math.sin(Math.toRadians(theta2)));
				secondMagnet1p.getTransforms().add(rotation);
				secondMagnet1p.setTranslateZ(15*Math.sin(Math.toRadians(-theta2)));
				secondMagnet1p.setTranslateX(15-15*Math.cos(Math.toRadians(-theta2)));
				secondMagnet2p.getTransforms().add(rotation);
				secondMagnet2p.setTranslateX(-15+15*Math.cos(Math.toRadians(theta2)));
				secondMagnet2p.setTranslateZ(15*Math.sin(Math.toRadians(theta2)));
				theta2 -= dy/180;
				if (theta2>360)
					theta2-=360;
				if (theta2<-360)
					theta2+=360;
				ang2.setText(Integer.toString((int)theta2) + " " + ResourceBundle.getBundle("application.lang.lang",lang).getString("deg"));
				repaint();
			}
			if ((y<300 && y>220)&&(x<380 && x>320)&&(on3==1)){
				thirdMagnet1.getTransforms().add(rotation);
				thirdMagnet1.setTranslateZ(15*Math.sin(Math.toRadians(-theta3)));
				thirdMagnet1.setTranslateX(15-15*Math.cos(Math.toRadians(-theta3)));
				thirdMagnet2.getTransforms().add(rotation);
				thirdMagnet2.setTranslateX(-15+15*Math.cos(Math.toRadians(theta3)));
				thirdMagnet2.setTranslateZ(15*Math.sin(Math.toRadians(theta3)));
				thirdMagnet1p.getTransforms().add(rotation);
				thirdMagnet1p.setTranslateZ(15*Math.sin(Math.toRadians(-theta3)));
				thirdMagnet1p.setTranslateX(15-15*Math.cos(Math.toRadians(-theta3)));
				thirdMagnet2p.getTransforms().add(rotation);
				thirdMagnet2p.setTranslateX(-15+15*Math.cos(Math.toRadians(theta3)));
				thirdMagnet2p.setTranslateZ(15*Math.sin(Math.toRadians(theta3)));
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
				p4.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta2/2)*Math.PI/180)), 2)))));
				p3.setText("p="+(df.format((0.5*Math.pow((Math.sin((theta2/2)*Math.PI/180)), 2)))));
			}
			else if (on2 == 0){
				p3.setText("");
				p4.setText("");
			}
			if (on3 == 1){
				p6.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)))));
				p5.setText("p="+(df.format((0.5*Math.pow((Math.sin((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)))));
			}
			else if (on3 == 0){
				p5.setText("");
				p6.setText("");
			}
       	}
	}
	
	// odœwie¿anie ekranu
	void repaint(){
		firstMagnet.setVisible(false);
		secondMagnet.setVisible(false);
		thirdMagnet.setVisible(false);
		ekran.setVisible(false);
		
		ekran.setVisible(true);
		thirdMagnet.setVisible(vM3);
		if(((theta3<-147 && theta3>-322)||(theta3<202 && theta3>35))&&vM3){
			thirdMagnet1.setVisible(!vM3);
			thirdMagnet2.setVisible(!vM3);
			thirdMagnet1p.setVisible(vM3);
			thirdMagnet2p.setVisible(vM3);
		}
		else{
			thirdMagnet2.setVisible(vM3);
			thirdMagnet1.setVisible(vM3);
			thirdMagnet2p.setVisible(!vM3);
			thirdMagnet1p.setVisible(!vM3);
			
		}
		secondMagnet.setVisible(vM2);
		if(((theta2<-147 && theta2>-322)||(theta2<202 && theta2>35))&&vM2){
			secondMagnet1.setVisible(!vM2);
			secondMagnet1.setVisible(!vM2);
			secondMagnet1p.setVisible(vM2);
			secondMagnet1p.setVisible(vM2);
		}
		else{
			secondMagnet1.setVisible(vM2);
			secondMagnet1.setVisible(vM2);
			secondMagnet1p.setVisible(!vM2);
			secondMagnet1p.setVisible(!vM2);
		}
		firstMagnet.setVisible(true);
		firstMagnet1.setVisible(true);
		firstMagnet2.setVisible(true);	
	}
	
	// inicjalizacja animacji
	void startCalculation(){
		
		start.setDisable(true);
		check2.setDisable(true);
		check3.setDisable(true);
		settingMode = false;
		
		Task<Void> task = new Task<Void>() {
			@Override public Void call() {
		    	
				charge2.setVisible(true);
				for (int i=1; i<112; i++) {
		        	
					charge2.setTranslateY(i);

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.out.println(2);
					}
				}
		        
				charge2.setVisible(false);
				charge2.setTranslateY(0);

				firstMagnet1.setVisible(false);
				firstMagnet2.setVisible(false);
				charge3.setVisible(true);
				firstMagnet1.setVisible(true);
				firstMagnet2.setVisible(true);
				       	
				for (int i=1; i<120; i++) {
					charge3.setTranslateY(i);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.out.println(3);
					}
					if(i==119){
						charge3.setVisible(false);
						charge3.setTranslateY(0);				
					}           	
				}
					
				charge4.setVisible(true);
				
				for (int i=1; i<130; i++) {
					if (isCancelled()) {
						break;
					}
					charge4.setTranslateY(i);
					if(upordown){
						charge4.setTranslateX(-0.001*i*i);
						charge4.setTranslateZ(-0.001*i*i);
					}
					else{
						charge4.setTranslateX(0.001*i*i);
						charge4.setTranslateZ(0.001*i*i);
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.out.println(4);
					}
					if(i==128){
						cancel();
						charge4.setVisible(false);
						charge4.setTranslateY(0);
						charge4.setTranslateX(0);
						charge4.setTranslateZ(0);
						start.setDisable(false);
						settingMode = true;
						check2.setDisable(false);
						check3.setDisable(false);	
					}
				}
				
		        return null;
		    }
		};
	
		new Thread(task).start();
		}
	
	// generacja przyk³adowego stanu magnesów
	private void exampleCalc(){
		
		double dy = Math.random()*360*180;
		double dy1 = Math.random()*360*180;
		Rotate rotation = new Rotate(dy/180);
		Rotate rotation1 = new Rotate(dy1/180);
		Point3D axis = new Point3D(0,0,0); 
		rotation.setAxis(axis.midpoint(0, 304, 0));
		rotation1.setAxis(axis.midpoint(0, 304, 0));
    
		if ((on2==1)){
			secondMagnet1.getTransforms().add(rotation);
			secondMagnet1.setTranslateZ(15*Math.sin(Math.toRadians(-theta2)));
			secondMagnet1.setTranslateX(15-15*Math.cos(Math.toRadians(-theta2)));
			secondMagnet2.getTransforms().add(rotation);
			secondMagnet2.setTranslateX(-15+15*Math.cos(Math.toRadians(theta2)));
			secondMagnet2.setTranslateZ(15*Math.sin(Math.toRadians(theta2)));
			secondMagnet1p.getTransforms().add(rotation);
			secondMagnet1p.setTranslateZ(15*Math.sin(Math.toRadians(-theta2)));
			secondMagnet1p.setTranslateX(15-15*Math.cos(Math.toRadians(-theta2)));
			secondMagnet2p.getTransforms().add(rotation);
			secondMagnet2p.setTranslateX(-15+15*Math.cos(Math.toRadians(theta2)));
			secondMagnet2p.setTranslateZ(15*Math.sin(Math.toRadians(theta2)));
			theta2 -= dy/180;
			if (theta2>360)
				theta2-=360;
			if (theta2<-360)
				theta2+=360;
			ang2.setText(Integer.toString((int)theta2) + " " + ResourceBundle.getBundle("application.lang.lang",lang).getString("deg"));
			repaint();
		}
		
		if ((on3==1)){
			thirdMagnet1.getTransforms().add(rotation1);
			thirdMagnet1.setTranslateZ(15*Math.sin(Math.toRadians(-theta3)));
			thirdMagnet1.setTranslateX(15-15*Math.cos(Math.toRadians(-theta3)));
			thirdMagnet2.getTransforms().add(rotation1);
			thirdMagnet2.setTranslateX(-15+15*Math.cos(Math.toRadians(theta3)));
			thirdMagnet2.setTranslateZ(15*Math.sin(Math.toRadians(theta3)));
			thirdMagnet1p.getTransforms().add(rotation1);
			thirdMagnet1p.setTranslateZ(15*Math.sin(Math.toRadians(-theta3)));
			thirdMagnet1p.setTranslateX(15-15*Math.cos(Math.toRadians(-theta3)));
			thirdMagnet2p.getTransforms().add(rotation1);
			thirdMagnet2p.setTranslateX(-15+15*Math.cos(Math.toRadians(theta3)));
			thirdMagnet2p.setTranslateZ(15*Math.sin(Math.toRadians(theta3)));
			theta3 -=dy1/180;
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
			p4.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta2/2)*Math.PI/180)), 2)))));
			p3.setText("p="+(df.format((0.5*Math.pow((Math.sin((theta2/2)*Math.PI/180)), 2)))));
		}
		else if (on2 == 0){
			p3.setText("");
			p4.setText("");
		}
		if (on3 == 1){
			p6.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)))));
			p5.setText("p="+(df.format((0.5*Math.pow((Math.sin((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)))));
		}
		else if (on3 == 0){
			p5.setText("");
			p6.setText("");
		}
		
		dy = 180;
		dy1= -180;
		rotation = new Rotate(dy/180);
		rotation1 = new Rotate(dy1/180);
		axis = new Point3D(0,0,0); 
		
		if ((on2==1)){
			secondMagnet1.getTransforms().add(rotation);
			secondMagnet1.setTranslateZ(15*Math.sin(Math.toRadians(-theta2)));
			secondMagnet1.setTranslateX(15-15*Math.cos(Math.toRadians(-theta2)));
			secondMagnet2.getTransforms().add(rotation);
			secondMagnet2.setTranslateX(-15+15*Math.cos(Math.toRadians(theta2)));
			secondMagnet2.setTranslateZ(15*Math.sin(Math.toRadians(theta2)));
			secondMagnet1p.getTransforms().add(rotation);
			secondMagnet1p.setTranslateZ(15*Math.sin(Math.toRadians(-theta2)));
			secondMagnet1p.setTranslateX(15-15*Math.cos(Math.toRadians(-theta2)));
			secondMagnet2p.getTransforms().add(rotation);
			secondMagnet2p.setTranslateX(-15+15*Math.cos(Math.toRadians(theta2)));
			secondMagnet2p.setTranslateZ(15*Math.sin(Math.toRadians(theta2)));
			theta2 -= dy/180;
			if (theta2>360)
				theta2-=360;
			if (theta2<-360)
				theta2+=360;
			ang2.setText(Integer.toString((int)theta2) + " " + ResourceBundle.getBundle("application.lang.lang",lang).getString("deg"));
			repaint();
		}
		
		if ((on3==1)){
			thirdMagnet1.getTransforms().add(rotation1);
			thirdMagnet1.setTranslateZ(15*Math.sin(Math.toRadians(-theta3)));
			thirdMagnet1.setTranslateX(15-15*Math.cos(Math.toRadians(-theta3)));
			thirdMagnet2.getTransforms().add(rotation1);
			thirdMagnet2.setTranslateX(-15+15*Math.cos(Math.toRadians(theta3)));
			thirdMagnet2.setTranslateZ(15*Math.sin(Math.toRadians(theta3)));
			thirdMagnet1p.getTransforms().add(rotation1);
			thirdMagnet1p.setTranslateZ(15*Math.sin(Math.toRadians(-theta3)));
			thirdMagnet1p.setTranslateX(15-15*Math.cos(Math.toRadians(-theta3)));
			thirdMagnet2p.getTransforms().add(rotation1);
			thirdMagnet2p.setTranslateX(-15+15*Math.cos(Math.toRadians(theta3)));
			thirdMagnet2p.setTranslateZ(15*Math.sin(Math.toRadians(theta3)));
			theta3 -=dy1/180;
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
			p4.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta2/2)*Math.PI/180)), 2)))));
			p3.setText("p="+(df.format((0.5*Math.pow((Math.sin((theta2/2)*Math.PI/180)), 2)))));
		}
		else if (on2 == 0){
			p3.setText("");
			p4.setText("");
		}
		if (on3 == 1){
			p6.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)))));
			p5.setText("p="+(df.format((0.5*Math.pow((Math.sin((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)))));
		}
		else if (on3 == 0){
			p5.setText("");
			p6.setText("");
		}
   			
	}

	// filechooser dla zapisywania wykresu do pliku
	private static void configureFileChooser(
	        final FileChooser fileChooser) {      
	            fileChooser.setTitle("Save Image");
	            fileChooser.setInitialDirectory(
	                new File(System.getProperty("user.home"))
	            );                 
	            fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("All Images", "*.*"),
	                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
	                new FileChooser.ExtensionFilter("PNG", "*.png")
	            );
	    }	
	
	// generowanie wyniku eksperymentu na podstawie rozk³adu prawdopodobieñstwa
	private boolean upordown(){
		int out = 0;
		
		while (out == 0){
		int[] dist = new int[50];
		
		for (int i = 0; i < probup; i++)
			dist[i] = 1;
		
		for (int i = probup; i < probup+probdown; i++)
			dist[i] = -1;
		
		for (int i = probup+probdown; i < 50; i++)
			dist[i] = 0;
		
		Random generator = new Random();
		
		int x = (int)(generator.nextDouble()*50);

		out = dist[x];
		}
		
		if (out == 1)
			return true;
		return false;
	}
	
	// resetowanie ustawienia magnesów
	private void reset(){
		countUp.setText("0");
		countup = 0;
		countDown.setText("0");
		countdown = 0;
		Rotate rotation = new Rotate(-135);
		Point3D axis = new Point3D(0,0,0); 
		rotation.setAxis(axis.midpoint(0, 304, 0));
		
		secondMagnet1.getTransforms().clear();
		secondMagnet1.setTranslateZ(0);
		secondMagnet1.setTranslateX(0);
		secondMagnet2.getTransforms().clear();
		secondMagnet2.setTranslateX(0);
		secondMagnet2.setTranslateZ(0);
		secondMagnet1p.getTransforms().clear();
		secondMagnet1p.setTranslateZ(0);
		secondMagnet1p.setTranslateX(0);
		secondMagnet1p.getTransforms().clear();
		secondMagnet2p.setTranslateX(0);
		secondMagnet2p.setTranslateZ(0);
		thirdMagnet1.getTransforms().clear();
		thirdMagnet1.setTranslateZ(0);
		thirdMagnet1.setTranslateX(0);
		thirdMagnet2.getTransforms().clear();
		thirdMagnet2.setTranslateX(0);
		thirdMagnet2.setTranslateZ(0);
		thirdMagnet1p.getTransforms().clear();
		thirdMagnet1p.setTranslateZ(0);
		thirdMagnet1p.setTranslateX(0);
		thirdMagnet2p.getTransforms().clear();
		thirdMagnet2p.setTranslateX(0);
		thirdMagnet2p.setTranslateZ(0);
		theta2 = 0;
		theta3 = 0;
		p1.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta1/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta1/2)*Math.PI/180)),2)))));
		p2.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta1/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta1/2)*Math.PI/180)),2)))));
		p4.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta2/2)*Math.PI/180)), 2)))));
		p3.setText("p="+(df.format((0.5*Math.pow((Math.sin((theta2/2)*Math.PI/180)), 2)))));
		p6.setText("p="+(df.format((0.5*Math.pow((Math.cos((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)))));
		p5.setText("p="+(df.format((0.5*Math.pow((Math.sin((theta3/2)*Math.PI/180)), 2))*(Math.pow((Math.cos((theta2/2)*Math.PI/180)),2)))));
		ang2.setText(Integer.toString((int)theta2) + " " + ResourceBundle.getBundle("application.lang.lang",lang).getString("deg"));
		ang3.setText(Integer.toString((int)theta3) + " " + ResourceBundle.getBundle("application.lang.lang",lang).getString("deg"));
		repaint();
	}
}