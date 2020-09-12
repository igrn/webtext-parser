package igrn.wtp.gui;

import java.awt.*;

interface ConstraintsInterface {	
	GridBagConstraints constraints = new GridBagConstraints();
	
	void setConstraints();
	
	GridBagConstraints getConstraints();
	
	default void restoreDefaultConstraints() {
		setGridPos(0, 0);
		setGridSize(1, 1);
		setWeight(0.0, 0.0);
		setPadding(0, 0, 0, 0);
		setAnchor(Alignment.CENTER);
		setFillMode(FillMode.NONE);
		adjustMinSize(0, 0);
	}
	
	default void setGridPos(int column, int row) {
		constraints.gridx = column;
		constraints.gridy = row;
	}
	
	default void setGridSize(int numberOfColumns, int numberOfRows) {
		constraints.gridwidth = numberOfColumns;
		constraints.gridheight = numberOfRows;
	}
	
	default void setWeight(double weightx, double weighty) {
		constraints.weightx = weightx;
		constraints.weighty = weighty;
	}
		
	default void setPadding(int top, int left, int bottom, int right) {
		constraints.insets = new Insets(top, left, bottom, right);
	}
	
	default void setAnchor(Alignment alignment) {
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
			case CENTER:
				constraints.anchor = GridBagConstraints.CENTER;
				break;
		}
	}
	
	default public void setFillMode(FillMode mode) {
		switch (mode) {
			case HORIZONTAL:
				constraints.fill = GridBagConstraints.HORIZONTAL;
				break;
			case VERTICAL:
				constraints.fill = GridBagConstraints.VERTICAL;
				break;
			case BOTH:
				constraints.fill = GridBagConstraints.BOTH;
				break;
			case NONE:
				constraints.fill = GridBagConstraints.NONE;
				break;
		}
	}
	
	default void adjustMinSize(int width, int height) {
		constraints.ipadx = width;
		constraints.ipady = height;
	}
}
