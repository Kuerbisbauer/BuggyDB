package kb.gui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import kb.bugES.Bug;

public class Module_ListOfBugs extends JPanel {
	
	/*	###############################
	 * 	Konstante
	 * 	###############################
	 */
	private static final long serialVersionUID = 1L;

	
	/*	###############################
	 * 	Swing Komponenten
	 * 	###############################
	 */
	
	/**
	 * 	JList enth�lt eine Liste von Bugs
	 */
	private JList<Bug> bug = new JList<Bug>();
	
	private DefaultListModel<Bug> dlm = new DefaultListModel<Bug>();
	
	/**
	 * 	JScrollPane f�r die JList um mehr Bugs anzuzeigen.
	 */
	private JScrollPane scrollPane = new JScrollPane();
	
	/**
	 * 	<b>Kunstruktor</b><p>
	 * 	Baut die JList in das ScrollPane.<br>
	 * 	Layout wird auf BorderLayout gesetzt, damit die Liste
	 * 	den Platz ausf�llt. In die ScrollPane wird die JList
	 * 	hinzugef�gt.<br>
	 * 	Abschlie�end wird die Scrollpane im JPanel hinzugef�gt
	 */
	public Module_ListOfBugs(){
		
		//Layoutmanager
		setLayout(new BorderLayout());
		
		//JList wird in den ScrollPane Container gelegt
		scrollPane.setViewportView(bug);
		
		//ScrollPane wird in das JPanel hinzugef�gt
		this.add(scrollPane);
	}
}
