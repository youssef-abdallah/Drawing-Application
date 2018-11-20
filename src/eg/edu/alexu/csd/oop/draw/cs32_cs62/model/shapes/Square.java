package eg.edu.alexu.csd.oop.draw.cs32_cs62.model.shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashMap;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Square extends Rectangle {
	public Square() {
		super();
	}

	HashMap<String, Double> properties = (HashMap<String, Double>)this.getProperties();
	
	public Square(Double x, Double y, Double height, Double width) {
		super(x, y, height, width);
		// TODO Auto-generated constructor stub
		Point position = new Point();
		position.x = x.intValue();
		position.y = y.intValue();
		properties.put("x", position.getX());
		properties.put("y", position.getY());
		properties.put("height", height);
		properties.put("width", height);
		this.setPosition(position);
	}
	

	@Override
	public void draw(Object canvas) {
		// TODO Auto-generated method stub
		Graphics2D canvas2 = (Graphics2D)canvas;
		canvas2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		canvas2.setColor(this.getColor());
		canvas2.drawRect(this.getPosition().x, this.getPosition().y
				, properties.get("height").intValue(), properties.get("height").intValue());
		
		canvas2.setColor(this.getFillColor());
		canvas2.fillRect(this.getPosition().x, this.getPosition().y
				, properties.get("height").intValue(), properties.get("height").intValue());
		
	}
	
	@Override
    public Object clone() throws CloneNotSupportedException{
		 Shape copy = new Square(this.getPosition().getX(),this.getPosition().getY(), 
				 properties.get("height"), properties.get("width"));
		 copy.setColor(this.getColor());
		 copy.setFillColor(this.getFillColor());
		 return copy;
	}

	
	
	
	
	


}
