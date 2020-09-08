package igrn.wtp;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import igrn.wtp.gui.*;

public class MainWindow extends JFrame {
	private JPanel contents;
	private GridBagLabel descLabel;
	private GridBagComboBox<String> httpBox;
	private GridBagTextField httpField;
	private GridBagButton okButton;
	private GridBagLabel openFileLabel;
	private GridBagCheckBox saveCheckBox;
	
	public MainWindow() {
		super("Webtext Parser");
		createGUI();
		addListeners();
		
		// Настройки окна
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(450, 300));
		setContentPane(contents);
		setVisible(true);
	}
	
	// Собирает весь интерфейс окна в одну панель
	private void createGUI() {
		addLabels();
		addComboBox();
		addTextField();
		addButton();
		addCheckBox();
		addElementsToPanel();
	}
	
	// Добавление элементов окна на панель
	private void addElementsToPanel() {
		contents = new JPanel();
		contents.setBackground(new Color(44, 49, 53));
		contents.setBorder(new EmptyBorder(5, 5, 5, 5));
		contents.setLayout(new GridBagLayout());
		
		contents.add(descLabel, descLabel.getConstraints());
		contents.add(httpBox, httpBox.getConstraints());
		contents.add(httpField, httpField.getConstraints());
		contents.add(okButton, okButton.getConstraints());
		contents.add(openFileLabel, openFileLabel.getConstraints());
		contents.add(saveCheckBox, saveCheckBox.getConstraints());
	}
	
	// Надписи в окне
	private void addLabels() {
		// Верхняя надпись
		descLabel = new GridBagLabel("Введите название сайта, чтобы продолжить:");
		descLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		descLabel.setGridPos(0, 0);
		descLabel.setGridSize(3, 1);
		descLabel.setWeight(0.0, 1.0);
		descLabel.setPadding(0, 0, 20, 0);
		descLabel.setAnchor(Alignment.BOTTOM);
		descLabel.setConstraints();
		
		// Открывает диалоговое окно для открытия файла *.html
		openFileLabel = new GridBagLabel("<HTML>или <U>Открыть файл</U></HTML>");
		openFileLabel.setGridPos(0, 2);
		openFileLabel.setGridSize(3, 1);
		openFileLabel.setPadding(10, 0, 0, 0);
		openFileLabel.setConstraints();
	}
	
	// Выпадающий список с выбором https или http
	private void addComboBox() {
		httpBox = new GridBagComboBox<String>();
		httpBox.setModel(new DefaultComboBoxModel<String>(new String[] {"https://", "http://"}));
		httpBox.setGridPos(0, 1);
		httpBox.adjustMinSize(-5, 0);
		httpBox.setConstraints();
		httpBox.setFocusable(false);
	}
	
	// Текстовое поле для ввода адреса сайта
	private void addTextField() {
		httpField = new GridBagTextField("www.example.com");
		httpField.setForeground(Color.GRAY);
		httpField.setFillMode(FillMode.HORIZONTAL);
		httpField.setGridPos(1, 1);
		httpField.setWeight(1.0, 0.0);
		httpField.setCaretPosition(0);
		httpField.setConstraints();
	}
	
	// Кнопка "OK"
	private void addButton() {
		okButton = new GridBagButton("OK");		
		okButton.setGridPos(2, 1);
		okButton.setPadding(0, 5, 0, 0);
		okButton.adjustMinSize(5, 0);
		okButton.setConstraints();
		okButton.setFocusable(false);
	}
	
	// Опция сохранения обработанного сайта в файл имя-сайта.html
	private void addCheckBox() {
		saveCheckBox = new GridBagCheckBox("Сохранять сайт");
		saveCheckBox.setGridPos(0, 3);
		saveCheckBox.setGridSize(3, 1);
		saveCheckBox.setWeight(0.0, 1.0);
		saveCheckBox.setAnchor(Alignment.BOTTOMLEFT);
		saveCheckBox.setFocusable(false);
		saveCheckBox.setConstraints();
	}
	
	// Обрабатывает все возникающие события
	private void addListeners() {
		addComponentListener(new ResizeListener(this));
		
		okButton.addActionListener(new ActionListener() {
			private static final String urlRegex = "[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?";
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Pattern.matches(urlRegex, httpField.getText())) {
					showResultWindow(httpBox.getSelectedItem() + httpField.getText());
				} else if (httpField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Пустая строка!", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Адрес страницы введен неверно!", "Ошибка", JOptionPane.ERROR_MESSAGE);					
				}
			}
		});
		
		httpField.addActionListener(new ActionListener() {
			private static final String urlRegex = "[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?";
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Pattern.matches(urlRegex, httpField.getText())) {
					showResultWindow(httpBox.getSelectedItem() + httpField.getText());
				} else if (httpField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Пустая строка!", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Адрес страницы введен неверно!", "Ошибка", JOptionPane.ERROR_MESSAGE);					
				}
			}
		});
		
		// Добавляет подсказку для ввода в текстовое поле
		httpField.addFocusListener(new FocusListener() {
			private boolean isShowingHint = true;
			
			@Override
			public void focusGained(FocusEvent e) {
				if (isShowingHint) {
					httpField.setText("");
					isShowingHint = false;
				}
				httpField.setForeground(Color.BLACK);
				httpField.setBackground(Color.WHITE);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (httpField.getText().isEmpty()) {
					httpField.setText("www.example.com");
					httpField.setForeground(Color.GRAY);
					isShowingHint = true;
				} else {
					httpField.setForeground(Color.DARK_GRAY);
				}
				httpField.setBackground(Color.LIGHT_GRAY);
				httpField.setCaretPosition(httpField.getText().length());
			}
		});
	}
	
	// Выводит новое окно с количеством вхождений каждого слова и скачивает указанную страницу в html-файл
	private void showResultWindow(String website) {
		Downloader.saveToHtml(website);
		String parsedHtml = Parser.findText("index.html");
		Parser.getWordFrequency(parsedHtml);
	}
}
