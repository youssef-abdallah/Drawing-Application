package eg.edu.alexu.csd.oop.draw.cs32_cs62.model.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashMap;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.MyShape;

public class Line extends MyShape{
	public Line() {
		super();
	}
	HashMap<String, Double> properties = (HashMap<String, Double>)this.getProperties();

	
	public Line(Double x, Double y, Double height, Double width) {
		properties.put("x", x);
		properties.put("y", y);
		properties.put("height", height);
		properties.put("width", width);
		Point p = new Point();
		p.x = x.intValue();
		p.y = y.intValue();
		this.setPosition(p);

	}
	
	@Override
	public void draw(Object canvas) {
		Graphics2D canvas2 = (Graphics2D)canvas;
		canvas2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		canvas2.setColor(this.getColor());
		canvas2.drawLine(getPosition().x, getPosition().y,
				properties.get("height").intValue(), properties.get("width").intValue());
		// no fill color for Line
	}
	
	@Override
    public Object clone() throws CloneNotSupportedException{
		 Shape copy = new Line(properties.get("x1"), properties.get("y1"),
					properties.get("x2"), properties.get("y2"));
		 copy.setColor(this.getColor());
		 //copy.setFillColor(this.getFillColor());
		 return copy;
	}


}
