package igrn.wtp;

import java.io.*;
import java.net.*;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Downloader {
	private static Document bufferedWebpage;
	
	// Сохраняет указанную страницу в буфер для последующей обработки парсером
	public static Document saveToBuffer(String website) {
		try {
			bufferedWebpage = Jsoup.connect(website)
								   .userAgent("Mozilla/5.0")
								   .timeout(3000)
								   .maxBodySize(0)
								   .execute()
								   .parse();
			return bufferedWebpage;
		// Исключения
		} catch (MalformedURLException e) {
			System.err.println("MalformedURLException: " + e.getMessage());
			return null;
		} catch (SocketTimeoutException e) {
			System.err.println("SocketTimeoutException: " + e.getMessage());
			return null;
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
			return null;
		}
	}
	
	// Сохраняет удержанную в буфере страницу в html-файл
	public static void saveToHtml(String name) {
		try {
			FileUtils.writeStringToFile(new File(name + ".html"), bufferedWebpage.outerHtml(), "UTF-8");
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}
}
