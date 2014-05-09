package kb.gui.newBug;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class NewBugWindow extends JDialog{
	//TODO MIGLAYOUT!
	/*	###############################
	 * 	Konstante
	 * 	###############################
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Gr��e des Fensters
	 * wird im Konstruker verwendet
	 */
	private static final int WIDTH 		= 500;
	private static final int HEIGTH 	= 500;
	
	/**
	 * Position des Fensters auf der X und Y Achse
	 */
	private static final int POSX 	= 100;
	private static final int POSY	= 20;
	
	/*	###############################
	 * 	Swing Komponenten
	 * 	###############################
	 */
	
	private JTextField 	titleField 			= new JTextField("Titel");
	private JEditorPane	textAreaDescription	= new JEditorPane();
	
	private JButton	clipBoard			= new JButton("Clipboard");
	private JButton	externalFile		= new JButton("Datei");
	private JButton externalFileLine	= new JButton("Zeile");
	
	private JButton	save				= new JButton("Speichern");
	private JButton cancel				= new JButton("Cancel");
	
	private JPanel fileControl	= new JPanel();
	private JPanel bugControl	= new JPanel();
	
	/**
	 * 	<b>Kunstruktor</b><p>
	 * 	Legt die Gr��e und Position des Fensters fest.<br>
	 * 	Der JDialog ist modul und muss somit geschlossen werden, bevor
	 * 	das Hauptfenster aktiv wird.
	 *	
	 */
	public NewBugWindow(){
		//Position und Gr��e des Fensters
		setBounds(POSX, POSY, WIDTH, HEIGTH);
		
		//Modal
		setModal(true);
		
		//Layoutmanager wird auf das BorderLayout festgelegt
		setLayout(new BorderLayout());
		
		//GUI wird zusammengestellt
		buildGUI();
	}

	/**
	 * 	Die Swing Komponenten werden im Borderlayout
	 * 	zusammengesetzt.
	 */
	private void buildGUI() {
		
		//F�r fileControl und bugControl werden unterschiedliche Layouts verwendet
		setAdditionalLayout();
		
		//SwingKomponenten werden hinzugef�gt:
		//Titelleiste, TextEditorArea, fileControl, bugControl
		this.add(titleField, BorderLayout.NORTH);
		this.add(textAreaDescription, BorderLayout.CENTER);
		this.add(fileControl, BorderLayout.EAST);
		this.add(bugControl, BorderLayout.SOUTH);
		
		//Es werden drei Buttons zu FileControl hinzugef�gt
		fileControl.add(clipBoard);
		fileControl.add(externalFile);
		fileControl.add(externalFileLine);
		
		//Der Save und Cancel Button werden zu bugControl hinzugef�gt
		bugControl.add(save);
		bugControl.add(cancel);
	}

	/**
	 * 	F�r fileControl wird ein BoxLayout verwendet.
	 * 	F�r bugControl wird ein FlowLayout verwendet.
	 */
	private void setAdditionalLayout() {
		fileControl.setLayout(new BoxLayout(fileControl, BoxLayout.Y_AXIS));
		bugControl.setLayout(new FlowLayout());
	}
}
