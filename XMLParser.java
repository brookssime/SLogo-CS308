import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser{
	
	private Document myDoc;
	private Element myRoot;
	
	/**
	 * Instantiates the XMLParser class, which reads from and writes to XML files
	 *
	 * @param  path  the path from which to read the XML
	 */
	public XMLParser(String path) throws ParserConfigurationException,
			SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		myDoc = builder.parse(new File(path));
		myRoot = pullElement(myDoc.getChildNodes(),0);
	}
	
	/**
	 * Using the original parsed xml as a template, this writes a new xml file with parameter changes specified in p
	 *
	 * @param  p  a Parameters object, which contains parameter values to alter
	 */
	public void writeXML(Parameters p) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		Map<String, Object> params = p.getParameters();
		for (String tag: params.keySet()){
			String value;
			if(tag.equals(Parameters.GRID)){
				value = gridToString((int[][])params.get(tag));
				setValue(myRoot, tag, value);
			}
			else if(tag.equals(Parameters.NAME)){
				value = p.getShape() + " " + params.get(tag).toString() + " " + getSimType();
				setValue(myRoot, Parameters.SIM_TYPE, value);
			}
			else if(tag.equals(Parameters.COLORS)){
				@SuppressWarnings("unchecked")
				Map<Integer,String> map = (Map<Integer, String>)params.get(tag);
				for (int colorTag: map.keySet()){
					setSpec(Parameters.STATES, Parameters.COLORS, "" + colorTag, map.get(colorTag).toString());
				}
			}
			else{
				value = params.get(tag).toString();
				setSpec(Parameters.SPECS, Parameters.DATA, tag, value);
			}
		}
		
		Transformer tform = TransformerFactory.newInstance().newTransformer();
		tform.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(myDoc);
        String filename = "src/" + getSimType().replaceAll(" ", "") + ".xml";
        StreamResult console = new StreamResult(new File(filename));
        tform.transform(source, console);
	}

	/**
	 * sets a parameter contained within a field by matching the element ID with tag
	 *
	 * @param  field the field name
	 * @param  param the parameter name
	 * @param  tag   the id to check
	 * @param  value the value to set
	 */
	private void setSpec(String field, String param, String tag, String value) {
		NodeList stateList = pullField(myRoot,field);
		for (int i = 0; i < stateList.getLength(); i++){
			Node node = stateList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE){
				Element e = (Element) node;
				String id = node.getAttributes().getNamedItem(Parameters.ID).getNodeValue();
				if (id.equals(tag)){
					setValue(e, param, value);
				}
			}
		}
	}
	
	/**
	 * converts a grid to a String for writing to file
	 *
	 * @param  grid an int[][] specifying states at coordinates
	 * @return a String representation of the grid
	 */
	private String gridToString(int[][] grid) {
		String gridString = "\n";
		if (grid.length > 0){
			for (int i = 0; i < grid[0].length; i++){
				String gridRow = "";
				for (int j = 0; j < grid.length; j++){
					gridRow = gridRow + grid[j][i] + " "; 
				}
				gridRow = gridRow.trim() + "\n";
				gridString = gridString + gridRow;
			}
		}
		return gridString + "#";
	}
	
	/**
	 * sets a free parameter by matching the parameter name with tag
	 *
	 * @param  e     the root element 
	 * @param  tag   the id to check
	 * @param  value the value to set
	 */
	private void setValue(Element e, String tag, String value){
		pullField(e,tag).item(0).setNodeValue(value);
	}
	
	/**
	 * pulls a free parameter by matching the parameter name with tag
	 *
	 * @param  e     the root element 
	 * @param  tag   the id to check
	 * @param  value the value to set
	 * @return the value of the parameter
	 */
	private String pullValue(Element e, String tag){
		return pullField(e,tag).item(0).getNodeValue();
	}
	
	/**
	 * gets a Node at index and casts to Element for use in queries
	 *
	 * @param  n     NodeList of child elements 
	 * @param  index index in the NodeList
	 * @return Element casted from Node
	 */
	private Element pullElement(NodeList n, int index){
		return (Element) n.item(index);
	}
	
	/**
	 * gets a NodeList of children of the field named tag
	 *
	 * @param  e     root element
	 * @param  tag   name of the field
	 * @return NodeList
	 */
	private NodeList pullField(Element e, String tag){
		return e.getElementsByTagName(tag).item(0).getChildNodes();
	}
	
	/**
	 * gets a SimSpec and returns it as an int
	 *
	 * @param  tag  name of SimSpec
	 * @return desired int value
	 */
	public int getIntSpec(String tag){
		System.out.println(tag + " " + Integer.parseInt(getSimSpec(tag)));
		return Integer.parseInt(getSimSpec(tag));
	}
	
	/**
	 * gets a SimSpec and returns it as an boolean
	 *
	 * @param  tag  name of SimSpec
	 * @return desired boolean value
	 */
	public boolean getBoolSpec(String tag){
		return Boolean.parseBoolean(getSimSpec(tag));
	}
	
	/**
	 * gets a SimSpec, a generic parameter in the SimSpecs field, and returns it
	 *
	 * @param  tag  name of SimSpec
	 * @return SimSpec
	 */
	public String getSimSpec(String tag){
		NodeList stateList = pullField(myRoot,Parameters.SPECS);
		for (int i = 0; i < stateList.getLength(); i++){
			Node node = stateList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE){
				Element e = (Element) node;
				String id = node.getAttributes().getNamedItem(Parameters.ID).getNodeValue();
				if (id.equals(tag)){
					return pullValue(e, Parameters.DATA);
				}
			}
		}
		return null;
	}
	
	/**
	 * gets the type of simulation
	 *
	 * @return type of simulation
	 */
	public String getSimType(){
		return pullValue(myRoot, Parameters.SIM_TYPE);
	}

	/**
	 * gets a map of state indices and maps them to their respective State objects
	 *
	 * @return map
	 */
	public HashMap<Integer, State> getStateMap(){
		HashMap<Integer, State> map = new HashMap<>();
		NodeList stateList = pullField(myRoot,Parameters.STATES);
		for (int i = 0; i < stateList.getLength(); i++){
			Node node = stateList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE){
				Element e = (Element) node;
				Integer id = Integer.parseInt(node.getAttributes().getNamedItem(Parameters.ID).getNodeValue());
				String name = pullValue(e, Parameters.NAME);
				Color color = Color.web(pullValue(e, "Color"));
				map.put(id, new State(name, color));
			}
		}
		return map;
	}
	
	public String getGridType(){
		return pullValue(myRoot, Parameters.GRID_TYPE);
	}
	
	/**
	 * gets a grid of state locations
	 *
	 * @return grid
	 */
	public int[][] getGrid(){
		String[] rows = pullValue(myRoot,Parameters.GRID).split("\n");
		int height = rows.length - 2;
		int width = rows[1].split(" ").length;
		int[][] grid = new int[width][height];
		for (int i = 1; i <= height; i++){
			String[] stateStrings = rows[i].trim().split(" ");
			int[] stateInts = new int[width];
			for (int j = 0; j < width; j++){
				stateInts[j] = Integer.parseInt(stateStrings[j]);
			}
			grid[i-1] = stateInts;
		}
		return grid;
	}
	
	/**
	 * gets an array of integers representing states that the user can interact with
	 *
	 * @return array
	 */
	public int[] getInteractStates(){
		String[] interactStates = getSimSpec("InteractStates").split(" ");
		int[] states = new int[interactStates.length];
		for(int i = 0; i < interactStates.length; i++){
			for(int j = 0; j < interactStates[i].length(); j++){
				try{
					states[i] = Integer.parseInt(""+interactStates[i].charAt(j));
				}
				catch (NumberFormatException n){}
			}
		}
		return states;
	}
	
	public boolean getCornerNeighbors(){
		return getBoolSpec("CornerNeighbors");
	}
	
	public boolean getWrapAround(){
		return getBoolSpec("WrapAround");
	}
	
	public int getNeighborhoodSize(){
		return getIntSpec("NeighborhoodSize");
	}
	
	public int getNumCellProperties(){
		return getIntSpec("NumCellProperties");
	}
}
