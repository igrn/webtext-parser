package igrn.wtp;

import java.io.*; 
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Downloader {
	
	private static boolean hasErrors = false;
	public static boolean isValid() {if (hasErrors) return false; else return true;}
	
	// Скачивает указанную страницу в html-файл
	public static void download(String website) { 
		try {
			Response html = Jsoup.connect(website).execute();
			Document doc = html.parse();
			doc.outerHtml();		
			
			File f = new File("Download.html");
			FileUtils.writeStringToFile(f, doc.outerHtml(), "UTF-8");
			
			
			
		// Исключения
		} catch (MalformedURLException e) {
			System.err.println("MalformedURLException: " + e.getMessage());
			hasErrors = true;
		} catch (SocketTimeoutException e) {
			System.err.println("SocketTimeoutException: " + e.getMessage());
			hasErrors = true;
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
			hasErrors = true;
		} 
	}
	
}
