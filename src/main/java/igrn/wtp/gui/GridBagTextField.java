package igrn.wtp.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

public class GridBagTextField extends JTextField implements ConstraintsInterface {
	private GridBagConstraints constraints;
	
	public GridBagTextField(String text) {
		super(text);
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
	}
	
	@Override
	public void setConstraints() {
		constraints = (GridBagConstraints) ConstraintsInterface.constraints.clone();
		restoreDefaultConstraints();
	}
	
	@Override
	public GridBagConstraints getConstraints() {
		return constraints;
	}
}
