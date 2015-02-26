package view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageChooser {

	private Desktop desktop = Desktop.getDesktop();
	private Display display;
	public ImageChooser(Display d) {
		display=d;
	}
	
	public void start(final Stage stage) {
		final FileChooser fileChooser = new FileChooser();
		
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			openFile(file);
		}


		final GridPane inputGridPane = new GridPane();

		final Pane rootGroup = new VBox(12);
		rootGroup.getChildren().addAll(inputGridPane);
		rootGroup.setPadding(new Insets(12, 12, 12, 12));
	}

	private void openFile(File file) {
				ImageView img=display.updateTurtleImage(); 
				try {
					Image image = SwingFXUtils.toFXImage(ImageIO.read(file), null);
	                img.setFitHeight(display.SIZE_OF_TURTLE);
	                img.setFitWidth(display.SIZE_OF_TURTLE);
	                img.setImage(image);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 
	}
}


