package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.stage.Stage;
import javafx.util.Pair;
import view.View;

public class Wrapper {

	private static Wrapper instance;
	private List<Pair<Model,View>> listOfWindows=new ArrayList<Pair<Model,View>>();
//	private Wrapper()
//	{
//		
//	}

	public static synchronized Wrapper getInstance()
	{
		if (instance == null)
			instance = new Wrapper();

		return instance;
	}
	
	
	public void makeNewWindow(Model myModel, View myView) {
		myView.addModelListeners(myModel);
		myView.start(new Stage());
		listOfWindows.add(new Pair<Model,View>(myModel,myView));
	}
}
