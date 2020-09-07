package igrn.wtp;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import igrn.wtp.gui.*;

public class MainWindow extends JFrame {
	private static final String urlRegex = "[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?";
	
	public MainWindow() {
		super("Webtext Parser");
		// Верхняя надпись
		GridBagLabel descLabel = new GridBagLabel("Введите название сайта, чтобы продолжить:");
		descLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		descLabel.setGridPos(0, 0);
		descLabel.setGridSize(3, 1);
		descLabel.setWeight(0.0, 1.0);
		descLabel.setPadding(0, 0, 20, 0);
		descLabel.setAnchor(Alignment.BOTTOM);
		
		// Выпадающий список с выбором https или http
		GridBagComboBox<String> httpBox = new GridBagComboBox<String>();
		httpBox.setModel(new DefaultComboBoxModel<String>(new String[] {"https://", "http://"}));
		httpBox.setGridPos(0, 1);
		httpBox.modifyMinSize(-5, 0);
		httpBox.setFocusable(false);
		
		// Текстовое поле для ввода адреса сайта
		GridBagTextField httpField = new GridBagTextField("www.example.com");
		httpField.setForeground(Color.GRAY);
		httpField.setFillMode(FillMode.HORIZONTAL);
		httpField.setGridPos(1, 1);
		httpField.setWeight(1.0, 0.0);
		httpField.setCaretPosition(0);
		
		// Кнопка OK
		GridBagButton okButton = new GridBagButton("OK");		
		okButton.setGridPos(2, 1);
		okButton.setPadding(0, 5, 0, 0);
		okButton.modifyMinSize(5, 0);
		okButton.setFocusable(false);
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Pattern.matches(urlRegex, httpField.getText())) {
					showResultWindow(httpBox.getSelectedItem() + httpField.getText());
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Адрес страницы введен неверно!", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// Открывает диалоговое окно для открытия файла *.html
		GridBagLabel openFileLabel = new GridBagLabel("<HTML>или <U>Открыть файл</U></HTML>");
		openFileLabel.setGridPos(0, 2);
		openFileLabel.setGridSize(3, 1);
		openFileLabel.setPadding(10, 0, 0, 0);
		
		// Опция сохранения обработанного сайта в файл имя-сайта.html
		GridBagCheckBox saveCheckBox = new GridBagCheckBox("Сохранять сайт");
		saveCheckBox.setGridPos(0, 3);
		saveCheckBox.setGridSize(3, 1);
		saveCheckBox.setWeight(0.0, 1.0);
		saveCheckBox.setAnchor(Alignment.BOTTOMLEFT);
		saveCheckBox.setFocusable(false);		
		
		// Добавление элементов окна на панель
		JPanel contents = new JPanel();
		contents.setBackground(new Color(44, 49, 53));
		contents.setBorder(new EmptyBorder(5, 5, 5, 5));
		contents.setLayout(new GridBagLayout());
		
		contents.add(descLabel, descLabel.constraints);
		contents.add(httpBox, httpBox.constraints);
		contents.add(httpField, httpField.constraints);
		contents.add(okButton, okButton.constraints);
		contents.add(openFileLabel, openFileLabel.constraints);
		contents.add(saveCheckBox, saveCheckBox.constraints);
		
		// Настройки окна
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(450, 300));
		setContentPane(contents);
		setVisible(true);
		
		addComponentListener(new ResizeListener(this));
	}
	
	// Выводит новое окно с количеством вхождений каждого слова и скачивает указанную страницу в html-файл
	private void showResultWindow(String website) {
		Downloader.saveToHtml(website);
		String parsedHtml = Parser.findText("index.html");
		Parser.getWordFrequency(parsedHtml);
	}
}
