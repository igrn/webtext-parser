package igrn.wtp;

import java.io.*;
import java.net.*;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Downloader {
	
	// Скачивает указанную страницу в html-файл
	public static void savetoHtml(String website) {
		try {
			Document doc = Jsoup.connect(website)
								.userAgent("Mozilla/5.0")
								.timeout(3000)
								.maxBodySize(0)
								.execute()
								.parse();
			
			FileUtils.writeStringToFile(new File("index.html"), doc.outerHtml(), "UTF-8");
			
		// Исключения
		} catch (MalformedURLException e) {
			System.err.println("MalformedURLException: " + e.getMessage());
		} catch (SocketTimeoutException e) {
			System.err.println("SocketTimeoutException: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		} 
	}
	
}
