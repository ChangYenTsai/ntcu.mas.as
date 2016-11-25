package mas.v5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskReverse extends UI{
	
	public void reverseContent() {
		
		for (final File fileEntry : selectedFile.listFiles()) {	
        	BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader( filePath + "\\" + fileEntry.getName()));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
    		try {			    		    
    		    String line = null;
    		    
				try {								
					line = br.readLine();														
				} catch (IOException e1) {
					e1.printStackTrace();
				}		
				
				List<String> storeLine = new ArrayList<String>();
				List<String> storeReverse = new ArrayList<String>();
				
				//store original file line
				while (line != null) {								
					storeLine.add(line);
					try {
						line = br.readLine();
					} catch (IOException e1) {
						e1.printStackTrace();
					}	
				}							
				//store reverse line
				for (int i=storeLine.size()-1; i>=0; i--) {
					storeReverse.add(storeLine.get(i));
					try {
						line = br.readLine();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				storeReverseName = fileEntry.getName();
				StoreReverse(storeReverse);
    		} finally {
    		    try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
    		}
		}
	System.out.println("Reversing done.");		
	}
	
}
