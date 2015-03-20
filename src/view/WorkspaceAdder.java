// This entire file is part of my masterpiece.
// COSETTE GOLDSTEIN

package view;

import application.Model;
import application.Wrapper;
import javafx.scene.control.Button;

public class WorkspaceAdder {

	protected Button addButton() {
		Button addWorkspace=new Button("Add New Workspace");
		Model m=new Model(View.SIZE_OF_TURTLE_DISPLAY[0]/2,View.SIZE_OF_TURTLE_DISPLAY[1]/2);
		addWorkspace.setOnAction(w-> Wrapper.getInstance().makeNewWindow(m,new View(m)));
		return addWorkspace;
	}
}
