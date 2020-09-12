package igrn.wtp;

import java.util.HashMap;
import java.util.regex.Pattern;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class Parser {
	// Выдает строку со всем тектом, содержащимся в теле кода указанной web-страницы
	public static String findText(Document html) {
		String parsedHtml = "";
		Element htmlBody = html.body();
		Elements tags = htmlBody.select("*");
		for (Element tag : tags) {
			for (TextNode tn : tag.textNodes()) { //textNode делит вложенные теги на отдельные словосочетания
				String tagText = tn.text().trim();
				if (tagText.length() > 0) {
					parsedHtml += tagText + " ";
				}
			}
		}
		return parsedHtml;
	}
	
	// Возвращает словарь типа "Найденное слово - Количество вхождений"
	public static void getWordFrequency(String parsedHtml) {
		HashMap<String, Integer> foundWords = new HashMap<String, Integer>();
		for (String word : parsedHtml.split("[ ,.!?\";:\n\r\t]")) { // Делим строку на отдельные слова
			word = word.toUpperCase();
			if (Pattern.matches("[A-ZА-Я]([A-ZА-Я0-9-]*?)", word)) { // Пропускаем числа, отдельные символы (напр. ©)
				if (foundWords.containsKey(word)) {
					foundWords.put(word, foundWords.get(word) + 1);
				} else {
					foundWords.put(word, 1);
				}
			}
		}
		new ConsoleWindow(foundWords); // Вывод результата в консоль
	}
}
