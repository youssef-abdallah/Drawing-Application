package eg.edu.alexu.csd.oop.draw.cs32_cs62.model.shapes;

import java.util.HashMap;

import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.MyShape;

public class DummyShape extends MyShape {
	public DummyShape() {
		super();
	}
	public DummyShape(HashMap<String, Double> properties) {
		this.setProperties(properties);
	}
}
