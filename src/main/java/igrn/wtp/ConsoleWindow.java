package igrn.wtp;

import java.awt.*;
import java.util.HashMap;
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
	public ConsoleWindow(HashMap<String, Integer> foundWords) {
		this();
		setTitle("Найденные слова");	
		for(String word: foundWords.keySet()) {
			console.append(word + " - " + foundWords.get(word));
			console.append("\n");
		}
	}
}
