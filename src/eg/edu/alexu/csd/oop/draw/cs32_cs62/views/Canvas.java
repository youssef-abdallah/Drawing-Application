package eg.edu.alexu.csd.oop.draw.cs32_cs62.views;

import javax.swing.*;

import eg.edu.alexu.csd.oop.draw.cs32_cs62.model.DrawingBoard;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class Canvas extends JFrame {

	private JButton lineBut, ellipseBut, rectBut, strokeBut, fillBut, saveBut, loadBut, undoBut, redoBut, refreshBut,
			circleBut, triangleBut, squareBut, pluginBut;
	private JRadioButton drawingMode, motionMode, deletionMode;
	private ButtonGroup modeSelection;
	private ButtonFactory btnFactory;
	private static DrawingBoard drawingBoard;
	private JFileChooser fileChooser;
	private JPanel buttonPanelDown, buttonPanelUp;
	

	public JButton getCircleBut() {
		return circleBut;
	}

	public void setCircleBut(JButton circleBut) {
		this.circleBut = circleBut;
	}

	public JButton getTriangleBut() {
		return triangleBut;
	}

	public void setTriangleBut(JButton triangleBut) {
		this.triangleBut = triangleBut;
	}

	public JButton getSquareBut() {
		return squareBut;
	}

	public void setSquareBut(JButton squareBut) {
		this.squareBut = squareBut;
	}
	
	public JButton getPluginBut() {
		return pluginBut;
	}

	public Canvas() {
		this.setSize(800, 600);
		this.setTitle("Java Paint");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonPanelDown = new JPanel();
		buttonPanelUp = new JPanel();
		// Box theBox = Box.createHorizontalBox();
		btnFactory = new ButtonFactory();
		fileChooser = new JFileChooser();
		drawingBoard = DrawingBoard.getInstance();

		// Make all the buttons in makeMeButtons by passing the
		// button icon.

		lineBut = btnFactory.makeButton("line");
		ellipseBut = btnFactory.makeButton("ellipse");
		rectBut = btnFactory.makeButton("rectangle");
		triangleBut = btnFactory.makeButton("triangle");
		squareBut = btnFactory.makeButton("square");
		circleBut = btnFactory.makeButton("circle");
		strokeBut = btnFactory.makeButton("stroke");
		fillBut = btnFactory.makeButton("fill");
		saveBut = btnFactory.makeButton("save");
		loadBut = btnFactory.makeButton("load");
		undoBut = btnFactory.makeButton("undo");
		redoBut = btnFactory.makeButton("redo");
		refreshBut = btnFactory.makeButton("refresh");
		pluginBut = btnFactory.makeButton("plugin");
		drawingMode = new JRadioButton("Drawing");
		motionMode = new JRadioButton("Motion");
		deletionMode = new JRadioButton("Deletion");
		modeSelection = new ButtonGroup();
		modeSelection.add(drawingMode);
		modeSelection.add(motionMode);
		modeSelection.add(deletionMode);
		drawingMode.setSelected(true);
		
		GridLayout gridLayout = new GridLayout(1, 0);
		GridLayout gridLayout2 = new GridLayout(1, 0);
		buttonPanelDown.setLayout(gridLayout);
		buttonPanelUp.setLayout(gridLayout2);

		buttonPanelDown.add(lineBut);
		buttonPanelDown.add(circleBut);
		buttonPanelDown.add(ellipseBut);
		buttonPanelDown.add(rectBut);
		buttonPanelDown.add(squareBut);
		buttonPanelDown.add(triangleBut);
		buttonPanelDown.add(strokeBut);
		buttonPanelDown.add(fillBut);

		buttonPanelUp.add(saveBut);
		buttonPanelUp.add(loadBut);
		buttonPanelUp.add(undoBut);
		buttonPanelUp.add(redoBut);
		buttonPanelUp.add(refreshBut);
		buttonPanelUp.add(pluginBut);
		buttonPanelUp.add(drawingMode);
		buttonPanelUp.add(motionMode);
		buttonPanelUp.add(deletionMode);
		
		// buttonPanel.add(theBox);
		getContentPane().add(buttonPanelDown, BorderLayout.SOUTH);
		getContentPane().add(buttonPanelUp, BorderLayout.NORTH);
		getContentPane().add(drawingBoard, BorderLayout.CENTER);
		getContentPane().setBackground(Color.WHITE);
		this.setVisible(true);
	}
	public JRadioButton getDeletionMode() {
		return deletionMode;
	}
	public JRadioButton getDrawingMode() {
		return drawingMode;
	}

	public JRadioButton getMotionMode() {
		return motionMode;
	}

	public ButtonGroup getModeSelection() {
		return modeSelection;
	}

	public JPanel getButtonPanelDown() {
		return buttonPanelDown;
	}

	public ButtonFactory getBtnFactory() {
		return btnFactory;
	}

	public void setBtnFactory(ButtonFactory btnFactory) {
		this.btnFactory = btnFactory;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public JButton getLineBut() {
		return lineBut;
	}

	public JButton getEllipseBut() {
		return ellipseBut;
	}

	public JButton getRectBut() {
		return rectBut;
	}

	public JButton getStrokeBut() {
		return strokeBut;
	}

	public JButton getFillBut() {
		return fillBut;
	}

	public JButton getSaveBut() {
		return saveBut;
	}

	public JButton getLoadBut() {
		return loadBut;
	}

	public JButton getUndoBut() {
		return undoBut;
	}

	public JButton getRedoBut() {
		return redoBut;
	}

	public JButton getRefreshBut() {
		return refreshBut;
	}

}