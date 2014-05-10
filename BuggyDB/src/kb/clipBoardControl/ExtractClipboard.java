package kb.clipBoardControl;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

public class ExtractClipboard {

	//Zeilennummer
	private int lineNumber = 0;
	
	//Der String in der Zwischenablage
	private String clipBoardString = "";
	
	//Ausgewählte Datei
	private File file = null;
	
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
	 * Jede Zeile der Datei wird ausgelesen und mit dem ClipBoard String verglichen.<br>
	 * Falls der String übereinstimmt, so wird die Schleife beendet. Es wird die Zeilennummer mitgezählt.<br>
	 * 
	 * @throws IOException
	 */
	public void lineReader() throws IOException {
		//Liest jede Zeile ein und vergleicht diese mit dem Clipboard
		//Dabei wird jede Zeile gezählt und die entsprechende Zeile aufgeführt
		BufferedReader in = new BufferedReader(new FileReader(chooseFile()));
		String lineString = "";
		
		//Jede Zeile wird gelesen bis der String im Clipboard der gleiche ist wie die Zeile
		while(!lineString.equals(clipBoardString)){
			//Leerzeichen werden entfernt
			lineString = in.readLine().trim();
			
			//Falls der ClipBoard String identisch mit der Zeile ist, so wird dieser mit der Zeilennummer
			//in der Console ausgegeben.
			if(lineString.equals(clipBoardString))
				System.out.println(lineNumber + 1 + ": " + lineString);
			
			//Zeilennummer wird mit 1 addiert
			addNumber();
		}
		in.close();
	}
	
	/**
	 * Eine Datei kann mittels FileChooser ausgewählt werden
	 * @return 
	 */
	public File chooseFile(){
		JFileChooser jfc = new JFileChooser();
		int returnValue = jfc.showOpenDialog(null);
		
		if(returnValue == JFileChooser.APPROVE_OPTION){
			setFile(jfc.getSelectedFile());
		}else{
			setFile(null);
		}
		
		return getFile();
	}
	
	/**
	 * Der Dateiname inklusive Dateieindung wird aus dem absoluten Pfad extrahiert
	 * 
	 * @return - Dateiname inkl. Dateiendung
	 */
	public String getFileName(){
		String s = getFile().getAbsolutePath();
		String fileName = s.substring(s.lastIndexOf("\\")+1); 
		
		return fileName;
	}

	public void addNumber(){
		lineNumber++;
	}
	
	public void resetNumber(){
		setLineNumber(1);
	}
	
	/*	###############################
	 * 	Getter & Setter
	 * 	###############################
	 */
	public File getFile(){
		return file;
	}
	
	public void setFile(File file){
		this.file = file;
	}
	
	public int getLineNumber(){
		return lineNumber;
	}
	
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
}
