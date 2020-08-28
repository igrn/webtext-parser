package igrn.wtp;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.*;

public class MainWindow extends JFrame {
	
	private JTextField textbar = new JTextField();
	private JLabel label = new JLabel("https://");
	private JButton button = new JButton("OK");
	private JPanel contents = new JPanel();
	private static final String urlRegex = "[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?";
	
	public MainWindow() {
		super("Введите URL");
		// Создание текстого поля, заголовка и кнопки
		textbar.setFont(new Font("Dialog", Font.PLAIN, 16));
		textbar.setToolTipText("Введите адрес сайта в формате www.example.com");
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Pattern.matches(urlRegex, textbar.getText())) {
					getResultWindow(textbar.getText());
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Адрес страницы введен неверно!", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// Добавление элементов окна на панель
		contents.setLayout(new BoxLayout(contents, BoxLayout.X_AXIS));
		contents.add(label);
		contents.add(textbar);
		contents.add(button);
		
		// Настройки окна
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(contents);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	// Выводит новое окно с количеством вхождений каждого слова и скачивает указанную страницу в html-файл
	private void getResultWindow(String website) {
		Downloader.download("https://" + website);
		Parser.parse("Download.html");
		// Не уверен нужен ли здесь этот список, т.к. логику лучше держать отдельно от GUI
		// И вообще вызов этих методов как осуществить?
		// Надо переделать HTMLParser в загрузчик страниц в html, а Analyzer в парсер - т.е. он будет и слова искать, и находить их количество
		ArrayList <String> wordList = Parser.analyzeText("Download-Words.txt");
		if (Downloader.isValid()) {
			new ConsoleWindow(wordList);
		}
	}
	
}
