package igrn.wtp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FileManager {
	
	// Сохраняет удержанную в буфере страницу в html-файл
	public static void saveToHtmlFile(Document bufferedWebpage, String name) {
		try {
			FileUtils.writeStringToFile(new File(name + ".html"), bufferedWebpage.outerHtml(), "UTF-8");
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}
	
	// Открывает html-файл для использования парсером
	public static Document openHtmlFile(File htmlFile) {
		try {
			Document openedHtmlFile = Jsoup.parse(htmlFile, "UTF-8");
			return openedHtmlFile;
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
			return null;
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
			return null;
		}
	}
}
