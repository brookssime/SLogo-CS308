
package view;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class ButtonBar {
	
	private ColorChooser colz;
	private ImageChooser img;
	private LanguageChooser lang;
	private Display dis;
	
	public ButtonBar(ColorChooser c,LanguageChooser l) {
		colz=c;
		lang=l;
		img=new ImageChooser(colz.getDisplay());
		dis=colz.getDisplay();
		//lang=new LanguageChooser();
	}

	private Button makeButton(String name) {

		Button btn = new Button(name);
		btn.setScaleX(2);
		btn.setScaleY(2);
		return btn;
	}
	protected VBox makeButtonBar() {
		VBox vbox = new VBox(30);
		Button setLang=makeButton("Set Lang");
		ErrorDisplay err=new ErrorDisplay();
		Button chooseColor=makeButton("Set Colors");
		chooseColor.setOnAction(c-> colz.makeColorChooserPopUp(new Stage()));
		Button setImage=makeButton("Set Image");
		setImage.setOnAction(s-> img.start(new Stage()));
		//setLang.setOnAction(l-> lang.setLanguage(new Stage()));
		setLang.setOnAction(l-> {dis.updateTurtleLocation(dis.getTurtX()+50, dis.getTurtY()+50); dis.drawLines(dis.getTurtX(), dis.getTurtY(), dis.getTurtX()+50, dis.getTurtY()+50);});
//		setLang.setOnAction(s-> {Stage newStage=new Stage();
//		newStage.setScene(err.display("YO ERROR"));
//		newStage.show();});
		vbox.getChildren().addAll(setLang,chooseColor,setImage);
		vbox.setAlignment(Pos.CENTER);
		vbox.setTranslateX(675);
		vbox.setTranslateY(550);
		return vbox;
	}
}
