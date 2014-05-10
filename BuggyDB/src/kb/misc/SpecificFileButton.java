package kb.misc;

import java.io.File;

import javax.swing.JButton;

public class SpecificFileButton extends JButton{

	private static final long serialVersionUID = 1L;
	
	private File file;
	
	public SpecificFileButton(String string) {
		setText(string);
	}

	@Override
	public String toString() {
		String s = getFile().getAbsolutePath();
		String fileName = s.substring(s.lastIndexOf("\\")+1);
		
		return fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
		setText(toString());
	}
}
