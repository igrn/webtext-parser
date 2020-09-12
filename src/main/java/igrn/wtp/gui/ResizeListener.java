package igrn.wtp.gui;

import java.awt.Component;
import java.awt.event.*;

// Вспомогательный класс-слушатель, отвечающий за изменение размера JFrame при попытке его уменьшения меньше указанного минимума в методе setMinimumSize
// Частично фиксит баг Swing, когда setMinimumSize игнорирует настройки скейлинга интерфейса в Windows (для hidpi-дисплеев, у меня как раз такой)
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
