package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class Chart extends Application {
 
    @Override public void start(Stage stage) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(10,100,10);
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);  
 
        XYChart.Data up = new XYChart.Data("up", 60);
        XYChart.Data down = new XYChart.Data("down", 40);
        
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(up);
        series1.getData().add(down);
        // czysci: series1.getData().clear();
        bc.getData().addAll(series1);
        bc.setLegendVisible(false);
        
        Scene scene  = new Scene(bc,300,300);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}