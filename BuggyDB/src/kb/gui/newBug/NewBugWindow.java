package kb.gui.newBug;

import javax.swing.JDialog;

public class NewBugWindow extends JDialog{

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
	private static final int HEIGTH 	= 200;
	
	/**
	 * Position des Fensters auf der X und Y Achse
	 */
	private static final int POSX 	= 100;
	private static final int POSY	= 20;
	
	/**
	 * 	<b>Kunstruktor</b><p>
	 * 	Legt die Größe und Position des Fensters fest.<br>
	 *	
	 */
	public NewBugWindow(){
		setBounds(WIDTH, HEIGTH, POSX, POSY);
	}
}
