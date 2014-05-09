package kb.misc;

import java.io.Serializable;
import java.util.Locale;

import javax.swing.JMenuItem;

public class LanguageControl implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Locale local;
	
	/**
	 * 	Leerer Konstruktor
	 */
	public LanguageControl(){
		
	}
	
	/**
	 * Speichert die angeklickte Sprache
	 * 
	 * @param jMenuItem - daraus wird der String von der Sprache geholt um diesen als Indikator zu speichern
	 */
	public void saveLanguage(JMenuItem jMenuItem){
		String s = jMenuItem.getText();
		
		Locale locale = new Locale(s);
		System.out.println(locale.getDisplayLanguage(locale));
	}
	
	public void loadLanguage(){
		
	}
}
