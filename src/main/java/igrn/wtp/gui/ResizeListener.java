package igrn.wtp.gui;


import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ResizeListener extends ComponentAdapter {
	private final Component component;
	private final int width, height;
	
	public ResizeListener(Component component) {
		super();
		this.component = component;
		width = component.getWidth();
		height = component.getHeight();
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		final int width = component.getWidth();
		final int height = component.getHeight();
		if (width < this.width || height < this.height) {
			component.setSize(Math.max(width, this.width), Math.max(height, this.height));
		}
	}
	
}
