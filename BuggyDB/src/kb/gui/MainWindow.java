package kb.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import kb.gui.newBug.NewBugWindow;
import kb.misc.LanguageControl;

public class MainWindow extends JFrame {
	
	/*	###############################
	 * 	Konstante
	 * 	###############################
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Größe des Fensters
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
	
	private LanguageControl		languageControl = new LanguageControl();
	
	/**
	 * 	<b>Kunstruktor</b><p>
	 * 	Legt die Größe und Position des Fensters fest.<br>
	 * 	Das Aussehen des Fensters wird an das OS
	 * 	angepasst.
	 * 
	 * 	Die gespeicherte Sprache wird geladen.
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
		
		
		//Der Prozess wird beendet wenn das Programm über das geschlossen wird
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Aussehen wird an das OS angepasst
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		//Position und Größe des Fensters
		setBounds(POSX, POSY, WIDTH, HEIGTH);
		
		//Layoutmanager wird auf das BorderLayout festgelegt
		setLayout(new BorderLayout());
		
		//Sprache wird geladen
		languageControl.loadLanguage();
		
		//Module werden zusammengesetzt
		buildGUI();
		
		//Menü wird erstellt
		buildMenu();
	}
	
	/**
	 * 	Die verfügbaren Module werden im Borderlayout
	 * 	zusammengesetzt.
	 */
	private void buildGUI() {
		
		//Austrichtung der Module
		this.add(details, 		BorderLayout.CENTER);
		this.add(listOfBugs, 	BorderLayout.WEST);
		this.add(status, 		BorderLayout.SOUTH);	
	}
	
	/**
	 * 	Menü wird erstellt.<p>
	 * 	
	 * 	Vorhandene Optionen:
	 * 	<ul>
	 * 		<li>Datei
	 * 			<ul>
	 * 				<li>Neuer Bug</li>
	 *  		</ul>
	 *  	</li>
	 * 	</ul>
	 * 	<ul>
	 * 		<li>Einstellungen
	 * 			<ul>
	 * 				<li>Sprachen
	 * 					<ul>
	 * 						<li>Deutsch</li>
	 * 						<li>Englisch</li>
	 * 					</ul>
	 * 				</li>
	 * 			</ul>
	 * 		</li>
	 * 	</ul>
	 * 	<ul>
	 * 		<li>Hilfe</li>
	 * 	</ul>
	 * 	</ul>
	 *  <p><br>
	 * 
	 * 	Die Menüleiste und deren Items sind für mehrere Sprachen optimiert.<br>
	 * 	Eine neue Sprache kann mittels einer "MessagesBundle" Datei hinzugefügt werden.
	 */
	private void buildMenu() {
		//TODO Sprachenwechsel implementieren!
		
		//Variablen für die Unterstützung mehrerer Sprachen
		Locale[] supportedLocales = {
			Locale.GERMAN,
		    Locale.ENGLISH
		};
		
		String language = new String("de");
		String country = new String("DE");
		
		Locale currentLocale;
		ResourceBundle messages;
		
		currentLocale = new Locale(language, country);
		
		//Hierbei wird die "MessagesBundle" Datei gesucht und mit dem entsprechenden Suffix,
		//welche in der "currentLocale" Variable gespeichert ist, geladen.
		messages = ResourceBundle.getBundle("kb.properties.MessagesBundle", currentLocale);
		
		//Menüleiste und Items werden beigelegt
		//messages.getString(XY) holt sich das Keyword aus der MessagesBundle*.properties Datei
		JMenuBar 	menuBar 		= new JMenuBar();
		JMenu 		menuFile 		= new JMenu(messages.getString("file"));
		JMenu		menuSettings	= new JMenu(messages.getString("settings"));
		JMenu		menuHelp		= new JMenu(messages.getString("help"));
		
		JMenuItem	itemFileNewBugWindow	= new JMenuItem(messages.getString("newBug"));
		
		//Menu als MenuItem
		JMenu	itemSettingsLanguage	= new JMenu(messages.getString("language"));
		
		/*
		 * Schleife um alle Sprachen anzuzeigen
		 * Hierbei wird eine ArrayList zur Unterstützung genommen.
		 * Diese speichert alle verfügbaren Sprachen.
		 */
		ArrayList<JMenuItem> languageList = new ArrayList<JMenuItem>();
		for(Locale locale : supportedLocales)
			languageList.add(new JMenuItem(locale.getDisplayLanguage(currentLocale)));
		
		
		
		//MenuBar wird der GUI hinzugefügt
		setJMenuBar(menuBar);
		
		//Menu --- "File"
		menuFile.add(itemFileNewBugWindow);
		
		//Menu --- "Settings"
		menuSettings.add(itemSettingsLanguage);
		
		//Menu --- "Settings" - "Language"
		
		/*
		 *	Alle gespeicherten Sprachen in der ArrayList "languageList"
		 *	werden zum JMenu "itemSettingsLanguage" hinzugefügt 
		 */
		for(JMenuItem jmi : languageList){
			itemSettingsLanguage.add(jmi);
			addActionListenerToLanguageMenuItem(jmi);
		}
		
		
		//Reihenfolge der Items wird festgelegt
		menuBar.add(menuFile);
		menuBar.add(menuSettings);
		menuBar.add(menuHelp);
		
		//ActionListener werden hinzugefügt
		addActionListenerToNewBug(itemFileNewBugWindow);
	}
	
	/**
	 * <b>ActionListener für das MenuItem "New Bug"</b><p>
	 * Ein JDialog wird geöffnet um die Daten des neuen Bugs zu erfassen.
	 * 
	 * @param itemFileNewBugWindow - Das MenuItem "New Bug" wird übergeben
	 */
	private void addActionListenerToNewBug(JMenuItem itemFileNewBugWindow) {
		itemFileNewBugWindow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewBugWindow nbw = new NewBugWindow();
				nbw.setVisible(true);
			}
		});
	}

	/**
	 * <b>Actionlistener werden zu dem jeweiligen MenuItem hinzugefügt</b>
	 * 
	 * @param jMenuItem	- Das angeklickte JMenuItem
	 */
	private void addActionListenerToLanguageMenuItem(final JMenuItem jMenuItem){
		jMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				languageControl.saveLanguage(jMenuItem);
			}
		});
	}
}
