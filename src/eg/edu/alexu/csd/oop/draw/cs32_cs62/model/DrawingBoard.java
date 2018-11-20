package eg.edu.alexu.csd.oop.draw.cs32_cs62.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import eg.edu.alexu.csd.oop.draw.Shape;

@SuppressWarnings("serial")
public class DrawingBoard extends JComponent {
	private static DrawingBoard uniqueInstance = new DrawingBoard();
	MyDrawingEngine drawingEngine;
	private static String currentShape;
	Graphics2D graphSettings;
	private static Color strokeColor, fillColor;
	Point drawStart, drawEnd;

	public Graphics2D getGraphSettings() {
		return graphSettings;
	}

	public void setGraphSettings(Graphics2D graphSettings) {
		this.graphSettings = graphSettings;
	}

	public static String getCurrentShape() {
		return currentShape;
	}

	public static void setCurrentShape(String currentShape) {
		DrawingBoard.currentShape = currentShape;
	}

	public static Color getStrokeColor() {
		return strokeColor;
	}

	public static void setStrokeColor(Color strokeColor) {
		DrawingBoard.strokeColor = strokeColor;
	}

	public static Color getFillColor() {
		return fillColor;
	}

	public static void setFillColor(Color fillColor) {
		DrawingBoard.fillColor = fillColor;
	}

	public static DrawingBoard getInstance() {
		if (strokeColor == null) {
			strokeColor = Color.BLACK;
		}
		if (fillColor == null) {
			fillColor = Color.WHITE;
		}
		return uniqueInstance;
	}

	private DrawingBoard() {
		drawingEngine = MyDrawingEngine.getInstance();
	}

	public Point getDrawStart() {
		return drawStart;
	}

	public void setDrawStart(Point drawStart) {
		this.drawStart = drawStart;
	}

	public Point getDrawEnd() {
		return drawEnd;
	}

	public void setDrawEnd(Point drawEnd) {
		this.drawEnd = drawEnd;
	}

	public void paint(Graphics g) {
		graphSettings = (Graphics2D) g;
		graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphSettings.setStroke(new BasicStroke(4));
		for (Shape s : drawingEngine.getShapes()) {
			if (s == null)
				continue;
			graphSettings.setPaint((Color) s.getColor());
			s.draw(graphSettings);
			graphSettings.setPaint((Color) s.getFillColor());
		}
	}
}