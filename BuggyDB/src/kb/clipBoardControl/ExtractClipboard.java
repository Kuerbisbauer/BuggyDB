package kb.clipBoardControl;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ExtractClipboard {

	private static final String file = "E:\\zeuch\\webwanderverein\\src\\fachlogik\\MitarbeiterVerwalten.java";

	//Zeilennummer
	private int lineNumber = 0;
	
	public ExtractClipboard(){
		
	}
	
	/**
	 * Öffnet das Standard Programm zur Datei
	 * 
	 * @throws IOException
	 */
	public void getLine() throws IOException{
		Desktop dt = Desktop.getDesktop();
		dt.open(new File(file));
	}
	
	/**
	 * Die Zwischenablage wird gelesen.
	 * 
	 * @throws UnsupportedFlavorException	-	Datentyp wird nicht unterstützt
	 * @throws IOException					-	Fehler beim Lesen der Datei
	 */
	public void getFile() throws UnsupportedFlavorException, IOException{
		
		//Die Zwischenablage wird in "result" gespeichert
		//Es wird nur Text unterstützt
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String result = (String) clipboard.getData(DataFlavor.stringFlavor);
		//Leerzeichen werden entfernt
		result.trim();
		
		
		//Liest jede Zeile ein und vergleicht diese mit dem Clipboard
		//Dabei wird jede Zeile gezählt und die entsprechende Zeile aufgeführt
		BufferedReader in = new BufferedReader(new FileReader(file));
		String s = "";
		
		//Jede Zeile wird gelesen bis der String im Clipboard der gleiche ist wie die Zeile
		while(!s.equals(result)){
			//Leerzeichen werden entfernt
			s = in.readLine().trim();
			if(s.equals(result))
				System.out.println(getLineNumber()+1 + ": " + s);
			
			//Zeilennummer wird mit 1 addiert
			addNumber();
		}
		in.close();
	}
	
	public void addNumber(){
		lineNumber++;
	}
	
	public void resetNumber(){
		setLineNumber(1);
	}
	
	
	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
}
