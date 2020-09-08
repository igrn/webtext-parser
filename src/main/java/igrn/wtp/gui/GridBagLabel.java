package igrn.wtp.gui;

import java.awt.*;
import javax.swing.JLabel;

public class GridBagLabel extends JLabel implements ConstraintsInterface {
	private GridBagConstraints constraints;
	
	public GridBagLabel() {
		super();
	}
	
	public GridBagLabel(String text) {
		super(text);
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setForeground(Color.WHITE);
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
