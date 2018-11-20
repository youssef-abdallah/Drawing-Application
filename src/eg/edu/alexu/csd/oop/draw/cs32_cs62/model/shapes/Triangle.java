package eg.edu.alexu.csd.oop.draw.cs32_cs62.model.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashMap;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.MyShape;

public class Triangle extends MyShape {
	public Triangle() {
		super();
	}
	
	HashMap<String, Double> properties = (HashMap<String, Double>)this.getProperties();
	public Triangle(Double x1, Double y1, Double height, Double width) {
		// TODO Auto-generated constructor stub
		Double xPoints[] = new Double[3];
		Double yPoints[] = new Double[3];
		xPoints[0] = x1;
		yPoints[0] = y1 + height;
		xPoints[1] = x1 + width;
		yPoints[1] = y1 + height;
		xPoints[2] = (xPoints[0] + xPoints[1]) / 2;
		yPoints[2] = y1;
	
		properties.put("x", x1);
		properties.put("y", y1);
		properties.put("x1", xPoints[0]);
		properties.put("y1", yPoints[0]);
		properties.put("x2", xPoints[1]);
		properties.put("y2", yPoints[1]);
		properties.put("x3", xPoints[2]);
		properties.put("y3", yPoints[2]);
		properties.put("height", height);
		properties.put("width", width);
		Point p = new Point(xPoints[0].intValue(),yPoints[0].intValue());
		this.setPosition(p);
	}
	
	@Override
	public void draw(Object canvas) {
		Graphics2D canvas2 = (Graphics2D)canvas;
		canvas2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		canvas2.setColor(this.getColor());
		int xPoints[] = {getPosition().x,
				properties.get("x2").intValue(), properties.get("x3").intValue()};
		int yPoints[] = {getPosition().y, 
				properties.get("y2").intValue(), properties.get("y3").intValue()};
		canvas2.drawPolygon(xPoints, yPoints, 3);
		canvas2.setColor(this.getFillColor());
		canvas2.fillPolygon(xPoints, yPoints, 3);
	}
	
	@Override
    public Object clone() throws CloneNotSupportedException{
		 Shape copy = new Triangle(properties.get("x"), properties.get("y3"),
				 properties.get("height"), properties.get("width"));
		 
		 copy.setColor(this.getColor());
		 copy.setFillColor(this.getFillColor());
		 return copy;
	}

}
