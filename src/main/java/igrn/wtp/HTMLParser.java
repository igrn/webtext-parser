package igrn.wtp;

import java.io.*; 
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class HTMLParser {
	
	private static BufferedReader reader;
	private static BufferedWriter writer;
	private static String line;
	
	// Скачивает указанную страницу в html-файл
	public static void download(String website) { 
		try { 
			URL url = new URL(website);
			reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)); 
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Download.html"), StandardCharsets.UTF_8)); 
			
			// Чтение и запись данных из потока URL 
			while ((line = reader.readLine()) != null) {
				writer.append(line);
				writer.append('\n');
			}
			reader.close(); 
			writer.close();
		
		
		// ОНИ СЕЙЧАС ТОЛЬКО В КОНСОЛЬ ВЫВОДЯТСЯ -> ДОБАВИТЬ ВЫВОД ConsoleWindow С ОШИБКОЙ
		// Исключения
		} catch (MalformedURLException mue) {
			new ConsoleWindow("MalformedURLException: " + mue.getMessage());
			//mue.printStackTrace();
		} catch (IOException ioe) {
			new ConsoleWindow("IOException: " + ioe.getMessage());
			//ioe.printStackTrace();
		} 
	}
	
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
		} catch (FileNotFoundException ffe) {
			System.err.println("FileNotFoundException: " + ffe.getMessage());
			//ffe.printStackTrace();
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
			//ioe.printStackTrace();
		}
	}
	
}
