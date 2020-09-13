package igrn.wtp.gui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

// Вспомогательный класс-слушатель, добавляющий подсказку для ввода в текстовое поле, когда оно пустое и не в фокусе
public class HintListener implements FocusListener {
	private JTextField textfield;
	private String hint;
	private boolean isShowingHint = true;
	
	public HintListener(JTextField textfield, String hint) {
		super();
		this.textfield = textfield;
		this.hint = hint;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if (isShowingHint) {
			textfield.setText("");
			isShowingHint = false;
		}
		textfield.setForeground(Color.BLACK);
		textfield.setBackground(Color.WHITE);
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (textfield.getText().isEmpty()) {
			textfield.setText(hint);
			textfield.setForeground(Color.GRAY);
			isShowingHint = true;
		} else {
			textfield.setForeground(Color.DARK_GRAY);
		}
		textfield.setBackground(Color.LIGHT_GRAY);
		textfield.setCaretPosition(textfield.getText().length());
	}
}
