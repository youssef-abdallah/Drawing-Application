package eg.edu.alexu.csd.oop.draw.cs32_cs62.model;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public abstract class MyShape implements Shape {
	
	private Map<String, Double> properties;
	private Color strokeColor;
	private Color fillColor;
	private Point position;
	
	public MyShape() {
		strokeColor = Color.BLACK;
		fillColor = Color.WHITE;
		properties = new HashMap<String, Double>();
		properties.put("strokeColor", (double)strokeColor.getRGB());
		properties.put("fillColor", (double)fillColor.getRGB());

	}

	@Override
	public void setPosition(Object position) {
		// TODO Auto-generated method stub
		this.position = (Point) position;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		this.properties = properties;

	}

	@Override
	public Map<String, Double> getProperties() {
		return properties;
	}

	@Override
	public void setColor(Object color) {
		// TODO Auto-generated method stub
		strokeColor = (Color) color;
		properties.put("strokeColor", (double)strokeColor.getRGB());

	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return strokeColor;
	}

	@Override
	public void setFillColor(Object color) {
		// TODO Auto-generated method stub
		fillColor = (Color) color;
		properties.put("fillColor", (double)fillColor.getRGB());
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return fillColor;
	}

	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
	}
	
	 public Object clone() throws CloneNotSupportedException{
		 
		 return null;
	}

}
