package view;

import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import application.Turtle;
import application.TurtleList;

public class TurtleViewer {
    
    private Display myDisplay;
    private Stage myStage = new Stage();
    private GridPane turtles = new GridPane();
    private ImageChooser myChooser = new ImageChooser(myDisplay);
    
    public TurtleViewer (Display display){
        myDisplay = display;
        
}
    public void createTurtleViewer(){
        Map<Turtle, ImageView> myTurtles = myDisplay.getMap();
        int count = 0;
        for (Turtle t: myTurtles.keySet()){
            turtles.add(createTurtleButton(myTurtles.get(t)), count, 0);
            count++;
        }
    }
    
    public Button createTurtleButton(ImageView image){
       Button turtleImage = new Button("", image);
       turtleImage.setOnAction(e -> myChooser.start());
       return turtleImage;
    }
    
    
    protected void ViewOfTurtle(){
        myStage.setTitle("Turtle Viewer");
        GridPane root = turtles;
        Scene scene = new Scene(root);
        myStage.setScene(scene);
        myStage.show();
    }
    
    protected void init(){
        createTurtleViewer();
        ViewOfTurtle();
    }
    
    protected Button viewer(){
        Button b = new Button("Turtle Viewer");
        b.setOnAction(n -> {
                init();
                
        });
        return b;
    }
}
