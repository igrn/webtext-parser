package igrn.wtp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;

public class Connector {
	// Связывается с веб-сайтом и сохраняет код страницы в буферный документ для последующей обработки парсером
	// Google перестал работать - выдает HttpStatusException c 429 статусом (превышен лимит подключений)
	public static Document saveToBuffer(String url) {
		try {
			Document bufferedWebpage = Jsoup.connect(url)
											.userAgent("Mozilla/5.0")
											.timeout(3000)
											.maxBodySize(0)
											.get();
			return bufferedWebpage;
		} catch (HttpStatusException e) {
			System.err.println(e);
			return null;
		} catch (UnsupportedMimeTypeException e) {
			System.err.println(e);
			return null;
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
}
