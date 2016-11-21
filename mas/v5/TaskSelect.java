package mas.v5;

import javax.swing.JFileChooser;

public class TaskSelect extends UI{

	public void selectFolder() {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        	//get the path
        	selectedFile = fileChooser.getSelectedFile();
        	filePath = selectedFile.getAbsolutePath();
        }
	}
	
	
}
