package igrn.wtp.gui;

interface ConstraintsInterface {	
	
	void setGridPos(int column, int row);
	
	void setGridSize(int numberOfColumns, int numberOfRows);
	
	void setWeight(double weightx, double weighty);
		
	void setPadding(int top, int left, int bottom, int right);
	
	void setAnchor(Alignment alignment);
	
	void adjustMinSize(int width, int height);
	
}
