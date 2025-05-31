package main;

import visuals.Principal;

import java.awt.EventQueue;
import java.sql.SQLException;

import utils.DatabaseCleaner;
import utils.DatabaseInitializer;

public class Main {
	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		DatabaseCleaner.limpiarDatos();
		DatabaseInitializer.initializeBaseData();
	           
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	/**
	 * Create the frame.
	 */
}
}