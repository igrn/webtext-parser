package igrn.wtp.gui;

import java.awt.*;
import javax.swing.JComboBox;

public class GridBagComboBox<E> extends JComboBox<E> implements ConstraintsInterface{
	private GridBagConstraints constraints;
	
	public GridBagComboBox() {
		super();
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
