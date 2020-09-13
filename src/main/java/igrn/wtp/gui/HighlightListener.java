package igrn.wtp.gui;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HighlightListener implements ChangeListener {
	JButton button;
	
	
	public HighlightListener(JButton button) {
		super();
		this.button = button;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (button.getModel().isRollover()) {
			button.setText("<html><font color='ffffff'>или </font><font color='00cf9f'><u>Открыть файл</u></font></html>");
		} else {
			button.setText("<html><font color='ffffff'>или <u>Открыть файл</u></font></html>");
		}
	}

}
