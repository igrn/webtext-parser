package igrn.wtp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TextAnalyzer {
	
	// Метод удаляет из файла ненужные символы Юникода ЭТОТ МЕТОД ТУТ НЕ НУЖЕН
	private static void cleanText(String WordsFileName) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(WordsFileName), StandardCharsets.UTF_8));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Download-Words-temp.txt"), StandardCharsets.UTF_8));
			String line;
		
			while ((line = reader.readLine()) != null) {
				if (Pattern.matches(".*?(&#[0-9]{2,4};).*?", line) == true) {
					line = line.replaceAll("&#[0-9]{2,4};", " ");
				} else if (Pattern.matches(".*?(&nbsp;).*?", line) == true) {
					line = line.replaceAll("&nbsp;", " ");
				}
				line = line.trim();
				if (line.length() > 0) {
					writer.append(line);
					writer.append('\n');
				}
			}
			reader.close();
			writer.close();
		
		// Исключения
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
			//e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
			//e.printStackTrace();
		}
	}
	
	// Метод проводит анализ тектового файла на количество нахождений уникальных слов и возвращает результат в консоль
	public static ArrayList <String> analyzeText(String WordsFileName) {
		ArrayList <String> wordsList = new ArrayList<>();
		ArrayList <Integer> wordsCount = new ArrayList<>();
		
		// "Чистим" файл от ошибок кодировки
		cleanText(WordsFileName);
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Download-Words-temp.txt"), StandardCharsets.UTF_8));
			String line;
			while ((line = reader.readLine()) != null) {
				// Делим строки на отдельные слова
				for (String word : line.split("[ ,.!?\";:\n\r\t]")) {
					word = word.toUpperCase();
					// Добавляем новые слова в список, у старых увеличиваем количество вхождений
					if (Pattern.matches("[A-ZА-Я]([A-ZА-Я0-9-]*?)", word) == true) {
						if (wordsList.contains(word) == false) {
							wordsList.add(word);
							wordsCount.add(1);
						} else {
							wordsCount.set(wordsList.indexOf(word), wordsCount.get(wordsList.indexOf(word)) + 1);
						}
					}
				}
			}
			reader.close();
			new File("Download-Words-temp.txt").delete();
		
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
			//e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
			//e.printStackTrace();
		}
		
		// Объединяем два списка в один формата "СЛОВО - количество вхождений"
		for (int i = 0 ; i < wordsList.size(); i++) {
			wordsList.set(i, wordsList.get(i) + " - " + Integer.toString(wordsCount.get(i)));
		}
		return wordsList;
	}
	
}
