package kb.clipBoardControl;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

public class ExtractClipboard {

	//private static final String file = "E:\\zeuch\\webwanderverein\\src\\fachlogik\\MitarbeiterVerwalten.java";
	
	//Zeilennummer
	private int lineNumber = 0;
	
	private String clipBoardString = "";
	
	/**
	 * <b>Konstruktor</b><p>
	 * Extrahiert den String im Clipboard und sucht die entsprechende Zeilennummer
	 */
	public ExtractClipboard(){
		
		try {
			setClipBoardString(getClipBoardString());
			//getLine(clipBoardString);
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Die Zwischenablage wird gelesen.
	 * 
	 * @throws UnsupportedFlavorException	-	Datentyp wird nicht unterstützt
	 * @throws IOException					-	Fehler beim Lesen der Datei
	 */
	private String getClipBoardString() throws UnsupportedFlavorException, IOException {
		//Die Zwischenablage wird in "result" gespeichert
		//Es wird nur Text unterstützt
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		
		if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
			String result = (String) clipboard.getData(DataFlavor.stringFlavor);
			//Leerzeichen werden entfernt
			result.trim();
			
			return result;
		}else{
			System.out.println("KEIN STRING!");
			return null;
		}
	}

	public void setClipBoardString(String clipBoardString) {
		this.clipBoardString = clipBoardString;
	}

	/**
	 * Eine Datei wird den Daten hinzugefügt.<br>
	 * Liest jede Zeile ein und vergleicht diese mit dem Clipboard.<br>
	 * Dabei wird jede Zeile gezählt und die entsprechende Zeile aufgeführt.
	 * @return 
	 * 
	 * @throws IOException
	 */
	public int getLine() throws IOException{
		//TODO Filechooser Gerät
		//FileChooser falls noch keine Datei ausgewählt wurde
		JFileChooser jfc = new JFileChooser();
		int returnValue = jfc.showOpenDialog();
		//Liest jede Zeile ein und vergleicht diese mit dem Clipboard
		//Dabei wird jede Zeile gezählt und die entsprechende Zeile aufgeführt
		BufferedReader in = new BufferedReader(new FileReader(file));
		String lineString = "";
		
		//Jede Zeile wird gelesen bis der String im Clipboard der gleiche ist wie die Zeile
		while(!lineString.equals(clipBoardString)){
			//Leerzeichen werden entfernt
			lineString = in.readLine().trim();
			
			//Zeilennummer wird mit 1 addiert
			addNumber();
			
			//Falls der ClipBoard String identisch mit der Zeile ist, so wird dieser mit der Zeilennummer
			//in der Console ausgegeben.
			if(lineString.equals(clipBoardString))
				System.out.println(getLineNumber() + 1 + ": " + lineString);
		}
		in.close();
		
		return getLineNumber();
	}
	
	
	public void getFile(){
		
		
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
