package igrn.wtp.gui;

import java.awt.*;
import javax.swing.JComboBox;

public class GridBagComboBox<E> extends JComboBox<E> implements ConstraintsInterface{
	public GridBagConstraints constraints = new GridBagConstraints();	
	
	public GridBagComboBox() {
		super();
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
	}

	@Override
	public void setGridPos(int column, int row) {
		constraints.gridx = column;
		constraints.gridy = row;
	}
	
	@Override
	public void setGridSize(int numberOfColumns, int numberOfRows) {
		constraints.gridwidth = numberOfColumns;
		constraints.gridheight = numberOfRows;
	}
	
	@Override
	public void setWeight(double weightx, double weighty) {
		constraints.weightx = weightx;
		constraints.weighty = weighty;
	}

	@Override
	public void setPadding(int top, int left, int bottom, int right) {
		constraints.insets = new Insets(top, left, bottom, right);
	}
	
	@Override
	public void setAnchor(Alignment alignment) {
		switch (alignment) {
			case TOP:
				constraints.anchor = GridBagConstraints.NORTH;
				break;
			case BOTTOM:
				constraints.anchor = GridBagConstraints.SOUTH;
				break;
			case LEFT:
				constraints.anchor = GridBagConstraints.WEST;
				break;
			case RIGHT:
				constraints.anchor = GridBagConstraints.EAST;
				break;
			case TOPLEFT:
				constraints.anchor = GridBagConstraints.NORTHWEST;
				break;
			case TOPRIGHT:
				constraints.anchor = GridBagConstraints.NORTHEAST;
				break;
			case BOTTOMLEFT:
				constraints.anchor = GridBagConstraints.SOUTHWEST;
				break;
			case BOTTOMRIGHT:
				constraints.anchor = GridBagConstraints.SOUTHEAST;
				break;
		}
	}
	
	@Override
	public void adjustMinSize(int width, int height) {
		constraints.ipadx = width;
		constraints.ipady = height;
	}
}
