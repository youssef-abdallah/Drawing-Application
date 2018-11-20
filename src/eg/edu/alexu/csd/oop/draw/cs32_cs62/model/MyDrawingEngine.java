package eg.edu.alexu.csd.oop.draw.cs32_cs62.model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.shapes.*;

public class MyDrawingEngine implements DrawingEngine {
	private final int UNDOSTEPS = 20;
	private List<Class<? extends Shape>> supportedShapes;
	private ArrayList<Shape> shapes;
	private Deque<Shape> undoShapes;
	private Deque<String> undoOperations;
	private Stack<Shape> redoStack;
	private Stack<String> redoOperations;
	private Deque<Shape> newShapes;
	private static MyDrawingEngine uniqueInstance = new MyDrawingEngine();
	private boolean redoing = false;
	
	private MyDrawingEngine() {
		shapes = new ArrayList<Shape>();
		undoShapes = new LinkedList<>();
		redoStack = new Stack<>();
		undoOperations = new LinkedList<>();
		redoOperations = new Stack<String>();
		newShapes = new LinkedList<>();
		supportedShapes = new ArrayList<>();
		supportedShapes.add(Line.class);
		supportedShapes.add(Rectangle.class);
		supportedShapes.add(Square.class);
		supportedShapes.add(Circle.class);
		supportedShapes.add(Ellipse.class);
		supportedShapes.add(Triangle.class);
	}
	
	private void clearUndo() {
		undoShapes.clear();
		undoOperations.clear();
		redoStack.clear();
		redoOperations.clear();
		newShapes.clear();
	}
	
	private void clearEverything() {
		clearUndo();
		supportedShapes.clear();
		shapes.clear();
	}
	
	public static MyDrawingEngine getInstance() {
		return uniqueInstance;
	}
	@Override
	public void refresh(Object canvas) {
		for (Shape s : shapes) {
			s.draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		if (!redoing) {
			redoStack.clear();
		}
		shapes.add(shape);
		if (undoShapes.size() < UNDOSTEPS) {
			undoShapes.addLast(shape);
			undoOperations.addLast("add");
		} else {
			undoShapes.removeFirst();
			undoShapes.addLast(shape);
			undoOperations.removeFirst();
			undoOperations.addLast("add");
		}

	}

	@Override
	public void removeShape(Shape shape) {
		if (undoShapes.size() < UNDOSTEPS) {
			undoShapes.addLast(shape);
			undoOperations.addLast("remove");
		} else {
			undoShapes.removeFirst();
			undoShapes.addLast(shape);
			undoOperations.removeFirst();
			undoOperations.addLast("remove");
		}
		shapes.remove(shape);
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		shapes.remove(oldShape);
		shapes.add(newShape);
		//addShape(newShape);
		if (undoShapes.size() < UNDOSTEPS) {
			newShapes.addLast(newShape);
			undoShapes.addLast(oldShape);
			undoOperations.addLast("update");
		} else {
			newShapes.removeFirst();
			newShapes.addLast(newShape);
			undoShapes.removeFirst();
			undoShapes.addLast(oldShape);
			undoOperations.removeFirst();
			undoOperations.addLast("update");
		}

	}
	
	@SuppressWarnings("unchecked")
	public void loadPlugin(String pathToJar) {
		try(JarFile jarFile = new JarFile(pathToJar);) {
		Enumeration<JarEntry> e = jarFile.entries();
		URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);

		while (e.hasMoreElements()) {
		    JarEntry je = e.nextElement();
		    if(je.isDirectory() || !je.getName().endsWith(".class")){
		        continue;
		    }
		    // -6 because of .class
		    String className = je.getName().substring(0,je.getName().length() - 6);
		    className = className.replace('/', '.');
		    Class<?> c = cl.loadClass(className);
		    if (c.newInstance() instanceof Shape) {
		    	supportedShapes.add((Class<? extends Shape>) c);
		    }

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Shape[] getShapes() {
		return shapes.toArray(new Shape[shapes.size()]);
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		loadPlugin("RoundRectangle.jar");
		return supportedShapes;
	}

	@Override
	public void undo() {
		if (undoOperations.size() == 0) {
			return;
		}
		String lastAction = undoOperations.getLast();
		undoOperations.removeLast();
		redoOperations.push(lastAction);
		if (lastAction.equals("remove")) {
			Shape lastDrawnShape = undoShapes.getLast();
			shapes.add(lastDrawnShape);
			redoStack.push(lastDrawnShape);
			undoShapes.removeLast();
		} else if (lastAction.equals("add") ){
			Shape lastDrawnShape = undoShapes.getLast();
			undoShapes.removeLast();
			shapes.remove(lastDrawnShape);
			redoStack.push(lastDrawnShape);
		} else if (lastAction.equals("update")) {
			Shape lastDrawnShape = newShapes.getLast();
			newShapes.removeLast();
			shapes.remove(lastDrawnShape);
			Shape oldShape = undoShapes.getLast();
			undoShapes.removeLast();
			shapes.add(oldShape);
			redoStack.push(lastDrawnShape);
			redoStack.push(oldShape);
		}
		/*Shape lastDrawnShape = undoShapes.peek();
		redoStack.push(lastDrawnShape);
		removeShape(lastDrawnShape);*/
	}

	@Override
	public void redo() {
		if (redoOperations.size() == 0)
			return;
		redoing = true;
		String lastAction = redoOperations.pop();
		undoOperations.push(lastAction);
		if (lastAction.equals("remove")) {
			Shape lastDrawnShape = redoStack.pop();
			shapes.remove(lastDrawnShape);
			undoShapes.addLast(lastDrawnShape);
		} else if (lastAction.equals("add")) {
			Shape lastDrawnShape = redoStack.pop();
			shapes.add(lastDrawnShape);
			undoShapes.addLast(lastDrawnShape);
		} else {
			Shape oldShape = redoStack.pop();
			Shape lastDrawnShape = redoStack.pop();
			shapes.remove(oldShape);
			shapes.add(lastDrawnShape);
			undoShapes.addLast(oldShape);
			newShapes.addLast(lastDrawnShape);
		}
		redoing = false;
	}

	@Override
	public void save(String path) {
		if (path.substring(path.length() - 4).equalsIgnoreCase(".xml")) {
			saveXml(path);
		} else if (path.substring(path.length() - 5).equalsIgnoreCase(".json")) {
			saveJson(path);
		}

	}

	@Override
	public void load(String path) {
		clearEverything();
		if (path.substring(path.length() - 4).equalsIgnoreCase(".xml")) {
			loadXml(path);
		} else if (path.substring(path.length() - 5).equalsIgnoreCase(".json")) {
			loadJson(path);
		}
	}
	
	private void saveXml(String path) {
		HashMap<String, Integer> shapeNumber = new HashMap<>();
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			XmlWriter writer = new XmlWriter(bw);
			writer.addNode("shapes");
			for (Shape s : shapes) {
				String shapeName = s.getClass().getSimpleName();
				if (shapeNumber.containsKey(shapeName)) {
					shapeNumber.put(shapeName, shapeNumber.get(shapeName) + 1);
				} else {
					shapeNumber.put(shapeName, 1);
				}
				writer.addNode(shapeName + shapeNumber.get(shapeName));
				Map<String, Double> properties = s.getProperties();
				if (properties != null) {
					for (Map.Entry<String, Double> property : properties.entrySet()) {
						writer.addTextNode(property.getKey(), "" + property.getValue());
					}
				} else {
					writer.addTextNode("dummy", "0");
				}
				writer.pop();
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadXml(String path) {
		ShapesFactory shapesFactory = new ShapesFactory();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(path);
			NodeList shapesList = doc.getElementsByTagName("shapes");
			for (int i = 0; i < shapesList.getLength(); i++) {
				Node s = shapesList.item(i);
				if (s.getNodeType() == Node.ELEMENT_NODE) {
					Element shapes = (Element) s;
					NodeList shapeList = shapes.getChildNodes();
					for (int j = 0; j < shapeList.getLength(); j++) {
						HashMap<String, Double> loadedProperties = new HashMap<String, Double>();
						Node p = shapeList.item(j);
						if (p.getNodeType() == Node.ELEMENT_NODE) {
							Element shape = (Element) p;
							NodeList propList = shape.getChildNodes();
							for (int k = 0; k < propList.getLength(); k++) {
								Node prop = propList.item(k);
								if (prop.getNodeType() == Node.ELEMENT_NODE) {
									Element property = (Element) prop;
									loadedProperties.put(property.getNodeName(), Double.valueOf(property.getTextContent()));
								}
							}
							if (loadedProperties.containsKey("x") && loadedProperties.containsKey("y")
									&& loadedProperties.containsKey("height") && loadedProperties.containsKey("width")) {
									String shapeToCreate = "";
									for (int x = 0; x < p.getNodeName().length(); x++) {
										if (Character.isAlphabetic(p.getNodeName().charAt(x))) {
											shapeToCreate += p.getNodeName().charAt(x);
										} else {
											break;
										}
									}
									Shape loadedShape = shapesFactory.ShapesFactory(shapeToCreate, loadedProperties.get("x"),
									loadedProperties.get("y"),
									loadedProperties.get("height"), loadedProperties.get("width"));
									loadedShape.setProperties(loadedProperties);
									loadedShape.setColor(new Color(loadedProperties.get("strokeColor").intValue()));
									loadedShape.setFillColor(new Color(loadedProperties.get("fillColor").intValue()));
									addShape(loadedShape);
							} else {
								Shape loadedShape = new DummyShape();
								loadedShape.setProperties(loadedProperties);
								addShape(loadedShape);
							}
						}
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clearUndo();
	}
	
	private void saveJson(String path) {
		HashMap<String, Integer> shapeNumber = new HashMap<>();
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			JsonWriter writer = new JsonWriter(bw);
			writer.startFile();
			for (Shape s : shapes) {
				String shapeName = s.getClass().getSimpleName();
				if (shapeNumber.containsKey(shapeName)) {
					shapeNumber.put(shapeName, shapeNumber.get(shapeName) + 1);
				} else {
					shapeNumber.put(shapeName, 1);
				}
				writer.startElement(shapeName + shapeNumber.get(shapeName));
				Map<String, Double> properties = s.getProperties();
				if (properties != null) {
					for (Map.Entry<String, Double> property : properties.entrySet()) {
						writer.addElement(property.getKey(), "" + property.getValue());
					}
				} else {
					//writer.addTextNode("dummy", "0");
				}
				writer.closeElement();
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadJson(String path) {
		ShapesFactory shapesFactory = new ShapesFactory();
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			JsonReader reader = new JsonReader(br);
			reader.pass2Lines();
			int endFile = 0;
			while(endFile == 0) {
				HashMap<String, Double> loadedProperties = new HashMap<String, Double>();
				String name = reader.readName();
				if(name == "fileEnded") {
					endFile = 1;
					continue;
				}
				loadedProperties = reader.readProperties();
				name = name.replaceAll("[^a-zA-Z]+", "");
				if(loadedProperties.containsKey("x") && loadedProperties.containsKey("y")
									&& loadedProperties.containsKey("height") && loadedProperties.containsKey("width")) {
				Shape loadedShape = shapesFactory.ShapesFactory(name, loadedProperties.get("x"),
						loadedProperties.get("y"),
						loadedProperties.get("height"), loadedProperties.get("width"));
						loadedShape.setProperties(loadedProperties);
						loadedShape.setColor(new Color(loadedProperties.get("strokeColor").intValue()));
						loadedShape.setFillColor(new Color(loadedProperties.get("fillColor").intValue()));
						addShape(loadedShape);
				}
				else {
					Shape loadedShape = new DummyShape();
					loadedShape.setProperties(loadedProperties);
					addShape(loadedShape);
				}
			}
			
		} catch(IOException e) {
		}

		
	}

}