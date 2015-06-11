package application;

import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;

public class MagnetsScene extends SubScene {

	public MagnetsScene(AnchorPane arg0) {
		super(arg0,800,600,true,SceneAntialiasing.BALANCED);
		Group objects = new Group();
		Box firstMagnet1 = new Box(25,100,50);
		Box firstMagnet2 = new Box(25,100,50);
		
		objects.getChildren().add(firstMagnet1);
		objects.getChildren().add(firstMagnet2);
		
		setFill(Color.WHITE);
		setCamera(new ParallelCamera());
		
		arg0.getChildren().add(objects);
	}

}
