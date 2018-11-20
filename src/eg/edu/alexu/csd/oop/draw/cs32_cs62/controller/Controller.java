package eg.edu.alexu.csd.oop.draw.cs32_cs62.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.DrawingBoard;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.JarFileFilter;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.JsonFileFilter;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.MyDrawingEngine;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.ShapesFactory;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.XmlFileFilter;
import eg.edu.alexu.csd.oop.draw.cs32_cs62.views.Canvas;

public class Controller {
	private static DrawingBoard drawingBoard;
	private static DrawingEngine drawingEngine;
	private ShapesFactory shapesFactory;
	private static Canvas canvas;

	public Controller() {
		drawingBoard = DrawingBoard.getInstance();
		drawingEngine = MyDrawingEngine.getInstance();
		shapesFactory = new ShapesFactory();
		canvas = new Canvas();
        addBtnListener(canvas.getLineBut(), "line");
        addBtnListener(canvas.getEllipseBut(), "ellipse");
        addBtnListener(canvas.getStrokeBut(), "stroke");
        addBtnListener(canvas.getFillBut(), "fill");
        addBtnListener(canvas.getRectBut(), "rectangle");
        addBtnListener(canvas.getSaveBut(), "save");
        addBtnListener(canvas.getLoadBut(), "load");
        addBtnListener(canvas.getUndoBut(), "undo");
        addBtnListener(canvas.getRedoBut(), "redo");
        addBtnListener(canvas.getRefreshBut(), "refresh");
        addBtnListener(canvas.getCircleBut(), "circle");
        addBtnListener(canvas.getTriangleBut(), "triangle");
        addBtnListener(canvas.getSquareBut(), "square");
        addBtnListener(canvas.getPluginBut(), "plugin");
        
		drawingBoard.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (canvas.getDeletionMode().isSelected()) {
					if (e.getClickCount() == 2) {
						double dx = e.getX();
			            double dy = e.getY();
			            for (Shape s : drawingEngine.getShapes()) {
			    			if (s == null)
			    				continue;
			    			double width, height;
			    			if (s.getClass().getSimpleName().equals("RoundRectangle")) {
			    				width = s.getProperties().get("Width");
			    				height = s.getProperties().get("Length");
			    			} else {
			    				width = s.getProperties().get("width");
			    				height = s.getProperties().get("height");
			    			}
			    			Point position = (Point) s.getPosition();
			    			if (dx >= position.getX() - width / 2 
			    					&& dx <= position.getX() + width / 2
			    					&& dy >= position.getY() - height / 2
			    					&& dy <= position.getY() + height / 2) {
			    				drawingEngine.removeShape(s);
			    				drawingBoard.repaint();
			    				return;
			    			}
			    		}
					}
					return;
				}
				if (DrawingBoard.getCurrentShape() == null) {
					DrawingBoard.setCurrentShape("null");
					return;
				}
				drawingBoard.setDrawStart(new Point(e.getX(), e.getY()));
				drawingBoard.setDrawEnd(drawingBoard.getDrawStart());
				drawingBoard.repaint();

			}

			public void mouseReleased(MouseEvent e) {
				if (DrawingBoard.getCurrentShape().equalsIgnoreCase("null")
						|| canvas.getMotionMode().isSelected() || canvas.getDeletionMode().isSelected()) {
					return;
				}
				if (DrawingBoard.getCurrentShape() != "line") {

					// Create a shape using the starting x & y
					// and finishing x & y positions

					Shape addedShape = null;
					// Create a new rectangle using x & y coordinates
					int x = Math.min(drawingBoard.getDrawStart().x, e.getX());
					int y = Math.min(drawingBoard.getDrawStart().y, e.getY());
					int width = Math.abs(drawingBoard.getDrawStart().x - e.getX());
					int height = Math.abs(drawingBoard.getDrawStart().y - e.getY());
					if (DrawingBoard.getCurrentShape().equalsIgnoreCase("roundRectangle")) {
						List<Class<? extends Shape>> list = drawingEngine.getSupportedShapes();
			            for(Class<? extends Shape> c : list){
			                if(c.getName().equals("eg.edu.alexu.csd.oop.draw.RoundRectangle")){
			                    try {
									addedShape = c.newInstance();
									addedShape.setPosition(new Point(x, y));
									addedShape.getProperties().put("Length", 1.0 * height);
									addedShape.getProperties().put("Width", 1.0 * width);
									addedShape.getProperties().put("ArcWidth", 40.0);
									addedShape.getProperties().put("ArcLength", 40.0);
								} catch (InstantiationException | IllegalAccessException e1) {
									e1.printStackTrace();
								}
			                }
			            }
					}
					else {
						addedShape = shapesFactory.ShapesFactory(DrawingBoard.getCurrentShape(), 1.0 * x, 1.0 * y, 1.0 * height, 1.0 * width);
					}
					addedShape.setColor(DrawingBoard.getStrokeColor());
					addedShape.setFillColor(DrawingBoard.getFillColor());
					// Add shapes, fills and colors to there ArrayLists
					drawingEngine.addShape(addedShape);
					drawingBoard.setDrawStart(null);
					drawingBoard.setDrawEnd(null);

					// repaint the drawing area

					drawingBoard.repaint();

				}
				else if (DrawingBoard.getCurrentShape() != "motion"){
					Shape addedShape = null;
					addedShape = shapesFactory.ShapesFactory(DrawingBoard.getCurrentShape(),
							1.0 * drawingBoard.getDrawStart().x, 1.0 * drawingBoard.getDrawStart().y,
							1.0 * e.getX(), 1.0 * e.getY());
					addedShape.setColor(DrawingBoard.getStrokeColor());
					addedShape.setFillColor(DrawingBoard.getFillColor());
					// Add shapes, fills and colors to there ArrayLists
					drawingEngine.addShape(addedShape);
					drawingBoard.setDrawStart(null);
					drawingBoard.setDrawEnd(null);
					
					drawingBoard.repaint();


				}

			}
		});
		
		drawingBoard.addMouseMotionListener(new MouseMotionAdapter() {
			private void doMove(MouseEvent e) {
	            
	            double dx = e.getX();
	            double dy = e.getY();
	            for (Shape s : drawingEngine.getShapes()) {
	    			if (s == null)
	    				continue;
	    			double width, height;
	    			if (s.getClass().getSimpleName().equals("RoundRectangle")) {
	    				width = s.getProperties().get("Width");
	    				height = s.getProperties().get("Length");
	    			} else {
	    				width = s.getProperties().get("width");
	    				height = s.getProperties().get("height");
	    			}
	    			Point position = (Point) s.getPosition();
	    			if (dx >= position.getX() - width / 2 
	    					&& dx <= position.getX() + width / 2
	    					&& dy >= position.getY() - height / 2
	    					&& dy <= position.getY() + height / 2) {
	    				DrawingBoard.setCurrentShape("motion");
	    				if (s.getClass().getSimpleName().equals("Line")) {
	    					s.getProperties().put("height", height - position.getX() + dx);
	    					s.getProperties().put("width", width - position.getY() + dy);
	    				} else if(s.getClass().getSimpleName().equals("Triangle")) {
	    					s.getProperties().put("x2", s.getProperties().get("x2") - (position.getX() - dx) );
	    					s.getProperties().put("y2", s.getProperties().get("y2") - (position.getY() - dy) );
	     					s.getProperties().put("x3", s.getProperties().get("x3") - (position.getX() - dx) );
	    					s.getProperties().put("y3", s.getProperties().get("y3") - (position.getY() - dy) );
	    				}
	    				Point newPosition = new Point();
	    				newPosition.setLocation(dx, dy);
	    				s.setPosition(newPosition);
	    				return;
	    			}
	    		}
	            /*x += dx;
	            y += dy;*/            
	        }
			public void mouseDragged(MouseEvent e) {
				if (canvas.getDrawingMode().isSelected() || canvas.getDeletionMode().isSelected()) {
					return;
				}
				doMove(e);
				drawingBoard.repaint();
			}
		});
		drawingBoard.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				doScale(e);
				drawingBoard.repaint();
			}
			private void doScale(MouseWheelEvent e) {
				double dx = e.getX();
	            double dy = e.getY();
	            double amount =  e.getWheelRotation() * 5.0;
	            for (Shape s : drawingEngine.getShapes()) {
	            	if (s == null)
	    				continue;
	            	double width, height;
	    			if (s.getClass().getSimpleName().equals("RoundRectangle")) {
	    				width = s.getProperties().get("Width");
	    				height = s.getProperties().get("Length");
	    			} else {
	    				width = s.getProperties().get("width");
	    				height = s.getProperties().get("height");
	    			}
	    			Point position = (Point) s.getPosition();
	    			if (dx >= position.getX() - width / 2 
	    					&& dx <= position.getX() + width / 2
	    					&& dy >= position.getY() - height / 2
	    					&& dy <= position.getY() + height / 2) {
	    				if (s.getClass().getSimpleName().equals("RoundRectangle")) {
	    					s.getProperties().replace("Width", s.getProperties().get("Width") + amount);
		    				s.getProperties().replace("Length", s.getProperties().get("Length") + amount);
	    				} else if (s.getClass().getSimpleName().equals("Line")) {
	    					Point newPosition = new Point();
	    					double slope = (position.getY() - s.getProperties().get("width"))
	    							/ ((position.getX() - s.getProperties().get("height")));
	    					newPosition.setLocation(position.getX() + amount, position.getY() + slope * amount);
	    					s.setPosition(newPosition);
	    				}
	    				else if(s.getClass().getSimpleName().equals("Triangle")) {
		    				Point newPosition = new Point();
		    				newPosition.setLocation(position.getX() - amount, position.getY());
		    				s.setPosition(newPosition);
		    				s.setPosition(newPosition);
		    				s.getProperties().replace("x2", s.getProperties().get("x2")+amount);
		    				s.getProperties().replace("y3", s.getProperties().get("y3")+amount);
		    			}
	    				else {
		    				s.getProperties().replace("width", s.getProperties().get("width") + amount);
		    				s.getProperties().replace("height", s.getProperties().get("height") + amount);
		    			}
	    			}
	    		}            
	        }
			
		});
	}
	  public static void addBtnListener(JButton btn, String btnName) {
      	btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (btnName.equals("stroke")) {
						DrawingBoard.setStrokeColor(JColorChooser.showDialog(null,  "Pick a Stroke", Color.BLACK));
					} else if (btnName.equals("fill")) {
						DrawingBoard.setFillColor(JColorChooser.showDialog(null,  "Pick a Fill", Color.WHITE));
					} else if (btnName.equalsIgnoreCase("undo")) {
						drawingEngine.undo();
						drawingBoard.repaint();
					} else if (btnName.equalsIgnoreCase("redo")) {
						drawingEngine.redo();
						drawingBoard.repaint();
					} else if (btnName.equalsIgnoreCase("refresh")) {
						drawingEngine.refresh(drawingBoard.getGraphSettings());
						drawingBoard.repaint();
					} else if (btnName.equalsIgnoreCase("load")) {
						XmlFileFilter xmlFilter = new XmlFileFilter();
						JsonFileFilter jsonFilter = new JsonFileFilter();
						JFileChooser fileChooser = canvas.getFileChooser();
						fileChooser.setAcceptAllFileFilterUsed(false);
						fileChooser.addChoosableFileFilter(xmlFilter);
						fileChooser.addChoosableFileFilter(jsonFilter);
						if (canvas.getFileChooser().showOpenDialog(canvas) == JFileChooser.APPROVE_OPTION) {
							drawingEngine.load(fileChooser.getSelectedFile().getPath());
							drawingBoard.repaint();
						}
					} else if (btnName.equalsIgnoreCase("save")) {
						XmlFileFilter xmlFilter = new XmlFileFilter();
						JsonFileFilter jsonFilter = new JsonFileFilter();
						JFileChooser fileChooser = canvas.getFileChooser();
						fileChooser.setAcceptAllFileFilterUsed(false);
						fileChooser.addChoosableFileFilter(xmlFilter);
						fileChooser.addChoosableFileFilter(jsonFilter);
						if (fileChooser.showSaveDialog(canvas) == JFileChooser.APPROVE_OPTION) {
							File file = new File(fileChooser.getSelectedFile().getAbsolutePath()
									+ fileChooser.getFileFilter().getDescription());
							drawingEngine.save(file.getPath());
						}
					} else if (btnName.equalsIgnoreCase("plugin")) {
						JarFileFilter jarFilter = new JarFileFilter();
						JFileChooser fileChooser = canvas.getFileChooser();
						fileChooser.setAcceptAllFileFilterUsed(false);
						fileChooser.addChoosableFileFilter(jarFilter);
						if (canvas.getFileChooser().showOpenDialog(canvas) == JFileChooser.APPROVE_OPTION) {
							((MyDrawingEngine) drawingEngine).loadPlugin(fileChooser.getSelectedFile().getPath());
							JButton roundRectangleButton = canvas.getBtnFactory().makeButton("roundRectangle");
							addBtnListener(roundRectangleButton, "roundRectangle");
							canvas.getButtonPanelDown().add(roundRectangleButton);
							canvas.getButtonPanelDown().repaint();
							drawingBoard.repaint();
							canvas.revalidate();
						}
					}
					else {
						DrawingBoard.setCurrentShape(btnName);
					}
				}
          });
      }
	
	public static void main (String[] args) {
		new Controller();
		Controller.canvas.setVisible(true);
	}
}
