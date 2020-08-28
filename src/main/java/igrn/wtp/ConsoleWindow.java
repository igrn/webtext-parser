package igrn.wtp;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class ConsoleWindow extends JFrame {
	
	private JTextArea console = new JTextArea();
	private JPanel contents = new JPanel();
	
	// Базовый конструктор этого класса
	public ConsoleWindow() {
		super();
		console.setFont(new Font("Consolas", Font.PLAIN, 14));
		console.setBackground(Color.BLACK);
		console.setForeground(Color.WHITE);
		console.setEditable(false);
		contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
		contents.add(new JScrollPane(console));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(contents);
		setSize(800, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	// Конструктор для вывода окна консоли с результами поиска слов
	public ConsoleWindow(ArrayList <String> wordsList) {
		this();
		setTitle("Найденные слова");
		for (int i = 0 ; i < wordsList.size(); i++) {
			write(wordsList.get(i));
		}
	}

	// Конструктор для вывода логов с ошибками
	// ЕСТЬ 2 ПРОБЛЕМЫ - Как сделать так чтобы все ошибки записывались в 1 окно (список не подойдет? добавить 2-й параметр??)
	// И Как избавиться от лишних пустых окошек
	public ConsoleWindow(String errorMessage) {
		this();
		setTitle("Возникла ошибка");
		write("При выполнении программы возникли следующие ошибки:");
		write(errorMessage);
	}
	
	//Нужен метод записи строк в консоль write()
	//Возможно нужен метод проверки успешности выполнения программы validate() или checkErrors() только не в этом классе
	
	public void write(String line) {
		console.append(line);
		console.append("\n");
	}
	
}
