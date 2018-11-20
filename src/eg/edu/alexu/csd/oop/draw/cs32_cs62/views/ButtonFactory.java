package eg.edu.alexu.csd.oop.draw.cs32_cs62.views;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonFactory {

	public JButton makeButton(String btnName) {
		JButton theBut = new JButton();
		try {
			Icon butIcon = new ImageIcon(getClass().getResource(btnName + ".png"));
			theBut.setIcon(butIcon);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		theBut.setSize(50, 50);
		return theBut;
	}
}
