package igrn.wtp.gui;

import java.awt.*;
import javax.swing.JCheckBox;

public class GridBagCheckBox extends JCheckBox implements ConstraintsInterface {
	private GridBagConstraints constraints;
	
	public GridBagCheckBox(String text) {
		super(text);
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setForeground(Color.WHITE);
		setBackground(new Color(44, 49, 53));
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
