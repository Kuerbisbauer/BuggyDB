package kb.gui;

import java.awt.BorderLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow extends JFrame {
	
	/*	###############################
	 * 	Konstante
	 * 	###############################
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Gr��e des Fensters
	 * wird im Konstruker verwendet
	 */
	private static final int WIDTH 		= 800;
	private static final int HEIGTH 	= 800;
	
	/**
	 * Position des Fensters auf der X und Y Achse
	 */
	private static final int POSX 	= 100;
	private static final int POSY	= 20;
	
	
	/*	###############################
	 * 	Attribute anderer Klassen
	 * 	###############################
	 */
	
	private Module_Details 		details 	= new Module_Details();
	private Module_ListOfBugs 	listOfBugs 	= new Module_ListOfBugs();
	private Module_Status		status		= new Module_Status();
	
	/**
	 * 	<b>Kunstruktor</b><p>
	 * 	Legt die Gr��e und Position des Fensters fest.<br>
	 * 	Das Aussehen des Fensters wird an das OS
	 * 	angepasst.
	 * 
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	public MainWindow() 
			throws 
				ClassNotFoundException, 
				InstantiationException, 
				IllegalAccessException, 
				UnsupportedLookAndFeelException{
		
		
		//Der Prozess wird beendet wenn das Programm �ber das geschlossen wird
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Aussehen wird an das OS angepasst
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		//Position und Gr��e des Fensters
		setBounds(POSX, POSY, WIDTH, HEIGTH);
		
		//Layoutmanager wird festgelegt
		setLayout(new BorderLayout());
		
		//Module werden zusammengesetzt
		buildGUI();
		
		//Men� wird erstellt
		buildMenu();
	}
	
	/**
	 * 	Die verf�gbaren Module werden im Borderlayout
	 * 	zusammengesetzt.
	 */
	private void buildGUI() {
		
		//Austrichtung der Module
		this.add(details, 		BorderLayout.CENTER);
		this.add(listOfBugs, 	BorderLayout.WEST);
		this.add(status, 		BorderLayout.SOUTH);	
	}
	
	/**
	 * 	Men� wird erstellt.<p>
	 * 	
	 * 	Vorhandene Optionen:
	 * 	<li>Datei</li>
	 * 	<li>Einstellungen</li>
	 * 	<li>Hilfe</li>
	 */
	private void buildMenu() {
		//Variablen f�r die Unterst�tzung mehrerer Sprachen
		String language = new String("de");
		String country = new String("DE");
		
		Locale currentLocale;
		ResourceBundle messages;
		
		currentLocale = new Locale(language, country);
		messages = ResourceBundle.getBundle("kb.properties.MessagesBundle");
		
		//Men�leiste und Items werden beigelegt
		JMenuBar 	menuBar 			= new JMenuBar();
		JMenu 		menuDatei 			= new JMenu(messages.getString("file"));
		JMenu		menuEinstellungen	= new JMenu("Einstellungen");
		JMenu		menuHilfe			= new JMenu("Hilfe");
		
		JMenuItem	itemNewBugWindow	= new JMenuItem("Neuer Bug");
		
		//MenuBar wird der GUI hinzugef�gt
		setJMenuBar(menuBar);
		
		menuDatei.add(itemNewBugWindow);
		
		//Reihenfolge der Items wird festgelegt
		menuBar.add(menuDatei);
		menuBar.add(menuEinstellungen);
		menuBar.add(menuHilfe);
	}
}
