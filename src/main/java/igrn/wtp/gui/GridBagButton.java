package igrn.wtp.gui;

import java.awt.*;
import javax.swing.JButton;

public class GridBagButton extends JButton implements ConstraintsInterface {
	private GridBagConstraints constraints;
	
	public GridBagButton() {
		super();
	}
	
	public GridBagButton(String text) {
		super(text);
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
	}
	
	@Override
	public void setConstraints() {
		constraints = (GridBagConstraints) ConstraintsInterface.constraints.clone();
		resetConstraintsToDefault();
	}
	
	@Override
	public GridBagConstraints getConstraints() {
		return constraints;
	}
}
