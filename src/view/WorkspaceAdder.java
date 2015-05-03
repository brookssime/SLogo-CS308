package view;

import application.Model;
import application.Wrapper;
import javafx.scene.control.Button;

public class WorkspaceAdder {

	protected Button addButton() {
		Button addWorkspace=new Button("Add New Workspace");
		Model m=new Model(374/2,374/2);
		addWorkspace.setOnAction(w-> {Wrapper.getInstance().makeNewWindow(m,new View(m));});
		return addWorkspace;
	}
}
