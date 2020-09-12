package igrn.wtp;

import java.awt.event.ActionEvent;
import java.util.regex.Pattern;
import javax.swing.*;
import org.jsoup.nodes.Document;

public class SearchListener extends AbstractAction {
	private JComboBox<String> httpBox;
	private JTextField httpField;
	private JCheckBox saveCheckBox;
	private static final String urlRegex = "[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?";
	
	public SearchListener(JComboBox<String> httpBox, JTextField httpField, JCheckBox saveCheckBox) {
		super();
		this.httpBox = httpBox;
		this.httpField = httpField;
		this.saveCheckBox = saveCheckBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (Pattern.matches(urlRegex, httpField.getText())) {
			showSearchResult(httpBox.getSelectedItem().toString(), httpField.getText());
		} else if (httpField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), "Пустая строка!", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Адрес страницы введен неверно!", "Ошибка", JOptionPane.ERROR_MESSAGE);					
		}
	}
	
	// Выводит новое окно с количеством вхождений каждого слова и скачивает указанную страницу в html-файл
	private void showSearchResult(String protocol, String website) {
		Document bufferedWebpage = Connector.saveToBuffer(protocol + website);
		if (saveCheckBox.isSelected()) {
			FileManager.saveToHtmlFile(bufferedWebpage, website);
		}
		String parsedHtml = Parser.findText(bufferedWebpage);
		Parser.getWordFrequency(parsedHtml);
	}
}
