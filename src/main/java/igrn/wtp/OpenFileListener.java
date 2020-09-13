package igrn.wtp;

import java.awt.Component;
import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jsoup.nodes.Document;


public class OpenFileListener implements ActionListener {
	private Component parentFrame;
	
	public OpenFileListener(Component parentFrame) {
		super();
		this.parentFrame = parentFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Выберите файл");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Веб-страница (*.htm; *.html)", "htm", "html"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		int result = fileChooser.showOpenDialog(parentFrame);
		if (result == JFileChooser.APPROVE_OPTION) {
			Document openedHtmlFile = FileManager.openHtmlFile(fileChooser.getSelectedFile());
			String parsedHtml = Parser.findText(openedHtmlFile);
			Parser.getWordFrequency(parsedHtml);
		}
	}
}
