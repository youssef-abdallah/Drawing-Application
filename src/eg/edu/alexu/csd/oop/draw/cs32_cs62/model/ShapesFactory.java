package eg.edu.alexu.csd.oop.draw.cs32_cs62.model;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.shapes.Ellipse;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.shapes.Line;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.shapes.Square;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.shapes.Triangle;

public class ShapesFactory {
	
	public Shape ShapesFactory(final String shapeName, final Double x, final Double y,
			final Double height, final Double width) {
		Shape shape = null;
		if(shapeName.equalsIgnoreCase("rectangle")) {
			shape = new Rectangle(x, y, height, width);
		} else if(shapeName.equalsIgnoreCase("square")) {
			shape = new Square(x, y, height, width);
		} else if(shapeName.equalsIgnoreCase("ellipse")) {
			shape = new Ellipse(x, y, height, width);
		} else if(shapeName.equalsIgnoreCase("circle")) {
			shape = new Circle(x, y, height, width);
		} else if(shapeName.equalsIgnoreCase("line")) {
			// in case of Line x2 = height & y2 = width
			shape = new Line(x, y, height, width); 
		} else if (shapeName.equalsIgnoreCase("triangle")) {
			shape = new Triangle(x, y, height, width);
		} else if (shapeName.equalsIgnoreCase("dummy")) {
			
		}
		return shape;
	}
}
