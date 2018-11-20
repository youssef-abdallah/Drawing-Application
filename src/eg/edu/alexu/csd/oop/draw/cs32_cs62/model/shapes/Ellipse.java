package eg.edu.alexu.csd.oop.draw.cs32_cs62.model.shapes;

import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;

import java.awt.Graphics2D;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.MyShape;

public class Ellipse extends MyShape {
	public Ellipse() {
		super();
	}
	HashMap<String, Double> properties = (HashMap<String, Double>)this.getProperties();
	
	public Ellipse(Double x, Double y, Double height, Double width) {
		Point position = new Point();
		position.x = x.intValue();
		position.y = y.intValue();
		properties.put("x", position.getX());
		properties.put("y", position.getY());
		properties.put("height", height);
		properties.put("width", width);
		this.setPosition(position);
		
	}
	
	@Override
	public void draw(Object canvas) {
		Graphics2D canvas2 = (Graphics2D)canvas;
		canvas2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		canvas2.setColor(this.getColor());
		canvas2.draw(new Ellipse2D.Double(this.getPosition().getX(), this.getPosition().getY(),
				properties.get("width"), properties.get("height")));
		canvas2.setColor(this.getFillColor());
		canvas2.fill(new Ellipse2D.Double(this.getPosition().getX(), this.getPosition().getY(),
				properties.get("width"), properties.get("height")));

	}
	
	public Object clone() throws CloneNotSupportedException{
		 Shape copy = new Ellipse(this.getPosition().getX(), this.getPosition().getY(),
				 properties.get("height"), properties.get("width"));
		 copy.setColor(this.getColor());
		 copy.setFillColor(this.getFillColor());
		 return copy;
	}
	


}
