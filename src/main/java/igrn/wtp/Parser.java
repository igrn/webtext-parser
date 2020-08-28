package igrn.wtp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Parser {
	
	private static BufferedReader reader;
	private static BufferedWriter writer;
	private static String line;
	
	// Метод создает из указанного html-файла новый txt-файл, содержащий только видимую текстовую информацию 
	// (т.е. опускает все теги и скрипты), для его последующего анализа.
	public static void parse(String HTMLFileName) {
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(HTMLFileName), StandardCharsets.UTF_8));
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("download-temp.html"), StandardCharsets.UTF_8)); 
			
			// Создаем временный файл, который удобнее форматировать
			while ((line = reader.readLine()) != null) {
				writer.append(line);
				if (line.length() > 0 && line.charAt(line.length() - 1) == '>') {
					writer.append('\n');
				} else {
					writer.append(' ');
				}
			}	
			reader.close(); 
			writer.close(); 
			
			// Удаляем всё до тега <body>
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("download-temp.html"), StandardCharsets.UTF_8));
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("words-temp.txt"), StandardCharsets.UTF_8));
			do {
				line = reader.readLine();
			} while (Pattern.matches(".*?<body.*", line) == false);
			do {
				writer.append(line);
				writer.append('\n');
			} while ((line = reader.readLine()) != null);	
			reader.close(); 
			writer.close(); 
			new File("download-temp.html").delete();
			
			// Удаляем содержимое тегов, лишние пробелы и пустые строки из файла	
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("words-temp.txt"), StandardCharsets.UTF_8));
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Download-Words.txt"), StandardCharsets.UTF_8));
			while ((line = reader.readLine()) != null) {
				if (Pattern.matches(".*?</script>", line) == true) {
					line = line.replaceAll(".*?</script>", " ");
				} else {
					line = line.replaceAll("<.*?>", " ");
				}
				line = line.trim();
				if (line.length() > 0) {
					writer.append(line);
					writer.append('\n');
				}
			}
			reader.close(); 
			writer.close();
			new File("words-temp.txt").delete();
			
		// Исключения	
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}
	
	
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
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
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
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
		
		// Объединяем два списка в один формата "СЛОВО - количество вхождений"
		for (int i = 0 ; i < wordsList.size(); i++) {
			wordsList.set(i, wordsList.get(i) + " - " + Integer.toString(wordsCount.get(i)));
		}
		return wordsList;
	}
	
}
