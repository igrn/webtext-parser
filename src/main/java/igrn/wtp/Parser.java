package igrn.wtp;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class Parser {
	
	// Парсит указанный html-файл в текстовую строку
	public static String parseHtml(String htmlFile) {
		String text = "";
		try {
			Document doc = Jsoup.parse(new File(htmlFile), "UTF-8");
			Elements tags = doc.body().select("*");
			for (Element tag : tags) {
				for (TextNode tn : tag.textNodes()) { //textNode делит вложенные теги на отдельные словосочетания
					String tagText = tn.text().trim();
					if (tagText.length() > 0) {
						text += tagText + " ";
					}
				}
			}
		
		// Исключения	
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
		return text;
	}
	
	// Возвращает словарь типа "Найденное слово - Количество вхождений"
	public static void analyzeText(String text) {
		HashMap<String, Integer> wordsList = new HashMap<String, Integer>();
				
		// Делим строку на отдельные слова
		for (String word : text.split("[ ,.!?\";:\n\r\t]")) {
			word = word.toUpperCase();
			if (Pattern.matches("[A-ZА-Я]([A-ZА-Я0-9-]*?)", word)) {
				if (!wordsList.containsKey(word)) { // Добавляем новые слова в список, у старых увеличиваем количество вхождений
					wordsList.put(word, 1);
				} else {
					wordsList.put(word, wordsList.get(word) + 1);
				}
			}
		}
		new ConsoleWindow(wordsList); // Вывод результата в консоль
	}
	
}
