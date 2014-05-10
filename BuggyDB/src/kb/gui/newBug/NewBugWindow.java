package kb.gui.newBug;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kb.clipBoardControl.ExtractClipboard;
import kb.misc.SpecificFileButton;


public class NewBugWindow extends JDialog{
	//TODO MIGLAYOUT!
	/*	###############################
	 * 	Konstante
	 * 	###############################
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Größe des Fensters
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
	
	private JButton				clipBoard			= new JButton("Clipboard");
	private SpecificFileButton	externalFile		= new SpecificFileButton("Datei");
	private JButton 			externalFileLine	= new JButton("Zeile");
	
	private JButton	save				= new JButton("Speichern");
	private JButton cancel				= new JButton("Cancel");
	
	private JPanel fileControl	= new JPanel();
	private JPanel bugControl	= new JPanel();
	
	/**
	 * 	<b>Kunstruktor</b><p>
	 * 	Legt die Größe und Position des Fensters fest.<br>
	 * 	Der JDialog ist modul und muss somit geschlossen werden, bevor
	 * 	das Hauptfenster aktiv wird. 
	 */
	public NewBugWindow(){
		//Position und Größe des Fensters
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
	private void buildGUI(){
		
		//Für fileControl und bugControl werden unterschiedliche Layouts verwendet
		setAdditionalLayout();
		
		//SwingKomponenten werden hinzugefügt:
		//Titelleiste, TextEditorArea, fileControl, bugControl
		this.add(titleField, BorderLayout.NORTH);
		this.add(textAreaDescription, BorderLayout.CENTER);
		this.add(fileControl, BorderLayout.EAST);
		this.add(bugControl, BorderLayout.SOUTH);
		
		//Es werden drei Buttons zu FileControl hinzugefügt
		fileControl.add(clipBoard);
		fileControl.add(externalFile);
		fileControl.add(externalFileLine);
		
		//Der Save und Cancel Button werden zu bugControl hinzugefügt
		bugControl.add(save);
		bugControl.add(cancel);
		
		addActionListenerForButtons();
	}

	/**
	 * 	Actionlistener für folgende Buttons wird hinzugefügt:<br>
	 * 	<li>clipBoard
	 * 	<li>externalFile
	 * 	<li>externalFileLine
	 * 	<li>save
	 * 	<li>cancel
	 * @throws IOException 
	 */
	private ExtractClipboard extractClip = new ExtractClipboard();
	
	private void addActionListenerForButtons() {
		clipBoard.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshButtons(clipBoard, externalFile, externalFileLine);
				extractClip.resetNumber();
			}
		});
		
		externalFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				extractClip.chooseFile();
				refreshButtons(clipBoard, externalFile, externalFileLine);
				extractClip.resetNumber();
			}
		});
	}
	
	private void refreshButtons(JButton clipBoard, SpecificFileButton externalFile, JButton externalFileLine){

		File f = extractClip.getFile();
		
		//Zeilen werden gelesen
		if(extractClip.getFile() == null){
			extractClip.chooseFile();
			try {
				extractClip.lineReader();
			} catch (IOException e) {
				System.out.println("Fehler beim Lesen der Datei");
				e.printStackTrace();
			}
		}
		
		//Setzt die Zeilennummer bei Übereinstimmung in den Buttontext 
		externalFileLine.setText(String.valueOf(extractClip.getLineNumber()));
		
		//Der Dateiname wird in den Buttontext geschrieben
		externalFile.setText(extractClip.getFileName());
	}

	/**
	 * 	Für fileControl wird ein BoxLayout verwendet.
	 * 	Für bugControl wird ein FlowLayout verwendet.
	 */
	private void setAdditionalLayout() {
		fileControl.setLayout(new BoxLayout(fileControl, BoxLayout.Y_AXIS));
		bugControl.setLayout(new FlowLayout());
	}
}
