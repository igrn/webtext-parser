package igrn.wtp;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
		} catch (UnsupportedLookAndFeelException e) {
			System.err.println("UnsupportedLookAndFeelException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
		} catch (InstantiationException e) {
			System.err.println("InstantiationException: " + e.getMessage());
		} catch (IllegalAccessException e) {
			System.err.println("IllegalAccessException: " + e.getMessage());
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow();
			}
		});
	}
}
