package igrn.wtp;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import igrn.wtp.gui.*;

public class MainWindow extends JFrame {
	private JPanel contents;
	private GridBagLabel descLabel;
	private GridBagComboBox<String> httpBox;
	private GridBagTextField httpField;
	private GridBagButton okButton;
	private GridBagButton openFileButton;
	private GridBagCheckBox saveCheckBox;
	
	public MainWindow() {
		super("Webtext Parser");
		createGUI();
		setContentPane(contents);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(450, 300));
		setLocationRelativeTo(null);
		setVisible(true);	
		addListeners();
	}
	
	// Собирает весь интерфейс окна в одну панель
	private void createGUI() {
		addLabel();
		addComboBox();
		addTextField();
		addButtons();
		addCheckBox();
		addElementsToPanel();
	}
	
	// Создание и настройка панели
	private void addElementsToPanel() {
		contents = new JPanel();
		contents.setBackground(new Color(44, 49, 53));
		contents.setBorder(new EmptyBorder(5, 5, 5, 5));
		contents.setLayout(new GridBagLayout());
		
		contents.add(descLabel, descLabel.getConstraints());
		contents.add(httpBox, httpBox.getConstraints());
		contents.add(httpField, httpField.getConstraints());
		contents.add(okButton, okButton.getConstraints());
		contents.add(openFileButton, openFileButton.getConstraints());
		contents.add(saveCheckBox, saveCheckBox.getConstraints());
	}
	
	// Верхняя надпись
	private void addLabel() {
		descLabel = new GridBagLabel("Введите название сайта, чтобы продолжить:");
		descLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		descLabel.setGridPos(0, 0);
		descLabel.setGridSize(3, 1);
		descLabel.setWeight(0.0, 1.0);
		descLabel.setPadding(0, 0, 20, 0);
		descLabel.setAnchor(Alignment.BOTTOM);
		descLabel.setConstraints();
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
		httpField.setGridPos(1, 1);
		httpField.setWeight(1.0, 0.0);
		httpField.setFillMode(FillMode.HORIZONTAL);
		httpField.setConstraints();
		httpField.setCaretPosition(0);
	}
	
	private void addButtons() {
		// Кнопка "OK"
		okButton = new GridBagButton("OK");		
		okButton.setGridPos(2, 1);
		okButton.setPadding(0, 5, 0, 0);
		okButton.adjustMinSize(5, 0);
		okButton.setConstraints();
		okButton.setFocusable(false);
		
		// Открывает диалоговое окно для открытия файла *.html
		openFileButton = new GridBagButton("<HTML>или <U>Открыть файл</U></HTML>");
		openFileButton.setForeground(Color.WHITE);
		openFileButton.setBackground(new Color(44, 49, 53));
		openFileButton.setGridPos(0, 2);
		openFileButton.setGridSize(3, 1);
		openFileButton.setPadding(5, 0, 0, 0);
		openFileButton.setConstraints();
		openFileButton.setFocusPainted(false);
		openFileButton.setContentAreaFilled(false);
	}
	
	// Опция сохранения обработанного сайта в файл имя-сайта.html
	private void addCheckBox() {
		saveCheckBox = new GridBagCheckBox("Сохранять сайт");
		saveCheckBox.setGridPos(0, 3);
		saveCheckBox.setGridSize(3, 1);
		saveCheckBox.setWeight(0.0, 1.0);
		saveCheckBox.setAnchor(Alignment.BOTTOMLEFT);
		saveCheckBox.setConstraints();
		saveCheckBox.setFocusPainted(false);
	}
	
	// Обрабатывает все возникающие события
	private void addListeners() {
		addComponentListener(new ResizeListener(this)); // исправляет баг swing с дисплеями hidpi
		SearchListener searchListener = new SearchListener(httpBox, httpField, saveCheckBox); // выполняет поиск слов по заданному адресу
		okButton.addActionListener(searchListener);
		httpField.addActionListener(searchListener);
		httpField.addFocusListener(new HintListener(httpField, "www.example.com")); // добавляет подсказку в поле ввода
		openFileButton.addActionListener(new OpenFileListener(this)); // открывает диалог выбора файла
	}
}
